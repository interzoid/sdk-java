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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Interzoid, Inc
 * @version 1.1
 * @see <a href="https://www.interzoid.com/apis/company-name-matching">Get Company Match Similarity Key API</a>
 *
 * <h2>Get Company Match Similarity Key API</h2>
 *
 * <p>Company/Organization name data can be inconsistent, making redundant data difficult to track down or match (GE, Gen Electric, G.E., etc.). This API provides a hashed similarity key from the input data used to match with other similar company name data. Use the generated similarity key, rather than the actual data itself, to match and/or sort company name data to identify inconsistently represented company and organization name data. This avoids the problems of data inconsistency, misspellings, and name variations when matching within a single dataset or across multiple data sources.</p>
 *
 * <p>Heuristics, phonetics, specific knowledge bases, categorization hierarchies, contextual machine learning, and other various algorithms are used as the basis of our similarity key generation.</p>
 *
 * <p>You can choose from multiple matching algorithms, "wide", "medium", and "narrow". "Wide" will find a greater number of matches, but may also find similarly-spelled false positives. "Narrow" will result in tighter matching requirements, but then may miss a few matches. Your business case will dictate which algorithm is ideal.</p>
 *
 * <p>APIs should be reused instead of creating new instances per request. This will give you the best performance.</p>
 *
 * <h3>Example</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.CompanyNameMatchKeyApi;
 * import com.interzoid.sdk.model.CompanyNameMatchKeyRequest;
 * import com.interzoid.sdk.model.MatchKeyResponse;
 *
 * public class CompanyNameMatchKeyTest {
 *   public static void main(String[] args) throws Exception {
 *     CompanyNameMatchKeyApi api = new CompanyNameMatchKeyApi.Builder().build();
 *     CompanyNameMatchKeyRequest request = new CompanyNameMatchKeyRequest(
 *       "YOUR-API-KEY", // apiKey
 *       "Apple"         // company
 *     );
 *
 *     MatchKeyResponse response = api.doRequest(request);
 *   }
 * }
 * }</pre>
 *
 * <h3>With Custom OkHttpClient</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.CompanyNameMatchKeyApi;
 * import okhttp3.OkHttpClient;
 * import java.util.concurrent.TimeUnit;
 *
 * public class CompanyNameMatchKeyTest {
 *   public static void main(String[] args) throws Exception {
 *     OkHttpClient okHttpClient = new OkHttpClient.Builder()
 *       .connectTimeout(10, TimeUnit.SECONDS)
 *       // other configuration
 *       .build();
 *     CompanyNameMatchKeyApi api = new CompanyNameMatchKeyApi.Builder()
 *       .withClient(okHttpClient)
 *       .build();
 *     // usage of api is the same as above
 *   }
 * }
 * }</pre>
 */

public final class CompanyNameMatchKeyApi {
    private static final String RESOURCE = "getcompanymatchadvanced";
    private final InterzoidApi interzoidApi;
    private final Validator validator;

    /**
     * The builder class for {@link CompanyNameMatchKeyApi}, providing a flexible way to configure and create an instance of {@code CompanyNameMatchKeyApi}.
     * It follows the builder pattern, allowing for configuration settings to be specified before calling the {@link CompanyNameMatchKeyApi.Builder#build()} method to construct an instance of {@code CompanyNameMatchKeyApi}.
     *
     * @see CompanyNameMatchKeyApi
     */
    public static class Builder {
        private OkHttpClient client;
        private InterzoidApi api;

        /**
         * Default constructor for the {@link CompanyNameMatchKeyApi.Builder} class.
         */
        public Builder() {
        }

        /**
         * Specifies the {@link OkHttpClient} instance to be used by the {@code CompanyNameMatchKeyApi} instance being built.
         * This is optional and allows for customization of the {@code OkHttpClient} instance used internally by the {@code CompanyNameMatchKeyApi} instance.
         *
         * @param client the {@code OkHttpClient} instance to be used
         * @return the current builder instance, allowing for method chaining
         */
        public Builder withClient(OkHttpClient client) {
            this.client = client;
            return this;
        }

        /**
         * Specifies the {@link InterzoidApi} instance to be used by the {@code CompanyNameMatchKeyApi} instance being built.
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
         * Constructs a new {@code CompanyNameMatchKeyApi} instance with the current configuration settings specified in this builder.
         *
         * @return a new {@code CompanyNameMatchKeyApi} instance
         */
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

    /**
     * Constructs a new {@code CompanyNameMatchKeyApi} instance with the specified {@link InterzoidApi} instance.
     *
     * @param interzoidApi the {@code InterzoidApi} instance to be used
     * @see CompanyNameMatchKeyApi.Builder#build() for a method to construct a {@code CompanyNameMatchKeyApi} instance based on the configuration settings specified in the {@code Builder}
     */
    private CompanyNameMatchKeyApi(InterzoidApi interzoidApi) {
        this.interzoidApi = interzoidApi;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    /**
     * Sends a request to the API to obtain a similarity key based on the provided company name.
     *
     * @param request the request object containing the necessary details, including the API key, to fetch the similarity key
     * @return a representation of the matching information as an {@code MatchKeyResponse} object
     * @throws ValidationException if the request is invalid
     * @throws IOException         if the API request fails for any reason
     * @see CompanyNameMatchKeyRequest
     * @see MatchKeyResponse
     * @see InterzoidApiException
     */
    public MatchKeyResponse doRequest(CompanyNameMatchKeyRequest request) throws IOException {
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
        String response = interzoidApi.doApiGetRequest(request.getApikey(), RESOURCE, params);
        return jsonAdapter.fromJson(response);
    }
}
