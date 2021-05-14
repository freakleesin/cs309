package com.Ape.webSocketServer;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket/{userName}")
@Component
public class WebSocketServer {
	 private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
	 private static Map<String, Session> usernameSessionMap = new Hashtable<>();

	 private final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    @OnOpen
    public void onOpen( Session session, @PathParam("userName") String userName)
						throws IOException
    {


        logger.info("Entered into Open");

        sessionUsernameMap.put(session, userName);
        usernameSessionMap.put(userName, session);

        String message="User:" + userName + " has Joined the Chat";
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException
    {
        // Handle new messages
    	logger.info("Entered into Message: Got Message:"+message);
    	String userName = sessionUsernameMap.get(session);

    	if (message.startsWith("@")) // Direct message to a user using the format "@username <message>"
    	{
    		int index = message.indexOf(" ");
    		String destUsername = message.substring(1, index);
    		
    		sendMessageToPArticularUser(destUsername, "[DM] " + userName + ": " + message);
    		sendMessageToPArticularUser(userName, "[DM] " + userName + ": " + message);
    	}
    	else // Message to whole chat
    	{
	    	broadcast(userName + ": " + message);
    	}
    }

    @OnClose
    public void onClose(Session session) throws IOException
    {
    	logger.info("Close the chat");

    	String username = sessionUsernameMap.get(session);
    	sessionUsernameMap.remove(session);
    	usernameSessionMap.remove(username);

    	String message= username + " disconnected";
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable)
    {
    	logger.info("Error");
    }

	  private void sendMessageToPArticularUser(String username, String message)
    {
    	  try {
    		  usernameSessionMap.get(username).getBasicRemote().sendText(message);
        } catch (IOException e) {
        	logger.info("Exception: " + e.getMessage().toString());
          e.printStackTrace();
        }
    }

    private void broadcast(String message)
		{
			  sessionUsernameMap.forEach((session, username)->{
					try {
								session.getBasicRemote().sendText(message);
					} catch (IOException e) {
								logger.info("Exception: " + e.getMessage().toString());
								e.printStackTrace();
					}

				});

    }

}
