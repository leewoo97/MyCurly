package com.dldnwls.mycurly;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("prod")  // 프로파일 명시적으로 지정
class MycurlyApplicationTests {

    @Test
    void contextLoads() {
    }

}
