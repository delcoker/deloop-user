package com.deloop.user.data.api.dtos;


import java.io.Serializable;
import java.util.Objects;

public final class ProviderAccountDto implements Serializable {
    private final long id;
    private final String provider;
    private final String profileLink;

    public ProviderAccountDto(long id, String provider, String profileLink) {
        this.id = id;
        this.provider = provider;
        this.profileLink = profileLink;
    }

    public long id() {
        return id;
    }

    public String provider() {
        return provider;
    }

    public String profileLink() {
        return profileLink;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        ProviderAccountDto that = (ProviderAccountDto) obj;
        return this.id == that.id &&
                Objects.equals(this.provider, that.provider) &&
                Objects.equals(this.profileLink, that.profileLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, provider, profileLink);
    }

    @Override
    public String toString() {
        return "ProviderAccountDto[" +
                "id=" + id + ", " +
                "provider=" + provider + ", " +
                "profileLink=" + profileLink + ']';
    }

}
