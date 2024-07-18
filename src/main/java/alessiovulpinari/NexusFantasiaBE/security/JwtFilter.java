package alessiovulpinari.NexusFantasiaBE.security;

import alessiovulpinari.NexusFantasiaBE.entities.User;
import alessiovulpinari.NexusFantasiaBE.exceptions.ErrorsPayload;
import alessiovulpinari.NexusFantasiaBE.exceptions.UnauthorizedException;
import alessiovulpinari.NexusFantasiaBE.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private UserService userService;

    private final ObjectMapper objectMapper;

    public JwtFilter() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer "))
                throw new UnavailableException("Per favore inserisci correttamente il token nell'header");

            String accessToken = authHeader.substring(7);

            jwtTools.verifyToken(accessToken);

            String id = jwtTools.extractIdFromToken(accessToken);
            User user = userService.getUser(UUID.fromString(id));

            Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);

        } catch (UnauthorizedException exception) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            ErrorsPayload errorsPayload = new ErrorsPayload(exception.getMessage(), LocalDateTime.now());
            String jsonResponse= objectMapper.writeValueAsString(errorsPayload);
            response.getWriter().write(jsonResponse);
        } catch (Exception exception) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json");
            response.getWriter().write(exception.getMessage());
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/api/auth/**", request.getServletPath());
    }
}
