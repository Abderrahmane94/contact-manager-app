package be.polyscripts.contactmanagerapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;


    @Column(name = "uuid", updatable = false, unique = true)
    private UUID uuid;

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

    @JsonIgnore
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