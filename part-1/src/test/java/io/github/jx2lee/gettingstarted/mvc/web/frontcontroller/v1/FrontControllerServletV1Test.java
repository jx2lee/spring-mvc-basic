package io.github.jx2lee.gettingstarted.mvc.web.frontcontroller.v1;

import io.github.jx2lee.gettingstarted.mvc.web.frontcontroller.v1.controller.MemberFormControllerV1;
import io.github.jx2lee.gettingstarted.mvc.web.frontcontroller.v1.controller.MemberListControllerV1;
import io.github.jx2lee.gettingstarted.mvc.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class FrontControllerServletV1Test {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    private FrontControllerServletV1 frontControllerServletV1;

    @Test
    void front_controller_v1_main_test() throws ServletException, IOException {
        // given
        frontControllerServletV1 = new FrontControllerServletV1();

        // when
        frontControllerServletV1.service(request, response);

        // then
        Map<String, ControllerV1> controllerMap = frontControllerServletV1.getControllerMap();
        Assertions.assertThat(controllerMap.get("/front-controller/v1/members/new-form")).isExactlyInstanceOf(MemberFormControllerV1.class);
        Assertions.assertThat(controllerMap.get("/front-controller/v1/members/save")).isExactlyInstanceOf(MemberSaveControllerV1.class);
        Assertions.assertThat(controllerMap.get("/front-controller/v1/members")).isExactlyInstanceOf(MemberListControllerV1.class);
    }
}
