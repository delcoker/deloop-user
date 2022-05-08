package com.deloop.user.data.config;

import com.deloop.user.data.DBEbeanService;
import com.deloop.user.data.IDBEbeanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ebean.datasource.DataSourceConfig;
import io.ebean.dbmigration.DbMigration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@Import({DBRepositoryConfiguration.class})
public class DBConfiguration {
    @Value("${db.driver}")
    private String db_driver;

    @Value("${db.language}")
    private String db_language;

    @Value("${db.server}")
    private String db_server;

    @Value("${db.port}")
    private short db_port;

    @Value("${db.name}")
    private String db_name;

    @Value("${db.user}")
    private String db_user;

    @Value("${db.password}")
    private String db_password;

    @Value("${db.executeddl}")
    private boolean db_execute_ddl;

    @Bean(name = "dbEbeanService")
    DBEbeanService dbEbeanService(ObjectMapper objectMapper) {
        Map<String, String> configMap = new HashMap<>();
        configMap.put(ServerConfigKeys.DB_DATABASE_SERVER.getLabel(), db_server);
        configMap.put(ServerConfigKeys.DB_DATABASE_NAME.getLabel(), db_name);
        configMap.put(ServerConfigKeys.DB_DATABASE_USER.getLabel(), db_user);
        configMap.put(ServerConfigKeys.DB_DATABASE_PASSWORD.getLabel(), db_password);

        String databaseServer = configMap.get(ServerConfigKeys.DB_DATABASE_SERVER.getLabel());
        String databaseName = configMap.get(ServerConfigKeys.DB_DATABASE_NAME.getLabel());
        String databaseUser = configMap.get(ServerConfigKeys.DB_DATABASE_USER.getLabel());
        String databasePassword = configMap.get(ServerConfigKeys.DB_DATABASE_PASSWORD.getLabel());

        String databaseLanguage = db_language;

        DataSourceConfig dataSource = new DataSourceConfig();
//        dataSource.setDriver(db_driver);
//        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
//        dataSource.setDriver("org.postgresql.Driver");
        dataSource.setUsername(databaseUser);
        dataSource.setPassword(databasePassword);

        String message = "DB Server => " + databaseServer;
        log.warn(message);
        System.err.println(message);

        short databasePort = db_port;
        short connectTimeout = 5000;
        short socketTimeout = 5000;

        String url = String.format("jdbc:" + "%s" + "://" + "%s" + ":" + "%d" + "/" + "%s"
                        + "?characterEncoding=UTF-8&serverTimezone=UTC&autoReconnect=true"
                        + "&useSSL=false&readFromMasterWhenNoSlaves=true"
                        + "&connectTimeout=" + "%d" + "&socketTimeout=" + "%d",
                databaseLanguage, databaseServer, databasePort,
                databaseName, connectTimeout, socketTimeout);

        dataSource.setUrl(url);

        log.info(dataSource.getUrl());
//        dataSource.setUrl("jdbc:postgresql://" + databaseServer + ":" + 5432 + "/" + databaseName);

        dataSource.setReadOnly(false);
        dataSource.setMaxInactiveTimeSecs(1);
        dataSource.setTrimPoolFreqSecs(10);
        dataSource.setLeakTimeMinutes(1);
        dataSource.setMaxConnections(60);
        dataSource.setWaitTimeoutMillis(5000);

        DBEbeanService db = new DBEbeanService(dataSource, databaseName, objectMapper, db_execute_ddl); // allows db changes
        migrateDB(db);
        return db;
    }

//    @Bean("outlooksDatabase")
//    HealthIndicator ebeanHealthIndicator(IEbeanService ebeanService) {
//        DataSource dataSource = ebeanService.getDb().getPluginApi().getDataSource();
//        return new DataSourceHealthIndicator(dataSource);
//    }

    @Value("${env}")
    private String env;

    void migrateDB(IDBEbeanService ebeanService) {
        log.info("env: " + env);
        System.err.println("migrating DB: " + env);
//        String description = description.replace(" ", "_");
//        System.setProperty("ddl.migration.name", description);
        DbMigration dbMigration = DbMigration.create();
        String filePath = "db/" + env;
        dbMigration.setPathToResources(filePath);
        dbMigration.setServer(ebeanService.getDb());
        dbMigration.setStrictMode(false);
        try {
            dbMigration.generateMigration();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
