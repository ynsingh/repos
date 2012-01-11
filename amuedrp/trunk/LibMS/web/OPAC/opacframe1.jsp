<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
       <title>Opac Multilingual</title>
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
     //   System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

String library_id=(String)session.getAttribute("library_id");
       
  
%>


  
    <script language="javascript">
        function fun()
        {

            document.form1.submit();

        }


     
    </script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
    </head>
    <body background="<%=request.getContextPath()%>/images/body-bg.png">
        <form method="post" action="language1.jsp" target="_top" name="form1">
             <table border=0 cellpadding=0 cellspacing=0 width="100%" dir="<%=rtl%>" align="<%=align%>">
            
     <tr>
    <td  valign="left" dir="<%=rtl%>" align="<%=align%>">
        <table width="100%"  dir="<%=rtl%>" align="<%=align%>" border="0">
            <tr><td align="right" colspan="2"><%--<img src="<%=request.getContextPath()%>/images/opac_lib.PNG" alt="logo1"   align="top" dir="<%=rtl%>" >--%>&nbsp;
                    <b style="color:white;letter-spacing: 1px;text-decoration:none;font-family: Arial;color:white;font-size: 11px">
                    <a  href="http://catalog.loc.gov" dir="<%=rtl%>" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" >Library of Congress</a>&nbsp;|&nbsp;<a href="../OPAC/OpacLib.do?name=newmember" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px"  target="f3" dir="<%=rtl%>"  ><%=resource.getString("opacmainframe.mframe.memberregistration")%></a>&nbsp;|&nbsp;<a  href="../OPAC/OpacLib.do?name=myaccount" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px"  target="f3" dir="<%=rtl%>"  >My Account</a>&nbsp;|&nbsp;<a href="../OPAC/OPACmain1.jsp" target="_top" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" ><b style="color:white" dir="<%=rtl%>" > <%=resource.getString("opacmainframe.header.home")%></b></a>&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/ModuleHelp/MainPage.html" target="f3" dir="<%=rtl%>"  style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" ><b><%=resource.getString("opacmainframe.header.help")%></b></a>&nbsp;|&nbsp;
                    <%if(library_id==null){%>
      <a href="<%=request.getContextPath()%>/login.jsp" onclick="" target="_top" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opacmainframe.header.exit")%></a>
 <%}else{%><a href="<%=request.getContextPath()%>/admin/main.jsp" onclick="" target="_top" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" ><%=resource.getString("opacmainframe.header.exit")%></a><%}%>

                    </b>
                </td></tr>
            <tr><td align="left" colspan="2">
                   <p dir="<%=rtl%>" style="font-family: Tahoma;color:white;font-size: 20px"><b>
   <span style="color:white;font-size: 30px;font-family: arial;font-weight: bold">Lib</span><span style="color:pink;font-size: 30px;font-family: arial;font-weight: bold">MS</span>&nbsp;(<%=resource.getString("opacmainframe.header.opac")%>)</b>


    </p>
                </td>


               

            </tr>



        </table>

    </td>








  </tr>
  <tr><td dir="<%=rtl%>" align="<%=align%>" height="25px"  <%--bgcolor="#c0003b"--%> valign="bottom">
          <table width="100%" dir="<%=rtl%>"><tr><td>

      &nbsp;&nbsp;  
      <b style="color:white;letter-spacing: 1px;font-weight: bold;text-decoration:none;font-family: Arial;color:white;font-size: 11px">
       <a href="OpacLib.do?name=simple" dir="<%=rtl%>"  target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" ><%=resource.getString("opacmainframe.header.simple")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=browse" target="f3" dir="<%=rtl%>"  style="font-weight: bold;text-decoration:none;font-family: Arial;color:white;font-size: 11px" ><%=resource.getString("opacmainframe.header.browse")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=additional" target="f3" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" ><%=resource.getString("opacmainframe.header.additional")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=advance" target="f3" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" ><%=resource.getString("opacmainframe.header.advance")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=isbn" target="f3" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" ><%=resource.getString("opacmainframe.header.isbn")%></a>&nbsp;|&nbsp;<a href="OpacLib.do?name=callno" target="f3" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" ><%=resource.getString("opacmainframe.header.callno")%></a>&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/OPAC/OpacLib.do?name=accno" dir="<%=rtl%>" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" ><%=resource.getString("opacmainframe.header.accessionno")%></a>&nbsp;|&nbsp;<a  href="./OpacLib.do?name=newarrival" target="f3" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px"><%=resource.getString("opacmainframe.mframe.newarrivals")%></a>&nbsp;|&nbsp;<a  href="../OPAC/Notice.do" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" target="f3" dir="<%=rtl%>"><%=resource.getString("opacmainframe.mframe.notices")%></a>&nbsp;|&nbsp;<a  href="../OPAC/Locations.do" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" dir="<%=rtl%>"  ><%=resource.getString("opacmainframe.mframe.location")%></a>&nbsp;|&nbsp;<a  href="../OPAC/OpacLib.do?name=feedback" target="f3" dir="<%=rtl%>" style="text-decoration:none;font-family: Arial;color:white;font-size: 11px" ><%=resource.getString("opacmainframe.mframe.feedback")%></a>
      </b>


      </td>
            <td dir="<%=rtl%>" align="<%=align%>" style="top:2%; font-family:Tahoma;font-size:11px;" ><b style="color:white" dir="<%=rtl%>"><%=resource.getString("login.message.selectlanguage")%></b><select dir="<%=rtl%>" class="selecthome" name="button" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select> </td>
            </table></td>
  </tr>


            </table>

        </form>

    </body>
</html>

