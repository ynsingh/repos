<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
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
                    <p align="center" class="pageHeading" style="color: #ffffff">College/Faculty/School Records Management</p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>

                <div style="border: solid 1px #000000; background: gainsboro">

                    <s:form name="frmSubInstitution" action="SaveSubInstitutionAction"  validate="true" theme="qxhtml">
                        <s:hidden name="sim.simId" />
                        <s:hidden name="cm.committeeId"/>
                        <s:hidden name="cm.departmentmaster.dmId"/>
                        <s:hidden name="cm.institutionmaster.imId"/>
                        <s:hidden name="cm.erpmGenMaster.erpmgmEgmId"/>

                        <br>
                        <s:label value="..." cssClass="tdSpace" />
                        <s:select label="Institution" name="sim.institutionmaster.imId" headerKey="0" headerValue="-- Please Select --" 
                                  list="simImIdList" listKey="imId" listValue="imName" cssClass="textInput"
                                  required="true">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:select>

                        <s:label value="..." cssClass="tdSpace" />
                        <s:textfield name="sim.simName" required="true" maxLength="70" size="50"
                                     label="College/Faculty/School Name" title="Enter College/Faculty/School"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:label value="....." cssClass="tdSpace" />                                                

                        <s:textfield required="true" maxLength="10" size="10"
                                     label="College/Faculty/School's Short Name" name="sim.simShortName" title="Enter Short Name for InstitutionName" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>


                        <s:label/>
                        <s:select label="Type" required="true" name="sim.erpmGenMaster.erpmgmEgmId" headerKey="0" headerValue="-- Please Select --" list="simTypeList" listKey="erpmgmEgmId" listValue="erpmgmEgmDesc" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:select>

                        <s:label value="..." cssClass="tdSpace" />                                           

                        <s:select label="Head's Name" required="true" name="sim.employeemaster.empId" headerKey="0" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname"
                                  onchange="getEmployeeEmail('SaveSubInstitutionAction_sim_employeemaster_empId','SaveSubInstitutionAction_sim_simEmailId')" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:label value="....." cssClass="tdSpace"/>                                                                              

                        <s:textfield required="true" maxLength="50" size="30"
                                     label="Head's Designation " name="sim.simHeadDesignation" title="Enter Head Designation"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace" />                                           
                        <s:textfield required="true" maxLength="50" size="50"
                                     label="Address" name="sim.simAddressLine1" title="Enter Address" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace" />                                                                           
                        <s:textfield required="false" requiredposition="left" maxLength="50" size="50" label="" labelSeparator=""
                                     name="sim.simAddressLine2" title="Enter Institution Address"  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace" />                                           
                        <s:textfield required="false" requiredposition="left" maxLength="50" size="30"
                                     label="District" name="sim.simDistrict" title="Enter District "  cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace" />                                           
                        <s:select label="Country" required="true" name="sim.countrymaster.countryId" headerKey="0" headerValue="-- Please Select --" list="ctList" listKey="countryId" listValue="countryName"
                                  onchange="getStateList('SaveSubInstitutionAction_sim_countrymaster_countryId','SaveSubInstitutionAction_sim_statemaster_stateId')" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:select>

                        <s:label value="..." cssClass="tdSpace" />                                           
                        <s:select label="State" required="true"  name="sim.statemaster.stateId" headerKey="0" headerValue="-- Please Select --" list="statemasterList" listKey="stateId" listValue="stateName" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:select>

                        <s:label value="..." cssClass="tdSpace" />                                           
                        <s:textfield required="false" requiredposition="left" maxLength="6" size="6"
                                     label="Pin Code" name="sim.simPinNo" title="Enter Pin Code" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace" />                                           
                        <s:textfield required="false" requiredposition="left" maxLength="30" size="30"
                                     label="Phone Number" name="sim.simPhone" title="Enter Phone" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace" />                                           
                        <s:textfield required="false" requiredposition="left" maxLength="30" size="30"
                                     label="Fax Number" name="sim.simFax" title="Enter Fax" cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{2}" />
                        </s:textfield>

                        <s:label value="..." cssClass="tdSpace" />                                           
                        <s:textfield required="true" maxLength="50" size="50"
                                     label="E-Mail Adress" name="sim.simEmailId" title="Enter Subinstitue's E-Mail " cssClass="textInput">
                            <s:param name="labelcolspan" value="%{1}" />
                            <s:param name="inputcolspan" value="%{6}" />
                        </s:textfield>

                        <s:iterator value="{1,1,1,1,1,1,1,1}">
                            <s:label value="..." cssClass="tdSpace"/>
                        </s:iterator>

                        <s:label value="..." cssClass="tdSpace" />                                           
                        <s:submit value="Save Sub Institution">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit value="Browse Sub Institution"  action="BrowseSubInstitutions">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit value="Clear Form" onclick="ClearSubInstitutionFields();">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>

                        <s:submit value="Export Data" action="PrintSubInstitutions">
                            <s:param name="colspan" value="%{1}" />
                            <s:param name="align" value="%{'center'}" />
                        </s:submit>
                    </s:form>
                    <br>
                </div>
                &nbsp;
            </div>
            <div id="footer">
                <jsp:include page="footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>
