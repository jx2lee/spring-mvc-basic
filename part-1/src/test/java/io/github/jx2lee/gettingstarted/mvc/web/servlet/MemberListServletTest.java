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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberListServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Test
    void memberListServletTest() throws IOException {
        // given
        StringWriter stringWriter = new StringWriter();
        List<Member> members = new ArrayList<>(Arrays.asList(new Member("jx2lee", 31)));
        PrintWriter writer = writeHtml(new PrintWriter(stringWriter), members);
        String expectedRequestURI = "/servlet/members/new-form";

        // when
        when(request.getRequestURI()).thenReturn("/servlet/members/new-form");
        when(response.getWriter()).thenReturn(writer);
        new MemberFormServlet().service(request, response);

        // then
        verify(response).setContentType("text/html");
        verify(response).setCharacterEncoding("utf-8");
        assertThat(request.getRequestURI())
                .isNotNull()
                .isEqualTo(expectedRequestURI);
        assertThat(stringWriter.toString())
                .isNotNull()
                .isEqualTo("<html><head>    <meta charset=\"UTF-8\">    <title>Title</title></head><body><a href=\"/index.html\">메인</a><table>    <thead>    <th>id</th>    <th>username</th>    <th>age</th>    </thead>    <tbody>    <tr>      <td>null</td>      <td>jx2lee</td>      <td>31</td>    </tr>    </tbody></table></body></html><!DOCTYPE html>\n" +
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

    private PrintWriter writeHtml(PrintWriter writer, List<Member> members) {
        writer.write("<html>");
        writer.write("<head>");
        writer.write("    <meta charset=\"UTF-8\">");
        writer.write("    <title>Title</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<a href=\"/index.html\">메인</a>");
        writer.write("<table>");
        writer.write("    <thead>");
        writer.write("    <th>id</th>");
        writer.write("    <th>username</th>");
        writer.write("    <th>age</th>");
        writer.write("    </thead>");
        writer.write("    <tbody>");
        for (Member member : members) {
            writer.write("    <tr>");
            writer.write("      <td>" + member.getId() + "</td>");
            writer.write("      <td>" + member.getUsername() + "</td>");
            writer.write("      <td>" + member.getAge() + "</td>");
            writer.write("    </tr>");
        }
        writer.write("    </tbody>");
        writer.write("</table>");
        writer.write("</body>");
        writer.write("</html>");

        return writer;
    }

}
