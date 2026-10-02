package mz.com.dimasoft.smartlibrary.domain.entities;

import jakarta.persistence.*;
import mz.com.dimasoft.smartlibrary.domain.enums.FineStatus;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "fines")
public class Fine extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    
    @Column(name = "days_late", nullable = false)
    private Integer daysLate;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FineStatus status;

    public Fine() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Loan getLoan() { return loan; }
    public void setLoan(Loan loan) { this.loan = loan; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public Integer getDaysLate() { return daysLate; }
    public void setDaysLate(Integer daysLate) { this.daysLate = daysLate; }

    public FineStatus getStatus() { return status; }
    public void setStatus(FineStatus status) { this.status = status; }
}
