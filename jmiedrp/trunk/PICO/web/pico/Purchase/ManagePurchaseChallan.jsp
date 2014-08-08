<%--
    Document   : ManageChallan
    Created on : 3 Jun, 2011, 11:32:07 AM
    Author     : Tanvir Ahmed and Sajid and Mohd. Manauwar Alam
    I18n By    : Mohd. Manauwar Alam
               : March 2014
--%>

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
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Tanvir Ahmed, Jamia Millia Islamia">
        <meta name="email" content="tanvirahmed74@gmail.com">
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
            <div id ="mainContent" >
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br>
                <br>
                
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color:  #ffffff"><s:property value="getText('Purchase.ManagePurchaseChallanMaster')" /></p>
                    <p align="center" class="mymessage" style="color:  #ffff99 "><s:property value="message"/> </p>
                </div>
                <%--------------------this is a Purchase Challan Form --------------------%>

                <s:actionerror />
                <div style="border: solid 1px #000000; background: gainsboro">
                    <s:form name="FrmPurchaseChallan" action="SavePurchaseChallan" theme="qxhtml">
                        <%-- <p align="left" class="pageMessage"><s:property value="message" /></p>  --%>
                        <s:hidden name ="PChallanMast.pcmPcmId" />
                        <%--use where only number is required for input other than number it does not accept value--%>
                          <script type='text/javascript'>
     function isNumberKey(evt)
     {
     var charCode = (evt.which) ? evt.which : event.keyCode
     if (charCode > 31 && (charCode < 48 || charCode > 57))
     return false;
     return true;
     }


</script>

                        <table border="0" cellpadding="4" cellspacing="0" align="center" >
                            <tbody>

                                        <s:select key="Purchase.Institution" required="true" requiredposition="" name="PChallanMast.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" value="DefaultInsitute" cssClass="textInput"
                                                  onchange="getSubinstitutionList('SavePurchaseChallan_PChallanMast_institutionmaster_imId','SavePurchaseChallan_PChallanMast_subinstitutionmaster_simId')">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{7}" />
                                        </s:select>
                                        <s:select key="Purchase.SubInstitution" required="true" requiredposition="" name="PChallanMast.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName" value="DefaultSubInsitute"
                                                  onchange="getDepartmentList('SavePurchaseChallan_PChallanMast_subinstitutionmaster_simId','SavePurchaseChallan_PChallanMast_departmentmaster_dmId')">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:select>
                                        <s:label value=". . ." cssClass="tdSpace"/>
                                        <s:select key="Purchase.Department" required="true" requiredposition="" name="PChallanMast.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName"   value="DefaultDepartment">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:select>

                                         <s:textfield key="Purchase.RecdDate" required="true" requiredposition="left" maxLength="40" size="20"
                                                name="recieveDate" title="Enter Order"  cssClass="textInput">
                                                <s:param name="labelcolspan" value="%{1}" />
                                                <s:param name="inputcolspan" value="%{7}" />
                                         </s:textfield>

                            <%--            <sx:datetimepicker required="true" requiredposition="left" name="PChallanMast.pcmRecvDate" label="Recd. Date(dd-MM-yyyy)" displayFormat="dd-MM-yyyy" >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{7}" />
                                        </sx:datetimepicker>--%>

                                        <s:select  required="true" requiredposition="left" key="Purchase.PONo" name="PChallanMast.erpmPoMaster.pomPoMasterId" headerKey="0" headerValue="-- Please Select --" list="POMasterList" listKey="poid"
                                                   listValue="pono"  onchange="getSuppliersName('SavePurchaseChallan_PChallanMast_erpmPoMaster_pomPoMasterId','SavePurchaseChallan_PChallanMast_erpmPoMaster_suppliermaster_smName');"
                                                   ondblclick="getSuppliersName('SavePurchaseChallan_PChallanMast_erpmPoMaster_pomPoMasterId','SavePurchaseChallan_PChallanMast_erpmPoMaster_suppliermaster_smName');"
                                                   onselect="getSuppliersName('SavePurchaseChallan_PChallanMast_erpmPoMaster_pomPoMasterId','SavePurchaseChallan_PChallanMast_erpmPoMaster_suppliermaster_smName');" >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{1}" />
                                        </s:select>
                                        <s:submit name="btnSubmit" key="Purchase.ShowPO" action="ShowPOReport"/>


                                        <s:label value="." cssClass="tdSpace"/>
                                        <s:textfield  size="40" key="Purchase.SupplierName" name="PChallanMast.erpmPoMaster.suppliermaster.smName"    readonly="true">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{4}" />
                                        </s:textfield>

                                        <s:textfield required="true" requiredposition="left" maxLength="20" size="40" key="Purchase.ChallanNo" name="PChallanMast.pcmChallanNo">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{4}" />
                                        </s:textfield>

                                        <s:textfield key="Purchase.ChallanDate" required="true" requiredposition="left" maxLength="40" size=""
                                            name="challanDate" title="Enter Order" cssClass="textInput" >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:textfield>
                                        <%--   
                                        <sx:datetimepicker required="true" requiredposition="left" name="PChallanMast.pcmChallanDate" label="Challan Date(dd-MM-yyyy)" displayFormat="dd-MM-yyyy" >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </sx:datetimepicker>--%>


                                        <s:textfield maxLength="100" size="40" key="Purchase.ImportExchangeRate" name="PChallanMast.pcmImportExchangeRate"   onkeypress="return isNumberKey(event)">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{4}" />
                                        </s:textfield>

                                        <s:textarea cssClass="textArea"  rows="3" cols="40" key="Purchase.Remarks" name="PChallanMast.pcmRemarks"     maxLength="500">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{1}" />
                                        </s:textarea>
                                        <s:label value=". . ." cssClass="tdSpace"/>
                                        <s:textfield maxLength="50" size="45" key="Purchase.CheckedBy" name="PChallanMast.pcmCheckedBy" cssClass="textInput"  >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:textfield>

                                        <%--<s:select cssClass="textInput" label="Item Name" name="PCDetail.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --" list="itemlist" listKey="erpmimId" listValue="erpmimItemBriefDesc"
                                ondblclick="getitemList('SavePurchaseChallan_PCDetail_erpmItemMaster_erpmimId');"/>--%>


                                        <%--       <s:submit theme="simple" name="btnFetch" value="Browse Purchase Challan"  action="BrowsePurchaseChallan">--%>
                                        <tr>
                                </tr> <tr>
                                    <td align="left">
                                        <s:submit theme="simple" name="btnSubmit" key="Purchase.Save"/>
                                    </td>
                                    <td>
                                        <s:submit theme="simple" name="btnSubmit" key="Purchase.Browse"   action="BrowsePurchaseChallanMaster"/>

                                    </td>
                                    <td>
                                        <s:submit theme="simple" name="btnSubmit" key="Purchase.Clear"   action="ManagePurchaseChallanAction"/>

                                        <s:submit theme="simple" name="showGFRreport"  key="Purchase.ShowGFR" action="showGFRreportChallan" disabled="varShowGFR" />
                                    </td>
                                    
                                </tr>
                            </tbody>
                        </table>

                    </s:form>
                </div>
            </div>
            <br>
            <div id="footer" >
                 <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
