/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.hbm.AcqInvoiceDetail;
import com.myapp.struts.hbm.AcqInvoiceDetailId;
import com.myapp.struts.hbm.AcqInvoiceHeader;
import com.myapp.struts.hbm.AcqInvoiceHeaderId;
import com.myapp.struts.hbm.AcqOrderHeader;
import com.myapp.struts.hbm.AcqRecievingDetails;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author edrp02
 */
public class AcqInvoiceItem3Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
     int i=0,j=0;
    String invoice_no,invoicing_date,control_no,order_no,recieved_copies,unit_price,recieving_no,vendor_id,recieved_by,list,pending_copies;
    String discount,net_amt;
    String delimiter1 = ";";
    String delimiter2 = ",";
    String items[];
    String o_no[];
    String sizearraylist1[];
    List<String> l1=new ArrayList();
    List<Integer> l2=new ArrayList();

    AcqInvoiceHeader acqinvoiceheader1=new AcqInvoiceHeader();
    AcqInvoiceHeaderId acqinvoiceheader1id=new AcqInvoiceHeaderId();
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AcqInvoiceItemActionForm aiiaf=(AcqInvoiceItemActionForm)form;
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        invoice_no=aiiaf.getInvoice_no();
        invoicing_date=aiiaf.getInvoicing_date();
        list=aiiaf.getList();
        items= list.split(delimiter1);
        String sizearraylist=(String)session.getAttribute("i");
        sizearraylist1=sizearraylist.split(delimiter2);
        System.out.println("testtesttesttesttesttest"+sizearraylist);
        int[] disc=(int[])session.getAttribute("disc");
        String sizearray=(String)session.getAttribute("sizearray");
       
        for(i=0;i<Integer.parseInt(sizearray);i++)
         System.out.println("checkcheckcheckcheck"+disc[i]);

        for(int j=0;j<sizearraylist1.length;j++)
        {
          for(i=0;i<Integer.parseInt(sizearray);i++)
          {
              if(Integer.parseInt(sizearraylist1[j])==i)
              {
                l2.add(disc[i]);
                break;
              }
          }
        }

        for(int s=0;s<l2.size();s++)
        System.out.println("testtesttesttesttestaaaaaaaaa"+l2.get(s));
        

        for(i=0;i<items.length;i++)
        {
          String item[]=items[i].split(delimiter2);
          l1.add(item[1]);
        }

        for(i=0;i<l1.size();i++)
        {  
          System.out.println("Value of i="+i);
           for(j=i+1;j<l1.size();j++)
           {
               System.out.println("Value of j="+j);
               if(!l1.get(i).equals(l1.get(j)))
               {
                break;
               }
           }
           if(j!=l1.size())break;
        }

       
        if((i+1)<l1.size())
        { 
           request.setAttribute("msg1", "Select all receiving no. having of same order");
           l1=new ArrayList();
           
           return mapping.findForward(SUCCESS);
        }
       
        

        List<AcqInvoiceHeader> acqinvoiceheader=(List<AcqInvoiceHeader>)AcquisitionDao.searchByInovoiceHeader(library_id,sub_library_id,invoice_no);
        if(!acqinvoiceheader.isEmpty())
        {
           request.setAttribute("msg1", "This invoice No. is already exist");
           l1=new ArrayList();
           return mapping.findForward(SUCCESS);
        }


        for(i=0;i<items.length;i++)
        {
           String item[]=items[i].split(delimiter2);
           recieving_no=item[5];
           order_no=item[1];
           vendor_id=item[6];
           recieved_copies=item[2];
           unit_price=item[3];
           pending_copies=item[8];
           recieved_by=item[7];
           discount=String.valueOf(l2.get(i));
           

           AcqRecievingDetails acqredetails=AcquisitionDao.searchInRecievingDetails(library_id, sub_library_id, recieving_no, pending_copies);
           acqredetails.setStatus("Invoice");
           int tamount=Integer.parseInt(recieved_copies)*Integer.parseInt(unit_price);
           net_amt=String.valueOf(tamount-.01*l2.get(i)*tamount);
           AcqInvoiceDetail acqindetail=new AcqInvoiceDetail();
           AcqInvoiceDetailId acqindetailid=new AcqInvoiceDetailId();
           acqindetailid.setLibraryId(library_id);
           acqindetailid.setSubLibraryId(sub_library_id);
           acqindetailid.setInvoiceNo(invoice_no);
           acqindetailid.setRecievingNo(recieving_no);
           acqindetail.setId(acqindetailid);
           acqindetail.setOrderNo(order_no);
           acqindetail.setVendorId(vendor_id);
           acqindetail.setDiscount(discount);
           acqindetail.setNetTotal(net_amt);
           acqindetail.setTotalAmount(String.valueOf(tamount));
           acqindetail.setRecievingItemId(acqredetails.getId().getRecievingItemId());
           boolean result=AcquisitionDao.insertInInovoiceDetails(acqindetail,acqredetails);
           if(result==false)
           {
             request.setAttribute("msg1", "Receiving No.="+recieving_no+" not inserted");
             l1=new ArrayList();
             return mapping.findForward(SUCCESS);
           }



        }
        l2=new ArrayList();
        
        AcqOrderHeader acqoheader=AcquisitionDao.searchOrderHeaderByOrderNo(library_id, sub_library_id,l1.get(0));
        acqinvoiceheader1id.setLibraryId(library_id);
        acqinvoiceheader1id.setSublibraryId(sub_library_id);
        acqinvoiceheader1id.setInvoiceNo(invoice_no);
        acqinvoiceheader1id.setOrderNo(order_no);
        acqinvoiceheader1id.setVendorId(vendor_id);
        acqinvoiceheader1.setId(acqinvoiceheader1id);
        acqinvoiceheader1.setRecievedBy(recieved_by);
        acqinvoiceheader1.setInvoiceDate(invoicing_date);
        acqinvoiceheader1.setOverallDiscount(acqoheader.getDiscount());
        acqinvoiceheader1.setGrandTotal(acqoheader.getGrandTotal());

        Integer all=0;
        String shipcost=acqoheader.getShipCost();
        if(!shipcost.equals("null"))
           if(!shipcost.equals(""))all= Integer.parseInt(shipcost);
        String ocost=acqoheader.getOtherCost();
        if(!ocost.equals("null"))
           if(!ocost.equals(""))all=all=all+Integer.parseInt(ocost);
        String tex=acqoheader.getTaxAmount();
        if(!tex.equals("null"))
           if(!tex.equals(""))all=all+Integer.parseInt(tex);

        acqinvoiceheader1.setMiscCharges(String.valueOf(all));
        boolean result=AcquisitionDao.insertInInovoiceHeader(acqinvoiceheader1);
        if(result)
        {
              l1=new ArrayList();
             request.setAttribute("msg", "record inserted Successfully");
             return mapping.findForward(SUCCESS);
        }
        else
        {
             l1=new ArrayList();
            request.setAttribute("msg", "record not insert");
            return mapping.findForward(SUCCESS);
        }



     // return mapping.findForward(SUCCESS);

    }

}
