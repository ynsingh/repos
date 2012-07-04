import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import java.text.SimpleDateFormat
import java.text.*;
import java.util.*;
import groovy.sql.Sql
class MiscellaneousController {

    def dataSource
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    
    def index = {
            GrailsHttpSession gh = getSession()
            //gh.removeValue("InstId")
            redirect(controller: "miscellaneous", action: "miscInformation")   
    }
    
    def miscInformation = {
    }
    
    
    def firstTab = {
            GrailsHttpSession gh = getSession()
            //gh.removeValue("InstId")
            redirect(controller: "miscellaneous", action: "income") 
    }
    
    def income = {
           GrailsHttpSession gh = getSession()
           def financialInfoInstance = new FinancialInfo()
           def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
           def itemList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='INCOME'")
           def financialInfoInstanceList = FinancialInfo.findAll("from FinancialInfo F where F.incOrExp = 'INCOME' and F.institutionDetails ="+gh.getValue("InstId"))
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
           financialInfoInstance.institutionDetails = institutionDetailsInstance
           return [institutionDetailsInstanceList: institutionDetailsInstanceList, itemList: itemList, financialInfoInstance: financialInfoInstance,
                       financialInfoInstanceList: financialInfoInstanceList]
    }
    
    def incomeList = {
           GrailsHttpSession gh = getSession()
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id ="+params.incomeList )
		   gh.putValue("InstId","${institutionDetailsInstance.id}")
		   def financialInfoInstanceList = FinancialInfo.findAll("from FinancialInfo F where F.incOrExp = 'INCOME' and F.institutionDetails ="+params.incomeList)
		   render (template:"listIncome", model : ['financialInfoInstanceList' : financialInfoInstanceList])
    }
    
