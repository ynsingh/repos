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
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author maqbool
 */
public class AcqInitiateAcquisitionProcess1Action extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
   AcquisitionDao ado=new AcquisitionDao();
   AcqBibliography acqbib=new AcqBibliography();
   AcqApprovalHeader acqh=new AcqApprovalHeader();
   AcqApprovalHeaderId acqhid=new AcqApprovalHeaderId();
   AcqApproval acqa=new AcqApproval();
   AcqApprovalId acqaid=new AcqApprovalId();
        String delimiter1 = ";";
        String delimiter2 = ",";
        String items[];
        String items2[];
        String title;
        String partial[];
        String partial_qty;
        String partial_title;
        String partial_approved[];
        String partial_approved_qty;
        String partial_approved_title;
        
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        AcqBiblioActionForm acqbibform=(AcqBiblioActionForm)form;
        String approval_no=acqbibform.getApproval_no();
        String unit_price=acqbibform.getUnit_price();
        String requested_date=acqbibform.getRequested_date();
        String  recommended_by=acqbibform.getRecommended_by();
        String vendor=acqbibform.getVendor();
                 System.out.println(vendor);
        String approval_status=acqbibform.getApproval_status();
        String approved_by=acqbibform.getApproved_by();
        String approval_date=acqbibform.getApproval_date();
       
        String button=acqbibform.getButton();
        String list3=acqbibform.getList3();
        String list2=acqbibform.getList2();
        System.out.println("fdgdgdgdgdgfdgfdgfdgfnmbmbmbmn"+list2+"LIST!!!!!!!!!!!!!!"+list3);
        String list1=acqbibform.getList1();
       
        System.out.println("fdgdgdgdgdgfdgfdgfdgfnmbmbmbmn"+list1);      

       
         ArrayList<Integer> l1=new ArrayList<Integer>();
        if(StringUtils.isNotEmpty(list2)&& !list2.equalsIgnoreCase("undefined"))
        {
            partial=list2.split(delimiter1);
        for(int  i=0;i<partial.length;i++){
        String t2[]=partial[i].split(delimiter2);
        int controlno=Integer.parseInt(t2[0]);
        System.out.println("CONNNNNNNNNNNNNNNNNNNNNNNNNNNNNTRRRRRRRRRRRRRRRRRR"+controlno);
        int no_of_copies=Integer.parseInt(t2[1]);
        l1.add(controlno);
        }
}
// For All Copy Selection
if(!l1.isEmpty())
{
       for(int h=0;h<l1.size();h++){
        try
        {
         int con_no=l1.get(h);
         System.out.println(con_no+".....................");
         AcqBibliographyDetails acqbibdtail= ado.BibliobyControlId(library_id, sub_library_id, con_no);
         System.out.println("$$$$$$$$$$$$$$$$$$$"+acqa);      
         acqa.setControlNo(con_no);
         acqa.setAcqApprovalHeader(acqh);
         acqa.setApprovalNo(approval_no);

         acqa.setNoOfCopies(acqbibdtail.getNoOfCopies());
       
         


         acqaid.setLibraryId(library_id);
         acqaid.setSubLibraryId(sub_library_id);
         int maxitemid=ado.returnMaxItemId(library_id, sub_library_id);
         acqaid.setApprovalItemId(maxitemid);
         acqa.setId(acqaid);
         acqa.setStatus("pending");
         ado.insert3(acqa);

         AcqBibliographyDetails acqbib1=new AcqBibliographyDetails();
         AcqBibliographyDetailsId acqbibid1=new AcqBibliographyDetailsId();
         acqbibid1.setControlNo(con_no);
         acqbibid1.setLibraryId(library_id);
         acqbibid1.setSubLibraryId(sub_library_id);
         
         acqbib1.setTitleId(acqbibdtail.getTitleId());
         acqbib1.setId(acqbibid1);
         acqbib1.setStatus("Fully Approved");
         acqbib1.setAcqMode("Approved");

         acqbib1.setVendor(vendor);
         acqbib1.setUnitPrice(acqbibdtail.getUnitPrice());
         acqbib1.setNoOfCopies(0);
         acqbib1.setAuthor(acqbibdtail.getAuthor());
         acqbib1.setNoOfVolume(acqbibdtail.getNoOfVolume());
         acqbib1.setPrimaryBudget(acqbibdtail.getPrimaryBudget());
         acqbib1.setCurrency(acqbibdtail.getCurrency());
         acqbib1.setSubject(acqbibdtail.getSubject());
         ado.update(acqbib1);
        }
       catch(Exception e)
        {
            System.out.println("SerialFinalDemandAction:"+e+"*******************");
        }
       }
}

