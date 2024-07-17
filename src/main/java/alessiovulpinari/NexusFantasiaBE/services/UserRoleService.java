package alessiovulpinari.NexusFantasiaBE.services;

import alessiovulpinari.NexusFantasiaBE.entities.UserRole;
import alessiovulpinari.NexusFantasiaBE.exceptions.BadRequestException;
import alessiovulpinari.NexusFantasiaBE.exceptions.NotFoundException;
import alessiovulpinari.NexusFantasiaBE.payloads.UserRoleDTO;
import alessiovulpinari.NexusFantasiaBE.repositories.UserRoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepo;

    public UserRole findByName(String name) {
        return this.userRoleRepo.findByName(name).orElseThrow(() -> new BadRequestException("Il ruolo " + name + " non esiste"));
    }

    public UserRole saveRole(UserRoleDTO body) {
        this.userRoleRepo.findByName(body.name()).ifPresent(userRole ->
        {throw new BadRequestException("Esiste già un ruolo con questo nome: " + body.name());});

        UserRole newRole = new UserRole(body.name());
        return userRoleRepo.save(newRole);
    }

    public Page<UserRole> getRoles(int pageNumber, int pageSize) {
        if (pageSize <= 0) pageSize =10;
        if (pageSize >= 50) pageSize = 50;
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        return userRoleRepo.findAll(pageable);
    }

    public UserRole getRole(UUID id) {
        Optional<UserRole> optionalUser = userRoleRepo.findById(id);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new NotFoundException("Ruolo con questo id non trovato");
        }
    }

    public void findByIdAndDelete(UUID roleId) {
        UserRole found = findById(roleId);
        this.userRoleRepo.delete(found);
    }

    public UserRole findById(UUID roleId) {
        return this.userRoleRepo.findById(roleId).orElseThrow(() -> new NotFoundException(roleId.toString()));
    }

    public UserRole findByIdAndUpdate(UUID roleId, UserRoleDTO body)
    {
        UserRole found = findById(roleId);
        found.setName(body.name());
        return userRoleRepo.save(found);
    }
}
