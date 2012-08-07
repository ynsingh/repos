<%-- 
    Document   : listrooms
    Created on : Oct 13, 2011, 4:37:32 PM
    Author     : edrp01
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,chat.*,com.myapp.struts.hbm.*,com.myapp.struts.hbm.LoginDAO.*"%>
<%--<jsp:include page="/chat/chatlogin.jsp"/>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<HTML>
<HEAD>
<TITLE> Chat - Room List</TITLE>
<LINK rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css">
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<SCRIPT language="JavaScript">

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
<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String sessionId="";
    boolean page=true;
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");
sessionId = session.getId().toString();
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
String user=(String)session.getAttribute("username");
String instituteName=(String)session.getAttribute("institute_name");
 String contextPath = request.getContextPath();
 String role=(String)session.getAttribute("login_role");
    %>

<BODY>
    <div>



         <table align="center" style="" dir="<%=rtl%>" width="100%">

        <tr>
            <td  valign="top" colspan="2" width="100%" align="<%=align%>">

                <table  align="<%=align%>"  dir="<%=rtl%>" width="100%">
            <tr><td valign="bottom"  align="<%=align%>">
            <img src="<%=request.getContextPath()%>/images/logo.bmp" alt="banner space"  border="0" align="top" id="Image1">
            </td>
            <td style="color: maroon;font-size: 12px"><%=instituteName%><br>&nbsp; Role[<%=role%>]</td>
            <td align="right" valign="top" dir="<%=rtl%>"><span style="font-family:arial;color:brown;font-size:12px;" dir="<%=rtl%>"><b dir="<%=rtl%>">Hi [<%=user%>]&nbsp;<a href="<%=contextPath%>/logout.do" style="text-decoration: none;color:brown" dir="<%=rtl%>">&nbsp;</a></b></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             </td></tr>
            </table><br>
            </td>

            </tr>

        </table>
    
             <br>
<TABLE width="80%" align="center">
   
	<tr>
            <td class="normal" align="left"><b>Welcome:</b> <span style="font-size: 15px;color:red"><%=session.getAttribute("Name")%></span>
                <br><b>You are:</b><span style="font-size: 15px;color:red"> <%=session.getAttribute("Position")%></span>
                </td>

	</tr>
               
         <TR>
             <TD align="left" width="100%"><b>Select the Candidate you want to chat with</b>
		</TD>

         </TR>
	
	
</TABLE>
<BR>

<DIV align="center">
<CENTER>
	<form  action="/EMS/catchroom.do" name="kk" method="post">
	<TABLE width="80%" border="0" cellspacing="1" cellpadding="1" align="center">
	<TR>
	<TD colspan="2" class="pagetitle">

             </TD>
	</TR>
            <TR>
		<TD width="50%">
 <%


            ChatRoomList demo=(ChatRoomList)session.getAttribute("chatroomlist");
            if(demo!=null){
            ChatRoom[] demo2=demo.getRoomListArray();


        try
          {
               int i=0;
               if(demo2.length>0)
                 {
//iterate through HashMap values iterator
 %>
 <b> Online Candidates</b>
         <select  id="chatroom" name="rn">
         <%
                      while(i<demo2.length)
                      {
                         System.out.println((String)session.getAttribute("institute_id")+"&candidate&");
                         if(demo2[i].getName().startsWith((String)session.getAttribute("institute_id")+"&candidate&") && demo2[i].getName().endsWith("&h")){
         %>
                 <option value="<%=demo2[i].getName().substring(0,demo2[i].getName().length()-2)%>">
        <%
                 String temp=demo2[i].getName().substring(0,((String)demo2[i].getName()).lastIndexOf("&"));
                temp=temp.substring(temp.lastIndexOf("&")+1,temp.length());
                Login obj=(Login)LoginDAO.getLoginUserName(temp,(String)session.getAttribute("institute_id"));
        %>
                  <%=obj.getUserName()%>
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
                            <font color="red"><%=msg%></font>
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
    </div>
</BODY>
</HTML>
