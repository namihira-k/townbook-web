package jp.co.namihira.townbookweb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PrefectureDto extends AbstractDto {

	private String code;
	private String name;
	
	public PrefectureDto(final String code) {
		this.code = code;
	}
	
}
