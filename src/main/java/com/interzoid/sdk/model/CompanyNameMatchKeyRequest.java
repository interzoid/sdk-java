package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents a request for generating a company name match key, used for comparing and sorting company names.
 * This class is used to specify the company name, match algorithm, and API key required for the request.
 * @see InterzoidRequest
 * @see MatchAlgorithm
 */
public class CompanyNameMatchKeyRequest extends InterzoidRequest {

    @NotBlank(message = "Company name is required")
    private final String companyName;

    @NotNull(message = "Match algorithm is required")
    private final MatchAlgorithm matchAlgorithm;

    /**
     * Constructs a new CompanyNameMatchKeyRequest with the specified API key, company name, and match algorithm.
     *
     * @param apikey         The API key required for making the request.
     * @param companyName    The company name for which the match key is generated.
     * @param matchAlgorithm The match algorithm to use.
     * @see MatchAlgorithm
     */
    public CompanyNameMatchKeyRequest(String apikey, String companyName, MatchAlgorithm matchAlgorithm) {
        super(apikey);
        this.companyName = companyName;
        this.matchAlgorithm = matchAlgorithm;
    }

    /**
     * Gets the company name.
     * @return The company name.
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Gets the match algorithm.
     * @return The match algorithm.
     * @see MatchAlgorithm
     */
    public MatchAlgorithm getMatchAlgorithm() {
        return matchAlgorithm;
    }

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
         * Constructs a new MatchAlgorithm with the specified string value.
         * @param value The string value of the match algorithm.
         */
        MatchAlgorithm(String value) {
            this.value = value;
        }

        /**
         * Gets the match algorithm value.
         * @return The match algorithm value.
         */
        public String getValue() {
            return value;
        }
    }
}
