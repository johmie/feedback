package io.feedback.survey.repository;

import io.feedback.core.repository.AbstractRepository;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class AnswerRepositoryTest {

    private AnswerRepository answerRepository;

    @Before
    public void setUp() {
        answerRepository = new AnswerRepository();
    }

    @Test
    public void repositoryExtendsAbstractRepository() {
        assertTrue(answerRepository instanceof AbstractRepository);
    }
}