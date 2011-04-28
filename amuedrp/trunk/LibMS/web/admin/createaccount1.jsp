
<%@page import="java.sql.*,com.myapp.struts.hbm.*,com.myapp.struts.AdminDAO.*,java.util.*"%>
 <jsp:include page="header.jsp" flush="true" />
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 
 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
StaffDetail  rst=(StaffDetail)session.getAttribute("account_resultset");

List<SubLibrary> sublibrary=(List<SubLibrary>)session.getAttribute("sublib");

String mainlib=(String)session.getAttribute("mainsublibrary");
String sublibrary_name="";


    

String sublibrary_id=(String)session.getAttribute("sublibrary_id");

String login_role=(String)session.getAttribute("login_role");




String staff_id=null;
String user_name=null;


if(rst!=null)
    {

    staff_id=rst.getId().getStaffId();
    user_name=rst.getFirstName()+" "+rst.getLastName();
    sublibrary_name=rst.getSublibraryId();
   
    }

System.out.println(sublibrary_name+mainlib);
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS : Create Account Page </title>
    </head>
       <script  type="text/javascript" language="javascript">

    function check()
    {
     var role1=document.getElementById('role').options[document.getElementById('role').selectedIndex].value;
    if(role1=="Select")
        {
            alert("Select Role");
            document.getElementById('role').focus();
            return false;
        }

         var x=document.getElementById('login_id');
        if(x.value=="")
            {
                alert("Login ID should not be blank");
                 document.getElementById('login_id').focus();
                return false;
            }

       
    
        var x=document.getElementById('password');
        if(x.value=="")
            {
                alert("Password should not be blank");
                document.getElementById('password').focus();
                return false;
            }
     if(document.getElementById('password1').value=="")
    {
        alert("Enter Reenter password...");

        document.getElementById('password1').focus();
        

        return false;
    }
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


    }
  
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
   

      function send1()
    {
        window.location="<%=request.getContextPath()%>/admin/account.jsp";
        return false;

    }
function check1()
{
    KeyValue=document.getElementById('login_id').value;
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

    </script>
    <body onload="return checkrole();">
      <html:form  action="/createaccount" method="post" onsubmit="return check();">
<div
   style="  top:120px;
   left:15px;
   right:5px;
      position: absolute;

      visibility: show;">

     <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Staff Account</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">

                          <% if(rst!=null)
                                 {
                                 %>
                     
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt2">SubLibrary Name</td><td>
                                
                                
                               
                    <%if(sublibrary_id.equalsIgnoreCase(mainlib)){%>
                 
                    <html:select  property="sublibrary_id" styleId="sublibrary_id"   value="<%=sublibrary_name%>" onchange="return checkrole();">
                        <html:option value="Select">Select</html:option>
                        <html:options name="SubLibrary" collection="sublib" property="id.sublibraryId" labelProperty="sublibName"></html:options>
                     </html:select>

                    <%}else{%>
                        <html:hidden property="sublibrary_id" styleId="sublibrary_id"  value="<%=sublibrary_name%>"/>
                        <html:select  property="sublibrary_id" tabindex="3" disabled="true"  value="<%=sublibrary_name%>">
                             <html:option value="Select">Select</html:option>
                    <html:options name="SubLibrary" collection="sublib" property="id.sublibraryId" labelProperty="sublibName"></html:options>
                     </html:select>

                    <%}%>

                   


                            </td></tr>

                        <tr><td class="txt2">Staff ID</td><td><input type="text" id="staff_id" name="staff_id"  readonly   value="<%=staff_id%>"></td></tr>
                         <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt2">User Name</td><td><input type="text" id="user_name" name="user_name" readonly   value="<%=user_name%>"></td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                        <tr><td class="txt2" >Login ID</td><td><input type="text" id="login_id" name="login_id"    value=""/> </td></tr>
                        <tr><td colspan="2" height="5px"></td></tr>
                           <tr><td class="txt2" width="250px" align="left">Select Role</td><td width="250px">
                                   <select id="role" size="1" name="role">
                                    
                                    <%
                                    if(login_role.equals("dept-admin"))
                                    {%>
                                  
                                    <option value="dept-staff">Departmental Staff</option>
                                  
                                    <%}%>

                                </select>
                                </td></tr>
                            <tr><td colspan="2" height="5px">
                       <tr><td class="txt2">Password</td><td><input type="password" id="password"  name="password"  onblur="return check1();"  value="">
                           <div align="left" id="searchResult" class="err" style="border:#000000; "></div>
                           </td></tr>
                        <tr><td colspan="2" height="5px">
                       
                       <tr><td class="txt2">Reenter Password</td><td><input type="password" id="password1"  name="password1" onblur="return search1();" value=""></td></tr>

                       <tr><td colspan="2" height="5px"></td></tr>
                       


                    </table>


                        <%}%>
                                <br>
                                <br>
                         
                            <input type="submit" id="Button1" name="button" value="Create Account" class="txt2" />

                         <input type="button" id="Button3" name="" value="Back" onclick=" return send1()" class="txt2"/>
                       

                           

</td></tr>

    </table>
        </div>
   
</html:form>
    </body>


 

</html>
