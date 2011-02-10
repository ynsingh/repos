/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.*;
/**
 *
 * @author Faraz
 */
public class AdvanceSearchAction extends org.apache.struts.action.Action {
    
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
                String p1,p2,p3,yr1,yr2,cf1,cf2,cf3,cmbyr,db,title,author,accno;
                String cnf1,cnf2,callno,publ,loc,place,cnf3,c1,c2,c3,op1="",op2="",op3="";
                String sort,field,query="",query1="";
                ResultSet rs=null;
                AdvanceSearchActionForm myForm = (AdvanceSearchActionForm)form;
                String lib_id= myForm.getCMBLib();
                HttpSession session = request.getSession();
                session.removeAttribute("ResultSet");
                p1=myForm.getTXTPHRASE1();
         p2=myForm.getTXTPHRASE2();
         p3=myForm.getTXTPHRASE3();
         yr1=myForm.getTXTYR1();
         yr2=myForm.getTXTYR2();
         cf1=myForm.getCMBFIELD1();
         cf2=myForm.getCMBFIELD2();
         cf3=myForm.getCMBFIELD3();
         cmbyr=myForm.getCMBYR();
         db=myForm.getCMBDB();
         cnf1=myForm.getCMBF1();
         cnf2=myForm.getCMBF2();
         cnf3=myForm.getCMBF3();
         c1=myForm.getCMB1();
         c2=myForm.getCMB2();
         c3=myForm.getCMB3();

        

          query="select * from document_details";
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
 /*     if(cnf1.equals("phrase")){op1="or";}
      else                     {op1=cnf1;}
      if(cnf2.equals("phrase")){op2="or";}
       else                     {op2=cnf2;}
      if(cnf3.equals("phrase")){op3="or";}
       else                     {op3=cnf3;}


        if(cnf1.equals("or") || cnf1.equals("and") || cnf2.equals("or") || cnf2.equals("and") || cnf3.equals("or"))
          {
            if (!p1.equals("") && !cnf1.equals("phrase")) query=query + " and "+ cf1 +" like '"+p1+"%' ";
            else if (!p1.equals("")) query=query+" and "+ cf1 +" like '%"+p1+"%' ";
            if (p1.equals("") && !p2.equals("") && !cnf2.equals("phrase")) query=query + " and "+ cf2 +" like '"+p2+"%' ";
              else if(!p2.equals("") && !cnf2.equals("phrase")) query = query + " " + op1 + " "+ cf2 +" like '" + p2 + "%' ";
              else if(!p2.equals("")) query = query + " or "+ cf2 +" like '%" + p2 + "%' ";
            if (p1.equals("") && p2.equals("") && !p3.equals("") && !cnf3.equals("phrase") ) query=query + " and "+ cf3 +" like '"+p3+"%'";
              else if(!p3.equals("") && !cnf3.equals("phrase")) query = query + " " + op2 + " "+ cf3 +" like '" + p3 + "%'";
            else if (!p3.equals("")) query=query+" or "+ cf3 +" like '%"+p3+"%' ";
        }
/*
        if(cnf1.equals("phrase")){query=query+" and author like '%"+author+"%' ";}
        if(cnf2.equals("phrase")) {query=query+" or title like '%"+title+"%' ";}
        if(cnf3.equals("phrase")) {query=query+" or subject like '%"+subject+"%' ";}*/
       /* if(!other.equalsIgnoreCase(""))
        if(cnf4.equals("phrase"))
         {
            query=q+" and (author like'%"+other+"%' or title like '%"+other+"%' or subject like '%"+other+"%' or pub_yr like '%"+other+"%' " +
                    "or publisher like '%"+other+"%' or accessionno like'%"+other+"%' or pubPlace like'%"+other+"%')";
         }
        else
        {
            query=q+" and (author like'"+other+"%' or title like '"+other+"%' or subject like '"+other+"%' or pub_yr like '"+other+"%' " +
                    "or publisher like '"+other+"%' or accessionno like'"+other+"%' or pubPlace like'"+other+"%')";
        }

                //if(cnf4.equals("or") || cnf4.equals("and")){
                   // query=query+" or author='"+other+"' or title='"+other+"' or subject='"+other+"' or pub_yr='"+other+"' " +
                     //   "or publisher='"+other+"' or accessionNo='"+other+"' or pubplace='"+other+"'";
                if(cmbyr.equals("all")){query=query;}
                if(cmbyr.equals("between")){query=query+" "+cnf3+" pub_yr between "+yr1+" and "+yr2;}
                if(cmbyr.equals("upto")){query=query+" "+cnf3+" pub_yr <="+yr1;}
                if(cmbyr.equals("after")){query=query+" "+cnf3+" pub_yr >="+yr1;}
*/
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

         
          System.out.println("Advance Query="+query);
         rs = MyQueryResult.getMyExecuteQuery(query);
         //if(rs.next())
         session.setAttribute("ResultSet", rs);

        return mapping.findForward(SUCCESS);
    }
}
