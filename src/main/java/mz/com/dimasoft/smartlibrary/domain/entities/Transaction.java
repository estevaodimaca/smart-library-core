package mz.com.dimasoft.smartlibrary.domain.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private String name;
    
    private String route;
    private String icon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Transaction parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<Transaction> subTransactions = new HashSet<>();

    public Transaction() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public Transaction getParent() { return parent; }
    public void setParent(Transaction parent) { this.parent = parent; }

    public Set<Transaction> getSubTransactions() { return subTransactions; }
}
