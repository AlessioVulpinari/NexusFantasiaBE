package alessiovulpinari.NexusFantasiaBE.controllers;

import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.payloads.UserDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.UserLoginDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.UserLoginResponseDTO;
import alessiovulpinari.NexusFantasiaBE.payloads.UserRegistrationResponseDTO;
import alessiovulpinari.NexusFantasiaBE.services.AuthService;
import alessiovulpinari.NexusFantasiaBE.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userServices;

    @PostMapping("/user/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(authService.generateToken(body));
    }

    @PostMapping("/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegistrationResponseDTO registerUser(@RequestBody @Validated UserDTO body, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors());
        }

        return new UserRegistrationResponseDTO(this.userServices.saveUser(body).getUserId());
    }
}
