/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import com.myapp.struts.opacDAO.OpacSearchDAO;
import java.sql.*;
import java.util.List;
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
     String p,cmbyr,cf,db,cnf,sort;
     Integer yr1,yr2;
     String lib_id,sub_lib,phrase[];
    // ArrayList opacList;
     //OpacDoc Ob;
     Connection conn;
     PreparedStatement stmt;
     ResultSet rs;
     OpacSearchDAO simpleSearchDAO=new OpacSearchDAO();
     List simple_search_list;
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SimpleSearchActionForm simpleform = (SimpleSearchActionForm)form;
        HttpSession session =request.getSession();
        lib_id= simpleform.getCMBLib();
        sub_lib=simpleform.getCMBSUBLib();
        p = simpleform.getTXTPHRASE();
        cmbyr = simpleform.getCMBYR();
        if(simpleform.getTXTYR1()!=null)
        yr1 = Integer.parseInt(simpleform.getTXTYR1());
        if(simpleform.getTXTYR2()!=null)
        yr2 = Integer.parseInt(simpleform.getTXTYR2());
        cf = simpleform.getCMBFIELD();
        db = simpleform.getCMBDB();
        cnf = simpleform.getCMBCONN();
        sort= simpleform.getCMBSORT();
        int flag=0;
        //System.out.println("*************************"+p+".....");
        //String library_id,String searching_word,String sortby
   //System.out.println(yr1+"............."+yr2+".............");
   /* if(cmbyr.equalsIgnoreCase("all"))
    {
        yr1=-1;
        yr2=9999;
    }
    else if (cmbyr.equalsIgnoreCase("after"))
    {

        yr2=9999;
    }
    else if(cmbyr.equalsIgnoreCase("upto"))
    {
    yr2=yr1+1;
    yr1=0;
    }*/
/*
 * Seperate words that are entered to search in String Array named phrase
 */
    //if(p.equals(""))
    //phrase[0]="";
    //else
    phrase=p.split(" ");
    System.out.println("*************************"+phrase.length+phrase[0]+".....");
 /*
 * Execute query by passing parameters and set resulting List in session
 */
    simple_search_list =simpleSearchDAO.simpleSearch(lib_id,sub_lib,phrase,cnf,db,sort,cf,yr1,yr2);
    
    session.setAttribute("simple_search_list",simple_search_list);



/*
        String query = "select * from document_details";
    
         
         
        
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
   pageContext.setAttribute("tCount", tcount);
   */
        return mapping.findForward(SUCCESS);
    }
}
