package be.polyscripts.contactmanagerapp.repo;

import be.polyscripts.contactmanagerapp.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactRepository extends JpaRepository<Contact, Long> {
}