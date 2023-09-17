package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a request for generating a company name match key, used for comparing and sorting company names.
 * This class is used to specify the company name, match algorithm, and API key required for the request.
 */
public class CompanyNameMatchKeyRequest extends InterzoidRequest {

    /**
     * The company name for which the match key is generated.
     */
    @NotBlank(message = "Company name is required")
    private final String companyName;

    /**
     * The match algorithm to use when generating the match key.
     * Possible values are "wide" or "narrow."
     */
    @NotNull(message = "Match algorithm is required")
    private final MatchAlgorithm matchAlgorithm;

    /**
     * Constructs a new CompanyNameMatchKeyRequest with the specified API key, company name, and match algorithm.
     *
     * @param apikey         The API key required for making the request.
     * @param companyName    The company name for which the match key is generated.
     * @param matchAlgorithm The match algorithm to use.
     */
    public CompanyNameMatchKeyRequest(String apikey, String companyName, MatchAlgorithm matchAlgorithm) {
        super(apikey);
        this.companyName = companyName;
        this.matchAlgorithm = matchAlgorithm;
    }

    /**
     * Gets the company name for which the match key is generated.
     *
     * @return The company name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Gets the match algorithm used for generating the match key.
     *
     * @return The match algorithm.
     */
    public MatchAlgorithm getMatchAlgorithm() {
        return matchAlgorithm;
    }

    /**
     * Generates a string representation of the CompanyNameMatchKeyRequest for debugging purposes.
     *
     * @return A string containing the company name.
     */
    @Override
    public String toString() {
        return "CompanyNameMatchKeyRequest{" +
                "companyName='" + companyName + '\'' +
                '}';
    }

    /**
     * Enumerates the available match algorithms for generating company name match keys.
     */
    public enum MatchAlgorithm {
        /**
         * The "wide" match algorithm generates company name match keys that consider a broader range of elements.
         */
        WIDE("wide"),

        /**
         * The "narrow" match algorithm generates company name match keys that consider a narrower range of elements.
         */
        NARROW("narrow");

        private final String value;

        /**
         * Constructs a MatchAlgorithm enum value with the specified string value.
         *
         * @param value The string value of the match algorithm.
         */
        MatchAlgorithm(String value) {
            this.value = value;
        }

        /**
         * Gets the string value of the match algorithm.
         *
         * @return The match algorithm value.
         */
        public String getValue() {
            return value;
        }
    }
}
