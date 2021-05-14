package com.example.ape;

public class MemberData {
    private String username;
    private int userId;

    public MemberData(String name, int id){
        this.username = name;
        this.userId = id;
    }

    public MemberData(){

    }

    public String getUsername(){
        return username;
    }

    public int getUserId() {
        return userId;
    }
}
