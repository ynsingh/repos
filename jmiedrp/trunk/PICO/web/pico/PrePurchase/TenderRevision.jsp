<%--
    Document   : TenderRevision
    Created on : 06 May, 2013, 10:29:11 AM
    Author     : Manauwar
--%>


<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content="Mohd. Manauwar Alam, Jamia Millia Islamia">
        <meta name="email" content="manauwar.alam@outlook.com">
        <meta name="copyright" content="NMEICT, MHRD, Govt. of India">
    </head>
    <body class="twoColElsLtHdr">
        <div id="container" >
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp"  flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" > <br><br>
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <div style ="background-color: #215dc6;" align="center">
                    <p align="center" class="pageHeading" style="color: #ffffff">TENDER REVISION</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                    <br>

                   <s:form name="frmTenderRevision" action="TenderRevision" theme="qxhtml">

                        <s:hidden name="etr.trTrId"/>
                        <script type='text/javascript'>
     function isNumberKey(evt)
     {
     var charCode = (evt.which) ? evt.which : event.keyCode
     if (charCode > 31 && (charCode < 48 || charCode > 57))
     return false;
     return true;
     }


</script>
                        
                       <table border="0" cellpadding="4" cellspacing="0" align="center">


                            <s:select label="Institution"  required="true"  requiredposition="" name="etr.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"
                                      onchange="getSubinstitutionList('TenderRevision_etr_institutionmaster_imId', 'TenderRevision_etr_subinstitutionmaster_simId');">
                                      <s:param name="labelcolspan" value="%{1}" />
                                      <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <s:label value="" cssClass="tdSpace"/>

                            <s:select  label="College/Faculty/School"  required="true" requiredposition="" name="etr.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName"
                                      onchange="getDepartmentList('TenderRevision_etr_subinstitutionmaster_simId','TenderRevision_etr_departmentmaster_dmId');" >
                                      <s:param name="labelcolspan" value="%{1}" />
                                      <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <s:select  label="Department Name" required="true" requiredposition="" name="etr.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --"
                                       list="dmList" listKey="dmId" listValue="dmName" >
                                      <s:param name="labelcolspan" value="%{1}" />
                                      <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                           <s:label value="" cssClass="tdSpace"/>
                           
                            <s:select required="true" label="Tender No " requiredposition="left" name="etr.erpmTenderMaster.tmTmId"
                                        headerKey="" headerValue="-- Please Select --" list="tmList" listKey="tmTmId" listValue="tmTenderNo"
                                        onchange="getTenderName('TenderRevision_etr_erpmTenderMaster_tmTmId','TenderRevision_TenderName');">
                                       <s:param name="labelcolspan" value="%{1}" />
                                       <s:param name="inputcolspan" value="%{3}" />
                           </s:select>

                            <s:textfield  maxLength="50" size="30" readonly="true" 
                                          label="Tender Name" name = "TenderName"  >
                                         <s:param name="labelcolspan" value="%{1}" />
                                         <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>

                             <s:label value="" cssClass="tdSpace"/>
                             
                            <s:textfield   required="true" requiredposition="left" maxLength="10" size="30"
                                          label="Revision No" name="etr.trRevisionNo" onkeypress="return isNumberKey(event)">
                                         <s:param name="labelcolspan" value="%{1}" />
                                         <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>  
                            
                             <s:select label="Revision Type"  required="true"  requiredposition="left" name = "etr.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --"
                                      list = "revTypeList" listKey = "erpmgmEgmId" listValue = "erpmgmEgmDesc">
                                      <s:param name="labelcolspan" value="%{1}" />
                                      <s:param name="inputcolspan" value="%{3}" />
                            </s:select>

                            <s:label value="" cssClass="tdSpace"/>

                            <s:textfield   size="30"  label="Date(dd-mm-yyyy)" name="revisionDate"  title="Enter the date " >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{3}" />
                            </s:textfield>
                            
                            <s:textarea name="etr.trRevisionDescription" label="Revision Decscription" cols="80" maxLength="200" >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{7}" />
                            </s:textarea>

                            <s:textfield   size="60" maxLength="100" label="Revision Document Link" name="etr.trRevisionLink"  >
                                <s:param name="labelcolspan" value="%{1}" />
                                <s:param name="inputcolspan" value="%{7}" />
                            </s:textfield> 
                            
                            <s:submit value="Save" action="SaveTenderRevision"/>
                            <s:submit value="Browse" action="BrowseTenderRevision"/>
                            <s:submit value="Clear" action="clearTenderRevision"/>
                        </table>
                    </s:form>
                </div>
                &nbsp;
            </div>

            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
