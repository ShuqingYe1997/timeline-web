package webapp.domain;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by smcsvip on 2019/1/3.
 */
public class PostTest {
    private Post post;
    @Before
    public void setUp() throws Exception {
        post = new Post("username",new Timestamp(System.currentTimeMillis()),"This is the content.");
    }

    @Test
    public void test_running_time_less_than_10_ms(){
        assertThat(post.getTimeStamp(),is(not(new Timestamp(System.currentTimeMillis()-10))));
    }

    @Test
    public void test_timestamp_1_sec_ago(){
        post.setTimeStamp(new Timestamp(System.currentTimeMillis()-1000));
        assertEquals("1"+" sec ago",post.timestampToString());
    }

    @Test
    public void test_timestamp_5_min_ago(){
        post.setTimeStamp(new Timestamp(System.currentTimeMillis()-5*60*1000));
        assertEquals("5"+" min ago",post.timestampToString());
    }

    @Test
    public void test_timestamp_1_hr_ago(){
        post.setTimeStamp(new Timestamp(System.currentTimeMillis()-60*60*1000));
        assertEquals("1"+" hr ago",post.timestampToString());
    }

    @Test
    public void test_timestamp_yesterday_24h_ago(){
        post.setTimeStamp(new Timestamp(System.currentTimeMillis()-24*60*60*1000));
        assertEquals("yesterday",post.timestampToString());
    }

    @Test
    public void test_timestamp_yesterday_25h_ago(){
        post.setTimeStamp(new Timestamp(System.currentTimeMillis()-25*60*60*1000));
        assertEquals("yesterday",post.timestampToString());
    }

    @Test
    public void test_timestamp_1_m_ago(){
        post.setTimeStamp(new Timestamp(System.currentTimeMillis()-30*24*60*60*1000L));
        assertEquals("1"+" m ago",post.timestampToString());
    }

    @Test
    public void test_timestamp_1_yr_ago(){
        post.setTimeStamp(new Timestamp(System.currentTimeMillis()-12*30*24*60*60*1000L));
        assertEquals("1"+" yr ago",post.timestampToString());
    }

    @Test
    public void why_bother_writing_this_to_test_username(){
        assertEquals("username",post.getUsername());
    }

    @Test
    public void why_bother_writing_this_to_test_content(){
        assertEquals("This is the content.",post.getContent());

    }

    @Test
    public void why_bother_writing_this_to_test_pic(){
        assertNull(post.getPicture());
    }

    @Test
    public void why_bother_writing_this_to_test_id(){
        assertNull(post.getId());
    }

}