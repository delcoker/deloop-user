package com.deloop.user.data.db.models;

import com.deloop.user.data.db.enums.RoleStatus;
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
@Table(name = "user_roles")
public class UserRole extends Model {
    @Id
    private long id;

    @Column
    @DbDefault("")
    private String name;

    @Column(columnDefinition = "TEXT")
    @DbDefault("")
    private String description;

    @Column(columnDefinition = "TEXT")
    @DbDefault("")
    private String capabilities;

    @Enumerated(value = EnumType.STRING)
    private RoleStatus status;

    @OneToMany(mappedBy = "userRole")
    private List<User> users;

//    @ManyToMany(mappedBy = "userRoles")
//    private List<UserPermission> userPermissions;

    @OneToMany(mappedBy = "role")
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
