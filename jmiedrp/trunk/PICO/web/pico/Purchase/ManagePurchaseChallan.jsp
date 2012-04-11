<%--
    Document   : ManageChallan
    Created on : 3 Jun, 2011, 11:32:07 AM
    Author     : Tanvir Ahmed and Sajid
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
                <br><br>
            <p align="center"><s:label cssClass="pageHeading" value="MANAGE PURCHASE CHALLAN MASTER" /></p>
                 <%--------------------this is a Purchase Challan Form --------------------%>

            <s:actionerror />

            <s:form name="FrmPurchaseChallan" action="SavePurchaseChallan" >
                    <p align="left" class="pageMessage"><s:property value="message" /></p>
                    <s:hidden name ="PChallanMast.pcmPcmId" />
                    
<%--use where only number is required for input other than number it does not accept value
                          <script type='text/javascript'>
              function isNumberKey(evt)
      {
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }
   </script>
    --%>
 <table border="0" cellpadding="4" cellspacing="0" align="center" >
                    <tbody>
                    <tr>
                    <td>
                    <s:select label="Institution" name="PChallanMast.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" value="DefaultInsitute"
                                onchange="getSubinstitutionList('SavePurchaseChallan_PChallanMast_dba_institutionmaster_imId','SavePurchaseChallan_PChallanMast_dba_subinstitutionmaster_simId')" cssClass="textInput" />
                       <s:select label="College/Faculty/School" name="PChallanMast.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName" value="DefaultSubInsitute"
                                onchange="getDepartmentList('SavePurchaseChallan_PChallanMast_dba_subinstitutionmaster_simId','SavePurchaseChallan_PChallanMast_dba_departmentmaster_dmId')"  cssClass="textInput"/>
                       <s:select label="Department" name="PChallanMast.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName"  cssClass="textInput" value="DefaultDepartment"/>
                       <sx:datetimepicker required="true" requiredposition="left" name="PChallanMast.pcmRecvDate" label="Recd. Date(yyyy-mm-dd)" displayFormat="dd-MM-yyyy" value="%{'today'}"  cssClass="textInput" />
                       <s:select required="true" requiredposition="left" label="P.O. No." name="PChallanMast.erpmPoMaster.pomPoMasterId" headerKey="" headerValue="-- Please Select --" list="POMasterList" listKey="pomPoMasterId" listValue="pomPoNo" cssClass="textInput"/>
                       <s:textfield required="true" requiredposition="left" maxLength="100" size="50" label="Challan No." name="PChallanMast.pcmChallanNo"    cssClass="textInput"/>
                       <sx:datetimepicker required="true" requiredposition="left" name="PChallanMast.pcmChallanDate" label="Challan Date(yyyy-mm-dd)" displayFormat="dd-MM-yyyy" value="%{'today'}"  cssClass="textInput" />
                       <s:textfield maxLength="100" size="50" label="Import Exchange Rate" name="PChallanMast.pcmImportExchangeRate"    cssClass="textInput" onkeypress="return isNumberKey(event)"/>
                       <s:textarea cssClass="textArea"  rows="2" cols="50"label="Remarks" name="PChallanMast.pcmRemarks"    cssClass="textInput"/>
                       <s:textfield maxLength="100" size="50" label="Checked By" name="PChallanMast.pcmCheckedBy"    cssClass="textInput" />

                       <%--<s:select cssClass="textInput" label="Item Name" name="PCDetail.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --" list="itemlist" listKey="erpmimId" listValue="erpmimItemBriefDesc"
               ondblclick="getitemList('SavePurchaseChallan_PCDetail_erpmItemMaster_erpmimId');"/>--%>


              <%--       <s:submit theme="simple" name="btnFetch" value="Browse Purchase Challan"  action="BrowsePurchaseChallan">--%>
                      </td>
                </tr> <tr>
                <td align="right">
                     <s:submit theme="simple" name="btnSubmit" value="Save & Add Challan Detail"/>
                    </td>
                <td>
                     <s:submit theme="simple" name="btnClear" value="Clear"   action="ManagePurchaseChallanAction"/>
 </td>
                </tr>
                </tbody>
        </table

            </s:form>
            </div>
                <div id="footer" >
                    <jsp:include page="footer.jsp" flush="true" ></jsp:include>
                </div>
            </div>
       </body>
</html>
