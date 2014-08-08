<%-- 
    Document   : ManageCommitteeMaster
    Created on : 7 Oct, 2011, 10:36:18 PM
    Author     : sknaqvi
    I18n By    : Mohd. Manauwar Alam
               : Jan 2014
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
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
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
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>

            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>
            
            <jsp:include page="jobBar.jsp" flush="true"></jsp:include>

            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent">

                <br><br>
                <div style ="background-color: #215dc6;">
<%--                    <p align="center" class="pageHeading" style="color: #ffffff">COMMITTEE MASTER</p> --%>
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Administration.CommiteeMast')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                    <s:form name="frmCommitteeMaster" action="SaveCommitteeMasterAction">
                        <s:hidden name="cm.committeeId" />

                        <table border="0" cellpadding="4" cellspacing="0" align="center" >
                            <tbody>
                                <tr>
                                    <td>
                                        <script type='text/javascript'>
                                            function emailValidator(elem, helperMsg){
                                                var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/;
                                                if(elem.value.match(emailExp)){
                                                    return true;
                                                }else {
                                                    alert(helperMsg);
                                                    elem.focus();
                                                    return false;
                                                }
                                            }
                                        </script>

                                        <br>
<%--                                        <s:select label="Institution" required="true" requiredposition="" name="cm.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName" cssClass="textInput"
                                                  onchange="getSubinstitutionList('SaveCommitteeMasterAction_cm_institutionmaster_imId', 'SaveCommitteeMasterAction_cm_subinstitutionmaster_simId');"/>

                                        <s:select label="College/Faculty/School" required="true" requiredposition="" name="cm.subinstitutionmaster.simId" headerKey="0" headerValue="-- Please Select --" list="simImIdList" listKey="simId" listValue="simName" cssClass="textInput"
                                                  onchange="getAllDepartmentList('SaveCommitteeMasterAction_cm_subinstitutionmaster_simId', 'SaveCommitteeMasterAction_cm_departmentmaster_dmId');"/>

                                        <s:select label="Department" required="true" requiredposition="" name="cm.departmentmaster.dmId" headerKey="0" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" cssClass="textInput"/>

                                        <s:select label="Committee/Authority" required="true" requiredposition="" name="cm.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="committeeTypesList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>

                                        <s:radio label="Committee Level" name="cm.committeeLevel" list="#{'I':'Institution','S':'College/Faculty/School','D':'Department'}" value="'I'" cssClass="checkboxLabel"/>

                                        <s:textfield label="Name of the Committee/Authority" name="cm.committeeName" title="Enter Name of the Committee/Authority"
                                                     required="true" requiredposition="left" maxLength="100" size="100"  cssClass="textInput"/>

                                        <s:textarea rows="2" cols="100" name="cm.commmitteePurpose" label="Purpose" title="Enter purpose of committee"   cssClass="textArea"/>

                                        <s:textfield label="EMail address of the Committee Convener/Authority" name="cm.committeeConvener" title="Enter EMail address of the Committee Convener/Authority"
                                                     required="true" requiredposition="left" maxLength="100" size="100"  cssClass="textInput"  />

                                        <s:checkbox cssClass="checkboxLabel"  label="Is Committee Active?" name="cm.committeeActive" labelposition="right"/>
                                    </td>
                                </tr> <tr>
                                    <td>
                                        <s:submit theme="simple" name="btnSubmit" value="Save Committee/Authority Entry"  cssClass="textInput" />

                                    </td>
                                    <td>
                                        <s:submit theme="simple" name="bthReset" value="Fetch Committee Entries" action="FetchCommitteeMaster"  cssClass="textInput"/>
                                    </td>
                                    <td>
                                        <s:submit theme="simple" name="bthReset" value="Clear"  action="ClearCommitteeMasterAction" cssClass="textInput"/> --%>
                                        <s:select key="Administration.InstitutionName" required="true" requiredposition="" name="cm.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName" cssClass="textInput"
                                                  onchange="getSubinstitutionList('SaveCommitteeMasterAction_cm_institutionmaster_imId', 'SaveCommitteeMasterAction_cm_subinstitutionmaster_simId');"/>

                                        <s:select key="Administration.SubinstitutionName" required="true" requiredposition="" name="cm.subinstitutionmaster.simId" headerKey="0" headerValue="-- Please Select --" list="simImIdList" listKey="simId" listValue="simName" cssClass="textInput"
                                                  onchange="getAllDepartmentList('SaveCommitteeMasterAction_cm_subinstitutionmaster_simId', 'SaveCommitteeMasterAction_cm_departmentmaster_dmId');"/>

                                        <s:select key="Administration.DepartmentName" required="true" requiredposition="" name="cm.departmentmaster.dmId" headerKey="0" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" cssClass="textInput"/>

                                        <s:select key="Administration.CommiteeOrAuthority" required="true" requiredposition="" name="cm.erpmGenMaster.erpmgmEgmId" headerKey="" headerValue="-- Please Select --" list="committeeTypesList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput"/>
                                        <s:radio key="Administration.CommitteeLevel" name="cm.committeeLevel" list="#{'I':getText('Administration.InstitutionName'),'S':getText('Administration.SubinstitutionName'),'D':getText('Administration.DepartmentName')}" value="'I'" cssClass="checkboxLabel"/>

                                        <s:textfield key="Administration.CommitteeOrAuthorityName" name="cm.committeeName" title="Enter Name of the Committee/Authority"
                                                     required="true" requiredposition="left" maxLength="100" size="100"  cssClass="textInput"/>

                                        <s:textarea rows="2" cols="100" name="cm.commmitteePurpose" key="Administration.Purpose" title="Enter purpose of committee"   cssClass="textArea"/>

                                        <s:textfield key="Administration.EMailCommitteeConvenerOrAuthorityEMail" name="cm.committeeConvener" title="Enter EMail address of the Committee Convener/Authority"
                                                     required="true" requiredposition="left" maxLength="100" size="100"  cssClass="textInput"  />

                                        <s:checkbox cssClass="checkboxLabel"  key="Administration.IsCommitteeActive" name="cm.committeeActive" labelposition="right"/>
                                    </td>
                                </tr> <tr>
                                    <td>
                                        <s:submit theme="simple" name="btnSubmit" key="Administration.Save"  cssClass="textInput" />

                                    </td>
                                    <td>
                                        <s:submit theme="simple" name="bthReset" key="Administration.Browse" action="FetchCommitteeMaster"  cssClass="textInput"/>
                                    </td>
                                    <td>
                                        <s:submit theme="simple" name="bthReset" key="Administration.Clear"  action="ClearCommitteeMasterAction" cssClass="textInput"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </s:form>
                    <br>
                </div>
                &nbsp;
            </div>

            <s:if test="cmList.size() > 0">
            <div id ="mainContent" align="center">
                <div style="border: solid 1px #000000; background: gainsboro">
                    <s:form name="frmCommitteeMasterBrowse">
                        <br>
                        <table width="100%" border="1" cellspacing="0" cellpadding="0" align="center" >
                            <tr><td>
                                    <display:table name="cmList" pagesize="15"
                                                   excludedParams="*" export="true" cellpadding="0"
                                                   cellspacing="0" id="doc" 
                                                   requestURI="/Administration/FetchCommitteeMaster">
                                        <display:column  class="griddata" title="Record" sortable="true" maxLength="100" headerClass="gridheader" style="width:5%">
                                    <c:out> ${doc_rowNum}
                                    </display:column>

                                    <display:column property="institutionmaster.imShortName" title="Institution"
                                                    maxLength="8" headerClass="gridheader"  style="width:8%"
                                                    class="<s:if test= ${doc_rowNum}%2== 0>even</s:if><s:else>odd</s:else>" sortable="false" />

                                    <display:column property="subinstitutionmaster.simShortName" title="SubInstitution"
                                                    maxLength="8" headerClass="gridheader" style="width:8%"
                                                    class="griddata" sortable="false"/>

                                    <display:column property="departmentmaster.dmShortName" title="Department"
                                                    maxLength="8" headerClass="gridheader" style="width:8%"
                                                    class="griddata" sortable="false"/>

                                    <display:column property="erpmGenMaster.erpmgmEgmDesc" title="Committee/Authority" style="width:10%"
                                                    maxLength="10" headerClass="gridheader"
                                                    class="griddata" sortable="false"/>

                                    <display:column property="committeeName" title="Committee Name" style="width:30%"
                                                    maxLength="50" headerClass="gridheader"
                                                    class="griddata" sortable="false"/>

                                    <display:column property="committeeConvener" title="Contact EMail" style="width:15%"
                                                    maxLength="25" headerClass="gridheader"
                                                    class="griddata" sortable="false"/>

                                    <display:column paramId="committeeId" paramProperty="committeeId"
                                                    href="/pico/Administration/EditCommitteeMasterAction.action"
                                                    headerClass="gridheader" class="griddata" media="html"  title="Edit">
                                        Edit
                                    </display:column>
                                    <display:column paramId="committeeId" paramProperty="committeeId"
                                                    href="/pico/Administration/DeleteCommitteeMasterAction.action"
                                                    headerClass="gridheader" class="griddata" media="html" title="Delete">
                                        Delete
                                    </display:column>

                                </display:table>
                                </td></tr>
                        </table>
                    </s:form>
                    <br>
                </div>
                &nbsp;
            </div>
            </s:if>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
