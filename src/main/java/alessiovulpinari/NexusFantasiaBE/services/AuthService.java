package alessiovulpinari.NexusFantasiaBE.services;

import alessiovulpinari.NexusFantasiaBE.entities.User;
import alessiovulpinari.NexusFantasiaBE.exceptions.UnauthorizedException;
import alessiovulpinari.NexusFantasiaBE.payloads.UserLoginDTO;
import alessiovulpinari.NexusFantasiaBE.security.JwtTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService utenteServices;

    @Autowired
    private JwtTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String generateToken(UserLoginDTO uld) {
        User utente = utenteServices.findByEmail(uld.email());

        if (bcrypt.matches(uld.password(), utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali errate");
        }
    }
}
