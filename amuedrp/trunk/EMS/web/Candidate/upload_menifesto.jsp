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
<jsp:include page="/Voter/voterheader.jsp"/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<script type="text/javascript">
    function submit()
    {
        var f = document.getElementById("form1").submit();
    }
    </script>

    

        <html:form action="/candidatemenifestoupload" method="post" styleId="form1" enctype="multipart/form-data">
     

         <%
         session.setAttribute("election_id",(String)request.getParameter("id"));
         session.setAttribute("position_id",(String)request.getParameter("pos_id"));
         %>
         Upload Menifesto for :
         Election Name: <%=(String)session.getAttribute("election_id")%>

     Position :<%=(String)session.getAttribute("position_id")%>
         <%
String msg=(String)request.getAttribute("msg");
if(msg!=null)
{out.println(msg)      ;  }
else{

%>

         Upload PDF Format Menifeasto   <html:file  property="menifesto" name="CandidateMenifestoActionForm" styleId="menifesto" onchange="submit()"  />
<%}%>
        </html:form>



  
    


