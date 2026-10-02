package mz.com.dimasoft.smartlibrary.repositories;
import mz.com.dimasoft.smartlibrary.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {}
