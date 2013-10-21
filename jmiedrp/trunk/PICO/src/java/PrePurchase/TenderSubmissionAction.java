/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 *  @author ehtesham, Arpan, Tanvir
 */
package PrePurchase;

import org.apache.struts2.ServletActionContext;
import pojo.hibernate.ErpmTenderMasterDAO;
import java.io.*;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileInputStream;

import java.util.*;
import org.apache.struts2.interceptor.validation.SkipValidation;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.ErpmTenderMaster;
import pojo.hibernate.ErpmTenderSubmission;
import pojo.hibernate.ErpmTenderSubmissionDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import utils.DateUtilities;
import utils.DevelopmentSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pojo.hibernate.ErpmTenderSubmissionFiles;
import pojo.hibernate.ErpmTenderSubmissionFilesDao;

public class TenderSubmissionAction extends DevelopmentSupport {

    private HttpServletRequest request;
    public static Integer tsbTsbId;
    public static Integer tsfTsfId ;
    public static String localtendername;
    public static String localtcompanyname;
    public static String localttenderno;
    public static String filename="-";//this var temprory used for checking same name of file in SaveUploadedFile method

    private ErpmTenderMaster erpmTenderMaster;
    private ErpmGenMaster erpmGenMaster;
    private String tsbCompanyEmail;
    private String tsbCompanyAddress;
    private String tsbEmdReturnFileReference;
    private Date tsbEmdReturnDate;
    private Character tsbEmdReturned;
    private String tsbDdCashReceiptNo;
    private String tsbDdCash;
    private Integer tsbTenderFee;
    private Date tsbBgDdValidityDate;
    private String tsbBgDdNo;
    private String tsbEmdBankName;
    private Integer tsbEmdAmount;
    private String tsbCompanyPhone;
    private String message;
    private Character emdreturn;
    private InputStream inputStream;
    private String tsbCompanyName;
    private String TenderName;
    private Integer TenderNumber;
    private Short DefaultInsitute1;
    private Integer DefaultSubInsitute;
    private String tname;
    private String tendersubmission;
    private short s = 24;
    private Integer DefaultDepartment;
    private List<ErpmTenderSubmission> erpmtsbList;
    private ErpmGenMasterDao emdtypedao = new ErpmGenMasterDao();
    private ErpmTenderSubmission erpmtsb;
    private Departmentmaster departmentmaster;
    private Subinstitutionmaster subinstitutionmaster;
    private Institutionmaster institutionmaster;
    private ErpmTenderSubmissionFiles erpmtsf;
    private List<ErpmTenderSubmissionFiles> erpmtsfList = new ArrayList<ErpmTenderSubmissionFiles>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private ErpmTenderSubmissionFilesDao erpmtsfDao = new ErpmTenderSubmissionFilesDao();
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private List<Departmentmaster> dmList = new ArrayList<Departmentmaster>();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Subinstitutionmaster> simList = new ArrayList<Subinstitutionmaster>();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private List<ErpmTenderMaster> tmList = new ArrayList<ErpmTenderMaster>();
    private List<ErpmTenderSubmission> erpmtsmList = new ArrayList<ErpmTenderSubmission>();
    private List<ErpmGenMaster> emdtypeList = new ArrayList<ErpmGenMaster>();
    private ErpmTenderMasterDAO tmDao = new ErpmTenderMasterDAO();
    private ErpmTenderSubmissionDAO erpmtsbDao = new ErpmTenderSubmissionDAO();
//                              GETTER SETTER METHODS
    public static File Uploadfile;//Upload  TenderSubmissionFile
    public String UploadfileContentType;//this is content type of file 
    private static String fileUploadFileName;
    private static String UploadfileFileName;//this is file name which is obtained direct from uploadfile  without entering filename
    private static String TsfFileRemarks;
    private HttpServletRequest servletRequest;

    public String getUploadfileContentType() {
        return UploadfileContentType;
    }

