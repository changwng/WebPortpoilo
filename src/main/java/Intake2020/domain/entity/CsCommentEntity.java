package Intake2020.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//csdetail(공지사항 페이지 게시글)과 관계를 만들진 않았음,하지만 원래 jpa의 역할은 관계연결을 해주어 편하게 해줌

@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) //시간 자동설정시 사용하기_application에도 같이 설정
@Getter
@Table(name="intake_comment")
@Entity
public class CsCommentEntity {

	
	@Id
	@Column(name="r_no")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rNo; //댓글의 번호
	
	@Column(name="b_no")
	private Long bNo; //게시글 번호(삭제할 경우 게시글번호가 있어야함)
	
	
	@Column(nullable = false)
	private String writer; //작성자

	
	@Column(nullable = false)
	private String content; //댓글내용
	
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime reg_date; //작성일자

	@Builder
	public CsCommentEntity(Long bNo, String writer, String content) {
		this.bNo = bNo;
		this.writer = writer;
		this.content = content;
	}
	
	
	public CsCommentEntity edit(String content) {
		this.content = content;
		return this;
	}
	
	
	
	
	
	
	
}