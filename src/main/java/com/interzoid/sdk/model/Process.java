package com.interzoid.sdk.model;

/**
 * The process to be performed.
 */
public enum Process {
    /**
     * Return a match report
     */
    MATCH_REPORT("matchreport"),
    /**
     * Return the source values and similarity keys only
     */
    KEYS_ONLY("keysonly"),
    /**
     * Return SQL insert statements for the match report
     */
    GEN_SQL("gensql"),
    /**
     * Create a new table in the source database with the similarity keys and source values
     */
    CREATE_TABLE("createtable")
    ;

    private String value;

    /**
     * Constructor
     * @param value The value
     */
    Process(String value) {
        this.value = value;
    }

    /**
     * Gets the value
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
