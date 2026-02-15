package io.feedback.survey.web.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParticipationDtoTest {

    private ParticipationDto participationDto;

    @BeforeEach
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