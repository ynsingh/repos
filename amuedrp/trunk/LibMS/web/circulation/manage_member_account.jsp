<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID 
-->



 <jsp:include page="/admin/header.jsp" flush="true" />
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.sql.*,org.apache.struts.upload.FormFile,com.myapp.struts.hbm.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%

String msg=(String)request.getAttribute("msg");

String msg1=(String)request.getAttribute("msg1");

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage Member Account</title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
 <script language="javascript" type="text/javascript">

 function Create()
{
    var buttonvalue="Create";
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
    if(document.getElementById('mem_id').value=="")
    {
        alert("Enter Member Id...");

        document.getElementById('mem_id').focus();

        return false;
    }

  }





  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }



    </script>
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
    <script language="javascript" type="text/javascript">
    function loadHelp()
    {
        window.status="Press F1 for Help";
    }
</script>
</head>
<body onload="loadHelp()">
 
    <html:form method="post" onsubmit="return check1()" action="/mem_account_register">
       
<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table border="1" dir="<%=rtl%>" class="table" width="400px" height="200px" align="center">

  
                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("circulation.managememacc.creatmemacc")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                <table cellspacing="10px">

                    <tr><td dir="<%=rtl%>" rowspan="5" class="txt2"><%=resource.getString("circulation.cir_member_reg.entermemid")%><br><br>
                        <input type="text" id="mem_id" name="mem_id" value=""  onfocus="statwords('Member Id');" onblur="loadHelp()"/>
                        </td><td dir="<%=rtl%>" width="150px" align="center"> <input type="submit" class="btn" id="button1"  value="<%=resource.getString("circulation.managememacc.create")%>" onclick="return Create();" /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="button2" class="btn"  value="<%=resource.getString("circulation.cir_member_reg.update")%>" onclick="return Update();"  /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="button3"  value="<%=resource.getString("circulation.cir_member_reg.view")%>" class="btn" onclick="return View();" /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="button4"  value="<%=resource.getString("circulation.cir_member_reg.delete")%>" class="btn" onclick="return Delete();"  /></td></tr>
                         <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="button" id="button5"  value="<%=resource.getString("circulation.cir_member_reg.back")%>" class="btn" onclick="return quit()"/></td></tr>
 

                </table>
       

   
   
<input type="hidden" id="button" name="button" value=""/>







</td></tr>

                <tr><td class="mess">
                                <%
          if (msg!=null)
          {
        %>


        <p class="mess">  <%=msg%></p>

        <%
        }
        %>
         <%if (msg1!=null)
          {
        %>


        <p class="err">  <%=msg1%></p>

        <%
        }
        %>



                    </td></tr>

    </table>
        </div>
  
</html:form>

</body>

    
</html>
