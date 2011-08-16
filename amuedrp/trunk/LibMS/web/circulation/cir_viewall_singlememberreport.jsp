<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for Register newMember,Ajax for Dept,Fac,course is used & image upload can be done.
     This jsp page is Second page During Process of member Registration.
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,org.apache.struts.upload.FormFile,com.myapp.struts.hbm.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>

<jsp:include page="/admin/header.jsp"/>
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

 <%

        CirCheckin checkindetail=(CirCheckin)request.getAttribute("checkindetail");
        String  mem_id=(String)request.getAttribute(" mem_id");



%>




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newformat.css"/>



</head>
<script language="javascript" type="text/javascript">

   function back()
    {

        location.href="<%=request.getContextPath()%>/circulation/cir_checkin_report.jsp";
    }






</script>


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


    <form>

        <table  dir="<%=rtl%>" align="center" width="400px"  height="200px" class="table">



  <tr><td  width="400px" dir="<%=rtl%>"   class="headerStyle"  align="center">


		<%=resource.getString("circulation.cir_viewall_singlememreport.viewmemchkinreport")%>



        </td></tr>

  <tr><td dir="<%=rtl%>" valign="middle" align="center" >

 
            <table align="center" dir="<%=rtl%>"  width="400px">



                <tr><td dir="<%=rtl%>" width="150px">&nbsp;<%=resource.getString("circulation.cir_newmember.memberid")%></td><td dir="<%=rtl%>" class="table_textbox"><input type="text"   name="TXTMEMID" value="<%=checkindetail.getMemberId()%>" readonly="true" style="width:160px" /></td>
                    

                   </tr>

                   <tr><td dir="<%=rtl%>" ><%=resource.getString("circulation.cir_viewall_singlememreport.chkinid")%></td><td dir="<%=rtl%>" class="table_textbox"><input type="text"   name="TXTCHKINID" value="<%=checkindetail.getId().getCheckinId()%>" readonly="true" style="width:160px" /><br/>

                </td>

                </tr>
                <tr><td dir="<%=rtl%>">&nbsp;<%=resource.getString("circulation.cir_viewmem_chkoutreport.docid")%></td><td dir="<%=rtl%>" class="table_textbox"><input type="text"  name="TXTDOCID" value="<%=checkindetail. getDocumentId()%>" readonly="true" style="width:160px" /></td></tr>
                <tr><td dir="<%=rtl%>">&nbsp;<%=resource.getString("circulation.cir_checkinbookdetail.returndate")%></td><td dir="<%=rtl%>" class="table_textbox"><input type="text"  name="TXTRETDATE" value="<%=checkindetail.getReturningDate()%>" readonly="true" style="width:160px" />

                </td>



                </tr>
              

               



          
            <tr><td dir="<%=rtl%>"><%=resource.getString("circulation.cir_viewall_singlememreport.damage")%></td><td dir="<%=rtl%>" class="table_textbox"> <input type="text" name="TXTDAMAGESTATUS" value="<%=checkindetail.getDamagedStatus()==null?"":checkindetail.getDamagedStatus()%>" readonly="true" style="width:160px" />

                 </td>


             </tr>
             <tr><td dir="<%=rtl%>" ><%=resource.getString("circulation.cir_checkinbookdetail.lossstatus")%></td><td dir="<%=rtl%>"  class="table_textbox"><input type="text"  name="TXTLOSSTATUS" value="<%=checkindetail.getLossStatus()==null?"":checkindetail.getLossStatus()%>" readonly="true" style="width:160px"/>


                 </td></tr>
             <tr><td dir="<%=rtl%>" ><%=resource.getString("circulation.cir_viewall_singlememreport.reason")%></td><td dir="<%=rtl%>"  class="table_textbox"><input type="text"  name="TXTREASON" value="<%=checkindetail.getReason()==null?"":checkindetail.getReason()%>" readonly="true" style="width:160px"/>


                 </td></tr>
            



            </table></td></tr>
      
  <tr><td colspan="4" align="center" class="txt2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="<%=resource.getString("circulation.cir_member_reg.back")%>" onclick="return back();">
                      </td>

          </tr>
    


 </form>


</body>


</div>
  


</html>
