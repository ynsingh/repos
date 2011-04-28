<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%
CirMemberDetail cirmemberdetail=(CirMemberDetail)session.getAttribute("cirmemberdetail");
  CirMemberAccount  cma=(CirMemberAccount)session.getAttribute("cma");
  int max_chkout=Integer.parseInt(cma.getNoOfIssueableBook());
  int total_issued_book=Integer.parseInt(cma.getCurrentIssuedBook());
  //System.out.println(cma.getNoOfChkout()+"  "+cma.getCurrentIssuedBook()+" ");
  Integer available=max_chkout-total_issued_book;
  String msg2=(String)request.getAttribute("msg2");
  String msg1=(String)request.getAttribute("msg1");
  String checkout=(String)request.getAttribute("nocheckout");
  if(msg1!=null)
      session.setAttribute("expired", msg1);
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Serial Entry</title>
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

        <html:form action="/cir_view_member" method="post" onsubmit= "return confirm1();">

  <table width="40%" class="table"  border="1" align="center">

         <tr><td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Member CheckOut Detail</td></tr>
                <tr><td valign="top" align="center" style=" padding-left: 5px;">



            <table align="center" width="90%">
    <tr><td><html:img src="./circulation/view_mem_detail.jsp" width="128" height="120" /></td></tr>
   <tr>
    <td width="200" align="left"><strong>Member Id  </strong> </td>
    <td width=""> <html:text  property="memid"  styleClass="textBoxWidth" readonly="true" value="<%=cirmemberdetail.getId().getMemId()%>" /></td>
    

  </tr>
   
    <tr><td height="5px" colspan="4" ></td></tr>
  
  
   <tr>
    <td align="left"><strong>Member Name </strong></td>
    <td><html:text property="fname" styleClass="textBoxWidth" value="<%=cirmemberdetail.getFname() %>" readonly="true"/></td>
  </tr>
    
  
    <tr><td height="5px" colspan="4" ></td></tr>
 
   <tr>
    <td align="left"><strong>Max Checkout Limit</strong></td>
    <td><html:text property="max_chkout" styleClass="textBoxWidth" value="<%=String.valueOf(max_chkout)%>" readonly="true"/></td>
  </tr>
 
  <tr><td height="5px" colspan="4" ></td></tr>
  <tr>
    <td align="left"><strong>No. of Current CheckOut  </strong></td>
    <td ><html:text property="total_issued_book" styleClass="textBoxWidth" value="<%=cma.getCurrentIssuedBook() %>" readonly="true"/><a class="mess" href="<%=request.getContextPath()%>/cir_memcheckout_details.do">Detail</a></td>
  </tr>
  <tr><td height="5px" colspan="4" ></td></tr>
  <tr>
    <td align="left"><strong>Available Chkout</strong></td>
    <td><html:text property="" styleClass="textBoxWidth" value="<%=available.toString() %>" readonly="true"/></td>
  </tr>

  <tr>
    <td align="left"><strong>Total Chkout Transaction Till Date</strong></td>
    <td><html:text property="" styleClass="textBoxWidth" value="<%=cma.getTotalIssuedBook() %>" readonly="true"/></td>
  </tr>
  <tr><td height="10px" colspan="4" ></td></tr>
  <tr>
    <td colspan="4" align="center">
        <html:submit property="button" value="Checkout" styleClass="btn" style="left:80px" onclick="return confirm1();"  />
   <input type="button" onclick="return quit();" class="btn" style="left:150px" value="Back"/></td>
</tr>
  <tr><td height="15px" colspan="4" ></td></tr>
   <tr><td colspan="2">
           <%if(msg1!=null){%>
           <span style="font-size:12px;font-weight:bold;color:red;" ><%if(msg1!=null){%><%=msg1%><%}%></span>
           <%}else{%>
             <span style="font-size:12px;font-weight:bold;color:blue;" ><%if(msg2!=null){%><%=msg2%><%}%></span>
   <%}%>
   <span style="font-size:12px;font-weight:bold;color:red;" ><%if(checkout !=null){%><%=checkout%><%}%></span>
       </td></tr>
            </table></td></tr>


  </table>

    
  </html:form>
        </div>

</body>
</html>

<script language="javascript" type="text/javascript">
  function quit()
  {

      window.location="<%=request.getContextPath()%>/circulation/cir_checkout.jsp";
      return false;
  }

  function confirm1()
{
   
  if(<%=available%><=0)
  {
      alert("Can`t Checkout");
      return false;
  }
  return true;
}

</script>