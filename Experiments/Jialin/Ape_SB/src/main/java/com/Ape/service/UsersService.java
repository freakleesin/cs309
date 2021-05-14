/**
 * 
 */
package com.Ape.service;

import com.Ape.error.BusException;
import com.Ape.service.model.UsersModel;

/**
 * @author Miao Xu
 *
 */
public interface UsersService {
	
	UsersModel getUsersById(Integer iId);
	
	void register(UsersModel usersModel) throws BusException;
	
	UsersModel login(String username, String encryptPassword) throws BusException;
	
	void updateUsername(String oldUsername, String newUsername) throws BusException;
}
