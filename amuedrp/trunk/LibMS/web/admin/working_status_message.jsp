
 
<%
String msg=(String)request.getAttribute("msg");
String msg1=(String)request.getAttribute("msg1");
String msg2=(String)request.getAttribute("msg2");
String msg3=(String)request.getAttribute("msg3");




%>
     

 <link rel="stylesheet" href="/EMS-Struts/css/page.css"/>


    <table width="400px" height="400px" class="txt2"  valign="top" align="left" id="tab1">
        <tr><td   width="400px" height="600px" valign="top" style="" align="left" class="mess">
               
                <br>
                    <p align="left" ><b><%=msg%></b><br></p>
                    
                    <p align="left" >Institute ID    :<b><%=msg1%></b></p>
                    <p align="left"> Institute Name  :<b><%=msg2%></b></p>
                    <p align="left" >Working status:<b><%=msg3%></b></p>
                    
                    <script>
                        <%if(msg3!=null){%>
                           alert("Working Status Successfully Updated:");
                           parent.location="<%=request.getContextPath()%>/superadmin.do";
                            <%}%>
                                


                        </script>



</td></tr></table>
        
   
   

