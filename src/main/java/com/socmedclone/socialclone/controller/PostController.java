package com.socmedclone.socialclone.controller;

import com.socmedclone.socialclone.models.Post;
import com.socmedclone.socialclone.models.User;
import com.socmedclone.socialclone.repository.PostRepository;
import com.socmedclone.socialclone.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private final UserRepository userDao;
    private final PostRepository postDao;

    public PostController(UserRepository userDao, PostRepository postDao) {
        this.userDao = userDao;
        this.postDao = postDao;
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
        return "main/main";
    }

    @PostMapping("/post/delete")
    public String deleteReview(@RequestParam(name= "deleteReview") long id) {
        postDao.deleteById(id);
        return "home/index";
    }
}
