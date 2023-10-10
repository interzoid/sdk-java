package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Interzoid
 * @version 1.1
 * The CloudWorkloadRequest class is used to run cloud database workload requests against your cloud connected database.
 * @see <a href="https://connect.interzoid.com/">Interzoid Cloud Data Connect</a>
 * @see <a href="https://connect.interzoid.com/connection-strings">Interzoid Cloud Data Connect Example Connection Strings</a>
 * @see InterzoidRequest
 * @see Process
 * @see Source
 * @see Category
 */
@ValidCloudWorkloadRequest
public class CloudWorkloadRequest extends InterzoidRequest {
    @NotNull(message = "process is required")
    private final Process process;
    @NotNull(message = "source is required")
    private final Source source;
    @NotNull(message = "category is required")
    private final Category category;
    @NotBlank(message = "connectionString is required")
    private final String connectionString;
    @NotBlank(message = "sourceTableName is required")
    private final String sourceTableName;
    @NotBlank(message = "matchColumn is required")
    private final String matchColumn;
    private final String referenceColumn;
    private String newTableName;
    private boolean json;
    private boolean html;

    /**
     * Creates a new CloudWorkloadRequest.
     * This constructor is preferred when returning a match report ({@code Process.MATCH_REPORT}) as JSON, HTML, or text.
     * Set both the json and html flags to false to return the match report as text.
     * @param apikey The API key to be used for the request.
     * @param process The process to run
     * @param source The source used for the match report.
     * @param category The category used for generating the match keys.
     * @param connectionString The connection string used for the match report.
     * @param sourceTableName The table used for the match report.
     * @param matchColumn The column used for matching.
     * @param referenceColumn An optional reference column used in the report. Set to null if not used.
     * @param json Return match report as JSON.
     * @param html Return match report as HTML
     */
    public CloudWorkloadRequest(String apikey, Process process, Source source, Category category, String connectionString, String sourceTableName, String matchColumn, String referenceColumn, boolean json, boolean html) {
        super(apikey);
        this.process = process;
        this.source = source;
        this.category = category;
        this.connectionString = connectionString;
        this.sourceTableName = sourceTableName;
        this.matchColumn = matchColumn;
        this.referenceColumn = referenceColumn;
        this.json = json;
        this.html = html;
    }

    /**
     * This constructor is preferred when writing match results to a new table, generating SQL insert statements, or
     * returning match keys only. ({@code Process.CREATE_TABLE}, {@code Process.KEYS_ONLY}, or {@code Process.GEN_SQL})
     * @param apikey The API key to be used for the request.
     * @param process The process to run
     * @param source The source used for the match report.
     * @param category The category used for generating the match keys.
     * @param connectionString The connection string used for the match report.
     * @param sourceTableName The table used for the match report.
     * @param matchColumn The column used for matching.
     * @param referenceColumn An optional reference column used in the report.
     * @param newTableName A new table to store the match results. This is required if the process is CREATE_TABLE.
     */
    public CloudWorkloadRequest(String apikey, Process process, Source source, Category category, String connectionString, String sourceTableName, String matchColumn, String referenceColumn, String newTableName) {
        super(apikey);
        this.process = process;
        this.source = source;
        this.category = category;
        this.connectionString = connectionString;
        this.sourceTableName = sourceTableName;
        this.matchColumn = matchColumn;
        this.referenceColumn = referenceColumn;
        this.newTableName = newTableName;
    }

    /**
     * Converts the CloudWorkloadRequest to a Map of parameters to be used in the request.
     * @return A Map of parameters to be used in the request.
     */
    public Map<String, String> toParamMap() {
        Map<String, String> params = new HashMap<>();
        params.put("function", "match");
        params.put("apikey", getApikey());
        params.put("process", process.getValue());
        params.put("source", source.getValue());
        params.put("category", category.getValue());
        params.put("connection", connectionString);
        params.put("table", sourceTableName);
        params.put("column", matchColumn);
        if (referenceColumn != null) {
            params.put("reference", referenceColumn);
        }
        if (newTableName != null) {
            params.put("newtable", newTableName);
        }
        if (json) {
            params.put("json", "true");
        }
        if (html) {
            params.put("html", "true");
        }
        return params;
    }

    /**
     * @see Process
     * @return The process.
     */
    public Process getProcess() {
        return process;
    }

    /**
     * @see Source
     * @return The source.
     */
    public Source getSource() {
        return source;
    }

    /**
     * @see Category
     * @return The category.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @return The connection string.
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * @return The source table name.
     */
    public String getSourceTableName() {
        return sourceTableName;
    }

    /**
     * @return The source match column name.
     */
    public String getMatchColumn() {
        return matchColumn;
    }

    /**
     * @return The reference column name.
     */
    public String getReferenceColumn() {
        return referenceColumn;
    }

    /**
     * @return The new table name.
     */
    public String getNewTableName() {
        return newTableName;
    }

    /**
     * @return The JSON flag.
     */
    public boolean isJson() {
        return json;
    }

    /**
     * @return The HTML flag.
     */
    public boolean isHtml() {
        return html;
    }

    @Override
    public String toString() {
        return "CloudWorkloadRequest{" +
                "process=" + process +
                ", source=" + source +
                ", category=" + category +
                ", connectionString='" + connectionString + '\'' +
                ", table='" + sourceTableName + '\'' +
                ", column='" + matchColumn + '\'' +
                ", reference='" + referenceColumn + '\'' +
                ", newTable='" + newTableName + '\'' +
                ", json=" + json +
                ", html=" + html +
                "} " + super.toString();
    }
}
