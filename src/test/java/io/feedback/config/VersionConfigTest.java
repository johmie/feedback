package io.feedback.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VersionConfigTest {

    @Test
    void getBootstrapVersion_ReturnsInjectedVersion() {
        VersionConfig config = new VersionConfig("5.3.0", "1.10.0");

        assertEquals("5.3.0", config.getBootstrapVersion());
    }

    @Test
    void getBootstrapIconsVersion_ReturnsInjectedVersion() {
        VersionConfig config = new VersionConfig("5.3.0", "1.10.0");

        assertEquals("1.10.0", config.getBootstrapIconsVersion());
    }

    @Test
    void constructor_InjectsBothVersions() {
        VersionConfig config = new VersionConfig("5.2.3", "1.11.1");

        assertEquals("5.2.3", config.getBootstrapVersion());
        assertEquals("1.11.1", config.getBootstrapIconsVersion());
    }
}