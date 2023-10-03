package com.interzoid.sdk.model;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.List;

@JsonClass(generateAdapter = false)
public class CloudDatabaseJsonResponse implements CloudConnectResponse {
    @Json(name = "Status")
    private String status;

    @Json(name = "Message")
    private String message;

    @Json(name = "MatchClusters")
    private List<List<MatchCluster>> matchClusters;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<List<MatchCluster>> getMatchClusters() {
        return matchClusters;
    }

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

    @JsonClass(generateAdapter = false)
    public static class MatchCluster {

        @Json(name = "Data")
        private String data;

        @Json(name = "Reference")
        private String reference;

        @Json(name = "SimKey")
        private String simKey;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getSimKey() {
            return simKey;
        }

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
