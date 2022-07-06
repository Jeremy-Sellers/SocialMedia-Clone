package com.socmedclone.socialclone.controller;

import com.socmedclone.socialclone.models.User;
import com.socmedclone.socialclone.repository.PostRepository;
import com.socmedclone.socialclone.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private UserRepository userDao;
    private PostRepository postDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PostRepository postDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.postDao = postDao;
        this.passwordEncoder = passwordEncoder;
    }

    //Get request to show Register Form
    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "users/register";
    }

    //Post request to submit form
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, BindingResult result){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);

        User existingUser = userDao.findByUsername(user.getUsername());

        if (existingUser != null){
            result.rejectValue("username",null,"Username already exists");
        }

        if (result.hasErrors()){
            return "users/register";
        }

        userDao.save(user);
        return "redirect:/login";
    }
}
