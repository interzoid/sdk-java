package com.interzoid.sdk.api;

import com.interzoid.sdk.model.CloudConnectResponse;
import com.interzoid.sdk.model.CloudDatabaseJsonResponse;
import com.interzoid.sdk.model.CloudDatabaseResponseMessage;
import com.interzoid.sdk.model.CloudWorkloadRequest;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import okhttp3.OkHttpClient;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class CloudDatabaseMatchKeyReportApi {
    private final InterzoidApi interzoidApi;
    private final Validator validator;

    /**
     * The builder class for {@link CloudDatabaseMatchKeyReportApi}, providing a flexible way to configure and create an instance of {@code CloudDatabaseMatchKeyReportApi}.
     * It follows the builder pattern, allowing for configuration settings to be specified before calling the {@link CloudDatabaseMatchKeyReportApi.Builder#build()} method to construct an instance of {@code CloudDatabaseMatchKeyReportApi}.
     */
    public static class Builder {
        private OkHttpClient client;
        private InterzoidApi api;

        /**
         * Default constructor for the {@link CloudDatabaseMatchKeyReportApi.Builder} class.
         */
        public Builder() {
        }

        /**
         * Specifies the {@link OkHttpClient} instance to be used by the {@code CloudDatabaseMatchKeyReportApi} instance being built.
         * This is optional and allows for customization of the {@code OkHttpClient} instance used internally by the {@code CloudDatabaseMatchKeyReportApi} instance.
         * @param client the {@code OkHttpClient} instance to be used
         * @return the current builder instance, allowing for method chaining
         */
        public Builder withClient(OkHttpClient client) {
            this.client = client;
            return this;
        }

        /**
         * Specifies the {@link InterzoidApi} instance to be used by the {@code CloudDatabaseMatchKeyReport} instance being built.
         * This method is package protected and is intended for use in testing.
         *
         * @param interzoidApi the {@code InterzoidApi} instance to be used
         * @return the current builder instance, allowing for method chaining
         */
        Builder withInterzoidApi(InterzoidApi interzoidApi) {
            this.api = interzoidApi;
            return this;
        }

        /**
         * Constructs a new {@code CloudDatabaseMatchKeyReportApi} instance with the current configuration settings specified in this builder.
         *
         * @return a new {@code CloudDatabaseMatchKeyReportApi} instance
         */
        public CloudDatabaseMatchKeyReportApi build() {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .build();
            }
            if (api == null) {
                api = new InterzoidApi(client);
            }
            return new CloudDatabaseMatchKeyReportApi(api);
        }
    }

    /**
     * Constructs a new {@code CloudDatabaseMatchKeyReportApi} instance with the given {@link InterzoidApi} instance.
     *
     * @param interzoidApi the {@code InterzoidApi} instance to be used
     */
    private CloudDatabaseMatchKeyReportApi(InterzoidApi interzoidApi) {
        this.interzoidApi = interzoidApi;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    /**
     * Processes a Cloud Database Workload request
     *
     * @param {@code CloudDatabaseMatchKeyRequest} the request to be processed
     * @return a {@code CloudConnectResponse} containing the results of the request
     * @throws ValidationException if the request is invalid
     * @throws Exception           if the request fails
     * @see CloudWorkloadRequest
     * @see CloudConnectResponse
     * @see InterzoidApiException
     */
    public CloudConnectResponse doRequest(CloudWorkloadRequest request) throws Exception {
        // Validate request object
        Set<ConstraintViolation<CloudWorkloadRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ValidationException("Validation failed", violations);
        }


        boolean jsonResponse = request.isJson();

        Map<String, String> params = request.toParamMap();
        System.out.println(params);
        // Make request
        try {
            String response = interzoidApi.doCloudConnectRequest(params);
            if (jsonResponse) {
                Moshi moshi = new Moshi.Builder().build();
                JsonAdapter<CloudDatabaseJsonResponse> jsonAdapter = moshi.adapter(CloudDatabaseJsonResponse.class);
                return jsonAdapter.fromJson(response);
            } else {
                return new CloudDatabaseResponseMessage(response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
