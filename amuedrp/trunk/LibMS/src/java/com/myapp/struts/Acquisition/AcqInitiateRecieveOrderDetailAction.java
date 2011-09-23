/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author maqbool
 */
public class AcqInitiateRecieveOrderDetailAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
   AcquisitionDao ado=new AcquisitionDao();
   AcqOrder1 acqbib=new AcqOrder1();
   AcqOrder1Id acqbibid=new AcqOrder1Id();
   AcqOrderHeader acqh=new AcqOrderHeader();
   AcqRecievingHeader acqrch=new AcqRecievingHeader();
   AcqRecievingHeaderId acqrchid=new AcqRecievingHeaderId();
   AcqRecievingDetails acqa=new AcqRecievingDetails();
    AcqRecievingDetailsId acqaid=new AcqRecievingDetailsId();

        String delimiter1 = ";";
        String delimiter2 = ",";
        String items[];
        String items2[];
        String title;
        String partial[];
        String partial_qty;
        String partial_title;
        String partial_recieved[];
        String partial_recieved_qty;
        String partial_recieved_title;

    private int no_of_copies;
    private int title_id;


    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        AcqOrderManagementActionForm acqbibform=(AcqOrderManagementActionForm)form;


            String vendor=acqbibform.getVendor();
        System.out.println(vendor);
        int approval_item_id=acqbibform.getApproval_item_id();
        String button=acqbibform.getButton();
        String unit_price=acqbibform.getUnit_price();
        String recieving_no=acqbibform.getReceiving_no();
        String recieved_date=acqbibform.getRecieved_date();
        String recieved_by=acqbibform.getRecieved_by();
        String order_no=acqbibform.getOrder_no();

        String approval_type=acqbibform.getApproval_type();
        String list3=acqbibform.getList3();
        String list2=acqbibform.getList2();
        System.out.println("fdgdgdgdgdgfdgfdgfdgfnmbmbmbmn"+list2+"LIST!!!!!!!!!!!!!!"+list3);
        String list1=acqbibform.getList1();
        System.out.println("fdgdgdgdgdgfdgfdgfdgfnmbmbmbmn"+list1);
        ArrayList<Integer> l1=new ArrayList<Integer>();
        if(StringUtils.isNotEmpty(list2)&& !list2.equalsIgnoreCase("undefined"))
        {
         partial=list2.split(delimiter1);
        for(int i=0;i<partial.length;i++){
        String t2[]=partial[i].split(delimiter2);
        int controlno=Integer.parseInt(t2[0]);
        System.out.println("CONNNNNNNNNNNNNNNNNNNNNNNNNNNNNTRRRRRRRRRRRRRRRRRR"+controlno);
        int no_of_copies=Integer.parseInt(t2[1]);
        l1.add(controlno);
        }
}


          acqrch.setOrderNo(order_no);
         acqrch.setVendorId(vendor);
         acqrch.setRecievedBy(recieved_by);
         acqrch.setRecievedDate(recieved_date);
         acqrchid.setLibraryId(library_id);
         acqrchid.setSubLibraryId(sub_library_id);
         acqrchid.setRecievingNo(recieving_no);
         acqrch.setId(acqrchid);

         ado.insert7(acqrch);

