/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author SKNaqvi
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
import pojo.hibernate.Budgetheadmaster;
import pojo.hibernate.BudgetheadmasterDAO;
import pojo.hibernate.Committeemaster;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.ErpmIndentMaster;
import pojo.hibernate.ErpmIndentMasterDAO;
import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmItemRate;
import pojo.hibernate.Erpmusers;
import pojo.hibernate.ErpmusersDAO;
import pojo.hibernate.GfrProgramMappingDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.UserMessage;
import pojo.hibernate.Workflowmaster;
import pojo.hibernate.WorkflowmasterDAO;
import pojo.hibernate.Workflowtransaction;
import pojo.hibernate.WorkflowtransactionDAO;
import utils.DateUtilities;
import utils.DevelopmentSupport;

//import java.util.Locale;
//import java.util.ResourceBundle;
//import com.opensymphony.xwork2.ActionContext;

public class ManageIndent extends DevelopmentSupport {

    private InputStream inputStream;
    private ErpmIndentMaster erpmindtmast;
    private ErpmItemMaster erpmitemmaster;
    private ErpmItemRate itemrate;
    private Committeemaster cm;
    private Workflowmaster wfm;
    private Short indentDetailId;
    private short indtindentid;
    private Integer irItemRateId;
    private String message;
    private String indentAction;
    private String ErpmuFullName;
    Integer Id;
    Short defaultIndent;
    Erpmusers ermu;
    Erpmusers ermuf;
    private Short DefaultInsitute1;
    private Integer DefaultSubInsitute;
    private Integer DefaultDepartment;
    private Integer defaultCurrency;
    private String defaultIndentTitle;
    private String defaultCurrencyName;
    private Integer defaultUOP;
    private short indt_indt_mst_Indent_Id;
    private short bhmId;
    Integer erpmuId;
    private String indentDate;
    private Integer selectedItem;
    private String UOP;
    private Integer selectedItemRate;
    private String selectedItemRateValidFrom;
    private String selectedItemRateValidTo;
    private String selectedItemRateCurrency;
    private String irdRate1;
    private Boolean btnEditEnabled;
    private Integer minOrderQuantity, maxOrderQuantity;
    private String firstDate, lastDate;
    private Integer institution, subInstitution, department;
    private GfrProgramMappingDAO GfrProgramMappingDao = new GfrProgramMappingDAO();
    private ErpmIndentMasterDAO erpminDao = new ErpmIndentMasterDAO();
    private WorkflowtransactionDAO wftDAO = new WorkflowtransactionDAO();
    private Workflowtransaction wft = new Workflowtransaction();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Budgetheadmaster> bhmList = new ArrayList<Budgetheadmaster>();
    private BudgetheadmasterDAO bhmDao = new BudgetheadmasterDAO();
    private List<ErpmGenMaster> currencyList = new ArrayList<ErpmGenMaster>();
    private List<ErpmGenMaster> WmnameList = new ArrayList<ErpmGenMaster>();
    private ErpmGenMasterDao currencyDao = new ErpmGenMasterDao();
    private List<ErpmIndentMaster> IndentList = new ArrayList<ErpmIndentMaster>();
    private List<ErpmGenMaster> UOPList = new ArrayList<ErpmGenMaster>();
    private List<ErpmItemRate> itemRateList = new ArrayList<ErpmItemRate>();
    private ErpmGenMasterDao UOPListDao = new ErpmGenMasterDao();
    private List<ErpmItemMaster> itemList = new ArrayList<ErpmItemMaster>();
    private ErpmusersDAO ermuDao = new ErpmusersDAO();
    private List<String> iRateList = new ArrayList<String>();
    private List<Erpmusers> erpmuserlist = new ArrayList<Erpmusers>();
    private UserMessage um = new UserMessage();
    private WorkflowmasterDAO WfmDao = new WorkflowmasterDAO();
    private List<Workflowmaster> Wfmnamelist = new ArrayList<Workflowmaster>();
    private List<ErpmGenMaster> WfaActionsList = new ArrayList<ErpmGenMaster>();
    private Short indentId;
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

