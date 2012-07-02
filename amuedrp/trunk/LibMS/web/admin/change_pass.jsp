<%-- 
    Document   : manage_superadmin
    Created on : Jan 12, 2011, 7:34:19 PM
    Author     : System Administrator
--%>
<%@page import="java.util.*,java.io.*,java.net.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link rel="Stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/helpdemo.js"></script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
        <%
try{
if(session.getAttribute("library_id")!=null){
System.out.println("library_id"+session.getAttribute("library_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }
String contextPath = request.getContextPath();
%>
        <script language="javascript" type="text/javascript">
               function check()
    {
 var u=document.getElementById('user_id1');
        if(u.value=="")
            {
                alert("LoginId should not be blank");
                 document.getElementById('user_id1').focus();
                return false;
            }
               


        var x=document.getElementById('password2');
        if(x.value=="")
            {
                alert("Existing Password should not be blank");
                 document.getElementById('password2').focus();
                return false;
            }
     if(document.getElementById('password3').value=="")
    {
        alert("Enter New password...");

        document.getElementById('password3').focus();

        return false;
    }
   
      var x1=document.getElementById('password2');
        var x2=document.getElementById('password3');
        if(x1.value!=x2.value)
            {
                document.getElementById('password2').value="";
                document.getElementById('password3').value="";
                alert("Password Mismatch");
                document.getElementById('password3').focus();
                return false;
            }
            
            
                return true;




               


    }

function clearme()
{
   
   
   
    document.getElementById("user_id1").value = "";
    document.getElementById("password2").value = "";
    document.getElementById("password3").value = "";
    
    return true;
}


function loadHelp()
{
    window.status="Press F1 for Help";
}

            </script>
    </head>
    <body onload="loadHelp()">
        <html:form styleId="form1"  action="/manage.do" method="post">
            <table  align="<%=align%>" dir="<%=rtl%>" width="50%" height="150px" style="background-color: white;border:#C7C5B2 1px solid;margin:0px 0px 0px 0px;">
                <tr><td dir="<%=rtl%>" style="background-color: #7697BC;color:white;" colspan="2" class="btn1" height="30px"><b>Change Login Password of User</b> </td></tr>
                <tr><td width="30%" class="btn3" dir="<%=rtl%>" align="<%=align%>">LoginId of User</td><td>
                        
                        <html:text styleId="user_id1" property="user_id1" value="" readonly="false"/>
                    
                    </td></tr>
                <tr><td class="btn3" dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.managesuperadminaccount.newpassword")%></td><td><html:password styleId="password2" property="password2"/>
                          </td></tr>
           <tr><td class="btn3" dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.managesuperadminaccount.repassword")%></td><td><html:password styleId="password3" property="password1"  onblur="return check();"/>
                
            </td></tr>
           <tr><td></td><td dir="<%=rtl%>" width="300px"><br><html:submit styleClass="btn2"  onclick="return check();"> <%=resource.getString("login.managesuperadminaccount.changepassword")%></html:submit>
                   <input type="button" onclick="return clearme();"   class="btn2" dir="<%=rtl%>" value="Clear" />
            </td></tr>
        <tr><td></td><td  align="<%=align%>" width="200px">
                  <br>
   <%
   String   message="";
     message =(String) request.getAttribute("msg");
    if(message!=null) {%>
            <font size="2px" dir="<%=rtl%>" align="<%=align%>" color=red><b><%=message%></b></font>
         <%  }
    %>

                <br><br></td></tr>
    </table>
        </html:form>
    </body>
</html>
