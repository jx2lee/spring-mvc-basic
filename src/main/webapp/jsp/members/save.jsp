<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.jx2lee.springmvc.domain.member.MemberRepository" %>
<%@ page import="com.jx2lee.springmvc.domain.member.Member" %>
<%
    //request, response 는 바로 사용 가능함 (import 안해도..)
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    System.out.println("member = " + member);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
