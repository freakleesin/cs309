/**
 * 
 */
package com.Ape.service.implement;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ape.dao.DO_MerchantsMapper;
import com.Ape.dataObject.DO_Merchants;
import com.Ape.error.BusException;
import com.Ape.error.EnumBusError;
import com.Ape.service.MerchantsService;
import com.Ape.service.model.MerchantsModel;
import com.Ape.validator.ValidationResult;
import com.Ape.validator.ValidatorImplement;

/**
 * @author Miao Xu
 *
 */
@Service
public class MerchantsServiceImplement implements MerchantsService{

	@Autowired
	private ValidatorImplement validator;
	
	@Autowired
	private DO_MerchantsMapper do_MerchantsMapper;
	
	@Override
	@Transactional
	public MerchantsModel newMerchantsModel(MerchantsModel merchantsModel) throws BusException {

		ValidationResult result = validator.validate(merchantsModel);
		if(result.isHasErrors()) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR, result.getErrorMsg());
		}
		DO_Merchants do_Merchants = this.convertFromModel(merchantsModel);
		do_MerchantsMapper.insertSelective(do_Merchants);
		merchantsModel.setId(do_Merchants.getId());//useful or not?
		return merchantsModel;
	}
	
	@Override
	@Transactional
	public List<MerchantsModel> listMerchantsModelByType(String type) {

		List<DO_Merchants> listWithSameType = do_MerchantsMapper.selectByType(type);
		List<MerchantsModel> listMerchantsModels = listWithSameType.stream().map(do_Merchants->{
			MerchantsModel merchantsModel = this.convertFromDO(do_Merchants);
			return merchantsModel;
		}).collect(Collectors.toList());
		return listMerchantsModels;
	}
	
	@Override
	@Transactional
	public Integer updateMerchantsType(Integer usersId, String type) throws BusException{
		
		DO_Merchants do_Merchants = do_MerchantsMapper.selectByUsersId(usersId);
		if (do_Merchants == null) {
			throw new BusException(EnumBusError.USER_NOT_EXISIS);
		}
		do_Merchants.setType(type);
		return do_MerchantsMapper.updateByPrimaryKey(do_Merchants);
	}
	
	@Override
	@Transactional
	public Integer updateMerchantsAddress(Integer usersId, String address) throws BusException{
		
		DO_Merchants do_Merchants = do_MerchantsMapper.selectByUsersId(usersId);
		if (do_Merchants == null) {
			throw new BusException(EnumBusError.USER_NOT_EXISIS);
		}
		do_Merchants.setAddress(address);
		return do_MerchantsMapper.updateByPrimaryKey(do_Merchants);
	}
	
	@Override
	@Transactional
	public Integer updateMerchantsMainBusiness(Integer usersId, String mainBusiness) throws BusException{
		
		DO_Merchants do_Merchants = do_MerchantsMapper.selectByUsersId(usersId);
		if (do_Merchants == null) {
			throw new BusException(EnumBusError.USER_NOT_EXISIS);
		}
		do_Merchants.setMainbusiness(mainBusiness);
		return do_MerchantsMapper.updateByPrimaryKey(do_Merchants);
	}
	
	@Override
	@Transactional
	public Integer updateMerchantsImgUrl(Integer usersId, String imgUrl) throws BusException{
		
		DO_Merchants do_Merchants = do_MerchantsMapper.selectByUsersId(usersId);
		if (do_Merchants == null) {
			throw new BusException(EnumBusError.USER_NOT_EXISIS);
		}
		do_Merchants.setImgurl(imgUrl);
		return do_MerchantsMapper.updateByPrimaryKey(do_Merchants);
	}

	@Override
	@Transactional
	public MerchantsModel getMerchantsModelByUsersId(Integer id) {
		
		DO_Merchants do_Merchants = do_MerchantsMapper.selectByUsersId(id);
		return this.convertFromDO(do_Merchants);
	}

	private DO_Merchants convertFromModel(MerchantsModel merchantsModel) {
		
		if(merchantsModel == null) {
			return null;
		}
		DO_Merchants do_Merchants = new DO_Merchants();
		do_Merchants.setType(merchantsModel.getType());
		do_Merchants.setAddress(merchantsModel.getAddress());
		do_Merchants.setMainbusiness(merchantsModel.getMainBusiness());
		do_Merchants.setImgurl(merchantsModel.getImgUrl());
		do_Merchants.setUsersid(merchantsModel.getUsersId());
		do_Merchants.setStars(merchantsModel.getStars().doubleValue());
		return do_Merchants;
	}
	
	private MerchantsModel convertFromDO(DO_Merchants do_Merchants) {
		
		if(do_Merchants == null) {
			return null;
		}
		MerchantsModel merchantsModel = new MerchantsModel();
		merchantsModel.setId(do_Merchants.getId());
		merchantsModel.setType(do_Merchants.getType());
		merchantsModel.setAddress(do_Merchants.getAddress());
		merchantsModel.setMainBusiness(do_Merchants.getMainbusiness());
		merchantsModel.setImgUrl(do_Merchants.getImgurl());
		merchantsModel.setUsersId(do_Merchants.getUsersid());
		merchantsModel.setStars(new BigDecimal(do_Merchants.getStars()));
		return merchantsModel;
	}
}
