package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressMatchKeyRequest extends InterzoidRequest {

    @NotBlank(message = "Address is required")
    private final String address;
    @NotNull(message = "Match algorithm is required")
    private final MatchAlgorithm matchAlgorithm;

    private AddressMatchKeyRequest(String apikey, String address, MatchAlgorithm matchAlgorithm) {
        super(apikey);
        this.address = address;
        this.matchAlgorithm = matchAlgorithm;
    }

    public static AddressMatchKeyRequest create(String apikey, String address, MatchAlgorithm matchAlgorithm) {
        return new AddressMatchKeyRequest(apikey, address, matchAlgorithm);
    }

    public String getAddress() {
        return address;
    }

    public MatchAlgorithm getMatchAlgorithm() {
        return matchAlgorithm;
    }

    @Override
    public String toString() {
        return "AddressMatchKeyRequest{" +
                "address='" + address + '\'' +
                '}';
    }

    public enum MatchAlgorithm {
        WIDE("wide"),
        MEDIUM("medium"),
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
