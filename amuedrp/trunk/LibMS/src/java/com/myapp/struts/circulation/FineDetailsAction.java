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
import java.lang.*;
import java.util.ArrayList;

/**
 *
 * @author edrp01
 */
public class FineDetailsAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";



    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CirRequestfromOpacDAO ciropacdao=new CirRequestfromOpacDAO();
        CirculationDAO cirdao=new CirculationDAO();
        CirCheckInReportActionForm ccra   =(CirCheckInReportActionForm)form;

           HttpSession session=request.getSession();
           String  library_id=(String)session.getAttribute("library_id");
            String  sub_lib=(String)session.getAttribute("sublibrary_id");
            String memid =ccra.getMemid();
session.setAttribute("mem",memid);
           String year1=ccra.getStarting_date();
            String year2=ccra.getEnd_date();


    
           List<CheckInDocumentDetails>  requestList=null;
List<FineDetails> finedetaillist=null;


requestList = (List<CheckInDocumentDetails>)ciropacdao.getCheckIn(library_id, sub_lib, memid, year1, year2);
  finedetaillist=(List<FineDetails>)cirdao.getfinedetailslist(library_id, sub_lib, memid);
System.out.println("size===="+requestList.size());
//FineDetails fd=CirculationDAO.getfinedetails(library_id, sub_lib, memid);

double Totalfine=0.0;
double paid=0.0;
for(int i=0;i<requestList.size();i++){
CheckInDocumentDetails d=requestList.get(i);
Totalfine=Totalfine+Double.parseDouble(d.getCirTransactionHistory().getFineAmt().toString());


System.out.println(" fine===="+Totalfine);
    
   }


for(int i=0;i<finedetaillist.size();i++){
FineDetails d=finedetaillist.get(i);
paid=paid+Double.parseDouble(d.getPaid().toString());


System.out.println(" pfine===="+paid);

   }
System.out.println("Total fine========="+Totalfine+" pfine===="+paid);
//System.out.println(" finepaid===="+fd.getPaid());
ccra.setTotalfine(Totalfine);


session.setAttribute("finedetails", requestList);
session.setAttribute("finedetaillist",finedetaillist);
session.setAttribute("fine", Totalfine);
session.setAttribute("paid", paid);
//session.setAttribute("fdi", fd);




        return mapping.findForward(SUCCESS);
    }
}
