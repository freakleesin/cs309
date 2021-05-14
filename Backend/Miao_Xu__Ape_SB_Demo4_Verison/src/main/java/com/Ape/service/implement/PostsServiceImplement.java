/**
 * 
 */
package com.Ape.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ape.dao.DO_PostsMapper;
import com.Ape.dataObject.DO_Posts;
import com.Ape.repo.PostsRepository;
import com.Ape.service.PostsService;
import com.Ape.service.UsersService;
import com.Ape.service.model.PostsModel;

/**
 * @author Miao Xu
 *
 */

@Service
public abstract class PostsServiceImplement implements PostsService{
	
	@Autowired
    private static PostsRepository repository;

	@Autowired
	private DO_PostsMapper do_PostsMapper;
	
	@Autowired
	private UsersService usersService;
	
	@Override
	@Transactional
	public PostsModel newPostsMode(PostsModel postsModel) {

		postsModel.setUsername(usersService.getUsernameById(postsModel.getUsersId()));
		DO_Posts do_Posts = converFromModel(postsModel);
		do_PostsMapper.insertSelective(do_Posts);
		postsModel.setId(do_Posts.getId());
		return postsModel;
	}

	@Override
	@Transactional
	public List<PostsModel> listPostsModelByMerchantsType(String merchantsType) {

		List<DO_Posts> listDOPostsByMerchantsType = do_PostsMapper.selectByMerchantsType(merchantsType);
		List<PostsModel> listPostsModel = listDOPostsByMerchantsType.stream().map(do_Posts->{
			PostsModel postsModel = this.convertFromDO(do_Posts);
			return postsModel;
		}).collect(Collectors.toList());
		return listPostsModel;
	}

	@Override
	@Transactional
	public PostsModel getPostsById(Integer id) {

		return convertFromDO(do_PostsMapper.selectByPrimaryKey(id));
	}

	private DO_Posts converFromModel(PostsModel postsModel) { 
		
		DO_Posts do_Posts = new DO_Posts();
		do_Posts.setUsersid(postsModel.getUsersId());
		do_Posts.setMerchantstype(postsModel.getMerchantsType());
		do_Posts.setTitle(postsModel.getTitle());
		do_Posts.setContent(postsModel.getContent());
		do_Posts.setUsername(postsModel.getUsername());
		return do_Posts;
	}
	
	private PostsModel convertFromDO(DO_Posts do_Posts) {
		
		PostsModel postsModel = new PostsModel();
		postsModel.setId(do_Posts.getId());
		postsModel.setUsersId(do_Posts.getUsersid());
		postsModel.setMerchantsType(do_Posts.getMerchantstype());
		postsModel.setTitle(do_Posts.getTitle());
		postsModel.setContent(do_Posts.getContent());
		postsModel.setUsername(do_Posts.getUsername());
		return postsModel;
	}
	
	public void deletePosts(Integer id) {
		repository.deleteById(id);
	}
	
}
