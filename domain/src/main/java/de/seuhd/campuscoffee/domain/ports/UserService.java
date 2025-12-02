package de.seuhd.campuscoffee.domain.ports;

import de.seuhd.campuscoffee.domain.exceptions.DuplicationException;
import de.seuhd.campuscoffee.domain.exceptions.NotFoundException;
import de.seuhd.campuscoffee.domain.model.User;
import org.jspecify.annotations.NonNull;

import java.util.List;

/**
 * Service interface for user operations.
 * This interface defines the core business logic operations for managing users.
 * This is a port in the hexagonal architecture pattern, implemented by the domain layer
 * and consumed by the API layer. It encapsulates business rules and orchestrates
 * data operations through the {@link UserDataService} port.
 */
public interface UserService {
    /**
     * Clears all user data.
     * This operation removes all users from the system.
     * Warning: This is a destructive operation typically used only for testing
     * or administrative purposes. Use with caution in production environments.
     */
    void clear();

    /**
     * Retrieves all users in the system.
     *
     * @return a list of all user entities; never null, but may be empty if no users exist
     */
    @NonNull List<User> getAll();

    /**
     * Retrieves a specific user by their unique identifier.
     *
     * @param id the unique identifier of the user to retrieve; must not be null
     * @return the user entity with the specified ID; never null
     * @throws NotFoundException if no user exists with the given ID
     */
    @NonNull User getById(@NonNull Long id);

    /**
     * Retrieves a specific user by their unique login name.
     *
     * @param loginName the unique login name of the user to retrieve; must not be null
     * @return the user entity with the specified login name; never null
     * @throws NotFoundException if no user exists with the given login name
     */
    @NonNull User getByLoginName(@NonNull String loginName);

    /**
     * Creates a new user or updates an existing one.
     * This method performs an "upsert" operation:
     * <ul>
     *   <li>If the user has no ID (null), a new user is created</li>
     *   <li>If the user has an ID, and it exists, the existing user is updated</li>
     * </ul>
     * <p>
     * Business rules enforced:
     * <ul>
     *   <li>Login names must be unique (enforced by database constraint)</li>
     *   <li>Email addresses must be unique (enforced by database constraint)</li>
     *   <li>All required fields must be present and valid</li>
     *   <li>Timestamps (createdAt, updatedAt) are managed by the {@link UserDataService}.</li>
     * </ul>
     *
     * @param user the user entity to create or update; must not be null
     * @return the persisted user entity with populated ID and timestamps; never null
     * @throws NotFoundException if attempting to update a user that does not exist
     * @throws DuplicationException if a user with the same login name or email address already exists
     */
    @NonNull User upsert(@NonNull User user);

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id the unique identifier of the user to delete; must not be null
     * @throws NotFoundException if no user exists with the given ID
     */
    void delete(@NonNull Long id);
}
