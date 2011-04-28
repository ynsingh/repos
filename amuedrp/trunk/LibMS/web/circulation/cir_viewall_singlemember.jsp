<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--  Design by Iqubal Ahmad
      Modified on 2011-02-02
     This jsp page is meant for Register newMember,Ajax for Dept,Fac,course is used & image upload can be done.
     This jsp page is Second page During Process of member Registration.
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="/admin/header.jsp"/>
 <%@page contentType="text/html" import="java.util.*,java.io.*,java.sql.*,org.apache.struts.upload.FormFile,com.myapp.struts.hbm.*"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>




 <%
       
        CirMemberDetail cmemdetail=(CirMemberDetail)request.getAttribute("cmemdetail");
        String  mem_id=(String)request.getAttribute(" mem_id");

       

%>




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Registration Page</title>
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
    

    <form>
   
   <table  align="center"  height="400px"  class="table">



  <tr><td   class="headerStyle"  align="center">


		View All Member



        </td></tr>

  <tr><td valign="center" align="center" width="400px" >

          <br>
            <table  width="300px" >



                <tr><td >&nbsp;Member ID</td><td class="table_textbox"><input type="text"   name="TXTMEMID" value="<%=cmemdetail.getId().getMemId()%>" readonly="true" style="width:160px" /></td>
                    <td></td>    

                   </tr>
                   
                   <tr><td >First Name</td><td class="table_textbox"><input type="text"   name="TXTFNAME" value="<%=cmemdetail.getFname()%>" readonly="true" style="width:160px" /><br/>
                 
                </td>

                </tr>
                <tr><td>&nbsp;Middle Name</td><td class="table_textbox"><input type="text"  name="TXTMNAME" value="<%=cmemdetail.getMname()%>" readonly="true" style="width:160px" /></td></tr>
                <tr><td>&nbsp;Last Name</td><td class="table_textbox"><input type="text"  name="TXTLNAME" value="<%=cmemdetail.getLname()%>" readonly="true" style="width:160px" />
                
                </td>
                


                </tr>
                <tr>  <td>&nbsp;Email ID</td><td class="table_textbox"><input type="text"  name="TXTEMAILID" value="<%=cmemdetail.getEmail()%>" readonly="true" style="width:160px" />
                
                </td>
                


            </tr>
            <tr><td>Local Address</td><td class="table_textbox"> <input type="text" name="TXTADD1" value="<%=cmemdetail.getAddress1()%>" readonly="true" style="width:160px" />
                 
                 </td>
             

             </tr>
             <tr><td>City</td><td class="table_textbox"><input type="text"  name="TXTCITY1" value="<%=cmemdetail.getCity1()%>" readonly="true" style="width:160px"/>
                

                 </td></tr>
             <tr><td >State*</td><td class="table_textbox"><input type="text"  name="TXTSTATE1" value="<%=cmemdetail.getState1()%>" readonly="true" style="width:160px"/>
                 

                 </td></tr>
             <tr><td>Country</td><td class="table_textbox"><input type="text"  name="TXTCOUNTRY1" value="<%=cmemdetail.getCountry1()%>" readonly="true" style="width:160px"/>
                 
                 </td></tr>
             <tr><td>Mobile</td><td class="table_textbox"><input type="text"  name="TXTPH1" value="<%=cmemdetail.getPhone1()%>" readonly="true" style="width:160px"/>
                 

                 </td></tr>
             <tr><td>Land Line No.</td><td class="table_textbox"><input type="text"  name="TXTPH2" value="<%=cmemdetail.getPhone2()%>" readonly="true" style="width:160px"/></td> </tr>
             <tr><td>Fax</td><td class="table_textbox"><input type="text"  name="TXTFAX" value="<%=cmemdetail.getFax()%>" readonly="true" style="width:160px"/></td></tr>

             <tr><td>Permanent Address</td><td class="table_textbox"><input type="text" name="TXTADD2" value="<%=cmemdetail.getAddress1()%>" readonly="true" style="width:160px"/></td>
                

            </tr>
            <tr><td >City</td><td class="table_textbox"><input type="text"  name="TXTCITY2" value="<%=cmemdetail.getCity2()%>" readonly="true" style="width:160px"/></td></tr>
            <tr><td >State</td><td class="table_textbox"><input type="text"  name="TXTSTATE2"  readonly="true" style="width:160px"/></td>
                 </tr>
            <tr><td >Country</td><td class="table_textbox"><input type="text"  name="TXTCOUNTRY2" value="<%=cmemdetail.getCountry2()%>"  readonly="true" style="width:160px"/></td></tr>


         
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
        
        location.href="<%=request.getContextPath()%>/circulation/cir_viewall_member_detail.jsp";
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
