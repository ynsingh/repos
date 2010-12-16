<%-- 
    Document   : SimpleQuery
    Created on : Nov 10, 2010, 8:45:01 PM
    Author     : Algox
--%>

<%@page import="org.smvdu.payroll.beans.db.SalaryFormulaDB"%>
<%@page import="org.smvdu.payroll.beans.db.EmployeeDB"%>
        <%
        try
                {
          String code=request.getParameter("em");
          String opcode=request.getParameter("op");
          if(opcode.equals("1"))
              {
                  boolean b = new EmployeeDB().codeExist(code);
                  if(b)
                      {
                            out.println("1");
                      }
                     else
                      {
                            out.println("0");
                      }
          }
          if(opcode.equals("2"))
              {
                  out.println(new SalaryFormulaDB().loadFormula(Integer.parseInt(code)));
              }


          }
        catch(Exception e)

                  {
            out.println(e.getMessage());
            }

        %>
 