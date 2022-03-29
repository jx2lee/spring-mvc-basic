package io.github.jx2lee.gettingstarted.mvc.basic.response;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// PrintWriter Test code: https://github.com/cschneider/osgi-testing-example/blob/master/src/test/java/net/lr/example/testing/PrimeCalculatorServletTest.java
@ExtendWith(MockitoExtension.class)
class ResponseHtmlServletTest {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Test
    void responseHtmlServletTest() throws IOException {
        // given
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        // when
        when(response.getWriter()).thenReturn(writer);
        new ResponseHtmlServlet().service(request, response);

        // then
        verify(response).setContentType("text/html");
        verify(response).setCharacterEncoding("utf-8");
        Assertions.assertThat(stringWriter.toString())
                .isNotNull()
                .isEqualTo("<html>\n" + "<body>\n" + "  <div>안녕?</div>\n" + "</body>\n" + "</html>\n");
    }
}
