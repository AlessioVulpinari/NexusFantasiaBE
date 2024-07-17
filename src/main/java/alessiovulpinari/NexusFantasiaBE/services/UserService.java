package alessiovulpinari.NexusFantasiaBE.services;

import alessiovulpinari.NexusFantasiaBE.entities.User;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.UserDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleService userRoleService;

    public User getUser(UUID id) {
       return userRepository.findById(id).orElseThrow(() -> new  NotFoundException("Utente con questo id non trovato"));
    }

    public User saveUser(UserDTO body) {
        this.userRepository.findByEmail(body.email()).ifPresent(user ->
        {
            throw new BadRequestException("Esiste già un utente con questa email: " + body.email());
        });

        this.userRepository.findByUsername(body.username()).ifPresent(user -> {
            throw new BadRequestException("Esiste già un utente con questo username: " + body.username());
        });

        User newUser = new User(body.username(), body.email(), passwordEncoder.encode(body.password()), body.name(), body.surname());

        newUser.getUserRoles().add(userRoleService.findByName("USER"));
        return userRepository.save(newUser);
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("utente con questa email non trovato"));
    }

}
