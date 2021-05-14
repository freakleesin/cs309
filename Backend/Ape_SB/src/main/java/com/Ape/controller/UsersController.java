package com.Ape.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.mbeans.UserMBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Ape.controller.viewObject.UsersVO;
import com.Ape.error.BusException;
import com.Ape.error.EnumBusError;
import com.Ape.response.ComReturnType;
import com.Ape.service.UsersService;
import com.Ape.service.model.UsersModel;

/**
 * 
 * @author Miao Xu
 *
 */

@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@Controller("users")
@RequestMapping("/users")
public class UsersController extends BaseController{
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private HttpServletRequest httpServletRequest;
	
	//API for register.
	@RequestMapping(value = "/register", method = {RequestMethod.POST})
	@ResponseBody
	public ComReturnType register(@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("usersType") String usersType) throws NoSuchAlgorithmException, UnsupportedEncodingException, BusException {
		
		//create usersModel to store users info.
		UsersModel usersModel = new UsersModel();
		usersModel.setUsername(username);
		usersModel.setEmail(email);
		usersModel.setUsersType(usersType);
		usersModel.setEncryptPassword(this.EncodeByMd5(password));
		//register.
		usersService.register(usersModel);
		return ComReturnType.create(null);
	}

	//API for login.
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	@ResponseBody
	public ComReturnType login(@RequestParam("username") String username, @RequestParam("password") String password) throws BusException, NoSuchAlgorithmException, UnsupportedEncodingException {
		
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR);
		}
		//login.
		UsersModel usersModel = usersService.login(username, this.EncodeByMd5(password));
		UsersVO usersVO = convertFromModel(usersModel);
		//add valued login usersVO to session.
		this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
		this.httpServletRequest.getSession().setAttribute("LOGIN_USER", usersVO);
		return ComReturnType.create(usersVO);
	}
	
	//API for update username, password
	@RequestMapping(value = "/update", method = {RequestMethod.POST})
	@ResponseBody
	public ComReturnType updateUsername(@RequestParam("oldUsername") String oldUsername, @RequestParam("newUsername") String newUsername) throws BusException, NoSuchAlgorithmException, UnsupportedEncodingException {
		
		if(StringUtils.isEmpty(oldUsername) || StringUtils.isEmpty(newUsername)) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR);
		}
		//update
		//usersService.updateUsername();
		return null;
	}
	
	//API for get users by id.
	@RequestMapping("/get")
	@ResponseBody
	public ComReturnType getUsers(@RequestParam(name="id") String id) throws BusException {
		
		Integer usersId = Integer.parseInt(id);
		UsersModel usersModel = usersService.getUsersById(usersId);
		if(usersModel == null) {
			throw new BusException(EnumBusError.USER_NOT_EXISIS);
		}
		//return VO from DO.
		UsersVO usersVO = convertFromModel(usersModel);
		return ComReturnType.create(usersVO);
	}
	
	//convert model -> VO.
	private UsersVO convertFromModel(UsersModel usersModel) {
		
		if(usersModel == null) {
			return null;
		}
		UsersVO usersVO = new UsersVO();
		BeanUtils.copyProperties(usersModel, usersVO);
		return usersVO;
	}
	
	//encryption.
	public String EncodeByMd5(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		Encoder encoder = Base64.getEncoder();
		String newPw = encoder.encodeToString(md5.digest(password.getBytes("utf-8")));
		return newPw;
	}
}
