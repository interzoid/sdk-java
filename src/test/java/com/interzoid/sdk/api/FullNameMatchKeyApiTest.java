package com.interzoid.sdk.api;

import com.interzoid.sdk.api.exceptions.ValidationException;
import com.interzoid.sdk.model.FullNameMatchKeyRequest;
import com.interzoid.sdk.model.MatchKeyResponse;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class FullNameMatchKeyApiTest {
    private InterzoidApi interzoidApiMock;
    private FullNameMatchKeyApi fullNameMatchKeyApi;

    @BeforeEach
    public void setUp() {
        interzoidApiMock = Mockito.mock(InterzoidApi.class);
        fullNameMatchKeyApi = new FullNameMatchKeyApi.Builder()
                .withInterzoidApi(interzoidApiMock)
                .build();
    }

    @Test
    public void testSuccessfulRequest() throws Exception {
        String apiKey = "testApiKey";
        String fullName = "John Doe";

        String jsonResponse = "{\"SimKey\": \"12345\", \"Code\": \"Success\", \"Credits\": \"9999\"}";
        when(interzoidApiMock.doApiGetRequest(anyString(), anyString(), anyMap())).thenReturn(jsonResponse);

        FullNameMatchKeyRequest request = new FullNameMatchKeyRequest(apiKey, fullName);

        MatchKeyResponse response = fullNameMatchKeyApi.doRequest(request);

        assertNotNull(response);
        assertEquals("12345", response.getSimKey());
    }

    @Test
    public void testFailedValidation_MissingApiKey() {
        FullNameMatchKeyRequest invalidRequest = new FullNameMatchKeyRequest(
                null,  // Missing API key
                "John Doe"
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            fullNameMatchKeyApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());
    }

    @Test
    public void testFailedValidation_MissingFullName() {
        FullNameMatchKeyRequest invalidRequest = new FullNameMatchKeyRequest(
                "testApiKey",
                null  // Missing full name
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            fullNameMatchKeyApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());
    }
}
