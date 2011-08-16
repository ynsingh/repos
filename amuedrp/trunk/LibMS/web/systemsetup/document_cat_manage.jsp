<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%! boolean read=false;%>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String button=(String)request.getAttribute("button");
if (button.equals("View")||button.equals("Delete"))
read=true;

else
    read=false;
String msg1=(String) request.getAttribute("msg1");
%>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
    boolean button_visibility=true;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

<script type="text/javascript">

  function Submit()
{
    var buttonvalue="Submit";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}

 function Update()
{
    var buttonvalue="Update";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}

  function check1()
    {

    
   var st=document.getElementById('document_category_name').value;
    if(st=="")
    {
        alert("<%=resource.getString("systemsetup.document_category.enterdoccatename")%>");

        document.getElementById('document_category_name').focus();

        return false;
    }
 if(document.getElementById('issue_check').value=="Select")
    {
        alert("<%=resource.getString("systemsetup.document_category.selectissuetype")%>");

        document.getElementById('issue_check').focus();

        return false;
    }
return true;
  }
   function confirm1()
{
document.getElementById('button').value="<%=button%>" ;
    var option=document.getElementById('button').value;
    if(option=="Delete"){
        var a=confirm("<%=resource.getString("circulation.cir_newmember.douwanttodelrec")%>");
       // alert(a);
        if(a!=true)
            {
                document.getElementById('button').focus();
               return false;

        }
        else
            return true;
}
}
function send()
{
   
    window.location="<%=request.getContextPath()%>/systemsetup/document_category.jsp";
    return false;
}
</script>
<%! String button1;%>
<%

 if(button.equals("Update"))
 {
     button1=resource.getString("circulation.cir_member_reg.update");
     read=false;

   button_visibility=true;
 }
 if(button.equals("Delete"))
 {
   button1=resource.getString("circulation.cir_member_reg.delete");
   read=true;

   button_visibility=true;
 }



%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bibliographic Detail Entry Form</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
    <body>
   <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
   <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">


       <html:form method="post" action="/document_cat_manage" onsubmit="return check1()" >
       <table dir="<%=rtl%>" border="1" class="table"  align="center">
                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b><%=resource.getString("systemsetup.document_category.doccategoryentry")%></b></td></tr>
                 
                <tr><td dir="<%=rtl%>">
                        <table dir="<%=rtl%>" width="500" border="0" cellspacing="4" cellpadding="1" align="<%=align%>">
                       
                        <html:hidden property="library_id" name="DocumentCategoryActionForm" value="<%=library_id%>" />
                        <html:hidden property="sub_library_id" name="DocumentCategoryActionForm" value="<%=sub_library_id%>" /><td></td>
                       
<tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
<tr>
    <td dir="<%=rtl%>" align="<%=align%>" class="txtStyle"><strong><%=resource.getString("systemsetup.doc_category.enterdoccategoryid")%></strong></td>
    <td dir="<%=rtl%>"><html:text readonly="true" property="document_category_id"  name="DocumentCategoryActionForm" styleClass="textBoxWidth" /></td>
</tr>
   <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>

<tr>
    <td dir="<%=rtl%>" width="150" align="<%=align%>" class="txtStyle"><strong><%=resource.getString("systemsetup.document_category.doccategorname")%><a class="star">*</a>:</strong> </td>
    <td dir="<%=rtl%>"><html:text readonly="<%=read%>"  property="document_category_name" name="DocumentCategoryActionForm" styleClass="textBoxWidth" styleId="document_category_name" />
    </td>
  </tr>
 
 <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
  <tr>
    <td dir="<%=rtl%>" align="<%=align%>" class="txtStyle"><strong><%=resource.getString("systemsetup.document_category.issuecheck")%></strong></td>
    <td>
        <%if(button.equals("Update") || button.equals("Add")){%>
  <html:select  property="issue_check" name="DocumentCategoryActionForm" styleId="issue_check"   styleClass="textBoxWidth">
            <html:option value="Select">Select</html:option>
            <html:option value="Issuable">Issuable</html:option>
            <html:option value="NotIssuable">NonIssuable</html:option>
        </html:select>

    <%}else {%>
    <input type="hidden" name="issue_check" styleId="issue_check"   styleClass="textBoxWidth"/>
    <html:select  property="issue_check" name="DocumentCategoryActionForm" disabled="true" styleId="issue_check"   styleClass="textBoxWidth">
            <html:option value="Select">Select</html:option>
            <html:option value="Issuable">Issuable</html:option>
            <html:option value="NotIssuable">NotIssuable</html:option>
        </html:select>
   <%}%>



  </td>
  </tr>
 <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>





<tr>
<td dir="<%=rtl%>" align="center" colspan="5"><br>
    <%if(button.equals("Update")){%>
    <input   type="submit"  value="<%=button1%>" onclick="return Update();" />
    &nbsp;&nbsp;&nbsp;<input  type="button" value="<%=resource.getString("circulation.cir_member_reg.back")%>" onclick="return send()" />
    <%}else if(button.equals("Delete")){%>
    <input   type="submit" value="<%=button1%>" onclick="return confirm1();"/>
   &nbsp;&nbsp;&nbsp;<input  type="button" value="<%=resource.getString("circulation.cir_member_reg.back")%>" onclick="return send()" />
   <%}else if(button.equals("Add")){%>
   <input    type="submit"   value="<%=resource.getString("circulation.cir_newmember.submit")%>" onclick="return Submit();"  />
    &nbsp;&nbsp;&nbsp;<input  type="button" value="<%=resource.getString("circulation.cir_member_reg.back")%>" onclick="return send()" />
    <%}else{%>
    <input   type="button" value="<%=resource.getString("circulation.cir_member_reg.back")%>"  /><%}%>

    
    <br/><br/>	</td>
</tr>
</table>
                    </td></tr></table>
   </div>

    <input type="hidden" id="button" name="button" />
  </html:form>
    </body>
</html>
