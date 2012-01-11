/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Biblio;
import com.myapp.struts.hbm.BiblioId;
import java.util.HashMap;
import java.util.List;
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
public class UCatAction6 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    HashMap hm1=new HashMap();
    private MarcHibDAO marchib=new MarcHibDAO();



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
       int  bibid = Integer.parseInt((String)session.getAttribute("biblio_id"));
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
               if(caf6.getIn6001()!=null)
                if(StringUtils.isNotBlank(in6001.toString())&&StringUtils.isNotEmpty(in6001.toString()))
                biblio.setIndicator1(in6001);
                if(caf6.getIn6002()!=null)
                if(StringUtils.isNotBlank(in6002.toString())&&StringUtils.isNotEmpty(in6002.toString()))
                biblio.setIndicator2(in6002);
                if(StringUtils.isNotBlank(z600a)&&StringUtils.isNotEmpty(z600a))
                biblio.set$a(z600a);
               if(StringUtils.isNotBlank(z600b)&&StringUtils.isNotEmpty(z600b))
                    biblio.set$b(z600b);
               if(StringUtils.isNotBlank(z600c)&&StringUtils.isNotEmpty(z600c))
                    biblio.set$c(z600c);
               if(StringUtils.isNotBlank(z600d)&&StringUtils.isNotEmpty(z600d))
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
               if(caf6.getIn6501()!=null)
                if(StringUtils.isNotBlank(in6501.toString())&&StringUtils.isNotEmpty(in6501.toString()))
                biblio1.setIndicator1(in6501);
        if(caf6.getIn6502()!=null)
                if(StringUtils.isNotBlank(in6502.toString())&&StringUtils.isNotEmpty(in6502.toString()))
                biblio1.setIndicator2(in6502);
if(StringUtils.isNotBlank(z650a)&&StringUtils.isNotEmpty(z650a))
                biblio1.set$a(z650a);
if(StringUtils.isNotBlank(z650b)&&StringUtils.isNotEmpty(z650b))
                biblio1.set$b(z650b);
           if(StringUtils.isNotBlank(z650c)&&StringUtils.isNotEmpty(z650c))
                    biblio1.set$c(z650c);
if(StringUtils.isNotBlank(z650d)&&StringUtils.isNotEmpty(z650d))
                    biblio1.set$d(z650d);
           if(StringUtils.isNotBlank(z650e)&&StringUtils.isNotEmpty(z650e))
                    biblio1.set$e(z650e);
  if(StringUtils.isNotBlank(z6504)&&StringUtils.isNotEmpty(z6504))
                    biblio1.set$4(z6504);
           if(StringUtils.isNotBlank(z650v)&&StringUtils.isNotEmpty(z650v))
                    biblio1.set$a(z650v);
           if(StringUtils.isNotBlank(z650x)&&StringUtils.isNotEmpty(z650x))
                    biblio1.set$b(z650x);
             if(StringUtils.isNotBlank(z650y)&&StringUtils.isNotEmpty(z650y))
                biblio1.set$c(z650y);
          if(StringUtils.isNotBlank(z650z)&&StringUtils.isNotEmpty(z650z))
                    biblio1.set$d(z650z);
        if(StringUtils.isNotBlank(z6502)&&StringUtils.isNotEmpty(z6502))
                    biblio1.set$2(z6502);

                    biblioid1.setBibId(bibid);
                       biblio1.setId(biblioid1);
