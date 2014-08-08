/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PrePurchase;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;
import pojo.hibernate.ErpmCapitalCategory;
import pojo.hibernate.ErpmCapitalCategoryDao;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.ErpmItemCategoryMaster;
import pojo.hibernate.ErpmItemCategoryMasterDao;
import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmItemMasterDAO;
import pojo.hibernate.GfrProgramMappingDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import utils.DevelopmentSupport;

//import java.util.Locale;
//import java.util.ResourceBundle;
//import com.opensymphony.xwork2.ActionContext;

/**
 *
 * @author kazim
 */
public class ManageItems extends DevelopmentSupport {

    private Character erpmimSerialNoApplicable;
    private ErpmItemMaster erpmim;
    private ErpmItemMasterDAO erpmimDao = new ErpmItemMasterDAO();
    private ErpmGenMasterDao erpmGmDao = new ErpmGenMasterDao();
    private ErpmItemCategoryMasterDao erpmIcmDao = new ErpmItemCategoryMasterDao();
    private ErpmCapitalCategoryDao erpmccDao = new ErpmCapitalCategoryDao();
    private List<ErpmItemMaster> erpmimList = new ArrayList<ErpmItemMaster>();
    private List<ErpmGenMaster> erpmGmIdList = new ArrayList<ErpmGenMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList1 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList2 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList3 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmCapitalCategory> erpmCapitalCategoryList = new ArrayList<ErpmCapitalCategory>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private GfrProgramMappingDAO GfrProgramMappingDao = new GfrProgramMappingDAO();
    private String message;
    private Short i = 1;
    private Integer ErpmimId;
    private String erpmimIssuePolicy;
    private InputStream inputStream;
    private static Boolean varShowGFR;

    static String dataSourceURL=null;

    public Boolean getVarShowGFR() {
        return varShowGFR;
    }

