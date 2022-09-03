package app.repositories;

import app.models.UserDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {

    Optional<UserDetails> findByUsername(String username);
}
