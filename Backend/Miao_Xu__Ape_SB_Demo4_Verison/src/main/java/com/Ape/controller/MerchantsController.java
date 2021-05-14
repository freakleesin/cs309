/**
 * 
 */
package com.Ape.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Ape.controller.viewObject.MerchantsVO;
import com.Ape.error.BusException;
import com.Ape.response.ComReturnType;
import com.Ape.service.MerchantsService;
import com.Ape.service.model.MerchantsModel;

/**
 * @author Miao Xu
 *
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@Controller("merchants")
@RequestMapping("/merchants")
public class MerchantsController extends BaseController {

	@Autowired
	private MerchantsService merchantsService;
	
	//API for new merchants.
	@RequestMapping(value = "/newMerchants", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType newMerchants(@RequestParam("type") String type, @RequestParam("address") String address,
			@RequestParam("mainBusiness") String mainBusiness, @RequestParam("imgUrl") String imgUrl,
			@RequestParam("usersId") String usersId)
					throws NoSuchAlgorithmException, UnsupportedEncodingException, BusException {

		MerchantsModel merchantsModel = new MerchantsModel();
		merchantsModel.setType(type);
		merchantsModel.setAddress(address);
		merchantsModel.setMainBusiness(mainBusiness);
		merchantsModel.setImgUrl(imgUrl);
		merchantsModel.setUsersId(Integer.parseInt(usersId));
		merchantsModel.setStars(new BigDecimal(0));
		MerchantsModel temp = merchantsService.newMerchantsModel(merchantsModel);
		MerchantsVO merchantsVO = convertFromModel(temp);
		return ComReturnType.create(merchantsVO);
	}

	//API for list all merchants by type.
	@RequestMapping(value = "/listMerchantsVOByType", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType listMerchantsVOByType(@RequestParam("type") String type) {
		
		List<MerchantsModel> listModelWithSameType = merchantsService.listMerchantsModelByType(type);
		List<MerchantsVO> listMerchantsVO = listModelWithSameType.stream().map(merchantsModel->{
			MerchantsVO merchantsVO = this.convertFromModel(merchantsModel);
			return merchantsVO;
		}).collect(Collectors.toList());
		return ComReturnType.create(listMerchantsVO);
	}
	
	//API for update type.
	@RequestMapping(value = "/updateMerchantsType", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType updateMerchantsType(@RequestParam("usersId") String usersId, @RequestParam("type") String type)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, BusException {

		Integer result = merchantsService.updateMerchantsType(Integer.parseInt(usersId), type);
		return ComReturnType.create(result);
	}

	//API for update address.
	@RequestMapping(value = "/updateMerchantsAddress", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType updateMerchantsAddress(@RequestParam("usersId") String usersId,
			@RequestParam("address") String address)
					throws NoSuchAlgorithmException, UnsupportedEncodingException, BusException {

		Integer result = merchantsService.updateMerchantsAddress(Integer.parseInt(usersId), address);
		return ComReturnType.create(result);
	}
	
	//API for update mainBusiness.
	@RequestMapping(value = "/updateMerchantsMainBusiness", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType updateMerchantsMainBusiness(@RequestParam("usersId") String usersId,
			@RequestParam("mainBusiness") String mainBusiness)
					throws NoSuchAlgorithmException, UnsupportedEncodingException, BusException {

		Integer result = merchantsService.updateMerchantsMainBusiness(Integer.parseInt(usersId), mainBusiness);
		return ComReturnType.create(result);
	}
	
	//API for update imgUrl.
	@RequestMapping(value = "/updateMerchantsImgUrl", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType updateMerchantsImgUrl(@RequestParam("usersId") String usersId,
			@RequestParam("imgUrl") String imgUrl)
					throws NoSuchAlgorithmException, UnsupportedEncodingException, BusException {

		Integer result = merchantsService.updateMerchantsImgUrl(Integer.parseInt(usersId), imgUrl);
		return ComReturnType.create(result);
	}

	//API for get merchants by usersId.
	@RequestMapping(value = "/getMerchantsByUsersId", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType getMerchantsByUsersId(@RequestParam("usersId") String usersId)
			throws NoSuchAlgorithmException, UnsupportedEncodingException, BusException {

		MerchantsModel merchantsModel = merchantsService.getMerchantsModelByUsersId(Integer.parseInt(usersId));
		MerchantsVO merchantsVO = convertFromModel(merchantsModel);
		return ComReturnType.create(merchantsVO);
	}

	//convert model to VO.
	private MerchantsVO convertFromModel(MerchantsModel merchantsModel) {

		if (merchantsModel == null) {
			return null;
		}
		MerchantsVO merchantsVO = new MerchantsVO();
		merchantsVO.setId(merchantsModel.getId());
		merchantsVO.setType(merchantsModel.getType());
		merchantsVO.setAddress(merchantsModel.getAddress());
		merchantsVO.setMainBusiness(merchantsModel.getMainBusiness());
		merchantsVO.setImgUrl(merchantsModel.getImgUrl());
		merchantsVO.setUsersId(merchantsModel.getUsersId());
		merchantsVO.setStars(merchantsModel.getStars());
		return merchantsVO;
	}
}