// For All Copy Selection
if(!l1.isEmpty())
        {
         for(int h=0;h<l1.size();h++){
         try
        {
         int con_no=l1.get(h);
         System.out.println(con_no+".....................");
         List<AcqOrder1> aa2=ado.BibliobyControlId2(library_id, sub_library_id, con_no,order_no);
         AcqBibliographyDetails acqobj=ado.BibliobyControlId(library_id, sub_library_id, con_no);
         AcqBibliography acqobj1=ado.BibliobyTitleId(acqobj.getTitleId(), library_id, sub_library_id);
         title_id=acqobj1.getId().getTitleId();


      System.out.println("@@@@@@@@@@@@@@@@@"+aa2.size());

      for(int k=0;k<aa2.size();k++)
      {
      int x =aa2.get(k).getApprovalItemId();
      System.out.println("%%%%%%%%%%%%%%%%%%%"+x);

      if(x>0)
      {
         AcqApproval acqbibdtail = ado.BibliobyControlId3(library_id, sub_library_id, con_no,x);
         System.out.println("$$$$$$$$$$$$$$$$$$$"+acqbibdtail.getNoOfCopies());
         acqa.setApprovalType(approval_type);
         acqa.setTitleId(title_id);
         acqa.setRecievedCopies(acqbibdtail.getNoOfCopies());
         acqa.setControlNo(con_no);
         acqa.setUnitPrice(unit_price);
         acqa.setId(acqaid);
         acqaid.setRecievingNo(recieving_no);
         acqaid.setLibraryId(library_id);
         acqaid.setSubLibraryId(sub_library_id);
         int maxitemid=ado.returnMaxRecievingItemId(library_id, sub_library_id);
         acqaid.setRecievingItemId(maxitemid);
         ado.insert5(acqa);

          AcqApproval acqbibdtail1 = ado.BibliobyControlId3(library_id, sub_library_id, con_no,x);


                 acqbibdtail1.setStatus("Ordered/FullyRecieved");

           ado.updateApproval(acqbibdtail1);

        List<AcqOrder1> aa3=ado.getOrderno1(library_id, sub_library_id,order_no,con_no);
         for(int j=0;j<aa3.size();j++)
         {
         aa3.get(j).setRecievingStatus("Fully Recieved");
         aa3.get(j).setRecievingNo(recieving_no);
         ado.update2(aa3.get(j));
         }




       }
      else
        {
           AcqBibliographyDetails acqbibdtail1=ado.BibliobyControlIdonApproval(library_id, sub_library_id,con_no);
      acqa.setApprovalType(approval_type);
      acqa.setControlNo(con_no);
      acqa.setUnitPrice(unit_price);
      acqa.setRecievedCopies(acqbibdtail1.getNoOfCopies());
      int maxitemid=ado.returnMaxRecievingItemId(library_id, sub_library_id);
      acqaid.setRecievingItemId(maxitemid);
      acqaid.setLibraryId(library_id);
      acqaid.setSubLibraryId(sub_library_id);
      acqaid.setRecievingNo(recieving_no);
      acqa.setId(acqaid);
        ado.insert5(acqa);
        List<AcqOrder1> aa3=ado.getOrderno1(library_id, sub_library_id,order_no,con_no);
         for(int j=0;j<aa3.size();j++)
         {
         aa3.get(j).setRecievingStatus("Fully Recieved");
         aa3.get(j).setRecievingNo(recieving_no);
         ado.update2(aa3.get(j));
         }
        }
      }
        }
       catch(Exception e)
        {
            System.out.println("SerialFinalDemandAction:"+e+"*******************");
        }
       }
}

