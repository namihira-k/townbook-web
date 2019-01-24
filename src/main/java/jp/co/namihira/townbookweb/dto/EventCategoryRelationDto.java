package jp.co.namihira.townbookweb.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import jp.co.namihira.townbookweb.consts.EventCategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event_category_relation")
@Setter
@Getter
@NoArgsConstructor
public class EventCategoryRelationDto extends AbstractDto {

    @Id
    @SequenceGenerator(name="event_category_relation_seq", initialValue=1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="event_category_relation_seq")
    public Integer id;

    public Integer eventId;
    
    @Enumerated(EnumType.STRING)
    public EventCategoryEnum eventCategory;

}
