/**
 * 
 */
package com.Ape.service.implement;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ape.dao.DO_UsersMapper;
import com.Ape.dao.DO_UsersPasswordMapper;
import com.Ape.dataObject.DO_Users;
import com.Ape.dataObject.DO_UsersPassword;
import com.Ape.error.BusException;
import com.Ape.error.EnumBusError;
import com.Ape.service.UsersService;
import com.Ape.service.model.UsersModel;

/**
 * @author Miao Xu
 *
 */

@Service
public class UsersServiceImplement implements UsersService {

	@Autowired
	private DO_UsersMapper do_UsersMapper;

	@Autowired
	private DO_UsersPasswordMapper do_UsersPasswordMapper;

	@Override
	@Transactional
	public void register(UsersModel usersModel) throws BusException {

		if (usersModel == null) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR);
		}
		if (StringUtils.isEmpty(usersModel.getUsername()) || StringUtils.isEmpty(usersModel.getEmail()) ) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR);
		}
		DO_Users do_Users = convertFromModel(usersModel);
		
		try {
			do_UsersMapper.insertSelective(do_Users);
		} catch (DuplicateKeyException e) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR, "Username exists.");
		}

		usersModel.setId(do_Users.getId());
		DO_UsersPassword do_UsersPassword = convertPasswordFromModel(usersModel);
		do_UsersPasswordMapper.insertSelective(do_UsersPassword);
		return;
	}

	@Override
	@Transactional
	public UsersModel login(String username, String encryptPassword) throws BusException {

		DO_Users do_Users = do_UsersMapper.selectByUsername(username);
		if (do_Users == null) {
			throw new BusException(EnumBusError.USER_LOGIN_FAIL);
		}
		DO_UsersPassword do_UsersPassword = do_UsersPasswordMapper.selectByUsersId(do_Users.getId());
		UsersModel usersModel = convertFromDO(do_Users, do_UsersPassword);
		if (!StringUtils.equals(encryptPassword, usersModel.getEncryptPassword())) {
			throw new BusException(EnumBusError.USER_LOGIN_FAIL);
		}
		return usersModel;
	}
	
	@Override
	@Transactional
	public void updateUsername(String oldUsername, String newUsername) throws BusException {
		
		DO_Users do_Users = do_UsersMapper.selectByUsername(oldUsername);
		if(do_Users == null) {
			throw new BusException(EnumBusError.USER_NOT_EXISIS);
		}
	}

	@Override
	@Transactional
	public UsersModel getUsersById(Integer id) {

		DO_Users do_Users = do_UsersMapper.selectByPrimaryKey(id);
		if (do_Users == null) {
			return null;
		}
		DO_UsersPassword do_usersPassword = do_UsersPasswordMapper.selectByUsersId(do_Users.getId());
		return convertFromDO(do_Users, do_usersPassword);
	}

	private UsersModel convertFromDO(DO_Users do_Users, DO_UsersPassword do_UsersPassword) {

		if (do_Users == null) {
			return null;
		}
		UsersModel usersModel = new UsersModel();
//		BeanUtils.copyProperties(do_Users, usersModel);
		usersModel.setId(do_Users.getId());
		usersModel.setUsername(do_Users.getUsername());
		usersModel.setEmail(do_Users.getEmail());
		usersModel.setUsersType(do_Users.getUserstype());
		if (do_UsersPassword != null) {
			usersModel.setEncryptPassword(do_UsersPassword.getEncryptpassword());
		}
		return usersModel;
	}

	private DO_Users convertFromModel(UsersModel usersModel) {

		if (usersModel == null) {
			return null;
		}
		DO_Users do_Users = new DO_Users();
//		BeanUtils.copyProperties(usersModel, do_Users);
		do_Users.setUsername(usersModel.getUsername());
		do_Users.setEmail(usersModel.getEmail());
		do_Users.setUserstype(usersModel.getUsersType());
		return do_Users;
	}

	private DO_UsersPassword convertPasswordFromModel(UsersModel usersModel) {

		if (usersModel == null) {
			return null;
		}
		DO_UsersPassword do_UsersPassword = new DO_UsersPassword();
		do_UsersPassword.setEncryptpassword(usersModel.getEncryptPassword());
		do_UsersPassword.setUsersid(usersModel.getId());
		return do_UsersPassword;
	}
}
