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
<title>LibMS : Institute Registration Page</title>

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
 
      
 <table align="center" height="100%" style="width: 100%;background-color: white;border: solid #ECF1EF 0px;" dir="<%=rtl%>" >
     <tr><td align="right"><img src="<%=request.getContextPath()%>/images/bp.PNG" alt="banner space"  border="0"  dir="<%=rtl%>" id="Image1" style="height:40px;width:150px;"></td></tr>
     <tr><td style="height: 60px;" class="admintxtStyle" valign="bottom"><img src="<%=request.getContextPath()%>/images/help.jpeg" height="60px" width="100px">
             Contact Us
         </td>           </tr>
     
               
                <tr><td valign="top" align="left" style="border-bottom: dashed 1px cyan" >
                        <p style="margin-left: 100px;">
                    Dr. M. U. Bokhari<br>
                    Principal Investigator<br>
                    EdRP Project,NMEICT, Dept of Computer Science<br>
                    Aligrah Muslium University<br>
                    Aligrah,PIN-202002 UP<br>
                    For General Inquiries<br>
                    Tel :+91 941 264 0294<br>
                    e-mail: amuedrp@gmail.com,mubokhari@gmail.com
                        </p>
                    </td></tr>
            
        </table>
           
          
 


 </body>


 <jsp:include page="OPAC/opacfooter.jsp"/>


  
</html>
