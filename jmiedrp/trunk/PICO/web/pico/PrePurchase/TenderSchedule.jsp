<%-- 
    Document   : TenderSchedule
    Created on : 22 Mar, 2013, 10:29:11 AM
    Author     : Saeed
    I18n By    : Mohd. Manauwar Alam
               : Feb 2014
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
        <meta name="author" content="S.Kazim Naqvi, Jamia Millia Islamia">
        <meta name="email" content="sknaqvi@jmi.ac.in">
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
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('PrePurchase.TenderSchedule')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">
                    <br>

                    <s:form name="frmTenderScheduleAction" action="TenderScheduleAction"  theme="qxhtml">
                       
                         <s:hidden name="tenschdl.tscTscId"/>
                        <table border="0" cellpadding="4" cellspacing="0" align="center" >
                            <tbody>

                                <s:select key="PrePurchase.Institution" required="true" requiredposition="" name="tenschdl.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName"
                                          cssClass="textInput"
                                          onchange="getSubinstitutionList('TenderScheduleAction_tenschdl_institutionmaster_imId', 'TenderScheduleAction_tenschdl_subinstitutionmaster_simId');">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:select>

                                <s:select key="PrePurchase.SubInstitution" required="true" requiredposition="" name="tenschdl.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName"
                                              cssClass="textInput"
                                              onchange="getDepartmentList('TenderScheduleAction_tenschdl_subinstitutionmaster_simId', 'TenderScheduleAction_tenschdl_departmentmaster_dmId');">
                                        <s:param name="labelcolspan" value="%{3}" />
                                        <s:param name="inputcolspan" value="%{2}" />
                                </s:select>

                                <s:select key="PrePurchase.Department" required="true" requiredposition=""  name="tenschdl.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName"  cssClass="textInput">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:select>

                                <s:select key="PrePurchase.TenderNo" required="true" requiredposition=""  name="tenschdl.erpmTenderMaster.tmTmId" headerKey="" headerValue="-- Please Select --" list="tnoList" listKey="tmTmId" listValue="tmTenderNo"  cssClass="textInput">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:select>

                                <s:textarea cssClass="textArea" rows="3" cols="40" key="PrePurchase.Remarks" name="tenschdl.tscRemarks" maxLength="500">
                                   <s:param name="labelcolspan" value="%{1}" />
                                   <s:param name="inputcolspan" value="%{3}" />
                                </s:textarea>


                                <tr><td align="left">
                                <s:submit theme="simple" name="btnSubmit" key="PrePurchase.SaveAndProceed"   action="SaveTenderScheduleAction" />

                                <s:submit theme="simple" name="btnSubmit" key="PrePurchase.Browse"   action="BrowseTenderSchedule" />

                                <s:submit theme="simple" name="btnSubmit" key="PrePurchase.Clear"   action="ClearTenderSchedule" />
                                 </td></tr>

                                <%-- <s:textfield  maxLength="30" size="20"  label="Schedule No" requiredposition="" name="tenschdl.tscScheduleNo" required="true">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:textfield>

                                <s:select label="Schedule Type" required="true" requiredposition=""  name="tenschdl.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="gmIdList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc"  cssClass="textInput">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:select>

                                <s:textfield required="true" requiredposition="left" maxLength="10" size="10" title="Date [DD-MM-YYYY]"
                                         label="Date" name="schdDate" >
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:textfield>

                               <s:textfield required="true" requiredposition="left" maxLength="100" size="40" title="Venue"
                                         label="Venue" name="tenschdl.tscVenue" >
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:textfield>

                                <s:select label="Time (hr)" required="true" requiredposition="" name="strhr" headerKey="" headerValue="-- Please Select --" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23'}"  cssClass="queryInput">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:select>

                                 <s:select label="Time (min)" required="true" requiredposition="" name="strmin" headerKey="" headerValue="-- Please Select --" list="{'00','01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40','41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59'}"  cssClass="queryInput">
                                    <s:param name="labelcolspan" value="%{1}" />
                                    <s:param name="inputcolspan" value="%{3}" />
                                </s:select>

                                <s:textarea cssClass="textArea"  rows="3" cols="40" label="Remarks" name="tenschdl.tscRemarks"     maxLength="500">
                                   <s:param name="labelcolspan" value="%{1}" />
                                   <s:param name="inputcolspan" value="%{1}" />
                                </s:textarea>
                                
                                <tr><td align="left">
                                <s:submit theme="simple" name="btnSubmit" value="SAVE"   action="SaveTenderScheduleAction" />

                                <s:submit theme="simple" name="btnSubmit" value="Browse"   action="BrowseTenderSchedule" />

                                <s:submit theme="simple" name="btnSubmit" value="Clear"   action="ClearTenderSchedule" />
                                 </td></tr>
                                --%>
                            </tbody>

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
