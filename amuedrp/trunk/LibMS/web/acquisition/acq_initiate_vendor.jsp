<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%

String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("msg1");

%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Vendor details</title>
<link href="common" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
</head>
<body>
   <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

       <html:form action="/acq_vendor" method="post" onsubmit="return check1();"  style="position:absolute; left:400px; top:50px;">
 <table width="400px" border="1" class="table" height="200px"  valign="top" align="center" >

  <tr><td   class="headerStyle" height="25px"  align="center">



         Manage Vendor

      </td></tr>
   

        <tr><td   width="400px" height="200px" valign="top" style="" align="center">
                <br/>    <br/> 
                <table cellspacing="10px">

                    <tr><td rowspan="5">
                            <table>
                                <tr><td>Vendor Id:<a class="star">*</a></td>

                                </tr>
                                <tr><td><html:text property="vendor_id" styleId="vendor_id" /></td>


                                 </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                       <tr><td colspan="5" height="10px" class="mandatory" align="right"><a class="star">*</a>indicated fields are mandatory</td></tr>
<tr><td colspan="5" height="20px"></td>
</tr>
                                    </tr>





                            </table>





                        </td><td width="150px" align="center"> <input type="submit" name="button" value="New" Class="txt1"/>
</td></tr>
                    <tr><td width="150px" align="center"> <html:submit property="button" value="Update" styleClass="txt1" />
</td></tr>

 <tr><td width="150px" align="center"> <html:submit property="button" value="Delete" styleClass="txt1"/>
</td></tr>

<tr><td width="150px" align="center"><input type="submit" name="button" value="View" class="txt1" /></td></tr>
<tr><td width="150px" align="center"><input type="button" id="Button5" name="button" value="Back" class="txt1" onclick="return quit()"/></td></tr>
 


                </table>


</td></tr>
<tr><td border="1">

<%  if(msg!=null)
    {%>
   <span class="mess" ><%=msg%></span>
<%}%>
<%  if(msg1!=null)
    {%>
   <span class="err"><%=msg1%></span>
<%}%>

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


function check1()
{
    if(document.getElementById('vendor_id').value=="")
    {
        alert("Enter Vendor Id...");

        document.getElementById('vendor_id').focus();

        return false;
    }

  }






  function quit()
  {

      window.location="<%=request.getContextPath()%>/admin/main.jsp";
      return false;
  }



</script>
     

</html>
