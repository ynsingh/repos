<%--
    Document   : Account Details.jsp
    Created on : Jun 15, 2010, 1:15:37 PM
    Author     : Mayank Saxena
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@ page import="java.util.*, java.lang.*"%>

<html>
 <head>
     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>Account Details..</title>
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
</head><body>
    <%if(page.equals(true)){

try
{ /*Create string of connection url within specified
    format with machine name, port number and database name.
    Here machine name id localhost and
    database name is library.*/


%>
<div id="tableBar" style="position: absolute; left: 0px; top: 20px; width: 748px; z-index: 6; height: 388px;">
<table width="50%" height="10" border="1" align="center" vspace="30%" cellpadding="0" cellspacing="0" bordercolor="#330033">
  <tr bgcolor="#D0EFF9">
    
      <td width="6%" height="10" align="center" valign="middle" bgcolor="#D0EFF9" top="50"> <a href="newdemand1.jsp" target="f3" style="text-decoration:none"> <font color="#C0003B"><b> <font size="1.9" face="Arial, Helvetica,sans-serif"><strong><%=resource.getString("opac.accountdetails.newdemand")%></strong></font></b></font></a></td>
    <td width="6%" height="10" align="center" valign="middle" bgcolor="#D0EFF9" top="50"> <a href="reservationrequest1.jsp" target="f3" style="text-decoration:none"> <font color="#C0003B"><b> <font size="1.7" face="Arial, Helvetica,sans-serif"><%=resource.getString("opac.accountdetails.reservationrequest")%></font></b></font></a></td>
    <td width="6%" height="10" align="center" valign="middle" bgcolor="#D0EFF9" top="50">
     <A  href="opac_logout.jsp" style="text-decoration:none">
      <font color="#C0003B"><b> <font size="1.7" face="Arial, Helvetica,sans-serif"><%=resource.getString("opac.accountdetails.logout")%></font></b></font></A>
    </td>
  </tr>
</table>
</div>

<div id="wb_Text1" style="position: absolute; left: 150px; top: 20px;
width: 650px; height: 19px; background-color: rgb(255, 255, 224);
z-index: 0;" >
<font style="font-size: 16px;" color="#c0003b" face="Arial" align="center"><b>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%=resource.getString("opac.accountdetails.accountdetails")%></b></font></div>
<div id="wb_Form1" style="position: absolute; left: 154px; top: 80px;
width: 537px; height: 251px; z-index: 6;">

<form name="Form1" method="post" id="Form1">
<div id="wb_Text5" style="position:absolute;left:73px;top:20px;width:55px;height:15px;z-index:0;" align="left">
    <font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.accountdetails.finedue")%></b></font></div>
<div id="wb_Text6" style="position:absolute;left:51px;top:107px;width:76px;height:15px;z-index:1;" align="left">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.accountdetails.checkouts")%></b></font></div>
<div id="wb_Text8" style="position:absolute;left:21px;top:138px;width:103px;height:30px;z-index:2;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.accountdetails.lastcheckoutdate")%></b></font></div>
<%!
String ID,status,lastchkoutdate,no_of_chkout ,reservation_made, fine ,name;
ResultSet rs=null,rs1=null;
%>
<%
    ID = (String)session.getAttribute("id");
     rs= (ResultSet)request.getAttribute("rs");
     rs1= (ResultSet)request.getAttribute("rs1");

        rs.beforeFirst();
        boolean s=rs.next();
//out.println(s);
   if(s)
      { status=rs.getString(7);
        if(status.equals("Y"))
          {
            name=rs.getString(2);
            //fine=rs.getString(3);
            no_of_chkout=rs.getString(4);
            reservation_made=rs.getString(5);
            lastchkoutdate=rs.getString(6);
            session.setAttribute("memname",name);
            
          }
        else
          {
            response.sendRedirect("member.jsp?msg=Sorry, your Membership is cancelled" +
                           " for somehow reason, please contact to your Library..");
          }
       }
   else
       {
    response.sendRedirect("member.jsp?msg=Invalid Member,try again..");
       }
   
   
   
   
    while(rs1.next()){fine=rs1.getString(1);}


%>

<div id="wb_Text1" style="position: absolute; left: -5px; top: -59px;
width: 610px; height: 19px;
z-index: 0;" align="left">
 <font size="2" color="#c0003b" face="Arial, Helvetica,sans-serif">
  <b>
     <%=resource.getString("opac.accountdetails.welcome")%> <%=name%>..  </b> </font></div>

<input type="text" id="TXTFINE" style="position:absolute;left:130px;top:14px;width:141px;height:18px;border:1px #C0C0C0 solid;font-family: Arial, Helvetica, sans-serif; color:TEAL;font-size:15px;z-index:3" name="TXTFINE" value="<%=fine%>" disabled="disabled">
<input type="text" id="TXTCHECKOUT" style="position:absolute;left:130px;top:106px;width:142px;height:18px;border:1px #C0C0C0 solid;font-family: Arial, Helvetica, sans-serif; color:TEAL;font-size:15px;z-index:4" name="TXTCHECKOUT" value="<%=no_of_chkout%>" disabled="disabled">
<input type="text" id="TXTRESERVATION" style="position:absolute;left:129px;top:47px;width:142px;height:18px;border:1px #C0C0C0 solid;font-family: Arial, Helvetica, sans-serif; color:TEAL;font-size:15px;z-index:5" name="TXTRESERVATION" value="<%=reservation_made%>" disabled="disabled">
<input type="text" id="TXTCHKDATE" style="position:absolute;left:130px;top:141px;width:143px;height:18px;border:1px #C0C0C0 solid;font-family: Arial, Helvetica, sans-serif; color:TEAL;font-size:15px;z-index:6" name="TXTCHKDATE" value="<%=lastchkoutdate%>" disabled="disabled">
<div id="wb_Text7" style="position:absolute;left:29px;top:46px;width:92px;height:30px;z-index:7;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b<%=resource.getString("opac.accountdetails.reservationmade")%></b></font></div>
<%
       /* fine=null;
        no_of_chkout=null;
        reservation_made=null;
        lastchkoutdate=null;
        name="Guest";*/
%>
</form>
<div id="wb_Text5" style="position:absolute;left:282px;top:16px;width:55px;height:15px;z-index:0;" align="left"> <a href="finedetails.jsp"><font style="FONT-SIZE: 12px" face="Arial" color="red"><strong><%=resource.getString("opac.accountdetails.details")%></strong></font></a></div>
<div id="wb_Text5" style="position:absolute;left:282px;top:47px;width:55px;height:15px;z-index:0;" align="left"> <a href="reservationdetails.jsp"><font style="FONT-SIZE: 12px" face="Arial" color="red"><strong><%=resource.getString("opac.accountdetails.details")%></strong></font></a></div>
</div>
<%
}catch(Exception ex){}      }
else{
%>
<div id="tableBar" style="position: absolute; right: 0px; top: 20px; width: 748px; z-index: 6; height: 388px;">
<table width="50%" height="10" border="1" align="center" vspace="30%" cellpadding="0" cellspacing="0" bordercolor="#330033">
  <tr bgcolor="#D0EFF9">

      <td width="6%" height="10" align="center" valign="middle" bgcolor="#D0EFF9" top="50"> <a href="newdemand1.jsp" target="f3" style="text-decoration:none"> <font color="#C0003B"><b> <font size="1.9" face="Arial, Helvetica,sans-serif"><strong><%=resource.getString("opac.accountdetails.newdemand")%></strong></font></b></font></a></td>
    <td width="6%" height="10" align="center" valign="middle" bgcolor="#D0EFF9" top="50"> <a href="reservationrequest1.jsp" target="f3" style="text-decoration:none"> <font color="#C0003B"><b> <font size="1.7" face="Arial, Helvetica,sans-serif"><%=resource.getString("opac.accountdetails.reservationrequest")%></font></b></font></a></td>
    <td width="6%" height="10" align="center" valign="middle" bgcolor="#D0EFF9" top="50">
     <A  href="opac_logout.jsp" style="text-decoration:none">
      <font color="#C0003B"><b> <font size="1.7" face="Arial, Helvetica,sans-serif"><%=resource.getString("opac.accountdetails.logout")%></font></b></font></A>
    </td>
  </tr>
</table>
</div>

<div id="wb_Text1" style="position: absolute; right: 150px; top: 20px;
width: 650px; height: 19px; background-color: rgb(255, 255, 224);
z-index: 0;" >
<font style="font-size: 16px;" color="#c0003b" face="Arial" align="center"><b>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <%=resource.getString("opac.accountdetails.accountdetails")%></b></font></div>
<div id="wb_Form1" style="position: absolute; right: 154px; top: 80px;
width: 537px; height: 251px; z-index: 6;">

<form name="Form1" method="post" id="Form1">
    <div id="wb_Text5" style="position:absolute;right:40px;top:20px;width:55px;height:15px;z-index:0;" align="right" dir="rtl">
    <font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.accountdetails.finedue")%></b></font></div>
<div id="wb_Text6" style="position:absolute;right:40px;top:107px;width:76px;height:15px;z-index:1;" align="right" dir="rtl">
<font style="FONT-SIZE: 12px" face="Arial" color="#000000"><b><%=resource.getString("opac.accountdetails.checkouts")%></b></font></div>
<div id="wb_Text8" style="position:absolute;right:40px;top:138px;width:103px;height:30px;z-index:2;" align="right" dir="rtl">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.accountdetails.lastcheckoutdate")%></b></font></div>

<%
    ID = (String)session.getAttribute("id");
     rs= (ResultSet)request.getAttribute("rs");
     rs1= (ResultSet)request.getAttribute("rs1");

        rs.beforeFirst();
        boolean s=rs.next();
//out.println(s);
   if(s)
      { status=rs.getString(7);
        if(status.equals("Y"))
          {
            name=rs.getString(2);
            //fine=rs.getString(3);
            no_of_chkout=rs.getString(4);
            reservation_made=rs.getString(5);
            lastchkoutdate=rs.getString(6);
            session.setAttribute("memname",name);

          }
        else
          {
            response.sendRedirect("member.jsp?msg=Sorry, your Membership is cancelled" +
                           " for somehow reason, please contact to your Library..");
          }
       }
   else
       {
    response.sendRedirect("member.jsp?msg=Invalid Member,try again..");
       }




    while(rs1.next()){fine=rs1.getString(1);}


%>

<div id="wb_Text1" style="position: absolute; right: -5px; top: -59px;
width: 610px; height: 19px;
z-index: 0;" align="right" dir="rtl">
 <font size="2" color="#c0003b" face="Arial, Helvetica,sans-serif">
  <b>
     <%=resource.getString("opac.accountdetails.welcome")%> <%=name%>  </b> </font></div>

<input type="text" id="TXTFINE" style="position:absolute;right:130px;top:14px;width:141px;height:18px;border:1px #C0C0C0 solid;font-family: Arial, Helvetica, sans-serif; color:TEAL;font-size:15px;z-index:3" name="TXTFINE" value="<%=fine%>" disabled="disabled">
<input type="text" id="TXTCHECKOUT" style="position:absolute;right:130px;top:106px;width:142px;height:18px;border:1px #C0C0C0 solid;font-family: Arial, Helvetica, sans-serif; color:TEAL;font-size:15px;z-index:4" name="TXTCHECKOUT" value="<%=no_of_chkout%>" disabled="disabled">
<input type="text" id="TXTRESERVATION" style="position:absolute;right:129px;top:47px;width:142px;height:18px;border:1px #C0C0C0 solid;font-family: Arial, Helvetica, sans-serif; color:TEAL;font-size:15px;z-index:5" name="TXTRESERVATION" value="<%=reservation_made%>" disabled="disabled">
<input type="text" id="TXTCHKDATE" style="position:absolute;right:130px;top:141px;width:143px;height:18px;border:1px #C0C0C0 solid;font-family: Arial, Helvetica, sans-serif; color:TEAL;font-size:15px;z-index:6" name="TXTCHKDATE" value="<%=lastchkoutdate%>" disabled="disabled">
<div id="wb_Text7" style="position:absolute;right:40px;top:46px;width:92px;height:30px;z-index:7;" align="right">
<font style="font-size:12px" color="#000000" face="Arial"><b><%=resource.getString("opac.accountdetails.reservationmade")%></b></font></div>
<%
       /* fine=null;
        no_of_chkout=null;
        reservation_made=null;
        lastchkoutdate=null;
        name="Guest";*/
%>
</form>
<div id="wb_Text5" style="position:absolute;right:282px;top:16px;width:55px;height:15px;z-index:0;" align="right"> <a href="finedetails.jsp"><font style="FONT-SIZE: 12px" face="Arial" color="red"><strong><%=resource.getString("opac.accountdetails.details")%></strong></font></a></div>
<div id="wb_Text5" style="position:absolute;right:282px;top:47px;width:55px;height:15px;z-index:0;" align="right"> <a href="reservationdetails.jsp"><font style="FONT-SIZE: 12px" face="Arial" color="red"><strong><%=resource.getString("opac.accountdetails.details")%></strong></font></a></div>
</div>
<%}%>
</body>
</html>