/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Editmarc;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author zeeshan
 */
public class ShowAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    NewMARCDAO marcdao = new NewMARCDAO();
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
              NewStrutsActionForm nsaf=(NewStrutsActionForm)form;
              String button=nsaf.getBtn();
              String tagnumber=nsaf.getTagnumber();

              int bibid=0;
           HttpSession session = request.getSession();

          String library_id = (String) session.getAttribute("library_id");
          String sub_library_id = (String) session.getAttribute("sublibrary_id");

          
            if(button.equals("New")){

               bibid = marcdao.returnMaxBiblioId(library_id, sub_library_id);
                   session.setAttribute("biblio_id", bibid);
               List<String> editmarc11 = marcdao.show();

               request.setAttribute("tagno", editmarc11);

            return mapping.findForward(SUCCESS);
            }

            else if(button.equals("Add")){
                  request.setAttribute("tagnumber", tagnumber);
                  return mapping.findForward("add");
              }


            else if(button.equals("Modify")){
                 List<Editmarc> edm=marcdao.getMarc(tagnumber);
                 if(edm.isEmpty()){
                     request.setAttribute("msg", "record not found !");
                  return mapping.findForward("failure");
                 }
                 else{
                 request.setAttribute("edm", edm);
                  return mapping.findForward("modify");
                }
              }

              
            else if(button.equals("Delete"))
           {
                 List<Editmarc> edm=marcdao.getMarc(tagnumber);
               if(edm.isEmpty()){
                     request.setAttribute("msg", "record not found !");
                  return mapping.findForward("failure");
                 }
                 else{
                
                 request.setAttribute("edm", edm);
                  return mapping.findForward("delete");
                }
           }

           return mapping.findForward("failure");
    }
}
