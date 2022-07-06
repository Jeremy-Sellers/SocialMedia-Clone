package com.socmedclone.socialclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
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
