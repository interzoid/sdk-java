package com.interzoid.sdk.api;

import com.interzoid.sdk.api.exceptions.InterzoidApiException;
import com.interzoid.sdk.api.exceptions.ValidationException;
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

/**
 * <h2>Full Name Match Score Compare API</h2>
 *
 * <p>This API provides a match score (likelihood of matching) between two individual names on a scale of 0-100, where 100 is the highest possible match.</p>
 *
 * <p>Heuristics, phonetics, specific knowledge bases, categorization hierarchies, contextual machine learning, and other various algorithms are used as the basis of scoring the match. These algorithms get smarter over time. As the context of the data is known, in this case individual person names, it performs far better than generic string comparison algorithms.</p>
 *
 * <p>Learn more about Interzoid Data Matching at <a href="https://docs.interzoid.com/entries/understanding-data-matching">https://docs.interzoid.com/entries/understanding-data-matching</a></p>
 *
 * <p>APIs should be reused instead of creating new instances per request. This will give you the best performance.</p>
 *
 * <h3>Example</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.FullNameMatchScoreApi;
 * import com.interzoid.sdk.model.FullNameMatchScoreRequest;
 * import com.interzoid.sdk.model.MatchScoreResponse;
 *
 * public class FullNameMatchScoreTest {
 *   public static void main(String[] args) throws Exception {
 *     FullNameMatchScoreApi api = new FullNameMatchScoreApi.Builder().build();
 *     FullNameMatchScoreRequest request = new FullNameMatchScoreRequest(
 *       "YOUR-API-KEY", // apiKey
 *       "John Smith",   // full name 1
 *       "John Smyth"    // full name 2
 *     );
 *     MatchScoreResponse response = api.doRequest(request);
 *   }
 * }
 * }</pre>
 *
 * <h3>With Custom OkHttpClient</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.FullNameMatchScoreApi;
 * import okhttp3.OkHttpClient;
 * import java.util.concurrent.TimeUnit;
 *
 * public class FullNameMatchScoreTest {
 *   public static void main(String[] args) throws Exception {
 *     OkHttpClient okHttpClient = new OkHttpClient.Builder()
 *       .connectTimeout(10, TimeUnit.SECONDS)
 *       // other configuration
 *       .build();
 *     FullNameMatchScoreApi api = new FullNameMatchScoreApi.Builder()
 *       .withClient(okHttpClient)
 *       .build();
 *     // usage of api is the same as above
 *   }
 * }
 * }</pre>
 * @see <a href="https://www.interzoid.com/apis/individual-match-score">Full Name Match Score Compare API</a>
 * @see FullNameMatchScoreRequest
 * @see MatchScoreResponse
 * @version 1.0
 */

public final class FullNameMatchScoreApi {
    private static final String RESOURCE = "getfullnamematchscore";
    private final InterzoidApi interzoidApi;
    private final Validator validator;

    /**
     * The builder class for {@link FullNameMatchScoreApi}, providing a flexible way to configure and create an instance of {@code FullNameMatchScoreApi}.
     * It follows the builder pattern, allowing for configuration settings to be specified before calling the {@link FullNameMatchScoreApi.Builder#build()} method to construct an instance of {@code FullNameMatchScoreApi}.
     *
     * @see FullNameMatchScoreApi
     */
    public static class Builder {
        private OkHttpClient client;
        private InterzoidApi api;

        /**
         * Default constructor for the {@link FullNameMatchScoreApi.Builder} class.
         */
        public Builder() {
        }

        /**
         * Specifies the {@link OkHttpClient} instance to be used by the {@code FullNameMatchScoreApi} instance being built.
         * This is optional and allows for customization of the {@code OkHttpClient} instance used internally by the {@code FullNameMatchScoreApi} instance.
         *
         * @param client the {@code OkHttpClient} instance to be used
         * @return the current builder instance, allowing for method chaining
         */
        public Builder withClient(OkHttpClient client) {
            this.client = client;
            return this;
        }

        /**
         * Specifies the {@link InterzoidApi} instance to be used by the {@code FullNameMatchScoreApi} instance being built.
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
         * Constructs a new {@code FullNameMatchScoreApi} instance with the current configuration settings specified in this builder.
         *
         * @return a new {@code FullNameMatchScoreApi} instance
         */
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

    /**
     * Constructs a new {@code FullNameMatchScoreApi} instance with the specified {@link InterzoidApi} instance.
     *
     * @param interzoidApi the {@code InterzoidApi} instance to be used
     * @see FullNameMatchScoreApi.Builder#build() for a method to construct a {@code FullNameMatchScoreApi} instance based on the configuration settings specified in the {@code Builder}
     */
    private FullNameMatchScoreApi(InterzoidApi interzoidApi) {
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
     * @throws Exception           if the request fails
     * @see FullNameMatchScoreRequest
     * @see MatchScoreResponse
     * @see InterzoidApiException
     */
    public MatchScoreResponse doRequest(FullNameMatchScoreRequest request) throws Exception {
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
        String response = interzoidApi.doApiGetRequest(request.getApikey(), RESOURCE, params);
        return jsonAdapter.fromJson(response);
    }
}
