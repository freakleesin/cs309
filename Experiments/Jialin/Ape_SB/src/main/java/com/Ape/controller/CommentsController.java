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

import com.Ape.controller.viewObject.CommentsVO;
import com.Ape.controller.viewObject.MerchantVO;
import com.Ape.error.BusException;
import com.Ape.response.ComReturnType;
import com.Ape.service.CommentsService;
import com.Ape.service.MerchantService;
import com.Ape.service.model.CommentsModel;
import com.Ape.service.model.MerchantModel;

@Controller("comment")
@RequestMapping("/comment")
@CrossOrigin(allowedHeaders ="*",allowCredentials = "true")
public class CommentsController extends BaseController{
	
	@Autowired
	private CommentsService commentsService;
	
	@RequestMapping(value = "/create", method = {RequestMethod.POST})
	@ResponseBody
	public ComReturnType createComments(@RequestParam(name= "usersId")Integer usersId,
			@RequestParam(name= "merchantsId")Integer merchantsId,
			@RequestParam(name= "content")String content) throws NoSuchAlgorithmException, UnsupportedEncodingException,BusException {
		
		CommentsModel commentsModel = new CommentsModel();
		commentsModel.setUsersId(usersId);
		commentsModel.setMerchantsId(merchantsId);
		commentsModel.setContent(content);
		
		commentsService.createComments(commentsModel);
		
		return ComReturnType.create(null);
	}
	
	@RequestMapping(value = "/get", method = {RequestMethod.GET})
	@ResponseBody
	public ComReturnType getComments(@RequestParam(name= "merchantsId")Integer id)throws BusException{
		List<CommentsModel> commentsModelList = commentsService.getCommentsByMerchantsId(id);
		
		List<CommentsVO> commentsVOList = commentsModelList.stream().map(commentsModel -> {
			CommentsVO commentsVO = this.convertVOFromModel(commentsModel);
			return commentsVO;
		}).collect(Collectors.toList());
		return ComReturnType.create(commentsVOList);
	}

	private CommentsVO convertVOFromModel(CommentsModel commentsModel) {
		if(commentsModel==null) {
			return null;
		}
		CommentsVO commentsVO = new CommentsVO();
		BeanUtils.copyProperties(commentsModel, commentsVO);
		commentsVO.setUsersId(commentsModel.getUsersId());
		commentsVO.setMerchantsId(commentsModel.getMerchantsId());
		return commentsVO;
	}
}
