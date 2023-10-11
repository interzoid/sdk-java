package com.interzoid.sdk.api;

import com.interzoid.sdk.api.exceptions.InterzoidApiException;
import com.interzoid.sdk.api.exceptions.ValidationException;
import com.interzoid.sdk.model.*;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.lang.Process;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <h2>Interzoid Cloud Database MatchKey Report API</h2>
 * <p>
 * Provides a client for interacting with the Interzoid Cloud Database MatchKey Report API.
 * This API client allows for processing cloud database workload requests and retrieving the respective responses.
 * </p>
 *
 * <p>Immediate results can be displayed, written to a text file, used to generate a SQL INSERT scripts, or stored in a new table in the source database.</p>
 *
 * <p>APIs should be reused instead of creating new instances per request. This will give you the best performance.</p>
 *
 * <h3>Example</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.CloudDatabaseMatchKeyReportApi;
 * import com.interzoid.sdk.model.CloudWorkloadRequest;
 * import com.interzoid.sdk.model.CloudConnectResponse;
 *
 * public class CloudDatabaseMatchKeyReportTest {
 *   public static void main(String[] args) throws Exception {
 *     CloudDatabaseMatchKeyReportApi api = new CloudDatabaseMatchKeyReportApi.Builder().build();
 *     CloudWorkloadRequest request = new CloudWorkloadRequest(
 *       "YOUR-API-KEY",        // apiKey
 *       Process.CREATE_TABLE,  // process
 *       Source.MYSQL           // source
 *       Category.COMPANY,      // category
 *       "db_username:db_password@tcp(db_host)/db_name", // connectionString
 *       "companies",           // sourceTable
 *       "companyname",         // sourceColumn
 *       "id",                  // sourceReferenceColumn
 *       "companies_matched",   // targetTable
 *     );
 *     CloudConnectResponse response = api.doRequest(request);
 *   }
 * }
 * }</pre>
 *
 * 
 * @see <a href="https://connect.interzoid.com/">Interzoid Cloud Data Connect</a>
 * @see <a href="https://connect.interzoid.com/connection-strings">Interzoid Cloud Data Connect Example Connection Strings</a>
 * @see Process
 * @see Source
 * @see Category
 * @see CloudWorkloadRequest
 * @version 1.0
 */
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
         *
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
     * Processes a Cloud Database Workload request to obtain a MatchKey report.
     *
     * @param request the {@link CloudWorkloadRequest} object containing the details of the workload request
     * @return a {@link CloudConnectResponse} object containing the results of the workload request
     * @throws ValidationException if the request is invalid or missing required parameters
     * @throws IOException         if the API request fails for any reason
     * @see CloudWorkloadRequest
     * @see CloudConnectResponse
     * @see InterzoidApiException
     */
    public CloudConnectResponse doRequest(CloudWorkloadRequest request) throws IOException {
        // Validate request object
        Set<ConstraintViolation<CloudWorkloadRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new ValidationException("Validation failed", violations);
        }

        String response = interzoidApi.doCloudConnectRequest(request.toParamMap());
        if (request.isJson()) {
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<CloudDatabaseJsonResponse> jsonAdapter = moshi.adapter(CloudDatabaseJsonResponse.class);
            return jsonAdapter.fromJson(response);
        } else {
            return new CloudDatabaseStringResponse(response);
        }
    }
}
