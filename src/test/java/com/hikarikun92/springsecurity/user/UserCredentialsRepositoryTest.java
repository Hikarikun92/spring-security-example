package com.hikarikun92.springsecurity.user;

import org.jooq.DSLContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;

import java.util.Optional;

import static com.hikarikun92.springsecurity.factory.UserCredentialsFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@JooqTest
class UserCredentialsRepositoryTest {
    private UserCredentialsRepository repository;

    @Autowired
    private DSLContext dsl;

    @BeforeEach
    void setup() {
        repository = new UserCredentialsRepository(dsl);
    }

    @Test
    void findByUsernameWith2Roles() {
        assertEquals(Optional.of(getUserCredentials1()), repository.findByUsername("user1"));
    }

    @Test
    void findByUsernameWith1Role() {
        assertEquals(Optional.of(getUserCredentials2()), repository.findByUsername("user2"));
    }

    @Test
    void findByUsernameWithNoRoles() {
        assertEquals(Optional.of(getUserCredentials3()), repository.findByUsername("user3"));
    }

    @Test
    public void findByUsernameNotExisting() {
        final Optional<UserCredentials> optional = repository.findByUsername("non-existing");
        assertTrue(optional.isEmpty());
    }
}
