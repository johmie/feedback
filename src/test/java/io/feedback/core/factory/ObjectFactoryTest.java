package io.feedback.core.factory;

import io.feedback.core.exception.ObjectInstantiationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ObjectFactoryTest {

    private ObjectFactory objectFactory;

    @BeforeEach
    public void setUp() {
        objectFactory = new ObjectFactory();
    }

    @Test
    public void createInstance_ClassOfTypeString_ObjectOfTypeStringIsCreated() {
        Class<String> stringClass = String.class;

        Object stringAsObject = objectFactory.createInstance(stringClass);

        assertInstanceOf(String.class, stringAsObject);
    }

    @Test
    public void createInstance_ClassOfTypeInteger_ObjectInstantiationExceptionIsThrown() {
        Class<Integer> integerClass = Integer.class;

        assertThrows(ObjectInstantiationException.class, () -> objectFactory.createInstance(integerClass));
    }
}