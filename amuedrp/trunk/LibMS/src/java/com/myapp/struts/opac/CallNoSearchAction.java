/*
 * CALL NO SEARCH ACTION OPAC
 */
package com.myapp.struts.opac;
import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import com.myapp.struts.opacDAO.*;
import com.myapp.struts.utility.LoggerUtils;
import java.util.List;
import org.apache.log4j.Logger;
public class CallNoSearchAction extends org.apache.struts.action.Action
{
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    OpacSearchDAO osdao= new OpacSearchDAO();
    BibliopgraphicEntryDAO bibdao=new BibliopgraphicEntryDAO();
    private static Logger log4j =LoggerUtils.getLogger();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception 
    {
        try
        {
            System.gc();
            HttpSession session = request.getSession();
            session.removeAttribute("callnodocumentdetail");
            session.removeAttribute("callnodocumentdetail1");
            session.removeAttribute("simple_search_nor");
            int pageno=Integer.parseInt((String)(request.getParameter("page")==null || request.getParameter("page").isEmpty() ?"0":request.getParameter("page")));
            CallNoSearchActionForm myform = (CallNoSearchActionForm)form;
            String lib_id=myform.getCMBLib();
            String callno = myform.getTXTKEY();
            String sublib =myform.getCMBSUBLib();

            if(lib_id!=null)
                session.setAttribute("calllib_id", lib_id);
            if(sublib!=null)
                session.setAttribute("callsublib", sublib);
            if(callno!=null)
                session.setAttribute("callcallno",callno);
            
             if(lib_id==null)
                lib_id=(String)session.getAttribute("calllib_id");
            if(sublib==null)
                sublib=(String)session.getAttribute("callsublib");
            if(callno==null)
                callno=(String)session.getAttribute("callcallno");

//            if(myform.getCheckbox()==null)
//                myform.setCheckbox((String)session.getAttribute("callcheckbox"));

                List documentdetail1  =(List)osdao.callNoLangSearch(callno, lib_id, sublib,myform.getLanguage().toUpperCase(),pageno);
                int size=osdao.getSize();
               // session.setAttribute("simple_search_nor",size);


//           if(myform.getCheckbox().equals("Checked"))
//           {
//               session.setAttribute("callcall", myform.getCheckbox());
//                List documentdetail  =(List)osdao.callNoLangSearch(callno, lib_id, sublib,myform.getLanguage().toUpperCase(),pageno);
//                int size=osdao.getSize();
//                session.setAttribute("simple_search_nor",size);
//                session.setAttribute("callnodocumentdetail1", documentdetail);
//            }
//           else
//           {
                if(size>=1)
                {
                 session.setAttribute("simple_search_nor",size);
                 session.setAttribute("callnodocumentdetail1", documentdetail1);
                 System.out.println("MLI");
                }
                else
                {
                    //session.setAttribute("callcall", myform.getCheckbox());
                    System.out.println("MLI1");
                    List<BibliographicDetails> documentdetail  =(List)osdao.callNoSearch(callno, lib_id, sublib,pageno);
                    size=osdao.getSize();
                    session.setAttribute("simple_search_nor",size);
                    session.setAttribute("callnodocumentdetail", documentdetail);
                }
            //}
        }
        catch(Exception e)
        {
        log4j.error(e);
        }
        return mapping.findForward(SUCCESS);
    }
}
