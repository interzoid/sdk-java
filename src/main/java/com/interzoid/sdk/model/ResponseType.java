package com.interzoid.sdk.model;

import com.interzoid.sdk.api.TextFileMatchKeyReportApi;

/**
 * @author Interzoid
 * @version 1.1
 * Represents the response type for the MatchKey API.
 * @see TextFileMatchKeyReportApi
 */
public enum ResponseType {
    /**
     * Return match report as JSON
     */
    JSON,
    /**
     * Return match report as HTML
     */
    HTML,
    /**
     * Return match report as TXT
     */
    TXT
}
