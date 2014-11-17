<%-- 
    Document   : PurchaseInvoiceBrowse
    Created on : 6 Aug, 2012, 1:08:56 PM
    Author     : Tanvir Ahmed & Saeed-uz-Zama & mkhan
<%@page contentType="text/html" pageEncoding="UTF-8"%>
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Budgettypemaster.js"></script>
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp"   flush="true" ></jsp:include >
            </div>

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">
                <br><br>
                <div style ="background-color: #215dc6;">
                <p align="center" class="pageHeading" style="color:  #ffffff">PURCHASE INVOICE/BILL LIST</p>
                <p align="center" class="mymessage" style="color:  #ffff99" ><s:property value="message" /></p>
                </div>
                <div style="border: solid 1px #000000; background: gainsboro">
             <s:form name="frmBrowsePurchaseInvoice">
                 <s:hidden name="pibm.pimPimId"/>
             <%--    <s:property value="message"/>
                 <s:label value="PURCHASE INVOICE/BILL LIST" /> --%>
                 <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                     <display:table name="pibmList" pagesize="15" decorator="Purchase.PurchaseDecorator"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" id="doc"
                               requestURI="/Purchase/BrowsePurchaseInvoice.action">
                            <display:column  class="griddata" title="S.No" maxLength="100" sortable="true" style="width:10%" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                    </display:column>
                    <display:column property="institutionmaster.imShortName" title="Institute Name"
                                    maxLength="20" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:10%" sortable="true"/>

                    <display:column property="subinstitutionmaster.simShortName" title="Subinstitution Name"
                                    maxLength="20" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>

                    <display:column property="departmentmaster.dmShortName" title="Department Name"
                                    maxLength="20" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>

                    <display:column property="suppliermaster.smName" title="Supplier Name"
                                    maxLength="500" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>

                    <display:column property="PONumberFromInvoice" title="PO NO."
                                    maxLength="500" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
                    
                    <display:column property="erpmPurchasechallanMaster.pcmChallanNo" title="Challan NO."
                                    maxLength="500" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
 
                    <display:column property="formattedInvoiceRecvdDate" title="Invoice Recieved Date"
                                    maxLength="500" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
 
                    <display:column paramId="pimPimId" paramProperty="pimPimId"
                                    href="/pico/Purchase/Edit.action"
                                    headerClass="gridheader" class="griddata" media="html" value="Edit">                                   
                    </display:column>

                    <display:column paramId="pimPimId" paramProperty="pimPimId"
                                    href="/pico/Purchase/GotoDetail.action"
                                    headerClass="gridheader" class="griddata" media="html" value="Go to Detail">
                    </display:column>

                    <display:column paramId="pimPimId" paramProperty="pimPimId"
                                    href="/pico/Purchase/PrintReceiving.action"
                                    headerClass="gridheader" class="griddata" media="html" value="Print Receiving">
                    </display:column>

     
        </display:table>


                </table>
             </s:form>
            </div>
            </div>
             <div id="footer">
                 <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
