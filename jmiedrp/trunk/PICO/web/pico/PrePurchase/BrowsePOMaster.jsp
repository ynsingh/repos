<%--
    Document   : IndentForm
    Created on : Feb 1, 2011, 12:28:32 PM
    Author     : Sajid Aziz
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
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="sajidaziz00000, Jamia Millia Islamia">
        <meta name="email" content="sajidaziz00@gmail.com">
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
              <div id ="mainContent" align="center">
             <s:form name="frmindentBrowse">
              <s:hidden name="pomaster.pomPoMasterId" />
                 <table width="90%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="POMasterList" pagesize="40"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/PrePurchase/BrowseIndentPOMaster.action">
                     <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>


                    <display:column property="departmentmaster.dmName" title="Department"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
                    <display:column property="pomPoNo" title="Po_No"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
                    <display:column property="pomPoDate" title="PO_Date"
                                   maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
                    <display:column property="suppliermaster.smName" title="Supplier"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
                    <display:column property="erpmGenMasterByPomCurrencyId.erpmgmEgmDesc" title="Currency"
                                    maxLength="35" headerClass="gridheader"
                                  class="griddata" style="width:10%" sortable="true"/>

                    <display:column property="erpmusersByPomUserId.erpmuName" title="User ID"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
                    <display:column property="pomDeliveryDate" title="PO_Delivery_Date"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
                    <display:column property="pomAccomplished" title="PO_Accompolished"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>
                    <display:column property="pomCancelled" title="PO_Cancelled"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:10%" sortable="true"/>


                    <display:column paramId="PoMasterId" paramProperty="pomPoMasterId"
                                    href="/pico/PrePurchase/DeletePOMaster.action"
                                    headerClass="gridheader" class="griddata" media="html">
                                   <img align="left" src="../images/TrashIcon.png" border="0" alt="Delete"  style="cursor:pointer;"/>
                    </display:column>

                    <display:column paramId="PoMasterId" paramProperty="pomPoMasterId"
                                    href="/pico/PrePurchase/EditPOMaster.action"
                                    headerClass="gridheader" class="griddata" media="html" >
                                   <img align="left" src="../images/edit.jpg" border="0" alt="Edit"  style="cursor:pointer;"/>
                    </display:column>
                    <display:column paramId="PoMasterId" paramProperty="pomPoMasterId"
                                    href="/pico/PrePurchase/BrowsePOMasterDetail.action"
                                    headerClass="gridheader" class="griddata" media="html">
                                    <img align="left" src="../images/more_bg.gif" border="0" alt="Items Details"  style="cursor:pointer;"  />
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
