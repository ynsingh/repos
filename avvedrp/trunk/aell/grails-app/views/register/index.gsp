

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>${grailsApplication.config.page_title}</title> 
<link rel="shortcut icon" type="image/x-icon" href="${hostname}/images/favicon.ico">
<link rel="stylesheet" type="text/css"  href="${hostname}/css/home_styles.css">
<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'applicationValidation.js')}"></script>
<style type="text/css">
.text {
			font-family: Verdana, Arial, Helvetica, Bitstream Vera Sans;
	        font-size: 12px;
			color:black;
			vertical-align:middle;
			font-weight:bold;
			padding:0.7%;
		}

</style>
</head>

<body background="${hostname}/images/bg_tile.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0"  >
<table id="Table_01" width="1027" border="0" cellpadding="0" cellspacing="0">
  <tr>
		<td colspan="16">
	        <img  align ="left" style="height:105px;margin-left:0%;" src="${hostname}/images/header-left.jpg"/>
			<img style="height:105px;width:18.5%;margin-right:-480px;"  src="${hostname}/images/header-tile.jpg"  alt="">
			 <img src="${hostname}/images/header-right.jpg" width="432"  align ="right" style="height:105px;"/>
	</tr>

	<tr>
	
	
		<td height="30px" style="background:url(${hostname}/images/top_sec_without_black.jpg)">
		<ul id="menuTop">
		<li><a href="${hostname}/login/auth" target="_self">Login</a></li>	
      		
		</ul>		
	
		</td>
  </tr>
  </table>

 <form action="registerUser" name="regForm" method='POST'>
 <table border="0" cellpadding="0" cellspacing="0" height="570" width="1027" bgcolor="white">
 <tr valign="top">
   <td><img src="${hostname}/images/mainpage_blue_slice1_05.jpg" width="40" height="580" alt="">
   </td>
   <td>
   <table width="900"  border="0">
   <tr><td>
   <g:if test="${flash.message}">
         <div class="message" ><img src="${hostname}/images/tick.gif" style="background-color: blue;" title="Success" height="20" width="20">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
         <div class="error"><img src="${hostname}/images/wrong.gif" title="Failure" height="20" width="20">${flash.error}</div>
    </g:if>
   </td></tr>
   <tr><td>
      </br>
      <h1> ${grailsApplication.config.register}</h1><br />
     
<div style="display:inline; font-size:16px; font-weight:bold;"><u>Enter your details</u></div>			
<br /><br />
     
            <table border="0" align="left" class="noBorderTable" width="100%">
    <tr>
      <td width="50%">
		<table border="0" align="left" class="noBorderTable" width="100%" >    
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
                    <input type="password" name="paswd" id="paswd" /> <span style="color:maroon;font-weight: bold;">*</span>
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                    Confirm Password
                </td>
                <td class="text" align="left">
                    <input type="password" name="confPaswd" id="confPaswd" /> <span style="color:maroon;font-weight: bold;">*</span>
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
             <tr >
                <td class="text" align="left">
                    University
                </td>
                <td class="text" align="left" >
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
                <td  style="font-family: Verdana, Arial, Helvetica, Bitstream Vera Sans;font-size: 12px;padding-left:2%;" align="left">
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
      <td width="2%" background="images/seperator_line.png" style="background-repeat:no-repeat;">&nbsp;</td>
      <td class="text" valign="top"> 
    </td> 
    </tr> 
</table>    	   
     </td>
   </tr>
 </table>
 </td>
 <td>
   <img align="right" src="${hostname}/images/mainpage_blue_slice1_08.jpg" width="45" height="580" alt="">
 </td>
 </tr>
  </table>
<table width="1027" border="0" cellpadding="0" cellspacing="0">
     <tr>
		<td align="center" valign="top"  width="1024" height="44" style="background:url(${hostname}/images/mainpage_blue_slice1_30.jpg); color:#FFF; font-size:9px">
               <div style="font-size:11px; padding-top:10px" align="center"><br/>Developed by Amrita University under ERP, NME ICT, MHRD </div>
	    </td>
     </tr>
</table>
</form>
</body>
</html>
 

