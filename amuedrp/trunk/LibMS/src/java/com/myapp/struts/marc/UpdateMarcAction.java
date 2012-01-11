/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.marc;

import com.myapp.struts.hbm.Biblio;
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
public class UpdateMarcAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    MarcHibDAO mhd=new MarcHibDAO();
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
        UpdateMarcActionForm uma=(UpdateMarcActionForm) form;
       // String bibid=uma.getBib_id();
        String bibid=request.getParameter("id");
System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"+bibid);
        HttpSession session = request.getSession();
         session.setAttribute("biblio_id", bibid);
               String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
//         List<Biblio> biblist= mhd.getdataforupdate1(bibid,"082");
//System.out.println("BBBBGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+biblist.size());
//       //  int i=0;
//            for(int i=0;biblist.size()>i;i++){
//               if(biblist.get(i).getId().getMarctag().equals("020")){
//                  
//                 request.setAttribute("020", biblist.get(i));
//                }
//               if(biblist.get(i).getId().getMarctag().equals("022")){
//                 
//                 request.setAttribute("022", biblist.get(i));
//                }
//               if(biblist.get(i).getId().getMarctag().equals("041")){
//                  // System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
//                 request.setAttribute("041", biblist.get(i));
//                }
//               if(biblist.get(i).getId().getMarctag().equals("043")){
//                    //System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
//                 request.setAttribute("043", biblist.get(i));
//                }
//               
//               if(biblist.get(i).getId().getMarctag().equals("082")){
////                    System.out.println("^^^^^^^^^^^^^ "+biblist.get(i).get$a());
//                 request.setAttribute("082", biblist.get(i));
//                }
//            //   i++;
//            }
     List<Biblio> biblist= mhd.getdataforupdate(bibid, library_id, sub_library_id);
System.out.println("1111111111111111111111   "+biblist.size());

            for(int i=0;i<biblist.size();i++)
            {
                System.out.println(biblist.get(i).getId().getMarctag());
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
       return mapping.findForward(SUCCESS);
        }
    }

