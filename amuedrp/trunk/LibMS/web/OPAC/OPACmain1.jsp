
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html style="height: 100%">
<head >
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>LibMS : OPAC</title>
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
<%--body
{
   overflow: hidden;
   height: auto;
}--%>

        </style>

</head>
<link rel="StyleSheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body style="margin: 0px 0px 0px 0px;" onload="doClick();">
      <jsp:include page="opacheader.jsp"/>
    <form method="post" action="opachomeRTL.jsp" name="form1">
        <table style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;border-collapse: collapse;  border-spacing: 0;" align="center"  width="100%" <%--id="tableContainer"--%>    dir="<%=rtl%>" >
           <%-- <tr><td class="homepage" style="background-color: black;color:white;" align="right" colspan="2">



         <a style="color:white" href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx">NME-ICT ERP Mission</a>&nbsp;|&nbsp;<a  style="color:white" href="<%=request.getContextPath()%>/contactus.jsp">Contact Us</a>&nbsp;|&nbsp; <a style="color:white" href="<%=request.getContextPath()%>/admin/admin_registration.jsp"><%= resource.getString("login.href.institute.registration") %></a>
         &nbsp;|&nbsp;            <a style="color:white" href="relnotes.jsp">   Release Notes</a>&nbsp;|&nbsp;  <a style="color:white" href="instantUserManual_LibMS-2012.pdf">UserManual</a>&nbsp;|&nbsp; <a style="color:white" href="<%=request.getContextPath()%>/admin/view_instiapproved.jsp">View All Registered Institutes</a>&nbsp;|&nbsp;<%=resource.getString("login.message.selectlanguage")%><select name="locale" class="selecthome" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select>



                                         </td>

                                     </tr>
                                     <tr ><td>
                                     <table width="100%" style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;"><tr>
                                         <td align="left"  style="background-color: white;color:blue;height: 50px;  margin: 0px 0px 0px 0px;font-size: 18px;valign:bottom" valign="bottom" align="center">
                                             &nbsp;&nbsp;<span style="font-size: 18px;">Online Public Access Catalog</span><br/>
                                             &nbsp;&nbsp;<span style="font-size: 18px;">LibMS....</span>        "<%=resource.getString("login.message.logo.under")%>"




                            </td>
                            <td align="right">
  <img src="<%=request.getContextPath()%>/images/logo.png" alt=""  border="0" align="top" id="Image1" style="height:70px;width:160px;">

                            </td>


                                         </tr></table>
                                          <hr color="cyan">
                                         </td>

           </tr>--%>
           <%-- <tr>
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
            </tr>--%>

       <%--     <tr   style="background-color: #BFDBFF;height: 50px;  background-image: url('<%=request.getContextPath()%>/images/banner_bg.jpg'); border:  solid 1px black;margin: 0px 0px 0px 0px"><td colspan="2" style="font-style: italic;font-size: 20px;color:white;" valign="middle" align="center">
           "Online Public Access Catalogue"</td>


            </tr>--%>
            <tr><td colspan="2" valign="top">



        <table cellpadding="0" class="txt2" border="0px" width="100%" height="100%"  cellspacing="1" id="Table1" dir="<%=rtl%>" align="center">
             

  <tr><td width="50%" dir="<%=rtl%>" align="right" >
          <table  class="datagrid" border="0" dir="<%=rtl%>"  width="100%" style="border:dashed 1px cyan;" align="center" frame="hspaces" >

    <tr dir="<%=rtl%>">
        <td style="border-bottom: dashed 1px cyan;line-height: 20px; " align="<%=align%>" valign="top" dir="<%=rtl%>" width="80%">
   <%-- An Online Public Access Catalog provides an online database of materials held by a library or group of libraries. Users search a library catalog principally to locate books and other material physically located at a library.
    <br/><b><i> Its Faciliates :-</i></b>
    <table class="datagrid">
         <tr>

    <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>"  dir="<%=rtl%>">&nbsp;

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
        --%>
        <table  width="100%" align="center"><tr><td width="80%"  valign="top" style="padding-left:  30px;line-height: 35px;"  align="left">
                    Some of the Key Features of LibMS OPAC are. <br>
                    <span style="font-family:Bernard MT Condensed;text-decoration: none;font-size: 15px;">
                       
                        <img src="../images/orange_sq_but.gif"/>&nbsp;
    		OPAC with different types of searching capabilities in English & Mulitingual
                <br>
                        <img src="../images/orange_sq_but.gif"/>&nbsp;
    		Member Login
                <br>
                        <img src="../images/orange_sq_but.gif"/>&nbsp;
    		Demand List
                <br>
                        <img src="../images/orange_sq_but.gif"/>&nbsp;
    		Request for CheckOut
                <br>
                        <img src="../images/orange_sq_but.gif"/>&nbsp;
    		View Notices/Location of Libraries
                <br>
                        <img src="../images/orange_sq_but.gif"/>&nbsp;
    		Give Star Rating to Book/Documents
                 <br>
                        <img src="../images/orange_sq_but.gif"/>&nbsp;
    		Export & Print Search Results
                <br>
                        <img src="../images/orange_sq_but.gif"/>&nbsp;
    		Send Member Registration request
                     <br>
                        <img src="../images/orange_sq_but.gif"/>&nbsp;
    		Support AACR2 & MARC-21 Bibliographic Details  of searching results in English & Mulitingual  </span>

                <%--<img style="border: solid 10px cyan" src="../images/a.JPG" width="800px" height="400px">--%></td>
                <td align="center" width="150px">
                    <img src="../images/review-icon.png" height="100px" width="100px" ><br>
                <a  href="../OPAC/OpacLib.do?name=feedback" dir="<%=rtl%>" style="text-decoration:underline;font-weight: bold;font-family: Arial;color:red;font-size: 15px" ><%=resource.getString("opacmainframe.mframe.feedback")%></a></br>
                <img src="../images/myacc.PNG"  height="100px" width="100px" ><br>
                <a  href="../OPAC/OpacLib.do?name=myaccount&p=t" dir="<%=rtl%>" style="text-decoration:underline;font-weight: bold;font-family: Arial;color:red;font-size: 15px" >My Account</a></br>
                 <img src="../images/help.jpg"  height="100px" width="100px" ><br>
                <a  href="../help.jsp" dir="<%=rtl%>" style="text-decoration:underline;font-weight: bold;font-family: Arial;color:red;font-size: 15px" >Help</a></br>
                </td></tr></table>
        </td><td valign="top" style="border-left: dashed 1px cyan;">
                <table class="datagrid">
                    <tr><td >
                                <span class="header">Popular Book&nbsp;&nbsp;</span><br>
                              <marquee direction="up">
                                   
                                   <table><tr><td>
                                     <img src="<%=request.getContextPath()%>/images/no-image.jpg" style="border : solid 5px cyan;" style="margin:5px 5px 5px 5px;" >
                                           </td><td valign="top" style="margin-left: 20px;">Title:<br/>
                                           Author:<br/>
                                           ISBN:<br/>
                                           Publisher<br/>
                                           </td></tr>
                                   </table>
                                   
                               </marquee>
                          

                        </td>
                     <tr>
    <td  width="100%"  class="tipstext" align="<%=align%>" dir="<%=rtl%>">
        <hr>
    <b><i> Next To Encorpate:-</i></b>
    </td>

    </tr>



    <tr>
    <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;

    	<%--<a href="<%=request.getContextPath()%>/OPAC/search_popular.jsp%>">--%>Most Popular Books 
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
    	Export Title Detail in MARC & XLS Format
    </td>
    </tr>
     <tr>
        <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	Self Renewal of Book
    </td>
    </tr>
    <tr>
        <td style="border-bottom: dashed 1px cyan;line-height: 20px; "  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	 Request for Reservation
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
     
        </table>
         <jsp:include page="opacfooter.jsp"/>
    </form>
</body>
</html>
