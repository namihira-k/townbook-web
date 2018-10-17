package jp.co.namihira.townbookweb.dto;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class AbstractDto {

	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@PrePersist
	public void onPrePersist() {
		setCreatedAt(LocalDateTime.now());
		setUpdatedAt(LocalDateTime.now());
	}
	
	@PreUpdate
	public void onPreUpdate() {
		setUpdatedAt(LocalDateTime.now());
	}
	
}
