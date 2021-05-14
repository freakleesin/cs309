/**
 * 
 */
package com.Ape.service;

import java.util.List;

import com.Ape.error.BusException;
import com.Ape.service.model.MerchantsModel;

/**
 * @author Miao Xu
 *
 */
public interface MerchantsService {
	
	MerchantsModel newMerchantsModel(MerchantsModel merchantsModel) throws BusException;
	
	List<MerchantsModel> listMerchantsModelByType(String type);
	
	MerchantsModel getMerchantsModelByUsersId(Integer id);

	Integer updateMerchantsType(Integer usersId, String type) throws BusException;

	Integer updateMerchantsAddress(Integer usersId, String address) throws BusException;

	Integer updateMerchantsMainBusiness(Integer usersId, String mainBusiness) throws BusException;

	Integer updateMerchantsImgUrl(Integer usersId, String imgUrl) throws BusException;
}
