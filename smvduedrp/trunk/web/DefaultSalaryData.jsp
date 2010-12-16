<%-- 
    Document   : SimpleSalaryData
    Created on : Jul 21, 2010, 5:06:00 PM
    Author     : Algox
--%>

<%@page import="org.smvdu.payroll.beans.db.SalaryGradeDB"%>
<%@page import="org.smvdu.payroll.beans.SalaryGrade"%>
<%@page import="org.smvdu.payroll.beans.WorkingSalary"%>
<%@page import="org.smvdu.payroll.beans.db.EmployeeDB"%>
<%@page import="org.smvdu.payroll.beans.Employee"%>
<%@page import="org.smvdu.payroll.beans.db.SalaryHeadDB"%>
<%@page import="org.smvdu.payroll.beans.SalaryHead"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <%


%>

   
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>     

    </head>
    <body>

      

        <form method="get" action="">
            <table width="50%" align="center">

                <tr bgcolor="yellow">
                    <td> Select Grade</td>
                    <td>
                        <select name="salGrade">
                            <%

                            for(SalaryGrade sg : new SalaryGradeDB().load())
                                {
                                    out.println("<option value="+sg.getCode()+">"+sg.getName()+"</option>");
                                }
%>
                        </select>
                        
                    </td>
                    <td><input type="submit" value="Load" ></td>
                </tr>
                
                <tr><td colspan="4"> <hr></td> </tr>
            </table>
        </form>
                        
                
                <%
                try
                                    {
                int code = Integer.parseInt(request.getParameter("salGrade"));
                out.println("<form method='post' action='DefaultSalaryProcessor.jsp?hcode="+code+"'>");
                out.println("<table id='txtTable' align='center'>");
                ArrayList<SalaryHead> heads = new WorkingSalary().loadEmpHeads(code);
                SalaryHead sh = null;
                for(int i=0;i<heads.size();i++)
                    {
                        sh = heads.get(i);
                        out.println("<tr><td> "+sh.getName()+"</td><td><input type='text' value="+sh.getDefaultValue()+" name=box_"+sh.getNumber()+"></td></tr>");
                    }

                out.println("<tr>");
                out.println("<td><input type='submit' value='Save'></td>");
                out.println("<td><input type='reset' value='Reset'></td>");
                out.println("</tr>");
                out.println("</table></form>");
                }
                catch(Exception e)
                        {
                    }

%>




    </body>
</html>
