<html>

<head>
<script type="text/javascript">
function noBack(){
 window.history.forward(); }
</script>
</head>
<title><g:message code="default.GrantManagementSystem.title"/></title>  

<frameset frameborder="NO"  border="0" style="overflow:hidden" rows="20%,76%,4%">
	<frame src="../grantAllocation/top" name="top" scrolling="no" noresize="noresize" style="z-index:-1;overflow:hidden; height:100%; width:100%;">
		<frameset frameborder="NO" border="3"  cols="160px,*">
			<frame src="../grantAllocation/menu" name="left">
			<g:if test="${(role == 'ROLE_FINANCE')}">
    			<frame src="../expenseRequestEntry/financeLogin" name="right">
	    	</g:if>
			<g:else>
	    		<frame src="../projects/list" name="right">
	    	</g:else>
			
		</frameset>
		<frame src="../grantAllocation/bottom.gsp" name="top" scrolling="no" noresize="noresize" style="z-index:-1;overflow:hidden; height:100%; width:100%;">
</frameset> 
</html>