    public void setVarShowGFR(Boolean varShowGFR) {
        this.varShowGFR = varShowGFR;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void seterpmim(ErpmItemMaster erpmim) {
        this.erpmim = erpmim;
    }

    public ErpmItemMaster geterpmim() {
        return this.erpmim;
    }

    public List<ErpmGenMaster> geterpmGmIdList() {
        return this.erpmGmIdList;
    }

    public void seterpmGmIdList(List<ErpmGenMaster> erpmGmIdList) {
        this.erpmGmIdList = erpmGmIdList;
    }

    public List<ErpmItemCategoryMaster> geterpmIcmList1() {
        return this.erpmIcmList1;
    }

    public void seterpmIcmList1(List<ErpmItemCategoryMaster> erpmIcmList1) {
        this.erpmIcmList1 = erpmIcmList1;
    }

    public List<ErpmItemCategoryMaster> geterpmIcmList2() {
        return this.erpmIcmList2;
    }

    public void seterpmIcmList2(List<ErpmItemCategoryMaster> erpmIcmList2) {
        this.erpmIcmList2 = erpmIcmList2;
    }

    public List<ErpmItemCategoryMaster> geterpmIcmList3() {
        return this.erpmIcmList3;
    }

    public void seterpmIcmList3(List<ErpmItemCategoryMaster> erpmIcmList3) {
        this.erpmIcmList3 = erpmIcmList3;
    }

    public List<ErpmCapitalCategory> geterpmCapitalCategoryList() {
        return this.erpmCapitalCategoryList;
    }

    public void seterpmCapitalCategoryList(List<ErpmCapitalCategory> erpmCapitalCategoryList) {
        this.erpmCapitalCategoryList = erpmCapitalCategoryList;
    }

    public List<ErpmItemMaster> geterpmimList() {
        return this.erpmimList;
    }

    public void seterpmimList(List<ErpmItemMaster> erpmimList) {
        this.erpmimList = erpmimList;
    }

    public List<Institutionmaster> getimList() {
        return this.imList;
    }

    public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public Integer getErpmimId() {
        return this.ErpmimId;
    }

    public void setErpmimId(Integer ErpmimId) {
        this.ErpmimId = ErpmimId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public String getErpmimIssuePolicy() {
        return this.erpmimIssuePolicy;
    }

    public void setErpmimIssuePolicy(String erpmimIssuePolicy) {
        this.erpmimIssuePolicy = erpmimIssuePolicy;
    }

    public Character getErpmimSerialNoApplicable() {
        return this.erpmimSerialNoApplicable;
    }

    public void setErpmimSerialNoApplicable(Character erpmimSerialNoApplicable) {
        this.erpmimSerialNoApplicable = erpmimSerialNoApplicable;
    }

    @Override
    public String execute() throws Exception {
        try {

            //Initialize LOVs
            InitializeLOVs();
            Short i=16;
            Integer count = GfrProgramMappingDao.findCountByProgramId(i);


            if(count > 0){
             setVarShowGFR(false);
            }
            else{
             setVarShowGFR(true);
            }
            //Set the object as erpmim as Null
            erpmim = null;

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Browse() throws Exception {
        try {
            //Retrieve Items which belong to the institutes in the perview of user
            erpmimList = erpmimDao.findItemsForUserInstitutes(Integer.valueOf(getSession().getAttribute("userid").toString()));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Save() throws Exception {
        try {
            //If part saves new record else parts is for record update

            if (erpmim.getErpmimId() == null) {

                //Check if dupliacte item exist(s)
                if (erpmimDao.hasDuplicate(erpmim.getErpmimItemBriefDesc(), erpmim.getInstitutionmaster().getImId())) {
                    message = "An item with the given brief description already exist(s)";
                    InitializeLOVs();
                    return INPUT;
                } else {
                    erpmimDao.save(erpmim);
                    message = "Item record created. Item id is : " + erpmim.getErpmimId();
                }

            } else {
                ErpmItemMaster erpmim2 = erpmimDao.findByErpmimId(erpmim.getErpmimId());
                erpmim2 = erpmim;
                erpmimDao.update(erpmim2);
                message = "Item Record updated successfully.";
            }
            erpmimList = erpmimDao.findItemsForUserInstitutes(Integer.valueOf(getSession().getAttribute("userid").toString()));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Save method -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Edit() throws Exception {
        try {

            erpmim = erpmimDao.findByErpmimId(getErpmimId());

            //Populate LOV for Unit of Purchase
            erpmGmIdList = erpmGmDao.findByErpmGmType(Short.parseShort("3".toString()));

            //Prepare LOV for Ite Type
            erpmIcmList1 = erpmIcmDao.findByErpmicmItemLevel(i);

            //Prepare LOV for Capital categories
            erpmCapitalCategoryList = erpmccDao.findByImId(erpmim.getInstitutionmaster().getImId());

            //Prepare Item Category List
            erpmIcmList2 = erpmIcmDao.findByerpmItemCategoryMaster(erpmim.getErpmItemCategoryMasterByErpmimItemCat1().getErpmicmItemId());

            //Prepare Item Sub Category List
            erpmIcmList3 = erpmIcmDao.findByerpmItemCategoryMaster(erpmim.getErpmItemCategoryMasterByErpmimItemCat2().getErpmicmItemId());

            //Prepare User Institution LOV
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {
            //Retrieve the record to be deleted
            erpmim = erpmimDao.findByErpmimId(getErpmimId());
            erpmimDao.delete(erpmim);

            //Retrieve Items which belong to the institutes in the perview of user
            erpmimList = erpmimDao.findItemsForUserInstitutes(Integer.valueOf(getSession().getAttribute("userid").toString()));
            message = "Item Record deleted successfully.";

            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("java.sql.BatchUpdateException: Cannot delete or update a parent row")) {
                message = "This record cannot be Deleted. It is being used in other Tables." ;
            } else {
            message = "Exception in Delete method -> ManageItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
	    }
            return ERROR;
        }
    }

    private void InitializeLOVs() {
        //Prepare LOV for Unit of Purchase
        erpmGmIdList = erpmGmDao.findByErpmGmType(Short.parseShort("3".toString()));
        //Prepare LOV for Item Types
        erpmIcmList1 = erpmIcmDao.findByErpmicmItemLevel(i);
        //Prepare LOV for User's institutions
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        //Prepare LOV for the user institution
        erpmCapitalCategoryList = erpmccDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));


    }

    public void validate() {
        try {


            if (erpmim.getInstitutionmaster().getImId() == null) {
                addFieldError("erpmim.institutionmaster.imId", "Please select institution from the list");
            }

            if (erpmim.getErpmimItemBriefDesc().length() == 0) {
                addFieldError("erpmim.erpmimItemBriefDesc", "Please enter item's brief description");
            }

            if (erpmim.getErpmItemCategoryMasterByErpmimItemCat1().getErpmicmItemId() == null) {
                addFieldError("erpmim.erpmItemCategoryMasterByErpmimItemCat1.erpmicmItemId", "Please select Item Type from the list");
            }
            InitializeLOVs();

            if (erpmim.getErpmItemCategoryMasterByErpmimItemCat2().getErpmicmItemId() == null) {
                addFieldError("erpmim.erpmItemCategoryMasterByErpmimItemCat2.erpmicmItemId", "Please select Item category from the list");
            }
            InitializeLOVs();

            if (erpmim.getErpmItemCategoryMasterByErpmimItemCat3().getErpmicmItemId() == null) {
                addFieldError("erpmim.erpmItemCategoryMasterByErpmimItemCat3.erpmicmItemId", "Please select Item sub category from the list");
            }

            if (erpmim.getErpmGenMaster().getErpmgmEgmId() == null) {
                addFieldError("erpmim.erpmGenMaster.erpmgmEgmId", "Please select Unit of purchase from the list");
            }

            if (erpmim.getErpmimDetailedDesc().length() == 0) {
                addFieldError("erpmim.erpmimDetailedDesc", "Please enter item's detailed description");
            }

            if (erpmim.getErpmCapitalCategory().getErpmccId() == null) {
                erpmim.setErpmCapitalCategory(null);
            }

//                 if(erpmim.getErpmimDepreciationPercentage() == null)
//                  addFieldError("erpmim.erpmimDepreciationPercentage", "Please enter DepreciationPercentage");

//                  if(erpmim.getErpmimResidualValue() == null)
//                  addFieldError("erpmim.erpmimResidualValue", "Please enter ResidualValue");

            //Initialize LOVs
            InitializeLOVs();

        } catch (NullPointerException npe) {
        };
    }

    @SkipValidation
    public String Print() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "PrePurchase"  + pathSeparator + "Reports" + pathSeparator + "ItemList.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\PrePurchase\\Reports\\ItemList.jasper");

//F:\PICO\web\pico\PrePurchase\Reports
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
            response.setHeader("Content-Disposition", "attachment; filename=ItemList.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            if (erpmim.getInstitutionmaster().getImId() == null) {
                whereCondition = " erpm_item_master.ERPMIM_IM_ID =" + getSession().getAttribute("imId");
            } else {
                whereCondition = " erpm_item_master.ERPMIM_IM_ID = " + erpmim.getInstitutionmaster().getImId();
            }

            if (erpmim.getErpmCapitalCategory().getErpmccId() == null) {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Capital_Cat <> 0";
            } else {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Capital_Cat = " + erpmim.getErpmCapitalCategory().getErpmccId();
            }

            if (erpmim.getErpmItemCategoryMasterByErpmimItemCat1().getErpmicmItemId() == null) {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat1 <> 0";
            } else {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat1 = " + erpmim.getErpmItemCategoryMasterByErpmimItemCat1().getErpmicmItemId();
            }

            if (erpmim.getErpmItemCategoryMasterByErpmimItemCat2().getErpmicmItemId() == null) {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat2 <> 0";
            } else {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat2 = " + erpmim.getErpmItemCategoryMasterByErpmimItemCat2().getErpmicmItemId();
            }

            if (erpmim.getErpmItemCategoryMasterByErpmimItemCat3().getErpmicmItemId() == null) {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat3 <> 0 ";
            } else {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_Item_Cat3 = " + erpmim.getErpmItemCategoryMasterByErpmimItemCat3().getErpmicmItemId();
            }

            if (erpmim.getErpmGenMaster().getErpmgmEgmId() == null) {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_UOP <>0";
            } else {
                whereCondition = whereCondition + " and erpm_item_master.ERPMIM_UOP =" + erpmim.getErpmGenMaster().getErpmgmEgmId();
            }
            /**/
            hm.put("condition", whereCondition);

            JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
            JasperExportManager.exportReportToPdfStream(jp, baos);
            response.setContentLength(baos.size());
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            inputStream = bis;

            return SUCCESS;
        } catch (JRException e) {
            message = "Error is : " + e.getMessage() + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String showGFRreport() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Administration"  + pathSeparator + "Reports" + pathSeparator + "ShowGFRMappedinProgram.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\Administration\\Reports\\ShowGFRMappedinProgram.jasper");

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
            response.setHeader("Content-Disposition", "attachment; filename=ShowGfr.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            whereCondition = "gfr_program_mapping.`GPM_Program_ID` = 16";

            hm.put("condition", whereCondition);
            hm.put("screen_name", "ITEM PROFILE");
            JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
            JasperExportManager.exportReportToPdfStream(jp, baos);
            response.setContentLength(baos.size());
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            inputStream = bis;

            return SUCCESS;
        } catch (JRException e) {
            message = "Error is : " + e.getMessage() + e.getCause();
            return ERROR;
        }
    }
}