//                       marchib.insert(biblio1);
if(hm1.containsKey("27")){
            hm1.remove("27");
        }
 hm1.put("27", biblio1);



             System.out.println("All both objects saved now NAvigating to page "+t);
              String bib_id=(String)session.getAttribute("biblio_id") ;
                 List<Biblio> biblist= marchib.getdataforupdate(bib_id, library_id, sub_library_id);
             //code for mapping forwards......
                     if(t.equals("0"))
                    {

System.out.println("BBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+biblist.size());

            for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("020")){

                     request.setAttribute("020", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("022")){

                     request.setAttribute("022", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("041")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("041", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("043")){
                        //System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("043", biblist.get(i));
                    }

                   if(biblist.get(i).getId().getMarctag().equals("082")){
    //                    System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("082", biblist.get(i));
                    }

            }
                        return mapping.findForward("forward0");
                    }
                    else if(t.equals("1"))
                    {
             
System.out.println("1111111111111111111111   "+biblist.size());

            for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("100")){

                     request.setAttribute("100", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("110")){

                     request.setAttribute("110", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("130")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("130", biblist.get(i));
                    }
            }
                            return mapping.findForward("forward1");
                    }
                     else if(t.equals("2"))
                     {

           
                System.out.println("22222222222222    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("210")){

                     request.setAttribute("210", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("245")){

                     request.setAttribute("245", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("250")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("250", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("256")){

                     request.setAttribute("256", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("260")){

                     request.setAttribute("260", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("263")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("263", biblist.get(i));
                    }
            }
                            return mapping.findForward("forward2");
                    }
                    else if(t.equals("3"))
                    {
                
                System.out.println("666666666666    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("300")){

                     request.setAttribute("300", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("306")){

                     request.setAttribute("306", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("336")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("336", biblist.get(i));
                    }

            }
                            return mapping.findForward("forward3");
                    }
                    else if(t.equals("4"))
                    {
 
                System.out.println("44444444444    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("490")){

                     request.setAttribute("490", biblist.get(i));
                    }

            }

                            return mapping.findForward("forward4");
                    }

                    else if(t.equals("5"))
                    {
   
                System.out.println("555555555555    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
                 {
                   if(biblist.get(i).getId().getMarctag().equals("500")){

                     request.setAttribute("500", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("502")){

                     request.setAttribute("502", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("504")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("504", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("505")){

                     request.setAttribute("505", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("520")){

                     request.setAttribute("520", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("546")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("546", biblist.get(i));
                    }
                   }
                            return mapping.findForward("forward5");
                    }

                    else if(t.equals("7"))
                    {
                
                        System.out.println("77777777777    "+biblist.size());
                            for(int i=0;biblist.size()>i;i++)
                        {
                               if(biblist.get(i).getId().getMarctag().equals("700")){

                                 request.setAttribute("700", biblist.get(i));
                                }
                               if(biblist.get(i).getId().getMarctag().equals("740")){

                                 request.setAttribute("740", biblist.get(i));
                                }
                        }
                            return mapping.findForward("forward7");
                    }
                    else if(t.equals("8"))
                    {
           
                        System.out.println("888888888888    "+biblist.size());
                        for(int i=0;biblist.size()>i;i++)
                         {
                           if(biblist.get(i).getId().getMarctag().equals("800"))
                           {

                             request.setAttribute("800", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("830"))
                           {

                             request.setAttribute("830", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("850"))
                           {
                              // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                             request.setAttribute("850", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("852"))
                           {

                             request.setAttribute("852", biblist.get(i));
                            }
                           if(biblist.get(i).getId().getMarctag().equals("856"))
                           {

                             request.setAttribute("856", biblist.get(i));
                            }

                       }
                            return mapping.findForward("forward8");
                    }
                    else if(t.equals("9"))
                    {
                            return mapping.findForward("forward9");
                    }

 else if(t.equals("10"))
        {
   
System.out.println("1111111111111111111111   "+biblist.size());

            for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("Leader")){

                     request.setAttribute("Leader", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("001")){

                     request.setAttribute("001", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("003")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("003", biblist.get(i));
                    }
                 if(biblist.get(i).getId().getMarctag().equals("005")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("005", biblist.get(i));
                    }
                      if(biblist.get(i).getId().getMarctag().equals("007")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("007", biblist.get(i));
                    }
                     if(biblist.get(i).getId().getMarctag().equals("008")){
                      // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
                     request.setAttribute("008", biblist.get(i));
                    }
            }
            return mapping.findForward("forward10");
        }
                        
                  
                System.out.println("3333333333333    "+biblist.size());
                for(int i=0;biblist.size()>i;i++)
            {
                   if(biblist.get(i).getId().getMarctag().equals("600")){

                     request.setAttribute("600", biblist.get(i));
                    }
                   if(biblist.get(i).getId().getMarctag().equals("650")){

                     request.setAttribute("650", biblist.get(i));
                    }

            }
                    return mapping.findForward("forward6");
     }
        
}
