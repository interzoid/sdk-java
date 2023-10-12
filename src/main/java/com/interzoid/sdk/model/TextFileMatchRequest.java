package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This is a request object for the Delimited File Match service
 * @see com.interzoid.sdk.api.TextFileMatchKeyReportApi
 * @see Source
 * @see Category
 * @see ResponseType
 * @see InterzoidRequest
 */
public class TextFileMatchRequest extends InterzoidRequest {
    @NotNull(message = "source is required")
    private final Source source;
    @NotNull(message = "category is required")
    private final Category category;
    @NotBlank(message = "fileUrl is required")
    private final String fileUrl;
    @Positive(message = "value of matchColumnNumber must be an int greater than 0")
    private final int matchColumnNumber;
    @PositiveOrZero(message = "value of referenceColumnNumber must be an int greater than or equal to 0")
    private int referenceColumnNumber;
    @NotNull(message = "responseType is required")
    private final ResponseType responseType;

    /**
     * Constructs a new TextFileMatchRequest with a reference column
     *
     * @param apikey       The API key required for authenticating API requests.
     * @param source       The source of the data to be matched. See {@link Source}
     * @param category     The category of the data to be matched. See {@link Category}
     * @param fileUrl      The URL of the file to be matched
     * @param matchColumnNumber       The column number of the data to be matched
     * @param referenceColumnNumber    The column number of the data to use as a reference for matching
     * @param responseType The type of response to be returned. See {@link ResponseType}
     */
    public TextFileMatchRequest(String apikey, Source source, Category category, String fileUrl, int matchColumnNumber, int referenceColumnNumber, ResponseType responseType) {
        super(apikey);
        this.source = source;
        this.category = category;
        this.fileUrl = fileUrl;
        this.matchColumnNumber = matchColumnNumber;
        this.referenceColumnNumber = referenceColumnNumber;
        this.responseType = responseType;
    }

    /**
     * Constructs a new TextFileMatchRequest without a reference column
     *
     * @param apikey       The API key required for authenticating API requests.
     * @param source       The source of the data to be matched. See {@link Source}
     * @param category     The category of the data to be matched. See {@link Category}
     * @param fileUrl      The URL of the file to be matched
     * @param matchColumnNumber       The column number of the data to be matched
     * @param responseType The type of response to be returned. See {@link ResponseType}
     */
    public TextFileMatchRequest(String apikey, Source source, Category category, String fileUrl, int matchColumnNumber, ResponseType responseType) {
        super(apikey);
        this.source = source;
        this.category = category;
        this.fileUrl = fileUrl;
        this.matchColumnNumber = matchColumnNumber;
        this.responseType = responseType;
    }

    /**
     * Converts the request object to a Map of key/value pairs for use in the API request
     *
     * @return A Map of key/value pairs representing the request object
     */
    public Map<String, String> toParamMap() {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", getApikey());
        params.put("function", "match");
        params.put("process", "matchreport");
        params.put("category", category.getValue());
        params.put("source", source.getValue());
        params.put("table", source.getValue());
        params.put("connection", fileUrl);
        params.put("column", String.valueOf(matchColumnNumber));
        if (referenceColumnNumber > 0) {
            params.put("reference", String.valueOf(referenceColumnNumber));
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

    /**
     * Gets the Source
     * @return The source of the data to be matched
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the Category
     * @return The category of the data to be matched
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Gets the file URL
     * @return The URL of the file to be matched
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * Gets the match column number
     * @return The column number of the data to be matched
     */
    public int getMatchColumnNumber() {
        return matchColumnNumber;
    }

    /**
     * Gets the reference column number
     * @return The column number of the data to use as a reference for matching
     */
    public int getReferenceColumnNumber() {
        return referenceColumnNumber;
    }

    /**
     * Gets the type of response to be returned
     * @return The type of response to be returned
     */
    public ResponseType getResponseType() {
        return responseType;
    }

    /**
     * Returns a string representation of the object
     * @return A string representation of the object
     */
    @Override
    public String toString() {
        return "DelimitedFileMatchRequest{" +
                "source=" + source +
                ", category=" + category +
                ", fileUrl='" + fileUrl + '\'' +
                ", column=" + matchColumnNumber +
                ", reference=" + referenceColumnNumber +
                ", responseType=" + responseType +
                "} " + super.toString();
    }
}
