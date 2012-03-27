<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page session="true" import="java.util.*" %>


<html>
    <head>
    	<link rel="stylesheet" type="text/css" href="style.css" title="style" />
        <!--<script type="text/javascript" src="includes/roundCorner.js"></script>
        <script>
        DD_roundies.addRule('.rightContainer', '10px');
        </script>-->
    </head>
    <body>
    	<!-- div for superbar -->
    	<%@ include file="includes/header.jsp" %>
        <!-- end of superbar -->
        
        <div class="content">
               
        <!-- div news container-->
        <div class="leftContainer">
        <p><h2 style="margin-bottom:1em; padding:1em"><a href="listevents.jsp?action=1">CURRENT EVENTS</a></h2></p>
        <p><h2 style="margin-bottom:1em; padding:1em"><a href="listevents.jsp?action=0">PAST EVENTS</a></h2></p>
        
        </div>  
        <!-- End newsContainer-->
        
        <!-- div right info-->
        <div class="rightContainer">
          <%@ include file="includes/rightcontainer.jsp" %> 	
          </div>  
          <!-- End rightInfo--> 
        <!-- end div rightinfo-->
             	
        <!-- div maincontainer-->
        <div class="centerContainer">
       	<div style="padding:1em; color:#666;"><strong style="font-size:large;">EVENTS</strong></h2></div>	
                    
            			<!-- JSP code to list events-->
			<%
			Calendar c = Calendar.getInstance();
          	java.util.Date d = c.getTime();
		  	java.sql.Date currDate = new java.sql.Date(d.getTime());
				
		if((request.getParameter("action") == null)||(Integer.parseInt(request.getParameter("action")) == 1))
		{
        
        	 quer = "select * from calendar1 where date>='"+currDate+"'&&flag=1 order by sdate";
		}
		else if(Integer.parseInt(request.getParameter("action")) == 0)
		{
			 quer = "select * from calendar1 where date<'"+currDate+"'&&flag=1 order by sdate";	
		}
		
        		       
        if(request.getParameter("date")==null)
		{
			rs = stmt.executeQuery(quer);
        	while(rs.next())
        	{
		%>	
        
				<div class="mainInfo">
        			<table border="0" width="100%">
  						<tr>
    						<td width="60%"><a style="font-size:15px;" href="listevents.jsp?date=<%=rs.getDate("date")%>"><%=rs.getString("title")%></a></td>
                            <td align="right" style="font-size:11px; vertical-align:top"><% 
							if(!rs.getDate("sdate").equals(rs.getDate("edate")))
							{
							%>
                            <%=rs.getDate("sdate")%> <strong>To</strong> <%=rs.getDate("edate")%>
                            <%
							}
							else if(rs.getDate("sdate").equals(rs.getDate("edate")))
							{
							%><strong>On</strong> 
                            <%=rs.getDate("sdate")%>
							<%
							}
						%></td>
 						</tr>
                        </table>
                       	<p><%String message = rs.getString("message");
							String message1=new String();
							if(message.length()>150)
							{
							message1 = message.substring(0,150);
							message1=message1.concat(" ......");
							}else
							message1=message;%><%=message1%></p>
 						
	    		</div>
 
		<%
			}
				stmt.close();
        		con.close();
		}
		else if (request.getParameter("date")!=null)
		{
			quer = "select * from calendar1 where date='"+request.getParameter("date")+"'";	
			rs = stmt.executeQuery(quer);
			rs.next();
			%>
            <div class="mainInfo">
            <table border="0" width="100%">
  						<tr>
    						<td width="60%"><a style="font-size:15px;" href="listevents.jsp?date=<%=rs.getDate("date")%>"><%=rs.getString("title")%></a></td>
                            <td align="right" style="font-size:11px; vertical-align:top"><% 
							if(!rs.getDate("sdate").equals(rs.getDate("edate")))
							{
							%>
                            <%=rs.getDate("sdate")%> <strong>To</strong> <%=rs.getDate("edate")%>
                            <%
							}
							else if(rs.getDate("sdate").equals(rs.getDate("edate")))
							{
							%><strong>On</strong> 
                            <%=rs.getDate("sdate")%>
							<%
							}
						%></td>
 						</tr>
                        </table>
                <p><%=rs.getString("message")%></p>
			</div>
			<%
		}
		
		%>
        <!-- end JSP code -->
</div>
        <!-- end div maincontainer -->
        
        </div>
       </body>
</html>
        
