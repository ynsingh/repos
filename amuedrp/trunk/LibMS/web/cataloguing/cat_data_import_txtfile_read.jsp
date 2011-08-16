<%-- 
    Document   : cat_data_import_txtfile_read
    Created on : Jun 18, 2011, 4:27:57 PM
    Author     : khushnood
--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html>
    <head>
        <title>Text File Upload</title>
        <html:base />
    </head>
    <html:form action="/uploadExcel" method="post"  enctype="multipart/form-data">
        <table border="2" align="center" style="top: 25%">
            <caption align="center">
                <font size="2" color="black">
                    <h3>
                        To read Data from TextFile <br>

                    </h3>
                </font>
            </caption>
            <tr>
                <td align="left" colspan="3"><font color="red">
                        <html:errors /></font>

                </td>
            </tr>
            <tr>
                <td align="right">Select Microsoft Excel File :  </td>
                <td>
                    <html:file  property="excelFile" name="StrutsUploadForm"/>
                </td>
                <td>
                    <html:submit>Upload File</html:submit>

                </td>

                <td style="text-align: center;">
                   
                </td>
            </tr></table>
        <table border="0" align="center"  >
            <tr><br>
        
        </tr>
        <tr><td>
                <font color="red">
                    <html:errors /></font>
                <font size="2" color="red">
                    <h3 style="text-align: center; ">
                        <%if (request.getAttribute("msg1") != null) {
                                        out.println(request.getAttribute("msg1"));
                                    }
                        %></h3>   </font></td>
            <td>
                <font size="2" color="green">
                    <h3 style="text-align: center; ">
                        <%if (request.getAttribute("msg2") != null) {
                                        out.println(request.getAttribute("msg2"));
                                    }
                        %></h3>   </font>
            </td>
            <td>
                <font size="2" color="red">
                    <h3 style="text-align: center; ">
                        <%if (request.getAttribute("error") != null) {
                                        out.println(request.getAttribute("error"));
                                    }
                        %></h3>   </font>
            </td>
            <td>
                <font size="2" color="red">
                    <h3 style="text-align: center; ">
                        <%if (request.getAttribute("msg") != null) {
                                        out.println(request.getAttribute("error"));
                                    }
                        %></h3>   </font>
            </td>
        </tr>
    </table>
</html:form>
</body>
</html>

