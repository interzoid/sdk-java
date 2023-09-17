package com.interzoid.sdk.api;

import com.interzoid.sdk.model.FullNameMatchScoreRequest;
import com.interzoid.sdk.model.MatchScoreResponse;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class FullNameMatchScoreApiTest {

    private InterzoidApi interzoidApiMock;
    private FullNameMatchScoreApi fullNameMatchScoreApi;

    @BeforeEach
    public void setUp() {
        interzoidApiMock = Mockito.mock(InterzoidApi.class);
        fullNameMatchScoreApi = new FullNameMatchScoreApi.Builder()
                .withInterzoidApi(interzoidApiMock)
                .build();
    }

    @Test
    public void testSuccessfulRequest() throws Exception {
        String apiKey = "testApiKey";
        String fullName1 = "John Smith";
        String fullName2 = "John Smyth";

        String jsonResponse = "{\"Score\": \"89\", \"Code\": \"Success\", \"Credits\": \"9999\"}";
        when(interzoidApiMock.doGetRequest(anyString(), anyString(), anyMap())).thenReturn(jsonResponse);

        FullNameMatchScoreRequest request = new FullNameMatchScoreRequest(apiKey, fullName1, fullName2);

        MatchScoreResponse response = fullNameMatchScoreApi.doRequest(request);

        assertNotNull(response);
        assertEquals("89", response.getScore());
    }

    @Test
    public void testFailedValidation_MissingApiKey() {
        FullNameMatchScoreRequest invalidRequest = new FullNameMatchScoreRequest(
                null,  // Missing API key
                "John Smith",
                "John Smyth"
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            fullNameMatchScoreApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());
    }

    @Test
    public void testFailedValidation_MissingFullName1() {
        FullNameMatchScoreRequest invalidRequest = new FullNameMatchScoreRequest(
                "testApiKey",
                null,  // Missing full name 1
                "John Smyth"
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            fullNameMatchScoreApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());
    }

    @Test
    public void testFailedValidation_MissingFullName2() {
        FullNameMatchScoreRequest invalidRequest = new FullNameMatchScoreRequest(
                "testApiKey",
                "John Smith",
                null  // Missing full name 2
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            fullNameMatchScoreApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());
    }
}
