package com.interzoid.sdk.api;

import com.interzoid.sdk.api.exceptions.ValidationException;
import com.interzoid.sdk.model.MatchScoreResponse;
import com.interzoid.sdk.model.OrganizationMatchScoreRequest;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class OrganizationMatchScoreApiTest {
    private InterzoidApi interzoidApiMock;
    private OrganizationMatchScoreApi organizationMatchScoreApi;

    @BeforeEach
    public void setUp() {
        interzoidApiMock = Mockito.mock(InterzoidApi.class);
        organizationMatchScoreApi = new OrganizationMatchScoreApi.Builder()
                .withInterzoidApi(interzoidApiMock)
                .build();
    }

    @Test
    public void testSuccessfulRequest() throws Exception {
        String apiKey = "testApiKey";
        String org1 = "Apple";
        String org2 = "Apple Inc.";

        String jsonResponse = "{\"Score\": \"89\", \"Code\": \"Success\", \"Credits\": \"9999\"}";
        when(interzoidApiMock.doApiGetRequest(anyString(), anyString(), anyMap())).thenReturn(jsonResponse);

        OrganizationMatchScoreRequest request = new OrganizationMatchScoreRequest(apiKey, org1, org2);

        MatchScoreResponse response = organizationMatchScoreApi.doRequest(request);

        assertNotNull(response);
        assertEquals("89", response.getScore());
    }

    @Test
    public void testFailedValidation_MissingApiKey() {
        OrganizationMatchScoreRequest invalidRequest = new OrganizationMatchScoreRequest(
                null,  // Missing API key
                "Apple",
                "Apple Inc."
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            organizationMatchScoreApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());
    }

    @Test
    public void testFailedValidation_MissingOrg1() {
        OrganizationMatchScoreRequest invalidRequest = new OrganizationMatchScoreRequest(
                "testApiKey",
                null,  // Missing org1
                "Apple Inc."
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            organizationMatchScoreApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());
    }

    @Test
    public void testFailedValidation_MissingOrg2() {
        OrganizationMatchScoreRequest invalidRequest = new OrganizationMatchScoreRequest(
                "testApiKey",
                "Apple",
                null  // Missing org2
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            organizationMatchScoreApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());
    }
}
