<%--
    Document   : cat_update_titles
    Created on : Jan 12, 2011, 10:49:28 AM
    Author     : Asif Iqubal
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String msg=(String)request.getAttribute("msg");
String msg2=(String)request.getAttribute("msg2");
String msg3=(String)request.getAttribute("msg3");
String msg4=(String)request.getAttribute("msg4");
String titlenotfound=(String)request.getAttribute("titlenotfound");
String titlemsg=(String)request.getAttribute("titlemsg");
String option=(String)request.getAttribute("option");
%>
<html>
    <head>
 <script>
            function back()
            {
                location.href="<%=request.getContextPath()%>/admin/main.jsp";

            }

           function check1()
{


    var keyValue = document.getElementById('document_type').options[document.getElementById('document_type').selectedIndex].value;
   if (keyValue=="Select")
    {

        alert("Select Document Type");

        document.getElementById('document_type').focus();

        return false;
    }
    
     if(document.getElementById('titlename').value=="")
    {
        alert("Enter Title Name");

        document.getElementById('titlename').focus();

        return false;
    }

 
    return true;

  }


 </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LibMS:Acquisition Module</title>
             <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
   <jsp:include page="/admin/header.jsp"/>
    <body>
        <html:form method="post" action="/acq_new_entry"  onsubmit="return check1();" style="position:absolute; left:30%; top:30%">
            <table border="1" class="table" width="400">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Enter New Title</b></td></tr>
                 <html:hidden property="library_id" name="AcqBiblioActionForm" value="<%=library_id%>" />
                        <html:hidden property="sub_library_id" name="AcqBiblioActionForm" value="<%=sub_library_id%>" /><td></td>
                <tr><td>
                <table border="0" cellspacing="4" cellpadding="1" align="center">
                    <tr><td><br><br></td></tr>
                    <tr><td rowspan="7" width="100">
               <strong>Document Type:<a class="star">*</a></strong><br>
               <html:select property="document_type" value="Select" styleId="document_type" name="AcqBiblioActionForm" styleClass="textBoxWidth" >
       <html:option value="Select">Select</html:option>
            <html:option value="Book">Book</html:option>
            <html:option value="CD">CD</html:option>
  </html:select> 
                           <strong>Enter Title:<a class="star">*</a></strong>   <br>
                           <html:text property="title"  name="AcqBiblioActionForm" styleClass="textBoxWidth" styleId="titlename"/><br>
                                   <span class="err"><%if(titlemsg!=null){%><%= titlemsg %><%}%></span><br><br>
                            <div class="mandatory">   <a class="star">*</a>indicated fields are mandatory</div>
                        </td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="New" Class="txt1"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="Update" Class="txt1"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="View" Class="txt1"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="Delete" Class="txt1"/></td></tr>
                    <tr><td width="40"></td><td><input type="button" name="button" value="Back" Class="txt1" onclick="back();"/></td></tr>
                        <tr><td height="20px;"></td></tr>
                    <tr><td colspan="2" align="center"><br><br></td></tr>
                    <tr><td colspan="2">
    
                        </td> </tr>
                      <tr><td colspan="2">
                        <%  if(msg!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg%></span>
<%}%>
<%  if(msg2!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg2%></span>
<%}%>
        <%  if(msg3!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg3%></span>
<%}%>
      <%  if(msg4!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg4%></span>
<%}%>
      <%  if(titlenotfound!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:red;" ><%=titlenotfound %></span>
<%}%>
                        </td> </tr>
                </table>
                    </td></tr>

            </table>
    </html:form>
 </body>
</html>
