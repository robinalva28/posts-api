package com.example.postsapi.adapter.out.persistence.posts;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(value = "postitems")
public class PostEntity {

    private String title;
    private String body;
    private Long userId;

    private LocalDateTime created;

    public PostEntity(String title,
                      String body,
                      Long userId,
                      LocalDateTime created) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.created = created;
    }
}
