package com.deloop.user.core.services;

import com.deloop.user.core.models.requests.LicenseTypeRequest;

public interface ILicenseTypeService {
    void addLicenseType(LicenseTypeRequest licenseTypeRequest);
}