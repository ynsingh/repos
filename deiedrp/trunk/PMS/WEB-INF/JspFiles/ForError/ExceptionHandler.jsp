<%@ page isErrorPage="true" import="java.io.*" %>
<html>
<head>
	<title>Exceptional Even Occurred!</title>
	<style>
	body, p { font-family:Tahoma; font-size:10pt; padding-left:30; }
	pre { font-size:8pt; }
	</style>
	<script language="JavaScript" type="text/javascript">
	function show()
	{
	alert("hello");
	}
	</script>
</head>
<body onload="show();">

<%="exception is comimg" %>
</body>
</html>