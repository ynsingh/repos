/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

/**
 *
 * @author farah
 * Updated By : Tanvir Ahmed
 */
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.SuppliermasterDAO;
import pojo.hibernate.ErpmItemMasterDAO;
import pojo.hibernate.ErpmStockReceived;
import pojo.hibernate.ErpmTempOpeningStock;
import pojo.hibernate.Suppliermaster;
import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmItemCategoryMaster;
import pojo.hibernate.ErpmItemCategoryMasterDao;
import java.sql.DriverManager;
import org.apache.struts2.ServletActionContext;
import net.sf.jasperreports.engine.*;
import java.io.*;
import java.sql.Connection;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import utils.DateUtilities;
import org.apache.struts2.interceptor.validation.SkipValidation;
import utils.DevelopmentSupport;
import java.text.*;

import java.util.Locale;
import java.util.ResourceBundle;
import com.opensymphony.xwork2.ActionContext;

public class ManageStockReports extends DevelopmentSupport {
    private String message;
    private ErpmTempOpeningStock tos = new ErpmTempOpeningStock();
    private ErpmItemCategoryMaster erpmItemCategoryMaster = new ErpmItemCategoryMaster();
    private ErpmItemCategoryMasterDao erpmItemCategoryMasterDao = new ErpmItemCategoryMasterDao();
    private Suppliermaster supplier = new Suppliermaster();
    private SuppliermasterDAO supplierdao = new SuppliermasterDAO();
    private ErpmItemMasterDAO itemDao = new ErpmItemMasterDAO();
    private ErpmStockReceived esr = new ErpmStockReceived();
    private List<Institutionmaster> tosImIdList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Subinstitutionmaster> tosSimImIdList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Departmentmaster> tosDmList = new ArrayList<Departmentmaster>();
    private List<Suppliermaster> tosSmList = new ArrayList<Suppliermaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList1 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList2 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList3 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemMaster> tosINList = new ArrayList<ErpmItemMaster>();
    private String fromDate;
    private String toDate;
    private String tosInStockSince;
    private String tosPoDate;
    private String tosChallanDate;
    private String tosInvoiceDate;
    
    private Short i = 1;
    private Short j = 2;
    private Short k = 3;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ErpmTempOpeningStock getTos() {
        return tos;
    }

    /**
     * @param tos the tos to set
     */
    public void setTos(ErpmTempOpeningStock tos) {
        this.tos = tos;
    }

  
    //getter setter for fromdate,todate

    public void setfromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String gettoDate() {
        return toDate;
    }

    public String getfromDate() {
        return fromDate;
    }

    public void settoDate(String toDate) {
        this.toDate = toDate;
    }

   

    /**
     * @return the tosImIdList
     */
    public List<Institutionmaster> getTosImIdList() {
        return tosImIdList;
    }

    /**
     * @param tosImIdList the tosImIdList to set
     */
    public void setTosImIdList(List<Institutionmaster> tosImIdList) {
        this.tosImIdList = tosImIdList;
    }

   

    public List<ErpmItemMaster> getTosINList() {
        return tosINList;
    }

    /**
     * @param tosINList the tosINList to set
     */
    public void setTosINList(List<ErpmItemMaster> tosINList) {
        this.setTosINList(tosINList);
    }

    /**
     * @return the tosSimImIdList
     */
    public List<Subinstitutionmaster> getTosSimImIdList() {
        return tosSimImIdList;
    }

    /**
     * @param tosSimImIdList the tosSimImIdList to set
     */
    public void setTosSimImIdList(List<Subinstitutionmaster> tosSimImIdList) {
        this.tosSimImIdList = tosSimImIdList;
    }

    /**
     * @return the tosDmList
     */
    public List<Departmentmaster> getTosDmList() {
        return tosDmList;
    }

    /**
     * @param tosDmList the tosDmList to set
     */
    public void setTosDmList(List<Departmentmaster> tosDmList) {
        this.tosDmList = tosDmList;
    }

    /**
     * @return the tosSmList
     */
    public List<Suppliermaster> getTosSmList() {
        return tosSmList;
    }

    /**
     * @param tosSmList the tosSmList to set
     */
    public void setTosSmList(List<Suppliermaster> tosSmList) {
        this.tosSmList = tosSmList;
    }

    public List<ErpmItemCategoryMaster> getErpmIcmList1() {
        return erpmIcmList1;
    }

    /**
     * @param erpmIcmList1 the erpmIcmList1 to set
     */
    public void setErpmIcmList1(List<ErpmItemCategoryMaster> erpmIcmList1) {
        this.erpmIcmList1 = erpmIcmList1;
    }

