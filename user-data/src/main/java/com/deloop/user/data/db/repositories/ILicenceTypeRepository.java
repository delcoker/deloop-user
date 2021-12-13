package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.LicenseType;

import java.util.Optional;

public interface ILicenceTypeRepository {

    Optional<LicenseType> findBy(long id);

    Optional<LicenseType> findBy(String name);

    LicenseType save(LicenseType licenseType);

    LicenseType update(LicenseType licenseType);

    boolean delete(long LicenseTypeId);
}
