<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="Mayank Saxena" content="MCA,AMU">
<title>New Entry..</title>
<style type="text/css">
body
{
   background-color: #FFFFFF;
   color: #000000;
}
</style>
<link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>
<style type="text/css">
a:active
{
   color: #0000FF;
}
</style>
<script language="javascript">
/*
* Returns an new XMLHttpRequest object, or false if the browser
* doesn't support it
*/
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
%>
<%
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;}
    else{ rtl="RTL";page=false;}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>


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
/*
* Returns a function that waits for the specified XMLHttpRequest
* to complete, then passes it XML response to the given handler function.
* req - The XMLHttpRequest whose state is changing
* responseXmlHandler - Function to pass the XML response to
*/
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

//search library and update faculty
function search_lib() {

    var keyValue = document.getElementById('CMBLib').options[document.getElementById('CMBLib').selectedIndex].value;
    var keyValue1 = document.getElementById('TXTFACULTY').options[document.getElementById('TXTFACULTY').selectedIndex].value;
document.getElementById('TXTFACULTY').options.length = 1;

if (keyValue=="all")
    {


               document.getElementById('CMBLib').focus();
               document.getElementById('TXTFACULTY').options.length = 1;
		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update_faculty);

req.open("POST","<%=request.getContextPath()%>/lib.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getLibrary_Id="+keyValue);




}
return true;
}
}
function update_faculty(cartXML)
{
var courses = cartXML.getElementsByTagName("faculty_ids")[0];
var em = courses.getElementsByTagName("faculty_id");
var em1 = courses.getElementsByTagName("faculty_name");
        var newOpt =document.getElementById('TXTFACULTY').appendChild(document.createElement('option'));
document.getElementById('TXTFACULTY').options.length = 1;

document.getElementById('TXTDEPT').options.length = 1;
document.getElementById('TXTCOURSE').options.length = 1;

//alert(em.length);

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('TXTFACULTY').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;
}

}





function search() {

    var keyValue = document.getElementById('TXTFACULTY').options[document.getElementById('TXTFACULTY').selectedIndex].value;

if (keyValue=="Select")
    {


               document.getElementById('TXTFACULTY').focus();
               document.getElementById('TXTDEPT').options.length = 1;
                document.getElementById('TXTCOURSE').options.length = 1;
             

		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update);

req.open("POST","<%=request.getContextPath()%>/dept.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getFaculty_Id="+keyValue);


}
return true;
}
}

function search_dept() {

    var keyValue = document.getElementById('TXTDEPT').options[document.getElementById('TXTDEPT').selectedIndex].value;
var keyValue1 = document.getElementById('TXTFACULTY').options[document.getElementById('TXTFACULTY').selectedIndex].value;


if (keyValue=="Select")
    {


               document.getElementById('TXTDEPT').focus();
               document.getElementById('TXTCOURSE').options.length = 1;
		return false;
	}
else
    {
    keyValue = keyValue.replace(/^\s*|\s*$/g,"");
if (keyValue.length >= 1)
{

var req = newXMLHttpRequest();

req.onreadystatechange = getReadyStateHandler(req, update_course);

req.open("POST","<%=request.getContextPath()%>/course.do", true);

req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
req.send("getDept_Id="+keyValue+"&getFacultyID="+keyValue1);
    



}
return true;
}
}
function update_course(cartXML)
{
var courses = cartXML.getElementsByTagName("course_ids")[0];
var em = courses.getElementsByTagName("course_id");
var em1 = courses.getElementsByTagName("course_name");
        var newOpt =document.getElementById('TXTCOURSE').appendChild(document.createElement('option'));
        document.getElementById('TXTCOURSE').options.length = 1;

//alert(em.length);

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('TXTCOURSE').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;
}

}
   


function update(cartXML)
{
var depts = cartXML.getElementsByTagName("dept_ids")[0];
var em = depts.getElementsByTagName("dept_id");
var em1 = depts.getElementsByTagName("dept_name");

        var newOpt =document.getElementById('TXTDEPT').appendChild(document.createElement('option'));
        document.getElementById('TXTDEPT').options.length = 1;

for (var i = 0; i < em.length ; i++)
{
var ndValue = em[i].firstChild.nodeValue;
var ndValue1=em1[i].firstChild.nodeValue;
newOpt = document.getElementById('TXTDEPT').appendChild(document.createElement('option'));
newOpt.value = ndValue;
newOpt.text = ndValue1;


}
 search_dept();
}
</script>
 <%
        ResultSet rst = (ResultSet)session.getAttribute("faculty_resultset");
         
      
       if(rst!=null) rst.beforeFirst();
      
    %>