    /**
     * @return the erpmIcmList2
     */
    public List<ErpmItemCategoryMaster> getErpmIcmList2() {
        return erpmIcmList2;
    }

    /**
     * @param erpmIcmList2 the erpmIcmList2 to set
     */
    public void setErpmIcmList2(List<ErpmItemCategoryMaster> erpmIcmList2) {
        this.erpmIcmList2 = erpmIcmList2;
    }

    /**
     * @return the erpmIcmList3
     */
    public List<ErpmItemCategoryMaster> getErpmIcmList3() {
        return erpmIcmList3;
    }

    /**
     * @param erpmIcmList3 the erpmIcmList3 to set
     */
    public void setErpmIcmList3(List<ErpmItemCategoryMaster> erpmIcmList3) {
        this.erpmIcmList3 = erpmIcmList3;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

   
  

    /**
     * @return the supplier
     */
    public Suppliermaster getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(Suppliermaster supplier) {
        this.supplier = supplier;
    }

   
    
    /**
     * @return the erpmItemCategoryMaster
     */
    public ErpmItemCategoryMaster getErpmItemCategoryMaster() {
        return erpmItemCategoryMaster;
    }

    /**
     * @param erpmItemCategoryMaster the erpmItemCategoryMaster to set
     */
    public void setErpmItemCategoryMaster(ErpmItemCategoryMaster erpmItemCategoryMaster) {
        this.erpmItemCategoryMaster = erpmItemCategoryMaster;
    }

   
    /**
     * @return the esr
     */
    public ErpmStockReceived getEsr() {
        return esr;
    }

    /**
     * @param esr the esr to set
     */
    public void setEsr(ErpmStockReceived esr) {
        this.esr = esr;
    }

  
   
    public String getTosInStockSince() {
        return tosInStockSince;
    }

    public void setTosInStockSince(String tosInStockSince) {
        this.tosInStockSince = tosInStockSince;
    }

    public String getTosChallanDate() {
        return tosChallanDate;
    }

    public void setTosChallanDate(String tosChallanDate) {
        this.tosChallanDate = tosChallanDate;
    }

    public String getTosInvoiceDate() {
        return tosInvoiceDate;
    }

    public void setTosInvoiceDate(String tosInvoiceDate) {
        this.tosInvoiceDate = tosInvoiceDate;
    }

    public String getTosPoDate() {
        return tosPoDate;
    }

    public void setTosPoDate(String tosPoDate) {
        this.tosPoDate = tosPoDate;
    }




    @Override
    public String execute() throws Exception {
        try {
            InitializeLOVs();

            //Set Indent Date
            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            setTosInStockSince(d1.convertDateToString(d, "dd-MM-yyyy"));
            setTosChallanDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            setTosInvoiceDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            setTosPoDate(d1.convertDateToString(d, "dd-MM-yyyy"));

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ManageOpeningStockAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public void InitializeLOVs() {

        //Initialise List for Institution, Sub-Institution, Department,Supplier and Item.
        tosImIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        tosSimImIdList = simDao.findBysimImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        tosDmList = dmDao.findBydmSimId(Integer.valueOf(getSession().getAttribute("simId").toString()));
        tosSmList = supplierdao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        erpmIcmList1 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(i);
        erpmIcmList2 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(j);
        erpmIcmList3 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(k);
        tosINList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        tos.setTosBatchId(getSession().getAttribute("username").toString());
        return;
    }

    
    public void validate() {
        try {

            //Validations on Supplier data,Institution,subinstitution,department,item,unit rate,CsrNo,CsrPgNo,DeptSRno,DeptSRPgNo
            if (tos.getInstitutionmaster().getImId() == null) {
                addFieldError("tos.institutionmaster.imId", "Please Select Institution Name.");

            }
            if (tos.getSubinstitutionmaster().getSimId() == null) {
                addFieldError("tos.subinstitutionmaster.simId", "Please Select SubInstitution Name.");
            }
            if (tos.getDepartmentmaster().getDmId() == null) {
                addFieldError("tos.departmentmaster.dmId", "Please Select Department Name.");
            }
            if (tos.getSuppliermaster().getSmId() == null) {
                addFieldError("tos.suppliermaster.smId", "Please Select Supplier Name");
            }

            if (tos.getErpmItemMaster().getErpmimId() == null) {
                addFieldError("tos.erpmItemMaster.erpmimId", "Please Select Item Name");
            }
//
//          

            if (tos.getTosUnitRate() == null) {
                addFieldError("tos.tosUnitRate", "Please Enter Unit Rate");
            }

            if (tos.getTosCsrNo().length() == 0) {
                addFieldError("tos.tosCsrNo", "Please Enter Central Stock Register No.");
            }

            if (tos.getTosCsrPgNo() == 0) {
                addFieldError("tos.tosCsrPgNo", "Please Enter Central Stock Register Page No.");
            }

            if (tos.getTosDeptSrNo().length() == 0) {
                addFieldError("tos.tosDeptSrNo", "Please Enter Departmental Stock Register No.");
            }

            if (tos.getTosDeptSrPgNo() == 0) {
                addFieldError("tos.tosDeptSrPgNo", "Please Enter Departmental Stock Register Page No.");
            }


            DateUtilities dt = new DateUtilities();

            if (getTosInStockSince().length() != 0) {
                if (dt.isValidDate(getTosInStockSince()) == false) {
                    addFieldError("tosInStockSince", "Please give valid In Stock Since Date [dd-mm-yyyy]");
                }
            }

            if (getTosPoDate().length() != 0) {
                if (dt.isValidDate(getTosPoDate()) == false) {
                    addFieldError("tosPoDate", "Please give valid Purchase Order Date [dd-mm-yyyy]");
                }
            }

            if (getTosChallanDate().length() != 0) {
                if (dt.isValidDate(getTosChallanDate()) == false) {
                    addFieldError("tosChallanDate", "Please give valid Challan Date [dd-mm-yyyy]");
                }
            }

            if (getTosInvoiceDate().length() != 0) {
                if (dt.isValidDate(getTosInvoiceDate()) == false) {
                    addFieldError("tosInvoiceDate", "Please give valid Invoice Date [dd-mm-yyyy]");
                }
            }

        

            InitializeLOVs();

        } catch (NullPointerException npe) {
        }
    }


    @SkipValidation
    public String PrintStockSummary() throws Exception {
        HashMap hm = new HashMap();
        String frmDate = null;
        String toDate = null;
        //assign value to var frmDate ,toDate
        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }


        java.util.Date d1 = null;
        java.util.Date d2 = null;
        DateUtilities dt = new DateUtilities();

        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
  			d2 = dt.convertStringToDate(toDate.trim());
         
        }
        	else{
             try{
            	d2 = dt.convertStringToDate(toDate.trim());
            	toDate=dt.convertDateToString(d2, "dd-MM-yyyy");

            }catch(Exception e){} 
        }
        if (frmDate.isEmpty()) {
            //SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
           //Calendar originalDate = Calendar.getInstance();
           // System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
            d1 = dt.convertStringToDate(frmDate.trim());
        }
        else{
              try{
            d1 = dt.convertStringToDate(frmDate.trim());
             frmDate=dt.convertDateToString(d1, "dd-MM-yyyy");

        } catch (Exception e) {}
        }
//check fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {

            // Get System properties
            Properties properties = System.getProperties();

            // Get the path separator symbol, which is unfortunatly different, in different OS platform.
            String pathSeparator = properties.getProperty("file.separator");

            pathSeparator =pathSeparator + pathSeparator;
            String repPath = "pico" + pathSeparator + "Inventory"  + pathSeparator + "Reports" + pathSeparator + "Stock Summary.jasper" ;

            String fileName = getSession().getServletContext().getRealPath(repPath);
//            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\Stock Summary.jasper");


            String whereCondition;

            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=Stock Summary.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();


                //Setup Where Condition Clause

                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = "erpm_stock_received.ST_Item_ID <> 0 ";
                } else {
                    whereCondition = "erpm_stock_received.ST_Item_ID =" + esr.getErpmItemMaster().getErpmimId();
                }



                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_IM_ID = " + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_IM_ID= " + esr.getInstitutionmaster().getImId();
                }



