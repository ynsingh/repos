<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@page import="in.ac.dei.mhrd.omr.img.RotateImg"%>
<%@page import="java.lang.reflect.Array"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
    <title>My JSP 'ValidateAnswer.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <script type="text/javascript">
    			function validate()
    				{
    				  var whereTo= confirm("Do you really want to discard the answers?");
 					if (whereTo== true)
 						{
 						window.history.back();
 						  return true;
 						}
 					else
 							{
  							return false;
 							 }
						return false;
    				}
    </script>
  </head>
  
  <body>
  
  	<%	  	 int questionSequence=1;
         	 //for the sequence of question for displaying
          
   	int i=0;	
	byte[] answer;    
    ArrayList<Byte> answerByte=(ArrayList<Byte>)session.getAttribute("byteTransffer"); 
   // System.out.println(answerByte.size());
         	answer=new byte[answerByte.size()];
      		for(Byte bt:answerByte)
      			{
      			answer[i]=bt;
      			i++;
      			}
      		i=0;	
     RotateImg ri=new RotateImg(); 		
	ArrayList<String> answerString=ri.convertToString(answer);
			
	   %>
      
        <html:form action="addAnswer" method="post">
          <center>
          	<strong><font face="Arial" color="#000040"><bean:message key="label.verify"/></font>
          	<br>
          	<table width="15%" border="1">
          		
          		 <tr><td><strong><font face="Arial" color="#000040"><bean:message key="label.ques"/> </font></td><td><strong><font face="Arial" color="#000040"><bean:message key="label.ans"/></font></td></tr>
       			<%
       			for(String st:answerString)
				{
				%><tr><td><%=questionSequence%></td><td><%=st%></td></tr>	
       			 <%questionSequence++;
       			 } %>
       			</table>
       			<%questionSequence=0; %>
       			<font size="3" color="blue" face="verdana"><bean:message key="label.submit"/></font> 
       					<input type="submit" name="button" value="YES">
       					<input type="button" name="button" value="NO" onclick="return validate();"> 
           </center> 
          
       </html:form> 
  </body>
</html>
    