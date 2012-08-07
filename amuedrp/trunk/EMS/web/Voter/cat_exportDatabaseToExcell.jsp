<%-- 
    Document   : ExportDatabaseToExcell
    Created on : Apr 11, 2011, 5:42:02 PM
    Author     : khushnood
--%>
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
   <script language="javascript">
function fun()
{
document.Form1.action="<%= request.getContextPath() %>/catExport.do";
document.Form1.method="post";
document.Form1.target="f1";
document.Form1.submit();
}
</script>
    </head>
    <body onload="fun()">
        <div style="top:100px; left:20%; position: absolute; visibility: show;">
            <html:form action="/export" method="post"  onsubmit="return export_excel();">
               <table border="1" align="center" class="table" width="500px" height="100px">
                
                <tr><td class="headerStyle" colspan="2" height="25px" align="center">Export Table to Excel Sheet</tr>
                <tr>
                    <td align="center" width="200px" height="25px">
                        Select Table Name
                    </td>
                    <td height="25px">
                            <html:select   styleId="table"  property="combo_table_name" name="StrutsUploadForm" >
                            <html:option value="Select">Select</html:option>
                            <html:option value="accession_register">accession_register</html:option>
                            <html:option value="bibliographic_details">bibliographic_details</html:option>
                            <html:option value="document_details">document_details</html:option>
                           
                            </html:select>
                            <br>
                            <div id="div2" class="err"></div>
                    </td>
                </tr>
                <tr>
                    <td align="center" colspan="2" height="25px">  <input align="center" name="button"  type="submit" value="Export Data"  />
                 <input align="center" name="button" type="submit"  value="Back" /><br/>
                   <p class="mess">
                        <%if (request.getAttribute("msg") != null) {
                                out.println(request.getAttribute("msg"));
                            }%>
                    </p>
                    <p class="err">
                <%if (request.getAttribute("msg1") != null) {
                                out.println(request.getAttribute("msg1"));
                            }
                %></p>
                </td>
            </tr>
           
            
        </table>                
            </html:form>
        </div>
         <div style="top:200px; left:20%; position: absolute; visibility: show;">
     <html:form action="/ExportDatabaseToTextAction" method="post"  >
        
       <table border="1" align="center" class="table" width="500px" height="100px">
  <tr><td class="headerStyle" colspan="2" height="25px" align="center">Export Table to Flat/Txt File</tr>


                <tr>
                    <td style="text-align: center;"  >
                        SELECT TABLE NAME
                    </td>
                    <td style="text-align: center;">
                        <html:select   style="color:blue;text-align: center;font: bold;text-transform: uppercase"   property="combo_table_name" name="StrutsUploadForm" >
                            <html:option value="accession_register">accession_register</html:option>

                            <html:option value="bibliographic_details">bibliographic_details</html:option>

                            <html:option value="document_details">document_details</html:option>

                       

                        </html:select>
                    </td>

                </tr>
                <tr >
                    <td align="right">Delimiter used (eg ",|,)etc) :  </td>
                    <td>
                        <html:select   style="color:blue;text-align: center;font: bold;text-transform: uppercase"   property="combo_delimiter" name="StrutsUploadForm" >
                            <html:option value="">Select Any Delimiter </html:option>
                            <html:option value=",">Comma ( , ) </html:option>
                            <html:option value=" "> Blank space </html:option>
                            <html:option value=";">Semi colon (;) </html:option>
                            <html:option value=":"> colon (:) </html:option>
                            <html:option value="\t">Tab </html:option>

                        </html:select>
                    </td>



                </tr>
                <tr>
                    <td></td>
                     <td> Other :
                        <html:text  name="StrutsUploadForm" property="delimiter"/>

                    </td>
                </tr>

                <tr><br>

                <td style="text-align: center;" align="center">  <input align="center" name="button" style="text-align: center;color:green;  " type="submit" value="Export Data"  />            </td>
                <td style="text-align: center;" align="center">  <input align="center" name="button" type="submit" style="text-align: center;color:red;  " value="Back" />

                </td>
            </tr>
            <tr><td colspan="2"><div id="div1" style="visibility: hidden"></div></tr>
        </table>
        <font size="2" color="blue">
            <h3 style="text-align: center; ">
                <%if (request.getAttribute("msgt") != null) {
                                out.println(request.getAttribute("msgt"));
                            }
                %></h3>   </font>
        <font size="2" color="red">
            <h3 style="text-align: center; ">
                <%if (request.getAttribute("msg1") != null) {
                                out.println(request.getAttribute("msg1"));
                            }
                %></h3>   </font>
            </html:form>
        </div>
 <div style="top:350px; left:20%; position: absolute; visibility: show; width: 60%">
     <form action="<%= request.getContextPath() %>/catExport.do" name="Form1" method="post"  >
<%--            <table class="table" align="center" width="350px" height="150px">
                <tr><td class="headerStyle" colspan="2" height="25px" align="center">Create .mrc file</tr>
                <tr>
                <td align="center" colspan="2">
                    <input align="center" name="button"  type="submit" value="Export Data"/>
                    <input align="center" name="button" type="submit"  value="Back"/>
                </td>
            </tr>
            <tr><td colspan="2"><div id="div1"></div></tr>
            <tr><td colspan="2">
                    <p class="mess">
                        <%if (request.getAttribute("msg") != null) {
                                out.println(request.getAttribute("msg"));
                            }
                        %>
                    </p>
                    <p class="err">
                <%if (request.getAttribute("msg1") != null) {
                                out.println(request.getAttribute("msg1"));
                            }
                %>
                    </p>
                </td></tr>
        </table> --%>
<table width="100%">
    <tr><td align="center" class="headerStyle">Export MARC data</td></tr>
    <tr>
        <td><IFRAME  name="f1" src="#" frameborder=0  id="f1" width="100%"  class="table" ></IFRAME></td>
    </tr>
</table>
            </form>
        </div>
    </body>
</html>