                if (esr.getSubinstitutionmaster().getSimId() == null)// || esr.getSubinstitutionmaster().getSimId()==0)
                {
                    whereCondition = whereCondition + " and  erpm_stock_received.ST_SIM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_SIM_ID= " + esr.getSubinstitutionmaster().getSimId();
                }



                if (esr.getDepartmentmaster().getDmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_DM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_DM_ID= " + esr.getDepartmentmaster().getDmId();
                }



                if (esr.getSuppliermaster().getSmId() != null) {
                    whereCondition = whereCondition + " and erpm_stock_received.st_sm_id= " + esr.getSuppliermaster().getSmId();
                }

                if (frmDate.trim().length() != 10) {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (toDate.trim().length() != 10) {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since <= str_to_date('" + toDate + "','%d-%m-%Y')";
                }


                hm.put("condition", whereCondition);

                JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
                JasperExportManager.exportReportToPdfStream(jp, baos);
                response.setContentLength(baos.size());
                ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
                setInputStream(bis);



                return SUCCESS;
            } catch (JRException e) {
                message = "Error is : " + e.getMessage() + e.getCause();
                return ERROR;
            }
        }
        return "input";
    }

    //method to genrate report for item details
    @SkipValidation
    public String Printstockdetail() throws Exception {
        HashMap hm = new HashMap();
        // message=""+esr.getErpmItemMaster().getErpmimId();
        String frmDate = null;
        //String toDate = null;
        //assign value to var frmDate ,toDate
        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }


        java.util.Date d1 = null;
        java.util.Date d2 = null;
        DateUtilities dt = new DateUtilities();

        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
            d2 = dt.convertStringToDate(toDate.trim());
            System.out.println("d2 : " +d2);

         
        }
        else{
             try{
            d2 = dt.convertStringToDate(toDate.trim());
            toDate=dt.convertDateToString(d2, "dd-MM-yyyy");

            }catch(Exception e){} 
        }
        if (frmDate.isEmpty()) {
            //SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
           //Calendar originalDate = Calendar.getInstance();
           // System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
            d1 = dt.convertStringToDate(frmDate.trim());
            System.out.println("d1 : " +d1);


        }
        else{
              try{
            d1 = dt.convertStringToDate(frmDate.trim());
             frmDate=dt.convertDateToString(d1, "dd-MM-yyyy");

        } catch (Exception e) {}
        }
