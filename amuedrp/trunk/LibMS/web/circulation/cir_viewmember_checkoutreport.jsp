<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for Register newMember,Ajax for Dept,Fac,course is used & image upload can be done.
     This jsp page is Second page During Process of member Registration.
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,org.apache.struts.upload.FormFile,com.myapp.struts.hbm.*"%>
 <jsp:include page="/admin/header.jsp"/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>




 <%

        CirCheckout checkoutdetail=(CirCheckout)request.getAttribute("checkoutdetail");
        String  mem_id=(String)request.getAttribute(" mem_id");



%>




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newformat.css"/>



</head>
<div
   style="  top:130px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">




<style type="text/css">
body
{

}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<style type="text/css">
a:active
{
   color: #0000FF;
}
</style>


</head>
<body>


   

   <table  align="center" width="500px" height="200px"  class="table">



  <tr><td    class="headerStyle"  align="center">


		View Member CheckOut Detail



        </td></tr>

  <tr><td valign="middle" align="center" >

  
            <table>



                <tr><td width="150px">&nbsp;Member ID</td><td class="table_textbox"><input type="text"   name="TXTMEMID" value="<%=checkoutdetail.getMemid()%>" readonly="true" style="width:160px" /></td>
                    <td></td>

                   </tr>

                   <tr><td >Checkout Id</td><td class="table_textbox"><input type="text"   name="TXTCHKOUTID" value="<%=String.valueOf(checkoutdetail.getId().getCheckoutId())%>" readonly="true" style="width:160px" /><br/>

                </td>

                </tr>
                <tr><td>&nbsp;DocumentId</td><td class="table_textbox"><input type="text"  name="TXTDOCID" value="<%=checkoutdetail.getDocumentId()%>" readonly="true" style="width:160px" /></td></tr>
                <tr><td>&nbsp;Issue Date</td><td class="table_textbox"><input type="text"  name="TXTISSUEDATE" value="<%=checkoutdetail.getIssueDate()%>" readonly="true" style="width:160px" />

                </td>



                </tr>
                <tr>  <td>&nbsp;Due Date</td><td class="table_textbox"><input type="text"  name="TXTDUEDATE" value="<%=checkoutdetail.getDueDate() %>" readonly="true" style="width:160px" />

                </td>



            </tr>
            <tr><td> Status</td><td class="table_textbox"> <input type="text" name="TXTSTATUS" value="<%=checkoutdetail.getStatus()%>" readonly="true" style="width:160px" />

                 </td>


             </tr>





     </table>
      </td></tr>
  <tr><td colspan="4" align="center" class="txt2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Back" onclick="return back();">
                      </td>

          </tr>
     </table>


 </form>


</body>


</div>
  <script language="javascript" type="text/javascript">

   function back()
    {

        location.href="<%=request.getContextPath()%>/circulation/cir_checkout_report.jsp";
    }






</script>



   <div
   style="
      top: 650px;

      position: absolute;

      visibility: show;">
        <jsp:include page="/admin/footer.jsp" />
   </div>

</html>
