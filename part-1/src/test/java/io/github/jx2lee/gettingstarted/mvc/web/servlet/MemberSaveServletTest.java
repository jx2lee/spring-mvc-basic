package io.github.jx2lee.gettingstarted.mvc.web.servlet;

import io.github.jx2lee.gettingstarted.mvc.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberSaveServletTest {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Test
    void memberListServletTest() throws IOException {
        // given
        StringWriter stringWriter = new StringWriter();
        Member member = new Member("jx2lee", 31);
        PrintWriter writer = writeHtml(new PrintWriter(stringWriter), member);
        String expectedRequestURI = "/servlet/members/save";

        // when
        when(request.getParameter("username")).thenReturn("jx2lee");
        when(request.getParameter("age")).thenReturn("32");
        when(request.getRequestURI()).thenReturn("/servlet/members/save");
        when(response.getWriter()).thenReturn(writer);
        new MemberFormServlet().service(request, response);

        // then
        verify(response).setContentType("text/html");
        verify(response).setCharacterEncoding("utf-8");
        assertThat(request.getParameter("username")).isEqualTo("jx2lee");
        assertThat(request.getParameter("age")).isEqualTo("32");
        assertThat(request.getRequestURI()).isEqualTo(expectedRequestURI);
        System.out.println("stringWriter = " + stringWriter);
        assertThat(stringWriter.toString())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("<html>\n" +
                        "<head>\n" +
                        " <meta charset=\"UTF-8\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "성공\n" +
                        "<ul>\n" +
                        "    <li>id=null</li>\n" +
                        "    <li>username=jx2lee</li>\n" +
                        " <li>age=31</li>\n" +
                        "</ul>\n" +
                        "<a href=\"/index.html\">메인</a>\n" +
                        "</body>\n" +
                        "</html><!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Title</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                        "    username: <input type=\"text\" name=\"username\" />\n" +
                        "    age:      <input type=\"text\" name=\"age\" />\n" +
                        " <button type=\"submit\">전송</button>\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>\n");
    }

    private PrintWriter writeHtml(PrintWriter printWriter, Member member) {
        printWriter.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" + "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                "    <li>id="+member.getId()+"</li>\n" +
                "    <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" + "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
                "</html>");

        return printWriter;
    }
}
