package ecnu.repository;

import ecnu.domain.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Transactional
public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testPostsByUsername(){

    }
}