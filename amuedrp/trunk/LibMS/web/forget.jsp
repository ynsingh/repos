<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
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
  String question=(String)session.getAttribute("question");
  String staff_id= (String)session.getAttribute("staff_id");
  String ans=(String)session.getAttribute("ans");
  String password=(String)request.getAttribute("password");
  String errors=(String)request.getAttribute("error");
  
  %>
  <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";
    boolean page=true;
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");
sessionId = session.getId().toString();
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

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
             
      <table   valign="top" align="center" class="table">
        <tr><td  class="headerStyle"  valign="top" style="" align="center"><%=resource.getString("login.button.sigin.forgetpassword")%></td></tr>
        <tr><td>
                
                   
                    <table width="500px" align="center">
                        <tr><td class="txt2" width="150px"><%=resource.getString("staffid")%></td><td width="200px"><input style="width:170px;" type="text" id="staff_id" name="staff_id" readonly  name="Editbox1" value="<%=staff_id%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt2" ><%=resource.getString("admin.createaccount1.username")%></td><td><input type="text" style="width:170px;" id="user_name" name="user_name"   readonly  value="<%=username%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt2" width="150px"><%=resource.getString("admin.forgetpassword.note")%></td><td>
                                <input type="hidden" name="question" id="question" value="<%=question%>" style="width:170px;"/>
                                <select name="quest" size="1" id="quest" tabindex="3"  disabled style="width:170px;">
                                <option selected value="Select"><%=resource.getString("opac.newarrivals.selectperiod")%></option>
                                <option  value="What is your pet name?"><%=resource.getString("admin.forgetpassword.q1")%></option>
                                <option value="What is your school name?"><%=resource.getString("admin.forgetpassword.q2")%></option>
                                </select>


                            </td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt2" width="150px"><%=resource.getString("admin.forgetpassword.ans")%></td><td><input style="width:170px;" type="text" id="ans"  name="ans"  <% if(ans==null){%> value="" <%}else{%> value="<%=ans%>"<%}%>></td></tr>
                        <input type="hidden" style="width:170px;" id="password"  name="password"  <% if(password==null){%> value="" <%}else{%> value="<%=password%>"<%}%>>
                            <tr><td colspan="2" height="5px"></td></tr>
                        <% if(errors!=null){%>    <tr><td class="err" width="150px"></td><td class="err" ><%=errors%></td></tr> <%}%>







                        <tr><td colspan="2" align="center">
                                <br>
                                <br>
                            
                       <%if(password==null)
                    {    %> <input type="submit" id="submit"  value="<%=resource.getString("opac.newmemberentry.submit")%>" class="btn" />
                    <input type="reset"  value="<%=resource.getString("opac.newmemberentry.reset")%>"  class="btn" onclick="return search();">
                        <input type="button" class="btn" onclick="quit()" value="<%=resource.getString("login.viewpending.back")%>"/>
                     <%}
                     else
                        {%>
                        
                        <span class="txt2"><%=resource.getString("admin.forgetpassword.mail")%></span>
                        <br><br>
                       <input type="button" class="btn" onclick="quit()" value="Back"/>

     <%                   }%>
                        
                       
                        
                        <script>
                            function quit()
                            {
                                window.location="<%=request.getContextPath()%>/login.jsp";
                            }
                            </script>
                            </td></tr>
                       






                    </table>















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
        alert("<%=resource.getString("admin.forgetpassword.alert")%>");

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

       window.location="<%=request.getContextPath()%>/login.jsp";
       return false;

    }
    
    </script>

