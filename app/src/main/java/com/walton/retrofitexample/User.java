package com.walton.retrofitexample;
public class User {

    private String username;
    private String token_type;
    private String access_token;


    public User(String username, String token_type,String access_token) {
        this.username = username;
        this.token_type = token_type;
        this.access_token = access_token;
    }

    public String getUsername() {
        return username;
    }

    public String getTokentype() {
        return token_type;
    }


    public String getAcceesstoken() {
        return access_token;
    }
}