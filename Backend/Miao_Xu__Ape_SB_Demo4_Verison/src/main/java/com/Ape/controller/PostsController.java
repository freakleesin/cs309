/**
 * 
 */
package com.Ape.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Ape.controller.viewObject.PostsVO;
import com.Ape.response.ComReturnType;
import com.Ape.service.PostsService;
import com.Ape.service.implement.PostsServiceImplement;
import com.Ape.service.model.PostsModel;

/**
 * @author Miao Xu
 *
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@Controller("posts")
@RequestMapping("/posts")
public class PostsController {

	@Autowired
	private PostsService postsService;
	private PostsServiceImplement PI;

	// API for new posts.
	@RequestMapping(value = "/newPosts", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType newPosts(@RequestParam("usersId") String usersId,
			@RequestParam("merchantsType") String merchantsType, @RequestParam("title") String title,
			@RequestParam("content") String content) {

		PostsModel postsModel = new PostsModel();
		postsModel.setUsersId(Integer.parseInt(usersId));
		postsModel.setMerchantsType(merchantsType);
		postsModel.setTitle(title);
		postsModel.setContent(content);
		PostsModel temp = postsService.newPostsMode(postsModel);
		PostsVO postsVO = this.convertFromModel(temp);
		return ComReturnType.create(postsVO);
	}

	// API for list all posts by merchantsType.
	@RequestMapping(value = "/listPostsVOByMerchantsType", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType listPostsVOByMerchantsType(@RequestParam("merchantsType") String merchantsType) {

		List<PostsModel> listModelByMerchantsType = postsService.listPostsModelByMerchantsType(merchantsType);
		List<PostsVO> listPostsVO = listModelByMerchantsType.stream().map(postsModel -> {
			PostsVO postsVO = this.convertFromModel(postsModel);
			return postsVO;
		}).collect(Collectors.toList());
		return ComReturnType.create(listPostsVO);
	}

	// API for get posts by id.
	@RequestMapping(value = "/getPostsVOById", method = { RequestMethod.POST })
	@ResponseBody
	public ComReturnType getPoststById(@RequestParam("id") String id) {

		PostsVO postsVO = this.convertFromModel(postsService.getPostsById(Integer.parseInt(id)));
		return ComReturnType.create(postsVO);
		}


	private PostsVO convertFromModel(PostsModel postsModel) {

		PostsVO postsVO = new PostsVO();
		postsVO.setId(postsModel.getId());
		postsVO.setUsersId(postsModel.getUsersId());
		postsVO.setMerchantsType(postsModel.getMerchantsType());
		postsVO.setTitle(postsModel.getTitle());
		postsVO.setContent(postsModel.getContent());
		postsVO.setUsername(postsModel.getUsername());
		return postsVO;
	}
	
	// API for deleting posts by id.
	
	//@DeleteMapping(value = "/deletePostById/")
	//@ResponseBody
	//public ComReturnType deletePostsById(@RequestParam("id") String id) {
		//PostsVO postsVO = this.convertFromModel(postsService.deleteById(Integer.parseInt(id)));
		//return ComReturnType.create(postsVO);
	//}
	
	@DeleteMapping(value = "/deletePostById/")
	@ResponseBody
	public void deletePosts(@PathVariable Integer id) {
		//postsService.deleteById(id);
		PI.deleteById(id);
	}
	

}
