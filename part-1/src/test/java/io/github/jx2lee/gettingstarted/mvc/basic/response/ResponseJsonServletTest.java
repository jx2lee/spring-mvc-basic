package io.github.jx2lee.gettingstarted.mvc.basic.response;

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
class ResponseJsonServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Test
    void responseJsonServletTest() throws IOException {
        // given
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);

        // when
        when(response.getWriter()).thenReturn(writer);
        new ResponseJsonServlet().service(request, response);

        // then
        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("utf-8");
        assertThat(stringWriter.toString())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("{\"username\":\"jx2lee\",\"age\":31}");
    }
}
