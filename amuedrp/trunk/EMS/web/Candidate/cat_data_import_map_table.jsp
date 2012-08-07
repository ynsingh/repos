<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
if(session.isNew()){
%>
<script>parent.location="<%=request.getContextPath()%>/login.jsp";</script>
<%}%>
  <jsp:include page="/election_manager/login.jsp"/>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@page import="com.myapp.struts.Voter.StrutsUploadForm,java.util.*"%>

<html:html lang="true">
    <link rel="stylesheet" href="/EMS/css/page.css"/>
    <link rel="stylesheet" href="/EMS/css/newFormat.css"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <html:base/>
        <script>
        
            function showdata1(){}

            </script>
    </head>
    <body>

        <html:form action="/insert1" method="post"  style="position:absolute;left:10%;  top:15%">
            <table border="1" align="center" class="table">
                <tr><td colspan="2" align="center" class="headerStyle">Mapping is being done on the table</td></tr>
                





                <%
                            int size = 0;
                            if (request.getAttribute("table_size") != null) {
                                size = (Integer) request.getAttribute("table_size");
                            }
                            int sheet_column_size=0;

                            if (session.getAttribute("no_columns") != null) {
                                sheet_column_size = (Integer) session.getAttribute("no_columns");
                            }
                            
                            int max_size=Math.max(size, sheet_column_size);
                          //  out.println("max size is ::::::"+max_size);
                            String table_column[] = new String[max_size];
                            String column_datatype[]=new String[max_size];
                            int countindex = size-1;
                            while(countindex<max_size)
                                table_column[countindex++] = "No MAP";
                           if (session.getAttribute("table") != null) {

                               // StringBuffer table_colum1 = (StringBuffer)
                                String[] table_column1 = (String[])session.getAttribute("table");
                                
                                countindex = 0;
                            while(countindex<table_column1.length)
                                table_column[countindex] = table_column1[countindex++];
                            }

                               if (session.getAttribute("table_datatype") != null) {

                              
                                String[] table_1 = (String[])session.getAttribute("table_datatype");

                                countindex = 0;
                            while(countindex<table_1.length)
                              column_datatype[countindex] = table_1[countindex++];
                            }


                              
                            String cell = "cell";
                            String combo = "combo";
                            //int s=size.intValue();
                            int row = 0;
                         //   out.println("this is the table column size:::::: " + size);
                %>
                <tr>
                    <td style="padding:5px 5px 5px 5px;">
                        <table border="1" align="center" >
                            <tr>

                                <td style="text-align: center;">
                                    <font size="3" color="black" style="" >
                                        <h3 align="center" style="color: black ;font: oblique;"> Header from Excell sheet
                                        </h3>   </font>
                                </td>
                                <td style="text-align: center;">
                                    <font size="3" color="black" style="font: oblique;"  >
                                        <h3 align="center" style="color: black ;" > Table Column name
                                        </h3>
                                    </font>
                                </td>
                            </tr>
                            <%
                                        for (row = 0; row <max_size / 2 ; row++) {
                            %>
                            <tr>
                                <td style="text-align: center;">
                                    <font color="black" size="2%" style="text-align:left;font-style: oblique"> Field<%=row + 1%></font> : <html:text    style="color:blue;text-align: center;" size="20%" name="StrutsUploadForm" property="<%=cell.concat(String.valueOf(row))%>" disabled="true"></html:text>

                                </td>
                                <td style="text-align: center;">1st
                                    <html:select    property="<%=combo.concat(String.valueOf(row))%>" name="StrutsUploadForm" styleClass="textBoxWidth"  styleId="col1" >

                                        <%for (int j = 0; j < size; j++) {
                                            
                                           if ((j == 0)&&(table_column[row]!=null)) {%>
                    <html:option styleClass="err"  value="<%=table_column[row]%>"><%=table_column[row]%>&nbsp;&nbsp;<font color="red"><%=column_datatype[row]%></font></html:option>--%>
                                        <%}
                                            if ((j != row)&&(j<size)) {

                                        %>
                     <html:option value="<%=table_column[j]%>"><%=table_column[j]%>&nbsp;&nbsp;<p class="err"><%=column_datatype[j]%></p></html:option>
                                        <%}
                                                                                    }
                                        %>
                <html:option value="No map">NO MAP</html:option>
                    </html:select>
                                       
                                </td>
                            </tr>
                               <%}
                                         if ((max_size % 2) != 0) {
                                         //    out.println("<tr><td><br> </td><td><br> </td></tr>");
                           }%>

                        </table>

                    </td>
                    <td>
                        <table border="1" align="center" >
                            <tr>

                                <td style="text-align: center;">
                                    <font size="3" color="black" style="" >
                                        <h3 align="center" style="color: black ;font: oblique;"> Header from Excell sheet
                                        </h3>   </font>
                                </td>
                                <td style="text-align: center;">
                                    <font size="3" color="black" style="font: oblique;"  >
                                        <h3 align="center" style="color: black ;" > Table Column name
                                        </h3>   </font>
                                </td>
                            </tr>
                            <%
                                        for (row = max_size / 2 ; row < max_size; row++) {
                            %>
                            <tr>
                                <td style="text-align: center;">
                                    <font color="black" size="2%" style="text-align:left;font-style: oblique"> Field<%=row + 1%></font> : <html:text    style="color:blue;text-align: center;" size="20%" name="StrutsUploadForm" property="<%=cell.concat(String.valueOf(row))%>" disabled="true"></html:text>

                                </td>
                                <td style="text-align: center;">
                                   <html:select    property="<%=combo.concat(String.valueOf(row))%>" name="StrutsUploadForm" styleClass="textBoxWidth"  styleId="col1" >

                                        <%for (int j = 0; j < size; j++) {


                             if ((j == 0)&&(table_column[row]!=null)) {%>
                     <html:option styleClass="err"  value="<%=table_column[row]%>"><%=table_column[row]%>&nbsp;&nbsp;<font color="red"><%=column_datatype[row]%></font></html:option>--%>
                                        <%}
                                            if ((j != row)&&(j<size)) {

                                        %>
                                        <html:option value="<%=table_column[j]%>"><%=table_column[j]%>&nbsp;&nbsp;<p class="err"><%=column_datatype[j]%></p></html:option>
                                        <%}
                                                                                    }
                                        %>
                                         <html:option value="No map">NO MAP</html:option>
                                    </html:select>
                                   
                                </td>
                            </tr>
                            <%}%>
                         
                        </table>
                    </td>
                </tr>

                <tr ><br>

                <td  align="center" colspan="2">  <input  name="button"  type="submit" value="Import Data" />
             
               <input  name="button" type="button"  value="Back" />
                </td>
            </tr>
            <tr><td colspan="2">
    <p class="err">
                        <html:errors />

                            <%if (request.getAttribute("msg1") != null) {%>
                                          <%=request.getAttribute("msg1")%>
                                        <%}
                            %>  </p>
                    <p class="mess">
                            <%if (request.getAttribute("msg2") != null) {
                                    %>        <%=request.getAttribute("msg2")%>
                                        <%}
                            %></p>

                    <p class="err">
                            <%if (request.getAttribute("error") != null) {
                                           %>        <%=request.getAttribute("error")%>
                                        <%
                                        }
                            %></p>




                </td></tr>
        </table>
                


    </html:form>


</body>
</html:html>
