package ecnu.domain;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;

/**
 * Created by smcsvip on 2019/1/3.
 */
public class PostTest {
    private Post post;
    @Before
    public void setUp() throws Exception {
        post = new Post("22",new Timestamp(System.currentTimeMillis()-10000000),"sb");
    }

    @Test
    public void testFormat(){
        System.out.println(post.timestampToString());
    }

}