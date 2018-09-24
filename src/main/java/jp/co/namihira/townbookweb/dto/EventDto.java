package jp.co.namihira.townbookweb.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public LocalDateTime startDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	public LocalDateTime endDateTime;

}
