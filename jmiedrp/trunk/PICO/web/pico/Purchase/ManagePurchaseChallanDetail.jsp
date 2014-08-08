<%--
    Document   : ManagePurchaseChallanDetail
    Created on : 24 Jun, 2011, 3:20:50 PM
    Author     : Tanvir Ahmed, sajid and Mohd. Manauwar Alam
    I18n By    : Mohd. Manauwar Alam
               : March 2014
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Tanvir Ahmed, Jamia Millia Islamia">
        <meta name="email" content="tanvirahmed74@gmail.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <s:head />
    </head>
    <body class="twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <%@include  file="../Administration/header.jsp"%>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" align="left">
                <br><br>
                <s:actionerror />

                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>

                <div style="background-color: #215dc6;  " >
                    <p align="center" class="pageHeading" style="color:  #ffffff"> &nbsp;<s:property value="getText('Purchase.ManagePurchaseChallanDetail')" /></p>
                    <p  align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"  /> </p>
                </div>
                <div style="border: solid 1px #000000; background:  gainsboro">
                    <s:form name="FrmPurchaseChallanDetails" action="SavePurchaseChallanDetail" theme="qxhtml">


                        <s:hidden name ="PCDetail.pcdPcdId" />
                        <s:hidden name ="PChallanMast.pcmPcmId" />
                        <s:hidden name ="defaultPCM"/>
                        <s:hidden name="purchaseOrderNo"/>
                        <s:hidden name="subInstitutionName"/>
                        <s:hidden name="departmentName"/>
                        <s:hidden name="institutionName"/>


                        <%--use where only number is required for input other than number it does not accept value--%>
                        <script type='text/javascript'>
                            function isNumberKey(evt)
                            {
                                var charCode = (evt.which) ? evt.which : event.keyCode
                                if (charCode > 31 && (charCode < 48 || charCode > 57))
                                    return false;
                                return true;
                            }

                            function checkDropdown(choice) {
                                var error = "";
                                if (choice == 0) {
                                    error = "You didn't choose an option from the drop-down list.\n";
                                }
                                return error;
                            }

                            var frmvalidator  = new Validator("FrmPurchaseChallanDetails");
                            frmvalidator.addValidation("Quantity","req","Please enter Quantity");

                        </script>
                        <table border="0" cellpadding="4" cellspacing="0" align="center" >
                            <tbody>
                                <tr>
                                    <td>

                                        <%-- <s:textfield  cssClass="textInputRO"  maxLength="10" size="10"
                                       label="You are now Adding Details for the PC ID NO:" name="defaultPCM" title="" readonly="true"/>     --%>

                                        <s:textfield maxLength="30" size="30" key="Purchase.PONo"  value="%{purchaseOrderNo}"   cssClass="textInputRO" readonly="true" >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:textfield>
                                        <s:label value=". . ." cssClass="tdSpace"/>
                                        <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                                                      key="Purchase.ChallanNo" name="PChallanMast.pcmChallanNo" title="" readonly="true">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:textfield>
                                        <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                                                      key="Purchase.Institution" name="PChallanMast.institutionmaster.imName" title="" readonly="true" value="%{institutionName}" >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:textfield>
                                        <s:label value=". . ." cssClass="tdSpace"/>
                                        <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                                                      key="Purchase.SubInstitution" name="PChallanMast.subinstitutionmaster.simName" title="" readonly="true" value="%{subInstitutionName}" >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:textfield>
                                        <s:textfield  cssClass="textInputRO"  maxLength="50" size="50" value="%{departmentName}"
                                                      key="Purchase.Department" name="PChallanMast.departmentmaster.dmName" title="" readonly="true" >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{7}" />
                                        </s:textfield>
                                        <br><s:label value="%{getText('Purchase.ps_AddItemDetForPurChallan')}" cssClass= "pageSubHeading">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{8}" />
                                        </s:label>

                                        <s:select cssClass="textInput" key="Purchase.ItemName" name="PCDetail.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --" list="poditemlist" listKey="erpmItemMaster.erpmimId"
                                                  listValue="erpmItemMaster.erpmimItemBriefDesc" disabled="PcdDisable">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:select>
                                        <s:label value=". . ." cssClass="tdSpace"/>
                                        <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="8" size="10"  readonly="PcdDisable"
                                                     key="Purchase.Quantity" name="PCDetail.pcdRecvQuantity" title="" onkeypress="return isNumberKey(event)">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:textfield>
                                        <s:checkbox key="Purchase.HaveYouCheckedTheQuantity" name="checked" />
                                        <s:checkbox label="Have the items been verified" name="verified" />

                                        <s:textarea cssClass="textArea"  rows="3" cols="50" key="Purchase.CheckedAndVerifiedRemarks" name="CheckAndVerifiedRemarks"     maxLength="100">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{1}" />
                                         </s:textarea>

                                        <br>
                                    </td>
                                </tr>

                                <tr>
                                    <td align = "centre">
                                        <s:submit cssClass="savebutton"  name="btnSubmit" key="Purchase.AddItems" disabled="PcdDisable"/>

                                        <s:submit  cssClass="savebutton"  name="btnSubmit" key="Purchase.Clear" action="ClearPurchaseChallanDetail" />

                                        <s:submit  cssClass="savebutton"  name="btnSubmit" key="Purchase.SaveCheckVerify" action="SaveCheckAndVerify" disabled="btnCheckAndVerifySave" />
                                    </td>

                                </tr>
                            </tbody>
                        </table>
                    </s:form>
                <s:if test="PCDetailslist.size() > 0">
                        <hr>
                    <s:label value="%{getText('Purchase.ps_ChallanDetAre')}" cssClass= "pageSubHeading">
                        <s:param name="labelcolspan" value="%{0}" />
                        <s:param name="inputcolspan" value="%{11}" />
                    </s:label>
                    <hr>
                <s:form name="FrmPurchaseChallanDetails">

                   
                                <display:table name="PCDetailslist" decorator="Purchase.PurchaseDecorator"
                                               excludedParams="*" export="false" cellpadding="8"
                                               cellspacing="0" summary="true" id="doc"
                                               requestURI="/Purchase/ManagePurchaseChallanAction.action">
                                    <display:column  class="griddata" title="Record"  sortable="true" maxLength="100" headerClass="gridheader">
                                <c:out> ${doc_rowNum}
                                </display:column>

                                <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item Name"
                                                maxLength="100" headerClass="gridheader" style="width:30%"
                                                class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                                sortable="false"/>

                                <display:column property="pcdRecvQuantity" title="Quantity"
                                                maxLength="100" headerClass="gridheader" style="width:10%"
                                                class="griddata"
                                                sortable="false">
                                </display:column>


                                <display:column paramId="PCDETID" paramProperty="pcdPcdId"  maxLength="100" style="width:10%"
                                                href="/pico/Purchase/DeletePurchaseChallanDetail.action?PChallanMast.pcmPcmId=${param['PChallanMast.pcmPcmId']}"
                                                headerClass="gridheader" class="griddata" media="html" title="Delete" >
                                    Delete
                                </display:column>

                                <display:column paramId="PCDETID" paramProperty="pcdPcdId" maxLength="100" property="editView"
                                                href="/pico/Purchase/EditViewSerialDetail"
                                                headerClass="gridheader" class="griddata" media="html" title="Edit Item/View Serial Detail"/>
                                    


                                <display:column property = "checked" title="Checked" media="html" paramId="PCDETID" paramProperty="pcdPcdId"
                                                style="width:10%" headerClass="gridheader" href="/pico/Purchase/Check"
                                                class="griddata" sortable="true" />

                                <display:column property = "verified" title="Verified" media="html" paramId="PCDETID" paramProperty="pcdPcdId"
                                                style="width:10%" headerClass="gridheader" href="/pico/Purchase/Verify"
                                                class="griddata" sortable="true" />

                                </display:table>
                            
                    <br>
                </s:form>
             
                        </s:if>

                        </div>
                        </div>
                <br>
                <br>
                        <div id="footer">
                            <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
                        </div>
                </div>
                </body>
                </html>
