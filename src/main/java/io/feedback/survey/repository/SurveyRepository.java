package io.feedback.survey.repository;

import io.feedback.survey.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    @Query("SELECT DISTINCT s FROM Survey s " +
            "LEFT JOIN FETCH s.pages p " +
            "LEFT JOIN FETCH p.questions q " +
            "LEFT JOIN FETCH q.answers a " +
            "LEFT JOIN FETCH q.answerRows ar")
    List<Survey> findAll();

    @Query("SELECT DISTINCT s FROM Survey s " +
            "LEFT JOIN FETCH s.pages p " +
            "LEFT JOIN FETCH p.questions q " +
            "LEFT JOIN FETCH q.answers a " +
            "LEFT JOIN FETCH q.answerRows ar " +
            "WHERE s.id = :id")
    Optional<Survey> findById(Long id);
}