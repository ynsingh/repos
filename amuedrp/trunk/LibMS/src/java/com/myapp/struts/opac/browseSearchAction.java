/*
 * BROWSE SEARCH OPAC SECTION
 */

package com.myapp.struts.opac;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import java.util.ArrayList;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.opacDAO.OpacSearchDAO;
import com.myapp.struts.utility.LoggerUtils;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

public class browseSearchAction extends org.apache.struts.action.Action {
    
  
    OpacSearchDAO opacSearchDAO=new OpacSearchDAO();
     BibliopgraphicEntryDAO bibdao=new BibliopgraphicEntryDAO();
    String phrase,title,author,accno;
    String doc_type,id,callno,publ,loc,place,sort,field,sublib_id,lib_id;
     private static Logger log4j =LoggerUtils.getLogger();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try
        {
            System.gc();
            int pageno=Integer.parseInt((String)(request.getParameter("page")==null || request.getParameter("page").isEmpty() ?"0":request.getParameter("page")));
            HttpSession session=request.getSession();
            session.removeAttribute("browse_search_list1");
            session.removeAttribute("browse_search_list");
            session.removeAttribute("simple_search_nor");
            request.setCharacterEncoding("UTF-8");
            browseSearchActionForm myform = (browseSearchActionForm)form;
            lib_id= myform.getCMBLib();
            sublib_id=myform.getCMBSUBLib();
            phrase=myform.getTXTTITLE();
            doc_type=myform.getCMBDB();
            sort=myform.getCMBSORT();
            field=myform.getCMBFIELD();

            if(lib_id!=null)
            session.setAttribute("brolib_id", lib_id);
            if(sublib_id!=null)
            session.setAttribute("brosub_lib", sublib_id);
            if(phrase!=null)
            session.setAttribute("brophrase", phrase);
            if(doc_type!=null)
            session.setAttribute("brodoc_type", doc_type);
            if(sort!=null)
            session.setAttribute("brosort", sort);
            if(field!=null)
            session.setAttribute("field",field);

            if(lib_id==null)
            lib_id=(String)session.getAttribute("brolib_id");
            if(sublib_id==null)
            sublib_id=(String)session.getAttribute("brosub_lib");
            if(phrase==null)
            phrase=(String)session.getAttribute("brophrase");
            if(doc_type==null)
            doc_type=(String)session.getAttribute("brodoc_type");
            if(sort==null)
            sort=(String)session.getAttribute("brosort");
            if(field==null)
            sort=(String)session.getAttribute("brofield");

            if(myform.getCheckbox()==null)
                myform.setCheckbox((String)session.getAttribute("brocheckbox"));

            List browse_search_list=new ArrayList();
            List<BibliographicDetails> browse_search_list1=new ArrayList();
            if(myform.getCheckbox().equals("Checked"))
            {
                 if(sort.equalsIgnoreCase("mainEntry"))
                sort="main_entry";
            if(sort.equalsIgnoreCase("publisherName"))
                sort="publisher_name";


                session.setAttribute("brocheckbox", myform.getCheckbox());
                browse_search_list=opacSearchDAO.browseLangSearch(lib_id, sublib_id,phrase, doc_type, sort, field,myform.getLanguage().toUpperCase(),pageno);
                int size=opacSearchDAO.getSize();
                session.setAttribute("simple_search_nor",size);
                session.setAttribute("browse_search_list1", browse_search_list);
            }
            else
            {
                session.setAttribute("brocheckbox", myform.getCheckbox());
                browse_search_list1=opacSearchDAO.browseSearch(lib_id, sublib_id,phrase, doc_type, sort, field,pageno);
                int size=opacSearchDAO.getSize();
                session.setAttribute("simple_search_nor",size);
                session.setAttribute("browse_search_list", browse_search_list1);
            }
       }
       catch(Exception e)
       {
        log4j.error(e);
       }
        return mapping.findForward("success");
    }
}