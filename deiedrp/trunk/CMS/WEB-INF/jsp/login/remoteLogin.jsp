<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
System.out.println(getServletContext().getRealPath("\\"));
//response.sendRedirect(getServletContext().getRealPath("\\")+"Flex\\MAIN_HOME.html");
response.sendRedirect("http://localhost:8080//CMS//FLEX//remoteLogin.html");

	//response.sendRedirect("C:\\Users\\dei\\Documents\\FlexBuilder3\\CMS_FLEX\\bin-debug\\MAIN_HOME.html");
%>
