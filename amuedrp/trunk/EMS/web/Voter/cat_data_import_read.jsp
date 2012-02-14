<%@page import="java.util.*,java.io.*,java.net.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
    <head>
       
         <jsp:include page="/election_manager/login.jsp"/>
        <html:base />
    </head>
    <table style="position:absolute; left:20%; top:30%" class="table">
            <tr><td align="center" colspan="3" height="25px" class="headerStyle">Import Voter Registration Data</td></tr>
            <tr><td height="5px"></td></tr>

           <html:form action="/uploadExcel" method="post"  enctype="multipart/form-data">
                <tr><td colspan="3" align="center" class="headerStyle">Upload Excel File</td></tr>
            <tr>
               <td align="left" style="padding:10px">
                  <b>Note</b>:You Need to Import Voter Registration Details data in XLS Format. &nbsp;&nbsp;<a href="ImportHelpFile.html" target="_blank">Help</a><br/>   Select Microsoft Excel File : <br><html:file  property="excelFile" name="StrutsUploadForm1"/>
                    <br> <html:submit>Upload File</html:submit><br>
                    <div style="visibility: hidden">
                          <html:select   style="color:blue;text-align: center;font: bold;text-transform: uppercase"   property="combo_table_name" name="StrutsUploadForm1" >
                      <html:option value="bibliographic_details">bibliographic_details</html:option>
                    </html:select>
                    </div>
                    <%--<br><a href="<%=request.getContextPath()%>/cataloguing/Import.pdf">Help of Excel File</a>
                    <a href="<%=request.getContextPath()%>/cataloguing/CatalogImport.xls">Excel File</a>--%>
                </td>            
            </tr>
            </html:form>


   <%-- <html:form action="/uploadtxt" method="post"  enctype="multipart/form-data">
          <tr><td colspan="3" align="center" class="headerStyle">Upload Txt/Flat File</td></tr>
         
            <tr>
                <td colspan="3">Select  Text File :
               
               
                 <html:text  name="StrutsUploadForm" property="delimiter"/>
 <input  name="button"  type="submit" value="Brows file" />

                </td>

                
            </tr>

           <tr>
                <td align="left">Delimiter used (eg ",|,)etc) :  </td>
                
                <td colspan="2">
                   
                </td>

               
            </tr>

       
       
           
        <tr><td colspan="3">
                <font color="red">
                    <html:errors /></font>
                <font size="2" color="red">
                    <h3 style="text-align: center; ">
                        <%if (request.getAttribute("msg1") != null) {
                                        out.println(request.getAttribute("msg1"));
                                    }
                        %></h3>   </font>

                <font size="2" color="green">
                    <h3 style="text-align: center; ">
                        <%if (request.getAttribute("msg2") != null) {
                                        out.println(request.getAttribute("msg2"));
                                    }
                        %></h3>   </font>
           
                <font size="2" color="red">
                    <h3 style="text-align: center; ">
                        <%if (request.getAttribute("error") != null) {
                                        out.println(request.getAttribute("error"));
                                    }
                        %></h3>   </font>
            
                <font size="2" color="red">
                    <h3 style="text-align: center; ">
                        <%if (request.getAttribute("msg") != null) {
                                        out.println(request.getAttribute("error"));
                                    }
                        %></h3>   </font>
            


                        <%

                        if (request.getAttribute("testfileread") != null) {%>
                        <p class="mess"><%=request.getAttribute("testfileread")%></p>
                                    <%}
                        %>

            </td>
        </tr>
   



</html:form>
   
--%>
                      <tr><td colspan="3">
                  <%--<p class="err">
                    

                   
                        <%if (request.getAttribute("msg1") != null) {
                                        out.println(request.getAttribute("msg1")+"\n");
                                    }
                        %>
                  </p>--%>
                <p class="mess">

                        <%if (request.getAttribute("msg") != null) {
                                        out.println(request.getAttribute("msg")+"\n");
                                    }
                        %>
                        
                </p>


            </td>
        </tr>
        <tr><td>
                <table>
                    <%
                        List obj=(List)session.getAttribute("log");
if(obj!=null){
StringBuffer str = new StringBuffer();
//always give the path from root. This way it almost always works.
String userid=(String)session.getAttribute("user_id");
String nameOfTextFile = userid+"log.txt";
String path=(String)session.getAttribute("apppath");
//path=path.substring(0,path.lastIndexOf("/"));
//path=path.substring(0,path.lastIndexOf("/"));
//path=path.substring(0,path.lastIndexOf("/"));
System.out.println(path+"..........................");
try {
    PrintWriter pw = new PrintWriter(new FileOutputStream(path+"/EMSLOG/"+nameOfTextFile,true));
//System.out.println(pw);
    for(int i=0;i<obj.size();i++)
        str.append(obj.get(i)+"\n");
    pw.println(str+"\n");
    //clean up
    pw.close();
} catch(IOException e) {
   out.println(e.getMessage());
}
%>   <a href="<%="/EMS/EMSLOG/"+nameOfTextFile%>" target="_blank">View Log</a><%
}

                    %>
                    
                   

                    <%
                        if(obj!=null){
                        for(int i=0;i<obj.size();i++){
                          %><tr><td>  <%=obj.get(i)%></td></tr>
                       <% }}
                      //  if (request.getAttribute("msg") != null) {
                        //                out.println(request.getAttribute("msg"));
                         //           }
                        %>
                </table>
            </td></tr>

        </table>
</body>
</html>
