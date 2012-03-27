
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<html>
<head>
<link rel="stylesheet" title="Style CSS" href="css/our.css" type="text/css" media="all" />
</head>

<script type="text/javascript" src="ew.js"></script>
<script type="text/javascript">
<!--
function EW_checkMyForm(EW_this) {
	if (!EW_hasValue(EW_this.username, "TEXT" )) {
		if  (!EW_onError(EW_this, EW_this.username, "TEXT", "Please enter user ID"))
			return false;
	}
	if (!EW_hasValue(EW_this.password, "PASSWORD" )) {
		if (!EW_onError(EW_this, EW_this.password, "PASSWORD", "Please enter password"))
			return false;
	}
	return true;
}
-->
</script>

<body>
<%@ include file="includes/header.jsp" %>

<form action="includes/login2.jsp" method="post" onSubmit="return EW_checkMyForm(this);">
<table border="0" cellspacing="0" cellpadding="4" width="849">
	<tr>
		<td width="388" align="center">
        <p align="right"><font color="#000080">Login ID(Email-ID)&nbsp;</font></td>

		<td width="445" align="center">
          <p align="left">
        <input name="username" size="34" value="" style="float: left"></p>
        </td>
	</tr>
	<tr>
		<td width="388" align="center">
        <p align="right"><font color="#000080">Password&nbsp;&nbsp;</font></td>

		<td width="445" align="center">
          <p align="left">
        <input type="password" name="password" size="20" style="float: left"></p>
        </td>
	</tr>
	<tr>
		<td colspan="2" align="center" width="841"><input type="submit" name="submit" value="Login" style="background-color:Lightgrey">
        </td>
	</tr>
</table>

</form>
</body>
</html>
