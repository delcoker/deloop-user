package com.deloop.user.data.db.repositories;

import com.deloop.user.data.db.models.LicenseType;
import com.deloop.user.data.db.models.query.QLicenseType;
import io.ebean.Database;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class LicenseTypeRepository implements ILicenceTypeRepository {
    private final Database db;

    @Override
    public Optional<LicenseType> findBy(long id) {
        return Optional.ofNullable(new QLicenseType(db).id.eq(id).findOne());
    }

    @Override
    public Optional<LicenseType> findBy(String name) {
        return Optional.ofNullable(new QLicenseType(db).name.eq(name).findOne());
    }

    @Override
    public LicenseType save(LicenseType LicenseType) {
        db.save(LicenseType);
        return LicenseType;
    }

    @Override
    public LicenseType update(LicenseType LicenseType) {
        Optional<LicenseType> existing = findBy(LicenseType.getId());
        if (existing.isEmpty()) {
            String message = "License with id " + LicenseType.getId() + " does not exist!";
            log.info(message);
            throw new IllegalStateException(message);
        }
        db.update(LicenseType);
        return LicenseType;
    }

    @Override
    public boolean delete(long licenseTypeId) {
        if (db.delete(LicenseType.builder().id(licenseTypeId).build())) {
            return true;
        }
        String message = "License with id " + licenseTypeId + " does not exist!";
        log.error(message);
        return false;
    }
}
