
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>OPAC</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>
<style type="text/css">

body
{
   background-color: #FFFFFF;
   color: #000000;
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
    <%
    String library_id = (String)session.getAttribute("library_id");
    String sublibrary_id = (String)session.getAttribute("memsublib");
     if(sublibrary_id==null)sublibrary_id=   (String)session.getAttribute("sublibrary_id");
if(sublibrary_id==null)sublibrary_id="all";
%>
    
    
</head>
<link rel="StyleSheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body style="margin: 0px 0px 0px 0px;">
    
        <table align="center" id="tab" border="0" class="datagrid" dir="<%=rtl%>" width="100%" bgcolor="white">
            <tr  ><td   valign="bottom" height="10%"   >





                                <img src="<%=request.getContextPath()%>/images/bp.PNG" alt="banner space"  border="0" align="<%=align%>" dir="<%=rtl%>" id="Image1" style="height:40px;width:150px;">
                                <br>


                            </td>
                            <td align="right" >
                                <img src="<%=request.getContextPath()%>/images/logo.png" alt=""  border="0" align="top" id="Image1" style="height:70px;width:160px;">
                </td>

            </tr>
            <tr>
                <td colspan="2">



       <a href="OpacLib.do?name=simple" dir="<%=rtl%>"   style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.simple")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=browse"  dir="<%=rtl%>"  style="font-weight: bold;text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.browse")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=additional" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.additional")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=advance" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.advance")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=isbn" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.isbn")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=callno" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.callno")%></a>&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/OPAC/OpacLib.do?name=accno" dir="<%=rtl%>"  style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.accessionno")%></a>&nbsp;|&nbsp;<a  href="./OpacLib.do?name=newarrival" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px"><%=resource.getString("opacmainframe.mframe.newarrivals")%></a>&nbsp;|&nbsp;<a  href="../OPAC/Notice.do" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" dir="<%=rtl%>"><%=resource.getString("opacmainframe.mframe.notices")%></a>&nbsp;|&nbsp;<a  href="../OPAC/Locations.do" style="text-decoration:none;font-family: Arial;font-size: 11px;color:blue" dir="<%=rtl%>"  ><%=resource.getString("opacmainframe.mframe.location")%></a>&nbsp;|&nbsp;<a  href="../OPAC/OpacLib.do?name=feedback" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.mframe.feedback")%></a>&nbsp;|&nbsp;
        <b style="color:blue;letter-spacing: 1px;text-decoration:none;font-family: Arial;font-size: 11px">
                  <a href="../OPAC/OpacLib.do?name=newmember" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px"   dir="<%=rtl%>"  ><%=resource.getString("opacmainframe.mframe.memberregistration")%></a>&nbsp;|&nbsp;<a  href="../OPAC/OpacLib.do?name=myaccount&p=t" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px"   dir="<%=rtl%>"  >My Account</a>&nbsp;|&nbsp;<a href="../OPAC/OPACmain1.jsp"  style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><b style="color:blue" dir="<%=rtl%>" > <%=resource.getString("opacmainframe.header.home")%></b></a>&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/help.jsp"  dir="<%=rtl%>"  style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><b><%=resource.getString("opacmainframe.header.help")%></b></a>&nbsp;|&nbsp;
                    <%if(library_id==null){%>
      <a href="<%=request.getContextPath()%>/login.jsp" onclick=""  style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opacmainframe.header.exit")%></a>
 <%}else{%><a href="<%=request.getContextPath()%>/admin/main.jsp" onclick=""  style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.exit")%></a><%}%>

                    </b>



      </td>
            </tr>

            <tr   style="background-color: #BFDBFF;height: 50px;  background-image: url('<%=request.getContextPath()%>/images/banner_bg.jpg'); border:  solid 1px black;margin: 0px 0px 0px 0px"><td colspan="2" style="font-style: italic;font-size: 20px;color:white;" valign="middle" align="center">
           "Online Public Access Catalogue"</td>


            </tr>
        </table>
</body>
</html>
