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
public class CatAction6 extends org.apache.struts.action.Action {
    
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
         System.out.println("inside cataction6 !");
        CatActionForm6 caf6=(CatActionForm6)form;

        HttpSession session = request.getSession();
       int  bibid = (Integer)session.getAttribute("biblio_id");
        System.out.println("************************************************  "+bibid);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");



        String t=caf6.getZclick();             // t is click value on jsp

         String z600a,z600b,z600c,z600d, z650a,z650b,z650c,z650d,z650e,z6504,z650v,z650x,z650y,z650z,z6502,zclick;
         Character in6001,in6002,in6501,in6502;

         // getting values of indicator fields from CatActionForm6
         in6001=caf6.getIn6001();
         in6002=caf6.getIn6002();
         in6501=caf6.getIn6501();
         in6502=caf6.getIn6502();

           //getting values of subfields from CatActionForm6
                z600a=caf6.getZ600a();
                z600b=caf6.getZ600b();
                z600c=caf6.getZ600c();
                z600d=caf6.getZ600d();

                z650a=caf6.getZ650a();
                z650b=caf6.getZ650b();
                z650c=caf6.getZ650c();
                z650d=caf6.getZ650d();
                z650e=caf6.getZ650e();
                z6504=caf6.getZ6504();
                z650v=caf6.getZ650v();
                z650x=caf6.getZ650x();
                z650y=caf6.getZ650y();
                z650z=caf6.getZ650z();
                z6502=caf6.getZ6502();

        //filling object with data for MARC Tag 600
                biblioid.setLibraryId(library_id);
                biblio.setSublibraryId(sub_library_id);
                biblioid.setMarctag("600");
                biblio.setIndicator1(in6001);
                biblio.setIndicator2(in6002);
                    biblio.set$a(z600a);
                    biblio.set$b(z600b);
                    biblio.set$c(z600c);
                    biblio.set$d(z600d);


                   biblioid.setBibId(bibid);
                       biblio.setId(biblioid);
//                       marchib.insert(biblio);
                       hm1=(HashMap)session.getAttribute("hsmp");
if(hm1.containsKey("26")){
            hm1.remove("26");
        }
 hm1.put("26", biblio);

                

                     //filling object with data for MARC Tag 650
                biblioid1.setLibraryId(library_id);
                biblio1.setSublibraryId(sub_library_id);
                biblioid1.setMarctag("650");
                biblio1.setIndicator1(in6501);
                biblio1.setIndicator2(in6502);
                    biblio1.set$a(z650a);
                    biblio1.set$b(z650b);
                    biblio1.set$c(z650c);
                    biblio1.set$d(z650d);
                    biblio1.set$e(z650e);
                    biblio1.set$4(z6504);
                    biblio1.set$a(z650v);
                    biblio1.set$b(z650x);
                    biblio1.set$c(z650y);
                    biblio1.set$d(z650z);
                    biblio1.set$e(z6502);

                    biblioid1.setBibId(bibid);
                       biblio1.setId(biblioid1);
//                       marchib.insert(biblio1);
if(hm1.containsKey("27")){
            hm1.remove("27");
        }
 hm1.put("27", biblio1);

                

             System.out.println("All both objects saved now NAvigating to page "+t);
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

                    return mapping.findForward("forward6");
                }
}
