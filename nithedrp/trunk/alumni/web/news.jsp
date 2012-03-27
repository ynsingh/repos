<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page session="true" import="java.util.*" %>


<html>
    <head>
    	<link rel="stylesheet" type="text/css" href="style.css" title="style" />
    </head>
    <body>
    	<!-- div for superbar -->
    	<%@ include file="includes/header.jsp" %>
        <!-- end of superbar -->
        
        <div class="content">
               
        <!-- div news container-->
        <div class="leftContainer">
        </div>  
        <!-- End newsContainer-->
        
        <!-- div right info-->
        <div class="rightContainer">
          <%@ include file="includes/rightcontainer.jsp" %> 	
          </div>  
        <!-- End rightInfo--> 
        
             	
        <!-- div centercontainer-->
        <div class="centerContainer">
       	<div style="padding:1em; color:#666;"><strong style="font-size:large;">NEWS/ANNOUNSMENTS</strong></h2></div>	
                    
            			<!-- JSP code to list events-->
			<%
			
		if(request.getParameter("newsid") == null)
		{
			quer = "select * from divtext where divtype = 'news' ";
			rs = stmt.executeQuery(quer);
        	while(rs.next())
        	{
				 if(!rs.getString(3).equals(""))
			  	{
				  %>
                    <div class="mainInfo">
                        <a style="font-size:15px;" href="news.jsp?newsid=<%=rs.getString("divid")%>"><%=rs.getString("title")%></a>
                        <p><%String news3 = rs.getString("message");
                        String news4=new String();
                        if(news3.length()>150)
                        {
                            news4 = news3.substring(0,150);
                            news4=news4.concat(" ......");
                        }else
                            news4=news3;%><%=news4%></p>
                    </div>
 
		<%
				}
			}
		}
		else if (request.getParameter("newsid")!=null)
		{
			quer = "select * from divtext where divtype = 'news' ";
			rs = stmt.executeQuery(quer);
		   	while(rs.next())
        	{
				if(!rs.getString("divid").equals(request.getParameter("newsid")))
				{
					 if(!rs.getString(3).equals(""))
					{
					  	%>
						<div class="mainInfo">
							<a style="font-size:15px;" href="news.jsp?newsid=<%=rs.getString("divid")%>"><%=rs.getString("title")%></a>
							<p><%String news3 = rs.getString("message");
							String news4=new String();
							if(news3.length()>150)
							{
								news4 = news3.substring(0,150);
								news4=news4.concat(" ......");
							}else
								news4=news3;%><%=news4%></p>
						</div>
 						<%
					}
				}
				else if(rs.getString("divid").equals(request.getParameter("newsid")))
				{
			%>
				<div class="mainInfo" style="background-color:#F8F8F8;">
					<h3><%=rs.getString("title")%></h3>
					<p><%=rs.getString("message")%></p>
				</div>
            <%  }  
			}
		}
            
			%>
                
            <%
			
		stmt.close();
        con.close();
		%>
        <!-- end JSP code -->
</div>
        <!-- end div maincontainer -->
        
        </div>
       </body>
</html>
        
