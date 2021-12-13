package com.deloop.user.core.services;

import com.deloop.user.data.api.requests.UserTypeRequest;
import com.deloop.user.data.db.repositories.IUserTypeRepository;
import com.deloop.user.data.db.models.UserType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserTypeServiceImpl implements IUserTypeService {
    public final IUserTypeRepository userTypeRepository;

    @Override
    public void addUserType(UserTypeRequest userTypeRequest) {
        UserType userType = UserType.builder()
                .name(userTypeRequest.getName())
                .access(userTypeRequest.getAccess())
                .description(userTypeRequest.getDescription())
                .build();
        userTypeRepository.save(userType);
    }
}
