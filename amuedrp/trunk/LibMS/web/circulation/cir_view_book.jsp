<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.*,java.util.*,java.text.SimpleDateFormat"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%
DocumentDetails docdetail=(DocumentDetails)request.getAttribute("docdetail");
Calendar cal=Calendar.getInstance();
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
String date=(String)request.getAttribute("limit");
String memId=(String)request.getAttribute("memid");
String back=(String)request.getAttribute("back");
String msg1 = (String)request.getAttribute("msg1");

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
<title>Serial Entry</title>
<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

        <html:form action="/cir_checkout" method="post">

  <table width="40%" class="table"  border="1" align="center">

         <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Checkout Detail</td></tr>
                <tr><td valign="top" align="right" style=" padding-left: 5px;">


                        <html:hidden property="memid" value="<%=memId%>"/>
            <table align="center">
    <tr><td height="5px" colspan="4" ></td></tr>
   <tr>
    <td width="150" align="right"><strong>Title : </strong> </td>
    <td width="200"> <html:text  property="title" readonly="true"  styleClass="textBoxWidth" value="<%=docdetail.getTitle() %>" /></td>
  </tr>
   
    <tr><td height="5px" colspan="4" ></td></tr>
  
  
   <tr>
    <td align="right"><strong>Author Name :</strong></td>
    <td><html:text property="author_name" styleClass="textBoxWidth" readonly="true"  value="<%=docdetail.getMainEntry() %>" /></td>
   </tr>
    
  
    <tr><td height="5px" colspan="4" ></td></tr>
 
   <tr>
    <td align="right"><strong>Accession No :</strong></td>
    <td><html:text property="accession_no" readonly="true"  styleClass="textBoxWidth" value="<%=docdetail.getAccessionNo() %>" /></td>
   </tr>
 
  <tr><td height="5px" colspan="4" ></td></tr>
  
  
  
  <tr>
    <td align="right"><strong>Issue Date:</strong></td>
    <td><html:text property="issue_date" styleId="issue_date" readonly="true"  styleClass="textBoxWidth" value="<%=sdf.format(cal.getTime()).toString() %>" /></td>
  </tr>
  <tr><td height="5px" colspan="4" ></td></tr>
  <tr>
    <td align="right"><strong>Due Date:</strong></td>
    <td><html:text  property="due_date" value="<%=date%>" readonly="true"  styleId="due_date"/></td>
  </tr>
 <tr>
     <td><input type="hidden" name="document_id" id="document_id" value="<%=docdetail.getId().getDocumentId() %>" /></td>
  </tr>
     <tr><td height="10px" colspan="4" ><%if(msg1!=null){%><p class="err"><%=msg1%></p><%}%></td></tr>
  <tr>
    <td colspan="4" align="center">
        <html:submit property="button" value="Checkout" styleClass="btn" style="left:80px" />
        <%if(back!=null)
{%>
    <input type="button" onclick="return quit();" class="btn" style="left:150px" value="Back"/>
<%}
else{%>
         
           <input type="button" onclick="return quit();" class="btn" style="left:150px" value="Back"/>

    <%}%>



       
  </td>
  </tr></table></td></tr></table>

    
  </html:form>
        </div>

</body>
</html>

<script language="javascript" type="text/javascript">
  function quit()
  {
<%if(back!=null)
{%>
      window.location="<%=request.getContextPath()%>/circulation/cir_checkout.jsp";
<%}
else{%>
         window.location="<%=request.getContextPath()%>/circulation/cir_viewall_callno.jsp";
    <%}%>

  }

 

</script>