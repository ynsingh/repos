/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sajid Aziz
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
import pojo.hibernate.Countrymaster;
import pojo.hibernate.CountrymasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.GfrProgramMappingDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Statemaster;
import pojo.hibernate.StatemasterDAO;
import pojo.hibernate.SupplierAddress;
import pojo.hibernate.SupplierAddressDAO;
import pojo.hibernate.Suppliermaster;
import pojo.hibernate.SuppliermasterDAO;
import utils.DateUtilities;
import utils.DevelopmentSupport;

//import java.util.Locale;
//import java.util.ResourceBundle;
//import com.opensymphony.xwork2.ActionContext;

public class ManageSupplier extends DevelopmentSupport {

    private Suppliermaster erpmsm;
    private Suppliermaster erpmsm1;
    private SupplierAddress supad;
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private ErpmGenMasterDao gmDao = new ErpmGenMasterDao();
    private SuppliermasterDAO erpmsmDao = new SuppliermasterDAO();
    private StatemasterDAO smDao = new StatemasterDAO();
    private CountrymasterDAO cmDao = new CountrymasterDAO();
    private SupplierAddressDAO adDao = new SupplierAddressDAO();
    private List<Institutionmaster> imIdList = new ArrayList<Institutionmaster>();
    private List<SupplierAddress> saIdList = new ArrayList<SupplierAddress>();
    private List<ErpmGenMaster> gmIdList = new ArrayList<ErpmGenMaster>();
    private List<ErpmGenMaster> gmIdList1 = new ArrayList<ErpmGenMaster>();
    private List<Suppliermaster> erpmsmList = new ArrayList<Suppliermaster>();
    private List<Statemaster> stList = new ArrayList<Statemaster>();
    private List<Countrymaster> ctList = new ArrayList<Countrymaster>();
    private List<SupplierAddress> adList = new ArrayList<SupplierAddress>();
    private GfrProgramMappingDAO GfrProgramMappingDao = new GfrProgramMappingDAO();
    private Integer SMID;
    private String message;
    private String estDate, regDate, SMNAME;
    private Byte defaultCountry;
    short i1 = 1;
    short i2 = 2;
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

    public void setregDate(String regDate) {
        this.regDate = regDate;
    }

    public String getregDate() {
        return this.regDate;
    }

    public void setestDate(String estDate) {
        this.estDate = estDate;
    }

    public String getestDate() {
        return this.estDate;
    }

    public void setdefaultCountry(Byte defaultCountry) {
        this.defaultCountry = defaultCountry;
    }

    public Byte getdefaultCountry() {
        return this.defaultCountry;
    }

    public void setSMID(Integer SMID) {
        this.SMID = SMID;
    }

    public Integer getSMID() {
        return this.SMID;
    }

    public void setSMNAME(String SMNAME) {
        this.SMNAME = SMNAME;
    }

    public String getSMNAME() {
        return this.SMNAME;
    }

    public void seterpmsm(Suppliermaster erpmsm) {
        this.erpmsm = erpmsm;
    }

    public Suppliermaster geterpmsm() {
        return this.erpmsm;
    }

    public void setsupad(SupplierAddress supad) {
        this.supad = supad;
    }

    public SupplierAddress getsupad() {
        return this.supad;
    }

    public void setimIdList(List<Institutionmaster> imIdList) {
        this.imIdList = imIdList;
    }

    public List<Institutionmaster> getimIdList() {
        return this.imIdList;
    }

    public void setsaIdList(List<SupplierAddress> saIdList) {
        this.saIdList = saIdList;
    }

    public List<SupplierAddress> getsaIdList() {
        return this.saIdList;
    }

    public void seterpmsmList(List<Suppliermaster> erpmsmList) {
        this.erpmsmList = erpmsmList;
    }

    public List<Suppliermaster> geterpmsmList() {
        return this.erpmsmList;
    }

    public void setstList(List<Statemaster> stList) {
        this.stList = stList;
    }

    public List<Statemaster> getstList() {
        return this.stList;
    }

    public void setctList(List<Countrymaster> ctList) {
        this.ctList = ctList;
    }

    public List<Countrymaster> getctList() {
        return this.ctList;
    }

    public void setgmIdList(List<ErpmGenMaster> gmIdList) {
        this.gmIdList = gmIdList;
    }

    public List<ErpmGenMaster> getgmIdList() {
        return this.gmIdList;
    }

    public void setgmIdList1(List<ErpmGenMaster> gmIdList1) {
        this.gmIdList1 = gmIdList1;
    }

    public List<ErpmGenMaster> getgmIdList1() {
        return this.gmIdList1;
    }

    public void setadList(List<SupplierAddress> adList) {
        this.adList = adList;
    }