    def saveIncome = {
           GrailsHttpSession gh = getSession()
           def financialInfoInstance = new FinancialInfo()
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.InstId )
           //gh.putValue("InstId","${institutionDetailsInstance.id}")
           def itemInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.item )
           if(itemInstance){financialInfoInstance.item=itemInstance.item;}
		      else{financialInfoInstance.item="";}
		   financialInfoInstance.institutionDetails=institutionDetailsInstance;
		   financialInfoInstance.incOrExp="INCOME";
		   financialInfoInstance.amount=params.amount;
		    if (financialInfoInstance.save(flush: true)) {
		                   redirect(controller: "miscellaneous", action: "income")    
		       }
		       else {
			                render(view:'income',model:[financialInfoInstance: financialInfoInstance])
			             }
           
    }
    
    def editIncome = {
           GrailsHttpSession gh = getSession()
           def financialInfoInstance = FinancialInfo.get(params.id)
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
           def itemInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+financialInfoInstance.item+"'")
           def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
           def itemList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='INCOME'")
           return [financialInfoInstance: financialInfoInstance, institutionDetailsInstance: institutionDetailsInstance, 
                       itemInstance: itemInstance, institutionDetailsInstanceList: institutionDetailsInstanceList, itemList: itemList]
    }
    
    def updateIncome = {
           GrailsHttpSession gh = getSession()
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.institutionDetails)
           def financialInfoInstance = FinancialInfo.get(params.id)
           def itemInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.item )
           if (financialInfoInstance) {
			            if (params.version) {
			                def version = params.version.toLong()
			                if (financialInfoInstance.version > version) {
			                    financialInfoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'FinancialInfo.label', default: 'FinancialInfo')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
			                    render(view: "editIncome", model: [financialInfoInstance: financialInfoInstance])
			                }
			            }
			     		      
		      if(itemInstance){financialInfoInstance.item=itemInstance.item;}
		      else{financialInfoInstance.item="";}
		      
		      financialInfoInstance.institutionDetails=institutionDetailsInstance;
		      financialInfoInstance.amount=params.amount;
		                if (financialInfoInstance.save(flush: true)) {
		                flash.message = "${message(code: 'Updated', args: [message(code: 'FinancialInfo.label', default: 'FinancialInfo'), financialInfoInstance.id])}"
			                redirect(controller: "miscellaneous", action: "income", id: financialInfoInstance.id)
			            }
			            else {
			                render(view: "editIncome", model: [financialInfoInstance: financialInfoInstance])
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'FinancialInfo.label', default: 'FinancialInfo'), params.id])}"
			             redirect(controller: "miscellaneous", action: "income")
			        }
           
    }
    
    def deleteIncome = {
            def financialInfoInstance = FinancialInfo.get(params.id)
			        if (financialInfoInstance) {
			            try {
			                financialInfoInstance.delete(flush: true)
			                flash.message = "${message(code: 'Deleted', args: [message(code: 'FinancialInfo.label', default: 'FinancialInfo'), params.id])}"
			               redirect(controller: "miscellaneous", action: "income")
			            }
			            catch (org.springframework.dao.DataIntegrityViolationException e) {
			                flash.message = "${message(code: 'Not deleted', args: [message(code: 'FinancialInfo.label', default: 'FinancialInfo'), params.id])}"
			                redirect(controller: "miscellaneous", action: "editIncome", id: params.id)
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'FinancialInfo.label', default: 'FinancialInfo'), params.id])}"
			            redirect(controller: "miscellaneous", action: "income")
			        }  
    }
    
    def secondTab = {
            GrailsHttpSession gh = getSession()
            //gh.removeValue("InstId")
            redirect(controller: "miscellaneous", action: "expense") 
    }
    
    def expense = {
            GrailsHttpSession gh = getSession()
            def financialInfoInstance = new FinancialInfo()
            def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
            def itemList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='EXPENSE'")
            def financialInfoInstanceList = FinancialInfo.findAll("from FinancialInfo F where F.incOrExp = 'EXPENSE' and F.institutionDetails ="+gh.getValue("InstId"))
            def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
            financialInfoInstance.institutionDetails = institutionDetailsInstance
            return [institutionDetailsInstanceList: institutionDetailsInstanceList, itemList: itemList, financialInfoInstance: financialInfoInstance,
                       financialInfoInstanceList: financialInfoInstanceList]
    }
    
    def expenseList = {
           GrailsHttpSession gh = getSession()
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id ="+params.expenseList )
		   gh.putValue("InstId","${institutionDetailsInstance.id}")
		   def financialInfoInstanceList = FinancialInfo.findAll("from FinancialInfo F where F.incOrExp = 'EXPENSE' and F.institutionDetails ="+params.expenseList)
		   render (template:"listExpense", model : ['financialInfoInstanceList' : financialInfoInstanceList])
    }
    
    def saveExpense = {
           GrailsHttpSession gh = getSession()
           def financialInfoInstance = new FinancialInfo()
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.InstId )
           //gh.putValue("InstId","${institutionDetailsInstance.id}")
           def itemInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.item )
           if(itemInstance){financialInfoInstance.item=itemInstance.item;}
		      else{financialInfoInstance.item="";}
		   financialInfoInstance.institutionDetails=institutionDetailsInstance;
		   financialInfoInstance.incOrExp="EXPENSE";
		   financialInfoInstance.amount=params.amount;
		    if (financialInfoInstance.save(flush: true)) {
		                   redirect(controller: "miscellaneous", action: "expense")    
		       }
		       else {
			                render(view:'expense',model:[financialInfoInstance: financialInfoInstance])
			             }
    }
    
    def editExpense = {
           GrailsHttpSession gh = getSession()
           def financialInfoInstance = FinancialInfo.get(params.id)
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
           def itemInstance = GeneralLookup.find("from GeneralLookup GL where GL.item='"+financialInfoInstance.item+"'")
           def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
           def itemList = GeneralLookup.findAll("from GeneralLookup G where G.typeCode='EXPENSE'")
           return [financialInfoInstance: financialInfoInstance, institutionDetailsInstance: institutionDetailsInstance, 
                       itemInstance: itemInstance, institutionDetailsInstanceList: institutionDetailsInstanceList, itemList: itemList]
    }
    
    def updateExpense = {
           GrailsHttpSession gh = getSession()
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.institutionDetails)
           def financialInfoInstance = FinancialInfo.get(params.id)
           def itemInstance = GeneralLookup.find("from GeneralLookup GL where GL.id="+params.item )
           if (financialInfoInstance) {
			            if (params.version) {
			                def version = params.version.toLong()
			                if (financialInfoInstance.version > version) {
			                    financialInfoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'FinancialInfo.label', default: 'FinancialInfo')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
			                    render(view: "editExpense", model: [financialInfoInstance: financialInfoInstance])
			                }
			            }
			     		      
		      if(itemInstance){financialInfoInstance.item=itemInstance.item;}
		      else{financialInfoInstance.item="";}
		      
		      financialInfoInstance.institutionDetails=institutionDetailsInstance;
		      financialInfoInstance.amount=params.amount;
		                if (financialInfoInstance.save(flush: true)) {
		                flash.message = "${message(code: 'Updated', args: [message(code: 'FinancialInfo.label', default: 'FinancialInfo'), financialInfoInstance.id])}"
			                redirect(controller: "miscellaneous", action: "expense", id: financialInfoInstance.id)
			            }
			            else {
			                render(view: "editExpense", model: [financialInfoInstance: financialInfoInstance])
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'FinancialInfo.label', default: 'FinancialInfo'), params.id])}"
			             redirect(controller: "miscellaneous", action: "expense")
			        }
           
    }
    
    def deleteExpense = {
           def financialInfoInstance = FinancialInfo.get(params.id)
			        if (financialInfoInstance) {
			            try {
			                financialInfoInstance.delete(flush: true)
			                flash.message = "${message(code: 'Deleted', args: [message(code: 'FinancialInfo.label', default: 'FinancialInfo'), params.id])}"
			               redirect(controller: "miscellaneous", action: "expense")
			            }
			            catch (org.springframework.dao.DataIntegrityViolationException e) {
			                flash.message = "${message(code: 'Not deleted', args: [message(code: 'FinancialInfo.label', default: 'FinancialInfo'), params.id])}"
			                redirect(controller: "miscellaneous", action: "editExpense", id: params.id)
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'FinancialInfo.label', default: 'FinancialInfo'), params.id])}"
			            redirect(controller: "miscellaneous", action: "expense")
			        }  
    }
    
    
    def thirdTab = {
           GrailsHttpSession gh = getSession()
           //gh.removeValue("InstId")
           redirect(controller: "miscellaneous", action: "infrastructure") 
    }
    
    def infrastructure = {
           GrailsHttpSession gh = getSession()
           def infrastructureInstance = new Infrastructure()
           def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
           def infrastructureInstanceList = Infrastructure.findAll("from Infrastructure I where I.institutionDetails ="+gh.getValue("InstId"))
           infrastructureInstance.institutionDetails = institutionDetailsInstance
           return [institutionDetailsInstanceList: institutionDetailsInstanceList, infrastructureInstance: infrastructureInstance,
                       infrastructureInstanceList: infrastructureInstanceList]
    }
    
    def infrastructureList = {
           GrailsHttpSession gh = getSession()
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id ="+params.infrastructureList )
		   gh.putValue("InstId","${institutionDetailsInstance.id}")
		   def infrastructureInstanceList = Infrastructure.findAll("from Infrastructure I where I.institutionDetails ="+params.infrastructureList)
		   render (template:"listinfrastructure", model : ['infrastructureInstanceList' : infrastructureInstanceList])
    }
    
    
    def saveInfrastructure = {
           GrailsHttpSession gh = getSession()
           def infrastructureInstance = new Infrastructure()
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.InstId )
           //gh.putValue("InstId","${institutionDetailsInstance.id}")
           infrastructureInstance.institutionDetails=institutionDetailsInstance;
		   infrastructureInstance.infrastructure=params.infrastructure;
		   infrastructureInstance.description=params.description;
		    if (infrastructureInstance.save(flush: true)) {
		                   redirect(controller: "miscellaneous", action: "infrastructure")    
		       }
		       else {
			                render(view:'infrastructure',model:[infrastructureInstance: infrastructureInstance])
			             }
    }
    
    def editInfrastructure = {
           GrailsHttpSession gh = getSession()
           def infrastructureInstance = Infrastructure.get(params.id)
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+gh.getValue("InstId") )
           def institutionDetailsInstanceList =InstitutionDetails.findAll("from InstitutionDetails ID" )
           return [infrastructureInstance: infrastructureInstance, institutionDetailsInstance: institutionDetailsInstance, 
                       institutionDetailsInstanceList: institutionDetailsInstanceList]
    }
    
    def updateInfrastructure = {
           GrailsHttpSession gh = getSession()
           def institutionDetailsInstance =InstitutionDetails.find("from InstitutionDetails ID where ID.id="+params.institutionDetails)
           def infrastructureInstance = Infrastructure.get(params.id)
           if (infrastructureInstance) {
			            if (params.version) {
			                def version = params.version.toLong()
			                if (infrastructureInstance.version > version) {
			                    infrastructureInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'Infrastructure.label', default: 'Infrastructure')] as Object[], "Another user has updated this InstitutionDetails while you were editing")
			                    render(view: "editInfrastructure", model: [infrastructureInstance: infrastructureInstance])
			                }
			            }
			     		      
		      infrastructureInstance.institutionDetails=institutionDetailsInstance;
		      infrastructureInstance.infrastructure=params.infrastructure;
		      infrastructureInstance.description=params.description;
		                if (infrastructureInstance.save(flush: true)) {
		                flash.message = "${message(code: 'Updated', args: [message(code: 'Infrastructure.label', default: 'Infrastructure'), infrastructureInstance.id])}"
			                redirect(controller: "miscellaneous", action: "infrastructure", id: infrastructureInstance.id)
			            }
			            else {
			                render(view: "editInfrastructure", model: [infrastructureInstance: infrastructureInstance])
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'Infrastructure.label', default: 'Infrastructure'), params.id])}"
			             redirect(controller: "miscellaneous", action: "infrastructure")
			        }
    }
    
    def deleteInfrastructure = {
             def infrastructureInstance = Infrastructure.get(params.id)
			        if (infrastructureInstance) {
			            try {
			                infrastructureInstance.delete(flush: true)
			                flash.message = "${message(code: 'Deleted', args: [message(code: 'Infrastructure.label', default: 'Infrastructure'), params.id])}"
			               redirect(controller: "miscellaneous", action: "infrastructure")
			            }
			            catch (org.springframework.dao.DataIntegrityViolationException e) {
			                flash.message = "${message(code: 'Not deleted', args: [message(code: 'Infrastructure.label', default: 'Infrastructure'), params.id])}"
			                redirect(controller: "miscellaneous", action: "editInfrastructure", id: params.id)
			            }
			        }
			        else {
			            flash.message = "${message(code: 'Not found', args: [message(code: 'Infrastructure.label', default: 'Infrastructure'), params.id])}"
			            redirect(controller: "miscellaneous", action: "infrastructure")
			        }  
    }
}