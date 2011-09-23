<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID 
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*,com.myapp.struts.admin.Traker"%>
 <jsp:include page="header.jsp" flush="true" />

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
try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
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
String msg1=(String)request.getAttribute("msg1");

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Staff </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <script language="javascript" type="text/javascript">



function check1()
{
    if(document.getElementById('staff_id').value=="")
    {
        alert("<%=resource.getString("admin.acq_register.enterStaff")%>");

        document.getElementById('staff_id').focus();

        return false;
    }

  }
function Send(value)
{
 
    document.getElementById("button").setAttribute("value", value);
    return true;
}

  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }



    </script>
</head>
<body>
 
    <html:form method="post"  action="/staffRegistration" onsubmit="return check1();">
<div
   style="  top:15%;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

        <font size="-2"><br/>&nbsp;&nbsp;<i>You are here : </i>LibMS-><%
        String p=request.getParameter("p");

        String data=Traker.getActivity(p,(String)session.getAttribute("locale"));
        out.println(data);

        %></font>
</div>
<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table border="1" class="table" width="400px" height="200px" dir="<%=rtl%>" align="center">

  
                <tr><td align="center" class="headerStyle" dir="<%=rtl%>"  bgcolor="#E0E8F5" height="25px;"><%=resource.getString("admin.acq_register.heading")%></td></tr>
                <tr><td valign="top" dir="<%=rtl%>"  align="center"> <br/>
                <table cellspacing="10px" dir="<%=rtl%>" >

                    <tr><td rowspan="5" dir="<%=rtl%>"  class="txt2"><%=resource.getString("admin.acq_register.enterStaff")%><br><br>
                        <input type="text" id="staff_id" name="staff_id" value=""/>
                        </td><td width="150px" dir="<%=rtl%>"  align="center"> <input type="submit" class="btn" id="Button1" name="button1" value="<%=resource.getString("admin.acq_register.register")%>" onClick="return Send('Register')" /></td></tr>
                    <tr><td width="150px" dir="<%=rtl%>"  align="center"><input type="submit" id="Button2" class="btn" name="button1" value="<%=resource.getString("admin.acq_register.update")%>" onClick="return Send('Update')"  /></td></tr>
                    <tr><td width="150px" dir="<%=rtl%>"  align="center"><input type="submit" id="Button3" name="button1" value="<%=resource.getString("admin.acq_register.view")%>" class="btn" onClick="return Send('View')" /></td></tr>
                    <tr><td width="150px" dir="<%=rtl%>"  align="center"><input type="submit" id="Button4" name="button1" value="<%=resource.getString("admin.acq_register.delete")%>" class="btn" onClick="return Send('Delete')" /></td></tr>
                         <tr><td width="150px" dir="<%=rtl%>"  align="center"><input type="button" id="Button5" name="button1" value="<%=resource.getString("admin.acq_register.back")%>" class="btn" onclick="return quit()" /></td></tr>
 

                </table>
       

    <input type="hidden" name="library_id" value="<%=library_id%>">
    <input type="hidden" id="button" name="button" value=""/>




</td></tr>
                <tr><td dir="<%=rtl%>" align="<%=align%>">


 <%
          if (msg1!=null)
          {
        %>


               <p class="err"><%=msg1%></p>

        <%
        }
        %>




                    </td></tr>
    </table>
        </div>
  
</html:form>

</body>

     
</html>
