package be.polyscripts.contactmanagerapp.repository;

import be.polyscripts.contactmanagerapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String userName);
}