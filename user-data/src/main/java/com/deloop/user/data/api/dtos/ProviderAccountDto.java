package com.deloop.user.data.api.dtos;


import java.io.Serializable;

public record ProviderAccountDto(long id, String provider, String profileLink) implements Serializable {
}
