import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import java.text.SimpleDateFormat
import java.text.*;
import java.util.*;
import groovy.sql.Sql
class ProgrammeDetailsController {

    def dataSource
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def index = {
               GrailsHttpSession gh = getSession()
               //gh.removeValue("InstId")
               gh.removeValue("FacultyId")
               gh.removeValue("DeptId")
               redirect(controller: "programmeDetails", action: "programmeInfo")
    }
    
    def programmeInfo = {
    }
    
    def firstTab = {
                GrailsHttpSession gh = getSession()
                //gh.removeValue("InstId")
                gh.removeValue("FacultyId")
                gh.removeValue("DeptId")
                redirect(controller: "programmeDetails", action: "facultyDetails")
    }
    def facultyDetails = {
		        GrailsHttpSession gh = getSession()
		        def facultyInstance = new Faculty()
		        def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
		        def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+gh.getValue("InstId"))
		        def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
		        facultyInstance.institutionDetails = institutionDetailsInstance
			    return[facultyInstance:facultyInstance,
			               institutionDetailsInstanceList:institutionDetailsInstanceList,
			               facultyInstanceList:facultyInstanceList]
    }
    
    def facultyList = {
		       GrailsHttpSession gh = getSession()
			   def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.facultyList )
			   gh.putValue("InstId","${institutionDetailsInstance.id}")
			   def facultyInstance = new Faculty()
			   facultyInstance.properties = params
			   def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+params.facultyList )
			   render (template:"listFaculty", model : ['facultyInstanceList' : facultyInstanceList,'facultyInstance':facultyInstance])
    }
    
    
    def saveFaculty = {
              GrailsHttpSession gh = getSession()
              def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.InstId )
		      //gh.putValue("InstId","${institutionDetailsInstance.id}")
		      def facultyInstance = new Faculty()
		      facultyInstance.institutionDetails=institutionDetailsInstance;
		      facultyInstance.facultyName=params.facultyName;
		      facultyInstance.facultyCode=params.facultyCode;
		      
		      
		       if (facultyInstance.save(flush: true)) {
		                   redirect(controller: "programmeDetails", action: "facultyDetails")
		       }
		       else {
			                render(view:'facultyDetails',model:[facultyInstance:facultyInstance])
			             }
    }
    
    def editFaculty = {
               def facultyInstance = Faculty.get(params.id)
               def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
			   return ['facultyInstance':facultyInstance, 'institutionDetailsInstanceList':institutionDetailsInstanceList]
    }
    
    def updateFaculty = {
               GrailsHttpSession gh = getSession()
		       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.institutionDetails )
		       def facultyInstance = Faculty.get(params.id)
		       if (facultyInstance) {
			            if (params.version) {
			                def version = params.version.toLong()
			                if (facultyInstance.version > version) {
			                    facultyInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'Faculty.label', default: 'Faculty')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
			                    render(view: "editFaculty", model: [facultyInstance: facultyInstance])
			                }
			            }
			            facultyInstance.institutionDetails=institutionDetailsInstance;
		                facultyInstance.facultyName=params.facultyName;
		                facultyInstance.facultyCode=params.facultyCode;
		                if (facultyInstance.save(flush: true)) {
		                flash.message = "${message(code: 'Updated', args: [message(code: 'Faculty.label', default: 'Faculty'), facultyInstance.id])}"
			                redirect(controller: "programmeDetails", action: "facultyDetails", id: facultyInstance.id)
			            }
			            else {
			                render(view: "editFaculty", model: [facultyInstance: facultyInstance])
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'Faculty.label', default: 'Faculty'), params.id])}"
			             redirect(controller: "programmeDetails", action: "facultyDetails")
			        }
    }
    
    def deleteFaculty = {
			    def facultyInstance = Faculty.get(params.id)
			        if (facultyInstance) {
			            try {
			                facultyInstance.delete(flush: true)
			                flash.message = "${message(code: 'Deleted', args: [message(code: 'Faculty.label', default: 'Faculty'), params.id])}"
			               redirect(controller: "programmeDetails", action: "facultyDetails")
			            }
			            catch (org.springframework.dao.DataIntegrityViolationException e) {
			                flash.message = "${message(code: 'Not deleted', args: [message(code: 'Faculty.label', default: 'Faculty'), params.id])}"
			                redirect(controller: "programmeDetails", action: "editFaculty", id: params.id)
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'Faculty.label', default: 'Faculty'), params.id])}"
			            redirect(controller: "programmeDetails", action: "facultyDetails")
			        }
    }
    
    def secondTab = {
                  GrailsHttpSession gh = getSession()
               	  //gh.removeValue("InstId")
                  gh.removeValue("FacultyId")
                  gh.removeValue("DeptId")
                  redirect(controller: "programmeDetails", action: "departmentDetails")
    }
    
    
    def departmentDetails = {
                  GrailsHttpSession gh = getSession()
                  def departmentInstance = new Department()
                  def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
		          def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+gh.getValue("FacultyId"))
		          def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
		          def  facultyInstance =Faculty.find("from Faculty F where F.id="+gh.getValue("FacultyId") )
		          def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+gh.getValue("InstId"))
		          departmentInstance.faculty = facultyInstance
			      return[departmentInstance: departmentInstance,
			                 institutionDetailsInstanceList: institutionDetailsInstanceList,
			                 departmentInstanceList: departmentInstanceList,
			                 facultyInstanceList: facultyInstanceList,
			                 institutionDetailsInstance: institutionDetailsInstance]
                  
    }
    
    def facultyNameList = {
                 GrailsHttpSession gh = getSession()
                 def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.facultyNameList )
			     gh.putValue("InstId","${institutionDetailsInstance.id}")
                 def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+params.facultyNameList)
                 render (template:"listFacultyName", model : ['facultyInstanceList' : facultyInstanceList])
    }
    
    def saveDepartment = {
                  GrailsHttpSession gh = getSession()
                  def departmentInstance = new Department()
	              def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.InstId )
			      def facultyInstance = Faculty.find("from Faculty F where F.id="+params.faculty )
			      //gh.putValue("InstId","${institutionDetailsInstance.id}")
			      departmentInstance.faculty=facultyInstance;
			      departmentInstance.deptName=params.deptName;
			      departmentInstance.deptCode=params.deptCode;
			      
			      
			       if (departmentInstance.save(flush: true)) {
			                   redirect(controller: "programmeDetails", action: "departmentDetails")
			       }
			       else {
				                render(view:'departmentDetails',model:[departmentInstance: departmentInstance])
				             }
    }
    
    def departmentList = {
               GrailsHttpSession gh = getSession()
			   def facultyInstance =Faculty.find("from Faculty F where F.id="+params.departmentList )
			   gh.putValue("FacultyId","${facultyInstance.id}")
			   def departmentInstance = new Department()
			   departmentInstance.properties = params
			   def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
			   def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+params.departmentList )
			   render (template:"listDepartment", model : ['departmentInstanceList' : departmentInstanceList,'departmentInstance':departmentInstance, 'institutionDetailsInstance':institutionDetailsInstance])
    }
    
    def editDepartment = {
               GrailsHttpSession gh = getSession()
               def departmentInstance = Department.get(params.id)
               def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
               def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
               def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+gh.getValue("InstId"))
			   return ['departmentInstance':departmentInstance, 'institutionDetailsInstanceList':institutionDetailsInstanceList, 'facultyInstanceList':facultyInstanceList, 'institutionDetailsInstance':institutionDetailsInstance]
           
    }
    
    def updateDepartment = {
               GrailsHttpSession gh = getSession()
		       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
		       def departmentInstance = Department.get(params.id)
		       def facultyInstance = Faculty.find("from Faculty F where F.id="+params.faculty )
		       if (departmentInstance) {
			            if (params.version) {
			                def version = params.version.toLong()
			                if (departmentInstance.version > version) {
			                    departmentInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'Department.label', default: 'Department')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
			                    render(view: "editDepartment", model: [departmentInstance: departmentInstance])
			                }
			            }
			      departmentInstance.faculty=facultyInstance;
			      departmentInstance.deptName=params.deptName;
			      departmentInstance.deptCode=params.deptCode;
		                if (departmentInstance.save(flush: true)) {
		                flash.message = "${message(code: 'Updated', args: [message(code: 'Department.label', default: 'Department'), departmentInstance.id])}"
			                redirect(controller: "programmeDetails", action: "departmentDetails", id: departmentInstance.id)
			            }
			            else {
			                render(view: "editDepartment", model: [departmentInstance: departmentInstance])
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'Department.label', default: 'Department'), params.id])}"
			             redirect(controller: "programmeDetails", action: "departmentDetails")
			        }
    }
    
    def deleteDepartment = {
                  def departmentInstance = Department.get(params.id)
			        if (departmentInstance) {
			            try {
			                departmentInstance.delete(flush: true)
			                flash.message = "${message(code: 'Deleted', args: [message(code: 'Department.label', default: 'Department'), params.id])}"
			               redirect(controller: "programmeDetails", action: "departmentDetails")
			            }
			            catch (org.springframework.dao.DataIntegrityViolationException e) {
			                flash.message = "${message(code: 'Not deleted', args: [message(code: 'Department.label', default: 'Department'), params.id])}"
			                redirect(controller: "programmeDetails", action: "editDepartment", id: params.id)
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'Department.label', default: 'Department'), params.id])}"
			            redirect(controller: "programmeDetails", action: "departmentDetails")
			        }
       
    }
    
    
    def thirdTab = {
               GrailsHttpSession gh = getSession()
               //gh.removeValue("InstId")
               gh.removeValue("FacultyId")
               gh.removeValue("DeptId")
               redirect(controller: "programmeDetails", action: "programme")
    }
    
   
    def programme = {
              GrailsHttpSession gh = getSession()
              def programmeDetailsInstance = new ProgrammeDetails()
              def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
              def institutionDetailsInstance = InstitutionDetails.find("from InstitutionDetails ID where ID.id = "+gh.getValue("InstId"))
              def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+gh.getValue("InstId"))
              def facultyInstance = Faculty.find("from Faculty F where F.id = "+gh.getValue("FacultyId"))
              def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+gh.getValue("FacultyId"))
              def departmentInstance = Department.find("from Department D where D.id = "+gh.getValue("DeptId"))
              def programmeDetailsInstanceList = ProgrammeDetails.findAll("from ProgrammeDetails PD where PD.department = "+gh.getValue("DeptId"))
              def modeList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='PROG_MODE'")
              def levelList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='PROG_LEVEL'")
              def typeList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='PROG_TYPE'")
              def examSystemList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='EXAM_SYSTEM'")
              programmeDetailsInstance.department = departmentInstance
              return [institutionDetailsInstanceList: institutionDetailsInstanceList, 
                         facultyInstanceList: facultyInstanceList, 
                         departmentInstanceList: departmentInstanceList,
                         modeList: modeList,
                         levelList: levelList,
                         typeList: typeList,
                         examSystemList: examSystemList,
                         programmeDetailsInstanceList: programmeDetailsInstanceList,
                         institutionDetailsInstance: institutionDetailsInstance,
                         programmeDetailsInstance: programmeDetailsInstance,
                         facultyInstance: facultyInstance]
    }
    
     def facultyNameList1 = {
              GrailsHttpSession gh = getSession()
              def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.facultyNameList1 )
		      gh.putValue("InstId","${institutionDetailsInstance.id}")
              def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+params.facultyNameList1)
              render (template:"listFacultyName1", model : ['facultyInstanceList' : facultyInstanceList])
    }
    
    
    def departmentNameList = {
              GrailsHttpSession gh = getSession()
              def facultyInstance =Faculty.find("from Faculty F where F.id="+params.departmentNameList )
			  gh.putValue("FacultyId","${facultyInstance.id}")
              def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+params.departmentNameList)
              render (template:"listDepartmentName", model : ['departmentInstanceList' : departmentInstanceList])
    }
    
    def programmeList = {
               GrailsHttpSession gh = getSession()
			   def departmentInstance =Department.find("from Department D where D.id="+params.programmeList )
			   gh.putValue("DeptId","${departmentInstance.id}")
			   def programmeDetailsInstance = new ProgrammeDetails()
			   programmeDetailsInstance.properties = params
			   def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
			   def facultyInstance = Faculty.find("from Faculty F where F.id ="+gh.getValue("FacultyId"))
			   def programmeDetailsInstanceList = ProgrammeDetails.findAll("from ProgrammeDetails PD where PD.department = "+params.programmeList )
			   render (template:"listProgramme", model : ['programmeDetailsInstanceList' : programmeDetailsInstanceList,'programmeDetailsInstance':programmeDetailsInstance, 'institutionDetailsInstance':institutionDetailsInstance, 'facultyInstance':facultyInstance])
    }
   
    def saveProgramme = {
              GrailsHttpSession gh = getSession()
              def programmeDetailsInstance = new ProgrammeDetails()
              def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.InstId )
		      def facultyInstance = Faculty.find("from Faculty F where F.id="+params.faculty )
		      def departmentInstance = Department.find("from Department D where D.id="+params.department )
		      
		      //gh.putValue("InstId","${institutionDetailsInstance.id}")
		      gh.putValue("FacultyId","${facultyInstance.id}")
		      
		      def modeInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.mode )
		      def levelInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.level )
		      def programmeTypeInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.programmeType )
		      def examinationSystemInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.examinationSystem )
		      
		      if(modeInstance){programmeDetailsInstance.mode=modeInstance.item;}
		      else{programmeDetailsInstance.mode="";}
		      if(levelInstance){programmeDetailsInstance.level=levelInstance.item;}
		      else{programmeDetailsInstance.level="";}
		      if(programmeTypeInstance){programmeDetailsInstance.programmeType=programmeTypeInstance.item;}
		      else{programmeDetailsInstance.programmeType="";}
		      if(examinationSystemInstance){programmeDetailsInstance.examinationSystem=examinationSystemInstance.item;}
		      else{programmeDetailsInstance.examinationSystem="";}
		      
		      programmeDetailsInstance.department=departmentInstance;
		      programmeDetailsInstance.programmeName=params.programmeName;
		      programmeDetailsInstance.programmeCode=params.programmeCode;
		      programmeDetailsInstance.disciplineName=params.disciplineName;
		      programmeDetailsInstance.disciplineCode=params.disciplineCode;
		      programmeDetailsInstance.broadDisciplineName=params.broadDisciplineName;
		      programmeDetailsInstance.broadDIsciplineCode=params.broadDIsciplineCode;
		      programmeDetailsInstance.intakeCapacity=params.intakeCapacity;
		      programmeDetailsInstance.numberOfApplicants=params.numberOfApplicants;
		      programmeDetailsInstance.programmeDuration=params.programmeDuration;
		      programmeDetailsInstance.university=params.university;
		      programmeDetailsInstance.fsYOrN="N"
              programmeDetailsInstance.fsCountryName=""
              programmeDetailsInstance.fsCountryCode=""
              programmeDetailsInstance.totalFs=""
              programmeDetailsInstance.femaleFs=""
		      
		       if (programmeDetailsInstance.save(flush: true)) {
		                   redirect(controller: "programmeDetails", action: "programme")    
		       }
		       else {
			                render(view:'programme',model:[programmeDetailsInstance: programmeDetailsInstance])
			             }
    
    }
    
    def editProgramme = {
               GrailsHttpSession gh = getSession()
               def programmeDetailsInstance = ProgrammeDetails.get(params.id)
               def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
               def facultyInstance = Faculty.find("from Faculty F where F.id="+gh.getValue("FacultyId") )
               def modeInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+programmeDetailsInstance.mode+"'")
			   def levelInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+programmeDetailsInstance.level+"'")
			   def typeInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+programmeDetailsInstance.programmeType+"'")
			   def examSystemInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+programmeDetailsInstance.examinationSystem+"'")
              
               def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
               def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+gh.getValue("InstId"))
			   def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+gh.getValue("FacultyId"))
			   def modeList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='PROG_MODE'")
               def levelList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='PROG_LEVEL'")
               def typeList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='PROG_TYPE'")
               def examSystemList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='EXAM_SYSTEM'")
			   
			   return ['programmeDetailsInstance':programmeDetailsInstance, 'institutionDetailsInstanceList':institutionDetailsInstanceList, 
			               'facultyInstanceList':facultyInstanceList, modeList: modeList,
                            levelList: levelList, typeList: typeList,
                            examSystemList: examSystemList, 'institutionDetailsInstance':institutionDetailsInstance, 
			               'departmentInstanceList':departmentInstanceList, 'facultyInstance':facultyInstance,
			               'modeInstance':modeInstance, 'levelInstance':levelInstance,
			               'typeInstance':typeInstance, 'examSystemInstance':examSystemInstance]
    }
    
   def updateProgramme = {
   
               GrailsHttpSession gh = getSession()
		       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
		       def programmeDetailsInstance = ProgrammeDetails.get(params.id)
		       def departmentInstance = Department.find("from Department D where D.id="+params.department )
		       def modeInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.mode )
		       def levelInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.level )
		       def programmeTypeInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.programmeType )
		       def examinationSystemInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.examinationSystem )
		       if (programmeDetailsInstance) {
			            if (params.version) {
			                def version = params.version.toLong()
			                if (programmeDetailsInstance.version > version) {
			                    programmeDetailsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'ProgrammeDetails.label', default: 'ProgrammeDetails')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
			                    render(view: "editProgramme", model: [programmeDetailsInstance: programmeDetailsInstance])
			                }
			            }
			     		      
		      if(modeInstance){programmeDetailsInstance.mode=modeInstance.item;}
		      else{programmeDetailsInstance.mode="";}
		      if(levelInstance){programmeDetailsInstance.level=levelInstance.item;}
		      else{programmeDetailsInstance.level="";}
		      if(programmeTypeInstance){programmeDetailsInstance.programmeType=programmeTypeInstance.item;}
		      else{programmeDetailsInstance.programmeType="";}
		      if(examinationSystemInstance){programmeDetailsInstance.examinationSystem=examinationSystemInstance.item;}
		      else{programmeDetailsInstance.examinationSystem="";}
		      
		      programmeDetailsInstance.department=departmentInstance;
		      programmeDetailsInstance.programmeName=params.programmeName;
		      programmeDetailsInstance.programmeCode=params.programmeCode;
		      programmeDetailsInstance.disciplineName=params.disciplineName;
		      programmeDetailsInstance.disciplineCode=params.disciplineCode;
		      programmeDetailsInstance.broadDisciplineName=params.broadDisciplineName;
		      programmeDetailsInstance.broadDIsciplineCode=params.broadDIsciplineCode;
		      programmeDetailsInstance.intakeCapacity=params.intakeCapacity;
		      programmeDetailsInstance.numberOfApplicants=params.numberOfApplicants;
		      programmeDetailsInstance.programmeDuration=params.programmeDuration;
		      programmeDetailsInstance.university=params.university;
		      programmeDetailsInstance.fsYOrN="N"
              programmeDetailsInstance.fsCountryName=""
              programmeDetailsInstance.fsCountryCode=""
              programmeDetailsInstance.totalFs=""
              programmeDetailsInstance.femaleFs=""
		                if (programmeDetailsInstance.save(flush: true)) {
		                flash.message = "${message(code: 'Updated', args: [message(code: 'ProgrammeDetails.label', default: 'ProgrammeDetails'), programmeDetailsInstance.id])}"
			                redirect(controller: "programmeDetails", action: "programme", id: programmeDetailsInstance.id)
			            }
			            else {
			                render(view: "editProgramme", model: [programmeDetailsInstance: programmeDetailsInstance])
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'ProgrammeDetails.label', default: 'ProgrammeDetails'), params.id])}"
			             redirect(controller: "programmeDetails", action: "programme")
			        }
   }
   
   def deleteProgramme = {
             def programmeDetailsInstance = ProgrammeDetails.get(params.id)
			        if (programmeDetailsInstance) {
			            try {
			                programmeDetailsInstance.delete(flush: true)
			                flash.message = "${message(code: 'Deleted', args: [message(code: 'ProgrammeDetails.label', default: 'ProgrammeDetails'), params.id])}"
			               redirect(controller: "programmeDetails", action: "programme")
			            }
			            catch (org.springframework.dao.DataIntegrityViolationException e) {
			                flash.message = "${message(code: 'Not deleted', args: [message(code: 'ProgrammeDetails.label', default: 'ProgrammeDetails'), params.id])}"
			                redirect(controller: "programmeDetails", action: "editProgramme", id: params.id)
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'ProgrammeDetails.label', default: 'ProgrammeDetails'), params.id])}"
			            redirect(controller: "programmeDetails", action: "programme")
			        }  
   }
    
}
