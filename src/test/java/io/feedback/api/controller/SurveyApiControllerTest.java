package io.feedback.api.controller;

import io.feedback.api.dto.ApiResponse;
import io.feedback.survey.entity.Survey;
import io.feedback.survey.repository.SurveyRepository;
import io.feedback.survey.service.SurveyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SurveyApiControllerTest {

    @Mock
    private SurveyService surveyService;

    @Mock
    private SurveyRepository surveyRepository;

    private SurveyApiController controller;

    @BeforeEach
    public void setUp() {
        controller = new SurveyApiController();
        controller.setSurveyService(surveyService);
        lenient().when(surveyService.getSurveyRepository()).thenReturn(surveyRepository);
    }

    @Test
    public void getAllSurveys_ReturnsListOfSurveys() {
        Survey survey = new Survey();
        survey.setId(1L);
        survey.setName("Test Survey");
        List<Survey> surveys = Arrays.asList(survey);
        when(surveyService.findAll()).thenReturn(surveys);

        ResponseEntity<ApiResponse<List<Survey>>> response = controller.getAllSurveys();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals(1, response.getBody().getData().size());
    }

    @Test
    public void getSurveyById_WhenExists_ReturnsSurvey() {
        Survey survey = new Survey();
        survey.setId(1L);
        survey.setName("Test Survey");
        when(surveyService.findById(1L)).thenReturn(Optional.of(survey));

        ResponseEntity<ApiResponse<Survey>> response = controller.getSurveyById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
        assertEquals(survey, response.getBody().getData());
    }

    @Test
    public void getSurveyById_WhenNotExists_Returns404() {
        when(surveyService.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<ApiResponse<Survey>> response = controller.getSurveyById(999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }

    @Test
    public void createSurvey_WhenSuccess_Returns201() {
        Survey survey = new Survey();
        survey.setName("New Survey");
        doNothing().when(surveyService).saveSurvey(any(Survey.class));

        ResponseEntity<ApiResponse<Survey>> response = controller.createSurvey(survey);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
    }

    @Test
    public void createSurvey_WhenException_Returns400() {
        Survey survey = new Survey();
        survey.setName("New Survey");
        doThrow(new RuntimeException("Validation error")).when(surveyService).saveSurvey(any(Survey.class));

        ResponseEntity<ApiResponse<Survey>> response = controller.createSurvey(survey);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }

    @Test
    public void updateSurvey_WhenExists_Returns200() {
        Survey existingSurvey = new Survey();
        existingSurvey.setId(1L);
        existingSurvey.setVersion(0L);
        Survey updatedSurvey = new Survey();
        updatedSurvey.setName("Updated Survey");
        when(surveyService.getSurveyRepository().findById(1L)).thenReturn(Optional.of(existingSurvey));
        doNothing().when(surveyService).saveSurvey(any(Survey.class));

        ResponseEntity<ApiResponse<Survey>> response = controller.updateSurvey(1L, updatedSurvey);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
    }

    @Test
    public void updateSurvey_WhenNotExists_Returns404() {
        when(surveyService.getSurveyRepository().findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<ApiResponse<Survey>> response = controller.updateSurvey(999L, new Survey());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }

    @Test
    public void updateSurvey_WhenException_Returns400() {
        Survey existingSurvey = new Survey();
        existingSurvey.setId(1L);
        existingSurvey.setVersion(0L);
        when(surveyService.getSurveyRepository().findById(1L)).thenReturn(Optional.of(existingSurvey));
        doThrow(new RuntimeException("Validation error")).when(surveyService).saveSurvey(any(Survey.class));

        ResponseEntity<ApiResponse<Survey>> response = controller.updateSurvey(1L, new Survey());

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }

    @Test
    public void deleteSurvey_WhenExists_Returns200() {
        Survey survey = new Survey();
        survey.setId(1L);
        when(surveyService.findById(1L)).thenReturn(Optional.of(survey));
        doNothing().when(surveyService).deleteSurvey(survey);

        ResponseEntity<ApiResponse<Void>> response = controller.deleteSurvey(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isSuccess());
    }

    @Test
    public void deleteSurvey_WhenNotExists_Returns404() {
        when(surveyService.findById(999L)).thenReturn(Optional.empty());

        ResponseEntity<ApiResponse<Void>> response = controller.deleteSurvey(999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }

    @Test
    public void deleteSurvey_WhenException_Returns400() {
        Survey survey = new Survey();
        survey.setId(1L);
        when(surveyService.findById(1L)).thenReturn(Optional.of(survey));
        doThrow(new RuntimeException("Cannot delete")).when(surveyService).deleteSurvey(survey);

        ResponseEntity<ApiResponse<Void>> response = controller.deleteSurvey(1L);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
    }
}