package com.interzoid.sdk.api;

import com.interzoid.sdk.api.exceptions.InterzoidApiException;
import com.interzoid.sdk.api.exceptions.ValidationException;
import com.interzoid.sdk.model.MatchScoreResponse;
import com.interzoid.sdk.model.OrganizationMatchScoreRequest;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * OrganizationMatchScoreApi provides a client for interacting with the Interzoid Organization Match Score API.
 *
 * @see <a href="https://www.interzoid.com/apis/organization-match-score">Organization Match Score API</a>
 * <h2>Organization Match Score API</h2>
 *
 * <p>Copyright (C) 2023 Interzoid, Inc</p>
 *
 * <p>This API provides a match score (likelihood of matching) from 0-100 between two organization names.</p>
 *
 * <p>Heuristics, phonetics, specific knowledge bases, categorization hierarchies, contextual machine learning, and other various algorithms are used as the basis of scoring the match. These algorithms get smarter over time. As the context of the data is known, in this case individual person names, it performs far better than generic string comparison algorithms.</p>
 *
 * <p>Learn more about Interzoid Data Matching at <a href="https://docs.interzoid.com/entries/understanding-data-matching">https://docs.interzoid.com/entries/understanding-data-matching</a></p>
 *
 * <p>APIs should be reused instead of creating new instances per request. This will give you the best performance.</p>
 *
 * <h3>Example</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.OrganizationMatchScoreApi;
 * import com.interzoid.sdk.model.OrganizationMatchScoreRequest;
 * import com.interzoid.sdk.model.MatchScoreResponse;
 *
 * public class OrganizationMatchScoreTest {
 *   public static void main(String[] args) throws Exception {
 *     OrganizationMatchScoreApi api = new OrganizationMatchScoreApi.Builder().build();
 *
 *     OrganizationMatchScoreRequest request = new OrganizationMatchScoreRequest(
 *       "YOUR-API-KEY",  // apiKey
 *       "Apple",         // org 1
 *       "Apple, Inc");   // org 2
 *
 *     MatchScoreResponse response = api.doRequest(request);
 *   }
 * }
 * }</pre>
 *
 * <h3>With Custom OkHttpClient</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.OrganizationMatchScoreApi;
 * import okhttp3.OkHttpClient;
 * import java.util.concurrent.TimeUnit;
 *
 * public class OrganizationMatchScoreTest {
 *   public static void main(String[] args) throws Exception {
 *     OkHttpClient okHttpClient = new OkHttpClient.Builder()
 *      .connectTimeout(10, TimeUnit.SECONDS)
 *      // other configuration
 *      .build();
 *     OrganizationMatchScoreApi api = new OrganizationMatchScoreApi.Builder()
 *       .withClient(okHttpClient)
 *       .build();
 *     // usage of api is the same as above
 *   }
 * }
 * }</pre>
 * @see OrganizationMatchScoreRequest
 */

public final class OrganizationMatchScoreApi {
    private static final String RESOURCE = "getorgmatchscore";
    private final InterzoidApi interzoidApi;
    private final Validator validator;

    /**
     * The builder class for {@link OrganizationMatchScoreApi}, providing a flexible way to configure and create an instance of {@code OrganizationMatchScoreApi}.
     * It follows the builder pattern, allowing for configuration settings to be specified before calling the {@link OrganizationMatchScoreApi.Builder#build()} method to construct an instance of {@code OrganizationMatchScoreApi}.
     *
     * @see OrganizationMatchScoreApi
     */
    public static class Builder {
        private OkHttpClient client;
        private InterzoidApi api;

        /**
         * Default constructor for the {@link OrganizationMatchScoreApi.Builder} class.
         */
        public Builder() {
        }

        /**
         * Specifies the {@link OkHttpClient} instance to be used by the {@code OrganizationMatchScoreApi} instance being built.
         * This is optional and allows for customization of the {@code OkHttpClient} instance used internally by the {@code OrganizationMatchScoreApi} instance.
         *
         * @param client the {@code OkHttpClient} instance to be used
         * @return the current builder instance, allowing for method chaining
         */
        public Builder withClient(OkHttpClient client) {
            this.client = client;
            return this;
        }

        /**
         * Specifies the {@link InterzoidApi} instance to be used by the {@code OrganizationMatchScoreApi} instance being built.
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
         * Constructs a new {@code OrganizationMatchScoreApi} instance with the current configuration settings specified in this builder.
         *
         * @return a new {@code OrganizationMatchScoreApi} instance
         */
        public OrganizationMatchScoreApi build() {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .build();
            }
            if (api == null) {
                api = new InterzoidApi(client);
            }
            return new OrganizationMatchScoreApi(api);
        }
    }

    /**
     * Constructs a new {@code OrganizationMatchScoreApi} instance with the specified {@link InterzoidApi} instance.
     *
     * @param interzoidApi the {@code InterzoidApi} instance to be used
     * @see OrganizationMatchScoreApi.Builder#build() for a method to construct a {@code OrganizationMatchScoreApi} instance based on the configuration settings specified in the {@code Builder}
     */
    private OrganizationMatchScoreApi(InterzoidApi interzoidApi) {
        this.interzoidApi = interzoidApi;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    /**
     * Sends a request to the API to obtain a match score based on the provided data.
     *
     * @param request the request object containing the necessary details, including the API key, to fetch the match score
     * @return a representation of the matching information as an {@code MatchScoreResponse} object
     * @throws ValidationException if the request is invalid
     * @throws IOException         if the request fails
     * @see OrganizationMatchScoreRequest
     * @see MatchScoreResponse
     * @see InterzoidApiException
     */
    public MatchScoreResponse doRequest(OrganizationMatchScoreRequest request) throws IOException {
        // Validate request
        Set<ConstraintViolation<OrganizationMatchScoreRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ValidationException("Validation failed", violations);
        }

        // Set up JSON adapter
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<MatchScoreResponse> jsonAdapter = moshi.adapter(MatchScoreResponse.class);

        // Build request parameters
        Map<String, String> params = new HashMap<>();
        params.put("org1", request.getValue1());
        params.put("org2", request.getValue2());

        // Make request
        try {
            String response = interzoidApi.doApiGetRequest(request.getApikey(), RESOURCE, params);
            return jsonAdapter.fromJson(response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
