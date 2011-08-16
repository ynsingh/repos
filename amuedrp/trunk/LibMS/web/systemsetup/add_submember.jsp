<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Library Details
-->
<%@page pageEncoding="UTF-8"  import="java.util.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="/admin/header.jsp" flush="true" />

<%
String new_sub_emptype_id=(String)request.getAttribute("new_sub_emptype_id");
String emptype_full_name=(String)request.getAttribute("emptype_full_name");
String emptype_id=(String)request.getAttribute("emptype_id");

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
<title>LibMS : Manage Member </title>
<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.js"></script>
</head>
<body>
 <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    
     <html:form action="/add_submember" method="post" >
 <table dir="<%=rtl%>" border="1" class="table" width="400px" height="200px" align="center" >


                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_submember.managesubmemtype")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                <table dir="<%=rtl%>" cellspacing="10px">
<tr>

    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("systemsetup.manage_submember.submemtypeid")%><a class="star"></a></strong></td>
    <html:hidden property="sub_emptype_id" styleId="faculty_id"   value="<%=new_sub_emptype_id%>" styleClass="textBoxWidth" />
    <td dir="<%=rtl%>"><html:text property="sub_emptype_id" styleId="faculty_id" readonly="true" value="<%=new_sub_emptype_id%>" styleClass="textBoxWidth" />

    </td>
  </tr>
<tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
    <tr><td dir="<%=rtl%>" align="<%=align%>" width="150px"><%=resource.getString("systemsetup.member_view_update.memname")%> </td><td align="<%=align%>">

            <html:hidden property="emptype_full_name"  value="<%=emptype_full_name%>" styleClass="textBoxWidth" />
            <html:hidden property="emptype_id"  value="<%=emptype_id%>" styleClass="textBoxWidth" />
            <html:text property="emptype_full_name"  value="<%=emptype_full_name%>" readonly="true" styleClass="textBoxWidth"/>

    </td>

                     


               </tr>

  
  
  <tr>
    <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("systemsetup.add_submember.submemtypename")%><a class="star">*</a> </strong></td>
    <td dir="<%=rtl%>"><html:text property="sub_emptype_full_name"  styleId="sub_emptype_full_name" value="" styleClass="textBoxWidth"/>
       
    </td>
  </tr>
  <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
  
 <tr>

    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("systemsetup.add_submember.maxissuebook")%><a class="star">*</a></strong></td>

    <td dir="<%=rtl%>"><html:text property="no_of_issueable_book" styleId="limit"   value="" styleClass="textBoxWidth" />

    </td>
  </tr>
 
 
  
 
<tr>
    <td dir="<%=rtl%>" colspan="4" align="center"><br/><br/><input type="submit"  value="<%=resource.getString("circulation.cir_newmember.submit")%>"  onClick="return validation();"/>

        <input type="button"  value="<%=resource.getString("circulation.cir_newmember.back")%>" onclick="return quit();" />
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

      window.location="<%=request.getContextPath()%>/systemsetup/manage_submember.jsp";
      return false;
  }
   function validation()
    {




 
    var sublib_name=document.getElementById('sub_emptype_full_name');
var limit=document.getElementById('limit');
  
    







var str="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-";


   
    if(sublib_name.value=="")
        {str+="\n <%=resource.getString("systemsetup.add_submember.entersubmemtype")%> ";
             alert(str);
             document.getElementById('sub_emptype_full_name').focus();
            return false;

        }
    if(limit.value=="")
        {str+="\n <%=resource.getString("systemsetup.add_submember.enternoofisuablebook")%> ";
             alert(str);
             document.getElementById('limit').focus();
            return false;

        }





  

if(str=="<%=resource.getString("circulation.cir_newmember.enterfollowingvalues")%>:-")
   {
       return true;

   }
else
    {

        alert(str);
        document.getElementById('sub_emptype_id').focus();
        return false;
    }




    }
 </script>

  <%
 String msg=(String)request.getAttribute("msg");
           if (msg!=null){
%>
 <script language="javascript">
 window.location="<%=request.getContextPath()%>/systemsetup/add_submember.jsp";
 alert("<%=msg%>");
 </script>
 <%
}

%>
</html>

 