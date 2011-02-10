<%--
    Document   : ReservationReques1.jsp
    Created on : July 17, 2010, 7:15:37 PM
    Author     : Mayank Saxena
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%@page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
<body>
<%
String card_id,mem_id;
//Retrieving the values of NewMember form in variables.
card_id=(String)session.getAttribute("card_id");
mem_id=(String)session.getAttribute("mem_id");

 String name; 
  name=(String)session.getAttribute("memname");
%>

<html:form method="post" action="/OPAC/ReservationRequest">

  <%if(page.equals(true))
    {

%>

<table  align="left" width="700px"  style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">



  <tr><td  width="700px"  style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="left">
          <table>
              <tr><td width="550px" style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="left"><b>


	&nbsp;&nbsp;
                <a href="accountdetails.jsp" target="f3" style="text-decoration: none;color:white"><%=resource.getString("opac.accountdetails.home")%></a>&nbsp;|&nbsp;
            <a href="newdemand2.jsp" target="f3" style="text-decoration: none;color:white"> <%=resource.getString("opac.accountdetails.newdemand")%></a>&nbsp;
    |&nbsp;<a href="reservationrequest1.jsp" target="f3" style="text-decoration: none;color:white"> <%=resource.getString("opac.accountdetails.reservationrequest")%></a>




          </b>
                  </td><td align="right" style="color:white;font-family:Tahoma;font-size:12px"><%=resource.getString("opac.accountdetails.hi")%>&nbsp;<%=name%>&nbsp;<b>|</b>&nbsp;<a href="home.do" target="f3" style="text-decoration: none;color:white"><%=resource.getString("opac.accountdetails.logout")%></a></td></tr></table>
        </td></tr>


    
      <tr><td colspan="3"  valign="top" class="btn1" align="center">
             <table align="center" width="700px">
 <tr><td align="center" style="color:Brown" colspan="3">
         <table align="center" width="700px">
             <tr><td width="350px" align="center">
       <b><%=resource.getString("opac.myaccount.reservationrequest.text")%></b></td><td><font color="blue" align="right"> <b><%=resource.getString("opac.myaccount.reservationrequest.note")%></b><br><br></font>
                 </td></table>
         </td></tr>

                 <tr><td align="left" width="150px"><%=resource.getString("opac.myaccount.reservationrequest.cardid")%></td><td align="left" width="200px"><html:text property="TXTCARDID"  value="<%=card_id%>" readonly="true"  /></td></tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.category")%>*</td><td align="left">
        <html:select property="CMBCAT" size="1">
    <html:option value="">Select</html:option>
<html:option value="books">BOOK</html:option>
<html:option value="journals">JOURNAL</html:option>
<html:option value="others">OTHERS</html:option>
</html:select>


    </td>
<td  class="err" align="left">   <html:messages id="err_name"  property="CMBCAT">
            <bean:write name="err_name" />
             </html:messages>
         </td>

</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.accessionno")%>*</td><td align="left"><html:text  property="accessionno"/></td>
<td  class="err" align="left">   <html:messages id="err_name"  property="accessionno">
            <bean:write name="err_name" />
             </html:messages>
         </td>

</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.title")%>*</td><td align="left"><html:text  property="TXTTITLE"  /></td>
<td  class="err" align="left">   <html:messages id="err_name"  property="TXTTITLE">
            <bean:write name="err_name" />
             </html:messages>
         </td>
</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.author")%>*</td><td align="left"><html:text  property="TXTAUTHOR"  /></td>
<td  class="err" align="left">   <html:messages id="err_name"  property="TXTAUTHOR">
            <bean:write name="err_name" />
             </html:messages>
         </td>
</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.isbn")%></td><td align="left"><html:text  property="TXTISBN"  /></td></tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.issn")%></td><td align="left"><html:text  property="issn"  /></td></tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.callno")%>*</td><td align="left"><html:text  property="TXTCALLNO"   /></td>
<td  class="err" align="left">   <html:messages id="err_name"  property="TXTCALLNO">
            <bean:write name="err_name" />
             </html:messages>
         </td>

</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.publisher")%></td><td align="left"><html:text  property="TXTPUBL"   /></td></tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.publishingyear")%></td><td align="left"><html:text  property="pub_year"  /></td></tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.remark")%></td><td align="left"><html:textarea  property="TXTREMARKS"   rows="2" cols="24"/></td></tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.language")%></td><td align="left"><html:text  property="lang" /></td></tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.edition")%></td><td align="left"><html:text  property="TXTEDITION" /></td></tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.volume")%></td><td align="left"><html:text  property="TXTVOL"  /></td></tr>

<tr><td></td><td  align="left"><html:submit styleClass="btn"><%=resource.getString("opac.myaccount.reservationrequest.send")%></html:submit>&nbsp;&nbsp;<input type="button"   name="cancel" value="<%=resource.getString("opac.myaccount.reservationrequest.cancel")%>" class="txt2" onclick="quit()"><br/></td></tr>
 </table>


         </td></tr></table>

