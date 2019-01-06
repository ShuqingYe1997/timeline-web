package webapp.repository;

import webapp.ApplicationTest;
import webapp.domain.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class PostRepositoryTest {

    // No autowired!
    private PostRepository postRepository = Mockito.mock(PostRepository.class);

    @Test
    public void sanity(){
        assertNotNull(postRepository);
    }

    @Test
    public void testGetAllPosts() {
        List<Post>posts = new ArrayList();
        Post p1 = new Post("xiaoming", new Timestamp(20000), "xiaoming's post");
        p1.setId(2L);
        Post p2 = new Post("BenBen", new Timestamp(10000), "BenBen's post");
        p2.setId(1L);

        posts.add(p2);
        posts.add(p1);
        // GIVEN
        Mockito.when(postRepository.findAllByOrderByIdDesc())
                .thenReturn(posts);

        // WHEN

        // THEN
        assertEquals(2,postRepository.findAllByOrderByIdDesc().size());
        assertThat(postRepository.findAllByOrderByIdDesc().get(0).getId(),is(1L));
    }


}