package ape.learningbackend.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApeTopicController {//you can have method in this restController
	
	@Autowired
	private ApeService apeService;
	
	@RequestMapping("/topics")
	public List<Topic> getAllTopics() {
		return apeService.getAllToptics();
	}
	
	@RequestMapping("/topics/{id}")
	public Topic getTopic(@PathVariable String id) {
		return apeService.getTopic(id);
	}
	
}
