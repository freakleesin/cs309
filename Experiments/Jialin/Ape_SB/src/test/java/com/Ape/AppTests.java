package com.Ape;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Ape.service.model.*;
import com.Ape.controller.*;
import com.Ape.dao.DO_UsersMapper;
import com.Ape.error.BusException;
import com.Ape.service.UsersService;
import com.Ape.service.implement.*;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Test;


import org.springframework.boot.test.context.SpringBootTest;

public class AppTests {

	@InjectMocks
	UsersServiceImplement usersServiceImp;

	@Mock
	UsersService usersService ;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getUsersByIdTest() {
		when(usersService.getUsersById(1)).thenReturn(new UsersModel(1,"xingjl","xingjl@iastate.edu","normal","xingjl"));

		UsersModel usersModel = usersService.getUsersById(1);

		assertEquals("xingjl", usersModel.getUsername());
		assertEquals("xingjl@iastate.edu", usersModel.getEmail());
		assertEquals("normal", usersModel.getUsersType());
		assertEquals("xingjl",usersModel.getEncryptPassword());
	}
	
	@Test
	public void loginTest() {
		try {
			when(usersService.login("xingjl", "xingjl")).thenReturn(new UsersModel(1,"xingjl","xingjl@iastate.edu","normal","xingjl"));
			
			UsersModel usersModel = usersService.login("xingjl", "xingjl");
			
			assertEquals("xingjl", usersModel.getUsername());
			assertEquals("xingjl@iastate.edu", usersModel.getEmail());
			assertEquals("normal", usersModel.getUsersType());
			assertEquals("xingjl",usersModel.getEncryptPassword());
		} catch (BusException e) {
			e.printStackTrace();
		}
	}




}
