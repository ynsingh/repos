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
public class CatAction7 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    HashMap hm1=new HashMap();

     private MarcHibDAO marchib=new MarcHibDAO();
    
    MarcHibDAO dao=new MarcHibDAO();

    private Biblio biblio=new Biblio();
    private BiblioId biblioid= new BiblioId();

    private Biblio biblio1=new Biblio();
    private BiblioId biblioid1= new BiblioId();

    
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

        System.out.println("inside cataction7 !");
        CatActionForm7 caf7=(CatActionForm7)form;

        HttpSession session = request.getSession();
        int bibid = (Integer)session.getAttribute("biblio_id");
        System.out.println("************************************************  "+bibid);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");

        String z700a,z700b,z700c,z700d,z700e,z700f,z700h,z700k,z700l,z700m,z700n,z700p,z700r,z700s,z700t,z7004, z740a,z740h,z740n,z740p,z7405;
         Character in7001,in7002,in7401,in7402;

        String t=caf7.getZclick();             // t is click value on jsp

        // getting values of indicator fields from CatActionForm7
                in7001=caf7.getIn7001();
                in7002=caf7.getIn7002();
                in7401=caf7.getIn7401();
                in7402=caf7.getIn7402();
       //getting values of subfields from CatActionForm7
                z700a=caf7.getZ700a();
                z700b=caf7.getZ700b();
                z700c=caf7.getZ700c();
                z700d=caf7.getZ700d();
                z700e=caf7.getZ700e();
                z700f=caf7.getZ700f();
                z700h=caf7.getZ700h();
                z700k=caf7.getZ700k();
                z700l=caf7.getZ700l();
                z700m=caf7.getZ700m();
                z700n=caf7.getZ700n();
                z700p=caf7.getZ700p();
                z700r=caf7.getZ700r();
                z700s=caf7.getZ700s();
                z700t=caf7.getZ700t();
                z7004=caf7.getZ7004();

                z740a=caf7.getZ740a();
                z740h=caf7.getZ740h();
                z740n=caf7.getZ740n();
                z740p=caf7.getZ740p();
               
                //filling object with data for MARC Tag 700
                biblioid.setLibraryId(library_id);
                biblio.setSublibraryId(sub_library_id);
                biblioid.setMarctag("700");
                biblio.setIndicator1(in7001);
                biblio.setIndicator2(in7002);
                    biblio.set$a(z700a);
                    biblio.set$b(z700b);
                    biblio.set$c(z700c);
                    biblio.set$d(z700d);
                    biblio.set$a(z700e);
                    biblio.set$b(z700f);
                    biblio.set$c(z700h);
                    biblio.set$d(z700k);
                    biblio.set$a(z700l);
                    biblio.set$b(z700m);
                    biblio.set$c(z700n);
                    biblio.set$d(z700p);
                    biblio.set$a(z700r);
                    biblio.set$b(z700s);
                    biblio.set$c(z700t);
                    biblio.set$d(z7004);


                    biblioid.setBibId(bibid);
                       biblio.setId(biblioid);
//                       marchib.insert(biblio);
                       hm1=(HashMap)session.getAttribute("hsmp");
if(hm1.containsKey("28")){
            hm1.remove("28");
        }
 hm1.put("28", biblio);


                     //filling object with data for MARC Tag 740
                biblioid1.setLibraryId(library_id);
                biblio1.setSublibraryId(sub_library_id);
                biblioid1.setMarctag("740");
                biblio1.setIndicator1(in7401);
                biblio1.setIndicator2(in7402);
                    biblio1.set$a(z740a);
                    biblio1.set$b(z740h);
                    biblio1.set$c(z740n);
                    biblio1.set$d(z740p);
                    
                   
                     biblioid1.setBibId(bibid);
                       biblio1.setId(biblioid1);
//                       marchib.insert(biblio1);
if(hm1.containsKey("29")){
            hm1.remove("29");
        }
 hm1.put("29", biblio1);

                

             System.out.println(" Both the objects saved now NAvigating to page "+t);
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

                    return mapping.findForward("forward7");
                }

}