<%}else{%>
<table  align="left" width="800px"  style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">




  <tr><td  width="800px"  style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="right">
          <table>
              <tr>
                  <td width="520px" align="left" style="color:white;font-family:Tahoma;font-size:12px"><a href="home.do" target="f3" style="text-decoration: none;color:white"><%=resource.getString("opac.accountdetails.logout")%></a>&nbsp;|&nbsp;<%=resource.getString("opac.accountdetails.hi")%>&nbsp;<%=name%></td>

                  <td  style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="right"><b>


	&nbsp;&nbsp;<a href="reservationrequest1.jsp" target="f3" style="text-decoration: none;color:white"> <%=resource.getString("opac.accountdetails.reservationrequest")%>
        &nbsp;|&nbsp;    <a href="newdemand2.jsp" target="f3" style="text-decoration: none;color:white"> <%=resource.getString("opac.accountdetails.newdemand")%></a>&nbsp;
        &nbsp;|&nbsp;    <a href="accountdetails.jsp" target="f3" style="text-decoration: none;color:white"><%=resource.getString("opac.accountdetails.home")%></a>

    </a>




          </b>
                  </td></tr></table>
        </td></tr>



      <tr><td colspan="3"  valign="top" class="btn1" align="center">
             <table align="center" width="700px">
 <tr><td align="center" style="color:Brown" colspan="3">
         <table align="center" width="700px">
             <tr><td width="400px"><font color="blue" align="right"> <b><%=resource.getString("opac.myaccount.reservationrequest.note")%></b><br><br></font>
                 </td><td width="350px" align="center">
       <b><%=resource.getString("opac.myaccount.reservationrequest.text")%></b></td></table>
         </td></tr>

                 <tr><td></td><td align="right" width="200px"><html:text property="TXTCARDID"  value="<%=card_id%>" readonly="true"  /></td><td align="left" width="150px"><%=resource.getString("opac.myaccount.reservationrequest.cardid")%></td></tr>
<tr><td  class="err" align="right">   <html:messages id="err_name"  property="CMBCAT">
            <bean:write name="err_name" />
             </html:messages>
         </td><td align="right">
        <html:select property="CMBCAT" size="1">
    <html:option value="">Select</html:option>
<html:option value="books">BOOK</html:option>
<html:option value="journals">JOURNAL</html:option>
<html:option value="others">OTHERS</html:option>
</html:select>


    </td>
<td align="left">*<%=resource.getString("opac.myaccount.reservationrequest.category")%></td>

</tr>
<tr><td  class="err" align="right">   <html:messages id="err_name"  property="accessionno">
            <bean:write name="err_name" />
             </html:messages>
         </td><td align="right"><html:text  property="accessionno"/></td>
<td align="left">*<%=resource.getString("opac.myaccount.reservationrequest.accessionno")%></td>

</tr>
<tr><td  class="err" align="right">   <html:messages id="err_name"  property="TXTTITLE">
            <bean:write name="err_name" />
             </html:messages>
         </td><td align="right"><html:text  property="TXTTITLE"  /></td>
<td align="left">*<%=resource.getString("opac.myaccount.reservationrequest.title")%></td>
</tr>
<tr><td  class="err" align="right">   <html:messages id="err_name"  property="TXTAUTHOR">
            <bean:write name="err_name" />
             </html:messages>
         </td><td align="right"><html:text  property="TXTAUTHOR"  /></td>
<td align="left">*<%=resource.getString("opac.myaccount.reservationrequest.author")%></td>
</tr>
<tr><td></td><td align="right"><html:text  property="TXTISBN"  /></td><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.isbn")%></td></tr>
<tr><td></td><td align="right"><html:text  property="issn"  /></td><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.issn")%></td></tr>
<tr><td  class="err" align="right">   <html:messages id="err_name"  property="TXTCALLNO">
            <bean:write name="err_name" />
             </html:messages>
         </td><td align="right"><html:text  property="TXTCALLNO"   /></td>
<td align="left">*<%=resource.getString("opac.myaccount.reservationrequest.callno")%></td>

</tr>
<tr><td></td><td align="right"><html:text  property="TXTPUBL"   /></td><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.publisher")%></td></tr>
<tr><td></td><td align="right"><html:text  property="pub_year"  /></td><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.publishingyear")%></td></tr>
<tr><td></td><td align="right"><html:textarea  property="TXTREMARKS"   rows="2" cols="24"/></td><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.remark")%></td></tr>
<tr><td></td><td align="right"><html:text  property="lang" /></td><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.language")%></td></tr>
<tr><td></td><td align="right"><html:text  property="TXTEDITION" /></td><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.edition")%></td></tr>
<tr><td></td><td align="right"><html:text  property="TXTVOL"  /></td><td align="left"><%=resource.getString("opac.myaccount.reservationrequest.volume")%></td></tr>

<tr><td  align="right" colspan="2"><input type="button"   name="cancel" value="<%=resource.getString("opac.myaccount.reservationrequest.cancel")%>" class="txt2" onclick="quit()">&nbsp;&nbsp;<html:submit styleClass="btn"><%=resource.getString("opac.myaccount.reservationrequest.send")%></html:submit><br/></td><td></td></tr>
 </table>


         </td></tr></table>
<%}%>


<input type="hidden" id="id" value="<%=card_id%>" name="id"/>




</html:form>
</body>
</html>
<script>
    function quit()
    {
        location.href="/LibMS-Struts/OPAC/accountdetails.jsp";
    }
</script>