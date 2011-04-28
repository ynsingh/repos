/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.*;
import com.myapp.struts.hbm.*;
import com.myapp.struts.systemsetupDAO.BookCategoryDAO;
/**
 *
 * @author edrp01
 */
public class updatebookAction extends org.apache.struts.action.Action {
    
    
    String library_id,button,book_type,emptype_id,subemptype_id,full_name,issue_days,fine;
boolean result;
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
     BookCategoryDecideActionForm1 bcda  =(BookCategoryDecideActionForm1)form;
     HttpSession session=request.getSession();
        button=bcda.getButton();
        book_type=bcda.getBook_type();
        emptype_id=bcda.getEmptype_id();
        subemptype_id=bcda.getSub_emptype_id();

        library_id=(String)session.getAttribute("library_id");
        full_name=bcda.getFull_name();
        issue_days=bcda.getPermitday();
        fine=bcda.getFineRs()+"."+bcda.getFinePs();



/*get the id of member and sub member type*/

EmployeeType memtype=BookCategoryDAO.searchMemTypeId(library_id,emptype_id);
SubEmployeeType submemtype=BookCategoryDAO.searchSubMemTypeId(library_id,subemptype_id);

if(memtype!=null)
    emptype_id=memtype.getId().getEmptypeId();

if(submemtype!=null)
    subemptype_id=submemtype.getId().getSubEmptypeId();

System.out.println(button+"....................Emp Type.........."+emptype_id+"...SubEmp........."+subemptype_id+"...Doc Type-.    "+book_type);

        if(button.equalsIgnoreCase("Update"))
        {

           BookCategory book=(BookCategory)BookCategoryDAO.searchBookTypeDetails(library_id, emptype_id, subemptype_id, book_type);
           System.out.println(book);
           if(book!=null){

           book.setFine(Float.parseFloat(fine));
           book.setIssueDaysLimit(Integer.parseInt(issue_days));
           book.setDetail(full_name);
           result=BookCategoryDAO.update(book);
           if(result==true){
               request.setAttribute("msg", "Record Successfully Updated");

            return mapping.findForward("success");
           }


           }
           
        }
        if(button.equalsIgnoreCase("Delete"))
        {

           BookCategory book=(BookCategory)BookCategoryDAO.searchBookTypeDetails(library_id, emptype_id, subemptype_id, book_type);
          
           if(book!=null)
           {
            result=BookCategoryDAO.DeleteBook(library_id,emptype_id,subemptype_id,book_type);
            if(result==true)
            {
                  request.setAttribute("msg", "Record Successfully Deleted");
           
                return mapping.findForward("success");
            }
           }
           else{
               request.setAttribute("msg",book_type + "not Found");
           return mapping.findForward("failure");

           }

        }
     return null;
    }
}
