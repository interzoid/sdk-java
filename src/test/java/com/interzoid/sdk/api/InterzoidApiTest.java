package com.interzoid.sdk.api;

import okhttp3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class InterzoidApiTest {
    private InterzoidApi interzoidApi;
    private OkHttpClient client;

    @BeforeEach
    public void setUp() {
        client = Mockito.mock(OkHttpClient.class);
        interzoidApi = new InterzoidApi(client);
    }

    @Test
    public void sendGet_returnsResponseBody() throws Exception {
        Response response = Mockito.mock(Response.class);
        ResponseBody responseBody = Mockito.mock(ResponseBody.class);
        Call call = Mockito.mock(Call.class);
        Headers headers = Mockito.mock(Headers.class);

        when(client.newCall(any(Request.class))).thenReturn(call);
        when(call.execute()).thenReturn(response);
        when(response.body()).thenReturn(responseBody);
        when(response.headers()).thenReturn(headers);
        when(response.code()).thenReturn(200);
        when(response.isSuccessful()).thenReturn(true);
        when(headers.size()).thenReturn(0);
        when(responseBody.string()).thenReturn("OK");

        String resp = interzoidApi.doGetRequest("apikey", "resource", null);

        assertEquals("OK", resp);
    }
}
