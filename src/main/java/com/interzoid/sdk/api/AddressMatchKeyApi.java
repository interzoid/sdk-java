package com.interzoid.sdk.api;

import com.interzoid.sdk.api.exceptions.InterzoidApiException;
import com.interzoid.sdk.api.exceptions.ValidationException;
import com.interzoid.sdk.model.AddressMatchKeyRequest;
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
 * <h2>Get Address Match Similarity Key API</h2>
 *
 * <p>This API provides a hashed similarity key from the input data used to match with other similar address data. Use the generated similarity key, rather than the actual data itself, to match and/or sort address data by similarity. This avoids the problems of data inconsistency, misspellings, and address element variations when matching either within a single dataset or across datasets. It also provides for broader searching capabilities.</p>
 *
 * <p>Heuristics, phonetics, specific knowledge bases, categorization hierarchies, contextual machine learning, and other various algorithms are used as the basis of our similarity key generation.</p>
 *
 * <p>You can choose from two matching algorithms, "wide" and "narrow". The algorithm "narrow" considers a unit number (suite, apartment, unit, etc.) when generating similarity keys. This ensures individual units are identified separately when comparing generated keys. The "wide" parameter will not consider the unit numbers, generating matching similarity keys based on the primary address only. Your business case will dictate which algorithm is ideal.</p>
 *
 * <p>APIs should be reused instead of creating new instances per request. This will give you the best performance.</p>
 *
 * <h3>Example</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.AddressMatchKeyApi;
 * import com.interzoid.sdk.model.AddressMatchKeyRequest;
 * import com.interzoid.sdk.model.MatchKeyResponse;
 *
 * public class AddressMatchKeyTest {
 *   public static void main(String[] args) throws Exception {
 *     AddressMatchKeyApi api = new AddressMatchKeyApi.Builder().build();
 *
 *     AddressMatchKeyRequest request = new AddressMatchKeyRequest(
 *       "YOUR-API-KEY",       // apiKey
 *       "123 Main Street"     // address
 *     );
 *     MatchKeyResponse response = api.doRequest(request);
 *   }
 * }
 * }</pre>
 *
 * <h3>With Custom OkHttpClient</h3>
 *
 * <pre>{@code
 * import com.interzoid.sdk.api.AddressMatchKeyApi;
 * import okhttp3.OkHttpClient;
 * import java.util.concurrent.TimeUnit;
 *
 * public class AddressMatchKeyTest {
 *   public static void main(String[] args) throws Exception {
 *     OkHttpClient okHttpClient = new OkHttpClient.Builder()
 *       .connectTimeout(10, TimeUnit.SECONDS)
 *       // other configuration
 *       .build();
 *     AddressMatchKeyApi api = new AddressMatchKeyApi.Builder()
 *       .withClient(okHttpClient)
 *       .build();
 *     // usage of api is the same as above
 *   }
 * }
 * }</pre>
 * @see <a href="https://www.interzoid.com/apis/street-address-matching">Get Address Match Similarity Key API</a>
 * @see AddressMatchKeyRequest
 * @see MatchKeyResponse
 * @version 1.0
 */
public final class AddressMatchKeyApi {
    private static final String RESOURCE = "getaddressmatchadvanced";
    private final InterzoidApi interzoidApi;
    private final Validator validator;

    /**
     * The builder class for {@link AddressMatchKeyApi}, providing a flexible way to configure and create an instance of {@code AddressMatchKeyApi}.
     * It follows the builder pattern, allowing for configuration settings to be specified before calling the {@link AddressMatchKeyApi.Builder#build()} method to construct an instance of {@code AddressMatchKeyApi}.
     *
     * @see AddressMatchKeyApi
     */
    public static class Builder {
        private OkHttpClient client;
        private InterzoidApi api;

        /**
         * Default constructor for the {@link AddressMatchKeyApi.Builder} class.
         */
        public Builder() {
        }

        /**
         * Specifies the {@link OkHttpClient} instance to be used by the {@code AddressMatchKeyApi} instance being built.
         * This is optional and allows for customization of the {@code OkHttpClient} instance used internally by the {@code AddressMatchKeyApi} instance.
         *
         * @param client the {@code OkHttpClient} instance to be used
         * @return the current builder instance, allowing for method chaining
         */
        public Builder withClient(OkHttpClient client) {
            this.client = client;
            return this;
        }

        /**
         * Specifies the {@link InterzoidApi} instance to be used by the {@code AddressMatchKeyApi} instance being built.
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
         * Constructs a new {@code AddressMatchKeyApi} instance with the current configuration settings specified in this builder.
         *
         * @return a new {@code AddressMatchKeyApi} instance
         */
        public AddressMatchKeyApi build() {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .build();
            }
            if (api == null) {
                api = new InterzoidApi(client);
            }
            return new AddressMatchKeyApi(api);
        }
    }

    /**
     * Constructs a new {@code AddressMatchKeyApi} instance with the specified {@link InterzoidApi} instance.
     *
     * @param interzoidApi the {@code InterzoidApi} instance to be used
     * @see AddressMatchKeyApi.Builder#build() for a method to construct a {@code AddressMatchKeyApi} instance based on the configuration settings specified in the {@code Builder}
     */
    private AddressMatchKeyApi(InterzoidApi interzoidApi) {
        this.interzoidApi = interzoidApi;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    /**
     * Sends a request to the API to obtain a similarity key based on the provided address.
     *
     * @param request the {@link AddressMatchKeyRequest} containing the address and matching algorithm preference
     * @return the {@link MatchKeyResponse} containing the similarity key and other related information
     * @throws ValidationException   if the request is invalid, for instance, if any required field is missing or malformed
     * @throws InterzoidApiException if there is an error during the API request, like network issues or server errors
     * @see AddressMatchKeyRequest
     * @see MatchKeyResponse
     * @see InterzoidApiException
     */
    public MatchKeyResponse doRequest(AddressMatchKeyRequest request) throws IOException {
        Set<ConstraintViolation<AddressMatchKeyRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ValidationException("Validation failed", violations);
        }

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<MatchKeyResponse> jsonAdapter = moshi.adapter(MatchKeyResponse.class);

        Map<String, String> params = new HashMap<>();
        params.put("address", request.getAddress());
        params.put("algorithm", request.getMatchAlgorithm().getValue());

        String response = interzoidApi.doApiGetRequest(request.getApikey(), RESOURCE, params);
        return jsonAdapter.fromJson(response);
    }
}
