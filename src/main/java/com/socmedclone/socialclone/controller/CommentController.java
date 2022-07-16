package com.socmedclone.socialclone.controller;

import com.socmedclone.socialclone.models.Comment;
import com.socmedclone.socialclone.models.Post;
import com.socmedclone.socialclone.repository.CommentRepository;
import com.socmedclone.socialclone.repository.PostRepository;
import com.socmedclone.socialclone.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {
    private final UserRepository userDao;
    private final PostRepository postDao;
    private final CommentRepository commentDao;

    public CommentController(UserRepository userDao, PostRepository postDao, CommentRepository commentDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.commentDao = commentDao;
    }

    @PostMapping("/comment/like")
    public String likePost(@RequestParam(name = "likeComment") long id){
        Comment findComment = commentDao.findById(id);
        findComment.setLikes(findComment.getLikes() + 1);
        commentDao.save(findComment);
        return "redirect:/main";
    }
}
