package be.polyscripts.contactmanagerapp.repo;

import be.polyscripts.contactmanagerapp.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact findContactById(Long aLong);
}