/// End For all copy selection


       //  AcqBibliographyDetails acqbib1=new AcqBibliographyDetails();
        // AcqBibliographyDetailsId acqbibid1=new AcqBibliographyDetailsId();
        //  List<AcqOrder1> aa2=ado.BibliobyControlId2(library_id, sub_library_id, con_no,order_no);

         ArrayList<Integer> l2=new ArrayList<Integer>();
         ArrayList<Integer> l3=new ArrayList<Integer>();
       items= list3.split(delimiter1);
       System.out.println("itemsaaaaaaaaaaaaaaaa"+items);
       StringUtils.trim(list1);
         if(StringUtils.isNotEmpty(list3)&& !list3.equalsIgnoreCase("undefined"))
        {
       String items1[]=list3.split(delimiter1);
       for(int i=0;i<items.length;i++)
       {
        String doc[]=items[i].split(delimiter2);
        String doc1[]=new String[i];
        doc1=items1[i].split(delimiter2);
        String c_no=doc[0];
        System.out.println("control  nooooo"+c_no);
        int co_no=0;
        if(!StringUtils.isEmpty(c_no))
        {
            co_no =Integer.parseInt(c_no);
        }
        l2.add(co_no);
       // l3.add(no_of_copies);
       }
       }



     //  System.out.println("itemsaaaaaaaaaaaaaaaa"+items2);

       if(!StringUtils.isEmpty(list1));
       {
           String items3[]=list1.split(delimiter1);
       for(int i=0;i<items3.length;i++)
       {
        String no_select=items3[i];
        int no_copies=0;
        if(!StringUtils.isEmpty(no_select))
        {
          no_copies =Integer.parseInt(no_select);
        }
       // l2.add(con_no);
        l3.add(no_copies);
       }
       }
       if(!l2.isEmpty())
 {
     for(int m=0;m<l2.size();m++){
        try
        {
            int control_no=l2.get(m);

            int selected_copies=l3.get(m);
             List<AcqOrder1> aa2=ado.BibliobyControlId2(library_id, sub_library_id, control_no,order_no);
      System.out.println("@@@@@@@@@@@@@@@@@"+aa2.size());
            System.out.println("selected cpoiesjjjjjjjjjjjjjjjjjj"+selected_copies+"........"+l3+control_no);
            AcqBibliographyDetails acqobj=ado.BibliobyControlId(library_id, sub_library_id, control_no);
         AcqBibliography acqobj1=ado.BibliobyTitleId(acqobj.getTitleId(), library_id, sub_library_id);
         title_id=acqobj1.getId().getTitleId();


            for(int k1=0; k1<aa2.size(); k1++)
            {

           int x =aa2.get(k1).getApprovalItemId();
           System.out.println(x);
           if(x>0)
           {
            AcqApproval acqbibdtail = ado.BibliobyControlId3(library_id, sub_library_id, control_no,x);
            System.out.println(acqbibdtail+"..................");
            int no_of_copies=acqbibdtail.getNoOfCopies();
            int sub_no_of_copies=no_of_copies-selected_copies;
            int maxitemid=ado.returnMaxRecievingItemId(library_id, sub_library_id);
            acqaid.setRecievingItemId(maxitemid);
            acqaid.setLibraryId(library_id);
            acqaid.setSubLibraryId(sub_library_id);
            acqaid.setRecievingNo(recieving_no);
            acqa.setId(acqaid);
            acqa.setApprovalType(approval_type);
            acqa.setRecievedCopies(selected_copies);
            acqa.setControlNo(control_no);
            acqa.setUnitPrice(unit_price);

             ado.insert5(acqa);

              AcqApproval acqbibdtail1 = ado.BibliobyControlId3(library_id, sub_library_id, control_no,x);
           acqbibdtail1.setNoOfCopies(sub_no_of_copies);

             if(sub_no_of_copies==0)
         {

                 acqbibdtail1.setStatus("Ordered/FullyRecieved");
         }
         else{
              acqbibdtail1.setStatus("Ordered/PartiallyRecieved");
         }
           ado.updateApproval(acqbibdtail1);

    List<AcqOrder1> aa3=ado.getOrderno1(library_id, sub_library_id,order_no,control_no);
         for(int j=0;j<aa3.size();j++)
         {

       if(sub_no_of_copies==0)
         {
                 aa3.get(j).setRecievingStatus("Fully Recieved");

         }
         else{
              aa3.get(j).setRecievingStatus("Partially Recieved");
         }

aa3.get(j).setRecievingNo(recieving_no);
 ado.update2(aa3.get(j));
         }

           }
           else
           {
                AcqBibliographyDetails acqbibdtail1=ado.BibliobyControlIdonApproval(library_id, sub_library_id,control_no);
             int no_of_copies=acqbibdtail1.getNoOfCopies();
            int sub_no_of_copies=no_of_copies-selected_copies;

            int maxitemid=ado.returnMaxRecievingItemId(library_id, sub_library_id);
            acqaid.setRecievingItemId(maxitemid);
            acqaid.setLibraryId(library_id);
            acqaid.setSubLibraryId(sub_library_id);
            acqaid.setRecievingNo(recieving_no);
            acqa.setId(acqaid);
            acqa.setApprovalType(approval_type);
            acqa.setRecievedCopies(selected_copies);
            acqa.setControlNo(control_no);
            acqa.setUnitPrice(unit_price);

            ado.insert5(acqa);


            AcqBibliographyDetails acqbibdtail2=ado.BibliobyControlIdonApproval(library_id, sub_library_id,control_no);


            acqbibdtail2.setNoOfCopies(sub_no_of_copies);

            ado.update(acqbibdtail2);

            List<AcqOrder1> aa3=ado.getOrderno1(library_id, sub_library_id,order_no,control_no);
         for(int j=0;j<aa3.size();j++)
         {

       if(sub_no_of_copies==0)
         {
                 aa3.get(j).setRecievingStatus("Fully Recieved");

         }
         else{
              aa3.get(j).setRecievingStatus("Partially Recieved");
         }
       aa3.get(j).setRecievingNo(recieving_no);
         ado.update2(aa3.get(j));
           }


        }
            }
        }

       catch(Exception e)
        {
            System.out.println("SerialFinalDemandAction:"+e+"*******************");
        }



//###############




         String msg="Order Recieved Successfully";
        request.setAttribute("msg",msg);

    }

       }
  return mapping.findForward(SUCCESS);
    }
}

