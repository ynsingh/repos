<%@ page import="java.util.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/admin/header.jsp" flush="true" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
<script language="javascript">
function fun()
{

    document.getElementById("form1").action ="<%=request.getContextPath()%>/membercard.do"
    document.getElementById("form1").method="post";
    document.getElementById("form1").submit();
}

</script>


<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>

<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />


<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>

<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>




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

          <html:form  method="post" action="/membercard" onsubmit="fun();"  styleId="form1">
              <table width="50%"  align="center" class="table" border="1px">


                <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Member List</td></tr>
                <tr><td  valign="top" align="center">
                        <table>
                 <tr>
                     <td align="" >Member ID</td><td><html:text property="memid" styleId="memid"  value=""  onchange="fun()" /> </td>
        </tr>

        <tr>
         <td align="">Registration Date</td>
         <td><html:text property="reg_date" styleId="reg_date"  value=""  onchange="fun()"/></td>
         <td>Expiry date</td>
         <td width="50%">
             <html:text property="expiry_date" styleId="expiry_date"  value=""  onchange="fun()"/>
             
             
         </td>
       </tr>
       <tr><td colspan="4" align="center"><input type="submit" value="Find & Generate Card"  onclick="fun()"/>
             <input type="reset" value="Clear"  onclick="clear()"/></td></tr>
                        </table>
      </tr>
      
                </table>

      </html:form>


        </div>
    </body>


</html>
