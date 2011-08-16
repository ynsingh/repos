<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.utility.DateCalculation"  %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%! boolean read=false;%>
<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String button=(String)request.getAttribute("button");
DateCalculation doc=new DateCalculation();
String cdate=doc.now();
//request.setAttribute("back", request.getAttribute("back"));

if (button.equals("View")||button.equals("Delete"))
read=true;

else
    read=false;
String msg1=(String) request.getAttribute("msg1");
%>
<script type="text/javascript">

            <!-- This script and many more are available free online at -->
            <!-- The JavaScript Source!! http://javascript.internet.com -->
            <!-- Created by: www.jdstiles.com -->
            <!-- Begin
            function startCalc(){
                interval = setInterval("calc()",1);
            }
            function calc(){
                one = document.getElementById(1).value;
                two = document.getElementById(2).value;

                document.getElementById(3).value = (one * 1) * (two * 1);


            }
            function stopCalc(){
                clearInterval(interval);
            }

 function back()
  {

   window.location="<%=request.getContextPath()%>/currency1.jsp";
      return false;
  }


      
function add(){
    var a=parseInt(document.getElementById('rcvd').value);
    var b=parseInt(document.getElementById('op').value);
    var c=document.getElementById('tm');
    c.value=a+b;
}
function check1()
{


     if(document.getElementById('unit_price').value=="0.0")
    {
        alert("Enter Value greater than 0");

        document.getElementById('unit_price').focus();

        return false;
    }
     
return true;
  }

function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }

  function confirm1()
{
var answer = confirm ("Do you want to delete record?")
if (answer!=true)
    {
        document.getElementById('button1').focus();
        return false;
    }
   else{ return true;}
}
function send()
{
    window.location="<%=request.getContextPath()%>/acquisition/acq_currency1.jsp";
    return false;
}
</script>


       
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="common" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
    <body>


   <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
   <div
   style="  top:150px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
   <html:form method="post" action="/currency3"  onsubmit="return check1();" >
       <table border="1" class="table" align="center">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;" ><b>Currency Conversion</b></td></tr>
                <tr><td>
             <table width="400px" border="0" cellspacing="4" cellpadding="1" align="left">
                       
<tr><td colspan="5" height="10px"></td>
</tr>
<tr><td colspan="5" height="10px"></td>
</tr>

  <tr><td align="left" class="txtStyle" ><strong>Date:</strong> </td><td>

        <html:text readonly="<%=read%>" property="date" value="<%=cdate%>" name="AcqCurrency1ActionForm" ></html:text></td></tr>
<tr>
    <td align="left" class="txtStyle"><strong>Source Currency:</strong></td>
    <td>

        <html:text readonly="true" property="sCountry" style="width: 200px;"/>
                                            

                                        </td>
   
</tr>
<input type="hidden" name="firstBox" id="1" value="1" onFocus="startCalc();" readonly onBlur="stopCalc();">

<tr><td align="left" class="txtStyle" ><strong>Unit</strong> </td><td>

        <input type="text" readonly="true" value="1" /></td></tr>



<tr>
   <td align="left" class="txtStyle"><strong>Target Currency:*</strong></td>
   <td>
       <input type="hidden"  name="dCountry" value="<%=session.getAttribute("basecurrencyid")%>"/>
       <input  type="text" readonly="true" value="<%=session.getAttribute("basecurrencyname")%>"/>
<html:hidden property="conid" name="AcqCurrency1ActionForm"  /> </td>
   



  
</tr>
<tr><td align="left" class="txtStyle" ><strong>Current Conversion Rate:</strong> </td><td>

        <html:text readonly="<%=read%>" property="secondBox"  styleId="unit_price" ></html:text></td></tr>



<tr><td colspan="5" height="5px"></td>
</tr>


<tr><td></td><td></td></tr>


<tr><td colspan="5" height="10px"></td>
</tr>
<tr>
<td align="center" colspan="5">
    <%if(button.equals("Update")){%>
    <input id="button1"  name="button" type="submit" value="<%=button%>"/>
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Back" onclick="return send()" />
    <%}else if(button.equals("Delete")){%>
    <input id="button1"  name="button" type="submit" onClick="return confirm1()" value="<%=button%>"  />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" onclick="return send()"  value="Back" />
   <%}else if(button.equals("Add")){%>
    <input id="button1"  name="button" type="submit" value="Submit" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" value="Back" onclick="return send()" />
    <%}else{%>
    <input  name="button" type="button" value="Back" onclick="return send();"/><%}%>
	</td>
</tr><tr><td colspan="5" height="5px"></td>
</tr>
</table>

                   

  </html:form>
       </div>
    </body>
</html>
