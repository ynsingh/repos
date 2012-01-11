/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class ActivityAdd1Action extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    List<DeliquencyReason> actl;

    DeliquencyReason act=new DeliquencyReason();
    DeliquencyReasonId actid=new DeliquencyReasonId();
    CirculationDAO dao=new CirculationDAO();
    String id;
    String details;


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            {
        ActivityActionForm actf=(ActivityActionForm)form;
        HttpSession session = request.getSession();
        String        library_id=(String)session.getAttribute("library_id");
        String sublibrary_id=(String)session.getAttribute("sublibrary_id");
       
       
       id=actf.getId();
       details=actf.getDetails();


             DeliquencyReason obj=CirculationDAO.searchDeliReason(library_id,sublibrary_id,id.toString());
           if(obj!=null){
           request.setAttribute("msg1", "Deliquency  Id Already Exist");
             session.setAttribute("actlist", CirculationDAO.searchDelReason(library_id, sublibrary_id));
          return mapping.findForward(SUCCESS);

           }
           else{

        act.setDetails(details.toString());

       actid.setId(id.toString());

       actid.setLibraryId(library_id);
       actid.setSublibraryId(sublibrary_id);
       act.setId(actid);

       System.out.println("Boooolean"+CirculationDAO.insertDelinquencyout(act));
       CirculationDAO.insertDelinquencyout(act);
       
      session.setAttribute("actlist", CirculationDAO.searchDelReason(library_id, sublibrary_id));
      return mapping.findForward(SUCCESS);
           }
            
    }
}
