package jp.co.namihira.townbookweb.service.event;

import java.time.LocalDateTime;
import java.util.List;

import jp.co.namihira.townbookweb.consts.EventCategoryEnum;
import lombok.Data;

@Data
public class EventSearchCondition {

    private String name;
    private List<String> prefectureCodes;
    private List<String> stationCodes;
    private List<EventCategoryEnum> categpries;
    private LocalDateTime startDateTime;
    
}
