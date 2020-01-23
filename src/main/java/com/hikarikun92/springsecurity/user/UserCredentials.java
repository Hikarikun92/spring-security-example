package com.hikarikun92.springsecurity.user;

import com.hikarikun92.springsecurity.util.ObjectBuilder;
import org.springframework.util.Assert;

import java.util.Objects;

public final class UserCredentials {
    private final User user;
    private final String password;

    private UserCredentials(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredentials that = (UserCredentials) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, password);
    }

    public static final class Builder implements ObjectBuilder {
        private User user;
        private String password;

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        @Override
        public UserCredentials build() {
            Assert.notNull(user, "User must not be null");
            Assert.notNull(password, "Password must not be null");

            return new UserCredentials(user, password);
        }
    }
}
