<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("userLogin") == null){
	  System.out.append("a");
		response.sendRedirect(request.getContextPath()+"/show-login");
		return;
	}
%>