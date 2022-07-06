package com.socmedclone.socialclone.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    //FOr security reasons
    public User(User copy){
        id = copy.id;
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    // Generate a key for id
    // Primary Key
    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FIRST_NAME",nullable = false, length = 30)
    private String firstName;

    @Column(name="LAST_NAME", nullable = false, length = 30)
    private String lastName;

    @Column(name="EMAIL",nullable = false, length = 100)
    private String email;

    // The Username is only max length at 15 characters
    @Column(name="USERNAME", nullable = false,unique = true, length = 16)
    private String username;

    // Password Max length is 15 characters


    @Column(name="PASSWORD", nullable = false, length = 100)
    private String password;

    // Only Visiable on the Profile Page
    @Column(name="BIO", nullable = true, length = 250)
    private String bio;

    @Column(name="CITY",nullable = false, length = 40)
    private String City;

    @Column(name="STATE", nullable = false, length = 40)
    private String State;

    @Column(name = "JOINED_DATE")
    private String JoinedDate;

    @Column(name = "PROFILE_PIC")
    private String ProfilePic;

    @Column(name = "Bg_Pic")
    private String BgPic;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<FollowsList> followsLists;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    private List<FollowersList> followersLists;

    public User(){
    }

    public User(long id, String firstName, String lastName, String email, String username, String password, String bio, String city, String state, String joinedDate, String profilePic, String bgPic, List<Post> posts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.City = city;
        this.State = state;
        this.JoinedDate = joinedDate;
        this.ProfilePic = profilePic;
        this.BgPic = bgPic;
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getJoinedDate() {
        return JoinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        JoinedDate = joinedDate;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getBgPic() {
        return BgPic;
    }

    public void setBgPic(String bgPic) {
        BgPic = bgPic;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

//    public List<FollowsList> getFollowsLists() {
//        return followsLists;
//    }
//
//    public void setFollowsLists(List<FollowsList> followsLists) {
//        this.followsLists = followsLists;
//    }
//
//    public List<FollowersList> getFollowersLists() {
//        return followersLists;
//    }
//
//    public void setFollowersLists(List<FollowersList> followersLists) {
//        this.followersLists = followersLists;
//    }
}
