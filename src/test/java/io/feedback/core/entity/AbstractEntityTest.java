package io.feedback.core.entity;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
public class AbstractEntityTest {

    private AbstractEntity abstractEntity;

    @Before
    public void setUp() {
        abstractEntity = mock(AbstractEntity.class, Mockito.CALLS_REAL_METHODS);
    }

    @Test
    public void setId_SomeId_SameValueIsReturnedByGetId() {
        Long id = 1L;

        abstractEntity.setId(id);

        assertEquals(id, abstractEntity.getId());
    }

    @Test
    public void setVersion_SomeVersion_SameValueIsReturnedByGetVersion() {
        Long version = 1L;

        abstractEntity.setVersion(version);

        assertEquals(version, abstractEntity.getVersion());
    }
}