/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.opac;
import  com.myapp.struts.*;
import com.myapp.struts.hbm.DocumentDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import com.myapp.struts.opacDAO.*;
import java.util.List;
/**
 *
 * @author Faraz
 */
public class SearchByIsbnAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    OpacSearchDAO osdao= new OpacSearchDAO();
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
    
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        
        SearchByIsbnActionForm myForm = (SearchByIsbnActionForm)form;
        String lib_id=myForm.getCMBLib();
        String   isbn = myForm.getTXTKEY();
        String sublib=myForm.getCMBSUBLib();
        if (session.getAttribute("Result")!=null) session.removeAttribute("Result");
      List documentdetail  =(List)osdao.isbnSearch(isbn, lib_id, sublib);
      //  query = "select * from document_details where isbn10='" + isbn + "'";
      //  if(!lib_id.equals("all"))
        //     query +=" and library_id='" + lib_id + "'";

       // rst = MyQueryResult.getMyExecuteQuery(query);
        //session.setAttribute("Result", rst);
        session.setAttribute("documentdetail", documentdetail);
        return mapping.findForward(SUCCESS);
    }
}
