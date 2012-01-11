<%-- 
    Document   : cir_checkout_report
    Created on : Apr 11, 2011, 12:43:03 AM
    Author     : edrp02
--%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/admin/header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout Report : LibMS</title>
<script language="javascript">
function fun()
{

    document.getElementById("form1").action ="<%=request.getContextPath()%>/checkoutReport.do"
    document.getElementById("form1").method="post";
    document.getElementById("form1").target="f4";
    document.getElementById("form1").submit();
}
function print()
{

    document.getElementById("form1").action ="<%=request.getContextPath()%>/checkoutReportPrint.do"
    document.getElementById("form1").method="post";
    document.getElementById("form1").target="";
    document.getElementById("form1").submit();
}
</script>
     <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
%>
<%
 String lib_id = (String)session.getAttribute("library_id");
  String sublib_id = (String)session.getAttribute("memsublib");
        if(sublib_id==null)sublib_id= (String)session.getAttribute("sublibrary_id");
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
    %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>

<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.js"></script>
<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>
    <script language="javascript" type="text/javascript">

var availableSelectList1;
var availableSelectList2;

var str1;
function dcheck() {

availableSelectList1 = document.getElementById("searchResult1");

    if (isValidDate(start_date.value)==false)
    {

        availableSelectList1.innerHTML=str1;
		start_date.value="";

                start_date.focus();
		return false;
	}
        else
            {
              availableSelectList1.innerHTML="";
            }
}
function dcheck1() {

availableSelectList2 = document.getElementById("searchResult2");

    if (isValidDate(end_date.value)==false)
    {
         availableSelectList2.innerHTML=str1;
		end_date.value="";

                end_date.focus();
		return false;
	}
        else
    {
    availableSelectList2.innerHTML="";
    }
}

function isValidDate(dateStr) {
// Checks for the following valid date formats:
// YYYY-mm-dd
// Also separates date into month, day, and year variables

var datePat = /^(\d{4})(\-)(\d{1,2})\2(\d{1,2})$/;

// To require a 4 digit year entry, use this line instead:
// var datePat = /^(\d{4})(\-)(\d{1,2})\2(\d{1,2})$/;

var matchArray = dateStr.match(datePat); // is the format ok?
if (matchArray == null) {
str1="Date is not in a valid format.";
return false;
}
month = matchArray[3]; // parse date into variables
day = matchArray[4];
year = matchArray[1];
if (month < 1 || month > 12) { // check month range
str1="Month must be between 1 and 12.";
return false;
}
if (day < 1 || day > 31) {
str1="Day must be between 1 and 31.";

return false;
}
if ((month==4 || month==6 || month==9 || month==11) && day==31) {
str1="Month "+month+" doesn't have 31 days!";

return false
}
if (month == 2) { // check for february 29th
var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
if (day>29 || (day==29 && !isleap)) {
str1="February " + year + " doesn't have " + day + " days!";

return false;
   }
}
return true;  // date is valid
}
//  End -->






/**
 * DHTML email validation script. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */









</script>
<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>
<script>



$(document).ready(function()
{
   var jQueryDatePicker1Opts =
   {
      dateFormat: 'yy-mm-dd',
      changeMonth: false,
      changeYear: false,
      showButtonPanel: false,
      showAnim: 'show'
   };
   $("#starting_date").datepicker(jQueryDatePicker1Opts);
   $("#end_date").datepicker(jQueryDatePicker1Opts);




});

    </script>


 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
  </head>
  <body onload="fun()">

      <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

       <html:form  method="post" action="/checkoutReport" target="f4" styleId="form1">
           <table  dir="<%=rtl%>" class="table" width="50%"  align="center">


                <tr><td dir="<%=rtl%>" align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("circulation.cir_checkout_report.chekoutreport")%></td></tr>
                <tr><td dir="<%=rtl%>" valign="top" align="center">
                        <table width="90%">
                 <tr>
                     <td dir="<%=rtl%>" align="left" valign="top" ><br/><%=resource.getString("circulation.cir_newmember.memberid")%>:</td><td><html:text property="memid" styleId="memid"  value=""  onchange="fun()" /> </td>
        </tr>

        <tr>
            <td dir="<%=rtl%>" align="left" valign="top">Issue Date from
            </td><td><html:text property="starting_date" styleId="starting_date"  value=""  onchange="fun()"/>
         
        to
        <html:text property="end_date" styleId="end_date"  value=""  onchange="fun()"/> 
         <br/>    <input type="submit" value="<%=resource.getString("opac.simplesearch.find")%>"  onclick="fun()"/>
             <input type="reset" value="<%=resource.getString("opac.simplesearch.clear")%>"  onclick="clear()"/>
             <input type="reset" value="Print Report"  onclick="print()"/>

         </td>


 


        </tr></table>
      </tr>
      <tr><td valign="top">
           <IFRAME  src=""  frameborder=1  height="400px" width="100%" scrolling="no" name="f4" id="f4"></IFRAME>

      

          </td></tr>
                </table>

                    </html:form>


        </div>
    </body>


</html>

                    
      


             

             

    