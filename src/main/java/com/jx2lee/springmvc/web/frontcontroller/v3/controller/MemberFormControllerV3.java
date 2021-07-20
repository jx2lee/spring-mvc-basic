package com.jx2lee.springmvc.web.frontcontroller.v3.controller;

import com.jx2lee.springmvc.web.frontcontroller.ModelView;
import com.jx2lee.springmvc.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
