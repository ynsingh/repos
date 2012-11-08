/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.struts.cataloguing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.cataloguingDAO.BibliographicEntryDAO;
import com.myapp.struts.hbm.*;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import java.util.Locale;
import java.util.ResourceBundle;
public class AccessionDeleteAction1 extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    BibliographicEntryDAO dao = new BibliographicEntryDAO();
    Locale locale=null;
    String locale1="en";
    String rtl="ltr";
    String align="left";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BibliographicDetailEntryActionForm1 bform = (BibliographicDetailEntryActionForm1) form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        System.out.println("I am here");
                      try{
        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
    }catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align="left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
    String oldacc=(String)bform.getAcc_no1();
        String acc_no1 = (String) bform.getAccession_no();
        String acc_no=acc_no1;
        int record_no = bform.getRecord_no();
        int biblio_id = bform.getBiblio_id();
      
        String button = (String) bform.getButton();
        if (button.equals("Delete"))
        {
            if (StringUtils.isEmpty(acc_no))
            {
                String msg1= resource.getString("cataloguing.ownaccessionentryaction.accessblank");//Accession no field can not be left blank
                request.setAttribute("msg2", msg1);
            } 
            else
            {
                    DocumentDetails doc=dao.searchAccession(library_id, sub_library_id, acc_no);
                    if(!doc.getStatus().equals("available"))
                    {
                         String msg1= resource.getString("cataloguing.ownaccessionentryaction.reserveditem");//This item is either checked out or reserved, can not update details
                         request.setAttribute("msg1", msg1);
                         return mapping.findForward(SUCCESS);
                    }
                    else
                    {
                        dao.deletedocItem(acc_no, library_id, sub_library_id);
                        dao.deleteaccItem(acc_no, library_id, sub_library_id);
                    }
                
                String msg2 = resource.getString("cataloguing.accessiondeleteaction.itemdelete");//Item detail is deleted successfully
                request.setAttribute("msg", msg2);
                return mapping.findForward(SUCCESS);
            }
        }
         if (button.equals("Update"))
         {
            if (StringUtils.isEmpty(acc_no))
            {
                 String msg1 = resource.getString("cataloguing.ownaccessionentryaction.accessblank");//Accession no field can not be left blank
                request.setAttribute("msg1", msg1);
            } 
            else
            {
                    
                    AccessionRegister hh = dao.searchaccupdate(record_no,library_id, sub_library_id, acc_no);
                    if (hh != null)
                    {
                        String msg1 = resource.getString("cataloguing.ownaccessionentryaction.accessduplicate");//This accession no already exists enter different
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward(SUCCESS);
                    } 
                    else
                    {

                    //get the details of AccessionNo
                    BibliographicEntryDAO bibdao=new BibliographicEntryDAO();
                    DocumentDetails doc1=bibdao.searchAccession(library_id, sub_library_id, oldacc);
                    if(doc1!=null && doc1.getStatus().equalsIgnoreCase("available"))
                    {

                        BibliographicDetails title=bibdao.getBiblio(library_id, sub_library_id,doc1.getBiblioId());
                        AccessionRegisterId acid=new AccessionRegisterId(record_no, library_id, sub_library_id);
                        AccessionRegister  accreg =new AccessionRegister();
                        accreg.setId(acid);
                        accreg.setAccessionNo(acc_no);
                        accreg.setDateAcquired(bform.getDate_acquired());
                        accreg.setBibliographicDetails(title);
                        accreg.setVolumeNo(bform.getVolume_no());
                        accreg.setLocation(bform.getLocation());
                        accreg.setShelvingLocation(bform.getShelving_location());
                        accreg.setIndexNo(bform.getIndex_no());
                        accreg.setBiblioId(doc1.getBiblioId());
                        accreg.setNoOfPages(bform.getNo_of_pages());
                        accreg.setColour(bform.getColour());

                        doc1.setVolumeNo(bform.getVolume_no());
                        doc1.setAccessionNo(acc_no);
                        doc1.setBiblioId(doc1.getBiblioId());
                        doc1.setLocation(bform.getLocation());
                        doc1.setShelvingLocation(bform.getShelving_location());
                        doc1.setIndexNo(bform.getIndex_no());
                        doc1.setNoOfPages(bform.getNo_of_pages());
                        doc1.setCollation1(bform.getCollation());
                        doc1.setBindType(bform.getBind_type());
                        doc1.setBibliographicDetails(title);
                        dao.updateAccession(accreg,doc1);
                        request.setAttribute("msg", "Record Updated Successfully");
                        return mapping.findForward(SUCCESS);
                    }
                    else if(!doc1.getStatus().equals("available"))
                    {
                        String msg1= resource.getString("cataloguing.ownaccessionentryaction.reserveditem");//This item is either checked out or reserved, can not update details
                        request.setAttribute("msg1", msg1);
                        return mapping.findForward(SUCCESS);
                    }
                    
                     
                }
           }
        }
        return mapping.findForward(SUCCESS);
    }
}
