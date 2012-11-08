/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcqOrderDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author edrp02
 */
public class AcqReceiveItem3Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    String recieving_no,recieved_date,recieved_by,no_of_copies,recieved_copies,recieved_now,pending_copies,control_no,title_id,unit_price,acq_mode,vendor_id,order_no;
    AcqRecievingDetails acqrecdetails=new AcqRecievingDetails();
    AcqRecievingDetailsId  acqrecdetailsid=new AcqRecievingDetailsId();
    AcqRecievingHeader acqrecheader=new AcqRecievingHeader();
    AcqRecievingHeaderId  acqrecheaderid=new AcqRecievingHeaderId();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqReceiveItemActionForm ariaf=(AcqReceiveItemActionForm)form;
            AcquisitionDao acqdao=new AcquisitionDao();
        recieving_no=ariaf.getRecieving_no();
        recieved_date=ariaf.getRecieved_date();
        recieved_by=ariaf.getRecieved_by();
        no_of_copies=ariaf.getNo_of_copies();
        int n=Integer.parseInt(no_of_copies);
        recieved_copies=ariaf.getRecieved_copies();
        int pr=Integer.parseInt(recieved_copies);
        recieved_now=ariaf.getRecieved_now();
        int r=Integer.parseInt(recieved_now);
        pending_copies=ariaf.getPending_copies();
        int p=Integer.parseInt(pending_copies);
        control_no=ariaf.getControl_no();
        int con_no=Integer.parseInt(control_no);
        int c=Integer.parseInt(control_no);
        title_id=ariaf.getTitle_id();
        int t=Integer.parseInt(title_id);
        unit_price=ariaf.getUnit_price();
        acq_mode=ariaf.getAcq_mode();
        vendor_id=ariaf.getVendor_id1();
        order_no=ariaf.getOrder_no1();
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date date = new Date();
        Timestamp time =new Timestamp(date.getTime());
       


        AcqRecievingHeader acqrec= acqdao.searchByRecievingNo(library_id, sub_library_id, recieving_no);
        if(acqrec==null)
        {
          acqrecheaderid.setLibraryId(library_id);
          acqrecheaderid.setSubLibraryId(sub_library_id);
          acqrecheaderid.setRecievingNo(recieving_no);
          acqrecheader.setId(acqrecheaderid);
          acqrecheader.setVendorId(vendor_id);
          acqrecheader.setOrderNo(order_no);
          acqrecheader.setRecievedDate(recieved_date);
          acqrecheader.setRecievedBy(recieved_by);
        }

//        if(acqrec!=null)
//        {
//          if(!acqrec.getOrderNo().equals(order_no))
//          {
//            acqrecheaderid.setLibraryId(library_id);
//            acqrecheaderid.setSubLibraryId(sub_library_id);
//            acqrecheaderid.setRecievingNo(recieving_no);
//            acqrecheader.setId(acqrecheaderid);
//            acqrecheader.setVendorId(vendor_id);
//            acqrecheader.setOrderNo(order_no);
//            acqrecheader.setRecievedDate(recieved_date);
//            acqrecheader.setRecievedBy(recieved_by);
//          }
//       }

