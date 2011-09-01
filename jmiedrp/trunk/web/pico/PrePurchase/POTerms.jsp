<%-- Document   : Purchase Order Master form
    Created on : May  2011, 12:28:32 PM
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
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" >
                             <p align="center"><s:label cssClass="pageHeading" value="MANAGE PO TERMS AND CONDITION" /></p>
                 <%--------------------this is a Purchase Order Terms and conditon form --------------------%>


                <s:actionerror/>

                  <s:form name="FrmpoTerms"   action="SavePOTerms">
                  <p align="left" class="pageMessage"><s:property value="message" /></p>
                  <s:hidden name ="pomaster.pomPoMasterId" />
                  <s:hidden name="Gterms.gtGtid"/>
                  <s:property value="epoterms.potPotId"/>

     <s:textfield  cssClass="textInputRO"  maxLength="10" size="10" label="You are now Adding Po Terms & conditon for the PO No:" name="defaultPOM" title="" readonly="true"/>

     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="PO  Date" name="pomaster.pomPoDate" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="Insitute Name:" name="pomaster.institutionmaster.imName" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="College/Faculty" name="pomaster.subinstitutionmaster.simName" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="Department" name="pomaster.departmentmaster.dmName" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="Supplier" name="pomaster.suppliermaster.smName" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="PO Approved By" name="pomaster.erpmusersByPomApprovedById.erpmuFullName" title="" readonly="true" maxLength="30" size="30" />

      <s:select cssClass="textInput" label="PO Terms" name="epoterms.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="potermslist" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"
                onchange="getpoterms('SavePOTerms_epoterms_erpmGenMaster_erpmgmEgmId', 'SavePOTerms_epoterms_potTermsDescription')"/>

     <s:textarea rows="10" cols="100" label="Terms And Condition" name="epoterms.potTermsDescription"/>

     <br>
      <s:submit cssClass="savebutton"  name="btnSubmit" value="Save Terms and Condition" action="SavePOTerms" />

      <s:submit cssClass="savebutton"  name="btnSubmit" value="Get All Default PO Terms" action="GetDefaultGeneralTerms" />


  </s:form>

            </div>


             <div id ="mainContent" align="center">
             <s:form name="frmpotermsBrowse">
                 <table width="60%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="erpotermlist" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/PrePurchase/ManagePOMaster.action">
                        <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>


                        <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Terms_Type"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                    sortable="true"  href=""/>

                        <display:column property="potTermsDescription" title="PO_Terms_Description"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                   style="width:30%" sortable="true"  href=""/>


                        <display:column paramId="PotpoId" paramProperty="potPotId"
                                    href="/pico/PrePurchase/DeletePoTerms.action?pomaster.pomPoMasterId=${param['pomaster.pomPoMasterId']}"
                                    headerClass="gridheader" class="griddata" media="html">
                                    <img align="left" src="../images/TrashIcon.png" border="0" alt="Delete"  style="cursor:pointer;"/>
                        </display:column>


                      <display:column paramId="PotpoId" paramProperty="potPotId"
                                    href="/pico/PrePurchase/EditPoTerms.action?pomaster.pomPoMasterId=${param['pomaster.pomPoMasterId']}"
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

