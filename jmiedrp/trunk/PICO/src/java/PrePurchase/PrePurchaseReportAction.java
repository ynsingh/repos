/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrePurchase;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.ServletActionContext;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.ErpmItemCategoryMaster;
import pojo.hibernate.ErpmItemCategoryMasterDao;
import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmItemMasterDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.Suppliermaster;
import pojo.hibernate.SuppliermasterDAO;
import utils.DateUtilities;
import utils.DevelopmentSupport;
/*import org.apache.struts2.interceptor.validation.SkipValidation;
import net.sf.jasperreports.engine.*;
import java.text.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import utils.DateUtilities;*/

//import java.util.Locale;
//import java.util.ResourceBundle;
//import com.opensymphony.xwork2.ActionContext;

/**
 *
 * @author wml4
 */
public class PrePurchaseReportAction extends DevelopmentSupport {

    private String fromDate;
    private String toDate;
    private Integer supplierId, itemId, institutionId, subinstitutionId, departmentId, SubCategoryId, CategoryId, ItemTypeId, ItemNameId;
    private List<ErpmItemCategoryMaster> erpmIcmList1 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList2 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList3 = new ArrayList<ErpmItemCategoryMaster>();
    private SuppliermasterDAO suppliermasterDAO = new SuppliermasterDAO();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
    private List<Suppliermaster> suppList = new ArrayList<Suppliermaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<ErpmItemMaster> itemlist = new ArrayList<ErpmItemMaster>();
    private ErpmItemMasterDAO itemlistDAO = new ErpmItemMasterDAO();
    private ErpmItemCategoryMasterDao erpmItemCategoryMasterDao = new ErpmItemCategoryMasterDao();
    private Short i = 1;
    private Short j = 2;
    private Short k = 3;
    private String message;
    private InputStream inputStream;

    static String dataSourceURL=null;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErpmItemMaster> getItemlist() {
        return itemlist;
    }

    public void setItemlist(List<ErpmItemMaster> itemlist) {
        this.itemlist = itemlist;
    }

    public List<Departmentmaster> getDmList() {
        return dmList;
    }

    public void setDmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<Institutionmaster> getImList() {
        return imList;
    }

    public void setImList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public List<Subinstitutionmaster> getSimList() {
        return simList;
    }

    public void setSimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public List<Suppliermaster> getSuppList() {
        return suppList;
    }

    public void setSuppList(List<Suppliermaster> suppList) {
        this.suppList = suppList;
    }

