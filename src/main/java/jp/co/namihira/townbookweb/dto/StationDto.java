package jp.co.namihira.townbookweb.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "stations")
@Setter
@Getter
public class StationDto extends AbstractDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	
	@Column(unique = true)
	private String code;
	
	private String name;

	public StationDto() {};
	
	public StationDto(final String code, final String name) {
		this.code = code;
		this.name = name;
	};

}
