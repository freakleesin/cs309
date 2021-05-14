/**
 * 
 */
package com.Ape;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.Ape.service.UsersService;
import com.Ape.service.implement.UsersServiceImplement;
import com.Ape.service.model.UsersModel;



/**
 * @author Miao Xu
 *
 */
public class TestUsersService {

	
	@InjectMocks
	UsersServiceImplement usersServiceImplement;
	
	@Mock
	UsersService usersService;
	
	@Before
	public void init() {
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getUsersById() {
		
		UsersModel usersModel = new UsersModel();
		usersModel.setId(14);
		usersModel.setUsername("abc");
		usersModel.setEmail("abc");
		usersModel.setUsersType("Consumer");
		usersModel.setEncryptPassword("kAFQmDzST7DWlj99KOF/cg==");
		when(usersService.getUsersById(14)).thenReturn(usersModel);
		
		assertEquals(14, usersService.getUsersById(14).getId());
		assertEquals("abc", usersService.getUsersById(14).getUsername());
		assertEquals("abc", usersService.getUsersById(14).getEmail());
		assertEquals("Consumer", usersService.getUsersById(14).getUsersType());
		assertEquals("kAFQmDzST7DWlj99KOF/cg==", usersService.getUsersById(14).getEncryptPassword());
	}
}
