<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%--
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERP Mission - A Project sponsored by NMEICT, MHRD, Govt. of India</title>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/ajax/jquery2.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/PrePurchase/country.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Administration/Admin.js"></script>
        <script language="JavaScript" type="text/JavaScript" src="../javaScript/Inventory/Inventory.js"></script>

        <link href="../css/pico.css" rel="stylesheet" type="text/css" />
        <meta HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=UTF-8">
        <meta name="description" content="ERP for Universities">
        <meta name="keywords" content="ERP">
        <meta name="author" content=", Jamia Millia Islamia">
        <meta name="email" content="@gmail.com">
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
            <div id ="mainContent" ><br><br>
                <p align="center"><s:label cssClass="pageHeading" value="MANAGE RETURN ISSUED ITEMS" /></p>

                <s:actionerror />

                <s:form name="FrmReturnIssuedItems" action="ReturnIssuedItemsAction" >
                    <p align="left" class="pageMessage"></p>
                   <s:hidden name ="erpmirm.irmId" />

                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <tr>
                                <td>




                                    <s:select cssClass="textInput" label="Institution"  required="true" requiredposition="" name="erpmirm.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" value="DefaultInsitute1"
                                              onchange="getSubinstitutionList('ReturnIssuedItemsAction_erpmirm_institutionmaster_imId', 'ReturnIssuedItemsAction_erpmirm_subinstitutionmaster_simId');"/>

                                    <s:select cssClass="textInput" label="College/Faculty/School" required="true" requiredposition="" name="erpmirm.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName" value="DefaultSubInsitute"
                                              onchange="getDepartmentList('ReturnIssuedItemsAction_erpmirm_subinstitutionmaster_simId', 'ReturnIssuedItemsAction_erpmirm_departmentmaster_dmId');"/>

                                    <s:select cssClass="textInput" label="Department Name" required="true" requiredposition="" name="erpmirm.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" value="DefaultDepartment"
                                            onchange="getEmployeeListByDmID('ReturnIssuedItemsAction_erpmirm_departmentmaster_dmId', 'ReturnIssuedItemsAction_erpmirm_employeemaster_empId');" />


                                    <s:select cssClass="textInput" label="Employee Name" required="true" requiredposition=""  name="erpmirm.employeemaster.empId" headerKey="" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname" />

                                    <s:textfield label="Return Date" required="true" requiredposition="" maxLength="40" size="20"
                                            name="returnDate" title="Enter Order"  cssClass="textInput">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                    </s:textfield>

                                    <%-- <sx:datetimepicker name="erpmirm.irmReturnDate" label="Return Date(yyyy-mm-dd)" displayFormat="yyyy-MM-dd" value="%{'today'}"/>--%>

                                    <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInput" label="Return No." name="erpmirm.irmReturnNo" title="" />


                                    <s:select cssClass="textInput" label="Return Type" required="true" requiredposition="" name="erpmirm.irmReturnType" headerKey="" headerValue="-- Please Select --" list="#{'U':'Returned After USE','R':'Returned After REPAIR'}"

                                     />

                                    <s:textarea cssClass="textArea"  rows="2" cols="50" label="Remarks" name="erpmirm.irmRemarks" title="" />

                                     <s:radio label="Select any one" required="true" requiredposition="" list="#{'I':'Issue No','S':'Item Serial No'}" name="radSelect" value="'I'"
                                    />


                                
                                </td>
                            </tr> <tr>
                                <td align="right">
                                    <s:submit theme="simple" name="btnSubmit" value="Save and Proceed" action="SaveReturnIssuedItemsAction"  onclick="makeDisableInReturnIssuedItem();"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" cssClass="savebutton"  name="btnSubmit" value="Clear" action="ReturnIssuedItemsAction"/>

                                    <s:submit theme="simple" name="bthReset" value="Browse" action="BrowseReturnIssuedItems"  />

                                    <s:submit theme="simple" name="showGFRreport"  value="Show GFR" action="showGFRreportReturn" disabled="varShowGFR" />
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </s:form>

            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>


