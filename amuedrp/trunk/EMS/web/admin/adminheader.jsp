<%-- 
    Document   : header
    Created on : Nov 13, 2010, 4:45:02 PM
    Author     : System Administrator
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN "http://www.w3.org/TR/html4/strict.dtd">
<%@page import="java.util.*,java.io.*,java.net.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

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
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/logout.do?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }

String user=(String)session.getAttribute("username");
String pass=(String)session.getAttribute("pass");
 session.setAttribute("pass","t");
  String user_id=   (String)session.getAttribute("user_id");
String user_name=   (String) session.getAttribute("username");
  String question=  (String)request.getAttribute("question");
   String staff_id=  (String) request.getAttribute("staff_id");
   String contextPath = request.getContextPath();
%>

<table width="100%" height="50px;" border="0px" style="margin:0px 0px 0px 0px" dir="<%=rtl%>">

    <tr dir="<%=rtl%>"><td valign="bottom" dir="<%=rtl%>">

          <%--  <span align="<%=align%>"  style="font-family:Arial;font-weight: bold;font-size:22px;" dir="<%=rtl%>">&nbsp;&nbsp;<%=resource.getString("electionmanagement")%></span>--%>
           <img src="<%=request.getContextPath()%>/images/logo.bmp" alt="banner space"  border="0" align="top" id="Image1">
        </td>
                    <td dir="<%=rtl%>"><p align="center"  style="font-family:Tempus Sans ITC;color:brown;font-size:20px;" dir="<%=rtl%>"><span dir="<%=rtl%>"><b> SuperAdmin Module</b></span></td>

                    <td align="right" width="250px" valign="top" dir="<%=rtl%>">
                        
                        <%--<span style="font-family:arial;color:brown;font-size:12px;" dir="<%=rtl%>"><b dir="<%=rtl%>"><%=resource.getString("login.hello")%> [<%=user%>]&nbsp;|<a href="<%=contextPath%>/logout.do" style="text-decoration: none;color:brown" dir="<%=rtl%>">&nbsp;<%=resource.getString("login.signout")%></a></b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>

                            <span  dir="<%=rtl%>" style="font:8pt Verdana;"><%=resource.getString("login.hello")%> [<%=user%>]&nbsp;|<a href="<%=request.getContextPath()%>/logout.do" style="text-decoration: none;color:brown" dir="<%=rtl%>">&nbsp;<%=resource.getString("login.signout")%></a></span>
                        <br>

                         <img src="<%=request.getContextPath()%>/images/logo.png" alt="" height="65px" width="160px" border="0" align="top" id="Image1" style="">
                                
                     </td>
                </tr>




                </table>

