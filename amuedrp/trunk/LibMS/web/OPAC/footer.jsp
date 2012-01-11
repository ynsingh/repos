

 <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body style="margin: 0px 0px 0px 0px">
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
<table align="center" width="100%" style="background-image: url('<%=request.getContextPath()%>/images/footerbg.gif')">
    <tr>
    <%--  <%if(page.equals(true)){%>
      <td colspan="2" align="center" height="50px" valign="top">

                    <span>&copy; <%=resource.getString("login.message.footer")%></span>
                </td></tr>


        <%}else{%>
        <tr><td colspan="2" align="center" height="50px" valign="bottom">

                <span>&copy; <%=resource.getString("login.message.footer")%></span>
                </td></tr>


        <%}%>--%>
    
                <td class="homepage" valign="top">

        <%--Powered By<br>
        <img src="<%=request.getContextPath()%>/images/apache-struts-logo.jpg" height="60px" width="100px"/>--%>


    </td><td class="homepage" valign="top" >
        <b style="color:white">
        <%--<b>Help Center</b> &nbsp;About Us&nbsp;|&nbsp;Advertising&nbsp;|&nbsp;User Agreement&nbsp;|&nbsp;Copyright Policy&nbsp;|&nbsp;<a href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx">NME-ICT ERP Mission</a>--%><%=resource.getString("developedby")%> &nbsp;<br/> &copy; <%=resource.getString("login.message.footer")%>
        <%--<br>follow us :--%><%-- <img src="<%=request.getContextPath()%>/images/blog.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/facebook.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/twitter.jpeg" height="16px" width="20px"/>--%>
      <%--<a href="http://www.youtube.com/user/DrAasimZafar?blend=15&ob=5#p/u/0/COwssqRU9Ao"><img src="<%=request.getContextPath()%>/images/youtube.jpeg" height="16px" width="40px"/></a>--%>
        </b>
    </td><td class="homepage" align="right" valign="top">
         <b style="color:white">Based on Open Source LibMS at<br></b>
         <a href="http://sourceforge.net/projects/libms/">   <img src="/LibMS/images/sflogo.png"/></a>
    </td>       

    </tr>
</table>

</body></html>
