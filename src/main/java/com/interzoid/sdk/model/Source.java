package com.interzoid.sdk.model;

/**
 * The source datasource used for generating the match keys
 */
public enum Source {
    POSTGRES("Postgres"),
    MYSQL("mysql"),
    SNOWFLAKE("snowflake"),
    DATABRICKS("databricks"),
    AWS_AURORA_POSTGRES("AWS Aurora Postgres"),
    AWS_AURORA_MYSQL("AWS Aurora MySQL"),
    AWS_RDS_POSTGRES("AWS RDS Postgres"),
    AWS_RDS_MYSQL("AWS RDS MySQL"),
    AWS_RDS_SQL_SERVER("AWS RDS SQL Server"),
    GOOGLE_SQL_CLOUD_POSTGRES("Google SQL Cloud Postgres"),
    GOOGLE_SQL_CLOUD_MYSQL("Google SQL Cloud MySQL"),
    MARIADB("mariadb"),
    MARIADB_SKYSQL("MariaDB SkySQL"),
    MICROSOFT_SQL_SERVER("Microsoft SQL Server"),
    AZURE_SQL("Azure SQL"),
    AZURE_MYSQL("Azure MySQL"),
    COCKROACHDB("CockroachDB"),
    CSV("CSV"),
    TSV("TSV");

    private final String value;

    /**
     * Constructor
     * @param value The value
     */
    Source(String value) {
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

