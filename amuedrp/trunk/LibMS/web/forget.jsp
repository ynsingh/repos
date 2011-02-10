<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <jsp:include page="header.jsp" flush="true" />
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
  <!--int res=MyQueryResult.getMyExecuteUpdate("update login set first_login='t' where user_id='"+user_id+"' and password='"+password+"'");
    //                  if(res!=0)
                      {-->

  <%
  String user_id=(String)session.getAttribute("user_id");
  String username=(String)session.getAttribute("username");
  String question=(String)request.getAttribute("question");
  String staff_id= (String)request.getAttribute("staff_id");
  String ans=(String)request.getAttribute("ans");
  String password=(String)request.getAttribute("password");
  String errors=(String)request.getAttribute("error");
  
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
        <title>LibMS</title>
    </head>
    <body >

  <html:form  action="/forget" method="post" onsubmit="return check1()">
      <input type="hidden" name="user_id" id="user_id" value="<%=user_id%>"/>
             
      <table width="600px" height="600px"  valign="top" align="center" >
        <tr><td   width="600px" height="500px" valign="top" style="" align="center">
                <fieldset style="border:solid 1px brown;height:350px;width:200px;padding-left: 5px">
                    <legend><img src="/LibMS-Struts/images/ForgetPassword.png"/></legend><br><br>
                    <table width="500px" align="center">
                        <tr><td class="txt2" width="150px">Staff ID</td><td width="200px"><input style="width:170px;" type="text" id="staff_id" name="staff_id" readonly  name="Editbox1" value="<%=staff_id%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt2" >User Name</td><td><input type="text" style="width:170px;" id="user_name" name="user_name"   readonly  value="<%=username%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt2" width="150px">Your Selected Security Question Is:-</td><td>
                                <input type="hidden" name="question" id="question" value="<%=question%>" style="width:170px;"/>
                                <select name="quest" size="1" id="quest" tabindex="3"  disabled style="width:170px;">
                                <option selected value="Select">Select</option>
                                <option  value="What is your pet name?">What is your pet name?</option>
                                <option value="What is your school name?">What is your school name?</option>
                                </select>


                            </td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt2" width="150px">Answer</td><td><input style="width:170px;" type="text" id="ans"  name="ans"  <% if(ans==null){%> value="" <%}else{%> value="<%=ans%>"<%}%>></td></tr>
                        <input type="hidden" style="width:170px;" id="password"  name="password"  <% if(password==null){%> value="" <%}else{%> value="<%=password%>"<%}%>>
                            <tr><td colspan="2" height="5px"></td></tr>
                        <% if(errors!=null){%>    <tr><td class="err" width="150px"></td><td class="err" ><%=errors%></td></tr> <%}%>







                        <tr><td colspan="2" align="center">
                                <br>
                                <br>
                            
                       <%if(password==null)
                    {    %> <input type="submit" id="submit"  value="Submit" class="btn" />
                    <input type="reset"  value="Reset"  class="btn" onclick="return search();">
                        <input type="button" class="btn" onclick="quit()" value="Back"/>
                     <%}
                     else
                        {%>
                        
                        <span class="txt2">Password sent to your Mail Account</span>
                        <br><br>
                       <input type="button" class="btn" onclick="quit()" value="Back"/>

     <%                   }%>
                        
                       
                        
                        <script>
                            function quit()
                            {
                                window.location="/libMS-Struts/login.jsp";
                            }
                            </script>
                            </td></tr>
                       






                    </table>





</fieldset>











</td></tr></table>
                        </html:form>
        </div>
   


    </body>
</html>


   <script language="javascript" type="text/javascript">
document.getElementById('quest').options[document.getElementById('quest').selectedIndex].text='<%=question%>';

function check1()
{
   var ans=document.getElementById('ans');
   

     if(ans.value=="")
    {
        alert("Enter ans..");

        document.getElementById('ans').focus();

        return false;
    }
          


  }
  function cl()
{
   alert("e") ;
  
  ans.value="";
  ans.focus();
return true;


  }
    function quit()
    {

       window.location="/LibMS-Struts/login.jsp";
       return false;

    }
    
    </script>

