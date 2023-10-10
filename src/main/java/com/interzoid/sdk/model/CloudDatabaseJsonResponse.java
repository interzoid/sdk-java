package com.interzoid.sdk.model;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;

/**
 * @author Interzoid
 * @version 1.1
 *
 * This represents a match report in JSON format.
 * @see CloudConnectResponse
 */
@JsonClass(generateAdapter = false)
public class CloudDatabaseJsonResponse implements CloudConnectResponse {
    /**
     * The status of the response
     */
    @Json(name = "Status")
    private String status;

    /**
     * The response message
     */
    @Json(name = "Message")
    private String message;

    /**
     * The list of match clusters
     */
    @Json(name = "MatchClusters")
    private List<List<MatchCluster>> matchClusters;

    /**
     * @return The status of the response
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status of the response
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The response message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The response message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The list of match clusters
     */
    public List<List<MatchCluster>> getMatchClusters() {
        return matchClusters;
    }

    /**
     * @param matchClusters The list of match clusters
     */
    public void setMatchClusters(List<List<MatchCluster>> matchClusters) {
        this.matchClusters = matchClusters;
    }

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

        /**
         * The original source data column
         */
        @Json(name = "Data")
        private String data;

        /**
         * The reference data column
         */
        @Json(name = "Reference")
        private String reference;

        /**
         * The similarity key
         */
        @Json(name = "SimKey")
        private String simKey;

        /**
         * @return The original source data column
         */
        public String getData() {
            return data;
        }

        /**
         * @param data The original source data column
         */
        public void setData(String data) {
            this.data = data;
        }

        /**
         * @return The reference data column
         */
        public String getReference() {
            return reference;
        }

        /**
         * @param reference The reference data column
         */
        public void setReference(String reference) {
            this.reference = reference;
        }

        /**
         * @return The similarity key
         */
        public String getSimKey() {
            return simKey;
        }

        /**
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
