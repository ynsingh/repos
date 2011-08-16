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
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%
 String lib_id = (String)session.getAttribute("library_id");
 String sublib_id = (String)session.getAttribute("memsublib");
if(sublib_id==null)sublib_id= (String)session.getAttribute("sublibrary_id");
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
    %>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String msg1=(String)request.getAttribute("msg1");
String msg2=(String)request.getAttribute("msg2");
%>
<html>
    <head>
        <script type="text/javascript" language="javascript">
    function submitNew()
{
    var buttonvalue="New";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function submitUpdate()
{
    var buttonvalue="Update";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function submitView()
{
    var buttonvalue="View";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
function submitDelete()
{
    var buttonvalue="Delete";
    document.getElementById("button1").setAttribute("value", buttonvalue);
    return true;
}
    </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
             <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
   <jsp:include page="/admin/header.jsp"/>
    <body>
        <html:form method="post" action="/catOldBiblioAction" style="position:absolute; left:30%; top:30%">
            <table border="1" class="table" width="400" dir="<%=rtl%>">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b><%=resource.getString("cataloguing.catoldtitle.header")%></b></td></tr>
                <html:hidden property="library_id" name="BibliographicDetailEntryActionForm" value="<%=library_id%>"/>
                <html:hidden property="sublibrary_id" name="BibliographicDetailEntryActionForm" value="<%=sub_library_id%>" />
                <html:hidden property="main_entry" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="statement_responsibility" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="call_no" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="accession_type" name="BibliographicDetailEntryActionForm" value="Old"/>
                <html:hidden property="book_type" name="BibliographicDetailEntryActionForm" value="Old"/>
                <tr><td>
                <table border="0" cellspacing="4" cellpadding="1" align="center">
                    <tr><td><br><br></td></tr>
                    <tr><td rowspan="7" width="100" dir="<%=rtl%>" align="<%=align%>">
               <strong dir="<%=rtl%>"><%=resource.getString("cataloguing.catoldtitle.documenttype")%>:<a class="star">*</a></strong><br>
  <html:select property="document_type" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" >
       <html:option value=""><%=resource.getString("cataloguing.catoldtitle.select")%></html:option>
            <html:option value="Book"><%=resource.getString("cataloguing.catoldtitle.book")%></html:option>
            <html:option value="CD"><%=resource.getString("cataloguing.catoldtitle.cd")%></html:option>
  </html:select>
            <br><span class="err">   <html:messages id="err_name" property="document_type">
        <%=resource.getString("cataloguing.catoldtitle.err1")%>
    </html:messages></span><br>
                           <strong dir="<%=rtl%>"><%=resource.getString("cataloguing.catoldtitle.entertitle")%>:<a class="star">*</a></strong>   <br>
                            <html:text property="title" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" /><br>
                                <span class="err"><html:messages id="err_name" property="title">
                          <%=resource.getString("cataloguing.catoldtitle.err2")%>
                            </html:messages></span><br>
                                 <strong dir="<%=rtl%>"><%=resource.getString("cataloguing.catoldtitle.enterisbn")%>:</strong><br>
                            <html:text property="isbn10" name="BibliographicDetailEntryActionForm" styleClass="textBoxWidth" /><br><br>
                            <div class="mandatory">   <a class="star">*</a><%=resource.getString("cataloguing.catoldtitle.mandatory")%></div>
                        </td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button1" dir="<%=rtl%>" value="<%=resource.getString("cataloguing.catoldtitle.new")%>" Class="btn" onclick="return submitNew();"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button1" dir="<%=rtl%>" value="<%=resource.getString("cataloguing.catoldtitle.update")%>" Class="btn" onclick="return submitUpdate();"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button1" dir="<%=rtl%>" value="<%=resource.getString("cataloguing.catoldtitle.view")%>" Class="btn" onclick="return submitView();"/></td></tr>
                    <tr><td width="40"></td><td><input type="Submit" name="button1" dir="<%=rtl%>" value="<%=resource.getString("cataloguing.catoldtitle.delete")%>" Class="btn" onclick="return submitDelete();"/></td></tr>
                    <tr><td width="40"></td><td><input type="button" name="button1" dir="<%=rtl%>" value="<%=resource.getString("cataloguing.catoldtitle.back")%>" Class="btn"/></td></tr>
                    <input type="hidden" id="button1" name="button"/>
                    <tr><td height="20px;"></td></tr>
                    <tr><td colspan="2" align="center"><br><br></td></tr>
                    <tr><td colspan="2">
                        <%  if(msg1!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:red;" dir="<%=rtl%>"><%=msg1%></span>
<%}%>
    <%  if(msg2!=null)
    {%>
    <span style="font-size:12px;font-weight:bold;color:blue;" dir="<%=rtl%>"><%=msg2%></span>
<%}%>
                        </td> </tr>
  </table>
                    </td></tr>

            </table>
    </html:form>
 </body>
</html>
