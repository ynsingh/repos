 <%--   I18n By    : Mohd. Manauwar Alam
                   : March 2014
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>


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
        <s:head />
    </head>
    <body class = "twoColElsLtHdr">
        <div id="container">
            <div id="headerbar1">
                <jsp:include page="../Administration/header.jsp" flush="true"></jsp:include>
            </div>
            <div id="sidebar1">
                <jsp:include page="../Administration/menu.jsp" flush="true"></jsp:include>
            </div>
            <!-- *********************************End Menu****************************** -->
            <div id ="mainContent" >

               <s:bean name="java.util.HashMap" id="qTableLayout">
                    <s:param name="tablecolspan" value="%{8}" />
                </s:bean>
                <br>
                <br>
                <div style ="background-color: #215dc6;">
                    <p align="center" class="pageHeading" style="color: #ffffff"><s:property value="getText('Inventory.ManageReturnIssuedItems')" /></p>
                    <p align="center" class="mymessage" style="color: #ffff99"><s:property value="message" /></p>
                </div>
                <%--------------------this is a return issued items  form --------------------%>

                <s:actionerror />
                <div style="border: solid 1px #000000; background: gainsboro">


                <s:form name="FrmReturnIssuedItems" action="ReturnIssuedItemsAction" >
                    
                   <s:hidden name ="erpmirm.irmId" />

                    <table border="0" cellpadding="4" cellspacing="0" align="center" >
                        <tbody>
                            <tr>
                                <td>

                                    <s:select cssClass="textInput" key="Inventory.Institution"  required="true" requiredposition="" name="erpmirm.institutionmaster.imId" headerKey="" headerValue="-- Please Select --" list="imList" listKey="imId" listValue="imName" value="DefaultInsitute1"
                                              onchange="getSubinstitutionList('ReturnIssuedItemsAction_erpmirm_institutionmaster_imId', 'ReturnIssuedItemsAction_erpmirm_subinstitutionmaster_simId');"/>

                                    <s:select cssClass="textInput" key="Inventory.SubInstitution" required="true" requiredposition="" name="erpmirm.subinstitutionmaster.simId" headerKey="" headerValue="-- Please Select --" list="simList" listKey="simId" listValue="simName" value="DefaultSubInsitute"
                                              onchange="getDepartmentList('ReturnIssuedItemsAction_erpmirm_subinstitutionmaster_simId', 'ReturnIssuedItemsAction_erpmirm_departmentmaster_dmId');"/>

                                    <s:select cssClass="textInput" key="Inventory.Department" required="true" requiredposition="" name="erpmirm.departmentmaster.dmId" headerKey="" headerValue="-- Please Select --" list="dmList" listKey="dmId" listValue="dmName" value="DefaultDepartment"
                                            onchange="getEmployeeListByDmID('ReturnIssuedItemsAction_erpmirm_departmentmaster_dmId', 'ReturnIssuedItemsAction_erpmirm_employeemaster_empId');" />


                                    <s:select cssClass="textInput" key="Inventory.EmployeeName" required="true" requiredposition=""  name="erpmirm.employeemaster.empId" headerKey="" headerValue="-- Please Select --" list="empList" listKey="empId" listValue="empFname+' '+empMname+' '+empLname" />

                                    <s:textfield key="Inventory.ReturnDate" required="true" requiredposition="" maxLength="40" size="20"
                                            name="returnDate" title="Enter Order"  cssClass="textInput">
                                            <s:param name="labelcolspan" value="%{1}" />
                                            <s:param name="inputcolspan" value="%{3}" />
                                    </s:textfield>

                                    <%-- <sx:datetimepicker name="erpmirm.irmReturnDate" label="Return Date(yyyy-mm-dd)" displayFormat="yyyy-MM-dd" value="%{'today'}"/>--%>

                                    <s:textfield requiredposition="left" maxLength="50" size="30" cssClass="textInput" key="Inventory.ReturnNo" name="erpmirm.irmReturnNo" title="" />


                                    <s:select cssClass="textInput" key="Inventory.ReturnType" required="true" requiredposition="" name="erpmirm.irmReturnType" headerKey="" headerValue="-- Please Select --" list="#{'U':'Returned After USE','R':'Returned After REPAIR'}"

                                     />

                                    <s:textarea cssClass="textArea"  rows="2" cols="50" key="Inventory.Remarks" name="erpmirm.irmRemarks" title="" />

                                     <s:radio key="Inventory.SelectAnyOne" required="true" requiredposition="" list="#{'I':'Issue No','S':'Item Serial No'}" name="radSelect" value="'I'"
                                    />


                                
                                </td>
                            </tr> <tr>
                                <td align="right">
                                    <s:submit theme="simple" name="btnSubmit" key="Inventory.SaveProceed" action="SaveReturnIssuedItemsAction"  onclick="makeDisableInReturnIssuedItem();"/>
                                </td>
                                <td>
                                    <s:submit theme="simple" cssClass="savebutton"  name="btnSubmit" key="Inventory.Clear" action="ReturnIssuedItemsAction"/>

                                    <s:submit theme="simple" name="bthReset" key="Inventory.Browse" action="BrowseReturnIssuedItems"  />

                                    <s:submit theme="simple" name="showGFRreport"  key="Inventory.ShowGFR" action="showGFRreportReturn" disabled="varShowGFR" />
                                </td>
                            </tr>
                        </tbody>
                    </table>

                </s:form>

            </div>
                 <br>
            </div>
            <div id="footer">
                <jsp:include page="../Administration/footer.jsp" flush="true"></jsp:include>
            </div>
        </div>
    </body>
</html>


