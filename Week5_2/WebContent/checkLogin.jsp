<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("userinfo") == null){
		response.sendRedirect(request.getContextPath()+"/admin/show-login");
		return;
	}
%>