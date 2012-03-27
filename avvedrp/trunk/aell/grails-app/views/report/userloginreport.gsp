
<%@ page import="java.lang.String" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

 <head>
  <g:javascript library="prototype" />
 
 <title>${grailsApplication.config.page_title}</title> 
 <link rel="shortcut icon" type="image/x-icon" href="${hostname}/images/favicon.ico">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="contentAdmin" />
<script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery.tablednd_0_5.js')}"></script>
<script type="text/javascript">
function getUsers()
{
		document.statusSelectForm.submit();			
}
</script>
  </head>
<body>
<div id="innerData">
<table border="0" width="100%">
     <tr>
         <td bgcolor="#FFFF99" class="tblAdminTemplate">${grailsApplication.config.current_login_details} </td>
     </tr>
      	 <tr>
	 	 <td colspan="2">
	 	      <g:if test="${flash.message}">
                  <div class="message"><img src="${hostname}/aell/images/tick.gif" title="Success" height="20" width="20">${flash.message}</div>
              </g:if>
              <g:if test="${flash.error}">
                  <div class="error"><img src="${hostname}/aell/images/wrong.gif" title="Failure" height="20" width="20">${flash.error}</div>
              </g:if>
			  <table border="0"><tbody><tr><td></td></tr></tbody></table>
			  <g:form action="updateSequence" name="sequenceForm" method="post" >
				  <input type="hidden" name="sequenceOrder" id="sequenceOrder"/>
			  </g:form>
			  <g:form action="index" name="contentForm" method="post" >
			  <table id="tableDrag" border="1" width="100%" class="myTable">	
				   <tbody>				       
				       <tr><b><font size="2">Users currently logged into the system</font></b></tr>
				       <br></br>
				       <tr style="cursor: move; background-color: rgb(204, 227, 255);" bgcolor="#cce3ff">       
				         <td style="text-align:center;font-family:Verdana, Geneva, sans-serif;font-size:12px;font-weight:bold;color:green;width:1%;">SNo</td>
				         <td style="text-align:center;font-family:Verdana, Geneva, sans-serif;font-size:12px;font-weight:bold;color:green;width:5%;">User Name</td>
				         <td style="text-align:center;font-family:Verdana, Geneva, sans-serif;font-size:12px;font-weight:bold;color:green;width:3%;">Role</td>
				         <td style="text-align:center;font-family:Verdana, Geneva, sans-serif;font-size:12px;font-weight:bold;color:green;width:5%;">Email</td>
				         <td style="text-align:center;font-family:Verdana, Geneva, sans-serif;font-size:12px;font-weight:bold;color:green;width:5%;">Login Time</td>
				       </tr>
				   </tbody>
				   <tbody>
					    <g:each in="${logdetails}" status="i" var="log_details">     
						<tr class="text" id="${log_details.id}" style="cursor: move; ">
						    <td style="text-align:center;">${i+1 }</td>
						    <td style="text-align:left;"><b>${log_details.username}</b></td>
						    <td style="text-align:left;"><b>${log_details.role}</b></td>
						    <td style="text-align:left;"><b>${log_details.email}</b></td>
						    <td style="text-align:left;"><b>${log_details.logintime}</b></td>
						</tr>
					    </g:each>
				  </tbody>
		    </table>
		    </g:form>
		</td>
	 </tr>
</table>
<div id="editUserForm">		 
</div>
</div>
</body>
</html>