<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->


 <%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="/admin/header.jsp" flush="true" />

<%
  Notices notice=(Notices)request.getAttribute("notice");
  session.setAttribute("notice", notice);
  String button=(String)request.getAttribute("button");
  boolean read=true;
  boolean button_visibility=true;

%>
<%
 if(button.equals("View"))
 {
   read=true;
   button_visibility=false;
 }
 if(button.equals("Update"))
 {
   read=false;
   button_visibility=true;
 }
 if(button.equals("Delete"))
 {
   read=true;
   button_visibility=true;
 }
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Book Category </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
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
<script>


$(document).ready(function()
{
   var jQueryDatePicker1Opts =
   {
      dateFormat: 'yy-mm-dd',
      changeMonth: false,
      changeYear: false,
      showButtonPanel: false,
      showAnim: 'show'
   };
   $("#date").datepicker(jQueryDatePicker1Opts);

   



});

    </script>
 <script language="javascript" type="text/javascript">


function check1()
{
    if(document.getElementById('subject').value=="")
    {
        alert("Enter Subject");

        document.getElementById('subject').focus();

        return false;
    }
     if(document.getElementById('detail').value=="")
    {
        alert("Enter Detail");


        document.getElementById('detail').focus();

        return false;
    }
   

return true;
  }
function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }


function del()
{
   var answer = confirm ("Do you want to Delete Record?")
if (answer!=true)
    {
        document.getElementById('button').focus();
        return false;
    }
  

}



  function quit()
  {

      window.location="<%=request.getContextPath()%>/systemsetup/manage_notices.jsp";
      return false;
  }



    </script>
</head>
<body>

    <html:form method="post" action="/viewupdatenotice">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Add Notices</td></tr>
                <tr><td valign="top" align="center"> <br/>
                        <table cellspacing="10px">
                            <tr><td  colspan="2">Date

                        </td>
                        <td align="left"><html:text   property="date"  styleId="date" value="<%=notice.getDate()%>" readonly="true"/><br>
                       </td>
                    </tr>
                            <tr><td width="150px"  colspan="2">Notice Id </td>
                                <td width="150px" align="left"> <html:text   property="notice_id" value="<%= String.valueOf(notice.getId().getNoticeId())%>" readonly="true"/>
                                </td>
                            </tr>
                          
                           
                            <tr><td   colspan="2">Enter Subject<span class="star">*</span>

                        </td>
                        <td align="left"> <html:text  property="subject" styleId="subject" value="<%=notice. getSubject()%>" readonly="<%=read%>" /></td>
                    </tr>
                       <tr><td   colspan="2">Enter Detail<span class="star">*</span>

                        </td>
                        <td align="left"> <html:textarea    property="detail" styleId="detail" value="<%=notice.getDetail()%>" readonly="<%=read%>"/></td>
                    </tr>
                    
                 

                    <tr><td   colspan="3" align="center"><br/>
    <%if(button_visibility){
    if(button.equals("Delete")){%>
    <html:submit property="button" styleId="button" value="<%=button%>" onclick="return del();" styleClass="btn" style="left:80px"  />
    <%}
    else{%>
    <html:submit property="button" value="<%=button%>" styleClass="btn" onclick="return check1()" style="left:80px"  />
     <%}%><%}%>
                             <input type="button" onClick="quit()"  value="Back"/>
                        </td>

                    </tr>


                </table>


    









</td></tr>
                </table>
        </div>

</html:form>

</body>

    

</html>
