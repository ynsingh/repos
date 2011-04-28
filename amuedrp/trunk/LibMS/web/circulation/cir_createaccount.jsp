<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
      This jsp page is used for  Create Account By Accepting password for OPAC NewMember Entry For Particular Member
      This jsp page is Fifth page  for one Complete Process  of member Registration.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/admin/header.jsp" flush="true" />
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String mem_id=(String)request.getParameter("mem_id");
String mem_name=(String)request.getParameter("mem_name");
String last_name=(String)request.getParameter("last_name");
String mail_id=(String)request.getParameter("mail_id");

%>
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <title>LibMS</title>
    </head>
      <script language="javascript" type="text/javascript">
          function check2()
{
    KeyValue=document.getElementById('mem_id').value;
        KeyValue1=document.getElementById('password').value;

        if(KeyValue==KeyValue1)
            {
               availableSelectList = document.getElementById("searchResult");

               availableSelectList.innerHTML="LoginId and Password Should not be same";
                document.getElementById('password').value="";

            }
else{
 availableSelectList = document.getElementById("searchResult");

               availableSelectList.innerHTML="";

}

}

function check1()
{



   var x=document.getElementById('password');
        if(x.value=="")
            {
                alert("Password should not be blank");
                document.getElementById('password').focus();
                return false;
            }
     if(document.getElementById('password1').value=="")
    {
        alert("Enter Confirm password...");

        document.getElementById('password1').focus();
 document.getElementById('password1').focus();
        return false;
    }
          var x1=document.getElementById('password');
        var x2=document.getElementById('password1');
        if(x1.value!=x2.value)
            {
                alert("password mismatch");
                document.getElementById('password1').focus();
                return false;
            }




var x=document.getElementById('card_id');
        if(x.value=="")
            {
                alert("Card Id should not be blank");
                 document.getElementById('card_id').focus();
                return false;
            }
    return true;
  }
   function dupli()
    {







      var x1=document.getElementById('password');
        var x2=document.getElementById('password1');
        if(x1.value!=x2.value)
            {
                alert("password mismatch");
                document.getElementById('password').value="";
                document.getElementById('password1').value="";
                document.getElementById('password').focus();
                return false;
            }
            else
                return true;




                return true;


    }

    function quit()
    {
       window.location="<%=request.getContextPath()%>/admin/main.jsp";
       return false;

    }

    </script>

    <body >


  <html:form  action="/cir_account" method="post" onsubmit="return check1()">
      <table width="400px"   valign="top" align="center" class="table">
          <tr><td   class="headerStyle" valign="top" align="center" height="10px">Manage Member Account</td></tr>
        <tr><td   valign="top" align="center">

                    <table width="400px" align="center" width="100%">
                        <tr><td class="btn" width="250px">Member ID/Login ID</td><td width="250px"><input type="text" id="mem_id"  name="mem_id" readonly  value="<%=mem_id%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn">Member Name</td><td><input type="text"  name="mem_name" id="mem_name"   readonly  value="<%=mem_name%> <%=last_name%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn" >Email ID</td><td><input type="text"  name="mail_id" id="mail_id" readonly  name="Editbox2" value="<%=mail_id%>"/> </td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn">Password</td><td><input type="password"   name="password"  id="password" onblur="check2()"  value=""><br/>
                            <div align="left" id="searchResult" class="err" style="border:#000000; "></div>
                            </td></tr>
                        <tr><td colspan="2" height="5px" align="right"></td></tr>
                        <tr><td class="btn">Confirm Password</td><td><input type="password"   name="password1" id="password1" onblur="dupli();"  value=""></td></tr>

                         <tr><td class="btn">Card Type</td><td>
                                 <select id="card" name="card_type">
                                     <option value="sc">Smart Card</option>
                                     <option value="gc" selected>General Card</option>
                                 </select>
                                 </td></tr>


                        <tr><td class="btn">Card ID</td><td><input type="text"   name="card_id" id="card_id"   value=""></td></tr>

                        <tr><td colspan="2" align="center">
                                <br>
                                <br>
                         <input type="submit" id="Button1" name="button"  value="Submit" class="btn" onclick="return dupli()">
                         <input type="reset" id="Button2" value="Reset" onclick=" " class="btn">
                         <input type="button" id="Button3"  value="Back" onclick=" return quit()" class="btn">
                         <br><br>
                            </td></tr>

<input type="hidden" value="<%=mem_name%>" name="mem_name"/>
<input type="hidden" value="<%=last_name%>" name="last_name"/>
                    </table>













</td></tr></table>
                        </html:form>
        </div>



    </body>




</html>
