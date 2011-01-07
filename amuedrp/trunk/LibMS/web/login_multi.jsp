<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Untitled Page</title>



</head>
 <html:form method="post" action="/login" >
        <table align="center" style="border:solid 1px white;height:700px">
            <tr><td align="right"> <select name="lang" size="1" id="lang" onchange="callme()">
                                    <option  selected value="urdu">Urdu</option>
                                    <option  value="eng">English</option>
                                    <option value="hindi">Hindi</option>

                                    </select>
</td></tr>
  <tr>
    <td height="116"><img src="images/logo.png" width="252" height="112"></td>
  </tr>
  <tr>
    <td height="429"><table  >
      <tr>
          <td height="216" align="right">
		  <table width="984"  cellpadding="0" style="border:solid 1px #BFDBFF;color:#006BF5;font-family:Tahoma;font-size:13px;font-weight: bold;height:300px;width:400px">
                    <tr><td width="978" height="17px" colspan="2" align="center" style="color:#006BF5;"><br><br>Sign in with your<br><br>

                         <img src="images/lib1.PNG" style="height:50px;width:250px;" alt="" style=""  border="0" align="top" id="Image1" style="">

                        </td>
                    </tr>
                    <tr>
                    <td  align="center">
                        <table>
                             <tr><td align="right"><input name="username" type="text" id="username" value="" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;"></td> <td>  :User Name</td>
                    
                    </tr>
                           <tr>
                    <td align="right"><input name="password" type="password" id="password" value="" style="width:160px;height:18px;background-color:#FFFFFF;border-color:#BFDBFF;border-width:1px;border-style:solid;color:#006BF5;font-family:Verdana;font-size:11px;"></td><td  height="20px">:Password</td>
                    
                    </tr>
                    <tr>
                    <td height="20px" align="right">Remember me<input id="rememberme" type="checkbox" name="rememberme">
                        <br><br>
                    
                    
                    </td><td>&nbsp;</td>
                    </tr>
                    <tr>
                   <td align="right" valign="bottom">
                        <br>
                        <input type="submit" name="button" value="Forget Password"/><input type="submit" name="button" value="Log In" />
                    </td> <td>&nbsp;</td>
                    </tr>
                        </table>

                        <blockquote>&nbsp;</blockquote></td>
              </tr>
                    

                    

            </table>
		
		</td>
        <td align="right">
		 <img src="images/lib.PNG" alt="banner space"  border="0" align="top" id="Image1" style="height:50px;width:200px;">
                    <br><br>
                    <span style="font-family: Tahoma">A MultiLingual  Approach of Library Management</span><br>
                    <br>
                    <span style="font-family: Arial;font-size:13px;"><b>
                    LibMs is Built on the Idea that Library Management can be more intutive,efficient and meaningfull,After All,LibMS has 
                    <br>
                    <table cellpadding="5px">
                        
                        <tr><td align="right">Acquition</td><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td align="right">Cataloging</td><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td align="right">Serial</td><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        <tr><td align="right">OPAC</td><td><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style=""></td></tr>
                        <tr><td height="10px"></td><td></td></tr>
                        
                       
                      </table>
                        </b>
                </span>

		
		</td>
      </tr>
      <tr>
                      <td  align="right">
					   <span style="font-family: Tahoma;font-size:14px"><b> New to LibMS? Its Free & Easy</b></span><br><br>
                   &nbsp;&nbsp;&nbsp;&nbsp; <b><a href="./admin_registration.jsp" style="text-decoration: none;color:brown;font-family: Tahoma;font-size:13px;font-weight: bold;">Institute Registration</a><img src="images/B.jpg" alt="" style="height:30px;width:40px;"  border="0" align="top" id="Image1" style="">&nbsp;&nbsp;</b>

					  </td>
                    </tr>
    </table></td>
  </tr>
  <tr>
    <td align="center">
	        <b><span style="font-family: Tahoma;font-size:14px">&copy; 2010 , ERP Mission Project (AMU Aligarh) INDIA</span></b>
            
	</td>
  </tr>
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
