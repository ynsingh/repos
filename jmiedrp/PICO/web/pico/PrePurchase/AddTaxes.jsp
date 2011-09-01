<%--
    Document   : IndentForm
    Created on : Feb 1, 2011, 12:28:32 PM
    Author     : Sajid Aziz
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="html" uri="/struts-tags"%>
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
            <div id ="mainContent" >
                <p align="center"><s:label cssClass="pageHeading" value="MANAGE ITEM RATES TAXES" /></p>

                <%--------------------this is a internal indent request form fill by internal users--------------------%>

                 <s:form name="Frmitemratetaxes" action="SaveIndentRateTaxes"  validate="true" >
                    <p align="left" class="pageMessage"><s:property value="message" /></p>
                    <s:hidden name ="itemrate.irItemRateId" />
                    <s:hidden name="itemratedet.irdItemRateDetailsId" />
                  <s:hidden name="itemratetax.irtItemRateTaxesId" />
                    

                <s:textfield  cssClass="textInputRO"  maxLength="10" size="10"
               label="You are now Adding Items  Rate Taxes For The Item Rate ID_NO:" name="defaultRate" title="" readonly="true"/>

       
 

          <s:textfield cssClass="textInputRO" required="" requiredposition="left" maxLength="50" size="50"
                       label="Item Name" name="itemrate.erpmItemMaster.erpmimItemBriefDesc" readonly="True"   disabled="True"/>


          <s:textfield cssClass="textInputRO" required="" requiredposition="left" maxLength="50" size="50"
                       label="Supplier Name" name="itemrate.suppliermaster.smName" readonly="True" />

        
          <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="50" size="50"
                    label="Tax Name" name="itemratetax.irtTaxName" title="" />

          <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="50" size="50"
                    label="Tax Percent" name="itemratetax.irtTaxPercent" title="" />

          <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="50" size="50"
                    label="Tax on Value Percent" name="itemratetax.irtTaxOnValuePercent" title="" />

          <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="50" size="50"
                    label="Surcharge Percent" name="itemratetax.irtSurchargePercent" title="" />

          <s:submit cssClass="savebutton"  name="btnSubmit" value="Save" />



                       </s:form>
            </div>


             <div id ="mainContent" align="center">
             <s:form name="frmitemratetaxrowse">
                 <table width="60%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="itemratetaxlist" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/PrePurchase/ManageItemRates.action">
                        <display:column  class="griddata" title="Record" style="width:40%" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>

                        <display:column property="irtTaxName" title="Tax Name"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                   style="width:30%" sortable="true"  href=""/>
                       <display:column property="irtTaxPercent" title="Tax %"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="true"/>

                       <display:column property="irtTaxOnValuePercent" title="Taxon Value %"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="true"/>

                       <display:column property="irtSurchargePercent" title="Surcharge %"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:40%" sortable="true"/>

                   
                        <display:column paramId="IrtItemRateTaxesId" paramProperty="irtItemRateTaxesId"
                                    href="/pico/PrePurchase/DeleteIndentRateTaxes.action?itemratedet.irdItemRateDetailsId=${param['itemratedet.irdItemRateDetailsId']}"
                                    headerClass="gridheader" class="griddata" media="html">
                                    <img align="left" src="../images/TrashIcon.png" border="0" alt="Delete"  style="cursor:pointer;"/>
                        </display:column>



                        <display:column paramId="IrtItemRateTaxesId" paramProperty="irtItemRateTaxesId"
                                    href="/pico/PrePurchase/EditIndentRateTaxes.action?itemratedet.irdItemRateDetailsId=${param['itemratedet.irdItemRateDetailsId']}"
                                    headerClass="gridheader" class="griddata" media="html">
                                    <img align="left" src="../images/edit.jpg" border="0" alt="Edit"  style="cursor:pointer;"/>
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


