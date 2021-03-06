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
    @DbDefault("disabled")
    private PermissionStatus status;

//    @ManyToMany
//    private List<UserType> userTypes;

//    @ManyToMany//(cascade = CascadeType.ALL)
//    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "permission")
    private List<UserRolePermission> userRolePermissions;

    @Column
    @WhenCreated
    @DbDefault("2020-04-26 00:00")
    public LocalDateTime createdAt;

    @Column
    @WhenModified
    @DbDefault("2020-04-26 00:00")
    public LocalDateTime updatedAt;
}
