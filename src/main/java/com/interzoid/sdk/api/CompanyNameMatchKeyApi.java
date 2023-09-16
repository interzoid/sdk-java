package com.interzoid.sdk.api;

import com.interzoid.sdk.model.CompanyNameMatchKeyRequest;
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

public class CompanyNameMatchKeyApi {
    private static final String RESOURCE = "getcompanymatchadvanced";
    private final InterzoidApi interzoidApi;
    private final Validator validator;

    public static class Builder {
        private OkHttpClient client;
        private InterzoidApi api;

        public Builder withClient(OkHttpClient client) {
            this.client = client;
            return this;
        }

        // For internal testing
        Builder withInterzoidApi(InterzoidApi interzoidApi) {
            this.api = interzoidApi;
            return this;
        }

        public CompanyNameMatchKeyApi build() {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .build();
            }
            if (api == null) {
                api = new InterzoidApi(client);
            }
            return new CompanyNameMatchKeyApi(api);
        }
    }

    private CompanyNameMatchKeyApi(InterzoidApi interzoidApi) {
        this.interzoidApi = interzoidApi;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    public MatchKeyResponse doRequest(CompanyNameMatchKeyRequest request) {
        // Validate request object
        Set<ConstraintViolation<CompanyNameMatchKeyRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ValidationException("Validation failed", violations);
        }

        // Setup JSON adapter
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<MatchKeyResponse> jsonAdapter = moshi.adapter(MatchKeyResponse.class);

        // Set parameters
        Map<String, String> params = new HashMap<>();
        params.put("company", request.getCompanyName());
        params.put("algorithm", request.getMatchAlgorithm().getValue());

        // Make request
        try {
            String response = interzoidApi.doGetRequest(request.getApikey(), RESOURCE, params);
            return jsonAdapter.fromJson(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
