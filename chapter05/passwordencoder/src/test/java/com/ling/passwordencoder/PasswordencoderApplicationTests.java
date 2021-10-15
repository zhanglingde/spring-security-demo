package com.ling.passwordencoder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class PasswordencoderApplicationTests {

    // 单元测试生成密码
    @Test
    void contextLoads() {
        // admin $2a$10$hrMJFk5Udfs.djnhtuijE.WbbxXdUzZuIp18vLx5ZJbxurx0zbhFG
        // 正确密码
        // $2a$10$2A6h9O5XyylzE5UJs4k1seqtegMIo9IRA5huFrrWY1c0fzdgwyaDS
        // $2a$10$pX3d4fuSVHtIuJt.MmY0heVOoC0aConXgH1uQAddS2BpzzlvCTHjy
        // $2a$10$YmyYPPVp5l7QgyHAme9j7OXhmgUPUVHF/CQO8aSIxGf8dbqgSPISy / faw123456
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("faw1234");
        System.out.println("password = " + password);
    }

}
