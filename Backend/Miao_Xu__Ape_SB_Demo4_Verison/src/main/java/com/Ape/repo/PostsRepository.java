package com.Ape.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ape.service.model.PostsModel;


public interface PostsRepository extends JpaRepository<PostsModel, String>{

	public void deleteById(Integer id);

}
