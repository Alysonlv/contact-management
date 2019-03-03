package com.contact.management.domain.to;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.annotation.Nullable;

/*
 * Created by alysonlv - 2019-03-02
 */
@Value.Immutable(prehash = true)
@JsonDeserialize(as = ImmutableContactRecord.class)
@JsonSerialize(as = ImmutableContactRecord.class)
public abstract class ContactRecord {

    @Nullable
    public abstract Integer id();

    public abstract String firstName();

    public abstract String lastName();

    public abstract Contact personalContact();

    @Nullable
    public abstract Contact businessContact();

    @Nullable
    public abstract Address address();

    public ContactRecord defineId() {
        return ImmutableContactRecord
                .builder()
                .firstName(this.firstName())
                .lastName(this.lastName())
                .personalContact(this.personalContact())
                .businessContact(this.businessContact())
                .id(this.hashCode())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof ContactRecord) {
            return this.id() != null && this.id().intValue() == ((ContactRecord)o).id() && ((ContactRecord)o).id() != null;
        } else {
            return false;
        }

    }

}
