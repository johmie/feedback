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

public class PageModelMockProvider {

    public Object[] provideOneWithCountOfResults() {
        return new Object[]{
                new Object[]{createOneWithEmptyResults(), 0},
                new Object[]{createOneWithOneResult(), 1},
                new Object[]{createOneWithTwoResults(), 2},
                new Object[]{createOneWithTwoResultsInTwoQuestions(), 2},
        };
    }

    public Object[] provideOneWithCountOfQuestions() {
        return new Object[]{
                new Object[]{createOneWithEmptyResults(), 1},
                new Object[]{createOneWithOneResult(), 1},
                new Object[]{createOneWithTwoResults(), 1},
                new Object[]{createOneWithTwoResultsInTwoQuestions(), 2},
        };
    }

    public Object[] provideOneWithEmptyResults() {
        return new Object[]{
                new Object[]{createOneWithEmptyResults(), createPageMockWithOneQuestion()},
                new Object[]{createOneWithEmptyResults(), createPageMockWithThreeQuestions()}
        };
    }

    private PageModel createOneWithOneResult() {
        Answer answerMock = createAnswerMock(1L);
        Result resultMock = createResultMock(null, answerMock);
        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMock);

        QuestionModel questionModelMock = createQuestionModelMock(resultMocks);
        Map<Long, QuestionModel> questionModelMocks = new HashMap<>();
        questionModelMocks.put(1L, questionModelMock);

        return createPageModelMock(questionModelMocks);
    }

    private PageModel createOneWithTwoResults() {
        Answer answerMockOne = createAnswerMock(1L);
        Answer answerMockTwo = createAnswerMock(2L);
        Result resultMockOne = createResultMock(null, answerMockOne);
        Result resultMockTwo = createResultMock(null, answerMockTwo);
        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMockOne);
        resultMocks.add(resultMockTwo);

        QuestionModel questionModelMock = createQuestionModelMock(resultMocks);
        Map<Long, QuestionModel> questionModelMocks = new HashMap<>();
        questionModelMocks.put(1L, questionModelMock);

        return createPageModelMock(questionModelMocks);
    }

    private PageModel createOneWithTwoResultsInTwoQuestions() {
        Answer answerMockOne = createAnswerMock(1L);
        Answer answerMockTwo = createAnswerMock(2L);
        Result resultMockOne = createResultMock(null, answerMockOne);
        Result resultMockTwo = createResultMock(null, answerMockTwo);
        List<Result> resultMocksOne = new ArrayList<>();
        resultMocksOne.add(resultMockOne);
        List<Result> resultMocksTwo = new ArrayList<>();
        resultMocksOne.add(resultMockTwo);

        QuestionModel questionModelMockTwo = createQuestionModelMock(resultMocksTwo);
        QuestionModel questionModelMockOne = createQuestionModelMock(resultMocksOne);
        Map<Long, QuestionModel> questionModelMocks = new HashMap<>();
        questionModelMocks.put(1L, questionModelMockOne);
        questionModelMocks.put(2L, questionModelMockTwo);

        return createPageModelMock(questionModelMocks);
    }

    private PageModel createOneWithEmptyResults() {
        Answer answerMockOne = createAnswerMock(null);
        Answer answerMockTwo = createAnswerMock(null);
        Result resultMockOne = createResultMock(null, answerMockOne);
        Result resultMockTwo = createResultMock(null, answerMockTwo);
        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMockOne);
        resultMocks.add(resultMockTwo);

        QuestionModel questionModelMock = createQuestionModelMock(resultMocks);
        Map<Long, QuestionModel> questionModelMocks = new HashMap<>();
        questionModelMocks.put(1L, questionModelMock);

        return createPageModelMock(questionModelMocks);
    }

    private Page createPageMockWithOneQuestion() {
        Question questionMock = createQuestionMock(1L);
        Set<Question> questionMocks = new HashSet<>();
        questionMocks.add(questionMock);

        return createPageMock(1L, questionMocks);
    }

    private Page createPageMockWithThreeQuestions() {
        Question questionMockOne = createQuestionMock(1L);
        Question questionMockTwo = createQuestionMock(2L);
        Question questionMockThree = createQuestionMock(3L);
        Set<Question> questionMocks = new HashSet<>();
        questionMocks.add(questionMockOne);
        questionMocks.add(questionMockTwo);
        questionMocks.add(questionMockThree);

        return createPageMock(1L, questionMocks);
    }

    private Answer createAnswerMock(Long answerId) {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(answerId);
        return answerMock;
    }

    private Result createResultMock(Long resultId, Answer answerMock) {
        Result resultMock = mock(Result.class);
        when(resultMock.getId()).thenReturn(resultId);
        when(resultMock.getAnswer()).thenReturn(answerMock);
        return resultMock;
    }

    private QuestionModel createQuestionModelMock(List<Result> resultMocks) {
        QuestionModel questionModelMock = mock(QuestionModel.class);
        when(questionModelMock.getResults()).thenReturn(resultMocks);
        return questionModelMock;
    }

    private PageModel createPageModelMock(Map<Long, QuestionModel> questionModelMocks) {
        PageModel pageModelMock = mock(PageModel.class);
        when(pageModelMock.getQuestionModels()).thenReturn(questionModelMocks);
        return pageModelMock;
    }

    private Question createQuestionMock(Long questionId) {
        Question questionMock = mock(Question.class);
        when(questionMock.getId()).thenReturn(questionId);
        return questionMock;
    }

    private Page createPageMock(Long pageId, Set<Question> questionMocks) {
        Page pageMock = mock(Page.class);
        when(pageMock.getId()).thenReturn(pageId);
        when(pageMock.getQuestions()).thenReturn(questionMocks);
        return pageMock;
    }
}