//chech fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {

            // Get System properties
            Properties properties = System.getProperties();

            // Get the path separator symbol, which is unfortunatly different, in different OS platform.
            String pathSeparator = properties.getProperty("file.separator");

            pathSeparator =pathSeparator + pathSeparator;
            String repPath = "pico" + pathSeparator + "Inventory"  + pathSeparator + "Reports" + pathSeparator + "STOCK DETAILS.jasper" ;

            String fileName = getSession().getServletContext().getRealPath(repPath);
//            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\STOCK DETAILS.jasper");


            String whereCondition;

            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=STOCK DETAILS.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                // message=""+esr.getErpmItemMaster().getErpmimId();
                //Setup Where Condition Clause  whereCondition = "erpm_stock_received.ST_Item_ID <> 0 ";


                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = "erpm_stock_received.ST_Item_ID <> 0 ";
                } else {
                    whereCondition = "erpm_stock_received.ST_Item_ID =" + esr.getErpmItemMaster().getErpmimId();
                }



                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_IM_ID =" + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_IM_ID= " + esr.getInstitutionmaster().getImId();
                }



                if (esr.getSubinstitutionmaster().getSimId() == null)// || esr.getSubinstitutionmaster().getSimId()==0)
                {
                    whereCondition = whereCondition + " and  erpm_stock_received.ST_SIM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_SIM_ID= " + esr.getSubinstitutionmaster().getSimId();
                }



                if (esr.getDepartmentmaster().getDmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_DM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_DM_ID= " + esr.getDepartmentmaster().getDmId();
                }



                /*   if(esr.getSuppliermaster().getSmId() == null)
                 whereCondition = whereCondition + " and erpm_stock_received.ST_SM_ID= <>0";
                 else
                 whereCondition = whereCondition + " and erpm_stock_received.ST_SM_ID= " + esr.getSuppliermaster().getSmId();*/

                if (frmDate.trim().length() != 10) {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (toDate.trim().length() != 10) {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_stock_received.ST_In_Stock_Since <= str_to_date('" + toDate + "','%d-%m-%Y')";
                }


                hm.put("condition", whereCondition);

                JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
                JasperExportManager.exportReportToPdfStream(jp, baos);
                response.setContentLength(baos.size());
                ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
                //setInputStream(bis);
                inputStream = bis;



                return SUCCESS;
            } catch (Exception e) {
                message = "Error is : " + e.getMessage() + e.getCause();
                return ERROR;
            }
        }
        return "input";
    }

    

    @SkipValidation
    public String IssuesPendingReport() throws Exception {
        HashMap hm = new HashMap();
        String frmDate = null;
        String toDate = null;
        //assign value to var frmDate ,toDate
        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }

        java.util.Date d1 = null;
        java.util.Date d2 = null;
        DateUtilities dt = new DateUtilities();

        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
            d2 = dt.convertStringToDate(toDate.trim());

         
        }
        else{
             try{
            d2 = dt.convertStringToDate(toDate.trim());
            toDate=dt.convertDateToString(d2, "dd-MM-yyyy");

            }catch(Exception e){} 
        }
        if (frmDate.isEmpty()) {
            //SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
           //Calendar originalDate = Calendar.getInstance();
           // System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
            d1 = dt.convertStringToDate(frmDate.trim());

        }
        else{
              try{
            d1 = dt.convertStringToDate(frmDate.trim());
             frmDate=dt.convertDateToString(d1, "dd-MM-yyyy");

        } catch (Exception e) {}
        }
