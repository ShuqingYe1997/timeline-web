package webapp.service;

import webapp.domain.Post;
import webapp.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * Created by smcsvip on 2018/12/27.
 */

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Transactional
    public List<Post> getAllPosts(){
        return postRepository.findAllByOrderByIdDesc();
    }

    @Transactional
    public Post getNewPost(){
        String names[] = {"nishishazima" , "hahaha", "Goodbye886", "BenBen", "xiaoming"};

        String contents[]={"President Trump’s handling of Border Security - 62% Approval Rating. On being a strong leader - 59%.\nAP Poll.\nThank you!",
                "One thing has now been proven. The Democrats do not care about Open Borders and all of the crime and drugs that Open Borders bring!",
                "The Democrats, much as I suspected, have allocated no money for a new Wall. So imaginative! The problem is, without a Wall there can be no real Border Security - and our Country must finally have a Strong and Secure Southern Border!",
                "HAPPY NEW YEAR TO EVERYONE, INCLUDING THE HATERS AND THE FAKE NEWS MEDIA! 2019 WILL BE A FANTASTIC YEAR FOR THOSE NOT SUFFERING FROM TRUMP DERANGEMENT SYNDROME.\n JUST CALM DOWN AND ENJOY THE RIDE, GREAT THINGS ARE HAPPENING FOR OUR COUNTRY!",
                "A train accident on the Great Belt Bridge in #Denmark kills 6 people. Rail network officials said the commuter train braked suddenly after hit by debris from a freight train during a heavy storm."
        };

        String pics[] = {"https://pbs.twimg.com/media/Dv-Ezj3VsAENd91.jpg:large",
        "http://img2.chinadaily.com.cn/images/201901/02/5c2cd84ba310d9126fdc9414.jpeg",
        "https://www.piccoloteatro.org/uploads/seasons/2018-2019/it_logo-18-19-912x395_original.jpg"};

        Random random=new Random();
        int i = random.nextInt(names.length);
        int j = random.nextInt(contents.length);
        int k = random.nextInt(pics.length);

        Timestamp timestamp= new Timestamp(System.currentTimeMillis());

        Post post = new Post(names[i],timestamp,contents[j],pics[k]);
        return post;
    }

    @Transactional
    public Post insertNewPost(Post post){
        return postRepository.save(post);
    }
}
