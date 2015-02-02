/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 *
 * @arpan prasad maurya
 */
package Inventory;

//import javax.servlet.http.HttpServletResponse;
//import org.apache.struts2.ServletActionContext;
//import net.sf.jasperreports.engine.*;
import java.io.*;
import java.math.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Employeemaster;
import pojo.hibernate.EmployeemasterDAO;
import pojo.hibernate.ErpmIssueDetail;
import pojo.hibernate.ErpmIssueDetailDAO;
import pojo.hibernate.ErpmIssueMaster;
import pojo.hibernate.ErpmIssueMasterDAO;
import pojo.hibernate.ErpmIssueReturnDetail;
import pojo.hibernate.ErpmIssueReturnDetailDAO;
import pojo.hibernate.ErpmIssueReturnMaster;
import pojo.hibernate.ErpmIssueReturnMasterDAO;
import pojo.hibernate.ErpmIssueSerialDetail;
import pojo.hibernate.ErpmIssueSerialDetailDAO;
import pojo.hibernate.ErpmItemMaster;
import pojo.hibernate.ErpmItemMasterDAO;
import pojo.hibernate.ErpmStockReceived;
import pojo.hibernate.ErpmStockReceivedDAO;
import pojo.hibernate.Erpmusers;
import pojo.hibernate.GfrProgramMappingDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import pojo.hibernate.ViewIssueSerialDetail;
import pojo.hibernate.ViewIssueSerialDetailDAO;
import utils.DateUtilities;
import utils.DevelopmentSupport;

//import java.util.Locale;
//import java.util.ResourceBundle;
//import com.opensymphony.xwork2.ActionContext;

public class ReturnIssuedItemsAction extends DevelopmentSupport {

    private ErpmIssueMaster eim;
    private ErpmIssueReturnDetail erpmird;
    private ErpmStockReceived erpmStockReceived;
    private ErpmIssueDetail eid;
    private String message;
    private static String VariableUsedtoCheckExecutionOfShowDetailmessage = "yes";
    private String messageInItemSerialNoPage;
    private Short DefaultInsitute;
    private ErpmIssueReturnMaster erpmirm;
    Erpmusers ermu;
    Institutionmaster im;
    private String username;
    private Short DefaultInsitute1;
    private Integer DefaultSubInsitute;
    private Integer DefaultDepartment;
    private ErpmItemMaster erpmim;
    private Integer ErpmimId;
    private ErpmIssueSerialDetail erpmIssueSerialDetail;
    private ErpmIssueMasterDAO eimDAO = new ErpmIssueMasterDAO();
    private ErpmIssueReturnMasterDAO erpmirmDAO = new ErpmIssueReturnMasterDAO();
    private ErpmIssueReturnDetailDAO erpmirdDAO = new ErpmIssueReturnDetailDAO();
    private ErpmItemMasterDAO erpmimDAO = new ErpmItemMasterDAO();
    private ViewIssueSerialDetailDAO viewIsdDAO = new ViewIssueSerialDetailDAO();
    private ErpmIssueDetailDAO eidDAO = new ErpmIssueDetailDAO();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private List<ErpmIssueDetail> eidList = new ArrayList<ErpmIssueDetail>();
    private List<Employeemaster> empList = new ArrayList<Employeemaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private EmployeemasterDAO empDao = new EmployeemasterDAO();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    static private List<ErpmIssueReturnMaster> erpmirmList = new ArrayList<ErpmIssueReturnMaster>();
    private List<ErpmIssueMaster> issuemasterList = new ArrayList<ErpmIssueMaster>();
    private static List<ErpmIssueSerialDetail> ErpmIssueSerialDetailList = new ArrayList<ErpmIssueSerialDetail>();
    private static List<ErpmIssueSerialDetail> ErpmIssueSerialDetailListforitemSerialNo = new ArrayList<ErpmIssueSerialDetail>();
    private ErpmIssueSerialDetailDAO erpmIssueSerialDetailDAO = new ErpmIssueSerialDetailDAO();
    private ErpmStockReceivedDAO erpmStockReceivedDAO = new ErpmStockReceivedDAO();
//    private List<ErpmStockReceived> erpmStockReceivedList = new ArrayList<ErpmStockReceived>();
    private static List<ViewIssueSerialDetail> ViewIssueSerialDetailList = new ArrayList<ViewIssueSerialDetail>();
    private ErpmIssueReturnDetailDAO erpmIssueReturnDetailDAO = new ErpmIssueReturnDetailDAO();
    private static List<ErpmIssueReturnDetail> erpmIssueReturnDetailList = new ArrayList<ErpmIssueReturnDetail>();
    private GfrProgramMappingDAO GfrProgramMappingDao = new GfrProgramMappingDAO();
    private static char LocalVariableusedinPlaceOfreturntype;
    private static int LocalVariableusedinPlaceOfdmId;
    private static String returntype;
    private String DefoultissuenoList = "";
    private static String IssueNo;
//    private static Date ReturnDate;
    private static Integer ReturnNo;
    private static String VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = null;
    private static String Variable = null;
    private static String IssueSerialNo;
    private String defaultdisablevar;
    private static String returnQuantityWhenItemSerialNoZero;//this used when we go by selecting issue no and issue serial no of selcted quntity will be zero
    private String radSelectvalue;
    private static Integer irmId;
    private static Integer tempirmId;
    private static Integer ismId;
    private static Integer stId;
    private static Integer tempIssdId; //this is use inplace of  getissid
    private static Integer StackSerialNo; //this is use inplace of  getissid
    private static int erpmimId;
    private InputStream inputStream;
    private Integer issdId;
    private static Integer LocalVariableUsedInPlaceofIsmId;
    private Boolean bool;
    private static String condvar = null;
    private static String condvarforItemSerialNoPage = null;
    private static String itemName;
    private static BigDecimal availableQuantity;//this var is used for available quatity whhen we are going by selcting issue no
    private static String VarusedforIssuedQuantity;//this var is used for IssuedQuantity whhen we are going by selcting issue no
    private static String VarusedforReceivedQuantity;//this var is used for ReceivedQuantity whhen we are going by selcting issue no
    private static String VarusedforAllreadyReturnedQuantity;//this var is used for AllreadyReturned Quantity  whhen we are going by selcting issue no
    private static int isdId;
    private static String varToShowSelectedIssueNo = "Please Select";
    private static String ItemSerialNo;
    private String returnDate;
    private static Boolean varShowGFR;

    static String dataSourceURL=null;

    public Boolean getVarShowGFR() {
        return varShowGFR;
    }

