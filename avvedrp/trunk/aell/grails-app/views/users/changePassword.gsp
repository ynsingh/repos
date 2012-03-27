
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
<body> 
<div id="innerData">
<table border="0" width="100%">
     <tr>
         <td bgcolor="#FFFF99" class="tblAdminTemplate">${grailsApplication.config.change_password} </td>
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
						</g:form>
				
		</td>
		<td width="450px"></td>
     </tr>
</table>
</div>
</body>
</html>