package webapp.service;

import webapp.domain.Post;
import webapp.repository.PostRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = ApplicationTest.class)
public class PostServiceUnitTest {

//    @Autowired
    private PostService postService;

    private PostRepository postRepository= Mockito.mock(PostRepository.class);

    private String names[] = {"nishishazima" , "hahaha", "Goodbye886", "BenBen", "xiaoming"};

    private List<Post>posts = new ArrayList();

    @Before
    public void setUp(){
        Post p1 = new Post("xiaoming", new Timestamp(20000), "xiaoming's post");
        p1.setId(2L);
        Post p2 = new Post("BenBen", new Timestamp(10000), "BenBen's post");
        p2.setId(1L);

        posts.add(p2);
        posts.add(p1);

        Mockito.when(postRepository.findAllByOrderByIdDesc()).thenReturn(posts);
        postService = new PostService(postRepository);
    }

    @Test
    public void sanity(){
        assertNotNull(postRepository);
    }

    @Test
    public void test_get_all_posts(){
        Mockito.when(postRepository.findAllByOrderByIdDesc()).thenReturn(posts);
        assertEquals(2,postService.getAllPosts().size());
    }

    @Test
    public void test_get_all_posts_ordered(){
        Mockito.when(postRepository.findAllByOrderByIdDesc()).thenReturn(posts);
        assertEquals("BenBen's post",postService.getAllPosts().get(0).getContent());
    }

    @Test
    public void test_get_new_post(){
        assertThat(postService.getNewPost().getUsername(),
                anyOf(equalTo("nishishazima"),equalTo("hahaha"),
                        equalTo("Goodbye886"),equalTo("xiaoming"),equalTo("BenBen")));
    }

    @Test
    public void test_save_new_post(){
        Post post = posts.get(0);
        Mockito.when(postRepository.save(post)).thenReturn(post);
        assertEquals("BenBen's post",postService.insertNewPost(post).getContent());
    }
}