package com.Ape;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.Ape.controller.UsersController;
import com.Ape.error.BusException;
import com.Ape.response.ComReturnType;
import com.Ape.service.UsersService;
import com.Ape.service.implement.UsersServiceImplement;
import com.Ape.service.model.UsersModel;

@RunWith(MockitoJUnitRunner.class)
public class UserIdTest {


	@InjectMocks
	UsersServiceImplement usersServiceImp;

	@Mock
	UsersService usersService ;
	
	@Autowired
	private UsersController usersController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void getUserIdTest1() {
		UsersService us = mock(UsersService.class);
		when(us.getUsersById(1)).thenReturn(new UsersModel(12, "name", "email", "type", "password"));
		UsersModel um = us.getUsersById(1);
		
		assertEquals("12", um.getId().toString());
		assertEquals("name", um.getUsername());
		assertEquals("email", um.getEmail());
		assertEquals("type", um.getUsersType());
		assertEquals("password", um.getEncryptPassword());
		
	}
	
	@Test
	public void getUserIdTest2() {
		UsersService us = mock(UsersService.class);
		when(us.getUsersById(1)).thenReturn(new UsersModel(14, "n", "e", "t", "p"));
		UsersModel um = us.getUsersById(1);
		
		assertEquals("14", um.getId().toString());
		assertEquals("n", um.getUsername());
		assertEquals("e", um.getEmail());
		assertEquals("t", um.getUsersType());
		assertEquals("p", um.getEncryptPassword());
	}
	

	public void getDataFromDBTest() throws BusException {
		ComReturnType list = usersController.getUsers("s");
		assert(list != null);
		
	}
	
}
