/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.CirDAO.CirculationDAO;
import java.util.Locale;

/**
 *
 * @author edrp02
 */
public class SearchCallnoForBookAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String call_no;
    String library_id;
    String sublibrary_id;
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

     
        HttpSession session=request.getSession();

          String call_no = request.getParameter("getCallNo");
       
        library_id=(String)session.getAttribute("library_id");
        sublibrary_id=(String)session.getAttribute("sublibrary_id");
        


         
        String document_category=getCallNo(library_id, sublibrary_id, call_no);
    
       

       




         if (!document_category.equals("") && !document_category.equals("null"))
        {
        response.setContentType("application/xml");
        response.getWriter().write(document_category);

        }
        return null;
      
       
    }
    public String getCallNo (String library_id,String sublibrary_id,String call_no) {
   CirculationDAO cirdao=new CirculationDAO();

        StringBuffer dept_ids = new StringBuffer();

try {
 String document_category=cirdao.DistinctDocType(library_id, sublibrary_id, call_no);


dept_ids.append("<course_ids>");
if (!document_category.equals("") && !document_category.equals("null"))
{
   dept_ids.append("<course_id>"+document_category+"</course_id>");


}
dept_ids.append("</course_ids>");
}

catch(Exception se) {
se.printStackTrace();
}
finally {
try {

}
catch(Exception e) {
e.printStackTrace();
}
}
return dept_ids.toString();



}


}
