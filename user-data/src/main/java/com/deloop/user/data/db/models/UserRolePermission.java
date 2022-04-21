package com.deloop.user.data.db.models;

import io.ebean.Model;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Builder
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
}
