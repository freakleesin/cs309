/**
 * 
 */
package com.Ape.Chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Miao Xu
 *
 */
public interface MessageRepository extends JpaRepository<Message, Long>{

	public static List<Message> findByusername(){
		
		return null;
	}
}
