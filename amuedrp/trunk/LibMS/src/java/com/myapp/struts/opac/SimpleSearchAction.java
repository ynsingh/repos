/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;

import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/**
 *
 * @author Faraz
 */
public class SimpleSearchAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     String p,cmbyr,yr1,yr2,cf,db,cnf,sort;
    // ArrayList opacList;
     //OpacDoc Ob;
     Connection conn;
     PreparedStatement stmt;
     ResultSet rs;
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SimpleSearchActionForm simpleform = (SimpleSearchActionForm)form;
        HttpSession session =request.getSession();
        String lib_id= simpleform.getCMBLib();
        p = simpleform.getTXTPHRASE();
        cmbyr = simpleform.getCMBYR();
        yr1 = simpleform.getTXTYR1();
        yr2 = simpleform.getTXTYR2();
        cf = simpleform.getCMBFIELD();
        db = simpleform.getCMBDB();
        cnf = simpleform.getCMBCONN();
        sort= simpleform.getCMBSORT();


        String query = "select * from document";

         if(db.equals("combined")){query=query+" where db_category='combined'";}
         else if(db.equals("combined")){query=query+" where category='"+db+"'";}

         if(cf.equals("any field")){query=query+" and (author like '%"+p+"%' or title like '%"+p+"%' or subject like '%"+p+"%' or pub_yr like '%"+p+"%' " +
                    "or publisher like '%"+p+"%' or accessionno like '%"+p+"%' or pubPlace like '%"+p+"%')";
                                   }
         else{
                query=query+" and "+cf+" like '%"+p+"%'";
             }


         if(cmbyr.equals("all")){query=query;}
         if(cnf.equals("or") || cnf.equals("and"))
          {
            if(cmbyr.equals("between")){query=query+" "+cnf+" pub_yr between "+yr1+" and "+yr2;}
            if(cmbyr.equals("upto"))   {query=query+" "+cnf+" pub_yr <="+yr1;}
            if(cmbyr.equals("after"))  {query=query+" "+cnf+" pub_yr >="+yr1;}
          }
        /* if(cnf.equals("and"))
          {
            if(cmbyr.equals("between")){query=query+" and pub_yr between "+yr1+" and "+yr2;}
            if(cmbyr.equals("upto"))   {query=query+" and pub_yr <="+yr1;}
            if(cmbyr.equals("after"))  {query=query+" and pub_yr >="+yr1;}
          }*/
        if (!lib_id.equalsIgnoreCase("all"))
            query = query+ " and library_id='"+ lib_id  +"'";
        
         query=query+" order by "+sort+" asc";


        
        //{%>
 ////     <jsp:forward page="journal.jsp" >
     //     <jsp:param name="QUERY" value="<%=query%>"/>
      //</jsp:forward>

                             // <%}%>
 //<!--font color="red" face="CALIBRI"><%//=query%></font-->
//<%
  System.out.println("query="+query);
  session.removeAttribute("simple_resultset");
  rs=MyQueryResult.getMyExecuteQuery(query);
  if(rs.next())
  {

  session.setAttribute("simple_resultset", rs);
  }
  



/*%>
<font size="3" color="teal" align="center">
      <%{%>
        No Records Found...&nbsp;&nbsp;&nbsp;&nbsp;
</font>>
         <%out.println(e);
        }*/


  /* fromIndex = (int) DataGridParameters.getDataGridPageIndex (request, "datagrid1");
   if ((toIndex = fromIndex+4) >= opacList.size ())
   toIndex = opacList.size();
   request.setAttribute ("opacList", opacList.subList(fromIndex, toIndex));
   pageContext.setAttribute("tCount", tcount);*/

        return mapping.findForward(SUCCESS);
    }
}
