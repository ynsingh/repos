

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>        
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Afree Khan, Jamia Millia Islamia">
        <meta name="email" content="afreen.mca@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="center">
                <p align="center"><s:label value="SUPPLIER LIST" /></p>
                <p align="center"><s:property value="message" /></p>

                <s:form name="frmSupplierBrowse">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                        <display:table name="erpmsmList" pagesize="15"
                                       excludedParams="*" export="true" cellpadding="0"
                                       cellspacing="0" id="doc"
                                       requestURI="/PrePurchase/BrowseSupplier.action">
                             <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                         <c:out> ${doc_rowNum}
                         </display:column>
                            <display:column property="institutionmaster.imShortName" title="Institution"
                                            maxLength="35" headerClass="gridheader"
                                            class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:10%" sortable="true"/>
                            <display:column property="smName" title="Name"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:25%" sortable="true"/>
                            <display:column property="erpmGenMasterBySmOwnershipType.erpmgmEgmDesc" title="Supplier Type"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:25%" sortable="true"/>
                            <display:column property="erpmGenMasterBySmSupplierType.erpmgmEgmDesc" title="Supplier Type"
                                            maxLength="40" headerClass="gridheader"
                                            class="griddata" style="width:25%" sortable="true"/>
                            <display:column property="smDealsWith" title="Deals With"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:25%" sortable="true"/>


                            <display:column paramId="SMID" paramProperty="smId"
                                            href="/pico/PrePurchase/EditSupplier.action"
                                            headerClass="gridheader" class="griddata" media="html"  title="Edit" >
                                <img align="right" src="../images/edit.jpg" border="0" alt="Edit" style="cursor:pointer;"  title="Edit"/>
                            </display:column>
                            <display:column paramId="SMID" paramProperty="smId"
                                            href="/pico/PrePurchase/DeleteSupplier.action"
                                            headerClass="gridheader" class="griddata" media="html" title="Delete" >
                                <img align="right" src="../images/TrashIcon.png" border="0" alt="Delete"  style="cursor:pointer;" title="Delete"/>
                            </display:column>

                            <display:column paramId="SMID" paramProperty="smId"
                                            href="/pico/PrePurchase/ManageSupplierAddress.action"
                                            headerClass="gridheader" class="griddata" media="html" title="Address"  style="width:40%">
                                <img align="right" src="../images/Home.jpg" border="0" alt="Address"  style="cursor:pointer;" title="Address"/>
                            </display:column>
                        </display:table>
                    </table>
                </s:form>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>