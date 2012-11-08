<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
This Page is to accept Institute Request and Send to SuperAdmin
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,org.apache.struts.upload.FormFile"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LibMS : Contact Us </title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css" type="text/css"/>
<script language="javascript" type="text/javascript">
    <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
%>
<%
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

    %>


var availableSelectList;


function check1()
{
    KeyValue=document.getElementById('login_id').value;
        KeyValue1=document.getElementById('password1').value;

        if(KeyValue==KeyValue1)
            {
               availableSelectList = document.getElementById("searchResult");

               availableSelectList.innerHTML="LoginId and Password Should not be same";
                document.getElementById('password1').value="";

            }
else{
 availableSelectList = document.getElementById("searchResult");

               availableSelectList.innerHTML="";

}

}



</script>


<script type="text/javascript" language="javascript">
function fun()
        {

            document.form1.action="<%=request.getContextPath()%>/admin/language.jsp";
            document.form1.submit();
        }
    function quit()
    {
        window.location="<%=request.getContextPath()%>/login.jsp";
    }

    function check()
    {
      var x1=document.getElementById('password1');
        var x2=document.getElementById('password2');
        if(x1.value!=x2.value)
            {
                alert("password mismatch");
                document.getElementById('password2').focus();
                return false;
            }
            else
                return true;
                return true;
    }
            </script>

 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
</head>
<body style="margin: 0px 0px 0px 0px" >
 
      <form method="post" name="form1">
 <table align="center" width="100%" height="100%" style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;border-collapse: collapse;  border-spacing: 0;" dir="<%=rtl%>" >
      <tr><td class="homepage" style="background-color: black;color:white;" align="right" colspan="2">



         <a style="color:white" href="<%=request.getContextPath()%>">Home</a>&nbsp;|&nbsp;     <a style="color:white" href="http://www.ignouonline.ac.in/sakshatproposal/default.aspx">NME-ICT ERP Mission</a>&nbsp;|&nbsp;<a  style="color:white" href="<%=request.getContextPath()%>/contactus.jsp">Contact Us</a>&nbsp;|&nbsp; <a style="color:white" href="<%=request.getContextPath()%>/admin/admin_registration.jsp"><%= resource.getString("login.href.institute.registration") %></a>
         &nbsp;|&nbsp;            <a style="color:white" href="relnotes.jsp">   Release Notes</a>&nbsp;|&nbsp;  <a style="color:white" href="instantUserManual_LibMS-2012.pdf">UserManual</a>&nbsp;|&nbsp; <a style="color:white" href="<%=request.getContextPath()%>/admin/view_instiapproved.jsp">View All Registered Institutes</a>&nbsp;|&nbsp;<%=resource.getString("login.message.selectlanguage")%><select name="locale" class="selecthome" onchange="fun()"><option dir="<%=rtl%>"<%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("en")){ %>selected<%}%>>English</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ur")){ %>selected<%}%>>Urdu</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("ar")){ %>selected<%}%>>Arabic</option><option dir="<%=rtl%>" <%if(session.getAttribute("locale")!=null && session.getAttribute("locale").equals("hi")){ %>selected<%}%>>Hindi</option></select>



                                         </td>

                                     </tr>
            <tr><td><table align="center"  width="100%"  style="background-color: white;"   dir="<%=rtl%>" >

   <tr ><td>
                                     <table width="100%" style="margin: 0px 0px 0px 0px;padding: 0px 0px 0px 0px;"><tr>
                                         <td align="left"  style="background-color: white;color:blue;height: 50px;  margin: 0px 0px 0px 0px;font-style: italic;font-size: 18px;valign:bottom" valign="bottom" align="center">
                                          
                                             &nbsp;&nbsp;<span style="font-style: italic;font-size: 18px;">LibMS....</span>        "<%=resource.getString("login.message.logo.under")%>"




                            </td>
                            <td align="right">
  <img src="<%=request.getContextPath()%>/images/logo.png" alt=""  border="0" align="top" id="Image1" style="height:70px;width:160px;">

                            </td>


                                         </tr></table>
                                          <hr color="cyan">
                                         </td>

           </tr>

     <tr><td style="height: 60px;font-size:25px;color:red;font-family: arial;margin-left: 100px; "  valign="bottom">
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  CONTACT US<hr color="cyan">
         </td>           </tr>
     
               
                <tr><td valign="top" align="left"  >
                        <p style="margin-left: 100px;line-height: 26px;font-family: arial">
                    Dr. M. U. Bokhari&nbsp;<i>(Principal Investigator)&nbsp;</i><br>
                    
                    EdRP Project, NMEICT, Dept of Computer Science<br>
                    Aligrah Muslium University<br>
                    Aligrah,PIN-202002 UP<br>
                    For General Inquiries<br>
                    Tel :+91 941 264 0294<br>
                    e-mail: amuedrp@gmail.com,mubokhari@gmail.com
                        </p>
                    </td></tr>
            
        </table>
           
          
 
                </td></tr></table>
</form>
 </body>


 <jsp:include page="OPAC/opacfooter.jsp"/>


  
</html>
