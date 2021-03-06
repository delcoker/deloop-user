package com.deloop.user.core.services;

import com.deloop.user.core.models.requests.LicenseTypeRequest;
import com.deloop.user.data.db.models.LicenseType;
import com.deloop.user.data.db.repositories.ILicenceTypeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LicenseTypeServiceImpl implements LicenseTypeService {
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
