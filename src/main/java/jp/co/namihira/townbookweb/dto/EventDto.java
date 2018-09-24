package jp.co.namihira.townbookweb.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "events_test")
@Data
@NoArgsConstructor
public class EventDto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	
	public LocalDateTime startDateTime;

	public LocalDateTime endDateTime;

}
