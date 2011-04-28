
<%@page import="java.sql.*;"%>
 <jsp:include page="header.jsp" flush="true" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS : Add SubLibrary Details</title>
    </head>
    
    <body>
      <html:form  action="/addsublibrary" method="post" onsubmit="return check();">
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table width="400px"   align="center" border="0" class="table">
        <tr><td class="headerstyle" align="center"><b>Manage SubLibrary</b></td></tr>
      
        <tr><td>
                
                <br><br>
                <table width="100%">
               
<tr><td class="txtStyle" width="150px">Enter DepartMent ID</td><td><input type="text" id="sublibrary_id" name="sublibrary_id"   value=""></td></tr>
      <tr><td class="txtStyle">Enter Department Name<input type="text" id="sublibrary_name" name="sublibrary_name"   value=""></td></tr>
      <tr><td> <br><input type="submit" id="button" name="button" value="Add" class="txt2" />
                         <input type="button" id="button" name="" value="Cancel" onclick="return send1()" class="txt2"/>


                         <%

String msg,msg1;

msg=(String)request.getAttribute("msg");
msg1=(String)request.getAttribute("msg1");

if(msg!=null)
{
%>
<p class="err"><%=msg%></p>


<%}
if(msg1!=null){
    %>
    <p class="mess"><%=msg1%></p>

    <%}%>
                            </td></tr>
               
                
        </table>

               
            </td></tr>
    </table>
        </div>
   

</html:form>
    </body>

<script language="javascript" type="text/javascript">

    function check()
    {
        var x=document.getElementById('sublibrary_name');
        if(x.value=="")
            {
                alert("Sublibrary Name should not be blank");
                return false;
            }

        var x1=document.getElementById('sublibrary_id');
        if(x1.value=="")
            {
                alert("Sublibrary id should not be blank");
                return false;
            }

    
           return true;
    }


      function send1()
    {
        window.location="<%=request.getContextPath()%>/admin/main.jsp";
        return false;

    }


    </script>

</html>
