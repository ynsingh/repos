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



function validateAll()
{
    var sel=document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;



var cardno=document.forms[0].cardno.value;
var comm=document.forms[0].comments.value;
var email = document.forms[0].email.value;
var name = document.forms[0].name.value;
var reg = new RegExp("^[A-Za-z0-9_]{1,}[.]?[A-Za-z0-9_]{1,}@{1}([A-Za-z0-9_]+[.]{1})+[A-Za-z0-9_]{1,}$");

if(isEmpty(sel))
 {
alert("Please Select Library ID.");
document.getElementById('CMBLib').focus();
return false;
    }
if(isEmpty(cardno))
{
alert("Please type your card no.");
document.forms[0].cardno.focus();
return false;
}

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
//else if(!isEmpty(email))
//{
//if(!reg.test(email))
//{
//alert("Invalid E-mail address. Please re-enter.");
//document.forms[0].email.focus();
//return false;
//}
//}
if(isEmpty(comm))
{
alert("Please type your Comment.");
document.forms[0].comments.focus();
return false;
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
function quit()
{
    location.href="/LibMS-Struts/OPAC/opachome.jsp";
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
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
    <%if(page.equals(true)){%>

    <FORM  name="form1"action="Feedback.do" method="post" onsubmit="return validateAll();">
<table  align="left" width="600px"  style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">



  <tr><td  width="600px"  style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="left">
          <table>
              <tr><td width="600px" style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="center"><b>

		    <%=resource.getString("opac.feedback.yourfeedback")%>



          </b>
                  </td></tr>
              </table>
        </td></tr>

  <tr><td class="btn1" width="600px" >
          <br>

          <table align="center" width="600px" >
              <tr><td  align="right" colspan="2" class="mess" width="600px" >  <font color="blue">  <b><%=resource.getString("opac.feedback.note")%></b><br><br></font> </td></tr>
 <tr><td style="width:130px" align="left">Library ID</td><td width="200px">
         <select name="CMBLib"   id="CMBLib" size="1">
    <%
        ResultSet rs = (ResultSet)session.getAttribute("libRs");
        String lib_id = (String)session.getAttribute("library_id");

        rs.beforeFirst();

    if(lib_id==null)
    {%>

    <option selected value="">ALL</option>
    <%}
    else
    {%>
   
    <option value="">ALL</option>
     <option selected value="<%=lib_id%>"><%=lib_id.toUpperCase()%></option>

    <%
    }
    while (rs.next())
            {
    %>
    <option value="<%= rs.getString(1) %>"><%=rs.getString(1).toUpperCase()%></option>
    <% } %>
</select></td></tr>
              <tr><td style="width:130px" align="left"><%=resource.getString("opac.feedback.date")%>:</td><td>
              <input type="text" name="date1"  font="Arial" color="BLACK" disabled="disabled"  value=<%=date%>>
             </td></tr>

              <tr><td style="width:130px" align="left"><%=resource.getString("opac.feedback.cardno")%>*:</td><td>
                <input type="text" name="cardno">
             </td></tr>
              <tr><td style="width:130px" align="left"><%=resource.getString("opac.feedback.name")%>*:</td><td>
                 <input type="text" name="name">
             </td></tr>
               <tr><td style="width:130px" align="left"><%=resource.getString("opac.feedback.email")%>*:</td><td>
                 <input type="text" name="email">
             </td></tr>
               <tr><td style="width:130px" align="left"><%=resource.getString("opac.feedback.comments")%>*:</td><td>
                 <input type="text" name="comments"/>
             </td></tr>
               <tr><td></td><td>
              <input class="btn" type="submit" name="Submit" value="<%=resource.getString("opac.feedback.submit")%>" align="right">   <input class=btn type="reset" value="<%=resource.getString("opac.feedback.clear")%>" align="left">
              <input class=btn type="button" name="Cancel" value="<%=resource.getString("opac.feedback.cancel")%>" align="left" onclick="quit();">
                   </td></tr>

            </table>
          <br>
      </td></tr></table>




        

</FORM>
            <%}else{%>
            

<FORM  name="form1"action="Feedback.do" method="post" onsubmit="return validateAll();">
<table  align="left" width="600px"  style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">



  <tr><td  width="600px"  style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="left">
          <table>
              <tr><td width="600px" style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="center"><b>

		    <%=resource.getString("opac.feedback.yourfeedback")%>



          </b>
                  </td></tr>
              </table>
        </td></tr>

  <tr><td class="btn1" width="600px" >
          <br>

          <table align="center" width="600px" >
              <tr><td  align="left" colspan="2" class="mess" width="600px" >  <font color="blue">  <b><%=resource.getString("opac.feedback.note")%></b><br><br></font> </td></tr>

              <tr><td style="width:130px" align="right">
              <input type="text" name="date1"  font="Arial" color="BLACK" disabled="disabled"  value=<%=date%>>
             </td><td ><%=resource.getString("opac.feedback.date")%>:</td></tr>

              <tr><td style="width:130px" align="right">
                <input type="text" name="cardno">
             </td><td style="width:130px" align="left">:*<%=resource.getString("opac.feedback.cardno")%></td></tr>
              <tr><td style="width:130px" align="right">
                 <input type="text" name="name">
             </td><td style="width:130px" align="left">:*<%=resource.getString("opac.feedback.name")%></td></tr>
               <tr><td style="width:130px" align="right">
                 <input type="text" name="email">
             </td><td style="width:130px" align="left">:*<%=resource.getString("opac.feedback.email")%></td></tr>
               <tr><td style="width:130px" align="right">
                 <input type="text" name="comments" />
             </td><td style="width:130px" align="left">:*<%=resource.getString("opac.feedback.comments")%></td></tr>
               <tr><td style="width:130px" align="right">
              <input class=btn type="button" name="Cancel" value="<%=resource.getString("opac.feedback.cancel")%>" align="left" onclick="quit();">
                       <input class=btn type="reset" value="<%=resource.getString("opac.feedback.clear")%>" align="left">
               <input class="btn" type="submit" name="Submit" value="<%=resource.getString("opac.feedback.submit")%>" align="right">
              
                   </td><td></td></tr>

            </table>
          <br>
      </td></tr></table>






</FORM>
            
            <%}%>

</body>
</html>
