<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Library Details
-->
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="../admin/header.jsp" flush="true" />

<%
String sublib_id=(String)request.getAttribute("new_faculty_id");

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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>LibMS : Manage Faculty </title>
<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.js"></script>
<script type="text/javascript" language="javascript">
    function loadHelp()
    {
        window.status="Press F1 for Help";

    }


</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>

</head>
<body onload="loadHelp()" >

 <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    
     <html:form action="/add_faculty" method="post" >
 <table dir="<%=rtl%>" border="1" class="table" width="400px" height="200px" align="center">


                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_faculty.managefaulty")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                <table dir="<%=rtl%>" cellspacing="10px">


  <tr>

    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("systemsetup.add_faculty.facultyid")%><a class="star">*</a> :</strong></td>
    <html:hidden property="faculty_id" styleId="faculty_id"   value="<%=sublib_id%>" styleClass="textBoxWidth" />
    <td dir="<%=rtl%>"><html:text property="faculty_id" styleId="faculty_id" disabled="true"  value="<%=sublib_id%>" styleClass="textBoxWidth" />

    </td>
  </tr>
  
  <tr>
    
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("systemsetup.add_faculty.facultyname")%><a class="star">*</a> :</strong></td>
    <td dir="<%=rtl%>"><html:text property="faculty_name" styleId="faculty_name"  value="" styleClass="textBoxWidth" onfocus="statwords('Please Enter Valid Faculty Name ');" onblur="return loadHelp();"
/>
       
    </td>
  </tr>
  <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
  
  
 
 
  
 
<tr>
    <td dir="<%=rtl%>" colspan="4" align="center"><br/><input type="submit"   value="<%=resource.getString("circulation.cir_newmember.submit")%>"  onClick="return validation();"/>

        <input type="button"  value="<%=resource.getString("circulation.cir_member_reg.back")%>" onclick="return quit();" />
 </td>
</tr>
</table>
                    </td></tr></table>
</html:form>>
 
</div>
</body>

 <script language="javascript" type="text/javascript">
  function quit()
  {

      window.location="<%=request.getContextPath()%>/systemsetup/manage_faculty.jsp";
      return false;
  }
   function validation()
    {


        // var buttonvalue="Submit";
   // document.getElementById("button").setAttribute("value", buttonvalue);

 
    var sublib_name=document.getElementById('faculty_name');

  
    







var str="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";


   
    if(sublib_name.value=="")
        {str+="\n<%=resource.getString("systemsetup.add_faculty.enterfacname")%>  ";
             alert(str);
             document.getElementById('faculty_name').focus();
            return false;

        }
   




  

if(str=="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-")
   {
       return true;

   }
else
    {

        alert(str);
        document.getElementById('faculty').focus();
        return false;
    }




    }
 </script>

  <%
 String msg=(String)request.getAttribute("msg");
           if (msg!=null){
%>
 <script language="javascript">
 window.location="<%=request.getContextPath()%>/systemsetup/add_faculty.jsp";
 alert("<%=msg%>");
 </script>
 <%
}

%>
</html>

 