package Intake2020.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinRepository extends JpaRepository<JoinEntity, String>{

	JoinEntity findByEmailAndPw(String email, String pw);

	Optional<JoinEntity> findByEmail(String email);

}
