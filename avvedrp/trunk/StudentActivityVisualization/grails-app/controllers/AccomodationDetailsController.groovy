import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import java.text.SimpleDateFormat
import java.text.*;
import java.util.*;
import groovy.sql.Sql
import jofc2.model.Chart
import jofc2.model.elements.LineChart
import jofc2.model.axis.YAxis
import jofc2.model.axis.XAxis
import jofc2.model.axis.Label
import jofc2.model.elements.AreaChart
import jofc2.model.elements.PieChart
import jofc2.model.axis.Label.Rotation
import jofc2.model.elements.BarChart
import jofc2.OFC
import jofc2.model.elements.FilledBarChart
import jofc2.model.elements.SketchBarChart
import jofc2.model.elements.HorizontalBarChart
import jofc2.model.elements.ScatterChart
import java.math.MathContext
import jofc2.model.elements.StackedBarChart
import jofc2.model.elements.StackedBarChart.StackValue
import jofc2.model.elements.AbstractDot
class AccomodationDetailsController {

    def dataSource
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def index = {
               GrailsHttpSession gh = getSession()
               //gh.removeValue("InstId")
               redirect(controller: "accomodationDetails", action: "quartersAndHostels")
    }
    
    def quartersAndHostels = {
	          
   }
   
   def firstTab = {
               GrailsHttpSession gh = getSession()
               //gh.removeValue("InstId")
               redirect(controller: "accomodationDetails", action: "staffQuarters")
   }
   
   def staffQuarters = {
               GrailsHttpSession gh = getSession()
	           def staffQuartersInstance = new StaffQuarters()
			   def generalLookupList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='STAFF_TYPE'")
			   def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
			   def staffQuartersInstanceList = StaffQuarters.findAll("from StaffQuarters S where S.institutionDetails = "+gh.getValue("InstId"))
			   def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
			   staffQuartersInstance.institutionDetails = institutionDetailsInstance
			   return[staffQuartersInstance:staffQuartersInstance,
			              generalLookupList:generalLookupList,
			              institutionDetailsInstanceList:institutionDetailsInstanceList,
			              staffQuartersInstanceList:staffQuartersInstanceList]
  
   }
   
