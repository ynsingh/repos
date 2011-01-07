<%--
    Document   : ReservationReques1.jsp
    Created on : July 17, 2010, 7:15:37 PM
    Author     : Mayank Saxena
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage = "ErrorPage.jsp" language="java"%>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>New Reservation...</title>

<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<style type="text/css">
a:active
{
   color: #0000FF;
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


</head>
<body>
<%
String card_id;
//Retrieving the values of NewMember form in variables.
card_id=(String)session.getAttribute("current_member_id");
 String name; 
  name=(String)session.getAttribute("memname");
%>
 <%if(page.equals(true)){%>

<div id="wb_Text1" style="position:absolute;left:34px;top:10px;width:615px;height:19px;background-color:#FFFFE0;z-index:23;" align="left">
<font style="font-size:16px" color="#c0003b" face="Arial"><b>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; <%=resource.getString("opac.myaccount.reservationrequest.text")%></b></font>
</div>
<div id="wb_Text2" style="position:absolute;left:3px;top:33px;width:583px;height:19px;background-color:#FFFFE0;z-index:23;" align="left">
<font style="font-size:12px" color="#c0003b" face="Arial"><b> &nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;<%=resource.getString("opac.myaccount.reservationrequest.welcome")%>&nbsp;  <%=name%> </b></font>
</div>
<div id="wb_Form1" style="position:absolute;left:31px;top:61px;width:624px;height:409px;z-index:24">
<form name="Form1" method="post" action="reservationrequest.jsp" id="Form1">
<div id="wb_Text2" style="position:absolute;left:20px;top:9px;width:81px;height:15px;z-index:0;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.libraryname")%></b></font></div>
<div id="wb_Text3" style="position:absolute;left:54px;top:40px;width:47px;height:15px;z-index:1;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.cardid")%></b></font></div>
<div id="wb_Text4" style="position:absolute;left:69px;top:75px;width:34px;height:15px;z-index:2;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.title")%></b></font></div>
<div id="wb_Text5" style="position:absolute;left:58px;top:109px;width:44px;height:15px;z-index:3;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.author")%></b></font></div>
<div id="wb_Text6" style="position:absolute;left:65px;top:141px;width:31px;height:15px;z-index:4;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.isbn")%></b></font></div>
<div id="wb_Text7" style="position:absolute;left:56px;top:172px;width:44px;height:15px;z-index:5;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.callno")%></b></font></div>
<div id="wb_Text8" style="position:absolute;left:42px;top:273px;width:61px;height:15px;z-index:6;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.publisher")%></b></font></div>
<div id="wb_Text9" style="position:absolute;left:41px;top:327px;width:57px;height:15px;z-index:7;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.remark")%></b></font></div>
<input type="text" id="TXTLIBNAME" style="position:absolute;left:106px;top:5px;width:268px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:8" name="TXTLIBNAME" >
<input type="text" disabled="true" id="TXTCARDID"  value="<%=card_id%> "style="position:absolute;left:106px;top:38px;width:183px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:9" name="TXTCARDID" >
<input type="hidden" id="id" value="<%=card_id%>"style="position:absolute;left:102px;top:69px;width:198px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:11" name="id" >
<input type="text" id="TXTTITLE" style="position:absolute;left:106px;top:70px;width:298px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:10" name="TXTTITLE" >
<input type="text" id="TXTAUTHOR" style="position:absolute;left:106px;top:104px;width:294px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:11" name="TXTAUTHOR" >
<input type="text" id="TXTISBN" style="position:absolute;left:106px;top:136px;width:178px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:12" name="TXTISBN" >
<input type="text" id="TXTCALLNO" style="position:absolute;left:106px;top:169px;width:177px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:13" name="TXTCALLNO" >
<input type="text" id="TXTPUBL" style="position:absolute;left:105px;top:269px;width:187px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:14" name="TXTPUBL" >
<textarea name="TXTREMARKS" id="TXTREMARKS" style="position:absolute;left:106px;top:306px;width:229px;height:63px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:15" rows="2" cols="24"></textarea>
<input type="submit" id="Button1" name="" value="<%=resource.getString("opac.myaccount.reservationrequest.process")%>" style="position:absolute;left:393px;top:322px;width:73px;height:25px;font-family:Arial;font-size:13px;z-index:16">
<div style="position:absolute;left:495px;top:75px;width:120px;height:18px;border:1px #C0C0C0 solid;z-index:17">
<select name="CMBCAT" size="1" id="CMBCAT" style="left:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected VALUE="books">BOOK</option>
<option value="journals">JOURNAL</option>
<option value="others">OTHERS</option>
</select>
</div>
<div id="wb_Text10" style="position:absolute;left:427px;top:78px;width:55px;height:15px;z-index:18;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.category")%></b></font></div>
<div id="wb_Text11" style="position:absolute;left:56px;top:208px;width:44px;height:15px;z-index:19;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.edition")%></b></font></div>
<div id="wb_Text12" style="position:absolute;left:52px;top:241px;width:49px;height:15px;z-index:20;" align="left">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.volume")%></b></font></div>
<input type="text" id="TXTEDITION" style="position:absolute;left:106px;top:203px;width:178px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:21" name="TXTEDITION">
<input type="text" id="TXTVOL" style="position:absolute;left:106px;top:236px;width:109px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:22" name="TXTVOL" >
</form>
</div>
<%}else{%>

<div id="wb_Text1" style="position:absolute;right:34px;top:10px;width:615px;height:19px;background-color:#FFFFE0;z-index:23;" align="right">
<font style="font-size:16px" color="#c0003b" face="Arial"><b>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; <%=resource.getString("opac.myaccount.reservationrequest.text")%></b></font>
</div>
<div id="wb_Text2" style="position:absolute;right:3px;top:33px;width:583px;height:19px;background-color:#FFFFE0;z-index:23;" align="right">
<font style="font-size:12px" color="#c0003b" face="Arial"><b> &nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;<%=resource.getString("opac.myaccount.reservationrequest.welcome")%>&nbsp;  <%=name%> </b></font>
</div>
<div id="wb_Form1" style="position:absolute;right:31px;top:61px;width:624px;height:409px;z-index:24">
<form name="Form1" method="post" action="reservationrequest.jsp" id="Form1">
<div id="wb_Text2" style="position:absolute;right:20px;top:9px;width:81px;height:15px;z-index:0;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.libraryname")%></b></font></div>
<div id="wb_Text3" style="position:absolute;right:54px;top:40px;width:47px;height:15px;z-index:1;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.cardid")%></b></font></div>
<div id="wb_Text4" style="position:absolute;right:69px;top:75px;width:34px;height:15px;z-index:2;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.title")%></b></font></div>
<div id="wb_Text5" style="position:absolute;right:58px;top:109px;width:44px;height:15px;z-index:3;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.author")%></b></font></div>
<div id="wb_Text6" style="position:absolute;right:65px;top:141px;width:31px;height:15px;z-index:4;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.isbn")%></b></font></div>
<div id="wb_Text7" style="position:absolute;right:56px;top:172px;width:44px;height:15px;z-index:5;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.callno")%></b></font></div>
<div id="wb_Text8" style="position:absolute;right:42px;top:273px;width:61px;height:15px;z-index:6;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.publisher")%></b></font></div>
<div id="wb_Text9" style="position:absolute;right:41px;top:327px;width:57px;height:15px;z-index:7;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.remark")%></b></font></div>
<input type="text" id="TXTLIBNAME" style="position:absolute;right:106px;top:5px;width:268px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:8" name="TXTLIBNAME" >
<input type="text" disabled="true" id="TXTCARDID"  value="<%=card_id%> "style="position:absolute;right:106px;top:38px;width:183px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:9" name="TXTCARDID" >
<input type="hidden" id="id" value="<%=card_id%>"style="position:absolute;right:102px;top:69px;width:198px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:11" name="id" >
<input type="text" id="TXTTITLE" style="position:absolute;right:106px;top:70px;width:298px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:10" name="TXTTITLE" >
<input type="text" id="TXTAUTHOR" style="position:absolute;right:106px;top:104px;width:294px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:11" name="TXTAUTHOR" >
<input type="text" id="TXTISBN" style="position:absolute;right:106px;top:136px;width:178px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:12" name="TXTISBN" >
<input type="text" id="TXTCALLNO" style="position:absolute;right:106px;top:169px;width:177px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:13" name="TXTCALLNO" >
<input type="text" id="TXTPUBL" style="position:absolute;right:105px;top:269px;width:187px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:14" name="TXTPUBL" >
<textarea name="TXTREMARKS" id="TXTREMARKS" style="position:absolute;right:106px;top:306px;width:229px;height:63px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:15" rows="2" cols="24"></textarea>
<input type="submit" id="Button1" name="" value="<%=resource.getString("opac.myaccount.reservationrequest.process")%>" style="position:absolute;right:393px;top:322px;width:73px;height:25px;font-family:Arial;font-size:13px;z-index:16">
<div style="position:absolute;right:495px;top:75px;width:120px;height:18px;border:1px #C0C0C0 solid;z-index:17">
<select name="CMBCAT" size="1" id="CMBCAT" style="right:0px;top:0px;width:100%;height:100%;border-width:0px;font-family:Courier New;font-size:13px;">
<option selected VALUE="books">BOOK</option>
<option value="journals">JOURNAL</option>
<option value="others">OTHERS</option>
</select>
</div>
<div id="wb_Text10" style="position:absolute;right:427px;top:78px;width:55px;height:15px;z-index:18;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.category")%></b></font></div>
<div id="wb_Text11" style="position:absolute;right:56px;top:208px;width:44px;height:15px;z-index:19;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.edition")%></b></font></div>
<div id="wb_Text12" style="position:absolute;right:52px;top:241px;width:49px;height:15px;z-index:20;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.myaccount.reservationrequest.volume")%></b></font></div>
<input type="text" id="TXTEDITION" style="position:absolute;right:106px;top:203px;width:178px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:21" name="TXTEDITION">
<input type="text" id="TXTVOL" style="position:absolute;right:106px;top:236px;width:109px;height:18px;border:1px #C0C0C0 solid;font-family:Courier New;font-size:13px;z-index:22" name="TXTVOL" >
</form>
</div>


 <% } %>

</body>
</html>