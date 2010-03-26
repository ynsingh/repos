	import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
	import java.util.Iterator;

class GrantAllocationController extends GmsController {

    def index = { 
    		
    		redirect(action:list,params:params) 
    		}

	

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST',funtSave:'POST']

    def list = {
    	GrailsHttpSession gh=getSession()
    		
        if(!params.max) params.max = 10
        
    	def dataSecurityService = new DataSecurityService()
        def grantAllocationInstanceList=dataSecurityService.getGrantAllocationsForLoginUser(gh.getValue("ProjectID"),gh.getValue("PartyID"))
        		
        [ grantAllocationInstanceList: grantAllocationInstanceList ]
    }
    
    def delete = {
		def grantAllocationService = new GrantAllocationService()
        def dataSecurityService = new DataSecurityService()
		   def grantAllocationInstance=grantAllocationService.getGrantAllocationById(new Integer(params.id))

		def grantAllocationId = grantAllocationService.deleteGrantAllocation(new Integer(params.id))
		if(grantAllocationId != null){
			if(grantAllocationId > 0){
				GrailsHttpSession gh=getSession()
	            if(gh.getValue("fromUrL")==null) {
	            	redirect(action:'fundAllot')
	                flash.message = "Fund Allocation deleted"
                }
                else {
                	if(gh.getValue("fromUrL")=="subGrantAllotExt")
                	redirect(action:'subGrantAllotExt',id:gh.getValue("fromID"))
                	else
                		redirect(action:'subGrantAllot',id:gh.getValue("fromID"))	
                	flash.message = "Project Allocation deleted"
                }
			}
			else {
	            flash.message = "GrantAllocation not found with id ${params.id}"
	            redirect(action:list)
	        }
		}
		
    }

