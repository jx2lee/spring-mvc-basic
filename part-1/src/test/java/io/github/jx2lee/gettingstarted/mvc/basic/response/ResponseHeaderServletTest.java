package io.github.jx2lee.gettingstarted.mvc.basic.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ResponseHeaderServletTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    PrintWriter printWriter;

    @Test
    void responseHeaderServletTest() throws ServletException, IOException {

        // when
        when(response.getWriter()).thenReturn(printWriter);
        new ResponseHeaderServlet().service(request, response);

        // then
        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(response).setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        verify(response).setHeader("Pragma", "no-cache");
        verify(response).setHeader("my-header", "hello");
        verify(response).sendRedirect("/basic/hello-form.html");
        verify(response).setHeader("Content-Type", "text/plain;charset=utf-8");
        verify(response).setContentType("text/plain");
        verify(response).setCharacterEncoding("utf-8");
    }

    @Test
    void responseHeaderServletCookieTest() throws ServletException, IOException {
        /**
         * https://github.com/mjeanroy/springhub/blob/master/springhub-web/src/test/java/com/mjeanroy/springhub/commons/web/utils/CookieSessionTest.java
         */

        //given
        ArgumentCaptor<Cookie> argument = ArgumentCaptor.forClass(Cookie.class);

        // when
        when(response.getWriter()).thenReturn(printWriter);
        new ResponseHeaderServlet().service(request, response);
        verify(response).addCookie(argument.capture());

        // then
        Cookie cookie = argument.getValue();
        assertThat(cookie).isNotNull();
        assertThat(cookie.getName()).isNotNull().isEqualTo("myCookie");
        assertThat(cookie.getValue()).isNotNull().isEqualTo("good");
        assertThat(cookie.getMaxAge()).isNotNull().isEqualTo(600);
    }
}
