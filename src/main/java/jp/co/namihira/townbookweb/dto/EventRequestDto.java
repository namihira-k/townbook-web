package jp.co.namihira.townbookweb.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event_requests")
@Setter
@Getter
@NoArgsConstructor
public class EventRequestDto extends AbstractDto {

	@Id
	@SequenceGenerator(name="event_request_seq", initialValue=1000)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="event_request_seq")
	public Integer id;

	public String name;

	public String url;
		
}
