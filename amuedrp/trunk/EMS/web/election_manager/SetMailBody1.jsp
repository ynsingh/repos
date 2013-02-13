<%-- 
    Document   : SetMailBody
    Created on : Feb 5, 2012, 11:54:47 AM
    Author     : guest
--%>

<%@page contentType="text/html" import="java.util.*,java.io.*,com.myapp.struts.utility.*" pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
String role=(String)session.getAttribute("login_role");
String userid=(String)session.getAttribute("user_id");
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
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
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
 <%!
    String candimailbody,simpleMailBody,voteronetime,voterresetpasswithkey,voterchpass,voterresetpasswithkeyandlink,voterresetlogin,electionmanager;
 %>

<%
    candimailbody=UserLog.readProperty("mail.properties", userid+"candi")!=null?UserLog.readProperty("mail.properties", userid+"candi"):"";
   simpleMailBody=UserLog.readProperty("mail.properties", userid+"vm")!=null?UserLog.readProperty("mail.properties", userid+"vm"):"";
   voteronetime=UserLog.readProperty("mail.properties", userid+"voteronetime")!=null?UserLog.readProperty("mail.properties", userid+"voteronetime"):"";
   voterresetpasswithkey=UserLog.readProperty("mail.properties", userid+"voterresetpasswithkey")!=null?UserLog.readProperty("mail.properties", userid+"voterresetpasswithkey"):"";
   voterchpass=UserLog.readProperty("mail.properties", userid+"voterchpass")!=null?UserLog.readProperty("mail.properties", userid+"voterchpass"):"";
   voterresetpasswithkeyandlink=UserLog.readProperty("mail.properties", userid+"voterresetpasswithkeyandlink")!=null?UserLog.readProperty("mail.properties", userid+"voterresetpasswithkeyandlink"):"";
   voterresetlogin=UserLog.readProperty("mail.properties", userid+"voterresetlogin")!=null?UserLog.readProperty("mail.properties", userid+"voterresetlogin"):"";
 %>

    <body>
        
        <form action="/EMS/election_manager/mail.do" name="MailBodyFormBean" >
            <table  cellspacing="10px" align="center" >
                <tr><td><u>Election manager Mail body</u></td>   </tr>
                        
                        <%
//String role=(String)session.getAttribute("login_role");
if(role.equalsIgnoreCase("insti-admin")|| role.equalsIgnoreCase("insti-admin,voter"))
   {
     electionmanager=UserLog.readProperty("mail.properties", userid+"em")!=null?UserLog.readProperty("mail.properties", userid+"em"):"";

%>
<td>Type Mail body</td><td><textarea cols="50" rows="5" name="electionmail" id="e" ><%=electionmanager%></textarea><br>
 <input type="Submit" onclick="return submit1();" id="button" name="button" value="Submit" />
    <%}else{%>
    <table  align="center" class="datagrid">
        <tr><td class="header" colspan="2" align="center">Mail Body Setting</td></tr>

                    <tr>
        <tr><td width="50%">
                <fieldset><legend>Voter Mail Body Section</legend>
                   <table>
       <tr> <td>Simple mail body</td>
            <td><textarea  cols="50" rows="5"  name="voterid" id="voterid" ><%=simpleMailBody%></textarea></td></tr>
       <tr><td>One time key mail body</td><td><textarea  cols="50" rows="5"  name="onetime" id="onetime" ><%=voteronetime%></textarea></td></tr>
       <tr><td>Reset Password with one time key mail body</td><td><textarea  cols="50" rows="5"  name="resetpassonetime" id="resetpassonetime" ><%=voterresetpasswithkey%></textarea></td></tr>
       <tr><td>Reset Password with one time key with direct link mail body</td><td><textarea  cols="50" rows="5"  name="resetpassonetimewithlink" id="resetpassonetimewithlink" ><%=voterresetpasswithkeyandlink%></textarea></td></tr>
        <tr><td>Changed Password Mail body</td><td><textarea  cols="50" rows="5"  name="changepass" id="changepass" ><%=voterchpass%></textarea></td></tr>
         <tr><td>Reset Login Password Mail  body</td><td><textarea  cols="50" rows="5"  name="resetlogin" id="resetlogin" ><%=voterresetlogin%></textarea></td></tr>
    </table>

                </fieldset>
    
            </td><td valign="top">
<fieldset><legend>Candidate Mail Body Section</legend>
                <textarea  cols="50" rows="5" name="candidateid" id="candidateid"><%=candimailbody%></textarea>
</fieldset>
            </td></tr>
        <tr>     <td colspan="2" align="center"> <input type="Submit" onclick="return submit();" id="button" name="button" value="Submit" />
                       </td>
                    </tr>

    </table>
     <%}%>
    </tr>
    
 

   
                   
                </table>

 



   
        </form>

    

    </body>
</html>
