package com.deloop.user.data.config;

import com.deloop.user.data.IDBEbeanService;
import com.deloop.user.data.db.repositories.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBRepositoryConfiguration {

    @Bean
    IConfirmationTokenRepository confirmationTokenRepository(@Qualifier("dbEbeanService") IDBEbeanService dbEbeanService) {
        return new ConfirmationTokenRepository(dbEbeanService.getDb());
    }

    @Bean
    IUserRepository userRepository(@Qualifier("dbEbeanService") IDBEbeanService dbEbeanService) {
        return new UserRepository(dbEbeanService.getDb());
    }

    @Bean
    IUserRoleRepository userRoleRepository(@Qualifier("dbEbeanService") IDBEbeanService dbEbeanService) {
        return new UserRoleRepository(dbEbeanService.getDb());
    }

//    @Bean
//    IUserTypeRepository userTypeRepository(@Qualifier("dbEbeanService") IDBEbeanService dbEbeanService) {
//        return new UserTypeRepository(dbEbeanService.getDb());
//    }

    @Bean
    IAddressRepository addressRepository(@Qualifier("dbEbeanService") IDBEbeanService dbEbeanService) {
        return new AddressRepository(dbEbeanService.getDb());
    }

    @Bean
    ILicenceTypeRepository licenceTypeRepository(@Qualifier("dbEbeanService") IDBEbeanService dbEbeanService) {
        return new LicenseTypeRepository(dbEbeanService.getDb());
    }

    @Bean
    IProviderAccountRepository providerAccountRepository(@Qualifier("dbEbeanService") IDBEbeanService dbEbeanService) {
        return new ProviderAccountRepository(dbEbeanService.getDb());
    }

    @Bean
    IUserDetailsRepository userDetailsRepository(@Qualifier("dbEbeanService") IDBEbeanService dbEbeanService) {
        return new UserDetailsRepository(dbEbeanService.getDb());
    }

    @Bean
    IUserPermissionRepository userPermissionRepository(@Qualifier("dbEbeanService") IDBEbeanService dbEbeanService) {
        return new UserPermissionRepository(dbEbeanService.getDb());
    }
}
