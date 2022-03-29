package io.github.jx2lee.gettingstarted.mvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.jx2lee.gettingstarted.mvc.basic.HelloData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestBodyJsonServletTest {
    @Test
    void requestBodyJsonServletTest() throws IOException, ServletException {

        // given
        RequestBodyJsonServlet servlet = mock(RequestBodyJsonServlet.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        ServletInputStream servletInputStream = new ServletInputStream() {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("{\"username\": \"jx2lee\", \"age\": 32}".getBytes());
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

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };

        String messageBody = StreamUtils.copyToString(servletInputStream, StandardCharsets.UTF_8);
        HelloData helloData = new ObjectMapper().readValue(messageBody, HelloData.class);


        // when
        when(request.getInputStream()).thenReturn(servletInputStream);
        servlet.service(request, response);

        // then
        Assertions.assertThat(helloData)
                .isNotNull()
                .extracting("username", "age")
                .contains("jx2lee", 32);
    }
}
