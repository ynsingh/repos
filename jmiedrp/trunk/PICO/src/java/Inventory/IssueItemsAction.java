/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

/**
 *
 * @author manauwar
 *
 *
 * <table-filter match-name="view_issue_indent_detail"/>
<table name="view_issue_indent_detail">
<primary-key>
<key-column name='isd_id' />
</primary-key>
</table>
 *
 *
 */
import java.io.*;
import java.math.BigDecimal;
import java.sql.Connection;
import org.apache.struts2.ServletActionContext;
import java.sql.DriverManager;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.ArrayList;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import utils.DateUtilities;
import utils.sendMail;
import utils.DevelopmentSupport;

import java.util.Locale;
import java.util.ResourceBundle;
import com.opensymphony.xwork2.ActionContext;

import pojo.hibernate.*;

public class IssueItemsAction extends DevelopmentSupport {

    private ErpmIssueDetail eid;
    private ErpmIssueMaster eim;
    private ErpmIssueMaster eim1;
    private ErpmIndentDetail eind;
    private ErpmItemMaster eitm;
    private InputStream inputStream;
    private ErpmStockReceived esr = new ErpmStockReceived();
    private ErpmIssueSerialDetail eisd;
    private ViewIssueIndentDetail viid;
    private ErpmIssueDetail eid3 = new ErpmIssueDetail();
    private ViewIssueIndentDetailDAO viidDao = new ViewIssueIndentDetailDAO();
    private ErpmIssueSerialDetailDAO eisdDao = new ErpmIssueSerialDetailDAO();
    private ErpmIssueMasterDAO eimDAO = new ErpmIssueMasterDAO();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private ErpmIssueDetailDAO eidDao = new ErpmIssueDetailDAO();
    private ErpmStockReceivedDAO esrDao = new ErpmStockReceivedDAO();
    private SubinstitutionmasterDAO subImDao = new SubinstitutionmasterDAO();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private EmployeemasterDAO empDao = new EmployeemasterDAO();
    private CommitteemasterDAO cmDao = new CommitteemasterDAO();
    private ErpmIndentMasterDAO indDao = new ErpmIndentMasterDAO();
    private SuppliermasterDAO smDao = new SuppliermasterDAO();
    private ErpmItemCategoryMasterDao erpmIcmDao = new ErpmItemCategoryMasterDao();
    private ErpmItemMasterDAO itemDao = new ErpmItemMasterDAO();
    private ErpmIndentDetailDAO indDetailDao = new ErpmIndentDetailDAO();
    private List<Suppliermaster> smList = new ArrayList<Suppliermaster>();
    private List<ErpmIssueMaster> eimList = new ArrayList<ErpmIssueMaster>();
    private List<Employeemaster> empList = new ArrayList<Employeemaster>();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private List<Subinstitutionmaster> subImList = new ArrayList<Subinstitutionmaster>();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private List<Committeemaster> cmList = new ArrayList<Committeemaster>();
    private List<ErpmIndentMaster> indList = new ArrayList<ErpmIndentMaster>();
    private ErpmIndentMaster indMast = new ErpmIndentMaster();
    private List<ErpmItemCategoryMaster> erpmIcmList1 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList2 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemCategoryMaster> erpmIcmList3 = new ArrayList<ErpmItemCategoryMaster>();
    private List<ErpmItemMaster> itemList = new ArrayList<ErpmItemMaster>();
    private List<ErpmIndentDetail> indDetailList = new ArrayList<ErpmIndentDetail>();
//    private List<ErpmIndentDetail> indDetailList1 = new ArrayList<ErpmIndentDetail>();
    private List<ErpmIssueDetail> issueDetList = new ArrayList<ErpmIssueDetail>();
    private List<ErpmIssueDetail> issueDetList1 = new ArrayList<ErpmIssueDetail>();
    private List<ErpmStockReceived> listStockRecSerialNo = new ArrayList<ErpmStockReceived>();
    private List<ErpmIssueSerialDetail> listIssueSerialDetail = new ArrayList<ErpmIssueSerialDetail>();
    private List<ErpmStockReceived> listStockRecSerialNoLeft = new ArrayList<ErpmStockReceived>();
    private List<ErpmIssueSerialDetail> eisdList = new ArrayList<ErpmIssueSerialDetail>();
    private GfrProgramMappingDAO GfrProgramMappingDao = new GfrProgramMappingDAO();
    private List<String> leftList = new ArrayList<String>();
    private String leftName;
    private Integer leftListKey;
    private String leftListValue;
    private static List<String> rightList = new ArrayList<String>();
    private String rightName;
    private Integer rightListKey = null;
    private String rightListValue = null;
    private Integer EIMID;
    private Integer EIDID;
    private Integer ESRID;
    private Integer EISDID;
    private Short i = 1, j = 2, k = 3;
    private String VAL1;
    private static Integer ItemId;
    private static Integer IssueMasterId;
    private static String Issue_No;
    private static String Issue_Date;
    private static String Indent_Title;
    private static BigDecimal IssQnty_val;
    private static Integer IssueDetailId;
    private String BTNDSBL;
    private String message;
    private String ON_TRANSFER_DISABLE;
    private String ON_TRANSFER_BUTTONS;
    private static Boolean Issue_Item_New_Entry;
    private String IssueQtyReadOnly;
    private String radValue;
    private Boolean IssueToAuthority;
    private Boolean IssueToEmployee;
    private Boolean IssueToSupplier;
    private String issueDate;
    private String returnDueDate;
    private String emailAddress;
    private static Boolean varShowGFR;

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

    public String getBTNDSBL() {
        return BTNDSBL;
    }

