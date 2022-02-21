package io.github.jx2lee.gettingstarted.mvc.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     * @param paramMap
     * @param model
     * @return viewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
