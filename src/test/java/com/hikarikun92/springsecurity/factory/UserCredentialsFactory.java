package com.hikarikun92.springsecurity.factory;

import com.hikarikun92.springsecurity.user.UserCredentials;

import static com.hikarikun92.springsecurity.factory.UserFactory.*;

public final class UserCredentialsFactory {
    private UserCredentialsFactory() {
        throw new AssertionError();
    }

    public static UserCredentials getUserCredentials1() {
        return new UserCredentials.Builder()
                .setUser(getUser1())
                .setPassword("password1")
                .build();
    }

    public static UserCredentials getUserCredentials2() {
        return new UserCredentials.Builder()
                .setUser(getUser2())
                .setPassword("password2")
                .build();
    }

    public static UserCredentials getUserCredentials3() {
        return new UserCredentials.Builder()
                .setUser(getUser3())
                .setPassword("password3")
                .build();
    }
}
