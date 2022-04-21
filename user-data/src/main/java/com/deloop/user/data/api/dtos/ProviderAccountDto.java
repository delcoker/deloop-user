package com.deloop.user.data.api.dtos;


import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class ProviderAccountDto implements Serializable {
    @Builder.Default
    private long id = -1;

    @Builder.Default
    private String provider = "";

    @Builder.Default
    private String profileLink = "";
}
