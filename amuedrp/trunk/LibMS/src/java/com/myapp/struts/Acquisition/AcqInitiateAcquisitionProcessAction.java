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
import com.myapp.struts.AcquisitionDao.VendorDAO;
import com.myapp.struts.utility.DateCalculation;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
/**
 *
 * @author maqbool
 */
public class AcqInitiateAcquisitionProcessAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    AcqBibliographyDetails acqbibdtail1=new AcqBibliographyDetails();
    AcqBibliographyDetailsId acqbibdtailid1=new AcqBibliographyDetailsId();
    AcqBibliography acqbibdtail=new AcqBibliography();
    AcqBibliographyId acqbibdtailid=new AcqBibliographyId();
    AcquisitionDao acqdao=new AcquisitionDao();
    float total;

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
        String sub_author0=acqbib.getSub_author0();
        String sub_author1=acqbib.getSub_author1();
        String sub_author2=acqbib.getSub_author2();

        String gg=String.valueOf(acqbib.getNo_of_copies());
        System.out.println("NOOOOOOOOOOO of CCCCCCCCCCCC"+gg+"Price"+acqbib.getUnit_price());
int i=Integer.parseInt(gg);
        if(i<=0){
String msg1="No of copy is invalid value or blank";
request.setAttribute("msg1", msg1);
return mapping.findForward("fail");
}




int title_id=(Integer)session.getAttribute("titleid");
//int title_id1=Integer.parseInt(title_id);

        acqbibdtail.setAuthor(author);
        acqbibdtail.setTitle(title);
        acqbibdtail.setPublisherName(publisher_name);
        acqbibdtail.setPublishingPlace(publishing_year);
        acqbibdtail.setPublishingYr(publishing_year);
        acqbibdtail.setEdition(edition);
        acqbibdtail.setIsbn(isbn);
        acqbibdtail.setSubAuthor(sub_author);
        acqbibdtail.setSubAuthor0(sub_author0);
        acqbibdtail.setSubAuthor1(sub_author1);
        acqbibdtail.setSubAuthor2(sub_author2);
        acqbibdtail.setLccNo(acqbib.getLcc_no());
        acqbibdtail.setPublishingPlace(publishing_place);
        acqbibdtailid.setLibraryId(library_id);
        acqbibdtailid.setSubLibraryId(sub_library_id);
        acqbibdtailid.setTitleId(acqbib.getTitle_id());
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
        acqbibdtail1.setRequestedBy(acqbib.getRequested_by());
        acqbibdtail1.setRequestedDate(acqbib.getRequested_date());
        acqbibdtail1.setAcqMode(acqbib.getAcq_mode());
       // acqbibdtail1.setStatus("");
        acqbibdtail1.setVendor(acqbib.getVendor());
        acqbibdtail1.setTitleId(acqbib.getTitle_id());

          String budgetHeadId=acqbib.getPrimary_budget();
        String curr=acqbib.getExchange();
        int unitprice=Integer.parseInt(acqbib.getUnit_price());
        int noc=acqbib.getNo_of_copies();
BudgetDAO buddao=new BudgetDAO();

         AcqCurrency acq=(AcqCurrency)buddao.getConversionRate(library_id,curr);
        AcqBudgetAllocation allocation=buddao.getSearchBudgetHead(library_id,budgetHeadId,year);


        if(!curr.equalsIgnoreCase("Select")){
            acqbibdtail1.setCurrency(acqbib.getExchange());

       float rate=acq.getConversionRate()*unitprice;
      total=rate*noc;
       float bal=Float.parseFloat(allocation.getTotalAmount())-total;
       if(bal<=0){
       request.setAttribute("msg1", "Budget is not Sufficient");
       return mapping.findForward("fail");

       }




        }
        else
        {
               BaseCurrency acq1=(BaseCurrency)buddao.getBaseCurrency(library_id);
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
       buddao.insertTransaction(budget);



        acqdao.insert1(acqbibdtail1);
        String msg="Record is inserted successfully with control no :"+control_no;
        request.setAttribute("msg2", msg);
        acqbib.setDocument_type("");
        acqbib.setTitle("");
        return mapping.findForward(SUCCESS);
    }
    }

