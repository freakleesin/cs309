package com.demo.moments;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MomentsController {

	/**
	 * instance variable for moment service
	 */
	@Autowired
	private MomentsService momentsService;

	/**
	 * get a list of all moments
	 * 
	 * @return all moments
	 */
	@RequestMapping("/moments")
	public List<Moments> getAllMoments() {
		return momentsService.getAllMoments();
	}

	/**
	 * get one moment by moment id
	 * 
	 * @param id moment id
	 * @return one moment
	 */
	@RequestMapping("/moment/{id}")
	// PathVariable told spring MVC what path of the request need to pick
	public Moments getOneMomet(@PathVariable Integer id) {
		return momentsService.getOneMomet(id);
	}

	/**
	 * get a list of moments by user id
	 * 
	 * @param userid user id
	 * @return a list of moments
	 */
	@RequestMapping("/moments/{userid}")
	public List<Moments> getUserMoment(@PathVariable Integer userid) {
		return momentsService.getUserMoment(userid);
	}

	/**
	 * get a list of moments for all his/her and his/her friends moments by user id
	 * 
	 * @param userid user id
	 * @return a list of moments for all his/her and his/her friends moments
	 */
	@RequestMapping("/moments/all/{userid}")
	public List<Moments> getUserAllMoment(@PathVariable Integer userid) {
		return momentsService.getUserAllMoment(userid);
	}

	/**
	 * post a moment object
	 * 
	 * @param moments moment object
	 */
	@PostMapping(value = "/moments/add")
	public void addMoments(@RequestBody Moments moments) {
		momentsService.addMoments(moments);
	}

	@DeleteMapping("/moments/{id}")
	public void deleteMoment(@PathVariable Integer id) {
		momentsService.deleteMoment(id);
	}
}
