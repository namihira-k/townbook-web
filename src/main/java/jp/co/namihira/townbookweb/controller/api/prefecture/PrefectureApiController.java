package jp.co.namihira.townbookweb.controller.api.prefecture;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.consts.PrefectureEnum;
import jp.co.namihira.townbookweb.controller.api.AppApiListResponse;
import jp.co.namihira.townbookweb.dto.PrefectureDto;

@RestController
@RequestMapping("api/prefectures") 
public class PrefectureApiController {

	
    @Autowired
    private MessageSource messageSource;

	
	@RequestMapping(method=RequestMethod.GET)
	public AppApiListResponse get(final Locale locale) {
		final List<PrefectureDto> prefectureDtos = Stream.of(PrefectureEnum.values())
				                                         .map(p -> p.toPrefectureDto(messageSource, locale))
				                                         .collect(Collectors.toList());
        return new AppApiListResponse(prefectureDtos);
    }

}
