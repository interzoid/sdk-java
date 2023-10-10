package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Interzoid
 * @version 1.1
 * Represents a request for generating an address match key, used for comparing and sorting address data.
 * This class is used to specify the address, match algorithm, and API key required for the request.
 * @see MatchAlgorithm
 * @see InterzoidRequest
 */
public class AddressMatchKeyRequest extends InterzoidRequest {

    /**
     * The address for which the match key is generated.
     */
    @NotBlank(message = "Address is required")
    private final String address;

    /**
     * The match algorithm to use when generating the match key.
     * Possible values are "wide," "medium," or "narrow."
     *
     * @see MatchAlgorithm
     */
    @NotNull(message = "Match algorithm is required")
    private final MatchAlgorithm matchAlgorithm;

    /**
     * Constructs a new AddressMatchKeyRequest with the specified API key, address, and match algorithm.
     *
     * @param apikey         The API key required for making the request.
     * @param address        The address for which the match key is generated.
     * @param matchAlgorithm The match algorithm to use.
     * @see MatchAlgorithm
     */
    public AddressMatchKeyRequest(String apikey, String address, MatchAlgorithm matchAlgorithm) {
        super(apikey);
        this.address = address;
        this.matchAlgorithm = matchAlgorithm;
    }

    /**
     * Gets the address for which the match key is generated.
     *
     * @return The address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the match algorithm used for generating the match key.
     *
     * @return The match algorithm.
     * @see MatchAlgorithm
     */
    public MatchAlgorithm getMatchAlgorithm() {
        return matchAlgorithm;
    }

    /**
     * Generates a string representation of the AddressMatchKeyRequest for debugging purposes.
     *
     * @return A string containing the address.
     */
    @Override
    public String toString() {
        return "AddressMatchKeyRequest{" +
                "address='" + address + '\'' +
                ", matchAlgorithm=" + matchAlgorithm +
                "} " + super.toString();
    }

    /**
     * Enumerates the available match algorithms for generating address match keys.
     */
    public enum MatchAlgorithm {
        /**
         * The "wide" match algorithm considers the primary address only when generating match keys.
         */
        WIDE("wide"),

        /**
         * The "medium" match algorithm considers a broader range of address elements when generating match keys.
         */
        MEDIUM("medium"),

        /**
         * The "narrow" match algorithm considers unit numbers (suite, apartment, unit, etc.) when generating match keys.
         * This ensures individual units are identified separately when comparing generated keys.
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
