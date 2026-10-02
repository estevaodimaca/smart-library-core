package mz.com.dimasoft.smartlibrary.dto;

import mz.com.dimasoft.smartlibrary.domain.entities.Loan;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public class LoanDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID userId;
    private String bookTitle;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private String status;

    public LoanDTO() {}

    public LoanDTO(Loan entity) {
        this.id = entity.getId();
        this.userId = entity.getUser().getId();
        this.bookTitle = entity.getBook().getTitle();
        this.checkoutDate = entity.getCheckoutDate();
        this.dueDate = entity.getDueDate();
        this.status = entity.getStatus().name();
    }
    
    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public String getBookTitle() { return bookTitle; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public LocalDate getDueDate() { return dueDate; }
    public String getStatus() { return status; }
}
