<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID 
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>

 <jsp:include page="header.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
String library_id=(String)session.getAttribute("library_id");
String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("msg1");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Manage SubLibrary </title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
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
    if(document.getElementById('sublibrary_id').value=="")
    {
        alert("<%=resource.getString("systemsetup.manage_library.entersubliid")%>");

        document.getElementById('sublibrary_id').focus();

        return false;
    }

  }





  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }



    </script>
</head>
<body>
 
    <html:form method="post" onsubmit="return check1()" action="/libraryRegistration">
       
<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table dir="<%=rtl%>" border="1" class="table" width="400px" height="200px" align="center">

  
                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("systemsetup.manage_library.managesublib")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                <table dir="<%=rtl%>" cellspacing="10px">

                    <tr><td dir="<%=rtl%>" rowspan="5" class="txt2"><%=resource.getString("systemsetup.manage_library.entersubliid")%><br>
                            
                            <br>
                        <input type="text" id="sublibrary_id" name="sublibrary_id" value=""/><br>
                        </td><td dir="<%=rtl%>" width="150px" align="center"> <input type="submit" class="btn" id="Button1"  value="<%=resource.getString("systemsetup.manage_notice.add")%>" onclick="return Add();" /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button2" class="btn" value="<%=resource.getString("circulation.cir_member_reg.update")%>" onclick="return Update();"   /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button3"  value="<%=resource.getString("circulation.cir_member_reg.view")%>"  onclick="return View();" class="btn"  /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button4"  value="<%=resource.getString("circulation.cir_member_reg.delete")%>" onclick="return Delete();" class="btn" /></td></tr>
                         <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="button" id="Button5"  value="<%=resource.getString("circulation.cir_member_reg.back")%>" class="btn" onclick="return quit()"/></td></tr>
 

                </table>
       

    <input type="hidden" name="library_id" value="<%=library_id%>">
   
     <input type="hidden" id="button" name="button" />







</td></tr>
                <tr><td >
                         <%
          if (msg!=null)
          {
        %>
            

        <p class="mess" dir="<%=rtl%>" align="<%=align%>">  <%=msg%></p>
            
        <%
        }
        %>
         <%if (msg1!=null)
          {
        %>


        <p class="err" dir="<%=rtl%>" align="<%=align%>">  <%=msg1%></p>

        <%
        }
        %>

                    </td></tr>
                <tr><td align="justify"><font color="blue" size="-1"><b><%=resource.getString("systemsetup.manage_notice.example")%>:</b> cs <%=resource.getString("systemsetup.manage_library.forcosc")%>, phy <%=resource.getString("systemsetup.manage_library.forphysics")%>, <%=resource.getString("systemsetup.manage_library.ifsublib")%>/<%=resource.getString("systemsetup.manage_library.deptlib")%>/<%=resource.getString("systemsetup.manage_library.seminarlibexist")%>.</font></td></tr>
    </table>
        </div>
  
</html:form>

</body>

     
</html>
