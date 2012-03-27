

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
<script type="text/javascript">
    function validatePassword(){
    	
    	if(document.getElementById("oldPassword").value == ""){
    		alert("Please Enter New Password");
		    document.getElementById("oldPassword").focus();
		    return false;
    	}
	    
    	 if(document.getElementById("newPassword").value == ""){
    		alert("Please Enter New Password");
		    document.getElementById("newPassword").focus();
		    return false;
    	}
	    	 if(document.getElementById("confirmNewPassword").value == ""){
    		alert("Please Enter Confirm Password Field");
		    document.getElementById("confirmNewPassword").focus();
		    return false;
    	} 
    	if(document.getElementById("newPassword").value !==document.getElementById("confirmNewPassword").value){
    	alert("New Password and confirm Passsword  cannot match");
    	document.getElementById("newPassword").value ="";
    	document.getElementById("confirmNewPassword").value ="";
		    document.getElementById("newPassword").focus();
		    return false;
    	}
    	    	   	return true;
    
    }
    </script>
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
     
<div style="display:inline; font-size:16px; font-weight:bold;"><u>${grailsApplication.config.change_password}</u></div>			
<br /><br />
     
            <table border="0" align="left" class="noBorderTable" width="100%">
    <tr>
      <td width="50%">
		<g:form action="updatePassword">
       <table border="0" class="noBorderTable" align="left" width="100%">    
				<input type="hidden" name="id" value="${avlUserMasterInstance.id}" />
					   <tr class="text">
				             <td class="text" align="left">User Name</td>
				             <td><label  class="text"  name="username" id="username" >${avlUserMasterInstance.username }</label></td>
				        </tr>
				        

				 <tr class="text">
						 <td class="text" align="left">Old Password</td>
						<td valign="top" >
							<input type="password" id="oldPassword" name="oldPassword" />
						</td>
					</tr>
					<tr class="text">
						 <td class="text" align="left">New Password</td>
						<td valign="top" >
							<input type="password" id="newPassword" name="newPassword" />
						</td>
					</tr>
					<tr class="text">
						 <td class="text" align="left">Confirm Password</td>
						<td valign="top" >
							<input type="password" id="confirmNewPassword" name="confirmNewPassword" />
						</td>
					</tr>
              <tr >
            <td></td>
                <td align="center">
                    <input type="reset" value="Reset" id="reset" name="reset" style="width:55px" />&nbsp;&nbsp;
                    <input type="submit" value="Update" id="update" name="update" style="width:65px" onClick="return validatePassword();" />  
                </td>
            </tr>				</table>
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
 

