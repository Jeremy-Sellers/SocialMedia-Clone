package com.socmedclone.socialclone.models;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String postBody;

    @Column(nullable = true)
    private Long likes;

    @Column(nullable = true)
    private String comment;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
}
