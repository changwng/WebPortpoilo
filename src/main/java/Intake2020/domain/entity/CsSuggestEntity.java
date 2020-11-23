package Intake2020.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Getter
@Table(name="intake_suggest")
@Entity
public class CsSuggestEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	
	@Column(nullable = false)
	private String writer;
	
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	
	@Builder
	public CsSuggestEntity(String content,String writer) {
		this.content = content;
		this.writer = writer;
	}
	
	
	
	
	
}
