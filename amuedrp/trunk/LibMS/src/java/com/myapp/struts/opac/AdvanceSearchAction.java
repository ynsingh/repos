/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import  com.myapp.struts.opacDAO.OpacSearchDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
import java.util.List;
/**
 *
 * @author Faraz
 */
public class AdvanceSearchAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String searchtext1[],searchtext2[],searchtext3[]; /* array of fields*/
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
                String p1,p2,p3,yr1,yr2,cf1,cf2,cf3,cmbyr,db,title,author,accno,subject,isbn10;
                String cnf1,cnf2,callno,publ,loc,place,cnf3,c1,c2,c3,op1="",op2="",op3="";
                String sort,field,query="",query1="";
                ResultSet rs=null;
                AdvanceSearchActionForm myForm = (AdvanceSearchActionForm)form;
                String lib_id= myForm.getCMBLib();
                String sub_lib = myForm.getCMBSUBLib();
                HttpSession session = request.getSession();
                session.removeAttribute("ResultSet");
                //variables for phrases
                 p1=myForm.getTXTPHRASE1();
                 p2=myForm.getTXTPHRASE2();
                 p3=myForm.getTXTPHRASE3();
                 //variable for years
                 yr1=myForm.getTXTYR1();
                 yr2=myForm.getTXTYR2();
                 //variables for fields to be search in
                 cf1=myForm.getCMBFIELD1();
                 cf2=myForm.getCMBFIELD2();
                 cf3=myForm.getCMBFIELD3();
                 //variable for search type of year
                 cmbyr=myForm.getCMBYR();
                 //variable for selecting a document type
                 db=myForm.getCMBDB();
                 //variable for query connectors
                 cnf1=myForm.getCMBF1();
                 cnf2=myForm.getCMBF2();
                 cnf3=myForm.getCMBF3();
                 //variable for word connectors
                 c1=myForm.getCMB1();
                 c2=myForm.getCMB2();
                 c3=myForm.getCMB3();
                 //variable for sort
                 sort=myForm.getCMBSORT();

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
    yr2=String.valueOf(Integer.parseInt(yr1)+1);
    yr1=null;
    }

                 /*
 * Criteria for setting Variables to pass searching method
 * 1.Set variables for author,title,subject,other field in String Array by seperating word  (if connector is AND or OR )
 * 2.set variables for author,title,subject,other field in String Array at 0 postion (if connector is phrase)
 * 3.if any text box has no value at submit form  then assign Null corresponding to field
 */
/* conditions for phrases */
   /* author=title=subject=isbn10="";

    //for first phrase
                 if(cf1.equals("author"))
                        author = p1;
                 else if(cf1.equals("title"))
                        title = p1;
                 else if(cf1.equals("subject"))
                        subject = p1;
                 else if(cf1.equals("isbn10"))
                        isbn10 = p1;
    //for second phrase
                 if(cf2.equals("author"))
                        author += " " + p2;
                 else if(cf2.equals("title"))
                        title += " "+p2;
                 else if(cf2.equals("subject"))
                        subject += " "+p2;
                 else if(cf2.equals("isbn10"))
                        isbn10 += " "+p2;

    //for third phrase
                 if(cf3.equals("author"))
                        author = " "+p3;
                 else if(cf3.equals("title"))
                        title = " "+p3;
                 else if(cf3.equals("subject"))
                        subject = " "+p3;
                 else if(cf3.equals("isbn10"))
                        isbn10 = " "+p3;

           author = author.replace(" ", "  ");
           subject = subject.replace(" ", "  ");
           title = title.replace(" ", "  ");
           isbn10 = isbn10.replace(" ", "  ");*/
         /* conditions for author field  */

         if(p1.equals("")||p1.equals(" "))
         {
         searchtext1=null;
         }
         else
         {
           if(c1.equalsIgnoreCase("or")||c1.equalsIgnoreCase("and"))
           {
              searchtext1=p1.split(" ");
           }
           else         /*for Phrase*/
           {
           searchtext1[0]=p1;
           }
         }
/* conditions for Title field  */
         if(p2.equals("")||p2.equals(" "))
         {
         searchtext2=null;
         }
         else
         {
         if(c2.equalsIgnoreCase("or")||c2.equalsIgnoreCase("and"))
           {
              searchtext2=p2.split(" ");
           }
           else         /*for Phrase*/
           {
           searchtext2[0]=p2;
           }
         }
/* conditions for Subject field  */

         if(p3.equals(""))
         {
         searchtext3=null;
         }
         else
         {
         if(cnf3.equalsIgnoreCase("or")||cnf3.equalsIgnoreCase("and"))
           {
              searchtext3=p3.split(" ");
           }
           else         /*for Phrase*/
           {
           searchtext3[0]=p3;
           }
         }


