package io.feedback.api.controller;

import io.feedback.api.dto.ApiResponse;
import io.feedback.survey.entity.Survey;
import io.feedback.survey.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/surveys", produces = MediaType.APPLICATION_JSON_VALUE)
public class SurveyApiController {

    private SurveyService surveyService;

    public SurveyService getSurveyService() {
        return surveyService;
    }

    @Autowired
    public void setSurveyService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Survey>>> getAllSurveys() {
        List<Survey> surveys = getSurveyService().findAll();
        return ResponseEntity.ok(
                ApiResponse.success("Surveys retrieved successfully", surveys)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Survey>> getSurveyById(@PathVariable Long id) {
        Optional<Survey> survey = surveyService.findById(id);
        if (survey.isPresent()) {
            return ResponseEntity.ok(
                    ApiResponse.success("Survey retrieved successfully", survey.get())
            );
        } else {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND
            ).body(
                    ApiResponse.error("Survey not found with id: " + id)
            );
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Survey>> createSurvey(@Valid @RequestBody Survey survey) {
        try {
            surveyService.saveSurvey(survey);
            return ResponseEntity.status(
                    HttpStatus.CREATED
            ).body(
                    ApiResponse.success("Survey created successfully", survey)
            );
        } catch (Exception e) {
            return ResponseEntity.status(
                    HttpStatus.BAD_REQUEST
            ).body(
                    ApiResponse.error("Failed to create survey: " + e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Survey>> updateSurvey(
            @PathVariable Long id,
            @Valid @RequestBody Survey survey) {
        Optional<Survey> existingSurvey = surveyService.getSurveyRepository().findById(id);
        if (existingSurvey.isPresent()) {
            try {
                survey.setId(id);
                survey.setVersion(existingSurvey.get().getVersion());
                surveyService.saveSurvey(survey);
                return ResponseEntity.ok(
                        ApiResponse.success("Survey updated successfully", survey)
                );
            } catch (Exception e) {
                return ResponseEntity.status(
                        HttpStatus.BAD_REQUEST
                ).body(
                        ApiResponse.error("Failed to update survey: " + e.getMessage())
                );
            }
        } else {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND
            ).body(
                    ApiResponse.error("Survey not found with id: " + id)
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSurvey(@PathVariable Long id) {
        Optional<Survey> survey = surveyService.findById(id);
        if (survey.isPresent()) {
            try {
                surveyService.deleteSurvey(survey.get());
                return ResponseEntity.ok(
                        ApiResponse.success("Survey deleted successfully", null)
                );
            } catch (Exception e) {
                return ResponseEntity.status(
                        HttpStatus.BAD_REQUEST
                ).body(
                        ApiResponse.error("Failed to delete survey: " + e.getMessage())
                );
            }
        } else {
            return ResponseEntity.status(
                    HttpStatus.NOT_FOUND
            ).body(
                    ApiResponse.error("Survey not found with id: " + id)
            );
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Survey>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ": " + errorMessage);
        });
        return ResponseEntity.status(
                HttpStatus.BAD_REQUEST
        ).body(
                ApiResponse.error("Validation failed", errors)
        );
    }
}