   def quartersList = {
			   GrailsHttpSession gh = getSession()
			   def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.quartersList )
			   gh.putValue("InstId","${institutionDetailsInstance.id}")
			   def staffQuartersInstance = new StaffQuarters()
			   staffQuartersInstance.properties = params
			   def staffQuartersInstanceList = StaffQuarters.findAll("from StaffQuarters S  where S.institutionDetails = "+params.quartersList )
			   render (template:"listQuarters", model : ['staffQuartersInstanceList' : staffQuartersInstanceList,'staffQuartersInstance':staffQuartersInstance])
   
   }
   
   def saveStaff = {
              GrailsHttpSession gh = getSession()
              def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.InstId )
		      def categoryTypeInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.categoryType )
		     // gh.putValue("InstId","${institutionDetailsInstance.id}")
		      def staffQuartersInstance = new StaffQuarters()
		      staffQuartersInstance.institutionDetails=institutionDetailsInstance;
		      staffQuartersInstance.categoryType=categoryTypeInstance.item;
		      staffQuartersInstance.numberOfQuarters=params.numberOfQuarters;
		      
		      
		       if (staffQuartersInstance.save(flush: true)) {
		                   redirect(controller: "accomodationDetails", action: "staffQuarters")
		       }
		       else {
			                render(view:'staffQuarters',model:[staffQuartersInstance:staffQuartersInstance])
			             }
     }
     
     def editStaffQuarters = {
               def staffQuartersInstance = StaffQuarters.get(params.id)
               def generalLookupList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='STAFF_TYPE'")
			   def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
			   def categoryTypeInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+staffQuartersInstance.categoryType+"'")
		       return ['staffQuartersInstance':staffQuartersInstance, 'generalLookupList':generalLookupList, 'institutionDetailsInstanceList':institutionDetailsInstanceList,'categoryTypeInstance':categoryTypeInstance]
     }
     
     def updateStaffQuarters = {
               GrailsHttpSession gh = getSession()
		       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
		       def staffQuartersInstance = StaffQuarters.get(params.id)
		       def categoryTypeInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.categoryType )
		       if (staffQuartersInstance) {
			            if (params.version) {
			                def version = params.version.toLong()
			                if (staffQuartersInstance.version > version) {
			                    staffQuartersInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'StaffQuarters.label', default: 'StaffQuarters')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
			                    render(view: "editStaffQuarters", model: [staffQuartersInstance: staffQuartersInstance])
			                }
			            }
			            staffQuartersInstance.institutionDetails=institutionDetailsInstance;
			            staffQuartersInstance.categoryType=categoryTypeInstance.item;
		                staffQuartersInstance.numberOfQuarters=params.numberOfQuarters;
		                if (staffQuartersInstance.save(flush: true)) {
		                flash.message = "${message(code: 'Updated', args: [message(code: 'StaffQuarters.label', default: 'StaffQuarters'), staffQuartersInstance.id])}"
			                redirect(controller: "accomodationDetails", action: "staffQuarters", id: staffQuartersInstance.id)
			            }
			            else {
			                render(view: "editStaffQuarters", model: [staffQuartersInstance: staffQuartersInstance])
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'StaffQuarters.label', default: 'StaffQuarters'), params.id])}"
			             redirect(controller: "accomodationDetails", action: "staffQuarters")
			        }
			    
   }
   
     def deleteStaffQuarters = {
        def staffQuartersInstance = StaffQuarters.get(params.id)
        if (staffQuartersInstance) {
            try {
                staffQuartersInstance.delete(flush: true)
                flash.message = "${message(code: 'Deleted', args: [message(code: 'StaffQuarters.label', default: 'StaffQuarters'), params.id])}"
               redirect(controller: "accomodationDetails", action: "staffQuarters")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'Not deleted', args: [message(code: 'StaffQuarters.label', default: 'StaffQuarters'), params.id])}"
                redirect(controller: "accomodationDetails", action: "editStaffQuarters", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'Not found', args: [message(code: 'StaffQuarters.label', default: 'StaffQuarters'), params.id])}"
            redirect(controller: "accomodationDetails", action: "staffQuarters")
        }
    }
    
    def secondTab = {
               GrailsHttpSession gh = getSession()
             //gh.removeValue("InstId")
               redirect(controller: "accomodationDetails", action: "studentsHostel")
    }
    
     def studentsHostel = {
              GrailsHttpSession gh = getSession()
              def generalLookupList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='STUD_HOSTEL_CODE'")
              def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
              def studentHostelsinstance = new StudentHostels()
              def studentHostelsinstanceList = StudentHostels.findAll("from StudentHostels S where S.institutionDetails = "+gh.getValue("InstId"))
              def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
			  studentHostelsinstance.institutionDetails = institutionDetailsInstance
			  return[studentHostelsinstance:studentHostelsinstance,
			               generalLookupList:generalLookupList,
			               institutionDetailsInstanceList:institutionDetailsInstanceList,
			               studentHostelsinstanceList:studentHostelsinstanceList]
  
     }
     
     def hostelList = {
             GrailsHttpSession gh = getSession()
			 def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.hostelList )
			 gh.putValue("InstId","${institutionDetailsInstance.id}")
             def studentHostelsinstance = new StudentHostels()
			 studentHostelsinstance.properties = params
			 def studentHostelsinstanceList = StudentHostels.findAll("from StudentHostels S  where S.institutionDetails = "+params.hostelList )
			 render (template:"listHostels", model : ['studentHostelsinstanceList' : studentHostelsinstanceList,'studentHostelsinstance':studentHostelsinstance])
     }
     
     def saveHostel = {
              GrailsHttpSession gh = getSession()
              def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.InstId )
		      def hostelTypeInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.hostelType )
		     // gh.putValue("InstId","${institutionDetailsInstance.id}")
		      def studentHostelsinstance = new StudentHostels()
		      studentHostelsinstance.institutionDetails=institutionDetailsInstance;
		      studentHostelsinstance.hostelType=hostelTypeInstance.item;
		      studentHostelsinstance.name=params.name;
		      studentHostelsinstance.intakeCapacity=params.intakeCapacity;
		      studentHostelsinstance.numberOfStudents=params.numberOfStudents;
		      
		       if (studentHostelsinstance.save(flush: true)) {
		                   redirect(controller: "accomodationDetails", action: "studentsHostel")
		       }
		       else {
			                render(view:'studentsHostel',model:[studentHostelsinstance:studentHostelsinstance])
			             }
     
     }
   
   def editStudentsHostel = {
               def studentHostelsinstance = StudentHostels.get(params.id)
               def generalLookupList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='STUD_HOSTEL_CODE'")
			   def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
		       def hostelTypeInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+studentHostelsinstance.hostelType+"'")
		       return ['studentHostelsinstance':studentHostelsinstance, 'generalLookupList':generalLookupList, 'institutionDetailsInstanceList':institutionDetailsInstanceList,'hostelTypeInstance':hostelTypeInstance]
     }
     
     def updateStudentsHostel = {
              GrailsHttpSession gh = getSession()
		       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
		       def hostelTypeInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.hostelType )
		       def studentHostelsinstance = StudentHostels.get(params.id)
			        if (studentHostelsinstance) {
			            if (params.version) {
			                def version = params.version.toLong()
			                if (studentHostelsinstance.version > version) {
			                    studentHostelsinstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'StudentHostels.label', default: 'StudentHostels')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
			                    render(view: "editStudentsHostel", model: [studentHostelsinstance: studentHostelsinstance])
			                }
			            }
			            studentHostelsinstance.institutionDetails=institutionDetailsInstance;
		                studentHostelsinstance.hostelType=hostelTypeInstance.item;
		                studentHostelsinstance.name=params.name;
		                studentHostelsinstance.intakeCapacity=params.intakeCapacity;
		                studentHostelsinstance.numberOfStudents=params.numberOfStudents;
		               
		                if (studentHostelsinstance.save(flush: true)) {
			                flash.message = "${message(code: 'Updated', args: [message(code: 'StudentHostels.label', default: 'StudentHostels'), studentHostelsinstance.id])}"
			                redirect(controller: "accomodationDetails", action: "studentsHostel")
			            }
			            else {
			                render(view: "editStudentsHostel", model: [studentHostelsinstance: studentHostelsinstance])
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'StudentHostels.label', default: 'StudentHostels'), params.id])}"
			             redirect(controller: "accomodationDetails", action: "studentsHostel")
			        }
			    
   }
   
     def deleteStudentsHostel = {
        def studentHostelsinstance = StudentHostels.get(params.id)
        if (studentHostelsinstance) {
            try {
                studentHostelsinstance.delete(flush: true)
                flash.message = "${message(code: 'Deleted', args: [message(code: 'StudentHostels.label', default: 'StudentHostels'), params.id])}"
               redirect(controller: "accomodationDetails", action: "studentsHostel")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'Not deleted', args: [message(code: 'StudentHostels.label', default: 'StudentHostels'), params.id])}"
                redirect(controller: "accomodationDetails", action: "editStudentsHostel", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'Not found', args: [message(code: 'StudentHostels.label', default: 'StudentHostels'), params.id])}"
            redirect(controller: "accomodationDetails", action: "studentsHostel")
        }
    }
   

   }
