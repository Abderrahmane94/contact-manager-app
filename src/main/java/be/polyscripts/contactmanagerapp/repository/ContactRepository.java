package be.polyscripts.contactmanagerapp.repository;

import be.polyscripts.contactmanagerapp.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Contact findContactById(Long contactID);

    Contact findContactByUuid(UUID uuid);

    Boolean existsContactByUuid(UUID uuid);

    void deleteContactByUuid(UUID uuid);
}