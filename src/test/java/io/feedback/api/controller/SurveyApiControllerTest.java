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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
import static org.mockito.Mockito.mock;
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
        List<Survey> surveys = List.of(survey);
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

    @Test
    public void handleValidationExceptions_Returns400WithErrors() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError nameError = new FieldError("survey", "name", "Name may not be blank");
        FieldError titleError = new FieldError("survey", "title", "Title may not be blank");
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList(nameError, titleError));
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<ApiResponse<Survey>> response = controller.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertEquals("Validation failed", response.getBody().getMessage());
        assertEquals(2, response.getBody().getErrors().size());
        assertTrue(response.getBody().getErrors().contains("name: Name may not be blank"));
        assertTrue(response.getBody().getErrors().contains("title: Title may not be blank"));
    }

    @Test
    public void handleValidationExceptions_WithSingleError_Returns400WithSingleError() {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError nameError = new FieldError("survey", "name", "Name must be between 10 and 200 characters");
        when(bindingResult.getAllErrors()).thenReturn(List.of(nameError));
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<ApiResponse<Survey>> response = controller.handleValidationExceptions(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertFalse(response.getBody().isSuccess());
        assertEquals("Validation failed", response.getBody().getMessage());
        assertEquals(1, response.getBody().getErrors().size());
        assertEquals("name: Name must be between 10 and 200 characters", response.getBody().getErrors().get(0));
    }
}