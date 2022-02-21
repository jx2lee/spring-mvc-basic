package io.github.jx2lee.gettingstarted.mvc.web.frontcontroller.v3;

import io.github.jx2lee.gettingstarted.mvc.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
