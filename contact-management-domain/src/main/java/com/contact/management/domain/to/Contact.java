package com.contact.management.domain.to;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/*
 * Created by alysonlv - 2019-03-02
 */
@Value.Immutable(prehash = true)
@JsonDeserialize(as = ImmutableContact.class)
@JsonSerialize(as = ImmutableContact.class)
public interface Contact {

    String phoneNumber();

    String email();

}
