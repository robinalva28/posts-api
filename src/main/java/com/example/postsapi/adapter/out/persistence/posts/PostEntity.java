package com.example.postsapi.adapter.out.persistence.posts;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Post")
@Table(name = "posts")
public class PostEntity {

    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence"
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "post_sequence"
    )
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "title", nullable = false, columnDefinition = "TEXT")
    private String title;
    @Column(name = "body", nullable = false, columnDefinition = "TEXT")
    private String body;
    @Column(name = "userId", nullable = false)
    private Long userId;


    public PostEntity(String title,
                      String body,
                      Long userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }

    public PostEntity() {
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
