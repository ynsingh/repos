

// ADDITIONAL SEARCH ACTION
package com.myapp.struts.opac;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.opacDAO.OpacSearchDAO;
import com.myapp.struts.utility.LoggerUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class AdditionalSearchAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    String authors[],titles[],subjects[],other_fields[]; /* array of fields*/
    OpacSearchDAO opacSearchDAO=new OpacSearchDAO();
    BibliographicEntryDAO bibdao=new BibliographicEntryDAO();
    private static Logger log4j =LoggerUtils.getLogger();
    String yr1,yr2;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
      try
      {
          System.gc();
         int pageno=Integer.parseInt((String)(request.getParameter("page")==null || request.getParameter("page").isEmpty() ?"0":request.getParameter("page")));
         String subject,cmbyr,title,author,sub_lib;
         String other,cnf1,cnf2,cnf3,cnf4;
         String doc_type,sort;         
         request.setCharacterEncoding("UTF-8");
         HttpSession session = request.getSession();
         session.removeAttribute("additional_search_list");
         session.removeAttribute("additional_search_list1");
         session.removeAttribute("simple_search_nor");

         AdditionalSearchActionForm myForm = (AdditionalSearchActionForm)form;
         String lib_id= myForm.getCMBLib();
         sub_lib=myForm.getCMBSUBLib();
         author=myForm.getTXTAUTHOR();
         title=myForm.getTXTTITLE();
         subject=myForm.getTXTSUBJECT();
         if(myForm.getTXTYR1()!=null)
         yr1 = myForm.getTXTYR1().trim();
         if(myForm.getTXTYR2()!=null)
         yr2 = myForm.getTXTYR2().trim();
         other=myForm.getTXTOTHER();
         cmbyr=myForm.getCMBYR();
         doc_type=myForm.getCMBDB();
         sort=myForm.getCMBSORT();
         cnf1=myForm.getCMBCONN1();
         cnf2=myForm.getCMBCONN2();
         cnf3=myForm.getCMBCONN3();
         cnf4=myForm.getCMBCONN4();

            if(lib_id!=null)
            session.setAttribute("addlib_id", lib_id);
            if(sub_lib!=null)
            session.setAttribute("addsub_lib", sub_lib);
            if(yr1!=null)
            session.setAttribute("addyr1", yr1);
            if(yr2!=null)
            session.setAttribute("addyr2", yr2);
            if(author!=null)
            session.setAttribute("addauthor", author);
            if(title!=null)
            session.setAttribute("addtitle", title);
            if(subject!=null)
            session.setAttribute("addsubject", subject);
            if(sort!=null)
            session.setAttribute("addsort", sort);
            if(cmbyr!=null)
            session.setAttribute("addcmbyr", cmbyr);
            if(other!=null)
            session.setAttribute("addother", other);
            if(doc_type!=null)
            session.setAttribute("adddoc_type", doc_type);
            if(cnf1!=null)
            session.setAttribute("addcnf1", cnf1);
            if(cnf2!=null)
            session.setAttribute("addcnf2", cnf2);
            if(cnf3!=null)
            session.setAttribute("addcnf3", cnf3);
            if(cnf4!=null)
            session.setAttribute("addcnf4", cnf4);


            if(lib_id==null)
            lib_id=(String)session.getAttribute("addlib_id");
            if(sub_lib==null)
            sub_lib=(String)session.getAttribute("addsub_lib");
            if(author==null)
            author=(String)session.getAttribute("addauthor");
            if(title==null)
            title=(String)session.getAttribute("addtitle");
            if(subject==null)
            subject=(String)session.getAttribute("addsubject");
            if(sort==null)
            sort=(String)session.getAttribute("addsort");
            if(cmbyr==null)
            cmbyr=(String)session.getAttribute("addcmbyr");
            if(other==null)
            other=(String)session.getAttribute("addother");
            if(doc_type==null)
            doc_type=(String)session.getAttribute("adddoc_type");
            if(cnf1==null)
            cnf1=(String)session.getAttribute("addcnf1");
            if(cnf2==null)
            cnf2=(String)session.getAttribute("addcnf2");
            if(cnf3==null)
            cnf3=(String)session.getAttribute("addcnf3");
            if(cnf4==null)
            cnf4=(String)session.getAttribute("addcnf4");
//System.out.println(cnf2);

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

        /* conditions for other field  
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
           else         
           {
           other_fields[0]=other;
           }
         }
         * 
         */
            if(myForm.getCheckbox()==null)
                myForm.setCheckbox((String)session.getAttribute("addcheckbox"));



        List additional_search_list=new ArrayList();
        List<BibliographicDetails> additional_search_list1=new ArrayList();

          if(myForm.getCheckbox()==null)
                myForm.setCheckbox((String)session.getAttribute("addcheckbox"));

        if(myForm.getCheckbox().equals("Checked"))
         {
             if(sort.equalsIgnoreCase("mainEntry"))
                sort="main_entry";
            if(sort.equalsIgnoreCase("publisherName"))
                sort="publisher_name";

              session.setAttribute("addcheckbox", myForm.getCheckbox());
              additional_search_list=opacSearchDAO.additionalSearchLang(lib_id, sub_lib, authors, cnf1, titles, cnf2,
              subjects,cnf3,other_fields,cnf4,doc_type,sort,yr1,yr2,myForm.getLanguage().toUpperCase(),pageno,cmbyr);
                 int size=opacSearchDAO.getSize();
                session.setAttribute("simple_search_nor",size);
                    request.setAttribute("from", pageno*100);
                if(additional_search_list1.size()<100)
                    request.setAttribute("to", (pageno*100)+additional_search_list1.size());
                else
                    request.setAttribute("to", (pageno*100)+100);
                session.setAttribute("additional_search_list1", additional_search_list);
        }
        else
        {
                session.setAttribute("addcheckbox", myForm.getCheckbox());
                additional_search_list1=opacSearchDAO.additionalSearch(lib_id, sub_lib, authors, cnf1, titles, cnf2,
                subjects,cnf3,other_fields,cnf4,doc_type,sort,yr1,yr2,pageno,cmbyr);

                 int size=opacSearchDAO.getSize();
                session.setAttribute("simple_search_nor",size);
                    request.setAttribute("from", pageno*100);
                if(additional_search_list1.size()<100)
                    request.setAttribute("to", (pageno*100)+additional_search_list1.size());
                else
                    request.setAttribute("to", (pageno*100)+100);
                session.setAttribute("additional_search_list", additional_search_list1);
        }
      }
       catch(Exception e)
       {
           e.printStackTrace();
       log4j.error(e);
       }
        return mapping.findForward(SUCCESS);
    }
}

