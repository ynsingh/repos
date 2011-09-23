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
public class CatAction8 extends org.apache.struts.action.Action {
    
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
        System.out.println("inside cataction8 !");
        CatActionForm8 caf8=(CatActionForm8)form;

        HttpSession session = request.getSession();
       int bibid = (Integer)session.getAttribute("biblio_id");
        System.out.println("************************************************  "+bibid);
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");



        String t=caf8.getZclick();             // t is click value on jsp

        String z800a,z800b,z800c,z800d,z800t,z800l,z800f,z800s,z800v,z8004, z830a,z830h,z830n,z830p,z830v,z830x,z8303,z8305, z850a;
     String  z852a,z852b,z852c,z852f,z852e,z852h,z852i,z852n,z852t,z852u, z856a,z856b,z856c,z856d,z856h,z856i,z856f,z856j,z856k,z856l,z856m,z856n,z856o,z856p,z856q,z856u,z856t,z856s,z856x,z856z;
    Character in8001,in8002,in8301,in8302,in8501,in8502,in8521,in8522,in8561,in8562;

    // getting values of indicator fields from CatActionForm8
            in8001=caf8.getIn8001();
            in8002=caf8.getIn8002();
            in8301=caf8.getIn8301();
            in8302=caf8.getIn8302();
            in8501=caf8.getIn8501();
            in8502=caf8.getIn8502();
            in8521=caf8.getIn8521();
            in8522=caf8.getIn8522();
            in8561=caf8.getIn8561();
            in8562=caf8.getIn8562();

     //getting values of subfields from CatActionForm8
            z800a=caf8.getZ800a();
            z800b=caf8.getZ800b();
            z800c=caf8.getZ800c();
            z800d=caf8.getZ800d();
            z800t=caf8.getZ800t();
            z800l=caf8.getZ800l();
            z800f=caf8.getZ800f();
            z800s=caf8.getZ800s();
            z800v=caf8.getZ800v();
            z8004=caf8.getZ8004();

                z830a=caf8.getZ830a();
                z830h=caf8.getZ830h();
                z830n=caf8.getZ830n();
                z830p=caf8.getZ830p();
                z830v=caf8.getZ830v();
                z830x=caf8.getZ830x();
                z8303=caf8.getZ8303();
                z8305=caf8.getZ8305();
            
                    z850a=caf8.getZ850a();

                        z852a=caf8.getZ852a();
                        z852b=caf8.getZ852b();
                        z852c=caf8.getZ852c();
                        z852f=caf8.getZ852f();
                        z852e=caf8.getZ852e();
                        z852h=caf8.getZ852h();
                        z852i=caf8.getZ852i();
                        z852n=caf8.getZ852n();
                        z852t=caf8.getZ852t();
                        z852u=caf8.getZ852u();

                            z856a=caf8.getZ856a();
                            z856b=caf8.getZ856b();
                            z856c=caf8.getZ856c();
                            z856d=caf8.getZ856d();
                            z856h=caf8.getZ856h();
                            z856i=caf8.getZ856i();
                            z856f=caf8.getZ856f();
                            z856j=caf8.getZ856j();
                            z856k=caf8.getZ856k();
                            z856l=caf8.getZ856l();
                            z856m=caf8.getZ856m();
                            z856n=caf8.getZ856n();
                            z856o=caf8.getZ856o();
                            z856p=caf8.getZ856p();
                            z856q=caf8.getZ856q();
                            z856u=caf8.getZ856u();
                            z856t=caf8.getZ856t();
                            z856s=caf8.getZ856s();
                            z856x=caf8.getZ856x();
                            z856z=caf8.getZ856z();

             //filling object with data for MARC Tag 800
                biblioid.setLibraryId(library_id);
                biblio.setSublibraryId(sub_library_id);
                biblioid.setMarctag("800");
                biblio.setIndicator1(in8001);
                biblio.setIndicator2(in8002);
                    biblio.set$3(z800a);
                    biblio.set$a(z800b);
                    biblio.set$3(z800c);
                    biblio.set$a(z800d);
                    biblio.set$3(z800t);
                    biblio.set$a(z800l);
                    biblio.set$3(z800f);
                    biblio.set$a(z800s);
                    biblio.set$3(z800v);
                    biblio.set$a(z8004);

