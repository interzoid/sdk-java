package com.interzoid.sdk.model;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;

import java.util.List;

/**
 * This represents a match report in JSON format.
 * @see CloudConnectResponse
 */
@JsonClass(generateAdapter = false)
public class CloudDatabaseJsonResponse implements CloudConnectResponse {
    @Json(name = "Status")
    private String status;

    @Json(name = "Message")
    private String message;

    @Json(name = "MatchClusters")
    private List<List<MatchCluster>> matchClusters;

    /**
     * Default constructor for the {@link CloudDatabaseJsonResponse} class.
     */
    public CloudDatabaseJsonResponse() {
    }

    /**
     * Gets the status of the response.
     * @return The status of the response
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the response.
     * @param status The status of the response
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the response message.
     * @return The response message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the response message.
     * @param message The response message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the list of match clusters.
     * @return The list of match clusters
     */
    public List<List<MatchCluster>> getMatchClusters() {
        return matchClusters;
    }

    /**
     * Sets the list of match clusters.
     * @param matchClusters The list of match clusters
     */
    public void setMatchClusters(List<List<MatchCluster>> matchClusters) {
        this.matchClusters = matchClusters;
    }

    /**
     * @return The string representation of the response
     */
    @Override
    public String toString() {
        return "CloudDatabaseJsonResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", matchClusters=" + matchClusters +
                '}';
    }

    /**
     * This represents a match cluster in JSON format.
     */
    @JsonClass(generateAdapter = false)
    public static class MatchCluster {
        @Json(name = "Data")
        private String data;

        @Json(name = "Reference")
        private String reference;

        @Json(name = "SimKey")
        private String simKey;

        /**
         * Default constructor for the {@link MatchCluster} class.
         */
        public MatchCluster() {
        }

        /**
         * Gets the original source data column.
         * @return The original source data column
         */
        public String getData() {
            return data;
        }

        /**
         * Sets the original source data column.
         * @param data The original source data column
         */
        public void setData(String data) {
            this.data = data;
        }

        /**
         * Gets the reference data column.
         * @return The reference data column
         */
        public String getReference() {
            return reference;
        }

        /**
         * Sets the reference data column.
         * @param reference The reference data column
         */
        public void setReference(String reference) {
            this.reference = reference;
        }

        /**
         * Gets the similarity key.
         * @return The similarity key
         */
        public String getSimKey() {
            return simKey;
        }

        /**
         * Sets the similarity key.
         * @param simKey The similarity key
         */
        public void setSimKey(String simKey) {
            this.simKey = simKey;
        }

        @Override
        public String toString() {
            return "MatchCluster{" +
                    "data='" + data + '\'' +
                    ", reference='" + reference + '\'' +
                    ", simKey='" + simKey + '\'' +
                    '}';
        }
    }
}
