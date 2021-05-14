package com.example.demo.websocket;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;
 
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
 

@RestController
@ServerEndpoint(value = "/OneToOne/{param}")
public class WebSocketServer {
    
    private static int onlineCount;
    
    private static Map<String,WebSocketServer> connections = new ConcurrentHashMap<>();
    
    private Session session;
    private String role;
    private String socketId;
 
   
    @OnOpen
    public void onOpen(@PathParam("param") String param, Session session) {
        this.session = session;
        String[] arr = param.split(",");
        this.role = arr[0];             //mark user
        this.socketId = arr[1];         
        connections.put(role,this);    
        addOnlineCount();               
        System.out.println("New user connects"+role+",current user number:" + getOnlineCount());
    }
 
    
    @OnClose
    public void onClose() {
        connections.remove(role);  // remove user
        subOnlineCount();          
        System.out.println("Disconnected, current user number" + getOnlineCount());
    }
 
    
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("From client:" + message);
        JSONObject json=JSONObject.fromObject(message);
        String string = null;  
        String to = null;      
        if(json.has("message")){
            string = (String) json.get("message");
        }
        if(json.has("role")){
            to = (String) json.get("role");
        }
        send(string,role,to,socketId);
    }
 
    
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("Error");
        error.printStackTrace();
    }
 
 
    //发送给指定角色
    public void send(String msg,String from,String to,String socketId){
        try {
           
            WebSocketServer con = connections.get(to);
            if(con!=null){
                if(socketId==con.socketId||con.socketId.equals(socketId)){
                    con.session.getBasicRemote().sendText(from+"："+msg);
                }
 
            }
            
            WebSocketServer confrom = connections.get(from);
            if(confrom!=null){
                if(socketId==confrom.socketId||confrom.socketId.equals(socketId)){
                    confrom.session.getBasicRemote().sendText(from+"："+msg);
                }
 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }
 
    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}

