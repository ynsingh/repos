

<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";

    String align="left";

   //  String webadmin=getServletContext().getInitParameter("webmail");
   //  String webpass=getServletContext().getInitParameter("webpass");
%>
<%
try{
locale1=(String)session.getAttribute("locale");
sessionId = session.getId().toString();
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
    <body style="background-color:#e0e8f5;margin: 0px 0px 0px 0px;">
<%--<table height="50px" border="0px" align="center" width="100%">
<link rel="stylesheet" href="/LibMS/css/page.css"/>
               <tr ><td class="homepage" valign="top">
        Powered By<br>
        <img src="<%=request.getContextPath()%>/images/apache-struts-logo.jpg" height="60px" width="100px"/>


    </td><td class="homepage" valign="top"><b>Help Center</b> &nbsp;About Us&nbsp;|&nbsp;Advertising&nbsp;|&nbsp;User Agreement&nbsp;|&nbsp;Copyright Policy&nbsp;|&nbsp;<a href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx">NME-ICT ERP Mission</a><br/><%=resource.getString("developedby")%> &nbsp;<br/> &copy; <%=resource.getString("login.message.footer")%>
        <br>follow us : <img src="<%=request.getContextPath()%>/images/blog.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/facebook.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/twitter.jpeg" height="16px" width="20px"/>
      <a href="http://www.youtube.com/user/DrAasimZafar?blend=15&ob=5#p/u/0/COwssqRU9Ao"><img src="<%=request.getContextPath()%>/images/youtube.jpeg" height="16px" width="40px"/></a>
    </td><td class="homepage" align="right" valign="top">Based on Open Source LibMS at<br>
         <a href="http://sourceforge.net/projects/libms/">   <img src="/LibMS/images/sflogo.png"/></a>
     </td></tr>
                </table>--%>
    </body>