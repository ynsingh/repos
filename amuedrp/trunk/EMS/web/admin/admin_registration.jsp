<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" flush="true" />
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@page import="java.util.*,java.io.*,java.net.*"%>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
    String regid="";
%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
<%
try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
    regid = resource.getString("registrationid");
    System.out.println("Reg id="+regid);
    %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Institute Registration</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
<script type= "text/javascript" src = "/EMS/js/countries.js"></script>
<script language="javascript" type="text/javascript">
    function quit()
    {
      
        window.location="<%=request.getContextPath()%>/login.jsp";
    }
    </script>
</head>
<body dir="<%=rtl%>">
  
        <div
   style="  top:85px;
   left:150px;
   right:5px;
      position: absolute;

      visibility: show;">

         

            <html:form  method="post" action="/instituteregistration" onsubmit="return checkPassword();" >

                <table   align="center">




     
           <tr><td valign="top" width="70%" >
            
          
                   <table align="center"  class="btn3"  style="" dir="<%=rtl%>" >
  <tr><td align="center" colspan="2" style="background-color: #7697BC; height:5px;color:white;border:solid 1px  #7697BC; margin:0px 0px 0px 0px;font-family:Tahoma;">
          <b dir="<%=rtl%>"><%=resource.getString("login.href.institute.registration")%></b></td><td dir="<%=rtl%>" align="<%=align%>"><b class="mess"><%=resource.getString("(*)")%></b></td></tr>
     <tr><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("institutename")%>*</td><td width="150px"><html:text property="institute_name" name="AdminRegistrationActionForm" />

         </td><td width="300px" class="err" dir="<%=rtl%>" align="<%=align%>"> <html:messages id="err_name"  property="institute_name">
            <%=resource.getString("institutename_cannotbe_blank")%>
             </html:messages>
         </td></tr>
     <tr><td align="<%=align%>" dir="<%=rtl%>"><%=resource.getString("instituteabbrevation")%></td><td><html:text property="abbreviated_name" name="AdminRegistrationActionForm" />  </td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="abbreviated_name">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("instituteAddress")%>*</td><td><html:text    property="institute_address" name="AdminRegistrationActionForm"/></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="institute_address">
				<%=resource.getString("instiaddress_cannotbe_blank")%>

			</html:messages>
         </td></tr>
             
             
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("country")%>*</td><td><select onchange="print_state('state',this.selectedIndex);" id="country" name ="country" style="width:180px"></select></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="country">
				<%=resource.getString("country_cannotbe_blank")%>

			</html:messages><script language="javascript">print_country("country");</script>
         </td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("state")%>*</td><td><select name ="state" style="width:180px" id="state">
                         <option value="">Select</option>

                     </select></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="state">
				<%=resource.getString("state_cannotbe_blank")%>

			</html:messages>
         </td></tr>

             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("city")%>*</td><td><html:text  property="city"/></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="city">
				<%=resource.getString("city_cannotbe_blank")%>

			</html:messages>
         </td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("pin")%></td><td><html:text  property="pin" /></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="pin">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>">StaffID/Enrollment*</td><td><html:text  property="enrollment" /></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="enrollment">
				Enrollment/StaffID cannot be blank

			</html:messages>
         </td></tr>

              <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("courtesy")%></td><td>
 <html:text property="courtesy"/>



     <%--<html:option value="" dir="<%=rtl%>"><%=resource.getString("select")%> </html:option>


                    <html:option value="mr"><%=resource.getString("Mr")%></html:option>

                    <html:option value="mrs"><%=resource.getString("Mrs")%></html:option>
                    <html:option value="ms"><%=resource.getString("Ms")%></html:option>

</html:select>--%>





                 </td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="courtesy">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("firstname")%>*</td><td><html:text  property="admin_fname" /></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="admin_fname">
				<%=resource.getString("firstname_cannotbe_blank")%>

			</html:messages>
         </td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("lastname")%>*</td><td><html:text  property="admin_lname" /></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="admin_lname">
				<%=resource.getString("lastname_cannotbe_blank")%>

			</html:messages>
         </td></tr>
              <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("gender")%>*</td><td>
 <html:select property="gender" style="width:146px">




   <html:option value="" dir="<%=rtl%>"><%=resource.getString("select")%> </html:option>

                    <html:option value="male"><%=resource.getString("male")%></html:option>
                    <html:option value="female"><%=resource.getString("female")%></html:option>

                    </html:select>








                 </td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="gender">
				<%=resource.getString("gender_cannotbe_blank")%>

			</html:messages>
         </td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("designation")%></td><td><html:text  property="admin_designation"/></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="admin_designation">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("landlineno")%></td><td><html:text  property="land_line_no" /></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="land_line_no">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("mobileno")%>*</td><td><html:text  property="mobile_no" /></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="mobile_no">
				<%=resource.getString("contactno_cannotbe_blank")%>

			</html:messages>
         </td></tr>
          <%--   <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("userid")%>*</td><td><html:text  property="userId" styleId="user_id"/></td><td  class="err" dir="<%=rtl%>" align="<%=align%>" >   <html:messages id="err_name" property="userId">
				<%=resource.getString("userid_cannotbe_blank")%>

			</html:messages>
         </td></tr>--%>
