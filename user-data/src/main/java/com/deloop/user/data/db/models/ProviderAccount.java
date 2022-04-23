package com.deloop.user.data.db.models;

import io.ebean.Model;
import io.ebean.annotation.DbDefault;
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
}
