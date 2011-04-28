<!-- 
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 * Author: Anshul Agarwal

 -->
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="in.ac.dei.mhrd.omr.*"%>
<%@ page import="in.ac.dei.mhrd.omr.img.*"%>


<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Online OMR Evaluation System</title>	
    <script type="text/javascript">
    	function validate()
    				{
    					var q;
    					var noq=document.getElementById("noq").value;
    					var flag=1;
    					var mainFlag=0;
    					var total="";
    					var i=1;
    					var j=1;
    						
    					for(i=1;i<=noq;i++)
    						{
    						q="q"+i;
    						flag=1;
    						for(j=0;j<4;j++)
    							{
    							var ans=document.getElementsByName(q);
    							if(ans[j].checked)
    								{
    								flag=0;
    								break;
    								}
    							}
    							if(flag==1)
    								{
    								mainFlag=1;
    								total=total+q+"\n";
    								}
    						}
    		if(mainFlag==1)
    				{				
						 alert("Please mark the answers of following Questions:"+"\n"+total.toUpperCase());
						 return false;
						    	
					}		    	
						    	
    				}
    </script>

  </head>
  
  
  
  <body>
   <table width="100%">
  <tr><td>  <jsp:include page="header.jsp"></jsp:include></td></tr>
  <tr><td>	<hr width="100%"> </td></tr>
 <tr><td> <jsp:include page="Menu.jsp"></jsp:include></td></tr>
</table>
  	<html:form action="/validate" method="post">
  		
  				<%!
  		  					                                    int i;
  		  				  		  				  				int questionNo=1;
  		  				  		  				  				//variable for displaying question no.
  		  				  		  				  		
  		  				  		  				  				ArrayList<Integer> quesNo=new ArrayList<Integer>();
  		  				  		  				  				//declaring ArrayList of integer for question no 
  		  				  		  						  		
  		  				  		  				  				ArrayList<Integer> sectionNo=new ArrayList<Integer>();
  		  				  		  				  				//declaring ArrayList of Section no.
  		  				  		  				  				
  		  				  		  				  				HttpSession hs =null;
  		  				  		  						         
  		  				  		  				  				%>

  		  				  		  				  					<% try
  		  				  		  				  						{
  		  				  		  				  						hs = request.getSession();
  		  				  		  				  						hs.setAttribute("correctSheetMsg", "NA");
  		  				  		  				  						quesNo=(ArrayList<Integer>)hs.getAttribute("qno");
  		  				  		  				  						sectionNo=(ArrayList<Integer>)hs.getAttribute("secno");
  		  				  		  				     		   									}
  		  				  		  				  							catch(Exception e){
  		  				  		  				  			 					
  		  				  		  				  								}
  		  				%>	
  		 <font size="4"face="Arial" color="#000040"><b>  <bean:message key="label.testname"/>: </b><I> <%= (String)hs.getAttribute("testName")  %></I> </font>
  	<div align="right"><html:link href="correctScanSheet.jsp" title="upload via scanned sheet"><bean:message key="label.upload"/> </html:link></div>
  								
  		  				
  					<%  
  		   			Object element1,element2;
  		   			//element for asigning ArrayList values
  		
  		   			Iterator itr1=sectionNo.iterator();
  		   			Iterator itr2=quesNo.iterator();
  		
  					   	while(itr1.hasNext())
  		   					{
  		   					element1=itr1.next();
  		   			%>
  		   				
  		 <center><u>
  		 <font size="3" color="#000040" style="font-family: Arial;font-weight: bold">
  		 <bean:message key="label.section"/><%out.print(element1.hashCode()); %>
  		 </font>
  		 </u></center>
  		 <br>
  		<center>
  		<table align="center" width="40%" cellspacing="5">
  			   		<%
  		   			element2=itr2.next();
  		   		 		   		
  		   			for(i=1;i<=element2.hashCode();i++)
  					{
  					%>
  					
  					<tr><td><strong>Q<%=questionNo%></strong></td>
  					 
  				     <td><strong>  <bean:message key="label.a"/> </strong> </td><td><input type='checkbox' name=q<%=questionNo%> value="A"></td>
  				     <td><strong> <bean:message key="label.b"/> </strong> </td><td><input type='checkbox' name=q<%=questionNo%> value="B"></td>
  				     <td> <strong> <bean:message key="label.c"/></strong>  </td><td><input type='checkbox' name=q<%=questionNo%> value="C"></td>
  				      <td><strong> <bean:message key="label.d"/></strong> </td><td><input type='checkbox' name=q<%=questionNo%> value="D"></td>
  				</tr>
  				<% questionNo++;
  				}
  				%>
  			
  				</table>
  				<br><br>
  				<%
  			}
  				%></center>
  				
  		 <input type="hidden" name="noq" id="noq" value=<%=--questionNo %>>
  		 <center>
  		 <input type="submit" name="Submit" value="Submit" onclick="return validate();">
  		 <html:cancel></html:cancel>
  		 </center>
  		 <% questionNo=1; %>
  		 </html:form>
  </body>
</html>