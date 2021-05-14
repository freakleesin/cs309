package com.Ape;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.ResourceAccessException;

import com.Ape.controller.UsersController;
import com.Ape.controller.viewObject.UsersVO;
import com.Ape.repo.UserRepository;
import com.Ape.service.UsersService;
import com.Ape.service.model.UsersModel;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	@Rule
	public ExpectedException e = ExpectedException.none();
	@InjectMocks
	private UsersController uc = new UsersController();
	@InjectMocks
	private UsersService usersService;
	@Mock(answer = Answers.CALLS_REAL_METHODS)
	private UsersVO givenUser;
	private UsersVO returnedUser;
	private ArrayList<UsersVO> bunchofUsers;
	private UserRepository userRepository;
	private Integer id;
	
	@Before
	public void begin() {
		id = 123;
		this.givenUser = new UsersVO();
		this.returnedUser = new UsersVO();
		this.bunchofUsers = new ArrayList<UsersVO>();
		this.bunchofUsers.add(givenUser);
		this.bunchofUsers.add(returnedUser);
		when(userRepository.findAll()).thenReturn(this.bunchofUsers);
		when(userRepository.findById(id)).thenReturn(Optional.of(this.returnedUser));
		when(userRepository.save(null)).thenReturn(this.returnedUser);
		
	}
	
	@Test
	public void TestDeleteUser() {
		when(userRepository.existsById(id)).thenReturn(false);
		
		e.expect(ResourceAccessException.class);
		uc.deleteUsers(id);
	}
	
	@Test
	public void TestGetAllUsers() {
		this.bunchofUsers.add(givenUser);
		this.bunchofUsers.add(returnedUser);
		
		when(userRepository.findAllById(null)).thenReturn(this.bunchofUsers);
		
		
		this.givenUser.setEmail("123321");
		assertEquals("123321", this.givenUser.getEmail());
		
		this.givenUser.setId(111);
		assertEquals(111, this.givenUser.getId());
		
		this.givenUser.setUsername("name");
		assertEquals("name", this.givenUser.getUsername());
		
		this.givenUser.setUsersType("type");
		assertEquals("type", this.givenUser.getUsersType());
		
		this.givenUser.setLikeNum(1);
		assertEquals(1, this.givenUser.getLikeNum());
	}
}
 