/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Administration;

/**
 *
 * @author kazim
 */

import java.io.*;
import java.sql.Connection;
import org.apache.struts2.ServletActionContext;
import java.sql.DriverManager;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import net.sf.jasperreports.engine.*;
import org.apache.struts2.interceptor.validation.SkipValidation;

import pojo.hibernate.Institutionmaster;
import pojo.hibernate.InstitutionmasterDAO;
import pojo.hibernate.ErpmGenMaster;
import pojo.hibernate.ErpmGenMasterDao;
import pojo.hibernate.Statemaster;
import pojo.hibernate.StatemasterDAO;
import pojo.hibernate.Countrymaster;
import utils.DevelopmentSupport;
import pojo.hibernate.CountrymasterDAO;
import pojo.hibernate.Employeemaster;
import pojo.hibernate.EmployeemasterDAO;

public class InstitutionAction extends DevelopmentSupport{

    private Institutionmaster im;
    private Short ImId;
    private List<Institutionmaster> imList = new ArrayList<Institutionmaster>();
    private InstitutionmasterDAO imDao = new InstitutionmasterDAO();

    private EmployeemasterDAO empDao = new EmployeemasterDAO();
    private List<Statemaster> statemasterList = new ArrayList<Statemaster>();
    private List<ErpmGenMaster> institutiontypeList = new ArrayList<ErpmGenMaster>();
    private List<Countrymaster> ctList = new ArrayList<Countrymaster>();
    private CountrymasterDAO cmDao = new CountrymasterDAO();
    private List<Employeemaster> empList = new ArrayList<Employeemaster>();
    private String message;
    private String fileForExport;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getMessage() {
        return message;
    }

    public String getfileForExport() {
        return fileForExport;
    }


    public Short getImId() {
        return ImId;
    }

    public void setImId(Short ImId) {
        this.ImId = ImId;
    }

    public Institutionmaster getim() {
        return im;
    }
    public void setim(Institutionmaster im) {
        this.im = im;
    }

    public List<Institutionmaster> getimList() {
        return imList;
    }

    public void setbtmList(List<Institutionmaster> imList) {
        this.imList = imList;
    }

