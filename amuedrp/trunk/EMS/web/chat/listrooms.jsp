<%-- 
    Document   : listrooms
    Created on : Oct 13, 2011, 4:37:32 PM
    Author     : edrp01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,chat.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
<HEAD>
<TITLE> Chat - Room List</TITLE>
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<SCRIPT language="JavaScript">
	 <!--
	 if(window.top != window.self)
	 {
		 window.top.location = window.location;
	 }
	 //-->


	
      
        


function getSelectedRadioValue(buttonGroup) {
   // returns the value of the selected radio button or "" if no button is selected
   alert(buttonGroup.Length);
   var i = getSelectedRadio(buttonGroup);
   if (i == -1) {
      return "";
   } else {
      if (buttonGroup[i]) { // Make sure the button group is an array (not just one button)
         return buttonGroup[i].value;
      } else { // The button group is just the one button, and it is checked
         return buttonGroup.value;
      }
   }
} // Ends the "getSelectedRadioValue" function




             
         

</SCRIPT>
</HEAD>


<BODY  bgcolor="#FFFFFF">

<div align="center">

<center>
 <table bgcolor="yellow" width="100%" border="1">
	<tr>
            <td  width="100%" align="center" class="headerStyle">Welcome To Online Chatting</td>
	</tr>
<br>
<%

//System.out.println(msg+"Messageeeeeeeeeeeeeeeeeeeeeeeeeeee");

%>
</table>
</center>
</div>
<br>
<TABLE width="80%" align="center">
    <TR>
		<TD width="100%">Select the Candidate you want to chat with
		</TD>

	</TR>
	<tr>
            <td class="normal">Welcome <span><%=session.getAttribute("Name")%></span>
                <br>You are: <%=session.getAttribute("Position")%> 
                </td>

	</tr>
               
            
	
	
</TABLE>
<BR>

<DIV align="center">
<CENTER>
	<form  action="/EMS/catchroom.do" name="kk" method="post">
	<TABLE width="80%" border="0" cellspacing="1" cellpadding="1" align="center">
	<TR>
	<TD colspan="2" class="pagetitle"></TD>
	</TR>


		<%--<TR>
		<TD width="50%">
		<INPUT type=radio name="rn" value="Web_Designing"
		<A>Web_Designing</A>
		</TD>

			<TD width="50%"><A href="<%=request.getContextPath()%>/listrooms.jsp?rn=Web_Designing&sd=y">View Description</A></TD>

		</TR>

		<TR>
		<TD width="50%">

		<INPUT type=radio name="rn" value="ASP"
		<A>ASP</A>
		</TD>

			<TD width="50%"><A href="<%=request.getContextPath()%>/listrooms.jsp?rn=ASP&sd=y">View Description</A></TD>

		</TR>

		<TR>
		<TD width="50%">
		<INPUT type=radio name="rn" value="Jsp"
		<A>Jsp</A>

		</TD>

			<TD width="50%"><A href="<%=request.getContextPath()%>/listrooms.jsp?rn=Jsp&sd=y">View Description</A></TD>

		</TR>

		<TR>
		<TD width="50%">
		<INPUT type=radio name="rn" value="Java"
		<A>Java</A>
		</TD>

			<TD width="50%"><A href="<%=request.getContextPath()%>/listrooms.jsp?rn=Java&sd=y">View Description</A></TD>


		</TR>
--%>
<TR>
		<TD width="50%">

           
 <%


            ChatRoomList demo=(ChatRoomList)session.getAttribute("chatroomlist");
            if(demo!=null){
            ChatRoom[] demo2=demo.getRoomListArray();


try
{

 //out.println(demo2.length);


//obtain an Iterator for Collection

int i=0;


if(demo2.length>0){
//iterate through HashMap values iterator
%>
Room List
<select  id="chatroom" name="rn">
<%
while(i<demo2.length)
{
System.out.println((String)session.getAttribute("institute_id")+"&candidate&");
if(demo2[i].getName().startsWith((String)session.getAttribute("institute_id")+"&candidate&") && demo2[i].getName().endsWith("&h")){
%>
<option value="<%=demo2[i].getName().substring(0,demo2[i].getName().length()-2)%>"><%
String temp=demo2[i].getName().substring(0,((String)demo2[i].getName()).lastIndexOf("&"));

temp=temp.substring(temp.lastIndexOf("&")+1,temp.length());

%>
<%=temp%>
</option>
                    
<%}
i++;
}


}
else{%>

<%}
}catch(Exception e){}

%>
</select>
<% if(demo!=null){%>
<input type="submit" name="button" value="Start">
<%}}%>


<%

if(demo==null){%>
No Candidate is available for Chatting
<%}
%>
<% String  msg= (String)session.getAttribute("msg1");


if(msg!=null){
      %>
      <font color="red">     <%=msg%></font>
        <%
        session.removeAttribute("msg1");
}

%>		</TD>

		

</TR>

<TR>
        
	

</TR>
</TABLE>
</form>
</CENTER>
</DIV>

<DIV align="center">
<CENTER>

<TABLE width="100%" border="0" align="center" cellpadding="3" cellspacing="0">
	<TR>
		<TD width="100%" align="center"><a href="<%=request.getContextPath()%>/Voter/voter_home.jsp">Back</a> </TD>
	</TR>
</TABLE>
</CENTER>
</DIV>
</BODY>
</HTML>
