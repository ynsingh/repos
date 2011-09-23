<%-- 
    Document   : newjsp
    Created on : Mar 29, 2011, 3:21:11 PM
    Author     : zeeshan
--%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/admin/header.jsp"/>
<html>
    <head>
       

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage MARC Based Bibliography</title>
        <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">
             <link rel="stylesheet" href="<%=request.getContextPath()%>/LibMS-Struts/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/LibMS-Struts/css/formstyle.css"/>
         
    </head>
    <script type="text/javascript"  >
        function vcheck(){
        var str=document.getElementById("title").value;
        var str1=document.getElementById("isbn").value;

alert("hi")
         if (str ==null||str==""||str.search(/[^a-zA-Z \-]/g) != -1 ) {
            alert("Special Symbols, null And digits Are Not Allowed in TITLE OF BOOK!");

            return false;
        }

         if (str1 ==null||str1==""||str1.search(/[^a-zA-Z \-]/g) == -1 ) {
            alert("ISBN CONTAINS ONLY DIGITS !");

            return false;
        }
        }

        function back()
        {
            location.href="<%=request.getContextPath()%>/admin/main.jsp";
        }
 </script>
            <body>
                
        
        <table border="1" cellspacing="0"  class="table" width="500"  style="position: absolute; top: 20%; left: 20%" >
            
            <html:form action="/biblio" method="post" onsubmit="return vcheck(); " >
                <tr><td colspan="2" align="center" class="headerStyle" bgcolor="#E0E8F5" height="30px;" ><b>Machine Readable Cataloging</b></td></tr>
                <tr><td>
                <table border="0" cellspacing="8" cellpadding="1" align="center">
                <tr><td width="60"></td></tr>
                <tr><td width="60">Title</td></tr>
                <tr><td width="60"><input type="text" value="" name="title"/></td></tr>
                <tr><td width="60">ISBN </td></tr>
                <tr><td width="60"><input type="text" value="" name="isbn"/></td></tr>
                <tr><td width="60"></td></tr>

                </table></td>
            <td>
                    <table border="0" cellspacing="8" cellpadding="1" align="center">
                    <tr><td><input type="submit" value="New" name="btn" Class="txt1"/></td></tr>
                    <tr><td><input type="submit" value="Update" name="btn" Class="txt1"/></td></tr>
                    <tr><td><input type="submit" value="View" name="btn" Class="txt1" /></td></tr>
                    <tr><td><input type="submit" value="Delete" name="btn" Class="txt1"/></td></tr>
                    <tr><td><input type="button" value="Back" onclick="back();" Class="txt1"/></td></tr>
                        </table>
                    </td></tr>
                 </html:form>
                       <tr><td><font color="red">
        <% if(request.getAttribute("msg")!=null){
                  out.println(request.getAttribute("msg"));

        }
        %></font></td></tr>
        </table>
        
                  <%  if(request.getAttribute("del")!=null){
                        out.println(request.getAttribute("del"));
                        %>

<!--                        alert(""+<%--=request.getAttribute("del") --%>);-->
                <% }
        else{}
                %>
    </body>
</html>
