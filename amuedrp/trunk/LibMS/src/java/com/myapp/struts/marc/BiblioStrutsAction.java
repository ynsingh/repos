/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import java.util.ArrayList;
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
    com.myapp.struts.cataloguingDAO.MarcHibDAO dao=new com.myapp.struts.cataloguingDAO.MarcHibDAO();
    BibliographicDetails bib=new BibliographicDetails();
    BibliographicDetailsId biblioid=new BibliographicDetailsId();
    List<BibliographicDetails> ls=new ArrayList<BibliographicDetails>();
    
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


        HashMap t=(HashMap)session.getAttribute("hsmp");
        if(t!=null  && t.isEmpty()==false)
        {System.out.println("Get Value"+t.size());

          t.clear();
          System.out.println("Get Value"+t.size());
          session.setAttribute("hsmp",t);
         }
            
        
        btn=eaf.getBtn();
        isbn=eaf.getIsbn();
        title=eaf.getTitle();
        
//       if(isbn.isEmpty())
//       {
//       request.setAttribute("msg", "Please Enter ISBN Value");
//        return mapping.findForward("failure");
//       }
        if(title.isEmpty())
       {
       request.setAttribute("msg", "Please Enter Title Value");
        return mapping.findForward("failure");
       }
       
      System.out.println("inside biblio action");
      session.setAttribute("marcbutton", btn);

    

  
      if(btn.equals("New"))
      {
          //session.setAttribute("hsmp",hm);
          //session.removeAttribute("hsmp");
        
        // else
         // session.removeAttribute("hsmp");
          
       ls.removeAll(ls);
          int ismarcdataexist = marchib.isMarcDataExist(title,library_id,sub_library_id);
List<Biblio> rst1=new ArrayList<Biblio>();

System.out.println(ismarcdataexist);

         if(ismarcdataexist != 0){
        List<Integer> rst = dao.searchDoc2(library_id, sub_library_id);
        int bib_id=0;
        for(int g=0;g<rst.size();g++){
         bib_id=rst.get(g);
        
       System.out.println("BBBBBiiiiibbbbbbbb"+bib_id);
         rst1 = dao.searchBiblioId(library_id, sub_library_id,bib_id);
        for(int j=0;j<rst1.size();j++){
        System.out.println("BBBBBiiiiibbbbbbbb"+bib_id);
            if(rst1.get(j).getId().getMarctag().equals("245"))
        {
        bib.setTitle(rst1.get(j).get$a());
        }
        if(rst1.get(j).getId().getMarctag().equals("100"))
        {
        bib.setMainEntry(rst1.get(j).get$a());
        }

        }
           
        biblioid.setBiblioId(bib_id);
        biblioid.setLibraryId(library_id);
        biblioid.setSublibraryId(sub_library_id);
        bib.setId(biblioid);
        ls.add(bib);
      biblioid=new BibliographicDetailsId();
        bib=new BibliographicDetails();
        }
System.out.println("daaaaaaaaaaaaaaasas"+ls.get(0).getId().getBiblioId()+"sdsdsd"+ls.get(1).getId().getBiblioId());
        session.setAttribute("editlist", rst1);
        session.setAttribute("opacList", ls);
         session.setAttribute("title",title);         
         session.setAttribute("hsmp", hm);

 return mapping.findForward("duplicate");
         }
           else
         {
               bibid = marchib.returnMaxBiblioId(library_id, sub_library_id);
           session.setAttribute("biblio_id", bibid);
              //  request.setAttribute("isbn", isbn);
                session.setAttribute("title",title);
                session.setAttribute("hsmp", hm);
                       return mapping.findForward(SUCCESS);
          }                 
        }

      

      if(btn.equals("Update")){
ls.removeAll(ls);
          int ismarcdataexist = marchib.isMarcDataExist(title,library_id, sub_library_id);
List<Biblio> rst1=new ArrayList<Biblio>();

System.out.println(ismarcdataexist);

          if(ismarcdataexist != 0){
        List<Integer> rst = dao.searchDoc2(library_id, sub_library_id);
        int bib_id=0;
        for(int g=0;g<rst.size();g++){
         bib_id=rst.get(g);

       System.out.println("BBBBBiiiiibbbbbbbb"+bib_id);
         rst1 = dao.searchBiblioId(library_id, sub_library_id,bib_id);
        for(int j=0;j<rst1.size();j++){
        System.out.println("BBBBBiiiiibbbbbbbb"+bib_id);
            if(rst1.get(j).getId().getMarctag().equals("245"))
        {
        bib.setTitle(rst1.get(j).get$a());
        }
        if(rst1.get(j).getId().getMarctag().equals("100"))
        {
        bib.setMainEntry(rst1.get(j).get$a());
        }

        }

        biblioid.setBiblioId(bib_id);
        biblioid.setLibraryId(library_id);
        biblioid.setSublibraryId(sub_library_id);
        bib.setId(biblioid);
        ls.add(bib);
      biblioid=new BibliographicDetailsId();
        bib=new BibliographicDetails();
        }
        session.setAttribute("editlist", rst1);
        session.setAttribute("opacList", ls);
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

      if(btn.equals("View") ){
        
    ls.removeAll(ls);
          int ismarcdataexist = marchib.isMarcDataExist(title,library_id,sub_library_id);
List<Biblio> rst1=new ArrayList<Biblio>();
         if(ismarcdataexist != 0){
       List<Integer> rst = dao.searchDoc2(library_id, sub_library_id);
        int bib_id=0;
        for(int g=0;g<rst.size();g++){
         bib_id=rst.get(g);

       System.out.println("BBBBBiiiiibbbbbbbb"+bib_id);
         rst1 = dao.searchBiblioId(library_id, sub_library_id,bib_id);
        for(int j=0;j<rst1.size();j++){
        System.out.println("BBBBBiiiiibbbbbbbb"+bib_id);
            if(rst1.get(j).getId().getMarctag().equals("245"))
        {
        bib.setTitle(rst1.get(j).get$a());
        }
        if(rst1.get(j).getId().getMarctag().equals("100"))
        {
        bib.setMainEntry(rst1.get(j).get$a());
        }

        }

        biblioid.setBiblioId(bib_id);
        biblioid.setLibraryId(library_id);
        biblioid.setSublibraryId(sub_library_id);
        bib.setId(biblioid);
        ls.add(bib);
      biblioid=new BibliographicDetailsId();
        bib=new BibliographicDetails();
        }
        session.setAttribute("editlist", rst1);
        session.setAttribute("opacList", ls);
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

      if( btn.equalsIgnoreCase("Delete")){
        
    ls.removeAll(ls);
          int ismarcdataexist = marchib.isMarcDataExist(title,library_id,sub_library_id);
List<Biblio> rst1=new ArrayList<Biblio>();
          if(ismarcdataexist != 0){
        List<Integer> rst = dao.searchDoc2(library_id, sub_library_id);
        int bib_id=0;
        for(int g=0;g<rst.size();g++){
         bib_id=rst.get(g);

       System.out.println("BBBBBiiiiibbbbbbbb"+bib_id);
         rst1 = dao.searchBiblioId(library_id, sub_library_id,bib_id);
        for(int j=0;j<rst1.size();j++){
        System.out.println("BBBBBiiiiibbbbbbbb"+bib_id);
            if(rst1.get(j).getId().getMarctag().equals("245"))
        {
        bib.setTitle(rst1.get(j).get$a());
        }
        if(rst1.get(j).getId().getMarctag().equals("100"))
        {
        bib.setMainEntry(rst1.get(j).get$a());
        }

        }

        biblioid.setBiblioId(bib_id);
        biblioid.setLibraryId(library_id);
        biblioid.setSublibraryId(sub_library_id);
        bib.setId(biblioid);
        ls.add(bib);
      biblioid=new BibliographicDetailsId();
        bib=new BibliographicDetails();
        }
        session.setAttribute("editlist", rst1);
        session.setAttribute("opacList", ls);
         session.setAttribute("title",title);
         
         session.setAttribute("hsmp", hm);
          return mapping.findForward("update1");
      }
           else
         {
          request.setAttribute("msg", "*Error : The Data do not exist!");
          return mapping.findForward("failure");
          }
      }
      return null;
    }
}
