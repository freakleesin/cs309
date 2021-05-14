/**
 * 
 */
package com.Ape.service;

import java.util.List;

import com.Ape.error.BusException;
import com.Ape.service.model.CommentsStarsModel;

/**
 * @author Miao Xu
 *
 */
public interface CommentsStarsService {

	CommentsStarsModel newCommentsStarsModel(CommentsStarsModel commentsStarsModel) throws BusException;
	
	List<CommentsStarsModel> listCommentsStarsModelByMerchantsId(Integer merchantsId);
	
	List<CommentsStarsModel> listCommentsStarsModelByPostsId(Integer postsId);
}
