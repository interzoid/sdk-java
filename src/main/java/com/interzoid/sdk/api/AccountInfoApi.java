package com.interzoid.sdk.api;

import com.interzoid.sdk.model.InterzoidRequest;
import com.interzoid.sdk.model.InterzoidResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import okhttp3.OkHttpClient;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <h2>Account Info (Credits Remaining) API</h2>
 *
 * <p>Copyright (C) 2023 Interzoid, Inc.</p>
 *
 * <p>AccountInfoApi is a client for the Interzoid Get Remaining Credits API.</p>
 *
 * <p>APIs should be reused instead of creating new instances per request. This will give you the best performance.</p>
 *
 * <h3>Example</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.AccountInfoApi;
 * import com.interzoid.sdk.model.AccountInfoRequest;
 * import com.interzoid.sdk.model.AccountInfoResponse;
 *
 * AccountInfoApi api = new AccountInfoApi.Builder().build();
 *
 * AccountInfoRequest request = AccountInfoRequest.create("YOUR-API-KEY");
 * AccountInfoResponse response = api.getAccountInfo(request);
 * }</pre>
 *
 * <h3>With Custom OkHttpClient</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.AccountInfoApi;
 * import okhttp3.OkHttpClient;
 * import java.util.concurrent.TimeUnit;
 *
 * OkHttpClient okHttpClient = new OkHttpClient.Builder()
 *      .connectTimeout(10, TimeUnit.SECONDS)
 *      // other configuration
 *      .build();
 * AccountInfoApi api = new AccountInfoApi.Builder().withClient(okHttpClient).build();
 *
 * // usage of api is the same as above
 * }</pre>
 *
 * @author Interzoid, Inc
 * @version 1.0
 * @see <a href="https://www.interzoid.com/apis/remaining-api-credits">Credits Remaining API</a>
 */


public final class AccountInfoApi {
    private static final String RESOURCE = "getremainingcredits";
    private final InterzoidApi interzoidApi;
    private final Validator validator;

    /**
     * The builder class for {@link AccountInfoApi}, providing a flexible way to configure and create an instance of {@code AccountInfoApi}.
     * It follows the builder pattern, allowing for configuration settings to be specified before calling the {@link Builder#build()} method to construct an instance of {@code AccountInfoApi}.
     *
     * @see AccountInfoApi
     */
    public static class Builder {
        private OkHttpClient client;
        private InterzoidApi api;

        /**
         * Default constructor for the {@link Builder} class.
         */
        public Builder() {
        }

        /**
         * Specifies the {@link OkHttpClient} instance to be used by the {@code AccountInfoApi} instance being built.
         * This is optional and allows for customization of the {@code OkHttpClient} instance used internally by the {@code AccountInfoApi} instance.
         *
         * @param client the {@code OkHttpClient} instance to be used
         * @return the current builder instance, allowing for method chaining
         */
        public Builder withClient(OkHttpClient client) {
            this.client = client;
            return this;
        }

        /**
         * Specifies the {@link InterzoidApi} instance to be used by the {@code AccountInfoApi} instance being built.
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
         * Constructs a new {@code AccountInfoApi} instance with the current configuration settings specified in this builder.
         *
         * @return a new {@code AccountInfoApi} instance
         */
        public AccountInfoApi build() {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .build();
            }
            if (api == null) {
                api = new InterzoidApi(client);
            }
            return new AccountInfoApi(api);
        }
    }

    /**
     * Constructs a new {@code AccountInfoApi} instance with the specified {@link InterzoidApi} instance.
     *
     * @param interzoidApi the {@code InterzoidApi} instance to be used
     * @see Builder#build() for a method to construct a {@code AccountInfoApi} instance based on the configuration settings specified in the {@code Builder}
     */
    private AccountInfoApi(InterzoidApi interzoidApi) {
        this.interzoidApi = interzoidApi;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    /**
     * Retrieves the number of remaining credits in your account.
     * This endpoint does not deduct credits from your account.
     *
     * @param request the request object containing the necessary details, including the API key, to fetch the account information
     * @return a representation of the account information as an {@code AccountInfoResponse} object
     * @throws ValidationException if the request is invalid
     * @throws Exception           if the request fails
     * @see InterzoidRequest
     * @see InterzoidResponse
     * @see InterzoidApiException
     */
    public InterzoidResponse doRequest(InterzoidRequest request) throws Exception {
        Set<ConstraintViolation<InterzoidRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ValidationException("Validation failed", violations);
        }

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<InterzoidResponse> jsonAdapter = moshi.adapter(InterzoidResponse.class);

        String response = interzoidApi.doGetRequest(request.getApikey(), RESOURCE, null);
        return jsonAdapter.fromJson(response);
    }
}
