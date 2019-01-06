package webapp.repository;

import webapp.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByUsername(String username);
    boolean existsByUsername(String username);
    User save(User user);
}
