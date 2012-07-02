<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.List,com.myapp.struts.utility.*"%>
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
                   <p class="mess"><b>Note</b>:You Need to Import Retrospective Document before any Bibliographic Detail Entry in Library. </p><br/>   Select Microsoft Excel File : <br>
                   <html:file  property="excelFile" name="StrutsUploadForm"/>
                    <br> <html:submit>Upload File</html:submit><br>
                    <div style="visibility: hidden">
                          <html:select   style="color:blue;text-align: center;font: bold;text-transform: uppercase"   property="combo_table_name" name="StrutsUploadForm" >
                      <html:option value="bibliographic_details">bibliographic_details</html:option>
                    </html:select>
                    </div>
                    <br><a target="_blank" href="<%=request.getContextPath()%>/cataloguing/help.html">Help of Excel File</a>
                    
                </td>            
            </tr>
            </html:form>
               <%--<tr><td colspan="3" align="center" class="headerStyle">Upload TXT File</td></tr>
            <tr><td>
           <h3>File Upload:</h3>
Select a file to upload: <br />
<form action="UploadServlet" method="post"
                        enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value="Upload File" />
</form>
                </td></tr>--%>
            <tr><td>


    
                <font color="red">
                    <html:errors /></font>
                        <%if (request.getAttribute("msg1") != null) {
                                        out.println(request.getAttribute("msg1"));
                                    }
                        %>
                        <%if (request.getAttribute("msg") != null) {
                                        out.println(request.getAttribute("error"));
                                    }
                        %>
                    
            </td>
        </tr>
   


<%--
</html:form>
   

           <html:form action="/uploadmarc" method="post"  enctype="multipart/form-data">
               <tr><td colspan="3" align="center" class="headerStyle">Upload .mrc File</td></tr>
           <tr>
               <td align="left" style="padding:10px ">
                   Select MARC File : <br><html:file  property="excelFile" name="StrutsUploadForm"/><a href="<%= request.getContextPath() %>/viewMarcRepos.do">View Repository Record</a>
                    <br> <html:submit>Upload File</html:submit><br>
                    <input type="checkbox"> Check if you want to overwrite previous same record.
                </td>            
            </tr>
           </html:form>-->
            <tr><td colspan="3">
                  <p class="err">
                    <html:errors />
--%>
                    <%--<h3 style="text-align: center; ">
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
                        %></p>--%>
                    <tr><td>
                         
                       
                        <%
                        List obj=(List)session.getAttribute("importlog");
                        if(obj!=null)
                        {
                        for(int i=0;i<obj.size();i++)
                        {
                          %><%=obj.get(i)%><br>
                       <%}}
                        %>

                        <%
                        //remove session after import
                        session.removeAttribute("table");
                        session.removeAttribute("importlog");
                        session.removeAttribute("table_datatype");
                        session.removeAttribute("table_name");
                        session.removeAttribute("no_columns");
                        session.removeAttribute("myFile");
                        System.gc();
                            %>

               
            </td>
        </tr>

        </table>
</body>
</html>
