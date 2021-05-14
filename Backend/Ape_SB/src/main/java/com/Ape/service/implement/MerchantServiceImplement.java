package com.Ape.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.engine.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ape.dao.DO_MerchantCommentsMapper;
import com.Ape.dao.DO_MerchantMapper;
import com.Ape.dataObject.DO_Merchant;
import com.Ape.dataObject.DO_MerchantComments;
import com.Ape.dataObject.DO_UsersPassword;
import com.Ape.error.BusException;
import com.Ape.error.EnumBusError;
import com.Ape.service.MerchantService;
import com.Ape.service.model.MerchantModel;

@Service
public class MerchantServiceImplement implements MerchantService {

	@Autowired
	private DO_MerchantMapper doMerchantMapper;
	
	
	@Autowired
	private DO_MerchantCommentsMapper doMerchantCommentsMapper;
	
	private DO_Merchant convertDO_MerchantFromMerchantModel(MerchantModel merchantModel) {
		if(merchantModel ==null) {
			return null;
		}
		DO_Merchant doMerchant = new DO_Merchant();
		BeanUtils.copyProperties(merchantModel, doMerchant);
		return doMerchant;
	}
	
	
	@Override
	@Transactional
	public void createMerchant(MerchantModel merchantModel) throws BusException {
		
		if (merchantModel == null) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR);
		}
		if (StringUtils.isEmpty(merchantModel.getMerchantName()) || StringUtils.isEmpty(merchantModel.getMerchantType())
				|| StringUtils.isEmpty(merchantModel.getDescription())|| StringUtils.isEmpty(merchantModel.getAddress())|| StringUtils.isEmpty(merchantModel.getImgUrl())) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR);
		}
		DO_Merchant doMerchant = convertDO_MerchantFromMerchantModel(merchantModel);
		
		try {
		doMerchantMapper.insertSelective(doMerchant);
		}catch(DuplicateKeyException e) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR, "MerchantName exists.");
		}
		merchantModel.setId(doMerchant.getId());
		
		return;
	}
	
	@Override
	@Transactional
	public void createComment(MerchantModel merchantModel) throws BusException{
		if (merchantModel == null) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR);
		}
		//only check if comments have null
		if (StringUtils.isEmpty(merchantModel.getComments()) )
				 {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR);
		}
		DO_MerchantComments doMerchantComments = convertDO_MerchantCommentsFromMerchantModel(merchantModel);
		
		doMerchantCommentsMapper.insertSelective(doMerchantComments);
		merchantModel.setId(merchantModel.getId());
		
		
	}

	private DO_MerchantComments convertDO_MerchantCommentsFromMerchantModel(MerchantModel merchantModel) {
		if(merchantModel ==null) {
			return null;
		}
		DO_MerchantComments doMerchantComments = new DO_MerchantComments();
		BeanUtils.copyProperties(merchantModel, doMerchantComments);
		doMerchantComments.setMerchantId(merchantModel.getId());
		return doMerchantComments;
	}


	@Override
	public List<MerchantModel> listMerchant() {
		List<DO_Merchant> doMerchantList = doMerchantMapper.listMerchant();
		List<MerchantModel> merchantModelList = doMerchantList.stream().map(doMerchant -> {
			DO_MerchantComments doMerchantComments = doMerchantCommentsMapper.selectByMerchantId(doMerchant.getId());
			MerchantModel merchantModel = this.convertModelFromDataObject(doMerchant, doMerchantComments);
			return merchantModel;}).collect(Collectors.toList());
		
		return merchantModelList;
	}

	@Override
	public MerchantModel getMerchantById(Integer id) {
		DO_Merchant doMerchant = doMerchantMapper.selectByPrimaryKey(id);
		if(doMerchant ==null) {
			return null;
		}
		DO_MerchantComments doMerchantComments = doMerchantCommentsMapper.selectByMerchantId(doMerchant.getId()); 
		
		MerchantModel merchantModel = convertModelFromDataObject(doMerchant,doMerchantComments);
		return merchantModel;
	}
	
	
	
	private MerchantModel convertModelFromDataObject(DO_Merchant doMerchant,DO_MerchantComments doMerchantComments) {
		MerchantModel merchantModel = new MerchantModel();
		BeanUtils.copyProperties(doMerchant, merchantModel);
		merchantModel.setComments(doMerchantComments.getComments());
		merchantModel.setRating(doMerchantComments.getRating());
		
		return merchantModel;
	}

}