/// End For all copy selection
       
 
          AcqBibliographyDetails acqbib1=new AcqBibliographyDetails();
         AcqBibliographyDetailsId acqbibid1=new AcqBibliographyDetailsId();
         ArrayList<Integer> l2=new ArrayList<Integer>();
         ArrayList<Integer> l3=new ArrayList<Integer>();
       items= list3.split(delimiter1);
       System.out.println("itemsaaaaaaaaaaaaaaaa"+items);
     //  StringUtils.trim(list1);
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
        //l3.add(no_copies);
       }
       }

     

       System.out.println("itemsaaaaaaaaaaaaaaaa"+items2);
    
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
        //l2.add(co_no);
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
            System.out.println("selected cpoiesjjjjjjjjjjjjjjjjjj"+selected_copies+"........"+l3);
         AcqBibliographyDetails acqbibdtail= ado.BibliobyControlId(library_id, sub_library_id, control_no);
         System.out.println("$$$$$$$$$$$$$$$$$$$"+acqa);
         acqa.setControlNo(control_no);
         acqa.setAcqApprovalHeader(acqh);
         acqa.setApprovalNo(approval_no);
         acqa.setNoOfCopies(selected_copies);
         acqa.setStatus("pending");
         
         acqaid.setLibraryId(library_id);
         acqaid.setSubLibraryId(sub_library_id);
         int maxitemid=ado.returnMaxItemId(library_id, sub_library_id);
         acqaid.setApprovalItemId(maxitemid);
         acqa.setId(acqaid);
         ado.insert3(acqa);
         int no_of_copy=acqbibdtail.getNoOfCopies();
         System.out.println("selected copiessssssssjjjjjjjjjjjjjjjjjj"+no_of_copy);
         int sub_no_of_copies=no_of_copy-selected_copies;
         System.out.println("sub   copiesjjjjjjjjjjjjjjjjjj"+sub_no_of_copies);
         acqbibid1.setControlNo(control_no);
         acqbibid1.setLibraryId(library_id);
         acqbibid1.setSubLibraryId(sub_library_id);
         
          acqbib1.setTitleId(acqbibdtail.getTitleId());
         acqbib1.setId(acqbibid1);        
         if(sub_no_of_copies==0)
            {
             acqbib1.setAcqMode("Approved");
             acqbib1.setNoOfCopies(0);
            }
        else
         {
           acqbib1.setAcqMode("On Approval");
           acqbib1.setNoOfCopies(sub_no_of_copies);
         }
         acqbib1.setAuthor(acqbibdtail.getAuthor());
         acqbib1.setNoOfVolume(acqbibdtail.getNoOfVolume());
         acqbib1.setPrimaryBudget(acqbibdtail.getPrimaryBudget());
         acqbib1.setUnitPrice(acqbibdtail.getUnitPrice());
         if(sub_no_of_copies==0)
         {
             acqbib1.setStatus("Fully Approved");
         }
         else{
             acqbib1.setStatus("Partially Approved");
         }
         
         acqbib1.setVendor(vendor);
         
         acqbib1.setCurrency(acqbibdtail.getCurrency());
         acqbib1.setSubject(acqbibdtail.getSubject());
         ado.update(acqbib1);
        }
       catch(Exception e)
        {
            System.out.println("SerialFinalDemandAction:"+e+"*******************");
        }

    }
 }
         acqh.setApprovedBy(approved_by);
         acqh.setVendorId(vendor);
         acqh.setAcqMode("Approved");
         acqh.setApprovalDate(approval_date);
         acqh.setRecommendedBy(recommended_by);
         acqhid.setApprovalNo(approval_no);
         acqhid.setLibraryId(library_id);
         acqhid.setSubLibraryId(sub_library_id);
         acqh.setId(acqhid);
         
         ado.insert2(acqh);


     //   System.out.println("HGBGVVVVVVVVVVVVV"+l2.size()+"GHFYTFYTRFYTFHGVH"+l2.get(0)+"RRRRRRRRRRRRRRRRRRRRRRRRRRR"+l2.get(1)+l2.get(2));
        String msg2="Record Approved Successfully";
        request.setAttribute("msg2",msg2);
        return mapping.findForward(SUCCESS);
    }
}
