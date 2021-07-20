package com.jx2lee.springmvc.web.frontcontroller.v3;

import com.jx2lee.springmvc.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paramMap);
}
