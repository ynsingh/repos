

/*
 * SIMPLE SEARCH OPAC SECTION
 */

package com.myapp.struts.opac;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import java.util.ArrayList;
import com.myapp.struts.opacDAO.OpacSearchDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.log4j.Logger;
import com.myapp.struts.utility.LoggerUtils;

public class SimpleSearchAction extends org.apache.struts.action.Action {
    
        /* forward name="success" path="" */
        private static final String SUCCESS = "success";
        String p,cmbyr,cf,db,cnf,sort;
        String yr1,yr2;
        String lib_id,sub_lib,phrase[];
        OpacSearchDAO simpleSearchDAO=new OpacSearchDAO();
        BibliopgraphicEntryDAO bibdao=new BibliopgraphicEntryDAO();
        private static Logger log4j =LoggerUtils.getLogger();
        @Override
        public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
       try
       {
           System.gc();
            int pageno=Integer.parseInt((String)(request.getParameter("page")==null || request.getParameter("page").isEmpty() ?"0":request.getParameter("page")));
            SimpleSearchActionForm simpleform = (SimpleSearchActionForm)form;
            HttpSession session =request.getSession();
            request.setCharacterEncoding("UTF-8");
            session.removeAttribute("simple_search_list");
            session.removeAttribute("simple_search_list1");
            session.removeAttribute("simple_search_nor");
            lib_id= simpleform.getCMBLib();
            sub_lib=simpleform.getCMBSUBLib();
            if(simpleform.getTXTPHRASE()!=null)
            p = simpleform.getTXTPHRASE();
            cmbyr = simpleform.getCMBYR();
            if(simpleform.getTXTYR1()!=null)
            yr1 = (String)simpleform.getTXTYR1().trim();
            if(simpleform.getTXTYR2()!=null)
            yr2 = simpleform.getTXTYR2();
            
            cf = simpleform.getCMBFIELD();
            db = simpleform.getCMBDB();
            cnf = simpleform.getCMBCONN();
            sort= simpleform.getCMBSORT();

            if(lib_id!=null)
            session.setAttribute("simplib_id", lib_id);
            if(sub_lib!=null)
            session.setAttribute("simpsub_lib", sub_lib);
            if(p!=null)
            session.setAttribute("simpp", p);
            if(yr1!=null)
            session.setAttribute("simpyr1", yr1);
            if(yr2!=null)
                session.setAttribute("simpyr2", yr2);
            if(cf!=null)
            session.setAttribute("simpcf", cf);
            if(db!=null)
            session.setAttribute("simpdb", db);
            if(cnf!=null)
            session.setAttribute("simpcnf", cnf);
            if(sort!=null)
            session.setAttribute("simpsort", sort);
            if(cmbyr!=null)
            session.setAttribute("simpcmbyr", cmbyr);

            if(lib_id==null)
                lib_id=(String)session.getAttribute("simplib_id");
            if(sub_lib==null)
                sub_lib=(String)session.getAttribute("simpsub_lib");
      

            if(cf==null || cf.isEmpty())
                cf=(String)session.getAttribute("simpcf");
            if(db==null || db.isEmpty())
                db=(String)session.getAttribute("simpdb");
            if(cnf==null || cnf.isEmpty())
                cnf=(String)session.getAttribute("simpcnf");
            if(sort==null || sort.isEmpty())
                sort=(String)session.getAttribute("simpsort");
            if(cmbyr==null || cmbyr.isEmpty())
                cmbyr=(String)session.getAttribute("simpcmbyr");
            System.out.println("I am here"+lib_id+sub_lib+phrase+cnf+db+sort+cf+yr1+yr2+p);
            log4j.error("I am here"+lib_id+sub_lib+phrase+cnf+db+sort+cf+yr1+yr2+p);
            log4j.error("I am here After "+lib_id+sub_lib+phrase+cnf+db+sort+cf+yr1+yr2+p);
            session.getAttribute("documentDetail1");
            if(p!=null)
            phrase=p.split(" ");
            log4j.error("*************************"+phrase.length+phrase[0]+".....");

        /*
        * Execute query by passing parameters and set resulting List in session
        */
        List simple_search_list=new ArrayList();
        List<BibliographicDetails> simple_search_list1=new ArrayList();

        if(simpleform.getCheckbox()==null)
            simpleform.setCheckbox((String)session.getAttribute("simpcheckbox"));

        log4j.error("Check Box"+simpleform.getCheckbox()+" Get");

        if(simpleform.getCheckbox().equals("Checked"))
        {
            if(sort.equalsIgnoreCase("mainEntry"))
                sort="main_entry";
            if(sort.equalsIgnoreCase("publisherName"))
                sort="publisher_name";


             session.setAttribute("simpcheckbox", simpleform.getCheckbox());
             simple_search_list=simpleSearchDAO.simpleLangSearch(lib_id,sub_lib,phrase,cnf,db,sort,cf,yr1,yr2,simpleform.getLanguage().toUpperCase(),pageno,cmbyr);
             int size=simpleSearchDAO.getSize();
                     request.setAttribute("from", pageno*100);
                if(simple_search_list1.size()<100)
                    request.setAttribute("to", (pageno*100)+simple_search_list1.size());
                else
                    request.setAttribute("to", (pageno*100)+100);

             session.setAttribute("simple_search_nor",size);
            
             session.setAttribute("simple_search_list1", simple_search_list);
          

             
        }
        else
        {
            session.setAttribute("simpcheckbox", simpleform.getCheckbox());
            simple_search_list1=simpleSearchDAO.simpleSearch(lib_id,sub_lib,phrase,cnf,db,sort,cf,yr1,yr2,pageno,cmbyr);
              int size=simpleSearchDAO.getSize();
              
                session.setAttribute("simple_search_nor",size);
                session.setAttribute("simple_search_list", simple_search_list1);

                    request.setAttribute("from", pageno*100);
                if(simple_search_list1.size()<100)
                    request.setAttribute("to", (pageno*100)+simple_search_list1.size());
                else
                    request.setAttribute("to", (pageno*100)+100);
            
            session.setAttribute("simple_search_list", simple_search_list1);
        }
       }
       catch(Exception e)
       {
           e.printStackTrace();
        log4j.error(e.toString());
       }
       System.out.println("I am here");
        return mapping.findForward(SUCCESS);
    }
}