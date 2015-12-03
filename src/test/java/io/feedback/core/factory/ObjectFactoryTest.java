package io.feedback.core.factory;

import io.feedback.core.exception.ObjectInstantiationException;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class ObjectFactoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private ObjectFactory objectFactory;

    @Before
    public void setUp() {
        objectFactory = new ObjectFactory();
    }

    @Test
    public void createInstance_ClassOfTypeString_ObjectOfTypeStringIsCreated() {
        Class<String> stringClass = String.class;

        Object stringAsObject = objectFactory.createInstance(stringClass);
        String string = (String) stringAsObject;

        assertTrue(string instanceof String);
    }

    @Test
    public void createInstance_ClassOfTypeInteger_ObjectInstantiationExceptionIsThrown() {
        Class<Integer> integerClass = Integer.class;

        thrown.expect(ObjectInstantiationException.class);

        objectFactory.createInstance(integerClass);
    }
}