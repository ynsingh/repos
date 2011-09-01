
<%-- Document   : IndentForm
    Created on : April 19, 2011, 12:28:32 PM
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
        <title> ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
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
                          <p align="center"><s:label cssClass="pageHeading" value="MANAGE PURCHASE ORDER" /></p>

                <%--------------------this is a internal indent request form fill by internal users--------------------%>

                <s:actionerror/>

                    <s:form name="Frmpodetails" action="SavePODetails"  validate="true" >
                    <p align="left" class="pageMessage"><s:property value="message" /></p>
                    <s:hidden name ="podetail.podPodetailsId" />
                    <s:hidden name="pomaster.pomPoMasterId" />
                  
     <s:textfield  cssClass="textInputRO"  maxLength="10" size="10"
                   label="You are now Adding Detail PO  For The PO ID_NO:" name="defaultPOM" title="" readonly="true"/>
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="PO  Date" name="pomaster.pomPoDate" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="Insitute Name" name="pomaster.institutionmaster.imName" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="College/Faculty" name="pomaster.subinstitutionmaster.simName" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="Department" name="pomaster.departmentmaster.dmName" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="Supplier" name="pomaster.suppliermaster.smName" title="" readonly="true" maxLength="30" size="30" />
     <s:textfield  cssClass="textInputRO"  maxLength="50" size="50"
                     label="PO Approved By" name="pomaster.erpmusersByPomApprovedById.erpmuFullName" title="" readonly="true" maxLength="30" size="30" />
      

     <br><s:label value="Please Add  Item Details For Your Purchase Orders:" cssClass= "pageSubHeading"/>
     <s:select cssClass="textInput" label="Item Name" name="podetail.erpmItemMaster.erpmimId" headerKey="" headerValue="-- Please Select --" list="itemlist" listKey="erpmimId" listValue="erpmimItemBriefDesc"
               ondblclick="getitemList('SavePODetails_podetail_erpmItemMaster_erpmimId');"/>
     <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="10" size="30"
                  label="Quantity" name="podetail.podQuantity" title=""  value="0"/>
     <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="12" size="30"
                   label="Rate" name="podetail.podRate" title="" />
     <s:textfield cssClass="textInput" required="" requiredposition="left" maxLength="8" size="30"
                    label="Discount" name="podetail.podDiscount" title="" />
     <br>
     <s:submit cssClass="savebutton"  name="btnSubmit" value="Add Items" />    
                       </s:form>
            </div>
 <div id ="mainContent" align="center">
             <s:form name="frmPODetails">
                 <table width="60%" border="1" cellspacing="0" cellpadding="0" align="center" >
                    <tr><td>
                    <display:table name="podetailslist" pagesize="15"
                               excludedParams="*" export="true" cellpadding="0"
                               cellspacing="0" summary="true" id="doc"
                               requestURI="/PrePurchase/ManagePOMaster.action">
                        <display:column  class="griddata" title="Record"  sortable="true" maxLength="100" headerClass="gridheader">
                        <c:out> ${doc_rowNum}
                        </display:column>



                        <display:column property="erpmItemMaster.erpmimItemBriefDesc" title="Item Name"
                                    maxLength="80" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                   style="width:25%" sortable="true"  href=""/>


                         <display:column property="podQuantity" title="Quantity"
                                    maxLength="100" headerClass="gridheader"
                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>"
                                   style="width:25%" sortable="true"  href=""/>


                        <display:column property="podRate" title="Unit_Rate"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:25" sortable="true"/>

                        <display:column property="podDiscount" title="Discount"
                                    maxLength="100" headerClass="gridheader"
                                    class="griddata" style="width:25%" sortable="true"/>


                        <display:column paramId="PodetailsId" paramProperty="podPodetailsId"
                                    href="/pico/PrePurchase/DeletePoDetails.action?pomaster.pomPoMasterId=${param['pomaster.pomPoMasterId']}"
                                    headerClass="gridheader" class="griddata" media="html">
                                    <img align="left" src="../images/TrashIcon.png" border="0" alt="Delete"  style="cursor:pointer;"/>
                        </display:column>


                       <display:column paramId="PodetailsId" paramProperty="podPodetailsId"
                                    href="/pico/PrePurchase/EditPODetails.action?pomaster.pomPoMasterId=${param['pomaster.pomPoMasterId']}"
                                    headerClass="gridheader" class="griddata" media="html">
                           <img align="left" src="../images/edit.jpg" border="0" alt="Edit"  style="cursor:pointer;"/>
                        </display:column>

                    </display:table>
                <br></td></tr>
                </table>
                 <br>
                 <%--
                 
       <s:url action="POTerms?POMID=" id="NavigatetoURL"></s:url>
       <p  align="center"><a href="POTerms?pomaster.pomPoMasterId=${param['pomaster.pomPoMasterId']}"><B><BIG>--->GO TO PO TERMS AND CONDITIONS</BIG></B></a>   </p>--%>

           <s:url action="POTerms"   id="NavigatetoURL"></s:url>
           <p align="center"><a href='<s:property value="NavigatetoURL" />' ><big>--->GO TO PO TERMS AND CONDITIONS</big></a></p>

                     


                     <br>


             </s:form>
     <br>
     
    <%-- <s:submit cssClass="savebutton"  name="btnSubmit" value="Terms and Conditions"   action="POTerms" />--%>

            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>


