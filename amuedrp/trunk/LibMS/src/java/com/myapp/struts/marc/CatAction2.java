/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

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
public class CatAction2 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    HashMap hm1=new HashMap();
     private MarcHibDAO marchib=new MarcHibDAO();
   
    MarcHibDAO dao=new MarcHibDAO();

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
    

    private Biblio biblio5=new Biblio();
    private BiblioId biblioid5= new BiblioId();


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

        System.out.println("inside cataction2 !");
        CatActionForm2 caf2=(CatActionForm2)form;

        HttpSession session = request.getSession();
        int bibid = (Integer)session.getAttribute("biblio_id");
        System.out.println("************************************************  "+bibid);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");

        

        String t=caf2.getZclick();             // t is click value on jsp

        Character in2101,in2102,in2451,in2452,in2501,in2502,in2561,in2562,in2601,in2602,in2631,in2632;
        String z210a,z210b,z2102, z245a,z245b,z245c,z245n,z245k, z250a,z250b, z256a, z260a,z260b,z260c,z260e,z260f, z263a;

            // getting values of indicator fields from CatActionForm2
                in2101=caf2.getIn2101();
                in2102=caf2.getIn2102();
                in2451=caf2.getIn2451();
                in2452=caf2.getIn2452();
                in2501=caf2.getIn2501();
                in2502=caf2.getIn2502();
                in2561=caf2.getIn2561();
                in2562=caf2.getIn2562();
                in2601=caf2.getIn2601();
                in2602=caf2.getIn2602();
                in2631=caf2.getIn2631();
                in2632 =caf2.getIn2632();

           //getting values of subfields from CatActionForm2

                z210a=caf2.getZ210a();
                z210b=caf2.getZ210b();
                z2102=caf2.getZ2102();

                z245a=caf2.getZ245a();
                z245b=caf2.getZ245b();
                z245c=caf2.getZ245c();
                z245n=caf2.getZ245n();
                z245k=caf2.getZ245k();

                z250a=caf2.getZ250a();
                z250b=caf2.getZ250b();

                z256a=caf2.getZ256a();

                z260a=caf2.getZ260a();
                z260b=caf2.getZ260b();
                z260c=caf2.getZ260c();
                z260e=caf2.getZ260e();
                z260f=caf2.getZ260f();

                z263a=caf2.getZ263a();

    //filling object with data for MARC Tag 210
    biblioid.setLibraryId(library_id);
    biblio.setSublibraryId(sub_library_id);
    biblioid.setMarctag("210");
    biblio.setIndicator1(in2101);
    biblio.setIndicator2(in2102);
        biblio.set$2(z2102);
        biblio.set$a(z210a);
        biblio.set$b(z210b);
       
       biblioid.setBibId(bibid);
           biblio.setId(biblioid);
//           marchib.insert(biblio);
hm1 =(HashMap)session.getAttribute("hsmp");
if(hm1.containsKey("9")){
            hm1.remove("9");
        }
 hm1.put("9", biblio);

    

         //filling object with data for MARC Tag 245
    biblioid1.setLibraryId(library_id);
    biblio1.setSublibraryId(sub_library_id);
    biblioid1.setMarctag("245");
    biblio1.setIndicator1(in2451);
    biblio1.setIndicator2(in2452);
        biblio1.set$a(z245a);
        biblio1.set$b(z245b);
        biblio1.set$c(z245c);
        biblio1.set$n(z245n);
        biblio1.set$k(z245k);
        
          biblioid1.setBibId(bibid);
           biblio1.setId(biblioid1);
//           marchib.insert(biblio1);
if(hm1.containsKey("10")){
            hm1.remove("10");
        }
 hm1.put("10", biblio1);

     

        //filling object with data for MARC Tag 250
    biblioid2.setLibraryId(library_id);
    biblio2.setSublibraryId(sub_library_id);
    biblioid2.setMarctag("250");
    biblio2.setIndicator1(in2501);
    biblio2.setIndicator2(in2502);
        biblio2.set$a(z250a);
        biblio2.set$b(z250b);

       biblioid2.setBibId(bibid);
           biblio2.setId(biblioid2);
//           marchib.insert(biblio2);
if(hm1.containsKey("11")){
            hm1.remove("11");
        }
 hm1.put("11", biblio2);


    //filling object with data for MARC Tag 256
    biblioid3.setLibraryId(library_id);
    biblio3.setSublibraryId(sub_library_id);
    biblioid3.setMarctag("256");
    biblio3.setIndicator1(in2561);
    biblio3.setIndicator2(in2562);
        biblio3.set$a(z256a);


       
          biblioid3.setBibId(bibid);
           biblio3.setId(biblioid3);
//           marchib.insert(biblio3);
if(hm1.containsKey("12")){
            hm1.remove("12");
        }
 hm1.put("12", biblio3);

     

        //filling object with data for MARC Tag 260
    biblioid4.setLibraryId(library_id);
    biblio4.setSublibraryId(sub_library_id);
    biblioid4.setMarctag("260");
    biblio4.setIndicator1(in2601);
    biblio4.setIndicator2(in2602);
        biblio4.set$a(z260a);
        biblio4.set$b(z260b);
        biblio4.set$c(z260c);
        biblio4.set$n(z260e);
        biblio4.set$k(z260f);

        biblioid4.setBibId(bibid);
           biblio4.setId(biblioid4);
//           marchib.insert(biblio4);
if(hm1.containsKey("13")){
            hm1.remove("13");
        }
 hm1.put("13", biblio4);

    
         //filling object with data for MARC Tag 263
    biblioid5.setLibraryId(library_id);
    biblio5.setSublibraryId(sub_library_id);
    biblioid5.setMarctag("263");
    biblio5.setIndicator1(in2631);
    biblio5.setIndicator2(in2632);
        biblio5.set$a(z263a);


       biblioid5.setBibId(bibid);
           biblio5.setId(biblioid5);
//           marchib.insert(biblio5);
if(hm1.containsKey("14")){
            hm1.remove("14");
        }
 hm1.put("14", biblio5);

    
        System.out.println("All six objects saved now NAvigating to page "+t);
 //code for mapping forwards......
         if(t.equals("0"))
        {
            return mapping.findForward("forward0");
        }
        else if(t.equals("1")){
                return mapping.findForward("forward1");
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

        return mapping.findForward("forward2");
    }
}
