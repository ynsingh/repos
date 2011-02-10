/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
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
        int flag=0;

        String query = "select * from document_details";
System.out.println(db+".............");
         
         
        
        if(db.equals("combined")==false){query=query+" where document_type='"+db+"' and";flag=1;}

        if (flag==1)
        {
        if(cf.equals("any field")){
        query=query+" (author_main like '%"+p+"%' or title like '%"+p+"%' or publishing_year like '%"+p+"%' or publisher_name like '%"+p+"%' or  publication_place like '%"+p+"%')";
                                   }
         else{
                query=query+" "+cf+" like '%"+p+"%'";
             }


         if(cmbyr.equals("all")){query=query;}
         if(cnf.equals("or") || cnf.equals("and"))
          {
            if(cmbyr.equals("between")){query=query+" "+cnf+" publishing_year between "+yr1+" and "+yr2;}
            if(cmbyr.equals("upto"))   {query=query+" "+cnf+" publishing_year <="+yr1;}
            if(cmbyr.equals("after"))  {query=query+" "+cnf+" publishing_year >="+yr1;}
          }
       
        if (!lib_id.equalsIgnoreCase("all"))
            query = query+ " and library_id='"+ lib_id  +"'";
        }
        else
        {
        if(cf.equals("any field")){
        query=query+" where (author_main like '%"+p+"%' or title like '%"+p+"%' or publishing_year like '%"+p+"%' or publisher_name like '%"+p+"%' or  publication_place like '%"+p+"%')";
                                   }
         else{
                query=query+" where "+cf+" like '%"+p+"%'";
             }


         if(cmbyr.equals("all")){query=query;}
         if(cnf.equals("or") || cnf.equals("and"))
          {
            if(cmbyr.equals("between")){query=query+" "+cnf+" publishing_year between "+yr1+" and "+yr2;}
            if(cmbyr.equals("upto"))   {query=query+" "+cnf+" publishing_year <="+yr1;}
            if(cmbyr.equals("after"))  {query=query+" "+cnf+" publishing_year >="+yr1;}
          }

        if (!lib_id.equalsIgnoreCase("all"))
            query = query+ " and library_id='"+ lib_id  +"'";
        }
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
  session.setAttribute("database",db );
  session.setAttribute("pubyr",   cmbyr );
  session.setAttribute("yr1",   yr1 );
  session.setAttribute("yr2",   yr2 );
  if(p.equals(""))
      p="No Specify";
  session.setAttribute("keyword","(Match All Words)="+p );

  
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
