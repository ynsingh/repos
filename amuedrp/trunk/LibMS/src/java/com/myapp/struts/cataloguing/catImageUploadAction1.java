

package com.myapp.struts.cataloguing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import com.myapp.struts.cataloguingDAO.*;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsLang;
import com.myapp.struts.utility.UserLog;


public class catImageUploadAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        String library_id=(String)session.getAttribute("library_id");
        String sublibrary_id=(String)session.getAttribute("sublibrary_id");

     System.out.println("Image Update code");
        catImageUploadActionForm form1 = (catImageUploadActionForm)form;
        byte[] img;
        FormFile filename = form1.getImg();
        img = form1.getImg().getFileData();
        String comment=form1.getComment();
        int bibid=Integer.parseInt((String)session.getAttribute("id"));
        System.out.println(bibid+"$$$$$$$$$$$$$$$$$$$$$$");
        session.removeAttribute("id");
      
        if (img!=null)
        {
            System.out.println("ImageUploadAction:"+img.length);
        session.setAttribute("image1", img);
        session.setAttribute("filename1", filename);
        request.setAttribute("imagechange1", 1);

          FormFile v=(FormFile)session.getAttribute("filename1");
       byte[] iii=null;
       if(v!=null)iii=v.getFileData();

BibliopgraphicEntryDAO bibdao=new BibliopgraphicEntryDAO();
       BibliographicDetails bib=bibdao.getBiblio(library_id, sublibrary_id, bibid);

String ext=null;
 ext=UserLog.returnextension(v.getFileName());


ext=ext.toLowerCase();


if (form1.getImg()!=null)
         {
                
                 
                  UserLog.writeImage1(bib.getId().getLibraryId()+bib.getId().getSublibraryId()+bib.getId().getBiblioId()+"dgi"+"."+ext, iii);
              
             }
if(bib.getEntryLanguage()!=null  && bib.getEntryLanguage().isEmpty()==false)
{
    BibliographicDetailsLang bib1=bibdao.getBiblioLang(library_id, sublibrary_id, bibid);
    bib1.setDigitalData(bib.getId().getLibraryId()+bib.getId().getSublibraryId()+bib.getId().getBiblioId()+"dgi"+"."+ext);
    bib1.setDigitalComment(comment);
    bibdao.updateBiblioLang(bib1);
    request.setAttribute("msg", "Book Image uploaded Successfully for bibio Id="+bib1.getId().getBiblioId());
}
else
{   bib.setDigitalData(bib.getId().getLibraryId()+bib.getId().getSublibraryId()+bib.getId().getBiblioId()+"dgi"+"."+ext);
    bib.setDigitalComment(comment);
    bibdao.update(bib);
    request.setAttribute("msg", "Book Image uploaded Successfully for bibio Id="+bib.getId().getBiblioId());
}


        return mapping.findForward(SUCCESS);
    }
        else{
            return mapping.findForward("failure");
        }
    }
}
