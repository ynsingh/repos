
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Untitled Page</title>
<link rel="stylesheet" href="common.css">
<style type="text/css">
    a{
      color:#c0003b;
        text-decoration:none;
        font-size:12px;
        font-weight: bold;

}
a:hover
{
    font-size:13px;
color: pink ;
        text-decoration:none;

        font-weight: bold;
}
    </style>
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
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
    <script language="javascript">
        function fun()
        {

            document.form1.submit();
        }
    </script>


    </head>
    <body style="margin:0px 0px 0px 0px" >
         <form method="post" action="mRTL.jsp" name="form1">
<table cellpadding="3px">
    <%if(!page.equals(true)){%>
    <tr>
     <td/>
  </tr>
  <tr align="right"><td><a class="menu" href="./OpacLib.do?name=newarrival" target="f3" dir="ltr" ><%=resource.getString("opacmainframe.mframe.newarrivals")%></a></td><td><img src="../images/orange_sq_but.gif"  align="right"/></td></tr>
  <tr  align="right"><td><a class="menu" href="../OPAC/OpacLib.do?name=journal" target="f3" dir="ltr" ><%=resource.getString("opacmainframe.mframe.journals")%></a></td><td><img src="../images/orange_sq_but.gif"/></td></tr>
  <tr  align="right"><td><a class="menu" href="../OPAC/member.jsp" target="f3" dir="ltr" ><%=resource.getString("opacmainframe.mframe.myaccount")%></a></td><td><img src="../images/orange_sq_but.gif"/></td></tr>
  <tr  align="right"><td><a class="menu" href="http://catalog.loc.gov" target="f3" dir="ltr" ><%=resource.getString("opacmainframe.mframe.libofcong")%></a></td><td><img src="../images/orange_sq_but.gif"/></td></tr>
  <tr  align="right"><td><a class="menu" href="../OPAC/Notice.do" target="f3" dir="ltr" ><%=resource.getString("opacmainframe.mframe.notices")%></a></td><td><img src="../images/orange_sq_but.gif"/></td></tr>
  <tr  align="right"><td><a class="menu" href="../OPAC/Locations.do" target="f3" dir="ltr" ><%=resource.getString("opacmainframe.mframe.location")%></a></td><td><img src="../images/orange_sq_but.gif"/></td></tr>
  <tr><td  align="right"><a class="menu" href="../OPAC/feedback.jsp" target="f3" dir="ltr" ><%=resource.getString("opacmainframe.mframe.feedback")%></a></td><td><img src="../images/orange_sq_but.gif"/></td></tr>

  <%}else{%>


  <tr>
     <td/>
  </tr>
  <tr align="left"><td><img src="../images/orange_sq_but.gif"/></td><td><a class="menu" href="./OpacLib.do?name=newarrival" target="f3" dir="rtl" ><%=resource.getString("opacmainframe.mframe.newarrivals")%> </a></td></tr>
  <tr  align="left"><td><img src="../images/orange_sq_but.gif"/></td><td><a class="menu" href="../OPAC/OpacLib.do?name=journal" target="f3" dir="rtl" ><%=resource.getString("opacmainframe.mframe.journals")%></a></td></tr>
  <tr  align="left"><td><img src="../images/orange_sq_but.gif"/></td><td><a class="menu" href="../OPAC/member.jsp" target="f3" dir="rtl" ><%=resource.getString("opacmainframe.mframe.myaccount")%>  </a></td></tr>
  <tr  align="left"><td><img src="../images/orange_sq_but.gif"/></td><td><a class="menu" href="http://catalog.loc.gov" target="f3" dir="rtl" ><%=resource.getString("opacmainframe.mframe.libofcong")%> </a></td></tr>
  <tr  align="left"><td><img src="../images/orange_sq_but.gif"/></td><td><a class="menu" href="../OPAC/Notice.do" target="f3" dir="rtl" ><%=resource.getString("opacmainframe.mframe.notices")%> </a></td></tr>
  <tr  align="left"><td><img src="../images/orange_sq_but.gif"/></td><td><a class="menu" href="../OPAC/Locations.do" target="f3" dir="rtl" ><%=resource.getString("opacmainframe.mframe.location")%></a></td></tr>
  <tr><td><img src="../images/orange_sq_but.gif"/></td><td  align="left"><a class="menu" href="../OPAC/feedback.jsp"  target="f3" dir="rtl" ><%=resource.getString("opacmainframe.mframe.feedback")%></a></td></tr>




   <%}%>

       </table>
</form>
    </body>


</html>

