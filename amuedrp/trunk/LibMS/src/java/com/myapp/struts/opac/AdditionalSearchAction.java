/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Faraz
 */
public class AdditionalSearchAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

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
         String subject,p2,p3,yr1,yr2,cf1,cf2,cf3,cmbyr,title,author,accno;
         String other,q,cnf1,cnf2,cnf3,cnf4,c1,c2,c3,op1,op2,op3,query="";
         String phrase;
         String db,callno,publ,loc,place,sort,field;
         ResultSet rs=null;
        HttpSession session = request.getSession();
        session.removeAttribute("Result");
        AdditionalSearchActionForm myForm = (AdditionalSearchActionForm)form;
        String lib_id= myForm.getCMBLib();
        author=myForm.getTXTAUTHOR();
         title=myForm.getTXTTITLE();
         subject=myForm.getTXTSUBJECT();
         yr1=myForm.getTXTYR1();
         yr2=myForm.getTXTYR2();
         other=myForm.getTXTOTHER();
         cmbyr=myForm.getCMBYR();
         db=myForm.getCMBDB();
         cnf1=myForm.getCMBCONN1();
         cnf2=myForm.getCMBCONN2();
         cnf3=myForm.getCMBCONN3();
         cnf4=myForm.getCMBCONN4();



          query="select * from document_details where document_type!='%%'";

        if(!db.equals("combined")){query="select * from document_details where document_type='"+db+"'";}
       
        q=query;
      if(cnf1.equals("phrase")){op1="or";}
      else                     {op1=cnf1;}
      if(cnf2.equals("phrase")){op2="or";}
       else                     {op2=cnf2;}
      if(cnf3.equals("phrase")){op3="or";}
       else                     {op3=cnf3;}

        if(cnf1.equals("or") || cnf1.equals("and") || cnf2.equals("or") || cnf2.equals("and") || cnf3.equals("or"))
          {
            if (query.contains("where")==true)
                {
                if (!author.equals("") && !cnf1.equals("phrase")) query=query + " and author_main like '"+author+"%' ";
                else if (!author.equals("")) query=query+" and author_main like '%"+author+"%' ";
                if (author.equals("") && !title.equals("") && !cnf2.equals("phrase")) query=query + " and title like '"+title+"%' ";
                  else if(!title.equals("") && !cnf2.equals("phrase")) query = query + " " + op1 + " title like '" + title + "%' ";
                  else if(!title.equals("")) query = query + " or title like '%" + title + "%' ";
                if (author.equals("") && title.equals("") && !subject.equals("") && !cnf3.equals("phrase") ) query=query + " and subject like '"+subject+"%'";
                  else if(!subject.equals("") && !cnf3.equals("phrase")) query = query + " " + op2 + " subject like '" + subject + "%'";
                else if (!subject.equals("")) query=query+" or subject like '%"+subject+"%' ";
                }
            else
                {
                if (!author.equals("") && !cnf1.equals("phrase")) query=query + cnf1+ " author_main like '"+author+"%' ";
                else if (!author.equals("")) query=query+" where author_main like '%"+author+"%' ";
                if (author.equals("") && !title.equals("") && !cnf2.equals("phrase")) query=query + cnf2+" title like '"+title+"%' ";
                  else if(!title.equals("") && !cnf2.equals("phrase")) query = query + cnf2+"  title like '" + title + "%' ";
                  else if(!title.equals("")) query = query + " where title like '%" + title + "%' ";
                if (author.equals("") && title.equals("") && !subject.equals("") && !cnf3.equals("phrase") ) query=query + cnf3+" subject like '"+subject+"%'";
                  else if(!subject.equals("") && !cnf3.equals("phrase")) query = query + cnf3+" subject like '" + subject + "%'";
                else if (!subject.equals("")) query=query+" where subject like '%"+subject+"%' ";
                }
            }
/*
        if(cnf1.equals("phrase")){query=query+" and author like '%"+author+"%' ";}
        if(cnf2.equals("phrase")) {query=query+" or title like '%"+title+"%' ";}
        if(cnf3.equals("phrase")) {query=query+" or subject like '%"+subject+"%' ";}*/
        if(!other.equalsIgnoreCase(""))
        if (query.contains("where")==true)
         {
          if(cnf4.equals("phrase"))
         {
            query=q+" and (author_main like'%"+other+"%' or title like '%"+other+"%' or subject like '%"+other+"%' or publishing_year like '%"+other+"%' " +
                    "or publisher_main like '%"+other+"%'  or publication_place like'%"+other+"%')";
         }
        else
        {
            query=q+" and (author_main like'"+other+"%' or title like '"+other+"%' or subject like '"+other+"%' or publication_year like '"+other+"%' " +
                    "or publisher_name like '"+other+"%' or accessionno like'"+other+"%' or publication_place like'"+other+"%')";
        }
        }
        else
        {
          if(cnf4.equals("phrase"))
         {
            query=q+" where (author_main like'%"+other+"%' or title like '%"+other+"%' or subject like '%"+other+"%' or publishing_year like '%"+other+"%' " +
                    "or publisher_name like '%"+other+"%'  or publication_place like'%"+other+"%')";
         }
        else
        {
            query=q+" where (author_main like'"+other+"%' or title like '"+other+"%' or subject like '"+other+"%' or publishing_year like '"+other+"%' " +
                    "or publisher_name like '"+other+"%' or publication_Place like'"+other+"%')";
        }
        }

         if (query.contains("where")==true)
         {
                if(cnf4.equals("or") || cnf4.equals("and")){
                // query=query+" or author='"+other+"' or title='"+other+"' or subject='"+other+"' or pub_yr='"+other+"' " +
                //   "or publisher='"+other+"' or accessionNo='"+other+"' or pubplace='"+other+"'";
                if(cmbyr.equals("all")){query=query;}
                if(cmbyr.equals("between")){query=query+" "+cnf4+" publishing_year between "+yr1+" and "+yr2;}
                if(cmbyr.equals("upto")){query=query+" "+cnf4+" publishing_year <="+yr1;}
                if(cmbyr.equals("after")){query=query+" "+cnf4+" publishing_year >="+yr1;}
          }
         }
         else
          {
                if(cnf4.equals("or") || cnf4.equals("and")){
                // query=query+" or author='"+other+"' or title='"+other+"' or subject='"+other+"' or pub_yr='"+other+"' " +
                //   "or publisher='"+other+"' or accessionNo='"+other+"' or pubplace='"+other+"'";
                if(cmbyr.equals("all")){query=query;}
                if(cmbyr.equals("between")){query=query+" where publishing_year between "+yr1+" and "+yr2;}
                if(cmbyr.equals("upto")){query=query+" where publishing_year <="+yr1;}
                if(cmbyr.equals("after")){query=query+" where publishing_year >="+yr1;}
          }
         }
              if (!lib_id.equalsIgnoreCase("all") && query.contains("where")==true)
                    query = query+ " and library_id='"+ lib_id  +"'";
              else if (!lib_id.equalsIgnoreCase("all") && query.contains("where")==false)
                  query = query+ " where library_id='"+ lib_id  +"'";

         //query=query+" order by "+cmbyr+" asc";
             System.out.println("Additional query="+query);
              rs = MyQueryResult.getMyExecuteQuery(query);
              if (rs.next()) session.setAttribute("Result", rs);
        return mapping.findForward(SUCCESS);
    }
}
