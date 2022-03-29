package io.github.jx2lee.gettingstarted.mvc.basic.request;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.mock;

class RequestBodyStringServletTest {

    @Test
    void requestBodyStringServletTest() throws IOException {
        // given
        RequestBodyStringServlet servlet = mock(RequestBodyStringServlet.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        ServletInputStream servletInputStream = new ServletInputStream() {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("jx2lee".getBytes());
            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }
        };

        String messageBodyString = StreamUtils.copyToString(servletInputStream, StandardCharsets.UTF_8);

        // when
        Mockito.when(request.getInputStream()).thenReturn(servletInputStream);
        servlet.service(request, response);

        // then
        Assertions.assertThat(messageBodyString)
                .isNotEmpty()
                .isEqualTo("jx2lee");
    }

}
