<%--
    Document   : FeedBack.jsp
    Created on : Jun 5, 2010, 5:15:00 PM
    Author     : Mayank Saxena
--%>
<%@ page errorPage = "ErrorPage.jsp" language="java" import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>

<%
String name="", email="", comments="",cardno="", date="" ;
Connection conn=null;
  Calendar cal = new GregorianCalendar();
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    int day = cal.get(Calendar.DAY_OF_MONTH);
    date=day+"/"+ (month+1) +"/"+year;

%>
<!--jsp:include page="feedback.htm"/-->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>FEEDBACK...</title>
<!?Applying styles for the page. -->
<!--Applying client side validations using JavaScript. -->
<script language="javascript">
function isEmpty(str)
{
if(str=="")
{
return true;
}
else return false;
}
<!--The validateAll() function checks if any field is left blank by the customer and displays the alert message. -->
function validateAll()
{
var email = document.forms[0].email.value;
var name = document.forms[0].name.value;
var reg = new RegExp("^[A-Za-z0-9_]{1,}[.]?[A-Za-z0-9_]{1,}@{1}([A-Za-z0-9_]+[.]{1})+[A-Za-z0-9_]{1,}$");
if(isEmpty(name))
{
alert("Please type your name.");
document.forms[0].name.focus();
return false;
}
else if(isEmpty(email))
{
alert("Please type your e-mail address.");
document.forms[0].email.focus();
return false;
}
else if(!isEmpty(email))
{
if(!reg.test(email))
{
alert("Invalid E-mail address. Please re-enter.");
document.forms[0].email.focus();
return false;
}
}
}
<!--The func_subm() function submits the text field values to the server after validating them. -->
function func_subm()
{
if(validateAll())
{
frmRegister.action="feedback.jsp";
frmRegister.method="post";
frmRegister.submit();
}
}
</script>
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
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <%if(page.equals(true)){%>

    <FORM  name="form1"action="Feedback.do" method="post">
<table align=center border=0 cellpadding=0 cellspacing=0 width="98%">
<tbody>

<!-- Top hyperlink row ends. --> </tbody>
</table>
  <div id="wb_Text3" style="position: absolute; left: 50px; top: 10px; width: 1000px; height: 22px; background-color: rgb(255, 255, 224); z-index: 7;" align="left">
<font style="font-size: 19px;" color="#000000" face="Arial"><b> &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<%=resource.getString("opac.feedback.yourfeedback")%></b></font></div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
 <br> <br>
<table width="85%" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
            <td width="20%">&nbsp;
</td>
            <td width="12%">&nbsp;
</td>
            <td colspan="2">&nbsp;
</td>
      </tr>
      <tr>
            <td width="20%">&nbsp;</td>
            <td width="12%" align="left"><font face="Arial, Helvetica, sans-serif" size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=resource.getString("opac.feedback.date")%>:</font>
</td>
<td colspan="2">
                <input type="text" name="date1"  font="Arial" color="BLACK" disabled="disabled"  value=<%=date%>>
            </td>
     </tr><tr><td><br></td></tr>
      <tr>
            <td width="20%">&nbsp;</td>
            <td width="12%" align="left"><font face="Arial, Helvetica, sans-serif" size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=resource.getString("opac.feedback.cardno")%>:</font>
</td>
            <td colspan="2">
            <input type="text" name="cardno">
            </td>
     </tr><tr><td><br></td></tr>
      <tr>
            <td width="20%">&nbsp;</td>
            <td width="12%" align="left"><font face="Arial, Helvetica, sans-serif" size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=resource.getString("opac.feedback.name")%>:</font>
</td>
            <td colspan="2">
                <input type="text" name="name">
            </td>
     </tr><tr><td><br></td></tr>
     <tr>
            <td width="20%">&nbsp;
</td>
<td width="12%" align="left"><font face="Arial, Helvetica, sans-serif" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=resource.getString("opac.feedback.email")%>:</font>
</td>
            <td colspan="2">
            <input type="text" name="email">
            </td>
</tr><tr><td><br></td></tr>
      <tr>
          <td width="20%">&nbsp;
</td>
<td width="12%" align="left"><font face="Arial, Helvetica, sans-serif" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=resource.getString("opac.feedback.comments")%>:</font>
</td>
            <td colspan="2">
<textarea name="comments"> </textarea>
            </td>
</tr><tr><td><br></td></tr>
      <tr>
            <td width="20%">&nbsp;</td>
            <td width="12%">&nbsp;</td>
            <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
            <td height="29" width="20%">
            <div align="center"> </div>
            </td>
            <td height="29" width="30%" colspan="3" align="right">
                <div align="center">
               <input class=bu type="submit" name="Submit" value="<%=resource.getString("opac.feedback.submit")%>" align="right">   <input class=bu type="reset" name="Cancel" value="<%=resource.getString("opac.feedback.cancel")%>" align="left">   </div>
            </td>
<td width="30%" height="29">&nbsp;
</td>
</tr>
      </table>
<hr color=#c0003b size=1>


</table>

</FORM>
            <%}else{%>
            
            <FORM  name="form1"action="Feedback.do" method="post">
<table align=center border=0 cellpadding=0 cellspacing=0 width="98%">
<tbody>

<!-- Top hyperlink row ends. --> </tbody>
</table>
  <div id="wb_Text3" style="position: absolute; left:180px;  top: 10px; width: 1000px; height: 22px; background-color: rgb(255, 255, 224); z-index: 7;" align="left">
<font style="font-size: 19px;" color="#000000" face="Arial"><b>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<%=resource.getString("opac.feedback.yourfeedback")%></b></font></div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td><br><br>
<table width="85%" border="0" cellspacing="0" cellpadding="0" align="center">
      <tr>
            <td colspan="2">&nbsp;
</td>
 
                  <td width="12%">&nbsp;
</td>
<td width="20%">&nbsp;
</td>

      </tr>
          <tr>

          <td>&nbsp;</td>

          <td align="right">
                <input type="text" name="date1"  font="Arial" color="BLACK" disabled="disabled"  value=<%=date%>>
            </td>
               <td align="left"><font face="Arial, Helvetica, sans-serif" size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:<%=resource.getString("opac.feedback.date")%></font>
</td>
          </tr><tr><td><br></td></tr>
           <tr>
            <td>&nbsp;</td>

            <td align="right">
            <input type="text" name="cardno">
            </td>
                <td align="left"><font face="Arial, Helvetica, sans-serif" size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:<%=resource.getString("opac.feedback.cardno")%></font>
</td>
           </tr><tr><td><br></td></tr>
      <tr>
            <td>&nbsp;</td>

            <td align="right">
                <input type="text" name="name">
            </td>
               <td align="left"><font face="Arial, Helvetica, sans-serif" size="2"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:<%=resource.getString("opac.feedback.name")%></font>
</td>
      </tr><tr><td><br></td></tr>
     <tr>
            <td>&nbsp;
</td>

<td align="right">
            <input type="text" name="email">
            </td>
<td align="left"><font face="Arial, Helvetica, sans-serif" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:<%=resource.getString("opac.feedback.email")%></font>
</td>
     </tr><tr><td><br></td></tr>
      <tr>
          <td>&nbsp;
</td>

<td align="right">
<textarea name="comments"> </textarea>
            </td>
<td align="left"><font face="Arial, Helvetica, sans-serif" size="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:<%=resource.getString("opac.feedback.comments")%></font>
</td>
      </tr>
      <tr>
           <td colspan="2">&nbsp;</td>
           <td width="12%">&nbsp;</td>
           <td width="20%">&nbsp;</td>
        
      </tr>
      <tr>
         <td width="30%" height="29">&nbsp;
</td>

            <td height="29" width="20%" align="right">
                <div align="center">
                <input class=bu type="reset" name="Cancel" value="<%=resource.getString("opac.feedback.cancel")%>" align="left"> <input class=bu type="submit" name="Submit" value="<%=resource.getString("opac.feedback.submit")%>" align="right">    </div>
            </td>
<td height="29" width="20%">
            <div align="center"> </div>
            </td>
</tr>
      </table>
<hr color=#c0003b size=1>


</table>

</FORM>
            
            
            <%}%>

</body>
</html>
