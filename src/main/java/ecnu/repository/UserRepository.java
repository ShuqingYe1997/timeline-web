package ecnu.repository;

import ecnu.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByUsername(String username);
    boolean existsByUsername(String username);
}
