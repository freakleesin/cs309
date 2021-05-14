package ape.learningbackend.topic;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ApeService {
	private List<Topic> topics = Arrays.asList(
			new Topic("1","admin","123","xxx@iastate.edu","123-123-1234","admin")
			);
	
	public List<Topic> getAllToptics(){
		return topics;
	}
	
	public Topic getTopic(String id) {
		return topics.stream().filter(t->t.getId().equals(id)).findFirst().get();
	}
}
