<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--
     Design by Iqubal Ahmad
     Modified on 2011-02-02
     This jsp page is meant for Dynamically change button value as View,Update,Deleate this is only page from where
     View, update, Deleate is done also used for   Ajax for
     Dept,Fac,course & image upload can be done.
     This jsp page is third page  for one Complete Process  of member Registration.
--%>


<%@page  pageEncoding="UTF-8" contentType="text/html" import="java.util.*,java.io.*,com.myapp.struts.hbm.*"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<jsp:include page="/admin/header.jsp"/>

<%CirMemberDetail memobj=(CirMemberDetail)request.getAttribute("memobj"); %>


<link rel="stylesheet" href="<%=request.getContextPath()%>/cupertino/jquery.ui.all.css" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.core.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.widget.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.ui.datepicker.min.js"></script>
<style type="text/css">
.ui-datepicker
{
   font-family: Arial;
   font-size: 13px;
}
</style>

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







<body>
   
 <html:form action="/card_inf" method="post">


   <table  align="center" width="80%"   class="table">



  <tr><td width="80%" height="25px"  class="headerStyle"  align="center"> Member Details



        </td></tr>

  <tr><td  align="center">

          <br>
            <table  width="80%" >



                <tr><td  class="txtStyle" width="25%">MemberId</td><td  width="25%" class="table_textbox">
                        <html:text    property="TXTMEMID" name="GenerateCardActionForm" readonly="true" value="<%=memobj.getId().getMemId()%>"  style="width:160px" /></td>
                    <td  width="25%" rowspan="6" width="150px" valign="bottom">

                       image
                        
                    </td><td rowspan="6" width="25%" class="table_textbox" valign="bottom">

                        
                        <%--    <html:img src="./circulation/upload.jsp"  alt="no Image Selected" width="120" height="120"/> --%>
                        
                        
                    </td>
                   </tr>
                   <tr><td class="txtStyle">Fname</td><td class="table_textbox"><html:text  property="TXTFNAME" readonly="true"  name="GenerateCardActionForm" value="<%=memobj.getFname()%>" style="width:160px" />
                 
                </td>

                </tr>
                <tr><td class="txtStyle">Mname</td><td class="table_textbox"><html:text  property="TXTMNAME" readonly="true"  name="GenerateCardActionForm" value="<%=memobj.getMname()%>"  style="width:160px" /></td></tr>
                <tr><td class="txtStyle">Lname</td><td class="table_textbox"><html:text   property="TXTLNAME" readonly="true" name="GenerateCardActionForm" value="<%=memobj.getLname()%>"  style="width:160px" />
                
                </td>



                </tr>
                <tr>  <td class="txtStyle">Email</td><td class="table_textbox"><html:text property="TXTEMAILID" readonly="true"  name="GenerateCardActionForm" value="<%=memobj.getEmail()%>" style="width:160px" />
                <br/><div align="left" class="err" id="searchResult" style="border:#000000; "></div>
                 
                </td>
                

            </tr>
            <tr><td class="txtStyle">LocalAddress</td><td  class="table_textbox"> <html:text property="TXTADD1" style="width:160px" readonly="true" name="GenerateCardActionForm" value="<%=memobj.getAddress1()%>"  style="width:160px" />
                 

                 </td>
             

             </tr>
            
            
             
             

           
            
            
             

     </table>
      </td></tr>
 <tr><td colspan="4" align="center" class="txt2">
         
         <input type="Submit" value=""  />
         
         &nbsp;&nbsp;

         <input type="submit"  value="PrintCard"/>
         <br/>        </td>

          </tr>
     </table>

         <input type="hidden" id="btn" name="button" value="" />



 </html:form>


</body>
</div>

</html>
