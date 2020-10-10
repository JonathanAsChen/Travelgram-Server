package com.wonderfour.server;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 10/10/2020 9:33 AM
 */
@SpringBootTest
@Slf4j
public class BCryptTest {

    @Test
    public void getBCrypt() {
        String password = BCrypt.hashpw("password", BCrypt.gensalt());
        log.info("[password] {}", password);
    }
}

