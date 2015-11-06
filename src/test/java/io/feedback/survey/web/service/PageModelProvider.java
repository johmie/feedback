package io.feedback.survey.web.service;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.Result;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.model.QuestionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PageModelProvider {

    public Object[] provideForCountResults() {
        return new Object[]{
                new Object[]{createMockWithOneResult(), 1},
                new Object[]{createMockWithTwoResults(), 2},
                new Object[]{createMockWithTwoResultsInTwoQuestions(), 2},
                new Object[]{createMockWithEmptyResults(), 0},
        };
    }

    private PageModel createMockWithOneResult() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(1L);

        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);

        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMock);

        QuestionModel questionModelMock = mock(QuestionModel.class);
        when(questionModelMock.getResults()).thenReturn(resultMocks);

        Map<Long, QuestionModel> questionModelMocks = new HashMap<>();
        questionModelMocks.put(1L, questionModelMock);

        PageModel pageModelMock = mock(PageModel.class);
        when(pageModelMock.getQuestionModels()).thenReturn(questionModelMocks);

        return pageModelMock;
    }

    private PageModel createMockWithTwoResults() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(1L);

        Result resultMockOne = mock(Result.class);
        when(resultMockOne.getAnswer()).thenReturn(answerMock);
        Result resultMockTwo = mock(Result.class);
        when(resultMockTwo.getAnswer()).thenReturn(answerMock);

        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMockOne);
        resultMocks.add(resultMockTwo);

        QuestionModel questionModelMock = mock(QuestionModel.class);
        when(questionModelMock.getResults()).thenReturn(resultMocks);

        Map<Long, QuestionModel> questionModelMocks = new HashMap<>();
        questionModelMocks.put(1L, questionModelMock);

        PageModel pageModelMock = mock(PageModel.class);
        when(pageModelMock.getQuestionModels()).thenReturn(questionModelMocks);

        return pageModelMock;
    }

    private PageModel createMockWithTwoResultsInTwoQuestions() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(1L);

        Result resultMockOne = mock(Result.class);
        when(resultMockOne.getAnswer()).thenReturn(answerMock);
        Result resultMockTwo = mock(Result.class);
        when(resultMockTwo.getAnswer()).thenReturn(answerMock);

        List<Result> resultMocksOne = new ArrayList<>();
        resultMocksOne.add(resultMockOne);
        QuestionModel questionModelMockOne = mock(QuestionModel.class);
        when(questionModelMockOne.getResults()).thenReturn(resultMocksOne);

        List<Result> resultMocksTwo = new ArrayList<>();
        resultMocksOne.add(resultMockTwo);
        QuestionModel questionModelMockTwo = mock(QuestionModel.class);
        when(questionModelMockTwo.getResults()).thenReturn(resultMocksTwo);

        Map<Long, QuestionModel> questionModelMocks = new HashMap<>();
        questionModelMocks.put(1L, questionModelMockOne);
        questionModelMocks.put(2L, questionModelMockTwo);

        PageModel pageModelMock = mock(PageModel.class);
        when(pageModelMock.getQuestionModels()).thenReturn(questionModelMocks);

        return pageModelMock;
    }

    private PageModel createMockWithEmptyResults() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(null);

        Result resultMockOne = mock(Result.class);
        when(resultMockOne.getAnswer()).thenReturn(answerMock);
        Result resultMockTwo = mock(Result.class);
        when(resultMockTwo.getAnswer()).thenReturn(answerMock);

        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMockOne);
        resultMocks.add(resultMockTwo);

        QuestionModel questionModelMock = mock(QuestionModel.class);
        when(questionModelMock.getResults()).thenReturn(resultMocks);

        Map<Long, QuestionModel> questionModelMocks = new HashMap<>();
        questionModelMocks.put(1L, questionModelMock);

        PageModel pageModelMock = mock(PageModel.class);
        when(pageModelMock.getQuestionModels()).thenReturn(questionModelMocks);

        return pageModelMock;
    }
}