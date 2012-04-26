<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
    <head>
       
         <jsp:include page="/admin/header.jsp"/>
        <html:base />
    </head>
    <table style="position:absolute; left:20%; top:20%" class="table">
            <tr><td align="center" colspan="3" height="25px" class="headerStyle">Import Data</td></tr>
            <tr><td height="5px"></td></tr>

           <html:form action="/uploadExcel" method="post"  enctype="multipart/form-data">
                <tr><td colspan="3" align="center" class="headerStyle">Upload Excel File</td></tr>
            <tr>
               <td align="left" style="padding:10px">
                   <p class="mess"><b>Note</b>:You Need to Import Retrospective Document before any Bibliographic Detail Entry in Library. </p><br/>   Select Microsoft Excel File : <br><html:file  property="excelFile" name="StrutsUploadForm"/>
                    <br> <html:submit>Upload File</html:submit><br>
                    <div style="visibility: hidden">
                          <html:select   style="color:blue;text-align: center;font: bold;text-transform: uppercase"   property="combo_table_name" name="StrutsUploadForm" >
                      <html:option value="bibliographic_details">bibliographic_details</html:option>
                    </html:select>
                    </div>
                    <br><a href="<%=request.getContextPath()%>/cataloguing/Import.pdf">Help of Excel File</a>
                    <a href="<%=request.getContextPath()%>/cataloguing/CatalogImport.xls">Excel File</a>
                </td>            
            </tr>
            </html:form>


    <!--<html:form action="/uploadtxt" method="post"  enctype="multipart/form-data">
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
   

           <html:form action="/uploadmarc" method="post"  enctype="multipart/form-data">
               <tr><td colspan="3" align="center" class="headerStyle">Upload .mrc File</td></tr>
           <tr>
               <td align="left" style="padding:10px ">
                   Select MARC File : <br><html:file  property="excelFile" name="StrutsUploadForm"/><a href="<%= request.getContextPath() %>/viewMarcRepos.do">View Repository Record</a>
                    <br> <html:submit>Upload File</html:submit><br>
                    <%--<input type="checkbox"> Check if you want to overwrite previous same record.--%>
                </td>            
            </tr>
           </html:form>-->
            <tr><td colspan="3">
                  <p class="err">
                    <html:errors />

                    <h3 style="text-align: center; ">
                        <%if (request.getAttribute("msg1") != null) {
                                        out.println(request.getAttribute("msg1"));
                                    }
                        %></h3>   

                <p class="mess">

                        <%if (request.getAttribute("msg2") != null) {
                                        out.println(request.getAttribute("msg2"));
                                    }
                        %>
                </p>


                <p class="err">

                        <%if (request.getAttribute("error") != null) {
                                        out.println(request.getAttribute("error"));
                                    }
                        %>



                        <%if (request.getAttribute("msg") != null) {
                                        out.println(request.getAttribute("error"));
                                    }
                        %></p>
            </td>
        </tr>

        </table>
</body>
</html>
