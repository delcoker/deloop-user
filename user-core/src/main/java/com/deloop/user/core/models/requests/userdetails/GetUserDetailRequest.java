package com.deloop.user.core.models.requests.userdetails;

import lombok.Builder;
import lombok.Getter;

@Getter
//@Setter
@Builder
//@ToString
//@AllArgsConstructor
public class GetUserDetailRequest {
    @Builder.Default
    private long userId = -1;
}
