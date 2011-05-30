<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="adminheader.jsp" flush="true" />
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
  <!--int res=MyQueryResult.getMyExecuteUpdate("update login set first_login='t' where user_id='"+user_id+"' and password='"+password+"'");
    //                  if(res!=0)
                      {-->


<%
String staff_id=(String)session.getAttribute("staff_id");
String user_name=(String)session.getAttribute("username");
String library_id=(String)session.getAttribute("library_id");
String lib_name=(String)session.getAttribute("library_name");
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
        <link rel="stylesheet" href="css/page.css"/>
        <title>EMS</title>
    </head>
    <body >
    

  <html:form  action="/forget1" method="post" onsubmit="return check1()">
      <table width="400px" height="600px"  valign="top" align="center" class="txt1">
        <tr><td   width="400px" height="500px" valign="top" style="" align="center">
                <fieldset style="border:solid 1px brown;height:300px;width:300px;padding-left: 10px">
                    <legend><img src="images/SecurityQuestion.png"/></legend><br>
                    <table width="400px" align="center">
                        <tr><td  class="txt1" width="250px">Staff ID</td><td width="250px"><input type="text" id="staff_id" style="width:200px" name="staff_id" readonly  name="Editbox1" value="<%=staff_id%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt1" >User Name</td><td><input type="text" id="user_name" name="user_name" style="width:200px"  readonly  value="<%=user_name%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt1" >Security Question</td><td>
                                <select name="question" size="1" id="question" tabindex="3" style="width:200px" class="txt1">
                                <option selected value="Select" >Select</option>
                                <option  value="What is your pet name?">What is your pet name?</option>
                                <option value="What is your school name?">What is your school name?</option>
                                </select>


                            </td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt1" valign="top" >Answer<br></td><td><textarea rows="3" cols="200" id="ans" name="ans"    style="width:200px"></textarea><br><p class="txt2">(In Max 1000 character)</p></td></tr>

                        <tr><td colspan="2" align="center">
                                
                                <br>
                         <input type="submit" id="Button1"  value="Submit" class="txt2" onclick="return dupli()">
                         <input type="reset" id="Button2" value="Reset" onclick=" " class="txt2">
                         <input type="button" id="Button3"  value="Cancel" onclick=" return quit()" class="txt2">
                         
                            </td></tr>
<tr><td colspan="2" height="10px"></td></tr>

                    </table>





</fieldset>











</td></tr></table>
                        </html:form>
        </div>
   


    </body>
     

   <script language="javascript" type="text/javascript">
function check1()
{
   var ans=document.getElementById('ans');
   var ques=document.getElementById('question').options[document.getElementById('question').selectedIndex].text;

        if(ques=="Select")
            {
                alert("Select Any Security question");
                return false;
            }
     if(ans.value=="")
    {
        alert("Enter ans..");

        document.getElementById('ans').focus();

        return false;
    }



  }
    function quit()
    {

       window.location="login.jsp";
       return false;

    }

    </script>


</html>
