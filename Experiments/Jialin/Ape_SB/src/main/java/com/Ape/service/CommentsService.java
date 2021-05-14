package com.Ape.service;

import java.util.List;

import com.Ape.error.BusException;
import com.Ape.service.model.CommentsModel;

public interface CommentsService {
	List<CommentsModel> getCommentsByMerchantsId(Integer id);
	
	void createComments(CommentsModel commetsModel) throws BusException;
	
}
