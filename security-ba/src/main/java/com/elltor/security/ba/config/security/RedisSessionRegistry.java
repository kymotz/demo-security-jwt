package com.elltor.security.ba.config.security;

import com.elltor.security.ba.entity.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="kim:username?username=liuqichun03">刘启春(liuqichun03)</a>
 * @since 2022/7/29
 */
@Slf4j
@Component
public class RedisSessionRegistry implements SessionRegistry {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * k:string, Set<sessionIdSet>
     */
    public static final String PREFIX_PRINCIPAL = "BA:PRINCIPALS:";

    /**
     * k:string, Object
     */
    public static final String PREFIX_SESSION = "BA:SESSIONIDS:";

    /** <principal:Object,SessionIdSet> */
//    private final ConcurrentMap<Object, Set<String>> principals;

    /**
     * <sessionId:Object,SessionInformation>
     */
//    private final Map<String, SessionInformation> sessionIds;
    public List<Object> getAllPrincipals() {
//        return new ArrayList<>(principals.keySet());
        Set<String> keys = redisTemplate.keys(PREFIX_PRINCIPAL + "*");
        if (keys == null) {
            return Collections.emptyList();
        }
        List<Object> reals = new ArrayList<>(keys.size());
        keys.forEach(s -> {
            if (s == null) return;
            reals.add(s.replace(PREFIX_PRINCIPAL, ""));
        });
        return reals;
    }

    public List<SessionInformation> getAllSessions(Object principal,
                                                   boolean includeExpiredSessions) {
//        final Set<String> sessionsUsedByPrincipal = principals.get(principal);
        Set<String> sessionsUsedByPrincipal = (Set<String>) redisTemplate.opsForValue().get(PREFIX_PRINCIPAL + ((LoginUser) principal).getUsername());

        if (sessionsUsedByPrincipal == null) {
            return Collections.emptyList();
        }

        List<SessionInformation> list = new ArrayList<>(
                sessionsUsedByPrincipal.size());

        for (String sessionId : sessionsUsedByPrincipal) {
            SessionInformation sessionInformation = getSessionInformation(sessionId);

            if (sessionInformation == null) {
                continue;
            }

            if (includeExpiredSessions || !sessionInformation.isExpired()) {
                list.add(sessionInformation);
            }
        }

        return list;
    }

    public SessionInformation getSessionInformation(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        log.info("============ " + PREFIX_SESSION + sessionId);
        Boolean has = redisTemplate.hasKey(PREFIX_SESSION + sessionId);
        if (!has) {
            log.info("============ sessionId 值不存在 ：" + sessionId);
            return null;
        }
        return (SessionInformation) redisTemplate.opsForValue().get(PREFIX_SESSION + sessionId);
    }

    public void onApplicationEvent(SessionDestroyedEvent event) {
        String sessionId = event.getId();
        removeSessionInformation(sessionId);
    }

    public void refreshLastRequest(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");

        SessionInformation info = getSessionInformation(sessionId);

        if (info != null) {
            info.refreshLastRequest();
        }
    }

    public void registerNewSession(String sessionId, Object principal) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        Assert.notNull(principal, "Principal required as per interface contract");
        LoginUser loginUserPrincipal = (LoginUser) principal;
        if (getSessionInformation(sessionId) != null) {
            removeSessionInformation(sessionId);
        }

        if (log.isDebugEnabled()) {
            log.debug("Registering session " + sessionId + ", for principal "
                    + principal);
        }

//        sessionIds.put(sessionId, new SessionInformation(principal, sessionId, new Date()));
        redisTemplate.opsForValue().set(PREFIX_SESSION + sessionId, new SessionInformation(principal, sessionId, new Date()), 1, TimeUnit.HOURS);

        Set<String> oldSessionsUsedByPrincipal = (Set<String>) redisTemplate.opsForValue().get(PREFIX_PRINCIPAL + loginUserPrincipal.getUsername());
        if (oldSessionsUsedByPrincipal == null) {
            oldSessionsUsedByPrincipal = new CopyOnWriteArraySet();
        }

        oldSessionsUsedByPrincipal.add(sessionId);

        redisTemplate.opsForValue().set(PREFIX_PRINCIPAL + loginUserPrincipal.getUsername(), oldSessionsUsedByPrincipal, 1, TimeUnit.HOURS);
    }

    public void removeSessionInformation(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        log.info("删除 sessionId ：" + sessionId);

        SessionInformation info = getSessionInformation(sessionId);
        if (info == null) {
            return;
        }

        if (log.isTraceEnabled()) {
            log.debug("Removing session " + sessionId
                    + " from set of registered sessions");
        }

//        sessionIds.remove(sessionId);
        redisTemplate.delete(PREFIX_SESSION + sessionId);

        Set<String> sessionsUsedByPrincipal = (Set<String>) redisTemplate.opsForValue().get(PREFIX_PRINCIPAL + ((LoginUser) info.getPrincipal()).getUsername());
        if (sessionsUsedByPrincipal == null) {
            log.info("通过 princlpal 查找登陆的 sessionIds 未找到 ：" + ((LoginUser) info.getPrincipal()).getUsername());
            return;
        }
        sessionsUsedByPrincipal.remove(sessionId);
        redisTemplate.opsForValue().set(PREFIX_PRINCIPAL + ((LoginUser) info.getPrincipal()).getUsername(), sessionsUsedByPrincipal, 1, TimeUnit.HOURS);
    }
}
