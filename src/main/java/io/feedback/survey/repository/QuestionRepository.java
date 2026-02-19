package io.feedback.survey.repository;

import io.feedback.survey.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT DISTINCT q FROM Question q " +
            "LEFT JOIN FETCH q.answerRows ar " +
            "WHERE q.id = :id")
    Optional<Question> findById(Long id);
}