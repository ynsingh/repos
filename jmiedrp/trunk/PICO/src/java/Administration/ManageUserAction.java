package Administration;

/**
 *
 * @author kazim
 */
import java.util.ArrayList;
import java.util.List;
import pojo.hibernate.Countrymaster;
import pojo.hibernate.CountrymasterDAO;
import pojo.hibernate.Departmentmaster;
import pojo.hibernate.DepartmentmasterDAO;
import pojo.hibernate.Erpmuserrole;
import pojo.hibernate.ErpmuserroleDAO;
import pojo.hibernate.Erpmusers;
import pojo.hibernate.ErpmusersDAO;
import pojo.hibernate.Genericroleprivileges;
import pojo.hibernate.GenericroleprivilegesDAO;
import pojo.hibernate.Genericuserroles;
import pojo.hibernate.GenericuserrolesDAO;
import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.Institutionroleprivileges;
import pojo.hibernate.InstitutionroleprivilegesDAO;
import pojo.hibernate.InstitutionuserroleDAO;
import pojo.hibernate.Institutionuserroles;
import pojo.hibernate.Statemaster;
import pojo.hibernate.Subinstitutionmaster;
import pojo.hibernate.SubinstitutionmasterDAO;
import utils.DevelopmentSupport;
import utils.sendMail;
import java.util.Locale;
import java.util.ResourceBundle;
import com.opensymphony.xwork2.ActionContext;


/**
 *
 * @author kazim
 */
public class ManageUserAction extends DevelopmentSupport  {

    private Erpmusers erpmusers;
    private ErpmusersDAO erpmusersDao = new ErpmusersDAO();

    private Erpmuserrole erpmur = new Erpmuserrole();
    private ErpmuserroleDAO erpmurDao = new ErpmuserroleDAO();

    private Institutionuserroles iur = new Institutionuserroles();
    private InstitutionuserroleDAO iurDao = new InstitutionuserroleDAO();

    private List<Statemaster> statemasterList = new ArrayList<Statemaster>();

    private List<Countrymaster> ctList = new ArrayList<Countrymaster>();
    private CountrymasterDAO cmDao = new CountrymasterDAO();

    private List<Institutionmaster> imIdList = new ArrayList<Institutionmaster>();
    private List<Subinstitutionmaster> simImIdList = new ArrayList<Subinstitutionmaster>();
    private List<Departmentmaster> dmIdList = new ArrayList<Departmentmaster>();
    private List<Institutionuserroles> iurIdList = new ArrayList<Institutionuserroles>();
    private InstitutionuserroleDAO irDao= new InstitutionuserroleDAO();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();
    private SubinstitutionmasterDAO simDao = new SubinstitutionmasterDAO();
    private DepartmentmasterDAO dmDao = new DepartmentmasterDAO();
    private List<Institutionmaster> varimList = new ArrayList<Institutionmaster>();
    private List<Subinstitutionmaster> varSimList = new ArrayList<Subinstitutionmaster>();
    private List<Departmentmaster> varDmList = new ArrayList<Departmentmaster>();
    private List<Erpmusers> varErpmUserList = new ArrayList<Erpmusers>();
    private List<Erpmuserrole> erpmurList = new ArrayList<Erpmuserrole>();

    private Institutionmaster im;
    private Subinstitutionmaster sim;
    private Departmentmaster dm;

    static String dataSourceURL=null;

    private String message;
    private Integer erpmuId;
    private String erpmuName;
    private String RetypedPassword;
    private Integer erpmurId;
    private String erpmusersdob;
    private Short imId;
    private String emailTo;
    private String subject;
    private String senderEmailId;
    private String senderContactNo;

    public void setemailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getemailTo() {
        return this.emailTo;
    }



    public void setimId(Short imId) {
        this.imId = imId;
    }

    public Short getimId() {
        return this.imId;
    }

    public void setsubject(String subject) {
        this.subject = subject;
    }

    public String getsubject() {
        return this.subject;
    }

