package mz.com.dimasoft.smartlibrary.services;
import mz.com.dimasoft.smartlibrary.domain.entities.Profile;
import mz.com.dimasoft.smartlibrary.dto.ProfileDTO;
import mz.com.dimasoft.smartlibrary.dto.TransactionDTO;
import mz.com.dimasoft.smartlibrary.domain.entities.Transaction;
import mz.com.dimasoft.smartlibrary.repositories.ProfileRepository;
import mz.com.dimasoft.smartlibrary.repositories.TransactionRepository;
import mz.com.dimasoft.smartlibrary.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository repository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public List<ProfileDTO> findAll() {
        return repository.findAll().stream().map(ProfileDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProfileDTO findById(UUID id) {
        Profile entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new ProfileDTO(entity);
    }

    @Transactional
    public ProfileDTO insert(ProfileDTO dto) {
        Profile entity = new Profile();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProfileDTO(entity);
    }

    @Transactional
    public ProfileDTO update(UUID id, ProfileDTO dto) {
        Profile entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new ProfileDTO(entity);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ProfileDTO dto, Profile entity) {
        entity.setName(dto.getName());
        entity.getTransactions().clear();
        if (dto.getTransactions() != null) {
            for (TransactionDTO transDto : dto.getTransactions()) {
                Transaction transaction = transactionRepository.findById(transDto.getId())
                        .orElseThrow(() -> new ResourceNotFoundException("Transaction not found: " + transDto.getId()));
                entity.getTransactions().add(transaction);
            }
        }
    }
}
