/**
 * 
 */
package com.Ape;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Ape.service.MerchantsService;
import com.Ape.service.implement.MerchantsServiceImplement;
import com.Ape.service.model.MerchantsModel;

/**
 * @author Miao Xu
 *
 */
public class TestCommentsStarsController {

	@InjectMocks
	MerchantsServiceImplement merchantsServiceImplement;
	
	@Mock
	MerchantsService merchantsService;
	
	@Before
	public void init() {
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getMerchantsModelByUsersId() {
		
		MerchantsModel merchantsModel = new MerchantsModel();
		merchantsModel.setId(1);
		merchantsModel.setType("restaurant");
		merchantsModel.setAddress("domino parks");
		merchantsModel.setMainBusiness("pizza, wings");
		merchantsModel.setImgUrl("");
		merchantsModel.setUsersId(19);
		when(merchantsService.getMerchantsModelByUsersId(19)).thenReturn(merchantsModel);
		assertEquals(1, merchantsService.getMerchantsModelByUsersId(19).getId());
		assertEquals("restaurant", merchantsService.getMerchantsModelByUsersId(19).getType());
		assertEquals("domino parks", merchantsService.getMerchantsModelByUsersId(19).getAddress());
		assertEquals("pizza, wings", merchantsService.getMerchantsModelByUsersId(19).getMainBusiness());
		assertEquals("", merchantsService.getMerchantsModelByUsersId(19).getImgUrl());
		assertEquals(19, merchantsService.getMerchantsModelByUsersId(19).getUsersId());
	}
}
