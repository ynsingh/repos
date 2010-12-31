<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${request.contextPath} Chart Servlet Test Page - 1</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
</head>



<body>
This page contains a bunch of charts that we're using to test ${request.contextPath}:
<br><br>
<img border="1" src="http://chart.apis.google.com/chart?cht=bhs&chs=200x125&chd=s:HELo" alt="Where's Google?" />
<img border="1" src="${request.contextPath}/chart?cht=bhs&chs=200x125&chd=s:HELo" alt="Where's ${request.contextPath}?" />

</body>
</html>
