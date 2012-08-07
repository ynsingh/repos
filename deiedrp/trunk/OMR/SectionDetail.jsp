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
<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>

<%@page import="javax.sql.rowset.CachedRowSet"%>
<%@page import="com.sun.rowset.CachedRowSetImpl"%>
<%@page import="in.ac.dei.mhrd.omr.result.GraceMarks"%>

<%@page import="java.util.ArrayList"%>

<%@page import="in.ac.dei.mhrd.omr.dbConnection.Connect;"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <head>
    <html:base />
    
    <title>SectionDetail.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script Language="javascript">
			function exportToExcel()
				{
				var strCopy = document.getElementById("MyTable").innerHTML;
				window.clipboardData.setData("Text", strCopy);
				var objExcel = new ActiveXObject ("Excel.Application");
				objExcel.visible = true;
				var objWorkbook = objExcel.Workbooks.Add;
				var objWorksheet = objWorkbook.Worksheets(1);
				objWorksheet.Paste;
				}
				function visibility()
				{
				var tableId=document.getElementById("wrongQuestion");
				if(tableId.style.display=='none')
				{
				tableId.style.display='block';
				tableId.style.visibility='visible';
				}
				else tableId.style.display='none';
				}
		</script> 
  </head>
  
  <body>
   <table width="100%">
  <tr><td>  <jsp:include page="header.jsp"></jsp:include></td></tr>
  <tr><td>	<hr width="100%"> </td></tr>
 <tr><td> <jsp:include page="Menu.jsp"></jsp:include></td></tr>
