<%--
    Document   : IndentForm
    Created on : Feb 1, 2011, 12:28:32 PM
    Author     : Sajid Aziz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="html" uri="/struts-tags"%>
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
            <div id ="mainContent">
            <br><br>
            <div style ="background-color: #215dc6;">
            	<p align="center"><s:label cssClass="pageHeading" value="MANAGE ITEM RATES TAXES" /></p>
           	<s:actionerror/>
	    </div>
            	<p align="left" class="pageMessage"><s:property value="message" /></p> <br>
            <s:form name="Frmitemratetaxes" action="SaveItemRateTaxes" >
            <s:hidden name="itemRateTax.irtItemRateTaxesId" />
            <s:hidden name ="itemrate.irItemRateId" />

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
     
            <table border="0" cellpadding="4" cellspacing="0">
                    <tbody>
                    <tr>
                        <s:textfield cssClass="textInputRO"  size="30" label="Supplier Name" name="itemrate.suppliermaster.smName" readonly="true"/>
                        
                        <s:select  cssClass="textInput" label="Tax Name" name="itemRateTax.erpmGenMaster.erpmgmEgmId" headerKey=""
                                   headerValue="-- Please Select --" list="taxList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" />
                        
                        <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="6" size="30"
                                     onkeypress="return isNumberKey(event)"  label="Tax Percent" name="itemRateTax.irtTaxPercent" title=""  />
                                            
                        <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="6" size="30"
                                     onkeypress="return isNumberKey(event)" label="Tax on Value Percent"
                                     name="itemRateTax.irtTaxOnValuePercent" value="100" title="" />

                        <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="6" size="30"
                                     onkeypress="return isNumberKey(event)" label="Surcharge Percent"
                                     name="itemRateTax.irtSurchargePercent" value="0" title=""  />
                    </tr> <tr>
                    <td align="right">
                    
                    </td>
                    <td>
                        <s:submit theme="simple" cssClass="savebutton"  name="btnSubmit" value="Save" />
                        <s:submit theme="simple" cssClass="savebutton"  name="btnSubmit" value="Go To Item Rate " action="FetchItemRatesDetailsFromTaxex"/>
                    </td>
                    </tr>
                    </tbody>
              </table>
            </s:form>
            </div>
            <div id ="mainContent">
            <s:form name="frmitemratetaxrowse" align="left">
              <table width="65%" border="1" cellspacing="0" cellpadding="0" >
                    <tr><td>
                    <display:table name="itemratetaxlist" pagesize="10"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0"  id="doc"
                               requestURI="/PrePurchase/ManageItemRates.action">
                    <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                    </display:column>

                    <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Tax Name"
                        headerClass="gridheader" style="width:30%"
                        class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                        sortable="true"  href=""/>
                    <display:column  property="irtTaxPercent" title="Tax %"
                        headerClass="gridheader"
                        class="griddata"  sortable="true"/>
                    <display:column  property="irtTaxOnValuePercent" title="Tax on Value %"
                        headerClass="gridheader"
                        class="griddata" sortable="true"/>
                    <display:column  property="irtSurchargePercent" title="Surcharge %"
                        headerClass="gridheader"
                        class="griddata" sortable="true"/>
                    <display:column paramId="IrtItemRateTaxesId" paramProperty="irtItemRateTaxesId" title="Delete"
                        href="/pico/PrePurchase/DeleteItemRateTaxes.action?itemrate.irItemRateId=${param['itemrate.irItemRateId']}"
                        headerClass="gridheader" class="griddata" media="html">
                        Delete
                    </display:column>
                    <display:column paramId="IrtItemRateTaxesId" paramProperty="irtItemRateTaxesId" title="Edit"
                        href="/pico/PrePurchase/EditItemRateTaxes.action?itemrate.irItemRateId=${param['itemrate.irItemRateId']}"
                        headerClass="gridheader" class="griddata" media="html">
                        Edit Tax
                    </display:column>
                  

                    </display:table>
                <br></td></tr>
                </table>
             </s:form>
                 <br>
            </div>
            
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
