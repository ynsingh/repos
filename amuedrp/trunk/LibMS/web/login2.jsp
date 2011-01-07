<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%
String msg1=(String)request.getAttribute("registration_msg");
String msg2=(String)request.getAttribute("msg");
if(msg2!=null || msg1!=null)
out.println(msg1);

%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Untitled Page</title>



</head>
<body style="margin:0px 0px 0px 0px" onload="return message()">

    



    <html:form method="post" action="/login" >
        <table align="center" style="border:solid 1px white;height:600px">
            <tr><td> <select name="lang" size="1" id="lang" onchange="callme()">
                                    <option  value="urdu">Urdu</option>
                                    <option selected value="eng">English</option>
                                    <option value="hindi">Hindi</option>

                                    </select>
</td></tr>
            <tr><td colspan="2" style="" valign="top" align="right"><img src="images/logo.png" alt=""  border="0" align="top" id="Image1" style=""></td></tr>
            <tr><td width="800px" valign="top" align="left" >
                    
                            <img src="images/lib.PNG" alt="banner space"  border="0" align="top" id="Image1" style="height:50px;width:200px;">
                            <br>
                            <span style="font-family: Tahoma">A MultiLingual  Approach of Library Management</span><br>
                            <br>
                            <span style="font-family: Arial;font-size:13px;"><b><br>
                            LibMs is Built on the Idea that Library Management can be more intutive,efficient and meaningfull,After All,LibMS has
                             <br><br><br><br>
                    <table cellpadding="5px">

                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="left">Acquition</td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="left">Catalog</td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="left">Serial</td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td><td align="left">OPAC</td></tr>
                        <tr><td height="10px"></td><td></td></tr>


                      </table>
                       

                        </b>
                        </span>
                   
                </td><td align="center">
                    <br><br>
                    <table  cellpadding="0" style="border:solid 1px #BFDBFF;color:#006BF5;font-family:Tahoma;font-size:13px;font-weight: bold;height:300px;width:300px">
                    <tr><td colspan="2" height="17px" style="color:#006BF5;" align="center"><br><br>Sign in with your<br><br>

                         <img src="images/lib1.PNG" style="height:50px;width:250px;" alt="" style=""  border="0" align="top" id="Image1" style="">

                        </td>
                    </tr>
                    <tr>
                    <td  align="center">
                        <table>
                             <tr> <td>  User Name:</td>
                    <td align="left"><input name="username" type="text" id="username" value="" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;"></td>
                    </tr>
                           <tr>
                    <td  height="20px">Password:</td>
                    <td align="left"><input name="password" type="password" id="password" value="" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;"></td>
                    </tr>
                    <tr>
                    <td>&nbsp;</td><td height="20px" align="left"><input id="rememberme" type="checkbox" name="rememberme">Remember me
                        <br><br>
                    
                    
                    </td>
                    </tr>
                    <tr>
                    <td>&nbsp;</td><td align="left" valign="bottom">
                        <br>
                        <input type="submit" name="button" value="Log In" /><input type="submit" name="button" value="Forget Password"/>
                    </td>
                    </tr>
                        </table>

                    </td></tr>

                    

                    </table>
                    <br>
                    <br>
                    <span style="font-family: Tahoma;font-size:14px"><b> New to LibMS? Its Free & Easy</b></span><br><br>
                   &nbsp;&nbsp;&nbsp;&nbsp; <img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style="">&nbsp;&nbsp;<b><a href="admin/admin_registration.jsp" style="text-decoration: none;color:brown;font-family: Tahoma;font-size:13px;font-weight: bold;">Institute Registration</a></b>
                    <br><br>
                    
                    <br>



                </td></tr>
            <tr><td colspan="2" align="center" height="50px" valign="bottom">

                    <b><span style="font-family: Tahoma;font-size:14px">&copy; 2010 , ERP Mission Project (AMU Aligarh) INDIA</span></b>
                </td></tr>

        </table>
</html:form>

</body>

</html>
<script type="text/javascript" language="javascript">
function callme()
{
    
     var ln=document.getElementById('lang').options[document.getElementById('lang').selectedIndex].text;
    
 if(ln=="Urdu")
     location.href="login_multi.jsp";
if(ln=="English")
     location.href="login.jsp";

 
}
</script>