</head>
<body onload="return search_lib();">
      <%if(page.equals(true))
    {

%>


 <html:form  action="/NewMember1" method="post">
     <table  align="left" width="600px"  style="background-color: white;border:#c0003b 1px solid;margin:0px 0px 0px 0px;">



  <tr><td  width="600px"  style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="left">
          <table>
              <tr><td width="600px" style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="center"><b>

		 <%=resource.getString("opac.newmemberentry.text")%>



          </b>
                  </td></tr>
              </table>
        </td></tr>

  <tr><td class="btn1" >
          
          <br>
          <table align="left">
              <tr><td style="width:130px" align="left" colspan="2">  <font color="blue">  <b><%=resource.getString("opac.newmemberentry.note")%></b><br><br></font> </td></tr>
  <tr><td style="width:130px" align="left">Library ID</td><td align="left">
                <select name="CMBLib" onChange="return search_lib();" size="1" id="CMBLib" class="">
    <%
        ResultSet rs = (ResultSet)session.getAttribute("libRs");
        String lib_id = (String)session.getAttribute("library_id");

        rs.beforeFirst();

    if(lib_id==null)
    {%>

    <option selected value="">Select Any</option>
    <%}
    else
    {%>
    <option selected value="<%=lib_id%>"><%=lib_id.toUpperCase()%></option>
    <option value="">Select Any</option>

    <%
    }
    while (rs.next())
            {
    %>
    <option value="<%= rs.getString(1) %>"><%=rs.getString(1).toUpperCase()%></option>
    <% } %>
                </select></td><td  class="err" align="left">   <html:messages id="err_name"  property="CMBLib">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
              <tr><td style="width:130px" align="left"> <%=resource.getString("opac.newmemberentry.category")%>*</td><td><html:select property="CMBCAT"  style="width:148px">
                 <html:option value="">Select </html:option>
         <html:option value="Employee">Employee</html:option>
             <html:option value="Student">Student</html:option>
             

             </html:select>
             </td><td  class="err" align="left">   <html:messages id="err_name"  property="CMBCAT">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.firstname")%>*</td><td><html:text    property="TXTFNAME" /></td><td  class="err" align="left">
<html:messages id="err_name"  property="TXTFNAME">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.middlename")%></td><td><html:text  property="TXTMNAME" /></td><td  class="err" align="left">
             <html:messages id="err_name"  property="TXTMNAME">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.lastname")%>*</td><td><html:text  property="TXTLNAME" /></td><td  class="err" align="left">
                     <html:messages id="err_name"  property="TXTLNAME">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.emailid")%>*</td><td><html:text  property="TXTEMAIL" /></td><td  class="err" align="left">
                     <html:messages id="err_name"  property="TXTEMAIL">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>

             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.password")%>*</td><td><html:password  property="TXTPASS" /></td><td  class="err" align="left">
                     <html:messages id="err_name"  property="TXTPASS">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.address1")%>*</td><td><html:text  property="TXTADD1" /></td><td  class="err" align="left">
                     <html:messages id="err_name"  property="TXTADD1">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.address2")%></td><td><html:text  property="TXTADD2"/></td><td  class="err" align="left">
                     <html:messages id="err_name"  property="TXTADD2">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
              <tr><td align="left"> <%=resource.getString("opac.newmemberentry.city")%>*</td><td>
 <html:text  property="TXTCITY" />






                 </td><td  class="err" align="left">
                     <html:messages id="err_name"  property="TXTCITY">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.pincode")%>*</td><td><html:text  property="TXTPIN" /></td><td  class="err" align="left">
                     <html:messages id="err_name"  property="TXTPIN">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
            
              <tr><td align="left"> <%=resource.getString("opac.newmemberentry.state")%>*</td><td>
 <html:text property="TXTSTATE"  />

                 </td><td  class="err" align="left">
                     <html:messages id="err_name"  property="TXTSTATE">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
           
             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.country")%>*<br></td><td><html:text  property="TXTCOUNTRY"/></td><td  class="err" align="left">
                     <html:messages id="err_name"  property="TXTCOUNTRY">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
             
             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.fax")%></td><td><html:text  property="TXTFAX"  /></td><td  class="err" align="left">
                     <html:messages id="err_name"  property="TXTFAX">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
                <tr><td align="left"> <%=resource.getString("opac.newmemberentry.phone1")%>*</td><td><html:text  property="TXTPH1" /></td><td  class="err" align="left">
                        <html:messages id="err_name"  property="TXTPH1">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>

             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.phone2")%></td><td>
                     <html:text property="TXTPH2" />

                 </td><td  class="err" align="left">
                     <html:messages id="err_name"  property="TXTPH2">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>


             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.facultyof")%>
                  </td><td><select  id="TXTFACULTY" name="TXTFACULTY" style="width:148px" onChange="return search();">

      <option value="Select">Select Any</option>
    <%
    if (rst!=null)
    while (rst.next())
            {
    %>
    <option value="<%= rst.getString(1) %>"><%=rst.getString(2).toUpperCase()%></option>
    <% } %>



                      </select>
                      </td><td  class="err" align="left">
                          <html:messages id="err_name"  property="TXTFACULTY">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
             <tr><td align="left"> <%=resource.getString("opac.newmemberentry.dept")%>   </td><td>
                    <select  id="TXTDEPT" name="TXTDEPT" style="width:148px" onChange="return search_dept();">
                           <option value="Select">Select Any</option>
  



                    </select>


                 </td><td  class="err" align="left">
                     <html:messages id="err_name"  property="TXTDEPT">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
              <tr><td align="left"> <%=resource.getString("opac.newmemberentry.course")%>
                  </td><td><select id="TXTCOURSE" name="TXTCOURSE" style="width:148px" >
                             <option value="Select">Select Any</option>
 
                      </select>




</td><td  class="err" align="left">
    <html:messages id="err_name"  property="TXTCOURSE">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
<tr><td align="left"> <%=resource.getString("opac.newmemberentry.id")%>
                  </td><td><html:text property="TXTROLL" />



</td><td  class="err" align="left">
    <html:messages id="err_name"  property="TXTROLL">
            <bean:write name="err_name" />
             </html:messages>
         </td></tr>
<tr><td></td><td  align="center"><br><html:submit styleClass="txt2"><%=resource.getString("opac.newmemberentry.submit")%>" </html:submit>&nbsp;&nbsp;<input type="button"   name="cancel" value="<%=resource.getString("opac.newmemberentry.cancel")%>" class="txt2" onclick="quit()"><br><br></td></tr>
        </table>

</td></tr></table>

 </html:form>
<%}
        else
            {%>
 <html:form  action="/NewMember" method="post">
     <table  align="center" width="600px"  style="background-color: white;border:#c0003b 1px solid;left:300px;position: absoulte">



  <tr><td  width="600px"  style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="left">
          <table>
              <tr><td width="600px" style="background-color:#c0003b;color:white;font-family:Tahoma;font-size:12px" height="28px" align="center"><b>

		 <%=resource.getString("opac.newmemberentry.text")%>



          </b>
                  </td></tr>
              </table>
        </td></tr>

  <tr><td class="btn1" width="600px">

          <br>
          <table width="600px">
              <tr><td  align="right" colspan="3">  <font color="blue">  <b><%=resource.getString("opac.newmemberentry.note")%></b><br><br></font> </td></tr>

              <tr>
                  <td  class="err" align="right">   <html:messages id="err_name"  property="CMBCAT">
            <bean:write name="err_name" />
             </html:messages>
         </td>

                  <td style="width:148px"><html:select property="CMBCAT"  style="width:148px">
                 <html:option value="">Select </html:option>
         <html:option value="Employee">Employee</html:option>
             <html:option value="Student">Student</html:option>


             </html:select>
             </td><td  align="left">*<%=resource.getString("opac.newmemberentry.category")%></td></tr>
             <tr><td  class="err" align="right">
<html:messages id="err_name"  property="TXTFNAME">
            <bean:write name="err_name" />
             </html:messages>
         </td><td><html:text    property="TXTFNAME" /></td><td align="left">*<%=resource.getString("opac.newmemberentry.firstname")%></td></tr>
             <tr><td></td><td><html:text  property="TXTMNAME" /></td><td align="left"><%=resource.getString("opac.newmemberentry.middlename")%></td></tr>
             <tr>
                 <td  class="err" align="right">
             <html:messages id="err_name"  property="TXTMNAME">
            <bean:write name="err_name" />
             </html:messages>
         </td>

                 <td><html:text  property="TXTLNAME" /></td>
                 <td align="left"> *<%=resource.getString("opac.newmemberentry.lastname")%></td>

                 </tr>
             <tr>
                 <td  class="err" align="right">
                     <html:messages id="err_name"  property="TXTEMAIL">
            <bean:write name="err_name" />
             </html:messages>
         </td>
                 <td><html:text  property="TXTEMAIL" /></td><td align="left"> *<%=resource.getString("opac.newmemberentry.emailid")%></td></tr>

             <tr><td  class="err" align="right">
                     <html:messages id="err_name"  property="TXTPASS">
            <bean:write name="err_name" />
             </html:messages>
         </td><td><html:password  property="TXTPASS" /></td><td align="left"> *<%=resource.getString("opac.newmemberentry.password")%></td></tr>
             <tr><td  class="err" align="right">
                     <html:messages id="err_name"  property="TXTADD1">
            <bean:write name="err_name" />
             </html:messages>
         </td><td><html:text  property="TXTADD1" /></td><td align="left"> *<%=resource.getString("opac.newmemberentry.address1")%></td></tr>
             <tr>
                 <td  class="err" align="right">
                     <html:messages id="err_name"  property="TXTADD2">
            <bean:write name="err_name" />
             </html:messages>
         </td>
                 <td><html:text  property="TXTADD2"/></td><td align="left"> <%=resource.getString("opac.newmemberentry.address2")%></td></tr>
              <tr><td  class="err" align="right">
                     <html:messages id="err_name"  property="TXTCITY">
            <bean:write name="err_name" />
             </html:messages>
         </td><td>
 <html:text  property="TXTCITY" />






                 </td><td align="left"> <%=resource.getString("opac.newmemberentry.city")%>*</td></tr>
             <tr><td  class="err" align="right">
                     <html:messages id="err_name"  property="TXTPIN">
            <bean:write name="err_name" />
             </html:messages>
         </td><td><html:text  property="TXTPIN" /></td><td align="left"> <%=resource.getString("opac.newmemberentry.pincode")%>*</td></tr>

              <tr><td  class="err" align="right">
                     <html:messages id="err_name"  property="TXTSTATE">
            <bean:write name="err_name" />
             </html:messages>
         </td><td>
 <html:text property="TXTSTATE"  />

                 </td><td align="left"> <%=resource.getString("opac.newmemberentry.state")%>*</td></tr>

             <tr><td  class="err" align="right">
                     <html:messages id="err_name"  property="TXTCOUNTRY">
            <bean:write name="err_name" />
             </html:messages>
         </td>
                 <td><html:text  property="TXTCOUNTRY"/></td><td align="left"> *<%=resource.getString("opac.newmemberentry.country")%><br></td></tr>

             <tr><td  class="err" align="right">
                     <html:messages id="err_name"  property="TXTFAX">
            <bean:write name="err_name" />
             </html:messages>
         </td><td><html:text  property="TXTFAX"  /></td><td align="left"> <%=resource.getString("opac.newmemberentry.fax")%></td></tr>
                <tr><td  class="err" align="right">
                        <html:messages id="err_name"  property="TXTPH1">
            <bean:write name="err_name" />
             </html:messages>
         </td><td><html:text  property="TXTPH1" /></td><td align="left">*<%=resource.getString("opac.newmemberentry.phone1")%></td></tr>

             <tr><td  class="err" align="right">
                     <html:messages id="err_name"  property="TXTPH2">
            <bean:write name="err_name" />
             </html:messages>
         </td><td>
                     <html:text property="TXTPH2" />

                 </td><td align="left"> <%=resource.getString("opac.newmemberentry.phone2")%></td></tr>


             <tr><td  class="err" align="right">
                          <html:messages id="err_name"  property="TXTFACULTY">
            <bean:write name="err_name" />
             </html:messages>
         </td><td><select  id="TXTFACULTY" name="TXTFACULTY" style="width:148px" onChange="return search();">

      <option value="Select">Select Any</option>
    <%
    if (rst!=null)
    while (rst.next())
            {
    %>
    <option value="<%= rst.getString(1) %>"><%=rst.getString(2).toUpperCase()%></option>
    <% } %>



                      </select>
                      </td><td align="left"> <%=resource.getString("opac.newmemberentry.facultyof")%>
                  </td></tr>
             <tr><td  class="err" align="right">
                     <html:messages id="err_name"  property="TXTDEPT">
            <bean:write name="err_name" />
             </html:messages>
         </td><td>
                    <select  id="TXTDEPT" name="TXTDEPT" style="width:148px" onChange="return search_dept();">
                           <option value="Select">Select Any</option>




                    </select>


                 </td><td align="left"> <%=resource.getString("opac.newmemberentry.dept")%>   </td></tr>
              <tr><td  class="err" align="right">
    <html:messages id="err_name"  property="TXTCOURSE">
            <bean:write name="err_name" />
             </html:messages>
         </td><td><select id="TXTCOURSE" name="TXTCOURSE" style="width:148px" >
                             <option value="Select">Select Any</option>

                      </select>




</td><td align="left"> <%=resource.getString("opac.newmemberentry.course")%>
                  </td></tr>
<tr><td  class="err" align="right">
    <html:messages id="err_name"  property="TXTROLL">
            <bean:write name="err_name" />
             </html:messages>
         </td><td><html:text property="TXTROLL" />



</td><td align="left"> <%=resource.getString("opac.newmemberentry.id")%>
                  </td></tr>
<tr><td  align="right"><br><input type="button"   name="cancel" value="<%=resource.getString("opac.newmemberentry.cancel")%>" class="txt2" onclick="quit()">&nbsp;&nbsp;<html:submit styleClass="txt2"><%=resource.getString("opac.newmemberentry.submit")%>" </html:submit><br><br></td><td></td></tr>
        </table>

</td></tr></table>

 </html:form>



            <%}%>
    

</body>
</html>
<script>
    function quit()
    {
        location.href="/LibMS-Struts/OPAC/member.jsp";
    }
</script>