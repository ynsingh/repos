<%--
    Document   : IndentForm
    Created on : Feb 1, 2011, 12:28:32 PM
    Author     : Sajid Aziz
    Revised By: S. Kazim Naqvi
    Revision Date: August 14, 2012
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <sx:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <br>
            <br>
            <div id ="mainContent" align="center">
            <div style="background-color: #215dc6;">
                <p align="left" class="pageHeading" style="color:  #ffffff"> &nbsp;List of Purchase Orders</p>             
                <p align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"/> </p>
            </div>

       <div style="border: solid 1px #000000; background:  gainsboro">

        <s:form name="frmPOBrowse" align="center">
           <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center"  >
                <tr><td>
                            
                    <display:table name="poMasterList" pagesize="15" style="width:100%"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc" decorator="PrePurchase.PrePurchaseDecorator"
                               requestURI="/PrePurchase/browsePOs.action">

                    <display:column  class="griddata" title="S.No." sortable="true" maxLength="100" headerClass="gridheader" style="width:5%">
                        <c:out> ${doc_rowNum}
                    </display:column>

                    <display:column property="departmentmaster.dmName" title="Department"
                                    style="width:20%" headerClass="gridheader"
                                    class="griddata" sortable="true"/>

                    <display:column property="poNumber" title="PO Number"
                                    style="width:10%" headerClass="gridheader"
                                    class="griddata" sortable="true"/>

                    <display:column property="formattedpomPoDate" title="PO Date"
                                    style="width:10%"  headerClass="gridheader"
                                    class="griddata" sortable="true"/>

                    <display:column property="suppliermaster.smName" title="Supplier"
                                    style="width:15%"  headerClass="gridheader"
                                    class="griddata" sortable="true"/>

                    <display:column property="erpmGenMasterByPomCurrencyId.erpmgmEgmDesc" title="Currency"
                                    style="width:10%"  headerClass="gridheader"
                                    class="griddata" sortable="true"/>

                    <display:column property="pomCancelled" title="PO Cancelled"
                                    style="width:10%" headerClass="gridheader"
                                    class="griddata" sortable="true"/>

                    <display:column paramId="PoMasterId" paramProperty="pomPoMasterId"
                                    href="/pico/PrePurchase/preparePOEdit.action" style="width:5%"
                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                    Edit
                    </display:column>

                    <display:column paramId="PoMasterId" paramProperty="pomPoMasterId"
                                    href="/pico/PrePurchase/DeletePOMaster.action" style="width:5%"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    Delete
                    </display:column>

                    <%--<display:column paramId="PoMasterId" paramProperty="pomPoMasterId"
                                    href="/pico/PrePurchase/SavePOMaster.action" style="width:15%"
                                    headerClass="gridheader" class="griddata" media="html" title="Add/Edit">
                                    Add/Edit Items
                    </display:column>
                    --%>
                </display:table>
                </table>
             </s:form>           
           </div>
           <br>
                   </div>

           <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
           </div>
        </div>
    </body>
</html>
