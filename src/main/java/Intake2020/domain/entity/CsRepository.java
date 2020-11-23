package Intake2020.domain.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsRepository extends JpaRepository<CsEntity,Long>{

	Page<CsEntity> findBySubjectLike(String text, Pageable pageable);

	Page<CsEntity> findByContentLike(String text, Pageable pageable);


}
