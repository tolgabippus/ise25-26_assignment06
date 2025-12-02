package de.seuhd.campuscoffee.domain.model;

import lombok.Builder;

import java.io.Serial;
import java.io.Serializable;

@Builder(toBuilder = true)
public record User (
        Long id,
        java.time.LocalDateTime createdAt,
        java.time.LocalDateTime updatedAt,
        String loginName,
        String emailAddress,
        String firstName,
        String lastName
) implements Serializable { // serializable to allow cloning (see TestFixtures class).
    @Serial
    private static final long serialVersionUID = 1L;
}
