/**
 * 
 */
package com.Ape.Chat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author Miao Xu
 *
 */
@Entity
@Table(name = "messages")
@Data
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 64)
	@Column
	private String username;

	@NotNull
	@Lob
	private String content;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sent")
	private Date sent = new Date();

	public Message() {};

	public Message(String username, String content) {

		this.username = username;
		this.content = content;
	}

}
