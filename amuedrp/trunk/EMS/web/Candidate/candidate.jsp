<%-- 
    Document   : candidate
    Created on : 18 Jun, 2011, 4:30:55 PM
    Author     : akhtar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>ELECTION Mnagement System </title>
        <script language="javascript" type="text/javascript">


            function check2()
            {
                if(document.getElementById('Enrollment').value=="")
                {
                    alert("Enter Enrollment...");
                    document.getElementById('Enrollment').focus();
                    return false;
                }
                 if(document.getElementById('ins').options[document.getElementById('ins').selectedIndex].value=="Select")
    {
        alert("Select Institute Name");

        document.getElementById('ins').focus();

        return false;
    }
      
            }

             function send()
      {
          window.location="<%=request.getContextPath()%>/login.jsp";
          return false;
      }

            
        </script>
                <%String msg=(String)request.getAttribute("msg1"); %>
    </head>
    <body>
        <html:form method="post" onsubmit="return check2()" action="/registration">
            <div
                style="  top:100px;
                left:5px;
                right:5px;
                position: absolute;

                visibility: show;">
                <table border="1" class="table" width="600px" height="300px" align="center">
                    <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Candidate Registration </td></tr>
                    <tr><td valign="top" align="center"> <br><br>
                            <table cellspacing="10px">
                                
<tr><td align="left">Enter Enrollment No</td><td>  <html:text property="enrollment" styleId="Enrollment" name="CandidateRegActionForm"/></td></tr>
                                    
 
                              
<br><tr><td align="left">Institute Name</td><td> <html:select property="institute_id" styleId="ins"  name="CandidateRegActionForm"  tabindex="10">

            <html:option  value="Select"> Select </html:option>
                                <html:option  value="amu">Aligarh muslim university</html:option>
                                <html:option value="jmi">Jamia Millia islamia</html:option>
                               <html:option value="du">Delhi University</html:option>
                               <html:option value="jnu">JNU</html:option>
</html:select>
    </td>
      </tr>
     
      <tr><td align="center"> <input type="submit" class="btn" id="Button1" name="button" value="Add" /><input type="button" class="btn" id="Button2" name="button" value="Cancel" onclick="return send();" /></td></tr>

                                <%-- <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Update"  /></td></tr>--%>
                                <%-- <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Delete"  /></td></tr>--%>
                                <!--  <tr><td width="150px" align="center"><input type="submit" id="Button3" name="button" value="View" class="btn"  /></td></tr>-->
                                     <tr><td><%if(msg!=null){%><%=msg %><%}%></td></tr>
                            </table>
                        </td></tr>

                </table>
            </div>
</html:form>
        </body>
    </html>
