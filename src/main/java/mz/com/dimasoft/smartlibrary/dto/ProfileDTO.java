package mz.com.dimasoft.smartlibrary.dto;

import mz.com.dimasoft.smartlibrary.domain.entities.Profile;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class ProfileDTO {
    private UUID id;
    private String name;
    private Set<TransactionDTO> transactions = new HashSet<>();

    public ProfileDTO() {}
    public ProfileDTO(Profile entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        if (entity.getTransactions() != null) {
            this.transactions = entity.getTransactions().stream().map(TransactionDTO::new).collect(Collectors.toSet());
        }
    }
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<TransactionDTO> getTransactions() { return transactions; }
    public void setTransactions(Set<TransactionDTO> transactions) { this.transactions = transactions; }
}
