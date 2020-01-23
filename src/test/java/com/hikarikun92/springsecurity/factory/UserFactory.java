package com.hikarikun92.springsecurity.factory;

import com.hikarikun92.springsecurity.user.User;

import java.util.Set;

public final class UserFactory {
    private UserFactory() {
        throw new AssertionError();
    }

    public static User getUser1() {
        return new User.Builder()
                .setId(1)
                .setUsername("user1")
                .setRoles(Set.of("ROLE_1", "ROLE_2"))
                .build();
    }

    public static User getUser2() {
        return new User.Builder()
                .setId(2)
                .setUsername("user2")
                .setRoles(Set.of("ROLE_1"))
                .build();
    }

    public static User getUser3() {
        return new User.Builder()
                .setId(3)
                .setUsername("user3")
                .setRoles(Set.of())
                .build();
    }
}