/*
 *  public List additionalSearch(String library_id,String sub_lib,String [] author,String author_connector,
         String [] title,String title_connector,String [] subject,String subject_connector,
         String [] other_field,String other_connector,String doc_type,
         String sortby,Integer year1,Integer year2)
 */ //                        all,all,null,or,Head First,or,null,or,null,or,combined,authorName,-1,9999
//         System.out.println(lib_id+ sub_lib+ authors+cnf1+ titles[0]+ cnf2+subjects+cnf3+other_fields+cnf4+doc_type+sort+yr1+yr2);
  OpacSearchDAO opac = new OpacSearchDAO();
  Integer year1 = (yr1==null?0:Integer.parseInt(yr1));
  Integer year2 = (yr2==null?0:Integer.parseInt(yr2));
  System.out.println("year1="+year1+" year2="+year2);
                 List advance_search_list=(List)opac.advanceSearch(lib_id, sub_lib, searchtext1, c1, searchtext2, c2,searchtext3,c3,cnf1,cnf2,cnf3,cf1,cf2,cf3,db,sort,year1,year2);
     /* query="select * from document_details";
          int flag=0;
          if(db.equals("combined")){query="select * from document_details "; flag=0;}
        else                     {query=query+" where document_type='"+db+"' ";flag=1;}
            query1=query;
          if (!cf1.equalsIgnoreCase("any field"))
            {
            String rep1 = "%' " + c1 + " " + cf1 + " like '%";

            if(!c1.equalsIgnoreCase("phrase") && !p1.equalsIgnoreCase(""))
            {
                p1 = p1.replace(" ",rep1);
                if (flag==1) p1 = " and " + cf1 + " like '%" + p1 + "%'";
                else {p1 = " where " + cf1 + " like '%" + p1 + "%'";flag=1;}
                System.out.println("p1="+p1);
            }


            if (!p1.equalsIgnoreCase("")) query = query + p1;
            }
        else
            {
                String p11,p12,p13,p14,p15,p16,p17;

                p11=p12=p13=p14=p15=p16=p17=p1;
                String rep1 = "%' " + c1 + " author_name like '%";
                String rep2 = "%' " + c1 + " title like '%";
                String rep3 = "%' " + c1 + " subject like '%";
                String rep4 = "%' " + c1 + " publishing_year like '%";
                String rep5 = "%' " + c1 + " publisher_name like '%";
                //String rep6 = "%' " + c1 + " accessionno like '%";
                String rep7 = "%' " + c1 + " publication_place like '%";

            if(!p1.equalsIgnoreCase(""))
            {
                p11 = p11.replace(" ",rep1);
                p12 = p12.replace(" ",rep2);
                p13 = p13.replace(" ",rep3);
                p14 = p14.replace(" ",rep4);
                p15 = p15.replace(" ",rep5);
               // p16 = p16.replace(" ",rep6);
                p17 = p17.replace(" ",rep7);

                if (flag==1)
                    p11 = " and (author_main like '%" + p11 + "%'";
                else{
                    p11 = " where (author_main like '%" + p11 + "%'";
                    flag=1;
                }
                p12 = " or title like '%" + p12 + "%'";
                p13 = " or subject like '%" + p13 + "%'";
                p14 = " or publishing_year like '%" + p14 + "%'";
                p15 = " or publisher_name like '%" + p15 + "%'";
                //p16 = " or accessionno like '%" + p16 + "%'";
                p17 = " or publication_place like '%" + p17 + "%')";

                System.out.println("p11="+p11);
            }


            if (!p1.equalsIgnoreCase("")) query = query + p11+ p12+ p13+ p14+ p15+ p16+ p17;

            System.out.println("query any field="+query);
            }
           if (!cf2.equalsIgnoreCase("any field"))
          {
          String rep2 = "%' " + c2 + " " + cf2 + " like '%";
        if(!c2.equalsIgnoreCase("phrase") && !p2.equalsIgnoreCase(""))
        {
           p2 = p2.replace(" ",rep2);
            p2 = " " + cf2 + " like '%" + p2 + "%'";
            System.out.println("p2="+p2);
        }
       if (!p2.equalsIgnoreCase("")) query = query + " " + cnf1 + " " + p2;
        }
          else
            {
                String p11,p12,p13,p14,p15,p16,p17;

                p11=p12=p13=p14=p15=p16=p17=p2;
                String rep1 = "%' " + c2 + " author_main like '%";
                String rep2 = "%' " + c2 + " title like '%";
                String rep3 = "%' " + c2 + " subject like '%";
                String rep4 = "%' " + c2 + " publishing_year like '%";
                String rep5 = "%' " + c2 + " publisher_name like '%";
               // String rep6 = "%' " + c2 + " accessionno like '%";
                String rep7 = "%' " + c2 + " publication_place like '%";

            if(!p2.equalsIgnoreCase(""))
            {
                p11 = p11.replace(" ",rep1);
                p12 = p12.replace(" ",rep2);
                p13 = p13.replace(" ",rep3);
                p14 = p14.replace(" ",rep4);
                p15 = p15.replace(" ",rep5);
               // p16 = p16.replace(" ",rep6);
                p17 = p17.replace(" ",rep7);

                p11 = " and (author_main like '%" + p11 + "%'";
                p12 = " or title like '%" + p12 + "%'";
                p13 = " or subject like '%" + p13 + "%'";
                p14 = " or publishing_year like '%" + p14 + "%'";
                p15 = " or publisher_name like '%" + p15 + "%'";
               // p16 = " or accessionno like '%" + p16 + "%'";
                p17 = " or publication_place like '%" + p17 + "%')";

                System.out.println("p11="+p11);
            }


            if (!p1.equalsIgnoreCase("")) query = query + p11+ p12+ p13+ p14+ p15+ p16+ p17;

            System.out.println("query any field="+query);
            }
           if (!cf3.equalsIgnoreCase("any field"))
          {
          String rep3 = "%' " + c3 + " " + cf3 + " like '%";
        if(!c3.equalsIgnoreCase("phrase") && !p3.equalsIgnoreCase(""))
        {
           p3 = p3.replace(" ",rep3);
            p3 = " " + cf3 + " like '%" + p3 + "%'";
            System.out.println("p2="+p3);
        }
       if (!p3.equalsIgnoreCase("")) query = query + " " + cnf3 + " " + p3;
        }
          else
            {
                 String p11,p12,p13,p14,p15,p16,p17;

                p11=p12=p13=p14=p15=p16=p17=p2;
                String rep1 = "%' " + c2 + " author_main like '%";
                String rep2 = "%' " + c2 + " title like '%";
                String rep3 = "%' " + c2 + " subject like '%";
                String rep4 = "%' " + c2 + " publishing_year like '%";
                String rep5 = "%' " + c2 + " publisher_name like '%";
             //   String rep6 = "%' " + c2 + " accessionno like '%";
                String rep7 = "%' " + c2 + " publication_place like '%";

            if(!p2.equalsIgnoreCase(""))
            {
                p11 = p11.replace(" ",rep1);
                p12 = p12.replace(" ",rep2);
                p13 = p13.replace(" ",rep3);
                p14 = p14.replace(" ",rep4);
                p15 = p15.replace(" ",rep5);
               // p16 = p16.replace(" ",rep6);
                p17 = p17.replace(" ",rep7);

                p11 = " and (author_main like '%" + p11 + "%'";
                p12 = " or title like '%" + p12 + "%'";
                p13 = " or subject like '%" + p13 + "%'";
                p14 = " or publishing_year like '%" + p14 + "%'";
                p15 = " or publisher_name like '%" + p15 + "%'";
             //   p16 = " or accessionno like '%" + p16 + "%'";
                p17 = " or publication_place like '%" + p17 + "%')";

                System.out.println("p11="+p11);
            }


            if (!p1.equalsIgnoreCase("")) query = query + p11+ p12+ p13+ p14+ p15+ p16+ p17;

            System.out.println("query any field="+query);
            }

          if(cmbyr.equals("between")){
              if (flag==0){
              query=query+" where publishing_year between "+yr1+" and "+yr2;flag=1;}
              else{
                  query=query+" and publishing_year between "+yr1+" and "+yr2;}
              }
            if (flag==0){
            if(cmbyr.equals("upto")){query=query+" where publishing_year <="+yr1;flag=1;}
            if(cmbyr.equals("after")){query=query+" where publishing_year >="+yr1;flag=1;}

            }
            else
             {
            if(cmbyr.equals("upto")){query=query+" and publishing_year <="+yr1;}
            if(cmbyr.equals("after")){query=query+" and publishing_year >="+yr1;}
            }
          if (!lib_id.equalsIgnoreCase("all") && flag==1)
            query = query+ " and library_id='"+ lib_id  +"'";
          else if (!lib_id.equalsIgnoreCase("all") && flag==0) query = query+ " where library_id='"+ lib_id  +"'";
*/



         // System.out.println("Advance Query="+query);
       //  rs = MyQueryResult.getMyExecuteQuery(query);
         //if(rs.next())
         session.setAttribute("ResultSet", advance_search_list);

        return mapping.findForward(SUCCESS);
    }
}
