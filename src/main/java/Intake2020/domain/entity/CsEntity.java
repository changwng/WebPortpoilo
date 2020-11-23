package Intake2020.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="intake_cs")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@DynamicUpdate
public class CsEntity {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@Column(nullable = false) //=not null
	private String subject;
	
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime reg_date;
	
	private int count;
	
	/*private String writer;*/
	
	@Column(columnDefinition = "varchar(255) default 'Intake'", nullable=false)
	private String writer;
	
	/*@Column(columnDefinition = "varchar(255) default '127.0.0.1'", nullable=false)
	private String user_ip;*/
	
	@Column(columnDefinition = "text" , nullable = false)
	private String content;
	
	
	
	@Builder
	public CsEntity(String subject, String writer, String content) { //String user_ip,
		this.subject = subject;
		this.writer = writer;
		//this.user_ip = user_ip;
		this.content = content;
	}
	
	
	public CsEntity update(String subject, String content) {
		this.subject=subject;
		this.content=content;
		return this;
		
	}
	
	//조회수증가
	public CsEntity countIncrement() {
		this.count++;
		return this;
	}
	
	
	
	
	
}
