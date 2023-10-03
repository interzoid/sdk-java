package com.interzoid.sdk.api;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

/**
 * Copyright (C) 2023 Interzoid, Inc
 * <p>
 * InterzoidApi is a wrapper around OkHttp client to make requests to Interzoid APIs.
 * This class is not intended to be used directly. Instead, use the more specific APIs in the com.interzoid.sdk.api package.
 *
 * @author Interzoid, Inc
 * @version 1.0
 */
public final class InterzoidApi {
    private static final String BASE_URL = "https://api.interzoid.com/";
    private static final String CONNECT_BASE_URL = "https://connect.interzoid.com/";
    private final OkHttpClient client;

    InterzoidApi(OkHttpClient client) {
        this.client = client;
    }

    /**
     * Makes a GET request to the specified resource with the given parameters.
     *
     * @param apiKey   the API key to be used for authentication
     * @param resource the resource to be requested
     * @param params   the parameters to be sent with the request
     * @return String (JSON)
     * @throws InterzoidApiException if an error occurs while making the request
     */
    String doGetRequest(String apiKey, String resource, Map<String, String> params) throws InterzoidApiException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + resource).newBuilder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .addHeader("x-api-key", apiKey)
                .build();

        return get(request);
    }

    /**
     * Makes a GET request to the specified resource with the given parameters.
     *
     * @param params   the parameters to be sent with the request
     * @return String (JSON or Plain Text)
     * @throws InterzoidApiException if an error occurs while making the request
     */
    String doCloudConnectRequest(Map<String, String> params) throws InterzoidApiException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(CONNECT_BASE_URL + "run").newBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .build();

        return get(request);
    }

    private String get(Request request) throws UnexpectedResponseException {
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
        } catch (IOException e) {
            throw new UnexpectedResponseException("Unexpected response", e);
        }
    }
}
