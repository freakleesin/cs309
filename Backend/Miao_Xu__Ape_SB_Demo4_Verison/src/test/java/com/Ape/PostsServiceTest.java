package com.Ape;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.Ape.repo.PostsRepository;
import com.Ape.service.MerchantsService;
import com.Ape.service.PostsService;
import com.Ape.service.model.PostsModel;

public class PostsServiceTest {
	@InjectMocks
	PostsService postsService;
	
	@Mock
	PostsRepository postsRepo;
	
	private Integer id;
	
	@Before
	public void begin() {
		MockitoAnnotations.initMocks(this);
		id = 1;
	}
	
	@Test
	public void getPostsTest() {
		ArrayList<PostsModel> list = new ArrayList<PostsModel>();
		when(postsRepo.findAll()).thenReturn(list);
		
		postsService.getPostsById(1);
		assertEquals(postsRepo, times(1));
		verify(postsRepo, times(1)).findAll();
		String str = "123";
		PostsModel postsModel = new PostsModel();
		when(this.postsRepo.existsById(str)).thenReturn(false);
		when(this.postsRepo.save(null)).thenReturn(new PostsModel());
		list.add(postsModel);
		
		PostsModel postsmodel = new PostsModel();
		assertEquals(postsmodel.getId(), id);
		assertEquals(postsmodel.hashCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void deleteTest() {
		when(this.postsRepo.existsById("1")).thenReturn(false);
		
		PostsModel postsModel = this.postsService.getPostsById(1);
		verify(this.postsRepo, never()).deleteById(1);
		
		assertEquals(postsModel.getContent(), HttpStatus.BAD_REQUEST);
		assertEquals(postsModel.getMerchantsType(), MediaType.APPLICATION_JSON);
	}

}
