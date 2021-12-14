package com.deloop.user.data.db.models;

import com.deloop.user.data.db.enums.ConfirmationTokenType;
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
@Table(name = "confirmation_tokens")
public class ConfirmationToken {
    @Id
    private long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ConfirmationTokenType type;

    @Column(nullable = false)
    @WhenCreated
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column
    private LocalDateTime confirmedAt;

    @ManyToOne
    private User user;
}
