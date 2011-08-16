<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID 
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>

 <jsp:include page="/admin/header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%

String msg=(String)request.getAttribute("msg");
String back=(String)session.getAttribute("page");
String msg2=(String)request.getAttribute("msg1");
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Faculty </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <script language="javascript" type="text/javascript">

 function Add()
{
    var buttonvalue="Add";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}

function View()
{
    var buttonvalue="View";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
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
    if(document.getElementById('emptype_id').value=="")
    {
        alert("<%=resource.getString("systemsetup.manage_member.entermemtypeid")%>");

        document.getElementById('emptype_id').focus();

        return false;
    }

  }




  function quit()
  {

   
    <%if(back!=null){%>
         window.location="<%=request.getContextPath()%>/admin/setup.jsp";
        <%}else{%>
      window.location="<%=request.getContextPath()%>/admin/main.jsp";
     <%}%>
      return true;
  }



    </script>
</head>
<body>
 
    <html:form method="post" onsubmit="return check1()" action="/memberRegistration">
       
<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table dir="<%=rtl%>" border="1" class="table" width="400px" height="200px" align="center">

  
                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_member.managememtype")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                <table dir="<%=rtl%>" cellspacing="10px">

                    <tr><td dir="<%=rtl%>" rowspan="5" class="txt2"><%=resource.getString("systemsetup.manage_member.entermemtypeid")%><br><br>
                        <input type="text" id="emptype_id" name="emptype_id" value=""/>
                        </td><td dir="<%=rtl%>" width="150px" align="center"> <input type="submit" class="btn" id="Button1" name="button1" value="<%=resource.getString("systemsetup.manage_notice.add")%>" onclick="return Add();" /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button1" value="<%=resource.getString("circulation.cir_member_reg.update")%>" onclick="return Update();" /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button3" name="button1" value="<%=resource.getString("circulation.cir_member_reg.view")%>" class="btn" onclick="return View();" /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button4" name="button1" value="<%=resource.getString("circulation.cir_member_reg.delete")%>" class="btn" onclick="return Delete();" /></td></tr>
                         <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="button" id="Button5" name="button1" value="<%=resource.getString("circulation.cir_member_reg.back")%>" class="btn" onclick="return quit()"/></td></tr>
 

                </table>
       

    
   

<input type="hidden" id="button" name="button" />






</td></tr>

                <tr><td class="mess">
                                 <%
          if (msg!=null && back!=null)
          {
              
        %>

        <p class="mess" dir="<%=rtl%>" align="<%=align%>">  <%=msg%></p>
        <script>
            var check = confirm("<%=resource.getString("systemsetup.manage_member.doyouwanttoaddmore")%>");
            if(check==false){
                    <%if(session.getAttribute("location")==null&& session.getAttribute("document")==null&& session.getAttribute("submember")==null)
                        {session.removeAttribute("member"); %>window.location="<%=request.getContextPath()%>/admin/main.jsp";<%}else{session.removeAttribute("member"); %>
                window.location="<%=request.getContextPath()%>/admin/setup.jsp";<%}%>}</script>

        <%
        }else if (msg!=null)
          {
        %>

        <p class="mess" dir="<%=rtl%>" align="<%=align%>">  <%=msg%></p>


        <%
        }
        %>
         <%if (msg2!=null)
          {
        %>


        <p class="err" dir="<%=rtl%>" align="<%=align%>">  <%=msg2%></p>

        <%
        }
        %>

                    </td></tr>
                 <tr><td dir="<%=rtl%>" align="justify"><font color="blue" size="-1"><b><%=resource.getString("systemsetup.manage_notice.example")%>:</b> s <%=resource.getString("systemsetup.manage_notice.forstudent")%>, t <%=resource.getString("systemsetup.manage_notice.forteachingstaff")%>, nt <%=resource.getString("systemsetup.manage_notice.nonteachingstaff")%>, <%=resource.getString("systemsetup.manage_notice.ifmemtypeexist")%>.</font></td></tr>

    </table>
        </div>
  
</html:form>

</body>

     
</html>
