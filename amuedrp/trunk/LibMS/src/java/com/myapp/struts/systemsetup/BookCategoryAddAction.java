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
import java.util.Locale;
import java.util.ResourceBundle;
/**
 *
 * @author edrp01
 */
public class BookCategoryAddAction extends org.apache.struts.action.Action {
    
    
    String library_id,button,book_type,emptype_id,subemptype_id,no_of_issue,full_name;
    Boolean result;
    Float fine;
   Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
     BookCategoryDecideActionForm bcda  =(BookCategoryDecideActionForm)form;
     HttpSession session=request.getSession();
      try{

        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);
        
        book_type=bcda.getBook_type();
        emptype_id=bcda.getEmptype_id();
        subemptype_id=bcda.getSub_emptype_id();

        library_id=(String)session.getAttribute("library_id");

        fine=Float.parseFloat(bcda.getFineRs()+"."+bcda.getFinePs());
        no_of_issue=bcda.getPermitday();
        full_name=bcda.getFull_name();

System.out.println(bcda.getFull_name()+"  "+bcda.getPermitday());

/*get the id of member and sub member type*/

EmployeeType memtype=BookCategoryDAO.searchMemTypeId(library_id,emptype_id);
SubEmployeeType submemtype=BookCategoryDAO.searchSubMemTypeId(library_id,subemptype_id);

if(memtype!=null)
    emptype_id=memtype.getId().getEmptypeId();

if(submemtype!=null)
    subemptype_id=submemtype.getId().getSubEmptypeId();


Library lib=new Library(library_id, "Ok");
EmployeeTypeId empid=new EmployeeTypeId(library_id, emptype_id);
EmployeeType emp=new EmployeeType(empid, lib);
SubEmployeeTypeId subempid=new SubEmployeeTypeId(library_id, emptype_id, subemptype_id);
SubEmployeeType subemp=new SubEmployeeType(subempid, memtype, lib);
BookCategoryId bookId=new BookCategoryId(book_type, library_id, emptype_id, subemptype_id);
BookCategory book=new BookCategory(bookId, subemp);

book.setDetail(full_name);
book.setIssueDaysLimit(Integer.parseInt(no_of_issue));
book.setFine(fine);

result=BookCategoryDAO.insert(book);
if(result==true)
{
  //request.setAttribute("msg","Record Successfully Added");
    request.setAttribute("msg",resource.getString("circulation.circulationnewmemberregAction.recinsesucc"));
return mapping.findForward("success");
}
else{

    // request.setAttribute("msg","Error In Insertion");
    request.setAttribute("msg",resource.getString("circulation.circulationnewmemberregAction.recnotinsesucc"));
return mapping.findForward("failure");
}





           
           


    }
}
