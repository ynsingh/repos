<%-- 
    Document   : candidate_registration
    Created on : 18 Jun, 2011, 7:28:39 PM
    Author     : akhtar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" %>
<%@page import="java.util.*,java.io.*,java.net.*" %>
<%@page import="com.myapp.struts.hbm.*,com.myapp.struts.hbm.Election"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<link rel="stylesheet" href="/EMS/css/page.css"/>
<script type="text/javascript">
    function submit()
    {
        var f = document.getElementById("form1").submit();
    }

    </script>

    

        <html:form action="/candidatemenifestoupload" method="post" styleId="form1" enctype="multipart/form-data">
            <table class="datagrid" align="center"><tr><td colspan="2" class="header">

         <%
         session.setAttribute("election_id",(String)request.getParameter("id"));
         session.setAttribute("position_id",(String)request.getParameter("pos_id"));
         %>
         Upload Menifesto</td></tr>
                <tr><td>Election Name:</td><td> <%=(String)session.getAttribute("election_id")%></td></tr>

                <tr><td>Position :</td><td><%=(String)session.getAttribute("position_id")%></td></tr>
         <%
String msg=(String)request.getAttribute("msg");
if(msg!=null)
{out.println(msg)      ;  }
else{

%>

<tr><td> Upload PDF Format Menifeasto </td><td>  <html:file  property="menifesto" name="CandidateMenifestoActionForm" styleId="menifesto" onchange="submit()"  /></tr>
<%}%>
            </table>
        </html:form>



  
    


