package mz.com.dimasoft.smartlibrary.dto;

import mz.com.dimasoft.smartlibrary.domain.entities.Transaction;
import java.util.UUID;

public class TransactionDTO {
    private UUID id;
    private String name;
    private String route;
    private String icon;

    public TransactionDTO() {}
    public TransactionDTO(Transaction entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.route = entity.getRoute();
        this.icon = entity.getIcon();
    }
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }
    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
}
