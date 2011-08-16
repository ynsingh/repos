/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.opacDAO.OpacSearchDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Faraz
 */
public class AdditionalSearchAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String authors[],titles[],subjects[],other_fields[]; /* array of fields*/
    OpacSearchDAO opacSearchDAO=new OpacSearchDAO();
    BibliopgraphicEntryDAO bibdao=new BibliopgraphicEntryDAO();
    Integer yr1,yr2;
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
         String subject,p2,p3,cf1,cf2,cf3,cmbyr,title,author,accno,sub_lib;
         String other,q,cnf1,cnf2,cnf3,cnf4,c1,c2,c3,op1,op2,op3,query="";
         String phrase;
         String doc_type,callno,publ,loc,place,sort,field;         
         ResultSet rs=null;
          request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        session.removeAttribute("Result");
        AdditionalSearchActionForm myForm = (AdditionalSearchActionForm)form;
        String lib_id= myForm.getCMBLib();
        sub_lib=myForm.getCMBSUBLib();
        author=myForm.getTXTAUTHOR();
         title=myForm.getTXTTITLE();
         subject=myForm.getTXTSUBJECT();
         if(myForm.getTXTYR1()!=null)
         {
        yr1 = Integer.parseInt(myForm.getTXTYR1());
         }
        if(myForm.getTXTYR2()!=null)
        yr2 = Integer.parseInt(myForm.getTXTYR2());
         other=myForm.getTXTOTHER();
         cmbyr=myForm.getCMBYR();
         doc_type=myForm.getCMBDB();
         sort=myForm.getCMBSORT();
         cnf1=myForm.getCMBCONN1();
         cnf2=myForm.getCMBCONN2();
         cnf3=myForm.getCMBCONN3();
         cnf4=myForm.getCMBCONN4();

         if(cmbyr.equalsIgnoreCase("all"))
    {
        yr1=null;
        yr2=null;
    }
    else if (cmbyr.equalsIgnoreCase("after"))
    {

        yr2=null;
    }
    else if(cmbyr.equalsIgnoreCase("upto"))
    {
    yr2=yr1+1;
    yr1=null;
    }
/*
 * Criteria for setting Variables to pass searching method
 * 1.Set variables for author,title,subject,other field in String Array by seperating word  (if connector is AND or OR )
 * 2.set variables for author,title,subject,other field in String Array at 0 postion (if connector is phrase)
 * 3.if any text box has no value at submit form  then assign Null corresponding to field
 */
/* conditions for author field  */
         if(author.equals(""))
         {
         authors=null;
         }
         else
         {
           if(cnf1.equalsIgnoreCase("or")||cnf1.equalsIgnoreCase("and"))
           {
              authors=author.split(" ");
           }
           else         /*for Phrase*/
           {
           authors[0]=author;
           }
         }
/* conditions for Title field  */
         if(title.equals(""))
         {
         titles=null;
         }
         else
         {
         if(cnf2.equalsIgnoreCase("or")||cnf2.equalsIgnoreCase("and"))
           {
              titles=title.split(" ");
           }
           else         /*for Phrase*/
           {
           titles[0]=title;
           }
         }
/* conditions for Subject field  */
         
         if(subject.equals(""))
         {
         subjects=null;
         }
         else
         {
         if(cnf3.equalsIgnoreCase("or")||cnf3.equalsIgnoreCase("and"))
           {
              subjects=subject.split(" ");
           }
           else         /*for Phrase*/
           {
           subjects[0]=subject;
           }
         }

/* conditions for other field  */
         if(other.equals(""))
         {
         other_fields=null;
         }
         else
         {
         if(cnf4.equalsIgnoreCase("or")||cnf4.equalsIgnoreCase("and"))
           {
              other_fields=other.split(" ");
           }
           else         /*for Phrase*/
           {
           other_fields[0]=other;
           }
         }

/*
 *  public List additionalSearch(String library_id,String sub_lib,String [] author,String author_connector,
         String [] title,String title_connector,String [] subject,String subject_connector,
         String [] other_field,String other_connector,String doc_type,
         String sortby,Integer year1,Integer year2)
 */ //                        all,all,null,or,Head First,or,null,or,null,or,combined,authorName,-1,9999
//         System.out.println(lib_id+ sub_lib+ authors+cnf1+ titles[0]+ cnf2+subjects+cnf3+other_fields+cnf4+doc_type+sort+yr1+yr2);
 List<BibliographicDetailsLang> additional_search_list=new ArrayList();
 List<BibliographicDetails> additional_search_list1=new ArrayList();
   ArrayList<BibliographicDetailsLang> bib=new ArrayList<BibliographicDetailsLang>();
 ArrayList<BibliographicDetails> bib1=new ArrayList<BibliographicDetails>();
 System.out.println("Check Box"+myForm.getCheckbox());
 if(myForm.getCheckbox().equals("Checked")){
 System.out.println("Languagebbb");
 additional_search_list=opacSearchDAO.additionalSearchLang(lib_id, sub_lib, authors, cnf1, titles, cnf2,
                subjects,cnf3,other_fields,cnf4,doc_type,sort,yr1,yr2,myForm.getLanguage());
if(!additional_search_list.isEmpty())
{
    for(int f=0;f<additional_search_list.size();f++)
    {
    int bibiid=additional_search_list.get(f).getId().getBiblioId();
    List<DocumentDetails> ddd=bibdao.searchDoc2(bibiid, lib_id, sub_lib);
    if(!ddd.isEmpty()){
        bib.add(additional_search_list.get(f));
    }
    }  
}
     session.setAttribute("additional_search_list", bib);
 }
 else{
      System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNLanguagebbb");
 additional_search_list1=opacSearchDAO.additionalSearch(lib_id, sub_lib, authors, cnf1, titles, cnf2,
                subjects,cnf3,other_fields,cnf4,doc_type,sort,yr1,yr2);
if(!additional_search_list1.isEmpty())
{
    for(int f=0;f<additional_search_list1.size();f++)
    {
    int bibiid=additional_search_list1.get(f).getId().getBiblioId();
    List<DocumentDetails> ddd=bibdao.searchDoc1(bibiid, lib_id, sub_lib);
    if(!ddd.isEmpty()){
        bib1.add(additional_search_list1.get(f));
    } 
    }
}
   session.setAttribute("additional_search_list", bib1);
 }
 return mapping.findForward(SUCCESS);
    }
}


  /*    query="select * from document_details where document_type!='%%'";

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
       *
       */
/*
        if(cnf1.equals("phrase")){query=query+" and author like '%"+author+"%' ";}
        if(cnf2.equals("phrase")) {query=query+" or title like '%"+title+"%' ";}
        if(cnf3.equals("phrase")) {query=query+" or subject like '%"+subject+"%' ";}*/
        /*
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
         *
         */