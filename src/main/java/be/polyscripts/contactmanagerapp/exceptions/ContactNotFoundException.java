package be.polyscripts.contactmanagerapp.exceptions;

public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(String message) {
        super(message);
    }
}