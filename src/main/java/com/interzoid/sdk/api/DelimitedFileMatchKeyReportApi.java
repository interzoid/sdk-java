package com.interzoid.sdk.api;

import com.interzoid.sdk.model.*;
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

public final class DelimitedFileMatchKeyReportApi {
    private final InterzoidApi interzoidApi;
    private final Validator validator;

    /**
     * The builder class for {@link DelimitedFileMatchKeyReportApi}, providing a flexible way to configure and create an instance of {@code CloudDatabaseMatchKeyReportApi}.
     * It follows the builder pattern, allowing for configuration settings to be specified before calling the {@link DelimitedFileMatchKeyReportApi.Builder#build()} method to construct an instance of {@code DelimitedFileMatchKeyReportApi}.
     */
    public static class Builder {
        private OkHttpClient client;
        private InterzoidApi api;

        /**
         * Default constructor for the {@link DelimitedFileMatchKeyReportApi.Builder} class.
         */
        public Builder() {
        }

        /**
         * Specifies the {@link OkHttpClient} instance to be used by the {@code DelimitedFileMatchKeyReportApi} instance being built.
         * This is optional and allows for customization of the {@code OkHttpClient} instance used internally by the {@code DelimitedFileMatchKeyReportApi} instance.
         * @param client the {@code OkHttpClient} instance to be used
         * @return the current builder instance, allowing for method chaining
         */
        public DelimitedFileMatchKeyReportApi.Builder withClient(OkHttpClient client) {
            this.client = client;
            return this;
        }

        /**
         * Specifies the {@link InterzoidApi} instance to be used by the {@code DelimitedFileMatchKeyReportApi} instance being built.
         * This method is package protected and is intended for use in testing.
         *
         * @param interzoidApi the {@code InterzoidApi} instance to be used
         * @return the current builder instance, allowing for method chaining
         */
        DelimitedFileMatchKeyReportApi.Builder withInterzoidApi(InterzoidApi interzoidApi) {
            this.api = interzoidApi;
            return this;
        }

        /**
         * Constructs a new {@code DelimitedFileMatchKeyReportApi} instance with the current configuration settings specified in this builder.
         *
         * @return a new {@code DelimitedFileMatchKeyReportApi} instance
         */
        public DelimitedFileMatchKeyReportApi build() {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .build();
            }
            if (api == null) {
                api = new InterzoidApi(client);
            }
            return new DelimitedFileMatchKeyReportApi(api);
        }
    }

    /**
     * Constructs a new {@code DelimitedFileMatchKeyReportApi} instance with the given {@link InterzoidApi} instance.
     *
     * @param interzoidApi the {@code InterzoidApi} instance to be used
     */
    private DelimitedFileMatchKeyReportApi(InterzoidApi interzoidApi) {
        this.interzoidApi = interzoidApi;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    public CloudConnectResponse doRequest(DelimitedFileMatchRequest request) throws InterzoidApiException {
        Set<ConstraintViolation<DelimitedFileMatchRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new InterzoidApiException(violations.toString());
        }

        try {
            String response = interzoidApi.doCloudConnectRequest(request.toParamMap());
            if (request.getResponseType().equals(ResponseType.JSON)) {
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
