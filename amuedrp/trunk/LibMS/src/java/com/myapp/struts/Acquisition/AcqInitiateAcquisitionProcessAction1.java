/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.myapp.struts.hbm.*;
import com.myapp.struts.AcquisitionDao.AcquisitionDao;
import com.myapp.struts.AcquisitionDao.BudgetDAO;
import javax.servlet.http.HttpSession;
import com.myapp.struts.utility.DateCalculation;
import java.util.*;
/**
 *
 * @author EdRP-04
 */
public class AcqInitiateAcquisitionProcessAction1 extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcqBibliographyDetails acqbibdtail1=new AcqBibliographyDetails();
    AcqBibliographyDetailsId acqbibdtailid1=new AcqBibliographyDetailsId();
    AcqBibliography acqbibdtail=new AcqBibliography();
    AcqBibliographyId acqbibdtailid=new AcqBibliographyId();
    AcquisitionDao acqdao=new AcquisitionDao();
      float total;
Locale locale=null;
   String locale1="en";
   String rtl="ltr";
   String align="left";

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
              AcqBiblioActionForm acqbib=(AcqBiblioActionForm)form;
              String now=DateCalculation.now();
              String year=String.valueOf(Integer.parseInt(now.substring(0,4)));
        HttpSession session = request.getSession();
        String library_id = (String) session.getAttribute("library_id");
        String sub_library_id = (String) session.getAttribute("sublibrary_id");
        String title=acqbib.getTitle();
        String author=acqbib.getAuthor();
        String publisher_name=acqbib.getPublisher_name();
        String publishing_year=acqbib.getPublishing_year();
        String isbn=acqbib.getIsbn();
        String edition=acqbib.getEdition();
        String publishing_place=acqbib.getPublication_place();
        String sub_author=acqbib.getSub_author();
        String gg=String.valueOf(acqbib.getNo_of_copies());
        System.out.println("NOOOOOOOOOOO of CCCCCCCCCCCC"+gg);


         try{

        locale1=(String)session.getAttribute("locale");
    if(session.getAttribute("locale")!=null)
    {
        locale1 = (String)session.getAttribute("locale");
        System.out.println("locale="+locale1);
    }
    else locale1="en";
}catch(Exception e){locale1="en";}
     locale = new Locale(locale1);
    if(!(locale1.equals("ur")||locale1.equals("ar"))){ rtl="LTR";align = "left";}
    else{ rtl="RTL";align="right";}
    ResourceBundle resource = ResourceBundle.getBundle("multiLingualBundle", locale);



int title_id=(Integer)session.getAttribute("titleid");
System.out.println("TTTTTTTITTTTTTTTll         "+title_id);
//int title_id1=Integer.parseInt(title_id);
        acqbibdtail.setAuthor(author);
        acqbibdtail.setTitle(title);
        acqbibdtail.setPublisherName(publisher_name);
        acqbibdtail.setPublishingPlace(publishing_year);
        acqbibdtail.setPublishingYr(publishing_year);
        acqbibdtail.setEdition(edition);
        acqbibdtail.setIsbn(isbn);
        acqbibdtail.setSubAuthor(sub_author);
        acqbibdtail.setLccNo(acqbib.getLcc_no());
        acqbibdtail.setPublishingPlace(publishing_place);
        acqbibdtailid.setLibraryId(library_id);
        acqbibdtailid.setSubLibraryId(sub_library_id);
        acqbibdtailid.setTitleId(title_id);
        acqbibdtail.setId(acqbibdtailid);
        int control_no=acqdao.returnMaxControlNo(library_id, sub_library_id);
        acqbibdtailid1.setControlNo(control_no);
        acqbibdtailid1.setLibraryId(library_id);
        acqbibdtailid1.setSubLibraryId(sub_library_id);
        acqbibdtail1.setId(acqbibdtailid1);
        acqbibdtail1.setAcqBibliography(acqbibdtail);
        acqbibdtail1.setVolume(acqbib.getVolume());
        acqbibdtail1.setNoOfVolume(acqbib.getNo_of_volume());
        acqbibdtail1.setNoOfCopies(acqbib.getNo_of_copies());
        acqbibdtail1.setUnitPrice(acqbib.getUnit_price());
        acqbibdtail1.setPrimaryBudget(acqbib.getPrimary_budget());
        //acqbibdtail1.setCurrency(acqbib.getExchange());
        acqbibdtail1.setRequestedBy(acqbib.getRequested_by());
        acqbibdtail1.setRequestedDate(acqbib.getRequested_date());
        acqbibdtail1.setAcqMode(acqbib.getAcq_mode());
        acqbibdtail1.setVendor(acqbib.getVendor());
        acqbibdtail1.setTitleId(title_id);


        String budgetHeadId=acqbib.getPrimary_budget();
        String curr=acqbib.getExchange();
        int unitprice=Integer.parseInt(acqbib.getUnit_price());
        int noc=acqbib.getNo_of_copies();



         AcqCurrency acq=(AcqCurrency)BudgetDAO.getConversionRate(library_id,curr);
        AcqBudgetAllocation allocation=BudgetDAO.getSearchBudgetHead(library_id,budgetHeadId,year);


        if(!curr.equalsIgnoreCase("Select")){
            acqbibdtail1.setCurrency(acqbib.getExchange());
       
       float rate=acq.getConversionRate()*unitprice;
      total=rate*noc;


      String acq1=BudgetDAO.getBudgetTransaction(library_id,budgetHeadId);

float bal=0;
if(acq1!=null)
{
    
     bal=total+Float.parseFloat(acq1);


}





        bal=Float.parseFloat(allocation.getTotalAmount())-bal;
       if(bal<=0){
      // request.setAttribute("msg1", "Budget is not Sufficient");
        request.setAttribute("msg1",resource.getString("acquisition.AcqInitiateAcqusitionProcessAction1.msg1"));
       return mapping.findForward("fail");

       }

    


        }
        else
        {
               BaseCurrency acq1=(BaseCurrency)BudgetDAO.getBaseCurrency(library_id);
              acqbibdtail1.setCurrency(acq1.getId().getBaseCurrencySymbol());
        
        total=unitprice*noc;
       float bal=Float.parseFloat(allocation.getTotalAmount())-total;
        if(bal<=0){
    //   request.setAttribute("msg1", "Budget is not Sufficient");
               request.setAttribute("msg1", resource.getString("acquisition.AcqInitiateAcqusitionProcessAction1.msg1"));
       return mapping.findForward("fail");

       }

        }


         int trans_no=acqdao.returnMaxTransNo(library_id);

       AcqBudgetTransactionId budgetid=new AcqBudgetTransactionId(trans_no, library_id);
       AcqBudgetTransaction budget=new AcqBudgetTransaction();
       budget.setId(budgetid);
       budget.setAcqBudgetHeadId(budgetHeadId);
       budget.setControlNo(String.valueOf(control_no));
       budget.setAmount(Double.parseDouble(String.valueOf(total)));
       budget.setTransactionDate(DateCalculation.now());
       BudgetDAO.insertTransaction(budget);






        acqdao.insert1(acqbibdtail1);
        //String msg="Record is inserted successfully with control no :"+control_no;
        String msg=resource.getString("acquisition.AcqInitiateAcqusitionProcessAction1.msg2")+control_no;
        request.setAttribute("msg2", msg);
        acqbib.setDocument_type("");
        acqbib.setTitle("");
        return mapping.findForward(SUCCESS);
    }
}