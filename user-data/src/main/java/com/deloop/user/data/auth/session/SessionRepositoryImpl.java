package com.deloop.user.data.auth.session;//package com.deloop.user.data.auth.session;
//
//import com.deloop.user.data.auth.security.JwtTokenService;
//import io.lettuce.core.RedisFuture;
//import io.lettuce.core.api.async.RedisAsyncCommands;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.*;
//import java.util.concurrent.ExecutionException;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//
///**
// * Created by delcoker on 26/04/2020.
// */
////@Transactional
//@Slf4j
//@RequiredArgsConstructor
//public class SessionRepositoryImpl implements SessionRepository {
//    private final RedisAsyncCommands<String, String> redisAsyncCommands;
//    private final JwtTokenService jwtTokenService;
//
//    @Override
//    public Optional<Session> findById(String userId, String sessionId) {
//
////        Map parameters = Stream.of(entry("sessionId", id)).collect(entriesToMap());
////        return doFindSession(FIND_BY_SESSION_ID_QUERY, parameters);
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Session> findByUserId(String userId) {
//        RedisFuture<String> redisFuture = redisAsyncCommands.get(userId);
//
//        return IntStream.range(0, 10)
//                .mapToObj(val -> "Session : userId : " + userId + " : " + "id :" + val)
//                .map(sessionKey -> getSession(userId))
//                .filter(Objects::nonNull)
//                .map(this::convertSessionStr)
//                .collect(Collectors.toList());
//    }
//
//    private String getSession(String userId) {
//        try {
//            return redisAsyncCommands.get(userId).get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private Session convertSessionStr(String redisSession) {
//        List<String> sessionList = Arrays.stream(redisSession.split(":"))
//                .map(String::trim)
//                .collect(Collectors.toList());
//
//        return Session.builder()
//                .id(Long.parseLong(sessionList.get(4)))
//                .userId(Long.parseLong(sessionList.get(2)))
//                .build();
//    }
//
//    @Override
//    public Session save(Session session) {
//        if (redisAsyncCommands.get(session.toString()) == null) {
////            redisAsyncCommands.set(session.toString(), jwtTokenService.createJwtToken())
//            return session;
//        }
//
//        return session;
//    }
//
//    Optional<Session> doFindSession(String queryName, Map<String, ?> parameters) {
////        TypedQuery<Session> sessionQuery = entityManager.createNamedQuery(queryName, Session.class);
////        parameters.forEach(sessionQuery::setParameter);
////        try {
////            return Optional.of(sessionQuery.getSingleResult());
////        } catch (NoResultException nre) {
////            LOGGER.debug(nre.getMessage(), nre);
////            return Optional.empty();
////        }
//        return Optional.empty();
//    }
//
//}
