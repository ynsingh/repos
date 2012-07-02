
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html>
<head>
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


    <%!
    static Integer count=0;
  
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
    </script>


</head>
<link rel="StyleSheet" href="<%=request.getContextPath()%>/css/page.css"/>
<body style="margin: 0px 0px 0px 0px">


    <form method="post" action="opachomeRTL.jsp" name="form1">

       
     

        <table cellpadding="0" border="0px" width="100%"  cellspacing="1" id="Table1" dir="<%=rtl%>" align="center">
 
            <tr><td dir="<%=rtl%>" align="<%=align%>" valign="top" >
         <table  dir="<%=rtl%>" align="center" border="0px"  <%--style="border:solid 1px #e0e8f5;"--%>>



             <tr  dir="<%=rtl%>" align="<%=align%>"><td     height="40px" dir="<%=rtl%>"  align="<%=align%>" colspan="2" class="headerStyle" style="background-color: white">
<%--   <div id="divTestArea1" style="padding: 50px; background-color: #89BC38; text-align: center; display: none;">
        <b>Under Construction</b>
</div>
<a href="javascript:void(0);" onclick="ShowBox();">News</a>
<script type="text/javascript">
function ShowBox()
{
        $("#divTestArea1").fadeIn();
}
</script>
--%>


<%--<div id="divTestArea21" style="width: 50px; height: 50px; display: none; background-color: #89BC38;"></div>
<div id="divTestArea22" style="width: 50px; height: 50px; display: none; background-color: #C3D1DF;"></div>
<div id="divTestArea23" style="width: 50px; height: 50px; display: none; background-color: #9966FF;"></div>
<a href="javascript:void(0);" onclick="ShowBoxes();">Show boxes</a>
<script type="text/javascript">
function ShowBoxes()
{
        $("#divTestArea21").fadeIn("fast");
        $("#divTestArea22").fadeIn("slow");
        $("#divTestArea23").fadeIn(2000);
}
</script>
--%>

<%--<div id="divTestArea3" style="width: 50px; height: 50px; display: none; background-color: #89BC38;"></div>
<script type="text/javascript">
$(function()
{
        $("#divTestArea3").fadeIn(2000, function()
        {
                $("#divTestArea3").fadeOut(3000);
        });
});
</script>--%>

<%--
<a href="javascript:void(0);" onclick="ShowBox();">News</a>
<div id="divTestArea1" style="padding: 50px; background-color: #89BC38; text-align: left; display: none;">
        <b>Under Construction</b>
</div>

<script type="text/javascript">
function ShowBox()
{
        $("#divTestArea1").slideDown();
}
</script>



                   <br/>   <a href="javascript:void(0);" onclick="ShowBox1();">Help Desk</a>
<div id="divTestArea2" style="padding: 50px; background-color: #89BC38; text-align: left; display: none;">
        <b>Under Construction</b>
</div>

<script type="text/javascript">
function ShowBox1()
{
        $("#divTestArea2").slideDown();
}
</script>
--%>
<%--
                   <div id="divTestArea21" style="width: 50px; height: 50px; display: none; background-color: #89BC38;"></div>
<div id="divTestArea22" style="width: 50px; height: 50px; display: none; background-color: #C3D1DF;"></div>
<div id="divTestArea23" style="width: 50px; height: 50px; display: none; background-color: #9966FF;"></div>
<a href="javascript:void(0);" onclick="ShowBoxes();">Show boxes</a>
<script type="text/javascript">
function ShowBoxes()
{
        $("#divTestArea21").slideDown("fast");
        $("#divTestArea22").slideDown("slow");
        $("#divTestArea23").slideDown(2000);
}
</script>
--%>

      <div>
       
          
        <div id="divTestBox1" style=" width: 200px; position: absolute;"><a href="javascript:void(0);" onclick="ToggleBox();">News</a>

<div id="divTestArea4" style="width: 200px; height: 150px; display: none; background-color: #89BC38;">Under Construction</div><br />

<script type="text/javascript">
function ToggleBox()
{
        $("#divTestArea4").slideToggle("slow");
}
</script>


<a href="javascript:void(0);" onclick="ToggleBox1();">Help Desk</a>


<div id="divTestArea5" style="width: 200px; height: 150px; display: none; background-color: #89BC38;">Under Construction</div><br />

