

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
		<li><a href="${hostname}/home/index" target="_self">Home</a></li>
      		
		</ul>		
	
		</td>
  </tr>
  </table>

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
     
<div style="display:inline; font-size:16px; font-weight:bold;"><u>Edit User Profile</u></div>			
<br /><br />
     
            <table border="0" align="left" class="noBorderTable" width="100%">
    <tr>
      <td width="50%">
		<g:form action="saveUser" name="editUserForm" method="POST" >		
				    <table border="0" width="100%" >
				        <tr class="text">
				             <td class="text" align="left">User Name</td>
				             <td><label  class="text"  name="username" id="username" >${userMasterInstance.username }</label></td>
				        </tr>
				        <tr class="text">
				             <td class="text" align="left">Email id</td>
				             <td><label  class="text" name="emailId" id="emailId" >${userMasterInstance.emailId }</label></td>
				        </tr>
				       <!--<tr>
                             <td class="text" align="left">
                                  Password
                             </td>
                             <td class="text" align="left">
                                  <input type="password" name="paswd" id="paswd" value="${userMasterInstance.password }"/> <span style="color: maroon;font-weight: bold;">*</span>
                             </td>
                       </tr>  --> 
				        <tr class="text">
				             <td class="text" align="left">User Role</td>
				             <td>
								<g:select id="userRole" name='roleList.id' optionKey="id" optionValue="authority" from="${roleListUser}" value="${roleId }"  ></g:select> <span style="color: maroon;font-weight: bold;">*</span>
							</td>
				         </tr>
				          <tr>
                <td class="text" align="left">
                    University
                </td>
                <td class="text" align="left">
                      <g:select name='universityList.id' optionKey="id" optionValue="universityName" from="${universityList}"  value="${userMasterInstance.universityId }"></g:select> <span style="color: maroon;font-weight: bold;">*</span>
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
                    <input type="text" name="firstName" id="firstName" value="${userDetailsInstance.firstName }"/>
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                    Last Name
                </td>
                <td class="text" align="left">
                    <input type="text" name="lastName" id="lastName" value="${userDetailsInstance.lastName }"/>
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                   Age Group
                </td>
                <td class="text" align="left">
                    <g:select name="age"   from="${['0-15','16-20','21-25','26-30','31-35','36-40','41-45','46-50','Above 50']}" value="${userDetailsInstance.age }" />
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                   Gender
                </td>
                <td style="font-family: Verdana, Arial, Helvetica, Bitstream Vera Sans;font-size: 12px;" style="color:black;" align="left">
                   <input type="radio" id="gender" name="gender" value="M" <g:if test="${userDetailsInstance.gender=='M'}">checked </g:if>>&nbsp;Male</input>&nbsp;&nbsp;
                   <input type="radio" id="gender" name="gender" value="F" <g:if test="${userDetailsInstance.gender=='F'}">checked </g:if>>&nbsp;Female</input>
                </td>
            </tr>   
            <tr>
                <td class="text" align="left">
                    Phone
                </td>
                <td class="text" align="left">
                    <input type="text" name="phoneNumber" id="phoneNumber" value="${userDetailsInstance.phoneNumber }"/>
                </td>
            </tr>             
            <tr>
                <td class="text" align="left">   
                	Profession             
                </td>
                <td class="text" align="left">
                    <g:select name="profession"   from="${['Student','Academician','Others']}" value="${userDetailsInstance.profession }" />
              </td>    
            </tr>
            <tr>
                <td class="text" align="left">
                    College
                </td>
                <td class="text" align="left">
                    <input type="text" name="college" id="college" value="${userDetailsInstance.college }"/>
                </td>
            </tr>
            <tr>
                <td class="text" align="left">
                    Subject
                </td>
                <td class="text" align="left">
                    <input type="text" name="specialization" id="specialization" value="${userDetailsInstance.specialization }"/>
                </td>
            </tr>  
           
                         
            <tr>
                <td class="text" align="left">
                    Country
                </td>               
                <td class="text" align="left">
                    <g:select name="country"   from="${['India','Australia','Belgium','Canada','China','Finland','France','Germany','Ireland','Italy','Japan','Kuwait','Netherlands','New Zealand','Oman','Singapore','Sweden','Switzerland','United States','United Kingdom','Other']}" value="${userDetailsInstance.country }" />
                </td>                
            </tr>
            <tr>
                <td class="text" align="left">
                    State
                </td>
                <td class="text" align="left">
                    <input type="text" name="state" id="state" value="${userDetailsInstance.state }"/>
                </td>
            </tr>                                  
       
				             <!--<tr class="text">
				            <td class="text" align="left">Status</td>
				            <td>
				            <g:if test="${userMasterInstance.userStatus =='A'}">
				                 <g:set var="status" value="Active" />
				            </g:if>
				            <g:elseif test="${userMasterInstance.userStatus =='D'}">
				                 <g:set var="status" value="Deactive" />
				            </g:elseif>
				            <g:select name="userStatus"   from="${['Active','Deactive']}" value="${status }" />
                            </td>
				          </tr>-->
				          <tr>
                <td colspan="2">&nbsp;</td>
            </tr>
				          <tr class="text">
				            <td colspan="2" align="center">
				        	      <input type="submit" value="Submit" id="edit_submit" name="edit_submit"  onclick="return validateUser(editUserForm);" style="width:75px"/>
				            </td>
				          </tr>
				  </table>	
				 <input type="hidden" name="userId" id="userId" value="${userMasterInstance.id }"/>
           <input type="hidden" value="1" name="user"  />  
						</g:form>
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

</body>
</html>
 

