/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.BudgetDAO;
import com.myapp.struts.AcquisitionDao.VendorDAO;
import com.myapp.struts.hbm.*;
import com.myapp.struts.opacDAO.NewDemandDAO;
import java.util.List;
/**
 *
 * @author maqbool
 */
public class AcqAcquisitionOfopacAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao dao=new AcquisitionDao();
    AcqBibliography acqbib=new AcqBibliography();
    Demandlist dmnd=new Demandlist();
    int demand_id;
    NewDemandDAO ndmndao=new NewDemandDAO();
    VendorDAO vendao=new VendorDAO();
    BudgetDAO bugdao=new BudgetDAO();
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqBiblioActionForm acqbiblio=(AcqBiblioActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        
      //  List<AcqVendor> acqvendor=VendorDAO.searchDoc5(library_id, sub_library_id);
       // System.out.println("VVVVVVVVVVVVVVVVVV"+acqvendor.size());
       String id = request.getParameter("id");
        demand_id = Integer.parseInt(id);
        dmnd=dao.BibliobyMemId(demand_id, library_id, sub_library_id);
          
         
        System.out.println("DDDDDDDDDDDDDDDDDDD"+dmnd);
       acqbiblio.setDocument_type(dmnd.getCategory());
      acqbiblio.setTitle(dmnd.getId().getTitle());
        acqbiblio.setAuthor(dmnd.getAuthor());
        acqbiblio.setSub_author(dmnd.getSubAuthor());
        //acqbiblio.setSub_author0(acqbib.getSubAuthor0());
        //acqbiblio.setSub_author1(acqbib.getSubAuthor1());
        //acqbiblio.setSub_author2(acqbib.getSubAuthor2());
        acqbiblio.setPublisher_name(dmnd.getPublisher());
       acqbiblio.setPublication_place(dmnd.getPublicationPlace());
        acqbiblio.setPublishing_year(dmnd.getPublishYr());
        acqbiblio.setLcc_no(dmnd.getLccNo());
        acqbiblio.setIsbn(dmnd.getIsbn());
        acqbiblio.setEdition(dmnd.getEdition());
        acqbiblio.setDemand_id(demand_id);

           List<AcqVendor> acqvendor=vendao.searchDoc5(library_id, sub_library_id);
             if(acqvendor.isEmpty()){
             String msg1="You need to set vendors list";
             request.setAttribute("msg1", msg1);
             return mapping.findForward("fail");
             }

 List<String> exchangerate=vendao.getCurrencyList(library_id);


            // if(exchangerate.isEmpty()){
             //String msg1="You need to set vendors list";
             //request.setAttribute("msg1", msg1);
             //return mapping.findForward("fail");
            // }
            session.setAttribute("exchangerate", exchangerate);

             BaseCurrency cur=bugdao.getBaseCurrency(library_id);
           if(cur==null){
             String msg1="You need to set Library Base Currency";
             request.setAttribute("msg1", msg1);
             return mapping.findForward("fail");
             }
   session.setAttribute("base", cur.getFormalName());
         session.setAttribute("vendors", acqvendor);

          List<MixBudgetAllocation> acqbudget1=bugdao.getMixBudgetAllocation(library_id);
          if(acqbudget1.isEmpty()){
          String msg1="You need to set Income budget head list";
             request.setAttribute("msg1", msg1);
             return mapping.findForward("fail");
          }
              session.setAttribute("budgetheads", acqbudget1);



session.setAttribute("demand_id", demand_id);

    
          
        return mapping.findForward(SUCCESS);
    }
}
