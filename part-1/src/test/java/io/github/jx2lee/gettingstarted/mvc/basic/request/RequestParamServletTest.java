package io.github.jx2lee.gettingstarted.mvc.basic.request;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestParamServletTest {

    @Test
    void requestParamServletTest() throws IOException {
        // given
        RequestParamServlet servlet = mock(RequestParamServlet.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // when
        when(request.getParameter("username")).thenReturn("jx2lee");
        when(request.getParameter("age")).thenReturn("32");
        servlet.service(request, response);

        // then
        assertThat(request.getParameter("username"))
                .isEqualTo("jx2lee");
        assertThat(request.getParameter("age"))
                .isEqualTo("32");
    }
}
