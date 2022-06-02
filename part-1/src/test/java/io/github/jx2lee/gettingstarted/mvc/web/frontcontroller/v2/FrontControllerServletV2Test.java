package io.github.jx2lee.gettingstarted.mvc.web.frontcontroller.v2;

import io.github.jx2lee.gettingstarted.mvc.web.frontcontroller.MyView;
import io.github.jx2lee.gettingstarted.mvc.web.frontcontroller.v2.controller.MemberFormControllerV2;
import io.github.jx2lee.gettingstarted.mvc.web.frontcontroller.v2.controller.MemberListControllerV2;
import io.github.jx2lee.gettingstarted.mvc.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class FrontControllerServletV2Test {
        @Mock
        HttpServletRequest request;

        @Mock
        HttpServletResponse response;
        private FrontControllerServletV2 frontControllerServletV2;

        @Test
        void front_controller_v2_main_test() throws ServletException, IOException {
                // given
                frontControllerServletV2 = new FrontControllerServletV2();

                // when
                frontControllerServletV2.service(request, response);

                // then
                Map<String, ControllerV2> controllerMap = frontControllerServletV2.getControllerMap();
                Assertions.assertThat(controllerMap.get("/front-controller/v2/members/new-form")).isExactlyInstanceOf(MemberFormControllerV2.class);
                Assertions.assertThat(controllerMap.get("/front-controller/v2/members/save")).isExactlyInstanceOf(MemberSaveControllerV2.class);
                Assertions.assertThat(controllerMap.get("/front-controller/v2/members")).isExactlyInstanceOf(MemberListControllerV2.class);
        }
}
