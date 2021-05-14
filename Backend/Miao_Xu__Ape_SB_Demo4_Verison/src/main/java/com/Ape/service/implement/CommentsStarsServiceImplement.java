/**
 * 
 */
package com.Ape.service.implement;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Ape.dao.DO_CommentsStarsMapper;
import com.Ape.dao.DO_MerchantsMapper;
import com.Ape.dataObject.DO_CommentsStars;
import com.Ape.dataObject.DO_Merchants;
import com.Ape.error.BusException;
import com.Ape.error.EnumBusError;
import com.Ape.service.CommentsStarsService;
import com.Ape.service.UsersService;
import com.Ape.service.model.CommentsStarsModel;

/**
 * @author Miao Xu
 *
 */
@Service
public class CommentsStarsServiceImplement implements CommentsStarsService{

	@Autowired
	private DO_CommentsStarsMapper do_CommentsStarsMapper;

	@Autowired
	private UsersService usersService;

	@Autowired
	private DO_MerchantsMapper do_MerchantsMapper;

	@Override
	@Transactional
	public CommentsStarsModel newCommentsStarsModel(CommentsStarsModel commentsStarsModel) throws BusException {

		commentsStarsModel.setUsername(usersService.getUsernameById(commentsStarsModel.getUsersId()));
		DO_CommentsStars do_CommentsStars = this.convertFromModel(commentsStarsModel);
		do_CommentsStarsMapper.insertSelective(do_CommentsStars);
		if(commentsStarsModel.getStars() != null && commentsStarsModel.getStars() != -1) {
			List<DO_CommentsStars> listDOCommentsStarsByMerchantsId = do_CommentsStarsMapper.selectByMerchantsId(commentsStarsModel.getMerchantsId());
			Integer people = 0;
			Integer stars = 0;
			for(int i = 0; i < listDOCommentsStarsByMerchantsId.size() - 1; i++) {
				DO_CommentsStars temp = listDOCommentsStarsByMerchantsId.get(i);
				Integer tempI = temp.getStars();
				if(tempI != -1) {
					stars += tempI;
					people++;
				}
			}
			BigDecimal merchantsStars = new BigDecimal(stars + commentsStarsModel.getStars());
			BigDecimal merchantsStarsPeople = new BigDecimal(people + 1);
			merchantsStars = merchantsStars.divide(merchantsStarsPeople, 1, BigDecimal.ROUND_HALF_UP);
			DO_Merchants do_Merchants = do_MerchantsMapper.selectByPrimaryKey(commentsStarsModel.getMerchantsId());
			do_Merchants.setStars(merchantsStars.doubleValue());
			Integer isUpdate = do_MerchantsMapper.updateByPrimaryKeySelective(do_Merchants);
			if(isUpdate == 0) {
				throw new BusException(EnumBusError.MERCHANT_UPDATE_STARS_FAIL);
			}
		}
		commentsStarsModel.setId(do_CommentsStars.getId());
		return commentsStarsModel;
	}

	@Override
	@Transactional
	public List<CommentsStarsModel> listCommentsStarsModelByMerchantsId(Integer merchantsId) {

		List<DO_CommentsStars> listDOCommentsStarsByMerchantsId = do_CommentsStarsMapper.selectByMerchantsId(merchantsId);
		List<CommentsStarsModel> listCommentsStarsModel = listDOCommentsStarsByMerchantsId.stream().map(do_CommentsStars->{
			CommentsStarsModel commentsStarsModel = this.convertFromDO(do_CommentsStars);
			return commentsStarsModel;
		}).collect(Collectors.toList());
		return listCommentsStarsModel;
	}

	@Override
	@Transactional
	public List<CommentsStarsModel> listCommentsStarsModelByPostsId(Integer postsId) {

		List<DO_CommentsStars> listDOCommentsStarsByPostsId = do_CommentsStarsMapper.selectByPostsId(postsId);
		List<CommentsStarsModel> listCommentsStarsModel = listDOCommentsStarsByPostsId.stream().map(do_CommentsStars->{
			CommentsStarsModel commentsStarsModel = this.convertFromDO(do_CommentsStars);
			return commentsStarsModel;
		}).collect(Collectors.toList());
		return listCommentsStarsModel;
	}

	private DO_CommentsStars convertFromModel(CommentsStarsModel commentsStarsModel) {

		DO_CommentsStars do_CommentsStars = new DO_CommentsStars();
		do_CommentsStars.setUsersid(commentsStarsModel.getUsersId());
		do_CommentsStars.setMerchantsid(commentsStarsModel.getMerchantsId());
		do_CommentsStars.setPostsid(commentsStarsModel.getPostsId());
		do_CommentsStars.setStars(commentsStarsModel.getStars());
		do_CommentsStars.setContent(commentsStarsModel.getContent());
		do_CommentsStars.setUsername(commentsStarsModel.getUsername());
		return do_CommentsStars;
	}

	private CommentsStarsModel convertFromDO(DO_CommentsStars do_CommentsStars) {

		CommentsStarsModel commentsStarsModel = new CommentsStarsModel();
		commentsStarsModel.setId(do_CommentsStars.getId());
		commentsStarsModel.setUsersId(do_CommentsStars.getUsersid());
		commentsStarsModel.setMerchantsId(do_CommentsStars.getMerchantsid());
		commentsStarsModel.setPostsId(do_CommentsStars.getPostsid());
		commentsStarsModel.setStars(do_CommentsStars.getPostsid());
		commentsStarsModel.setContent(do_CommentsStars.getContent());
		commentsStarsModel.setUsername(do_CommentsStars.getUsername());
		return commentsStarsModel;
	}
}
