<%--
    Document   : ManageStockDetails
    Created on : 7 Oct, 2011, 10:36:18 PM
    Author     : farah
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container" >
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>

            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent"> <br><br>
                <p align="center"><s:label value="BROWSE STOCK DETAILS" cssClass="pageHeading"/></p>
                <p align="center"><s:property value="message" /></p> 
                <s:form name="frmStockDetails" action="SaveStockDetailsAction">
                    <s:hidden name="tos.tosId" />

                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <tr>
                                <td>


                     <%--               <script type='text/javascript'>
             function emailValidator(elem, helperMsg){
                    var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
                    if(elem.value.match(emailExp)){
                        return true;
                     }else {
                        alert(helperMsg);
                        elem.focus();
                        return false;
                     
                    }
            }
             </script>--%>
    <s:select required="true" label="Batch ID" name="erpmuser.erpmuName" headerKey="" headerValue="-- Please Select --" list="ErpmuList" listKey="erpmuName" listValue="erpmuName" cssClass="textInput"/>
                     
<%--    <s:select required="true" label="Batch ID" name="erpmuser.erpmuId" headerKey="" headerValue="-- Please Select --" list="ErpmuList" listKey="erpmuId" listValue="erpmuName" cssClass="textInput"/>
<%-- <s:select required="true" label="Batch ID" name="tos.tosBatchId" headerKey="" headerValue="-- Please Select --" list="BIList" listKey="tosId" listValue="tosBatchId" cssClass="textInput"/>-->
     <%--      <s:textfield label="Batch ID" name="tos.tosBatchId" title="Enter Batch ID"
                           required="true" requiredposition="left" maxLength="100" size="100"  cssClass="textInput"/>--%>
            </td>
              </tr> <tr>
                  <td></td>
                                <td>
                                    <s:submit theme="simple" name="bthReset" value="Fetch Stock Detail Entries" action="FetchStockDetails"  cssClass="textInput"/>
                                </td>

                                <td></td>
                            </tr>
                            
                        </tbody>
                    </table>
                </s:form>
            </div>

             <div id ="mainContent" align="center">
             <s:form name="frmStockDetailsBrowse"  action="SaveStockDetailsAction"> 
                  <s:hidden name="varBatchID" />
                 <table width="80%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
			<%--
                             <display:table name="tosList" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" id="doc"
                               requestURI="/Inventory/FetchStockDetails">  --%>
                             <display:table name="tosList" pagesize="15" id="doc" requestURI="/Inventory/FetchStockDetails"> 
                         <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                            <c:out> ${doc_rowNum}
                         </display:column>

                        <display:column property="erpmItemMaster.erpmimDetailedDesc" title="Item Name"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="false" />

                            <display:column property="suppliermaster.smName" title="Supplier Name"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" sortable="false"/>

                        <display:column property="tosPoNo" title="purchase order no."
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" sortable="false"/>

                         <display:column property="tosChallanNo" title="Challan no."
                                    maxLength="150" headerClass="gridheader"
                                    class="griddata" sortable="false"/>

                         <display:column property="tosInvoiceNo" title="Invoice No"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" sortable="false"/>

                         <display:column property="tosUnitRate" title="Unit Rate"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" sortable="false"/>
                         <display:column property="tosStockSerialNo" title="Stock Serial No."
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" sortable="false"/>

                         <display:column paramId="TosId" paramProperty="tosId"
                                    href="/pico/Inventory/EditStockDetails.action"
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                    Edit
                    </display:column>
                    <display:column paramId="TosId" paramProperty="tosId"
                                    href="/pico/Inventory/DeleteStockDetails.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete
                    </display:column> 

                </display:table> 
                </td><td></td></tr>
                </table>
                 <td></td><td></td><td></td>
                  <td>
                           <s:submit theme="simple" name="bthReset" value="Transfer All Items to Stock"  action="TransferStockDetailsAction" cssClass="textInput"/> 
                                </td>
             </s:form>
             </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
