package com.Ape.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Ape.controller.viewObject.UsersVO;

@Repository
public interface UserRepository extends CrudRepository<UsersVO, Integer>{
	
}
