package jp.co.namihira.townbookweb.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
public class EventDto {

	@Id
	@SequenceGenerator(name="event_seq", initialValue=1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="event_seq")
	public Integer id;

	public String name;
	
	public String place;
	
	public String stationCode;
		
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public LocalDateTime startDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public LocalDateTime endDateTime;

}
