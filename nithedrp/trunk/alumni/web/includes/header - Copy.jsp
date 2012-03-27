<%@ include file="connectionstrings.jsp" %>

<%
   response.setHeader( "Pragma", "no-cache" );
   response.setHeader( "Cache-Control", "no-cache" );
   response.setDateHeader( "Expires", 0 );
%>
<div style=" float:right; padding-right:2em; padding-top:2em;">
<%
	//if((adminlogin == "true")||(alumnilogin== "true"))
	if(session.getAttribute("username")!=null)
		{
%>
	<p> User Name : <% out.println(session.getAttribute("username")); %></p><p><font color="#000080"><a href="includes/logout1.jsp">Logout</a></font></p>
<%
		}
	else
		{
%>

    <form action="includes/login1.jsp" method="post" onSubmit="return EW_checkMyForm(this);">
    	<p>	<font color="#000080">Login&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font>
            <input name="username" size="15" value=""/>
      		<br />
      		<font color="#000080">Password&nbsp;&nbsp;</font>
                <input  type="password" size="15" name="password" />
    	</p>
    	<p align="right;">
            <br>
      		<input type="submit" name="submit" value="Login" style="background-color: #99ccff"/>
    	</p>
  	</form>
<%
		}
%>
<%	
	if((session.getAttribute("Login")!=null)&&(session.getAttribute("Login").equals("Error")))
		{
%>
  	<p><font color="Red"><strong> Enter correct username and password</strong></font></p>
<%
		}
%>
</div>

<div class="logo" style="padding-top:1em;"> <img src="<%
     	quer = "select title from divtext where (divtype = 'header' && divid='logourl')";
        rs = stmt.executeQuery(quer);
		rs.next();
	 	%><%=rs.getString(1)%>" width="104px"; height="102px"> </div>
<div class="supBar" style="padding-right:25em; padding-left:10em; height:7em; vertical-align:middle; text-align:center; padding-top:1em; background-color: #F2FFFF">
  <h1>A<span class="blue">LUMN</span>I <span class="gray"></span></h1>
  <%
     	quer = "select title from divtext where (divtype = 'header' && divid='collegename')";
        rs = stmt.executeQuery(quer);
		rs.next();
	 %>
  <span style="color:#333; font-size:24px;font-weight: bold;"><%= rs.getString("title")%></span>
  <%	
			
		  if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("Administrator")))
		  {
	%>
  <p><a id="headerEdit"><span >&#187;&#187;Edit Header</span></a></p>
  <%
          }
		  %>
  <script type="text/javascript">

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

</script>
</div>
</div>
<div class="menu"> <a href="index.jsp"><span class="orange">&#187;</span>Home |</a> <a href="listevents.jsp"> <span class="orange">&#187;</span>Events</a> | <a href="news.jsp"> <span class="orange">&#187;</span>News</a> | <a href="registration.jsp"> <span class="orange">&#187;</span>Registration</a> | <a href="alumniedit.jsp"> <span class="orange">&#187;</span>Edit Details</a> |<a href="search.jsp"> <span class="orange">&#187;</span>Search</a>
<%if((session.getAttribute("type")!=null)&&(session.getAttribute("type").equals("Administrator")))
	  {
%>
  |<a href="adminedit.jsp"><span class="orange">&#187;</span>Edit Academic Details</a> 
  <%}%> | <a href="./forum/index.jsp"> <span class="orange">&#187;</span>Forum</a>
</div>
