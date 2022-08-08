package com.elltor.security.ba;

import lombok.val;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="kim:username?username=liuqichun03">刘启春(liuqichun03)</a>
 * @since 2022/7/29
 */
@SpringBootTest
public class RedisTemplateTests {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void redisSetAndReadTest() {
        Boolean hasLiuqichun = redisTemplate.hasKey("liuqichun");
        System.out.println("has key 'liuqichun' : " + hasLiuqichun);

        HashMap<String, String> map = new HashMap<>();
        map.put("sex", "male");
        map.put("name", "刘启春");
        map.put("phone", "123456");
        map.put("timestamp", System.currentTimeMillis() + "");
        redisTemplate.opsForValue().set("liuqichun", map, 2, TimeUnit.HOURS);
        Map<String, String> getMap = (Map) redisTemplate.opsForValue().get("liuqichun");
        System.out.println("getMap = " + getMap);
    }

    @Test
    void storageTest() {
        String prefix = "A:B:";
        Set<String> keys = redisTemplate.keys("A:*");

        for (String s : keys) {
            if (s != null) {
                String res = s.replaceFirst(prefix, "");
                System.out.println("res = " + res);
            }
        }
        System.out.println("keys = " + keys);
    }

    @Test
    void hasKeyTest() throws Exception{
        var liuqichun = redisTemplate.hasKey("liuqichun");
        System.out.println("liuqichun = " + liuqichun);
        redisTemplate.opsForValue().set("liuqichun", "man");
        System.out.println("redisTemplate.hasKey(\"liuqichun\") = " + redisTemplate.hasKey("liuqichun"));
    }

    @Test
    void hasKeyTest2() throws Exception {
        Boolean has = redisTemplate.hasKey("BA:SESSIONIDS:7DC3E0D59F70EC5D4C3266F66978ADC3");
        System.out.println("has = " + has);
    }

}
