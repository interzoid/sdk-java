package com.interzoid.sdk.api;

import com.interzoid.sdk.model.FullNameMatchScoreRequest;
import com.interzoid.sdk.model.MatchScoreResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import okhttp3.OkHttpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FullNameMatchScoreApi {
    private static final String RESOURCE = "getfullnamematchscore";
    private final InterzoidApi interzoidApi;
    private final Validator validator;

    public static class Builder {
        private OkHttpClient client;
        private InterzoidApi api;

        public Builder withClient(OkHttpClient client) {
            this.client = client;
            return this;
        }

        Builder withInterzoidApi(InterzoidApi interzoidApi) {
            this.api = interzoidApi;
            return this;
        }

        public FullNameMatchScoreApi build() {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .build();
            }
            if (api == null) {
                api = new InterzoidApi(client);
            }
            return new FullNameMatchScoreApi(api);
        }
    }

    private FullNameMatchScoreApi(InterzoidApi interzoidApi) {
        this.interzoidApi = interzoidApi;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    public MatchScoreResponse doRequest(FullNameMatchScoreRequest request) {
        // Validate request
        Set<ConstraintViolation<FullNameMatchScoreRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ValidationException("Validation failed", violations);
        }

        // Setup JSON adapter
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<MatchScoreResponse> jsonAdapter = moshi.adapter(MatchScoreResponse.class);

        // Setup request parameters
        Map<String, String> params = new HashMap<>();
        params.put("fullname1", request.getValue1());
        params.put("fullname2", request.getValue2());

        // Make request
        try {
            String response = interzoidApi.doGetRequest(request.getApikey(), RESOURCE, params);
            return jsonAdapter.fromJson(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
