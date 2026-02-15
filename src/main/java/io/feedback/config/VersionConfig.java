package io.feedback.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VersionConfig {

    private final String bootstrapVersion;
    private final String bootstrapIconsVersion;

    public VersionConfig(
            @Value("${app.bootstrapVersion}") String bootstrapVersion,
            @Value("${app.bootstrapIconsVersion}") String bootstrapIconsVersion) {
        this.bootstrapVersion = bootstrapVersion;
        this.bootstrapIconsVersion = bootstrapIconsVersion;
    }

    public String getBootstrapVersion() {
        return bootstrapVersion;
    }

    public String getBootstrapIconsVersion() {
        return bootstrapIconsVersion;
    }
}