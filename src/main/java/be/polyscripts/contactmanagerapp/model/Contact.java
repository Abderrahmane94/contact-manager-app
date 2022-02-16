package be.polyscripts.contactmanagerapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "tva")
    private String tva; // example: TVA BE 0123 456 789

    @Column(name = "type")
    private String type;

    @ManyToMany(mappedBy = "contacts")
    private Set<Company> companies = new HashSet<>();

    public void addCompany(Company company) {
        this.companies.add(company);
        company.getContacts().add(this);
    }

    public void removeCompany(Company company) {
        this.companies.remove(company);
        company.getContacts().remove(this);
    }

}