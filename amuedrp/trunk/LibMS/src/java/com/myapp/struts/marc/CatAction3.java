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
public class CatAction3 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     private MarcHibDAO marchib=new MarcHibDAO();
   HashMap hm1=new HashMap();
    MarcHibDAO dao=new MarcHibDAO();

    private Biblio biblio=new Biblio();
    private BiblioId biblioid= new BiblioId();

    private Biblio biblio1=new Biblio();
    private BiblioId biblioid1= new BiblioId();

    private Biblio biblio2=new Biblio();
    private BiblioId biblioid2= new BiblioId();

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
        System.out.println("inside cataction3 !");
        CatActionForm3 caf3=(CatActionForm3)form;
        HttpSession session = request.getSession();
       int bibid = (Integer)session.getAttribute("biblio_id");

        System.out.println("************************************************  "+bibid);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");



        String t=caf3.getZclick();             // t is click value on jsp

         Character in3001,in3002,in3061,in3062,in3361,in3362;
     String name,z300a,z300b,z300c,z300e,z300f,z300g,z3003, z306a, z336a,z336b,z3362,z3363 ,zclick;

     
     // getting values of indicator fields from CatActionForm3
                    in3001=caf3.getIn3001();
                    in3002=caf3.getIn3002();
                    in3061=caf3.getIn3061();
                    in3062=caf3.getIn3062();
                    in3361=caf3.getIn3361();
                    in3362=caf3.getIn3362();

      //getting values of subfields from CatActionForm3
                    z300a=caf3.getZ300a();
                    z300b=caf3.getZ300b();
                    z300c=caf3.getZ300c();
                    z300e=caf3.getZ300e();
                    z300f=caf3.getZ300f();
                    z300g=caf3.getZ300g();
                    z3003=caf3.getZ3003();

                    z306a=caf3.getZ306a();

                    z336a=caf3.getZ336a();
                    z336b=caf3.getZ336b();
                    z3362=caf3.getZ3362();
                    z3363=caf3.getZ3363();

        //filling object with data for MARC Tag 300
            biblioid.setLibraryId(library_id);
            biblio.setSublibraryId(sub_library_id);
            biblioid.setMarctag("300");
            biblio.setIndicator1(in3001);
            biblio.setIndicator2(in3002);
                biblio.set$a(z300a);
                biblio.set$b(z300b);
                biblio.set$c(z300c);
                biblio.set$f(z300f);
                biblio.set$e(z300e);
                biblio.set$g(z300g);
                biblio.set$3(z3003);

               biblioid.setBibId(bibid);
                   biblio.setId(biblioid);
//                   marchib.insert(biblio);
hm1 =(HashMap)session.getAttribute("hsmp");
if(hm1.containsKey("15")){
            hm1.remove("15");
        }
 hm1.put("15", biblio);

            

                 //filling object with data for MARC Tag 306
            biblioid1.setLibraryId(library_id);
            biblio1.setSublibraryId(sub_library_id);
            biblioid1.setMarctag("306");
            biblio1.setIndicator1(in3061);
            biblio1.setIndicator2(in3062);
                biblio1.set$a(z306a);
               
                  biblioid1.setBibId(bibid);
                   biblio1.setId(biblioid1);
//                   marchib.insert(biblio1);

if(hm1.containsKey("16")){
            hm1.remove("16");
        }
 hm1.put("16", biblio1);

            

                //filling object with data for MARC Tag 336
            biblioid2.setLibraryId(library_id);
            biblio2.setSublibraryId(sub_library_id);
            biblioid2.setMarctag("336");
            biblio2.setIndicator1(in3361);
            biblio2.setIndicator2(in3362);
                biblio2.set$a(z336a);
                biblio2.set$b(z336b);
                biblio2.set$2(z3362);
                biblio2.set$3(z3363);

                biblioid2.setBibId(bibid);
                   biblio2.setId(biblioid2);
//                   marchib.insert(biblio2);
if(hm1.containsKey("18")){
            hm1.remove("18");
        }
 hm1.put("18", biblio2);

             
            System.out.println("All three objects saved now NAvigating to page "+t);
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

        return mapping.findForward("forward3");
    }
}
