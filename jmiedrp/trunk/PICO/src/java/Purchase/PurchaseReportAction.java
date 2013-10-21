/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * @author Tanvir Ahmed and Sajid
 */
package Purchase;

import java.io.*;


import org.apache.struts2.interceptor.validation.SkipValidation;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import net.sf.jasperreports.engine.*;
import java.text.*;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;

import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;

import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;

import pojo.hibernate.Erpmusers;

import pojo.hibernate.Suppliermaster;
import pojo.hibernate.ErpmItemMasterDAO;
import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.SuppliermasterDAO;

import pojo.hibernate.ErpmPoMaster;
import pojo.hibernate.ErpmItemCategoryMaster;
import pojo.hibernate.ErpmItemCategoryMasterDao;
import java.sql.Connection;
import java.sql.DriverManager;
import utils.DevelopmentSupport;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import utils.DateUtilities;
import pojo.hibernate.ErpmPurchasechallanDetail;

import java.util.Locale;
import java.util.ResourceBundle;
import com.opensymphony.xwork2.ActionContext;

import java.util.Locale;
import java.util.ResourceBundle;
import com.opensymphony.xwork2.ActionContext;

public class PurchaseReportAction extends DevelopmentSupport {

    private Short i = 1;
    private Short j = 2;
    private Short k = 3;
    private Short l = 18;
    private String fromDate;
    private String toDate;
    private Suppliermaster suppliermaster;
    private List<ErpmItemCategoryMaster> erpmIcmList1 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList2 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList3 = new ArrayList<ErpmItemCategoryMaster>();
    private SuppliermasterDAO suppliermasterDAO = new SuppliermasterDAO();
    private List<ErpmPurchasechallanDetail> PCDetailslist = new ArrayList<ErpmPurchasechallanDetail>();
    private ErpmItemCategoryMasterDao erpmItemCategoryMasterDao = new ErpmItemCategoryMasterDao();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
    private List<Suppliermaster> suppList = new ArrayList<Suppliermaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Erpmusers> erpmuserlist = new ArrayList<Erpmusers>();
    private List<ErpmItemMaster> itemlist = new ArrayList<ErpmItemMaster>();
    private ErpmItemMasterDAO itemlistDAO = new ErpmItemMasterDAO();
    private List<ErpmPoMaster> POMasterList = new ArrayList<ErpmPoMaster>();
    Erpmusers ermu;
    private String username;
    private String message;
    private Integer supplierId, itemId, institutionId, subinstitutionId, departmentId, SubCategoryId, CategoryId, ItemTypeId, ItemNameId;
    private InputStream inputStream;
    Institutionmaster im;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setsupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getsupplierId() {
        return supplierId;
    }

    public void setitemId(Integer ItemNameId) {
        this.ItemNameId = ItemNameId;
    }

    public Integer getitemId() {
        return ItemNameId;
    }

    public void setsubinstitutionId(Integer subinstitutionId) {
        this.subinstitutionId = subinstitutionId;
    }

    public Integer getsubinstitutionId() {
        return subinstitutionId;
    }

    public void setinstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public Integer getinstitutionId() {
        return institutionId;
    }

    public void setSubCategoryId(Integer SubCategoryId) {
        this.SubCategoryId = SubCategoryId;
    }

    public Integer getCategoryId() {
        return CategoryId;
    }

    public void setItemTypeId(Integer ItemTypeId) {
        this.ItemTypeId = ItemTypeId;
    }

    public Integer getItemTypeId() {
        return itemId;
    }

    public void setItemNameId(Integer ItemNameId) {
        this.ItemNameId = ItemNameId;
    }

    public Integer getItemNameId() {
        return ItemNameId;
    }

    public void setCategoryId(Integer CategoryId) {
        this.CategoryId = CategoryId;
    }

    public Integer getSubCategoryId() {
        return SubCategoryId;
    }

    public void setdepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getdepartmentId() {
        return departmentId;
    }

    public void setPCDetailslist(List<ErpmPurchasechallanDetail> PCDetailslist) {
        this.PCDetailslist = PCDetailslist;
    }

