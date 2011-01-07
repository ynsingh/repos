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
    boolean page=true;
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
    <body>
        <form method="post" action="language.jsp" target="_top" name="form1">
            <table border=0 cellpadding=0 cellspacing=0 width="100%">
            <%if(!page.equals(true)){%>
            <tr>
      <td align=right  height="100" valign="bottom">
          <table width="100%"><tr>

      <td style="color:white;">
      <a href="/LibMS-Struts/OPAC/OPACmain.jsp" target="_top" style="text-decoration:none;font-family: Arial;color:#c0003b;font-size: 13px">
      <b style="color:#c0003b"><%=resource.getString("opacmainframe.header.home")%></a>&nbsp;|&nbsp;</b>
            <a href="help.html" target="f3" style="text-decoration:none;font-family: Arial;color:#c0003b;font-size: 13px" >
      <b> &nbsp;&nbsp;<%=resource.getString("opacmainframe.header.help")%></b></a>&nbsp;|&nbsp;
            <a href="/LibMS-Struts/admin/main.jsp" target="_top" style="text-decoration:none;font-family: Arial;color:#c0003b;font-size: 13px" >
      <b> &nbsp;&nbsp;<%=resource.getString("opacmainframe.header.exit")%></b></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


       </td><td>


       <td align="right" width="" valign="bottom">


           <p style="font-family: Tahoma;color:brown;font-size: 28px" dir="rtl" align="right">   &nbsp;&nbsp;<img src="/LibMS-Struts/images/bp.PNG" alt="logo1" width="150" height="65"  align="top" dir="rtl">
     <%=resource.getString("opacmainframe.header.opac")%>


    </p>

                </td>
            </tr>
            <tr dir="rtl" align="right" style="top:2%; font-family:Tahoma;font-size:15px;"><%=resource.getString("login.message.selectlanguage")%><select name="button" onchange="fun()" id="button"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select></tr>
          </table>

    </td>

  </tr>
  <tr align="right" dir="rtl"><td align="right" colspan="2" dir="rtl" height="25px" width="600px" bgcolor="#c0003b">
<a href="OpacLib.do?name=simple" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white"> <%=resource.getString("opacmainframe.header.simple")%></b></a>
 <a href="OpacLib.do?name=browse" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white">&nbsp;|&nbsp;<%=resource.getString("opacmainframe.header.browse")%></b> </a>
 <a href="OpacLib.do?name=additional" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white">&nbsp;|&nbsp;<%=resource.getString("opacmainframe.header.additional")%></b></a>
<a href="OpacLib.do?name=advance" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white">&nbsp;|&nbsp;<%=resource.getString("opacmainframe.header.advance")%></b></a>
          <a href="OpacLib.do?name=isbn" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white">&nbsp;|&nbsp;<%=resource.getString("opacmainframe.header.isbn")%></b></a>
            <a href="OpacLib.do?name=callno" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white">&nbsp;|&nbsp;<%=resource.getString("opacmainframe.header.callno")%></b></a>
           <a href="OpacLib.do?name=accno" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white">&nbsp;|&nbsp;<%=resource.getString("opacmainframe.header.accessionno")%></b></a>
           
      </td>
  </tr>

   <%}else{%>

     <tr>
    <td align=left  height="100" valign="left">
        <table width="100%" align="right"><tr><td>
    <p style="font-family: Tahoma;color:brown;font-size: 28px">   &nbsp;&nbsp;<img src="/LibMS-Struts/images/bp.PNG" alt="logo" width="150" height="65">
     <%=resource.getString("opacmainframe.header.opac")%>


    </p>
                </td><td>


                <td align="right" width="" valign="bottom">
            <a href="../OPAC/OPACmain.jsp" target="_top" style="text-decoration:none;font-family: Arial;color:#c0003b;font-size: 13px" >
      <b style="color:#c0003b"> <%=resource.getString("opacmainframe.header.home")%></b></a>&nbsp;|&nbsp;
            <a href="help.html" target="f3" style="text-decoration:none;font-family: Arial;color:#c0003b;font-size: 13px" >
      <b> &nbsp;&nbsp;<%=resource.getString("opacmainframe.header.help")%></b></a>&nbsp;|&nbsp;
            <a href="/LibMS-Struts/admin/main.jsp" target="_top" style="text-decoration:none;font-family: Arial;color:#c0003b;font-size: 13px" >
      <b> &nbsp;&nbsp;<%=resource.getString("opacmainframe.header.exit")%></b></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;


        </td>
            </tr>

            <tr  dir="rtl" align="left" style="top:2%; font-family:Tahoma;font-size:15px;"><%=resource.getString("login.message.selectlanguage")%><select dir="ltr" name="button" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select></tr>

        </table>

    </td>








  </tr>
  <tr><td align="left" height="25px" width="600px" bgcolor="#c0003b">

  <a href="OpacLib.do?name=simple" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white"> &nbsp;&nbsp;<%=resource.getString("opacmainframe.header.simple")%></a>&nbsp;|&nbsp;</b>
 <a href="OpacLib.do?name=browse" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white"><%=resource.getString("opacmainframe.header.browse")%> </a>&nbsp;|&nbsp;</b>
 <a href="OpacLib.do?name=additional" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white"><%=resource.getString("opacmainframe.header.additional")%></a>&nbsp;|&nbsp;</b>
<a href="OpacLib.do?name=advance" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white"><%=resource.getString("opacmainframe.header.advance")%></a>&nbsp;|&nbsp;</b>
          <a href="OpacLib.do?name=isbn" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white"><%=resource.getString("opacmainframe.header.isbn")%></a>&nbsp;|&nbsp;</b>
            <a href="OpacLib.do?name=callno" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white"><%=resource.getString("opacmainframe.header.callno")%></a>&nbsp;|&nbsp;</b>
           <a href="OpacLib.do?name=accno" target="f3" style="text-decoration:none;font-family: Arial;color:white;font-size: 13px" >
      <b style="color:white"><%=resource.getString("opacmainframe.header.accessionno")%></b></a>
      </td>
  </tr>

             <%}%>

            </table>

        </form>

    </body>
</html>

