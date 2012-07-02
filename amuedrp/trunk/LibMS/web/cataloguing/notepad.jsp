

 <%@page contentType="text/html" import="com.myapp.struts.utility.*,java.io.*" trimDirectiveWhitespaces="true" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%
	// fetch the file
	String filename = AppPath.getPropertiesFilePath()+session.getAttribute("file");
	response.setContentType("application/octet-stream");
	response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + '"');
	java.io.FileInputStream fileInputStream = new java.io.FileInputStream(filename);
	 
	int i;
	while ((i=fileInputStream.read()) != -1)
	{
	    out.write(i);
	}

	fileInputStream.close();
	out.close();
        session.removeAttribute("file");
        session.removeAttribute("type");
	%>
