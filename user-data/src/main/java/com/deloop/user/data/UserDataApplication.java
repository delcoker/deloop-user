package com.deloop.user.data;


import com.deloop.user.data.api.requests.UserPermissionRequest;
import com.deloop.user.data.api.requests.UserRequest;
import com.deloop.user.data.api.requests.UserRoleRequest;
import com.deloop.user.data.db.enums.RoleStatus;
import com.deloop.user.data.db.models.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class} /* purpose of : (exclude = {DataSourceAutoConfiguration.class})*/)
public class UserDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDataApplication.class, args);
    }

}