</table>

        <%
        int alt=1;// color alteration
        int totalSection;				
        // for no. of section for a corresponding test Id
        
        int noOfQuestion;
        //total no. of questions
        
        ArrayList<Float> discardMarks=new ArrayList<Float>();
        float marksEachQuestion=-1;
        float graceMarks=0;
        float sectionMarks=0;
		
        float totalMarks=0;
        int rollNo;
       // HttpSession sessionbj = request.getSession();
        int testid = (Integer)request.getAttribute("testid");
                session.setAttribute("testid", testid);
        
        String path=(String)request.getAttribute("imagePath");
                session.setAttribute("imagePath", path);
        
    		    int i = 1;
  				int j = 1;

  				String query1;
  				//query to retrieve data from attempt_info

  				String query2;
  				//query to get total section from testheader

  				String query3;
  				//query for retrieving no. of questions per section
				
				String query4;
				String query5;
  				String fileName;
  				//for storing file name corresponding to roll no.
        
        //Result Set
        ResultSet rsTestHeader=null;
        ResultSet rsTotalSection=null;
        ResultSet rsAnswer=null;
        ResultSet rsMarks=null;
        CachedRowSet crs=null;
        CachedRowSet crs1=null;
        PreparedStatement ps=null;
        Connection con = null;
        %>
        <%
        	GraceMarks grc=new GraceMarks();
                graceMarks=grc.computeGraceMarks(testid);
                discardMarks=grc.computeDiscardMarks(testid);
                try{
                 con = Connect.prepareConnection();
                crs=new CachedRowSetImpl();
                crs1=new CachedRowSetImpl();
        	 ps = con.prepareStatement("select Test_name,TestNo,Conduct_date from testheader where TestId="+testid);
                  
        		   rsTestHeader=ps.executeQuery();
        		    rsTestHeader.next();
        %>
         <div align="center" style="color:darkblue;font-weight: bold;"><bean:message key="msg.Result"/> </div>
  <div align="right"><u><a href="ExlResult.jsp"><bean:message key="link.exporttoexcel"/></a></u></div>
  <table style="color: darkblue">
  	<tr><td><font face="Arial" color="#000040"><bean:message key="label.testid"/> </font></td><td> <%=rsTestHeader.getObject("TestNo") %></td></tr>
  	<tr><td><font face="Arial" color="#000040"><bean:message key="label.testname"/></font></td><td> <%=rsTestHeader.getObject("Test_name")%> </td></tr>
  	<tr><td><font face="Arial" color="#000040"><bean:message key="label.testdate"/></font></td><td> <%=rsTestHeader.getDate("Conduct_date") %>  </td></tr>
  	<tr><td><font face="Arial" color="#000040"><bean:message key="label.gracemark"/></font></td><td><%=graceMarks%></td></tr>
  </table>
		  <%
  					totalSection=(Integer)request.getAttribute("totalSection");
  					session.setAttribute("totalSection", totalSection);
					query1 = "select distinct(FileName),RollNo from attempt_info where TestId="+testid;
					PreparedStatement psRno=con.prepareStatement(query1);
  					rsTotalSection = psRno.executeQuery();
  					crs.populate(rsTotalSection);		
  					
  			%>
  <a href="javascript:visibility()" title="show Question detail">Misprinted Questions</a>
  <table style="color:darkblue;font-family: arial;display: none" id="wrongQuestion" border="1">
  <logic:empty name="wrongDetail">
  <tr><td>No misprinted Question</td></tr>
  </logic:empty>
  <logic:notEmpty name="wrongDetail">
  
  <tr>
  <%
  int sec;
  String section="0";
  %>
  </tr>
  	<logic:iterate id="wrong" name="wrongDetail">
  		<logic:notEqual name="wrong" property="sectionNo" value="<%=section%>">
  		<tr><td>Section No:<bean:write name="wrong" property="sectionNo"/></td></tr>
 	 	<tr><td>Q.No.</td><td>Status</td></tr>
 	 	<%sec=Integer.parseInt(section)+1;
 	 	  section=String.valueOf(sec); 
	  	%>
  	   </logic:notEqual>
  		<tr>
		 <td><bean:write name="wrong" property="questionNo"/></td>
		 <td><bean:write name="wrong" property="status"/></td>
		 </tr>
		<html:hidden property="lastElement" value="'<%=section%>'"></html:hidden>
	</logic:iterate>
	 	</logic:notEmpty>
   </table>     
  		
		<span id="MyTable">
			<table border="2" align="center" cellspacing="0" bordercolor="black" style="font-family:TimesNewRoman;font-weight: bold">
    			<tr>
    			<td></td><td></td>
    			
    			
    	<%
    			for (j = 1; j <= totalSection; j++) {
    	%>
    			<td colspan="4" align="center" valign="middle">
    			<bean:message key="label.section"/>: 
    			<%out.print(j);%>
    			(
    			<%
    			//retrieving and calculating marks
    			//out.println("3 b4 sec detail");
    			 query4="select No_of_question,Marks_each_question from testsectiondetail where Section_number="+j+" and TestId="+testid;
    			 PreparedStatement psTestDetail=con.prepareStatement(query4);
    			 rsMarks=psTestDetail.executeQuery(query4);
    			 rsMarks.next();
    			 noOfQuestion=rsMarks.getInt(1);
    			 marksEachQuestion=rsMarks.getFloat(2);
    			 if(discardMarks.size()>0)
    			 out.print("M.M. :"+((noOfQuestion* marksEachQuestion)-discardMarks.get(j-1)));
    			 else
    			 out.print("M.M. :"+((noOfQuestion* marksEachQuestion)));
    			%>)
    			</td>
    	<%
    		}
    		rsMarks.close();
    	%>
    			<td></td>
    			<td></td>
    			</tr>
    			
    			<tr bgcolor="lightblue">
    			<td><bean:message key="label.sno"/></td><td><bean:message key="label.rollno"/></td>
    			<%
    				for (j = 1; j <= totalSection; j++) {
    			%>
    			<td>*<bean:message key="label.ca"/></td>
    			<td>*<bean:message key="label.wa"/></td>
    			<td>*<bean:message key="label.ua"/></td>
    			<td><bean:message key="label.marks"/></td>
    			<%
    				}
    			%>
    			
    			<td><bean:message key="label.total"/> </td>
    			<td><br><br></td>
    			</tr>
    		
		<%
			while (crs.next())
			 			{
						fileName = crs.getString("FileName");
						String Imgpath = path+fileName;
						rollNo = crs.getInt("RollNo");
							if(alt%2!=0)
							{alt++;
		%>
    			
    			<tr bgcolor="#c0c0c0"><%}else{alt++; %>
    			<tr bgcolor="white">
    			<%
    			 }
    			%>
    			<td>
    			<%
    				out.print(i++);
    			%>
    			</td><td><a href="displayResponse.do?filename=<%=Imgpath%>&testid=<%=testid %>"><%=rollNo%></a></td>
    	  <%
				ArrayList<Float> tempSectionMarks=new ArrayList<Float>();
    	     	   	for (j = 1; j <= totalSection; j++)
    	    	 {	
					
    	    			query3 = "select Correct_attempt,wrong_attempt,unattempt from attempt_info where TestId="+testid+" and SectionNumber="
    	    					+ j + " and RollNo="+rollNo+" and FileName='"+fileName+"'";
    	    					PreparedStatement psAttemptInfo=con.prepareStatement(query3);
    	    								
    	       			query4="select Marks_each_question,Neg_Marks from testsectiondetail where Section_number="+j+" and TestId="+testid;
    	                 PreparedStatement psMarks=con.prepareStatement(query4);
    	    			rsMarks=psMarks.executeQuery();
    	    			crs1.populate(rsMarks);
    	    			rsAnswer = psAttemptInfo.executeQuery();
    	    		while(rsAnswer.next()&&crs1.next())
    	    		{
    	  %>
    			<td>
    			<%
    			out.print(rsAnswer.getInt("Correct_attempt"));
    				if(discardMarks.size()>0)
    				{
    				sectionMarks=sectionMarks+rsAnswer.getInt("Correct_attempt")*crs1.getFloat("marks_each_question");
    				}
    				else
    				{
    				sectionMarks=sectionMarks+rsAnswer.getInt("Correct_attempt")*crs1.getFloat("Marks_each_question");
    				}
    		    	%>
    		    </td>
    			<td>
    			<%
    				
    				out.print(rsAnswer.getInt("Wrong_attempt"));
    				sectionMarks=sectionMarks-(rsAnswer.getInt("wrong_attempt")*Math.abs(crs1.getFloat("Neg_marks")));
    			 %>
    			</td>
    			<td>
    			<%
    				out.print(rsAnswer.getInt("unattempt"));
    			%>
    			</td>
    			<td>
    			<%
    			  out.print(sectionMarks);
				  tempSectionMarks.add(sectionMarks);
				  
    			  totalMarks=totalMarks+sectionMarks;	 
				  
    			%>
    			</td>
    			
    			<%
    			  	}
					
    			  	sectionMarks=0;
    			  	crs1.close();
    			  	}
					
					Float tempTotalMarks=0.0f;
					for(int m=0;m<tempSectionMarks.size();m++){
						tempTotalMarks=tempTotalMarks+tempSectionMarks.get(m);
					}
					
					
					for(int n=0;n<tempSectionMarks.size();n++){
					try{
						
    	                 PreparedStatement updateMarks=con.prepareStatement("update student_result_info set section_marks=?, total_marks=? where testId=? and RollNo=? and sectionNumber=? and FileName=?" );
						 updateMarks.setFloat(1,tempSectionMarks.get(n));
						 updateMarks.setFloat(2,(tempTotalMarks+graceMarks));
						 updateMarks.setInt(3,testid);
						 updateMarks.setInt(4,rollNo);
						 updateMarks.setInt(5,n+1);
						 updateMarks.setString(6,fileName);
						 updateMarks.executeUpdate();
						 }
						 catch(Exception ex){
						 System.out.println("hii catch exception "+ex);
						 }
					}
    			  	rsAnswer.close();
				%>
    			
    			
    			<td><%=totalMarks+graceMarks %></td>
    			<td><%=fileName%></td>
    			</tr>
    	
    	       <%
					
    	    		totalMarks=0;
    	    		}
    	    		i=1;
    	    
    	    	%>
    	</table>
    	</span>
    	<center><font style="color:blue;font-weight: bold">*CA=Correct Attempt *WA=Wrong Attempt *UA=Unattempt</font></center>
    	<div align="center"><input type="button" value="Back" onclick="history.back();"/></div>
    	<%
    		} catch (Exception e) {
    				
    			}finally{
    			Connect.freeConnection(con);
    			}
    	%>
  </body>
</html:html>
