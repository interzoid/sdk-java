package com.interzoid.sdk.api;

import com.interzoid.sdk.model.*;
import com.interzoid.sdk.model.Process;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;

public class CloudDatabaseMatchKeyReportApiTest {
    private InterzoidApi interzoidApiMock;
    private CloudDatabaseMatchKeyReportApi cloudDatabaseMatchKeyReportApi;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        interzoidApiMock = Mockito.mock(InterzoidApi.class);
        cloudDatabaseMatchKeyReportApi = new CloudDatabaseMatchKeyReportApi.Builder()
                .withInterzoidApi(interzoidApiMock)
                .build();
    }

    @Test
    public void testSuccessfulRequest() throws Exception {
        String apiKey = "testApiKey";

        String textResponse = "success";
        when(interzoidApiMock.doCloudConnectRequest(anyMap())).thenReturn(textResponse);

        CloudWorkloadRequest request = new CloudWorkloadRequest(
                apiKey,
                Process.GEN_SQL,
                Source.MARIADB,
                Category.COMPANY,
                "user:password@tcp(ec2-0-0-0-0.compute-1.amazonaws.com)/interzoid",
                "companies",
                "companyname",
                "id",
                false,
                false
        );
        CloudDatabaseStringResponse response = (CloudDatabaseStringResponse) cloudDatabaseMatchKeyReportApi.doRequest(request);
        assertEquals("success", response.getMessage());
    }

    @Test
    public void testMissingNewTableOnCreateTableProcess() {
        String apiKey = "testApiKey";
        CloudWorkloadRequest request = new CloudWorkloadRequest(
                apiKey,
                Process.CREATE_TABLE,
                Source.MARIADB,
                Category.COMPANY,
                "user:password@tcp(ec2-0-0-0-0.compute-1.amazonaws.com)/interzoid",
                "companies",
                "companyname",
                "id",
                null  // newTable
        );
        Set<ConstraintViolation<CloudWorkloadRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("newTable is required when process is CREATE_TABLE", violations.iterator().next().getMessage());
    }

}