    def edit = {
		def grantAllocationService = new GrantAllocationService()
        def grantAllocationInstance=grantAllocationService.getGrantAllocationById(new Integer(params.id))
          def dataSecurityService = new DataSecurityService()
		//checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(grantAllocationInstance.projects.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{
        if(!grantAllocationInstance) {
            flash.message = "GrantAllocation not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ grantAllocationInstance : grantAllocationInstance ]
        }
		}
    }
    

    def update = 
    {
    	println "*******************************Inside update***************"
    	def grantAllocationService = new GrantAllocationService()
    	def grantAllocation = grantAllocationService.checkGrantAllotted(params)
    	if(grantAllocation)
    	{
	    	flash.message = "Amount cannot be changed, as it is already allocated to sub-projects."
	    	redirect(action:edit,id:params.id)
    	}
    	else
    	{
	    	def grantAllocationInstance = grantAllocationService.updateGrantAllocation(params)
			if(grantAllocationInstance)
			{
				if(grantAllocationInstance.isSaved)
				{
					GrailsHttpSession gh=getSession()
					if(gh.getValue("fromUrL")==null)
					{
						redirect(action:'fundAllot',id:grantAllocationInstance.id)
		                flash.message = "Fund Allocation updated"
					}
					else
					{
						redirect(action:'create',id:gh.getValue("fromID"))
						flash.message = "Project Allocation updated"
					}
				}
				else
					render(view:'edit',model:[grantAllocationInstance:grantAllocationInstance])
			}
			else 
			{
				flash.message = "GrantAllocation not found with id ${params.id}"
				redirect(action:edit,id:params.id)
			}
    	}
    }
    
    def fundList = {
		GrailsHttpSession gh=getSession()
        def grantAllocationInstance = new GrantAllocation()
        grantAllocationInstance.properties = params
        
        def dataSecurityService = new DataSecurityService()
        def grantAllocationInstanceList=dataSecurityService.getGrantAllocationsForLoginUser(
        		"Fund",gh.getValue("ProjectID"),gh.getValue("PartyID"))
        
		println grantAllocationInstanceList
        return ['grantAllocationInstance':grantAllocationInstance,'grantAllocationInstanceList':grantAllocationInstanceList]
    }
    
    def fundAllot = {
    		
    		GrailsHttpSession gh=getSession()
        def grantAllocationInstance = new GrantAllocation()
        grantAllocationInstance.properties = params
        
        def grantAllocationService = new GrantAllocationService()
    		 def dataSecurityService = new DataSecurityService()
    		String subQuery="";
            if(params.sort != null && !params.sort.equals(""))
           	subQuery=" order by GA."+params.sort
           if(params.order != null && !params.order.equals(""))
           	subQuery =subQuery+" "+params.order
           
         def grantAllocationInstanceList=grantAllocationService.getGrantAllocations(gh.getValue("PartyID"),subQuery)
         
         def projectsList=dataSecurityService.getProjectsForLoginUser(gh.getValue("PartyID"))
        	def partyinstance=Party.get(gh.getValue("Party"))
        	println partyinstance
        return ['grantAllocationInstance':grantAllocationInstance,'grantAllocationInstanceList':grantAllocationInstanceList,'projectsList':projectsList,'partyinstance':partyinstance]
    }
        
    def funtSave = {
    		println "*******************************IfuntSave***************"
        def grantAllocationInstance = new GrantAllocation(params)

        	grantAllocationInstance.createdBy="admin"
    		grantAllocationInstance.modifiedBy="admin"
    		grantAllocationInstance.DateOfAllocation=new Date();
    	    grantAllocationInstance.allocationType="Fund"
    	    grantAllocationInstance.sanctionOrderNo="";
    	    grantAllocationInstance.code=""
    	    def granterInstance=Party.get(new Integer(params.grantor))
    				       
    		grantAllocationInstance.party=granterInstance
    	   	def grantAllocationService = new GrantAllocationService()
    	    
		    def fundAllotId = grantAllocationService.saveFundAllocation(grantAllocationInstance)
		    if(fundAllotId != null)
		    {
		    	flash.message = "Fund Allocation  created"
	    		redirect(action:'fundAllot')
		    }else{flash.message = "Fund Allocation is not created"
	    		redirect(action:'fundAllot')}
    	    
        
    }
    
    def subGrantAllot = 
    {
    		
    	GrailsHttpSession gh=getSession()
    		
    	gh.putValue("fromUrL", "subGrantAllot");
    	gh.putValue("fromID", params.id);
    	def grantAllocationService = new GrantAllocationService()
    	def grantAllocation=grantAllocationService.getGrantAllocationById(new Integer(params.id))
    	def dataSecurityService = new DataSecurityService()
    	//checking  whether the user has access to the given projects
    	if(dataSecurityService.checkForAuthorisedAcsessInProjects(new Integer(params.id),new Integer(getUserPartyID()))==0)
    	{
    		 redirect uri:'/invalidAccess.gsp'
		}
		else
		{
	        def grantAllocationInstance = new GrantAllocation()
	        grantAllocationInstance.properties = params
	        
	        def projectInstance = Projects.get( params.id )
	        projectInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectInstance.id,getUserPartyID())
	       
	      
	        def partyInstance=Party.get(gh.getValue("Party"))
	        grantAllocationInstance.granter=partyInstance;
	        println "params"+params.id
	        String subQuery="";
            if(params.sort != null && !params.sort.equals(""))
           	subQuery=" order by GA."+params.sort
           if(params.order != null && !params.order.equals(""))
           	subQuery =subQuery+" "+params.order
	        def grantAllocationInstanceList = grantAllocationService.getSubGrantAllocationsChild(new Integer(params.id),getUserPartyID(),subQuery)
	        double sumAmountAllocated = 0.00
	        for(int i=0;i<grantAllocationInstanceList.size();i++)
	        {
	        	sumAmountAllocated = sumAmountAllocated + grantAllocationInstanceList[i].amountAllocated
	        }
	        grantAllocationInstance.totAllAmount = sumAmountAllocated
	        return ['grantAllocationInstance':grantAllocationInstance,'projectInstance':projectInstance,'grantAllocationInstanceList':grantAllocationInstanceList,'partyInstance':partyInstance,'grantAllocation':grantAllocation ]
    	}
    	 /* }
    
    
    def subGrantAllotExt = {
    		
    		GrailsHttpSession gh=getSession()
    		
    		gh.putValue("fromUrL", "subGrantAllotExt");
    		gh.putValue("fromID", params.id);
        def grantAllocationInstance = new GrantAllocation()
        grantAllocationInstance.properties = params
        
        def grantAllocationService = new GrantAllocationService()
        def grantAllocation=grantAllocationService.getGrantAllocationById(new Integer(params.id))
        def dataSecurityService = new DataSecurityService()
//      checking  whether the user has access to the given projects
/*		if(dataSecurityService.checkForAuthorisedAcsessInProjects(new Integer(params.id),new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{ */
        
        
			def projectInstance = Projects.get( params.id )
	        projectInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectInstance.id,getUserPartyID())
	       
        
      
        	def partyInstance=Party.get(gh.getValue("Party"))
        	grantAllocationInstance.granter=partyInstance;
        println "params"+params.id
        
        def grantAllocationInstanceList = grantAllocationService.getSubGrantAllocationInSort(new Integer(params.id))
        
        return ['grantAllocationInstance':grantAllocationInstance,'projectInstance':projectInstance,'grantAllocationInstanceList':grantAllocationInstanceList,'partyInstance':partyInstance,'grantAllocation':grantAllocation ]
		//}
    }
    
    def subGrantAllotExt = {
    	GrailsHttpSession gh=getSession()
    	gh.putValue("fromUrL", "subGrantAllotExt");
    	gh.putValue("fromID", params.id);
    	
        def grantAllocationInstance = new GrantAllocation()
        grantAllocationInstance.properties = params
        
        def grantAllocationService = new GrantAllocationService()
        def dataSecurityService = new DataSecurityService()
        
//      checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(new Integer(params.id),new Integer(getUserPartyID()))==0)
		{	
			redirect uri:'/invalidAccess.gsp'
		}
		else
		{ 
			def projectInstance = Projects.get( params.id )
	        projectInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectInstance.id,getUserPartyID())
	       
        	def partyInstance=Party.get(gh.getValue("Party"))
        	grantAllocationInstance.party=partyInstance;
			grantAllocationInstance.projects = projectInstance
			
			println "params"+params.id
			String subQuery="";
	        if(params.sort != null && !params.sort.equals(""))
	       	subQuery=" order by GA."+params.sort
	       if(params.order != null && !params.order.equals(""))
	       	subQuery =subQuery+" "+params.order
			
			def grantAllocationInstanceList = grantAllocationService.getValidGrantAllocationsForProjectAndParty("("+projectInstance.id.toString()+")","("+partyInstance.id.toString()+")",subQuery)
        
			return ['grantAllocationInstance':grantAllocationInstance,'projectInstance':projectInstance,'grantAllocationInstanceList':grantAllocationInstanceList,'partyInstance':partyInstance]
		}
    }
	/*1. Get the total Amount allocated during the sub allotment and the new amount Allocated that entered 
	 * by the user.
	 * 2. Calculate the sum of these amounts 
	 * 3. Compare this total amount with the amount allocated for the entire Project
	 * 4. Only if the total amount is not exceeding the allocated amount for the
	 *    project,the user can give the suballocation
	 * */
    def subGrantSave = 
    {
    	double sumAmount = 0.0
    	double totAllAmount = ((params.totAllAmount).toDouble()).doubleValue()
    	double amountAllocated = ((params.amountAllocated).toDouble()).doubleValue()
    	double newAmount = ((params.amount).toDouble()).doubleValue()
    	sumAmount = amountAllocated + newAmount
    	println "++++++++++++++++++++++++++++++++++++++sumAmount+++++++++++++++++++++++++" + sumAmount
    	    
		println params
		println params.grantor
		def granterInstance=Party.get(new Integer(params.grantor))
		
        def grantAllocationInstance = new GrantAllocation(params)
		grantAllocationInstance.granter=granterInstance
		println grantAllocationInstance.projects
		println grantAllocationInstance.party
		println grantAllocationInstance.granter
		
	    grantAllocationInstance.createdBy="admin"
		grantAllocationInstance.modifiedBy="admin"
		grantAllocationInstance.allocationType="grand"
		grantAllocationInstance.code=""
	
		
		def partyService = new PartyService();
	
		def grantAllocationService = new GrantAllocationService()
		if(Double.compare(totAllAmount,sumAmount) >=0 )
		{
			
			Integer duplicateCheck = grantAllocationService.checkDuplicateFundAllot(grantAllocationInstance);
			println "+++++++++++++++++++++++++++++++++++duplicateCheck++++++++++++++++++++++++" + duplicateCheck
			if( duplicateCheck == 0 || duplicateCheck == null)
			{
				def GrantAllocation = grantAllocationService.saveSubGrantAllocation(grantAllocationInstance)
				flash.message = "New Grant Allocation Created"
			}
			else
			{
				flash.message = "Already Allotted to the same Recipient"
			}
		}
		else
		{
			flash.message = "The Amount should not exceed the Allocated Amount for the Project"
		}
			
	    redirect(action:'subGrantAllot',id:grantAllocationInstance.projects.parent.id)

    }

    
    def subGrantSaveExt = {
    	
        def grantAllocationInstance = new GrantAllocation(params)
		
        println "******************************subgrant save*************************"
		println grantAllocationInstance.projects
		println grantAllocationInstance.party
		println grantAllocationInstance.granter
		
	    grantAllocationInstance.createdBy="admin"
		grantAllocationInstance.modifiedBy="admin"
		grantAllocationInstance.createdDate = new Date()
		grantAllocationInstance.allocationType="grand"
		grantAllocationInstance.code=""
	
		//def granterInstance = Party.get(grantAllocationInstance.granter.id)
		//grantAllocationInstance.granter = granterInstance
//                    	    grantAllocationInstance.save();
		def grantAllocationService = new GrantAllocationService()
        def GrantAllocation = grantAllocationService.saveSubGrantAllocation(grantAllocationInstance)
            	
        flash.message = "New Grant Allocation Created"
        redirect(action:'subGrantAllotExt',id:grantAllocationInstance.projects.id)
       
    }

    def mainDash = {
    	GrailsHttpSession gh=getSession()
    	
    	//get data from grant_allocation
    	
	    	def dataSecurityService = new DataSecurityService();
	    	def grantAllocationInstanceList=dataSecurityService.getProjectsFromGrantAllocationForLoginUser(gh.getValue("PartyID"));
	    	
    	
    	
         
        [ grantAllocationInstanceList: grantAllocationInstanceList ]
    	
    }

    def show = {
		def grantAllocationService = new GrantAllocationService()
        def grantAllocationInstance = grantAllocationService.getGrantAllocationById(new Integer(params.id))
        	//GrantAllocation.get( params.id )

        if(!grantAllocationInstance) {
            flash.message = "GrantAllocation not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ grantAllocationInstance : grantAllocationInstance ] }
    }

    
    
    def editProAllot = {
		def grantAllocationService = new GrantAllocationService()
		def grantAllocationInstance = grantAllocationService.getGrantAllocationById(new Integer(params.id))
		  def projectInstance 
		 println "ss "+grantAllocationInstance.projects
        if(grantAllocationInstance.projects.parent!=null) 
         projectInstance = Projects.get(grantAllocationInstance.projects.parent.id )
        else
         	projectInstance = Projects.get(grantAllocationInstance.projects.id )
        projectInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectInstance.id,getUserPartyID())
        def grantAllocationInstanceList = grantAllocationService.getSubGrantAllocations(new Integer((projectInstance.id).intValue()))
        double sumAmountAllocated = 0.00
        for(int i=0;i<grantAllocationInstanceList.size();i++)
        {
        	sumAmountAllocated = sumAmountAllocated + grantAllocationInstanceList[i].amountAllocated
        }
        grantAllocationInstance.totAllAmount = sumAmountAllocated

        def dataSecurityService = new DataSecurityService()
        //checking  whether the user has access to the given projects
		if(dataSecurityService.checkForAuthorisedAcsessInProjects(projectInstance.id,new Integer(getUserPartyID()))==0)
		{
			
					
					 redirect uri:'/invalidAccess.gsp'

		}
		else
		{ 
	        if(!grantAllocationInstance) {
	            flash.message = "GrantAllocation not found with id ${params.id}"
	            redirect(action:list)
	        }
	        else {
	            return [ grantAllocationInstance : grantAllocationInstance ,'projectInstance':projectInstance]
	        }
        
		}
    }
    def updateProAllot = 
    {
		 
		println "++++++++++++++++++++++++++++++++++++++params+++++++++++++++++++++++++" + params

		 double sumAmount = 0.0
    	 double projectAmount = 0.0
    	 double amountToBeUpdated = 0.0
    	 projectAmount = ((params.totAllAmount).toDouble()).doubleValue()	 
     	 sumAmount = ((params.amountAllocated).toDouble()).doubleValue() + ((params.amount).toDouble()).doubleValue()
    	 GrailsHttpSession gh=getSession()
		 def grantAllocationService = new GrantAllocationService()
		 def pastGrantAllocation = grantAllocationService.getGrantAllocationById(new Integer(params.id))
		 amountToBeUpdated = (sumAmount - (pastGrantAllocation.amountAllocated).doubleValue())
		 
		 def grantAllocationInstance 
		 if(Double.compare(projectAmount,amountToBeUpdated) >=0)
		 {
			 grantAllocationInstance = grantAllocationService.updateGrantAllocation(params)
		 }
		 else
		 {
			 flash.message = "Amount should not exceed the Amount allocated for Project"
			 redirect(action:'editProAllot',id:pastGrantAllocation.id)
		 }
		 if(grantAllocationInstance)
		 {
			 if(grantAllocationInstance.isSaved)
			 {
				 flash.message = "Sub Project Allocation Updated"
				
				 if(gh.getValue("fromUrL")=="subGrantAllotExt")
					 redirect(action:'subGrantAllotExt',id:grantAllocationInstance.projects.id)
				 else
				 {
					 def grantAllocation = grantAllocationService.getGrantAllocationAfterSubProjectAllocation(grantAllocationInstance)
					 redirect(action:'subGrantAllot',id:grantAllocation.projects.id)
				 }
			 }
			 else 
			 {
				 render(view:'edit',model:[grantAllocationInstance:grantAllocationInstance])
			 }
		 }
/*		 else 
		 {
			 flash.message = "GrantAllocation not found with id ${params.id}"
			 redirect(action:edit,id:params.id)
		 }*/
    }
    
    def headMsg = {
		redirect(action:assignHeads,id:params.grantAllotId)
            
    }
    
    def reports = {
		println params
		GrailsHttpSession gh=getSession()
		def dataSecurityService = new DataSecurityService();
		
		/* Get projects mapped to the login user */
//    		def projectInstanceList = dataSecurityService.getProjectsOfLoginUser(gh.getValue("ProjectID"))
		def projectInstanceList = dataSecurityService.getProjectsForLoginUser(gh.getValue("PartyID"))
		
		/* Get parties mapped to the login user */
		//def partyInstanceList =dataSecurityService.getPartiesOfLoginUser(gh.getValue("PartyID"))
	
        return[projectInstanceList:projectInstanceList]
    }
        
    
    
    

    def create = {
    		
    		GrailsHttpSession gh=getSession()
    		gh.putValue("fromUrL", "Create");
    		gh.putValue("fromID", params.id);
        def grantAllocationInstance = new GrantAllocation()

        grantAllocationInstance.properties = params
        def grantAllocation=GrantAllocation.find("from GrantAllocation  GA where  GA.id="+params.id);
        println "params"+params.id
        grantAllocationInstance.projects=grantAllocation.projects
            def grantAllocationInstanceList=GrantAllocation.findAll("from GrantAllocation  GA where  GA.allocationType<>'Fund' and GA.projects= "+grantAllocation.projects.id);
         
   
        	 def allAmt=GrantAllocation.executeQuery("select sum(GA.amountAllocated) as total from GrantAllocation  GA where  GA.allocationType<>'Fund' and GA.projects= "+grantAllocation.projects.id+" group by GA.projects");
         println "allAmt"+allAmt;
        	 if(allAmt[0]!=null)
        	 grantAllocation.totAllAmount=new Double(allAmt[0]).doubleValue()
        return ['grantAllocationInstance':grantAllocationInstance,'projectInstance':grantAllocation,'grantAllocationInstanceList':grantAllocationInstanceList]
        
    }

    def save = {
        def grantAllocationInstance = new GrantAllocation(params)
        if(!grantAllocationInstance.hasErrors() ) {
        	    grantAllocationInstance.createdBy="admin"
        		grantAllocationInstance.modifiedBy="admin"
        		def projects=Projects.find("from Projects  PA where  PA.id="+params.project);
        	    println projects
        		grantAllocationInstance.projects=projects;
        	    grantAllocationInstance.allocationType="grand"
        	    grantAllocationInstance.code=""
        	    def grantAllocation = GrantAllocation.find("from GrantAllocation GA where GA.id = '"+params.grantAllot+"'");
        	    grantAllocationInstance.granter = grantAllocation.party
        	    grantAllocationInstance.save();
            	println params
            flash.message = "Project is  Allotted "
            redirect(action:'create',id:params.grantAllot)
        }
        else {
            render(view:'create',model:[grantAllocationInstance:grantAllocationInstance])
        }
    }
    
    def listReport = {
    		println params
        	
    		return['reportListInstance':params]
    		
    }
    
  
    
    def projectDash = {
    		
    		def totmonths=new HashSet()
    		def expense=[]
    		def recive=[]
    		def months=[]
    		def monthName=""
    		def totalexpense=0;
    		def totalRecieve=0;
    		def balance=[];
    		def range=[];
    		def rangelimit=[];
    		def monthname=["Jan","Feb","Mar","Apr","May","Jun","July","Aug","Sep","Oct","Nov","Dec"]
    		def monthnameView=[]
    		def grantAllocationService = new GrantAllocationService()
            def projectInstance = Projects.get( params.id )
            projectInstance.totAllAmount=grantAllocationService.getSumOfAmountAllocatedForProject(projectInstance.id,getUserPartyID())
           	def sumAmount = GrantExpense.executeQuery("select sum(GE.expenseAmount) as SumAmt from GrantExpense GE where GE.projects.id ="+ params.id) 
           	
           	def sumGrantRecieve = GrantExpense.executeQuery("select sum(GR.amount) as SumAmt from GrantReceipt GR where GR.projects.id ="+ params.id)
           	
           	 	def grantAllocationSplit = GrantAllocationSplit.findAll("from GrantAllocationSplit  GAS where  GAS.projects="+params.id);
            
    		  grantAllocationSplit = GrantAllocationSplit.executeQuery("select sum(GAS.amount) as SumAmt,GAS.accountHead.code from GrantAllocationSplit GAS where GAS.projects.id ="+ params.id+"and  GAS.grantAllocation.party.id ="+ getUserPartyID()+" group by GAS.accountHead.id")
                              
                              
             def monthlyExpenseAndRecipts = GrantReceipt.executeQuery("SELECT MONTH(dateOfExpense) as Month ,"+ 
									    " sum(expenseAmount) as  expAmt ,	(SELECT  sum(amount) as expAmt FROM GrantReceipt GR where GR.projects.id="+ params.id+"  	and  MONTHNAME(dateOfReceipt)=MONTHNAME(GA.dateOfExpense) "+
							"	group by MONTHNAME(dateOfReceipt)) as reciept "+
							"	 FROM GrantExpense GA where  GA.projects.id="+ params.id+"  group by MONTHNAME(GA.dateOfExpense) order by GA.dateOfExpense   asc")

							 println  "monthlyExpenseAndRecipts "+monthlyExpenseAndRecipts
							 
							 def totalExpense=0;
                              def totalReciept=0;
            
							 
                              monthnameView.add("0")
							   balance.add(0)
							 for(int i=0;i<monthlyExpenseAndRecipts.size();i++)
					            {
								monthnameView.add(monthname[new Integer(monthlyExpenseAndRecipts[i][0]).intValue()-1])					            
									double expenseMonth=0
								if(monthlyExpenseAndRecipts[i][1]!=null)
									expenseMonth=new Double(monthlyExpenseAndRecipts[i][1]).doubleValue()
								        totalExpense=totalExpense+ expenseMonth;
								double Reciept=0
								if(monthlyExpenseAndRecipts[i][2]!=null)
									Reciept=new Double(monthlyExpenseAndRecipts[i][2]).doubleValue()
								totalReciept=totalReciept+ Reciept;
								double balanceInmonth=0;
								if(totalReciept>0)
									balanceInmonth=(totalReciept-totalExpense)/totalReciept*4096
									if(balanceInmonth<0)
										balanceInmonth=0;
									balance.add(balanceInmonth)
                                } 
							 
							  println "aa"+ monthnameView
							  println "balance"+ balance
							  println "sumAmount"+ sumAmount
							  if(sumAmount[0]==null)
								  sumAmount[0]=0
								  if(sumGrantRecieve[0]==null)
									  sumGrantRecieve[0]=0
									
									  
									  
									  
								//drawing the line chart

									  def chart1 = new GoogleChartBuilder()
									def resultLinechart = chart1.lineChart{
									 size(w:250, h:180)  
									 title
									 {
									 row('Fund Utilization') 
									 } 
									 data(encoding:'extended')
									 { 
									 dataSet(balance)  

									 } 
									 colors
									 {   
									 color('66CC00') 
									 color('3399ff')  
									 } 
									 lineStyle(line1:[1,6,3]) 
									 legend{
									 label('Balance') 

									 }  
									 axis(left:[0,20,40,60,80,100], bottom:monthnameView) 
									 backgrounds{
									 background{
									  solid(color:'cbe2f0')
									    }  

									 area{
									       gradient(angle:45, start:'FFFFFF', end:'cbe2f0')
									 } 
									 } 
									 markers
									 {     
									 rangeMarker(type:'horizontal', color:'FF0000', start:50, end:52)

									 }

									  grid(y:50, x:100/1, dash:3, space:1)
									 }

									  
									  //drawing the  pie chart
									  
									    def chart = new GoogleChartBuilder()
				      def textList =[]

				      def total=0
				      def i=0
				      grantAllocationSplit.each {
                
										  
										  
							textList.add (grantAllocationSplit[i][0]*100/projectInstance.totAllAmount)
							total=total+grantAllocationSplit[i][0]
							i++
						}
							
									  
									  i=0
						if(total<projectInstance.totAllAmount)
						textList.add((projectInstance.totAllAmount-total)*100/projectInstance.totAllAmount)
				      def resultPiechart = chart.pie3DChart{
				      size(w:300, h:95)
				      title
				   {
				      row('Fund Allocation') 
				   } 
				      data(encoding:'text')
				      {
				       dataSet(textList)
				       }
					colors{
						color('e25d3a')
						color('fbc363')
					    }
					    
					     title(color:'0000ff', size:1){
						row('')
						row('')
					    }


				       labels
				       { 
					 grantAllocationSplit.each {

					 label(grantAllocationSplit[i][1]) 
					 i++
					 }
						if(total<projectInstance.totAllAmount)
						label("Un Allocated")
					}
				       backgrounds{
				 background{
				  solid(color:'cbe2f0')
				    }  

				 area{
					gradient(angle:45, start:'cbe2f0', end:'cbe2f0')
				 } 
				 } 

				       }
								  
                         
							         
            if(!projectInstance) {
                flash.message = "GrantAllocation not found with id ${params.id}"
                redirect(action:list)
            }
          
            else { return [ projectInstance : projectInstance,sumAmount:sumAmount,grantAllocationSplit:grantAllocationSplit,resultPiechart:resultPiechart,resultLinechart:resultLinechart,rangelimit:rangelimit,sumGrantRecieve:sumGrantRecieve]}
        }
        
        
    def grantReports = {
    		println params
    		GrailsHttpSession gh=getSession()
    		def dataSecurityService = new DataSecurityService();

    		def projectInstanceList = dataSecurityService.getProjectsForLoginUser(gh.getValue("PartyID"))

    		return[projectInstanceList:projectInstanceList]
        }

}
