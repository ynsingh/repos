<%-- 
    Document   : language.jsp
    Created on : Dec 2, 2010, 1:54:32 PM
    Author     : a
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%

 String locale=request.getParameter("locale");

if(locale.equals("English")) session.setAttribute("locale", "en");
                    else if(locale.equals("Hindi")) session.setAttribute("locale", "hi");
                    else if(locale.equals("Urdu")) session.setAttribute("locale", "ur");
                    else if(locale.equals("Arabic")) session.setAttribute("locale", "ar");
response.sendRedirect(request.getContextPath()+"/login.jsp");

%>
