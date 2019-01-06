package webapp.repository;

import webapp.domain.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface PostRepository extends CrudRepository<Post, Integer> {
    List<Post> findAllByOrderByIdDesc();
    Post save(Post post);
}
