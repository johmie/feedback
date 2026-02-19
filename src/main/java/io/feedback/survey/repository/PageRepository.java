package io.feedback.survey.repository;

import io.feedback.survey.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    @Query("SELECT p FROM Page p " +
            "LEFT JOIN FETCH p.questions q " +
            "LEFT JOIN FETCH q.answers a " +
            "LEFT JOIN FETCH q.answerRows ar " +
            "WHERE p.survey.id = :surveyId " +
            "ORDER BY p.position, q.position, a.position")
    List<Page> findBySurveyId(Long surveyId);
}