package jp.co.namihira.townbookweb.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import jp.co.namihira.townbookweb.consts.EventCategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event_categories")
@Setter
@Getter
@NoArgsConstructor
public class EventCategoryDto extends AbstractDto {

    @Id
    @SequenceGenerator(name="event_category_seq", initialValue=1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="event_category_seq")
    private Integer id;
    
    @Enumerated(EnumType.STRING)
    private EventCategoryEnum eventCategory;
    
    @ManyToMany(mappedBy="eventCategoryDtos")
    private List<EventDto> eventDtos;

}
