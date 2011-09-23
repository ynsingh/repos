<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->


 <%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.*,java.util.*"%>

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
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
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

<%! String button1;%>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Book Category </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
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

function Update()
{
    var buttonvalue="Update";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
function Delete()
{
    var buttonvalue="Delete";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}

function check1()
{
        var buttonvalue="Update";
    document.getElementById("button").setAttribute("value", buttonvalue);
   


    if(document.getElementById('subject').value=="")
    {
        alert("<%=resource.getString("systemsetup.manage_notice.entersub")%>");

        document.getElementById('subject').focus();

        return false;
    }
     if(document.getElementById('detail').value=="")
    {
        alert("<%=resource.getString("systemsetup.manage_notice.enterdetail")%>");


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



  function quit()
  {

      window.location="<%=request.getContextPath()%>/systemsetup/manage_notices.jsp";
      return false;
  }




    </script>

      <script language="javascript" type="text/javascript">
function loadHelp()
{
    window.status="Press F1 for Help";
}
</script>
</head>
<body onload="loadHelp()">

    <html:form method="post" action="/viewupdatenotice">

<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table dir="<%=rtl%>" border="1" class="table" width="400px" height="200px" align="center">


                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_notice.addnotices")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                        <table cellspacing="10px">
                            <tr><td dir="<%=rtl%>" colspan="2"><%=resource.getString("circulation.cir_checkout_report.date")%>

                        </td>
                        <td dir="<%=rtl%>" align="<%=align%>"><html:text   property="date"  styleId="date" value="<%=notice.getDate()%>" readonly="true" onfocus="statwords('Notice Publication Date')" onblur="loadHelp()" /><br>
                       </td>
                    </tr>
                            <tr><td dir="<%=rtl%>" width="150px"  colspan="2"><%=resource.getString("systemsetup.manage_notice.noticesid")%></td>
                                <td dir="<%=rtl%>" width="150px" align="<%=align%>"> <html:text   property="notice_id" value="<%= String.valueOf(notice.getId().getNoticeId())%>" readonly="true"onfocus="statwords('Notice Id')" onblur="loadHelp()" />
                                </td>
                            </tr>
                          
                           
                            <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("systemsetup.manage_notice.entersub")%><span class="star">*</span>

                        </td>
                        <td dir="<%=rtl%>" align="<%=align%>"> <html:text  property="subject" styleId="subject" value="<%=notice. getSubject()%>" readonly="<%=read%>" onfocus="statwords('Enter Subject of the Notice')" onblur="loadHelp()"/></td>
                    </tr>
                       <tr><td dir="<%=rtl%>"  colspan="2"><%=resource.getString("systemsetup.manage_notice.enterdetail")%><span class="star">*</span>

                        </td>
                        <td dir="<%=rtl%>" align="<%=align%>"> <html:textarea    property="detail" styleId="detail" value="<%=notice.getDetail()%>" readonly="<%=read%>" onfocus="statwords('Enter your Notice Here. This will be published in OPAC')" onblur="loadHelp()" /></td>
                    </tr>
                    
                 

                    <tr><td dir="<%=rtl%>"  colspan="3" align="center"><br/>
    <%if(button_visibility){
    if(button.equals("Delete")){%>
    <input type="submit"  value="<%=button1%>" onclick="return del();" styleClass="btn" style="left:80px"  />
    <%}
    else{%>
    <input type="submit" value="<%=button1%>" styleClass="btn" onclick="return check1()" style="left:80px"  />
     <%}%><%}%>
                             <input type="button" onClick="quit()"  value="<%=resource.getString("circulation.cir_member_reg.back")%>"/>
                        </td>

                    </tr>


                </table>


    

   <input type="hidden" id="button" name="button" />







</td></tr>
                </table>
        </div>

</html:form>

</body>

    

</html>
