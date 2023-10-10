package com.interzoid.sdk.api;

import com.interzoid.sdk.model.*;
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
 * @author Interzoid
 * @version 1.1
 * @see <a href="https://connect.interzoid.com/csv-file-processing">CSV File Processing for Data Analysis and Enhancement</a>
 * @see TextFileMatchRequest
 * @see CloudDatabaseJsonResponse
 * @see CloudDatabaseStringResponse
 *
 * <h2>CSV/TSV file Data Matching Workload API</h2>
 * <p>
 * This class provides the functionality for making requests to the Interzoid Data Matching APIs using CSV or TSV files as input.
 *
 * <h3>Example</h3>
 * <pre>{@code
 * import com.interzoid.sdk.api.TextFileMatchKeyReportApi;
 * import com.interzoid.sdk.model.TextFileMatchRequest;
 * import com.interzoid.sdk.model.CloudDatabaseJsonResponse;
 *
 * public class TextFileMatchKeyReportTest {
 *   public static void main(String[] args) throws Exception {
 *     TextFileMatchKeyReportApi api = new TextFileMatchKeyReportApi.Builder().build();
 *     TextFileMatchRequest request = new TextFileMatchRequest(
 *       "YOUR-API-KEY",
 *       Source.CSV,
 *       Category.COMPANY,
 *       "https://dl.interzoid.com/csv/companies.csv",
 *       1,
 *       ResponseType.JSON
 *     );
 *     CloudDatabaseJsonResponse response = (CloudDatabaseJsonResponse) api.doRequest(request);
 *   }
 * }
 * }</pre>
 */
public final class TextFileMatchKeyReportApi {
    private final InterzoidApi interzoidApi;
    private final Validator validator;

    /**
     * The builder class for {@link TextFileMatchKeyReportApi}, providing a flexible way to configure and create an instance of {@code CloudDatabaseMatchKeyReportApi}.
     * It follows the builder pattern, allowing for configuration settings to be specified before calling the {@link TextFileMatchKeyReportApi.Builder#build()} method to construct an instance of {@code DelimitedFileMatchKeyReportApi}.
     */
    public static class Builder {
        private OkHttpClient client;
        private InterzoidApi api;

        /**
         * Default constructor for the {@link TextFileMatchKeyReportApi.Builder} class.
         */
        public Builder() {
        }

        /**
         * Specifies the {@link OkHttpClient} instance to be used by the {@code DelimitedFileMatchKeyReportApi} instance being built.
         * This is optional and allows for customization of the {@code OkHttpClient} instance used internally by the {@code DelimitedFileMatchKeyReportApi} instance.
         *
         * @param client the {@code OkHttpClient} instance to be used
         * @return the current builder instance, allowing for method chaining
         */
        public TextFileMatchKeyReportApi.Builder withClient(OkHttpClient client) {
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
        TextFileMatchKeyReportApi.Builder withInterzoidApi(InterzoidApi interzoidApi) {
            this.api = interzoidApi;
            return this;
        }

        /**
         * Constructs a new {@code DelimitedFileMatchKeyReportApi} instance with the current configuration settings specified in this builder.
         *
         * @return a new {@code DelimitedFileMatchKeyReportApi} instance
         */
        public TextFileMatchKeyReportApi build() {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .build();
            }
            if (api == null) {
                api = new InterzoidApi(client);
            }
            return new TextFileMatchKeyReportApi(api);
        }
    }

    /**
     * Constructs a new {@code DelimitedFileMatchKeyReportApi} instance with the given {@link InterzoidApi} instance.
     *
     * @param interzoidApi the {@code InterzoidApi} instance to be used
     */
    private TextFileMatchKeyReportApi(InterzoidApi interzoidApi) {
        this.interzoidApi = interzoidApi;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    public CloudConnectResponse doRequest(TextFileMatchRequest request) throws InterzoidApiException {
        Set<ConstraintViolation<TextFileMatchRequest>> violations = validator.validate(request);
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
                return new CloudDatabaseStringResponse(response);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
