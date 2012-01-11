<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.*,java.util.*,java.text.SimpleDateFormat"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%
DocumentDetails docdetail=(DocumentDetails)request.getAttribute("docdetail");
Calendar cal=Calendar.getInstance();
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
String date=(String)request.getAttribute("limit");
String memId=(String)request.getAttribute("memid");
//String back=(String)request.getAttribute("back");
String back=(String)request.getParameter("b");
String msg1 = (String)request.getAttribute("msg1");

%>

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


<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>
<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <script>
        
    </script>

    <html:form action="/cir_checkout" method="post" target="_parent">

            <table dir="<%=rtl%>" width="40%" class="table"  align="center">

         <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("circulation.cir_view_book.chkoutdetail")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="<%=align%>" style=" padding-left: 5px;">


                        <html:hidden property="memid" value="<%=memId%>"/>
            <table dir="<%=rtl%>" align="center" class="txt2">
    
   <tr>
    <td dir="<%=rtl%>" width="150" align="<%=align%>"><strong><%=resource.getString("opac.myaccount.newdemand.title")%>: </strong> </td>
    <td width="200"> <html:text  property="title" readonly="true"  styleClass="textBoxWidth" value="<%=docdetail.getTitle() %>" /></td>
  </tr>
   
   
  
   <tr>
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("circulation.cir_view_book.authorname")%> :</strong></td>
    <td><html:text property="author_name" styleClass="textBoxWidth" readonly="true"  value="<%=docdetail.getMainEntry() %>" /></td>
   </tr>
   <tr>
    <td dir="<%=rtl%>" width="150" align="<%=align%>"><strong> Call No</strong> </td>
    <td width="200"> <html:text  property="callno" readonly="true"  styleClass="textBoxWidth" value="<%=docdetail.getCallNo() %>" /></td>
  </tr>

  
    
 
   <tr>
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("circulation.showcirreqopac.accessno")%> :</strong></td>
    <td><html:text property="accession_no" readonly="true"  styleClass="textBoxWidth" value="<%=docdetail.getAccessionNo() %>" /></td>
   </tr>
 
 
  
  
  
  <tr>
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("circulation.cir_view_book.issuedate")%>:</strong></td>
    <td><html:text property="issue_date" styleId="issue_date" readonly="true"  styleClass="textBoxWidth" value="<%=sdf.format(cal.getTime()).toString() %>" /></td>
  </tr>
 
  <tr>
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("circulation.cir_view_book.duedate")%>:</strong></td>
    <td><html:text  property="due_date" value="<%=date%>" readonly="true"  styleId="due_date"/></td>
  </tr>
 <tr>
     <td dir="<%=rtl%>" ><input type="hidden" name="document_id" id="document_id" value="<%=docdetail.getId().getDocumentId() %>" /></td>
  </tr>
     <tr><td dir="<%=rtl%>" height="10px" colspan="4" ><%if(msg1!=null){%><p class="err"><%=msg1%></p><%}%></td></tr>
  <tr>
    <td dir="<%=rtl%>" colspan="4" align="center">
        <input type="submit"  value="<%=resource.getString("circulation.viewformember.chkout")%>"  class="btn" style="left:80px" />
        
         
           <input type="button" onclick="quit();" class="btn" style="left:150px" value="<%=resource.getString("circulation.cir_newmember.back")%>"/>

    



       
  </td>
  </tr></table></td></tr></table>

    
  </html:form>
       

</body>
</html>

<script language="javascript" type="text/javascript">
  function quit()
  {
    
  
  <%if(back!=null){%>
window.location="<%=request.getContextPath()%>/circulation/cir_callno_view.jsp";
<%}else{%>
         window.location="<%=request.getContextPath()%>/circulation/cir_viewall_callno.jsp";
    <%}%>

  }

 

</script>