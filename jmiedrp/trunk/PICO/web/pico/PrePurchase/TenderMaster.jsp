
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html  >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
          <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
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
               
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">TENDER MASTER</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message"/></p>
                </div>


                <div style="border: solid 1px #000000; background:  gainsboro">
                    <s:form name="frmTender" action="TenderMasterAction" theme="qxhtml">
                        
                       
                       <s:hidden name="ermptendermaster.tmTmId" />
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
                        <s:property value="message"/>
                        <s:select label="Institution" required="true"  name="ermptendermaster.institutionmaster.imId" headerKey="0" headerValue="-- Please Select --" list="ImIdList" listKey="imId" listValue="imName" 
                                                cssClass="textInput" >
                        <s:param name="labelcolspan" value="%{2}" />
                        <s:param name="inputcolspan" value="%{2}" />
                        </s:select>
                        <s:select label="SubInstitution" required="true"  name="ermptendermaster.subinstitutionmaster.simId" headerKey="0" headerValue="-- Please select --" list="SimImIdList" listKey="simId" listValue="simName" 
                            onchange="getDepartmentList('TenderMasterAction_ermptendermaster_subinstitutionmaster_simId','TenderMasterAction_ermptendermaster_departmentmaster_dmId')" cssClass="textInput">
                        <s:param name="labelcolspan" value="%{2}" />
                        <s:param name="inputcolspan" value="%{2}" />
                        </s:select>
                         <s:label/>
                                  
                         <s:select required="true" label="Department" name="ermptendermaster.departmentmaster.dmId" headerKey="0" headerValue="-- Please Select --" list="DmList" listKey="dmId" listValue="dmName" cssClass="textInput"  > 
                         <s:param name="labelcolspan" value="%{2}" />
                         <s:param name="inputcolspan" value="%{2}" />
                         </s:select>  
                         <s:textfield label="Tender No" name="ermptendermaster.tmTenderNo" headerKey="0" headerValue="-- Please Select --" list="erpmIcmList1" listKey="erpmicmItemId" listValue="erpmicmCatDesc" onkeypress="return isNumberKey(event)">   <s:param name="labelcolspan" value="%{2}"  />
                         <s:param name="inputcolspan" value="%{2}" />
                         </s:textfield>
                         <s:label/>
                         
                         <s:textfield required="true" requiredposition="left" headerKey="0" maxLength="20" size="20" 
                                     label="Tender Date dd-MM-yyyy" name="tenDate" title="Enter Item Make" onfocus="addDate();" id="datetext"> 
                             
                         <s:param name="labelcolspan" value="%{2}" />
                         <s:param name="inputcolspan" value="%{2}" />
                         </s:textfield>
                         <s:textfield required="false" requiredposition="left" headerKey="0" maxLength="20" size="30"
                                     label="Tender Name" name="ermptendermaster.tmName" title="Enter Item Make">
                         <s:param name="labelcolspan" value="%{2}" />
                         <s:param name="inputcolspan" value="%{2}" />
                         </s:textfield>
                         <s:label/>
                          
                         <s:select label="Tender Type" name="ermptendermaster.erpmGenMasterByTmTypeId.erpmgmEgmId" headerKey="0" headerValue="-- Please Select --" list="tendertypeList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc">
                         <s:param name="labelcolspan" value="%{2}" />
                         <s:param name="inputcolspan" value="%{2}" />
                         </s:select>
                         <s:textfield required="false" requiredposition="left" maxLength="30" headerKey="0" size="30"
                                     label="Estimated Amount" name="ermptendermaster.tmEstimatedAmount" title ="Enter Item Make" onkeypress="return isNumberKey(event)">
                         <s:param name="labelcolspan" value="%{2}" />
                         <s:param name="inputcolspan" value="%{2}" />
                         </s:textfield>
                         <s:label/>
                         
                         <s:textfield required="false" requiredposition="left" maxLength="20" headerKey="0" size="20"
                                     label="Tender Fee" name="ermptendermaster.tmFee" title="Enter Item Make" onkeypress="return isNumberKey(event)">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                         </s:textfield>
                         <s:textfield required="false" requiredposition="left" maxLength="20" headerKey="0" size="20"
                                     label="EMD Amount" name="ermptendermaster.tmEmdAmount" title="Enter Item Make" onkeypress="return isNumberKey(event)">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>
                        <s:label/>
                        
                        <s:select label="Status" name="ermptendermaster.erpmGenMasterByTmStatusId.erpmgmEgmId" headerKey="0" headerValue="-- Please Select --" list="tendertypeList1" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc">
                        <s:param name="labelcolspan" value="%{2}" />
                        <s:param name="inputcolspan" value="%{2}" />
                        </s:select>
                         
                      
                         <s:textfield required="false" requiredposition="left" maxLength="40" headerKey="0" size="50"
                                     label="Notice Link" name="ermptendermaster.tmNoticeLink" title="Enter Item Make">
                         <s:param name="labelcolspan" value="%{2}" />
                         <s:param name="inputcolspan" value="%{2}" />
                         </s:textfield>
                        <s:label/>
                       
                        <s:textfield required="false" requiredposition="left" maxLength="50" headerKey="0" size="50"
                                     label="Document Link" name="ermptendermaster.tmDocumentLink" title="Enter Remarks, if any">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>
                        <s:label/>
                        
                        <s:textfield required="false" requiredposition="left" maxLength="50" headerKey="0" size="30"
                                     label="Remarks" name="ermptendermaster.tmRemarks" title="Enter Item Make">
                        <s:param name="labelcolspan" value="%{2}" />
                        <s:param name="inputcolspan" value="%{4}" />
                        </s:textfield>
                        <s:label/>
                           
                              <s:label/>
                                 
                              <s:label/>
                         <s:submit  name="btnClear" value="SubmittedReport"   action="SubmittedTendersReportAction">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{1}" />
                        </s:submit>     
                        
                                
                       
                        <s:submit name="btnSubmit" value="Save Item" action="SaveTender">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{1}" />
                        </s:submit>
                        <s:submit name="btnBrowse" value="Browse Items"  action="BrowseTender">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{1}" />
                        </s:submit>         
                           
                        <s:submit name="btnClear" value="Clear" action="ClearTender">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{1}" />
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