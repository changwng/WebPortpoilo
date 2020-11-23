package Intake2020.domain.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CsCommentRepository extends JpaRepository<CsCommentEntity, Long>{

	//JPQL
	@Query("select r from CsCommentEntity r where bNo=:b_no order by rNo desc")
	List<CsCommentEntity> findByBNo(@Param("b_no")Long b_no);
	
				//쿼리작성 -> findBy~뒤에는 대문자가 와야함
	
	//spring-data-jpa에서 _(언더스코어)가 프로퍼티를 찾기위한 탐색 경로를 지정하는 예약어
	
	
	
	
	/*메서드쿼리전략
	 * 조건컬럼 검색컬럼이나 정렬컬럼으로 사용하는 컬럼은 그냥 소문자로 만들자(메서드쿼리사용에 편리함)
	 * 
	1.by 뒤에는 컬럼명.. 첫글자 대문자로 표기해서 쓰자
	ex)column명이 bno이면 Bno으로 표기
	
	2.컬럼명 표기를 카멜표기법(낙타법)이나 스네이크법('_'으로 처리된 표기법) 사용하지 말자 
	
	
	
	
	*/
	
	//List<CsCommentEntity> findAllByBNoOrderByRNoDesc(Long bno);

}
