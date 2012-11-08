/*
 * ISBN SEARCH ACTION OPAC
 */

package com.myapp.struts.opac;
import com.myapp.struts.hbm.BibliographicDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import com.myapp.struts.opacDAO.*;
import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import com.myapp.struts.utility.LoggerUtils;
import java.util.List;
import org.apache.log4j.Logger;

public class SearchByIsbnAction extends org.apache.struts.action.Action
{
    private static final String SUCCESS = "success";
    OpacSearchDAO osdao= new OpacSearchDAO();
    BibliographicEntryDAO bibdao=new BibliographicEntryDAO();
    private static Logger log4j =LoggerUtils.getLogger();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            System.gc();
            int pageno=Integer.parseInt((String)(request.getParameter("page")==null || request.getParameter("page").isEmpty() ?"0":request.getParameter("page")));
            HttpSession session = request.getSession();
            session.removeAttribute("documentdetail1");
            session.removeAttribute("documentdetail");
            session.removeAttribute("simple_search_nor");
            
            SearchByIsbnActionForm myForm = (SearchByIsbnActionForm)form;
            String lib_id=myForm.getCMBLib();
            String   isbn = myForm.getTXTKEY();
            String sublib=myForm.getCMBSUBLib();
            if(lib_id!=null)
                session.setAttribute("isbnlib_id", lib_id);
            if(sublib!=null)
                session.setAttribute("isbnsublib", sublib);
            if(isbn!=null)
                session.setAttribute("isbnisbn",isbn);

            if(lib_id==null)
                lib_id=(String)session.getAttribute("isbnlib_id");
            if(sublib==null)
                sublib=(String)session.getAttribute("isbnsublib");
            if(isbn==null)
                isbn=(String)session.getAttribute("isbnisbn");

         //   if(myForm.getCheckbox()==null)
            //    myForm.setCheckbox((String)session.getAttribute("isbncheckbox"));

        //if(myForm.getCheckbox().equals("Checked"))
         //{
                List documentdetail1  =(List)osdao.isbnLangSearch(isbn, lib_id, sublib,pageno);
                int size=osdao.getSize();

                if(size>=1){
                    session.setAttribute("simple_search_nor",size);
                    session.setAttribute("documentdetail1", documentdetail1);
                      return mapping.findForward(SUCCESS);
                }
        else{
          System.out.println("OPAC ISBN ##########"+isbn);
                List<BibliographicDetails> documentdetail  =(List)osdao.isbnSearch(isbn, lib_id, sublib,pageno);
                 size=osdao.getSize();
                session.setAttribute("simple_search_nor",size);
                session.setAttribute("documentdetail", documentdetail);
               }
        }
        catch(Exception e)
        {
        log4j.error(e);
        }
            return mapping.findForward(SUCCESS);
    }
}
