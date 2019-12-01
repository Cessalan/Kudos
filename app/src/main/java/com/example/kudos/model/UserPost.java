package com.example.kudos.model;

public class UserPost {
    private int id;
    private String post;

    public UserPost(){}
    public UserPost(String post){
        this.post=post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
