package com.deloop.user.core.services;

import com.deloop.user.data.api.requests.LicenseTypeRequest;
import com.deloop.user.data.api.requests.UserRoleRequest;
import com.deloop.user.data.db.models.LicenseType;
import com.deloop.user.data.db.models.UserRole;
import com.deloop.user.data.db.repositories.ILicenceTypeRepository;
import com.deloop.user.data.db.repositories.IUserRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class LicenseTypeServiceImpl implements ILicenseTypeService {
    public final ILicenceTypeRepository licenceTypeRepository;

    @Override
    public void addLicenseType(LicenseTypeRequest licenseTypeRequest) {
        LicenseType licenseType = LicenseType.builder()
                .access(licenseTypeRequest.getAccess())
                .description(licenseTypeRequest.getDescription())
                .name(licenseTypeRequest.getName())
                .status(licenseTypeRequest.getStatus())
                .build();
        licenceTypeRepository.save(licenseType);
    }
}
