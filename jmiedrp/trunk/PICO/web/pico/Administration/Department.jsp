<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
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
                <jsp:include page="header.jsp" flush="true"></jsp:include>
            </div>

            <div id="sidebar1">
                <jsp:include page="menu.jsp" flush="true"></jsp:include>
            </div>

            <jsp:include page="jobBar.jsp" flush="true"></jsp:include>
            <!-- *********************************End Menu****************************** -->

            <div id ="mainContent">
                <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />                    
                </s:bean>

                <br><br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff">Department Records Management</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                    <s:form name="frmDepartment" action="SaveDepartmentAction"  validate="true" theme="qxhtml">
                        <br>
                        <s:hidden name="dm.dmId" />
                        <s:hidden name="cm.committeeId"/>
                        <s:hidden name="cm.institutionmaster.imId"/>
                        <s:hidden name="cm.subinstitutionmaster.simId"/>
                        <s:hidden name="cm.erpmGenMaster.erpmgmEgmId"/>

                        <s:select label="Institution" name="dm.institutionmaster.imId" headerKey="0" headerValue="-- Please Select --" list="imIdList" listKey="imId" listValue="imName"
                                  required = "true" requiredposition="right" 
                                  onchange="getSubinstitutionAndEmployeeList('SaveDepartmentAction_dm_institutionmaster_imId', 'SaveDepartmentAction_dm_subinstitutionmaster_simId', 'SaveDepartmentAction_dm_employeemaster_empId');" cssClass="textInput" >
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>
                        <s:label/>
                        <s:label value=".............................." cssClass="tdSpace" />
                        <s:select label="College/Faculty/School" name="dm.subinstitutionmaster.simId" headerKey="0" headerValue="-- Please Select --" list="simImIdList" listKey="simId" listValue="simName" cssClass="textInput" required = "true" requiredposition="right">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:select>

                        <s:textfield required="true" requiredposition="right" maxLength="70" size="70"
                                     label="Department Name" name="dm.dmName" title="Enter Department Name"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{4}" />
                        </s:textfield>

                        <s:textfield required="true" requiredposition="right" maxLength="10" size="10"
                                     label="Short Name" name="dm.dmShortName" title="Enter Short Name for Department"  
                                     cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield>

                        <s:select label="Head's Name" required="true" name="dm.employeemaster.empId" headerKey="0" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname"
                                  onchange="getEmployeeEmail('SaveDepartmentAction_dm_employeemaster_empId','SaveDepartmentAction_dm_dmEmailId')"
                                  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:label/>
                        <s:label/>

                        <s:textfield required="true" requiredposition="right" maxLength="25" size="25"
                                     label="Designation" name="dm.dmHeadDesignation" title="Enter Designation"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{1}" />
                        </s:textfield>


                        <s:textfield required="true" requiredposition="right" maxLength="50" size="50"
                                     label="E-Mail" name="dm.dmEmailId" title="Enter Department's E-Mail Address" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <s:textfield required="true" requiredposition="right" maxLength="50" size="50"
                                     label="Department's Address" name="dm.dmAddressLine1" title="Enter Address" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <s:textfield required="false" maxLength="50" size="50" label="" labelposition="right"
                                     name="dm.dmAddressLine2" title="Enter Department's Address" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <s:textfield maxLength="50" size="50" 
                                     label="District" name="dm.dmDistrict" title="Enter District "  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <s:select label="Country" name="dm.countrymaster.countryId" headerKey="0" headerValue="-- Please Select --" list="ctList" listKey="countryId" listValue="countryName"
                                  onchange="getStateList('SaveDepartmentAction_dm_countrymaster_countryId','SaveDepartmentAction_dm_statemaster_stateId')"  
                                  required="true" requiredposition="right" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}"/>
                            <s:param name="inputcolspan" value="%{6}"/>
                        </s:select>

                        <s:select label="State" name="dm.statemaster.stateId" headerKey="0" headerValue="-- Please Select --" list="statemasterList" listKey="stateId" listValue="stateName" 
                                  required="true" requiredposition="right" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{2}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:textfield required="true" requiredposition="right" maxLength="6" size="6"
                                     label="Pin Code" name="dm.dmPinNo" title="Enter Pin Code"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{3}" />
                        </s:textfield>

                        <s:iterator value="{1,1,1,1,1,1,1,1}">
                            <s:label value="..." cssClass="tdSpace"/>
                        </s:iterator>

                        <s:submit value="Save Department" >
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit value="Browse Department" action="BrowseDepartments">
                            <s:param name="colspan" value="%{2}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit value="Clear Form" onclick="ClearDepartmentFields();">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit value="Export Data" action="PrintDepartments">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                    </s:form>
                    <br>
                </div>
                &nbsp;
            </div>
            <div id="footer" >
                <jsp:include page="footer.jsp" flush="true" ></jsp:include>
            </div>
        </div>
    </body>
</html>
