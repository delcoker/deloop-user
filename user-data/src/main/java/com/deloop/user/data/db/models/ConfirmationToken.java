package com.deloop.user.data.db.models;

import com.deloop.user.data.db.enums.ConfirmationTokenType;
import io.ebean.Model;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.*;
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
public class ConfirmationToken extends Model {
    @Id
    private long id;

    @ManyToOne
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    private User user;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ConfirmationTokenType type;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @Column
    private LocalDateTime confirmedAt;

    @Column
    @WhenCreated
    @DbDefault("2020-04-26 00:00")
    public LocalDateTime createdAt;

    @Column
    @WhenModified
    @DbDefault("2020-04-26 00:00")
    public LocalDateTime updatedAt;
}
