package io.feedback.survey.web.dto;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class ParticipationDtoTest {

    private ParticipationDto participationDto;

    @Before
    public void setUp() {
        participationDto = new ParticipationDto();
    }

    @Test
    public void setIdentifier_SomeIdentifier_SameValueIsReturnedByGetIdentifier() {
        String identifier = "Participation identifier";

        participationDto.setIdentifier(identifier);

        assertEquals(identifier, participationDto.getIdentifier());
    }

    @Test
    public void setRemoteAddress_SomeRemoteAddress_SameValueIsReturnedByGetRemoteAddress() {
        String remoteAddress = "127.0.0.1";

        participationDto.setRemoteAddress(remoteAddress);

        assertEquals(remoteAddress, participationDto.getRemoteAddress());
    }
}