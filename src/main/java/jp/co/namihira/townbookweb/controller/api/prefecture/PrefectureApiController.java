package jp.co.namihira.townbookweb.controller.api.prefecture;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.namihira.townbookweb.controller.api.AbstractApiController;
import jp.co.namihira.townbookweb.controller.api.AppApiListResponse;
import jp.co.namihira.townbookweb.dto.PrefectureDto;
import jp.co.namihira.townbookweb.service.prefecture.PrefectureService;
import jp.co.namihira.townbookweb.util.CommonUtil;

@RestController
public class PrefectureApiController extends AbstractApiController {

	private static final String BASE_PATH = "/prefectures";
	
	@Autowired
    private MessageSource messageSource;
    
    @Autowired
    private PrefectureService prefectureService;

    @GetMapping(BASE_PATH)
	public AppApiListResponse get(
			final Locale locale,
			@RequestParam(required = false, defaultValue = "false") boolean hasEvents
	) {    	
    	List<PrefectureDto> prefectureDtos = CommonUtil.list();
    	if (hasEvents) {
    		prefectureDtos = prefectureService.getPrefecturesHasEvents(messageSource, locale);
    	} else {
    		prefectureDtos = prefectureService.getPrefectures(messageSource, locale);
    	}
    	
        return new AppApiListResponse(prefectureDtos.size(), prefectureDtos);
    }

}
