/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.cataloguing;

import com.myapp.struts.cataloguingDAO.BibliopgraphicEntryDAO;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import com.myapp.struts.hbm.DocumentDetails;
import com.myapp.struts.hbm.DocumentDetailsId;
import com.myapp.struts.hbm.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Transaction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Client
 */
public class NewEntryBiblioAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    BibliographicDetailsId bibid=new BibliographicDetailsId();
    BibliographicDetails bib=new BibliographicDetails();
      BibliographicDetails bib2=new BibliographicDetails();
    BibliographicDetails bib1=new BibliographicDetails();
    BibliopgraphicEntryDAO dao=new BibliopgraphicEntryDAO();
    DocumentDetails dd=new DocumentDetails();
    DocumentDetailsId ddid=new DocumentDetailsId();
    String maxbiblioIdStr;
    int maxbiblioIdInt;
    int  documentId ;
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
    
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
               BibliographicDetailEntryActionForm bibform=(BibliographicDetailEntryActionForm)form;
        String button=bibform.getButton();
        String call_no=bibform.getCall_no();
        HttpSession session=request.getSession();
        String library_id=(String)session.getAttribute("library_id");
        Session session1=HibernateUtil.getSessionFactory().getCurrentSession();
         Transaction tx=null;         
         if(button.equals("Submit")){
        
        bib2=dao.searchcall(call_no, library_id);
        if(bib2!=null){
       String msg3="You are trying to enter duplicate call no enter different";
       request.setAttribute("msg1",msg3);
        return mapping.findForward("fail");
        }
        else{
         try{
System.out.println("NewEntryBiblioAction:++++++++"+library_id);
            tx=session1.beginTransaction();
            Query query = session1.createQuery("Select max(id.biblioId) FROM BibliographicDetails  WHERE id.libraryId = :libraryId ");
       query.setString("libraryId", library_id);
       System.out.println("NewEntryBiblioAction:++++++++"+library_id);
    /* bib=(BibliographicDetails)query.uniqueResult();
       if(bib!=null){
        maxbiblioIdInt=bib.getId().getBiblioId();
          maxbiblioIdInt++;

       }
       else maxbiblioIdInt=1;*/
             
     List  result = query.list();
       if (result.get(0)!=null)
            maxbiblioIdInt = (Integer)result.get(0) + 1;
       else maxbiblioIdInt = 1;
            System.out.println("NewEntryBiblioAction:++++++++"+maxbiblioIdInt);
Query query1 = session1.createQuery("Select max(id.documentId) FROM DocumentDetails  WHERE id.libraryId = :libraryId ");
       query1.setString("libraryId", library_id);
     List  result1 = query.list();
       if (result1.get(0)!=null)
            documentId = (Integer)result1.get(0) + 1;
       else documentId = 1;
       /*  Criteria criteria1 = session1.createCriteria(DocumentDetails.class,library_id)
                 .setProjection(Projections.max("id.documentId"));
            Integer documentId= (Integer)criteria1.uniqueResult();
            System.out.println("NewEntryBiblioAction:++++++++"+maxbiblioIdInt);
          if(documentId==null)
            documentId=1;
          else
              documentId++;*/

        ddid.setLibraryId(library_id);     
        ddid.setDocumentId(documentId);
        dd.setId(ddid);
        dd.setIsbn10(bibform.getIsbn10());
        dd.setIsbn13(bibform.getIsbn13());
        dd.setBiblioId(maxbiblioIdInt);
        dd.setCallNo(bibform.getCall_no());
        dd.setAuthorMain(bibform.getAuthor_main());
        dd.setAuthorSub(bibform.getAuthor_sub());
        dd.setDocumentType(bibform.getDocument_type());
        dd.setEdition(bibform.getEdition());
        dd.setIndexNo(bibform.getIndex_no());
        dd.setNoOfCopy(bibform.getNo_of_copies());
        dd.setNoOfPages(bibform.getNo_of_pages());
        dd.setPhysicalWidth(bibform.getPhysical_width());
        dd.setPublicationPlace(bibform.getPublication_place());
        dd.setPublisherName(bibform.getPublisher_name());
        dd.setPublishingYear(bibform.getPublishing_year());
        dd.setSubtitle(bibform.getSubtitle());
        dd.setTitle(bibform.getTitle());
     
        bibid.setBiblioId(maxbiblioIdInt);
        bibid.setLibraryId(library_id);
        bibid.setCallNo(bibform.getCall_no());
        bibid.setIsbn10(bibform.getIsbn10());
        bib.setControlNo(bibform.getControl_no());
        String isbn10=bibform.getIsbn10();
        String isbn13=bibform.getIsbn13();
        bib.setIsbn13(bibform.getIsbn13());
        bib.setId(bibid);
  
        bib.setAccessionType(bibform.getAccession_type());
        bib.setNoOfCopy(bibform.getNo_of_copies());
        bib.setDocumentType(bibform.getDocument_type());
        bib.setTitle(bibform.getTitle());
        bib.setSubtitle(bibform.getSubtitle());
        bib.setAuthorMain(bibform.getAuthor_main());
        bib.setAuthorSub(bibform.getAuthor_sub());
        bib.setPublisherName(bibform.getPublisher_name());
        bib.setPublicationPlace(bibform.getPublication_place());
        bib.setPublishingYear(bibform.getPublishing_year());
        bib.setPrice(bibform.getPrice());
        bib.setEdition(bibform.getEdition());
        bib.setIndexNo(bibform.getIndex_no());
        bib.setNoOfPages(bibform.getNo_of_pages());
        bib.setPhysicalWidth(bibform.getPhysical_width());
        bib.setBindType(bibform.getBind_type());      
        //bib1=dao.view(isbn10, isbn13, library_id);
       session1.save(bib);
        session1.save(dd);
         tx.commit();
        String msg2="Data is saved successfully with biblio Id:"+maxbiblioIdInt;
        request.setAttribute("msg2",msg2);
        return mapping.findForward(SUCCESS);
        }
      
              catch(ConstraintViolationException xe)
       {
       tx.rollback();
       System.out.println("zzzzzzz"+xe);
       String msg1="Some exception occurred";
       request.setAttribute("msg1",msg1);
       return mapping.findForward("failure");
       }
       catch(RuntimeException ee){
       tx.rollback();
       System.out.println("NewENtryBiblioAction**********************************"+ee);
       String msg3="Some exception occurred while entering data";
       request.setAttribute("msg1",msg3);
       return mapping.findForward("failure");
       }

    }}
    session1.close();
       return mapping.findForward(SUCCESS);
    }
}
