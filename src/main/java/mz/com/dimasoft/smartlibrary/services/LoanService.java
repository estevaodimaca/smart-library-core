package mz.com.dimasoft.smartlibrary.services;

import mz.com.dimasoft.smartlibrary.domain.entities.Book;
import mz.com.dimasoft.smartlibrary.domain.entities.Loan;
import mz.com.dimasoft.smartlibrary.domain.entities.User;
import mz.com.dimasoft.smartlibrary.domain.enums.LoanStatus;
import mz.com.dimasoft.smartlibrary.dto.LoanDTO;
import mz.com.dimasoft.smartlibrary.repositories.BookRepository;
import mz.com.dimasoft.smartlibrary.repositories.LoanRepository;
import mz.com.dimasoft.smartlibrary.repositories.UserRepository;
import mz.com.dimasoft.smartlibrary.services.exceptions.BusinessException;
import mz.com.dimasoft.smartlibrary.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoanService {
    
    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);
    
    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public LoanDTO createLoan(UUID userId, UUID bookId) {
        logger.info("Iniciando processo de empréstimo para usuário {} e livro {}", userId, bookId);
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + userId));
                
        if (!Boolean.TRUE.equals(user.getActive())) {
            throw new BusinessException("O usuário está inativo e não pode realizar operações.");
        }
                
        if (Boolean.TRUE.equals(user.getHasActiveFine())) {
            throw new BusinessException("Usuário possui multas pendentes. Novos empréstimos estão bloqueados.");
        }
        
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado: " + bookId));
                
        if (book.getAvailableCopies() <= 0) {
            throw new BusinessException("Não há cópias disponíveis no momento para este livro.");
        }
        
        // Baixa no estoque
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        
        // Criação do empréstimo
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setCheckoutDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusDays(14)); // 14 dias de prazo
        loan.setStatus(LoanStatus.PENDING);
        
        loan = loanRepository.save(loan);
        
        logger.debug("Empréstimo processado e persistido com sucesso na base de dados.");
        return new LoanDTO(loan);
    }
    
    @Transactional(readOnly = true)
    public List<LoanDTO> findByUserId(UUID userId) {
        return loanRepository.findByUserId(userId).stream()
                .map(LoanDTO::new)
                .collect(Collectors.toList());
    }
}
