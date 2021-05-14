package com.Ape;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.Ape.controller.PostsController;
import com.Ape.controller.UsersController;
import com.Ape.controller.viewObject.PostsVO;
import com.Ape.controller.viewObject.UsersVO;
import com.Ape.repo.PostsRepository;
import com.Ape.repo.UserRepository;
import com.Ape.response.ComReturnType;
import com.Ape.service.PostsService;
import com.Ape.service.UsersService;
import com.Ape.service.model.PostsModel;

public class PostControllerTest {
	
	@Rule
	public ExpectedException e = ExpectedException.none();
	@InjectMocks
	private PostsController postsController = new PostsController();
	@InjectMocks
	private PostsService PostsService;
	@Mock(answer = Answers.CALLS_REAL_METHODS)
	private PostsModel givenPost;
	private PostsModel returnedPost;
	private List<PostsModel> bunchofPosts;
	private PostsRepository postsRepository;
	private Integer id;
	private ComReturnType comReturnType;

	@Before
	public void begin() {
		id = 1;
		this.givenPost = new PostsModel();
		this.returnedPost = new PostsModel();
		this.bunchofPosts = new ArrayList<PostsModel>();
		this.bunchofPosts.add(givenPost);
		this.bunchofPosts.add(returnedPost);
		
		when(postsRepository.findAll()).thenReturn(this.bunchofPosts);
		when(postsRepository.findById(null)).thenReturn(Optional.of(this.returnedPost));
		when(postsRepository.save(null)).thenReturn(this.returnedPost);
		
	}
	
	@Test
	public void testGetPostTable() {
		PostsService postsService = mock(PostsService.class);
		when(postsService.getPostsById(1)).thenReturn(givenPost);
		this.givenPost.setUsername("name");
		PostsModel pm = new PostsModel();
		
		assertEquals(1, pm.getId());
		assertEquals(1, pm.getUsername());
	}
	
	@Test
	public void testDeletePosts() {
		when(postsRepository.existsById(null)).thenReturn(true);
		postsController.deletePosts(id);
		assertEquals(null, this.givenPost.getContent());
		assertEquals(null, this.givenPost.getId());
		assertEquals(null, this.givenPost.getMerchantsType());
		assertEquals(null, this.givenPost.getTitle());
		assertEquals(null, this.givenPost.getUsername());
		assertEquals(null, this.givenPost.getUsersId());
	}
	
	@Test
	public void testAllPost() {
		ComReturnType response = postsController.getPoststById(null);
		assertEquals(this.comReturnType, response);
		
		postsController.deletePosts(id);
		assertEquals(null, response.getData());
	}

}
