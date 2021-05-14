/**
 * 
 */
package com.Ape.service;

import com.Ape.controller.viewObject.UsersVO;
import com.Ape.error.BusException;
import com.Ape.service.model.UsersModel;

/**
 * @author Miao Xu
 *
 */
public interface UsersService {
	
	UsersModel getUsersById(Integer id);
	
	void register(UsersModel usersModel) throws BusException;
	
	UsersModel login(String username, String encryptPassword) throws BusException;
	
	Integer updateUsername(String oldUsername, String newUsername) throws BusException;
	
	Integer updatePassword(String username, String oldPassword, String newPassword) throws BusException;
	
	String getUsernameById(Integer id);
	
	void like(String username);
	
	UsersVO selectByUsername(String username);

	UsersModel DeleteById(Integer id);
}
