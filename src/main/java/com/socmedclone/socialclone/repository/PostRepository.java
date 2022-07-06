package com.socmedclone.socialclone.repository;

import com.socmedclone.socialclone.models.Post;
import com.socmedclone.socialclone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUser(User user);

    Post findById(long id);

    List<Post> findAll();
}
