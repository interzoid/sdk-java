package com.interzoid.sdk.api;

import com.interzoid.sdk.model.CompanyNameMatchKeyRequest;
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

public class CompanyNameMatchKeyApiTest {

    private InterzoidApi interzoidApiMock;
    private CompanyNameMatchKeyApi companyNameMatchKeyApi;

    @BeforeEach
    public void setUp() {
        interzoidApiMock = Mockito.mock(InterzoidApi.class);
        companyNameMatchKeyApi = new CompanyNameMatchKeyApi.Builder()
                .withInterzoidApi(interzoidApiMock)
                .build();
    }

    @Test
    public void testSuccessfulRequest() throws Exception {
        String apiKey = "testApiKey";
        String company = "Apple";
        CompanyNameMatchKeyRequest.MatchAlgorithm matchAlgorithm = CompanyNameMatchKeyRequest.MatchAlgorithm.WIDE;

        String jsonResponse = "{\"SimKey\": \"12345\", \"Code\": \"Success\", \"Credits\": \"9999\"}";
        when(interzoidApiMock.doGetRequest(anyString(), anyString(), anyMap())).thenReturn(jsonResponse);

        CompanyNameMatchKeyRequest request = new CompanyNameMatchKeyRequest(apiKey, company, matchAlgorithm);

        MatchKeyResponse response = companyNameMatchKeyApi.doRequest(request);

        assertNotNull(response);
        assertEquals("12345", response.getSimKey());
    }

    @Test
    public void testFailedValidation_MissingApiKey() {
        CompanyNameMatchKeyRequest invalidRequest = new CompanyNameMatchKeyRequest(
                null,  // Missing API key
                "Apple",
                CompanyNameMatchKeyRequest.MatchAlgorithm.WIDE
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            companyNameMatchKeyApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());
    }

    @Test
    public void testFailedValidation_MissingCompany() {
        CompanyNameMatchKeyRequest invalidRequest = new CompanyNameMatchKeyRequest(
                "testApiKey",
                null,  // Missing company
                CompanyNameMatchKeyRequest.MatchAlgorithm.WIDE
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            companyNameMatchKeyApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());
    }

    @Test
    public void testFailedValidation_MissingMatchAlgorithm() {
        CompanyNameMatchKeyRequest invalidRequest = new CompanyNameMatchKeyRequest(
                "testApiKey",
                "Apple",
                null  // Missing match algorithm
        );

        ValidationException exception = assertThrows(ValidationException.class, () -> {
            companyNameMatchKeyApi.doRequest(invalidRequest);
        });

        Set<? extends ConstraintViolation<?>> violations = exception.getViolations();
        assertEquals(1, violations.size());
    }
}