    public void setUploadfileContentType(String UploadfileContentType) {
        this.UploadfileContentType = UploadfileContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getUploadfileFileName() {
        return UploadfileFileName;
    }

    public void setUploadfileFileName(String UploadfileFileName) {
        this.UploadfileFileName = UploadfileFileName;
    }

    public File getUploadfile() {
        return Uploadfile;
    }

    public void setUploadfile(File Uploadfile) {
        this.Uploadfile = Uploadfile;
    }

    public String getTendersubmission() {
        return tendersubmission;
    }

    public void setTendersubmission(String tendersubmission) {
        this.tendersubmission = tendersubmission;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Character getEmdreturn() {
        return emdreturn;
    }

    public void setEmdreturn(Character emdreturn) {
        this.emdreturn = emdreturn;
    }

    public Short getDefaultInsitute1() {
        return DefaultInsitute1;
    }

    public void setDefaultInsitute1(Short DefaultInsitute1) {
        this.DefaultInsitute1 = DefaultInsitute1;
    }

    public Integer getDefaultSubInsitute() {
        return DefaultSubInsitute;
    }

    public void setDefaultSubInsitute(Integer DefaultSubInsitute) {
        this.DefaultSubInsitute = DefaultSubInsitute;
    }

    public Integer getDefaultDepartment() {
        return DefaultDepartment;
    }

    public void setDefaultDepartment(Integer DefaultDepartment) {
        this.DefaultDepartment = DefaultDepartment;
    }

    public Integer getTenderNumber() {
        return TenderNumber;
    }

    public void setTenderNumber(Integer TenderNumber) {
        this.TenderNumber = TenderNumber;
    }

    public ErpmTenderSubmission getErpmtsb() {
        return erpmtsb;
    }

    public void setErpmtsb(ErpmTenderSubmission erpmtsb) {
        this.erpmtsb = erpmtsb;
    }

    public String getTenderName() {
        return TenderName;
    }

    public void setTenderName(String TenderName) {
        this.TenderName = TenderName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public Institutionmaster getInstitutionmaster() {
        return this.institutionmaster;
    }

    public void setInstitutionmaster(Institutionmaster institutionmaster) {
        this.institutionmaster = institutionmaster;
    }

    public Subinstitutionmaster getSubinstitutionmaster() {
        return this.subinstitutionmaster;
    }

    public void setSubinstitutionmaster(Subinstitutionmaster subinstitutionmaster) {
        this.subinstitutionmaster = subinstitutionmaster;
    }

    public Departmentmaster getDepartmentmaster() {
        return this.departmentmaster;
    }

    public void setDepartmentmaster(Departmentmaster departmentmaster) {
        this.departmentmaster = departmentmaster;
    }

    public String getTsbCompanyName() {
        return this.tsbCompanyName;
    }

    public void setTsbCompanyName(String tsbCompanyName) {
        this.tsbCompanyName = tsbCompanyName;
    }

    public Integer getTsbTsbId() {
        return this.tsbTsbId;
    }

    public void setTsbTsbId(Integer tsbTsbId) {
        this.tsbTsbId = tsbTsbId;
    }

    public ErpmTenderMaster getErpmTenderMaster() {
        return this.erpmTenderMaster;
    }

    public void setErpmTenderMaster(ErpmTenderMaster erpmTenderMaster) {
        this.erpmTenderMaster = erpmTenderMaster;
    }

    public ErpmGenMaster getErpmGenMaster() {
        return this.erpmGenMaster;
    }

    public void setErpmGenMaster(ErpmGenMaster erpmGenMaster) {
        this.erpmGenMaster = erpmGenMaster;
    }

    public String getTsbCompanyEmail() {
        return this.tsbCompanyEmail;
    }

    public void setTsbCompanyEmail(String tsbCompanyEmail) {
        this.tsbCompanyEmail = tsbCompanyEmail;
    }

    public String getTsbCompanyAddress() {
        return this.tsbCompanyAddress;
    }

    public void setTsbCompanyAddress(String tsbCompanyAddress) {
        this.tsbCompanyAddress = tsbCompanyAddress;
    }

    public String getTsbCompanyPhone() {
        return this.tsbCompanyPhone;
    }

    public void setTsbCompanyPhone(String tsbCompanyPhone) {
        this.tsbCompanyPhone = tsbCompanyPhone;
    }

    public Integer getTsbEmdAmount() {
        return this.tsbEmdAmount;
    }

    public void setTsbEmdAmount(Integer tsbEmdAmount) {
        this.tsbEmdAmount = tsbEmdAmount;
    }

    public String getTsbEmdBankName() {
        return this.tsbEmdBankName;
    }

    public void setTsbEmdBankName(String tsbEmdBankName) {
        this.tsbEmdBankName = tsbEmdBankName;
    }

    public String getTsbBgDdNo() {
        return this.tsbBgDdNo;
    }

    public void setTsbBgDdNo(String tsbBgDdNo) {
        this.tsbBgDdNo = tsbBgDdNo;
    }

    public Date getTsbBgDdValidityDate() {
        return this.tsbBgDdValidityDate;
    }

    public void setTsbBgDdValidityDate(Date tsbBgDdValidityDate) {
        this.tsbBgDdValidityDate = tsbBgDdValidityDate;
    }

    public Integer getTsbTenderFee() {
        return this.tsbTenderFee;
    }

    public void setTsbTenderFee(Integer tsbTenderFee) {
        this.tsbTenderFee = tsbTenderFee;
    }

    public String getTsbDdCash() {
        return this.tsbDdCash;
    }

    public void setTsbDdCash(String tsbDdCash) {
        this.tsbDdCash = tsbDdCash;
    }

    public String getTsbDdCashReceiptNo() {
        return this.tsbDdCashReceiptNo;
    }

    public void setTsbDdCashReceiptNo(String tsbDdCashReceiptNo) {
        this.tsbDdCashReceiptNo = tsbDdCashReceiptNo;
    }

    public Character getTsbEmdReturned() {
        return this.tsbEmdReturned;
    }

    public void setTsbEmdReturned(Character tsbEmdReturned) {
        this.tsbEmdReturned = tsbEmdReturned;
    }

    public Date getTsbEmdReturnDate() {
        return this.tsbEmdReturnDate;
    }

    public void setTsbEmdReturnDate(Date tsbEmdReturnDate) {
        this.tsbEmdReturnDate = tsbEmdReturnDate;
    }

    public String getTsbEmdReturnFileReference() {
        return this.tsbEmdReturnFileReference;
    }

    public void setTsbEmdReturnFileReference(String tsbEmdReturnFileReference) {
        this.tsbEmdReturnFileReference = tsbEmdReturnFileReference;
    }

    public List<ErpmTenderSubmission> geterpmtsbList() {
        return this.erpmtsmList;
    }

    public void seterpmtsmList(List< ErpmTenderSubmission> erpmtsmList) {
        this.erpmtsmList = erpmtsmList;
    }

    public List<Institutionmaster> getimList() {
        return this.imList;
    }

    public void setimList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

    public List<Subinstitutionmaster> getsimList() {
        return this.simList;
    }

    public void setsimList(List<Subinstitutionmaster> simList) {
        this.simList = simList;
    }

    public List<Departmentmaster> getdmList() {
        return this.dmList;
    }

    public void setdmList(List<Departmentmaster> dmList) {
        this.dmList = dmList;
    }

    public List<ErpmTenderMaster> gettmList() {
        return this.tmList;
    }

    public void settmList(List<ErpmTenderMaster> tmList) {
        this.tmList = tmList;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public List<ErpmGenMaster> getEmdtypeList() {
        return emdtypeList;
    }

    public void setEmdtypeList(List<ErpmGenMaster> emdtypeList) {
        this.emdtypeList = emdtypeList;
    }

    public ErpmGenMasterDao getEmdtypedao() {
        return emdtypedao;
    }

    public void setEmdtypedao(ErpmGenMasterDao emdtypedao) {
        this.emdtypedao = emdtypedao;
    }

    public ErpmTenderSubmissionDAO getErpmtsbDao() {
        return erpmtsbDao;
    }

    public void setErpmtsbDao(ErpmTenderSubmissionDAO erpmtsbDao) {
        this.erpmtsbDao = erpmtsbDao;
    }

    public List<ErpmTenderSubmission> getErpmtsbList() {
        return erpmtsbList;
    }

    public void setErpmtsbList(List<ErpmTenderSubmission> erpmtsbList) {
        this.erpmtsbList = erpmtsbList;
    }

    public void seterpmtsfList(List<ErpmTenderSubmissionFiles> erpmtsfList) {
        this.erpmtsfList = erpmtsfList;
    }

    public List<ErpmTenderSubmissionFiles> geterpmtsfList() {
        return this.erpmtsfList;
    }

    public ErpmTenderSubmissionFiles getErpmtsf() {
        return erpmtsf;
    }

    public void setErpmtsf(ErpmTenderSubmissionFiles erpmtsf) {
        this.erpmtsf = erpmtsf;
    }

    public int gettsfTsfId() {//this is used local id in action class for operation
        return tsfTsfId;
    }

    public void settsfTsfId(int tsfTsfId) {
        this.tsfTsfId = tsfTsfId;
    }

    public String getlocaltendername() {
        return localtendername;
    }

    public void setlocaltendername(String localtendername) {
        this.localtendername = localtendername;
    }

    public String getlocaltcompanyname() {
        return localtcompanyname;
    }

    public void setlocaltcompanyname(String localtcompanyname) {
        this.localtcompanyname = localtcompanyname;
    }

    public String getlocalttenderno() {
        return localttenderno;
    }

    public void setlocalttenderno(String localttenderno) {
        this.localttenderno = localttenderno;
    }

    public String getTsfFileRemarks() {
        return TsfFileRemarks;
    }

    public void setTsfFileRemarks(String TsfFileRemarks) {
        this.TsfFileRemarks = TsfFileRemarks;
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {

        this.servletRequest = servletRequest;



    }

    @SkipValidation
    public String execute() throws Exception {
        try {
            //TO SHOW OFF LIST
            InitializeLOVs();
            Date d = new Date();
            DateUtilities d1 = new DateUtilities();
            setTendersubmission(d1.convertDateToString(d, "dd-MM-yyyy"));
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> ManageOpeningStockAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String BrowseTSB() throws Exception {
        try {
            //Retrieve Items which belong to the institutes in the perview of user
            erpmtsbList = erpmtsbDao.findAll();
            //findItemsForUserInstitutes(Integer.valueOf(getSession().getAttribute("userid").toString()));
            tsfTsfId = 0;

            InitializeLOVs();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Browse method -> ManageItemsAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String SaveTSB() throws Exception {
        try {
            //If part saves new record else parts is for record update
            DateUtilities dt = new DateUtilities();
            if (erpmtsb.getTsbTsbId() == null) {
                erpmtsb.setTsbSubmissionDate(dt.convertStringToDate(getTendersubmission()));
                erpmtsbDao.save(erpmtsb);
                //THIS PART IS USED WHEN WE WANT TO SAVE VALUE AND BACK TO BLNK PAGE AFTER SAVING THE VALUE

                erpmtsb = new ErpmTenderSubmission();
                ErpmTenderSubmissionDAO erpmtsbDao = new ErpmTenderSubmissionDAO();
                erpmtsbList = erpmtsbDao.findAll();
                setTenderName(null);//USED FOR SETTNG THE BLANK VALUE OF TENDER NAME
                message = "Item record saved successfully.";// " +(erpmtsb.getTsbTsbId());

            } else {
                ErpmTenderSubmission erpmtsb1 = erpmtsbDao.findByErpmtsbId(erpmtsb.getTsbTsbId());                 //findByErpmicmItemId(erpmItemCategoryMaster.getErpmicmItemId());
                erpmtsb1 = erpmtsb;
                erpmtsb.setTsbSubmissionDate(dt.convertStringToDate(getTendersubmission()));
                erpmtsbDao.update(erpmtsb1);
                // FOR BLANK AFTER SAVING VALUES
                erpmtsb = new ErpmTenderSubmission();
                ErpmTenderSubmissionDAO erpmtsbDao = new ErpmTenderSubmissionDAO();
                erpmtsbList = erpmtsbDao.findAll();
                setTenderName(null);

                message = "Record updated successfully.";
            }

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Save method -> TenderSubissionAction" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String ClearTSB() throws Exception {
        try {
            message = "CLEARING ITEMS";
            //tosImIdList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
            erpmtsb = null;
            setTenderName(null);//FOR SETTNG BLNK NAME OF TENDER NAME
            InitializeLOVs();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Clear method -> TenderSubmissionAction " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }

    }

    @SkipValidation
    public String EditTSB() throws Exception {
        try {
            erpmtsb = erpmtsbDao.findByErpmtsbId(getTsbTsbId());
            localtendername = "" + erpmtsb.getErpmTenderMaster().getTmName();
            localtcompanyname = "" + erpmtsb.getTsbCompanyName();
            localttenderno = "" + erpmtsb.getErpmTenderMaster().getTmTenderNo();
            tsfTsfId = 1;//for apear of UploadSubmissionFile button int erpmtsf is not 0
            message = "" + erpmtsb.getTsbCompanyName();
            setTenderName(erpmtsb.getErpmTenderMaster().getTmName()); //FOR SET TENDER NAME
            DateUtilities dt = new DateUtilities();
            tendersubmission = dt.convertDateToString(erpmtsb.getTsbSubmissionDate(), "dd/MM/yyyy");
            InitializeLOVs();
            return SUCCESS;

        } catch (Exception e) {
            message = "Exception in Edit method ->" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String DeleteTSB() throws Exception {
        try {
            //Retrieve the record to be deleted
            erpmtsb = erpmtsbDao.findByErpmtsbId(getTsbTsbId());
            erpmtsbDao.delete(erpmtsb);
            erpmtsbList = erpmtsbDao.findAll();
            //       findByImId(Short.parseShort(getSession().getAttribute("imId").toString()));
            message = "Item Record deleted successfully.";

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Delete method -> ManageItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String UploadSubmissionFile() throws Exception {


        try {

            // message=""+erpmtsb.getTsbBgDdValidityDate();
            tsbTsbId = getTsbTsbId();
            tsfTsfId = null;
            erpmtsfList = erpmtsfDao.getListofFile(tsbTsbId);
 filename="";
            return "success";
        } catch (Exception e) {
            message = "Exception in UploadSubmissionFile method -> ManageItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String ClearTenderSubmissionFile() throws Exception {


        try {

            tsfTsfId = null;
            fileUploadFileName = "";
            TsfFileRemarks = "";
            erpmtsfList = erpmtsfDao.getListofFile(tsbTsbId);//get list of all file stored in database
 filename="";
            return "success";
        } catch (Exception e) {
            message = "Exception in UploadSubmissionFile method -> ManageItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation
    public String BackTenderSubmissionFile() throws Exception {


        try {

            // message=""+erpmtsb.getTsbBgDdValidityDate();
             erpmtsb = erpmtsbDao.findByErpmtsbId(getTsbTsbId());
            tsfTsfId = null;
            fileUploadFileName = "";
            TsfFileRemarks = "";
 filename="";
            return "success";
        } catch (Exception e) {
            message = "Exception in UploadSubmissionFile method -> ManageItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation//this method to edit file of selected id
    public String EditTenderSubmissionFile() throws Exception {


        try {
            //   message=""+gettsfTsfId()+tsbTsbId;
            tsfTsfId=gettsfTsfId();
            // message=""+erpmtsb.getTsbBgDdValidityDate();
            erpmtsf = erpmtsfDao.findByerpmtsfId(gettsfTsfId());//get object erpmtsf according selected id
            fileUploadFileName = "" + erpmtsf.getTsfFileName();
            TsfFileRemarks = "" + erpmtsf.getTsfFileRemarks();


            erpmtsfList = erpmtsfDao.getListofFile(tsbTsbId);//get list of all file stored in database

 filename="";
            return "success";
        } catch (Exception e) {
            message = "Exception in DeleteTenderSubmissionFile method -> ManageItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    @SkipValidation//this method to delete file stored in database
    public String DeleteTenderSubmissionFile() throws Exception {


        try {
            erpmtsf = erpmtsfDao.findByerpmtsfId(gettsfTsfId());//get object erpmtsf according selected id
            erpmtsfDao.DeleteSubmissionFile(erpmtsf);
            erpmtsf.setTsfTsfId(0);
            erpmtsfList = erpmtsfDao.getListofFile(tsbTsbId);//get list of all file stored in database

            tsfTsfId = null;
            filename="";
            return "success";
        } catch (Exception e) {
            message = "Exception in DeleteTenderSubmissionFile method -> ManageItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

	//this method used to save file in database
    @SkipValidation
    public String SaveUploadedFile() throws Exception {

      
        FileInputStream fis = null;
        //System.out.println("getFileUpload()--m,.m,----------");
       ErpmTenderSubmissionFiles erpmtsf1 = new ErpmTenderSubmissionFiles();
       try {
           
            String filePath = servletRequest.getRealPath("/");//getting server path
            System.out.println("Server path:" + filePath);
        
            System.out.println("TsfFileRemarks------------" + TsfFileRemarks);
            System.out.println("content type: " + UploadfileContentType);
            System.out.println(" File name: " + UploadfileFileName);
            System.out.println("entered file name : " + fileUploadFileName.indexOf(".pdf"));
            if (fileUploadFileName.equals("")) {
            
                fileUploadFileName = UploadfileFileName;
                System.out.println("fileUploadFileName : " + fileUploadFileName);

            } else {
                if (fileUploadFileName.indexOf(".pdf") == -1) {
                    fileUploadFileName = fileUploadFileName + ".pdf";
                }
            }
 if(!filename.equals(UploadfileFileName)){

            File fileToCreate = new File(filePath, this.fileUploadFileName);
            System.out.println("fileToCreate: " + fileToCreate.getAbsolutePath());
            String filepath = "" + fileToCreate.getAbsolutePath();
            FileUtils.copyFile(this.Uploadfile, fileToCreate);//copy file to server
            System.out.println("tsfTsfId:--------" + tsfTsfId);



            if (tsfTsfId == null) {//if we are saving file then its tsftsfid will be zero
                erpmtsf1.setTsfFileName(fileUploadFileName);
                erpmtsf1.setTsfFileRemarks(TsfFileRemarks);
                erpmtsb = erpmtsbDao.findByErpmtsbId(tsbTsbId);
                erpmtsf1.setErpmTenderSubmission(erpmtsb);
//proccess to read file from filepath
                File image = new File(filepath);
                byte[] bFile = new byte[(int) image.length()];
                try {
                    FileInputStream fileInputStream = new FileInputStream(image);
                    fileInputStream.read(bFile);
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fis = new FileInputStream(image);
                erpmtsf1.setTsfFileStream(bFile);
                System.out.println("erpmtsf1-------" + erpmtsf1.getTsfFileRemarks());
                erpmtsfDao.save(erpmtsf1);
                System.out.println(" done-save--- ");
                fis.close();
                filepath = null;
                erpmtsf1=null;
                tsfTsfId = 0; 
                Uploadfile=null;
            }
            if (tsfTsfId != null) {//if we are updating file then its tsftsfid will not  be zero
                erpmtsf1.setTsfTsfId(tsfTsfId);
                erpmtsf1.setTsfFileName(fileUploadFileName);
                erpmtsf1.setTsfFileRemarks(TsfFileRemarks);
                erpmtsb = erpmtsbDao.findByErpmtsbId(tsbTsbId);
                erpmtsf1.setErpmTenderSubmission(erpmtsb);
//proccess to read file  from filepath
                File image = new File(filepath);
                byte[] bFile = new byte[(int) image.length()];
                try {
                    FileInputStream fileInputStream = new FileInputStream(image);
                    fileInputStream.read(bFile);
                    fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fis = new FileInputStream(image);
                erpmtsf1.setTsfFileStream(bFile);
                erpmtsfDao.update(erpmtsf1);
                fis.close();
                System.out.println(" done---- ");
                erpmtsf1=null;
                 Uploadfile=null;
              
            }

}

        } catch (Exception e) {
            e.printStackTrace();
        }


        filename=""+fileUploadFileName;
       erpmtsf1=null;
     
      UploadfileFileName=null;
       tsfTsfId = null;
       fileUploadFileName = null;
        TsfFileRemarks = null;
       erpmtsfList = null;
       erpmtsfList = erpmtsfDao.getListofFile(tsbTsbId);//get list of all file stored in database
       

        return "success";


    }

    //this method used for download  file from database
	@SkipValidation
    public String DownLoadTenderSubmissionFile() throws Exception {
        try {
                     erpmtsf  = erpmtsfDao.findByerpmtsfId(gettsfTsfId());//getting erpmtsf object 
                     String filename = "1";
                     HttpServletResponse response = ServletActionContext.getResponse();//get response object
                     byte[] bytearray  = new byte[(int) erpmtsf.getTsfFileStream().length];//get array of size equal to file size
                     bytearray  = erpmtsf.getTsfFileStream();
                    filename=erpmtsf.getTsfFileName();
                    response.reset();
                    response.setContentType("application/pdf");

                   response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
                   response.setHeader("cache-control", "must-revalidate");


            int size = bytearray.length;

            try {

                while (bytearray .length!=-1) {
                    response.getOutputStream().write(bytearray , 0, size);
                    break;
                }
                response.flushBuffer();
            } catch (Exception e) {
            }
 filename="";
            return "success";
        } catch (Exception e) {
            message = "Exception in DownLoadTenderSubmissionFile method -> ManageItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    private void InitializeLOVs() {
        imList = imDao.findInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        simList = simDao.findSubInstForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Short.valueOf(getSession().getAttribute("imId").toString()));
        dmList = dmDao.findDepartmentForUser(Integer.valueOf(getSession().getAttribute("userid").toString()), Integer.valueOf(getSession().getAttribute("simId").toString()));
        tmList = tmDao.findAll();
        emdtypeList = emdtypedao.findByErpmGmType(s);
        DefaultInsitute1 = Short.valueOf(getSession().getAttribute("imId").toString());
        DefaultSubInsitute = Integer.valueOf(getSession().getAttribute("simId").toString());
        DefaultDepartment = Integer.valueOf(getSession().getAttribute("dmId").toString());
        dmList = dmDao.findAllDepartmentsForUser(Integer.valueOf(getSession().getAttribute("userid").toString()));
        erpmtsfList = erpmtsfDao.getListofFile(tsbTsbId);
    }

    @Override
    public void validate() {
        try {
            InitializeLOVs();

            emdtypeList = emdtypedao.findByErpmGmType(s);
            if (erpmtsb.getInstitutionmaster().getImId() == 0) {
                addFieldError("erpmtsb.institutionmaster.imId", "Please select institution from the list");
            }

            if (erpmtsb.getSubinstitutionmaster().getSimId() == 0) {
                addFieldError("erpmtsb.subinstitutionmaster.simId", "Please select Subinstitution");
            }


            if (erpmtsb.getDepartmentmaster().getDmId() == 0) {
                addFieldError("erpmtsb.departmentmaster.dmId", "Please select Department");
            }


            if (erpmtsb.getErpmTenderMaster().getTmTmId() == 0) {
                addFieldError("TenderName", "Please select Tender number");
            }

            if (erpmtsb.getTsbCompanyName().length() == 0) {
                addFieldError("CompanyName", "Please select Company Name");
            }



            if (erpmtsb.getTsbCompanyEmail().length() == 0) {
                addFieldError("erpmtsb.tsbCompanyEmail", "Please select company  email");
            }



        } catch (Exception e) {
            message = "Exception in Delete method -> ManageItemstAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();

        }
    }
}
