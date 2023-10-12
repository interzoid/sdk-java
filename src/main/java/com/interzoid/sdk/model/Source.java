package com.interzoid.sdk.model;

/**
 * 
 * The source datasource used for generating the match keys
 */
public enum Source {
    /**
     * Postgres
     */
    POSTGRES("Postgres"),
    /**
     * MySQL
     */
    MYSQL("mysql"),
    /**
     * Snowflake
     */
    SNOWFLAKE("snowflake"),
    /**
     * Databricks
     */
    DATABRICKS("databricks"),
    /**
     * AWS Aurora Postgres
     */
    AWS_AURORA_POSTGRES("AWS Aurora Postgres"),
    /**
     * AWS Aurora MySQL
     */
    AWS_AURORA_MYSQL("AWS Aurora MySQL"),
    /**
     * AWS RDS Postgres
     */
    AWS_RDS_POSTGRES("AWS RDS Postgres"),
    /**
     * AWS RDS MySQL
     */
    AWS_RDS_MYSQL("AWS RDS MySQL"),
    /**
     * AWS RDS SQL Server
     */
    AWS_RDS_SQL_SERVER("AWS RDS SQL Server"),
    /**
     * Google SQL Cloud Postgres
     */
    GOOGLE_SQL_CLOUD_POSTGRES("Google SQL Cloud Postgres"),
    /**
     * Google SQL Cloud MySQL
     */
    GOOGLE_SQL_CLOUD_MYSQL("Google SQL Cloud MySQL"),
    /**
     * MariaDB
     */
    MARIADB("mariadb"),
    /**
     * MariaDB SkySQL
     */
    MARIADB_SKYSQL("MariaDB SkySQL"),
    /**
     * Microsoft SQL Server
     */
    MICROSOFT_SQL_SERVER("Microsoft SQL Server"),
    /**
     * Azure SQL
     */
    AZURE_SQL("Azure SQL"),
    /**
     * Azure MySQL
     */
    AZURE_MYSQL("Azure MySQL"),
    /**
     * CockroachDB
     */
    COCKROACHDB("CockroachDB"),
    /**
     * CSV file
     */
    CSV("CSV"),
    /**
     * TSV file
     */
    TSV("TSV");

    private final String value;

    /**
     * Constructor
     *
     * @param value The value
     */
    Source(String value) {
        this.value = value;
    }

    /**
     * Gets the value
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
}

