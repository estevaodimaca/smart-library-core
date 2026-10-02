package mz.com.dimasoft.smartlibrary.dto;

import mz.com.dimasoft.smartlibrary.domain.entities.User;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class UserDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String taxId;
    private String email;
    private Boolean active;
    private Set<ProfileDTO> profiles = new HashSet<>();

    public UserDTO() {}
    public UserDTO(User entity) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.taxId = entity.getTaxId();
        this.email = entity.getEmail();
        this.active = entity.getActive();
        if (entity.getProfiles() != null) {
            this.profiles = entity.getProfiles().stream().map(ProfileDTO::new).collect(Collectors.toSet());
        }
    }
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getTaxId() { return taxId; }
    public void setTaxId(String taxId) { this.taxId = taxId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public Set<ProfileDTO> getProfiles() { return profiles; }
    public void setProfiles(Set<ProfileDTO> profiles) { this.profiles = profiles; }
}
