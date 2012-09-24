package com.myapp.struts.marc;

import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BiblioId;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class CatControlAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
   
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
    String control_no=null;
    MarcHibDAO marchib=new MarcHibDAO();
    MarcHibDAO dao=new MarcHibDAO();
     Biblio biblio=new Biblio();
     BiblioId biblioid= new BiblioId();
    Biblio biblio1=new Biblio();
    BiblioId biblioid1= new BiblioId();
     Biblio biblio2=new Biblio();
     BiblioId biblioid2= new BiblioId();
     Biblio biblio3=new Biblio();
     BiblioId biblioid3= new BiblioId();
     Biblio biblio4=new Biblio();
     BiblioId biblioid4= new BiblioId();
     Biblio biblio5=new Biblio();
     BiblioId biblioid5= new BiblioId();
        HttpSession session = request.getSession();
        CatControlActionForm caf8=(CatControlActionForm)form;
        HashMap hm1;
        String x=request.getParameter("st");
       if(!StringUtils.isBlank(caf8.getControl_no()))
            control_no=caf8.getControl_no();
       String t=caf8.getZclick();             // t is click value on jsp

         if(x!=null)
            session.setAttribute("st", x);


       int bibid = (Integer)session.getAttribute("biblio_id");
       String library_id = (String) session.getAttribute("library_id");
       String sub_library_id = (String) session.getAttribute("sublibrary_id");
       System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC "+caf8.getControl_no());

       //filling object with data for MARC Leader
                biblioid.setLibraryId(library_id);
                biblio.setSublibraryId(sub_library_id);
                biblioid.setMarctag("Leader");
                if(caf8.getLeader()!=null && !caf8.getLeader().equals(""))
                biblio.set$a(caf8.getLeader());
                biblioid.setBibId(bibid);
                biblio.setId(biblioid);
                hm1=(HashMap)session.getAttribute("hsmp");
              
                if(hm1==null)
                     hm1=new HashMap();
                
                   
             //   if(hm1.containsKey("Leader"))
              //  {
              //      hm1.remove("Leader");
              //  }
                hm1.put("Leader", biblio);

  //filling object with data for MARC Control No
                biblioid1.setLibraryId(library_id);
                biblio1.setSublibraryId(sub_library_id);
                biblioid1.setMarctag("001");
                if(control_no!=null && !control_no.equals(""))
                biblio1.set$a(control_no);
                biblioid1.setBibId(bibid);
                biblio1.setId(biblioid1);
               // hm1=(HashMap)session.getAttribute("hsmp");
             //   if(hm1.containsKey("001"))
             //   {
             //       hm1.remove("001");
             //   }
                hm1.put("001", biblio1);

   //filling object with data for MARC Control No id
                biblioid2.setLibraryId(library_id);
                biblio2.setSublibraryId(sub_library_id);
                biblioid2.setMarctag("003");
                if(caf8.getControl_no()!=null && !caf8.getControl_no().equals(""))
                biblio2.set$a(caf8.getControl_no_id());
                biblioid2.setBibId(bibid);
                biblio2.setId(biblioid2);
               // hm1=(HashMap)session.getAttribute("hsmp");
            //   if(hm1.containsKey("003"))
             //   {
             //       hm1.remove("003");
             //   }
                hm1.put("003", biblio2);

   //filling object with data for MARC Date and time of latest transaction
                biblioid3.setLibraryId(library_id);
                biblio3.setSublibraryId(sub_library_id);
                biblioid3.setMarctag("005");
                if(caf8.getD_t_l_t()!=null && !caf8.getD_t_l_t().equals(""))
                biblio3.set$a(caf8.getD_t_l_t());
                biblioid3.setBibId(bibid);
                biblio3.setId(biblioid3);
               // hm1=(HashMap)session.getAttribute("hsmp");
             //   if(hm1.containsKey("005"))
             //   {
             //       hm1.remove("005");
             //   }
                hm1.put("005", biblio3);

   //filling object with data for MARC Physical Desc
                biblioid4.setLibraryId(library_id);
                biblio4.setSublibraryId(sub_library_id);
                biblioid4.setMarctag("007");
                if(caf8.getPhy_desc()!=null && !caf8.getPhy_desc().equals(""))
                biblio4.set$a(caf8.getPhy_desc());
                biblioid4.setBibId(bibid);
                biblio4.setId(biblioid4);
              //  hm1=(HashMap)session.getAttribute("hsmp");
            //   if(hm1.containsKey("007"))
            //    {
            //        hm1.remove("007");
            //    }
                hm1.put("007", biblio4);

    //filling object with data for MARC Fixed length
                biblioid5.setLibraryId(library_id);
                biblio5.setSublibraryId(sub_library_id);
                biblioid5.setMarctag("008");
                if(caf8.getFix_data()!=null && !caf8.getFix_data().equals(""))
                    biblio5.set$a(caf8.getFix_data());
                biblioid5.setBibId(bibid);
                biblio5.setId(biblioid5);
              //  hm1=(HashMap)session.getAttribute("hsmp");
             //   if(hm1.containsKey("008"))
             //   {
             //       hm1.remove("008");
             //   }
                hm1.put("008", biblio5);

                System.out.println("All five objects saved now NAvigating to page "+t+hm1);
                          
session.setAttribute("hsmp", hm1);
           
 //code for mapping forwards......
         if(t.equals("0"))
        {
            return mapping.findForward("forward0");
        }
        else if(t.equals("1")){
                return mapping.findForward("forward1");
        }
       else if(t.equals("2")){
                return mapping.findForward("forward2");
        }
        else if(t.equals("3")){
                return mapping.findForward("forward3");
        }
        else if(t.equals("4")){

                return mapping.findForward("forward4");
        }

       else if(t.equals("5")){
                return mapping.findForward("forward5");
        }
        else if(t.equals("6")){
                return mapping.findForward("forward6");
        }
        else if(t.equals("7")){
                return mapping.findForward("forward7");
        }

        else if(t.equals("9"))
        {
                return mapping.findForward("forward9");
        }
        else if(t.equals("10"))
        {
        return mapping.findForward("forward10");
        }
        return mapping.findForward("forward10");
}
}
