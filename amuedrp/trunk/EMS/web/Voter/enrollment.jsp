<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Staff ID
-->

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
        <%@page import="java.util.*,java.io.*,java.net.*"%>
     <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
    String regid="";
%>  <%
try{
locale1=(String)session.getAttribute("locale");

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
    regid = resource.getString("registrationid");
    System.out.println("Reg id="+regid);
    %>
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
            function quit()
            {

                 window.location="<%=request.getContextPath()%>/login.jsp";
                return false;
            }

  function send()
{
    window.location="<%=request.getContextPath()%>/login.jsp";
    return false;
}


        </script>
                <%String msg=(String)request.getAttribute("msg1"); %>
    </head>
    <body  style=" background-image: url('/EMS/images/paperbg.gif'); margin-top:0; margin-bottom:0;">




        <table align="center" style="padding: 0px 0px 0px 0px;width: 80%;height:100%;border-right:  solid #ECF1EF 10px;border-left:  solid #ECF1EF 10px;" dir="<%=rtl%>" >



            <tr style="background-image: url('/EMS/images/header.jpg');height: 100px">
    <td  valign="top" colspan="2" width="100%" align="center">
        <table  align="center" width="100%"  dir="<%=rtl%>">
            <tr><td width="70%"  valign="bottom"  align="<%=align%>">
                            &nbsp;&nbsp;    <span style="font-weight: bold;color:white;font-size: 35px;font-family:Gill, Helvetica, sans-serif;" >Election</span><span style="color:white;font-weight: bold;font-size: 35px;font-family:Gill, Helvetica, sans-serif;" >MS</span>



                </td><td align="center" > <img src="<%=request.getContextPath()%>/images/logo.png" alt="No Image"  border="0" align="center" id="Image1" style="" height="100px" width="200"><br/>

                            </td></tr>
             <tr><td>
                    <div style="background-color: white;color:blue;font-size: 14px;border:double 1px black;font-family:Gill, Helvetica, sans-serif" >
&nbsp;<%=resource.getString("login.message.logo.under")%>&nbsp;

</div>
                </td>
                <td >
                    <div style="background-color: white;color:blue;font-size: 14px;border:double 1px black;font-family:Gill, Helvetica, sans-serif" >


</div>
                </td></tr>
            </table></td>
            </tr>
            <tr><td>
        <html:form method="post" onsubmit="return check2()" action="/newregistration">
           
                <table  class="table" width="600px" height="300px" align="center">
                    <tr><td align="center" class="headerStyle1" bgcolor="#E0E8F5" height="25px;">Voter Registration </td></tr>
                    <tr><td valign="top" align="center"> <br><br>
                            <table cellspacing="10px">

<tr><td align="left">Enter Enrollment No</td><td>  <html:text property="enrollment" styleId="Enrollment" name="VoterRegActionForm"/></td></tr>



<br><tr><td align="left">Institute Name</td><td> <html:select property="institute_id" styleId="ins"  name="VoterRegActionForm"  tabindex="10">

            <html:option  value="Select"> Select </html:option>
            <html:options collection="Institute"  labelProperty="instituteName" property="instituteId"  name="Institute" ></html:options>
                                <%--<html:option  value="amu">Aligarh muslim university</html:option>
                                <html:option value="jmi">Jamia Millia islamia</html:option>
                               <html:option value="du">Delhi University</html:option>
                               <html:option value="jnu">JNU</html:option>--%>
</html:select>
    </td>
      </tr>

      <tr><td align="center"> <input type="submit" class="btn" id="Button1" name="button" value="Add" /><input type="button" class="btn" id="Button11" name="button" value="Cancel" onclick="return send();"/></td>
          </tr>
                                <%-- <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Update"  /></td></tr>--%>
                                <%-- <tr><td width="150px" align="center"><input type="submit" id="Button2" class="btn" name="button" value="Delete"  /></td></tr>--%>
                                <!--  <tr><td width="150px" align="center"><input type="submit" id="Button3" name="button" value="View" class="btn"  /></td></tr>-->
                                     <tr><td><%if(msg!=null){%><%=msg %><%}%></td></tr>
                            </table>
                        </td></tr>

                </table>
          
</html:form>
                </td></tr>
        <tr><td colspan="2" align="center"  style="font-family: arial;color:white;font-size: 12px;background-color: #425C83;height: 25px" valign="middle">
         <%=resource.getString("developedby")%> &nbsp;
                    &copy; <%=resource.getString("login.message.footer")%>
                </td></tr>
        </table>
        </body>
    </html>
