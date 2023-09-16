package com.interzoid.sdk.api;

import com.interzoid.sdk.model.InterzoidRequest;
import com.interzoid.sdk.model.InterzoidResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class AccountInfoApiTest {
    private InterzoidApi interzoidApiMock;
    private AccountInfoApi accountInfoApi;

    @BeforeEach
    public void setUp() {
        interzoidApiMock = Mockito.mock(InterzoidApi.class);
        accountInfoApi = new AccountInfoApi.Builder()
                .withInterzoidApi(interzoidApiMock)
                .build();
    }

    @Test
    public void testSuccessfulRequest() throws Exception {
        String apiKey = "testApiKey";

        String jsonResponse = "{\"Code\": \"Success\", \"Credits\": \"9999\"}";
        when(interzoidApiMock.doGetRequest(anyString(), anyString(), eq(null))).thenReturn(jsonResponse);

        InterzoidRequest request = new InterzoidRequest(apiKey);

        InterzoidResponse response = accountInfoApi.doRequest(request);

        assertNotNull(response);
        assertEquals("9999", response.getCredits());
    }
}
