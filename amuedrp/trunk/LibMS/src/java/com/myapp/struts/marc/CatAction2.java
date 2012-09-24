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
import org.apache.commons.lang.StringUtils;
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
  


    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    MarcHibDAO marchib=new MarcHibDAO();
     HashMap hm1=new HashMap();

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
    if(caf2.getIn2101()!=null)
    if(StringUtils.isNotBlank(in2101.toString())&&StringUtils.isNotEmpty(in2101.toString()))
    biblio.setIndicator1(in2101);
    if(caf2.getIn2102()!=null)
    if(StringUtils.isNotBlank(in2102.toString())&&StringUtils.isNotEmpty(in2102.toString()))
    biblio.setIndicator2(in2102);
if(StringUtils.isNotBlank(z2102)&&StringUtils.isNotEmpty(z2102))
    biblio.set$2(z2102);
   if(StringUtils.isNotBlank(z210a)&&StringUtils.isNotEmpty(z210a))
        biblio.set$a(z210a);
  if(StringUtils.isNotBlank(z210b)&&StringUtils.isNotEmpty(z210b))
        biblio.set$b(z210b);
       biblioid.setBibId(bibid);
           biblio.setId(biblioid);
//           marchib.insert(biblio);
hm1 =(HashMap)session.getAttribute("hsmp");
if(hm1==null)
    hm1=new HashMap();

//if(hm1.containsKey("9")){
      //      hm1.remove("9");
     //   }
 hm1.put("9", biblio); 

         //filling object with data for MARC Tag 245
    biblioid1.setLibraryId(library_id);
    biblio1.setSublibraryId(sub_library_id);
    biblioid1.setMarctag("245");
    if(caf2.getIn2451()!=null)
    if(StringUtils.isNotBlank(in2451.toString())&&StringUtils.isNotEmpty(in2451.toString()))
    biblio1.setIndicator1(in2451);
    if(caf2.getIn2452()!=null)
    if(StringUtils.isNotBlank(in2452.toString())&&StringUtils.isNotEmpty(in2452.toString()))
    biblio1.setIndicator2(in2452);
    if(StringUtils.isNotBlank(z245a)&&StringUtils.isNotEmpty(z245a))
    biblio1.set$a(z245a);
    if(StringUtils.isNotBlank(z245b)&&StringUtils.isNotEmpty(z245b))
        biblio1.set$b(z245b);
    if(StringUtils.isNotBlank(z245c)&&StringUtils.isNotEmpty(z245c))
        biblio1.set$c(z245c);
    if(StringUtils.isNotBlank(z245n)&&StringUtils.isNotEmpty(z245n))
        biblio1.set$n(z245n);
    if(StringUtils.isNotBlank(z245k)&&StringUtils.isNotEmpty(z245k))
    biblio1.set$k(z245k);
        
          biblioid1.setBibId(bibid);
           biblio1.setId(biblioid1);
//           marchib.insert(biblio1);
//if(hm1.containsKey("10")){
       //     hm1.remove("10");
       // }
 hm1.put("10", biblio1);

     

        //filling object with data for MARC Tag 250
    biblioid2.setLibraryId(library_id);
    biblio2.setSublibraryId(sub_library_id);
    biblioid2.setMarctag("250");
    if(caf2.getIn2501()!=null)
    if(StringUtils.isNotBlank(in2501.toString())&&StringUtils.isNotEmpty(in2501.toString()))
    biblio2.setIndicator1(in2501);
    if(caf2.getIn2502()!=null)
    if(StringUtils.isNotBlank(in2502.toString())&&StringUtils.isNotEmpty(in2502.toString()))
    biblio2.setIndicator2(in2502);
    if(StringUtils.isNotBlank(z250a)&&StringUtils.isNotEmpty(z250a))
    biblio2.set$a(z250a);
    if(StringUtils.isNotBlank(z250b)&&StringUtils.isNotEmpty(z250b))
        biblio2.set$b(z250b);
       biblioid2.setBibId(bibid);
           biblio2.setId(biblioid2);