    public void setVarShowGFR(Boolean varShowGFR) {
        this.varShowGFR = varShowGFR;
    }
  
    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getIrmId() {
        return irmId;
    }

    public void setIrmId(Integer irmId) {
        this.irmId = irmId;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ErpmIssueReturnMaster getErpmirm() {
        return erpmirm;
    }

    public void setErpmirm(ErpmIssueReturnMaster erpmirm) {
        this.erpmirm = erpmirm;
    }

    public ErpmIssueReturnDetail getErpmird() {
        return erpmird;
    }

    public void setErpmird(ErpmIssueReturnDetail erpmird) {
        this.erpmird = erpmird;
    }

    public Integer getErpmimId() {
        return this.ErpmimId;
    }

    public void setErpmimId(Integer ErpmimId) {
        this.ErpmimId = ErpmimId;
    }

    public void seterpmim(ErpmItemMaster erpmim) {
        this.erpmim = erpmim;
    }

    public ErpmItemMaster geterpmim() {
        return this.erpmim;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getusername() {
        return username;
    }

    public void setDefaultInsitute(short DefaultInsitute) {
        this.DefaultInsitute = DefaultInsitute;
    }

    public short getDefaultInsitute() {
        return this.DefaultInsitute;
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

    public void setsimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public List<Subinstitutionmaster> getsimList() {
        return this.simList;
    }

    public void seterpmirmList(List<ErpmIssueReturnMaster> erpmirmList) {
        this.erpmirmList = erpmirmList;
    }

    public List<ErpmIssueReturnMaster> geterpmirmList() {
        return this.erpmirmList;
    }

    public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public List<Institutionmaster> getimList() {
        return this.imList;
    }

    public void setdmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<Departmentmaster> getdmList() {
        return this.dmList;
    }

    public void seteidList(List<ErpmIssueDetail> eidList) {
        this.eidList = eidList;
    }

    public List<ErpmIssueDetail> geteidList() {
        return this.eidList;
    }

    public List<Employeemaster> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Employeemaster> empList) {
        this.empList = empList;
    }

    public List<ErpmIssueMaster> getIssuemasterList() {
        return issuemasterList;
    }

    public void setIssuemasterList(List<ErpmIssueMaster> issuemasterList) {
        this.issuemasterList = issuemasterList;
    }

    public String getDefoultIssueNoList() {
        return DefoultissuenoList;
    }

    public void setDefoultIssueNoList(String DefoultissuenoList) {
        this.DefoultissuenoList = DefoultissuenoList;

    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setVariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod(String VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod) {
        this.VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod;
    }

    public String getVariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod() {
        return this.VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod;
    }

    public void semessageInItemSerialNoPage(String messageInItemSerialNoPage) {
        this.messageInItemSerialNoPage = messageInItemSerialNoPage;
    }

    public String getmessageInItemSerialNoPage() {
        return this.messageInItemSerialNoPage;
    }

    public void setDefaultdisablevar(String defaultdisablevar) {
        this.defaultdisablevar = defaultdisablevar;
    }

    public String getDefaultdisablevar() {
        return this.defaultdisablevar;
    }

    public void setIssueNo(String IssueNo) {
        this.IssueNo = IssueNo;
    }

    public String getIssueNo() {
        return this.IssueNo;
    }

    public void setIssueSerialNo(String IssueSerialNo) {
        this.IssueSerialNo = IssueSerialNo;
    }

    public String getIssueSerialNo() {
        return this.IssueSerialNo;
    }

    public Integer getReturnNo() {
        return this.ReturnNo;
    }

    public void setReturnNo(Integer ReturnNo) {
        this.ReturnNo = ReturnNo;
    }

//    public void setReturnDate(Date ReturnDate) {
//        this.ReturnDate = ReturnDate;
//    }
//
//    public Date getReturnDate() {
//        return this.ReturnDate;
//    }

    public void setEim(ErpmIssueMaster eim) {
        this.eim = eim;
    }

    public ErpmIssueMaster getEim() {
        return this.eim;
    }

    public void setEid(ErpmIssueDetail eid) {
        this.eid = eid;
    }

    public ErpmIssueDetail getEid() {
        return this.eid;
    }

    public void seterpmIssueSerialDetail(ErpmIssueSerialDetail erpmIssueSerialDetail) {
        this.erpmIssueSerialDetail = erpmIssueSerialDetail;
    }

    public ErpmIssueSerialDetail geterpmIssueSerialDetail() {
        return this.erpmIssueSerialDetail;
    }

//    public void setErpmStockReceivedList(List<ErpmStockReceived> erpmStockReceivedList) {
//        this.erpmStockReceivedList = erpmStockReceivedList;
//    }
//
//    public List<ErpmStockReceived> getErpmStockReceivedList() {
//        return erpmStockReceivedList;
//    }
    public void setErpmStockReceived(ErpmStockReceived erpmStockReceived) {
        this.erpmStockReceived = erpmStockReceived;
    }

    public ErpmStockReceived getErpmStockReceived() {
        return erpmStockReceived;
    }

    public List<ErpmIssueSerialDetail> getErpmIssueSerialDetailList() {
        return ErpmIssueSerialDetailList;
    }

    public void setErpmIssueSerialDetailList(List<ErpmIssueSerialDetail> ErpmIssueSerialDetailList) {
        this.ErpmIssueSerialDetailList = ErpmIssueSerialDetailList;
    }

    public List<ErpmIssueSerialDetail> getErpmIssueSerialDetailListforitemSerialNo() {
        return ErpmIssueSerialDetailListforitemSerialNo;
    }

    public void setErpmIssueSerialDetailListforitemSerialNo(List<ErpmIssueSerialDetail> ErpmIssueSerialDetailListforitemSerialNo) {
        this.ErpmIssueSerialDetailListforitemSerialNo = ErpmIssueSerialDetailListforitemSerialNo;
    }

    public List<ViewIssueSerialDetail> getViewIssueSerialDetailList() {
        return ViewIssueSerialDetailList;
    }

    public void setViewIssueSerialDetailList(List<ViewIssueSerialDetail> ViewIssueSerialDetailList) {
        this.ViewIssueSerialDetailList = ViewIssueSerialDetailList;
    }

    public Integer getIssdId() {
        return issdId;
    }

    public void setIssdId(Integer issdId) {
        this.issdId = issdId;
    }

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public Integer getIsmId() {
        return this.ismId;
    }

    public void setIsmId(Integer ismId) {
        this.ismId = ismId;
    }

    public Integer getStId() {
        return this.stId;
    }

    public void setStId(Integer stId) {
        this.stId = stId;
    }

    public void setReturntype(String returntype) {
        this.returntype = returntype;
    }

    public String getReturntype() {
        return this.returntype;
    }

    public void setradSelectvalue(String radSelectvalue) {
        this.radSelectvalue = radSelectvalue;
    }

    public String getradSelectvalue() {
        return this.radSelectvalue;
    }

    public void setcondvar(String condvar) {
        this.condvar = condvar;
    }

    public String getcondvar() {
        return this.condvar;
    }

    public void setcondvarforItemSerialNoPage(String condvarforItemSerialNoPage) {
        this.condvarforItemSerialNoPage = condvarforItemSerialNoPage;
    }

    public String getcondvarforItemSerialNoPage() {
        return this.condvarforItemSerialNoPage;
    }

    public void setitemName(String itemName) {
        this.itemName = itemName;
    }

    public String getitemName() {
        return this.itemName;
    }

    public void setItemSerialNo(String ItemSerialNo) {
        this.ItemSerialNo = ItemSerialNo;
    }

    public String getItemSerialNo() {
        return this.ItemSerialNo;
    }

    public void settempIssdId(int tempIssdId) {
        this.tempIssdId = tempIssdId;
    }

    public int gettempIssdId() {
        return this.tempIssdId;
    }

    public void setvarToShowSelectedIssueNo(String varToShowSelectedIssueNo) {
        this.varToShowSelectedIssueNo = varToShowSelectedIssueNo;
    }

    public String getvarToShowSelectedIssueNo() {
        return this.varToShowSelectedIssueNo;
    }

    public void setreturnQuantityWhenItemSerialNoZero(String returnQuantityWhenItemSerialNoZero) {
        this.returnQuantityWhenItemSerialNoZero = returnQuantityWhenItemSerialNoZero;
    }

    public String getreturnQuantityWhenItemSerialNoZero() {
        return this.returnQuantityWhenItemSerialNoZero;
    }

    public List<ErpmIssueReturnDetail> getErpmIssueReturnDetailList() {
        return erpmIssueReturnDetailList;
    }

    public void setErpmIssueReturnDetailList(List<ErpmIssueReturnDetail> erpmIssueReturnDetailList) {
        this.erpmIssueReturnDetailList = erpmIssueReturnDetailList;
    }

    @SkipValidation
    @Override
    public String execute() throws Exception {
        VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = null;
        try {


            InitializeLOVs();

            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            setReturnDate(d1.convertDateToString(d,"dd-MM-yyyy"));
            erpmirm = null;

            Short i=31;
            Integer count = GfrProgramMappingDao.findCountByProgramId(i);


            if(count > 0){
             setVarShowGFR(false);
            }
            else{
             setVarShowGFR(true);
            }

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ReturnIssuedMasterxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //This method is for Help
    public String Help() {
	return SUCCESS;
    }

    public String Save() throws Exception {
        Variable = "";
        condvar = null;
        itemName = null;
        messageInItemSerialNoPage = null;

        ViewIssueSerialDetailList = null;
        ErpmIssueSerialDetailList = null;
        condvarforItemSerialNoPage = null;
        try {
            username = getSession().getAttribute("userfullname").toString();

            DateUtilities dt = new DateUtilities();
            if (erpmirm.getIrmId() == null) {

                erpmirm.setIrmReturnDate(dt.convertStringToDate(getReturnDate()));
                erpmirmDAO.save(erpmirm);

            } else {

                erpmirm.setIrmReturnDate(dt.convertStringToDate(getReturnDate()));
                ErpmIssueReturnMaster erpmirm1 = erpmirmDAO.findByErpmirmId(erpmirm.getIrmId());

                irmId = erpmirm.getIrmId();
                tempirmId = irmId;
                erpmirm1 = erpmirm;
                erpmirmDAO.update(erpmirm1);


            }
            ismId = null;
            stId = null;
            //message ="gf"+getradSelectvalue();

            try {
                if (getradSelectvalue().equals("IssueNo")) {

                    VariableUsedtoCheckExecutionOfShowDetailmessage = "IssueNo";
                    irmId = erpmirm.getIrmId();
                    erpmirm = erpmirmDAO.findByErpmirmId(erpmirm.getIrmId());
                    setReturnDate(dt.convertDateToString(erpmirm.getIrmReturnDate(), "dd-MM-yyyy"));
                    ReturnNo = Integer.parseInt(erpmirm.getIrmReturnNo());

                    if (erpmirm.getIrmReturnType() == 'U') {
                        returntype = "Returned After USE";

                    }
                    if (erpmirm.getIrmReturnType() == 'R') {
                        returntype = "Returned After REPAIR";

                    }//issuemasterlist containing issue numner
                    issuemasterList = eimDAO.findIssueMasterListBydmIdandReturnType(erpmirm.getIrmReturnType(), erpmirm.getDepartmentmaster().getDmId());
                    LocalVariableusedinPlaceOfreturntype = erpmirm.getIrmReturnType();
                    LocalVariableusedinPlaceOfdmId = erpmirm.getDepartmentmaster().getDmId();
                    erpmirmList = erpmirmDAO.findReturnIssuedItemsForUserInstitutes(Integer.valueOf(getSession().getAttribute("userid").toString()));
//                    erpmIssueReturnDetailList = erpmIssueReturnDetailDAO.findListByirmId(irmId);
                    erpmIssueReturnDetailList = erpmIssueReturnDetailDAO.findListByirmIdwith_editedserialno(irmId);
                      //call for erpmIssueReturnDetailList
                     // generateSerialNoFromirmId(tempirmId);                    
                    tempirmId = irmId;


                    // message="varToShowSelectedIssueNo"+irmId+"erpmIssueReturnDetailList"+erpmIssueReturnDetailList;
                    return "SUCCESS1";

                }
            } catch (NullPointerException e5) {//do not remove // sign inside catch block
                message = "Exception in ReturnIssuedItemsAction save method-> " + e5.getMessage() + " Reported Cause is: " + e5.getCause();
                return ERROR;
            }
     //       try {
                if (getradSelectvalue().equals("ItemSerialNo")) {
                    VariableUsedtoCheckExecutionOfShowDetailmessage = "ItemSerialNo";

                    irmId = erpmirm.getIrmId();
                    tempirmId = irmId;
                    erpmirm = erpmirmDAO.findByErpmirmId(erpmirm.getIrmId());
                    setReturnDate(dt.convertDateToString(erpmirm.getIrmReturnDate(), "dd-MM-yyyy"));
//                    ReturnDate = erpmirm.getIrmReturnDate();
                    ReturnNo = Integer.parseInt(erpmirm.getIrmReturnNo());

                    // IssueSerialNo = "" + erpmStockReceived1.getStStockSerialNo();
                    if (erpmirm.getIrmReturnType() == 'U') {
                        returntype = "Returned After USE";

                    }
                    if (erpmirm.getIrmReturnType() == 'R') {
                        returntype = "Returned After REPAIR";

                    }
                    ReturnNo = Integer.parseInt(erpmirm.getIrmReturnNo());

                    //erpmStockReceivedList = erpmStockReceivedDAO.findItemSerialNoList(erpmirm.getIrmReturnType(), erpmirm.getDepartmentmaster().getDmId());
       //             ErpmIssueSerialDetailListforitemSerialNo = erpmIssueSerialDetailDAO.findListBydmIdReturnTypeissdReturn(erpmirm.getIrmReturnType(), erpmirm.getDepartmentmaster().getDmId(), false);
                    ErpmIssueSerialDetailListforitemSerialNo = erpmIssueSerialDetailDAO.findListBydmIdReturnTypeissdReturnwit_editedserialno(erpmirm.getIrmReturnType(), erpmirm.getDepartmentmaster().getDmId(), false);            
                    LocalVariableusedinPlaceOfdmId = erpmirm.getDepartmentmaster().getDmId();
                    LocalVariableusedinPlaceOfreturntype = erpmirm.getIrmReturnType();
                    erpmirmList = erpmirmDAO.findReturnIssuedItemsForUserInstitutes(Integer.valueOf(getSession().getAttribute("userid").toString()));
                  //  erpmIssueReturnDetailList = erpmIssueReturnDetailDAO.findListByirmId(tempirmId);
                    //message=""+erpmIssueReturnDetailList+""+tempirmId;
                    //call for erpmIssueReturnDetailList
                    erpmIssueReturnDetailList = erpmIssueReturnDetailDAO.findListByirmIdwith_editedserialno(tempirmId);
      
                    // generateSerialNoFromirmId(tempirmId);
                    
                    
                    
                    return "SUCCESS2";

                }
       /*     } catch (NullPointerException e2) {
                InitializeLOVs();
                message = "Exception in ReturnIssuedItemsAction save method-> " + e2.getMessage() + " Reported Cause is:getStId " + e2.getCause();
                return ERROR;
            }*/

            return "SUCCESS1";



        } catch (Exception e) {
            // InitializeLOVs();
            message = "Exception in ReturnIssuedItemsAction save method-> " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

    }
    //this will be execute when we select item serial no or item issue number then click show detail

    public String ShowDetail() throws Exception {
        VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = null;
        ;
        Variable = "";
        defaultdisablevar = "true";        //for disabling in show detail page
        condvar = null;
        ViewIssueSerialDetail visdobject2;


        try {


            if (eim.getIsmId() != null) {
                VariableUsedtoCheckExecutionOfShowDetailmessage = null;
                // eid = eidDAO.findByEimId(ismId);
//message = ""+ eim.getIsmId();
                ismId = eim.getIsmId();
                ViewIssueSerialDetailList = viewIsdDAO.findByEimIdfromView(eim.getIsmId());//this list containing row of viewissue serila detailto show in page when we click on show detail by selecting issue numberIN DROP DOWN
                Iterator itr2 = ViewIssueSerialDetailList.iterator();
                if (itr2.hasNext()) {
                    visdobject2 = (ViewIssueSerialDetail) itr2.next();
//                                                     message=""+visdobject2.getIsmIssueNo()+visdobject2.getErpmimItemBriefDesc()+visdobject2.getErpmimSerialNoApplicable();

                    varToShowSelectedIssueNo = visdobject2.getIsmIssueNo();
                }
                // message="varToShowSelectedIssueNo"+varToShowSelectedIssueNo+""+eim.getIsmId();
                issuemasterList = eimDAO.findIssueMasterListBydmIdandReturnType(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId);


                return "SUCCESS1";
            }
        } catch (NullPointerException e2) {
            InitializeLOVs();
            //message = "Exception in ReturnIssuedItemsAction ShowDetail method-> " + e2.getMessage() + " Reported Cause is:getIsmId " + e2.getCause();
            //return ERROR;
        }
        try {
            //message="erpmIssueSerialDetail.getErpmStockReceived().getStId()t"+erpmIssueSerialDetail.getErpmStockReceived().getStId();
            // message=""+LocalVariableusedinPlaceOfreturntype+"1"+LocalVariableusedinPlaceOfdmId;

            if (erpmIssueSerialDetail.getErpmStockReceived().getStId() != null) {
                message = "" + LocalVariableusedinPlaceOfreturntype + "" + LocalVariableusedinPlaceOfdmId;
                //ErpmIssueSerialDetailListforitemSerialNo = erpmIssueSerialDetailDAO.findListBydmIdReturnTypeissdReturn(LocalVariableusedinPlaceOfreturntype,LocalVariableusedinPlaceOfdmId,false);

                VariableUsedtoCheckExecutionOfShowDetailmessage = null;
                stId = erpmIssueSerialDetail.getErpmStockReceived().getStId();

                ErpmIssueSerialDetailList = erpmIssueSerialDetailDAO.findListByerpmStockReceivedId(erpmIssueSerialDetail.getErpmStockReceived().getStId());
                //message="eismId"+ismId+"stId"+erpmIssueSerialDetail.getErpmStockReceived().getStId();
                erpmIssueReturnDetailList = erpmIssueReturnDetailDAO.findListByirmId(tempirmId);

                message = null;

                return "SUCCESS2";

            }

        } catch (NullPointerException e2) {
            InitializeLOVs();
            //return ERROR;

        }//this check used when user does not select drop down list of issueno or issue serial no and  directaly click on show detaiil button

        try {
            if (VariableUsedtoCheckExecutionOfShowDetailmessage.equals("IssueNo")) {

                VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = "Please select an Issue No. to proceed";
                //  message=""+VariableUsedtoCheckExecutionOfShowDetailmessage.equals("IssueNo")+VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod;

                issuemasterList = eimDAO.findIssueMasterListBydmIdandReturnType(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId);
                defaultdisablevar = "false";
                return "SUCCESS1";
            }
        } catch (Exception e) {
            VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = "Please select an Issue No. to proceed";
            issuemasterList = eimDAO.findIssueMasterListBydmIdandReturnType(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId);
            defaultdisablevar = "false";
            return "SUCCESS1";
        }
        try {
            if (VariableUsedtoCheckExecutionOfShowDetailmessage.equals("ItemSerialNo")) {
                ErpmIssueSerialDetailListforitemSerialNo = erpmIssueSerialDetailDAO.findListBydmIdReturnTypeissdReturn(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId, false);
                defaultdisablevar = "false";
                VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = "Please select an Item Serial No. to proceed";
                return "SUCCESS2";
            }
        } catch (Exception e) {
            ErpmIssueSerialDetailListforitemSerialNo = erpmIssueSerialDetailDAO.findListBydmIdReturnTypeissdReturn(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId, false);
            defaultdisablevar = "false";
            VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = "Please select an Item Serial No. to proceed";
            return "SUCCESS2";
        }

        return "SUCCESS2";
    }

    public String ReceivedBackOK() throws Exception {
        //VariableUsedtoCheckExecutionOfShowDetailmessage="IssueNo";
        Variable = "yes";
        VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = null;

        //use only in this method

        ViewIssueSerialDetail visdobject;
        try {
            if (ismId != null) {
                //message=""+tempIssdId+ViewIssueSerialDetailList.size();
                VariableUsedtoCheckExecutionOfShowDetailmessage = "IssueNo";
                try {
                    issuemasterList = eimDAO.findIssueMasterListBydmIdandReturnType(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId);

                    tempIssdId = (int) getIssdId();
                    //erpmIssueSerialDetail=null;
                    if (ViewIssueSerialDetailList.size() != 0) {
                        Iterator itr = ViewIssueSerialDetailList.iterator();
                        while (itr.hasNext()) {

                            visdobject = (ViewIssueSerialDetail) itr.next();

                            condvar = "" + visdobject.getIssdId();
                            StackSerialNo = visdobject.getIssdStockSerialId().intValue();
                            //message=message+"StackSerialNo"+StackSerialNo+"issid"+tempIssdId;
                            VarusedforIssuedQuantity = "" + visdobject.getIsdIssuedQuantity();
                            VarusedforReceivedQuantity = "" + visdobject.getIsdReceivedQuantity();
                            VarusedforAllreadyReturnedQuantity = "" + visdobject.getIsdReturnedQuantity();
                            returnQuantityWhenItemSerialNoZero = "" + visdobject.getIsdReturnedQuantity();
                            try {
                                ItemSerialNo = visdobject.getStStockSerialNo();
                            } catch (Exception e10) {
                            }

                            if (visdobject.getIssdId() == Long.parseLong("" + tempIssdId)) {
                                //message="visdobject.getIssdId()"+visdobject.getIssdId()+message;
                                condvar = "" + visdobject.getIssdId();
                                erpmimId = visdobject.getIsdItemId();
                                itemName = visdobject.getErpmimItemBriefDesc();
                                //availableQuantity=""+visdobject.getIsdReturnedQuantity();
                                isdId = visdobject.getIsdId();
                                if (visdobject.getStStockSerialNo().equals("0")) {
                                    //message=visdobject.getErpmimItemBriefDesc()+message;

                                    return "SUCCESS1";
                                } else {
                                    condvar = "whenItemSerialNonotZero";
                                    erpmIssueReturnDetailList = erpmIssueReturnDetailDAO.findListByirmId(irmId);
                                    return "SUCCESS1";

                                }
                            }
                        }
                        stId = null;
                        return "SUCCESS1";
                    }
                } catch (NullPointerException e) {
                    // InitializeLOVs();
                    message = "Exception in ReturnIssuedItemsAction ReceivedBackOK1 method-> ReturnIssuedItemsAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
                    return ERROR;
                }
            }

            try {

                if (stId != null) {//this is executed when we come  by selcting issueserialno and clicking in receive back
                    condvarforItemSerialNoPage = "ismId" + ismId + "stId" + stId;
                    VariableUsedtoCheckExecutionOfShowDetailmessage = "ItemSerialNo";
                    ismId = null;
                    ErpmIssueSerialDetail erpmisd2 = new ErpmIssueSerialDetail();
                    erpmisd2 = erpmIssueSerialDetailDAO.findbystId(stId);
                    LocalVariableUsedInPlaceofIsmId = erpmisd2.getErpmIssueDetail().getErpmIssueMaster().getIsmId();
                    isdId = erpmisd2.getErpmIssueDetail().getIsdId();
                    ItemSerialNo = erpmisd2.getErpmStockReceived().getStStockSerialNo();
                    returnQuantityWhenItemSerialNoZero = erpmisd2.getErpmStockReceived().getErpmItemMaster().getErpmimDetailedDesc();
                    // VarForReturnQuantityWhenIssueSerialNoselcted=erpmisd2.getErpmIssueDetail().getIsdReturnedQuantity();
                   // erpmIssueReturnDetailList = erpmIssueReturnDetailDAO.findListByirmId(tempirmId);//this list shown data that already returned in issueserialno page
                    erpmIssueReturnDetailList = erpmIssueReturnDetailDAO.findListByirmIdwith_editedserialno(tempirmId);//this list shown data that already returned in issueserialno page
                      //call for erpmIssueReturnDetailList
                     // generateSerialNoFromirmId(tempirmId);
                    ErpmIssueSerialDetailListforitemSerialNo = erpmIssueSerialDetailDAO.findListBydmIdReturnTypeissdReturn(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId, false);

                    return "SUCCESS2";
                }
            } catch (NullPointerException e2) {
                // InitializeLOVs();
                message = "Exception in ReturnIssuedItemsAction ReceivedBackOK2 method-> pomasterAxn" + e2.getMessage() + " Reported Cause is: " + e2.getCause();
                return ERROR;
            }
            return "SUCCESS2";
        } catch (NullPointerException e) {
            message = "XXXXXXXXXXXXXX Exception in ReturnIssuedItemsAction ReceivedBackOK3 method-> ReturnIssuedItemsAction" + e.getMessage() + " Reported Cause is: " + e.getCause() + message;
            return ERROR;
        }
    }
    //this done method will excute when we click in receiveback method then get data in text box of same page the we click in done

    public String Done() throws Exception {
        try {
            if (!Variable.equals("yes")) {

                VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = "Please select an Issue / Item Serial No. to proceed or click on receive back to go to the main page";
                issuemasterList = eimDAO.findIssueMasterListBydmIdandReturnType(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId);
                return "SUCCESS1";

            }
        } catch (NullPointerException e5) {
            issuemasterList = eimDAO.findIssueMasterListBydmIdandReturnType(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId);

            VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = "Please select an Issue / Item Serial No. to proceed or click on receive back to go to the main page";
            return "SUCCESS1";
        }
        if (condvar.equals("whenItemSerialNonotZero") && (tempIssdId != 0)) {
//               
            try {

                ErpmIssueSerialDetail erpmisd1 = new ErpmIssueSerialDetail();
                erpmisd1 = erpmIssueSerialDetailDAO.findbyissid(tempIssdId);
                erpmisd1.getIssdId();
                //message=""+message+"tempid"+tempIssdId+erpmisd1.getIssdId()+""+erpmisd1.isIssdReturned();

                erpmisd1.setIssdReturned(true);
                erpmIssueSerialDetailDAO.update(erpmisd1);
                ViewIssueSerialDetailList = viewIsdDAO.findByEimIdfromView(ismId);
            } catch (NullPointerException e) {
                // InitializeLOVs();
                message = "Exception in ReturnIssuedItemsAction Done method-> toupdate 0 to 1" + e.getMessage() + " Reported Cause is: " + e.getCause() + "" + message;
                return ERROR;
            }
            try {

                ErpmIssueDetail erpmisdObject = new ErpmIssueDetail();

                erpmisdObject = eidDAO.findByisdId(isdId);

                availableQuantity = erpmisdObject.getIsdReturnedQuantity();
                //message=""+message+"availableQuantity"+availableQuantity+"isdId"+isdId;


                erpmisdObject.setIsdReturnedQuantity(new BigDecimal(1 + availableQuantity.intValue()));
                eidDAO.update(erpmisdObject);
                ViewIssueSerialDetailList = viewIsdDAO.findByEimIdfromView(ismId);

            } catch (NullPointerException e) {
                // InitializeLOVs();
                message = "Exception in ReturnIssuedItemsAction Done method-> toupdate 0 to 1" + e.getMessage() + " Reported Cause is: " + e.getCause() + "" + message;
                return ERROR;
            }

        } else {
            try {
                try {
                    // message=""+returnQuantityWhenItemSerialNoZero.equals(VarusedforAllreadyReturnedQuantity);
                    if (returnQuantityWhenItemSerialNoZero.equals(VarusedforAllreadyReturnedQuantity)) {
                        issuemasterList = eimDAO.findIssueMasterListBydmIdandReturnType(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId);

                        message = "please enter return quatity ";
                        return "SUCCESS1";
                    }
                } catch (Exception e) {
                }

                try {

                    double var1 = Double.valueOf(returnQuantityWhenItemSerialNoZero).doubleValue();
                    double var2 = Double.valueOf(VarusedforAllreadyReturnedQuantity).doubleValue();
                    double var3 = Double.valueOf(VarusedforReceivedQuantity).doubleValue();
                    var1 = var1 + var2;
                    //message=""+var1+"-"+var2+"-"+var3+(var3<=var1);
                    if (var3 < var1) {//this condition is checking issued quantinty should be less then returned+receive quantity
                        issuemasterList = eimDAO.findIssueMasterListBydmIdandReturnType(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId);

                        message = "You cannot Return more then totoal Received quantity ";
                        return "SUCCESS1";
                    }
                } catch (Exception e) {
                }
                ErpmIssueDetail erpmisdObject = new ErpmIssueDetail();

                erpmisdObject = eidDAO.findByisdId(isdId);

                availableQuantity = erpmisdObject.getIsdReturnedQuantity();
                //message="isdId"+isdId+"returnQuantityWhenItemSerialNoZero"+returnQuantityWhenItemSerialNoZero;
                // String str = availableQuantity.toString();
                int d = 0;
                try {
                    d = Integer.parseInt("" + returnQuantityWhenItemSerialNoZero);
                } catch (Exception e4) {
                }
                if (d == 0) {
                    try {
                        d = Integer.parseInt(returnQuantityWhenItemSerialNoZero);
                    } catch (Exception e4) {
                    }
                }

                d = d + availableQuantity.intValue();
                //message=""+message+"availableQuantity"+availableQuantity+"d"+d;

                erpmisdObject.setIsdReturnedQuantity(new BigDecimal(d));
                eidDAO.update(erpmisdObject);
                ViewIssueSerialDetailList = viewIsdDAO.findByEimIdfromView(ismId);


            } catch (NullPointerException e) {
                // InitializeLOVs();
//                message = "Exception in ReturnIssuedItemsAction Done method-> ReturnIssuedItemsAction" + e.getMessage() + " Reported Cause is: " + e.getCause() + "" + eimDAO.findByEimId(ismId) + "" + erpmirmDAO.findByErpmIrmId(irmId) + "" + erpmimDAO.findByErpmimId(ismId) + "" + erpmStockReceivedDAO.findbystid(ismId);
                message = "Exception in ReturnIssuedItemsAction Done method-> ReturnIssuedItemsAction" + e.getMessage() + " Reported Cause is: " + e.getCause();                
                return ERROR;
            }
        }
        //ismId=null;
        try {

            ErpmIssueMaster eim2 = eimDAO.findByEimId(ismId);
            ErpmIssueReturnMaster erpmirm2 = erpmirmDAO.findByErpmIrmId(irmId);
            ErpmItemMaster erpmim2 = erpmimDAO.findByErpmimId(erpmimId);
            ErpmStockReceived erpmstrobject1 = null;
            try {
                //erpmstrobject1 = erpmStockReceivedDAO.findbyStackSerialNo(StackSerialNo);
                erpmstrobject1 = erpmStockReceivedDAO.findbyStockSerialNo(StackSerialNo);
                // message = message+""+returnQuantityWhenItemSerialNoZero;

            } catch (Exception e8) {
            }
            ErpmIssueReturnDetail erpmird = new ErpmIssueReturnDetail();

            erpmird.setErpmIssueMaster(eim2);
            erpmird.setErpmIssueReturnMaster(erpmirm2);
            if (condvar.equals("whenItemSerialNonotZero")) {
                //erpmird.setIrmdReturnQuantity(BigDecimal.ZERO);
                erpmird.setIrmdReturnQuantity(new BigDecimal(1));

            } else {
                erpmird.setIrmdReturnQuantity(new BigDecimal(returnQuantityWhenItemSerialNoZero));
            }
            erpmird.setErpmItemMaster(erpmim2);
            if (tempIssdId != 0) {
                erpmird.setErpmStockReceived(erpmstrobject1);

            } else {
                erpmird.setErpmStockReceived(null);
            }
            erpmirdDAO.save(erpmird);
            erpmIssueReturnDetailList = erpmIssueReturnDetailDAO.findListByirmId(tempirmId);
            issuemasterList = eimDAO.findIssueMasterListBydmIdandReturnType(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId);

            VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = null;
            Variable = "";
            ViewIssueSerialDetailList = viewIsdDAO.findByEimIdfromView(ismId);

        } catch (NullPointerException e) {
            // InitializeLOVs();
            //message = "Exception in ReturnIssuedItemsAction Done method-> ReturnIssuedItemsAction" + e.getMessage() + " Reported Cause is: " + e.getCause() + "" + eimDAO.findByEimId(ismId) + "" + erpmirmDAO.findByErpmIrmId(irmId) + "" + erpmimDAO.findByErpmimId(ismId) + "" + erpmStockReceivedDAO.findbystid(ismId);
            message = "Exception in ReturnIssuedItemsAction Done method-> ReturnIssuedItemsAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
        try {

            return "SUCCESS1";
        } catch (Exception e) {
            message = "Exception in Done method-ReturnIssuedItemsAction> ReturnIssuedMasterxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

    }
    //this method execute when we get page by selecting issue serial no and clicking done button

    public String Donemethod() throws Exception {
        //try {
            if (!Variable.equals("yes")) {//if we are not secting item in dro down list and direct clicking in done method
                VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = "please first select Item or click on receiveBack link";
           //     ErpmIssueSerialDetailListforitemSerialNo = erpmIssueSerialDetailDAO.findListBydmIdReturnTypeissdReturn(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId, false);
                ErpmIssueSerialDetailListforitemSerialNo = erpmIssueSerialDetailDAO.findListBydmIdReturnTypeissdReturnwit_editedserialno(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId, false);
                return "SUCCESS2";
            }
    /*    } catch (NullPointerException e5) {
            ErpmIssueSerialDetailListforitemSerialNo = erpmIssueSerialDetailDAO.findListBydmIdReturnTypeissdReturn(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId, false);

            VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = "please first select Item";

            return "SUCCESS2";
        }*/
        try {
            //in this erpmissuedetail we have to update return quantity by adding 1
            ErpmIssueDetail erpmisdObject = new ErpmIssueDetail();

            erpmisdObject = eidDAO.findByisdId(isdId);

            availableQuantity = erpmisdObject.getIsdReturnedQuantity();
            //message=""+message+"availableQuantity"+availableQuantity+"isdId"+isdId;

            erpmisdObject.setIsdReturnedQuantity(new BigDecimal(1 + availableQuantity.intValue()));
            eidDAO.update(erpmisdObject);
            isdId = 0;
        } catch (Exception e) {
        }
        try {
            condvarforItemSerialNoPage = "ismId" + ismId + "stId" + stId + "" + LocalVariableUsedInPlaceofIsmId;
            ismId = null;
            ErpmIssueReturnDetail erpmird = new ErpmIssueReturnDetail();
            ErpmIssueSerialDetail erpmisd3 = new ErpmIssueSerialDetail();
            try {//in this we are saving information which getting by coming in this metha ti erpm issue return detail table
                erpmisd3 = erpmIssueSerialDetailDAO.findbystId(stId);
                ErpmIssueReturnMaster erpmirm3 = erpmirmDAO.findByErpmIrmId(irmId);
                ErpmItemMaster erpmim3 = erpmimDAO.findByErpmimId(erpmisd3.getErpmIssueDetail().getErpmItemMaster().getErpmimId());
                ErpmStockReceived erpmStockReceived2 = erpmStockReceivedDAO.findbystid(stId);
                ErpmIssueMaster eim3 = eimDAO.findByEimId(erpmisd3.getErpmIssueDetail().getErpmIssueMaster().getIsmId());
                erpmird.setErpmIssueMaster(eim3);
                erpmird.setErpmIssueReturnMaster(erpmirm3);
                erpmird.setErpmItemMaster(erpmim3);
                erpmird.setErpmStockReceived(erpmStockReceived2);
                erpmird.setIrmdReturnQuantity(BigDecimal.ONE);    // VarForReturnQuantityWhenIssueSerialNoselcted);
                erpmirdDAO.save(erpmird);

            } catch (Exception e6) {
                           message = "Exception in Done method - ReturnIssuedItemsAction> ReturnIssuedMasterxn" + e6.getMessage() + " Reported Cause is: " + e6.getCause();

                return ERROR;
            }

            erpmisd3.setIssdReturned(true);
            ItemSerialNo = erpmisd3.getErpmStockReceived().getStStockSerialNo();
            returnQuantityWhenItemSerialNoZero = erpmisd3.getErpmStockReceived().getErpmItemMaster().getErpmimDetailedDesc();
            erpmIssueSerialDetailDAO.update(erpmisd3);
            /*erpmIssueReturnDetailList = erpmIssueReturnDetailDAO.findListByirmId(tempirmId);
            ErpmIssueSerialDetailListforitemSerialNo = erpmIssueSerialDetailDAO.findListBydmIdReturnTypeissdReturn(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId, false);*/
            erpmIssueReturnDetailList = erpmIssueReturnDetailDAO.findListByirmIdwith_editedserialno(tempirmId);
            ErpmIssueSerialDetailListforitemSerialNo = erpmIssueSerialDetailDAO.findListBydmIdReturnTypeissdReturnwit_editedserialno(LocalVariableusedinPlaceOfreturntype, LocalVariableusedinPlaceOfdmId, false);

            messageInItemSerialNoPage = "This Item has Received ItemSerialNo " + ItemSerialNo + "and Item Name" + returnQuantityWhenItemSerialNoZero;
            ErpmIssueSerialDetailList = null;//erpmIssueSerialDetailDAO.findListByerpmStockReceivedId(stId);
            VariableWhichManageChecksequenceOfexecutionofDoneAndReceiveBackMethod = null;
            Variable = "";

        } catch (NullPointerException e) {
            // InitializeLOVs();
/*            message = "Exception in ReturnIssuedItemsAction Donemethod-> ReturnIssuedItemsAction" + e.getMessage() + " Reported Cause is: " + e.getCause() + "" + eimDAO.findByEimId(ismId) + "" + erpmirmDAO.findByErpmIrmId(irmId) + "" + erpmimDAO.findByErpmimId(ismId) + "" + erpmStockReceivedDAO.findbystid(ismId);*/
            message = "Exception in ReturnIssuedItemsAction Donemethod-> ReturnIssuedItemsAction" + e.getMessage() + " Reported Cause is: " + e.getCause();

            return ERROR;
        }
        return "SUCCESS2";

    }

    public String Back() throws Exception {
        InitializeLOVs();
        erpmirm = erpmirmDAO.findByErpmirmId(getIrmId());
        DateUtilities dt = new DateUtilities();
        returnDate= dt.convertDateToString(erpmirm.getIrmReturnDate(), "dd/MM/yyyy");
//        empList = empDao.findByDmId(Integer.valueOf(getSession().getAttribute("dmId").toString()));
//        empList = empDao.findAll();
        return "SUCCESS";

    }

    public void InitializeLOVs() {
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
        //itemlist = itemlistDAO.findByImId(DefaultInsitute);
        empList = empDao.findByDmId(Integer.valueOf(getSession().getAttribute("dmId").toString()));
        // empList = empDao.findAll();
        DefaultInsitute1 = Short.valueOf(getSession().getAttribute("imId").toString());
        DefaultSubInsitute = Integer.valueOf(getSession().getAttribute("simId").toString());
        DefaultDepartment = Integer.valueOf(getSession().getAttribute("dmId").toString());
//        erpmStockReceivedList = erpmStockReceivedDAO.findItemSerialNoList('U', Integer.valueOf(getSession().getAttribute("dmId").toString()));
        issuemasterList = erpmirmDAO.findIssuemasterlist('U', Integer.valueOf(getSession().getAttribute("dmId").toString()));
        defaultdisablevar = "true";

    }

    public void validate() {
        try {
            if (erpmirm.getIrmId() == null) {
                if (erpmirm.getInstitutionmaster().getImId() == null) {
                    addFieldError("erpmirm.institutionmaster.imId", "Please give Institution's name");
                }
                if (erpmirm.getSubinstitutionmaster().getSimId() == null) {
                    addFieldError("erpmirm.subinstitutionmaster.simId", "Please set SubInstitution's name");
                }
                if (erpmirm.getEmployeemaster().getEmpId() == null) {
                    addFieldError("erpmirm.employeemaster.empId", "Please give Employee's Short Name");
                }
                if (erpmirm.getDepartmentmaster().getDmId() == null) {
                    addFieldError("erpmirm.departmentmaster.dmId", "Please give Department's Name");
                }
                if (erpmirm.getIrmReturnNo().length() == 0) {
                    addFieldError("erpmirm.irmReturnNo", "Please give Return No's Name");
                }
                if (erpmirm.getIrmReturnType() == '\0') {
                    addFieldError("erpmirm.irmReturnType", "Please set Return Type's Name");
                }
                if (((eim.getIsmId() == null) || (erpmStockReceived.getStId() == null))) {
                    addFieldError("eim.ismId", "Please Select Anyone ");
                }
                if ((eim.getIsmIssueNo() == null) || (erpmStockReceived.getStStockSerialNo() == null)) {
                    addFieldError("eim.ismId", "Please Select Anyone List");
                }
                InitializeLOVs();
            }

        } catch (NullPointerException e) {
        }
    }

    @SkipValidation
    public String Browse() throws Exception {
        ViewIssueSerialDetailList = null;

        try {

            erpmirmList = erpmirmDAO.findReturnIssuedItemsForUserInstitutes(Integer.valueOf(getSession().getAttribute("userid").toString()));

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> SubInstitutionAxn" + e.getMessage();
            return ERROR;
        }
    }

    public String Edit() throws Exception {
        ViewIssueSerialDetailList = null;

        try {
            DateUtilities dt = new DateUtilities();

            erpmirm = erpmirmDAO.findByErpmirmId(getIrmId());
            returnDate= dt.convertDateToString(erpmirm.getIrmReturnDate(), "dd/MM/yyyy");
            
//            erpmStockReceivedList = erpmStockReceivedDAO.findItemSerialNoList('U', Integer.valueOf(getSession().getAttribute("dmId").toString()));
            issuemasterList = erpmirmDAO.findIssuemasterlist('U', Integer.valueOf(getSession().getAttribute("dmId").toString()));
            defaultdisablevar = "true";
            
            imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
            dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
            empList = empDao.findByDmId(Integer.valueOf(getSession().getAttribute("dmId").toString()));
//            empList = empDao.findAll();
            DefaultInsitute1 = Short.valueOf(getSession().getAttribute("imId").toString());
            DefaultSubInsitute = Integer.valueOf(getSession().getAttribute("simId").toString());
            DefaultDepartment = Integer.valueOf(getSession().getAttribute("dmId").toString());

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String PrintReturnIssuedItemReceiving() throws Exception {
        HashMap hm = new HashMap();
        // Get System properties
        Properties properties = System.getProperties();

        // Get the path separator symbol, which is unfortunatly different, in different OS platform.
        String pathSeparator = properties.getProperty("file.separator");

        pathSeparator =pathSeparator + pathSeparator;
        String repPath = "pico" + pathSeparator + "Inventory"  + pathSeparator + "Reports" + pathSeparator + "ReturnIssuedItemReceiving.jasper" ;

        String fileName = getSession().getServletContext().getRealPath(repPath);
//        String fileName = getSession().getServletContext().getRealPath("pico\\Inventory\\Reports\\ReturnIssuedItemReceiving.jasper");
        String whereCondition="";
        try {
  //          Locale locale = ActionContext.getContext().getLocale();
  //          ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
    //        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("JNDI");
            }
            dataSourceURL = (String) ctx.lookup("java:comp/env/ReportURL").toString();
            Connection conn = DriverManager.getConnection(dataSourceURL);
            
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=ReturnIssuedItem.pdf");
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //Setup Where Condition Clause
           if (getIrmId() != null) {
                 whereCondition = "erpm_issue_return_master.IRM_ID <>0";//getSession().getAttribute("imId");
              
            } else {
                 whereCondition = "erpm_issue_return_master.IRM_ID =" + getIrmId();
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

    public String Delete() {
        erpmirm = erpmirmDAO.findByErpmIrmId(getIrmId());
        erpmirmDAO.delete(erpmirm);
        erpmirmList = erpmirmDAO.findReturnIssuedItemsForUserInstitutes(Integer.valueOf(getSession().getAttribute("userid").toString()));
        return SUCCESS;
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
  //          Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bundle.getString("dbName"), bundle.getString("mysqlUserName"), bundle.getString("mysqlPassword")); 

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


            whereCondition = "gfr_program_mapping.`GPM_Program_ID` = 31";

            hm.put("condition", whereCondition);
            hm.put("screen_name", "RETURN ISSUED ITEMS");
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
