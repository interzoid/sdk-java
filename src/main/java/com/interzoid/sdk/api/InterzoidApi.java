package com.interzoid.sdk.api;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * InterzoidApi is a wrapper around OkHttp client to make requests to Interzoid APIs
 */
public class InterzoidApi {
    private static final String BASE_URL = "https://api.interzoid.com/";
    private final OkHttpClient client;

    InterzoidApi(OkHttpClient client) {
        this.client = client;
    }

    String doGetRequest(String apiKey, String resource, Map<String, String> params) throws Exception {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + resource).newBuilder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .addHeader("content-type", "application/json")
                .addHeader("x-api-key", apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            String body = responseBody != null ? responseBody.string() : null;

            if (response.isSuccessful()) {
                if (body == null || body.isEmpty()) {
                    throw new IOException("Response body is empty");
                }
                return body;
            } else {
                String errorMsg = body != null ? body : "Unknown error";
                int code = response.code();
                if (code >= 400 && code < 500) {
                    throw new ClientErrorException("Client error. StatusCode: " + code + ", Message: " + errorMsg);
                } else if (code >= 500) {
                    throw new ServerErrorException("Server error. StatusCode: " + code + ", Message: " + errorMsg);
                } else {
                    throw new UnexpectedResponseException("Unexpected response code: " + response.code() + ", message: " + errorMsg);
                }
            }
        }
    }
}
