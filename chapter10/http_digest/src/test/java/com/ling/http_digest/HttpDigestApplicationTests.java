package com.ling.http_digest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class HttpDigestApplicationTests {

    @Test
    void contextLoads() throws NoSuchAlgorithmException {
        String rawPassword = "ling:myrealm:123";
        MessageDigest digest = MessageDigest.getInstance("MD5");
        String s = new String(Hex.encode(digest.digest(rawPassword.getBytes())));
        System.out.println("s = " + s);
    }

}