//check fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {

            // Get System properties
            Properties properties = System.getProperties();

            // Get the path separator symbol, which is unfortunatly different, in different OS platform.
            String pathSeparator = properties.getProperty("file.separator");

            pathSeparator = pathSeparator + pathSeparator;
            String repPath = "pico" + pathSeparator + "Inventory"  + pathSeparator + "Reports" + pathSeparator + "IssuesPendingToBeReceive.jasper" ;

            String fileName = getSession().getServletContext().getRealPath(repPath);
//            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\IssuesPendingToBeReceive.jasper");


            String whereCondition = "";

            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=IssuesPendingToBeReceive.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                whereCondition = "";
//       Setup Where Condition Clause
                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_From_Institute_ID = " + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_From_Institute_ID = " + esr.getInstitutionmaster().getImId();
                }

                if (esr.getSubinstitutionmaster().getSimId() == null) {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_From_Subinstitute_ID = " + getSession().getAttribute("simId");
                } else {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_From_Subinstitute_ID = " + esr.getSubinstitutionmaster().getSimId();
                }

                if (esr.getDepartmentmaster().getDmId() == null) {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_From_Department_ID = " + getSession().getAttribute("dmId");
                } else {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_From_Department_ID = " + esr.getDepartmentmaster().getDmId();
                }


//        if(esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat1().getErpmicmItemId()==0)
//            whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat1 <> 0";
//        else
//            whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat1 = " + esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat1().getErpmicmItemId();

//        if(esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat2()==null)
//            whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat2 <> " + esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat2().getErpmicmItemId();
//        else
//            whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat2 = " + esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat2().getErpmicmItemId();
//
//        if(esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat3()==null)
//            whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat3 <> " + esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat3().getErpmicmItemId();
//        else
//            whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat3 = " + esr.getErpmItemMaster().getErpmItemCategoryMasterByErpmimItemCat3().getErpmicmItemId();

                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = whereCondition + " AND erpm_issue_detail.ISD_Item_ID <> 0";
                } else {
                    whereCondition = whereCondition + " AND erpm_issue_detail.ISD_Item_ID = " + esr.getErpmItemMaster().getErpmimId();
                }
                if (frmDate.trim().length() != 10) {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_Issue_Date >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_Issue_Date >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (toDate.trim().length() != 10) {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_Issue_Date <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_issue_master.ISM_Issue_Date <= str_to_date('" + toDate + "','%d-%m-%Y')";
                }


                hm.put("condition", whereCondition);

                JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
                JasperExportManager.exportReportToPdfStream(jp, baos);
                response.setContentLength(baos.size());
                ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
                inputStream = bis;

                return SUCCESS;
            } catch (Exception e) {
                message = "Exception -> " + e.getMessage() + e.getCause();
                return ERROR;
            }
        }
        return "input";
    }

    @SkipValidation
    public String PrintStockInHand() throws Exception {
        String frmDate = null;
        String toDate = null;
        //assign value to var frmDate ,toDate

        HashMap hm = new HashMap();

        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }

        java.util.Date d1 = null;
        java.util.Date d2 = null;
        DateUtilities dt = new DateUtilities();

        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
            d2 = dt.convertStringToDate(toDate.trim());

         
        }
        else{
             try{
            d2 = dt.convertStringToDate(toDate.trim());
            toDate=dt.convertDateToString(d2, "dd-MM-yyyy");

            }catch(Exception e){} 
        }
        if (frmDate.isEmpty()) {
            //SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
           //Calendar originalDate = Calendar.getInstance();
           // System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
            d1 = dt.convertStringToDate(frmDate.trim());

        }
        else{
              try{
            d1 = dt.convertStringToDate(frmDate.trim());
             frmDate=dt.convertDateToString(d1, "dd-MM-yyyy");

        } catch (Exception e) {}
        }
