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
@Builder
@ToString
@Entity
@Table(name = "provider_accounts")
public class ProviderAccount extends Model {
    @Id
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Column
    @DbDefault("")
    private String provider;

    @Column
    @DbDefault("")
    private String profileLink;

    @Column
    @WhenCreated
    @DbDefault("2020-04-26 00:00")
    public LocalDateTime createdAt;

    @Column
    @WhenModified
    @DbDefault("2020-04-26 00:00")
    public LocalDateTime updatedAt;
}
