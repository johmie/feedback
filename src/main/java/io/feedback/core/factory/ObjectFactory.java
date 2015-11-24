package io.feedback.core.factory;

import io.feedback.core.exception.ObjectInstantiationException;
import org.springframework.stereotype.Component;

@Component
public class ObjectFactory {

    public Object createInstance(Class clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            throw new ObjectInstantiationException();
        }
    }
}