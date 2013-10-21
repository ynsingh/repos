/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PrePurchase;

/**
 *
 * @author sknaqvi
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.displaytag.decorator.TableDecorator;
import pojo.hibernate.ErpmPoMaster;
import pojo.hibernate.ErpmPoDetails;
import pojo.hibernate.ErpmItemRate;
import pojo.hibernate.ErpmItemRateDAO;
import pojo.hibernate.ErpmItemRateTaxes;
import pojo.hibernate.ErpmItemRateTaxesDAO;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.ErpmIndentDetail;
import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.SupplierAddress;
import pojo.hibernate.ErpmIndentMaster;
import pojo.hibernate.ErpmTenderMaster;
import pojo.hibernate.ErpmTenderSchedule;
import pojo.hibernate.ErpmTenderScheduleDetail;
import utils.DateUtilities;
import java.util.*;

public class PrePurchaseDecorator extends TableDecorator{

    private String message;

    public String getmessage() {
        return this.message;
    }

    //Method to derive PO Number
    public String getpoNumber() throws Exception{

        try{
            DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
            DateUtilities dt = new DateUtilities();

            ErpmPoMaster  poMaster =  (ErpmPoMaster)getCurrentRowObject();

            String PON  =  dmDao.findDepartmentShortName(poMaster.getDepartmentmaster().getDmId())+ "/" + dt.convertDateToString(poMaster.getPomPoDate(),"dd-MM-yyyy").substring(6) + "/" + poMaster.getPomPoNo();
            return PON;
        }
        catch (Exception e) {
            message = message + e.getMessage() + e.getCause();
            return message;
        }
    }
    
      public String gettenderDate() {
      ErpmTenderMaster etm = (ErpmTenderMaster)getCurrentRowObject();
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(etm.getTmDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }

}

    //This method sets the tax value for the Item in the PO
    public String getpodTaxes() {

        ErpmPoDetails  poDetail =  (ErpmPoDetails)getCurrentRowObject();
        return this.findTaxesNarration(poDetail);
    }

    public String findTaxesNarration(ErpmPoDetails  poDetail) {
        List<ErpmItemRateTaxes> taxes = new ArrayList<ErpmItemRateTaxes>();
        ErpmItemRateTaxesDAO taxesDao = new ErpmItemRateTaxesDAO();

        if(poDetail.getErpmIndentDetail() == null && poDetail.getErpmItemRate() != null)
           taxes = taxesDao.findByirItemRateId(poDetail.getErpmItemRate().getIrItemRateId());
        else if (poDetail.getErpmIndentDetail() != null && poDetail.getErpmItemRate() == null)
           taxes = taxesDao.findByirItemRateId(poDetail.getErpmIndentDetail().getErpmItemRate().getIrItemRateId());
        else
            return "No Tax Set";
        
            String taxNames = "";
           // taxes = taxesDao.findByirItemRateId(poDetail.getErpmIndentDetail().getErpmItemRate().getIrItemRateId());
            for (int i=0; i< taxes.size(); ++i) {
                taxNames = taxNames + taxes.get(i).getErpmGenMaster().getErpmgmEgmDesc() + " (" +
                            taxes.get(i).getIrtTaxPercent() + "% on " +
                            taxes.get(i).getIrtTaxOnValuePercent() +"%";
                  if(taxes.get(i).getIrtSurchargePercent() != null)
                      taxNames = taxNames + "; Surcharge ( " + taxes.get(i).getIrtSurchargePercent() + "%";
                taxNames = taxNames + ") ";
            }

            return taxNames;
        
    }


    public String getpodTaxValue () {
        ErpmPoDetails  poDetail =  (ErpmPoDetails)getCurrentRowObject();

        return this.calulateTaxes(poDetail).toString();
    }


    public BigDecimal calulateTaxes(ErpmPoDetails  poDetail) {
        List<ErpmItemRateTaxes> taxes = new ArrayList<ErpmItemRateTaxes>();
        ErpmItemRateTaxesDAO taxesDao = new ErpmItemRateTaxesDAO();

        if(poDetail.getErpmIndentDetail() == null && poDetail.getErpmItemRate() != null)
           taxes = taxesDao.findByirItemRateId(poDetail.getErpmItemRate().getIrItemRateId());
        else if (poDetail.getErpmIndentDetail() != null && poDetail.getErpmItemRate() == null)
           taxes = taxesDao.findByirItemRateId(poDetail.getErpmIndentDetail().getErpmItemRate().getIrItemRateId());
        else {
            BigDecimal zero = new BigDecimal("0");
            return zero;
        }

        BigDecimal taxValue = new BigDecimal(0);
        BigDecimal totalTaxValue = new BigDecimal(0);
        BigDecimal surCharge = new BigDecimal(0);
        BigDecimal percent = new BigDecimal(100);         

        for (int i=0; i< taxes.size(); ++i) {
            taxValue =  poDetail.getPodRate().multiply(poDetail.getPodQuantity()).
                                              multiply(taxes.get(i).getIrtTaxOnValuePercent()).divide(percent).
                                              multiply(taxes.get(i).getIrtTaxPercent()).divide(percent);

            if(taxes.get(i).getIrtSurchargePercent() != null) {
                surCharge = taxValue.multiply(taxes.get(i).getIrtSurchargePercent()).divide(percent);
                taxValue = taxValue.add(surCharge) ;
             }
             totalTaxValue = totalTaxValue.add(taxValue);
        }
        return (totalTaxValue.stripTrailingZeros()).setScale(2,RoundingMode.HALF_UP);
    }
    

    public String getpodTotalValue() {

        ErpmPoDetails  poDetail =  (ErpmPoDetails)getCurrentRowObject();

        BigDecimal tax = new BigDecimal(getpodTaxValue());

        return poDetail.getPodQuantity().multiply(poDetail.getPodRate()).add(tax).setScale(2,RoundingMode.HALF_UP).toString();

    }


    public String getapprovedRate() {

        ErpmIndentDetail indentItem = (ErpmIndentDetail)getCurrentRowObject();

        if(getApprovedIndentItemRate(indentItem).toString().contains("-1") )
            return "No rates stand approved";
        else
            return getApprovedIndentItemRate(indentItem).toString();
    }


    public BigDecimal getApprovedIndentItemRate(ErpmIndentDetail indentItem){

        //Find out approved  rates for the poDetail Object which are less than or equal to the accepted rate for POdetail Object         
        ErpmItemRateDAO itemRateDao = new ErpmItemRateDAO();
        Date dt = new Date();

        List<ErpmItemRate> itemRateList = itemRateDao.findApprovedItemRatesForToday(  indentItem.getErpmItemMaster().getErpmimId(), dt,
                                                                                      indentItem.getErpmIndentMaster().getErpmGenMasterByIndtCurrencyId().getErpmgmEgmDesc(),
                                                                                      (int)indentItem.getIndtQuantity());
        if(itemRateList.size() > 0)
            return  itemRateList.get(0).getIrdRate().setScale(2,RoundingMode.HALF_UP);
        else
            return new BigDecimal(-1);
    }

    public String getapprovedSupplier() {

        ErpmIndentDetail indentItem = (ErpmIndentDetail)getCurrentRowObject();

        return getapprovedIndentItemSupplier(indentItem);

    }

    public String getapprovedIndentItemSupplier(ErpmIndentDetail indentItem) {
        //Find out approved rates for the poDetail Object which are less than or equal to the accepted rate for POdetail Object
        //Return the approved Supplier

        ErpmItemRateDAO itemRateDao = new ErpmItemRateDAO();
        Date dt = new Date();

        List<ErpmItemRate> itemRateList = itemRateDao.findApprovedItemRatesForToday(  indentItem.getErpmItemMaster().getErpmimId(), dt,
                                                                                      indentItem.getErpmIndentMaster().getErpmGenMasterByIndtCurrencyId().getErpmgmEgmDesc(),
                                                                                      (int)indentItem.getIndtQuantity());
        if(itemRateList.size() > 0)
            return  itemRateList.get(0).getSuppliermaster().getSmName();
        else
            return "No supplier approved";
    }

    //This method sets the Yes/No Value Serializable Items
    public String getSerializableStatus() {

        ErpmItemMaster  erpmItemMaster =  (ErpmItemMaster) getCurrentRowObject();
        if (Character.toUpperCase(erpmItemMaster.getErpmimSerialNoApplicable()) == 'Y')
            return "Yes";
        else
            return "No";
    }

    //This method sets the Yes/No Value Serializable Items
    public String getAddress() {
        String concatenatedAddress;
        SupplierAddress  supplierAddress =  (SupplierAddress) getCurrentRowObject();
        concatenatedAddress =     supplierAddress.getAdLine1() + " "
                                + supplierAddress.getAdLine2() + " "
                                + supplierAddress.getAdCity() + " "
                                + supplierAddress.getStatemaster().getStateName() + " "
                                + supplierAddress.getCountrymaster().getCountryName();
        
            return concatenatedAddress;
    }
//this method used for change date formate to(dd-mm-yyyy) in browse indent.jsp which is getting from list IndentList
    public String getformattedIndtIndentDate() {
      ErpmIndentMaster erpmindtmast = (ErpmIndentMaster)getCurrentRowObject();;
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(erpmindtmast.getIndtIndentDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }
        
}
    
   public String getformattedpomPoDate() {
      ErpmPoMaster erpmpom = (ErpmPoMaster)getCurrentRowObject();;
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(erpmpom.getPomDeliveryDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }

}

   
   //this method used to change formate of irdWefDate(yyyy-mm-dd) to (dd-mm-yyyy)in erpmItemRate brouse
   public String getformattedirdWefDate() {
      ErpmItemRate erpmir = (ErpmItemRate)getCurrentRowObject();;
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(erpmir.getIrdWefDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }

}

   //this method used to change formate of irdWetDate(yyyy-mm-dd) to (dd-mm-yyyy)in ErpmItemRate

     public String getformattedirdWetDate() {
      ErpmItemRate erpmir = (ErpmItemRate)getCurrentRowObject();;
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(erpmir.getIrdWetDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }

}

    public String getformatederpmimDepreciationMethod() {

        ErpmItemMaster  erpmItemMaster =  (ErpmItemMaster) getCurrentRowObject();
        if (Character.toUpperCase(erpmItemMaster.getErpmimDepreciationMethod()) == 'W')
            return "Written Down Value";
        else
            return "Straight Line";
    }


    public String getformattedTscScheduleDate() {
      ErpmTenderScheduleDetail schldDet = (ErpmTenderScheduleDetail)getCurrentRowObject();
       DateUtilities dt = new DateUtilities();
       try {
        return dt.convertDateToString(schldDet.getTscdScheduleDate(), "dd-MM-yyyy");
       }
       catch (Exception e) {
        return "Error in date formatting";
    }

}
}
    