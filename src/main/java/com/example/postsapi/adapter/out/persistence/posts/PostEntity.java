package com.example.postsapi.adapter.out.persistence.posts;

import lombok.Data;

@Data
public class PostEntity {
    private int id;
    private String title;
    private String body;
    private int userId;
}
