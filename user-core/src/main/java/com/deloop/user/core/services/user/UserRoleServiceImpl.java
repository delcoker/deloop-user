package com.deloop.user.core.services.user;

import com.deloop.user.core.models.requests.UserRoleRequest;
import com.deloop.user.data.db.models.UserRole;
import com.deloop.user.data.db.repositories.IUserRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {
    public final IUserRoleRepository userRoleRepository;

    @Override
    public void addUserRole(UserRoleRequest userRoleRequest) {
        UserRole userRole = UserRole.builder()
                .name(userRoleRequest.getName())
                .description(userRoleRequest.getDescription())
                .capabilities(userRoleRequest.getCapabilities())
                .status(userRoleRequest.getStatus())
                .build();
        userRoleRepository.save(userRole);
    }

    @Override
    public Optional<UserRole> getUserRoleById(UserRoleRequest userRoleRequest) {
        return userRoleRepository.findBy(userRoleRequest.getId());
    }

    @Override
    public Optional<UserRole> getUserRoleByName(UserRoleRequest userRoleRequest) {
        return userRoleRepository.findByName(userRoleRequest.getName());
    }
}
