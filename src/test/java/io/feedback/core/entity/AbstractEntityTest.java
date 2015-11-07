package io.feedback.core.entity;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class AbstractEntityTest {

    private AbstractEntity abstractEntity;

    @Before
    public void setUp() {
        abstractEntity = mock(AbstractEntity.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    public void getAndSetIdWorks() {
        abstractEntity.setId(1L);
        assertEquals(1L, (long) abstractEntity.getId());
    }

    @Test
    public void getAndSetVersionWorks() {
        abstractEntity.setVersion(1L);
        assertEquals(1L, (long) abstractEntity.getVersion());
    }
}