<%--<tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("password")%>*</td><td><html:password  property="admin_password" styleId="pass" style="width: 143px"/></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="admin_password">
				<bean:write name="err_name" />

			</html:messages><div id="repasswordErr1"></div>
         </td></tr>
<tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("login.managesuperadminaccount.repassword")%>*</td><td><input type="password" id="pass1" name="admin_password1" style="width: 143px"/></td><td  class="err" dir="<%=rtl%>" align="<%=align%>"><div id="repasswordErr"></div>

         </td></tr>--%>
                

             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("typeofinstitute")%>*</td><td>
                     <html:select property="type_of_institute"  style="width:146px">




 <html:option value="" dir="<%=rtl%>"><%=resource.getString("select")%></html:option>

                    <html:option value="self_financed"><%=resource.getString("selffinanced")%></html:option>
                    <html:option value="semi_govt"><%=resource.getString("semigovt")%></html:option>
                    <html:option value="govt"><%=resource.getString("govt")%></html:option>
                    <html:option value="other"><%=resource.getString("other")%></html:option>

                    </html:select>


                 </td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="type_of_institute">
				<%=resource.getString("typeofinsti_cannotbe_blank")%>

			</html:messages>
         </td></tr>
            
            
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("websitename")%></td><td><html:text  property="institute_website"/></td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="institute_website">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
             <tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("emailid")%>*</td><td>
                    <html:text  property="admin_email"/>
                   
                 
                 </td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="admin_email">
				 <%=resource.getString("emailid_cannotbe_blank")%>

			</html:messages>
         </td></tr>
              <%--<tr><td dir="<%=rtl%>" align="<%=align%>"><%=resource.getString("institutedomain")%></td><td><html:select property="institute_domain" style="width:146px" >




   <html:option value=""><%=resource.getString("select")%> </html:option>

                    <html:option value="com"><%=resource.getString(".com")%></html:option>
                  <html:option value="edu"><%=resource.getString(".edu")%></html:option>

                    </html:select>
</td><td  class="err" dir="<%=rtl%>" align="<%=align%>">   <html:messages id="err_name" property="institute_domain">
				<bean:write name="err_name" />

			</html:messages>
         </td></tr>
--%>
              <tr><td></td><td colspan="2" dir="<%=rtl%>" align="<%=align%>"><input type="submit"  id="submit" name="submit" value=" <%=resource.getString("register")%>" class="txt2" onclick="return checkPassLog();">&nbsp;<input type="button"   name="cancel" value="<%=resource.getString("cancel")%>" class="txt2" onclick="quit()"><br></td></tr>
        </table><br><br>
          </td></tr>
    </table>
 </html:form>
</div>

        </body>
        <script language="javascript">
    
    function  checkPassword()
    {
       // alert("hello");
        var pass = document.getElementById("pass").value;
        var pass1=document.getElementById("pass1").value;
        //alert(pass+" "+pass1);
        if(pass==pass1){
          //  alert("successfull");
            return true;}
        else{
            //alert("fail");
            document.getElementById("repasswordErr").innerHTML="<p>Password Mismatched!</p>";
            document.getElementById("pass1").value="";
            document.getElementById("pass1").focus();
            return false;
        }
    }

    function  checkPassLog()
    {
       // alert("hello");
        var pass = document.getElementById("pass").value;
        var id=document.getElementById("user_id").value;
        //alert(pass+" "+pass1);
        if(pass!=id){
          //  alert("successfull");
            return true;}
        else{
            //alert("fail");
            document.getElementById("repasswordErr1").innerHTML="<p>UserId & Password should be different</p>";
            document.getElementById("pass").value="";
            document.getElementById("pass").focus();
            return false;
        }
    }

    </script>

<%
String msg=(String)request.getAttribute("r_msg");
if(msg==null){msg=(String)request.getAttribute("msg1");}
if(msg!=null)
    {
    %>
    <script language="javascript" type="text/javascript">
        
        document.getElementById("user_id").value = "";
        document.getElementById("user_id").focus();
        alert("<%=msg%>");
    </script>
    <%}%>

  
</html>
