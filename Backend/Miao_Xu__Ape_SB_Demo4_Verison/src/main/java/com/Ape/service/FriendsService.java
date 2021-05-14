/**
 * 
 */
package com.Ape.service;


import java.util.List;

import com.Ape.controller.viewObject.UsersVO;
import com.Ape.error.BusException;

/**
 * @author Miao Xu
 *
 */
public interface FriendsService {

	void addFriends(Integer usersId, String friends) throws BusException;
	
	List<UsersVO> listUsersVOOfFriendsModelByUsersId(Integer usersId);
}
