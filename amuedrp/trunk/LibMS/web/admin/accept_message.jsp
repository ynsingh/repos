
 
<%
String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("accept_msg1");
String msg2=(String)request.getAttribute("accept_msg2");
String msg3=(String)request.getAttribute("accept_msg3");




%>
     

 <link rel="stylesheet" href="/LibMS-Struts/css/page.css"/>


    <table width="400px" height="400px" class="txt2"  valign="top" align="left" id="tab1">
        <tr><td   width="400px" height="600px" valign="top" style="" align="left" class="mess">
               
                <br>
                    <p align="left" >Institute Details:-<br></p>
                    
                    <p align="left" >Library ID    :<b><%=msg1%></b></p>
                    <p align="left"> Library Name  :<b><%=msg2%></b></p>
                    <p align="left" >Institute Name:<b><%=msg3%></b></p>
                    <p align="left" >Institute is Successfully registered</b></p>




</td></tr></table>
        
   
   

<script language="javascript">
    alert("<%=msg%>");
    
    parent.location.reload();
   // parent.document.getElementById('library_id').value="";

</script>