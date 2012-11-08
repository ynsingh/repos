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
import com.myapp.struts.opacDAO.NewDemandDAO;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import com.myapp.struts.utility.DateCalculation;
import java.util.List;
/**
 *
 * @author EdRP-04
 */
public class AcqAcquisitionProcessOfOpacAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcqBibliographyDetails acqbibdtail1=new AcqBibliographyDetails();
    AcqBibliographyDetailsId acqbibdtailid1=new AcqBibliographyDetailsId();
    Demandlist dmnd=new Demandlist();
    DemandlistId dmndid=new DemandlistId();
    AcqBibliography acqbibdtail=new AcqBibliography();
    AcqBibliographyId acqbibdtailid=new AcqBibliographyId();
    AcquisitionDao acqdao=new AcquisitionDao();
    NewDemandDAO ndmndao=new NewDemandDAO();
      float total;
  BudgetDAO bugdao=new BudgetDAO();

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
        String mem_id=(String)session.getAttribute("mem_id");
//int title_id=(Integer)session.getAttribute("title");
int demand_id=(Integer)session.getAttribute("demand_id");
System.out.println("TTTTTTTITTTTTTTTll         "+demand_id);
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
       // acqbibdtailid.setLibraryId(library_id);
        //acqbibdtailid.setSubLibraryId(sub_library_id);
        //acqbibdtailid.setTitleId(demand_id);
        List rst1=acqdao.getBiblioFromOpacUpdate(library_id, sub_library_id);

       dmnd=ndmndao.DemandId(library_id, sub_library_id, demand_id);
       String st=dmnd.getStatus();
       System.out.println("@@@@@@@@@@@@@@@@@@@"+st);
       if(st!="pending")
       {
         
        dmndid.setLibraryId(library_id);
        dmndid.setMemId(mem_id);
        dmndid.setSublibraryId(sub_library_id);
       dmnd.setStatus("titleinaquisition");
        
        ndmndao.updateDemandStatus(dmnd);
       }
      
      
        
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
        
        acqbibdtail1.setTitleId(demand_id);


        String budgetHeadId=acqbib.getPrimary_budget();
        String curr=acqbib.getExchange();
        int unitprice=Integer.parseInt(acqbib.getUnit_price());
        int noc=acqbib.getNo_of_copies();



         AcqCurrency acq=(AcqCurrency)bugdao.getConversionRate(library_id,curr);
        AcqBudgetAllocation allocation=bugdao.getSearchBudgetHead(library_id,budgetHeadId,year);


        if(!curr.equalsIgnoreCase("Select")){
            acqbibdtail1.setCurrency(acqbib.getExchange());

       float rate=acq.getConversionRate()*unitprice;
      total=rate*noc;


      String acq1=bugdao.getBudgetTransaction(library_id,budgetHeadId);

float bal=0;
if(acq1!=null)
{

     bal=total+Float.parseFloat(acq1);


}





        bal=Float.parseFloat(allocation.getTotalAmount())-bal;
       if(bal<=0){
       request.setAttribute("msg1", "Budget is not Sufficient");
       return mapping.findForward("fail");

       }




        }
        else
        {
               BaseCurrency acq1=(BaseCurrency)bugdao.getBaseCurrency(library_id);
              acqbibdtail1.setCurrency(acq1.getId().getBaseCurrencySymbol());

        total=unitprice*noc;
       float bal=Float.parseFloat(allocation.getTotalAmount())-total;
        if(bal<=0){
       request.setAttribute("msg1", "Budget is not Sufficient");
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
       bugdao.insertTransaction(budget);






        acqdao.insert1(acqbibdtail1);
        String msg="Record is inserted successfully with control no :"+control_no;
        request.setAttribute("msg2", msg);
        
        session.setAttribute("opacList", rst1);
        return mapping.findForward(SUCCESS);
    }
}