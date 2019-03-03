package com.contact.management.domain.to;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonDeserialize(as = ImmutableContactManagementUser.class)
@JsonSerialize(as = ImmutableContactManagementUser.class)
public interface ContactManagementUser {

    String username();

    default String getUserFileName() {
        return sanitize(username()) + "-" + username().hashCode();
    }

    default String sanitize(String value) {
        return value.replaceAll("[\\W]|_", "");

    }

}
