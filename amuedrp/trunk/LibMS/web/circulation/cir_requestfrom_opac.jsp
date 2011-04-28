<%-- 
    Document   : cir_requestfrom_opac
    Created on : Apr 6, 2011, 12:12:30 PM
    Author     : System Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/admin/header.jsp"/>
<%@page import="com.myapp.struts.hbm.*,java.util.*" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String sublibrary_id=(String)session.getAttribute("sublibrary_id");
%>
<script>
   var keyValue;
  function funcSearch()
{
    keyValue = document.getElementById('sublibary_id').options[document.getElementById('sublibary_id').selectedIndex].value;
     
   
    document.getElementById("Form1").action="requestfromopac.do?sublibrary_id="+keyValue;

    document.getElementById("Form1").method="post";
    document.getElementById("Form1").submit();
}
 function set(){

 keyValue = document.getElementById('sublibary_id').options[document.getElementById('sublibary_id').selectedIndex].value;
 document.getElementById('data').innerHTML="Search Result for "+ keyValue;}
    </script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
    </head>
    <div
   style="  top:120px;
   left:250px;
   right:5px;
      position: absolute;

      visibility: show;">


        <body onload="set()">
       <form method="post" action="/requestfromopac"  id="Form1">
        <table class="table" width="800px">
               <tr><td class="headerStyle" align="center">MemberShip Request From OPAC<br/></td></tr>
             <tr>
                 <td width="10%">      Library&nbsp;
                

<select  name="cmdSubLib" id="sublibary_id" tabindex="3" value="Select" onblur="funcSearch(); ">
<%
List<SubLibrary> sublib = (List<SubLibrary>)session.getAttribute("sublibrary");
Iterator it = sublib.iterator();
while(it.hasNext()){
    SubLibrary subLib = (SubLibrary)it.next();
    if(subLib.getId().getSublibraryId().equals(sublibrary_id)){
%>

<option name="SubLibrary" selected  value="<%=subLib.getId().getSublibraryId()%>"><%=subLib.getSublibName()%></option>
<%}else
{%>
<option name="SubLibrary" value="<%=subLib.getId().getSublibraryId()%>"><%=subLib.getSublibName()%></option>
<%}
    }%>
</select>
<div id="data"/>
               </td>
            </tr>
            <tr>
                <td colspan="4">
                    <iframe name="f1"  src="<%=request.getContextPath()%>/circulation/cir_requestfrom_opacgrid.jsp"  height="300px" width="800px" scrolling="no"  id="f1"/>
</td></tr>
        </table>
       </form>
    </body>
    </div>
</html>
