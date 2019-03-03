package com.contact.management.domain.to;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

/*
 * Created by alysonlv - 2019-03-02
 */
@Value.Immutable(prehash = true)
@JsonDeserialize(as = ImmutableAddress.class)
@JsonSerialize(as = ImmutableAddress.class)
public interface Address {

    @Nullable
    String street();

    @Nullable
    String city();

    @Nullable
    String country();
}
