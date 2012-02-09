<%-- 
    Document   : SetMailBody
    Created on : Feb 5, 2012, 11:54:47 AM
    Author     : guest
--%>

<%@page contentType="text/html" import="java.util.*,java.io.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String role=(String)session.getAttribute("login_role");

//out.println(role);

if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
%>
<jsp:include page="/institute_admin/adminheader.jsp"/>
<%}else{%>
<jsp:include page="/election_manager/login.jsp"/>
<%}%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;  charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <script type="text/javascript">
function submit1(){
   // alert("ok");
     
        var s=document.getElementById("e").value;
        alert(s);
        if(s=="")
           { alert("Please Enter EM Mail Body");return false;}
}
function submit1(){
   // alert("ok");

      var s=document.getElementById("voterid").value;
        if(s=="")
         {   alert("Please Enter Voter Mail Body");return false;}
        var s=document.getElementById("candidateid").value;
        if(s=="")
           { alert("Please Enter  Candidate Mail Body");return false;}



}
</script>
    <body>
        <form action="/EMS/election_manager/mail.do" name="MailBodyFormBean" >
                <table  cellspacing="10px">

                    <tr>
                        
                        <%
//String role=(String)session.getAttribute("login_role");
if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
%>
<td>Election manager mailbody</td><td><textarea cols="50" rows="5" name="electionmail" id="e" ></textarea><br>
 <input type="Submit" onclick="return submit1();" id="button" name="button" value="Submit" />
    <%}else{%>
<br><td>Voter Mail Body</td><td><textarea  cols="50" rows="5"  name="voterid" id="voterid" ></textarea><br>
 <td>Candidate Mail Body</td><td><textarea  cols="50" rows="5" name="candidateid" id="candidateid"></textarea>
 <input type="Submit" onclick="return submit();" id="button" name="button" value="Submit" />
     <%}%>


                        </td>
                    </tr>

                   
                </table>

 



   
        </form>

    

    </body>
</html>
