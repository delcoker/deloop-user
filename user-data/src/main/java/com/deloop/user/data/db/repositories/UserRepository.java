package com.deloop.user.data.db.repositories;

import com.deloop.user.data.api.dtos.UserDto;
import com.deloop.user.data.db.models.User;
import com.deloop.user.data.exceptions.NoSuchUserException;

import java.util.Optional;

/**
 * Repository for managing the lifecycle of {@link User} entities.
 */
public interface UserRepository {

    /**
     * Deletes the given user, provided that it exists.
     *
     * @param userId {@link User}'s ID
     * @return
     * @throws NoSuchUserException if the user doesn't exist
     */
    boolean delete(long userId) throws IllegalStateException;

    /**
     * Finds a user based on its ID.
     *
     * @param id ID
     * @return a {@link User}
     */
    Optional<UserDto> findById(long id);

    /**
     * Finds a user by email address.
     *
     * @param email Email address
     * @return a {@link User}
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds a user by username.
     *
     * @param userName the username
     * @return a {@link User}
     */
    Optional<User> findByUserName(String userName);

    /**
     * Stores the given user.
     *
     * @param user a {@link User}
     * @return the stored {@link User}
     */
    void save(User user);

    /**
     * Updates the given user.
     *
     * @param user a {@link User}
     * @return the stored {@link User}
     */
    User update(User user);

    int verifyUser(String email);
}
