import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import java.text.SimpleDateFormat
import java.text.*;
import java.util.*;
import groovy.sql.Sql
class InstitutionDetailsController {

    def dataSource
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

   def mainMenu = {
		        GrailsHttpSession gh = getSession()
		        gh.removeValue("InstId")
		        gh.removeValue("status")
		        def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
		        return [institutionDetailsInstanceList: institutionDetailsInstanceList]
   }

  def create = {
		        GrailsHttpSession gh = getSession()
		        gh.removeValue("InstId")
		        gh.putValue("status","create")
		        redirect(controller: "institutionDetails", action: "basicInformationOfInstitution")
    }

  def update = {
        		GrailsHttpSession gh = getSession()
        		def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.institutionDetails )
        		gh.putValue("InstId","${institutionDetailsInstance.id}")
        		gh.putValue("status","update")
        		redirect(controller: "institutionDetails", action: "basicInformationOfInstitution")
  }
  
  
   def basicInformationOfInstitution = {
                
   }
   
   
   def addressDetails = {
                GrailsHttpSession gh = getSession()
                def institutionDetailsInstance
                def stateInstance
		        if(gh.getValue("InstId"))
		        {
		        institutionDetailsInstance = InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
		        stateInstance = StateMaster.find("from StateMaster SM where SM.state='"+institutionDetailsInstance.state+"'")
		        }
		        else
		        {
		        institutionDetailsInstance = new InstitutionDetails()
		        institutionDetailsInstance.properties = params
		        }
		        def stateInstanceList = StateMaster.findAll("from StateMaster")
		        return [institutionDetailsInstance: institutionDetailsInstance, stateInstanceList: stateInstanceList, stateInstance: stateInstance]
       }
       
   
   def saveAddress = {
              GrailsHttpSession gh = getSession()
		      def institutionDetailsInstance = new InstitutionDetails()
		      def stateInstance = StateMaster.find("from StateMaster S where S.id="+params.state )
		      institutionDetailsInstance.institutionName=params.institutionName;
		      institutionDetailsInstance.postalAddress=params.postalAddress;
		      institutionDetailsInstance.state=stateInstance.state;
		      institutionDetailsInstance.district=params.district;
		      institutionDetailsInstance.website=params.website;
		      institutionDetailsInstance.totalArea=params.totalArea;
		      institutionDetailsInstance.totalConstructedArea=params.totalConstructedArea;
		      institutionDetailsInstance.establishedYear=params.establishedYear;
		      institutionDetailsInstance.location=""
		      institutionDetailsInstance.statutoryBody=""
		      institutionDetailsInstance.institutionType=""
		      institutionDetailsInstance.autonomous=""
		      institutionDetailsInstance.management=""
              institutionDetailsInstance.specialization=""
              institutionDetailsInstance.eveningCollege=""
              institutionDetailsInstance.girlsExclusive=""
               if (institutionDetailsInstance.save(flush: true)) {
                      gh.putValue("InstId","${institutionDetailsInstance.id}")
                       flash.message = "The Basic Information of Institution ${institutionDetailsInstance.institutionName} is succesfully saved. Kindly proceed for further details"
		              redirect(controller: "institutionDetails", action: "addressDetails", id: institutionDetailsInstance.id)
		        }
		        else {
			                render(view:'addressDetails',model:[institutionDetailsInstance: institutionDetailsInstance])
			            }
             
    }
   
   def updateAddress = {
            def institutionDetailsInstance = InstitutionDetails.get(params.InstId)
			        if (institutionDetailsInstance) {
			            if (params.version) {
			                def version = params.version.toLong()
			                if (institutionDetailsInstance.version > version) {
			                    institutionDetailsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'InstitutionDetails.label', default: 'InstitutionDetails')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
			                    render(view: "addressDetails", model: [institutionDetailsInstance: institutionDetailsInstance])
			                }
			            }
			            def stateInstance = StateMaster.find("from StateMaster S where S.id="+params.state )
				        institutionDetailsInstance.institutionName=params.institutionName;
				        institutionDetailsInstance.postalAddress=params.postalAddress;
				        institutionDetailsInstance.state=stateInstance.state;
				        institutionDetailsInstance.district=params.district;
				        institutionDetailsInstance.website=params.website;
				        institutionDetailsInstance.totalArea=params.totalArea;
				        institutionDetailsInstance.totalConstructedArea=params.totalConstructedArea;
				        institutionDetailsInstance.establishedYear=params.establishedYear;
			            if (institutionDetailsInstance.save(flush: true)) {
			                flash.message = "${message(code: 'Succesfully Updated', args: [message(code: 'InstitutionDetails.label', default: 'InstitutionDetails'), institutionDetailsInstance.id])}"
			                redirect(controller: "institutionDetails", action: "addressDetails", id: institutionDetailsInstance.id)
			            }
			            else {
			                render(view: "addressDetails", model: [institutionDetailsInstance: institutionDetailsInstance])
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'InstitutionDetails.label', default: 'InstitutionDetails'), params.id])}"
			             redirect(controller: "institutionDetails", action: "addressDetails")
			        }
   }
   
   
   def nodalOfficerDetails = {
       		   def nodalOfficerDetailsInstance = new NodalOfficerDetails()
		       nodalOfficerDetailsInstance.properties = params   
		       GrailsHttpSession gh = getSession()
		       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
		       def nodalOfficerDetailsInstanceList = NodalOfficerDetails.findAll("from NodalOfficerDetails N where N.institutionDetails.id = "+gh.getValue("InstId") )
		       return ['nodalOfficerDetailsInstance':nodalOfficerDetailsInstance,
		                   'institutionDetailsInstance':institutionDetailsInstance,
		                   'nodalOfficerDetailsInstanceList': nodalOfficerDetailsInstanceList]
    }
    
    
     def createNodalOfficer = {
		       GrailsHttpSession gh = getSession()
		       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
		       def nodalOfficerDetailsInstance = new NodalOfficerDetails()
		       nodalOfficerDetailsInstance.institutionDetails=institutionDetailsInstance;
		       nodalOfficerDetailsInstance.name=params.name;
		       nodalOfficerDetailsInstance.designation=params.designation;
		       nodalOfficerDetailsInstance.contactNumber=params.contactNumber;
		       nodalOfficerDetailsInstance.emailId=params.emailId;
		      
		       if (nodalOfficerDetailsInstance.save(flush: true)) {
		              flash.message = "${message(code: 'Succesfully Created', args: [message(code: 'NodalOfficerDetails.label', default: 'NodalOfficerDetails'), nodalOfficerDetailsInstance.id])}"
		              redirect(controller: "institutionDetails", action: "nodalOfficerDetails", id: nodalOfficerDetailsInstance.id)
		        }
		        else {
			                render(view:'nodalOfficerDetails',model:[nodalOfficerDetailsInstance:nodalOfficerDetailsInstance])
			            }
   }
   
   def editNodalOfficer = {
               def nodalOfficerDetailsInstance = NodalOfficerDetails.get(params.id)
		        return ['nodalOfficerDetailsInstance':nodalOfficerDetailsInstance]
   }
		                     
   def updateNodalOfficer = {
               GrailsHttpSession gh = getSession()
		       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
		       def nodalOfficerDetailsInstance = NodalOfficerDetails.get(params.id)
			        if (nodalOfficerDetailsInstance) {
			            if (params.version) {
			                def version = params.version.toLong()
			                if (nodalOfficerDetailsInstance.version > version) {
			                    nodalOfficerDetailsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'NodalOfficerDetails.label', default: 'NodalOfficerDetails')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
			                    render(view: "editNodalOfficer", model: [nodalOfficerDetailsInstance: nodalOfficerDetailsInstance])
			                }
			            }
			            nodalOfficerDetailsInstance.institutionDetails=institutionDetailsInstance;
			            nodalOfficerDetailsInstance.name=params.name;
		                nodalOfficerDetailsInstance.designation=params.designation;
		                nodalOfficerDetailsInstance.contactNumber=params.contactNumber;
		                nodalOfficerDetailsInstance.emailId=params.emailId;
			            if (nodalOfficerDetailsInstance.save(flush: true)) {
			                flash.message = "${message(code: 'Succesfully Updated', args: [message(code: 'NodalOfficerDetails.label', default: 'NodalOfficerDetails'), nodalOfficerDetailsInstance.id])}"
			                redirect(controller: "institutionDetails", action: "nodalOfficerDetails", id: nodalOfficerDetailsInstance.id)
			            }
			            else {
			                render(view: "editNodalOfficer", model: [nodalOfficerDetailsInstance: nodalOfficerDetailsInstance])
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'NodalOfficerDetails.label', default: 'NodalOfficerDetails'), params.id])}"
			             redirect(controller: "institutionDetails", action: "nodalOfficerDetails")
			        }
			    
   }
   
     def deleteNodalOfficer = {
        def nodalOfficerDetailsInstance = NodalOfficerDetails.get(params.id)
        if (nodalOfficerDetailsInstance) {
            try {
                nodalOfficerDetailsInstance.delete(flush: true)
                flash.message = "${message(code: 'Deleted', args: [message(code: 'institutionDetails.label', default: 'InstitutionDetails'), params.id])}"
                redirect(controller: "institutionDetails", action: "nodalOfficerDetails")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'Not deleted', args: [message(code: 'institutionDetails.label', default: 'InstitutionDetails'), params.id])}"
                redirect(controller: "institutionDetails", action: "editNodalOfficer", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'Not found', args: [message(code: 'institutionDetails.label', default: 'InstitutionDetails'), params.id])}"
            redirect(controller: "institutionDetails", action: "nodalOfficerDetails")
        }
    }
   
   
   def affiliationDetails = {
              GrailsHttpSession gh = getSession()
		      def affiliationDetailsInstance = new AffiliationDetails() 
		      affiliationDetailsInstance.properties = params
		      def affiliationDetailsInstanceList = AffiliationDetails.findAll("from AffiliationDetails A where A.institutionDetails.id = "+gh.getValue("InstId") )
		      return [affiliationDetailsInstance: affiliationDetailsInstance, affiliationDetailsInstanceList: affiliationDetailsInstanceList]
    }
   
   def saveAffiliationDetails = {
            GrailsHttpSession gh = getSession()
	        def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
	        def affiliationDetailsInstance = new AffiliationDetails() 
	        if (params.universityName)
	        {
	        affiliationDetailsInstance.institutionDetails=institutionDetailsInstance;
	        affiliationDetailsInstance.universityCode=params.universityCode;
	        affiliationDetailsInstance.universityName=params.universityName;
	        affiliationDetailsInstance.yearOfAffiliation=params.yearOfAffiliation;
	         if (affiliationDetailsInstance.save(flush: true)) {
	                  flash.message = "${message(code: 'Affiliation Details saved', args: [message(code: 'AffiliationDetails.label', default: 'AffiliationDetails'), params.id])}"
		              redirect(controller: "institutionDetails", action: "affiliationDetails", id: affiliationDetailsInstance.id)
		     }
		        else {
			                render(view:'affiliationDetails',model:[affiliationDetailsInstance:affiliationDetailsInstance])
			            }
			 }
			 else {
			        redirect(controller: "institutionDetails", action: "affiliationDetails")
			         }           
   }
   
   def editAffiliation = {
           def affiliationDetailsInstance = AffiliationDetails.get(params.id)
		        return ['affiliationDetailsInstance':affiliationDetailsInstance]
   }
   
   def updateAffiliation = {
           GrailsHttpSession gh = getSession()
		       def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
		       def affiliationDetailsInstance = AffiliationDetails.get(params.id)
			        if (affiliationDetailsInstance) {
			            if (params.version) {
			                def version = params.version.toLong()
			                if (affiliationDetailsInstance.version > version) {
			                    affiliationDetailsInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'AffiliationDetails.label', default: 'AffiliationDetails')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
			                    render(view: "editAffiliation", model: [affiliationDetailsInstance: affiliationDetailsInstance])
			                }
			            }
			             affiliationDetailsInstance.institutionDetails=institutionDetailsInstance;
	        			affiliationDetailsInstance.universityCode=params.universityCode;
				        affiliationDetailsInstance.universityName=params.universityName;
				        affiliationDetailsInstance.yearOfAffiliation=params.yearOfAffiliation;
			            if (affiliationDetailsInstance.save(flush: true)) {
			                flash.message = "${message(code: 'Succesfully Updated', args: [message(code: 'AffiliationDetails.label', default: 'AffiliationDetails'), affiliationDetailsInstance.id])}"
			                redirect(controller: "institutionDetails", action: "affiliationDetails", id: affiliationDetailsInstance.id)
			            }
			            else {
			                render(view: "editAffiliation", model: [affiliationDetailsInstance: affiliationDetailsInstance])
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'AffiliationDetails.label', default: 'AffiliationDetails'), params.id])}"
			             redirect(controller: "institutionDetails", action: "affiliationDetails")
			        }
   }
   
   def deleteAffiliation = {
           def affiliationDetailsInstance = AffiliationDetails.get(params.id)
           if (affiliationDetailsInstance) {
            try {
                affiliationDetailsInstance.delete(flush: true)
                flash.message = "${message(code: 'Deleted', args: [message(code: 'AffiliationDetails.label', default: 'AffiliationDetails'), params.id])}"
                redirect(controller: "institutionDetails", action: "affiliationDetails")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'Not deleted', args: [message(code: 'AffiliationDetails.label', default: 'AffiliationDetails'), params.id])}"
                redirect(controller: "institutionDetails", action: "editAffiliation", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'Not found', args: [message(code: 'AffiliationDetails.label', default: 'AffiliationDetails'), params.id])}"
            redirect(controller: "institutionDetails", action: "affiliationDetails")
        }
   }
   
   def moreInformation = {
           GrailsHttpSession gh = getSession()
	       def locationInstanceList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='LOCATION'")
	       def statutoryBodyInstanceList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='STATUTORY_BODY'")
	       def institutionTypeInstanceList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='INSTITUTION_TYPE'")
	       def managementInstanceList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='MANAGEMENT'")
	       def specializationInstanceList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='SPECIALIZATION'")
	       def institutionDetailsInstance
	       def locationInstance
	       def statutoryBodyInstance
	       def institutionTypeInstance
	       def managementInstance
	       def specializationInstance
	       if(gh.getValue("InstId"))
		        {
		        institutionDetailsInstance = InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
		        locationInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+institutionDetailsInstance.location+"'")
      			institutionTypeInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+institutionDetailsInstance.institutionType+"'")
		        managementInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+institutionDetailsInstance.management+"'")
		        specializationInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+institutionDetailsInstance.specialization+"'")
		        statutoryBodyInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+institutionDetailsInstance.statutoryBody+"'")
		        }
	      return[locationInstanceList: locationInstanceList, statutoryBodyInstanceList: statutoryBodyInstanceList, institutionTypeInstanceList: institutionTypeInstanceList,
	                  managementInstanceList: managementInstanceList, specializationInstanceList: specializationInstanceList, locationInstance: locationInstance,
	                  institutionTypeInstance: institutionTypeInstance, managementInstance: managementInstance, specializationInstance: specializationInstance, 
	                  institutionDetailsInstance: institutionDetailsInstance, statutoryBodyInstance: statutoryBodyInstance]
    }
   

	def saveInfo = {
		     //def sqlIns = Sql.newInstance(dataSource)
		     //sqlIns.execute("insert into statutory_body(institution_details_id, statutory_body) values ( '${gh.getValue("InstId")}', ${params.statutoryBody})") 
			
             def institutionDetailsInstance = InstitutionDetails.get(params.InstId)
             //def statutoryBodyInstance[] = GeneralLookup.find("from GeneralLookup G where G.id="+params.statutoryBody )
            def locationInstance = GeneralLookup.find("from GeneralLookup G where G.id="+params.location )
            def institutionTypeInstance = GeneralLookup.find("from GeneralLookup G where G.id="+params.institutionType )
            def managementInstance = GeneralLookup.find("from GeneralLookup G where G.id="+params.management )
            def specializationInstance = GeneralLookup.find("from GeneralLookup G where G.id="+params.specialization )
            if (institutionDetailsInstance) {
            
              if(locationInstance){institutionDetailsInstance.location=locationInstance.item;}
		      else{institutionDetailsInstance.location="";}
		      //if(statutoryBodyInstance){institutionDetailsInstance.statutoryBody=statutoryBodyInstance.item;}
		     // else{institutionDetailsInstance.statutoryBody="";}
		      if(institutionTypeInstance){institutionDetailsInstance.institutionType=institutionTypeInstance.item;}
		      else{institutionDetailsInstance.institutionType="";}
		      if(managementInstance){institutionDetailsInstance.management=managementInstance.item;}
		      else{institutionDetailsInstance.management="";}
		      if(specializationInstance){institutionDetailsInstance.specialization=specializationInstance.item;}
		      else{institutionDetailsInstance.specialization="";}
		      
		      institutionDetailsInstance.statutoryBody=params.statutoryBody
		      institutionDetailsInstance.autonomous=params.autonomous
		      institutionDetailsInstance.eveningCollege=params.eveningCollege
              institutionDetailsInstance.girlsExclusive=params.girlsExclusive
               if (institutionDetailsInstance.save(flush: true)) {
                      flash.message = "The information is succesfully saved"
                      redirect(controller: "institutionDetails", action: "moreInformation")
           		        }
		        else {
			                render(view:'moreInformation',model:[institutionDetailsInstance: institutionDetailsInstance])
			            }
            
            }
   }
   
  def success = {
   }
   
  }
