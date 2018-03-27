<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("userLogin") == null){
		response.sendRedirect(request.getContextPath()+"/show-login");
		return;
	}
%>