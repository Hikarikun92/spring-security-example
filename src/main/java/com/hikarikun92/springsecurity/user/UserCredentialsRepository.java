package com.hikarikun92.springsecurity.user;

import com.hikarikun92.springsecurity.persistence.jooq.tables.records.UserCredentialsRecord;
import com.hikarikun92.springsecurity.persistence.jooq.tables.records.UserRecord;
import com.hikarikun92.springsecurity.persistence.jooq.tables.records.UserRolesRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

import static com.hikarikun92.springsecurity.persistence.jooq.tables.User.USER;
import static com.hikarikun92.springsecurity.persistence.jooq.tables.UserCredentials.USER_CREDENTIALS;
import static com.hikarikun92.springsecurity.persistence.jooq.tables.UserRoles.USER_ROLES;

@Repository
public class UserCredentialsRepository {
    private final DSLContext dsl;

    public UserCredentialsRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public Optional<UserCredentials> findByUsername(String username) {
        final Result<Record> result = dsl.select()
                .from(USER_CREDENTIALS)
                .join(USER).on(USER_CREDENTIALS.USER_ID.eq(USER.ID))
                .leftJoin(USER_ROLES).on(USER_ROLES.USER_ID.eq(USER.ID))
                .where(USER.USERNAME.eq(username))
                .fetch();

        if (result.isEmpty()) {
            return Optional.empty();
        }

        final Record first = result.get(0);

        final UserRecord userRecord = first.into(USER);
        final Set<String> roles = getRoles(result);

        final User user = new User.Builder()
                .setId(userRecord.getId())
                .setUsername(username)
                .setRoles(roles)
                .build();

        final UserCredentialsRecord credentialsRecord = first.into(USER_CREDENTIALS);

        final UserCredentials credentials = new UserCredentials.Builder()
                .setUser(user)
                .setPassword(credentialsRecord.getPassword())
                .build();

        return Optional.of(credentials);
    }

    private Set<String> getRoles(Result<Record> result) {
        final UserRolesRecord firstRole = result.get(0).into(USER_ROLES);

        final Set<String> roles;
        if (firstRole.getUserId() == null) { //This should be set if there is a record
            roles = Set.of();
        } else {
            roles = result.into(USER_ROLES).intoSet(UserRolesRecord::getRoles);
        }
        return roles;
    }
}
