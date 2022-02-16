package be.polyscripts.contactmanagerapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "tva", nullable = false)
    private String tva; // example: TVA BE 0123 456 789

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "company_contacts",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "contacts_id"))
    private Set<Contact> contacts = new HashSet<>();

}