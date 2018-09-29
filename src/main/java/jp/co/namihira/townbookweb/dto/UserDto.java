package jp.co.namihira.townbookweb.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserDto {

	@Id
	@SequenceGenerator(name="user_seq", initialValue=1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_seq")
	public Integer id;

	@Column(nullable = false)
	public String userId;
	
	@Column(nullable = false)
	public String password;

	@Column(nullable = false)
	public int prefectureCode;
	
	@Column(nullable = false)
	public String email;

}
