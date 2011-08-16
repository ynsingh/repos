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
import com.myapp.struts.hbm.*;
import javax.servlet.http.HttpSession;
import com.myapp.struts.hbm.AcqBibliography;
import com.myapp.struts.hbm.AcqBibliographyId;
import com.myapp.struts.hbm.AcqBibliographyDetails;
import com.myapp.struts.hbm.AcqBibliographyDetailsId;
import java.util.List;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author maqbool
 */
public class AcqBiblioUpdateAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao ado= new AcquisitionDao();
    AcqBibliography acqbib= new AcqBibliography();
    AcqBibliographyId acqbibid=new AcqBibliographyId();
    AcqBibliographyDetails acqbibdtails= new AcqBibliographyDetails();
    AcqBibliographyDetailsId acqbibdtailsid=new AcqBibliographyDetailsId();
    private int title_id;
    
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
        AcqBiblioActionForm acqbibfrm= (AcqBiblioActionForm)form;
         HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String button= acqbibfrm.getButton();
        String title=acqbibfrm.getTitle();

        List l1=ado.searchDoc3(library_id, sub_library_id, title);
       // acqbib=(AcqBibliography) ado.searchDoc3(library_id, sub_library_id,title);
        
        String doc_type=acqbibfrm.getDocument_type();
       
        String author=acqbibfrm.getAuthor();    
        if(StringUtils.isEmpty(author)){
        String authormsg="Author field can't be left blank";
        request.setAttribute("msg1", authormsg);
        return mapping.findForward("fail");
        }
        String publisher_name=acqbibfrm.getPublisher_name();
        String publishing_year=acqbibfrm.getPublishing_year();
        String isbn=acqbibfrm.getIsbn();
        String edition=acqbibfrm.getEdition();
        String sub_author=acqbibfrm.getSub_author();
        String publication_place=acqbibfrm.getPublication_place();
        int title_id=acqbibfrm.getTitle_id();
        String lcc_no=acqbibfrm.getLcc_no();
        acqbibid.setTitleId(title_id);
        acqbibid.setSubLibraryId(sub_library_id);
        acqbibid.setLibraryId(library_id);
        acqbib.setId(acqbibid);
        acqbib.setVolume_no(acqbibfrm.getVolume_no());
        acqbib.setTitle(acqbibfrm.getTitle());
        acqbib.setDocType(acqbibfrm.getDocument_type());
        acqbib.setAuthor(acqbibfrm.getAuthor());
        acqbib.setPublisherName(acqbibfrm.getPublisher_name());
        acqbib.setPublishingYr(acqbibfrm.getPublishing_year());
        acqbib.setIsbn(acqbibfrm.getIsbn());
        acqbib.setEdition(acqbibfrm.getEdition());
        acqbib.setSubAuthor(acqbibfrm.getSub_author());
        acqbib.setSubAuthor0(acqbibfrm.getSub_author0());
        acqbib.setSubAuthor1(acqbibfrm.getSub_author1());
        acqbib.setSubAuthor2(acqbibfrm.getSub_author2());
        acqbib.setPublishingPlace(acqbibfrm.getPublication_place());
        acqbib.setLccNo(acqbibfrm.getLcc_no());
       if(button.equals("Update")){
           
           ado.update1(acqbib);
           String msg2="Record is updated Successfully";
           request.setAttribute("msg2",msg2);
           acqbibfrm.setTitle("");
           acqbibfrm.setDocument_type("");
 return mapping.findForward("update");

       }
        if(button.equals("Delete")){
          String id=(String)session.getAttribute("id");
          int title_id1=Integer.parseInt(id);
          AcqBibliographyDetails acqbibd=ado.getBiblioBytitleid(library_id, sub_library_id, title_id1);
          if(acqbibd!=null){
          String msg3="The title is in acquisition process can't delete data";
           request.setAttribute("titlenotfound",msg3);
           return mapping.findForward("fail");
          }
          ado.delete1(title_id, library_id, sub_library_id);
           String msg3="Record is deleted Successfully";
           request.setAttribute("msg3",msg3);
           acqbibfrm.setTitle("");
           acqbibfrm.setDocument_type("");
 return mapping.findForward("delete");

       }

         if(button.equals("View")){
          
          AcqBibliography acqbib=(AcqBibliography) ado.searchDoc3(library_id, sub_library_id, title);
           ado.searchDoc3(library_id, sub_library_id, title);
           acqbibfrm.setTitle("");
           acqbibfrm.setDocument_type("");
 return mapping.findForward("view");

       }
        
     return mapping.findForward("success");
    }
}
