package io.feedback.core.factory;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class ObjectFactoryTest {

    private ObjectFactory objectFactory;

    @Before
    public void setUp() {
        objectFactory = new ObjectFactory();
    }

    @Test
    public void createInstanceCreatesCorrectInstance() {
        Object stringAsObject = objectFactory.createInstance(String.class);
        String string = (String) stringAsObject;
        assertTrue(string instanceof String);
    }
}