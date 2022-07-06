package com.socmedclone.socialclone.controller;

import com.socmedclone.socialclone.models.Post;
import com.socmedclone.socialclone.repository.PostRepository;
import com.socmedclone.socialclone.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    private final UserRepository userDao;
    private final PostRepository postDao;

    public HomeController(UserRepository userDao, PostRepository postDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }

    //index page
    @GetMapping("/")
    public String home(Model model){
        return "home/index";
    }

    @GetMapping("/about")
    public String about(){
        return "home/about";
    }

}
