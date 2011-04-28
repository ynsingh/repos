
 
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


    <table width="400px" height="400px" class="txt2"  valign="top" align="left" id="tab1">
        <tr>
            <td   width="400px" height="600px" valign="top" style="" align="left" class="mess">
               
  <br>
                    <p align="left" ><u>Institute Details</u><br></p>
                    <p align="left" >Institute ID    :<b><%=msg1%></b></p>
                  <!-- <p align="left"> Library Name  :<b><%=msg2%></b></p>   -->
                    <p align="left" >Institute Name:<b><%=msg3%></b></p>
                    <p align="left" >Institute is Successfully registered</p>




             </td>
        </tr>
    </table>
        
   
   

<script language="javascript">
    alert("<%=msg%>");
    
    parent.location = "<%=request.getContextPath()%>/superadmin.do"
   // parent.document.getElementById('library_id').value="";

</script>