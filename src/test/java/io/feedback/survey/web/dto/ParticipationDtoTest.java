package io.feedback.survey.web.dto;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class ParticipationDtoTest {

    private ParticipationDto participationDto;

    @Before
    public void setUp() {
        participationDto = new ParticipationDto();
    }

    @Test
    public void getAndSetIdentifier() {
        String identifier = "Participation identifier";
        participationDto.setIdentifier(identifier);
        assertEquals(identifier, participationDto.getIdentifier());
    }

    @Test
    public void getAndSetRemoteAddress() {
        String remoteAddress = "127.0.0.1";
        participationDto.setRemoteAddress(remoteAddress);
        assertEquals(remoteAddress, participationDto.getRemoteAddress());
    }
}