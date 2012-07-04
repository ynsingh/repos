import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import java.text.SimpleDateFormat
import java.text.*;
import java.util.*;
import groovy.sql.Sql
class StaffDetailsController {

    def dataSource
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        GrailsHttpSession gh = getSession()
        //gh.removeValue("InstId")
        gh.removeValue("FacultyId")
        gh.removeValue("DeptId")
        redirect(controller: "staffDetails", action: "teachingAndNonteaching")
        }
        
    def teachingAndNonteaching = {
       }
       
    def firstTab = {
        GrailsHttpSession gh = getSession()
        //gh.removeValue("InstId")
        gh.removeValue("FacultyId")
        gh.removeValue("DeptId")
        redirect(controller: "staffDetails", action: "teachingStaff")
        }
     
    def teachingStaff = {
       GrailsHttpSession gh = getSession()
       def staffDetailsInstance = new StaffDetails()
       def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
       def institutionDetailsInstance = InstitutionDetails.find("from InstitutionDetails ID where ID.id = "+gh.getValue("InstId"))
       def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+gh.getValue("InstId"))
       def facultyInstance = Faculty.find("from Faculty F where F.id = "+gh.getValue("FacultyId"))
       def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+gh.getValue("FacultyId"))
       def designationList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='DESIGNATION_TS'")
       def selectionModeList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='SELECTION_MODE'")
       def categoryList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='CATEGORY'")
       def departmentInstance = Department.find("from Department D where D.id = "+gh.getValue("DeptId"))
       def staffDetailsInstanceList
       if(departmentInstance){
       staffDetailsInstanceList = StaffDetails.findAll("from StaffDetails S where S.tsOrNts = 'TS' and S.department ='"+departmentInstance.deptName+"'")
       staffDetailsInstance.department = departmentInstance.deptName
       }
       return [institutionDetailsInstanceList: institutionDetailsInstanceList, institutionDetailsInstance: institutionDetailsInstance,      
                  facultyInstanceList: facultyInstanceList, facultyInstance: facultyInstance,
                  departmentInstanceList: departmentInstanceList, designationList: designationList,
                  selectionModeList: selectionModeList, categoryList: categoryList,
                  staffDetailsInstanceList: staffDetailsInstanceList,departmentInstance: departmentInstance,
                  staffDetailsInstance: staffDetailsInstance]
       }
      
   def facultyNameList = {
        GrailsHttpSession gh = getSession()
        def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.facultyNameList )
        gh.putValue("InstId","${institutionDetailsInstance.id}")
        def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+params.facultyNameList)
        render (template:"listFacultyName", model : ['facultyInstanceList' : facultyInstanceList])
       }   
       
   def departmentNameList = {
       GrailsHttpSession gh = getSession()
       def facultyInstance =Faculty.find("from Faculty F where F.id="+params.departmentNameList )
	   gh.putValue("FacultyId","${facultyInstance.id}")
       def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+params.departmentNameList)
       render (template:"listDepartmentName", model : ['departmentInstanceList' : departmentInstanceList])
       }
    
    def staffDetailsList ={
       GrailsHttpSession gh = getSession()
	   def departmentInstance =Department.find("from Department D where D.deptName='"+params.staffDetailsList+"'")
	   gh.putValue("DeptId","${departmentInstance.id}")
	   def staffDetailsInstance = new StaffDetails()
	   staffDetailsInstance.properties = params
	   def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
	   def facultyInstance = Faculty.find("from Faculty F where F.id ="+gh.getValue("FacultyId"))
	   def staffDetailsInstanceList = StaffDetails.findAll("from StaffDetails S where S.tsOrNts = 'TS' and S.department ='"+params.staffDetailsList+"'")
	   render (template:"listStaffDetails", model : ['staffDetailsInstanceList' : staffDetailsInstanceList,'staffDetailsInstance':staffDetailsInstance, 'institutionDetailsInstance':institutionDetailsInstance, 'facultyInstance':facultyInstance])
       }
       
       
    def saveTeachingStaff = {
       GrailsHttpSession gh = getSession()
       def staffDetailsInstance = new StaffDetails()
       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.InstId )
       def facultyInstance = Faculty.find("from Faculty F where F.id="+params.faculty )
       def departmentInstance = Department.find("from Department D where D.deptName='"+params.department+"'" )
      
       //gh.putValue("InstId","${institutionDetailsInstance.id}")
       gh.putValue("FacultyId","${facultyInstance.id}")
       
       def designationInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.designation )
       def selectionModeInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.selectionMode )
       def categoryInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.category )
       
        if(designationInstance){staffDetailsInstance.designation=designationInstance.item;}
        else{staffDetailsInstance.designation="";}
        if(selectionModeInstance){staffDetailsInstance.selectionMode=selectionModeInstance.item;}
        else{staffDetailsInstance.selectionMode="";}
        if(categoryInstance){staffDetailsInstance.category=categoryInstance.item;}
        else{staffDetailsInstance.category="";}
		      
       staffDetailsInstance.tsOrNts="TS";
       staffDetailsInstance.ntsType="";
       staffDetailsInstance.institutionDetails=institutionDetailsInstance;
       staffDetailsInstance.department=params.department;
	   staffDetailsInstance.gradePay=params.gradePay;
	   staffDetailsInstance.sanctionedStrength=params.sanctionedStrength;
	   staffDetailsInstance.tsOrNtsTotal=params.tsOrNtsTotal;
	   staffDetailsInstance.tsOrNtsFemale=params.tsOrNtsFemale;
	   staffDetailsInstance.pwdReservation=params.pwdReservation;
	   staffDetailsInstance.tsOrNtsPwdTotal=params.tsOrNtsPwdTotal;
	   staffDetailsInstance.tsOrNtsPwdFemale=params.tsOrNtsPwdFemale;
	   staffDetailsInstance.tsOrNtsMusTotal=params.tsOrNtsMusTotal;
	   staffDetailsInstance.tsOrNtsMusFemale=params.tsOrNtsMusFemale;
	   staffDetailsInstance.tsOrNtsOthMinTotal=params.tsOrNtsOthMinTotal;
	   staffDetailsInstance.tsOrNtsOthMinFemale=params.tsOrNtsOthMinFemale;
	   if (staffDetailsInstance.save(flush: true)) {
		                   redirect(controller: "staffDetails", action: "teachingStaff")
		       }
		       else {
			                render(view:'teachingStaff',model:[staffDetailsInstance: staffDetailsInstance])
			             }
       }  
      
      
   def editTeachingStaff = {
       GrailsHttpSession gh = getSession()
       def staffDetailsInstance = StaffDetails.get(params.id)
       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
       def facultyInstance = Faculty.find("from Faculty F where F.id="+gh.getValue("FacultyId") )
       def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
       def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+gh.getValue("InstId"))
	   def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+gh.getValue("FacultyId"))
       def designationList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='DESIGNATION_TS'")
       def selectionModeList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='SELECTION_MODE'")
       def categoryList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='CATEGORY'")
       def designationInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+staffDetailsInstance.designation+"'")
	   def selectionModeInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+staffDetailsInstance.selectionMode+"'")
	   def categoryInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+staffDetailsInstance.category+"'")
	   return [staffDetailsInstance: staffDetailsInstance, institutionDetailsInstance: institutionDetailsInstance,
	               facultyInstance: facultyInstance, institutionDetailsInstanceList: institutionDetailsInstanceList,
	               facultyInstanceList: facultyInstanceList, departmentInstanceList: departmentInstanceList,
	               designationList: designationList, selectionModeList: selectionModeList,
	               categoryList: categoryList, designationInstance: designationInstance,
	               selectionModeInstance: selectionModeInstance, categoryInstance: categoryInstance]
	   }

   def updateTeachingStaff = {
           GrailsHttpSession gh = getSession()
	       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
	       def staffDetailsInstance = StaffDetails.get(params.id)
	       def departmentInstance = Department.find("from Department D where D.deptName='"+params.department+"'" )
	       def designationInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.designation )
           def selectionModeInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.selectionMode )
           def categoryInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.category )
	       if (staffDetailsInstance) {
		            if (params.version) {
		                def version = params.version.toLong()
		                if (staffDetailsInstance.version > version) {
		                    staffDetailsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'StaffDetails.label', default: 'StaffDetails')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
		                    render(view: "editTeachingStaff", model: [staffDetailsInstance: staffDetailsInstance])
		                }
		            }
		     		      
	      if(designationInstance){staffDetailsInstance.designation=designationInstance.item;}
          else{staffDetailsInstance.designation="";}
          if(selectionModeInstance){staffDetailsInstance.selectionMode=selectionModeInstance.item;}
          else{staffDetailsInstance.selectionMode="";}
          if(categoryInstance){staffDetailsInstance.category=categoryInstance.item;}
          else{staffDetailsInstance.category="";}
	      
	      staffDetailsInstance.tsOrNts="TS";
	      staffDetailsInstance.ntsType="";
	      staffDetailsInstance.institutionDetails=institutionDetailsInstance;
	      staffDetailsInstance.department=params.department;
		  staffDetailsInstance.gradePay=params.gradePay;
		  staffDetailsInstance.sanctionedStrength=params.sanctionedStrength;
		  staffDetailsInstance.tsOrNtsTotal=params.tsOrNtsTotal;
		  staffDetailsInstance.tsOrNtsFemale=params.tsOrNtsFemale;
		  staffDetailsInstance.pwdReservation=params.pwdReservation;
		  staffDetailsInstance.tsOrNtsPwdTotal=params.tsOrNtsPwdTotal;
	      staffDetailsInstance.tsOrNtsPwdFemale=params.tsOrNtsPwdFemale;
		  staffDetailsInstance.tsOrNtsMusTotal=params.tsOrNtsMusTotal;
		  staffDetailsInstance.tsOrNtsMusFemale=params.tsOrNtsMusFemale;
		  staffDetailsInstance.tsOrNtsOthMinTotal=params.tsOrNtsOthMinTotal;
		  staffDetailsInstance.tsOrNtsOthMinFemale=params.tsOrNtsOthMinFemale;
	                if (staffDetailsInstance.save(flush: true)) {
	                flash.message = "${message(code: 'Updated', args: [message(code: 'StaffDetails.label', default: 'StaffDetails'), staffDetailsInstance.id])}"
		                redirect(controller: "staffDetails", action: "teachingStaff", id: staffDetailsInstance.id)
		            }
		            else {
		                render(view: "editTeachingStaff", model: [staffDetailsInstance: staffDetailsInstance])
		            }
		        }
		        else {
		            flash.message = "${message(code: 'Not found', args: [message(code: 'StaffDetails.label', default: 'StaffDetails'), params.id])}"
		             redirect(controller: "staffDetails", action: "teachingStaff")
		        }
       }
   
   def deleteTeachingStaff = {
         def staffDetailsInstance = StaffDetails.get(params.id)
			        if (staffDetailsInstance) {
			            try {
			                staffDetailsInstance.delete(flush: true)
			                flash.message = "${message(code: 'Deleted', args: [message(code: 'StaffDetails.label', default: 'StaffDetails'), params.id])}"
			               redirect(controller: "staffDetails", action: "teachingStaff")
			            }
			            catch (org.springframework.dao.DataIntegrityViolationException e) {
			                flash.message = "${message(code: 'Not deleted', args: [message(code: 'StaffDetails.label', default: 'StaffDetails'), params.id])}"
			                redirect(controller: "staffDetails", action: "editTeachingStaff", id: params.id)
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'StaffDetails.label', default: 'StaffDetails'), params.id])}"
			            redirect(controller: "staffDetails", action: "teachingStaff")
			        }  
       }
   
   def secondTab = {
       GrailsHttpSession gh = getSession()
       //gh.removeValue("InstId")
       redirect(controller: "staffDetails", action: "nonTeachingStaff")
       }
       
       
   def nonTeachingStaff = {
       GrailsHttpSession gh = getSession()
       def staffDetailsInstance = new StaffDetails()
       def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
       def institutionDetailsInstance = InstitutionDetails.find("from InstitutionDetails ID where ID.id = "+gh.getValue("InstId"))
       def ntsTypeList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='NTS_TYPE'")
       def designationList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='DESIGNATION_NTS'")
       def categoryList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='CATEGORY'")
       def staffDetailsInstanceList = StaffDetails.findAll("from StaffDetails S where S.tsOrNts = 'NTS' and S.institutionDetails = "+gh.getValue("InstId"))
       return [staffDetailsInstance: staffDetailsInstance, staffDetailsInstanceList: staffDetailsInstanceList,
                   institutionDetailsInstance: institutionDetailsInstance, institutionDetailsInstanceList: institutionDetailsInstanceList,
                   ntsTypeList: ntsTypeList, designationList: designationList, categoryList: categoryList]
       }
      
      
   def ntsDetailsList = {
       GrailsHttpSession gh = getSession()
       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.ntsDetailsList )
       gh.putValue("InstId","${institutionDetailsInstance.id}")
       def staffDetailsInstance = new StaffDetails()
	   staffDetailsInstance.properties = params
       def staffDetailsInstanceList = StaffDetails.findAll("from StaffDetails S where S.tsOrNts = 'NTS' and S.institutionDetails.id= "+params.ntsDetailsList )
	   render (template:"listNtsDetails", model : ['staffDetailsInstanceList' : staffDetailsInstanceList,'staffDetailsInstance':staffDetailsInstance, 'institutionDetailsInstance':institutionDetailsInstance])
       }
       
   def saveNonteachingStaff = {
       GrailsHttpSession gh = getSession()
       def staffDetailsInstance = new StaffDetails()
       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.InstId )
       //gh.putValue("InstId","${institutionDetailsInstance.id}")
       
       def ntsTypeInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.ntsType )
       def designationInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.designation )
       def categoryInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.category )
       
       if(ntsTypeInstance){staffDetailsInstance.ntsType=ntsTypeInstance.item;}
       else{staffDetailsInstance.ntsType="";}
       if(designationInstance){staffDetailsInstance.designation=designationInstance.item;}
       else{staffDetailsInstance.designation="";}
       if(categoryInstance){staffDetailsInstance.category=categoryInstance.item;}
       else{staffDetailsInstance.category="";}
      
       staffDetailsInstance.tsOrNts="NTS";
       staffDetailsInstance.institutionDetails=institutionDetailsInstance;
       staffDetailsInstance.department="";
       staffDetailsInstance.gradePay="";
	   staffDetailsInstance.sanctionedStrength=params.sanctionedStrength;
	   staffDetailsInstance.selectionMode="";
	   staffDetailsInstance.tsOrNtsTotal=params.tsOrNtsTotal;
	   staffDetailsInstance.tsOrNtsFemale=params.tsOrNtsFemale;
	   staffDetailsInstance.pwdReservation=params.pwdReservation;
	   staffDetailsInstance.tsOrNtsPwdTotal=params.tsOrNtsPwdTotal;
	   staffDetailsInstance.tsOrNtsPwdFemale=params.tsOrNtsPwdFemale;
	   staffDetailsInstance.tsOrNtsMusTotal=params.tsOrNtsMusTotal;
	   staffDetailsInstance.tsOrNtsMusFemale=params.tsOrNtsMusFemale;
	   staffDetailsInstance.tsOrNtsOthMinTotal=params.tsOrNtsOthMinTotal;
	   staffDetailsInstance.tsOrNtsOthMinFemale=params.tsOrNtsOthMinFemale;
	   if (staffDetailsInstance.save(flush: true)) {
		                   redirect(controller: "staffDetails", action: "nonTeachingStaff")
		       }
		       else {
			                render(view:'nonTeachingStaff',model:[staffDetailsInstance: staffDetailsInstance])
			             }
       }
       
   def editNonteachingStaff = {
       GrailsHttpSession gh = getSession()
       def staffDetailsInstance = StaffDetails.get(params.id)
       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
       def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
       def ntsTypeList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='NTS_TYPE'")
       def designationList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='DESIGNATION_NTS'")
       def categoryList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='CATEGORY'")
       def ntsTypeInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+staffDetailsInstance.ntsType+"'")
	   def designationInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+staffDetailsInstance.designation+"'")
	   def categoryInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+staffDetailsInstance.category+"'")
	   return [staffDetailsInstance: staffDetailsInstance, institutionDetailsInstance: institutionDetailsInstance,
	               institutionDetailsInstanceList: institutionDetailsInstanceList, ntsTypeList: ntsTypeList,
	               designationList: designationList, categoryList: categoryList, ntsTypeInstance: ntsTypeInstance,
	               designationInstance: designationInstance, categoryInstance: categoryInstance]
       }
   
   def updateNonteachingStaff = {
       GrailsHttpSession gh = getSession()
       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
       def staffDetailsInstance = StaffDetails.get(params.id)
       def ntsTypeInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.ntsType )
       def designationInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.designation )
       def categoryInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.category )
       if (staffDetailsInstance) {
		            if (params.version) {
		                def version = params.version.toLong()
		                if (staffDetailsInstance.version > version) {
		                    staffDetailsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'StaffDetails.label', default: 'StaffDetails')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
		                    render(view: "editNonteachingStaff", model: [staffDetailsInstance: staffDetailsInstance])
		                }
		            }
	   
       if(ntsTypeInstance){staffDetailsInstance.ntsType=ntsTypeInstance.item;}
       else{staffDetailsInstance.ntsType="";}
       if(designationInstance){staffDetailsInstance.designation=designationInstance.item;}
       else{staffDetailsInstance.designation="";}
       if(categoryInstance){staffDetailsInstance.category=categoryInstance.item;}
       else{staffDetailsInstance.category="";}
      
       staffDetailsInstance.tsOrNts="NTS";
       staffDetailsInstance.institutionDetails=institutionDetailsInstance;
       staffDetailsInstance.department="";
       staffDetailsInstance.gradePay="";
	   staffDetailsInstance.sanctionedStrength=params.sanctionedStrength;
	   staffDetailsInstance.selectionMode="";
	   staffDetailsInstance.tsOrNtsTotal=params.tsOrNtsTotal;
	   staffDetailsInstance.tsOrNtsFemale=params.tsOrNtsFemale;
	   staffDetailsInstance.pwdReservation=params.pwdReservation;
	   staffDetailsInstance.tsOrNtsPwdTotal=params.tsOrNtsPwdTotal;
	   staffDetailsInstance.tsOrNtsPwdFemale=params.tsOrNtsPwdFemale;
	   staffDetailsInstance.tsOrNtsMusTotal=params.tsOrNtsMusTotal;
	   staffDetailsInstance.tsOrNtsMusFemale=params.tsOrNtsMusFemale;
	   staffDetailsInstance.tsOrNtsOthMinTotal=params.tsOrNtsOthMinTotal;
	   staffDetailsInstance.tsOrNtsOthMinFemale=params.tsOrNtsOthMinFemale;
	   if (staffDetailsInstance.save(flush: true)) {
	    flash.message = "${message(code: 'Updated', args: [message(code: 'StaffDetails.label', default: 'StaffDetails'), staffDetailsInstance.id])}"
		                redirect(controller: "staffDetails", action: "nonTeachingStaff", id: staffDetailsInstance.id)
		            }
		            else {
		                render(view: "editNonteachingStaff", model: [staffDetailsInstance: staffDetailsInstance])
		            }
		        }
		        else {
		            flash.message = "${message(code: 'Not found', args: [message(code: 'StaffDetails.label', default: 'StaffDetails'), params.id])}"
		             redirect(controller: "staffDetails", action: "nonTeachingStaff")
		        }
       }
       
   def deleteNonteachingStaff = {
       def staffDetailsInstance = StaffDetails.get(params.id)
			        if (staffDetailsInstance) {
			            try {
			                staffDetailsInstance.delete(flush: true)
			                flash.message = "${message(code: 'Deleted', args: [message(code: 'StaffDetails.label', default: 'StaffDetails'), params.id])}"
			               redirect(controller: "staffDetails", action: "nonTeachingStaff")
			            }
			            catch (org.springframework.dao.DataIntegrityViolationException e) {
			                flash.message = "${message(code: 'Not deleted', args: [message(code: 'StaffDetails.label', default: 'StaffDetails'), params.id])}"
			                redirect(controller: "staffDetails", action: "editNonteachingStaff", id: params.id)
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'StaffDetails.label', default: 'StaffDetails'), params.id])}"
			            redirect(controller: "staffDetails", action: "nonTeachingStaff")
			        }  
       }
 
  }