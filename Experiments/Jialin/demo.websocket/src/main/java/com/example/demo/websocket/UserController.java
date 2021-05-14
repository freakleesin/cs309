package com.example.demo.websocket;

import com.example.demo.websocket.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
 
import javax.servlet.http.HttpSession;
 
@Controller
public class UserController {
 
    @Autowired
    private WebSocketServer webSocketOneToOne;
    
    @RequestMapping("user_1")
    public String user_1(String usr , HttpSession session){
        session.setAttribute("usr",usr);
        return "user_1";
    }
    
    @RequestMapping("message")
    public void message(String msg,String from,String to,String socketId){
        webSocketOneToOne.send(msg, from, to, socketId);
 
    }
}
