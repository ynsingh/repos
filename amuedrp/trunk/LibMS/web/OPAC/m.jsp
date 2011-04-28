
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
    a{
        text-decoration: none;
       
        font-size: 12px;
        color:brown;
         font-family:Tahoma;
        
}
    </style>
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
    <script language="javascript">
        function fun()
        {

            document.form1.submit();
        }
    </script>


    </head>
    <body  >
         <form method="post" action="mRTL.jsp" name="form1">
         
             <table  width="150px" valign="top" border="0">
    
    <tr>
     <td/>
  </tr>
  <tr dir="<%=rtl%>" align="<%=align%>"><td><img src="../images/orange_sq_but.gif" dir="<%=rtl%>"  align="<%=align%>"/><a  href="./OpacLib.do?name=newarrival" target="f3" dir="<%=rtl%>" ><%=resource.getString("opacmainframe.mframe.newarrivals")%></a></td></tr>
  <tr dir="<%=rtl%>" align="<%=align%>"><td><img src="../images/orange_sq_but.gif" dir="<%=rtl%>"  align="<%=align%>"/><a href="../OPAC/OpacLib.do?name=newmember" target="f3" dir="<%=rtl%>"  >Member Registration</a></td></tr>
  <tr dir="<%=rtl%>" align="<%=align%>"><td><img src="../images/orange_sq_but.gif" dir="<%=rtl%>"  align="<%=align%>"/><a  href="../OPAC/OpacLib.do?name=myaccount" target="_parent" dir="<%=rtl%>" ><%=resource.getString("opacmainframe.mframe.myaccount")%></a></td></tr>
  <tr dir="<%=rtl%>" align="<%=align%>"><td><img src="../images/orange_sq_but.gif" dir="<%=rtl%>"  align="<%=align%>"/><a  href="http://catalog.loc.gov" target="f3" dir="<%=rtl%>"  ><%=resource.getString("opacmainframe.mframe.libofcong")%></a></td></tr>
  <tr dir="<%=rtl%>" align="<%=align%>"><td><img src="../images/orange_sq_but.gif" dir="<%=rtl%>"  align="<%=align%>"/><a  href="../OPAC/Notice.do" target="f3" dir="<%=rtl%>"  ><%=resource.getString("opacmainframe.mframe.notices")%></a></td></tr>
  <tr dir="<%=rtl%>" align="<%=align%>"><td><img src="../images/orange_sq_but.gif" dir="<%=rtl%>"  align="<%=align%>"/><a  href="../OPAC/Locations.do" target="f3" dir="<%=rtl%>"  ><%=resource.getString("opacmainframe.mframe.location")%></a></td></tr>
  <tr dir="<%=rtl%>" align="<%=align%>"><td><img src="../images/orange_sq_but.gif" dir="<%=rtl%>"  align="<%=align%>"/><a  href="../OPAC/OpacLib.do?name=feedback" target="f3" dir="<%=rtl%>" ><%=resource.getString("opacmainframe.mframe.feedback")%></a></td></tr>


       </table>
</form>
    </body>


</html>

