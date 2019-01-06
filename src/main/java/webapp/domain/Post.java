package webapp.domain;

import webapp.utils.TimeDisplay;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String username;

    private Timestamp timeStamp;

    private String content;

    private String picture;

    public Post(){}

    public Post(String username, Timestamp timeStamp,String content){
        this.username = username;
        this.timeStamp = timeStamp;
        this.content = content;
    }

    public Post(String username, Timestamp timeStamp, String content, String picture){
        this.username = username;
        this.timeStamp = timeStamp;
        this.content = content;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public String getPicture() {
        return picture;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public String getUsername() {
        return username;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String timestampToString(){
        return TimeDisplay.format(this.timeStamp);
    }

}