    public List<SupplierAddress> getadList() {
        return this.adList;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String execute() throws Exception {
        try {

            //Prepare LOVs
            InitializeLOVs();

            Short i=17;
            Integer count = GfrProgramMappingDao.findCountByProgramId(i);


            if(count > 0){
             setVarShowGFR(false);
            }
            else{
             setVarShowGFR(true);
            }
            // Clear Objects and Form data
            erpmsm = null;
            supad = null;

            //erpmsm.setSmRegDate(null);
            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            setregDate(d1.convertDateToString(d, "dd-MM-yyyy"));


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ManageSuppleirlAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Save() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update
            if (erpmsm.getSmId() == null) {
                //Save Supplier Record
                DateUtilities dt = new DateUtilities();
                erpmsm.setSmYearEstablishment(dt.convertStringToDate(getestDate()));
                erpmsm.setSmRegDate(dt.convertStringToDate(getregDate()));
                erpmsmDao.save(erpmsm);

                //Save Supplier's primary address
                supad.setSuppliermaster(erpmsm);
                adDao.save(supad);

                message = "Supplier record created. Supplier id is : " + erpmsm.getSmId();
            } else {

                erpmsm1 = erpmsmDao.findByErpmSMId(getSMID());

                //Convert Strings  to Date
                DateUtilities dt = new DateUtilities();
                erpmsm.setSmYearEstablishment(dt.convertStringToDate(getestDate()));
                erpmsm.setSmRegDate(dt.convertStringToDate(getregDate()));

                erpmsm1 = erpmsm;
                erpmsmDao.update(erpmsm1);


                SupplierAddress supad1 = adDao.findErpmSMId(erpmsm1.getSmId());
                supad1 = supad;
                supad1.setSuppliermaster(erpmsm1);
                adDao.update(supad1);
                message = "Supplier record updated successfully";
            }

            return SUCCESS;
        } catch (Exception e) {
            NotifyDatabaseConstraintViolation(e);
            InitializeLOVs();
            return ERROR;

        }
    }

    public String Browse() throws Exception {
        try {
            erpmsmList = erpmsmDao.findForUserInstitutes(Integer.valueOf(getSession().getAttribute("userid").toString()));

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> MangesupplierAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String Delete() throws Exception {
        try {

            //Retrieve Supplier's record
            erpmsm = erpmsmDao.findByErpmSMId(getSMID());

            // adDao.deleteSupplierAddresses(getSMID());
            saIdList = adDao.findBySupplierId(getSMID());

            for (int i = 0; i < saIdList.size(); i++) {
                adDao.delete(saIdList.get(i));
            }

            erpmsmDao.delete(erpmsm);

            message = "Supplier Record deleted successfully";
            Browse();

            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("java.sql.BatchUpdateException: Cannot delete or update a parent row")) {
                message = "This record cannot be Deleted. It is being used in other Tables.";
            }else{
            message = "Exception in Delete method ->MangesupplierAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
	    }
            return ERROR;
        }
    }

    public String Edit() throws Exception {
        try {
            InitializeLOVs();

            erpmsm = erpmsmDao.findByErpmSMId(getSMID());
            supad = adDao.findErpmSMId(erpmsm.getSmId());

            DateUtilities dt = new DateUtilities();
            estDate = dt.convertDateToString(erpmsm.getSmYearEstablishment(), "dd-MM-yyyy");
            regDate = dt.convertDateToString(erpmsm.getSmRegDate(), "dd-MM-yyyy");

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> MangesupplierAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public void InitializeLOVs() {
        //Prepare LOV containing User Institutions
        imIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

        //Prepare List containing Supplier Type Types
        gmIdList = gmDao.findByErpmGmType(i2);

        //Prepare List containing Owner Types
        gmIdList1 = gmDao.findByErpmGmType(i1);

        //Prepare LOV containing Countries
        ctList = cmDao.findAll();

        //Set default Country (Need to be setup as same as that of Institution at some stage
        defaultCountry = cmDao.findCountry("India");

        //Prepare LOV containing States
        stList = smDao.findAll();


        return;
    }

    public void validate() {
        try {

            //Validations on Supplier data
            if (erpmsm.getInstitutionmaster().getImId() == null) {
                addFieldError("erpmsm.institutionmaster.imId", "Select institution from the list");

            }
            if (erpmsm.getSmName().length() == 0) {
                addFieldError("erpmsm.smName", "Enter Supplier's Name ");
            }
            if (erpmsm.getErpmGenMasterBySmSupplierType().getErpmgmEgmId() == null) {
                addFieldError("erpmsm.erpmGenMasterBySmSupplierType.erpmgmEgmId", "Enter Supplier's Type");
            }
            if (erpmsm.getErpmGenMasterBySmOwnershipType().getErpmgmEgmId() == null) {
                addFieldError("erpmsm.erpmGenMasterBySmOwnershipType.erpmgmEgmId", "Select Ownership");
            }
            if (erpmsm.getSmPanNo().length() == 0 && erpmsm.getSmTanNo().length() == 0) {
                addFieldError("erpmsm.smPanNo", "Provide value for either PAN No or TAN No");
                addFieldError("erpmsm.smTanNo", "Provide value for either PAN No or TAN No");
            }
            if (erpmsm.getSmPanNo().length() == 0 && erpmsm.getSmTanNo().length() != 0) {
                erpmsm.setSmPanNo(null);
            }
            if (erpmsm.getSmPanNo().length() != 0 && erpmsm.getSmTanNo().length() == 0) {
                erpmsm.setSmTanNo(null);
            }
            if (getestDate().length() == 0) {
                addFieldError("estDate", "Enter date of Establishment");
            } else {
                DateUtilities dt = new DateUtilities();
                if (dt.isValidDate(getestDate()) == false) {
                    addFieldError("estDate", "Enter date of Establishment[dd-mm-yyyy]");
                }

            }
            if (getregDate().length() == 0) {
                addFieldError("regDate", "Enter Date of Registration");
            } else {
                DateUtilities dt = new DateUtilities();
                if (dt.isValidDate(getregDate()) == false) {
                    addFieldError("regDate", "Enter Registration Date [dd-mm-yyyy]");
                }

            }
            if (supad.getAdLine1().length() == 0) {
                addFieldError("supad.adLine1", "Enter primary contact address of supplier");
            }

            if (supad.getCountrymaster().getCountryId() == null) {
                addFieldError("supad.countrymaster.countryId", "Select country from the list");
            }
            if (supad.getAdCity().length() == 0) {
                addFieldError("supad.adCity", "Enter city");
            }
            if (supad.getStatemaster().getStateId() == null) {
                supad.setStatemaster(null);
            }

            //Initialize LOVs
            InitializeLOVs();

            //message = message + "== In Validate ==";

        } catch (NullPointerException npe) {
        }
    }

    public String NotifyDatabaseConstraintViolation(Exception e) {
        if (e.getCause().toString().contains("Unique_SM_IM_ID_SM_PAN_No")) {
            message = "The PAN No '" + erpmsm.getSmPanNo() + "' is already registered with supplier :" + erpmsmDao.findByPANNo(erpmsm.getSmPanNo(), erpmsm.getInstitutionmaster().getImId());
        } else if (e.getCause().toString().contains("Unique_SM_IM_ID_SM_TAN_No")) {
            message = "The TAN No '" + erpmsm.getSmTanNo() + "' is already registered with supplier :" + erpmsmDao.findByTANNo(erpmsm.getSmTanNo(), erpmsm.getInstitutionmaster().getImId());
        } else if (e.getCause().toString().contains("SM_IM_ID_SM_Name_Unique")) {
            message = "The supplier '" + erpmsm.getSmName() + "' is already registered with " + imDao.findByImId(erpmsm.getInstitutionmaster().getImId()).getImName();
        } else {
            message = "Exception in Save method -> MangesupplierAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
        }
        return ERROR;

    }

    @SkipValidation
    public String Export() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "PrePurchase"  + pathSeparator + "Reports" + pathSeparator + "RegisteredSupplier.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\PrePurchase\\Reports\\RegisteredSupplier.jasper");


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
            response.setHeader("Content-Disposition", "attachment; filename=RegisteredSupplier.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //Setup Where Condition Clause
            if (erpmsm.getInstitutionmaster().getImId() == null) {
                whereCondition = " and sm.sm_im_id = " + getSession().getAttribute("imId");
            } else {
                whereCondition = " and sm.sm_im_id = " + erpmsm.getInstitutionmaster().getImId();
            }

            if (erpmsm.getSmName().length() == 0) {
                whereCondition = whereCondition + " and sm.sm_name like '%' ";
            } else {
                whereCondition = whereCondition + " and upper(sm.sm_name) like '%" + erpmsm.getSmName().toString() + "%'";
            }

            if (erpmsm.getErpmGenMasterBySmSupplierType().getErpmgmEgmId() == null) {
                whereCondition = whereCondition + " and sm.SM_Supplier_Type <> 0 ";
            } else {
                whereCondition = whereCondition + " and sm.SM_Supplier_Type = " + erpmsm.getErpmGenMasterBySmSupplierType().getErpmgmEgmId();
            }

            if (erpmsm.getErpmGenMasterBySmOwnershipType().getErpmgmEgmId() == null) {
                whereCondition = whereCondition + " and sm.SM_Ownership_Type <> 0 ";
            } else {
                whereCondition = whereCondition + " and sm.SM_Ownership_Type = " + erpmsm.getErpmGenMasterBySmOwnershipType().getErpmgmEgmId();
            }

            if (erpmsm.getSmDealsWith().length() == 0) {
                whereCondition = whereCondition + " and sm.SM_Deals_With like '%' ";
            } else {
                whereCondition = whereCondition + " and upper(sm.SM_Deals_With) like '%" + erpmsm.getSmDealsWith().toString() + "%'";
            }


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


            whereCondition = "gfr_program_mapping.`GPM_Program_ID` = 17";

            hm.put("condition", whereCondition);
            hm.put("screen_name", "SUPPLIER REGISTRATION");

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
