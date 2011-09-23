/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Biblio;
import java.util.HashMap;
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
public class BiblioStrutsAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

     private MarcHibDAO marchib=new MarcHibDAO();
     HashMap hm = new HashMap();
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
            throws Exception
    {
       BiblioActionForm eaf=(BiblioActionForm)form;
       int bibid=0;
       HttpSession session = request.getSession();
       
      String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");

        String isbn,title,btn;
        btn=eaf.getBtn();
        isbn=eaf.getIsbn();
        title=eaf.getTitle();
      System.out.println("inside biblio action");
      session.setAttribute("marcbutton", btn);

      //---------------------------------------------------------------------------------------

      if(btn.equals("New"))
      { 
            bibid = marchib.returnMaxBiblioId(library_id, sub_library_id);
           session.setAttribute("biblio_id", bibid);
                request.setAttribute("isbn", isbn);
                session.setAttribute("title",title);
                session.setAttribute("hsmp", hm);
                 return mapping.findForward(SUCCESS);
            
        }

      //--------------------------------------------------------------------------------------

      if(btn.equals("Update")){

          int ismarcdataexist = marchib.isMarcDataExist(title);

         if(ismarcdataexist != 0){
          
         List<Biblio> bib1=marchib.getdataforupdate(title);
         System.out.println("size of bib1= "+bib1.get(1).get$a());
         session.setAttribute("MARCList", bib1);
         session.setAttribute("title",title);
         session.setAttribute("hsmp", hm);
          return mapping.findForward("update");
      }
           else
         {
          request.setAttribute("msg", "*Error : The Data do not exist!");
          return mapping.findForward("failure");
          }
        }

      //--------------------------------------------------------------------------------------

      if(btn.equals("View")){
         int ismarcdataexist = marchib.isMarcDataExist(title);

         if(ismarcdataexist != 0){
                List<Biblio> bib1=marchib.getdataforupdate(title);
                 System.out.println("size of bib1= "+bib1.get(1).get$a());
                 session.setAttribute("MARCList", bib1);
                 session.setAttribute("title",title);
                  return mapping.findForward("view");
          }
         else
         {
          request.setAttribute("msg", "*Error : The Data do not exist!");
          return mapping.findForward("failure");
          }
        }

        //--------------------------------------------------------------------------------------

      if(btn.equals("Delete")){


         int ismarcdataexist = marchib.isMarcDataExist(title);
         if(ismarcdataexist != 0){
             List<Biblio> bib1=marchib.getdataforupdate(title);

             System.out.println("size of bib1= "+bib1.get(1).get$a());
         session.setAttribute("MARCList", bib1);
         session.setAttribute("title",title);
          return mapping.findForward("view");
      }
         else{
          request.setAttribute("msg", "*Error : The Data do not exist!");
          return mapping.findForward("failure");
          }
        }


    request.setAttribute("msg", "* ERROR_ INVALID ACTION!");
    return mapping.findForward("failure");

       
    }
}
