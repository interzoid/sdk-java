package com.interzoid.sdk.model;

import jakarta.validation.constraints.*;

import java.util.HashMap;
import java.util.Map;

public class DelimitedFileMatchRequest extends InterzoidRequest{
    @NotNull(message = "source is required")
    private final Source source;
    @NotNull(message = "category is required")
    private final Category category;
    @NotBlank(message = "fileUrl is required")
    private final String fileUrl;
    @Positive(message = "value of column must be an int greater than 0")
    private final int column;
    @PositiveOrZero(message = "value of reference must be an int greater than or equal to 0")
    private int reference;
    @NotNull(message = "responseType is required")
    private final ResponseType responseType;

    /**
     * Constructs a new DelimitedFileMatchKeyReportRequest with a reference column
     *
     * @param apikey        The API key required for authenticating API requests.
     * @param source        The source of the data to be matched. See {@link Source}
     * @param category      The category of the data to be matched. See {@link Category}
     * @param fileUrl       The URL of the file to be matched
     * @param column        The column number of the data to be matched
     * @param reference     The column number of the data to use as a reference for matching
     * @param responseType  The type of response to be returned. See {@link ResponseType}
     */
    public DelimitedFileMatchRequest(String apikey, Source source, Category category, String fileUrl, int column, int reference, ResponseType responseType) {
        super(apikey);
        this.source = source;
        this.category = category;
        this.fileUrl = fileUrl;
        this.column = column;
        this.reference = reference;
        this.responseType = responseType;
    }

    /**
     * Constructs a new DelimitedFileMatchKeyReportRequest without a reference column
     *
     * @param apikey        The API key required for authenticating API requests.
     * @param source        The source of the data to be matched. See {@link Source}
     * @param category      The category of the data to be matched. See {@link Category}
     * @param fileUrl       The URL of the file to be matched
     * @param column        The column number of the data to be matched
     * @param responseType  The type of response to be returned. See {@link ResponseType}
     */
    public DelimitedFileMatchRequest(String apikey, Source source, Category category, String fileUrl, int column, ResponseType responseType) {
        super(apikey);
        this.source = source;
        this.category = category;
        this.fileUrl = fileUrl;
        this.column = column;
        this.responseType = responseType;
    }

    public Map<String, String> toParamMap() {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", getApikey());
        params.put("function", "match");
        params.put("process", "matchreport");
        params.put("category", category.getValue());
        params.put("source", source.getValue());
        params.put("table", source.getValue());
        params.put("connection", fileUrl);
        params.put("column", String.valueOf(column));
        if (reference > 0) {
            params.put("reference", String.valueOf(reference));
        }
        switch (responseType) {
            case JSON:
                params.put("json", "true");
                break;
            case HTML:
                params.put("html", "true");
                break;
            case TXT:
            default:
                break;
        }
        return params;
    }

    public Source getSource() {
        return source;
    }

    public Category getCategory() {
        return category;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public int getColumn() {
        return column;
    }

    public int getReference() {
        return reference;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    @Override
    public String toString() {
        return "DelimitedFileMatchRequest{" +
                "source=" + source +
                ", category=" + category +
                ", fileUrl='" + fileUrl + '\'' +
                ", column=" + column +
                ", reference=" + reference +
                ", responseType=" + responseType +
                "} " + super.toString();
    }
}
