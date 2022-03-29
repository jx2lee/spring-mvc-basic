package io.github.jx2lee.gettingstarted.mvc.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloDataTest {
    @Test
    @DisplayName("HelloData 테스트: 테스트 코드를 생활화 한다")
    void defaultHelloDataTest() {
        // given
        HelloData helloData = new HelloData();

        // when
        helloData.setAge(32);
        helloData.setUsername("jx2lee");

        // then
        Assertions.assertThat(helloData.getAge())
                .isEqualTo(32)
                .isInstanceOf(Integer.class);
        Assertions.assertThat(helloData.getUsername())
                .isEqualTo("jx2lee")
                .isInstanceOf(String.class);
    }
}