                   biblioid.setBibId(bibid);
                       biblio.setId(biblioid);
//                       marchib.insert(biblio);
                       hm1=(HashMap)session.getAttribute("hsmp");
if(hm1.containsKey("30")){
            hm1.remove("30");
        }
 hm1.put("30", biblio);


                     //filling object with data for MARC Tag 830
                biblioid1.setLibraryId(library_id);
                biblio1.setSublibraryId(sub_library_id);
                biblioid1.setMarctag("830");
                biblio1.setIndicator1(in8301);
                biblio1.setIndicator2(in8302);
                    biblio1.set$a(z830a);
                    biblio1.set$b(z830h);
                    biblio1.set$c(z830n);
                    biblio1.set$d(z830p);
                    biblio1.set$g(z830v);
                    biblio1.set$g(z830x);
                    biblio1.set$a(z8303);
                    biblio1.set$b(z8305);

                    biblioid1.setBibId(bibid);
                       biblio1.setId(biblioid1);
//                       marchib.insert(biblio1);
if(hm1.containsKey("31")){
            hm1.remove("31");
        }
 hm1.put("31", biblio1);


                    //filling object with data for MARC Tag 850
                biblioid2.setLibraryId(library_id);
                biblio2.setSublibraryId(sub_library_id);
                biblioid2.setMarctag("850");
                biblio2.setIndicator1(in8501);
                biblio2.setIndicator2(in8502);
                    biblio2.set$a(z850a);
                    
                          biblioid2.setBibId(bibid);
                       biblio2.setId(biblioid2);
//                       marchib.insert(biblio2);
if(hm1.containsKey("32")){
            hm1.remove("32");
        }
 hm1.put("32", biblio2);

                 

                //filling object with data for MARC Tag 852
                biblioid3.setLibraryId(library_id);
                biblio3.setSublibraryId(sub_library_id);
                biblioid3.setMarctag("852");
                biblio3.setIndicator1(in8521);
                biblio3.setIndicator2(in8522);
                    biblio3.set$a(z852a);
                    biblio3.set$g(z852b);
                    biblio3.set$r(z852c);
                    biblio3.set$t(z852e);
                    biblio3.set$u(z852f);
                    biblio3.set$a(z852h);
                    biblio3.set$g(z852i);
                    biblio3.set$r(z852n);
                    biblio3.set$t(z852t);
                    biblio3.set$u(z852u);

                  biblioid3.setBibId(bibid);
                       biblio3.setId(biblioid3);
//                       marchib.insert(biblio3);
if(hm1.containsKey("33")){
            hm1.remove("33");
        }
 hm1.put("33", biblio3);

              

                    //filling object with data for MARC Tag 856
                biblioid4.setLibraryId(library_id);
                biblio4.setSublibraryId(sub_library_id);
                biblioid4.setMarctag("856");
                biblio4.setIndicator1(in8561);
                biblio4.setIndicator2(in8562);
                    biblio4.set$a(z856a);
                    biblio4.set$b(z856b);
                    biblio4.set$c(z856c);
                    biblio4.set$u(z856d);
                    biblio4.set$2(z856f);
                    biblio4.set$3(z856h);
                    biblio4.set$a(z856i);
                    biblio4.set$b(z856j);
                    biblio4.set$c(z856k);
                    biblio4.set$u(z856l);
                    biblio4.set$2(z856m);
                    biblio4.set$3(z856n);
                    biblio4.set$a(z856o);
                    biblio4.set$b(z856p);
                    biblio4.set$c(z856q);
                    biblio4.set$u(z856u);
                    biblio4.set$2(z856t);
                    biblio4.set$3(z856s);
                    biblio4.set$2(z856x);
                    biblio4.set$3(z856z);
                    
                   biblioid4.setBibId(bibid);
                       biblio4.setId(biblioid4);
//                       marchib.insert(biblio4);
if(hm1.containsKey("34")){
            hm1.remove("34");
        }
 hm1.put("34", biblio4);

       System.out.println("All five objects saved now NAvigating to page "+t);
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
        return mapping.findForward("forward8");
}
}