//check fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {

            // Get System properties
            Properties properties = System.getProperties();

            // Get the path separator symbol, which is unfortunatly different, in different OS platform.
            String pathSeparator = properties.getProperty("file.separator");

            pathSeparator =pathSeparator + pathSeparator;
            String repPath = "pico" + pathSeparator + "Inventory"  + pathSeparator + "Reports" + pathSeparator + "Stock_In_Hand_Report.jasper" ;

            String fileName = getSession().getServletContext().getRealPath(repPath);
//            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\Stock_In_Hand_Report.jasper");

            String whereCondition = "";

            try {

                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

                HttpServletResponse response = ServletActionContext.getResponse();

                response.setHeader("Cache-Control", "no-cache");

                response.setHeader("Content-Disposition", "attachment; filename=Stock_In_Hand_Report.pdf");

                response.setHeader("Expires", "0");

                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");

                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();


                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = " view_item_ledger.Item_ID <> 0 ";//
                } else {
                    whereCondition = "view_item_ledger.Item_ID =" + esr.getErpmItemMaster().getErpmimId();
                }



                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and view_item_ledger.IM_ID =" + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and view_item_ledger.IM_ID= " + esr.getInstitutionmaster().getImId();
                }



                if (esr.getSubinstitutionmaster().getSimId() == null)// || esr.getSubinstitutionmaster().getSimId()==0)
                {
                    whereCondition = whereCondition + " and  view_item_ledger.SIM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and  view_item_ledger.SIM_ID= " + esr.getSubinstitutionmaster().getSimId();
                }



                if (esr.getDepartmentmaster().getDmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and  view_item_ledger.DM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and  view_item_ledger.DM_ID= " + esr.getDepartmentmaster().getDmId();
                }
                if (frmDate.trim().length() != 10) {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (toDate.trim().length() != 10) {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and  view_item_ledger.Dated <= str_to_date('" + toDate + "','%d-%m-%Y')";
                }


                hm.put("condition", whereCondition);

                JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);

                JasperExportManager.exportReportToPdfStream(jp, baos);

                response.setContentLength(baos.size());

                ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());

                inputStream = bis;
                return "success";

            } catch (JRException e) {
                message = "Error is : " + e.getMessage() + e.getCause();
                return ERROR;

            }

        }
        return "input";
    }
    //this report used general finacial resourse information

    @SkipValidation
    public String PrintGFRReport40() throws Exception {
        String frmDate = null;
        String toDate = null;
        //assign value to var frmDate ,toDate

        HashMap hm = new HashMap();

        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }

        java.util.Date d1 = null;
        java.util.Date d2 = null;
        DateUtilities dt = new DateUtilities();

        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());

                     d2 = dt.convertStringToDate(toDate.trim());

        }
        else{
             try{
            d2 = dt.convertStringToDate(toDate.trim());
            toDate=dt.convertDateToString(d2, "dd-MM-yyyy");

            }catch(Exception e){} 
        }
        if (frmDate.isEmpty()) {
            //SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
           //Calendar originalDate = Calendar.getInstance();
           // System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
             d1 = dt.convertStringToDate(frmDate.trim());

        }
        else{
              try{
            d1 = dt.convertStringToDate(frmDate.trim());
             frmDate=dt.convertDateToString(d1, "dd-MM-yyyy");

        } catch (Exception e) {}
        }
//check fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {
            System.out.println(toDate+"The original date is : " +frmDate);

            // Get System properties
            Properties properties = System.getProperties();

            // Get the path separator symbol, which is unfortunatly different, in different OS platform.
            String pathSeparator = properties.getProperty("file.separator");

            pathSeparator =pathSeparator + pathSeparator;
            String repPath = "pico" + pathSeparator + "Inventory"  + pathSeparator + "Reports" + pathSeparator + "GFRReport40.jasper" ;

            String fileName = getSession().getServletContext().getRealPath(repPath);
