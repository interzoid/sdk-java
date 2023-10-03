package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashMap;
import java.util.Map;

@ValidCreateTableRequest
public class CloudWorkloadRequest extends InterzoidRequest {
    @NotNull(message = "process is required")
    private final Process process;
    @NotNull(message = "source is required")
    private final Source source;
    @NotNull(message = "category is required")
    private final Category category;
    @NotBlank(message = "connectionString is required")
    private final String connectionString;
    @NotBlank(message = "table is required")
    private final String table;
    @NotBlank(message = "column is required")
    private final String column;
    private final String reference;
    private final String newTable; // Required if process is `CREATE_TABLE`
    private final boolean json;
    private final boolean html;

    public CloudWorkloadRequest(String apikey, Process process, Source source, Category category, String connectionString, String table, String column, String reference, String newTable, boolean json, boolean html) {
        super(apikey);
        this.process = process;
        this.source = source;
        this.category = category;
        this.connectionString = connectionString;
        this.table = table;
        this.column = column;
        this.reference = reference;
        this.newTable = newTable;
        this.json = json;
        this.html = html;
    }

    public Map<String, String> toParamMap() {
        Map<String, String> params = new HashMap<>();
        params.put("function", "match");
        params.put("apikey", getApikey());
        params.put("process", process.getValue());
        params.put("source", source.getValue());
        params.put("category", category.getValue());
        params.put("connection", connectionString);
        params.put("table", table);
        params.put("column", column);
        if (reference != null) {
            params.put("reference", reference);
        }
        if (newTable != null) {
            params.put("newtable", newTable);
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
     * Gets the process for which the match key is generated.
     *
     * @return The process.
     */
    public Process getProcess() {
        return process;
    }

    /**
     * Gets the source used for generating the match key.
     *
     * @return The source.
     */
    public Source getSource() {
        return source;
    }

    /**
     * Gets the category used for generating the match key.
     *
     * @return The category.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Gets the connection string used for generating the match key.
     *
     * @return The connection string.
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * Gets the table used for generating the match key.
     *
     * @return The table.
     */
    public String getTable() {
        return table;
    }

    /**
     * Gets the column used for generating the match key.
     *
     * @return The column.
     */
    public String getColumn() {
        return column;
    }

    /**
     * Gets the reference used for generating the match key.
     *
     * @return The reference.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Gets the new table used to store match results.
     *
     * @return The new table.
     */
    public String getNewTable() {
        return newTable;
    }

    /**
     * Gets the JSON flag used for generating the match key.
     *
     * @return The JSON flag.
     */
    public boolean isJson() {
        return json;
    }

    /**
     * Gets the HTML flag used for generating the match key.
     *
     * @return The HTML flag.
     */
    public boolean isHtml() {
        return html;
    }

    /**
     * Generates a string representation of the CloudWorkloadRequest for debugging purposes.
     *
     * @return A string containing the process, source, category, connection string, table, column, reference, new table, JSON flag, and HTML flag.
     */
    @Override
    public String toString() {
        return "CloudWorkloadRequest{" +
                "process=" + process +
                ", source=" + source +
                ", category=" + category +
                ", connectionString='" + connectionString + '\'' +
                ", table='" + table + '\'' +
                ", column='" + column + '\'' +
                ", reference='" + reference + '\'' +
                ", newTable='" + newTable + '\'' +
                ", json=" + json +
                ", html=" + html +
                "} " + super.toString();
    }
}
