package com.deloop.user.data.api.requests;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class UserTypeRequest {
    private long id;
    private String name;
    private String description;
    private String access;
}
