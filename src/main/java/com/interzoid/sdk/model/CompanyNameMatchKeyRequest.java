package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CompanyNameMatchKeyRequest extends InterzoidRequest {
    @NotBlank(message = "Company name is required")
    private final String companyName;
    @NotNull(message = "Match algorithm is required")
    private final MatchAlgorithm matchAlgorithm;

    private CompanyNameMatchKeyRequest(String apikey, String companyName, MatchAlgorithm matchAlgorithm) {
        super(apikey);
        this.companyName = companyName;
        this.matchAlgorithm = matchAlgorithm;
    }

    public static CompanyNameMatchKeyRequest create(String apikey, String companyName, MatchAlgorithm matchAlgorithm) {
        return new CompanyNameMatchKeyRequest(apikey, companyName, matchAlgorithm);
    }

    public String getCompanyName() {
        return companyName;
    }

    public MatchAlgorithm getMatchAlgorithm() {
        return matchAlgorithm;
    }

    @Override
    public String toString() {
        return "CompanyNameMatchKeyRequest{" +
                "companyName='" + companyName + '\'' +
                '}';
    }

    public enum MatchAlgorithm {
        WIDE("wide"),
        NARROW("narrow");

        private final String value;

        MatchAlgorithm(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