   public List<ErpmGenMaster> getInstitutiontypeList() {
        return institutiontypeList;
    }
    public void setInstitutiontypeList(List<ErpmGenMaster> institutiontypeList) {
        this.institutiontypeList = institutiontypeList;
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
public void setempList(List<Employeemaster> empList) {
        this.empList = empList;
    }

    public List<Employeemaster> getempList() {
        return this.empList;
    }


    @Override

      public String execute() throws Exception {
        try {
            prepare_lovs();
            return SUCCESS;
            }
       catch (Exception e)
       {
           message = "Exception in Browse method -> InstitutionAxn" + e.getMessage();
           return ERROR;
       }
       }

@SkipValidation
public String Browse() throws Exception {
    try {
        imList = imDao.findAll();

        return SUCCESS;
     }
        catch (Exception e)
            {
             message = "Exception in Browse method -> InstitutionAxn" + e.getMessage();
             return ERROR;
            }
        }

public String Edit() throws Exception {
        try {
            //Prepate Institution Type List
            ErpmGenMasterDao erpmgmDao = new ErpmGenMasterDao();
            institutiontypeList = erpmgmDao.findByErpmGmType(Short.parseShort("4"));

            //Prepare Employee List
            empList=empDao.findByImId(getImId());

            //Prepate Country List
            ctList=cmDao.findAll();

            //Prepate State List
            StatemasterDAO statemasterDao = new StatemasterDAO();
            statemasterList = statemasterDao.findAll();

            //Get the record having specified Institution ID
            im = imDao.findByImId(getImId());
            return SUCCESS;
        } catch (Exception e) {
            message = "Exception in Edit method -> InstitutionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }

public String Delete() {
    im = imDao.findByImId(getImId());
    imDao.delete(im);
    imList = imDao.findAll();
    return SUCCESS;
    }


public String Save() throws Exception {
        try {
            //If Institution Master ID is null
            if (im.getImId() == null) {
                //Check if Employee Is is Null
                if (im.getEmployeemaster().getEmpId() == 0)
                    im.setEmployeemaster(null);

                // Then save record as a new entry
                imDao.save(im);
                message = "Institution record saved successfully. Institution Id is " + im.getImId();
            } else {
            // Else update an existing record
                //Check if Employee Is is Null
                if (im.getEmployeemaster().getEmpId() == 0)
                    im.setEmployeemaster(null);
                Institutionmaster im2 = imDao.findByImId(im.getImId());
                im2 = im;
                imDao.update(im2);
                message = "Record saved successfully.";
            }
            imList = imDao.findAll();
            return SUCCESS;
        } catch (Exception e) {
            // On error i.e. on voilation of constraints of unique IM Name / Short Name etc display error message
            if (e.getCause().toString().contains("Unique_IM_Name"))
                message = "Institution with the name '" + im.getImName() + "' already exists";
            else if (e.getCause().toString().contains("Unique_IM_Short_Name"))
                message = "Institution with the short name '" + im.getImShortName() + "' already exists";
            else
                message = "Exception in Save method -> InstitutionAxn" + e.getMessage() + " Reported Cause is: " + e.getCause();
            return ERROR;
        }
    }


      public String Clear() throws Exception {
        try {
            im = null;

            //Prepate Country List
            CountrymasterDAO cnDAO=new CountrymasterDAO();
            ctList= cnDAO.findAll();

            //Prepate State List
            StatemasterDAO stateDao = new StatemasterDAO();
            statemasterList = stateDao.findAll();

            //Prepate Institution Type List
            ErpmGenMasterDao erpmgmDao = new ErpmGenMasterDao();
            institutiontypeList = erpmgmDao.findByErpmGmType(Short.parseShort("4"));

            //Prepate Employee List for Institution Head
            EmployeemasterDAO  empDao =new EmployeemasterDAO();
            empList = empDao.findAll();
            return SUCCESS;
            }
       catch (Exception e)
       {
           message = "Exception in Clear method -> InstitutionAxn" + e.getMessage();
           return ERROR;
       }
       }


       public void validate() {
        try {
                if(im.getImId() == null) {
                    if(im.getImName().isEmpty())
                        addFieldError("im.ImName" ,"Please give Institution's name");
                    if(im.getErpmGenMaster().getErpmgmEgmId() == null)
                        addFieldError("im.erpmGenMaster.erpmgmEgmId" ,"Please set Institution's type");
                    if(im.getImShortName().isEmpty())
                        addFieldError("im.ImShortName" ,"Please give Institution's Short Name");
                    if(im.getImAddressLine1().isEmpty())
                        addFieldError("im.ImAddressLine1" ,"Please give Institution's Address");
                    if(im.getCountrymaster().getCountryId() == null)
                        addFieldError("im.countrymaster.countryId" ,"Please select country");
                    if(im.getStatemaster().getStateId() == null)
                        addFieldError("im.statemaster.stateId" ,"Please select state");

                    //Prepare LOVs
                    prepare_lovs();
            }

           }
               catch (NullPointerException e) {}
    }


@SkipValidation
public String Print() throws Exception {
    HashMap hm = new HashMap();

    String fileName = getSession().getServletContext().getRealPath("pico\\Administration\\Reports\\Institution.jasper");


   String whereCondition;

    try{
         Connection conn =     DriverManager.getConnection("jdbc:mysql://localhost:3306/pico_basic", "root","root");

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Content-Disposition","attachment; filename=Institutions.pdf");
        response.setHeader("Expires" , "0");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();


        //Setup Where Condition Clause
        if(im.getImName().toUpperCase().isEmpty())
            whereCondition = " and upper(im.im_name) like '%'";
        else
            whereCondition = " and upper(im.im_name) like '" + im.getImName().toUpperCase() + "'";

        if (im.getErpmGenMaster().getErpmgmEgmId() == null)
            whereCondition = whereCondition + " and im.im_type <> 0 ";
        else
            whereCondition = whereCondition + " and im.im_type = " + im.getErpmGenMaster().getErpmgmEgmId();

        if(im.getStatemaster().getStateId() == null)
            whereCondition = whereCondition + " and im.im_state_id <> 0 ";
        else
            whereCondition = whereCondition + " and im.im_state_id = " + im.getStatemaster().getStateId();

        hm.put("condition", whereCondition);

        JasperPrint jp = JasperFillManager.fillReport(fileName, hm, conn);
        JasperExportManager.exportReportToPdfStream(jp,baos);
        response.setContentLength(baos.size());
        ByteArrayInputStream bis=new ByteArrayInputStream(baos.toByteArray());
        inputStream = bis;

        return SUCCESS;
    }
    catch (JRException  e)
    {
        message = "Error is : " + e.getMessage() + e.getCause();
        return ERROR;
    }
    }

private void prepare_lovs() {

            //Prepare Country List
            ctList=cmDao.findAll();
            //Prepare State List
            StatemasterDAO stateDao = new StatemasterDAO();
            statemasterList = stateDao.findAll();

            //Prepare Institution Type List
            ErpmGenMasterDao erpmgmDao = new ErpmGenMasterDao();
            institutiontypeList = erpmgmDao.findByErpmGmType(Short.parseShort("4"));
            String IM_SN = getSession().getAttribute("imshortname").toString();
            Institutionmaster im3=imDao.findInstByIMShortName(IM_SN);
            //Prepare Employee List for Institution Head
            empList=empDao.findByImId(im3.getImId());

}
    /*String jrprintFileName = "C:\\PICO\\PICO_Sir_IGNOU_Bkup\\pico24october2011\\web\\pico\\Administration\\Reports\\Institution.jrprint";
    String outFileName = "C:\\PICO\\PICO_Sir_IGNOU_Bkup\\pico24october2011\\web\\pico\\Administration\\Reports\\kazim.pdf"; // + getSession().getAttribute("username")+".pdf";
    //String fileName = ".\\Institution.jasper";


    HashMap hm = new HashMap();
    try{
    //Gettign the connection object


    //JasperFillManager.fillReportToFile("C:\\Users\\sknaqvi\\pico28october2011\\pico24october2011\\web\\pico\\Administration\\Reports\\Institution.jasper", hm, conn);
    //JasperExportManager.exportReportToPdfFile("C:\\Users\\sknaqvi\\pico28october2011\\pico24october2011\\web\\pico\\Administration\\Reports\\Institution.jrprint");


    JasperFillManager.fillReportToFile(fileName, null, conn);
    JRPdfExporter exporter = new JRPdfExporter();

    exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, jrprintFileName);
    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
    //exporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);

    exporter.exportReport();


    /////////////////////////////////////////////////
    // This Part Prints report

    long start = System.currentTimeMillis();
    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
    printRequestAttributeSet.add(MediaSizeName.ISO_A4);

    PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();

   // printServiceAttributeSet.add(new PrinterName("Epson Stylus 820 ESC/P 2", null));
   // printServiceAttributeSet.add(new PrinterName("hp LaserJet 1320 PCL 6", null));
   printServiceAttributeSet.add(new PrinterName("PDFCreator", null));


   JRPrintServiceExporter prnexporter = new JRPrintServiceExporter();

    prnexporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, "C:\\PICO\\PICO_Sir_IGNOU_Bkup\\pico24october2011\\web\\pico\\Administration\\Reports\\Institution.jrprint");//"build/reports/PrintServiceReport.jrprint");
    prnexporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
    prnexporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printServiceAttributeSet);
    prnexporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
    prnexporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.TRUE);

    prnexporter.exportReport();

    System.err.println("Printing time : " + (System.currentTimeMillis() - start));
     */

//fileForExport = outFileName;
/*
public String Print() throws Exception
    {

HttpServletResponse response;
    //       response.setContentType("application/pdf");
            response.setContentType("text/html");
        PrintWriter out = response.getWriter( );
        out.write("<html><head></head><body>Hello World!</body></html>");
        return null;

    httpResponse.setContentType("application/pdf");
httpResponse.setHeader("Content-disposition", "attachment; filename=MyFile.pdf");
ServletOutputStream out = httpResponse.getOutputStream();

//STEP 1: COMPILE REPORT (OPTIONAL ... U CAN USE DIRECTLY .jasper FILE AND SKIP THIS STEP)
JasperReport jasperReport = ....
....
....

//STEP 2: FILL REPORT
JasperPrint jasperPrint = ....
....
....

//STEP 3: EXPORT REPORT AND PUT INTO SERVLETOUTPUTSTREAM

JRAbstractExporter exporter = JRPdfExporter();
Map<JRExporterParameter, Object> parameterExport = new HashMap<JRExporterParameter, Object>();
parameterExport.put((JRExporterParameter.JASPER_PRINT, jasperPrint);
parameterExport.put((JRPdfExporterParameter.OUTPUT_STREAM,out);
exporter.setParameters(parameterExport):
exporter.exportReport();

out.flush();}*/
}
