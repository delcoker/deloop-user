package com.deloop.user.data.db.models;

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

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@Entity
@Table(name = "user_role_permissions")
public class UserRolePermission extends Model {
    @Id
    private long id;

    @Column
    @ManyToOne
    private UserRole role;

    @Column
    @ManyToOne
    private UserPermission permission;

    @Column
    @WhenCreated
    @DbDefault("2020-04-26 00:00")
    public LocalDateTime createdAt;

    @Column
    @WhenModified
    @DbDefault("2020-04-26 00:00")
    public LocalDateTime updatedAt;
}
