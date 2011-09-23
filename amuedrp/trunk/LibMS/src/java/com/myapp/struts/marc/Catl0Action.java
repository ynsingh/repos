/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.marc.MarcHibDAO;
import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BiblioId;
import java.util.HashMap;
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
public class Catl0Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private MarcHibDAO marchib=new MarcHibDAO();
    
    MarcHibDAO dao=new MarcHibDAO();
HashMap hm1 = new HashMap();
    private Biblio biblio=new Biblio();
    private BiblioId biblioid= new BiblioId();

    private Biblio biblio1=new Biblio();
    private BiblioId biblioid1= new BiblioId();

    private Biblio biblio2=new Biblio();
    private BiblioId biblioid2= new BiblioId();

    private Biblio biblio3=new Biblio();
    private BiblioId biblioid3= new BiblioId();

    private Biblio biblio4=new Biblio();
    private BiblioId biblioid4= new BiblioId();

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
        System.out.println("inside catl0action");
        BiblioActionForm caf=(BiblioActionForm)form;

        HttpSession session = request.getSession();
      int  bibid = (Integer)session.getAttribute("biblio_id");
        System.out.println("************************************************  "+bibid);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");


        String t=caf.getZclick();                       // t is the click value on jsp

      
        Character in0201,in0221,in0411,in0431,in0821,in0202,in0222,in0412,in0432,in0822;
      String z020,z020c,z020z,z022,z022y,z022z,z041,z041b,z041d,z043,z082,z082b,z0822;

      // getting values of isbn and indicator fields from biblioactionform
      
      in0201 = caf.getIn0201();
      in0202 = caf.getIn0202();
      in0221= caf.getIn0221();
      in0222 = caf.getIn0222();
      in0411 = caf.getIn0411();
      in0412 = caf.getIn0412();
      in0431 = caf.getIn0431();
      in0432 = caf.getIn0432();
      in0821 = caf.getIn0821();
      in0822 = caf.getIn0822();

      // getting values of subfields from biblioactionform
      z020 = caf.getZ020();
      z020c = caf.getZ020c();
      z020z = caf.getZ020z();
      z022 = caf.getZ022();
      z022y = caf.getZ022y();
      z022z = caf.getZ022z();
      z041  = caf.getZ041();
      z041b = caf.getZ041b();
      z041d = caf.getZ041d();
      z043 = caf.getZ043();
      z082 = caf.getZ082();
      z082b = caf.getZ082b();
      z0822 = caf.getZ0822();
      
//filling object with data for MARC Tag 020
    biblioid.setLibraryId(library_id);
    biblio.setSublibraryId(sub_library_id);
    biblioid.setMarctag("020");
    biblio.setIndicator1(in0201);
    biblio.setIndicator2(in0202);
    biblio.set$a(z020);
    biblio.set$c(z020c);
    biblio.set$z(z020z);
   
         
          biblioid.setBibId(bibid);
           biblio.setId(biblioid);
//           marchib.insert(biblio);
        hm1 = (HashMap)session.getAttribute("hsmp");
          //insert
        if(hm1.containsKey("1")){
            hm1.remove("1");
        }
    hm1.put("1", biblio);
   
    System.out.println("1st object saved");

//filling object with data for Marc tag 022
    biblioid1.setLibraryId(library_id);
    biblio1.setSublibraryId(sub_library_id);
    biblioid1.setMarctag("022");
    biblio1.setId(biblioid1);
    biblio1.setIndicator1(in0221);
    biblio1.setIndicator2(in0222);
    biblio1.set$a(z022);
    biblio1.set$y(z022y);
    biblio1.set$z(z022z);
   
          biblioid1.setBibId(bibid);
           biblio1.setId(biblioid1);
//           marchib.insert(biblio1);
            if(hm1.containsKey("2")){
            hm1.remove("2");
        }
hm1.put("2", biblio1);
          //insert
     
    System.out.println("2nd object saved");

//filling object with data for Marc tag 041
    
    biblioid2.setLibraryId(library_id);
    biblio2.setSublibraryId(sub_library_id);
    biblioid2.setMarctag("041");
    biblio2.setId(biblioid2);
    biblio2.setIndicator1(in0411);
    biblio2.setIndicator2(in0412);
    biblio2.set$a(z041);
    biblio2.set$y(z041b);
    biblio2.set$z(z041d);
  
         biblioid2.setBibId(bibid);
           biblio2.setId(biblioid2);
//           marchib.insert(biblio2);
            if(hm1.containsKey("3")){
            hm1.remove("3");
        }
hm1.put("3", biblio2);
          //insert
    
    System.out.println("3rd object saved");

////filling object with data for Marc tag 043
    biblioid3.setLibraryId(library_id);
    biblio3.setSublibraryId(sub_library_id);
    biblioid3.setMarctag("043");
    biblio3.setId(biblioid3);
    biblio3.setIndicator1(in0431);
    biblio3.setIndicator2(in0432);
    biblio3.set$a(z043);

    biblioid3.setBibId(bibid);
           biblio3.setId(biblioid3);
//           marchib.insert(biblio3);
            if(hm1.containsKey("4")){
            hm1.remove("4");
        }
hm1.put("4", biblio3);
          //insert
  
    System.out.println("4th object saved");

//filling object with data for Marc tag 082
   biblioid4.setLibraryId(library_id);
    biblio4.setSublibraryId(sub_library_id);
    biblioid4.setMarctag("082");
    biblio4.setId(biblioid1);
    biblio4.setIndicator1(in0821);
    biblio4.setIndicator2(in0822);
    biblio4.set$a(z082);
    biblio4.set$y(z082b);
    biblio4.set$z(z0822);
   
         biblioid4.setBibId(bibid);
           biblio4.setId(biblioid4);
//           marchib.insert(biblio4);
            if(hm1.containsKey("5")){
            hm1.remove("5");
        }
hm1.put("5", biblio4);
System.out.println(hm1.size());
          //insert
     session.setAttribute("hsmp", hm1);
    System.out.println("5th object saved");


      System.out.println("t value is = "+t);
        //code for mapping forwards......
         if(t.equals("1"))
        {
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
        else if(t.equals("8")){
                return mapping.findForward("forward8");
        }
        else if(t.equals("9"))
        {
        return mapping.findForward("forward9");
        }
        else if(t.equals("10"))
        {
        return mapping.findForward("forward10");
        }
       return mapping.findForward("forward0");
    }
}
