<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
    <head>
        <title>Struts File Upload</title>
         <jsp:include page="/admin/header.jsp"/>
        <html:base />
    </head>
    <html:form action="/uploadExcel" method="post"  enctype="multipart/form-data" style="position:absolute; left:30%; top:30%">
        <table  align="center" class="table" width="400px" height="200px" border="1">
            <tr><td align="center" colspan="3" height="25px" class="headerStyle">Import Data</td></tr>
          
            <tr>
                                <td align="left" style="padding:10px ">
                    Select Microsoft Excel File : <br><html:file  property="excelFile" name="StrutsUploadForm"/>
                    <br> <html:submit>Upload File</html:submit><br>
                    <div style="visibility: hidden">
                          <html:select   style="color:blue;text-align: center;font: bold;text-transform: uppercase"   property="combo_table_name" name="StrutsUploadForm" >
                      <html:option value="bibliographic_details">bibliographic_details</html:option>
                    </html:select>
                    </div>
                </td>
               

             
            </tr>

          <tr><td>
                  <p class="err">
                    <html:errors />

                    <h3 style="text-align: center; ">
                        <%if (request.getAttribute("msg1") != null) {
                                        out.println(request.getAttribute("msg1"));
                                    }
                        %></h3>   </p>

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
        
      
    
</html:form>
</body>
</html>
