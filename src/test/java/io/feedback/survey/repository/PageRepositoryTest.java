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
public class PageRepositoryTest {

    private PageRepository pageRepository;

    @Before
    public void setUp() {
        pageRepository = new PageRepository();
    }

    @Test
    public void repositoryExtendsAbstractRepository() {
        assertTrue(pageRepository instanceof AbstractRepository);
    }
}