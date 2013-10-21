/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Purchase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.displaytag.decorator.TableDecorator;
import pojo.hibernate.ErpmPurchaseinvoiceMaster;
import utils.DateUtilities;
import pojo.hibernate.ErpmPurchasechallanMaster;
/**
 *
 * @author Arpan, Saeed, Tanvir Ahmed, manauwar
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
import utils.DateUtilities;
import java.util.*;
import pojo.hibernate.ErpmPurchaseinvoiceMaster;
import pojo.hibernate.ErpmPurchasechallanDetail;
import pojo.hibernate.ErpmPurchaseinvoiceDetail;

public class PurchaseDecorator extends TableDecorator {

    private String message;

    public String getmessage() {
        return this.message;
    }

    //Method to derive PO Number
    public String getPONumberFromInvoice() throws Exception {

        try {
            String PON = "";
            DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
            DateUtilities dt = new DateUtilities();

            //ErpmP  poMaster =  (ErpmPoMaster)getCurrentRowObject()
            ErpmPurchaseinvoiceMaster invoiceMaster = (ErpmPurchaseinvoiceMaster) getCurrentRowObject();

            //String PON  =  dmDao.findDepartmentShortName(poMaster.getDepartmentmaster().getDmId())+ "/" + dt.convertDateToString(poMaster.getPomPoDate(),"dd-MM-yyyy").substring(6) + "/" + poMaster.getPomPoNo();
            if (invoiceMaster.getErpmPoMaster() != null) {
                PON = dmDao.findDepartmentShortName(invoiceMaster.getDepartmentmaster().getDmId()) + "/" + dt.convertDateToString(invoiceMaster.getErpmPoMaster().getPomPoDate(), "dd-MM-yyyy").substring(6) + "/" + invoiceMaster.getErpmPoMaster().getPomPoNo();
            }
            return PON;

        } catch (Exception e) {
            message = message + e.getMessage() + e.getCause();
            return message;
        }
    }

    public String getChecked() {

        ErpmPurchasechallanDetail PCDetail = (ErpmPurchasechallanDetail) getCurrentRowObject();
        if (PCDetail.getPcdQNQChecked() == 'Y') {
            return "Yes";
        } else {
            return "No";
        }

    }

    public String getVerified() {

        ErpmPurchasechallanDetail PCDetail = (ErpmPurchasechallanDetail) getCurrentRowObject();


        if (PCDetail.getPcdQNQVerified() == 'Y') {
            return "Yes";
        } else {
            return "No";
        }

    }


     public String getcheckedinvoice() {

     ErpmPurchaseinvoiceDetail pid = (ErpmPurchaseinvoiceDetail) getCurrentRowObject();

             if (pid.getPcdQNQChecked() == 'Y') {
            return "Yes";
        } else {
            return "No";
        }

    }

    public String getverifiedinvoice() {

        ErpmPurchaseinvoiceDetail pid = (ErpmPurchaseinvoiceDetail) getCurrentRowObject();

         if (pid.getPcdQNQVerified() == 'Y') {
            return "Yes";
            } else {
            return "No";
            }

    }

    public String getEditView() {
        ErpmPurchasechallanDetail PCDetail = (ErpmPurchasechallanDetail) getCurrentRowObject();
        if (PCDetail.getErpmItemMaster().getErpmimSerialNoApplicable() == 'Y') {
            return "View Serial Detail";
        } else {
            return "Edit item";
        }
    }


    public String geteditviewinvoice() {
        ErpmPurchaseinvoiceDetail epid = (ErpmPurchaseinvoiceDetail) getCurrentRowObject();
        if (epid.getErpmItemMaster().getErpmimSerialNoApplicable() == 'Y') {
            return "View Serial Detail";
        } else {
            return "Edit item";
        }
    }

    //this method used to connvert RecvDate date (yyyy-mm-dd)  PChallanMastList from managepurchase challanaction class  in formate(dd-mm-yyyy)
    public String getformattedRecvDate() {
        //getting current row object in string form
        String str = getCurrentRowObject().toString();
        //getting index of  RecvDate= and , from str
        int firstindex = str.indexOf("RecvDate=");
        int secondindex = str.indexOf(",", firstindex);
        //getting string datestr between two index from str
        String datestr = str.substring(firstindex + 9, secondindex);
        //convert datestr to date formate
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date today = null;
        try {
            today = df.parse(datestr.trim());
            DateUtilities dt = new DateUtilities();
            try {
                return dt.convertDateToString(today, "dd-MM-yyyy");
            } catch (Exception e) {
                return "Error in date formatting";
            }
        } catch (ParseException e) {
            return "" + e;
        }



    }

    //this method used to connvert ChallanDate date (yyyy-mm-dd)  PChallanMastList from managepurchase challanaction class  in formate(dd-mm-yyyy)
    public String getformattedChallanDate() {

        //getting current row object in string form

        String str = getCurrentRowObject().toString();
        //getting index of  RecvDate= and , from str

        int firstindex = str.indexOf("ChallanDate=");
        int secondindex = str.indexOf(",", firstindex);
        //getting string datestr between two index from str

        String datestr = str.substring(firstindex + 12, secondindex);
        //convert datestr to date formate

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date today = null;
        try {
            today = df.parse(datestr.trim());
            DateUtilities dt = new DateUtilities();
            try {
                return dt.convertDateToString(today, "dd-MM-yyyy");
            } catch (Exception e) {
                return "Error in date formatting";
            }
        } catch (ParseException e) {
            return "" + e;
        }

        //return "hello";

    }

    //this method ued to convert date InvoiceRecvdDate (yyyy-mm-dd) to (dd-mm-yyyy) in Purchase invoice
    public String getformattedpimInvoiceRecvdDate() {
        ErpmPurchaseinvoiceMaster erpmpim = (ErpmPurchaseinvoiceMaster) getCurrentRowObject();
        DateUtilities dt = new DateUtilities();
        try {
            return dt.convertDateToString(erpmpim.getPimInvoiceRecvdDate(), "dd-MM-yyyy");
        } catch (Exception e) {
            return "Error in date formatting";
        }

    }
    public String getformattedInvoiceRecvdDate() {
        ErpmPurchaseinvoiceMaster erpmpim = (ErpmPurchaseinvoiceMaster) getCurrentRowObject();
        DateUtilities dt = new DateUtilities();
        try {
            return dt.convertDateToString(erpmpim.getPimInvoiceRecvdDate(), "dd-MM-yyyy");
        } catch (Exception e) {
            return "Error in date formatting";
        }

    }
}

