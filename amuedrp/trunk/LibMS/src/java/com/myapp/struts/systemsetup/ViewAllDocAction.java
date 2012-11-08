/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.systemsetup;

import com.myapp.struts.circulation.FineDetailGrid;
import com.myapp.struts.systemsetupDAO.BookCategoryDAO;
import com.myapp.struts.hbm.BookCategory;
import com.myapp.struts.hbm.BookCategoryId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author EdRP-05
 */
public class ViewAllDocAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BookCategoryDAO bookdao=new BookCategoryDAO();

        HttpSession session=request.getSession();
        String     library_id=(String)session.getAttribute("library_id");
         String     sub_lib=(String)session.getAttribute("sublibrary_id");
                List<FineDetailGrid> ll=bookdao.ListbookType1(library_id);
       /* ArrayList list2=new ArrayList();
        System.out.println(ll.size());
        Iterator it = ll.iterator();
          while(it.hasNext()){
          String book_type = (String)obj.get;
          String fine=(String)String.valueOf(obj.get(1));
          String limit=(String)String.valueOf(obj.get(2));
          String emptype=(String)obj.get(3);
          String subemp=(String)obj.get(4);

         
          BookCategory book=new BookCategory();
          BookCategoryId bookid=new BookCategoryId();
          bookid.setBookType(String.valueOf(book_type));
         book.setFine(Float.parseFloat(fine));
          book.setIssueDaysLimit(Integer.parseInt(limit));
          bookid.setEmptypeId(emptype);
          bookid.setSubEmptypeId(subemp);
          

          book.setId(bookid);
          list2.add(book);
         }
      */
             session.setAttribute("finelist", ll);
        return mapping.findForward(SUCCESS);
    }
}
