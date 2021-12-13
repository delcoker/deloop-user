package com.deloop.user.data;

import com.deloop.user.data.db.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.config.DbConstraintNaming;
import io.ebean.config.MatchingNamingConvention;
import io.ebean.datasource.DataSourceConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBEbeanService implements IDBEbeanService {

    @Getter
    private final Database db;

//    public DBEbeanService(DataSourceConfig dataSource, String databaseName, ObjectMapper objectMapper) {
//        db = create(dataSource, databaseName, objectMapper, true);
//    }

    public DBEbeanService(DataSourceConfig dataSource, String databaseName, ObjectMapper objectMapper, boolean executeDdl) {
        db = create(dataSource, databaseName, objectMapper, executeDdl);
    }

    private Database create(DataSourceConfig dataSource, String databaseName, ObjectMapper objectMapper, boolean executeDdl) {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setName(databaseName);
        databaseConfig.setDataSourceConfig(dataSource);
        databaseConfig.addPackage("com.deloop.user.data.db.models"); // is this really needed?
        registerEntityBeans(databaseConfig);
        databaseConfig.setNamingConvention(new MatchingNamingConvention());
        databaseConfig.setConstraintNaming(new DbConstraintNaming(false));
        databaseConfig.setDefaultServer(true);
        databaseConfig.setRegister(true);
        databaseConfig.setDdlGenerate(executeDdl);
        databaseConfig.setDdlRun(executeDdl);
        databaseConfig.setObjectMapper(objectMapper);
        databaseConfig.setPersistBatchSize(500);
        return DatabaseFactory.create(databaseConfig);
    }

    private void registerEntityBeans(DatabaseConfig databaseConfig) {
        databaseConfig.addClass(User.class);
        databaseConfig.addClass(UserDetail.class);
        databaseConfig.addClass(UserType.class);
        databaseConfig.addClass(UserPermission.class);
        databaseConfig.addClass(LicenseType.class);
        databaseConfig.addClass(ProviderAccount.class);
        databaseConfig.addClass(UserRole.class);
        databaseConfig.addClass(Address.class);
    }
}