//            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\GFRReport40.jasper");
            String whereCondition = "";

            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 
                
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=GFRReport40.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //Setup Where Condition Clause

                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = " view_stock_received.ST_Item_ID <> 0 ";//
                } else {
                    whereCondition = "view_stock_received.ST_Item_ID =" + esr.getErpmItemMaster().getErpmimId();
                }



                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and view_stock_received.ST_IM_ID =" + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and view_stock_received.ST_IM_ID = " + esr.getInstitutionmaster().getImId();
                }



                if (esr.getSubinstitutionmaster().getSimId() == null)// || esr.getSubinstitutionmaster().getSimId()==0)
                {
                    whereCondition = whereCondition + " and   view_stock_received.ST_SIM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and  view_stock_received.ST_SIM_ID = " + esr.getSubinstitutionmaster().getSimId();
                }



                if (esr.getDepartmentmaster().getDmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and  view_stock_received.ST_DM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and  view_stock_received.ST_DM_ID = " + esr.getDepartmentmaster().getDmId();
                }
                if (esr.getSuppliermaster().getSmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and   view_stock_received.ST_SM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and   view_stock_received.ST_SM_ID = " + esr.getSuppliermaster().getSmId();
                }
                if (frmDate.trim().length() != 10) {
                    whereCondition = whereCondition + " and view_stock_received.ST_In_Stock_Since >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and  view_stock_received.ST_In_Stock_Since >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (toDate.trim().length() != 10) {
                    whereCondition = whereCondition + " and  view_stock_received.ST_In_Stock_Since <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and  view_stock_received.ST_In_Stock_Since <= str_to_date('" + toDate + "','%d-%m-%Y')";
                }



                hm.put("Condition", whereCondition);
                JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
                JasperExportManager.exportReportToPdfStream(jp, baos);
                response.setContentLength(baos.size());
                ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
                inputStream = bis;

                return "success";
            } catch (JRException e) {
                message = "Error is : " + e.getMessage() + e.getCause();
                return ERROR;
            }
        }
        return "input";
    }

    @SkipValidation
    public String PrintGFRReport41() throws Exception {
        String frmDate = null;
        String toDate = null;
        //assign value to var frmDate ,toDate

        HashMap hm = new HashMap();

        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }

        java.util.Date d1 = null;
        java.util.Date d2 = null;
        DateUtilities dt = new DateUtilities();

        //check dates are empty or not
        if (toDate.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
            Calendar originalDate = Calendar.getInstance();
            System.out.println("The original date is : " + originalDate.getTime());
            toDate = format.format(originalDate.getTime());
            d2 = dt.convertStringToDate(toDate.trim());

         
        }
        else{
             try{
            d2 = dt.convertStringToDate(toDate.trim());
            toDate=dt.convertDateToString(d2, "dd-MM-yyyy");

            }catch(Exception e){} 
        }
        if (frmDate.isEmpty()) {
            //SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            // get the date in String format by passing current date
            // in format method of simple date format object
           //Calendar originalDate = Calendar.getInstance();
           // System.out.println("The original date is : " + originalDate.getTime());
            frmDate = "11-11-2000";
           d1 = dt.convertStringToDate(frmDate.trim());

        }
        else{
              try{
            d1 = dt.convertStringToDate(frmDate.trim());
             frmDate=dt.convertDateToString(d1, "dd-MM-yyyy");

            }catch(Exception e){}
        }
//check fromdate should be before to todate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {
            System.out.println(toDate+"The original date is : " +frmDate);

            // Get System properties
            Properties properties = System.getProperties();

            // Get the path separator symbol, which is unfortunatly different, in different OS platform.
            String pathSeparator = properties.getProperty("file.separator");

            pathSeparator =pathSeparator + pathSeparator;
            String repPath = "pico" + pathSeparator + "Inventory"  + pathSeparator + "Reports" + pathSeparator + "GFRReport41.jasper" ;

            String fileName = getSession().getServletContext().getRealPath(repPath);
