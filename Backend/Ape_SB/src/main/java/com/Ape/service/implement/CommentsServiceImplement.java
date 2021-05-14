package com.Ape.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ape.dao.DO_CommentsMapper;
import com.Ape.dao.DO_MerchantCommentsMapper;
import com.Ape.dataObject.DO_Comments;
import com.Ape.dataObject.DO_Merchant;
import com.Ape.dataObject.DO_MerchantComments;
import com.Ape.error.BusException;
import com.Ape.error.EnumBusError;
import com.Ape.service.CommentsService;
import com.Ape.service.model.CommentsModel;
import com.Ape.service.model.MerchantModel;

@Service
public class CommentsServiceImplement implements CommentsService{
	
	@Autowired
	private DO_CommentsMapper doCommentsMapper;
	
	@Override
	@Transactional
	public void createComments(CommentsModel commentsModel) throws BusException{
		if (commentsModel == null) {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR);
		}
		//only check if comments have null
		if (StringUtils.isEmpty(commentsModel.getContent()) )
				 {
			throw new BusException(EnumBusError.PARAMETER_VALIDATIOIN_ERROR);
		}
		DO_Comments doComments = convertFromModel(commentsModel);
		
		doCommentsMapper.insertSelective(doComments);
		
	}

	private DO_Comments convertFromModel(CommentsModel commentsModel) {
		if(commentsModel == null) {
			return null;
		}
		DO_Comments doComments = new DO_Comments();
		doComments.setUsersid(commentsModel.getUsersId());
		doComments.setMerchantsid(commentsModel.getMerchantsId());
		doComments.setContent(commentsModel.getContent());
		return doComments;
	}


	@Override
	public List<CommentsModel> getCommentsByMerchantsId(Integer id) {
		List<DO_Comments> doCommentsList = doCommentsMapper.selectedByMerchantsId(id);
		List<CommentsModel> commentsModelList = doCommentsList.stream().map(doComments -> {
			CommentsModel commentsModel = this.convertFromDO(doComments);
			return commentsModel;
		}).collect(Collectors.toList());
		
		return commentsModelList;
	}

	private CommentsModel convertFromDO(DO_Comments doSelected) {
		CommentsModel commentsModel = new CommentsModel();
		BeanUtils.copyProperties(doSelected, commentsModel);
		commentsModel.setUsersId(doSelected.getUsersid());
		commentsModel.setMerchantsId(doSelected.getMerchantsid());
		return commentsModel;
	}
}
