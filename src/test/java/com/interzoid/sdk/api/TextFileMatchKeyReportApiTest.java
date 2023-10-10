package com.interzoid.sdk.api;

import com.interzoid.sdk.model.*;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

public class TextFileMatchKeyReportApiTest {
    private InterzoidApi interzoidApiMock;
    private TextFileMatchKeyReportApi textFileMatchKeyReportApi;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        interzoidApiMock = Mockito.mock(InterzoidApi.class);
        textFileMatchKeyReportApi = new TextFileMatchKeyReportApi.Builder()
                .withInterzoidApi(interzoidApiMock)
                .build();
    }

    @Test
    public void testSuccessfulRequest() throws Exception {
        String apiKey = "testApiKey";

        String textResponse = "success";
        when(interzoidApiMock.doCloudConnectRequest(anyMap())).thenReturn(textResponse);

        TextFileMatchRequest request = new TextFileMatchRequest(
                apiKey,
                Source.CSV,
                Category.COMPANY,
                "https://dl.interzoid.com/csv/companies.csv",
                1,
                ResponseType.HTML
        );
        CloudDatabaseStringResponse response = (CloudDatabaseStringResponse) textFileMatchKeyReportApi.doRequest(request);
        assertEquals("success", response.getMessage());
    }
}
