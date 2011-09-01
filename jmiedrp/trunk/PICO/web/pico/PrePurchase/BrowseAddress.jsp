<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

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
        <meta name="author" content="Afreen Khan, Jamia Millia Islamia">
        <meta name="email" content="afreen.mca@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
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
             <s:form name="frmSupplierAddressBrowse">
                 <s:property value="message" />
                
               
                
                <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                    <display:table name=" adList" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" id="doc"
                               requestURI="/PrePurchase/BrowseAddress.action">
                   <display:column  class="griddata" title="Record" style="width:40%" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                        <display:column property="suppliermaster.smName" title="SupplierName"
                                    maxLength="35" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" style="width:30%" sortable="true"/>
                    <display:column property="suppliermaster.smDealsWith" title="Deals With"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:30%" sortable="true"/>


                    <display:column property="adLine1" title="Line1"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:30%" sortable="true"/>
                    <display:column property="adLine2" title="Line2"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:30%" sortable="true"/>
                    <display:column property="adCity" title="City"
                                    maxLength="10" headerClass="gridheader"
                                    class="griddata" style="width:30%" sortable="true"/>
                    <display:column property="statemaster.stateName" title="State"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:30%" sortable="true"/>


                     <display:column property="countrymaster.countryName" title="Country"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:30%" sortable="true"/>

                     <display:column property="adPhn" title="Phone"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:30%" sortable="true"/>

                     <display:column property="adMob" title="Mob"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:30%" sortable="true"/>
                    <display:column property="adFaxn" title="Fax"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:30%" sortable="true"/>


                     <display:column property="adEmail" title="Email"
                                    maxLength="35" headerClass="gridheader"
                                    class="griddata" style="width:30%" sortable="true"/>


                    <display:column paramId="ADID" paramProperty="supAdId"
                                    href="/pico/PrePurchase/EditAddress"
                                    headerClass="gridheader" class="griddata" media="html" title="Edit" >
                                    <img align="right" src="../images/edit.jpg" border="0" alt="Edit" style="cursor:pointer;"/>
                    </display:column>
                   <display:column paramId="ADID" paramProperty="supAdId"
                                    href="/pico/PrePurchase/DeleteAddress.action"
                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                    <img align="left" src="../images/TrashIcon.png" border="0" alt="Delete"  style="cursor:pointer;"/>
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