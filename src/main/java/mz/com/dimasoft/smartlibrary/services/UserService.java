package mz.com.dimasoft.smartlibrary.services;
import mz.com.dimasoft.smartlibrary.domain.entities.User;
import mz.com.dimasoft.smartlibrary.dto.UserDTO;
import mz.com.dimasoft.smartlibrary.dto.ProfileDTO;
import mz.com.dimasoft.smartlibrary.domain.entities.Profile;
import mz.com.dimasoft.smartlibrary.repositories.UserRepository;
import mz.com.dimasoft.smartlibrary.repositories.ProfileRepository;
import mz.com.dimasoft.smartlibrary.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private ProfileRepository profileRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDTO findById(UUID id) {
        User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        // Default password for testing
        entity.setPassword("$2a$10$D/R38q5C72381yQo2f/LauPqDDEd6K1d.6Z69Wk4fJvP2V7N1cZVy"); 
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(UUID id, UserDTO dto) {
        User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setTaxId(dto.getTaxId());
        if(dto.getActive() != null) {
            entity.setActive(dto.getActive());
        } else {
            entity.setActive(true);
        }
        entity.getProfiles().clear();
        if (dto.getProfiles() != null) {
            for (ProfileDTO profileDto : dto.getProfiles()) {
                Profile profile = profileRepository.findById(profileDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Profile not found: " + profileDto.getId()));
                entity.getProfiles().add(profile);
            }
        }
    }
}
