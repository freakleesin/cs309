package com.example.ape;

public class ChatMessage {
    private String text;
    private MemberData memberData;
    private String username;
    private boolean belongsToCurrentUser;
    private String time;

    public ChatMessage(String text, String username, boolean belongsToCurrentUser){
        this.text = text;
        this.username = username;
        this.belongsToCurrentUser = belongsToCurrentUser;
        this.time = calhourmins(System.currentTimeMillis());
    }

    public String getText(){
        return text;
    }

    public String getUsername(){
        return username;
    }

    public boolean isBelongsToCurrentUser(){
        return belongsToCurrentUser;
    }

    public String getTime(){
        return time;
    }

    public String calhourmins(long i){
        long curMin = (i / 60000) % 60;
        long curHour = (i / 3600000) % 24;
        return curHour + ":" + curMin;
    }
}