<script type="text/javascript">
function ToggleBox1()
{
        $("#divTestArea5").slideToggle("slow");
}
</script>


</div>
</div>
<script type="text/javascript">
$(function()
{
        $("#divTestBox1").animate(
                {
                        "left" : "40px"
                }
        );
});
</script>         

        </td></tr>
  <tr><td width="800px" dir="<%=rtl%>" align="right">
              <table style="color:white;letter-spacing: 1px;text-decoration:none;font-family: Arial;color:black;font-size: 11px"  height="300px" border="0" dir="<%=rtl%>" cellpadding="2" cellspacing="0" width="100%" frame="hspaces" >

    <tbody><tr dir="<%=rtl%>">
            <td  align="<%=align%>" dir="<%=rtl%>">
    An Online Public Access Catalog provides an online database of materials held by a library or group of libraries. Users search a library catalog principally to locate books and other material physically located at a library.
    <br><br><b><i> Its Faciliates :-</i></b>
    </td>

    </tr>
    <tr>

    <td  class="tipstext" align="<%=align%>"  dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    		<%=resource.getString("opacmainframe.opachome.text2")%>
       
    </td>

    </tr>

<tr>

    <td  class="tipstext" align="<%=align%>"  dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    		
        <%=resource.getString("opacmainframe.opachome.text3")%>
    </td>

    </tr>
    <tr>
    <td  class="tipstext" dir="<%=rtl%>" align="<%=align%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text4")%>
    </td>

    </tr>
    <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text5")%>
    </td>

    </tr>
    <tr>
    <td class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text6")%>
    </td>

    </tr>
    <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text7")%>
    </td>

    </tr>
     <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text8")%>
    </td>

    </tr>
     <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	<%=resource.getString("opacmainframe.opachome.text9")%>
    </td>

    </tr>
    <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">

    <b><i> Next To Encorpate:-</i></b>
    </td>

    </tr>



    <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;

    	Most Viewed Books Section
    </td>

    </tr>
    <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	Book Rating By User
    </td>

    </tr>
    <tr>
    <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	Digital Library width Full Text Search
    </td>

    </tr>
    <tr>
        <td  class="tipstext" align="<%=align%>" dir="<%=rtl%>">&nbsp;<img src="../images/orange_sq_but.gif"/>&nbsp;
    	Export Title Detail in MARC,Flat File & XLS Format
    </td>
    </tr>
  </tbody></table>





      </td></tr></table>


                </td></tr>
           
                
               
            
           


  <%
    String message=(String)request.getAttribute("msg");
    if(message!=null){
       %> <br>
       <TABLE class="datagrid" align="<%=align%>" dir="<%=rtl%>" style="background-color: #E3E4FA;font-size:15px;"
                   WIDTH="40%" border="1" >
		      <tr><th><%=message%></th></tr>
		   </TABLE>
   <% }else
        message="";
    %>
    <%--<tr><td colspan="2">
              <table align="center" border="0px" width="100%" style="background-image: url('<%=request.getContextPath()%>/images/footerbg.gif')">
    <tr>


                <td class="homepage" valign="top">




    </td><td class="homepage" valign="top" >
        <b style="color:white">
        <b>Help Center</b> &nbsp;About Us&nbsp;|&nbsp;Advertising&nbsp;|&nbsp;User Agreement&nbsp;|&nbsp;Copyright Policy&nbsp;|&nbsp;<a href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx">NME-ICT ERP Mission</a><%=resource.getString("developedby")%> &nbsp;<br/> &copy; <%=resource.getString("login.message.footer")%>
        <br>follow us : <img src="<%=request.getContextPath()%>/images/blog.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/facebook.jpeg" height="16px" width="20px"/>
     <img src="<%=request.getContextPath()%>/images/twitter.jpeg" height="16px" width="20px"/>
      <a href="http://www.youtube.com/user/DrAasimZafar?blend=15&ob=5#p/u/0/COwssqRU9Ao"><img src="<%=request.getContextPath()%>/images/youtube.jpeg" height="16px" width="40px"/></a>
        </b>
    </td><td class="homepage" align="right" valign="top">
         <b style="color:white">Based on Open Source LibMS at<br></b>
         <a href="http://sourceforge.net/projects/libms/">   <img src="/LibMS/images/sflogo.png"/></a>
    </td>

    </tr>
</table>
        </td></tr>--%>
</table>
  
    </form>
</body>
</html>
