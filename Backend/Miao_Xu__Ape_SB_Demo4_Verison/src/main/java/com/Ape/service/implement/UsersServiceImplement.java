/**
 * 
 */
package com.Ape.service.implement;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ape.controller.viewObject.UsersVO;
import com.Ape.dao.DO_MerchantsMapper;
import com.Ape.dao.DO_UsersMapper;
import com.Ape.dao.DO_UsersPasswordMapper;
import com.Ape.dataObject.DO_Merchants;
import com.Ape.dataObject.DO_Users;
import com.Ape.dataObject.DO_UsersPassword;
import com.Ape.error.BusException;
import com.Ape.error.EnumBusError;
import com.Ape.service.UsersService;
import com.Ape.service.model.UsersModel;
import com.Ape.validator.ValidationResult;
import com.Ape.validator.ValidatorImplement;

/**
 * @author Miao Xu
 *
 */

@Service
@Transactional
public abstract class UsersServiceImplement implements UsersService {

	@Autowired
	private DO_UsersMapper do_UsersMapper;

	@Autowired
	private DO_UsersPasswordMapper do_UsersPasswordMapper;
	
	@Autowired
	private DO_MerchantsMapper do_MerchantsMapper;
	
	@Autowired
	private ValidatorImplement validator;
	
	@Override
	@Transactional
	public void register(UsersModel usersModel) throws BusException {

		if (usersModel == null) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR);
		}
		ValidationResult result = validator.validate(usersModel);
		if(result.isHasErrors()) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR, result.getErrorMsg());
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
		if(usersModel.getUsersType().equals("Merchant")) {
			DO_Merchants do_Merchants = convertMerchantsFromModel(usersModel);
			do_MerchantsMapper.insertSelective(do_Merchants);
		}
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
	public Integer updateUsername(String username, String newUsername) throws BusException {

		DO_Users do_Users = do_UsersMapper.selectByUsername(username);
		if (do_Users == null) {
			throw new BusException(EnumBusError.USER_NOT_EXISIS);
		}
		do_Users.setUsername(newUsername);
		return do_UsersMapper.updateByPrimaryKeySelective(do_Users);
	}

	@Override
	@Transactional
	public Integer updatePassword(String username, String oldPassword, String newPassword) throws BusException {

		DO_Users do_Users = do_UsersMapper.selectByUsername(username);
		if (do_Users == null) {
			throw new BusException(EnumBusError.USER_NOT_EXISIS);
		} else {
			DO_UsersPassword do_UsersPassword = do_UsersPasswordMapper.selectByUsersId(do_Users.getId());
			if (!(StringUtils.equals(do_UsersPassword.getEncryptpassword(), oldPassword))) {
				throw new BusException(EnumBusError.USER_UPDATEPASSWORD_FAIL);
			} else {
				do_UsersPassword.setEncryptpassword(newPassword);
				return do_UsersPasswordMapper.updateByPrimaryKeySelective(do_UsersPassword);
			}
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
	
	@Override
	@Transactional
	public String getUsernameById(Integer id) {

		return do_UsersMapper.selectByPrimaryKey(id).getUsername();
	}
	
	@Override
	@Transactional
	public void like(String username) {
		
		DO_Users do_Users = do_UsersMapper.selectByUsername(username);
		do_Users.setLikenum(do_Users.getLikenum() + 1);
		do_UsersMapper.updateByPrimaryKeySelective(do_Users);
	}

	@Override
	@Transactional
	public UsersVO selectByUsername(String username) {
		
		return this.convertUsersVOFromDO(do_UsersMapper.selectByUsername(username));
	}
	
	
	
	private UsersModel convertFromDO(DO_Users do_Users, DO_UsersPassword do_UsersPassword) {

		if (do_Users == null) {
			return null;
		}
		UsersModel usersModel = new UsersModel();
		usersModel.setId(do_Users.getId());
		usersModel.setUsername(do_Users.getUsername());
		usersModel.setEmail(do_Users.getEmail());
		usersModel.setUsersType(do_Users.getUserstype());
		usersModel.setLikeNum(do_Users.getLikenum());
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
		do_Users.setUsername(usersModel.getUsername());
		do_Users.setEmail(usersModel.getEmail());
		do_Users.setUserstype(usersModel.getUsersType());
		do_Users.setLikenum(usersModel.getLikeNum());
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

	private DO_Merchants convertMerchantsFromModel(UsersModel usersModel) {

		if (usersModel == null) {
			return null;
		}
		DO_Merchants do_Merchants = new DO_Merchants();
		do_Merchants.setUsersid(usersModel.getId());
		return do_Merchants;
	}
	
	private UsersVO convertUsersVOFromDO(DO_Users do_Users) {
		
		UsersVO usersVO = new UsersVO();
		usersVO.setId(do_Users.getId());
		usersVO.setUsername(do_Users.getUsername());
		usersVO.setEmail(do_Users.getEmail());
		usersVO.setUsersType(do_Users.getUserstype());
		usersVO.setLikeNum(do_Users.getLikenum());
		return usersVO;
	}
}
