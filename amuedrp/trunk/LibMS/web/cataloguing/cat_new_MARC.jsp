<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-layout.tld" prefix="layout" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="/admin/header.jsp"/>
<html>
    <head>
       <c:remove var="hsmp" scope="session" />
<c:set var="BiblioActionForm" value="${null}"/>
<c:set var="CatControlActionForm" value="${null}"/>
<c:set var="CatActionForm1" value="${null}"/>
<c:set var="CatActionForm2" value="${null}"/>
<c:set var="CatActionForm3" value="${null}"/>
<c:set var="CatActionForm4" value="${null}"/>
<c:set var="CatActionForm5" value="${null}"/>
<c:set var="CatActionForm6" value="${null}"/>
<c:set var="CatActionForm7" value="${null}"/>
<c:set var="CatActionForm8" value="${null}"/>
<%
session.removeAttribute("data");
%>




        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage MARC Based Bibliography</title>
        <link rel="shortcut icon" type="image/x-icon" href="<%=request.getContextPath()%>/images/marci.gif">
             <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
         
    </head>
    <script type="text/javascript"  >
        function vcheck(){

        var str=document.getElementById("title").value;
       
       // var str1=document.getElementById("isbn").value;


        if (str.value=="") {
            alert("Special Symbols, null And digits Are Not Allowed in TITLE OF BOOK!");

            return false;
        }

         <%--if (str1=="") {
            alert("ISBN Cann't be Blank !");

            return false;
        }--%>
        }

        function back()
        {
            location.href="<%=request.getContextPath()%>/admin/main.jsp";
        }
 </script>
            <body>
     
            
        
        <table border="1" cellspacing="0"  class="table" width="500"  style="position: absolute; top: 20%; left: 20%" >
            
            <html:form action="/biblio" method="post" onsubmit="return vcheck();">
                <tr><td colspan="2" align="center" class="headerStyle" bgcolor="#E0E8F5" height="30px;" ><b>Machine Readable Cataloging</b></td></tr>
                <tr><td>
                <table border="0" cellspacing="8" cellpadding="1" align="center">
                <tr><td width="60"></td></tr>

                <tr><td width="60">
<layout:html>
<layout:suggest suggestAction="/getTitleSuggestions"  isRequired="true"   key="*Title" styleId="title" property="title" suggestCount="8"/>
</layout:html>
                    </td>
                </tr>
                <tr></tr>
<!--                <tr><td width="60">*ISBN </td><td width="60"><input type="text" value="" id="isbn" name="isbn"/></td></tr>-->
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
        %>
                               
                               
                               </font>
        <p class="mess"> 
                            <%  if(request.getAttribute("del")!=null){
                        out.println(request.getAttribute("del"));
                        %>

        
                <% } %>
</p>
                           </td></tr>
        </table>
        
                 
    </body>
</html>
