package be.polyscripts.contactmanagerapp.validator;

import be.polyscripts.contactmanagerapp.exceptions.FreelanceWithNoTVAException;
import be.polyscripts.contactmanagerapp.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactValidator {

    public void validate(Contact contact) {
        if (isFreelanceWithNoTVA(contact)) {
            throw new FreelanceWithNoTVAException("Freelance contact must have a TVA number !");
        }
    }

    private boolean isFreelanceWithNoTVA(Contact contact) {
        return "freelance".equals(contact.getType()) && (contact.getTva() == null || contact.getTva().isEmpty());
    }
}
