<%-- 
    Document   : SimpleSalaryData
    Created on : Jul 21, 2010, 5:06:00 PM
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
