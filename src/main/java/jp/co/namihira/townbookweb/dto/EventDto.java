package jp.co.namihira.townbookweb.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import jp.co.namihira.townbookweb.consts.EventCategoryEnum;
import jp.co.namihira.townbookweb.util.CommonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "events")
@Setter
@Getter
@NoArgsConstructor
public class EventDto extends AbstractDto implements Cloneable {

    @Id
    @SequenceGenerator(name = "event_seq", initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    public Integer id;

    public String name;

    public String place;

    public String prefectureCode;

    public String stationCode;

    @Transient
    public String stationName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime startDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime endDateTime;

    public String conditions;

    public Boolean isFree = false;

    public String url;

    public String content;

    @Column(unique = true)
    public String uuid;

    @Transient
    private String viewUrl;

    @Transient
    private List<EventCategoryEnum> eventCategories;

    public EventDto addEventCategory(EventCategoryEnum category) {
        if (CommonUtil.isEmpty(this.eventCategories)) {
            this.eventCategories = CommonUtil.list();
        }
        this.eventCategories.add(category);
        return this;
    }

    @PrePersist
    public void autofill() {
        this.setUuid(UUID.randomUUID().toString());
    }

    @Override
    public EventDto clone() {
        EventDto dto = null;
        try {
            dto = (EventDto) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return dto;
    }

}
