package com.jx2lee.springmvc.web.frontcontroller.v2.controller;

import com.jx2lee.springmvc.domain.member.Member;
import com.jx2lee.springmvc.domain.member.MemberRepository;
import com.jx2lee.springmvc.web.frontcontroller.MyView;
import com.jx2lee.springmvc.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);

        // 데이터 보관 to Model
        request.setAttribute("member", member);

        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