    public List<ErpmPurchasechallanDetail> getPCDetailslist() {
        return this.PCDetailslist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getfromDate() {
        return fromDate;
    }

    public void setfromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String gettoDate() {
        return toDate;
    }

    public void settoDate(String toDate) {
        this.toDate = toDate;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getusername() {
        return username;
    }

    public List<Institutionmaster> getimList() {
        return imList;
    }

    public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public List<Subinstitutionmaster> getsimList() {
        return simList;
    }

    public void setsimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public List<Suppliermaster> getsuppList() {
        return suppList;
    }

    public void setsuppList(List<Suppliermaster> suppList) {
        this.suppList = suppList;
    }

    public List<ErpmItemCategoryMaster> geterpmIcmList1() {
        return erpmIcmList1;
    }

    public void seterpmIcmList1(List<ErpmItemCategoryMaster> erpmIcmList1) {
        this.erpmIcmList1 = erpmIcmList1;
    }

    public List<ErpmItemCategoryMaster> geterpmIcmList2() {
        return erpmIcmList2;
    }

    public void seterpmIcmList2(List<ErpmItemCategoryMaster> erpmIcmList2) {
        this.erpmIcmList2 = erpmIcmList2;
    }

    public List<ErpmItemCategoryMaster> geterpmIcmList3() {
        return erpmIcmList3;
    }

    public void seterpmIcmList3(List<ErpmItemCategoryMaster> erpmIcmList3) {
        this.erpmIcmList3 = erpmIcmList3;
    }

    public List<Departmentmaster> getdmList() {
        return dmList;
    }

    public void setdmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<ErpmPoMaster> getPOMasterList() {
        return POMasterList;
    }

    public void setPOMasterList(List<ErpmPoMaster> POMasterList) {
        this.POMasterList = POMasterList;
    }

    public List<Erpmusers> geterpmuserlist() {
        return erpmuserlist;
    }

    public void seterpmuserlist(List<Erpmusers> erpmuserlist) {
        this.erpmuserlist = erpmuserlist;
    }

    public void setitemlist(List<ErpmItemMaster> itemlist) {
        this.itemlist = itemlist;
    }

    public List<ErpmItemMaster> getitemlist() {
        return this.itemlist;
    }

    public void setSuppliermaster(Suppliermaster suppliermaster) {
        this.suppliermaster = suppliermaster;
    }

    public Suppliermaster getSuppliermaster() {
        return this.suppliermaster;
    }

    @Override
    public String execute() throws Exception {

        try {

            InitializeLOVs();


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Execute method -> ManagePurchaseChallanAxn" + e.getMessage() + e.getCause();
            return ERROR;
        }
    }

    public void InitializeLOVs() {
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));

        suppList = suppliermasterDAO.findAll();
        itemlist = itemlistDAO.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        erpmIcmList1 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(i);
        erpmIcmList2 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(j);
        erpmIcmList3 = erpmItemCategoryMasterDao.findByErpmicmItemLevel(k);
        return;
    }

    
    @SkipValidation
public String ExportPendingPO() throws Exception {

     HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Purchase"  + pathSeparator + "Reports" + pathSeparator + "PendingPOReport.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//    String fileName = getSession().getServletContext().getRealPath("pico\\Purchase\\Reports\\PendingPOReport.jasper");
    String whereCondition = "";
     try {
            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword"));

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition","attachment; filename = PendingPOReport.pdf");
            response.setHeader("Expires" , "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check = 0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            if(getinstitutionId() == null)
                whereCondition = whereCondition + "erpm_po_master.`POM_IM_ID` =" + getSession().getAttribute("imId").toString();
            else
                whereCondition = whereCondition + "erpm_po_master.`POM_IM_ID` =" + getinstitutionId();

            if(getsubinstitutionId()==null)
               whereCondition = whereCondition + " and erpm_po_master.`POM_SIM_ID` =" + getSession().getAttribute("simId").toString();
            else
               whereCondition = whereCondition + " and erpm_po_master.`POM_SIM_ID` ="+getsubinstitutionId();

            if(getdepartmentId()==null)
               whereCondition = whereCondition + " and erpm_po_master.`POM_DM_ID` =" + getSession().getAttribute("dmId").toString();
            else
               whereCondition = whereCondition + " and erpm_po_master.`POM_DM_ID` ="+getdepartmentId();

             if (getsupplierId() == null)
                    whereCondition = whereCondition + " and erpm_po_master.`POM_Supplier_ID` <> 0 ";
              else 
                    whereCondition = whereCondition + " and erpm_po_master.`POM_Supplier_ID` = " + getsupplierId();

            if (getItemNameId() == null) {
                    whereCondition = whereCondition + " and erpm_po_details.`POD_Item_ID` <> 0";
                } else {
                    whereCondition = whereCondition + " and erpm_po_details.`POD_Item_ID` =" + getItemNameId();
                }

whereCondition = whereCondition + " and erpm_po_master.`POM_PO_Master_ID` NOT IN (SELECT IF(ISNULL(PCM_PO_Master_ID), 0, PCM_PO_Master_ID) FROM erpm_purchasechallan_master)";
whereCondition = whereCondition + " and erpm_po_master.`POM_PO_Master_ID` NOT IN (SELECT IF(ISNULL(PIM_PO_Master_ID), 0, PIM_PO_Master_ID) FROM erpm_purchaseinvoice_master)";
whereCondition = whereCondition + " and erpm_po_master.`POM_Cancelled` = 'No' AND erpm_po_master.`POM_Accomplished` = 'No'";

            hm.put("condition", whereCondition);

            JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
            JasperExportManager.exportReportToPdfStream(jp,baos);
            response.setContentLength(baos.size());
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            inputStream = bis;
//            message = "Enter the details for pending Purchase Order";
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ExportPendingPO method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

}


    @SkipValidation
    public String PrintPendingBills() throws Exception {

        HashMap hm = new HashMap();
        String frmDate = null;
        String toDate = null;
        Institutionmaster institution = null;
        Subinstitutionmaster subinstitution = null;
        Departmentmaster department = null;
        Suppliermaster supplier = null;
        ErpmItemMaster itemMaster = null;
        //  message=""+getinstitutionId()+""+getsubinstitutionId()+""+getdepartmentId()+""+getsupplierId()+""+getItemNameId();

        try {
            frmDate = getfromDate();
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();
        } catch (Exception e) {
        }
        try {
            institution = imDao.findByImId(getinstitutionId().shortValue());
        } catch (Exception e) {
        }
        try {
            subinstitution = simDao.findBySimId(getsubinstitutionId());
        } catch (Exception e) {
        }
        try {
            department = dmDao.findByDmId(getdepartmentId());
        } catch (Exception e) {
        }
        try {

            supplier = suppliermasterDAO.findByErpmSMId(getsupplierId());
        } catch (Exception e) {
        }
        try {
            itemMaster = itemlistDAO.findByErpmimId(getitemId());
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


//message=""+institution.getImName()+""+subinstitution.getSimName()+""+department.getDmName()+""+supplier.getSmName();
        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Purchase"  + pathSeparator + "Reports" + pathSeparator + "PendingBill.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//            String fileName = getSession().getServletContext().getRealPath("pico\\Purchase\\Reports\\PendingBill.jasper");
            String whereCondition = "";
            //  whereCondition = " subinstitutionmaster.SIM_Id = "+subinstitution.getSimId();
            try {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

                HttpServletResponse response = ServletActionContext.getResponse();
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("Content-Disposition", "attachment; filename=PendingBill.pdf");
                response.setHeader("Expires", "0");
                response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                response.setHeader("Pragma", "public");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                //Setup Where Condition Clause
                if (getinstitutionId() == null) {
                    whereCondition = " institutionmaster.IM_Id =" + getSession().getAttribute("imId");
                } else {
                    whereCondition = "  institutionmaster.IM_Id= " + institution.getImId();
                }

                if (getsubinstitutionId() == null) {
                    whereCondition = whereCondition + " and subinstitutionmaster.SIM_Id <> 0";//getSession().getAttribute("imId");
                } else {
                    whereCondition = whereCondition + " and subinstitutionmaster.SIM_Id = " + subinstitution.getSimId();
                }

                if (getdepartmentId() == null) {
                    whereCondition = whereCondition + " and departmentmaster.DM_Id <> 0";
                } else {
                    whereCondition = whereCondition + " and departmentmaster.DM_Id = " + department.getDmId();
                }

                if (getsupplierId() == null) {
                    whereCondition = whereCondition + " and suppliermaster.SM_Id <> 0 ";
                } else {
                    whereCondition = whereCondition + " and suppliermaster.SM_Id = " + supplier.getSmId();
                }

                if (getitemId() == null) {
                    whereCondition = whereCondition + " and erpm_item_master.ERPMIM_ID <> 0";
                } else {
                    whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Id = " + itemMaster.getErpmimId();
                }

                if (frmDate.length() != 10) {
                    whereCondition = whereCondition + " and erpm_purchasechallan_master.PCM_Recv_Date >= str_to_date('01-01-2000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_purchasechallan_master.PCM_Recv_Date >= str_to_date('" + frmDate + "','%d-%m-%Y')";
                }
                if (toDate.length() != 10) {
                    whereCondition = whereCondition + " and erpm_purchasechallan_master.PCM_Recv_Date <= str_to_date('20-12-3000','%d-%m-%Y')";
                } else {
                    whereCondition = whereCondition + " and erpm_purchasechallan_master.PCM_Recv_Date <= str_to_date('" + toDate + "','%d-%m-%Y')";
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
    public String UncheckedAndUnverifiedItems() throws Exception {

     HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Purchase"  + pathSeparator + "Reports" + pathSeparator + "UncheckedAndUnverifiedItems.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//    String fileName = getSession().getServletContext().getRealPath("pico\\Purchase\\Reports\\UncheckedAndUnverifiedItems.jasper");
    String whereCondition = "";
     try {
            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword"));

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition","attachment; filename = UncheckedAndUnverifiedItems.pdf");
            response.setHeader("Expires" , "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check = 0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            if(getinstitutionId() == null)
                whereCondition = whereCondition + "erpm_purchasechallan_master.`PCM_IM_ID` =" + getSession().getAttribute("imId").toString();
            else
                whereCondition = whereCondition + "erpm_purchasechallan_master.`PCM_IM_ID` =" + getinstitutionId();

            if(getsubinstitutionId()==null)
               whereCondition = whereCondition + " and erpm_purchasechallan_master.`PCM_SIM_ID` =" + getSession().getAttribute("simId").toString();
            else
               whereCondition = whereCondition + " and erpm_purchasechallan_master.`PCM_SIM_ID` ="+getsubinstitutionId();

            if(getdepartmentId()==null)
               whereCondition = whereCondition + " and erpm_purchasechallan_master.`PCM_DM_ID` =" + getSession().getAttribute("dmId").toString();
            else
               whereCondition = whereCondition + " and erpm_purchasechallan_master.`PCM_DM_ID` ="+getdepartmentId();

             if (getsupplierId() == null)
                    whereCondition = whereCondition + " and suppliermaster.`SM_Id` <> 0 ";
              else
                    whereCondition = whereCondition + " and suppliermaster.`SM_Id` = " + getsupplierId();

            if (getItemNameId() == null) {
                    whereCondition = whereCondition + " and erpm_item_master.`ERPMIM_ID` <> 0";
                } else {
                    whereCondition = whereCondition + " and erpm_item_master.`ERPMIM_ID` =" + getItemNameId();
                }


            hm.put("condition", whereCondition);

            JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
            JasperExportManager.exportReportToPdfStream(jp,baos);
            response.setContentLength(baos.size());
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            inputStream = bis;
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ExportPendingPO method -> ManagePurchaseChallanAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

}


    public String ListOfInvoicesReceived() throws Exception {

     HashMap hm = new HashMap();
      String frmDate = null;
        String toDate = null;
        java.util.Date d1 = null;
        java.util.Date d2 = null;
        DateUtilities dt = new DateUtilities();

        try {
            frmDate = getfromDate();
            if (getfromDate().isEmpty()) {
                frmDate = "01-01-2000";
                d1 = dt.convertStringToDate(frmDate.trim());
                frmDate = dt.convertDateToString(d1, "yyyy-MM-dd");
            } else {
                d1 = dt.convertStringToDate(frmDate.trim());
                frmDate = dt.convertDateToString(d1, "yyyy-MM-dd");

            }
        } catch (Exception e) {
        }

        try {
            toDate = gettoDate();

            if (gettoDate().isEmpty()) {

            Date d = new Date();
            DateUtilities d3 = new DateUtilities();
            toDate = d3.convertDateToString(d, "yyyy-MM-dd");
            d2 = d;//dt.convertStringToDate(toDate.trim());
            } else {
                d2 = dt.convertStringToDate(toDate.trim());
                toDate = dt.convertDateToString(d2, "yyyy-MM-dd");
            }
        } catch (Exception e) {
        }


        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Purchase"  + pathSeparator + "Reports" + pathSeparator + "ListOfInvoicesReceived.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//    String fileName = getSession().getServletContext().getRealPath("pico\\Purchase\\Reports\\UncheckedAndUnverifiedItems.jasper");
    String whereCondition = "";
     try {
            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword"));

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition","attachment; filename = ListOfInvoicesReceived.pdf");
            response.setHeader("Expires" , "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check = 0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            if(getinstitutionId() == null)
                whereCondition = whereCondition + "erpm_purchaseinvoice_master.`PIM_IM_ID` =" + getSession().getAttribute("imId").toString();
            else
                whereCondition = whereCondition + "erpm_purchaseinvoice_master.`PIM_IM_ID` =" + getinstitutionId();

            if(getsubinstitutionId()==null)
               whereCondition = whereCondition + " and erpm_purchaseinvoice_master.`PIM_SIM_ID` =" + getSession().getAttribute("simId").toString();
            else
               whereCondition = whereCondition + " and erpm_purchaseinvoice_master.`PIM_SIM_ID` ="+getsubinstitutionId();

            if(getdepartmentId()==null)
               whereCondition = whereCondition + " and erpm_purchaseinvoice_master.`PIM_DM_ID` =" + getSession().getAttribute("dmId").toString();
            else
               whereCondition = whereCondition + " and erpm_purchaseinvoice_master.`PIM_DM_ID` ="+getdepartmentId();

             if (getsupplierId() == null)
                    whereCondition = whereCondition + " and erpm_purchaseinvoice_master.`PIM_Supplier_ID` <> 0 ";
              else
                    whereCondition = whereCondition + " and erpm_purchaseinvoice_master.`PIM_Supplier_ID` = " + getsupplierId();

            if (getItemNameId() == null) {
                    whereCondition = whereCondition + " and erpm_purchaseinvoice_detail.`PID_Item_ID` <> 0";
                } else {
                    whereCondition = whereCondition + " and erpm_purchaseinvoice_detail.`PID_Item_ID` =" + getItemNameId();
                }

         whereCondition = whereCondition + " and erpm_po_master.`POM_PO_Date` >= '" + frmDate+"'";
            whereCondition = whereCondition + " and erpm_po_master.`POM_PO_Date` <= '" + toDate+"'";

            hm.put("condition", whereCondition);
            hm.put("FDate", frmDate);
            hm.put("TDate", toDate);
            hm.put("d1",d1);
            hm.put("d2",d2);

            JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
            JasperExportManager.exportReportToPdfStream(jp,baos);
            response.setContentLength(baos.size());
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            inputStream = bis;
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ListOfInvoicesReceived method -> ManagePurchaseReportAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

}


    @Override
 public void validate() {
        try {
            DateUtilities dt = new DateUtilities();
             if (getfromDate().length() != 0) {
                if (dt.isValidDate(getfromDate()) == false) {
                    addFieldError("fromDate", "Please give valid From Date [dd-mm-yyyy]");
                }
            }

            if (gettoDate().length() != 0) {
                if (dt.isValidDate(gettoDate()) == false) {
                    addFieldError("toDate", "Please give valid To Date [dd-mm-yyyy]");
                }
            }
                InitializeLOVs();
            if(dt.isFirstDateBeforeSecond(dt.convertStringToDate(gettoDate().trim()), dt.convertStringToDate(getfromDate().trim())))
            {
                addFieldError("fromDate", "From Date cannot be greater than to Date");
            }
         }catch(Exception e)
        {

         }
        }

}
