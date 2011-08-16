<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%
String msg1=(String)request.getAttribute("msg1");
String msg2=(String)request.getAttribute("msg2");
String msg=(String)request.getAttribute("msg");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Vendor details</title>
<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />
</head>
<body>
   <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

       <html:form action="/acq_approval" onsubmit="return check1()" method="post"  style="position:absolute; left:400px; top:50px;">
  <table border="1" class="table" width="400px" height="200px" align="center">
                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"> Approval Process</td></tr>
                <tr><td valign="top" align="center"> <br/>
                <table cellspacing="10px">
                <table cellspacing="10px">

                    <tr><td rowspan="5">
                            <table>
                                <tr><td>Approval No.:</td></tr>
                                <tr><td><html:text  styleId="approvalno" property="approval_no" /></td>
                                    <tr><td></td></tr>
                                    <tr><td></td></tr>
                            </tr>
                                                    

                            </table>


 <%  if(msg1!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>
<%}%>
 <%  if(msg2!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg2%></span>
<%}%>

 <%  if(msg!=null)
    {%>
   <span style="font-size:12px;font-weight:bold;color:blue;" ><%=msg%></span>
<%}%>



                        </td><td width="150px" align="center"> <input type="submit" name="button" value="New" Class="txt1"/>
</td></tr>
                    <tr><td width="150px" align="center"> <html:submit property="button" value="View" styleClass="txt1" />
</td></tr>

 <tr><td width="150px" align="center"> <html:submit property="button" value="Delete" styleClass="txt1"/>
</td></tr>
 <tr><td width="150px" align="center"><input type="button" id="Button5" name="button" value="Back" class="txt1" onclick="return quit()"/></td></tr>



                </table>













</td></tr></table>


</html:form>
</div>
</body>
<div
   style="
      top: 750px;

      position: absolute;

      visibility: show;">

  </div>
<script language="javascript" type="text/javascript">






  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }

function check1()
{


     if(document.getElementById('approvalno').value=="")
    {
        alert("Enter Approval No");

        document.getElementById('approvalno').focus();

        return false;
    }
     

return true;
  }


</script>
  
</html>
