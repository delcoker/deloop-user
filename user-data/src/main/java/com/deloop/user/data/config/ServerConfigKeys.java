package com.deloop.user.data.config;

import lombok.Getter;

@Getter
public enum ServerConfigKeys {
    DB_DATABASE_SERVER("dbDatabaseServer"),
    DB_DATABASE_NAME("dbDatabaseName"),
    DB_DATABASE_USER("dbDatabaseUser"),
    DB_DATABASE_PASSWORD("dbDatabasePassword"),
//    HIGHCHARTS_SERVER_URL("highchartsServerUrl"),
//    HIGHCHARTS_API_USER("highchartsApiUser"),
//    HIGHCHARTS_API_PASSWORD("highchartsApiPassword"),
//    API_AUTH_USER("apiAuthUser"),
//    S3_ACCESS_KEY("s3AccessKey"),
//    S3_SECRET_KEY("s3SecretKey"),
//    FULL_PAGE_ROOT_PATH("fullPageRootPath"),
    ;

    final String label;

    ServerConfigKeys(String label) {
        this.label = label;
    }
}