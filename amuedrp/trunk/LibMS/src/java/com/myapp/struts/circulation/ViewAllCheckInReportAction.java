/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.circulation;
import com.myapp.struts.CirDAO.*;
import com.myapp.struts.hbm.*;
import com.myapp.struts.opacDAO.CirRequestfromOpacDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp01
 */
public class ViewAllCheckInReportAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
   
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirRequestfromOpacDAO ciropacdao=new CirRequestfromOpacDAO();
           CirCheckInReportActionForm ccra   =(CirCheckInReportActionForm)form;
           HttpSession session=request.getSession();
           String  library_id=(String)session.getAttribute("library_id");
            String  sub_lib=(String)session.getAttribute("sublibrary_id");
            String memid =ccra.getMemid();
          
           String year1=ccra.getStarting_date();
            String year2=ccra.getEnd_date();
        
         
         //List circheckInlist1=(List)CirculationDAO.CheckInReport(library_id, sub_lib, year1, year2, memid);
         //session.setAttribute("circheckInlist1", circheckInlist1);
         //System.out.println("@@@@@@@@@@@@+"+circheckInlist1.size());

           List<CheckInDocumentDetails>  requestList=null;



requestList = (List<CheckInDocumentDetails>)ciropacdao.getCheckIn(library_id, sub_lib, memid, year1, year2);
System.out.println("size="+requestList.size());

session.setAttribute("circheckInlist1", requestList);



        return mapping.findForward(SUCCESS);
    }
}
