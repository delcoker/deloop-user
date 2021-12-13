package com.deloop.user.data.auth.session;//
//package com.deloop.user.data.auth.session;
//
//import com.deloop.user.data.db.models.User;
//import com.deloop.user.data.util.DateTimeUtil;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//import static java.time.temporal.ChronoUnit.MINUTES;
//
///**
// * Data stored in {@link User}'s session.
// */
//@Getter
//@Setter
//@Builder
//@EqualsAndHashCode
//@NoArgsConstructor
//public class Session {
//
//    public static final int DEFAULT_EXPIRATION_MINUTES = 30 * 24 * 60;
//
//    private long id;
//    private long userId;
//
//    private String token;
//
//    private LocalDateTime expiresAt;
//    private LocalDateTime issuedAt;
//    private LocalDateTime lastUsedAt;
//    private LocalDateTime removedAt;
//
//    private boolean deleted;
//
//    /**
//     * Creates a new {@link Session} with the given ID for the given User ID.
//     *
//     * @param id     Session ID
//     * @param userId User ID
//     */
//    public Session(Long id, Long userId, String token) {
//        this(id, userId, token, DEFAULT_EXPIRATION_MINUTES);
//    }
//
//    /**
//     * Creates a new {@link Session} with the given ID for the given User ID.
//     *
//     * @param id      Session ID
//     * @param userId  User ID
//     * @param token   Token's token
//     * @param minutes minutes to expire from time of issue
//     */
//    public Session(Long id, Long userId, String token, int minutes) {
//        this.id = id;
//        this.userId = userId;
//        this.token = token;
//        if (minutes == 0) {
//            minutes = DEFAULT_EXPIRATION_MINUTES;
//        }
//        expiresAt = DateTimeUtil.expireNowUtc(minutes, MINUTES);
//        issuedAt = DateTimeUtil.nowUtc();
//    }
//
//    /**
//     * Use for testing only.
//     *
//     * @param id        Session ID
//     * @param userId    User ID
//     * @param token     Token's token
//     * @param expiresAt expire at
//     * @param issuedAt  issued at
//     */
//    public Session(Long id, Long userId, String token, LocalDateTime expiresAt, LocalDateTime issuedAt) {
//
//        this.id = id;
//        this.userId = userId;
//        this.token = token;
//        this.expiresAt = expiresAt;
//        this.issuedAt = issuedAt;
//    }
//
//    //      @Override
//    public Long getId() {
//        return id;
//    }
//
//    /**
//     * Returns is this session if still valid.
//     *
//     * @return true if valid, false otherwise
//     */
//    public boolean isValid() {
//        LocalDateTime now = DateTimeUtil.nowUtc();
//        return isValid(now);
//    }
//
//    public boolean isValid(LocalDateTime now) {
//        return expiresAt.isAfter(now) && !deleted;
//    }
//
//    //  @Override
//    public boolean sameIdentityAs(Session other) {
//        return false;
//    }
//
//    public void setDeleted(boolean deleted) {
//        this.deleted = deleted;
//        this.removedAt = deleted ? DateTimeUtil.nowUtc() : null;
//    }
//
//    //  @Override
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Override
//    public String toString() {
//        return "Session : " +
//                "userId : " + userId +
//                "id : " + id;
//    }
//}
