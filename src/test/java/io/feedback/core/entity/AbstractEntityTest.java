package io.feedback.core.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class AbstractEntityTest {

    private AbstractEntity abstractEntity;

    @BeforeEach
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