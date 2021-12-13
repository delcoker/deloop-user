package com.deloop.user.data.auth.session;//package com.deloop.user.data.auth.session;
//
//import java.util.List;
//import java.util.Optional;
//
///**
// * Repository for managing the lifecycle of {@link Session} entities.
// */
//public interface SessionRepository {
//
//    /**
//     * Finds a session based on its UerId and ID.
//     *
//     * @param id ID
//     * @return a {@link Session}
//     */
//    Optional<Session> findById(String userId, String sessionId);
//
//    /**
//     * Finds all the sessions belonging to the given User ID.
//     *
//     * @param userId ID
//     * @return a {@link Session}
//     */
//    List<Session> findByUserId(String userId);
//
//    /**
//     * Stores the given session.
//     *
//     * @param session a {@link Session}
//     * @return the stored {@link Session}
//     */
//    Session save(Session session);
//}
