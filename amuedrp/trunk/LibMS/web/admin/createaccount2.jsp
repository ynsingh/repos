 <%@page import="java.sql.*,com.myapp.struts.hbm.*,com.myapp.struts.AdminDAO.*,java.util.*"%>
<jsp:include page="header.jsp" flush="true" />

<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
Login  rst=(Login)session.getAttribute("update_account");

//List<SubLibrary> sublibrary=(List<SubLibrary>)session.getAttribute("sublib");


String user_sublibrary_id=(String)session.getAttribute("sublibrary_id");

String button=(String)session.getAttribute("button");

String login_role=(String)session.getAttribute("login_role");
String mainlib=(String)session.getAttribute("mainsublibrary");



String staff_id=null;
String user_name=null;
String login_role1=null;
String password=null;
String question=null;
String login_id=null;
String ans=null;

String sublibrary_id=null;

if(rst!=null)
    {

    staff_id=rst.getId().getStaffId();
    user_name=rst.getUserName();
    login_id=rst.getLoginId();
    login_role1=rst.getRole();
    question=rst.getQuestion();
    password=rst.getPassword();
    ans=rst.getAns();
    sublibrary_id=rst.getSublibraryId();
    }



%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS : Manage Staff Account Updation </title>
           <script language="javascript" type="text/javascript">
              function checkrole()
    {

var sublibrary_id=document.getElementById('sublibrary_id').options[document.getElementById('sublibrary_id').selectedIndex].text;

document.getElementById('role').options.length = 0;
var adminText="Admin";
var adminValue="admin";
var deptadminText="Departmental Admin";
var deptadminValue="dept-admin";
var staffText="Staff";
var staffValue="staff";
var deptstaffText="Deptartmental Staff";
var deptstaffValue="dept-staff";

var instiText="Institute Admin";
var instiValue="insti-admin";
var role="<%=login_role%>";


   if(sublibrary_id=="Select")
        {

            newOpt = document.getElementById('role').appendChild(document.createElement('option'));
            newOpt.value = "Select";
            newOpt.text = "Select";
        }
    else if(sublibrary_id=="Main Library" && role=="insti-admin")
        {

            newOpt = document.getElementById('role').appendChild(document.createElement('option'));
            newOpt.value = "Select";
            newOpt.text = "Select";

             newOpt = document.getElementById('role').appendChild(document.createElement('option'));
            newOpt.value = staffValue;
            newOpt.text = staffText;

            newOpt = document.getElementById('role').appendChild(document.createElement('option'));
            newOpt.value = adminValue;
            newOpt.text = adminText;








        }
        else if(sublibrary_id=="Main Library" && role=="admin")
        {
            newOpt = document.getElementById('role').appendChild(document.createElement('option'));
            newOpt.value = "Select";
            newOpt.text = "Select";

            newOpt = document.getElementById('role').appendChild(document.createElement('option'));
            newOpt.value = staffValue;
            newOpt.text = staffText;
        }
        else{
           if(sublibrary_id!="Main Library" && (role=="admin" || role=="insti-admin"))
               {
                   newOpt = document.getElementById('role').appendChild(document.createElement('option'));
            newOpt.value = "Select";
            newOpt.text = "Select";

            newOpt = document.getElementById('role').appendChild(document.createElement('option'));
            newOpt.value = deptstaffValue;
            newOpt.text = deptstaffText;

        newOpt = document.getElementById('role').appendChild(document.createElement('option'));
            newOpt.value = deptadminValue;
            newOpt.text = deptadminText;
               }
               else
                   {
                       newOpt = document.getElementById('role').appendChild(document.createElement('option'));
            newOpt.value = "Select";
            newOpt.text = "Select";

            newOpt = document.getElementById('role').appendChild(document.createElement('option'));
            newOpt.value = deptstaffValue;
            newOpt.text = deptstaffText;
                   }










        }




                return true;


    }

    function check()
    {
        var role=document.getElementById('role').options[document.getElementById('role').selectedIndex].value;
var sublibrary_id=document.getElementById('sublibrary_id').options[document.getElementById('sublibrary_id').selectedIndex].value;


    if(role=="Select")
        {
            alert("Select Role");
            document.getElementById('role').focus();
            return false;
        }
    if(sublibrary_id=="Select")
        {
            alert("Select Sublibrary");
            document.getElementById('sublibrary_id').focus();
            return false;
        }



                return true;


    }

      function send1()
    {
        window.location="<%=request.getContextPath()%>/admin/account.jsp";
        return false;

    }

    function confirm1()
{
   var answer = confirm ("Do you want to Delete Record?")
if (answer!=true)
    {
        document.getElementById('Button1').focus();
        return false;
    }
    else
        return true;


}
    </script>
    </head>
    <body onload="return checkrole();">
      <html:form  action="/updateaccount" method="post" onsubmit="return check();">
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

      <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Staff</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">

                          <tr>
                <td width="10%">      SubLibrary Name</td >
                <td width="15%">

                     <%
                        if(button.equals("Update Account"))
                        {%>

                        
                           <%if(user_sublibrary_id.equalsIgnoreCase(mainlib)){%>
                 
                    <html:select  property="sublibrary_id" styleId="sublibrary_id" tabindex="3"  value="<%=sublibrary_id%>" onchange="return checkrole();">
                   <html:options name="SubLibrary" collection="sublib" property="id.sublibraryId" labelProperty="sublibName"></html:options>
                     </html:select>

                    <%}else{%>
                        <html:hidden property="sublibrary_id" value="<%=sublibrary_id%>"/>
                        <html:select  property="sublibrary_id" tabindex="3" disabled="true"  value="<%=sublibrary_id%>">
                    <html:options name="SubLibrary" collection="sublib" property="id.sublibraryId" labelProperty="sublibName"></html:options>
                     </html:select>

                    <%}%>

                       

                        <%}else{%>

                        <input type="hidden" name="sublibrary_id" value="<%=sublibrary_id%>"/>
                        <html:select  property="sublibrary_id" tabindex="3"  value="<%=sublibrary_id%>"  disabled="true">
                    <html:options name="SubLibrary" collection="sublib" property="sublibName"></html:options>
                     </html:select>



                    <%}%>

                   
                </td>
            </tr>
                        <tr><td class="btn">Staff ID</td><td><input type="text" id="staff_id" name="staff_id"  readonly   value="<%=staff_id%>"></td></tr>
                         <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="btn">User Name</td><td><input type="text" id="user_name" name="user_name"  readonly   value="<%=user_name%>"></td></tr>
                        <tr><td colspan="2" height="5px" width="300px"></td></tr>
                        <tr><td class="btn" >Login ID</td><td><input type="text" id="login_id" name="login_id" readonly     value="<%=login_id%>"/> </td></tr>
                        <tr><td colspan="2" height="5px" align="center"></td></tr>
                        <tr><td class="btn" width="250px">Select Role</td><td width="250px">

                               
                                <%  if(button.equals("View Account")||button.equals("Delete Account"))
                                 {
                                %>
                                <input type="hidden" id="role" name="role" value="<%=login_role1%>"/>

                                <html:select styleId="role" size="1" property="role" disabled="true"  value="<%=login_role1%>" >
                                    <html:option value="<%=login_role1%>">  <%=login_role1%></html:option>

                                </html:select>

                                      <%  }else{%>
                                      <html:select styleId="role" size="1" property="role" value="<%=login_role1%>">
                                          <%if(login_role1.equalsIgnoreCase("dept-admin")){%>
                                          <html:option  value="dept-admin"> Departmental Admin</html:option>
                                           <html:option  value="dept-staff"> Departmental Staff</html:option>
                                           <%}else if(login_role1.equalsIgnoreCase("dept-staff")){%>
                                           <html:option  value="dept-staff"> Departmental Staff</html:option>

                                     <%}%>
                                      
                                </html:select>
                                 <%}%>
                         </td></tr>
                             <tr><td colspan="2" height="5px" align="center"></td></tr>
                         
                       
                    <tr><td class="btn">Password</td><td>            <input type="password" id="password"  name="password" readonly  value="<%=password%>"></td></tr>
                               <input type="hidden" id="password"  name="password"  value="<%=password%>">
 

                       
                            
  <tr><td colspan="2" height="5px" align="center"></td></tr>
                      

                            <tr><td colspan="2" height="5px" align="center">
                       
                       
                        <%
                        if(button.equals("View Account"))
                        {

                        %>
                        
                        <input type="button" id="Button3" name="" value="Back" onclick=" return send1()" class="txt2"/>
                      <%
                        }
                         else if(button.equals("Update Account"))
                            {
                        %>
                        <input type="submit" id="Button1" name="button" value="<%=button%>" class="txt2"/>
                         <input type="button" id="Button3" name="" value="Back" onclick=" return send1()" class="txt2"/>
                        <%
                         }
                        else if(button.equals("Delete Account"))
                        {%>
                         <input type="submit" id="Button1" name="button" value="Confirm" class="txt2" onclick="return confirm1()"/>

                         <input type="button" id="Button3" name="" value="Back" onclick=" return send1()" class="txt2"/>
                   
                       

                           <%}%>
                        <input type="hidden"  name="ans"    value="<%=ans%>">
                <input type="hidden" name="question"     value="<%=question%>">
                            </td></tr>


                    </table>
                    </td></tr></table>




        </div>
   
</html:form>
     


    </body>
</html>

