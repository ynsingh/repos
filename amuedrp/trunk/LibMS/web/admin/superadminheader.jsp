
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


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

               <table align="center" width="100%" height="100%" style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;border-collapse: collapse;  border-spacing: 0;background-color:black;color:white " dir="<%=rtl%>" >
                   <tr><td class="homepage"  align="left" valign="top"><b>      &nbsp;      Library Management System
               &nbsp; EdRP Mission , NMEICT Project</b></td><td class="homepage" align="right" valign="top">

                    <a style="color: white;font-weight: bold;">Welcome WebAdmin &nbsp|&nbsp;</a><a style="color: white;font-weight: bold;" href="./logout.do">Signout</a>&nbsp;

               </td></tr>
                 <%--  <tr ><td class="homepage" style="color:blue;" align="center" height="60px" width="40%"  >



              <b><font size="+4">WebAdmin Module</font></b>

          </td><td valign="center" class="homepage" style="border-left: solid 1px cyan;color:black;" align="left">
              
          </td>
          <td >
             
          
          </td>
                                     </tr>--%>
                                    
            
        </table>
</body>
</html>
