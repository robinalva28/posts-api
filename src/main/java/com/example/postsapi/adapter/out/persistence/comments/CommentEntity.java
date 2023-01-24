package com.example.postsapi.adapter.out.persistence.comments;

import com.example.postsapi.adapter.out.persistence.posts.PostEntity;
import jakarta.persistence.*;

@Entity(name = "Comment")
@Table(name = "comment")
public class CommentEntity {

    @Id
    @SequenceGenerator(sequenceName = "entity_sequence_generator", name = "entity_sequence_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_sequence_generator")
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "email", columnDefinition = "TEXT", nullable = false)
    private String email;
    @Column(name = "body", columnDefinition = "TEXT", nullable = false)
    private String body;

    @ManyToOne(targetEntity = PostEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "postId", referencedColumnName = "id")
    private PostEntity postEntity;

    public CommentEntity(String name, String email, String body) {
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public CommentEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public PostEntity getPostEntity() {
        return postEntity;
    }

    public void setPostEntity(PostEntity postEntity) {
        this.postEntity = postEntity;
    }
}
