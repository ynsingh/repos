<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Afreen Khan, Jamia Millia Islamia">
        <meta name="email" content="afreen.mca@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <sx:head />
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
            <div id ="mainContent">
                <p align="center"><s:label value="SUPPLIER ADDRESS ADDITION"  cssClass="pageHeading"/></p>
                <p align="center"><s:property value="message" /></p>
               <div style="border: solid 1px #000000; background:  gainsboro">
                <s:form name="frmAddress" action="SaveAddress"  validate="true">
                    <s:hidden name="erpmsmad.supAdId" />
                    <s:hidden name="erpmsmad.suppliermaster.smId" />
                    <s:hidden name="ADID" />
                    <s:hidden name="SMID" />

                    <table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                            <tr>
                                <td> <br><br>                                    
                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="Address Line1" name="erpmsmad.adLine1" title="" />
                                    <s:textfield required="false" requiredposition="left" maxLength="50" size="50"
                                                 label="Address Line2" name="erpmsmad.adLine2" title="" />
                                    <s:select required="true" label = "Country" name="erpmsmad.countrymaster.countryId" headerKey="" headerValue="-- Please Select --" list="ctList" listKey="countryId" listValue="countryName" value="defaultCountry"
                                              onchange="getStateList('SaveAddress_erpmsmad_countrymaster_countryId','SaveAddress_erpmsmad_statemaster_stateId')" />
                                    <s:select label="State" name="erpmsmad.statemaster.stateId" headerKey="" headerValue="-- Please Select --" list="stList" listKey="stateId" listValue="stateName"/>
                                    <s:textfield required="true" requiredposition="left" maxLength="50" size="50"
                                                 label="City" name="erpmsmad.adCity" title="" />
                                    <s:textfield requiredposition="left" maxLength="50" size="50"
                                                 label="Phone no" name="erpmsmad.adPhn" title="" />
                                    <s:textfield requiredposition="left" maxLength="50" size="50"
                                                 label="Mobile no" name="erpmsmad.adMob" title="" />
                                    <s:textfield requiredposition="left" maxLength="50" size="50"
                                                 label="Fax no" name="erpmsmad.adFaxn" title="" />
                                    <s:textfield requiredposition="left" maxLength="50" size="50"
                                                 label="Email id" name="erpmsmad.adEmail" title="" />
                                </td>
                            </tr> <tr>
                                <td>
                                </td>
                                <td>
                                    <s:submit theme="simple" name="btnSubmit" value="Save Address"/>
                                    <s:submit theme="simple" name="btnSubmit" value="Clear Address" action="ManageSupplierAddress"/>
                                    <s:submit theme="simple" name="btnSubmit" value="Browse Suppliers" action="BrowseSupplier" />
                                    <s:submit theme="simple" name="btnSubmit" value="Edit Supplier" action="EditSupplier"/>
                                <td>
                            </tr>
                            <tr>
                                <td> <br><br> </td>
                            </tr>                            
                        </tbody>
                    </table>
                </s:form>
       </div>
            </div>

            <div id ="mainContent" align="center">
                 <div style="border: solid 1px #000000; background:  gainsboro">
                <s:form name="frmSupplierAddressBrowse">
                    <s:hidden name="erpmsmad.supAdId" />
                    <s:hidden name="erpmsmad.suppliermaster.smId" />
                    <s:hidden name="ADID" />
                    <s:hidden name="SMID" />

                    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
                        <display:table name="adList" pagesize="15"
                                       excludedParams="*" export="true" cellpadding="0"
                                       cellspacing="0" id="doc"
                                       requestURI="/PrePurchase/BrowseAddressOnSamePage.action">
                            <display:column  class="griddata" title="S.No." style="width:3%" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>
                            <display:column property="suppliermaster.smName" title="SupplierName"
                                            maxLength="35" headerClass="gridheader"
                                            class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                            style="width:15%" sortable="true"/>
                            <display:column property="suppliermaster.smDealsWith" title="Deals With"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:15%" sortable="true"/>
                            <display:column property="adLine1" title="Line1"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:12%" sortable="true"/>
                            <display:column property="adLine2" title="Line2"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:12%" sortable="true"/>
                            <display:column property="adCity" title="City"
                                            maxLength="10" headerClass="gridheader"
                                            class="griddata" style="width:8%" sortable="true"/>
                            <display:column property="statemaster.stateName" title="State"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:8%" sortable="true"/>
<%--                            <display:column property="countrymaster.countryName" title="Country"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:10%" sortable="true"/>   --%>
                            <display:column property="adPhn" title="Phone"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:8%" sortable="true"/>
<%--                            <display:column property="adMob" title="Mob"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:30%" sortable="true"/>
                            <display:column property="adFaxn" title="Fax"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:30%" sortable="true"/>   --%>
                            <display:column property="adEmail" title="Email"
                                            maxLength="35" headerClass="gridheader"
                                            class="griddata" style="width:10%" sortable="true"/>
                            <display:column paramId="ADID" paramProperty="supAdId"
                                            href="/pico/PrePurchase/EditAddress"
                                            headerClass="gridheader" class="griddata" media="html" >
                                Edit
                            </display:column>
                            <display:column paramId="ADID" paramProperty="supAdId"
                                            href="/pico/PrePurchase/DeleteAddressOnSamePage.action"
                                            headerClass="gridheader" class="griddata" media="html">
                               Delete
                            </display:column>
                        </display:table>
                    </table>
                </s:form>
           
            </div>
            </div>
            <!--****************************End of Address.Jsp*************************************-->
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>