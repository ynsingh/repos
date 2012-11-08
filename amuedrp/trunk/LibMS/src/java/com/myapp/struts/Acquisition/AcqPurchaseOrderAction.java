/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.AcqOrderDao;
import com.myapp.struts.hbm.*;
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
public class AcqPurchaseOrderAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
   AcquisitionDao acqdao=new AcquisitionDao();
   AcqOrderDao acqorderdao=new AcqOrderDao();
   
    String title;
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         AcqPurchaseOrderActionForm acqprchase=(AcqPurchaseOrderActionForm)form;
         HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String order_no=acqprchase.getOrder_no();
        
        String ship_cmpny_name=acqprchase.getShip_company1()==null?"":acqprchase.getShip_company1();
        String ship_address=acqprchase.getShip_address1()==null?"":acqprchase.getShip_address1();
        String ship_pin=acqprchase.getShip_pin1()==null?"":acqprchase.getShip_pin1();
        String ship_fax=acqprchase.getShip_fax1()==null?"":acqprchase.getShip_fax1();
        String ship_email=acqprchase.getShip_email1()==null?"":acqprchase.getShip_email1();
        String discount=acqprchase.getDiscount()==null?"":acqprchase.getDiscount();
        String ship_cost=acqprchase.getShip_cost()==null?"":acqprchase.getShip_cost();
        String other_cost=acqprchase.getOther_cost()==null?"":acqprchase.getOther_cost();
        String tax_rate=acqprchase.getTax_rate()==null?"":acqprchase.getTax_rate();
        String tax_amount=acqprchase.getTax_amount()==null?"":acqprchase.getTax_amount();
        String grand_total=acqprchase.getGrand_total()==null?"":acqprchase.getGrand_total();
        String shipping_method=acqprchase.getShip_method()==null?"":acqprchase.getShip_method();
        String shipping_terms=acqprchase.getShip_terms()==null?"":acqprchase.getShip_terms();
        String comment=acqprchase.getNotes()==null?"":acqprchase.getNotes();
        String ship_contact_person=acqprchase.getShip_contact_person1()==null?"":acqprchase.getShip_contact_person1();
        String button=acqprchase.getButton();


        AcqOrderHeader acqorderheader=(AcqOrderHeader)acqdao.searchOrderHeaderByOrderNo(library_id,sub_library_id,order_no);

System.out.println(discount+"  "+ship_cost+" "+other_cost+ " "+tax_rate+" "+tax_amount+" "+grand_total+" "+comment+ship_cmpny_name+ship_address);
   
                      
                        
                         acqorderheader.setShipContactName(ship_contact_person);
                         acqorderheader.setShipCompanyName(ship_cmpny_name);
                         acqorderheader.setShipAddress(ship_address);
                         acqorderheader.setShipPin(ship_pin);
                         acqorderheader.setShipFax(ship_fax);
                         acqorderheader.setShipEmail(ship_email);
                         acqorderheader.setDiscount(discount);
                         acqorderheader.setShipCost(ship_cost);
                         acqorderheader.setOtherCost(other_cost);
                         acqorderheader.setTaxRate(tax_rate);
                         acqorderheader.setTaxAmount(tax_amount);
                         acqorderheader.setGrandTotal(grand_total);
                         acqorderheader.setShippingMethod(shipping_method);
                         acqorderheader.setShippingTerms(shipping_terms);
                         
                    
                         acqorderheader.setComments(comment);
                  boolean result=  acqorderdao.updateAcqOrderHeader(acqorderheader);
             if(result==true){
             String msg="Order Successfully processed";
              request.setAttribute("button", button);
             request.setAttribute("msg", msg);
             }
             else
             {
             String msg="Order cannot  processed Successfully";
             request.setAttribute("msg1", msg);
             }


         return mapping.findForward(SUCCESS);
    }
}
