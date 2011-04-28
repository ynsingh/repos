<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Devleoped By : Kedar Kumar
Modified On  : 17-Feb 2011
This Page is to Enter Library Details
-->

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
 <jsp:include page="../admin/header.jsp" flush="true" />

<%
String sublib_id=(String)request.getAttribute("new_faculty_id");

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>LibMS : Manage Faculty </title>
<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.css" media="screen"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/acquisition/dhtmlgoodies_calendar/dhtmlgoodies_calendar.js"></script>
</head>
<body>
 <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
    
     <html:form action="/add_faculty" method="post" >
 <table border="1" class="table" width="400px" height="200px" align="center">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Manage Faculty</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">


  <tr>

    <td align="right"><strong>Faculty Id<a class="star">*</a> :</strong></td>
    <html:hidden property="faculty_id" styleId="faculty_id"   value="<%=sublib_id%>" styleClass="textBoxWidth" />
    <td><html:text property="faculty_id" styleId="faculty_id" disabled="true"  value="<%=sublib_id%>" styleClass="textBoxWidth" />

    </td>
  </tr>
  
  <tr>
    
    <td align="right"><strong>Faculty Name<a class="star">*</a> :</strong></td>
    <td><html:text property="faculty_name" styleId="faculty_name"  value="" styleClass="textBoxWidth"/>
       
    </td>
  </tr>
  <tr><td height="5px" colspan="4" ></td></tr>
  
  
 
 
  
 
<tr>
    <td colspan="4" align="center"><br/><input type="submit"  value="Submit"  onClick="return validation();"/>

        <input type="button"  value="Back" onclick="return quit();" />
 </td>
</tr>
</table>
                    </td></tr></table>
</html:form>>
 
</div>
</body>
<div
   style="
      top: 680px;

      position: absolute;

      visibility: show;">
        <jsp:include page="/admin/footer.jsp" />

</div>

 <script language="javascript" type="text/javascript">
  function quit()
  {

      window.location="<%=request.getContextPath()%>/systemsetup/manage_faculty.jsp";
      return false;
  }
   function validation()
    {




 
    var sublib_name=document.getElementById('faculty_name');

  
    







var str="Enter Following Values:-";


   
    if(sublib_name.value=="")
        {str+="\n Enter Faculty Name ";
             alert(str);
             document.getElementById('faculty_name').focus();
            return false;

        }
   




  

if(str=="Enter Following Values:-")
   {
       return true;

   }
else
    {

        alert(str);
        document.getElementById('faculty').focus();
        return false;
    }




    }
 </script>

  <%
 String msg=(String)request.getAttribute("msg");
           if (msg!=null){
%>
 <script language="javascript">
 window.location="<%=request.getContextPath()%>/systemsetup/add_faculty.jsp";
 alert("<%=msg%>");
 </script>
 <%
}

%>
</html>

 