    public void setbtnEditEnabled(Boolean btnEditEnabled) {
        this.btnEditEnabled = btnEditEnabled;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public Boolean getbtnEditEnabled() {
        return this.btnEditEnabled;
    }

    public void setindentId(Short indentId) {
        this.indentId = indentId;
    }

    public Short getindentId() {
        return this.indentId;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public Integer getminOrderQuantity() {
        return this.minOrderQuantity;
    }

    public Integer getmaxOrderQuantity() {
        return this.maxOrderQuantity;
    }

    public void setindentDate(String indentDate) {
        this.indentDate = indentDate;
    }

    public String getindentDate() {
        return this.indentDate;
    }

    public void setdefaultIndentTitle(String defaultIndentTitle) {
        this.defaultIndentTitle = defaultIndentTitle;
    }

    public String getdefaultIndentTitle() {
        return this.defaultIndentTitle;
    }

    public void setindentAction1(String indentAction) {
        this.indentAction = indentAction;
    }

    public String getindentAction() {
        return this.indentAction;
    }

    public void setWmnameList(List<ErpmGenMaster> WmnameList) {
        this.WmnameList = WmnameList;
    }

    public List<ErpmGenMaster> getWmnameList() {
        return this.WmnameList;
    }

    public void setWfmnamelist(List<Workflowmaster> Wfmnamelist) {
        this.Wfmnamelist = Wfmnamelist;
    }

    public List<Workflowmaster> getWfmnamelist() {
        return this.Wfmnamelist;
    }

    public Integer getInstitution() {
        return institution;
    }

    public void setInstitution(Integer institution) {
        this.institution = institution;
    }

    public Integer getSubInstitution() {
        return subInstitution;
    }

    public void setSubInstitution(Integer subInstitution) {
        this.subInstitution = subInstitution;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public void setWfaActionsList(List<ErpmGenMaster> WfaActionsList) {
        this.WfaActionsList = WfaActionsList;
    }

    public List<ErpmGenMaster> getWfaActionsList() {
        return this.WfaActionsList;
    }

    public void setcm(Committeemaster cm) {
        this.cm = cm;
    }

    public Committeemaster getcm() {
        return this.cm;
    }

    public void setwfm(Workflowmaster wfm) {
        this.wfm = wfm;
    }

    public Workflowmaster getwfm() {
        return this.wfm;
    }

    public void setwft(Workflowtransaction wft) {
        this.wft = wft;
    }

    public Workflowtransaction getwft() {
        return this.wft;
    }

    public void setirdRate1(String irdRate1) {
        this.irdRate1 = irdRate1;
    }

    public String getirdRate1() {
        return this.irdRate1;
    }

    public void setselectedItemRateCurrency(String selectedItemRateCurrency) {
        this.selectedItemRateCurrency = selectedItemRateCurrency;
    }

    public String getselectedItemRateCurrency() {
        return this.selectedItemRateCurrency;
    }

    public void setselectedItemRateValidFrom(String selectedItemRateValidFrom) {
        this.selectedItemRateValidFrom = selectedItemRateValidFrom;
    }

    public String getselectedItemRateValidFrom() {
        return this.selectedItemRateValidFrom;
    }

    public void setselectedItemRateValidTo(String selectedItemRateValidTo) {
        this.selectedItemRateValidTo = selectedItemRateValidTo;
    }

    public String getselectedItemRateValidTo() {
        return this.selectedItemRateValidTo;
    }

    public void setiRateList(List<String> iRateList) {
        this.iRateList = iRateList;
    }

    public List<String> getiRateList() {
        return this.iRateList;
    }

    public void setselectedItem(Integer selectedItem) {
        this.selectedItem = selectedItem;
    }

    public Integer getselectedItem() {
        return this.selectedItem;
    }

    public void setUOP(String UOP) {
        this.UOP = UOP;
    }

    public String getUOP() {
        return this.UOP;
    }

    public void setselectedItemRate(Integer selectedItemRate) {
        this.selectedItemRate = selectedItemRate;
    }

    public Integer getselectedItemRate() {
        return this.selectedItemRate;
    }

    public void setirItemRateId(Integer irItemRateId) {
        this.irItemRateId = irItemRateId;
    }

    public Integer getirItemRateId() {
        return irItemRateId;
    }

    public void setitemrate(ErpmItemRate itemrate) {
        this.itemrate = itemrate;
    }

    public ErpmItemRate getitemrate() {
        return this.itemrate;
    }

    /*--------------------------setter getter for erpm_indent_master--------------------------*/
    public void setitemRateList(List<ErpmItemRate> itemRateList) {
        this.itemRateList = itemRateList;
    }

    public List<ErpmItemRate> getitemRateList() {
        return this.itemRateList;
    }

    public void setdefaultCurrency(Integer defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public Integer getdefaultCurrency() {
        return this.defaultCurrency;
    }

    public void setdefaultUOP(Integer defaultUOP) {
        this.defaultUOP = defaultUOP;
    }

    public Integer getdefaultUOP() {
        return this.defaultUOP;
    }

    public void setDefaultInsitute1(Short DefaultInsitute1) {
        this.DefaultInsitute1 = DefaultInsitute1;
    }

    public Short getDefaultInsitute1() {
        return this.DefaultInsitute1;
    }

    public void setDefaultSubInsitute(Integer DefaultSubInsitute) {
        this.DefaultSubInsitute = DefaultSubInsitute;
    }

    public Integer getDefaultSubInsitute() {
        return this.DefaultSubInsitute;
    }

    public void setDefaultDepartment(Integer DefaultDepartment) {
        this.DefaultDepartment = DefaultDepartment;
    }

    public Integer getDefaultDepartment() {
        return this.DefaultDepartment;
    }

    public void seterpmuserlist(List<Erpmusers> erpmuserlist) {
        this.erpmuserlist = erpmuserlist;
    }

    public List<Erpmusers> geterpmuserlist() {
        return this.erpmuserlist;
    }

    public void seterpmindtmast(ErpmIndentMaster erpmindtmast) {
        this.erpmindtmast = erpmindtmast;
    }

    public ErpmIndentMaster geterpmindtmast() {
        return this.erpmindtmast;
    }

    public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public List<Institutionmaster> getimList() {
        return this.imList;
    }

    public void setsimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public List<Subinstitutionmaster> getsimList() {
        return this.simList;
    }

    public void seterpmitemmaster(ErpmItemMaster erpmitemmaster) {
        this.erpmitemmaster = erpmitemmaster;
    }

    public ErpmItemMaster geterpmitemmaster() {
        return this.erpmitemmaster;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setdefaultCurrencyName(String defaultCurrencyName) {
        this.defaultCurrencyName = defaultCurrencyName;
    }

    public String getdefaultCurrencyName() {
        return this.defaultCurrencyName;
    }

    public void setIndentList(List<ErpmIndentMaster> IndentList) {
        this.IndentList = IndentList;
    }

    public List<ErpmIndentMaster> getIndentList() {
        return this.IndentList;
    }

    public void setdefaultIndent(Short defaultIndent) {
        this.defaultIndent = defaultIndent;
    }

    public Short getdefaultIndent() {
        return this.defaultIndent;
    }

    public void setdmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<Departmentmaster> getdmList() {
        return this.dmList;
    }

    public void setbhmList(List<Budgetheadmaster> bhmList) {
        this.bhmList = bhmList;
    }

    public List<Budgetheadmaster> getbhmList() {
        return this.bhmList;
    }

    public void setcurrencyList(List<ErpmGenMaster> currencyList) {
        this.currencyList = currencyList;
    }

    public List<ErpmGenMaster> getcurrencyList() {
        return this.currencyList;
    }

    public void setindentDetailId(Short indentDetailId) {
        this.indentDetailId = indentDetailId;
    }

    public Short getindentDetailId() {
        return this.indentDetailId;
    }

    public void setindtindentid(short indtindentid) {
        this.indtindentid = indtindentid;
    }

    public short getindtindentid() {
        return indtindentid;
    }

    public void setErpmuFullName(String ErpmuFullName) {
        this.ErpmuFullName = ErpmuFullName;
    }

    public String getErpmuFullName() {
        return ErpmuFullName;
    }


    public void setUOPList(List<ErpmGenMaster> UOPList) {
        this.UOPList = UOPList;
    }

    public List<ErpmGenMaster> getUOPList() {
        return this.UOPList;
    }

    public void setitemList(List<ErpmItemMaster> itemList) {
        this.itemList = itemList;
    }

    public List<ErpmItemMaster> getitemList() {
        return this.itemList;
    }

    public void setindt_indt_mst_Indent_Id(short indt_indt_mst_Indent_Id) {
        this.indt_indt_mst_Indent_Id = indt_indt_mst_Indent_Id;
    }

    public short getindt_indt_mst_Indent_Id() {
        return indt_indt_mst_Indent_Id;
    }

    public void setbhmId(short bhmId) {
        this.bhmId = bhmId;
    }

    public short getbhmId() {
        return bhmId;
    }

    public void seterpmuId(Integer erpmuId) {
        this.erpmuId = erpmuId;
    }

    public Integer geterpmuId() {
        return erpmuId;
    }

    public void setum(UserMessage um) {
        this.um = um;
    }

    public UserMessage getum() {
        return this.um;
    }

    @Override
    public String execute() throws Exception {
        try {
            erpmindtmast = new ErpmIndentMaster();

            InitializeLOVs();

            //Set default values from Session
            InitializeDefaults();

            //Set Indent Date
            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            setindentDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            Short i = 19;
            Integer count = GfrProgramMappingDao.findCountByProgramId(i);


            if (count > 0) {
                setVarShowGFR(false);
            } else {
                setVarShowGFR(true);
            }

            setbtnEditEnabled(true);

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> IndentAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //This function Save given IndentMaster Records
    public String SaveIndentMaster() throws Exception {
        try {
            // message = "Entering Save Indent Master";
            if (erpmindtmast.getIndtIndentId() == null) {
                //Save New Indent Record

                DateUtilities dt = new DateUtilities();
                erpmindtmast.setIndtIndentDate(dt.convertStringToDate(getindentDate()));

                erpmindtmast.setErpmusers(ermuDao.findByUserId(Integer.parseInt(getSession().getAttribute("userid").toString())));

                erpminDao.save(erpmindtmast);

                indentId = erpmindtmast.getIndtIndentId();
            } else {
                //This part updates the Indent and prepare for the Indent Item Section


                ErpmIndentMaster erpmindtmast1 = erpminDao.findIndentMasterId(erpmindtmast.getIndtIndentId());

                DateUtilities dt = new DateUtilities();
                erpmindtmast.setIndtIndentDate(dt.convertStringToDate(getindentDate()));
                erpmindtmast.setErpmusers(ermuDao.findByUserId(Integer.parseInt(getSession().getAttribute("userid").toString())));
                erpmindtmast1 = erpmindtmast;

                erpminDao.update(erpmindtmast1);

                erpmindtmast = erpmindtmast1;

                indentId = erpmindtmast1.getIndtIndentId();

            }
            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("UNIQUE_DeptId_Indent_Date_Indent_Title")) {
                message = "This action is not allowed as it would create a duplicate entry for the Indent";
            } else {
                message = message + "Exception in Save method-> INDENTMasterAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            }
            return ERROR;
        }
    }

    public void InitializeLOVs() {
        // defaultIndent = erpmindtmast.getIndtIndentId();
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
        bhmList = bhmDao.findforInsitutetBudgetHeadId(Integer.parseInt(getSession().getAttribute("userid").toString()));
        currencyList = currencyDao.findByErpmGmType(Short.parseShort("6"));
        Wfmnamelist = WfmDao.findByErpmGmID(Integer.parseInt("72"));
    }

    public void InitializeDefaults() {
        DefaultInsitute1 = Short.valueOf(getSession().getAttribute("imId").toString());
        DefaultSubInsitute = Integer.valueOf(getSession().getAttribute("simId").toString());
        DefaultDepartment = Integer.valueOf(getSession().getAttribute("dmId").toString());
        defaultCurrency = UOPListDao.findDefaultCurrency("Rupees");
        Wfmnamelist = WfmDao.findByErpmGmID(Integer.parseInt("72"));

    }

    //This function browse enable user to browse Indents
    @SkipValidation
    public String BrowseIndentMaster() throws Exception {
        try {
            IndentList = erpminDao.findIndentsForUser(Integer.parseInt(getSession().getAttribute("userid").toString()),
                    getSession().getAttribute("username").toString());
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> MangeBrowseIndentsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //This function Edit given Indent Master Records
    @SkipValidation
    public String EditIndentMaster() throws Exception {

        try {

            //Initialize LOVs
            InitializeLOVs();

            //Set default values
            InitializeDefaults();

            //Retrieve the record to be updated into erpindtdet object
            erpmindtmast = erpminDao.findIndentMasterId(getindtindentid());

            DateUtilities dt = new DateUtilities();
            indentDate = dt.convertDateToString(erpmindtmast.getIndtIndentDate(), "dd-MM-yyyy");

            //Check if Indent is on Workflow; If Yes disable Editing
            if (wftDAO.countWFTbyWorkId(getindtindentid()) > 1) {
                setbtnEditEnabled(false);
            } else {
                setbtnEditEnabled(true);
            }
//            message="I am here";
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManageIndentMasterAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //This function Delete  given Indent Master Records
    public String DeleteIndentMaster() throws Exception {
        try {
            //TO DELETE THE INDENT DETAIL BY ID
            erpmindtmast = erpminDao.findIndentMasterId(getindtindentid());
            //DELETE ABOVE ID IN ERPMINDTDET
            erpminDao.delete(erpmindtmast);
            IndentList = erpminDao.findIndentsForUserDepartments(Integer.parseInt(getSession().getAttribute("userid").toString()));
            message = "Record successfully deleted";
            erpmindtmast = null;
            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("fk_INDENTDET_IND_master")) {
                message = "The Indent Master cannot be  deleted because of this items is associated with other details";
            } else {
                message = "Exception in Delete method -> ManageIndentAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            }
            return ERROR;
        }
    }

    public String Browse() throws Exception {
        try {
            ErpmIndentMasterDAO erpminDao1 = new ErpmIndentMasterDAO();
            IndentList = erpminDao1.findIndentsForUser(Integer.parseInt(getSession().getAttribute("userid").toString()),getSession().getAttribute("username").toString());
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> MangesupplierAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String PendingIndentDetailReport() throws Exception {

        try {

            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
            dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
            return SUCCESS;
        } catch (Exception e) {
            message = "Error is : " + e.getMessage() + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String SubmitIndentDetail() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "PrePurchase"  + pathSeparator + "Reports" + pathSeparator + "PendingIndentDetail.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\PrePurchase\\Reports\\PendingIndentDetail.jasper");
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
            response.setHeader("Content-Disposition", "attachment; filename=PendingIndentDetail.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            if (getFirstDate().length() != 10) {
                whereCondition = whereCondition + " erpm_indent_master.`Indt_Indent_Date` > str_to_date('01-01-0001','%d-%m-%Y')";
            } else {
                whereCondition = whereCondition + " erpm_indent_master.`Indt_Indent_Date` >= str_to_date('" + getFirstDate() + "','%d-%m-%Y')";
            }

            if (getLastDate().length() != 10) {
                whereCondition = whereCondition + " and erpm_indent_master.`Indt_Indent_Date` >= str_to_date('01-01-0001','%d-%m-%Y')";
            } else {
                whereCondition = whereCondition + " and erpm_indent_master.`Indt_Indent_Date` <= str_to_date('" + getLastDate() + "','%d-%m-%Y')";
            }


            if (getInstitution() == null) {
                whereCondition = whereCondition + " and erpm_indent_master.`Indt_Institution_Id` <> 0 ";
            } else {
                whereCondition = whereCondition + " and erpm_indent_master.`Indt_Institution_Id` = " + getInstitution();
            }


            if (getSubInstitution() == null) {
                whereCondition = whereCondition + " and erpm_indent_master.`Indt_Subinstitution_Id` <> 0 ";
            } else {
                whereCondition = whereCondition + " and erpm_indent_master.`Indt_Subinstitution_Id` = " + getSubInstitution();
            }

            if (getDepartment() == null) {
                whereCondition = whereCondition + " and erpm_indent_master.`Indt_Department_Id` <> 0 ";
            } else {
                whereCondition = whereCondition + " and erpm_indent_master.`Indt_Department_Id` = " + getDepartment();
            }

            hm.put("condition", whereCondition);

            JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
            JasperExportManager.exportReportToPdfStream(jp, baos);
            response.setContentLength(baos.size());
            ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
            inputStream = bis;
            return SUCCESS;
        } catch (Exception e) {
            message = "Error is : " + e.getMessage() + e.getCause();
            return ERROR;
        }
    }

    public void validate() {
        try {
            //message = message + " " + "Entering Validate " + (erpmindtmast.getIndtIndentId()==null) +  (erpmindtdet == null ) ; //+ (erpmindtmast.getIndtIndentId()== null);

            //The If part validates the Indent Master Fields
            if (erpmindtmast.getIndtIndentId() == null) {

                if (erpmindtmast.getInstitutionmaster().getImId() == null) {
                    addFieldError("erpmindtmast.institutionmaster.imId", "Please select Institution/Double click to populate list");
                }

                if (erpmindtmast.getSubinstitutionmaster().getSimId() == null) {
                    addFieldError("erpmindtmast.subinstitutionmaster.simId", "Please select SubInstitution/Double click to populate list");
                }

                if (erpmindtmast.getDepartmentmaster().getDmId() == null) {
                    addFieldError("erpmindtmast.departmentmaster.dmId", "Please select Department/Double click to populate list");
                }

                if (erpmindtmast.getIndtTitle().length() == 0) {
                    addFieldError("erpmindtmast.indtTitle", "Please give Indent Title");
                }

                if (getindentDate().length() == 0) {
                    addFieldError("indentDate", "Please give Indent Date");
                } else {
                    DateUtilities dt = new DateUtilities();
                    if (dt.isValidDate(getindentDate()) == false) {
                        addFieldError("indentDate", "Please give valid Indent Date[dd-mm-yyyy]");
                    }
                }

                if (erpmindtmast.getBudgetheadmaster().getBhmId() == null) {
                    addFieldError("erpmindtmast.budgetheadmaster.bhmId", "Please select Budget Name/Double click to populate list");
                }

                if (erpmindtmast.getErpmGenMasterByIndtCurrencyId().getErpmgmEgmId() == null) {
                    addFieldError("erpmindtmast.erpmGenMasterByIndtCurrencyId.erpmgmEgmId", "Please select currency of purchase/Double click to populate list");
                }

                InitializeLOVs();
            }
        } catch (Exception e) {
            message = message + "Encountered an Exception during Validation" + e.getCause();
        }
    }

    @SkipValidation
    public String Print() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "PrePurchase"  + pathSeparator + "Reports" + pathSeparator + "Indent_Report.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\PrePurchase\\Reports\\Indent_Report.jasper");


        String whereCondition;

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
//            response.setHeader("Content-Disposition", "attachment; filename=Indent_Reports.pdf");
            response.setHeader("", "attachment; filename = Indent_Reports.pdf");  
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            //Setup Where Condition Clause
            if (erpmindtmast.getIndtIndentId() != null) {
           //     whereCondition = "erpm_indent_master.Indt_Indent_Id = " + erpmindtmast.getIndtIndentId();
                 whereCondition = "erpm_indent_master.Indt_Indent_Id = "+ erpmindtmast.getIndtIndentId();


            } else {
//                whereCondition = "erpm_indent_master.Indt_Indent_Id = ";
                 whereCondition = "erpm_indent_master.Indt_Indent_Id <> 0";
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


            whereCondition = "gfr_program_mapping.`GPM_Program_ID` = 19";

            hm.put("condition", whereCondition);
            hm.put("screen_name", "INDENT ITEMS");

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
