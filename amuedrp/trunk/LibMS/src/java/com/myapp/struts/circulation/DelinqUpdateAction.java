package com.myapp.struts.circulation;

import com.myapp.struts.hbm.DeliquencyReason;
import com.myapp.struts.hbm.DeliquencyReasonId;
import java.util.Arrays;
import java.util.LinkedList;
//import java.util.List;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.CirDAO.CirculationDAO;
//import com.myapp.struts.activityDao.ActivityDAO;
/**
 *
 * @author EdRP-05
 */
public class DelinqUpdateAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    List<DeliquencyReason> actl;

    DeliquencyReason act=new DeliquencyReason();
    DeliquencyReasonId actid=new DeliquencyReasonId();
    CirculationDAO dao=new CirculationDAO();
    LinkedList id;
    LinkedList details;


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            {
        ActivityActionForm1 actf=(ActivityActionForm1)form;
        HttpSession session = request.getSession();
        String        library_id=(String)session.getAttribute("library_id");
        String sublibrary_id=(String)session.getAttribute("sublibrary_id");

       if(actf.getId()!=null)
       {
           String[] a=actf.getId();
           id=new LinkedList(Arrays.asList(a)) ;
       }
       if(actf.getDetails()!=null)
       {
           String[] b=actf.getDetails();
           details=new LinkedList(Arrays.asList(b));
       }
       System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGG"+id.getFirst().toString());
       System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGG"+details.getFirst().toString());
      actl=(List<DeliquencyReason>)session.getAttribute("actlist");
      if(actf.getButton().equals("Update"))
      {
           System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHH"+details.getFirst().toString());
          for(int i=0;i<actl.size();i++){
       if(actl.get(i).getId().getId().equals(id.getFirst().toString())){
       act.setDetails(details.getFirst().toString());
       details.removeFirst();
       actid.setId(id.getFirst().toString());
       actid.setLibraryId(library_id);
       actid.setSublibraryId(sublibrary_id);
       act.setId(actid);
       dao.insertDelinquencyout(act);
       }
       }
      }
      if(actf.getButton().equals("Delete"))
      {
      dao.deleteDelinq(library_id, sublibrary_id, id.getFirst().toString());
      id.removeFirst();
      }
        session.setAttribute("actlist", dao.searchDelReason(library_id, sublibrary_id));
      return mapping.findForward(SUCCESS);
    }

}