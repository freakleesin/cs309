/**
 * 
 */
package com.Ape.service;

import java.util.List;

import com.Ape.service.model.PostsModel;

/**
 * @author Miao Xu
 *
 */
public interface PostsService {

	PostsModel newPostsMode(PostsModel postsModel);
	
	List<PostsModel> listPostsModelByMerchantsType(String merchantsType);
	
	PostsModel getPostsById(Integer id);
	
	PostsModel deleteById(Integer id);
}
