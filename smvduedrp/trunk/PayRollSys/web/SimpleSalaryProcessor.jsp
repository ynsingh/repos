<%-- 
    Document   : SimpleSalaryProcessor
    Created on : Jul 21, 2010, 5:12:34 PM
    Author     :  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
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
        response.sendRedirect("SimpleSalaryData.jsf?empCode="+eCode);
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
          response.sendRedirect("SimpleSalaryData.jsf?empCode="+eCode+"&msg='Data Updated'");
        }
    else
        {
        response.sendRedirect("SimpleSalaryData.jsf?empCode="+eCode+"&msg='Data Not Updated'");
        }

%>
