package io.github.jx2lee.gettingstarted.mvc.web.servlet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberFormServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Test
    void memberFormServletTest() throws IOException {
        // given
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        String expectedRequestURI = "/servlet/members/new-form";

        // when
        when(response.getWriter()).thenReturn(writer);
        when(request.getRequestURI()).thenReturn("/servlet/members/new-form");
        new MemberFormServlet().service(request, response);

        // then
        System.out.println("request = " + request.getRequestURI());
        verify(response).setContentType("text/html");
        verify(response).setCharacterEncoding("utf-8");
        assertThat(request.getRequestURI()).isEqualTo(expectedRequestURI);
        assertThat(stringWriter.toString())
                .isNotNull()
                .isEqualTo("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +

                        "    <title>Title</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                        "    username: <input type=\"text\" name=\"username\" />\n" +
                        "    age:      <input type=\"text\" name=\"age\" />\n" +
                        " <button type=\"submit\">전송</button>\n" + "</form>\n" +
                        "</body>\n" +
                        "</html>\n");

    }

}
