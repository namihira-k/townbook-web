package jp.co.namihira.townbookweb.service.event;

import java.time.LocalDateTime;
import java.util.List;

import jp.co.namihira.townbookweb.consts.EventCategoryEnum;
import jp.co.namihira.townbookweb.consts.EventTypeEnum;
import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.Data;

@Data
public class EventSearchCondition {

    private String name;
    private List<String> prefectureCodes;
    private List<String> stationCodes;
    private List<EventCategoryEnum> categories;
    private LocalDateTime startDateTime;
    private Boolean isFree;
    
    public void setEventTypes(List<EventTypeEnum> eventTypes) {
        if (CommonUtil.isEmpty(eventTypes)) {
            return;
        }
        
        if (eventTypes.contains(EventTypeEnum.FREE)) {
            this.isFree = true;
        }
        
    }
}