//            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\GFRReport41.jasper");
            String whereCondition;

            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 
                
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=GFRReport41.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //Setup Where Condition Clause

                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = "view_item_ledger.Item_ID <> 0 ";//
                } else {
                    whereCondition = "view_item_ledger.Item_ID =" + esr.getErpmItemMaster().getErpmimId();
                }

                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " and view_item_ledger.IM_ID =" + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and view_item_ledger.IM_ID = " + esr.getInstitutionmaster().getImId();
                }

                if (esr.getSubinstitutionmaster().getSimId() == null)// || esr.getSubinstitutionmaster().getSimId()==0)
                {
                    whereCondition = whereCondition + " and view_item_ledger.SIM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and view_item_ledger.SIM_ID = " + esr.getSubinstitutionmaster().getSimId();
                }

                if (esr.getDepartmentmaster().getDmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " and view_item_ledger.DM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " and view_item_ledger.DM_ID = " + esr.getDepartmentmaster().getDmId();
                }
                /* if(esr.getSuppliermaster().getSmId()!= null)
                 whereCondition = whereCondition + " and  view_item_ledger.Supplier_Name = " + esr.getSuppliermaster().getSmName();*/
                if (frmDate.trim().length() != 10) {
                    whereCondition = whereCondition + " and view_item_ledger.Dated >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and view_item_ledger.Dated >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (toDate.trim().length() != 10) {
                    whereCondition = whereCondition + " and view_item_ledger.Dated <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and view_item_ledger.Dated <= str_to_date('" + toDate + "','%d-%m-%Y')";
                }

                hm.put("condition", whereCondition);
                JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
                JasperExportManager.exportReportToPdfStream(jp, baos);
                response.setContentLength(baos.size());
                ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
                inputStream = bis;

                return "success";
            } catch (JRException e) {
                message = "Error is : " + e.getMessage() + e.getCause();
                return ERROR;
            }
        }
        return "input";
    }

    @SkipValidation
    public String PrintStockRegister() throws Exception {
        String fDate = null;
        String tDate = null;
        //assign value to var fDate ,tDate

        HashMap hm = new HashMap();

        try {
            fDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            tDate = gettoDate();
        } catch (Exception e) {
        }

        Date d1 = null;
        Date d2 = null;
        DateUtilities dt = new DateUtilities();

        //check dates are empty or not
        if (fDate.isEmpty()) {
           fDate = "2000-11-11";
           d1 = dt.convertStringToDate("11-11-2000");
        }
        else {
           d1 = dt.convertStringToDate(fDate.trim());
           fDate=dt.convertDateToString(d1, "yyyy-MM-dd");
        }
        if (tDate.isEmpty()) {
           d2 = new Date();
           tDate = dt.convertDateToString(d2, "yyyy-MM-dd");
        }
        else{
            d2 = dt.convertStringToDate(tDate.trim());
            tDate=dt.convertDateToString(d2, "yyyy-MM-dd");
        }
//check fromdate should be before to tdate
        if (d1.compareTo(d2) > 0) {
            message = "Please Enter Correct Date";
            InitializeLOVs();
        } else {
            System.out.println(tDate+"The Start date is : " +fDate);

            // Get System properties
            Properties properties = System.getProperties();

            // Get the path separator symbol, which is unfortunatly different, in different OS platform.
            String pathSeparator = properties.getProperty("file.separator");

            pathSeparator =pathSeparator + pathSeparator;
            String repPath = "pico" + pathSeparator + "Inventory"  + pathSeparator + "Reports" + pathSeparator + "Stock_Register.jasper" ;

            String fileName = getSession().getServletContext().getRealPath(repPath);
//            String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\Stock_Register.jasper");
            String whereCondition;

            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 
                
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=Stock_Register.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //Setup Where Condition Clause

                if (esr.getErpmItemMaster().getErpmimId() == null) {
                    whereCondition = "Item_ID <> 0 ";
                } else {
                    whereCondition = "Item_ID =" + esr.getErpmItemMaster().getErpmimId();
                }


                if (esr.getInstitutionmaster().getImId() == null) {
                    whereCondition = whereCondition + " AND IM_ID =" + getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " AND IM_ID = " + esr.getInstitutionmaster().getImId();
                }


                if (esr.getSubinstitutionmaster().getSimId() == null) {
                    whereCondition = whereCondition + " AND SIM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " AND SIM_ID = " + esr.getSubinstitutionmaster().getSimId();
                }



                if (esr.getDepartmentmaster().getDmId() == null)// || cm.getDepartmentmaster().getDmId() == 0)
                {
                    whereCondition = whereCondition + " AND DM_ID <> 0 ";
                } else {
                    whereCondition = whereCondition + " AND DM_ID = " + esr.getDepartmentmaster().getDmId();
                }

//                whereCondition = whereCondition + " " + fDate + " " + tDate;
                String varCondition1;
                String varCondition2;
                
                varCondition1 = "Dated < '" + fDate + "' AND " + whereCondition;   //Dated < $P!{frmDate} AND $P!{condition}
                varCondition2 = "Dated >= '" + fDate + "' AND Dated <= '" + tDate + "' AND " + whereCondition;   //Dated >= $P!{frmDate} AND Dated <= $P!{toDate} AND $P!{condition}
                
                hm.put("condition1", varCondition1);
                hm.put("condition2", varCondition2);
                hm.put("frmDate", d1);
                hm.put("toDate", d2);
                
                JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
                JasperExportManager.exportReportToPdfStream(jp, baos);
                response.setContentLength(baos.size());
                ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
                inputStream = bis;

                return "success";
            } catch (JRException e) {
                message = "Error is : " + e.getMessage() + e.getCause();
                return ERROR;
            }
        }
        return "input";
    }
}
