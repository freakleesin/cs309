/**
 * 
 */
package com.Ape.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Ape.controller.viewObject.CommentsStarsVO;
import com.Ape.error.BusException;
import com.Ape.response.ComReturnType;
import com.Ape.service.CommentsStarsService;
import com.Ape.service.model.CommentsStarsModel;

/**
 * @author Miao Xu
 *
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@Controller("commentsStars")
@RequestMapping("/commentsStars")
public class CommentsStarsController {

	@Autowired
	private CommentsStarsService commentsStarsService;

	// API for new commentsStars.
	@RequestMapping(value = "/newCommentsStars", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType newCommentsStars(@RequestParam("usersId") String usersId,
			@RequestParam("merchantsId") String merchantsId, @RequestParam("postsId") String postsId,
			@RequestParam("stars") String stars, @RequestParam("content") String content) throws BusException {

		CommentsStarsModel commentsStarsModel = new CommentsStarsModel();
		commentsStarsModel.setUsersId(Integer.parseInt(usersId));
		if (!merchantsId.equals("")) {
			commentsStarsModel.setMerchantsId(Integer.parseInt(merchantsId));
		}
		if (!postsId.equals("")) {
			commentsStarsModel.setPostsId(Integer.parseInt(postsId));
		}
		if (!stars.equals("")) {
			commentsStarsModel.setStars(Integer.parseInt(stars));
		}
		commentsStarsModel.setContent(content);
		CommentsStarsModel temp = commentsStarsService.newCommentsStarsModel(commentsStarsModel);
		CommentsStarsVO commentsStarsVO = this.convertFromModel(temp);

		return ComReturnType.create(commentsStarsVO);
	}

	// API for list commentsStars by merchantsId.
	@RequestMapping(value = "/listCommentsStarsByMerchantsId", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType listCommentsStarsVOByMerchantsId(@RequestParam("merchantsId") String merchantsId) {

		List<CommentsStarsModel> listCommentsStarsModelByMerchantsId = commentsStarsService
				.listCommentsStarsModelByMerchantsId(Integer.parseInt(merchantsId));
		List<CommentsStarsVO> listCommentsStarsVO = listCommentsStarsModelByMerchantsId.stream()
				.map(commentsStarsModel -> {
					CommentsStarsVO commentsStarsVO = this.convertFromModel(commentsStarsModel);
					return commentsStarsVO;
				}).collect(Collectors.toList());
		return ComReturnType.create(listCommentsStarsVO);
	}

	// API for list commentsStars by postsId.
	@RequestMapping(value = "/listCommentsStarsByPostsId", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType listCommentsStarsVOByPostsId(@RequestParam("postsId") String postsId) {

		List<CommentsStarsModel> listCommentsStarsModelByMerchantsId = commentsStarsService
				.listCommentsStarsModelByPostsId(Integer.parseInt(postsId));
		List<CommentsStarsVO> listCommentsStarsVO = listCommentsStarsModelByMerchantsId.stream()
				.map(commentsStarsModel -> {
					CommentsStarsVO commentsStarsVO = this.convertFromModel(commentsStarsModel);
					return commentsStarsVO;
				}).collect(Collectors.toList());
		return ComReturnType.create(listCommentsStarsVO);
	}

	private CommentsStarsVO convertFromModel(CommentsStarsModel commentsStarsMode) {

		CommentsStarsVO commentsStarsVO = new CommentsStarsVO();
		commentsStarsVO.setId(commentsStarsMode.getId());
		commentsStarsVO.setUsersId(commentsStarsMode.getUsersId());
		commentsStarsVO.setMerchantsId(commentsStarsMode.getMerchantsId());
		commentsStarsVO.setPostsId(commentsStarsMode.getPostsId());
		commentsStarsVO.setStars(commentsStarsMode.getStars());
		commentsStarsVO.setContent(commentsStarsMode.getContent());
		commentsStarsVO.setUsername(commentsStarsMode.getUsername());
		return commentsStarsVO;
	}
}
