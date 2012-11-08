/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.hbm.AcqBibliography;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.AcqBibliographyId;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.BudgetDAO;
import com.myapp.struts.AcquisitionDao.VendorDAO;
import com.myapp.struts.hbm.AcqBudgetAllocation;
import com.myapp.struts.hbm.AcqCurrency;
import com.myapp.struts.hbm.BaseCurrency;
import com.myapp.struts.hbm.AcqVendor;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author maqbool
 */
public class AcqBiblioNewEntryAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcqBibliography acqbibdtail=new AcqBibliography();
    AcqBibliographyId acqbibdtailid=new AcqBibliographyId();
    AcquisitionDao acqdao=new AcquisitionDao();
    VendorDAO vendao=new VendorDAO();
   BudgetDAO buddao=new BudgetDAO();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqBiblioActionForm acqbib=(AcqBiblioActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String title=acqbib.getTitle();
        String author=acqbib.getAuthor();
        
        if(StringUtils.isEmpty(author)){
        String authormsg="Author field can't be left blank";
        request.setAttribute("authormsg", authormsg);
        return mapping.findForward("fail");
        }
        String button=acqbib.getButton();
        String publisher_name=acqbib.getPublisher_name();
        String publishing_year=acqbib.getPublishing_year();
        String isbn=acqbib.getIsbn();
        String edition=acqbib.getEdition();
        String publishing_place=acqbib.getPublication_place();
        String sub_author=acqbib.getSub_author();
        String sub_author0=acqbib.getSub_author0();
        String sub_author1=acqbib.getSub_author1();
        String sub_author2=acqbib.getSub_author2();
        String no_of_volume=acqbib.getVolume_no();

        String document_type=acqbib.getDocument_type();

        if(button.equals("Submit")){
        int titId=acqdao.returnMaxBiblioId(library_id, sub_library_id);
        acqbibdtail.setAuthor(author);
        acqbibdtail.setDocType(document_type);
        acqbibdtail.setTitle(title);
        acqbibdtail.setPublisherName(publisher_name);
        acqbibdtail.setPublishingPlace(publishing_year);
        acqbibdtail.setPublishingYr(publishing_year);
        acqbibdtail.setEdition(edition);
        acqbibdtail.setIsbn(isbn);
        acqbibdtail.setVolume_no(no_of_volume);
        acqbibdtail.setSubAuthor(sub_author);
        acqbibdtail.setSubAuthor0(sub_author0);
        acqbibdtail.setSubAuthor1(sub_author1);
        acqbibdtail.setSubAuthor2(sub_author2);
        acqbibdtail.setLccNo(acqbib.getLcc_no());
        acqbibdtail.setPublishingPlace(publishing_place);
        acqbibdtailid.setLibraryId(library_id);
        acqbibdtailid.setSubLibraryId(sub_library_id);
        acqbibdtailid.setTitleId(titId);
        acqbibdtail.setId(acqbibdtailid);
        acqdao.insert(acqbibdtail);
        String msg="Record is inserted successfully with title id :"+titId;
        request.setAttribute("msg", msg);
        acqbib.setDocument_type("");
        acqbib.setTitle("");
        return mapping.findForward(SUCCESS);
        }
        if(button.equals("Initiate Acquisition")){
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

             BaseCurrency cur=buddao.getBaseCurrency(library_id);
           
             if(cur==null){
             String msg1="You need to set Library Base Currency";
             request.setAttribute("msg1", msg1);
             return mapping.findForward("fail");
             }
  session.setAttribute("base", cur.getFormalName());
         session.setAttribute("vendors", acqvendor);

          List<MixBudgetAllocation> acqbudget1=buddao.getMixBudgetAllocation(library_id);
          if(acqbudget1.isEmpty()){
          String msg1="You need to set Income budget head list";
             request.setAttribute("msg1", msg1);
             return mapping.findForward("fail");
          }
              session.setAttribute("budgetheads", acqbudget1);

             int titId=acqdao.returnMaxBiblioId(library_id, sub_library_id);
        acqbibdtail.setAuthor(author);
        acqbibdtail.setDocType(document_type);
        acqbibdtail.setTitle(title);
        acqbibdtail.setPublisherName(publisher_name);
        acqbibdtail.setPublishingPlace(publishing_year);
        acqbibdtail.setPublishingYr(publishing_year);
        acqbibdtail.setEdition(edition);
        acqbibdtail.setIsbn(isbn);
        acqbibdtail.setVolume_no(no_of_volume);
        acqbibdtail.setSubAuthor(sub_author);
        acqbibdtail.setSubAuthor0(sub_author0);
        acqbibdtail.setSubAuthor1(sub_author1);
        acqbibdtail.setSubAuthor2(sub_author2);
        acqbibdtail.setLccNo(acqbib.getLcc_no());
        acqbibdtail.setPublishingPlace(publishing_place);
        acqbibdtailid.setLibraryId(library_id);
        acqbibdtailid.setSubLibraryId(sub_library_id);
        acqbibdtailid.setTitleId(titId);
        acqbibdtail.setId(acqbibdtailid);
        acqdao.insert(acqbibdtail);
        session.setAttribute("titleid", titId);
        return mapping.findForward("intacq");
        }
        return mapping.findForward(SUCCESS);
    }
}
