package jp.co.namihira.townbookweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LineDto extends AbstractDto {

	private String code;
	private String name;
	
}
