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
if(session.getAttribute("library_id")!=null){
System.out.println("library_id"+session.getAttribute("library_id"));
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
     

 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>


    <table width="400px" height="400px" class="txt2"  valign="top"  dir="<%=rtl%>" align="<%=align%>" id="tab1">
        <tr>
            <td   width="400px" height="600px" valign="top" style=""  dir="<%=rtl%>" align="<%=align%>" class="mess">
               
  <br>
                     <%if(msg!=null){%>
                        <%=msg%>
                        <%}%>   
                        
                        
                    

                 




             </td>
        </tr>
    </table>
        
   
   

<script language="javascript">
    alert("<%=msg%>");
    
    parent.location = "<%=request.getContextPath()%>/superadmin.do"
   // parent.document.getElementById('library_id').value="";

</script>