    public void setsenderEmailId(String senderEmailId) {
        this.senderEmailId = senderEmailId;
    }

    public String getsenderEmailId() {
        return this.senderEmailId;
    }

    public void setsenderContactNo(String senderContactNo) {
        this.senderContactNo = senderContactNo;
    }

    public String getsenderContactNo() {
        return this.senderContactNo;
    }

    public void setim(Institutionmaster im) {
        this.im = im;
    }

    public Institutionmaster getim() {
        return this.im;
    }

    public void setsim(Subinstitutionmaster sim) {
        this.sim = sim;
    }

    public Subinstitutionmaster getsim() {
        return this.sim;
    }

    public void setdm(Departmentmaster dm) {
        this.dm = dm;
    }

    public Departmentmaster getdm() {
        return this.dm;
    }

    public void seterpmur(Erpmuserrole erpmur) {
        this.erpmur = erpmur;
    }

    public Erpmuserrole geterpmur() {
        return this.erpmur;
    }

    public void seterpmuId(Integer erpmuId) {
        this.erpmuId = erpmuId;
    }

    public Integer geterpmuId() {
        return this.erpmuId;
    }

    public Erpmusers geterpmusers() {
        return this.erpmusers;
    }

    public void seterpmusers(Erpmusers erpmusers) {
        this.erpmusers = erpmusers;
    }

    public void setimIdList(List<Institutionmaster> imIdList) {
        this.imIdList = imIdList;
    }

    public List<Institutionmaster> getimIdList() {
        return this.imIdList;
    }

    public void setsimImIdList(List<Subinstitutionmaster> simImIdList) {
        this.simImIdList = simImIdList;
    }

    public List<Subinstitutionmaster> getsimImIdList() {
        return this.simImIdList;
    }

    public void setdmIdListList(List<Departmentmaster> dmIdList) {
        this.dmIdList = dmIdList;
    }

    public List<Departmentmaster> getdmIdList() {
        return this.dmIdList;
    }

    public void setiurIdList(List<Institutionuserroles> iurIdList) {
        this.iurIdList = iurIdList;
    }

    public List<Institutionuserroles> getiurIdList() {
        return this.iurIdList;
    }


    public String geterpmusersdob() {
        return this.erpmusersdob;
    }

