package jp.co.namihira.townbookweb.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime recommended;
    
    public String conditions;

    public Boolean isFree = false;

    public String url;

    @Column(length = 1024)
    public String content;

    public String uuid;

    @Transient
    private String viewUrl;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "event_category_relations",
               joinColumns = { @JoinColumn(name = "event_id") },
               inverseJoinColumns = { @JoinColumn(name = "event_category_id") })
    private List<EventCategoryDto> eventCategoryDtos;
    
    @Transient
    private List<EventCategoryEnum> eventCategories;

    public EventDto addEventCategory(EventCategoryEnum category) {
        if (CommonUtil.isEmpty(this.eventCategories)) {
            this.eventCategories = CommonUtil.list();
        }
        this.eventCategories.add(category);
        return this;
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
