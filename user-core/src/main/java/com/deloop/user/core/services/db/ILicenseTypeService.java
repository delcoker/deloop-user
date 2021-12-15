package com.deloop.user.core.services.db;

import com.deloop.user.data.api.requests.LicenseTypeRequest;

public interface ILicenseTypeService {
    void addLicenseType(LicenseTypeRequest licenseTypeRequest);
}