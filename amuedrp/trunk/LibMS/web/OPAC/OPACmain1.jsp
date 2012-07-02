
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html style="height: 100%">
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
    <script language="javascript">
        function fun()
        {

            document.form1.submit();
        }
        function resetload(){
            alert(window.document.getElementById('tab').style.height);

            window.document.height=document.getElementById('tab').style.height;
        }
        function doClick(){
var x, y, msg='';
document.getElementById('tab').style.height=window.document.height;
// for all except Explorer
if (self.innerHeight) {
x = self.innerWidth;
y = self.innerHeight;
msg += 'self.innerHeight/Width: '
+ y + ', ' + x + 'px';

// Explorer 6 Strict Mode
} else if (document.documentElement
&& document.documentElement.clientHeight) {
x = document.documentElement.clientWidth;
y = document.documentElement.clientHeight;
msg += 'document.documentElement.clientHeight/Width: '
+ y + ', ' + x + 'px';

// other Explorers
} else if (document.body) {
x = document.body.clientWidth;
y = document.body.clientHeight;
msg += 'document.body.clientHeight/Width: '
+ y + ', ' + x + 'px';
}

}
    </script>
    <style>
body
{
   overflow: hidden;
   height: auto;
}

        </style>

</head>
<link rel="StyleSheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body style="margin: 0px 0px 0px 0px;" onload="doClick();">
    <form method="post" action="opachomeRTL.jsp" name="form1">
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
            <a href="../OPAC/OpacLib.do?name=newmember" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px"   dir="<%=rtl%>"  ><%=resource.getString("opacmainframe.mframe.memberregistration")%></a>&nbsp;|&nbsp;<a  href="../OPAC/OpacLib.do?name=myaccount&amp;p=t" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px"   dir="<%=rtl%>"  >My Account</a>&nbsp;|&nbsp;<a href="../OPAC/OPACmain1.jsp" target="_top" style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><b style="color:blue" dir="<%=rtl%>" > <%=resource.getString("opacmainframe.header.home")%></b></a>&nbsp;|&nbsp;<a href="<%=request.getContextPath()%>/help.jsp"  dir="<%=rtl%>"  style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><b><%=resource.getString("opacmainframe.header.help")%></b></a>&nbsp;|&nbsp;
                    <%if(library_id==null){
    session.removeAttribute("libRs");
    %>
      <a href="<%=request.getContextPath()%>/login.jsp" onclick=""  style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("opacmainframe.header.exit")%></a>
 <%}else{
       session.removeAttribute("libRs");
       %><a href="<%=request.getContextPath()%>/admin/main.jsp" onclick=""  style="text-decoration:none;font-family: Arial;color:blue;font-size: 11px" ><%=resource.getString("opacmainframe.header.exit")%></a><%}%>

                    </b>



      </td>
            </tr>

            <tr   style="background-color: #BFDBFF;height: 50px;  background-image: url('<%=request.getContextPath()%>/images/banner_bg.jpg'); border:  solid 1px black;margin: 0px 0px 0px 0px"><td colspan="2" style="font-style: italic;font-size: 20px;color:white;" valign="middle" align="center">
           "Online Public Access Catalogue"</td>


            </tr>
            <tr><td colspan="2" valign="top">



        <table cellpadding="0" class="datagrid" border="0px" width="100%" height="100%"  cellspacing="1" id="Table1" dir="<%=rtl%>" align="center">
             

  <tr><td width="50%" dir="<%=rtl%>" align="right" >
          <table  class="datagrid" border="0" dir="<%=rtl%>"  width="100%" style="border:dashed 1px cyan;" align="center" frame="hspaces" >

    <tr dir="<%=rtl%>">
        <td style="border-bottom: dashed 1px cyan;line-height: 20px; " align="<%=align%>" valign="top" dir="<%=rtl%>" width="60%">
    An Online Public Access Catalog provides an online database of materials held by a library or group of libraries. Users search a library catalog principally to locate books and other material physically located at a library.
    <br/><b><i> Its Faciliates :-</i></b>
    <table class="datagrid">
         <tr>

    <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>"  dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    		<%=resource.getString("opacmainframe.opachome.text2")%>

    </td>

    </tr>

<tr>

    <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>"  dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;

        <%=resource.getString("opacmainframe.opachome.text3")%>
    </td>

    </tr>
    <tr>
    <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" dir="<%=rtl%>" align="<%=align%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text4")%>
    </td>

    </tr>
    <tr>
    <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text5")%>
    </td>

    </tr>
    <tr>
    <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text6")%>
    </td>

    </tr>
    <tr>
    <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text7")%>
    </td>

    </tr>
     <tr>
    <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text8")%>
    </td>

    </tr>
     <tr>
    <td style="line-height: 20px; "  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text9")%>
    </td>

    </tr>
   
    </table>

        </td><td valign="top" style="border-left: dashed 1px cyan;">
                <table class="datagrid">
                     <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">

    <b><i> Next To Encorpate:-</i></b>
    </td>

    </tr>



    <tr>
    <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;

    	Most Viewed Books Section
    </td>

    </tr>
    <tr>
    <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	Book Rating By User
    </td>

    </tr>
    <tr>
    <td style="border-bottom: dashed 1px cyan;line-height: 20px; "   class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	Digital Library width Full Text Search
    </td>

    </tr>
    <tr>
        <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	Export Title Detail in MARC,Flat File & XLS Format
    </td>
    </tr>
     <tr>
        <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	Print Search Data
    </td>
    </tr>
                </table>


            </td>

    </tr>
   
</table>
        
                </td></tr>
   </table>
                </td></tr>
            <tr><td><%
    String message=(String)request.getAttribute("msg");
    if(message!=null){
       %>
       <span class="datagrid" align="<%=align%>" dir="<%=rtl%>" style="background-color: #E3E4FA;font-size:15px;"
                    border="1" >
		      <%=message%>
		   </span>
   <% }else
        message="";
    %></td></tr>
        <tr><td align="left" class="datagrid" valign="top" colspan="2">
                          <%=resource.getString("developedby")%>  &copy; <%=resource.getString("login.message.footer")%>
         &nbsp; follow us : <img src="<%=request.getContextPath()%>/images/blog.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/facebook.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/twitter.jpeg" height="16px" width="20px"/>
      <a href="http://www.youtube.com/user/DrAasimZafar?blend=15&ob=5#p/u/0/COwssqRU9Ao"><img src="<%=request.getContextPath()%>/images/youtube.jpeg" height="16px" width="40px"/></a>
            </td></tr>
        </table>
    </form>
</body>
</html>
