<%-- 
    Document        : SimpleSalaryData
    Created on      : 3:02 AM Saturday, October 02, 2010
    Author          : Saurabh Kumar
    Last Modified   : 3:35 AM Saturday, October 02, 2010
--%>

<%@page import="org.smvdu.payroll.beans.WorkingSalary"%>
<%@page import="org.smvdu.payroll.beans.db.EmployeeDB"%>
<%@page import="org.smvdu.payroll.beans.Employee"%>
<%@page import="org.smvdu.payroll.beans.db.SalaryHeadDB"%>
<%@page import="org.smvdu.payroll.beans.SalaryHead"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>



    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Process Monthly Salary Data</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="css/subpage.css"/>
        <link rel="stylesheet" type="text/css" href="css/table.css"/>
        <script type="text/javascript"  language="javascript">
            var net = 0;

            function refreshData()
            {
                sumUpIncome();
                sumUpDeduction();                
            }

            function sumUpIncome()
            {

                var txts = document.getElementById("incomeTable").getElementsByTagName("input");
                var x =0;
                for(i in txts)
                {
                    var p=parseInt(txts[i].value);
                    if(!isNaN(p))
                    {
                        x+=p;
                    }
                }
                var txt =  document.getElementById("txtGross");
                txt.value = x;
                net=x;
            }

            function sumUpDeduction()
            {

                var txts = document.getElementById("deductTable").getElementsByTagName("input");
                var x =0;
                for(i in txts)
                {
                    var p=parseInt(txts[i].value);
                    if(!isNaN(p))
                    {
                        x+=p;
                    }
                }
                var txt =  document.getElementById("txtDeduct");
                txt.value = x;
                net-=x;
                document.getElementById("txtNet").value = net;
            }



            function updateName()
            {

                var txt =  document.getElementById("empName");
                txt.value = "";
                txt =  document.getElementById("empDept");
                txt.value = "";
                txt =  document.getElementById("empDesig");
                txt.value = "";
                txt =  document.getElementById("empBank");
                txt.value = "";
                txt =  document.getElementById("empPF");
                txt.value = "";
            }
        </script>
        <style type="text/css" >
            .PXtable
            {
            float:left;
            width:30%;
            margin-left: 10px;
            background-position: center;
            border:0;
            }
            .othertable
            {
            clear: both;
            width:60%;
            margin-left: 10px;
            background-position: center;
            border:1px;
            border-bottom-color: wheat;
            }
            .tcaption
            {
                font-family: sans-serif;
                font-size: 14px;
                background-color: red;
            }

            .td
            {
                font-family: monospace;
                font-size: 10px;
            }
}
        </style>

    </head>
    <f:view>
    <body class="subpage" onload="refreshData()" id="">
        <div class="container_form">

            <%


                        
                        Employee emp = new Employee().getDefault();
                        String empCode = "";
                        String msg = request.getParameter("msg");
                        try {
                            empCode = request.getParameter("empCode");
                            if (empCode != null && !empCode.isEmpty()) {
                                emp = new Employee().getProfileData(empCode);
                            }
                        } catch (Exception e) {
                            empCode = "";
                        }



            %>

            
            <rich:panel style="width:500px;" header="Employee's Details">
            <form method="post" action="SimpleSalaryProcessor.jsp">
                <table>

                    <tr>
                        <td class="cLabel"> Employee Code</td>
                        <td><input class="cfields" type="text" id="empCode" name="empCode" value="<%=emp.getCode()%>" onkeypress="updateName()"></td>
                        <td class="cLabel"> Employee Name</td>
                        <td><input type="text" id="empName" name="empName" value="<%=emp.getName()%>" ></td>
                    </tr>
                    <tr>
                        <td class="cLabel"> Department</td>
                        <td><input class="cfields" type="text" id="empDept" value="<%=emp.getDeptName()%>" disabled="disabled"></td>
                        <td class="cLabel"> Designation</td>
                        <td><input type="text" id="empDesig" value="<%=emp.getDesigName()%>"  disabled="disabled"></td>
                    </tr>
                    <tr>
                        <td class="cLabel"> Employee Type</td>
                        <td><input class="cfields" type="text" id="empDept" value="<%=emp.getTypeName()%>" disabled="disabled"></td>
                        <td class="cLabel"> Salary Grade</td>
                        <td><input class="cfields" type="text" id="empDesig" value="<%=emp.getBandName()%>"  disabled="disabled"></td>
                        <td><input class="cfields" type="text" name="tCode"  id="emptp" value="<%=emp.getType()%>"  disabled="disabled"></td>
                    </tr>
                    <tr>
                        <td class="cLabel"> Bank Account No</td>
                        <td><input class="cfields" type="text" id="empBank" value="<%=emp.getBankAccNo()%>" disabled="disabled" ></td>
                        <td class="cLabel"> P.F. Account No</td>
                        <td><input class="cfields" type="text" id="empPF" value="<%=emp.getPfAccNo()%>" disabled="disabled" ></td>
                    </tr>
                   <tr>
                       <td class="cLabel">For the month Of</td>
                       <td colspan="3"><rich:calendar showApplyButton="false"  style="width:20px;" datePattern="yyyy-MM-dd" value="#{DateBean.date}"/></td>

                    </tr>
                </table>
                </rich:panel>
                <rich:panel style="width:500px;" header="Salary Details">

                <%
                            if (msg != null && !msg.isEmpty()) {
                                out.println(" <h2>" + msg + " </h2>");
                            }
                %>

                <h:panelGrid columns="2">
                <rich:panel header="Income">
                <div id="incomeTable">                    
                        <table id="income_table">

                    <%
                                ArrayList<SalaryHead> heads = new WorkingSalary().loadEmpHeadsIncome(emp.getType());
                                SalaryHead sh = null;
                                for (int i = 0; i < heads.size(); i++) {
                                    sh = heads.get(i);                                    
                                        out.println("<tr><td> " + sh.getName() + "</td><td><input type='text'  value=" + sh.getDefaultValue() + " name=box_" + sh.getNumber() + " onchange='refreshData()'></td></tr>");
                                   

                                }

                    %>
                        </table>
                </div>
            </rich:panel>
                <rich:panel header="Deductions">                    
                   
                    <div id="deductTable">
                        <table id="deduct_table">

                    <%
                                ArrayList<SalaryHead> heads2 = new WorkingSalary().loadEmpHeadsDeduct(emp.getType());
                                for (int i = 0; i < heads2.size(); i++) {
                                   SalaryHead sh2 = heads2.get(i);
                                        out.println("<tr><td> " + sh2.getName() + "</td><td><input type='text' value=" + sh2.getDefaultValue() + " name=box_" + sh2.getNumber() + " onchange='refreshData()'></td></tr>");
                                   
                                }

                    %>
                        </table>
                    </div>
                        </rich:panel>
                </h:panelGrid>
                    <rich:panel header="Salary Summery">
                     <table width="60%">
                         <tr><td>Gross Pay </td> <td><input readonly="readonly" type="text" value="0" id="txtGross" > </td> </tr>
                    <tr><td>Total Deduction </td> <td><input readonly="readonly"  type="text" value="0" id="txtDeduct" > </td> </tr>
                    <tr><td>Net Pay </td> <td><input type="text" readonly="readonly"  value="0" id="txtNet" > </td> </tr>                   
                </table>
                     </rich:panel>
                    <h:commandButton value="Save"/>
                    <h:commandButton value="Reset"/>
                </rich:panel>
            </form>
        </div>

</body>
</f:view>     
</html>
