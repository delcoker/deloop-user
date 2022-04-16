package com.deloop.user.data.db.models;

import com.deloop.user.data.db.enums.PermissionStatus;
import io.ebean.Model;
import io.ebean.annotation.DbDefault;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "user_permissions")
public class UserPermission extends Model {
    @Id
    private long id;

    @Column
    @DbDefault("")
    private String name;

    @Column
    @DbDefault("")
    private String code;

    @Column
    @DbDefault("")
    private String description;

    @Enumerated(value = EnumType.STRING)
    private PermissionStatus status;

    @Column
    @WhenCreated
    @DbDefault("2020-04-26 00:00")
    private LocalDateTime createdAt;

    @Column
    @WhenModified
    @DbDefault("2020-04-26 00:00")
    private LocalDateTime updatedAt;

//    @ManyToMany
//    private List<UserType> userTypes;

    @ManyToMany
    private List<UserRole> userRoles;
}
