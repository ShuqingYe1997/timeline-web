package webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
//    @Bean
//    CommandLineRunner demo(UserRepository userRepository, PostRepository postRepository) {
//        return args -> {
//            userRepository.deleteAll();
//            postRepository.deleteAll();
//
//            User u1 = new User("xiaoming","12345678");
//            User u2 = new User("BenBen","11111111");
//            User u3 = new User("Goodbye886","Goodbye886");
//            User u4 = new User("hahaha","123456");
//            User u5 = new User("nishishazima","bugaosuni");
//
//            Timestamp ts1 = new Timestamp(System.currentTimeMillis()-1000000000);
//            Timestamp ts2 = new Timestamp(System.currentTimeMillis()-10000000);
//            Timestamp ts3 = new Timestamp(System.currentTimeMillis()-100000);
//            Timestamp ts4 = new Timestamp(System.currentTimeMillis()-10000);
//            Timestamp ts5 = new Timestamp(System.currentTimeMillis()-5000);
//            Timestamp ts6 = new Timestamp(System.currentTimeMillis()-1000);
//
//            Post p1 = new Post(u1.getUsername(),ts1,"Hello!" ,
//                    "https://www.html5up.net/uploads/demos/future-imperfect/images/pic01.jpg");
//            Post p2 = new Post(u2.getUsername(),ts2,"Nice 2 meet U",
//                    "https://www.html5up.net/uploads/demos/future-imperfect/images/pic02.jpg");
//            Post p3 = new Post(u3.getUsername(),ts3,"I'm a big big girl in the big big world.",
//                    "https://www.html5up.net/uploads/demos/future-imperfect/images/pic03.jpg");
//            Post p4 = new Post(u4.getUsername(),ts4,"Lately I been, I been losing sleep." +
//                    "Dreaming about the things that we could be." +
//                    "But baby I been, I been prayin' hard." +
//                    "Said no more counting dollars." +
//                    "We'll be counting stars." +
//                    "Yeah, we'll be counting stars" ,
//                    "https://www.html5up.net/uploads/demos/future-imperfect/images/pic01.jpg");
//            Post p5 = new Post(u5.getUsername(),ts5,"I see this life." +
//                    "Like a swinging vine." +
//                    "Swing my heart across the line." +
//                    "In my faces flashing signs." +
//                    "Seek it out and ye shall find." +
//                    "The old, but I'm not that old." +
//                    "Young, but I'm not that bold." +
//                    "And I don't think the world is sold." +
//                    "I'm just doing what we're told",
//                    "https://www.html5up.net/uploads/demos/future-imperfect/images/pic02.jpg");
//            Post p6 = new Post(u3.getUsername(),ts6,"I, feel something so right." +
//                    "Doing the wrong thing." +
//                    "I, feel something so wrong." +
//                    "But doing the right thing." +
//                    "I could lie, could lie, could lie." +
//                    "Everything that kills me makes me feel alive",
//                    "https://www.html5up.net/uploads/demos/future-imperfect/images/pic03.jpg");
//
//            userRepository.save(u1);
//            userRepository.save(u2);
//            userRepository.save(u3);
//            userRepository.save(u4);
//            userRepository.save(u5);
//
//            postRepository.save(p1);
//            postRepository.save(p2);
//            postRepository.save(p3);
//            postRepository.save(p4);
//            postRepository.save(p5);
//            postRepository.save(p6);
//
//        };
//    }

       public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
