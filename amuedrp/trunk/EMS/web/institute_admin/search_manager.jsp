<%-- 
    Document   : manage_superadmin
    Created on : Jan 12, 2011, 7:34:19 PM
    Author     : System Administrator
--%>
<%@page import="java.util.*,java.io.*,java.net.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<link rel="Stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<%
   String role=(String)session.getAttribute("login_role");
   if(role.equalsIgnoreCase("Election Manager") || role.equalsIgnoreCase("Election Manager,voter")){
   %>
   <%List el=(List)session.getAttribute("elist"); %>
   <jsp:include page="/election_manager/login.jsp"/>
   <%} else if(role.equalsIgnoreCase("insti-admin") || role.equalsIgnoreCase("insti-admin,voter")){%>
   <jsp:include page="/institute_admin/adminheader.jsp"/>
<%}%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <%
try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }
String contextPath = request.getContextPath();
String user=(String)session.getAttribute("user_id");
%>
<script type="text/javascript" language="javascript">
       function getReadyStateHandler(req, responseXmlHandler) {
// Return an anonymous function that listens to the XMLHttpRequest instance
return function () {
// If the request's status is "complete"
if (req.readyState == 4) {
// Check that we received a successful response from the server
if (req.status == 200) {
// Pass the XML payload of the response to the handler function.
responseXmlHandler(req.responseXML);
} else {
// An HTTP problem has occurred
alert("HTTP error "+req.status+": "+req.statusText);
}
}
}
}

function newXMLHttpRequest() {
var xmlreq = false;
// Create XMLHttpRequest object in non-Microsoft browsers
if (window.XMLHttpRequest) {
xmlreq = new XMLHttpRequest();
} else if (window.ActiveXObject) {
try {
// Try to create XMLHttpRequest in later versions
// of Internet Explorer
xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
} catch (e1) {
// Failed to create required ActiveXObject
try {
// Try version supported by older versions
// of Internet Explorer
xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
} catch (e2) {
// Unable to create an XMLHttpRequest by any means
xmlreq = false;
}
}
}
return xmlreq;
}
function searchElections() {
    
    var user_id = document.getElementById('user_id1').value;
    
    if(user_id=="")
        {
            alert("Enter Election Manager ID");
        }
if (user_id.length >= 1)
{

    var req = newXMLHttpRequest();


req.onreadystatechange = getReadyStateHandler(req, update1);

req.open("POST","<%=request.getContextPath()%>/searchelection.do?user_id="+user_id, true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send();
}
}
function update1(cartXML)
{

var depts = cartXML.getElementsByTagName("dept_ids")[0];
var em = depts.getElementsByTagName("dept_id");
var em1 = depts.getElementsByTagName("dept_name");

        var newOpt =document.getElementById('election').appendChild(document.createElement('option'));
        document.getElementById('election').options.length = 0;
        newOpt = document.getElementById('election').appendChild(document.createElement('option'));
newOpt.value = "Select";
newOpt.text = "Select";

//alert(em.length+".................");
for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('election').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}

}
function check()
{


var em = depts.getElementsByTagName("user_id1").value;
var em1 = depts.getElementsByTagName("election").value;

       if(em=="")
         {  alert("Please Election Manager ID");return false;}
       if(em1=="Select")
        {    alert("Please Select Election");return false;}
        return true;
}
        </script>
    </head>
    <body>
        <html:form styleId="form1"  action="/migrateelectionmanager" method="post">
            <table  align="<%=align%>" dir="<%=rtl%>" width="500px" height="150px" style="background-color: white;border:#C7C5B2 1px solid;margin:0px 0px 0px 0px;">
                <tr><td dir="<%=rtl%>" style="background-color: #7697BC;color:white;" colspan="2" class="btn1" height="30px"><b>Migrate Role </b> </td></tr>
                <tr><td width="50%" class="btn3" dir="<%=rtl%>" align="right">Election Manager user id</td><td><html:text styleId="user_id1" style="width: 200px" property="user_id1" onblur="searchElections();" value=""/></td></tr>
                <tr><td>Election List</td><td>
                <select name="election" id="election">
                    <option value="Select">Select</option>
                </select>

                    </td></tr>
                 <tr><td>Migrated to Election Manager</td><td>
               <html:text styleId="user_id2" style="width: 200px" property="user_id2"  value=""/>

                    </td></tr>
               
                <tr><td class="btn3" dir="<%=rtl%>" align="<%=align%>" colspan="2">
                <br>
   <%
         String   message="";
     message =(String) request.getAttribute("msg");
    if(message!=null) {%>
               <%=message%>
               
            
          
          
         
        
        
                
   <% }
     String clear="clear";
    %>

 <%
   message="";
     message =(String) request.getAttribute("msg1");
    if(message!=null) {%>
               <%=message%>








   <% }else
     clear="clear";
    %>



            </td></tr>
           <tr><td><html:submit styleClass="btn2" style="float: right" onclick="return check();"> Submit</html:submit></td><td dir="<%=rtl%>" width="300px">
                   <input type="button" onclick="return clearme();"   class="btn2" dir="<%=rtl%>" value="<%=resource.getString(clear)%>" />
            </td></tr>
        <tr><td></td><td  align="<%=align%>" width="200px">
                  
                <br><br></td></tr>
    </table>
        </html:form>
    </body>
    <script>
          function check()
    {


  var password=document.getElementById('user_id1');

  var password1=document.getElementById('user_id2');
var election=document.getElementById('election');
var str="";




       if(password.value=="")
       {  str+="\n PLease Enter User ID of EM ";
       alert(str);
            document.getElementById('user_id1').focus();
            return false;
       }
        if(election.value=="Select")
       {  str+="\n PLease Select Election ";
       alert(str);
            document.getElementById('election').focus();
            return false;
       }



    if(password1.value=="")
       {  str+="\n PLease Enter User ID of EM ";
       alert(str);
            document.getElementById('user_id2').focus();
            return false;
       }
       if(password.value==password1.value)
       {  str+="\n Duplicate Entry  ";
       alert(str);
            document.getElementById('user_id1').focus();
            return false;
       }

if(str=="")
    return true;





    }
</script>
    </html>
