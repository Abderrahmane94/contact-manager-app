package be.polyscripts.contactmanagerapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;


    @Column(name = "uuid", updatable = false, unique = true)
    private UUID uuid;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "tva", nullable = false)
    private String tva; // example: TVA BE 0123 456 789

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "company_contacts",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "contacts_id"))
    private Set<Contact> contacts = new HashSet<>();
}