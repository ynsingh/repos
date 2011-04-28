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
String msg1=(String)request.getAttribute("msg1");
String msg2=(String)request.getAttribute("msg2");
%>
<html>
    <head>
        <script>
            function back()
            {
                location.href="<%=request.getContextPath()%>/admin/main.jsp";
            }

            </script>
      <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
%>
<%
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
             <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
   <jsp:include page="/admin/header.jsp"/>
    <body>
        <%if(page.equals(true)){%>

        <html:form method="post" action="/catBiblioAction" style="position:absolute; left:200px; top:200px;">
            <table border="1" class="table" width="400">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Manage Bibliographic Details</b></td></tr>
                <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>"/>
                <html:hidden property="sub_library_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>"/>
                <html:hidden property="main_entry" name="BibliographicDetailEntryActionForm" value="New"/>
                <html:hidden property="call_no" name="BibliographicDetailEntryActionForm" value="New"/>
                <html:hidden property="title" name="BibliographicDetailEntryActionForm" value="New"/>
                <html:hidden property="document_type" name="BibliographicDetailEntryActionForm" value="New"/>
                <html:hidden property="no_of_copies" name="BibliographicDetailEntryActionForm"/>
                <tr><td>
                <table border="0" cellspacing="4" cellpadding="1" align="center">
                    <tr><td><br><br></td></tr>
                    <tr><td rowspan="7" width="100">
                            Enter ISBN:<br><br>
                            <html:text styleId="isbn10" property="isbn10" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth"/>
                    <br><span class="err">   <html:messages id="err_name" property="isbn10">
                          <bean:write name="err_name" />
                            </html:messages></span>                          
                    </td></tr>
                    <tr><td height="10px;" width="40"><br></td></tr>
                    
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="New" Class="txt1"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="Update" Class="txt1"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="View" Class="txt1"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button" value="Delete" Class="txt1"/></td></tr>
                    <tr><td width="40"></td><td><input type="button" name="button" value="Back" Class="txt1" onclick="back();"/></td></tr>
                    <tr><td colspan="2" align="center"><br><br><br></td></tr>
                                                     <tr><td colspan="2">
                        <%  if(msg1!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>
<%}%>
    <%  if(msg2!=null)
    {%>
    <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg2%></span>
<%}%>
                        </td> </tr>
  </table>
                    </td></tr>

            </table>
    </html:form>
   <%}else{%>


    <%  if(msg1!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:12px;font-weight:bold;color:blue; position: absolute; top: 130px;" ><%=msg1%></span>
<%}%>
    <%  if(msg2!=null)
    {%>
    &nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:12px;font-weight:bold;color:red; position: absolute; top: 130px;" ><%=msg2%></span>
<%}%>

<html:form method="post" action="/catBiblioAction" style="position:absolute; left:800px; top:200px;">
            <table border="1" class="table" width="400">
                <tr><td align="center" class="headerStyle" bgcolor="#CCCCCC" height="25px;" ><b>Manage Bibliographic Details</b></td></tr>
               <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>"/>
                <html:hidden property="sub_library_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>"/>
               <tr><td>
                <table border="0" cellspacing="4" cellpadding="1" align="center">
                   <tr><td><br><br></td></tr>
                   <tr><td align="left" width="50"><input type="Submit" name="button" value="New" Class="txt1"/></td><td width="40"></td><td rowspan="5" width="100" align="right">Enter ISBN-10:<br><br><html:text property="isbn10" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth"/><br>
                        <html:text property="isbn13" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth"/><br></td></tr>
                    <tr><td align="left" width="50"></td><td><input type="Submit" name="button" value="Update" Class="txt1"/></td></tr>
                    <tr><td align="left" width="50"><input type="Submit" name="button" value="View" Class="txt1"/></td></tr>
                    <tr><td align="left" width="50"><input type="Submit" name="button" value="Delete" Class="txt1"/></td><td width="40"></td></tr>
                    <tr><td align="left" width="50"><input type="button" name="button" value="Back" Class="txt1" onclick="back();"/></td></tr>
                    <tr><td colspan="2" align="center" height="10px"><br><br></td></tr>


                </table>
                    </td></tr></table>
    </html:form>
   <%}%>  </body>
</html>
