import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import java.text.SimpleDateFormat
import java.text.*;
import java.util.*;
import groovy.sql.Sql
class StudentsEnrolledController {

    def dataSource
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def index = {
           GrailsHttpSession gh = getSession()
           //gh.removeValue("InstId")
           gh.removeValue("FacultyId")
           gh.removeValue("DeptId")
           gh.removeValue("PrgmId")
           redirect(controller: "studentsEnrolled", action: "studentsDetails")
    }
    
    def firstTab = {
          GrailsHttpSession gh = getSession()
          //gh.removeValue("InstId")
          gh.removeValue("FacultyId")
          gh.removeValue("DeptId")
          gh.removeValue("PrgmId")
          redirect(controller: "studentsEnrolled", action: "enrolledStudents")
    }
    
    
    def studentsDetails = {
	 }
    
    def enrolledStudents = {
          GrailsHttpSession gh = getSession()
          def studentsEnrolledInstance = new StudentsEnrolled()
		  def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails" )
		  def institutionDetailsInstance = InstitutionDetails.find("from InstitutionDetails ID where ID.id = "+gh.getValue("InstId"))
	      def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+gh.getValue("InstId"))
	      def facultyInstance = Faculty.find("from Faculty F where F.id = "+gh.getValue("FacultyId"))
	      def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+gh.getValue("FacultyId"))
	      def departmentInstance = Department.find("from Department D where D.id = "+gh.getValue("DeptId"))
	      def programmeDetailsInstanceList = ProgrammeDetails.findAll("from ProgrammeDetails P where P.department = "+gh.getValue("DeptId"))
	      def programmeDetailsInstance = ProgrammeDetails.find("from ProgrammeDetails P where P.id = "+gh.getValue("PrgmId"))
	      def studentsEnrolledInstanceList = StudentsEnrolled.findAll("from StudentsEnrolled S where S.programmeDetails.id= "+gh.getValue("PrgmId"))
	      def categoryList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='CATEGORY'")
	      return [institutionDetailsInstanceList: institutionDetailsInstanceList, facultyInstanceList: facultyInstanceList,
	                  departmentInstanceList: departmentInstanceList, programmeDetailsInstanceList: programmeDetailsInstanceList,
	                  categoryList: categoryList, studentsEnrolledInstanceList: studentsEnrolledInstanceList, studentsEnrolledInstance: studentsEnrolledInstance,
	                  institutionDetailsInstance: institutionDetailsInstance, facultyInstance: facultyInstance, departmentInstance: departmentInstance]
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
    
    def programmeNameList = {
          GrailsHttpSession gh = getSession()
          def departmentInstance = Department.find("from Department D where D.id= "+params.programmeNameList)
          gh.putValue("DeptId","${departmentInstance.id}")
          def programmeDetailsInstanceList = ProgrammeDetails.findAll("from ProgrammeDetails P where P.department = "+params.programmeNameList)
          render (template:"listProgrammeName", model : ['programmeDetailsInstanceList' : programmeDetailsInstanceList])
    }
    
    def studentsEnrolledList = {
          GrailsHttpSession gh = getSession()
          def studentsEnrolledInstance = new StudentsEnrolled()
	      studentsEnrolledInstance.properties = params
	      def programmeDetailsInstance =ProgrammeDetails.find("from ProgrammeDetails P where P.id="+params.studentsEnrolledList )
	      gh.putValue("PrgmId","${programmeDetailsInstance.id}")
	      def institutionDetailsInstance = InstitutionDetails.find("from InstitutionDetails ID where ID.id = "+gh.getValue("InstId"))
	      def facultyInstance = Faculty.find("from Faculty F where F.id = "+gh.getValue("FacultyId"))
	      def departmentInstance = Department.find("from Department D where D.id = "+gh.getValue("DeptId"))
		  def studentsEnrolledInstanceList = StudentsEnrolled.findAll("from StudentsEnrolled S where S.programmeDetails.id= "+params.studentsEnrolledList )
		  render (template:"listStudentsEnrolled", model : ['studentsEnrolledInstanceList' : studentsEnrolledInstanceList,'studentsEnrolledInstance':studentsEnrolledInstance, 
		                                                                                  'programmeDetailsInstance':programmeDetailsInstance, institutionDetailsInstance: institutionDetailsInstance,
		                                                                                   facultyInstance: facultyInstance, departmentInstance: departmentInstance])
    }
    
    def saveStudentInfo = {
          GrailsHttpSession gh = getSession()
          def studentsEnrolledInstance = new StudentsEnrolled()
          def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.InstId )
	      def facultyInstance = Faculty.find("from Faculty F where F.id="+params.faculty )
	      def departmentInstance = Department.find("from Department D where D.id="+params.department )
	      def programmeDetailsInstance = ProgrammeDetails.find("from ProgrammeDetails PD where PD.id="+params.programmeDetails)
	      
	      //gh.putValue("InstId","${institutionDetailsInstance.id}")
	      gh.putValue("FacultyId","${facultyInstance.id}")
	      gh.putValue("DeptId","${departmentInstance.id}")
	      
	      def categoryInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.category )
	      if(categoryInstance){studentsEnrolledInstance.category=categoryInstance.item;}
          else{studentsEnrolledInstance.category="";}
          
          studentsEnrolledInstance.programmeDetails=programmeDetailsInstance;
          studentsEnrolledInstance.year=params.year;
          studentsEnrolledInstance.totalStudents=params.totalStudents;
          studentsEnrolledInstance.femaleStudents=params.femaleStudents
          studentsEnrolledInstance.totalPwdStudents=params.totalPwdStudents
          studentsEnrolledInstance.femalePwdStudents=params.femalePwdStudents
          studentsEnrolledInstance.totalMuslimStudents=params.totalMuslimStudents
          studentsEnrolledInstance.femaleMuslimStudents=params.femaleMuslimStudents
          studentsEnrolledInstance.totalOthMinStudents=params.totalOthMinStudents
          studentsEnrolledInstance.femaleOthMinStudents=params.femaleOthMinStudents
          if (studentsEnrolledInstance.save(flush: true)) {
		                   redirect(controller: "studentsEnrolled", action: "enrolledStudents")
		       }
		       else {
			                render(view:'enrolledStudents',model:[studentsEnrolledInstance: studentsEnrolledInstance])
			             }
          
    }
    
    def editStudentInfo = {
          GrailsHttpSession gh = getSession()
          def studentsEnrolledInstance = StudentsEnrolled.get(params.id)
          def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
          def facultyInstance = Faculty.find("from Faculty F where F.id="+gh.getValue("FacultyId") )
          def departmentInstance = Department.find("from Department D where D.id="+gh.getValue("DeptId") )
          def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
          def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+gh.getValue("InstId"))
	      def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+gh.getValue("FacultyId"))
	      def programmeDetailsInstanceList = ProgrammeDetails.findAll("from ProgrammeDetails P where P.department = "+gh.getValue("DeptId"))
	      def categoryList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='CATEGORY'")
	      def categoryInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+studentsEnrolledInstance.category+"'")
          return [institutionDetailsInstanceList: institutionDetailsInstanceList, facultyInstanceList: facultyInstanceList,
	                 departmentInstanceList: departmentInstanceList, categoryList: categoryList, categoryInstance: categoryInstance,
	                 studentsEnrolledInstance: studentsEnrolledInstance, institutionDetailsInstance: institutionDetailsInstance, 
	                 facultyInstance: facultyInstance, departmentInstance: departmentInstance, programmeDetailsInstanceList: programmeDetailsInstanceList]
	                 
    }
    
    def updateStudentInfo = {
          GrailsHttpSession gh = getSession()
	      def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
	      def studentsEnrolledInstance = StudentsEnrolled.get(params.id)
	      def programmeDetailsInstance = ProgrammeDetails.find("from ProgrammeDetails PD where PD.id="+params.programmeDetails)
	      def categoryInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.category )
	      if (studentsEnrolledInstance) {
		            if (params.version) {
		                def version = params.version.toLong()
		                if (studentsEnrolledInstance.version > version) {
		                    studentsEnrolledInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'StudentsEnrolled.label', default: 'StudentsEnrolled')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
		                    render(view: "editStudentInfo", model: [studentsEnrolledInstance: studentsEnrolledInstance])
		                }
		            }
		  if(categoryInstance){studentsEnrolledInstance.category=categoryInstance.item;}
          else{studentsEnrolledInstance.category="";}          
		  studentsEnrolledInstance.programmeDetails=programmeDetailsInstance;
          studentsEnrolledInstance.year=params.year;
          studentsEnrolledInstance.totalStudents=params.totalStudents;
          studentsEnrolledInstance.femaleStudents=params.femaleStudents
          studentsEnrolledInstance.totalPwdStudents=params.totalPwdStudents
          studentsEnrolledInstance.femalePwdStudents=params.femalePwdStudents
          studentsEnrolledInstance.totalMuslimStudents=params.totalMuslimStudents
          studentsEnrolledInstance.femaleMuslimStudents=params.femaleMuslimStudents
          studentsEnrolledInstance.totalOthMinStudents=params.totalOthMinStudents
          studentsEnrolledInstance.femaleOthMinStudents=params.femaleOthMinStudents
         
          if (studentsEnrolledInstance.save(flush: true)) {
          flash.message = "${message(code: 'Updated', args: [message(code: 'StudentsEnrolled.label', default: 'StudentsEnrolled'), studentsEnrolledInstance.id])}"
		                redirect(controller: "studentsEnrolled", action: "enrolledStudents", id: studentsEnrolledInstance.id)
		            }
		            else {
		                render(view: "editStudentInfo", model: [studentsEnrolledInstance: studentsEnrolledInstance])
		            }
		        }
		        else {
		            flash.message = "${message(code: 'Not found', args: [message(code: 'StudentsEnrolled.label', default: 'StudentsEnrolled'), params.id])}"
		             redirect(controller: "studentsEnrolled", action: "enrolledStudents")
		        }      
    }
    
    def deleteStudentInfo = {
           def studentsEnrolledInstance = StudentsEnrolled.get(params.id)
			        if (studentsEnrolledInstance) {
			            try {
			                studentsEnrolledInstance.delete(flush: true)
			                flash.message = "${message(code: 'Deleted', args: [message(code: 'StudentsEnrolled.label', default: 'StudentsEnrolled'), params.id])}"
			               redirect(controller: "studentsEnrolled", action: "enrolledStudents")
			            }
			            catch (org.springframework.dao.DataIntegrityViolationException e) {
			                flash.message = "${message(code: 'Not deleted', args: [message(code: 'StudentsEnrolled.label', default: 'StudentsEnrolled'), params.id])}"
			                redirect(controller: "studentsEnrolled", action: "editStudentInfo", id: params.id)
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'StudentsEnrolled.label', default: 'StudentsEnrolled'), params.id])}"
			            redirect(controller: "studentsEnrolled", action: "enrolledStudents")
			        }  
    }
    
    def secondTab = {
            GrailsHttpSession gh = getSession()
            //gh.removeValue("InstId")
            gh.removeValue("FacultyId")
            gh.removeValue("DeptId")
            gh.removeValue("PrgmId")
            redirect(controller: "studentsEnrolled", action: "foriegnStudents")
    } 
    
    
    
    def foriegnStudents = {
           GrailsHttpSession gh = getSession()
           def programmeDetailsInstance = new ProgrammeDetails()
		   def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails" )
		   def institutionDetailsInstance = InstitutionDetails.find("from InstitutionDetails ID where ID.id = "+gh.getValue("InstId"))
	       def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+gh.getValue("InstId"))
	       def facultyInstance = Faculty.find("from Faculty F where F.id = "+gh.getValue("FacultyId"))
	       def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+gh.getValue("FacultyId"))
	       def departmentInstance = Department.find("from Department D where D.id = "+gh.getValue("DeptId"))
	       def programmeDetailsInstanceList = ProgrammeDetails.findAll("from ProgrammeDetails P where P.department = "+gh.getValue("DeptId"))
	       return [institutionDetailsInstanceList: institutionDetailsInstanceList, facultyInstanceList: facultyInstanceList,
	                  departmentInstanceList: departmentInstanceList, programmeDetailsInstanceList: programmeDetailsInstanceList,
	                  institutionDetailsInstance: institutionDetailsInstance, facultyInstance: facultyInstance, departmentInstance: departmentInstance]
    }
    
   def departmentNameList_fs = {
		  GrailsHttpSession gh = getSession()
          def facultyInstance =Faculty.find("from Faculty F where F.id="+params.departmentNameList )
		  gh.putValue("FacultyId","${facultyInstance.id}")
          def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+params.departmentNameList)
          render (template:"listDepartmentName_fs", model : ['departmentInstanceList' : departmentInstanceList])
   }
   
   def programmeNameList_fs = {
          GrailsHttpSession gh = getSession()
          def departmentInstance = Department.find("from Department D where D.id= "+params.programmeNameList)
          gh.putValue("DeptId","${departmentInstance.id}")
          def programmeDetailsInstanceList = ProgrammeDetails.findAll("from ProgrammeDetails P where P.department = "+params.programmeNameList)
          render (template:"listProgrammeName_fs", model : ['programmeDetailsInstanceList' : programmeDetailsInstanceList])
   }
   
   
    def foriegnStudentsDetails  = {
            def programmeDetailsInstance =ProgrammeDetails.find("from ProgrammeDetails P where P.id="+params.foriegnStudentsDetails )
    		println"qwertyuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu"
    		render (template:"foriegnStudentsDetails", model : ['programmeDetailsInstance':programmeDetailsInstance])
    }
    
    
    def saveForgnStudents = {
           GrailsHttpSession gh = getSession()
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
	       def programmeDetailsInstance = ProgrammeDetails.get(params.programmeDetails)
	       
	       if (programmeDetailsInstance) {
	       programmeDetailsInstance.fsYOrN="Y";
           programmeDetailsInstance.fsCountryName=params.fsCountryName;
           programmeDetailsInstance.fsCountryCode=params.fsCountryCode;
           programmeDetailsInstance.totalFs=params.totalFs;
           programmeDetailsInstance.femaleFs=params.femaleFs;
           if (programmeDetailsInstance.save(flush: true)) {
           redirect(controller: "studentsEnrolled", action: "secondTab")
		       }
		       else {
			                render(view:'foriegnStudents',model:[programmeDetailsInstance: programmeDetailsInstance])
			             }
        }
    }
    
    def thirdTab = {
           GrailsHttpSession gh = getSession()
           //gh.removeValue("InstId")
           gh.removeValue("FacultyId")
           gh.removeValue("DeptId")
           gh.removeValue("PrgmId")
           redirect(controller: "studentsEnrolled", action: "examResults")
    }
    
    def examResults = {
          GrailsHttpSession gh = getSession()
          def examResultsInstance = new ExamResults()
		  def examResultsInstanceList = ExamResults.findAll("from ExamResults E where E.programmeDetails.id= "+gh.getValue("PrgmId"))
		  def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails" )
		  def institutionDetailsInstance = InstitutionDetails.find("from InstitutionDetails ID where ID.id = "+gh.getValue("InstId"))
	      def facultyInstanceList = Faculty.findAll("from Faculty F where F.institutionDetails = "+gh.getValue("InstId"))
	      def facultyInstance = Faculty.find("from Faculty F where F.id = "+gh.getValue("FacultyId"))
	      def departmentInstanceList = Department.findAll("from Department D where D.faculty = "+gh.getValue("FacultyId"))
	      def departmentInstance = Department.find("from Department D where D.id = "+gh.getValue("DeptId"))
	      def programmeDetailsInstanceList = ProgrammeDetails.findAll("from ProgrammeDetails P where P.department = "+gh.getValue("DeptId"))
	      return [institutionDetailsInstanceList: institutionDetailsInstanceList, facultyInstanceList: facultyInstanceList, examResultsInstance: examResultsInstance,
	                  departmentInstanceList: departmentInstanceList, programmeDetailsInstanceList: programmeDetailsInstanceList,
	                  institutionDetailsInstance: institutionDetailsInstance, facultyInstance: facultyInstance, departmentInstance: departmentInstance, examResultsInstanceList: examResultsInstanceList]
    }
    
    def saveExamResults = {
           GrailsHttpSession gh = getSession()
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
	       def examResultsInstance = new ExamResults()
	       def programmeDetailsInstance = ProgrammeDetails.find("from ProgrammeDetails PD where PD.id="+params.programmeDetails)
	       
	       examResultsInstance.programmeDetails=programmeDetailsInstance
	       examResultsInstance.totStudAppd=params.totStudAppd;
           examResultsInstance.femStudAppd=params.femStudAppd;
           examResultsInstance.totStudPassd=params.totStudPassd;
           examResultsInstance.femStudPassd=params.femStudPassd;
           if (examResultsInstance.save(flush: true)) {
           redirect(controller: "studentsEnrolled", action: "examResults")
		       }
		       else {
			                render(view:'examResults',model:[examResultsInstance: examResultsInstance])
			             }
        
    }
    
 }