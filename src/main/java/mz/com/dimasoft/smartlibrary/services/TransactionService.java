package mz.com.dimasoft.smartlibrary.services;
import mz.com.dimasoft.smartlibrary.domain.entities.Transaction;
import mz.com.dimasoft.smartlibrary.dto.TransactionDTO;
import mz.com.dimasoft.smartlibrary.repositories.TransactionRepository;
import mz.com.dimasoft.smartlibrary.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    @Transactional(readOnly = true)
    public List<TransactionDTO> findAll() {
        return repository.findAll().stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TransactionDTO findById(UUID id) {
        Transaction entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new TransactionDTO(entity);
    }

    @Transactional
    public TransactionDTO insert(TransactionDTO dto) {
        Transaction entity = new Transaction();
        entity.setName(dto.getName());
        entity.setRoute(dto.getRoute());
        entity.setIcon(dto.getIcon());
        entity = repository.save(entity);
        return new TransactionDTO(entity);
    }

    @Transactional
    public TransactionDTO update(UUID id, TransactionDTO dto) {
        Transaction entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
        entity.setName(dto.getName());
        entity.setRoute(dto.getRoute());
        entity.setIcon(dto.getIcon());
        entity = repository.save(entity);
        return new TransactionDTO(entity);
    }

    @Transactional
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
