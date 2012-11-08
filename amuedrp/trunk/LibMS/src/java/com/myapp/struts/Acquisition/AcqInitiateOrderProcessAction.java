
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.AcqOrderDao;
import com.myapp.struts.AcquisitionDao.AcqApprovalDao;
import com.myapp.struts.AcquisitionDao.BudgetDAO;
import com.myapp.struts.hbm.*;
import java.util.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author maqbool
 */
public class AcqInitiateOrderProcessAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcquisitionDao ado=new AcquisitionDao();
    AcqOrderDao aordrdao=new AcqOrderDao();
    
    String delimeter1=";";
    String delimeter2=",";
    String items[];
    String title;  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        AcqOrderManagementActionForm aomd=(AcqOrderManagementActionForm)form;
        String order_no=aomd.getOrder_no();
        String order_date=aomd.getOrder_date();
        String due_date=aomd.getDue_date();
        String ordered_by=aomd.getOrdered_by();
        String order_status=aomd.getOrder_status();
        String price=aomd.getPrice();
        String discount=aomd.getDiscount();
        String vendor_id=aomd.getVendor();
        String button=aomd.getButton();
        String list=aomd.getList();
        
        System.out.println("list="+list);
        ArrayList<AcqBibliographyDetails> order=new ArrayList<AcqBibliographyDetails>();
            ArrayList<ApprovalList> approval=new ArrayList<ApprovalList>();
         ArrayList<Integer> l2=new ArrayList<Integer>();
         ArrayList<String> l3=new ArrayList<String>();
        items=list.split(delimeter1);
                for(int i=0;i<items.length;i++)
                {
                    System.out.println("##########"+items.length);
                    String doc[]=items[i].split(delimeter2);
                    
                      if(doc.length>1)
                      l3.add(doc[1]);
                    
                    l2.add(Integer.parseInt(doc[0]));



                }
        if(!l2.isEmpty()){
             AcqOrderHeaderId aordrhid=new AcqOrderHeaderId();
                AcqOrderHeader aordrh=new AcqOrderHeader();
             aordrhid.setLibraryId(library_id);
                aordrhid.setSubLibraryId(sub_library_id);
                System.out.println("Order NO::::::::"+order_no);
                aordrhid.setOrderNo(order_no);
                aordrh.setId(aordrhid);
                aordrh.setOrderDate(order_date);
                aordrh.setDueDate(due_date);
                aordrh.setOrderStatus("Placed");
                aordrh.setVendorId(vendor_id);





        String ship_cmpny_name="";
        String ship_address="";
        String ship_pin="";
        String ship_fax="";
        String ship_email="";
        discount="0";
        String ship_cost="0";
        String other_cost="0";
        String tax_rate="0";
        String tax_amount="0";
        String grand_total="0";
        String shipping_method="";
        String shipping_terms="";
        String comment="";
        String ship_contact_person="";






                         aordrh.setShipContactName(ship_contact_person);
                         aordrh.setShipCompanyName(ship_cmpny_name);
                         aordrh.setShipAddress(ship_address);
                         aordrh.setShipPin(ship_pin);
                         aordrh.setShipFax(ship_fax);
                         aordrh.setShipEmail(ship_email);
                         aordrh.setDiscount(discount);
                         aordrh.setShipCost(ship_cost);
                         aordrh.setOtherCost(other_cost);
                         aordrh.setTaxRate(tax_rate);
                         aordrh.setTaxAmount(tax_amount);
                         aordrh.setGrandTotal(grand_total);
                         aordrh.setShippingMethod(shipping_method);
                         aordrh.setShippingTerms(shipping_terms);


                         aordrh.setComments(comment);
                  aordrdao.insert(aordrh);
                     System.out.println("JJJJJJJJJJJJJJJJJJJJJJ");
                  session.setAttribute("orderheader", aordrh);
            for(int h=0;h<l2.size();h++){
            try
            {
                int con_no=l2.get(h);
                System.out.println("Control No"+con_no+"List3..."+h);
                String approval_no="";
                  AcqBibliographyDetails acqbib1=aordrdao.searchByControlNo(library_id, sub_library_id,con_no);
                  if(acqbib1.getAcqMode().equalsIgnoreCase("Firm Order")==false)
                  {
                    if(!l3.isEmpty())
                    {
                   approval_no=l3.get(h);
                   

                    }
                System.out.println("Approval No"+approval_no);
                  }

                AcqOrder1Id acqordr1id=new AcqOrder1Id();
                AcqOrder1 acqordr1=new AcqOrder1();             
                acqordr1id.setLibraryId(library_id);
                acqordr1id.setSubLibraryId(sub_library_id);
                acqordr1id.setOrderNo(order_no);
                acqordr1.setAcqOrderHeader(aordrh);
                acqordr1.setControlNo(con_no);              
                Integer maxitemid=aordrdao.returnMaxItemIdAcqOrder(library_id, sub_library_id);
                System.out.println(maxitemid+"..............");
                acqordr1id.setOrderItemId(maxitemid.toString());
                acqordr1.setId(acqordr1id);


                //get the approval item id from acq_approval table based on control no
                String acq=aordrdao.getApprovalItemId(library_id,sub_library_id,con_no,approval_no);


            if(acq!=null)
            {     acqordr1.setApprovalItemId(Integer.parseInt(acq));

            }
            else
                  acqordr1.setApprovalItemId(0);




                aordrdao.insert1(acqordr1);
                //update the list and set firm-order status to ordered
            

                AcqBibliographyDetails acqbib=aordrdao.searchByControlNo(library_id, sub_library_id,con_no);
                System.out.println(acqbib+"........"+con_no);
                if(acqbib.getAcqMode().equalsIgnoreCase("Firm Order")==false)
                    {
                   AcqApproval ac=ado.searchApproval(library_id, sub_library_id, approval_no,con_no);

                    ac.setStatus("Ordered");
                    ac.setOrderNo(order_no);
                    aordrdao.updateAcqApproval(ac);
                
                
                List<AcqApproval> ac1=ado.searchApprovalStatus(library_id, sub_library_id, con_no);
                System.out.println("Pending List....................."+ac1.size());

            

                if(!acqbib.getStatus().equalsIgnoreCase("Partially Approved") && ac1.isEmpty())
                        acqbib.setStatus("Ordered");
               //if(!acqbib.getAcqMode().equalsIgnoreCase("On Approval") && ac1.isEmpty())
               // acqbib.setAcqMode("Ordered");


                approval.add(ado.getApprovalListbySelectionA(library_id, sub_library_id,approval_no,con_no));
                }
                else if(acqbib.getAcqMode().equalsIgnoreCase("Firm Order")==true){
                approval.add(ado.getApprovalListbySelectionF(library_id, sub_library_id,acqbib.getId().getControlNo()));
            

                acqbib.setStatus("Ordered");
              
               // acqbib.setAcqMode("Ordered");

                }
                
aordrdao.updateAcqBibliographyDetails(acqbib);

                


               

                 request.setAttribute("maxitemid", maxitemid.toString());
                  System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
          
            

            }
            catch(Exception e1)
            {
            System.out.println("SerialFinalDemandAction111:"+e1+"*******************");
            }
            }
        }
       
        request.setAttribute("list", list);
System.out.println("Total Size"+approval.size());
session.setAttribute("listordered", approval);
       String msg="Record is Ordered successfully :";
        request.setAttribute("msg", msg);
        return mapping.findForward(SUCCESS);
    }
}
