package com.socmedclone.socialclone.controller;

import com.socmedclone.socialclone.models.User;
import com.socmedclone.socialclone.repository.PostRepository;
import com.socmedclone.socialclone.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        return "redirect:/";
    }

    //Profile page
    @GetMapping("/profile")
    public String profile(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("posts", postDao.findAllByUser(user));
        model.addAttribute("user",user);
        model.addAttribute("loggedInUser",user);
        return "users/profile";
    }

    @GetMapping("/profile/{username}")
    public String usersProfile(@PathVariable String username, Model model){
        User user = userDao.findByUsername(username);
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("posts", postDao.findAllByUser(user));
        model.addAttribute("user",user);
        model.addAttribute("loggedInUser",loggedInUser);
        return "users/profile";
    }

    @PostMapping("/profile/{username}")
    public String updateProfile(@ModelAttribute User userModel){
        User user = userDao.findByUsername(userModel.getUsername());
        String hash = passwordEncoder.encode(userModel.getPassword());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setEmail(userModel.getEmail());
        user.setUsername(userModel.getUsername());
        user.setPassword(hash);
        user.setBio(userModel.getBio());
        user.setCity(userModel.getCity());
        user.setState(userModel.getState());
        userDao.save(user);
        return "redirect:/profile/{username}";
    }
}
