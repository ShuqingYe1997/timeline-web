package webapp.controller;

import webapp.ApplicationTest;
import webapp.domain.Post;
import webapp.domain.User;
import webapp.service.LoginService;
import webapp.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = ApplicationTest.class)
public class UserActionControllerTest {
    @Autowired
    protected WebApplicationContext wac;

    private MockMvc mockMvc;

    private User user;

    private List<Post> posts = new ArrayList<>();

    private Post post;

    private HashMap<String, Object> sessionAttr = new HashMap<String, Object>();

    @MockBean
    private LoginService loginService ;

    @MockBean
    private PostService postService;


    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        // MyTimeline.html asks for full session attributes
        user = new User("F0rdMustang","BestP0nyCar");
        post = new Post(user.getUsername(),new Timestamp(System.currentTimeMillis()),"1");
        posts.add(post);

        sessionAttr.put("user",user);
        sessionAttr.put("allPosts",posts);
    }

    @Test
    public void test_refresh() throws Exception{
        post.setContent("2");
        Mockito.when(postService.getNewPost()).thenReturn(post);
        Mockito.when(postService.insertNewPost(Mockito.any())).thenReturn(post);

        MvcResult result = mockMvc.perform(
                get("/demo/refresh")
                        .sessionAttrs(sessionAttr))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("allPosts"))
                .andReturn();
        MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
        List<Post> postList = (List<Post>)session.getAttribute("allPosts");
        assertEquals(2,postList.size());
        assertEquals("2",postList.get(0).getContent());
    }

    @Test
    public void test_logout() throws Exception{
        MvcResult result = mockMvc.perform(
                get("/demo/logout")
                        .sessionAttrs(sessionAttr))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpSession session = (MockHttpSession) result.getRequest().getSession();
        assertNull(session.getAttribute("user"));
        assertNull(session.getAttribute("allPosts"));
    }
}
