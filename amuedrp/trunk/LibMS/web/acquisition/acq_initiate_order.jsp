<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>


<%
String library_id=(String)session.getAttribute("library_id");
String msg1=(String)request.getAttribute("msg1");
String msg=(String)request.getAttribute("msg");

//session.setAttribute("backlocation", back);

//String back1=(String)session.getAttribute("backlocation");
%>
<head>
     <script>
            function back()
            {
                location.href="<%=request.getContextPath()%>/admin/main.jsp";

            }

           function check1()

                {

            if(document.getElementById('orderno').value=="")
            {
                alert("Enter Order No....");

                document.getElementById('orderno').focus();

            return false;
            }

        var keyValue = document.getElementById('vendor_type').options[document.getElementById('vendor_type').selectedIndex].value;
   if (keyValue=="Select")
    {

        alert("Select Vendor Type");

        document.getElementById('vendor_type').focus();

        return false;
    }
    return true;

  }


 </script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Order Process</title>
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

       <html:form action="/acq_initiate_order"  onsubmit="return check1()"method="post"  style="position:absolute; left:400px; top:50px;">
 <table width="400px" height="200px"  valign="top" align="center" style="border:#cccccc solid 1px;">

  <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;"> Order Processing</td></tr>
   <tr><td></td></tr>

        <tr><td   width="400px" height="200px" valign="top" style="" align="center">
                <br></br>
                <table cellspacing="10px">

                    <tr><td rowspan="5">
                            <table>
                                <tr><td  align="left" class="txtStyle"> <strong> Order No<a class="star">*</a>:</strong></td></tr>
                                <tr><td><html:text property="order_no"  styleId="orderno" name="AcqOrderManagementActionForm" styleClass="textBoxWidth" /></td>
                                  
                            </tr>
                                <tr><td td  align="left" class="txtStyle"> <strong> Vendor<a class="star">*</a>:</strong></td></tr>
                                <tr> <td><html:select property="vendor"  styleId="vendor_type" name="AcqOrderManagementActionForm" value="Select" styleClass="textBoxWidth" >
                                            <html:option value="Select"> Select</html:option>
                                            <html:options collection="vendors" name="AcqBibliographyDetails" labelProperty="vendor" property="vendor"/>
                                         </html:select>
                                 </td></tr>
                            </table>

  </td><td width="150px" align="center"> <input type="submit" name="button" value="New Order" Class="txt1"/>
</td></tr>
                    <tr><td width="150px" align="center"> <html:submit property="button" value="Cancel Order" styleClass="txt1" />
</td></tr>

 <tr><td width="150px" align="center"> <html:submit property="button" value="View Order" styleClass="txt1"/>
</td></tr>
 <tr><td width="150px" align="center"> <html:submit property="button" value="Print Order" styleClass="txt1"/>
</td></tr>
 <tr><td width="150px" align="center"><input type="button" id="Button5" name="button" value="Back" class="txt1" onclick="return quit()"/></td></tr>


                </table>
<tr><td class="mess">

        <%
          if (msg!=null)
          {

        %>

        <p class="mess">  <%=msg%></p>

        <%
        }
        %>
         <%if (msg1!=null)
          {
        %>


        <p class="err">  <%=msg1%></p>

        <%
        }
        %>

                    </td></tr>

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



</script>
  
 

</html>
