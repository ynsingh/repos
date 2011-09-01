<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>

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
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" >


                <p align="center"><s:label value="SUPPLIER MASTER" cssClass="pageHeading"/></p>
                <p align="center"><s:property value="message" /></p>

                <s:actionerror />

                <s:form name="frmSupplier" action="SaveSupplier"  >

                    <s:hidden name="erpmsm.smId" />
                    <s:hidden name="supad.supAdId" />
                    <s:hidden name="SMID" />

<table border="0" cellpadding="4" cellspacing="0" align="center">
                        <tbody>
                            <tr>
                                <td><s:submit theme="simple" name="btnSubmit" value="Browse Suppliers" action="BrowseSupplier" />
                                    <s:select label="Institution" name="erpmsm.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName"/></td>
                                <td>
                                    <s:textfield required="true" requiredposition="left" maxLength="100" size="50"
                                                 label="Supplier Name" name="erpmsm.smName" title="Supplier Name" /></td>
                                    <s:textfield requiredposition="left" maxLength="100" size="50"
                                                 label="CEO/Proprietor Name" name="erpmsm.smCeoOrProprietorName" title="" />
                            </tr>
                            <tr>
                                <td><s:select label="Supplier Type" name="erpmsm.erpmGenMasterBySmSupplierType.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="gmIdList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"/>

                                </td>
                                <td>
                                    <s:select label="Owner Type" name="erpmsm.erpmGenMasterBySmOwnershipType.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="gmIdList1" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"/>
                                </td>
                                <td>    <s:textarea required="" requiredposition="left"
                                            label="Deals with" name="erpmsm.smDealsWith" title="" />
                                </td>
                            </tr>
                            <tr>
                                <td><sx:datetimepicker name="erpmsm.smYearEstablishment" label="Year of Establishment(yyyy)"
                                                   displayFormat="yyyy" value="%{'today'}" /></td>
                                <td><s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="PAN No." name="erpmsm.smPanNo" title="" /></td>

                                <td><s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="TAN No." name="erpmsm.smTanNo" title="" /></td>
                            </tr>
                            <tr>
                                <td><s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="State Vat Reg No" name="erpmsm.smStateStVatRgnNo" title="" /></td>
                                <td> <s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="Central Vat Reg No" name="erpmsm.smCenStVatRgnNo" title="" /></td>
                                <td><s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="Exercise Duty Reg No" name="erpmsm.smEdRgnNo" title="" /></td>
                            </tr>
                            <tr>
                                <td><s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="ECC Code" name="erpmsm.smEccCode" title="" /></td>

                                <td> <s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="Entreprenure No" name="erpmsm.smEntreprenureMemNo" title="" /></td>

                                <td><sx:datetimepicker name="erpmsm.smRegDate" label="Enter Date of Reg " displayFormat="dd-MMM-yyyy" value="%{'today'}"/>
                                </td
                            </tr>
                            <tr>
                                <td><s:textfield required="" requiredposition="left" maxLength="100" size="50"
                                             label="Remarks" name="erpmsm.smRemarks" title="" /></td>
                            </tr><br><br>
                        <tr><td>
                                <s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="Primary Contact Address" name="supad.adLine1" title="" />
                                <s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label=" " name="supad.adLine2" title="" />
                                <s:select label="Country" required="true" name="supad.countrymaster.countryId" headerKey="" headerValue="-- Please Select --" list="ctList" listKey="countryId" listValue="countryName"  value="defaultCountry"
                                            onchange="getStateList('SaveSupplier_supad_countrymaster_countryId','SaveSupplier_supad_statemaster_stateId')" />
                                <s:select label="State" name="supad.statemaster.stateId" headerKey="" headerValue="-- Please Select --" list="stList" listKey="stateId" listValue="stateName"/>
                                <s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="City" name="supad.adCity" title="" />
                                <s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="Phone no." name="supad.adPhn" title="" />
                                <s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="Mob no." name="supad.adMob" title="" />
                                <s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="Fax no." name="supad.adFaxn" title="" />
                                <s:textfield required="" requiredposition="left" maxLength="50" size="50"
                                             label="Email id" name="supad.adEmail" title="" />
                            </td>
                        </tr> <tr>
                            <td>
                            </td>
                            <td>
                                <s:submit theme="simple" name="btnSubmit" value="Save Supplier"  />

                                <s:submit theme="simple" name="btnSubmit" value="Clear" action="ManageSupplier" />
                               
                            <td>
                        </tr>
                        <tr>
                            <td> <br><br> </td>
                        </tr>
                        <tr>
                            <td> <br><br> </td> </tr>

                        </tbody>
                    </table>
                    
                </s:form>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>