//        AcqRecievingDetails acqrdetails=AcquisitionDao.searchRecievingDetails(library_id, sub_library_id, con_no);
//        if(acqrdetails!=null)
//        {
//
//          if(acqrdetails.getId().getRecievingNo().equals(recieving_no))
//          {
//              request.setAttribute("msg1", "change receiving no.");
//              return mapping.findForward(SUCCESS);
//          }
//          else
//          {
//              int maxreceivingid=AcquisitionDao.returnReceivingItemId(library_id, sub_library_id);
//              acqrecdetailsid.setLibraryId(library_id);
//              acqrecdetailsid.setSubLibraryId(sub_library_id);
//              acqrecdetailsid.setRecievingNo(recieving_no);
//              acqrecdetailsid.setRecievingItemId(maxreceivingid);
//
//              acqrecdetails.setId(acqrecdetailsid);
//              acqrecdetails.setTitleId(t);
//              acqrecdetails.setUnitPrice(unit_price);
//              acqrecdetails.setRecievedCopies(String.valueOf(r));
//              acqrecdetails.setPendingCopies(String.valueOf(n-(pr+r)));
//              acqrecdetails.setApprovalType(acq_mode);
//              acqrecdetails.setControlNo(c);
//              acqrecdetails.setVendorId(vendor_id);
//              acqrecdetails.setTime(time);
//
//          }
//        }
//        else
//        {
          int maxreceivingid=acqdao.returnReceivingItemId(library_id, sub_library_id);
          acqrecdetailsid.setLibraryId(library_id);
          acqrecdetailsid.setSubLibraryId(sub_library_id);
          acqrecdetailsid.setRecievingNo(recieving_no);
          acqrecdetailsid.setRecievingItemId(maxreceivingid);

          acqrecdetails.setId(acqrecdetailsid);
          acqrecdetails.setTitleId(t);
          acqrecdetails.setUnitPrice(unit_price);
          acqrecdetails.setRecievedCopies(r);
          acqrecdetails.setPendingCopies(n-(pr+r));
          acqrecdetails.setApprovalType(acq_mode);
          acqrecdetails.setControlNo(c);
          acqrecdetails.setVendorId(vendor_id);
          acqrecdetails.setTime(time);
      //  }



        AcqOrder1 acqorder1=acqdao.searchAcqOrder1(library_id, sub_library_id, order_no, con_no);
        acqorder1.setRecievingDate(recieved_date);
        acqorder1.setRecievingNo(recieving_no);
        if(n-(pr+r)==0)
          acqorder1.setRecievingStatus("Received");
        else
          acqorder1.setRecievingStatus("Partially Received");
        acqdao.updateOrder1table(acqorder1);

        int i;
        List<AcqOrder1> acqorder1list=acqdao.searchListAcqOrder1(library_id, sub_library_id, order_no);

        for(i=0;i<acqorder1list.size();i++)
        {
          if(acqorder1list.get(i).getRecievingStatus()==null)
              break;
          if(!acqorder1list.get(i).getRecievingStatus().equals("Received"))
             break;
        }
AcqOrderDao dao=new AcqOrderDao();
        AcqOrderHeader acqorderheader=null;
        acqorderheader=dao.search1Orderno(order_no, library_id, sub_library_id);
        if(i==0)
          acqorderheader.setOrderStatus("PC");
        if(acqorder1list.size()==i+1)
        {

            acqorderheader.setOrderStatus("C");
        }
        else
            acqorderheader.setOrderStatus("PC");

        AcqBibliographyDetails acqbibdetails=acqdao.BibliobyControlIdonApproval(library_id, sub_library_id, con_no);
        if(n-(pr+r)==0)
            acqbibdetails.setStatus("Received");
        else
            acqbibdetails.setStatus("Partially Received");

        boolean statement=false;
        if(acqrec==null)
          statement=acqdao.insertReceiveOrder(acqrecheader,acqrecdetails,acqorder1,acqorderheader,acqbibdetails) ;
        else
        {
           if(!acqrec.getOrderNo().equals(order_no))
           {
              // statement=AcquisitionDao.insertReceiveOrder(acqrecheader,acqrecdetails,acqorder1,acqorderheader,acqbibdetails) ;
               request.setAttribute("msg1", "Record not Inserted please select same order no. for given receiving no");
               return mapping.findForward(SUCCESS);
           }
           else
              statement=acqdao.insertReceiveOrder1(acqrecdetails,acqorder1,acqorderheader,acqbibdetails) ;

        }
        if(statement)
          request.setAttribute("msg", "Record Inserted Successfully");
        else
          request.setAttribute("msg1", "Record not Inserted");
        return mapping.findForward(SUCCESS);
    }
}
