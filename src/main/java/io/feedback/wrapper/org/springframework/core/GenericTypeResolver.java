package io.feedback.wrapper.org.springframework.core;

import org.springframework.stereotype.Component;

@Component
public class GenericTypeResolver<T> {

    public Class<T> resolveTypeArgument(Class<?> runtimeClass, Class<?> genericClass) {
        return (Class<T>) org.springframework.core.GenericTypeResolver.resolveTypeArgument(runtimeClass, genericClass);
    }
}