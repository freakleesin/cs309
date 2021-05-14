package com.demo.moments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MomentsService {

	/**
	 * an instance variable for moments repository
	 */
	@Autowired
	private MomentsRepository momentsRepository;

	/**
	 * get a list of all moments
	 * 
	 * @return a list of all moments
	 */
	public List<Moments> getAllMoments() {

		List<Moments> moments = new ArrayList<>();
		momentsRepository.findAll() // iterating all the users from the database
				.forEach(moments::add);

		return moments;

	}

	/**
	 * add one moment object to server
	 * 
	 * @param moments one moment object
	 */
	public void addMoments(Moments moments) {

		if (moments.getId() == null) {
			Date date = new Date();
			moments.setTime(date.toString());
			momentsRepository.save(moments);
		} else {

			String tem = momentsRepository.findById(moments.getId()).get().getTime();
			moments.setTime(tem);
			momentsRepository.save(moments);
		}

	}

	/**
	 * get a list of moments by one user id
	 * 
	 * @param id user id
	 * @return a list of moments
	 */
	public List<Moments> getUserMoment(Integer id) {

		List<Moments> moments = new ArrayList<>();
		momentsRepository.findByUserid(id).forEach(moments::add);

		return moments;

	}

	/**
	 * get one moment by moment id
	 * 
	 * @param id moment id
	 * @return one moment object
	 */
	public Moments getOneMomet(Integer id) {
		return momentsRepository.findById(id).get();
	}

	public void deleteMoment(Integer id) {
		momentsRepository.deleteById(id);
	}

	/**
	 * get a list of moments for all his/her and his/her friends moments by user id
	 * 
	 * @param id user id
	 * @return a list of moments for all his/her and his/her friends moments
	 */
	public List<Moments> getUserAllMoment(Integer id) {

		List<Moments> moments = new ArrayList<>();
		momentsRepository.getUserAllMoment(id).forEach(moments::add);

		return moments;

	}

//	public List<Moments> getUserAllMoment(Integer id)
//	{
//		String newid="";
//		List<Friends> friends= new ArrayList<>();
//		
//		getAllfriend(id)
//		.forEach(friends::add);
//		
//		
//		List<Moments> moments= new ArrayList<>();
//		
//		for (int i = 0; i < friends.size(); i++) {
//			newid = friends.get(i).getFriendId();
//			momentsRepository.findByUserid(newid)
//			.forEach(moments::add);
//		}
//		
//		
//		return moments;
//	}

}
