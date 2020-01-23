package com.hikarikun92.springsecurity.user;

import com.hikarikun92.springsecurity.util.ObjectBuilder;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.Set;

import static com.hikarikun92.springsecurity.util.ApplicationUtils.quote;

public final class User {
    private final Integer id;
    private final String username;
    private final Set<String> roles;

    private User(Integer id, String username, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, roles);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username=" + quote(username) +
                ", roles=" + roles +
                '}';
    }

    public static final class Builder implements ObjectBuilder {
        private Integer id;
        private String username;
        private Set<String> roles;

        public Builder setId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setRoles(Set<String> roles) {
            this.roles = roles;
            return this;
        }

        @Override
        public User build() {
            Assert.isTrue(id == null || id > 0, "ID must be null or greater than 0");
            Assert.notNull(username, "Username must not be null");
            Assert.notNull(roles, "Roles must not be null");

            return new User(id, username, roles);
        }
    }
}
