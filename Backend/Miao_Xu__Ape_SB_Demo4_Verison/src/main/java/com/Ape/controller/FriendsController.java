/**
 * 
 */
package com.Ape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Ape.error.BusException;
import com.Ape.response.ComReturnType;
import com.Ape.service.FriendsService;

/**
 * @author Miao Xu
 *
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@Controller("friends")
@RequestMapping("/friends")
public class FriendsController extends BaseController{

	@Autowired
	private FriendsService friendsService;

	@RequestMapping(value = "/addFriends", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType addFriends(@RequestParam("usersId") String usersId,
			@RequestParam("username") String username) throws BusException{

		friendsService.addFriends(Integer.parseInt(usersId), username);
		return ComReturnType.create(null);
	}

	@RequestMapping(value = "/listUsersVOOffriendsModelByUsersId", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType listUsersVOOffriendsModelByUsersId(@RequestParam("usersId") String usersId) {

		return ComReturnType.create(friendsService.listUsersVOOfFriendsModelByUsersId(Integer.parseInt(usersId)));
	}
}