    public Integer getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Integer CategoryId) {
        this.CategoryId = CategoryId;
    }

    public Integer getItemNameId() {
        return ItemNameId;
    }

    public void setItemNameId(Integer ItemNameId) {
        this.ItemNameId = ItemNameId;
    }

    public Integer getItemTypeId() {
        return ItemTypeId;
    }

    public void setItemTypeId(Integer ItemTypeId) {
        this.ItemTypeId = ItemTypeId;
    }

    public Integer getSubCategoryId() {
        return SubCategoryId;
    }

    public void setSubCategoryId(Integer SubCategoryId) {
        this.SubCategoryId = SubCategoryId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSubinstitutionId() {
        return subinstitutionId;
    }

    public void setSubinstitutionId(Integer subinstitutionId) {
        this.subinstitutionId = subinstitutionId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public List<ErpmItemCategoryMaster> getErpmIcmList1() {
        return erpmIcmList1;
    }

    public void setErpmIcmList1(List<ErpmItemCategoryMaster> erpmIcmList1) {
        this.erpmIcmList1 = erpmIcmList1;
    }

    public List<ErpmItemCategoryMaster> getErpmIcmList2() {
        return erpmIcmList2;
    }

    public void setErpmIcmList2(List<ErpmItemCategoryMaster> erpmIcmList2) {
        this.erpmIcmList2 = erpmIcmList2;
    }

    public List<ErpmItemCategoryMaster> getErpmIcmList3() {
        return erpmIcmList3;
    }

    public void setErpmIcmList3(List<ErpmItemCategoryMaster> erpmIcmList3) {
        this.erpmIcmList3 = erpmIcmList3;
    }

    @Override
    public String execute() throws Exception {

        try {

            InitializeLOVs();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Execute method -> PrePurchaseReportAxn" + e.getMessage() + e.getCause();
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
    }

    public String ListOfPurchaseOrdersReport() throws Exception {

        HashMap hm = new HashMap();
        String frmDate = null;
        String toDate = null;
        java.util.Date d1 = null;
        java.util.Date d2 = null;
        DateUtilities dt = new DateUtilities();

        try {
            frmDate = getFromDate();
            if (getFromDate().isEmpty()) {
                frmDate = "01-01-2000";
                d1 = dt.convertStringToDate(frmDate.trim());
                frmDate = dt.convertDateToString(d1, "yyyy-MM-dd");
            } else {
                d1 = dt.convertStringToDate(frmDate.trim());
                frmDate = dt.convertDateToString(d1, "yyyy-MM-dd");
               
            }
        } catch (Exception e) {message="in first try";
        }

        try {
            toDate = getToDate();

            if (getToDate().isEmpty()) {

            Date d = new Date();
            DateUtilities d3 = new DateUtilities();
            toDate = d3.convertDateToString(d, "yyyy-MM-dd");
            d2 = d; //dt.convertStringToDate(toDate.trim());
            } else {
                d2 = dt.convertStringToDate(toDate.trim());
                toDate = dt.convertDateToString(d2, "yyyy-MM-dd");
            }
        } catch (Exception e) {message="in second try";
        }


        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator"+ "/");

        //pathSeparator = pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "PrePurchase" + pathSeparator + "Reports" + pathSeparator + "ListOfPurchaseOrders.jasper";

        String fileName = getSession().getServletContext().getRealPath(repPath);

        String whereCondition = "";

        try {
//            Locale locale = ActionContext.getContext().getLocale();
//            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI");
            }
            dataSourceURL = (String) ctx.lookup("java:comp/env/ReportURL").toString();
            Connection conn = DriverManager.getConnection(dataSourceURL);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=ListOfPurchaseOrders.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //Setup Where Condition Clause
            if (getInstitutionId() == null) {
                whereCondition = " institutionmaster.IM_Id =" + getSession().getAttribute("imId");
            } else {
                whereCondition = "  institutionmaster.IM_Id= " + getInstitutionId();
            }

            if (getSubinstitutionId() == null) {
                whereCondition = whereCondition + " and subinstitutionmaster.SIM_Id <> 0";
            } else {
                whereCondition = whereCondition + " and subinstitutionmaster.SIM_Id = " + getSubinstitutionId();
            }

            if (getDepartmentId() == null) {
                whereCondition = whereCondition + " and departmentmaster.DM_Id <> 0";
            } else {
                whereCondition = whereCondition + " and departmentmaster.DM_Id = " + getDepartmentId();
            }

            if (getSupplierId() == null) {
                whereCondition = whereCondition + " and suppliermaster.SM_Id <> 0 ";
            } else {
                whereCondition = whereCondition + " and suppliermaster.SM_Id = " + getSupplierId();
            }

            if (getItemNameId() == null) {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_ID <> 0";
            } else {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_ID = " + getItemNameId();
            }

            whereCondition = whereCondition + " and erpm_po_master.POM_PO_Date >= '" + frmDate + "'";
            whereCondition = whereCondition + " and erpm_po_master.POM_PO_Date <= '" + toDate + "'";


            hm.put("condition", whereCondition);
            hm.put("FDate", frmDate);
            hm.put("TDate", toDate);
            hm.put("d1",d1);
            hm.put("d2",d2);

            JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
            JasperExportManager.exportReportToPdfStream(jp, baos);
            response.setContentLength(baos.size());
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            inputStream = bis;

            return "success";
        } catch (JRException e) {
            message = "Error is : " + e.getMessage() + e.getCause()+"========"+ pathSeparator;
            return ERROR;
        }
    }

@Override
     public void validate() {
        try {
            DateUtilities dt = new DateUtilities();
             if (getFromDate().length() != 0) {
                if (dt.isValidDate(getFromDate()) == false) {
                    addFieldError("fromDate", "Please give valid From Date [dd-mm-yyyy]");
                }
            }

            if (getToDate().length() != 0) {
                if (dt.isValidDate(getToDate()) == false) {
                    addFieldError("toDate", "Please give valid To Date [dd-mm-yyyy]");
                }
 
            }
InitializeLOVs();
            if(dt.isFirstDateBeforeSecond(dt.convertStringToDate(getToDate().trim()), dt.convertStringToDate(getFromDate().trim())))
            {
                addFieldError("fromDate", "From Date cannot be greater than to Date");
            }
         }catch(Exception e)
        {

         }
        }
 
    public String FloatedTendersReportAction() throws Exception {

        HashMap hm = new HashMap();
        String frmDate = null;
        String toDate = null;
        java.util.Date d1 = null;
        java.util.Date d2 = null;
        DateUtilities dt = new DateUtilities();

        try {
            frmDate = getFromDate();
            if (getFromDate().isEmpty()) {
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
            toDate = getToDate();

            if (getToDate().isEmpty()) {

            Date d = new Date();
            DateUtilities d3 = new DateUtilities();
            toDate = d3.convertDateToString(d, "yyyy-MM-dd");
            d2 = dt.convertStringToDate(toDate.trim());
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

        pathSeparator = pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "PrePurchase" + pathSeparator + "Reports" + pathSeparator + "List_of_Tenders_Floated.jasper";

        String fileName = getSession().getServletContext().getRealPath(repPath);

        String whereCondition = "";

        try {
//            Locale locale = ActionContext.getContext().getLocale();
//            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI");
            }
            dataSourceURL = (String) ctx.lookup("java:comp/env/ReportURL").toString();
            Connection conn = DriverManager.getConnection(dataSourceURL);

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=List_of_Tenders_Floated.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //Setup Where Condition Clause
           if (getInstitutionId() == null) {
                whereCondition = " institutionmaster.IM_Id =" + getSession().getAttribute("imId");
            } else {
                whereCondition = "institutionmaster.IM_Id= " + getInstitutionId();
            }

           if (getSubinstitutionId() == null) {
                whereCondition = whereCondition + " and erpm_tender_master.TM_SIM_ID <> 0";
            } else {
                whereCondition = whereCondition + " and erpm_tender_master.TM_SIM_ID= " + getSubinstitutionId();
            }

            if (getDepartmentId() == null) {
                whereCondition = whereCondition + " and departmentmaster.DM_ID <> 0";
            } else {
                whereCondition = whereCondition + " and departmentmaster.DM_ID = " + getDepartmentId();
            } 

             if (getDepartmentId() == null) {
                whereCondition = whereCondition + " and departmentmaster.DM_ID <> 0";
            } else {
                whereCondition = whereCondition + " and departmentmaster.DM_ID = " + getDepartmentId();
            }

            whereCondition = whereCondition + " and erpm_tender_master.TM_Date >= '" + frmDate+"'";
            whereCondition = whereCondition + " and erpm_tender_master.TM_Date <= '" + toDate+"'";


            hm.put("condition", whereCondition);
            hm.put("FDate", frmDate);
            hm.put("TDate", toDate);
            hm.put("d1",d1);
            hm.put("d2",d2);

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

    public String PrePurchaseReportAction() throws Exception {
        try {
            institutionId = null;
	    supplierId = null;
	    ItemTypeId= null;
	    CategoryId= null;
	    SubCategoryId= null;
	    ItemNameId= null;
	    fromDate= null;
	    toDate= null;
            //Prepare LOVs
            InitializeLOVs();
            return SUCCESS;
            } catch (Exception e) {
            	message = "Exception in Clear method -> PrePurchaseReportActionAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
                return ERROR;
            }
    }
}
