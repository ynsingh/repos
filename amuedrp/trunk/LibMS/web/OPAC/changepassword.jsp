<%@page import="java.sql.*,com.myapp.struts.hbm.*,com.myapp.struts.AdminDAO.*,java.util.*"%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>

 <%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<jsp:include page="opacheader.jsp"></jsp:include>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="";
%>
<%

try{
if(session.getAttribute("mem_id")!=null){
System.out.println("library_id"+session.getAttribute("mem_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/OPAC/member.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    }

try{
locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

<%
Login  rst=(Login)request.getAttribute("account_resultset");
String   name=(String)session.getAttribute("mem_name");

String mem_id;
mem_id=(String)session.getAttribute("mem_id");
String staff_id=null;


String user_name;
user_name=(String)session.getAttribute("mem_name");



if(rst!=null)
    {

    staff_id=rst.getId().getStaffId();
    user_name=rst.getUserName();

    mem_id=rst.getLoginId();


    }


%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/page.css"/>
        <title>LibMS : Change Password</title>
    </head>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/newformat.css"/>
    <body>
      <html:form  action="opac/changepassword2" method="post" onsubmit="return check();">
    


    <table  align="center" dir="<%=rtl%>" width="80%" class="datagrid" style="border: dashed 1px cyan;">



  <tr><td   dir="<%=rtl%>" style="font-family:Tahoma;font-size:12px" height="28px" align="<%=align%>">
          <table width="100%">
              <tr><td   dir="<%=rtl%>" colspan="2" style="font-family:Arial;font-size:12px;border-bottom: dashed 1px cyan" height="28px" align="center" ><b>Circulation Member : My Account Section OPAC</b></td></tr>
              <tr><td  dir="<%=rtl%>" style="font-family:Tahoma;font-size:12px;border-bottom: dashed 1px cyan" height="28px" align="<%=align%>"><b>


	&nbsp;&nbsp;
                <a href="<%=request.getContextPath()%>/OPAC/accountdetails.jsp"  style="text-decoration: none;"><%=resource.getString("opac.accountdetails.home")%></a>&nbsp;|&nbsp;
            <a href="<%=request.getContextPath()%>/OPAC/OpacLib.do?name=newdemand"  style="text-decoration: none;"> <%=resource.getString("opac.accountdetails.newdemand")%></a>&nbsp;
    |&nbsp;<a href="#"  style="text-decoration: none;"> <%=resource.getString("opac.accountdetails.reservationrequest")%></a>
  &nbsp;
    |&nbsp;<a href="<%=request.getContextPath()%>/OPAC/OpacLib.do?name=changepassword"  style="text-decoration: none;"> Change Password</a>
|&nbsp;<a href="<%=request.getContextPath()%>/OPAC/uploadExcel.do"  style="text-decoration: none;"> Bulk Import</a>



          </b>
                  </td><td align="right" dir="<%=rtl%>" style="font-family:Tahoma;font-size:12px;border-bottom: dashed 1px cyan;"><%=resource.getString("opac.accountdetails.hi")%>&nbsp;<%=name%>&nbsp;<b>|</b>&nbsp;<a href="home.do"  style="text-decoration: none;"><%=resource.getString("opac.accountdetails.logout")%></a></td></tr>
          </table>
        </td></tr>
  


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Change Password</td></tr>
                <tr><td valign="top" align="center"> <br/>
                    <table width="400px" align="center">

                        <tr><td class="txtStyle"></td><td></td></tr>
                        <tr><td class="txtStyle">User Name</td><td><input type="text" id="user_name" name="user_name"  readonly   value="<%=user_name%>"></td></tr>

                        <tr><td class="txtStyle" >Login ID</td><td><input type="text" id="mem_id" name="mem_id" readonly     value="<%=mem_id%>"/>


                            </td></tr>

                        <tr><td class="txtStyle">Enter New Password</td><td>            <input type="password" id="password" onblur="check1();"  name="password"  value="">
                           <div align="left" id="searchResult" class="err" style="border:#000000; "></div>
                           </td></tr>

                       <tr><td class="txtStyle">Re-enter New Password</td><td>            <input type="password" id="password1"  name="password1"  value=""></td></tr>
                       <tr><td class="txtStyle" align="center" colspan="2">
                       <br/>
                        <input type="submit" id="button" name="button" value="Submit" class="txt2" />
                         <input type="button" id="button" name="" value="Skip" onclick=" return send1()" class="txt2"/>

                         </td></tr>
                    </table>

                           </td></tr>

                    </table>










        
<jsp:include page="opacfooter.jsp"></jsp:include>

</html:form>
    </body>

<script language="javascript" type="text/javascript">

    function check()
    {
       
        var x=document.getElementById('password');
        if(x.value=="")
            {
                alert("Password should not be blank");
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



    </script>

</html>
