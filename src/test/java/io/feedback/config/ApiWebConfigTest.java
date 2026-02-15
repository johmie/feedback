package io.feedback.config;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

public class ApiWebConfigTest {

    @Test
    public void configureContentNegotiation_VerifiesConfiguration() {
        ApiWebConfig config = new ApiWebConfig();
        ContentNegotiationConfigurer mockConfigurer = mock(ContentNegotiationConfigurer.class, withSettings().lenient());
        when(mockConfigurer.defaultContentType(any(MediaType.class))).thenReturn(mockConfigurer);
        when(mockConfigurer.favorParameter(anyBoolean())).thenReturn(mockConfigurer);
        when(mockConfigurer.ignoreAcceptHeader(anyBoolean())).thenReturn(mockConfigurer);
        when(mockConfigurer.useRegisteredExtensionsOnly(anyBoolean())).thenReturn(mockConfigurer);
        when(mockConfigurer.mediaType(anyString(), any(MediaType.class))).thenReturn(mockConfigurer);

        config.configureContentNegotiation(mockConfigurer);

        verify(mockConfigurer).defaultContentType(MediaType.APPLICATION_JSON);
        verify(mockConfigurer).favorParameter(false);
        verify(mockConfigurer).ignoreAcceptHeader(false);
        verify(mockConfigurer).useRegisteredExtensionsOnly(false);
        verify(mockConfigurer).mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Test
    void addCorsMappings_VerifiesConfiguration() {
        ApiWebConfig config = new ApiWebConfig();
        CorsRegistry mockRegistry = mock(CorsRegistry.class, withSettings().lenient());
        org.springframework.web.servlet.config.annotation.CorsRegistration registration =
                mock(org.springframework.web.servlet.config.annotation.CorsRegistration.class, withSettings().lenient());
        when(registration.allowedOrigins(anyString())).thenReturn(registration);
        when(registration.allowedMethods(any(String[].class))).thenReturn(registration);
        when(registration.allowedHeaders(anyString())).thenReturn(registration);
        when(registration.maxAge(anyLong())).thenReturn(registration);
        when(mockRegistry.addMapping(anyString())).thenReturn(registration);

        config.addCorsMappings(mockRegistry);

        verify(mockRegistry).addMapping("/api/**");
        verify(registration).allowedOrigins("*");
        verify(registration).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
        verify(registration).allowedHeaders("*");
        verify(registration).maxAge(3600L);
    }
}