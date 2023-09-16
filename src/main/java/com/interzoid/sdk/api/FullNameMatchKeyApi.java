package com.interzoid.sdk.api;

import com.interzoid.sdk.model.FullNameMatchKeyRequest;
import com.interzoid.sdk.model.MatchKeyResponse;
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

public class FullNameMatchKeyApi {
    private static final String RESOURCE = "getfullnamematch";
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

        public FullNameMatchKeyApi build() {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .build();
            }
            if (api == null) {
                api = new InterzoidApi(client);
            }
            return new FullNameMatchKeyApi(api);
        }
    }

    private FullNameMatchKeyApi(InterzoidApi interzoidApi) {
        this.interzoidApi = interzoidApi;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    public MatchKeyResponse doRequest(FullNameMatchKeyRequest request) {
        Set<ConstraintViolation<FullNameMatchKeyRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ValidationException("Validation failed", violations);
        }

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<MatchKeyResponse> jsonAdapter = moshi.adapter(MatchKeyResponse.class);

        Map<String, String> params = new HashMap<>();
        params.put("fullname", request.getFullName());
        try {
            String response = interzoidApi.doGetRequest(request.getApikey(), RESOURCE, params);
            return jsonAdapter.fromJson(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
