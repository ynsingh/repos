<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID 
-->

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
 <jsp:include page="../election_manager/login.jsp" flush="true" />

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%
//String library_id=(String)session.getAttribute("library_id");
String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("msg1");
%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EMS : Manage Election </title>
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


function state()
{
    window.status=' ';
}


function help1()
{
    window.status=' ';
}

     function help()
     {
         window.status='Press F1 for help';
         statwords('Please enter any unique number or string like 123,asd etc.')
     }

<%-- function Add(val)
{
    
    

}

function View()
{
    return check1();
    var buttonvalue="View";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
function Update()
{
    return check1();
    var buttonvalue="Update";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
function Delete()
{
    return check1();
    var buttonvalue="Block";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
function Preview()
{
    return check1();
    var buttonvalue="Preview";
    document.getElementById("button").setAttribute("value", buttonvalue);
    return true;
}
--%>
function check1(val)
{


var buttonvalue=val;
    document.getElementById("button").setAttribute("value", buttonvalue);
  

    if(document.getElementById('electionid').value=="")
    {
        alert("Enter Election ID<%--<%=resource.getString("systemsetup.manage_faculty.enterfacultyid")%>--%>...");

        document.getElementById('electionid').focus();

        return false;
    }

var aURL = document.getElementById('electionid').value;
var aPosition = aURL.indexOf("0");

if(aPosition==0)
{
    alert("Election Id Cannot Start with 0");

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



  function quit()
  {

      window.location="<%=request.getContextPath()%>/election_manager/Election_List.jsp";
      return false;
  }



    </script>
</head>
<body dir="<%=rtl%>">
 
    <html:form method="post"   action="/electionview">
       
<div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <table border="1" dir="<%=rtl%>" class="table" width="400px" height="200px" align="center">

  
                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("manage_elction")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center"> <br/>
                <table dir="<%=rtl%>" cellspacing="10px">

                    <tr><td dir="<%=rtl%>" rowspan="6" class="txt2"><%=resource.getString("enterelectionid")%><%----%><br><br>
                            <html:text property="electionId" styleId="electionid" name="DepActionForm" onkeypress="return isNumberKey(event);" onfocus="return help()" onblur="return help1()"/>
                        </td><td dir="<%=rtl%>" width="150px" align="center"> <input type="submit" class="btn" id="Button1"  value="<%=resource.getString("addd")%><%----%>" onclick="return check1('Add');" /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button2" class="btn"  value="<%=resource.getString("update")%>" onclick="return check1('Update');"  /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button3"  value="<%=resource.getString("view")%>" onclick="return check1('View');" class="btn"  /></td></tr>
                    <%--<tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button4"  value="<%=resource.getString("blocked")%>" onclick="return check1('Block');" class="btn" /></td></tr>--%>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="submit" id="Button4"  value="<%=resource.getString("preview_blt")%>" onclick="return check1('Preview');" class="btn" /></td></tr>
                    <tr><td dir="<%=rtl%>" width="150px" align="center"><input type="button" id="Button5"  value="<%=resource.getString("back")%>" class="btn" onclick="return quit()"/></td></tr>
                       
 

                </table>
       

    <%--<input type="hidden" name="library_id" value="<%=library_id%>">--%>
   

    <input type="hidden" id="button" name="button" />






</td></tr>
          <tr><td class="mess">
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
                             </td></tr

       <%--<tr><td align="justify"><font color="blue" size="-1"><b><%=resource.getString("systemsetup.manage_notice.example")%>:</b> sc <%=resource.getString("systemsetup.manage_faculty.forsc")%>, art <%=resource.getString("systemsetup.manage_faculty.forart")%>, <%=resource.getString("systemsetup.manage_faculty.iffacexists")%>.</font></td></tr>--%>
    </table>
        </div>
  
</html:form>

</body>

    
</html>
