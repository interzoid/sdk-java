package com.interzoid.sdk.api;

import com.interzoid.sdk.model.AddressMatchKeyRequest;
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

public class AddressMatchKeyApiTest {
    private InterzoidApi interzoidApiMock;
    private AddressMatchKeyApi addressMatchKeyApi;

    @BeforeEach
    public void setUp() {
        interzoidApiMock = Mockito.mock(InterzoidApi.class);
        addressMatchKeyApi = new AddressMatchKeyApi.Builder()
                .withInterzoidApi(interzoidApiMock)
                .build();
    }

    @Test
    public void testSuccessfulRequest() throws Exception {
        String apiKey = "testApiKey";
        String address = "1234 Main Street";
        AddressMatchKeyRequest.MatchAlgorithm matchAlgorithm = AddressMatchKeyRequest.MatchAlgorithm.WIDE;

        String jsonResponse = "{\"SimKey\": \"12345\", \"Code\": \"Success\", \"Credits\": \"9999\"}";
        when(interzoidApiMock.doApiGetRequest(anyString(), anyString(), anyMap())).thenReturn(jsonResponse);

        AddressMatchKeyRequest request = new AddressMatchKeyRequest(apiKey, address, matchAlgorithm);

        MatchKeyResponse response = addressMatchKeyApi.doRequest(request);

        assertNotNull(response);
        assertEquals("12345", response.getSimKey());
    }

    @Test
    public void testFailedValidation_MissingApiKey() {
        AddressMatchKeyRequest invalidRequest = new AddressMatchKeyRequest(
                null,  // Missing API key
                "1234 Main Street",
                AddressMatchKeyRequest.MatchAlgorithm.WIDE
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            addressMatchKeyApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());

        ConstraintViolation<?> violation = violations.iterator().next();
        assertEquals("API key is required", violation.getMessage());
    }

    @Test
    public void testFailedValidation_MissingAddress() {
        AddressMatchKeyRequest invalidRequest = new AddressMatchKeyRequest(
                "testApiKey",
                null,  // Missing address
                AddressMatchKeyRequest.MatchAlgorithm.WIDE
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            addressMatchKeyApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());

        ConstraintViolation<?> violation = violations.iterator().next();
        assertEquals("Address is required", violation.getMessage());
    }

    @Test
    public void testFailedValidation_MissingAlgorithm() {
        AddressMatchKeyRequest invalidRequest = new AddressMatchKeyRequest(
                "testApiKey",
                "1234 Main Street",
                null  // Missing algorithm
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            addressMatchKeyApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());

        ConstraintViolation<?> violation = violations.iterator().next();
        assertEquals("Match algorithm is required", violation.getMessage());
    }
}
