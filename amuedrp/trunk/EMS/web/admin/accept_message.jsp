<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*,com.myapp.struts.admin.AdminReg_Institute,com.myapp.struts.hbm.*,java.util.*" %>

<%!
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    boolean page=true;
    String align="left";
%>
<%
try{
locale1=(String)session.getAttribute("locale");

    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
       // System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";page=true;align="left";}
    else{ rtl="RTL";page=false;align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);

    %>
 
<%
try{
if(session.getAttribute("institute_id")!=null){
System.out.println("institute_id"+session.getAttribute("institute_id"));
}
else{
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %><script>parent.location = "<%=request.getContextPath()%>"+"/login.jsp?session=\"expired\"";</script><%
    }
}catch(Exception e){
    request.setAttribute("msg", "Your Session Expired: Please Login Again");
    %>sessionout();<%
    }

String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("accept_msg1");
String msg2=(String)request.getAttribute("accept_msg2");
String msg3=(String)request.getAttribute("accept_msg3");
%>
     

 <link rel="stylesheet" href="/EMS/css/page.css"/>


    <table width="400px" height="400px" class="txt2"  valign="top" align="left" id="tab1">
        <tr>
            <td   width="400px" height="600px" valign="top" style="" align="left" class="mess">
               
  <br>
                    <p align="left" ><%=resource.getString("institute_details")%>:-<br></p>
                    <p align="left" ><%=resource.getString("instituteid")%>  :<b><%=msg1%></b></p>
                  <!-- <p align="left"> Library Name  :<b><%=msg2%></b></p>   -->
                    <p align="left" ><%=resource.getString("institutename")%>:<b><%=msg3%></b></p>
                    <p align="left" ><%=resource.getString("institute_isregistered")%></p>




             </td>
        </tr>
    </table>
        
   
   

<script language="javascript">
    alert("<%=msg%>");
    
    parent.location = "<%=request.getContextPath()%>/superadmin.do"
   // parent.document.getElementById('library_id').value="";

</script>