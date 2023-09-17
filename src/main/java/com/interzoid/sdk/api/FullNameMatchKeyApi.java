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

/**
 * <h2>Get Full Name Match Similarity Key API</h2>
 *
 * <p>Copyright (C) 2023 Interzoid, Inc</p>
 *
 * <p>This API provides a hashed similarity key from the input data used to match with other similar full name data. Use the generated similarity key, rather than the actual data itself, to match and/or sort individual name data by similarity. This avoids the problems of data inconsistency, misspellings, and name variations when matching within a single dataset, and can also help matching across datasets or for more advanced searching.</p>
 *
 * <p>Heuristics, phonetics, specific knowledge bases, categorization hierarchies, contextual machine learning, and other various algorithms are used as the basis of our similarity key generation.</p>
 *
 * <p>Learn more about Interzoid Data Matching at <a href="https://docs.interzoid.com/entries/understanding-data-matching">https://docs.interzoid.com/entries/understanding-data-matching</a></p>
 *
 * <p>APIs should be reused instead of creating new instances per request. This will give you the best performance.</p>
 *
 * <h3>Example</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.FullNameMatchKeyApi;
 * import com.interzoid.sdk.model.FullNameMatchKeyRequest;
 * import com.interzoid.sdk.model.FullNameMatchKeyResponse;
 *
 * FullNameMatchKeyApi api = new FullNameMatchKeyApi.Builder().build();
 *
 * FullNameMatchKeyRequest request = new FullNameMatchKeyRequest(
 *     "YOUR-API-KEY", // apiKey
 *     "John Smith"    // full name
 * );
 *
 * FullNameMatchKeyResponse response = api.doRequest(request);
 * }</pre>
 *
 * <h3>With Custom OkHttpClient</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.FullNameMatchKeyApi;
 * import okhttp3.OkHttpClient;
 * import java.util.concurrent.TimeUnit;
 *
 * OkHttpClient okHttpClient = new OkHttpClient.Builder()
 *     .connectTimeout(10, TimeUnit.SECONDS)
 *     // other configuration
 *     .build();
 * FullNameMatchKeyApi api = new FullNameMatchKeyApi.Builder().withClient(okHttpClient).build();
 *
 * // usage of api is the same as above
 * }</pre>
 *
 * @version 1.0
 * @see <a href="https://www.interzoid.com/apis/individual-name-matching">Get Full Name Match Similarity Key API</a>
 */

public final class FullNameMatchKeyApi {
    private static final String RESOURCE = "getfullnamematch";
    private final InterzoidApi interzoidApi;
    private final Validator validator;

    /**
     * The builder class for {@link FullNameMatchKeyApi}, providing a flexible way to configure and create an instance of {@code FullNameMatchKeyApi}.
     * It follows the builder pattern, allowing for configuration settings to be specified before calling the {@link FullNameMatchKeyApi.Builder#build()} method to construct an instance of {@code FullNameMatchKeyApi}.
     *
     * @see FullNameMatchKeyApi
     */
    public static class Builder {
        private OkHttpClient client;
        private InterzoidApi api;

        /**
         * Default constructor for the {@link FullNameMatchKeyApi.Builder} class.
         */
        public Builder() {
        }

        /**
         * Specifies the {@link OkHttpClient} instance to be used by the {@code FullNameMatchKeyApi} instance being built.
         * This is optional and allows for customization of the {@code OkHttpClient} instance used internally by the {@code FullNameMatchKeyApi} instance.
         *
         * @param client the {@code OkHttpClient} instance to be used
         * @return the current builder instance, allowing for method chaining
         */
        public Builder withClient(OkHttpClient client) {
            this.client = client;
            return this;
        }

        /**
         * Specifies the {@link InterzoidApi} instance to be used by the {@code FullNameMatchKeyApi} instance being built.
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
         * Constructs a new {@code FullNameMatchKeyApi} instance with the current configuration settings specified in this builder.
         *
         * @return a new {@code FullNameMatchKeyApi} instance
         */
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

    /**
     * Constructs a new {@code FullNameMatchKeyApi} instance with the specified {@link InterzoidApi} instance.
     *
     * @param interzoidApi the {@code InterzoidApi} instance to be used
     * @see FullNameMatchKeyApi.Builder#build() for a method to construct a {@code FullNameMatchKeyApi} instance based on the configuration settings specified in the {@code Builder}
     */
    private FullNameMatchKeyApi(InterzoidApi interzoidApi) {
        this.interzoidApi = interzoidApi;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    /**
     * Sends a request to the API to obtain a similarity key based on the provided full name.
     *
     * @param request the request object containing the necessary details, including the API key, to fetch the similarity key
     * @return a representation of the matching information as an {@code MatchKeyResponse} object
     * @throws ValidationException if the request is invalid
     * @throws Exception           if the request fails
     * @see FullNameMatchKeyRequest
     * @see MatchKeyResponse
     * @see InterzoidApiException
     */
    public MatchKeyResponse doRequest(FullNameMatchKeyRequest request) throws Exception {
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
