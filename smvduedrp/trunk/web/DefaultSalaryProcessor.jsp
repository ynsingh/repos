<%-- 
    Document   : SimpleSalaryProcessor
    Created on : Jul 21, 2010, 5:12:34 PM
    Author     : Algox
--%>

<%@page import="org.smvdu.payroll.beans.SalaryHead"%>
<%@page import="org.smvdu.payroll.beans.WorkingSalary"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%

try
        {
int hcode = Integer.parseInt(request.getParameter("hcode"));
ArrayList<SalaryHead> heads = new WorkingSalary().loadEmpHeads(hcode);
for(SalaryHead sh : heads)
    {
    int p = Integer.parseInt(request.getParameter("box_"+sh.getNumber()));
        sh.setDefaultValue(p);
        out.println("<br>"+sh.getName()+" "+sh.getDefaultValue());
    }
new WorkingSalary().save(heads, hcode);
response.sendRedirect("DefaultSalaryData.jsp?salGrade="+hcode);
}
catch(Exception e)
        {
  out.println(e);
    }  

%>