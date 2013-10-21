<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html  >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/ItemMasterScript.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
        <s:head />
    </head>
    <body class="twoColElsLtHdr">

        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp"  flush="true"></jsp:include>
                </div>

                <div id="sidebar1">
                <jsp:include page="../Administration//menu.jsp" flush="true"></jsp:include>
                </div>

                <jsp:include page="../Administration//jobBar.jsp" flush="true"></jsp:include>
                
                <!-- *********************************End Menu****************************** -->

                <div id ="mainContent">

                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">TENDER SUBMISSION</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>


                <div style="border: solid 1px #000000; background:  gainsboro">
                    <s:form name="frmTenderSubmission" action="SaveTenderSubmissionAction" theme="qxhtml">
                        
                        <s:hidden name="erpmtsb.tsbTsbId" />
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
                       

                        <s:label value="..." cssClass="tdSpace"/>
                       
                        
                        
                      <s:select required="true" label="Institution" requiredposition="left" name="erpmtsb.institutionmaster.imId" 
                       headerKey="0" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" 
                           onchange="getSubinstitutionList('SaveTenderSubmissionAction_erpmtsb_institutionmaster_imId','SaveTenderSubmissionAction_erpmtsb_subinstitutionmaster_simId');">
                 
                      <s:param name="labelcolspan" value="%{2}" />
                      <s:param name="inputcolspan" value="%{2}" />
                      </s:select>                           
                       
                      <s:select  required="true" requiredposition="left" cssClass="textInput" label="Subinstitute" name="erpmtsb.subinstitutionmaster.simId" headerKey="0" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName" 
                      onchange="getDepartmentList('SaveTenderSubmissionAction_erpmtsb_subinstitutionmaster_simId','SaveTenderSubmissionAction_erpmtsb_departmentmaster_dmId');">
                       <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
                      </s:select> 
            <s:label/>
 
                       <s:select required="true"  cssClass="textInput" label="Department" name="erpmtsb.departmentmaster.dmId" headerKey="0" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName"  >
                       <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
                       </s:select> 
            
                       <s:select required="true" label="Tender No " requiredposition="left" name="erpmtsb.erpmTenderMaster.tmTmId"
                       headerKey="0" headerValue="-- Please Select --" list="tmList" listKey="tmTmId" listValue="tmTenderNo"
                       onchange="getTenderName('SaveTenderSubmissionAction_erpmtsb_erpmTenderMaster_tmTmId','SaveTenderSubmissionAction_TenderName');" >
                        <s:param name="labelcolspan" value="%{2}" />
                        <s:param name="inputcolspan" value="%{2}" />
                        </s:select>     
                 <s:label/>
                 
                       <s:textfield   required="true" requiredposition="left" maxLength="50" size="30" readonly="true"
                       label="Tender Name" name="TenderName" headerKey="0" title="seleted from tender master">
                        <s:param name="labelcolspan" value="%{2}" />
                       <s:param name="inputcolspan" value="%{2}" />
                       </s:textfield>
            
                         <s:textfield required="true" requiredposition="left" maxLength="100" size="30"
                                     label="Company Name " name="erpmtsb.tsbCompanyName" headerKey="0" title="Enter Item Make" >
                         <s:param name="labelcolspan" value="%{2}" />
                          <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>
                       <s:label/>
                        <s:textfield required="true" requiredposition="left" rows="2" maxLength="50" size="30"
                                     label="E-mail Address " name="erpmtsb.tsbCompanyEmail" headerKey="0" title="Enter Item Model">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                      
                        <s:textarea required="false" requiredposition="left" rows="2" cols="40" maxLength="50"
                                     label="Company Address " name="erpmtsb.tsbCompanyAddress" headerKey="0" title="Enter Item Make">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textarea>
                        <s:label/>
                          <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                     label="TenderSubmission Date" name="tendersubmission" title="Enter date of submission">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>
                          
                        
                      <s:label/>
                         <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                     label="Phone " name="erpmtsb.tsbCompanyPhone" headerKey="0"   onkeypress="return isNumberKey(event)" title="Enter Item Make">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>
                        
                         <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                     label="EMD Amount " name="erpmtsb.tsbEmdAmount"  onkeypress="return isNumberKey(event)" title="Enter Item Make">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>
                       <s:label/>
                       
                        <s:select label="EMD Type " name="erpmtsb.erpmGenMaster.erpmgmEgmId" headerKey="0" headerValue="-- Please Select --" list="emdtypeList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" >
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>
                     
                             <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                     label="EMD Bank " name="erpmtsb.tsbEmdBankName" title="Enter Item Make">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>
                        <s:label/>
                         <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                     label="EMD BG/DD NO " name="erpmtsb.tsbBgDdNo"  onkeypress="return isNumberKey(event)" title="Enter Item Make">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>
                         
                         
                        <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                     label="BG/DD Validity Date" name="erpmtsb.tsbBgDdValidityDate" title="Enter Remarks, if any">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>
                        
                      <s:label/>
                           <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                     label="Tender Fee " name="erpmtsb.tsbTenderFee"  onkeypress="return isNumberKey(event)" title="Enter Item Make">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>
                        <s:select label="Tender Fee Mode " required="true" name="erpmtsb.tsbDdCash" headerKey="0" headerValue="-- Please Select --" list="#{'Cash':'Cash','Demand Draft':'Demand Draft'}"  cssClass="queryInput"  >
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                        </s:select>
                        <%-- value="modevalue"  value="emdreturn"  --%>
                        <s:label/>
                        <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                     label="DD no/Cash Receipt No " name="erpmtsb.tsbDdCashReceiptNo"  title="Enter Remarks, if any">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>
                       
                    
                          <s:label/>
                      
                           <s:select label="EMD Returned" required="true" name="erpmtsb.tsbEmdReturned" headerKey="0" headerValue="-- Please Select --" list="#{'Y':'YES','N':'NO'}"  cssClass="queryInput"  >
                                            <s:param name="labelcolspan" value="%{2}" />
                                            <s:param name="inputcolspan" value="%{2}" />
                                        </s:select>
                       
                      <s:textfield required="false" requiredposition="left" maxLength="20" size="20"
                                     label="EMD Return Date" name="erpmtsb.tsbEmdReturnDate" title="Enter Item Make">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>
                            
                          <s:label/>
                        <s:textfield required="false" requiredposition="left" maxLength="30" size="20"
                                     label="EMD Return File Refrence " name="erpmtsb.tsbEmdReturnFileReference" title="Enter Remarks, if any">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>
                        <s:label/><s:label/><s:label/>
                        <br><br>
                    
                          
                    
                         <tr><td> 
                      <s:submit name="btnSubmit" value="Save Item" action="SaveTenderSubmissionAction">
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit> 
                        
                   
                        <s:submit name="btnBrowse" value="Browse Items"  action="BrowseTenderSubmissionAction">
                            <s:param name="colspan" value="%{1}" />
                        </s:submit> 
                      <s:submit name="btnClear" value="Clear" action="ClearTenderSubmissionAction">
                            <s:param name="colspan" value="%{1}" />
                        </s:submit> 
                        <s:if test="%{tsfTsfId!=0}">
                      <s:submit name="btnPrint" value="Upload Submission File"  action="UploadSubmissionFile">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit> 
                      </s:if>
                         <s:submit name="btnPrint" value="Show GFR"  action="PrintItems">
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit> 
            
                       
                    </s:form>
                    <br>
                </div>
             </div>
            <br>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div> 
    </body>
</html>