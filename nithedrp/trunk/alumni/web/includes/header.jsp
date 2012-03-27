<%@ include file="connectionstrings.jsp" %>
<html>
<head>

     <script type="text/javascript">


function EW_onError(form_object, input_object, object_type, error_message) {
        alert(error_message);
        return false;
}


function EW_hasValue(obj, obj_type) {
        if (obj_type == "TEXT" || obj_type == "PASSWORD" || obj_type ==
"TEXTAREA" || obj_type == "FILE")        {
                if (obj.value.length == 0)
                        return false;
                else
                        return true;
        }        else if (obj_type == "SELECT") {
                if (obj.type != "select-multiple" && obj.selectedIndex == 0)
                        return false;
                else if (obj.type == "select-multiple" && obj.selectedIndex == -1)
                        return false;
                else
                        return true;
        }        else if (obj_type == "RADIO" || obj_type == "CHECKBOX")        {
                if (obj[0]) {
                        for (i=0; i < obj.length; i++) {
                                if (obj[i].checked)
                                        return true;
                        }
                } else {
                        return (obj.checked);
                }
                return false;
        }
}







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
</head>

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
  	<form action="includes/login1.jsp" name ="myForm" "method="post" onSubmit="return EW_checkMyForm(this);">
    	<p>	<font color="#000080">Login&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font>
      		<input name="username" value=""/><BR>
            <font size="1" color="gray">(User EmailID or admin)</font>
      		<br />
      		<font color="#000080">Password&nbsp;</font>
      		<input type="password" name="password"/>
    	</p>
    	<p align="right;">
      		<input type="submit" name="submit" value="Login" style="background-color:Lightgrey"/>
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
	 	%><%=rs.getString(1)%>" width="104px"; height="102px"><div class="logo"><img src="includes/logo.png"></div></div>
<div class="supBar" style="padding-right:25em; padding-left:10em; height:7em; vertical-align:middle; text-align:center; padding-top:1em; background-color: #F2FFFF">
  ALUMNI Management System <span class="gray"> </span><br>

  <%
     	quer = "select title from divtext where (divtype = 'header' && divid='collegename')";
        rs = stmt.executeQuery(quer);
		rs.next();
          session.setAttribute("cname",rs.getString("title"));
	 %>

  <span style="color:#333; font-size:16px;font-weight: bold;"><%= rs.getString("title")%></span>
  <%	
			
		  if((session.getAttribute("username")!=null)&&(session.getAttribute("type").equals("Administrator")))
		  {
	%>
  <p><a id="headerEdit"><span >&#187;&#187;Edit Header</span></a></p>
  <%
          }
%>
 
</div>
</div>
<div class="menu"> <a href="index.jsp"><span class="orange">&#187;</span>Home |

        <%if((session.getAttribute("type")!=null)&&(session.getAttribute("type").equals("Administrator")))
	  {
%>
  <a href="adminedit.jsp"><span class="orange">&#187;</span>Configure Academic Details</a>|
  <%}%> 

    </a> <a href="listevents.jsp"> <span class="orange">&#187;</span>Events</a> | <a href="news.jsp"> <span class="orange">&#187;</span>News</a> | <a href="registration.jsp"> <span class="orange">&#187;</span>Registration</a>  
    
    <%if((session.getAttribute("login")!=null) )
	  {
             if(session.getAttribute("type").equals("Administrator"))
                 {
                 }
          else
            {
%>
   | <a href="alumniedit.jsp"> <span class="orange">&#187;</span>Edit Details</a>
    <%
      
        }

      }%>

    |<a href="search.jsp"> <span class="orange">&#187;</span>Search</a>
| <a href="./forum/index.jsp"> <span class="orange">&#187;</span>Forum</a>
</div>
