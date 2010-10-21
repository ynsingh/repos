import java.text.*;
import java.util.*;
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class ItemPurchaseController 
{
	def itemPurchaseService
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	ConvertToIndainRS currencyFormatter=new ConvertToIndainRS();
	def index = 
	{
        redirect(action: "list", params: params)
    }
	
	def list = 
	{
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [itemPurchaseInstanceList: ItemPurchase.list(params), itemPurchaseInstanceTotal: ItemPurchase.count()]
    }
    
	def create = 
    {
        println "params"+params
		def itemPurchaseInstance = new ItemPurchase()
        itemPurchaseInstance.properties = params
        println "-----------"+params.id
        def projectInstance = Projects.get(params.id)
        println "projectInstance="+projectInstance
        def itemList=ItemPurchase.list(params)
        println "itmlis="+itemList.Status
        def itemPurchaseInstanceList=itemPurchaseService.getItemPurchaseList(projectInstance)
        return [itemPurchaseInstance: itemPurchaseInstance,
                itemPurchaseInstanceList:itemPurchaseInstanceList,
                currencyFormat:currencyFormatter,
                projectInstance:projectInstance]
    }
	
	def save = 
    {
		println "params"+params
		def itemPurchaseInstance = new ItemPurchase(params)
		def projectInstance = Projects.get(params.projectId)
        itemPurchaseInstance.projects=projectInstance
        println "projectInstance="+projectInstance
        if (itemPurchaseInstance.save(flush: true)) 
        {
            flash.message = "${message(code: 'default.created.label')}"
            redirect(action: "create", id: projectInstance.id)
        }
        else 
        {
            render(view: "create", model: [itemPurchaseInstance: itemPurchaseInstance])
        }
    }

    def show = 
    {
        def itemPurchaseInstance = ItemPurchase.get(params.id)
        if (!itemPurchaseInstance) 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
        else 
        {
            [itemPurchaseInstance: itemPurchaseInstance]
        }
    }

    def edit = 
    {
        def itemPurchaseInstance = ItemPurchase.get(params.id)
        if (!itemPurchaseInstance) 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "list")
        }
        else 
        {
        	NumberFormat formatter = new DecimalFormat("#0.00");
            return [itemPurchaseInstance: itemPurchaseInstance,
                    amount:formatter.format(itemPurchaseInstance.cost)]
        }
    }

    def update = 
    {
        def itemPurchaseInstance = ItemPurchase.get(params.id)
        if (itemPurchaseInstance) 
        {
            if (params.version) 
            {
                def version = params.version.toLong()
                if (itemPurchaseInstance.version > version) 
                {
                    
                    itemPurchaseInstance.errors.rejectValue("version", "default.optimistic.locking.failure", 
                    		[message(code: 'itemPurchase.label', default: 'ItemPurchase')] 
                    as Object[], "Another user has updated this ItemPurchase while you were editing")
                    render(view: "create", model: [itemPurchaseInstance: itemPurchaseInstance])
                    return
                }
            }
            itemPurchaseInstance.properties = params
            if (!itemPurchaseInstance.hasErrors() && itemPurchaseInstance.save(flush: true)) 
            {
                flash.message = "${message(code: 'default.updated.label')}"
                redirect(action: "create", id: itemPurchaseInstance.id)
            }
            else 
            {
                render(view: "create", model: [itemPurchaseInstance: itemPurchaseInstance])
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create")
        }
    }

    def delete = 
    {
        def itemPurchaseInstance = ItemPurchase.get(params.id)
        if (itemPurchaseInstance) 
        {
            try 
            {
            	itemPurchaseInstance.Status='D'
                itemPurchaseInstance.save(flush: true)
                flash.message = "${message(code: 'default.deleted.label')}"
                redirect(action: "create")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) 
            {
                flash.message = "${message(code: 'default.inuse.label')}"
                redirect(action: "create", id: params.id)
            }
        }
        else 
        {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action: "create")
        }
    }
	def purchase = 
	{
		def grantAllocationService = new GrantAllocationService()
		GrailsHttpSession gh=getSession()
		def dataSecurityService = new DataSecurityService()
		def grandAllocationList = []
		def grantAllocationInstance
		
		def projectsList = dataSecurityService.getProjectsForLoginUser(gh.getValue("PartyID"))
		
		for(int i=0;i<projectsList.size();i++)
		{
			grantAllocationInstance = grantAllocationService.getGrantAllocationByProjects(projectsList[i].id)
			grandAllocationList.add(grantAllocationInstance)
		}
		
		[ grandAllocationList: grandAllocationList ]
	}
}
