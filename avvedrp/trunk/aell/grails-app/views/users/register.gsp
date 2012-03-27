
<%@ page import="java.lang.String" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

 <head>
 <title>${grailsApplication.config.page_title}</title> 
 <link rel="shortcut icon" type="image/x-icon" href="${hostname}/aell/images/favicon.ico">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="contentAdmin" />
<style type="text/css">
.text {
			font-family: Verdana, Arial, Helvetica, Bitstream Vera Sans;
	        font-size: 12px;
			color:black;
			vertical-align:middle;
			font-weight:bold
		}
		
		
</style>
  </head>
<body> 
<div id="innerData">
<table border="0" width="100%">
     <tr>
         <td bgcolor="#FFFF99" class="tblAdminTemplate">${grailsApplication.config.user_register} </td>
      </tr>
       <tr><td>
   <g:if test="${flash.message}">
         <div class="message" ><img src="${hostname}/aell/images/tick.gif" style="background-color: blue;" title="Success" height="20" width="20">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
         <div class="error"><img src="${hostname}/aell/images/wrong.gif" title="Failure" height="20" width="20">${flash.error}</div>
    </g:if>
   </td></tr>
      <tr>
      <td>
      <form action="saveAdminUser" name="regForm" method='POST'>
         <table border="0" align="left" class="noBorderTable" width="100%">    
           <tr>
                <td class="text" width="40%" align="left">
                   Email
                </td>
                <td class="text" align="left" width="">
                    <input name="emailId" type="text" id="emailId" /> <span style="color:maroon;font-weight: bold;">*</span>
                    
                </td>
            </tr>   
            <tr>
                <td class="text" align="left">
                   Password
                </td>
                <td class="text" align="left">
                    <input type="password" name="paswd" id="paswd" /> <span style="color: maroon;font-weight: bold;">*</span>
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                    Confirm Password
                </td>
                <td class="text" align="left">
                    <input type="password" name="confPaswd" id="confPaswd" /> <span style="color: maroon;font-weight: bold;">*</span>
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                    Role
                </td>
                <td class="text" align="left">
                     <g:select name='roleList.id' optionKey="id" optionValue="authority" from="${roleList}"  noSelection="['null':'select']"></g:select><span style="color:maroon;font-weight: bold;">*</span>
                </td>
            </tr>
             <tr>
                <td class="text" align="left">
                    University
                </td>
                <td class="text" align="left">
                      <g:select name='universityList.id' optionKey="id" optionValue="universityName" from="${universityList}"  noSelection="['null':'select']"></g:select><span style="color:maroon;font-weight: bold;">*</span>
                </td>
            </tr>
            <tr>
                <td colspan="2">&nbsp;</td>
            </tr>        
            <tr>
                <td class="text" align="left">                    	
                   First Name
                </td>
                <td class="text" align="left">
                    <input type="text" name="firstName" id="firstName"/>
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                    Last Name
                </td>
                <td class="text" align="left">
                    <input type="text" name="lastName" id="lastName" />
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                   Age Group
                </td>
                <td class="text" align="left">
                    <select name="age" id="age" style="width:72%">
                      <option value="NULL">--Select--</option>
                	  <option value="0-15">0 - 15</option>
                	  <option value="16-20">16 - 20</option>
                	  <option value="21-25">21 - 25</option>
                      <option value="26-30">26 - 30</option> 
                      <option value="31-35">31 - 35</option> 
                      <option value="36-40">36 - 40</option> 
                      <option value="41-45">41 - 45</option> 
                      <option value="46-50">46 - 50</option> 
                      <option value="Above 50">Above 50</option>                                        
                    </select> 
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                   Gender
                </td>
                <td  style="font-family: Verdana, Arial, Helvetica, Bitstream Vera Sans;font-size: 12px;" align="left">
                    <input type="radio" id="gender" name="gender" value="M" checked="checked">&nbsp;Male</input>&nbsp;&nbsp;
                    <input type="radio" id="gender" name="gender" value="F">&nbsp;Female</input>
                </td>
            </tr>   
            <tr>
                <td class="text" align="left">
                    Phone
                </td>
                <td class="text" align="left">
                    <input type="text" name="phoneNumber" id="phoneNumber" />
                </td>
            </tr>             
            <tr>
                <td class="text" align="left">   
                	Profession             
                </td>
                <td class="text" align="left">
                	<select name="profession" id="profession" style="width:72%">
                      <option value="NULL">--Select--</option>
                	  <option value="Student">Student</option>
                	  <option value="Academician">Academician</option>
                	  <option value="Others">Others</option>                    
                    </select>                  
              </td>    
            </tr>
            <tr>
                <td class="text" align="left">
                    College
                </td>
                <td class="text" align="left">
                    <input type="text" name="college" id="college" />
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                    Subject
                </td>
                <td class="text" align="left">
                    <input type="text" name="specialization" id="specialization" />
                </td>
            </tr>  
           
                         
            <tr>
                <td class="text" align="left">
                    Country
                </td>               
                <td class="text" align="left">
                	<select name="country" id="country" style="width:72%">
                    	<option value="NULL">--Select--</option>
                        <option value="India">India</option> 
                       	<option value="Australia">Australia</option>                        
                       	<option value="Belgium">Belgium</option>                        
                       	<option value="Canada">Canada</option> 
                       	<option value="China">China</option> 
                        <option value="Finland">Finland</option>                        	
                       	<option value="France">France</option> 
                       	<option value="Germany">Germany</option>                        
                       	<option value="Ireland">Ireland</option>             
                        <option value="Italy">Italy</option> 
                        <option value="Japan">Japan</option> 
                        <option value="Kuwait">Kuwait</option>
                        <option value="Netherlands">Netherlands</option>
                        <option value="New Zealand">New Zealand</option>                        
                        <option value="Oman">Oman</option>  
                        <option value="Singapore">Singapore</option>                  
                        <option value="Sweden">Sweden</option>                       
                        <option value="Switzerland">Switzerland</option> 
                        <option value="United States">United States</option> 
                        <option value="United Kingdom">United Kingdom</option> 
                        <option value="Other">Other</option> 
                    </select> 
                </td>                
            </tr>
            <tr>
                <td class="text" align="left">
                    State
                </td>
                <td class="text" align="left">
                    <input type="text" name="state" id="state" />
                </td>
            </tr>                                  
            <tr>
                <td colspan="2">&nbsp;</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;
                    <input type="reset" value="Cancel" id="cancel" name="cancel" style="width:55px" />&nbsp;&nbsp;
                    <input type="submit" value="Register" id="register" name="register" style="width:65px" onClick="return registrationFormValidation(regForm);" />  
                </td>
            </tr>
		</table>
		</td>
		<td width="450px"></td>
     </tr>
     <input type="hidden" name="adminRegister" id="adminRegister" value="1"/>
</table>
</form>
</div>
</body>
</html>