<%--
    Document   : cat_biblio_entry
    Created on : Jan 13, 2011, 12:02:47 PM
    Author     : Client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<jsp:include page="/admin/header.jsp"/>

<%
String library_id=(String)session.getAttribute("library_id");
String sub_library_id=(String)session.getAttribute("sublibrary_id");
String msg1=(String) request.getAttribute("msg1");
%>
<script type="text/javascript">
function send()
{
    window.location="<%=request.getContextPath()%>/acquisition/acq_all_viewbiblioedit.jsp";
    return false;
}
</script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bibliographic Detail Entry Form</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css"/>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/css/formstyle.css"/>
    </head>
    <body>
   <%if(msg1!=null){%>   <span style=" position:absolute; top: 120px; font-size:12px;font-weight:bold;color:red;" ><%=msg1%></span>  <%}%>
  <html:form method="post" action="/acquisition/acq_update_approvedtitle" style="position:absolute; left:20%; top:20%;">
  <table border="1" class="table" width="750" align="center">
        <html:hidden property="library_id" name="AcqBiblioActionForm" value="<%=library_id%>" />
        <html:hidden property="title_id" name="AcqBiblioActionForm"  />
        <html:hidden property="sub_library_id" name="AcqBiblioActionForm" value="<%=sub_library_id%>" />
        <html:hidden property="control_no" name="AcqBiblioActionForm"  />
       <tr>
           <td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Bibliographic Details</td></tr>
       <tr><td><br>
               <table width="700" border="0">
                   <tr>
                       <td align="right"> <strong>Document Type:</strong><br></td>
                       <td> <html:text readonly="true" property="document_type" name="AcqBiblioActionForm" styleClass="textBoxWidth" >

  </html:text>
</td>
    <td width="150" align="right" class="txtStyle"><strong>Title:</strong> </td>
    <td><html:text readonly="true" property="title" name="AcqBiblioActionForm" styleClass="textBoxWidth" />

    </td>
                   </tr>
               <tr>
                       <td align="right" class="txtStyle"><strong>Author:</strong></td>
  <td><html:text readonly="true" property="author" name="AcqBiblioActionForm" styleClass="textBoxWidth" />

  </td>
       <td align="right" class="txtStyle"><strong>Sub Authors:</strong></td>
  <td><html:text readonly="true" property="sub_author" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
       </tr><tr>
              <td align="right" class="txtStyle"><strong>Publisher Name:</strong></td>
  <td><html:text readonly="true" property="publisher_name" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                       <td align="right" class="txtStyle"><strong>Publication Place :</strong></td>
  <td><html:text readonly="true" property="publication_place" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                   </tr><tr>
                       <td align="right" class="txtStyle"><strong>Publishing Year:</strong></td>
  <td><html:text readonly="true" property="publishing_year" name="AcqBiblioActionForm" styleClass="textBoxWidth" />

  </td>
                        <td align="right" class="txtStyle"><strong>LCC No:</strong></td>
  <td><html:text readonly="true" property="lcc_no" name="AcqBiblioActionForm" styleClass="textBoxWidth" />
         </td>
                   </tr>
               <tr>
                        <td align="right" class="txtStyle"><strong>ISBN: </strong></td>
    <td><html:text readonly="true"  property="isbn" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
    <td align="right" class="txtStyle"><strong>Edition:</strong></td>
  <td><html:text readonly="true" property="edition" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                   </tr>
               </table>
                   <br>       </td></tr>

       <tr>
       <td align="center" class="headerStyle" bgcolor="#E0E8F5" height="25px;">Initiate Acquisition</td>
       </tr>
       <tr><td><br>  <table width="700" border="0" >
                    <tr>
                   <td align="right" class="txtStyle"><strong>No of Volumes:</strong></td>
  <td><html:text property="no_of_volume" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
   <td align="right" class="txtStyle"><strong>Volume No:</strong></td>
  <td><html:text property="volume" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>

                    </tr><tr>
                       <td align="right" class="txtStyle"><strong>No Of Copies:</strong></td>
  <td><html:text property="no_of_copies" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                       <td align="right" class="txtStyle"><strong>Unit Price:</strong></td>
  <td><html:text property="unit_price" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                   </tr><tr>
                       <td align="right" class="txtStyle"><strong>Budget:</strong></td>
  <td><html:text property="primary_budget" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                       <td align="right" class="txtStyle"><strong>Request By:</strong></td>
  <td><html:text property="requested_by" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>
                   </tr>
                   <tr>

                       <td align="right" class="txtStyle"><strong>Requested Date:</strong></td>
  <td><html:text property="requested_date" name="AcqBiblioActionForm" styleClass="textBoxWidth" /></td>

 
  <td align="right">
              <strong>Acq Mode:<a class="star">*</a></strong>
  </td>
  <td>
  <html:select property="acq_mode" name="AcqBiblioActionForm" styleClass="textBoxWidth" >
       <html:option value="">Select</html:option>
            <html:option value="On Approval">On Approval</html:option>
            <html:option value="Firm Order">Firm Order</html:option>
  </html:select>
    </td>
  </tr>
                   <tr><td align="right" class="txtStyle"><strong>Vendor:</strong></td>
  <td><html:select property="vendor" name="AcqBiblioActionForm" styleClass="textBoxWidth" >
          <html:option value="">Select</html:option>
          <html:options collection="vendors" name="AcqVendor" labelProperty="vendor" property="id.vendorId"/>
      </html:select>
  </td>
  <td></td><td></td></tr>
                   <tr><td></td><td></td></tr>
<tr><td height="10px"></td>
</tr>
<tr><td  height="5px" class="mandatory" align="right" colspan="4"><a class="star">*</a>indicated fields are mandatory</td></tr>
<tr><td height="10px"></td>
</tr>
<tr>
    <td align="center" colspan="4">
    <input name="button" type="submit" value="Update" class="txt1" />
    &nbsp;&nbsp;&nbsp;<input name="button" type="submit" onclick="return send()" value="Cancel" class="txt1"/></td>
</tr>
<tr><td height="5px"></td>
</tr>
       </table></td></tr>
     </html:form>
   </table>
    </body>
</html>

