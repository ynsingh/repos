<%-- 
    Document   : SimpleSalaryProcessor
    Created on : Jul 21, 2010, 5:12:34 PM
    Author     : Algox
--%>

<%@page import="org.smvdu.payroll.beans.db.EmployeeDB"%>
<%@page import="org.smvdu.payroll.beans.WorkingSalary"%>
<%@page import="org.smvdu.payroll.beans.db.SalaryDataDB"%>
<%@page import="org.smvdu.payroll.beans.Employee"%>
<%@page import="org.smvdu.payroll.beans.SalaryData"%>
<%@page import="org.smvdu.payroll.beans.db.SalaryHeadDB"%>
<%@page import="org.smvdu.payroll.beans.SalaryHead"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%

String eCode = request.getParameter("empCode");
String name = request.getParameter("empName");
System.out.println("Processor : Name : "+name);

if(name==null||name.isEmpty())
    {
        response.sendRedirect("SimpleSalaryData.jsp?empCode="+eCode);
        return;
    }

System.err.println(" ---> Moving to Process Salary now ");


ArrayList<SalaryHead> heads = new WorkingSalary().loadEmpHeads(new EmployeeDB().getTypeCode(eCode));
SalaryHead sh = null;


out.println("Salary Data of "+eCode+" Saved");
ArrayList<SalaryData> salData = new ArrayList<SalaryData>();
int gross = 0;
SalaryHead sheads = null;
for(int i=0;i<heads.size();i++)
    {
        sheads = heads.get(i);
        sh = heads.get(i);
        int amount =0;
        try
                {
        amount= Integer.parseInt(request.getParameter("box_"+sh.getNumber()));
        if(sheads.isUnder())
            {
                gross-=amount;
            }
        else
            {
                gross+=amount;
            }
        System.out.println("Head : "+sh.getName()+" Amount : "+amount +" Emp Code :"+eCode);
        SalaryData sd = new SalaryData();
        Employee emp = new Employee();
        emp.setCode(eCode);
        sd.setEmployee(emp);
        sd.setHeadCode(sh.getNumber());
        sd.setHeadValue(amount);
        salData.add(sd);
        }
        catch(Exception e)
                {
            }

        
        
    }
        SalaryData sd = new SalaryData();
        Employee emp = new Employee();
        emp.setCode(eCode);
        sd.setEmployee(emp);
        sd.setHeadCode(101);
        sd.setHeadValue(gross);
        salData.add(sd);
    if(salData.isEmpty())
        {
           return;
        }
    boolean b = new SalaryDataDB().save(salData);
    if(b)
        {
          response.sendRedirect("SimpleSalaryData.jsp?empCode="+eCode+"&msg='Data Updated'");
        }
    else
        {
        response.sendRedirect("SimpleSalaryData.jsp?empCode="+eCode+"&msg='Data Not Updated'");
        }

%>