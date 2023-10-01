package com.interzoid.sdk.model;

/**
 * The process to be performed.
 */
public enum Process {
    MATCH_REPORT("matchreport"),
    KEYS_ONLY("keysonly"),
    GEN_SQL("gensql"),
    CREATE_TABLE("createtable")
    ;

    private String value;

    Process(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
