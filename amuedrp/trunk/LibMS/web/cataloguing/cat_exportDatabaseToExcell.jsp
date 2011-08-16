<%-- 
    Document   : ExportDatabaseToExcell
    Created on : Apr 11, 2011, 5:42:02 PM
    Author     : khushnood
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 <jsp:include page="/admin/header.jsp" flush="true" />
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LibMS</title>
        <script language="javascript">
            function export_excel(){
                 var keyValue = document.getElementById('table').options[document.getElementById('table').selectedIndex].value;

    if (keyValue=="Select")
    {


               document.getElementById('table').focus();
               var x=document.getElementById('div2');
               x.innerHTML="Please Select the Table";
		return false;
	}


            }
            </script>
    </head>
    <body>
        <div
   style="  top:200px;
   left:5px;
   right:5px;
      position: absolute;

      visibility: show;">
            <html:form action="/export" method="post"  onsubmit="return export_excel();">
            <table class="table" align="center" width="400px" height="200px">

                <tr><td class="headerStyle" colspan="2" height="25px" align="center">Export Table to Excel Sheet</tr>
                <tr>
                    <td align="center">
                        Select Table Name
                    </td>
                    <td >
                        <html:select   styleId="table"  property="combo_table_name" name="StrutsUploadForm" >
                              <html:option value="Select">Select</html:option>
                            <html:option value="accession_register">accession_register</html:option>
                            <html:option value="acq_final_demand_list">acq_final_demand_list</html:option>
                            <html:option value="bibliographic_details">bibliographic_details</html:option>
                            <html:option value="courses">courses</html:option>
                            <html:option value="demandlist">demandlist</html:option>
                            <html:option value="department">department</html:option>
                            <html:option value="document_details">document_details</html:option>
                            <html:option value="faculty">faculty</html:option>
                            <html:option value="feedback">feedback</html:option>
                            <html:option value="fine_details">fine_details</html:option>
                            <html:option value="member_accounthistory">member_accounthistory</html:option>
                            <html:option value="newarrivals">notice</html:option>
                            <html:option value="reservationlist">reservationlist</html:option>
                            <html:option value="sub_library">sub_library</html:option>
                            </html:select>
                            <br>
                            <div id="div2" class="err"></div>
                    </td>

                </tr>

                <tr>

                    <td align="center" colspan="2">  <input align="center" name="button"  type="submit" value="Export Data"  />           
                 <input align="center" name="button" type="submit"  value="Back" />
                
                </td>
            </tr>
            <tr><td colspan="2"><div id="div1"></div></tr>
            <tr><td colspan="2">

                    <p class="mess">  <%if (request.getAttribute("msg") != null) {
                                out.println(request.getAttribute("msg"));
                            }
                %></p>
                    <p class="err">

                <%if (request.getAttribute("msg1") != null) {
                                out.println(request.getAttribute("msg1"));
                            }
                %></p>

                </td></tr>
        </table>
        
           
                
            </html:form>
        </div>
</body>
</html>
