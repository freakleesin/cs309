package com.Ape.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Ape.controller.viewObject.MerchantVO;
import com.Ape.error.BusException;
import com.Ape.response.ComReturnType;
import com.Ape.service.MerchantService;
import com.Ape.service.model.MerchantModel;

@Controller("merchant")
@RequestMapping("/merchant")
@CrossOrigin(allowedHeaders ="*",allowCredentials = "true")
public class MerchantController extends BaseController {
	
	@Autowired
	private MerchantService merchantService;
	
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public ComReturnType createMerchant(@RequestParam(name= "merchantName")String merchantName,
			@RequestParam(name= "merchantType")String merchantType,
			@RequestParam(name= "description")String description,
			@RequestParam(name= "address")String address,
			@RequestParam(name= "imgUrl")String imgUrl) throws NoSuchAlgorithmException, UnsupportedEncodingException,BusException {
		
		MerchantModel merchantModel = new MerchantModel();
		merchantModel.setMerchantName(merchantName);
		merchantModel.setMerchantType(merchantType);
		merchantModel.setDescription(description);
		merchantModel.setAddress(address);
		merchantModel.setImgUrl(imgUrl);
		
		merchantService.createMerchant(merchantModel);
		
		return ComReturnType.create(null);
	}
	
	@RequestMapping(value = "/comment", method = {RequestMethod.POST})
	@ResponseBody
	public ComReturnType commentMerchant(@RequestParam(name= "comments")String comments,
			@RequestParam(name = "merchantId")Integer id,
			@RequestParam(name = "rating")Integer rating) throws NoSuchAlgorithmException, UnsupportedEncodingException,BusException{
		
		MerchantModel merchantModel = new MerchantModel();
		merchantModel.setComments(comments);
		merchantModel.setId(id);
		merchantModel.setRating(rating);
		
		merchantService.createComment(merchantModel);
		
		return ComReturnType.create(null);
	}
	
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public ComReturnType getMerchant(@RequestParam(name= "id")Integer id) {
		MerchantModel merchantModel = merchantService.getMerchantById(id);
		
		MerchantVO merchantVO = convertVOFromModel(merchantModel);
		
		return ComReturnType.create(merchantVO);
	}
	
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	@ResponseBody
	public ComReturnType listMerchant() {
		List<MerchantModel> merchantModelList = merchantService.listMerchant();
		
		List<MerchantVO> merchantVOList = merchantModelList.stream().map(merchantModel -> {
			MerchantVO merchantVO = this.convertVOFromModel(merchantModel);
			return merchantVO;
		}).collect(Collectors.toList());
		return ComReturnType.create(merchantVOList);
	}
	
	private MerchantVO convertVOFromModel(MerchantModel merchantModel) {
		if(merchantModel==null) {
			return null;
		}
		MerchantVO merchantVO = new MerchantVO();
		BeanUtils.copyProperties(merchantModel, merchantVO);
		return merchantVO;
	}
}
