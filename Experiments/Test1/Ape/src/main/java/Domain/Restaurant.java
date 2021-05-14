package Domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name = "restaurants")
@Data
@EqualsAndHashCode(exclude = { "id" })
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@JsonView(JsonViews.Summary.class)
	private String name;
	@JsonView(JsonViews.Summary.class)
	private String address;
	@JsonView(JsonViews.Summary.class)
	private String email;
	@JsonView(JsonViews.Summary.class)
	private String phone;
	@JsonView(JsonViews.Summary.class)
	private String url;
	@OneToMany(mappedBy="restaurant", cascade = CascadeType.REMOVE)
	@JsonView(JsonViews.Details.class)
	private List<Review> reviews = new ArrayList<>();
	
	public Restaurant() {
		
	}
	
	public Restaurant(Long id, String name, String address, String email, String phone, String url, List<Review> reviews) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.url = url;
		this.reviews = reviews;
	}
	
	public void addReview(Review review) {
		reviews.add(review);
	}
	

	public Restaurant(String name, String address, String email, String phone, String url, List<Review> reviews) {
		this(null,name,address,email,phone,url,reviews);
	}
	
}