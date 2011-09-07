<%-- 
    Document   : candidate_registration
    Created on : 18 Jun, 2011, 7:28:39 PM
    Author     : akhtar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" %>
<%@page import="java.util.*,java.io.*,java.net.*" %>
<%@page import="com.myapp.struts.hbm.*,com.myapp.struts.hbm.Election"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<script type="text/javascript">
    function submit()
    {
        var f = document.getElementById("form1").submit();
    }
    </script>

    <div
        style="  top:250px;
        left:500px;
        right:5px;
        position: absolute;

        visibility: visible; z-index: 100;" >


        <html:form action="/candidatemenifestoupload" method="post" styleId="form1" enctype="multipart/form-data">
            
            <html:file  property="menifesto" name="CandidateMenifestoActionForm" styleId="menifesto" onchange="submit()"  />

        </html:form>



    </div>
    


