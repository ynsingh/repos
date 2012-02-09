<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.util.*,java.io.*,java.net.*"%>
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
    String regid="";
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
    regid = resource.getString("registrationid");
    System.out.println("Reg id="+regid);
    %>

  <%--  <span style="font-family:Arial;color:brown;font-size:22px;">&nbsp;&nbsp;<%=resource.getString("electionmanagement")%></span>--%>

                    
                <table width="100%" height="50px;" border="0px" style="margin:0px 0px 0px 0px" dir="<%=rtl%>">

    <tr dir="<%=rtl%>"><td valign="bottom" dir="<%=rtl%>">

          <%--  <span align="<%=align%>"  style="font-family:Arial;font-weight: bold;font-size:22px;" dir="<%=rtl%>">&nbsp;&nbsp;<%=resource.getString("electionmanagement")%></span>--%>
           <img src="<%=request.getContextPath()%>/images/logo.bmp" alt="banner space"  border="0" align="top" id="Image1">
        </td>
                    <td dir="<%=rtl%>"><p align="center"  style="font-family:Tempus Sans ITC;color:brown;font-size:20px;" dir="<%=rtl%>"><span dir="<%=rtl%>"><b> </b></span></td>

                    <td align="right" width="250px" valign="top" dir="<%=rtl%>">

                        <%--<span style="font-family:arial;color:brown;font-size:12px;" dir="<%=rtl%>"><b dir="<%=rtl%>"><%=resource.getString("login.hello")%> [<%=user%>]&nbsp;|<a href="<%=contextPath%>/logout.do" style="text-decoration: none;color:brown" dir="<%=rtl%>">&nbsp;<%=resource.getString("login.signout")%></a></b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>

                         <%--   <span  dir="<%=rtl%>" style="font:8pt Verdana;"><%=resource.getString("login.hello")%> [<%=user%>]&nbsp;|<a href="<%=request.getContextPath()%>/logout.do" style="text-decoration: none;color:brown" dir="<%=rtl%>">&nbsp;<%=resource.getString("login.signout")%></a></span>
                        <br>
--%>
                         <img src="<%=request.getContextPath()%>/images/logo.png" alt="" height="65px" width="160px" border="0" align="top" id="Image1" style="">

                     </td>
                </tr>




                </table>
              
                    <hr>