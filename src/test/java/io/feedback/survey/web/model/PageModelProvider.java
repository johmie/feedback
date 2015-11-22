package io.feedback.survey.web.model;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Question;
import io.feedback.survey.entity.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public Object[] provideForCountQuestions() {
        return new Object[]{
                new Object[]{createMockWithTwoResults(), 1},
                new Object[]{createMockWithTwoResultsInTwoQuestions(), 2},
        };
    }

    public Object[] provideForNoQuestionAnswered() {
        return new Object[]{
                new Object[]{createMockWithEmptyResults(), createPageMockForNoQuestionAnsweredWithOneQuestion()},
                new Object[]{createMockWithEmptyResults(), createPageMockForNoQuestionAnsweredWithThreeQuestions()}
        };
    }

    public Object[] provideForNoQuestionAnsweredWithCountOfQuestions() {
        return new Object[]{
                new Object[]{createMockWithEmptyResults(), createPageMockForNoQuestionAnsweredWithOneQuestion(), 1},
                new Object[]{createMockWithEmptyResults(), createPageMockForNoQuestionAnsweredWithThreeQuestions(), 3}
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

    private Page createPageMockForNoQuestionAnsweredWithOneQuestion() {
        Question questionMock = mock(Question.class);
        when(questionMock.getId()).thenReturn(1L);

        Set<Question> questionMocks = new HashSet<>();
        questionMocks.add(questionMock);

        Page pageMock = mock(Page.class);
        when(pageMock.getQuestions()).thenReturn(questionMocks);

        return pageMock;
    }

    private Page createPageMockForNoQuestionAnsweredWithThreeQuestions() {
        Question questionMockOne = mock(Question.class);
        when(questionMockOne.getId()).thenReturn(1L);
        Question questionMockTwo = mock(Question.class);
        when(questionMockTwo.getId()).thenReturn(2L);
        Question questionMockThree = mock(Question.class);
        when(questionMockThree.getId()).thenReturn(3L);

        Set<Question> questionMocks = new HashSet<>();
        questionMocks.add(questionMockOne);
        questionMocks.add(questionMockTwo);
        questionMocks.add(questionMockThree);

        Page pageMock = mock(Page.class);
        when(pageMock.getQuestions()).thenReturn(questionMocks);

        return pageMock;
    }
}