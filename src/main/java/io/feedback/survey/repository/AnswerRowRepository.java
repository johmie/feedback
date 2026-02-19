package io.feedback.survey.repository;

import io.feedback.survey.entity.AnswerRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRowRepository extends JpaRepository<AnswerRow, Long> {
}