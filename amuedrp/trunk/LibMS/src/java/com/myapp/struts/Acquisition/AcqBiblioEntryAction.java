/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import org.apache.commons.lang.StringUtils;
import com.myapp.struts.hbm.*;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author maqbool
 */
public class AcqBiblioEntryAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao ado=new AcquisitionDao();
    AcqBibliography acqbib1=new AcqBibliography();
    AcqBibliographyId acqbib1id=new AcqBibliographyId();
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqBiblioActionForm acqbib=(AcqBiblioActionForm)form;

          HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String doc_type=acqbib.getDocument_type();
        String title=acqbib.getTitle();
        String isbn=acqbib.getIsbn();
        if(StringUtils.isEmpty(title)){
        String titlemsg="This field can't be left blank";
        request.setAttribute("titlemsg", titlemsg);
        }
        if(StringUtils.isEmpty(doc_type)){
        String option="Select one option";
        request.setAttribute("option", option);
        }
        String button=acqbib.getButton();
        acqbib.setAuthor("");
          List l=(List) ado.searchDoc1(library_id, sub_library_id, title);

        if(doc_type.equals("Book"))
            {
        if(button.equals("New")){
            if(!l.isEmpty()){
            session.setAttribute("title", title);
            session.setAttribute("doc_type", doc_type);
            session.setAttribute("opacList", l);
             return mapping.findForward("duplicatetitle");
            }

        return mapping.findForward("new");
        }
        }
          if(button.equals("Update"))
        {
              if(!l.isEmpty()){

              session.setAttribute("title",title);
              session.setAttribute("doc_type", doc_type);
              session.setAttribute("opacList", l);
              return mapping.findForward("duplicatetitle1");

              }

              else{
                    String msg="Title is not found";
                    request.setAttribute("titlenotfound", msg);
                  return mapping.findForward("fail");
              }

        }
        if(button.equals("Delete"))

        {
                if(!l.isEmpty())

                {
                 session.setAttribute("title",title);
              session.setAttribute("doc_type", doc_type);
              session.setAttribute("opacList", l);
              return mapping.findForward("duplicatetitledelete");

                }     
              else{
                    String msg="Title is not found";
                    request.setAttribute("titlenotfound", msg);
                  return mapping.findForward("fail");
              }
        }
           if(button.equals("View"))
        {
               if(!l.isEmpty())
               {
              session.setAttribute("title",title);
              session.setAttribute("doc_type", doc_type);
              session.setAttribute("opacList", l);
              return mapping.findForward("duplicatetitleview");
               }
                         else{
                    String msg="Title is not found";
                    request.setAttribute("titlenotfound", msg);
                  return mapping.findForward("fail");
              }
         }
        return mapping.findForward("fail");
    }
}
