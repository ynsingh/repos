package com.myapp.struts.circulation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpSession;
import com.myapp.struts.CirDAO.CirculationDAO;
import com.myapp.struts.hbm.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.myapp.struts.utility.Email;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.List;
public class RemindAllAction extends org.apache.struts.action.Action {
    
    private static final String SUCCESS = "success";
    Email obj;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {

        HttpSession session=request.getSession();
        CirculationDAO cirdao=new CirculationDAO();
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat formatter=
            new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = formatter.format(cal.getTime());
        String library_id=(String)session.getAttribute("library_id");
        String sublibrary_id=(String)session.getAttribute("sublibrary_id");
        List reminderlist=cirdao.ReminderList(library_id,sublibrary_id,dateNow);
        for(int i=0;i<reminderlist.size();i++)
        {
             CirculationList obj1=(CirculationList)reminderlist.get(i);
             String msg="Reminder for Book Return from Librarian" ;
             obj=new Email(obj1.getEmail(),"",msg,"\n\nDear "+obj1.getFname()+" "+obj1.getLname()+",\n Your Check out date for the book is over.\n You are requested to return book as soon as possible\nThanks\nAdmin\nLibMS");
             obj.send();
        }
        request.setAttribute("msg", "Mail Sent Successfully");
        return mapping.findForward(SUCCESS);
}
}
