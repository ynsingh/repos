/* MAIN ACTION TO ACCEPT TITLE FOR MARC-21 INTERFACE BIBLIOGRAPHIC DETAIL ENTRY */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BibliographicDetails;
import com.myapp.struts.hbm.BibliographicDetailsId;
import com.myapp.struts.utility.LoggerUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BiblioStrutsAction extends org.apache.struts.action.Action
{
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
       
    MarcHibDAO marchib=new MarcHibDAO();
    HashMap hm = new HashMap();
    com.myapp.struts.cataloguingDAO.MarcHibDAO dao=new com.myapp.struts.cataloguingDAO.MarcHibDAO();
    BibliographicDetails bib=new BibliographicDetails();
    BibliographicDetailsId biblioid=new BibliographicDetailsId();
    List<BibliographicDetails> ls=new ArrayList<BibliographicDetails>();
    //Logger log4j =LoggerUtils.getLogger();


        HttpSession session = request.getSession();
        session.removeAttribute("controltag");
        session.removeAttribute("tag0");
        session.removeAttribute("tag1");
        session.removeAttribute("tag2");
        session.removeAttribute("tag3");
        session.removeAttribute("tag4");
        session.removeAttribute("tag5");
        session.removeAttribute("tag6");
        session.removeAttribute("tag7");
        session.removeAttribute("tag8");
        session.removeAttribute("controltag");
        session.removeAttribute("st");
        session.removeAttribute("data");
        session.removeAttribute("editlist");
        session.removeAttribute("hsmp");
        
         request.removeAttribute("CatControlActionForm");
          request.removeAttribute("CatActionForm1");
           request.removeAttribute("CatActionForm2");
            request.removeAttribute("CatActionForm2");
             request.removeAttribute("CatActionForm3");
              request.removeAttribute("CatActionForm4");
               request.removeAttribute("CatActionForm5");
request.removeAttribute("CatActionForm6");
request.removeAttribute("CatActionForm7");
request.removeAttribute("CatActionForm8");

        try
        {

        BiblioActionForm eaf=(BiblioActionForm)form;
      
        int bibid=0;





        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String isbn,title,btn;
       // HashMap t=(HashMap)session.getAttribute("hsmp");




//        if(t!=null  && t.isEmpty()==false)
//        {
//          t.clear();
//          session.setAttribute("hsmp",t);
//         }
       btn=eaf.getBtn();
       isbn=eaf.getIsbn();
       title=eaf.getTitle();
       System.out.println("Title=............."+title);

       if(title.isEmpty())
       {
           request.setAttribute("msg", "Please Enter Title Value");
           return mapping.findForward("failure");
       }
      session.setAttribute("marcbutton", btn);
      if(btn.equals("New"))
      {
        ls=new ArrayList<BibliographicDetails>();
        int ismarcdataexist = marchib.isMarcDataExist(title,library_id,sub_library_id);
        List<Biblio> rst1=new ArrayList<Biblio>();
        if(ismarcdataexist != 0)
        {
        List<Integer> rst = dao.searchDoc3(library_id, sub_library_id,title);
        int bib_id=0;
        for(int g=0;g<rst.size();g++)
        {
        bib_id=rst.get(g);
        rst1 = dao.searchBiblioId(library_id, sub_library_id,bib_id);
        if(rst1!=null){
        for(int j=0;j<rst1.size();j++){
        if(rst1.get(j).getId().getMarctag().equals("245"))
        {
        bib.setTitle(rst1.get(j).get$a());
        }
        if(rst1.get(j).getId().getMarctag().equals("100"))
        {
        bib.setMainEntry(rst1.get(j).get$a());
        }
        if(rst1.get(j).getId().getMarctag().equals("082"))
        {
        bib.setCallNo(rst1.get(j).get$a());
      
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
        }
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
               session.setAttribute("title",title);
               session.setAttribute("hsmp", hm);
               return mapping.findForward("success");
        }                 
        }
      if(btn.equals("Update"))
      {
        ls.removeAll(ls);
        int ismarcdataexist = marchib.isMarcDataExist(title,library_id, sub_library_id);
        List<Biblio> rst1=new ArrayList<Biblio>();
        if(ismarcdataexist != 0)
        {
        List<Integer> rst = dao.searchDoc3(library_id, sub_library_id,title);
        int bib_id=0;
        for(int g=0;g<rst.size();g++){
         bib_id=rst.get(g);
         rst1 = dao.searchBiblioId(library_id, sub_library_id,bib_id);
        for(int j=0;j<rst1.size();j++)
        {
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

      if(btn.equals("View"))
      {
            ls.removeAll(ls);
            int ismarcdataexist = marchib.isMarcDataExist(title,library_id,sub_library_id);
            List<Biblio> rst1=new ArrayList<Biblio>();
            if(ismarcdataexist != 0)
            {
                List<Integer> rst = dao.searchDoc3(library_id, sub_library_id,title);
                int bib_id=0;
                for(int g=0;g<rst.size();g++)
                {
                    bib_id=rst.get(g);
                    rst1 = dao.searchBiblioId(library_id, sub_library_id,bib_id);
                    for(int j=0;j<rst1.size();j++)
                    {
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

      if( btn.equalsIgnoreCase("Delete"))
      {
            ls.removeAll(ls);
            int ismarcdataexist = marchib.isMarcDataExist(title,library_id,sub_library_id);
            List<Biblio> rst1=new ArrayList<Biblio>();
            if(ismarcdataexist != 0)
            {
                   List<Integer> rst = dao.searchDoc3(library_id, sub_library_id,title);
                    int bib_id=0;
                    for(int g=0;g<rst.size();g++)
                    {
                        bib_id=rst.get(g);
                        rst1 = dao.searchBiblioId(library_id, sub_library_id,bib_id);
                        for(int j=0;j<rst1.size();j++)
                        {
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
    }catch(Exception e)
    {
        request.setAttribute("msg", "Insertion has error encourted"+e.getMessage());
        e.printStackTrace();
       // log4j.error(e.toString());
    }
    return mapping.findForward("failure");
    }
}
