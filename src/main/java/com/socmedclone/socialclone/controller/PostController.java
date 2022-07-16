package com.socmedclone.socialclone.controller;

import com.socmedclone.socialclone.models.Comment;
import com.socmedclone.socialclone.models.Post;
import com.socmedclone.socialclone.models.User;
import com.socmedclone.socialclone.repository.CommentRepository;
import com.socmedclone.socialclone.repository.PostRepository;
import com.socmedclone.socialclone.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final UserRepository userDao;
    private final PostRepository postDao;
    private final CommentRepository commentDao;

    public PostController(UserRepository userDao, PostRepository postDao, CommentRepository commentDao) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.commentDao = commentDao;
    }

    //index page
    @GetMapping("/main")
    public String home(Model model){
        model.addAttribute("post", new Post());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("posts",postDao.findAll(Sort.by("id").descending()));
        return "main/main";
    }

    @PostMapping("/main")
    public String postForm(@ModelAttribute Post post){
        Post postToSave = new Post();
        postToSave.setPostBody(post.getPostBody());
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal;
        postToSave.setUser(user);
        Post newPost = postDao.save(postToSave);
        return "redirect:/main";
    }

    @PostMapping("/post/{id}")
    public String updatePost(@ModelAttribute Post post, @PathVariable long id, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Post findPost = postDao.findById(id);
        findPost.setPostBody(post.getPostBody());
        postDao.save(findPost);
        return "redirect:/main";
    }

    @PostMapping("/post/like")
    public String likePost(@RequestParam(name = "likePost") long id){
        Post findPost = postDao.findById(id);
        findPost.setLikes(findPost.getLikes() + 1);
        postDao.save(findPost);
        return "redirect:/main";
    }

    @PostMapping("/post/unlike")
    public String unlikePost(@RequestParam(name = "unlikePost") long id){
        Post findPost = postDao.findById(id);
        findPost.setLikes(findPost.getLikes() - 1);
        postDao.save(findPost);
        return "redirect:/main";
    }

    @GetMapping("/post/{id}/comment")
    public String commentForm(@PathVariable(name = "id") long postId, Model model){
        Post post = postDao.findById(postId);
        List<Comment> commentList = commentDao.findAllByPost(post);
        model.addAttribute("commentList", commentList);
        model.addAttribute("comment", new Comment());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("post", postDao.findById(postId));
        return "main/post";
    }

    @PostMapping("/post/{id}/comment")
    public String commentSubmit(@PathVariable(name = "id") long postId, @ModelAttribute Comment comment, Model model){
        Comment commentToSave = new Comment();
        commentToSave.setCommentBody(comment.getCommentBody());
        commentToSave.setPost(postDao.findById(postId));
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal;
        commentToSave.setUser(user);
        Comment newComment = commentDao.save(commentToSave);
        return "redirect:/post/{id}/comment";
    }

    @PostMapping("/post/delete")
    public String deletePost(@RequestParam(name= "deletePost") long id) {
        postDao.deleteById(id);
        return "redirect:/main";
    }
}
