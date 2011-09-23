/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Customizedbiblio;
import com.myapp.struts.hbm.CustomizedbiblioId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class CustomMarcAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    private NewMARCDAO marcdao=new NewMARCDAO();

    private Customizedbiblio biblio=new Customizedbiblio();
    private CustomizedbiblioId biblioid= new CustomizedbiblioId();
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

        int i;
        System.out.println("inside Custom MArc action");

        CustomMarcActionForm cmaf=(CustomMarcActionForm)form;
               String subfieldvalue[]=cmaf.getSub_names();
        String marctags[]=cmaf.getMarctags();
        char ind1[] = cmaf.getInd1();
        char ind2[] = cmaf.getInd2();
        String t =cmaf.getZclick();

         HttpSession session = request.getSession();
      int  bibid = (Integer)session.getAttribute("biblio_id");

        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String tagsymbols[]=cmaf.getAllmarc();
    List<String> list2 = new ArrayList<String>(tagsymbols.length);
     
        list2=Arrays.asList(tagsymbols);
System.out.println("IIIIIIIIIIIIINNNNNNNNNNNNNNNNNNNNN"+list2.toString());

        System.out.println("allmarc ki length = "+tagsymbols.length);

  ArrayList<Integer> sr=new ArrayList();
  ArrayList<List> jr=new ArrayList();
//String bb=null;
//        for(int pg=0;pg<list2.size();pg++){
//
//            //  bb.concat(list2.get(pg).toString());
//System.out.println("bb = "+bb);
//        }
//System.out.println("bb = "+bb);
//int index = 0;
//while((index = list2.toString().indexOf("a", index)) != -1) {
//    index = list2.toString().indexOf("a", index);
//    sr.add(index);
//    index++;
//        }
for( i=0;i<list2.size();i++){
    if(list2.get(i).equals("a")){
        sr.add(i);
        
    }
}
  System.out.println("SSSSSSSSSSSSSSSSSSSSSSr"+sr.toString());
  if(sr.size()==1){
  jr.add(list2.subList(sr.get(0), list2.size()));
  }
  else{
  for(int b=0;b<sr.size();b++){
    System.out.println("Senior List := "+sr.get(b));
  
    int bb=sr.lastIndexOf(sr.get(b));
    System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL"+sr.size());
   System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTT"+bb);
    if(bb==sr.size()-1)
    {
        jr.add(list2.subList(sr.get(b), list2.size()));
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"+sr.get(b-1));
   }
    else
    {
        jr.add(list2.subList(sr.get(b), sr.get(b+1)));
    }
    System.out.println("Junior List =  "+jr.toString());
}
        }

        for(i=0;i<tagsymbols.length;i++){
            System.out.println("allmarc= "+tagsymbols[i]);
        }

        for(i=0;i<subfieldvalue.length;i++){
            System.out.println("subnames= "+subfieldvalue[i]);
        }
     int j=0; int k=0;
        for(i=0;i<marctags.length;i++){
            System.out.println("000000000000000000000000000000000"+tagsymbols[0].charAt(0));
            biblioid.setLibraryId(library_id);
            biblio.setSublibraryId(sub_library_id);
            biblioid.setBibId(bibid);
            biblioid.setMarctag(marctags[i]);
            biblio.setIndicator1(ind1[i]);
            biblio.setIndicator2(ind2[i]);


                for(j=0;j<jr.get(i).size();j++)
                { System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+jr.get(i).get(j).toString().charAt(0)+" ########  "+subfieldvalue[k]);
                       switch(jr.get(i).get(j).toString().charAt(0))
                       {
                          case 'a':
                                biblio.set$a(subfieldvalue[k]);
                                    break;
                           case 'b':
                               biblio.set$b(subfieldvalue[k]);
                               break;
                           case 'c':
                                biblio.set$c(subfieldvalue[k]);
                                    break;
                           case 'd':
                               biblio.set$d(subfieldvalue[k]);
                               break;
                           case 'e':
                                biblio.set$e(subfieldvalue[k]);
                                    break;
                           case 'f':
                               biblio.set$f(subfieldvalue[k]);
                               break;
                           case 'g':
                                biblio.set$g(subfieldvalue[k]);
                                    break;
                           case 'h':
                               biblio.set$h(subfieldvalue[k]);
                               break;
                           case 'i':
                                biblio.set$i(subfieldvalue[k]);
                                    break;
                           case 'j':
                               biblio.set$j(subfieldvalue[k]);
                               break;
                          case 'k':
                                biblio.set$k(subfieldvalue[k]);
                                    break;
                           case 'l':
                               biblio.set$l(subfieldvalue[k]);
                               break;
                           case 'm':
                                biblio.set$m(subfieldvalue[k]);
                                    break;
                           case 'n':
                               biblio.set$n(subfieldvalue[k]);
                               break;
                           case 'o':
                                biblio.set$o(subfieldvalue[k]);
                                    break;
                           case 'p':
                               biblio.set$p(subfieldvalue[k]);
                               break;
                           case 'q':
                                biblio.set$q(subfieldvalue[k]);
                                    break;
                           case 'r':
                               biblio.set$r(subfieldvalue[k]);
                               break;
                           case 's':
                                biblio.set$s(subfieldvalue[k]);
                                    break;
                           case 't':
                               biblio.set$t(subfieldvalue[k]);
                               break;
                           case 'u':
                                biblio.set$u(subfieldvalue[k]);
                                    break;
                           case 'v':
                               biblio.set$v(subfieldvalue[k]);
                               break;
                           case 'w':
                                biblio.set$w(subfieldvalue[k]);
                                    break;
                           case 'x':
                               biblio.set$x(subfieldvalue[k]);
                               break;
                           case 'y':
                                biblio.set$y(subfieldvalue[k]);
                                    break;
                           case 'z':
                               biblio.set$z(subfieldvalue[k]);
                               break;
                           case '0':
                                biblio.set$0(subfieldvalue[k]);
                                    break;
                           case '1':
                               biblio.set$1(subfieldvalue[k]);
                               break;
                           case '2':
                                biblio.set$2(subfieldvalue[k]);
                                    break;
                           case '3':
                               biblio.set$3(subfieldvalue[k]);
                               break;
                           case '4':
                                biblio.set$4(subfieldvalue[k]);
                                    break;
                           case '5':
                               biblio.set$5(subfieldvalue[k]);
                               break;
                           case '6':
                                biblio.set$6(subfieldvalue[k]);
                                    break;
                           case '7':
                               biblio.set$7(subfieldvalue[k]);
                               break;
                           case '8':
                                biblio.set$8(subfieldvalue[k]);
                                    break;
                           case '9':
                               biblio.set$9(subfieldvalue[k]);
                               break;
                            default:
                           
                       }
                       k++;
                  
                }


      
  biblio.setId(biblioid);
           marcdao.insertBiblio(biblio);
          biblio=new Customizedbiblio();
          //insert


    System.out.println("object saved");
        }

