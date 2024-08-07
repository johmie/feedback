package io.feedback.survey.repository;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.Result;
import io.feedback.survey.entity.ResultMockProvider;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class ResultRepositoryTest {

    private ResultRepository resultRepository;

    @Before
    public void setUp() {
        resultRepository = new ResultRepository();
        resultRepository.setEntityManager(mock(EntityManager.class));
    }

    @Test
    @Parameters(source = ResultMockProvider.class, method = "provideListWithoutId")
    public void saveResults_SomeResults_AnswerOfAnyResultIsLoaded(List<Result> resultMocks) {
        resultRepository.saveResults(resultMocks);

        for (Result resultMock : resultMocks) {
            verify(resultRepository.getEntityManager(), times(1)).find(Answer.class, resultMock.getAnswer().getId());
        }
    }

    @Test
    @Parameters(source = ResultMockProvider.class, method = "provideListWithoutId")
    public void saveResults_SomeResults_LoadedAnswersOfResultsAreSet(List<Result> resultMocks) {
        List<Answer> answerMocks = new ArrayList<>();
        for (int i = 0; i < resultMocks.size(); i++) {
            Answer answerMock = mock(Answer.class);
            when(resultRepository.getEntityManager().find(Answer.class, resultMocks.get(i).getAnswer().getId()))
                    .thenReturn(answerMock);
            answerMocks.add(i, answerMock);
        }

        resultRepository.saveResults(resultMocks);

        for (int i = 0; i < resultMocks.size(); i++) {
            verify(resultMocks.get(i)).setAnswer(answerMocks.get(i));
        }
    }

    @Test
    @Parameters(source = ResultMockProvider.class, method = "provideListWithoutId")
    public void saveResults_ResultsWithoutId_PersistMethodOfEntityManagerIsCalledForAnyResult(List<Result> resultMocks) {
        resultRepository.saveResults(resultMocks);

        for (Result resultMock : resultMocks) {
            verify(resultRepository.getEntityManager(), times(1)).persist(resultMock);
        }
    }

    @Test
    @Parameters(source = ResultMockProvider.class, method = "provideListWithId")
    public void saveResults_ResultsWithId_MergeMethodOfEntityManagerIsCalledForAnyResult(List<Result> resultMocks) {
        resultRepository.saveResults(resultMocks);

        for (Result resultMock : resultMocks) {
            verify(resultRepository.getEntityManager(), times(1)).merge(resultMock);
        }
    }
}