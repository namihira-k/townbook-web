package jp.co.namihira.townbookweb.service.prefecture;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import jp.co.namihira.townbookweb.consts.PrefectureEnum;
import jp.co.namihira.townbookweb.dao.StationDao;
import jp.co.namihira.townbookweb.dto.PrefectureDto;

@Service
public class PrefectureService {

	@Autowired
	private StationDao stationDao;
	
	public List<PrefectureDto> getPrefecturesHasEvents(final MessageSource messageSource, final Locale locale){
		final List<PrefectureDto> dtos = stationDao.findPrefectures();				
		final List<PrefectureDto> results = dtos.stream()
												.sorted(Comparator.comparing(PrefectureDto::getCode))
				                                .map(dto -> PrefectureEnum.of(dto.getCode()).toPrefectureDto(messageSource, locale))
		  	                                    .collect(Collectors.toList());
		return results;
	}
	
	public List<PrefectureDto> getPrefectures(final MessageSource messageSource, final Locale locale){
		final List<PrefectureDto> results = Stream.of(PrefectureEnum.values())
												  .map(p -> p.toPrefectureDto(messageSource, locale))
												  .collect(Collectors.toList());
		return results;
	}
	
	
}
