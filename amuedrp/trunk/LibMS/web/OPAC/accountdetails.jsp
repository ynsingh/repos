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

<title>Account Details..</title>
<style type="text/css">

body
{
   background-color: #FFFFFF;
   color: #000000;
}
th a:link      { text-decoration: none; color: black }
     th a:visited   { text-decoration: none; color: black }
     .rows          { background-color: white;border: solid 1px blue; }
     .hiliterows    { background-color: pink; color: #000000; font-weight: bold;border: solid 1px blue; }
     .alternaterows { background-color: #efefef; }
     .header        { background-color: #c0003b; color: #FFFFFF;font-weight: bold;text-decoration: none;padding-left: 10px; }

     .datagrid      {  font-family: arial; font-size: 9pt;
	    font-weight: normal;}
     .item{ padding-left: 10px;}
</style>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
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



<%!
String ID,status,lastchkoutdate,no_of_chkout ,reservation_made, fine ,name;
ResultSet rs=null,rs1=null;
%>
<%
    ID = (String)session.getAttribute("id");
     rs= (ResultSet)session.getAttribute("rs");
    rs1= (ResultSet)session.getAttribute("rs1");

        rs.beforeFirst();
        boolean s=rs.next();

   if(s)
      { status=rs.getString(8);
        if(status.equals("Y"))
          {

            name=rs.getString(3);

            no_of_chkout=rs.getString(5);
            reservation_made=rs.getString(6);
            lastchkoutdate=rs.getString(7);
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

        if(fine==null)
            fine="0";
        if(no_of_chkout==null)
            no_of_chkout="";
        if(lastchkoutdate==null)
            lastchkoutdate="";


%>


</head><body>
    <%if(page.equals(true))
    {

%>

<form name="Form1" method="post" id="Form1">

 <table  align="left" width="800px" class="datagrid" style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">

      

  <tr><td  width="800px"  style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="left">
          <table>
              <tr><td width="640px" style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="left"><b>
               
		     
	&nbsp;&nbsp;
                <a href="accountdetails.jsp" target="f3" style="text-decoration: none;color:white"><%=resource.getString("opac.accountdetails.home")%></a>&nbsp;|&nbsp;
            <a href="newdemand2.jsp" target="f3" style="text-decoration: none;color:white"> <%=resource.getString("opac.accountdetails.newdemand")%></a>&nbsp;
    |&nbsp;<a href="reservationrequest1.jsp" target="f3" style="text-decoration: none;color:white"> <%=resource.getString("opac.accountdetails.reservationrequest")%></a>

            


          </b>
                  </td><td align="right" style="color:white;font-family:Tahoma;font-size:12px"><%=resource.getString("opac.accountdetails.hi")%>&nbsp;<%=name%>&nbsp;<b>|</b>&nbsp;<a href="home.do" target="f3" style="text-decoration: none;color:white"><%=resource.getString("opac.accountdetails.logout")%></a></td></tr></table>
        </td></tr>
  
    

    <tr><td height="300px" valign="top" class="btn1" align="left" colspan="2"><br><br>
            <table width="500px" class="btn1">
                <tr><td align="center" colspan="2" class="btn1" >
                        <b>  <%=resource.getString("opac.accountdetails.accountdetails")%></b><br><br>
        </td></tr>
                <tr><td align="left"><b><%=resource.getString("opac.accountdetails.finedue")%></b></td><td align="left"><input type="text" id="TXTFINE" name="TXTFINE" value="<%=fine%>" disabled="disabled"><a href="view_finedetails.jsp" style="text-decoration: none;color:blue"><b>&nbsp;<%=resource.getString("opac.accountdetails.details")%></b></a></td></tr>
                <tr><td align="left"><b><%=resource.getString("opac.accountdetails.checkouts")%></b></td><td align="left"><input type="text" id="TXTCHECKOUT"  name="TXTCHECKOUT" value="<%=no_of_chkout%>" disabled="disabled"></td></tr>
                <tr><td align="left"><b><%=resource.getString("opac.accountdetails.reservation")%></b></td><td align="left"><input type="text" id="TXTRESERVATION" name="TXTRESERVATION" value="<%=reservation_made%>" disabled="disabled"><a href="view_reservation.jsp" style="text-decoration: none;color:blue"><b>&nbsp;<%=resource.getString("opac.accountdetails.details")%></b></a></td></tr>
                <tr><td align="left"><b><%=resource.getString("opac.accountdetails.lastcheckoutdate")%></b></td><td align="left"><input type="text" id="TXTCHKDATE" name="TXTCHKDATE" value="<%=lastchkoutdate%>" disabled="disabled"></td></tr>
                <tr><td colspan="2" align="left"><br><br><br>
            <%
    String message=(String)request.getAttribute("msg");
    if(message!=null){
       %> <br>
	           <TABLE style="background-color: #E3E4FA;"
                    border="1" align="center">
		      <tr><th><%=message%></th></tr>
		   </TABLE>
   <% }else
        message="";
    %>

        </td></tr>

            </table>



        </td></tr>
    

</table>

  
    


</form>
 


<%
}
        else
            {

%>

<form name="Form1" method="post" id="Form1">

 <table  align="left" width="800px"  style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">

      

  <tr><td  width="800px"  style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="right">
          <table>
              <tr>
                  <td width="520px" align="left" style="color:white;font-family:Tahoma;font-size:12px"><a href="home.do" target="f3" style="text-decoration: none;color:white"><%=resource.getString("opac.accountdetails.logout")%></a>&nbsp;|&nbsp;<%=resource.getString("opac.accountdetails.hi")%>&nbsp;<%=name%></td>
                  
                  <td  style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="right"><b>
               
		     
	<a href="reservationrequest1.jsp" target="f3" style="text-decoration: none;color:white"> <%=resource.getString("opac.accountdetails.reservationrequest")%>
        &nbsp;|&nbsp;    <a href="newdemand2.jsp" target="f3" style="text-decoration: none;color:white"> <%=resource.getString("opac.accountdetails.newdemand")%></a>&nbsp;
        &nbsp;|&nbsp;    <a href="accountdetails.jsp" target="f3" style="text-decoration: none;color:white"><%=resource.getString("opac.accountdetails.home")%></a>
            
    </a>

            


          </b>
                  </td></tr></table>
        </td></tr>
  
    

    <tr><td height="300px" valign="top" class="btn1" align="right" colspan="2"><br><br>
            <table width="500px" class="btn1">
                <tr><td align="center" colspan="2" class="btn1" >
                        <b>  <%=resource.getString("opac.accountdetails.accountdetails")%></b><br><br>
        </td></tr>
                <tr><td align="right"><a href="view_finedetails.jsp" style="text-decoration: none;color:blue"><b>&nbsp;<%=resource.getString("opac.accountdetails.details")%></b></a><input type="text" id="TXTFINE" name="TXTFINE" value="<%=fine%>" disabled="disabled"></td><td align="left"><b><%=resource.getString("opac.accountdetails.finedue")%></b></td></tr>
                <tr><td align="right"><input type="text" id="TXTCHECKOUT"  name="TXTCHECKOUT" value="<%=no_of_chkout%>" disabled="disabled"></td><td align="left"><b><%=resource.getString("opac.accountdetails.checkouts")%></b></td></tr>
                <tr><td align="right"><b><a href="view_reservation.jsp" style="text-decoration: none;color:blue"><%=resource.getString("opac.accountdetails.details")%></a></b>&nbsp;<input type="text" id="TXTRESERVATION" name="TXTRESERVATION" value="<%=reservation_made%>" disabled="disabled"></td><td align="left"><b>Reservation Made</b></td></tr>
                <tr><td align="right"><input type="text" id="TXTCHKDATE" name="TXTCHKDATE" value="<%=lastchkoutdate%>" disabled="disabled"></td><td align="left"><b><%=resource.getString("opac.accountdetails.lastcheckoutdate")%></b></td></tr>
                <tr><td colspan="2" align="left"><br><br><br>
            <%
    String message=(String)request.getAttribute("msg");
    if(message!=null){
       %> <br>
	           <TABLE style="background-color: #E3E4FA;"
                    border="1" align="center">
		      <tr><th><%=message%></th></tr>
		   </TABLE>
   <% }else
        message="";
    %>

        </td></tr>

            </table>



        </td></tr>
    

</table>

  
    


</form>
 <%}%>

</body>
</html>