System.out.println("t value is = "+t);
        //code for mapping forwards......
         if(t.equals("1"))
        {
              List<String> editmarc11 = marcdao.show1();
           request.setAttribute("tagno", editmarc11);
            return mapping.findForward("forward1");
        }
        else if(t.equals("2")){
              List<String> editmarc11 = marcdao.show2();
           request.setAttribute("tagno", editmarc11);
                return mapping.findForward("forward2");
        }
        else if(t.equals("3")){
              List<String> editmarc11 = marcdao.show3();
           request.setAttribute("tagno", editmarc11);
                return mapping.findForward("forward3");
        }
        else if(t.equals("4")){
              List<String> editmarc11 = marcdao.show4();
           request.setAttribute("tagno", editmarc11);
                return mapping.findForward("forward4");
        }
        else if(t.equals("5")){
              List<String> editmarc11 = marcdao.show5();
           request.setAttribute("tagno", editmarc11);
                return mapping.findForward("forward5");
        }
        else if(t.equals("6")){
              List<String> editmarc11 = marcdao.show6();
           request.setAttribute("tagno", editmarc11);
                return mapping.findForward("forward6");
        }
        else if(t.equals("7")){
              List<String> editmarc11 = marcdao.show7();
           request.setAttribute("tagno", editmarc11);
                return mapping.findForward("forward7");
        }
        else if(t.equals("8")){
              List<String> editmarc11 = marcdao.show8();
           request.setAttribute("tagno", editmarc11);
                return mapping.findForward("forward8");
        }
        else if(t.equals("9"))
        {
              List<String> editmarc11 = marcdao.show9();
           request.setAttribute("tagno", editmarc11);
        return mapping.findForward("forward9");
        }

         List<String> editmarc11 = marcdao.show();
           request.setAttribute("tagno", editmarc11);
       return mapping.findForward("forward0");
    }
}
