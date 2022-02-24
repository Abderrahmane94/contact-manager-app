package be.polyscripts.contactmanagerapp.exceptions;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(String message) {
        super(message);
    }
}