    public void geterpmusersdob(String erpmusersdob) {
        this.erpmusersdob = erpmusersdob;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void seterpmuName(String erpmuName) {
        this.erpmuName = erpmuName;
    }

    public String geterpmuName() {
        return this.erpmuName;
    }


    public void setRetypedPassword(String RetypedPassword) {
        this.RetypedPassword = RetypedPassword;
    }

    public String getRetypedPassword() {
        return this.RetypedPassword;
    }

   public void seterpmurList(List<Erpmuserrole> erpmurList) {
        this.erpmurList = erpmurList;
    }

    public List<Erpmuserrole> geterpmurList() {
        return this.erpmurList;
    }

    public Integer geterpmurId() {
        return this.erpmurId;
    }

    public void seterpmurId(Integer erpmurId) {
        this.erpmurId = erpmurId;
    }

    public List<Statemaster> getStatemasterList() {
        return statemasterList;
    }

    public void setStatemasterList(List<Statemaster> statemasterList) {
        this.statemasterList = statemasterList;
    }

    public void setctList(List<Countrymaster> ctList) {
        this.ctList = ctList;
    }

    public List<Countrymaster> getctList() {
        return this.ctList;
    }


    public List<Departmentmaster> getVarDmList() {
        return varDmList;
    }

    public void setVarDmList(List<Departmentmaster> varDmList) {
        this.varDmList = varDmList;
    }

    public List<Erpmusers> getVarErpmUserList() {
        return varErpmUserList;
    }

    public void setVarErpmUserList(List<Erpmusers> varErpmUserList) {
        this.varErpmUserList = varErpmUserList;
    }

    public List<Subinstitutionmaster> getVarSimList() {
        return varSimList;
    }

    public void setVarSimList(List<Subinstitutionmaster> varSimList) {
        this.varSimList = varSimList;
    }

    public List<Institutionmaster> getVarimList() {
        return varimList;
    }

    public void setVarimList(List<Institutionmaster> varimList) {
        this.varimList = varimList;
    }


    @Override
    public String execute() throws Exception {
        try {
                
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in -> RegisterUserAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


        public String AddUser() throws Exception {
        try {
            imIdList = imDao.findAll();
            getSession().setAttribute("isAdministrator", "userRegisterRequest");
            return SUCCESS;
        }
        catch (Exception e) {
            message = "Exception in AddUser method -> ManageUserAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    //This method saves User Account and his/her profile
    public String SaveUser() throws Exception {
        try {
            //If part saves record for the first time; else parts is for record update

            if (erpmusers.getErpmuId() == null) {
                erpmusers.setErpmuActive("N");
                //erpmusers.setErpmuActive("Y");
                erpmusersDao.save(erpmusers);
                erpmur.setErpmurActive('N');
                //erpmur.setErpmurActive('Y');
                erpmur.setErpmusers(erpmusers);
                erpmurDao.save(erpmur);
		message="User Registered Successfully.";
            } else {
                Erpmusers erpmusers2 = erpmusersDao.findByUserName(erpmusers.getErpmuName());
                erpmusers2 = erpmusers;
                erpmusersDao.update(erpmusers2);
            }

            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("UNIQUE_ERPMU_Name")) {
                message = "Sorry, the User Id '" + erpmusers.getErpmuName() + "' is not available. Please try with a different one";
            } else {
                message = "Exception in Save method -> RegisterUserAxn" + e.getMessage() + "Reported Cause is: " + e.getCause();
            }
            return ERROR;
        }
    }

@Override
public void validate() {
        try {
            //Validations on Address part
            if (im.getImName().isEmpty()) {
                addFieldError("im.imName", "Please enter institution name");
            }
            if (im.getImShortName().isEmpty()) {
                addFieldError("im.imShortName", "Please enter institution's short name");
            }
            if (sim.getSimName().isEmpty()) {
                addFieldError("sim.simName", "Please enter College/Faculty/School name");
            }
            if (sim.getSimShortName().isEmpty()) {
                addFieldError("sim.simShortName", "Please enter College/Faculty/School's short name");
            }
            if (dm.getDmName().isEmpty()) {
                addFieldError("dm.dmName", "Please enter Department's name");
            }
            if (dm.getDmShortName().isEmpty()) {
                addFieldError("dm.dmShortName", "Please enter Department's short name");
            }
            if (im.getImAddressLine1().isEmpty()) {
                addFieldError("im.imAddressLine1", "Please enter Address");
            }
            if(im.getImPinNo().isEmpty()) {
                addFieldError("im.imPinNo", "Please enter PIN code");
            }
            if(erpmusers.getErpmuName().isEmpty()) {
                addFieldError("erpmusers.erpmuName", "Please enter User's Name");
            }
            if(erpmusers.getErpmuPassword().isEmpty()) {
                addFieldError("erpmusers.erpmuPassword", "Please enter password");
            }
            if(getRetypedPassword().isEmpty()) {
                addFieldError("RetypedPassword", "Please enter password");
            }
            if(erpmusers.getErpmuFullName().isEmpty()) {
                addFieldError("erpmusers.erpmuFullName", "Please enter user's Full Name");
            }
            if (erpmusers.getErpmuDob().toString().isEmpty()) {
                addFieldError("erpmusers.erpmuDob", "Please enter your date of birth");
            }
            if (erpmusers.getErpmuSecretQuestion().isEmpty()) {
                addFieldError("erpmusers.erpmuSecretQuestion", "Please enter secret question");
            }
            if (erpmusers.getErpmuSecretAnswer().isEmpty()) {
                addFieldError("erpmusers.erpmuSecretAnswer", "Please answer to your secret question");
            }

            varimList = imDao.findInstByIMName(im.getImName());
            if (!varimList.isEmpty()) {
                addFieldError("im.imName", "This Institute is already registard. Please create your user account from the login page.");
            }

            varimList = imDao.findInstByShortName(im.getImShortName());
            if (!varimList.isEmpty()) {
                addFieldError("im.imShortName", "This Institute Short Name already exists. Please change it and save again.");
            }

            varSimList = simDao.findSubInstByName(sim.getSimName());
            if (!varSimList.isEmpty()) {
                addFieldError("sim.simName", "This College/Faculty/School Name is already registard. Please create your user account from the login page.");
            }

            varSimList = simDao.findSubInstByShortName(sim.getSimShortName());
            if (!varSimList.isEmpty()) {
                addFieldError("sim.simShortName", "This College/Faculty/School Short Name already exists. Please change it and save again.");
            }

            varErpmUserList = erpmusersDao.findByErpmUserName(erpmusers.getErpmuName());
            if (!varErpmUserList.isEmpty()) {
                addFieldError("erpmusers.erpmuName", "This User Name is already registard. Please create a different user account or click on Forgot Password from the login page.");
            }

        } catch (NullPointerException npe) {
        }
    }

    public String AdminRegistration() throws Exception {

        try {
            //Prepare Country List
            ctList=cmDao.findAll();

            return SUCCESS;
        } catch (Exception e) {
                message = "Exception in AdminRegistration -> ManageUserAxn" + e.getMessage() + "Reported Cause is: " + e.getCause();
            }
            return ERROR;
        }



    public String SaveAdminRegistration() throws Exception {

        try {
            //Set Institution's Email address as Users Name
            im.setImEmailId(erpmusers.getErpmuName());

            //Initialize SubInstitution Fields
            sim.setInstitutionmaster(im);
            sim.setSimAddressLine1(im.getImAddressLine1());
            sim.setSimAddressLine2(im.getImAddressLine2());
            sim.setSimDistrict(im.getImDistrict());
            sim.setSimPinNo(im.getImPinNo());
            sim.setSimEmailId(erpmusers.getErpmuName());

            //Initialize Department Fields
            dm.setInstitutionmaster(im);
            dm.setSubinstitutionmaster(sim);
            dm.setDmAddressLine1(im.getImAddressLine1());
            dm.setDmAddressLine2(im.getImAddressLine2());
            dm.setDmDistrict(im.getImDistrict());
            dm.setDmPinNo(im.getImPinNo());
            dm.setDmEmailId(erpmusers.getErpmuName());

            //Set User's Status as Active
            erpmusers.setErpmuActive("Y");

            //Initialize ErpmuserRole Fields
            erpmur.setErpmusers(erpmusers);
            erpmur.setInstitutionmaster(im);
            erpmur.setSubinstitutionmaster(sim);
            erpmur.setDepartmentmaster(dm);
            erpmur.setErpmurDefault('Y');
            erpmur.setErpmurActive('Y');


            //Save Institution's record
            imDao.save(im);
            //Save Sub Institution's record
            simDao.save(sim);
            //Save Department's Record
            dmDao.save(dm);
            //Save ErpmUsers Record
            erpmusersDao.save(erpmusers);

            //Add InstitutionUserRole  based on geneeric roles for the institution
            GenericuserrolesDAO gurDao = new GenericuserrolesDAO();
            List <Genericuserroles> gurList = gurDao.findAll();
            int index = 0;
            
            while (!gurList.isEmpty()){
                iur.setInstitutionmaster(im);
                iur.setIurName(gurList.get(index).getGurRoleName());
                iur.setIurRemarks(gurList.get(index).getGurDescription());                
                iurDao.save(iur);
                gurList.remove(index);
            }
          
            //Save Erpmuserrole record
            iur = iurDao.findInstitutionAdministrator(im.getImId());
            erpmur.setInstitutionuserroles(iur);
            erpmurDao.save(erpmur);


            //Add InstitutionPrivileges for Administrator
            AddInstituitionPrivilegesForAdmin();

            message = "Registration successful, Please Login";
            return SUCCESS;
           }
        catch (Exception e) {
           if (e.getCause().toString().contains("Unique_IM_Name"))
               message = "Your institute is already registered, please create your account from the link on the main page";
           else if(e.getCause().toString().contains("Unique_IM_Short_Name"))
               message = "Institution Short Name is already in use, Please choose a different Short Name.";
           else if(e.getCause().toString().contains("Unique_SIM_Name"))
               message = "Your College/Faculty/School is already registered, please create your account from the link on the main page";
           else if(e.getCause().toString().contains("Unique_SIM_Short_Name"))
               message = "SubInstitution Short Name is already in use, Please choose a different Short Name.";
           else
               message = "Error is : " + e.getMessage() + e.getCause();
           ctList=cmDao.findAll();
           return ERROR;
        }
}

    public String Edit() throws Exception {
        try {

            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> ManageUserAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String DeleteUserProfile() throws Exception {
        try {
            ErpmuserroleDAO erpmurDao = new ErpmuserroleDAO();
            erpmur = erpmurDao.findByErpmUserRole(geterpmurId());
            erpmuName = erpmur.getErpmusers().getErpmuName();
            erpmurDao.delete(erpmur);
           imIdList = imDao.findAll();
            simImIdList=simDao.findAll();
            dmIdList=dmDao.findAll();
            iurIdList=irDao.findAll();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in DeleteUserProfileAxn method ManageUserAxn ->  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }



public String SaveUserProfile() throws Exception {
        try {
            erpmusers = erpmusersDao.findByUserName(geterpmuName());
            erpmur.setErpmusers(erpmusers);
            erpmur.setErpmurActive('N');
            ErpmuserroleDAO erpmurDao = new ErpmuserroleDAO();
            erpmurDao.save(erpmur);

            //Prepare Lists for Setting up User Profile
            imIdList = imDao.findAll();
            erpmurList = erpmurDao.findByErpmUserId(erpmusers.getErpmuId());

            return SUCCESS;
        } catch (Exception e) {
            if (e.getCause().toString().contains("UNIQUE_ERPMU_ID_ERPMUR_DM_ID_ERMUR_IUR_ID"))
                message = "This profile is already assigned to you";
            else
                message = "Exception in SaveUserProfile method -> ManageUserAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


       public String ShowUserProfile() throws Exception {
        try {
            ErpmuserroleDAO erpmurDao = new ErpmuserroleDAO();
            erpmurList = erpmurDao.findActiveRolesByErpmUserId(Integer.parseInt(getSession().getAttribute("userid").toString()));
            erpmuName = erpmurList.get(0).getErpmusers().getErpmuFullName() + " (" +erpmurList.get(0).getErpmusers().getErpmuName() + ")";
            message = erpmuName;
            return SUCCESS;
        } catch (Exception e1) {
            if(e1.getCause()!=null){
          if (e1.getCause().toString().contains("UNIQUE_ERPMU_ID_ERPMUR_DM_ID_ERMUR_IUR_ID"))
                message = "This profile is already assigned to you"+e1.getCause();
            }
          else
              message = "Exception in ShowUserProfile method -> ManageUserAxn " + e1.getMessage() + " Reported Cause is: " + e1.getCause();
           return ERROR;
        }
    }

        public String ChooseUserProfile() throws Exception {
        try {
            ErpmuserroleDAO erpmurDao = new ErpmuserroleDAO();
            erpmur = erpmurDao.findByErpmUserRole(geterpmurId());

            getSession().setAttribute("imId", erpmur.getInstitutionmaster().getImId());
            getSession().setAttribute("imshortname", erpmur.getInstitutionmaster().getImShortName());
            getSession().setAttribute("simshortname",erpmur.getSubinstitutionmaster().getSimShortName());
            getSession().setAttribute("dmshortname", erpmur.getDepartmentmaster().getDmShortName());
           return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ChooseUserProfile method -> ManageUserAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String SetDefaultProfile() throws Exception {
        try {

            ErpmuserroleDAO erpmurDao = new ErpmuserroleDAO();
            erpmur = erpmurDao.findByErpmUserRole(geterpmurId());
            erpmur.setErpmurDefault('t');
            erpmurDao.ClearDefaultProfile(erpmur.getErpmusers().getErpmuId());
            erpmurDao.update(erpmur);

            getSession().setAttribute("imId", erpmur.getInstitutionmaster().getImId());
            getSession().setAttribute("imshortname", erpmur.getInstitutionmaster().getImShortName());
            getSession().setAttribute("simshortname",erpmur.getSubinstitutionmaster().getSimShortName());
            getSession().setAttribute("dmshortname", erpmur.getDepartmentmaster().getDmShortName());

            erpmurList = erpmurDao.findAllInactiveUsers();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in SetDefaultProfile method ManageUserAction ->  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String AuthorizeUsers() throws Exception {
        try{
            ErpmuserroleDAO erpmurDao = new ErpmuserroleDAO();
            erpmurList = erpmurDao.findAllInactiveUsers();
        return SUCCESS;
        }
        catch (Exception e) {
            message = "Exception in AuthorizeUsers, method -> ManageUserAction " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String ApproveUserProfile() throws Exception {
        try {

            
            ErpmuserroleDAO erpmurDao = new ErpmuserroleDAO();
            erpmur = erpmurDao.findByErpmUserRole(geterpmurId());
            erpmur.setErpmurActive('Y');
            erpmurDao.update(erpmur);
            Integer iurId=erpmur.getInstitutionuserroles().getIurId();
            InstitutionuserroleDAO iurDao=new InstitutionuserroleDAO() ;
            Institutionuserroles iur =iurDao.findByIURId(iurId);
            // message=erpmur.getErpmusers().getErpmuName()+ erpmur.getErpmusers().getErpmuPassword()+erpmur.getErpmusers().getErpmuFullName()+iur.getIurName()+iur.getInstitutionmaster().getImName()+erpmur.getSubinstitutionmaster().getSimName()+erpmur.getDepartmentmaster().getDmName()+"hellooo" ;
            sendmailToapprovedusers(erpmur.getErpmusers().getErpmuFullName(),erpmur.getErpmusers().getErpmuName(),erpmur.getErpmusers().getErpmuPassword(),iur.getIurName(),iur.getInstitutionmaster().getImName(),erpmur.getSubinstitutionmaster().getSimName(),erpmur.getDepartmentmaster().getDmName());
            erpmurList = erpmurDao.findAllInactiveUsers();
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in ApproveUserProfile method ManageUserAction ->  " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

   public String  sendmailToapprovedusers(String  fullname,String username, String password,String role,String institution,String subinstitution,String department) throws Exception {
        try {

            ErpmuserroleDAO erpmurDao = new ErpmuserroleDAO();
            erpmur = erpmurDao.findByErpmUserRole(geterpmurId());

            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
            String toEmailAddress = username;
            String emailSubject = "Profile activation for PICO Module";
            String emailMessage = "<html><head><title>Your request has been approved</title></head><body><table width='500' border='0' align='left' cellpadding='15' cellspacing='0' style='font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12pt; color:#5a5a5a;'><tr><td align='left'><p>Dear " +  fullname+ ",</p></td></tr><tr><td align='left'><p >Your profile activation request with the following details has been approved by the PICO Adminstrator:</p><br/><p>Username: " + username+ "<br /><br/>Password: " + password + "<br /><br/>Role: " + role + "<br /><br/>Institution: " + institution + "<br /><br/>Sub Institution: " + subinstitution+ "<br /><br/>Department: " + department + "<br /></p><br/><p>Thank you for using this site.<br /></p><br/><br/><p>Regards,<br />Administrator, PICO Module<br /></p><p><br /><br />THIS IS AN AUTOMATED MESSAGE; PLEASE DO NOT REPLY. </p></td></tr></table></body></html>";
            sendMail.sendMail(bundle.getString("emailFrom"), bundle.getString("emailUser"), bundle.getString("emailFromPasswd"), toEmailAddress, "", emailSubject, emailMessage);


            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in sendmailToapprovedusers method -> ManageUserAction " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }



    // Method to recover the Password


public String RecoverPassword() throws Exception {

    try {
           //Check if user has filled in all required values
            if (erpmusers.getErpmuSecretQuestion().contains("Invalid user name or date of birth") ||
                erpmusers.getErpmuSecretQuestion().contains("Please provide values for the above fields")) {
                message = "Please provide correct values for user name and date of birth";
                return INPUT;
            }
            else
            {
                erpmusersDao = new ErpmusersDAO();
                Erpmusers erpmu = erpmusersDao.FindByUserNameSecretAnswer(erpmusers.getErpmuName(), erpmusers.getErpmuSecretAnswer());
                if (erpmu == null) {
                    message = "Incorrect Answer. Try again";
                    erpmusers = null;
                    return INPUT;
                }
                else
                {
                Locale locale = ActionContext.getContext().getLocale();
                ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
                if(!bundle.getString("emailFrom").equals("") && !bundle.getString("emailUser").equals("") && !bundle.getString("emailFromPasswd").equals("")) {
                    String toEmailAddress = erpmu.getErpmuName();
                    String emailSubject = "Password for PICO Module";
                    String emailMessage = "<html><head><title>Your Password Details</title></head><body><table width='500' border='0' align='center' cellpadding='15' cellspacing='0' style='font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12pt; color:#5a5a5a;'><tr><td align='left'><p>Dear " + erpmu.getErpmuFullName() + ",</p></td></tr><tr><td align='left'><p>Your password is:</p><br/><br/><p>Password: " + erpmu.getErpmuPassword() + "<br /></p><br/><p>Thank you for using this site.<br /></p><br/><br/><p>Regards,<br />Administrator, PICO Module<br /></p><p><br /><br />THIS IS AN AUTOMATED MESSAGE; PLEASE DO NOT REPLY. </p></td></tr></table></body></html>";
                    sendMail.sendMail(bundle.getString("emailFrom"), bundle.getString("emailUser"), bundle.getString("emailFromPasswd"), toEmailAddress, "", emailSubject, emailMessage);
                   // message = "An email containing your password has been sent to you";
                 message = "An email containing your password has been sent to you";
                }
                return SUCCESS;
                }
            }
        }
        catch (Exception e) {
            if (e.getCause().toString().contains("UnknownHostException"))
                message = "Unable to contact Mail Server. Please check your Internet connectivity and try again.";
            else
                message = "Exception in RecoverPassword method -> ManageUserAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }



     public String AddProfile() throws Exception {
        try{

            erpmuName=getSession().getAttribute("username").toString();
            imIdList = imDao.findAll();
            simImIdList=simDao.findAll();
            dmIdList=dmDao.findAll();
            iurIdList=irDao.findAll();
        return SUCCESS;
        }
        catch (Exception e) {
            message = "Exception in AddProfile method -> ManageUserAxn " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String AddInstituitionPrivilegesForAdmin() throws Exception {
    try{

        List<Genericroleprivileges> grpList = new ArrayList<Genericroleprivileges>();
        GenericroleprivilegesDAO grpDao = new GenericroleprivilegesDAO();

        Institutionroleprivileges iurp = new Institutionroleprivileges();
        InstitutionroleprivilegesDAO iurpDao = new InstitutionroleprivilegesDAO();

        //Find ID for "Administrator" from the GenericUserRole Table
        GenericuserrolesDAO gurDao = new GenericuserrolesDAO();
        Byte roleId = gurDao.RetrieveRoleId("Administrator");

        //For the RoleID retrieved above; Prepare list of generic privileges
        grpList = grpDao.RetrievePrivilegesForGenericRole(roleId);

        //Go through the list and copy privileges to the "InstitutionRolePrivileges" table
        int index = 0;

            while (!grpList.isEmpty()){
                iurp.setErpmmodule(grpList.get(index).getErpmmodule());
                iurp.setErpmprogram(grpList.get(index).getErpmprogram());
                iurp.setErpmsubmodule(grpList.get(index).getErpmsubmodule());
                iurp.setInstitutionmaster(im);
                iurp.setIupCanAdd(grpList.get(index).getGupCanAdd().contentEquals("0"));
                iurp.setIupCanDelete(grpList.get(index).getGupCanDelete().contentEquals("0"));
                iurp.setIupCanEdit(grpList.get(index).getGupCanEdit().contentEquals("0"));
                iurp.setIupCanView(grpList.get(index).getGupCanView().contentEquals("0"));
                iurp.setInstitutionuserroles(iur);

                /*message = "Module Id " +
                grpList.get(index).getErpmmodule().getErpmmName() + " Program " +
                grpList.get(index).getErpmprogram().getErpmpId() + " Sub Module " +
                grpList.get(index).getErpmsubmodule().getErpmSubModuleId() + " Inst " +
                im.getImName() + "Can Add " +
                grpList.get(index).getGupCanAdd().contentEquals("0") + " Can Delete " +
                grpList.get(index).getGupCanDelete().contentEquals("0") + " Can Edit " +
                grpList.get(index).getGupCanEdit().contentEquals("0") + " Can View " +
                grpList.get(index).getGupCanView().contentEquals("0") + " User Role " +
                iur.getIurName();
*/

                iurpDao.save(iurp);
                grpList.remove(index);
            }



        return SUCCESS;
    }
    catch (Exception e) {
         message = "Exception in ManageUserAxn method -> AddInstituitionPrivilegesForAdmin " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

public String ViewRegisteredInstitutions() throws Exception {
    try{
        //Prepare list of Institutions
        imIdList = imDao.findAll();        
        
        return SUCCESS;
    }
    catch (Exception e) {
         message = "Exception in ManageUserAxn method -> ViewRegisteredInstitutions " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

public String PrepareMessageForInstitutionAdmin() throws Exception {
    try{
        //Prepare list of Institutions
                
        im = imDao.findByImId(getimId());
        emailTo = im.getImEmailId();

        return SUCCESS;
    }
    catch (Exception e) {
         message = "Exception in ManageUserAxn method -> SendMessageToInstitutionAdmin " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

public String SendMessageToInstitutionAdmin() throws Exception {
    try{
        //Prepare list of Institutions

            Locale locale = ActionContext.getContext().getLocale();
            ResourceBundle bundle = ResourceBundle.getBundle("pico", locale);
		
            String emailSubject = getsubject();
		message="sbjct in SendMessageToInstitutionAdmin===="+emailSubject;
            String emailMessage = "<html><head><title>The message and other details are as follows:</title></head><body><table width='500' border='0' align='left' cellpadding='15' cellspacing='0' style='font-family:Verdana, Arial, Helvetica, sans-serif; font-size:10pt; color:#5a5a5a;'><tr><td align='left'><p>" +  message + "</p></td></tr><tr><td align='left'>User's Emails Id: " + getsenderEmailId() + "<br/>User's Contact No: " + getsenderContactNo() + "<br/><br/>THIS IS AN AUTOMATED MESSAGE; PLEASE DO NOT REPLY.</td></tr></table></body></html>";
            sendMail.sendMail(bundle.getString("emailFrom"), bundle.getString("emailUser"), bundle.getString("emailFromPasswd"),getemailTo(),"", emailSubject, emailMessage);

            message = "Message sent to the Institution Admin";
            
            return SUCCESS;
    }
    catch (Exception e) {
         message = "Exception in ManageUserAxn method -> SendMessageToInstitutionAdmin " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

    public String CurrentProfile() throws Exception {

    try {
            ErpmuserroleDAO erpmurDao = new ErpmuserroleDAO();
            erpmurList = erpmurDao.findActiveRolesByErpmUserId(Integer.parseInt(getSession().getAttribute("userid").toString()));

            return SUCCESS;
    }
    catch (Exception e) {
         message = "Exception in CurrentProfileAxn method " + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

}
