/**
 * 
 */
package com.Ape.Chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Miao Xu
 *
 */
@Configuration
public class ChatSocketConfig {

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		
		return new ServerEndpointExporter();
	}
}