//           marchib.insert(biblio2);
//if(hm1.containsKey("11")){
    //        hm1.remove("11");
     //   }
 hm1.put("11", biblio2);


    //filling object with data for MARC Tag 256
    biblioid3.setLibraryId(library_id);
    biblio3.setSublibraryId(sub_library_id);
    biblioid3.setMarctag("256");
    if(caf2.getIn2561()!=null)
    if(StringUtils.isNotBlank(in2561.toString())&&StringUtils.isNotEmpty(in2561.toString()))
    biblio3.setIndicator1(in2561);
    if(caf2.getIn2562()!=null)
    if(StringUtils.isNotBlank(in2562.toString())&&StringUtils.isNotEmpty(in2562.toString()))
    biblio3.setIndicator2(in2562);
    if(StringUtils.isNotBlank(z256a.toString())&&StringUtils.isNotEmpty(z256a.toString()))
    biblio3.set$a(z256a);
          biblioid3.setBibId(bibid);
           biblio3.setId(biblioid3);
//           marchib.insert(biblio3);
//if(hm1.containsKey("12")){
      //      hm1.remove("12");
       // }
 hm1.put("12", biblio3);

     

        //filling object with data for MARC Tag 260
    biblioid4.setLibraryId(library_id);
    biblio4.setSublibraryId(sub_library_id);
    biblioid4.setMarctag("260");
    if(caf2.getIn2601()!=null)
    if(StringUtils.isNotBlank(in2601.toString())&&StringUtils.isNotEmpty(in2601.toString()))
    biblio4.setIndicator1(in2601);
    if(caf2.getIn2602()!=null)
    if(StringUtils.isNotBlank(in2602.toString())&&StringUtils.isNotEmpty(in2602.toString()))
    biblio4.setIndicator2(in2602);
    if(StringUtils.isNotBlank(z260a)&&StringUtils.isNotEmpty(z260a))
    biblio4.set$a(z260a);
    if(StringUtils.isNotBlank(z260b)&&StringUtils.isNotEmpty(z260b))
    biblio4.set$b(z260b);
    if(StringUtils.isNotBlank(z260c)&&StringUtils.isNotEmpty(z260c))
    biblio4.set$c(z260c);
    if(StringUtils.isNotBlank(z260e)&&StringUtils.isNotEmpty(z260e))
    biblio4.set$n(z260e);
    if(StringUtils.isNotBlank(z260f)&&StringUtils.isNotEmpty(z260f))
    biblio4.set$k(z260f);

        biblioid4.setBibId(bibid);
           biblio4.setId(biblioid4);
//           marchib.insert(biblio4);
//if(hm1.containsKey("13")){
       //     hm1.remove("13");
      //  }
 hm1.put("13", biblio4);

    
         //filling object with data for MARC Tag 263
    biblioid5.setLibraryId(library_id);
    biblio5.setSublibraryId(sub_library_id);
    biblioid5.setMarctag("263");
    if(caf2.getIn2631()!=null)
    if(StringUtils.isNotBlank(in2631.toString())&&StringUtils.isNotEmpty(in2631.toString()))
    biblio5.setIndicator1(in2631);
    if(caf2.getIn2632()!=null)
    if(StringUtils.isNotBlank(in2632.toString())&&StringUtils.isNotEmpty(in2632.toString()))
    biblio5.setIndicator2(in2632);
    if(StringUtils.isNotBlank(z263a)&&StringUtils.isNotEmpty(z263a))
    biblio5.set$a(z263a);
       biblioid5.setBibId(bibid);
           biblio5.setId(biblioid5);
//           marchib.insert(biblio5);
//if(hm1.containsKey("14")){
          //  hm1.remove("14");
      //  }
 hm1.put("14", biblio5);

 session.setAttribute("hsmp", hm1);
    
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
