package com.deloop.user.data.db.models;

import com.deloop.user.data.db.enums.LicenseStatus;
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
@ToString
@Builder
@Entity
@Table(name = "license_types")
public class LicenseType extends Model {
    @Id
    private long id;

    @Column
    private String name;

    @Column
    @DbDefault("")
    private String description;

    @Column
    @DbDefault("")
    private String access;

    @Enumerated(value = EnumType.STRING)
    private LicenseStatus status;

    @OneToMany(mappedBy = "licenseType")
    private List<User> users;

    @Column
    @WhenCreated
    @DbDefault("2020-04-26 00:00")
    public LocalDateTime createdAt;

    @Column
    @WhenModified
    @DbDefault("2020-04-26 00:00")
    public LocalDateTime updatedAt;
}
