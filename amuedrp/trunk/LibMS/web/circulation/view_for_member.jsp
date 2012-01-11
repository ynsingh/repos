<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <%@page contentType="text/html" pageEncoding="UTF-8" import="com.myapp.struts.hbm.*,java.util.*;"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>
<%
CirMemberDetail cirmemberdetail=(CirMemberDetail)session.getAttribute("cirmemberdetail");
String path=request.getContextPath()+"/circulation/view_mem_detail.jsp";

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

   <%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";
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
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="common" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/newformat.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/css/page.css" rel="stylesheet" type="text/css" />

<script language="javascript" type="text/javascript">
  function quit()
  {

      window.location="<%=request.getContextPath()%>/circulation/cir_checkout.jsp";
      return false;
  }

  function confirm1()
{
 
  if(<%=available %> <=0 )
  {
      alert("<%=resource.getString("circulation.cir_newmember.canotcheckout")%>");
      return false;
  }

document.getElementById("section1").innerHTML = "<iframe align=center name=section scrolling=no id=section width=400px src=<%=request.getContextPath()%>/cir_view_member.do frameborder=0 />";
  
//window.frames['section'].location.href = "<%=request.getContextPath()%>/cir_view_member.do";



  return true;
}
 function call()
{



document.getElementById("section1").innerHTML = "<iframe align=center name=section scrolling=no id=section width=100% height=350px src=<%=request.getContextPath()%>/circulation/cir_memcheckout_details.do frameborder=0 />";



  return true;
}
</script>
</head>
<body>
     <div
   style="  top:120px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">

    <html:form action="/cir_view_member" method="post" >
 <table dir="<%=rtl%>" class="table" width="88%"  align="center">

   

      <tr><td dir="<%=rtl%>" align="center" colspan="2" class="headerStyle" bgcolor="#E0E8F5" height="25px;"><%=resource.getString("circulation.viewformember.memberchkoutdetail")%></td></tr>
      <tr><td valign="top" align="center" style=" padding-left: 10px;" width="50%">
                        <br/><br/>


                        <table dir="<%=rtl%>" align="center" width="100%" >
                <tr><td >Member Snap</td><td dir="<%=rtl%>" align="left"><html:img src="<%=path%>" width="128" height="120"  style="border:solid 4px cyan;" /><br/><br/></td></tr>
   <tr>
    <td dir="<%=rtl%>" width="200" align="<%=align%>"><strong><%=resource.getString("circulation.cir_newmember.memberid")%> </strong> </td>
    <td dir="<%=rtl%>" width=""> <html:text  property="memid"  styleClass="textBoxWidth" readonly="true" value="<%=cirmemberdetail.getId().getMemId()%>" /></td>
 
  </tr>
   
    <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
  
  
   <tr>
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("circulation.cir_createaccount1.memname")%> </strong></td>
    <td dir="<%=rtl%>"  ><html:text property="fname" styleClass="textBoxWidth" value="<%=cirmemberdetail.getFname() %>" readonly="true"/></td>
  </tr>
    
  
    <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
 
   <tr>
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("circulation.viewformember.maxchkoutlimit")%></strong></td>
    <td dir="<%=rtl%>" ><html:text property="max_chkout" styleClass="textBoxWidth" value="<%=String.valueOf(max_chkout)%>" readonly="true"/></td>
  </tr>
 
  <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
  <tr>
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("circulation.viewformember.noofcurrentchkout")%>  </strong></td>
    <td dir="<%=rtl%>" ><html:text property="total_issued_book" styleClass="textBoxWidth" value="<%=cma.getCurrentIssuedBook() %>" readonly="true"/><a class="mess" onClick="call();" href="#"><%=resource.getString("circulation.viewformember.detail")%></a></td>
  </tr>
  <tr><td dir="<%=rtl%>" height="5px" colspan="4" ></td></tr>
  <tr>
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("circulation.viewformember.availablechkout")%> </strong></td>
    <td dir="<%=rtl%>" ><html:text property="" styleClass="textBoxWidth" value="<%=available.toString() %>" readonly="true"/></td>
  </tr>

  <tr>
    <td dir="<%=rtl%>" align="<%=align%>"><strong><%=resource.getString("circulation.viewformember.totalchkout")%></strong></td>
    <td dir="<%=rtl%>"  ><html:text property="" styleClass="textBoxWidth" value="<%=cma.getTotalIssuedBook() %>" readonly="true"/></td>
  </tr>
  <tr><td dir="<%=rtl%>" height="10px" colspan="4" ></td></tr>
  <tr>
    <td dir="<%=rtl%>" colspan="4" align="center">
        <input type="button"  value="<%=resource.getString("circulation.viewformember.chkout")%>"  class="btn" style="left:80px" onclick="return confirm1();"  />
   <input type="button" onclick="return quit();" class="btn" style="left:150px" value="<%=resource.getString("circulation.cir_newmember.back")%>"/></td>
</tr>
  <tr><td dir="<%=rtl%>" height="15px" colspan="4" ></td></tr>
   <tr><td dir="<%=rtl%>" colspan="2">
           <%if(msg1!=null){%>
           <span style="font-size:12px;font-weight:bold;color:red;" ><%if(msg1!=null){%><%=msg1%><%}%></span>
           <%}else{%>
             <span style="font-size:12px;font-weight:bold;color:blue;" ><%if(msg2!=null){%><%=msg2%><%}%></span>
   <%}%>
   <span style="font-size:12px;font-weight:bold;color:red;" ><%if(checkout !=null){%><%=checkout%><%}%></span>
       </td></tr>
            </table></td>
       <td valign="top">
           <div id="section1">
               
           </div>
           </td>

                </tr>
       <tr>
           <td >
               <div id="section">
               
               </div>
           </td>
           <td  valign="top">
               <%--<div id="section2">
                   <iframe align=center name="section2" scrolling=yes id="section2" height="50%" width="100%" src="#" frameborder="0"/>

               </div>--%>
           </td>

      </tr>

     
     


  </table>

    
  </html:form>
        </div>




</body>
</html>
