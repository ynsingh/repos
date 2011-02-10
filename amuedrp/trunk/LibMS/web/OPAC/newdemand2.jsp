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



  <%if(page.equals(true))
    {

%>
<html:form method="post" action="/OPAC/NewDemand">
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
       <b><%=resource.getString("opac.myaccount.newdemand.newdemandtext")%></b></td><td><font color="blue" align="right"> <b><%=resource.getString("opac.myaccount.newdemand.note")%></b><br><br></font>
                 </td></table>
         </td></tr>
     
<tr><td align="left" width="150px"><%=resource.getString("opac.myaccount.newdemand.category")%>*</td><td align="left" width="200px">
        <html:select property="CMBCAT" size="1" >
            <html:option value="">Select Any</html:option>
            <html:option value="books">books</html:option>
<html:option value="journals">JOURNAL</html:option>
<html:option value="others">OTHERS</html:option>
</html:select>


    </td>
   <td  class="err" align="left">   <html:messages id="err_name"  property="CMBCAT">
            <bean:write name="err_name" />
             </html:messages>
         </td>
</tr>

<tr><td align="left"><%=resource.getString("opac.myaccount.newdemand.title")%>*</td><td align="left"><html:text property="TXTTITLE" /></td>
 <td  class="err" align="left">   <html:messages id="err_name"  property="TXTTITLE">
            <bean:write name="err_name" />
             </html:messages>
         </td>
</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.newdemand.author")%>*</td><td align="left"><html:text property="TXTAUTHOR"   /></td>
 <td  class="err" align="left">   <html:messages id="err_name"  property="TXTAUTHOR">
            <bean:write name="err_name" />
             </html:messages>
         </td>
</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.newdemand.isbn")%></td><td align="left"><html:text property="TXTISBN"/></td>
 <td  class="err">
         </td>
</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.newdemand.issn")%></td><td align="left"><html:text property="issn"  /></td>
 <td  class="err">
         </td>
</tr>

<tr><td align="left"><%=resource.getString("opac.myaccount.newdemand.publisher")%></td><td align="left"><html:text property="TXTPUB"  /></td>
 <td  class="err">
         </td>
</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.newdemand.publishingyear")%></td><td align="left"><html:text property="TXTPUBYR" /></td>
 <td  class="err">
         </td>
</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.newdemand.remark")%></td><td align="left"><html:textarea property="TXTREMARK"   rows="2" cols="24"/></td>
 <td  class="err">
         </td>
</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.newdemand.language")%></td><td align="left"><html:text property="lang" /></td>
 <td  class="err">
         </td>
</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.newdemand.edition")%></td><td align="left"><html:text property="TXTEDITION" /></td>
 <td  class="err">
         </td>
</tr>
<tr><td align="left"><%=resource.getString("opac.myaccount.newdemand.volume")%></td><td align="left"><html:text property="TXTVOL" /></td>
 <td  class="err">
         </td>
</tr>

<tr><td align="left"><%=resource.getString("opac.myaccount.newdemand.noofcopies")%>*</td><td align="left"><html:text property="TXTCOPY"  /></td>
 <td  class="err" align="left">   <html:messages id="err_name"  property="TXTCOPY">
            <bean:write name="err_name" />
             </html:messages>
         </td>
</tr>

<tr><td></td><td  align="left"><html:submit styleClass="btn"><%=resource.getString("opac.myaccount.newdemand.send")%></html:submit>&nbsp;&nbsp;<input type="button"   name="cancel" value="<%=resource.getString("opac.myaccount.newdemand.cancel")%>" class="txt2" onclick="quit()"><br></td></tr>
 </table>


         </td></tr></table>
</html:form>
<%}else{%>
<html:form method="post" action="/OPAC/NewDemand">
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
             <tr><td width="300px"><font color="blue" align="right"> <b><%=resource.getString("opac.myaccount.newdemand.note")%></b><br><br></font>
                 </td><td>
                     <b><%=resource.getString("opac.myaccount.newdemand.newdemandtext")%></b></td></tr></table>
         </td></tr>

<tr><td  class="err" align="right" width="250px">   <html:messages id="err_name"  property="CMBCAT">
            <bean:write name="err_name" />
             </html:messages>
         </td><td align="right" width="200px">
        <html:select property="CMBCAT" size="1" >
            <html:option value="">Select Any</html:option>
            <html:option value="books">books</html:option>
<html:option value="journals">JOURNAL</html:option>
<html:option value="others">OTHERS</html:option>
</html:select>


    </td>
   <td align="left">*<%=resource.getString("opac.myaccount.newdemand.category")%></td>
</tr>

<tr><td  class="err" align="right">   <html:messages id="err_name"  property="TXTTITLE">
            <bean:write name="err_name" />
             </html:messages>
         </td><td align="right"><html:text property="TXTTITLE" /></td>
 <td align="left">*<%=resource.getString("opac.myaccount.newdemand.title")%></td>
</tr>
<tr> <td  class="err" align="right">   <html:messages id="err_name"  property="TXTAUTHOR">
            <bean:write name="err_name" />
             </html:messages>
         </td><td align="right"><html:text property="TXTAUTHOR"   /></td>
<td align="left">*<%=resource.getString("opac.myaccount.newdemand.author")%></td>
</tr>
<tr><td  class="err">
         </td><td align="right"><html:text property="TXTISBN"/></td>
 <td align="left"><%=resource.getString("opac.myaccount.newdemand.isbn")%></td>
</tr>
<tr> <td  class="err">
         </td><td align="right"><html:text property="issn"  /></td>
<td align="left"><%=resource.getString("opac.myaccount.newdemand.issn")%></td>
</tr>

<tr> <td  class="err">
         </td><td align="right"><html:text property="TXTPUB"  /></td>
<td align="left"><%=resource.getString("opac.myaccount.newdemand.publisher")%></td>
</tr>
<tr><td  class="err">
         </td><td align="right"><html:text property="TXTPUBYR" /></td>
 <td align="left"><%=resource.getString("opac.myaccount.newdemand.publishingyear")%></td>
</tr>
<tr> <td  class="err">
         </td><td align="right"><html:textarea property="TXTREMARK"   rows="2" cols="24"/></td>
<td align="left"><%=resource.getString("opac.myaccount.newdemand.remark")%></td>
</tr>
<tr><td  class="err">
         </td><td align="right"><html:text property="lang" /></td>
 <td align="left"><%=resource.getString("opac.myaccount.newdemand.language")%></td>
</tr>
<tr><td  class="err">
         </td><td align="right"><html:text property="TXTEDITION" /></td>
 <td align="left"><%=resource.getString("opac.myaccount.newdemand.edition")%></td>
</tr>
<tr><td  class="err">
         </td><td align="right"><html:text property="TXTVOL" /></td>
 <td align="left"><%=resource.getString("opac.myaccount.newdemand.volume")%></td>
</tr>

<tr><td  class="err" align="right">   <html:messages id="err_name"  property="TXTCOPY">
            <bean:write name="err_name" />
             </html:messages>
         </td><td align="right"><html:text property="TXTCOPY"  /></td>
 <td align="left">*<%=resource.getString("opac.myaccount.newdemand.noofcopies")%></td>
</tr>

<tr><td  align="right" colspan="2"><input type="button"   name="cancel" value="<%=resource.getString("opac.myaccount.newdemand.cancel")%>" class="txt2" onclick="quit()">&nbsp;&nbsp;<html:submit styleClass="btn"><%=resource.getString("opac.myaccount.newdemand.send")%></html:submit><br></td><td></td></tr>
 </table>


         </td></tr></table>
</html:form>
<%}%>
<input type="hidden" id="id" value="<%=card_id%>" name="id" >





</body>
</html>
<script>
    function quit()
    {
        location.href="/LibMS-Struts/OPAC/accountdetails.jsp";
    }
</script>