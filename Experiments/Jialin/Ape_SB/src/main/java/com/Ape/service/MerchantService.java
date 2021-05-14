package com.Ape.service;

import java.util.List;

import com.Ape.error.BusException;
import com.Ape.service.model.MerchantModel;

public interface MerchantService {
	
	//create merchant
	void createMerchant(MerchantModel merchantModel) throws BusException;
	
	//view lists of merchant
	List<MerchantModel> listMerchant();
	
	//view details of merchant
	MerchantModel getMerchantById(Integer id);

	//create comment for specific merchant searched by id
	void createComment(MerchantModel merchantModel) throws BusException;
}