    public void setBTNDSBL(String BTNDSBL) {
        this.BTNDSBL = BTNDSBL;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getReturnDueDate() {
        return returnDueDate;
    }

    public void setReturnDueDate(String returnDueDate) {
        this.returnDueDate = returnDueDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public static Integer getIssueDetailId() {
        return IssueDetailId;
    }

    public static void setIssueDetailId(Integer IssueDetailId) {
        IssueItemsAction.IssueDetailId = IssueDetailId;
    }

    public Integer getESRID() {
        return ESRID;
    }

    public void setESRID(Integer ESRID) {
        this.ESRID = ESRID;
    }

    public Integer getEISDID() {
        return EISDID;
    }

    public void setEISDID(Integer EISDID) {
        this.EISDID = EISDID;
    }

    public Boolean getIssueToAuthority() {
        return IssueToAuthority;
    }

    public void setIssueToAuthority(Boolean IssueToAuthority) {
        this.IssueToAuthority = IssueToAuthority;
    }

    public Boolean getIssueToEmployee() {
        return IssueToEmployee;
    }

    public void setIssueToEmployee(Boolean IssueToEmployee) {
        this.IssueToEmployee = IssueToEmployee;
    }

    public Boolean getIssueToSupplier() {
        return IssueToSupplier;
    }

    public void setIssueToSupplier(Boolean IssueToSupplier) {
        this.IssueToSupplier = IssueToSupplier;
    }

    public String getRadValue() {
        return radValue;
    }

    public void setRadValue(String radValue) {
        this.radValue = radValue;
    }

    public ErpmIssueDetail getEid3() {
        return eid3;
    }

    public void setEid3(ErpmIssueDetail eid3) {
        this.eid3 = eid3;
    }

    public List<String> getLeftList() {
        return leftList;
    }

    public void setLeftList(List<String> leftList) {
        this.leftList = leftList;
    }

    public Integer getLeftListKey() {
        return leftListKey;
    }

    public void setLeftListKey(Integer leftListKey) {
        this.leftListKey = leftListKey;
    }

    public List<ErpmIssueDetail> getIssueDetList1() {
        return issueDetList1;
    }

    public void setIssueDetList1(List<ErpmIssueDetail> issueDetList1) {
        this.issueDetList1 = issueDetList1;
    }

    public List<ErpmIssueDetail> getIssueDetList() {
        return issueDetList;
    }

    public void setIssueDetList(List<ErpmIssueDetail> issueDetList) {
        this.issueDetList = issueDetList;
    }

    public String getLeftListValue() {
        return leftListValue;
    }

    public void setLeftListValue(String leftListValue) {
        this.leftListValue = leftListValue;
    }

    public String getLeftName() {
        return leftName;
    }

    public void setLeftName(String leftName) {
        this.leftName = leftName;
    }

    public List<String> getRightList() {
        return rightList;
    }

    public void setRightList(List<String> rightList) {
        IssueItemsAction.rightList = rightList;
    }

    public Integer getRightListKey() {
        return rightListKey;
    }

    public void setRightListKey(Integer rightListKey) {
        this.rightListKey = rightListKey;
    }

    public String getRightListValue() {
        return rightListValue;
    }

    public void setRightListValue(String rightListValue) {
        this.rightListValue = rightListValue;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public String getIssueQtyReadOnly() {
        return IssueQtyReadOnly;
    }

    public void setIssueQtyReadOnly(String IssueQtyReadOnly) {
        this.IssueQtyReadOnly = IssueQtyReadOnly;
    }

    public Boolean getIssue_Item_New_Entry() {
        return Issue_Item_New_Entry;
    }

    public void setIssue_Item_New_Entry(Boolean Issue_Item_New_Entry) {
        IssueItemsAction.Issue_Item_New_Entry = Issue_Item_New_Entry;
    }

    public List<ErpmIssueSerialDetail> getEisdList() {
        return eisdList;
    }

    public void setEisdList(List<ErpmIssueSerialDetail> eisdList) {
        this.eisdList = eisdList;
    }

    public ErpmIssueSerialDetail getEisd() {
        return eisd;
    }

    public void setEisd(ErpmIssueSerialDetail eisd) {
        this.eisd = eisd;
    }

    public String getON_TRANSFER_BUTTONS() {
        return ON_TRANSFER_BUTTONS;
    }

    public void setON_TRANSFER_BUTTONS(String ON_TRANSFER_BUTTONS) {
        this.ON_TRANSFER_BUTTONS = ON_TRANSFER_BUTTONS;
    }

    public String getON_TRANSFER_DISABLE() {
        return ON_TRANSFER_DISABLE;
    }

    public void setON_TRANSFER_DISABLE(String ON_TRANSFER_DISABLE) {
        this.ON_TRANSFER_DISABLE = ON_TRANSFER_DISABLE;
    }

    public List<ErpmStockReceived> getListStockRecSerialNoLeft() {
        return listStockRecSerialNoLeft;
    }

    public void setListStockRecSerialNoLeft(List<ErpmStockReceived> listStockRecSerialNoLeft) {
        this.listStockRecSerialNoLeft = listStockRecSerialNoLeft;
    }

    public List<ErpmIssueSerialDetail> getListIssueSerialDetail() {
        return listIssueSerialDetail;
    }

    public void setListIssueSerialDetail(List<ErpmIssueSerialDetail> listIssueSerialDetail) {
        this.listIssueSerialDetail = listIssueSerialDetail;
    }

    public ViewIssueIndentDetail getViid() {
        return viid;
    }

    public void setViid(ViewIssueIndentDetail viid) {
        this.viid = viid;
    }

    public BigDecimal getIssQnty_val() {
        return IssQnty_val;
    }

    public void setIssQnty_val(BigDecimal IssQnty_val) {
        IssueItemsAction.IssQnty_val = IssQnty_val;
    }

    public String getIssue_Date() {
        return Issue_Date;
    }

    public void setIssue_Date(String Issue_Date) {
        IssueItemsAction.Issue_Date = Issue_Date;
    }

    public String getIndent_Title() {
        return Indent_Title;
    }

    public void setIndent_Title(String Indent_Title) {
        IssueItemsAction.Indent_Title = Indent_Title;
    }

    public ErpmStockReceived getEsr() {
        return esr;
    }

    public void setEsr(ErpmStockReceived esr) {
        this.esr = esr;
    }

    public List<ErpmStockReceived> getListStockRecSerialNo() {
        return listStockRecSerialNo;
    }

    public void setListStockRecSerialNo(List<ErpmStockReceived> listStockRecSerialNo) {
        this.listStockRecSerialNo = listStockRecSerialNo;
    }

    public ErpmItemMaster getEitm() {
        return eitm;
    }

    public void setEitm(ErpmItemMaster eitm) {
        this.eitm = eitm;
    }

    public ErpmIssueMaster getEim1() {
        return eim1;
    }

    public void setEim1(ErpmIssueMaster eim1) {
        this.eim1 = eim1;
    }

    public String getIssue_No() {
        return Issue_No;
    }

    public void setIssue_No(String Issue_No) {
        IssueItemsAction.Issue_No = Issue_No;
    }

//    public List<ErpmIndentDetail> getIndDetailList1() {
//        return indDetailList1;
//    }
//
//    public void setIndDetailList1(List<ErpmIndentDetail> indDetailList1) {
//        this.indDetailList1 = indDetailList1;
//    }
    public Integer getIssueMasterId() {
        return IssueMasterId;
    }

    public void setIssueMasterId(Integer IssueMasterId) {
        IssueItemsAction.IssueMasterId = IssueMasterId;
    }

    public Integer getItemId() {
        return ItemId;
    }

    public void setItemId(Integer ItemId) {
        IssueItemsAction.ItemId = ItemId;
    }

    public ErpmIndentDetail getEind() {
        return eind;
    }

    public void setEind(ErpmIndentDetail eind) {
        this.eind = eind;
    }

    public Integer getEIDID() {
        return EIDID;
    }

    public void setEIDID(Integer EIDID) {
        this.EIDID = EIDID;
    }

    public List<ErpmIndentDetail> getIndDetailList() {
        return indDetailList;
    }

    public void setIndDetailList(List<ErpmIndentDetail> indDetailList) {
        this.indDetailList = indDetailList;
    }

    public String getVAL1() {
        return VAL1;
    }

    public void setVAL1(String VAL1) {
        this.VAL1 = VAL1;
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

    public List<ErpmItemMaster> getItemList() {
        return itemList;
    }

    public void setItemList(List<ErpmItemMaster> itemList) {
        this.itemList = itemList;
    }

    public List<ErpmItemCategoryMaster> getErpmIcmList1() {
        return erpmIcmList1;
    }

    public void setErpmIcmList1(List<ErpmItemCategoryMaster> erpmIcmList1) {
        this.erpmIcmList1 = erpmIcmList1;
    }

    public ErpmIssueDetail getEid() {
        return eid;
    }

    public void setEid(ErpmIssueDetail eid) {
        this.eid = eid;
    }

    public List<Suppliermaster> getSmList() {
        return smList;
    }

    public void setSmList(List<Suppliermaster> smList) {
        this.smList = smList;
    }

    public Integer getEIMID() {
        return EIMID;
    }

    public void setEIMID(Integer EIMID) {
        this.EIMID = EIMID;
    }

    public List<ErpmIssueMaster> getEimList() {
        return eimList;
    }

    public void setEimList(List<ErpmIssueMaster> eimList) {
        this.eimList = eimList;
    }

    public void setIndList(List<ErpmIndentMaster> indList) {
        this.indList = indList;
    }

    public List<ErpmIndentMaster> getIndList() {
        return indList;
    }

    public void setCmList(List<Committeemaster> cmList) {
        this.cmList = cmList;
    }

    public List<Committeemaster> getCmList() {
        return cmList;
    }

    public List<Departmentmaster> getdmList() {
        return dmList;
    }

    public void setdmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<Institutionmaster> getimList() {
        return imList;
    }

    public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public List<Subinstitutionmaster> getsubImList() {
        return subImList;
    }

    public void setsubImList(List<Subinstitutionmaster> subImList) {
        this.subImList = subImList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErpmIssueMaster getEim() {
        return eim;
    }

    public void setEim(ErpmIssueMaster eim) {
        this.eim = eim;
    }

    public void setempList(List<Employeemaster> empList) {
        this.empList = empList;
    }

    public List<Employeemaster> getempList() {
        return this.empList;
    }

    @Override
    public String execute() throws Exception {

        try {
            //initialize the fields
            populate_issueItems();
            eim = null;
            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            setIssueDate(d1.convertDateToString(d, "dd-MM-yyyy"));
            Short i=26;
            Integer count = GfrProgramMappingDao.findCountByProgramId(i);

            if (count > 0) {
                setVarShowGFR(false);
            } else {
                setVarShowGFR(true);
            }
//            setReturnDueDate(d1.convertDateToString(d,"dd-MM-yyyy"));



            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Execute method -> IssueItems Axn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

    }

    //this method is for populating the issue items page (issueitems.jsp)
    public void populate_issueItems() {

        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        subImList = subImDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        dmList = dmDao.findAllDepartmentsForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        smList = smDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        empList = empDao.findByDmId(Integer.valueOf(getSession().getAttribute("dmId").toString()));
        cmList = cmDao.findAll();
        indList = indDao.findIndentsForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), getSession().getAttribute("username").toString());
        setIssueToAuthority(true);
        setIssueToEmployee(true);
        setIssueToSupplier(true);

    }

    //for generating serial no from ErpmIssueSerialDetail Table
    List<ErpmIssueSerialDetail> generateSerialNoFromEISD(Integer issDetId, Integer itemId) {
        List<ErpmIssueSerialDetail> listIssueSerialDetail1 = eisdDao.findByEidId(issDetId);

        //concatinating serial no with serial code
        Integer ItemIdIength = itemId.toString().length();
        String itemSubString = "";
        if (ItemIdIength <= 9) {
            itemSubString = "0000";
        } else if (ItemIdIength > 9 && ItemIdIength <= 99) {
            itemSubString = "000";
        } else if (ItemIdIength > 99 && ItemIdIength <= 999) {
            itemSubString = "00";
        } else if (ItemIdIength > 999 && ItemIdIength <= 9999) {
            itemSubString = "0";
        }

        itemSubString = itemSubString + itemId.toString();
        String serialNoFull = "";
        for (int k = 0; k < listIssueSerialDetail1.size(); k++) {

            serialNoFull = listIssueSerialDetail1.get(k).getErpmStockReceived().getInstitutionmaster().getImShortName() + "/";
            serialNoFull = serialNoFull + listIssueSerialDetail1.get(k).getErpmStockReceived().getSubinstitutionmaster().getSimShortName() + "/";
            serialNoFull = serialNoFull + listIssueSerialDetail1.get(k).getErpmStockReceived().getDepartmentmaster().getDmShortName() + "/";
            serialNoFull = serialNoFull + itemSubString + "/";
            serialNoFull = serialNoFull + listIssueSerialDetail1.get(k).getErpmStockReceived().getStStockSerialNo();
            listIssueSerialDetail1.get(k).getErpmStockReceived().setStStockSerialNo(serialNoFull);

        }

        return listIssueSerialDetail1;
    }

    //for generating serial no from ErpmStockReceived Table
    List<ErpmStockReceived> generateSerialNoFromESR(Integer itemId, Integer eidId) {
        listStockRecSerialNo = esrDao.findRemainingStockAsc(itemId, Integer.valueOf(getSession().getAttribute("dmId").toString()), eidId);
        Integer ItemIdIength1 = itemId.toString().length();
        String itemSubString1 = "";
        if (ItemIdIength1 <= 9) {
            itemSubString1 = "0000";
        } else if (ItemIdIength1 > 9 && ItemIdIength1 <= 99) {
            itemSubString1 = "000";
        } else if (ItemIdIength1 > 99 && ItemIdIength1 <= 999) {
            itemSubString1 = "00";
        } else if (ItemIdIength1 > 999 && ItemIdIength1 <= 9999) {
            itemSubString1 = "0";
        }

        itemSubString1 = itemSubString1 + itemId.toString();

        for (int l = 0; l < listStockRecSerialNo.size(); l++) {
            String serialNoFull = listStockRecSerialNo.get(l).getInstitutionmaster().getImShortName() + "/";
            serialNoFull = serialNoFull + listStockRecSerialNo.get(l).getSubinstitutionmaster().getSimShortName() + "/";
            serialNoFull = serialNoFull + listStockRecSerialNo.get(l).getDepartmentmaster().getDmShortName() + "/";
            serialNoFull = serialNoFull + itemSubString1 + "/";
            serialNoFull = serialNoFull + listStockRecSerialNo.get(l).getStStockSerialNo();
            listStockRecSerialNo.get(l).setStStockSerialNo(serialNoFull);
        }
        return listStockRecSerialNo;
    }

    //this save is for saving issue items (save button of issueitems.jsp page)
    public String Save() throws Exception {
        try {

            //if part is for saving new record and else part is for updating existing record
                DateUtilities dt = new DateUtilities();

            if (eim.getIsmId() == null) {
                if (eim.getErpmIndentMaster().getIndtIndentId() == 0) {
                    eim.setErpmIndentMaster(null);
                }

                //issue items new entry is true if we are saving new record in the table
                Issue_Item_New_Entry = true;
                eim.setIsmIssueDate(dt.convertStringToDate(getIssueDate()));
                issueDate=""+dt.convertDateToString(dt.convertStringToDate(getIssueDate()), "dd-MM-yyyy");
                if (getReturnDueDate() == null || getReturnDueDate().isEmpty()) {
                    eim.setIsmReturnDueDate(null);
                } else {
                    eim.setIsmReturnDueDate(dt.convertStringToDate(getReturnDueDate()));
                }
                eim.setIsmToEmail(getEmailAddress());
                eimDAO.save(eim);
                message = "Issue Items saved successfully.";

            } else {
                if (eim.getErpmIndentMaster().getIndtIndentId() == 0) {
                    eim.setErpmIndentMaster(null);
                }
                //issue item new entry is false if we are updating old record
                Issue_Item_New_Entry = false;
                ErpmIssueMaster eim2 = eimDAO.findByeimId(eim.getIsmId());
                eim.setIsmIssueDate(dt.convertStringToDate(getIssueDate()));
                issueDate=""+dt.convertDateToString(dt.convertStringToDate(getIssueDate()), "dd-MM-yyyy");

                if (getReturnDueDate() == null || getReturnDueDate().isEmpty()) {
                    eim.setIsmReturnDueDate(null);
                } else {
                    eim.setIsmReturnDueDate(dt.convertStringToDate(getReturnDueDate()));
                }
                eim.setIsmToEmail(getEmailAddress());
                eim2 = eim;
                eimDAO.update(eim2);
                message = "Issue Items updated successfully.";

            }

            //issue master list for issue item Browse
            eimList = eimDAO.findByInstitutionId(Short.valueOf(getSession().getAttribute("imId").toString()));

            //if part is for when indent is not known and else part is for when indent is known
            if (eim.getErpmIndentMaster() == null) {
                //finding object for current issue items
                eim1 = eimDAO.findCurrentIssueId(eim.getEmployeemasterByIsmFromEmployeeId().getEmpId(), eim.getDepartmentmasterByIsmFromDepartmentId().getDmId(), eim.getIsmIssueNo());
                //setting current issue item masterid
                IssueMasterId = eim1.getIsmId();
                setIssueMasterId(IssueMasterId);
                Issue_No = eim1.getIsmIssueNo();
                Issue_Date = dt.convertDateToString(eim1.getIsmIssueDate(),"dd-MM-yyyy");
                //if its a new item then set the itemid as null
                if (getIssue_Item_New_Entry() == true) {
                    setItemId(null);
                }

                setIssue_No(Issue_No);
                setIssue_Date(Issue_Date);
                setBTNDSBL("false");
                setVAL1("false");
                setIssQnty_val(BigDecimal.ZERO);

                //issue detail list without indent
                issueDetList1 = eidDao.findByEimId(getIssueMasterId());

                //populating the issue details page
                populate_issueDetails();
                setIndent_Title(null);
                return "SUCCESS1";

            } else {

                //issue master object is created from the issue items page submitted cutrrently
                eim1 = eimDAO.findCurrentIssueIdByIndent(eim.getErpmIndentMaster().getIndtIndentId(), eim.getEmployeemasterByIsmFromEmployeeId().getEmpId(),
                        eim.getDepartmentmasterByIsmFromDepartmentId().getDmId(), eim.getIsmIssueNo());

                //data is fetched from the issue items currently submitted using the object created above
                IssueMasterId = eim1.getIsmId();
                setIssueMasterId(IssueMasterId);
                Issue_No = eim1.getIsmIssueNo();
                Issue_Date = dt.convertDateToString(eim1.getIsmIssueDate(),"dd-MM-yyyy");
                //setting indent title for issue details page
                Indent_Title = eim1.getErpmIndentMaster().getIndtTitle();
                setIssue_No(Issue_No);
                setIssue_Date(Issue_Date);
                setBTNDSBL("true");
                setVAL1("true");

                // indent detail list is created for the current indent id selected in issue items page
                indDetailList = indDetailDao.findByindtIndentId(eim1.getErpmIndentMaster().getIndtIndentId());
                // issue detail is saved for the issue master page
                ErpmIssueDetail eid = new ErpmIssueDetail();
                //if its a new entry then insert the data for the issue detail page
                if (getIssue_Item_New_Entry() == true) {
                    for (int x = 0; x < indDetailList.size(); x++) {
                        eid.setErpmItemMaster(indDetailList.get(x).getErpmItemMaster());
                        eid.setErpmIssueMaster(eim1);
                        eid.setIsdIssuedQuantity(BigDecimal.ZERO);
                        eid.setIsdReceivedQuantity(BigDecimal.ZERO);
                        eid.setIsdReturnedQuantity(BigDecimal.ZERO);
                        eidDao.save(eid);
                    }
                }

                //This is issue detail list for the gridview displayed below in the issue detail page
                issueDetList = eidDao.findByEimIdfromView(getIssueMasterId());

                //populating the issue details page
                populate_issueDetails();
                return "SUCCESS2";
            }

        } catch (Exception e) {
            if (eim.getErpmIndentMaster() == null) {
                return "SUCCESS1";

            } else {
                message = "Exception in Save method -> IssueItems Action" + "  " + e.getMessage() + " Reported Cause is: " + e.getCause();
                return ERROR;
            }
        }

    }

    // This method is for saving issue detail when there is no indent
    public String SaveIssueDetailsWithoutIndent() throws Exception {

        try {

            VAL1 = "false";
            setBTNDSBL("true");
            setIndent_Title("");
            ErpmIssueMaster eim3 = eimDAO.findByEimId(getIssueMasterId());
           // setIndent_Title(eim3.getErpmIndentMaster().getIndtTitle());
            //count the item to check whether that item is already issued or not i.e. already present in issue detail or not
            Integer count1 = eidDao.findCountByIssueMasterAndItemId(getIssueMasterId(), eid.getErpmItemMaster().getErpmimId());

            //Get the ledger quantity of items available in issue master
            Float ledgerQnty = eimDAO.findLedgerValue(eim3.getInstitutionmasterByIsmFromInstituteId().getImId(), eim3.getSubinstitutionmasterByIsmFromSubinstituteId().getSimId(),
                    eim3.getDepartmentmasterByIsmFromDepartmentId().getDmId(), eid.getErpmItemMaster().getErpmimId());

            Integer TotalQnty;
            //if its new issue detail Total available quantity is same as ledger quantity else it is sum of ledger quantity and issued quantity
            if (getEIDID() == null) {
                TotalQnty = ledgerQnty.intValue();
            } else {
                ErpmIssueDetail eid0 = eidDao.findByeidId(getEIDID());
                TotalQnty = ledgerQnty.intValue() + eid0.getIsdIssuedQuantity().intValue();
            }

            //if available quantity for issue is less than issue quantity then issue is not allowed else item will be allowed to issued
            if (TotalQnty < eid.getIsdIssuedQuantity().intValue()) {
                populate_issueDetails();
                message = "You cannot Issue " + eid.getIsdIssuedQuantity() + ". It is more than the Available Quantity i.e. " + TotalQnty + ".";
                return SUCCESS;
            } else {

                //if items issued is zero i.e if we are issuing new item then save it in new object
                if (count1 == 0) {
                    ErpmIssueDetail eid2 = new ErpmIssueDetail();
                    ErpmIssueMaster eim2 = eimDAO.findByEimId(getIssueMasterId());
                    eid2.setErpmIssueMaster(eim2);
                    eid2.setErpmItemMaster(eid.getErpmItemMaster());
                    eid2.setIsdIssuedQuantity(eid.getIsdIssuedQuantity());
                    eid2.setIsdReceivedQuantity(BigDecimal.ZERO);
                    eid2.setIsdReturnedQuantity(BigDecimal.ZERO);
                    setItemId(eid.getErpmItemMaster().getErpmimId());
                    eidDao.save(eid2);
                    setEid3(eid2);
                    setIssQnty_val(eid3.getIsdIssuedQuantity());
                    message = "Issue Detail Saved Successfully";
                } //else check for issue detail id to update the issued items
                else {

                    //if issue detail id is not null then update the item
                    if (getEIDID() != null) {
                        ErpmIssueDetail eid2 = eidDao.findByeidId(getEIDID());
                        ErpmIssueMaster eim2 = eimDAO.findByEimId(getIssueMasterId());
                        eid2.setErpmIssueMaster(eim2);
                        eid2.setErpmItemMaster(eid.getErpmItemMaster());
                        eid2.setIsdIssuedQuantity(eid.getIsdIssuedQuantity());
//                        eid2.setIsdReceivedQuantity(eid.getIsdReceivedQuantity());

                        eidDao.update(eid2);
                        setEid3(eid2);
                    } //if issue detail id is null then save the item in new object
                    else {
                        ErpmIssueDetail eid2 = new ErpmIssueDetail();
                        ErpmIssueMaster eim2 = eimDAO.findByEimId(getIssueMasterId());
                        eid2.setErpmIssueMaster(eim2);
                        eid2.setErpmItemMaster(eid.getErpmItemMaster());
                        eid2.setIsdIssuedQuantity(eid.getIsdIssuedQuantity());
                        eid2.setIsdReceivedQuantity(BigDecimal.ZERO);

                        eidDao.update(eid2);
                        setEid3(eid2);
                    }
                    message = "Issue Detail Updated Successfully ";// + qnty;
                }


                //This is issue detail list for the gridview displayed below in the issue detail page
                issueDetList1 = eidDao.findByEimId(getIssueMasterId());

                populate_issueDetails();

//                ErpmIssueDetail eid2 = eidDao.findByeidId(getEIDID());

                //create object for item master
                eitm = itemDao.findByItemId(eid.getErpmItemMaster().getErpmimId());

                // prepare count for the number of record of issue serial detail by passing issue detail id
                Integer count = eisdDao.findCountByIssueDetailId(eid3.getIsdId());

                // if serial number applicable option is yes (i.e. Y) and issued quantity is nor zero then items will be issued using either LIFO, FIFO or user choice
                if (eitm.getErpmimSerialNoApplicable() == 'Y' && eid.getIsdIssuedQuantity().intValue() != 0) {

                    //policy =1 is for FIFO
                    if (eitm.getErpmimIssuePolicy().equals("1")) {

                        listStockRecSerialNo = esrDao.findByItemIdAsc(getItemId(), Integer.valueOf(getSession().getAttribute("dmId").toString()));

                        //To save issue_detail_id along with stock_serial_detail_id in issue serial detail table if it is a new record

                        if (count == 0) {
                            ErpmIssueSerialDetail eisd1 = new ErpmIssueSerialDetail();

                            for (int y = 0; y < ((Integer) eid.getIsdIssuedQuantity().intValue()); y++) {
                                eisd1.setErpmIssueDetail(eid3);
                                eisd1.setErpmStockReceived(listStockRecSerialNo.get(y));
                                eisdDao.save(eisd1);
                            }

                            setEisd(eisd1);

                        }

                        setBTNDSBL("true");
                        listIssueSerialDetail = generateSerialNoFromEISD(eid3.getIsdId(), getItemId());
                        setIssueDetailId(eid3.getIsdId());
                        listStockRecSerialNoLeft.clear();
                        message = "Items Selected Using FIFO " + listIssueSerialDetail.size();

                        return "SUCCESS1";

                    } //policy =2 is for LIFO
                    else if (eitm.getErpmimIssuePolicy().equals("2")) {

                        listStockRecSerialNo = esrDao.findByItemIdDesc(getItemId(), Integer.valueOf(getSession().getAttribute("dmId").toString()));
                        //To save issue_detail_id along with stock_serial_detail_id in issue serial detail table

                        if (count == 0) {
                            ErpmIssueSerialDetail eisd2 = new ErpmIssueSerialDetail();


                            for (int z = 0; z < ((Integer) eid.getIsdIssuedQuantity().intValue()); z++) {
                                eisd2.setErpmIssueDetail(eid3);
                                eisd2.setErpmStockReceived(listStockRecSerialNo.get(z));
                                eisdDao.save(eisd2);

                            }

                            setEisd(eisd2);
                        }
                        listStockRecSerialNoLeft.clear();
                        setBTNDSBL("true");
                        setIssueDetailId(eid3.getIsdId());
                        listIssueSerialDetail = generateSerialNoFromEISD(eid3.getIsdId(), getItemId());
                        message = "Items Selected Using LIFO " + listIssueSerialDetail.size();

                        return "SUCCESS1";

                    } //policy =3 is for user choice
                    else if (eitm.getErpmimIssuePolicy().equals("3")) {

                        listStockRecSerialNo = generateSerialNoFromESR(getItemId(), eid3.getIsdId());

                        listIssueSerialDetail = generateSerialNoFromEISD(eid3.getIsdId(), getItemId());
                        setIssueDetailId(eid3.getIsdId());

                        return "SUCCESS2";

                    }

                }

                return SUCCESS;
            }
        } catch (Exception e) {
            message = "Exception in SaveIssueDetailsWithoutIndent method -> IssueItems Action" + "  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //This method is for saving or updating the issuedetail if indent is available
    public String SaveIssueDetailsWithIndent() throws Exception {

        try {

            VAL1 = "true";
            ErpmIssueMaster eim3 = eimDAO.findByEimId(getIssueMasterId());
            setIndent_Title(eim3.getErpmIndentMaster().getIndtTitle());
            //item list is created for current institution
            itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            ErpmIssueDetail eid2 = eidDao.findByeidId(getEIDID());
            //find ledger quantity from the issue master
            Float ledgerQnty = eimDAO.findLedgerValue(eim3.getInstitutionmasterByIsmFromInstituteId().getImId(), eim3.getSubinstitutionmasterByIsmFromSubinstituteId().getSimId(),
                    eim3.getDepartmentmasterByIsmFromDepartmentId().getDmId(), eid.getErpmItemMaster().getErpmimId());

            Integer TotalQnty;
            //if its a new issue detail then total available quantity is same as ledger quantity else it is sum of leger quantity and issued quantity
            if (getEIDID() == null) {
                TotalQnty = ledgerQnty.intValue();
            } else {
                TotalQnty = ledgerQnty.intValue() + eid2.getIsdIssuedQuantity().intValue();
            }

            //if available quantity for issue is less than issue quantity then issue is not allowed else item will be allowed to issued
            if (TotalQnty < eid.getIsdIssuedQuantity().intValue()) {
                populate_issueDetails();
                issueDetList = eidDao.findByEimIdfromView(eid2.getErpmIssueMaster().getIsmId());
                setBTNDSBL("true");
                message = "You cannot Issue " + eid.getIsdIssuedQuantity() + ". It is more than the Available Quantity i.e. " + TotalQnty + ".";
                return SUCCESS;
            } else {


                // View is created  from issue and indendt detail to get the issuedQuantity
                ViewIssueIndentDetail viid1 = new ViewIssueIndentDetail();
                viid1 = viidDao.findByeidId(getEIDID());
                setIssQnty_val(eid.getIsdIssuedQuantity());

                setViid(viid1);

                // checking if approved quantity is less than issue Quantity then give error
                if (viid.getIndentApprovedQuantity() < getIssQnty_val().intValue()) {
                    addFieldError("eid.isdIssuedQuantity", "You cannot enter Issue Quantity greater than the Indent's approved Quantity i.e. " + viid.getIndentApprovedQuantity());
                    setItemId(eid2.getErpmItemMaster().getErpmimId());
                    issueDetList = eidDao.findByEimIdfromView(eid2.getErpmIssueMaster().getIsmId());
                    return INPUT;
                } else {
                    //set issue quantity and update the issue detail
                    eid2.setIsdIssuedQuantity(eid.getIsdIssuedQuantity());
                    eidDao.update(eid2);
                    message = "Issue Quantity Updated successfully.";
                    setBTNDSBL("true");
                    setIssQnty_val(eid.getIsdIssuedQuantity());
                    setIssue_No(Issue_No);
                    setIssue_Date(Issue_Date);
                    setItemId(eid2.getErpmItemMaster().getErpmimId());

                    // prepare issue detail list from issue master id
                    issueDetList = eidDao.findByEimIdfromView(eid2.getErpmIssueMaster().getIsmId());

                    //create object for item master
                    eitm = itemDao.findByItemId(getItemId());

                    // prepare count for the number of record of issue serial detail by passing issue detail id
                    Integer count = eisdDao.findCountByIssueDetailId(eid2.getIsdId());



                    // if serial no applicable is yes (i.e. Y) then items will be issued using either LIFO, FIFO or user choice
                    if (eitm.getErpmimSerialNoApplicable() == 'Y' && eid.getIsdIssuedQuantity().intValue() != 0) {
                        //policy =1 is for FIFO
                        if (eitm.getErpmimIssuePolicy().equals("1")) {
                            listStockRecSerialNo = esrDao.findByItemIdAsc(getItemId(), Integer.valueOf(getSession().getAttribute("dmId").toString()));


                            //To save issue_detail_id along with stock_serial_detail_id in issue serial detail table if it is a new record

                            if (count == 0) {
                                ErpmIssueSerialDetail eisd1 = new ErpmIssueSerialDetail();

                                for (int y = 0; y < ((Integer) eid.getIsdIssuedQuantity().intValue()); y++) {
                                    eisd1.setErpmIssueDetail(eid2);
                                    eisd1.setErpmStockReceived(listStockRecSerialNo.get(y));
                                    eisdDao.save(eisd1);
                                }

                                setEisd(eisd1);

                            }
                 
                            setBTNDSBL("true");
                            setIssueDetailId(eid2.getIsdId());

                            listIssueSerialDetail = generateSerialNoFromEISD(eid2.getIsdId(), getItemId());

                            listStockRecSerialNoLeft.clear();
                            message = "Items Selected Using FIFO ";

                            return "SUCCESS1";

                            //policy =2 is for LIFO
                        } else if (eitm.getErpmimIssuePolicy().equals("2")) {

                            listStockRecSerialNo = esrDao.findByItemIdDesc(getItemId(), Integer.valueOf(getSession().getAttribute("dmId").toString()));
                            //To save issue_detail_id along with stock_serial_detail_id in issue serial detail table

                            if (count == 0) {
                                ErpmIssueSerialDetail eisd2 = new ErpmIssueSerialDetail();


                                for (int z = 0; z < ((Integer) eid.getIsdIssuedQuantity().intValue()); z++) {
                                    eisd2.setErpmIssueDetail(eid2);
                                    eisd2.setErpmStockReceived(listStockRecSerialNo.get(z));
                                    eisdDao.save(eisd2);

                                }

                                setEisd(eisd2);
                            }
                            listStockRecSerialNoLeft.clear();
                            setIssueDetailId(eid2.getIsdId());
                            setBTNDSBL("true");

                            listIssueSerialDetail = generateSerialNoFromEISD(eid2.getIsdId(), getItemId());

                            message = "Items Selected Using LIFO ";

                            return "SUCCESS1";

                            //issue policy is for user choice
                        } else if (eitm.getErpmimIssuePolicy().equals("3")) {


                            listStockRecSerialNo = generateSerialNoFromESR(getItemId(), eid2.getIsdId());

                            listIssueSerialDetail = generateSerialNoFromEISD(eid2.getIsdId(), getItemId());
                            setIssueDetailId(eid2.getIsdId());
                            return "SUCCESS2";
                        }

                    }
                    return SUCCESS;
                }
            }
        } catch (Exception e) {
            message = "Exception in SaveIssueDetailsWithIndent method -> IssueItems Action" + "  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

    }

    //this method is for coming back to the issue detail page afer issueing items
    public String IssueDone() {

        erpmIcmList1 = erpmIcmDao.findByErpmicmItemLevel(i);

        //Prepare Item Category List
        erpmIcmList2 = erpmIcmDao.findByErpmicmItemLevel(j);

        //Prepare Item Sub Category List
        erpmIcmList3 = erpmIcmDao.findByErpmicmItemLevel(k);

        //prepare item types
        itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
        //set the issue quantity to zero
        setIssQnty_val(BigDecimal.ZERO);

        if (getIndent_Title().isEmpty() || getIndent_Title() == null) {
            //This is issue detail list for the gridview displayed below in the issue detail page
            issueDetList1 = eidDao.findByEimId(getIssueMasterId());
            setBTNDSBL("true");

            return "SUCCESS1";
        } else {
            issueDetList = eidDao.findByEimIdfromView(getIssueMasterId());
            setBTNDSBL("true");
            setVAL1("true");
            return "SUCCESS2";
        }

    }

    //This method is for browsing issue items
    @SkipValidation
    public String Browse() throws Exception {
        try {

            eimList = eimDAO.findByInstitutionId(Short.parseShort(getSession().getAttribute("imId").toString()));

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> IsssueItemsAxn" + "Reported Cause is: " + e.getMessage();
            return ERROR;
        }
    }

    //this method is for sending mail to  authority or employee or supplier on issueitemsBrowse.jsp
    public String EmailIssueItems() throws Exception {
        try {

            eim = eimDAO.findByEimId(getEIMID());

            eimList = eimDAO.findByInstitutionId(Short.valueOf(getSession().getAttribute("imId").toString()));
            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            if (!bundle.getString("emailFrom").equals("") && !bundle.getString("emailUser").equals("") && !bundle.getString("emailFromPasswd").equals("")) {
                String toEmailAddress = eim.getIsmToEmail().toString();
                String emailSubject = "Issue Items mail";
                String emailMessage = "<html><head><title>Issue Items Detail</title></head>"
                        + "<body><table width='500' border='0' align='center' cellpadding='15' cellspacing='0' style='font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12pt; color:#5a5a5a;'>"
                        + "<tr>"
                        + "<b>Issue items are</b><br/><br/>"
                        + "<b>Issue Number : </b>" + eim.getIsmIssueNo() + "&nbsp   &nbsp &nbsp &nbsp    <b>Issue Date : </b>" + eim.getIsmIssueDate();

                if (eim.getErpmIndentMaster() != null) {
                    indMast = indDao.findIndentMasterId(eim.getErpmIndentMaster().getIndtIndentId());
                    emailMessage = emailMessage + "<br/><b>Indent Title : </b>" + indMast.getIndtTitle().toString();
                }

                emailMessage = emailMessage + "<br/><br/>";
                issueDetList = eidDao.findByEimId(getEIMID());
                String QtyAndSerial = "";
                String itemAndQty = "";

                /*preparing the list of issue items to be mailed with indent and without indent, if serial number is
                applicable then we are also sending the serial numbers otherwise only item name and quantity */
                for (Integer i = 0; i < issueDetList.size(); i++) {
                    if (issueDetList.get(i).getErpmItemMaster().getErpmimSerialNoApplicable() == 'Y') {
                        itemAndQty = "";

                        itemAndQty = (i + 1) + ". " + issueDetList.get(i).getErpmItemMaster().getErpmimItemBriefDesc() + " : " + issueDetList.get(i).getIsdIssuedQuantity() + " " + issueDetList.get(i).getErpmItemMaster().getErpmGenMaster().getErpmgmEgmDesc();
                        eisdList = eisdDao.findByEidId(issueDetList.get(i).getIsdId());
                        String serialNo = "&nbsp &nbsp   (";
                        for (Integer j = 0; j < eisdList.size(); j++) {
                            serialNo = serialNo + eisdList.get(j).getErpmStockReceived().getStStockSerialNo();
                            if (j == (eisdList.size() - 1)) {
                                serialNo = serialNo + ")";
                            } else {
                                serialNo = serialNo + ", ";
                            }

                        }
                        QtyAndSerial = itemAndQty + "<br/>" + "   " + serialNo + "<br/><br/>";

                    } else {
                        QtyAndSerial = (i + 1) + ". " + issueDetList.get(i).getErpmItemMaster().getErpmimItemBriefDesc() + " , " + issueDetList.get(i).getIsdIssuedQuantity() + " " + issueDetList.get(i).getErpmItemMaster().getErpmGenMaster().getErpmgmEgmDesc() + "<br/><br/>";
                    }

                    emailMessage = emailMessage + QtyAndSerial;
                }

                emailMessage = emailMessage
                        + "<br/><br/><p>Regards,<br />Administrator, PICO Module<br /></p><p><br /><br />THIS IS AN AUTOMATED MESSAGE; PLEASE DO NOT REPLY. </p></td></tr>"
                        + "</table></body></html>";

                sendMail.sendMail(bundle.getString("emailFrom"), bundle.getString("emailUser"), bundle.getString("emailFromPasswd"), toEmailAddress, "", emailSubject, emailMessage);
                message = "Email sent successfully";
            } else {
                message = "Email not send successfully";
            }
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in EmailIssueItems method -> IsssueItemsAxn" + "Reported Cause is: " + e.getMessage();
            return ERROR;
        }
    }


    //for adding more items with serial no to the issued items
    @SkipValidation
    public String AddSerialNo() throws Exception {
        try {

            eitm = itemDao.findByItemId(getItemId());
            ErpmIssueSerialDetail eisd1 = new ErpmIssueSerialDetail();
            ErpmIssueDetail eid4 = eidDao.findByeidId(getIssueDetailId());

            //checking for without indent issueing of items
            if (!getIndent_Title().equals("")) {
                //find view issue indent detail object by pasing issue detai id
           ViewIssueIndentDetail viid1 = viidDao.findByeidId(eid4.getIsdId());

           //if indent approved quantity is less than the issued quantity then issue will not be allowed else issue will be allowed
           if(viid1.getIndentApprovedQuantity() <= eid4.getIsdIssuedQuantity().intValue())
           {
               //getting item list with generated serial no
                listIssueSerialDetail = generateSerialNoFromEISD(eid4.getIsdId(), getItemId());
                itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                setItemId(eitm.getErpmimId());
                setVAL1("true");
               message = "Cannot Issue more than Apporved Quantity";
           }
         else
           {
               //if issue policy is 1 its FIFO, 2 for LIFO, 3 for user choice
                if (eitm.getErpmimIssuePolicy().equals("1")) {
                    listStockRecSerialNo = esrDao.findByItemIdAsc(getItemId(), Integer.valueOf(getSession().getAttribute("dmId").toString()));
                }
                if (eitm.getErpmimIssuePolicy().equals("2")) {
                    listStockRecSerialNo = esrDao.findByItemIdDesc(getItemId(), Integer.valueOf(getSession().getAttribute("dmId").toString()));
                }

                //checking whether items are there in database or not if not issue is not possible else allow issue
                if(listStockRecSerialNo.isEmpty())
                {
                    message = "no more items left to issue";
                }
          else
                {
                eisd1.setErpmIssueDetail(eid4);
                eisd1.setErpmStockReceived(listStockRecSerialNo.get(0));
                eisdDao.save(eisd1);
                eid4.setIsdIssuedQuantity(eid4.getIsdIssuedQuantity().add(BigDecimal.ONE));
                eidDao.update(eid4);
                setIssQnty_val(eid4.getIsdIssuedQuantity());
                setIssueMasterId(eid4.getErpmIssueMaster().getIsmId());
                listIssueSerialDetail = generateSerialNoFromEISD(eid4.getIsdId(), getItemId());
                itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                setItemId(eitm.getErpmimId());
                setVAL1("true");
               }
            }
            }
            //else part is for issue items with indent
            else {

//all other codes are similar to the without indent case above checked in if condition
                if (eitm.getErpmimIssuePolicy().equals("1")) {
                    listStockRecSerialNo = esrDao.findByItemIdAsc(getItemId(), Integer.valueOf(getSession().getAttribute("dmId").toString()));
                }
                if (eitm.getErpmimIssuePolicy().equals("2")) {
                    listStockRecSerialNo = esrDao.findByItemIdDesc(getItemId(), Integer.valueOf(getSession().getAttribute("dmId").toString()));
                }
                //if list is empty means no more items left to issue give message else allow to issue
                if(listStockRecSerialNo.isEmpty())
                {
                    listIssueSerialDetail = generateSerialNoFromEISD(eid4.getIsdId(), getItemId());
                     itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                     setItemId(eitm.getErpmimId());
                     setVAL1("true");
                    message = "no more items left to issue";
                }
          else
                {
                eisd1.setErpmIssueDetail(eid4);
                eisd1.setErpmStockReceived(listStockRecSerialNo.get(0));
                eisdDao.save(eisd1);
                eid4.setIsdIssuedQuantity(eid4.getIsdIssuedQuantity().add(BigDecimal.ONE));
                eidDao.update(eid4);
                setIssQnty_val(eid4.getIsdIssuedQuantity());
                setIssueMasterId(eid4.getErpmIssueMaster().getIsmId());
                listIssueSerialDetail = generateSerialNoFromEISD(eid4.getIsdId(), getItemId());
                itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                setItemId(eitm.getErpmimId());
                setVAL1("true");
                }
            }

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in AddSerialNo method -> IsssueItemsAxn " + "Reported Cause is: " + e.getMessage();
            return ERROR;
        }
    }

    // This method is for the user choice options by which user can issue or remove issue items
    public String IssueAndRemoveItemsUserChoice() throws Exception {
        try {

            listStockRecSerialNo = esrDao.findByItemIdAsc(getItemId(), Integer.valueOf(getSession().getAttribute("dmId").toString()));
            //generating serial no for list stock receive
            Integer ItemIdIength = getItemId().toString().length();
            String itemSubString = "";
            if (ItemIdIength <= 9) {
                itemSubString = "0000";
            } else if (ItemIdIength > 9 && ItemIdIength <= 99) {
                itemSubString = "000";
            } else if (ItemIdIength > 99 && ItemIdIength <= 999) {
                itemSubString = "00";
            } else if (ItemIdIength > 999 && ItemIdIength <= 9999) {
                itemSubString = "0";
            }

            itemSubString = itemSubString + getItemId().toString();

            for (int l = 0; l < listStockRecSerialNo.size(); l++) {
                String serialNoFull = listStockRecSerialNo.get(l).getInstitutionmaster().getImShortName() + "/";
                serialNoFull = serialNoFull + listStockRecSerialNo.get(l).getSubinstitutionmaster().getSimShortName() + "/";
                serialNoFull = serialNoFull + listStockRecSerialNo.get(l).getDepartmentmaster().getDmShortName() + "/";
                serialNoFull = serialNoFull + itemSubString + "/";
                serialNoFull = serialNoFull + listStockRecSerialNo.get(l).getStStockSerialNo();

                listStockRecSerialNo.get(l).setStStockSerialNo(serialNoFull);
            }
            ErpmIssueDetail eid2 = eidDao.findByeidId(getEIDID());
            listIssueSerialDetail = eisdDao.findByEidId(eid2.getIsdId());

            //generating serial no for stock receive serial detail
            Integer ItemIdIength1 = getItemId().toString().length();
            String itemSubString1 = "";
            if (ItemIdIength1 <= 9) {
                itemSubString1 = "0000";
            } else if (ItemIdIength1 > 9 && ItemIdIength1 <= 99) {
                itemSubString1 = "000";
            } else if (ItemIdIength1 > 99 && ItemIdIength1 <= 999) {
                itemSubString1 = "00";
            } else if (ItemIdIength1 > 999 && ItemIdIength1 <= 9999) {
                itemSubString1 = "0";
            }

            itemSubString1 = itemSubString1 + getItemId().toString();

            for (int k = 0; k < listIssueSerialDetail.size(); k++) {
                String serialNoFull1 = listIssueSerialDetail.get(k).getErpmStockReceived().getInstitutionmaster().getImShortName() + "/";
                serialNoFull1 = serialNoFull1 + listIssueSerialDetail.get(k).getErpmStockReceived().getSubinstitutionmaster().getSimShortName() + "/";
                serialNoFull1 = serialNoFull1 + listIssueSerialDetail.get(k).getErpmStockReceived().getDepartmentmaster().getDmShortName() + "/";
                serialNoFull1 = serialNoFull1 + itemSubString + "/";
                serialNoFull1 = serialNoFull1 + listIssueSerialDetail.get(k).getErpmStockReceived().getStStockSerialNo();
                listIssueSerialDetail.get(k).getErpmStockReceived().setStStockSerialNo(serialNoFull1);
            }
//             listIssueSerialDetail= generateSerialNoFromEISD(eid2.getIsdId(),getItemId());
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in IssueItemsUserChoice method -> IsssueItemsAxn" + "Reported Cause is: " + e.getMessage();
            return ERROR;
        }
    }

    //This method is for editing the issue items
    public String EditIssueItems() throws Exception {
        try {

            eim = eimDAO.findByeimId(getEIMID());
            DateUtilities dt = new DateUtilities();
            issueDate = dt.convertDateToString(eim.getIsmIssueDate(), "dd-MM-yyyy");
            setEmailAddress(eim.getIsmToEmail());
            if (eim.getIsmReturnDueDate() == null) {
                returnDueDate = "";
            } else {
                returnDueDate = dt.convertDateToString(eim.getIsmReturnDueDate(), "dd-MM-yyyy");
            }

            populate_issueItems();

            // for enabling and disabling the dropdown according to the radio button option selected
            if (eim.getCommitteemaster() != null) {
                setRadValue("A");
                setIssueToEmployee(true);
                setIssueToSupplier(true);
                setIssueToAuthority(false);


            } else if (eim.getEmployeemasterByIsmToEmployeeId() != null) {
                setRadValue("E");
                setIssueToEmployee(false);
                setIssueToSupplier(true);
                setIssueToAuthority(true);

            } else if (eim.getSuppliermaster() != null) {
                setRadValue("S");
                setIssueToEmployee(true);
                setIssueToSupplier(false);
                setIssueToAuthority(true);

            }

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in EditIssueItems method -> IssueItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //In user choice option we are allowing the user to issue items
    public String IssueSerialNumber() throws Exception {
        try {

//            esr = esrDao.findByesrId(getESRID());
            ErpmIssueDetail eid2 = new ErpmIssueDetail();
            eid2 = eidDao.findByeidId(getIssueDetailId());

            ErpmStockReceived esr2 = new ErpmStockReceived();
            esr2 = esrDao.findByesrId(getESRID());

            Integer count1 = eisdDao.findCountByIssueDetailId(eid2.getIsdId());
            Integer count = eisdDao.findCountByIssueDetailIdAndIssueSerialRecieveId(eid2.getIsdId(), esr2.getStId());

            //prepare item list
            itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

            if (count1 < IssQnty_val.intValue()) {

                if (count == 0) {
                    ErpmIssueSerialDetail eisd1 = new ErpmIssueSerialDetail();

                    eisd1.setErpmIssueDetail(eid2);
                    eisd1.setErpmStockReceived(esr2);
                    eisdDao.save(eisd1);
                }
                message = "Serial Number Issued";
            } else {
                message = "You cannot issue more than " + IssQnty_val.intValue();
            }



            listStockRecSerialNo = generateSerialNoFromESR(esr2.getErpmItemMaster().getErpmimId(), eid2.getIsdId());
            listIssueSerialDetail = eisdDao.findByEidId(eid2.getIsdId());

            //generating the proper serial no concatinating institution, subinstitution and departiment
            Integer ItemIdIength = getItemId().toString().length();
            String itemSubString = "";
            if (ItemIdIength <= 9) {
                itemSubString = "0000";
            } else if (ItemIdIength > 9 && ItemIdIength <= 99) {
                itemSubString = "000";
            } else if (ItemIdIength > 99 && ItemIdIength <= 999) {
                itemSubString = "00";
            } else if (ItemIdIength > 999 && ItemIdIength <= 9999) {
                itemSubString = "0";
            }

            itemSubString = itemSubString + getItemId().toString();

            for (int k = 0; k < listIssueSerialDetail.size(); k++) {
                String serialNoFull = listIssueSerialDetail.get(k).getErpmStockReceived().getInstitutionmaster().getImShortName() + "/";
                serialNoFull = serialNoFull + listIssueSerialDetail.get(k).getErpmStockReceived().getSubinstitutionmaster().getSimShortName() + "/";
                serialNoFull = serialNoFull + listIssueSerialDetail.get(k).getErpmStockReceived().getDepartmentmaster().getDmShortName() + "/";
                serialNoFull = serialNoFull + itemSubString + "/";
                serialNoFull = serialNoFull + listIssueSerialDetail.get(k).getErpmStockReceived().getStStockSerialNo();
                listIssueSerialDetail.get(k).getErpmStockReceived().setStStockSerialNo(serialNoFull);
            }
//             listIssueSerialDetail= generateSerialNoFromEISD(eid2.getIsdId(),getItemId());

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in IssueSerialNumber() method -> IssueItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //In user choice options we are allowing the user to remove the issue items in user choice option
    public String RemoveSerialNumber() throws Exception {
        try {
            //counting the no of record for given issue detail id if it is received or not
            Integer count = eisdDao.findCountByIssueSerialDetailId(getEISDID());


//if count is not zero means record is not received then delete it from the issue serial detail table else it cannot be removed it is already received
            if (count != 0) {
                eisd = eisdDao.findByeisdId(getEISDID());
                if (eisd.isIssdReceived() == false) {
                    eisdDao.delete(eisd);
                    message = "Item Removed successfully";
                } else {
                    message = "Item cannot be removed, Item is already recevied";
                }
            }

            listStockRecSerialNo = generateSerialNoFromESR(getItemId(), getIssueDetailId());

            listIssueSerialDetail = eisdDao.findByEidId(getIssueDetailId());

            // generating the serial no and concatinating inst, subinst and department
            Integer ItemIdIength = getItemId().toString().length();
            String itemSubString = "";
            if (ItemIdIength <= 9) {
                itemSubString = "0000";
            } else if (ItemIdIength > 9 && ItemIdIength <= 99) {
                itemSubString = "000";
            } else if (ItemIdIength > 99 && ItemIdIength <= 999) {
                itemSubString = "00";
            } else if (ItemIdIength > 999 && ItemIdIength <= 9999) {
                itemSubString = "0";
            }

            itemSubString = itemSubString + getItemId().toString();

            for (int k = 0; k < listIssueSerialDetail.size(); k++) {
                String serialNoFull = listIssueSerialDetail.get(k).getErpmStockReceived().getInstitutionmaster().getImShortName() + "/";
                serialNoFull = serialNoFull + listIssueSerialDetail.get(k).getErpmStockReceived().getSubinstitutionmaster().getSimShortName() + "/";
                serialNoFull = serialNoFull + listIssueSerialDetail.get(k).getErpmStockReceived().getDepartmentmaster().getDmShortName() + "/";
                serialNoFull = serialNoFull + itemSubString + "/";
                serialNoFull = serialNoFull + listIssueSerialDetail.get(k).getErpmStockReceived().getStStockSerialNo();
                listIssueSerialDetail.get(k).getErpmStockReceived().setStStockSerialNo(serialNoFull);
            }


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Remove method -> IssueItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //for editing issue detials page with indent
    public String EditIssueDetailsWithIndent() throws Exception {

        try {

            eid = eidDao.findByeidId(getEIDID());
            eitm = itemDao.findByItemId(eid.getErpmItemMaster().getErpmimId());
            setItemId(eitm.getErpmimId());
            // checking if serial no is not applicable  then items will be directly edited else item wil be modified with serial no
            if (eid.getErpmItemMaster().getErpmimSerialNoApplicable() == 'N') {

                setIndent_Title(eid.getErpmIssueMaster().getErpmIndentMaster().getIndtTitle());
                setIssue_No(Issue_No);
                setIssue_Date(Issue_Date);
                setIssQnty_val(eid.getIsdIssuedQuantity());
                setItemId(eid.getErpmItemMaster().getErpmimId());

                if (eid.getIsdIssuedQuantity().intValue() > 0) {
                    setIssueQtyReadOnly("true");
                }

                //prepare item type
                erpmIcmList1 = erpmIcmDao.findByErpmicmItemLevel(i);

                //Prepare Item Category List
                erpmIcmList2 = erpmIcmDao.findByErpmicmItemLevel(j);

                //Prepare Item Sub Category List
                erpmIcmList3 = erpmIcmDao.findByErpmicmItemLevel(k);

                //prepare item types
                itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

                ErpmIssueDetail eid2 = eidDao.findByeidId(getEIDID());

                //issue detail list when indent is present
                issueDetList = eidDao.findByEimIdfromView(eid2.getErpmIssueMaster().getIsmId());
                setIssueMasterId(eid2.getErpmIssueMaster().getIsmId());

                message = "Edit Issue Detail Page ";
                setEid(eid2);

                return SUCCESS;
                // if serial no. is applicable then go to the serial no. of the items and edit it
            } else if (eid.getErpmItemMaster().getErpmimSerialNoApplicable() == 'Y' && eid.getIsdIssuedQuantity().intValue() != 0) {
                if (eitm.getErpmimIssuePolicy().equals("1")) {

                    listIssueSerialDetail = generateSerialNoFromEISD(eid.getIsdId(), getItemId());
                    setVAL1("true");
                    itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                    setIssQnty_val(eid.getIsdIssuedQuantity());
                    setIssueDetailId(eid.getIsdId());
                    setIndent_Title(eid.getErpmIssueMaster().getErpmIndentMaster().getIndtTitle());
                    setItemId(eid.getErpmItemMaster().getErpmimId());
                    message = "Add or Delete Serial No for items";
                    return "SUCCESS1";
                } else if (eitm.getErpmimIssuePolicy().equals("2")) {

                    listIssueSerialDetail = generateSerialNoFromEISD(eid.getIsdId(), getItemId());
                    setVAL1("true");
                    itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                    setIssQnty_val(eid.getIsdIssuedQuantity());
                    setIssueDetailId(eid.getIsdId());
                    setIndent_Title(eid.getErpmIssueMaster().getErpmIndentMaster().getIndtTitle());
                    setItemId(eid.getErpmItemMaster().getErpmimId());
                    return "SUCCESS1";
                } else {

                    listStockRecSerialNo = generateSerialNoFromESR(getItemId(), eid.getIsdId());
                    listIssueSerialDetail = generateSerialNoFromEISD(eid.getIsdId(), getItemId());
                    setIssueDetailId(eid.getIsdId());
                    setIssQnty_val(eid.getIsdIssuedQuantity());
                    setIndent_Title(eid.getErpmIssueMaster().getErpmIndentMaster().getIndtTitle());
//                    setItemId(eid.getErpmItemMaster().getErpmimId());
                    return "SUCCESS2";
                }

            } else {
                //prepare item type
                erpmIcmList1 = erpmIcmDao.findByErpmicmItemLevel(i);

                //Prepare Item Category List
                erpmIcmList2 = erpmIcmDao.findByErpmicmItemLevel(j);

                //Prepare Item Sub Category List
                erpmIcmList3 = erpmIcmDao.findByErpmicmItemLevel(k);

                //prepare item types
                itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

                //issue detail list when indent is present
                issueDetList = eidDao.findByEimIdfromView(eid.getErpmIssueMaster().getIsmId());
//                setIssueMasterId(eid.getErpmIssueMaster().getIsmId());
//                setIndent_Title(eid.getErpmIssueMaster().getErpmIndentMaster().getIndtTitle());
                setBTNDSBL("false");
                setVAL1("true");

                return SUCCESS;
            }

        } catch (Exception e) {
            message = "Exception in EditIssueDetails method -> IssueDetailsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //edit the issue detail page without indent
    public String EditIssueDetailsWithoutIndent() throws Exception {
        try {

            eid = eidDao.findByeidId(getEIDID());
            eitm = itemDao.findByItemId(eid.getErpmItemMaster().getErpmimId());
            setItemId(eitm.getErpmimId());

            if (eitm.getErpmimSerialNoApplicable() == 'N') {
                setIndent_Title("");
                setIssue_No(Issue_No);
                setIssue_Date(Issue_Date);
                //create object for item master

                setIssQnty_val(eid.getIsdIssuedQuantity());
                setItemId(eid.getErpmItemMaster().getErpmimId());
                VAL1 = "false";

                //prepare item type
                erpmIcmList1 = erpmIcmDao.findByErpmicmItemLevel(i);

                //Prepare Item Category List
                erpmIcmList2 = erpmIcmDao.findByErpmicmItemLevel(j);

                //Prepare Item Sub Category List
                erpmIcmList3 = erpmIcmDao.findByErpmicmItemLevel(k);

                //prepare item types
                itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

                ErpmIssueDetail eid2 = eidDao.findByeidId(getEIDID());

                //issue detail list when indent is null
                issueDetList1 = eidDao.findByEimId(eid2.getErpmIssueMaster().getIsmId());
                setIssueMasterId(eid2.getErpmIssueMaster().getIsmId());
                setEid(eid2);
                message = "Edit Issue Detail Page ";
                return SUCCESS;
            } else {
                //item list is created for current institution
                itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

                if (eitm.getErpmimIssuePolicy().equals("1")) {

                    listIssueSerialDetail = generateSerialNoFromEISD(eid.getIsdId(), getItemId());
                    setIssueMasterId(eid.getErpmIssueMaster().getIsmId());
                    setVAL1("true");
                    itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                    setIssQnty_val(eid.getIsdIssuedQuantity());
                    setIndent_Title("");
                     setIssueDetailId(eid.getIsdId());
                    message = "Items selected using FIFO";
                    return "SUCCESS1";
                } else if (eitm.getErpmimIssuePolicy().equals("2")) {

                    listIssueSerialDetail = generateSerialNoFromEISD(eid.getIsdId(), getItemId());
                    setIndent_Title("");
                    setVAL1("true");
                    itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
                    setIssQnty_val(eid.getIsdIssuedQuantity());
                    setIssueMasterId(eid.getErpmIssueMaster().getIsmId());
                     setIssueDetailId(eid.getIsdId());
                    message = "Items Selected Using LIFO ";

                    return "SUCCESS1";
                } else {


                    listStockRecSerialNo = generateSerialNoFromESR(getItemId(), eid.getIsdId());
                    listIssueSerialDetail = generateSerialNoFromEISD(eid.getIsdId(), getItemId());
                    setIndent_Title("");
                    setIssueDetailId(eid.getIsdId());
                    setIssueMasterId(eid.getErpmIssueMaster().getIsmId());
                    setIssQnty_val(eid.getIsdIssuedQuantity());
                    message = "Issue items with users choice";

                    return "SUCCESS2";
                }
            }

        } catch (Exception e) {
            message = "Exception in EditIssueDetails method -> IssueDetailsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

    }

    //for deleting the issue items
    public String Delete() throws Exception {
        try {
            //Retrieve the record to be deleted
            eim = eimDAO.findByeimId(getEIMID());
            eimDAO.delete(eim);

            //Retrieve issue items list
            eimList = eimDAO.findByInstitutionId(Short.valueOf(getSession().getAttribute("imId").toString()));
            message = "Item Record deleted successfully.";

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> IssueItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //for deleting the issue serial no
    public String DeleteSerialNo() throws Exception {
        try {
            //Retrieve the record to be deleted
            eisd = eisdDao.findbyissid(getEISDID());

            //check if item is already received if not allow delete else dont allow
            if (eisd.isIssdReceived() == false) {
                eisdDao.delete(eisd);
                ErpmIssueDetail eid0 = eidDao.findByisdId(eisd.getErpmIssueDetail().getIsdId());
                eid0.setIsdIssuedQuantity(eid0.getIsdIssuedQuantity().subtract(BigDecimal.ONE));
                eidDao.update(eid0);
                setIssQnty_val(eid0.getIsdIssuedQuantity());
            } else {
                message = "Item is already received, Deletion is not allowed";
            }
            itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));
            listIssueSerialDetail = generateSerialNoFromEISD(eisd.getErpmIssueDetail().getIsdId(), eisd.getErpmIssueDetail().getErpmIssueMaster().getIsmId());
            setVAL1("true");
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> IssueItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String Clear() throws Exception {
        try {

            eim = null;
            populate_issueItems();

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Clear method -> IssueItemsAction" + e.getMessage();
            return ERROR;
        }
    }

//this method is for populating issue detils jsp page
    @SkipValidation
    public void populate_issueDetails() {

        //prepare item type
        erpmIcmList1 = erpmIcmDao.findByErpmicmItemLevel(i);

        //Prepare Item Category List
        erpmIcmList2 = erpmIcmDao.findByErpmicmItemLevel(j);

        //Prepare Item Sub Category List
        erpmIcmList3 = erpmIcmDao.findByErpmicmItemLevel(k);

        //prepare item types
        itemList = itemDao.findByImId(Short.valueOf(getSession().getAttribute("imId").toString()));

    }

    @Override
    public void validate() {
        try {


            /* validating the "issue items from" fields*/
            if (eim.getInstitutionmasterByIsmFromInstituteId().getImId() == null) {
                addFieldError("eim.institutionmasterByIsmFromInstituteId.imId", "Please select institution from the list");
            }
            if (eim.getSubinstitutionmasterByIsmFromSubinstituteId().getSimId() == null) {
                addFieldError("eim.subinstitutionmasterByIsmFromSubinstituteId.simId", "Please select Subinstitution from the list");
            }

            if (eim.getDepartmentmasterByIsmFromDepartmentId().getDmId() == null) {
                addFieldError("eim.departmentmasterByIsmFromDepartmentId.dmId", "Please select Department from the list");
            }

            if (eim.getIsmIssueNo().isEmpty()) {
                addFieldError("eim.ismIssueNo", "Plesse enter Issue No");
            }

            if (eim.getEmployeemasterByIsmFromEmployeeId().getEmpId() == null) {
                addFieldError("eim.employeemasterByIsmFromEmployeeId.empId", "Please select Issuing Authority name");
            }
//            if (eim.getIsmIssueDate() == null) {
//                addFieldError("eim.ismIssueDate", "Please select Issue Date");
//            }

            /* validating the "issue items To" fields */
            if (eim.getInstitutionmasterByIsmToInstituteId().getImId() == null) {
                addFieldError("eim.institutionmasterByIsmToInstituteId.imId", "Please select institution from the list");
            }
            if (eim.getSubinstitutionmasterByIsmToSubinstituteId().getSimId() == null) {
                addFieldError("eim.subinstitutionmasterByIsmToSubinstituteId.simId", "Please select Subinstitution from the list");
            }

            if (eim.getDepartmentmasterByIsmToDepartmentId().getDmId() == null) {
                addFieldError("eim.departmentmasterByIsmToDepartmentId.dmId", "Please select Department from the list");
            }

            DateUtilities dt = new DateUtilities();
            if (eim.getIsmIssueType() == 'R') {

                if (dt.isValidDate(getReturnDueDate()) == false) {
                    addFieldError("returnDueDate", "Please give valid Return Due Date [dd-mm-yyyy]");
                }

            }

            if (getIssueDate().length() != 0) {
                if (dt.isValidDate(getIssueDate()) == false) {
                    addFieldError("issueDate", "Please give valid Issue Date [dd-mm-yyyy]");
                }
            }

//            if(getReturnDueDate().length() != 0){
//                if (dt.isValidDate(getReturnDueDate()) == false)
//                   addFieldError("returnDueDate", "Please give valid Issue Date [dd-mm-yyyy]");
//            }

            //populate the issue items page
            populate_issueItems();
            message = "Please fill the empty fields";


            if (viid.getIndentApprovedQuantity() < viid.getIsdIssuedQuantity().intValue()) {
                addFieldError("eid.isdIssuedQuantity", "issue quantity should be less  or equal to  approved quantity ");
            }


            if (eid.getErpmItemMaster().getErpmimId() == null) {
                addFieldError("eid.erpmItemMaster.erpmimId", "Please select Item Name");
            }

            //populate the issue items page
            populate_issueItems();

            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));

        } catch (NullPointerException npe) {
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
            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=ShowGfr.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            whereCondition = "gfr_program_mapping.`GPM_Program_ID` = 26";

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
    public String IssueReport() throws Exception {
        HashMap hm = new HashMap();

        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator = pathSeparator + pathSeparator;
         String repPath = "pico" + pathSeparator + "Inventory"  + pathSeparator + "Reports" + pathSeparator +  "Issue_Report.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);


        String whereCondition = "";

        try {
            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=Issue_Report.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();


            whereCondition = "erpm_issue_master.`ISM_Issue_No`="+eim.getIsmIssueNo();

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
}
