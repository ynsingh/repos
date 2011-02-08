import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import groovy.sql.Sql
import java.util.*;
import jofc2.model.Chart
import jofc2.model.elements.PieChart
import jofc2.model.elements.LineChart
import jofc2.model.axis.YAxis
import jofc2.model.axis.XAxis
import jofc2.model.elements.BarChart
import jofc2.OFC
import grails.converters.*



class CourseActivityController {
	def dataSource
     	def index = { redirect(action:list,params:params) }
    	// the delete, save and update actions only accept POST requests
    	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
    	def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        def chartValueRes = []
        def presBegin = []
        def contentRead = []
        def user = []
        def range = []
        def axesLabelsRes = [:]
        def maxValue=0
        def isPresBegin = false
        def isContentRead = false
        def studentResourceList = []
        def studentResourceInstance
        def userLogin = ""
        def countStudent=0
        GrailsHttpSession gh=getSession()
     	def lms = gh.getValue("LMS") 
	def sql = getSqlConnection()
        /* sql.eachRow("select USER_ID,EVENT_ID,EVENT_COUNT from SST_EVENTS where EVENT_ID "+ 
        		"in('pres.begin','content.read') group by USER_ID,EVENT_ID order by USER_ID")
         { row ->
        	
        	user.add(row.USER_ID)
        	if(row.EVENT_ID.equals("pres.begin"))
        		presBegin.add(row.EVENT_COUNT)
        	else if(row.EVENT_ID.equals("content.read"))
        		contentRead.add(row.EVENT_COUNT)
        	
        	if(row.EVENT_COUNT > maxValue)
        		maxValue = row.EVENT_COUNT
        }
        chartValueRes.add(presBegin)
        chartValueRes.add(contentRead)
       for(int i=0;i<=maxValue;i++)
       range.add(i)
       axesLabelsRes.put(0,user)
       axesLabelsRes.put(1,range) */
        /* Get all users */
        if(lms.equals("Sakai"))
        {
	        sql.eachRow("select distinct USER_ID from SST_EVENTS where "+
					"EVENT_ID in('asn.submit.submission','content.read')"+
					" order by USER_ID")
	        {
	        	rowUser ->
	        	
	        	
	        	/* Get user login from user id */
	        	sql.eachRow("select EID from SAKAI_USER_ID_MAP where USER_ID = '"+rowUser.USER_ID+"' ")
				{
	        		rowUserDet ->
	        		user.add( rowUserDet.EID)
	        		userLogin = rowUserDet.EID
				}
	        	//user.add(rowUser.USER_ID)
	        	isPresBegin = false
	        	isContentRead = false
	        	
	        	/* Check whether pres.begin event is there for user */
	        	sql.eachRow("select USER_ID,EVENT_ID,sum(EVENT_COUNT) as EVENT_COUNT  from SST_EVENTS where EVENT_ID "+ 
						"in('asn.submit.submission') and USER_ID='"+rowUser.USER_ID+"' group by USER_ID,EVENT_ID order by USER_ID" )
	        	{
	        		rowPresBegin ->
	        		
	        		presBegin.add(rowPresBegin.EVENT_COUNT)
	        		isPresBegin = true
	        		if(rowPresBegin.EVENT_COUNT > maxValue)
	            		maxValue = rowPresBegin.EVENT_COUNT
	            	
	            	studentResourceInstance = new CourseActivity()
	            	studentResourceInstance.userId = userLogin
	            	studentResourceInstance.userName = userLogin
	            	studentResourceInstance.eventId = rowPresBegin.EVENT_ID
	            	studentResourceInstance.eventCount = rowPresBegin.EVENT_COUNT
	            	studentResourceList.add(studentResourceInstance)
	            	countStudent++
	            	
	        	}
	        	
	        	if(!isPresBegin)
	        		presBegin.add(0)
	        	/* Check whether content.read event is there for user */
	        	sql.eachRow("select USER_ID,EVENT_ID,sum(EVENT_COUNT) as EVENT_COUNT from SST_EVENTS where EVENT_ID "+ 
						"in('content.read') and USER_ID='"+rowUser.USER_ID+"' group by USER_ID,EVENT_ID order by USER_ID")
	        	{
	        		rowContentRead ->
	        		
	        		contentRead.add(rowContentRead.EVENT_COUNT)
	        		isContentRead = true
	        		if(rowContentRead.EVENT_COUNT > maxValue)
	            		maxValue = rowContentRead.EVENT_COUNT
	        		studentResourceInstance = new CourseActivity()
	        		studentResourceInstance.userId = userLogin
	        		studentResourceInstance.userName = userLogin
	            		studentResourceInstance.eventId = rowContentRead.EVENT_ID
	            		studentResourceInstance.eventCount = rowContentRead.EVENT_COUNT
	            		studentResourceList.add(studentResourceInstance)
	            		countStudent++
	            		
	        	}
	        	if(!isContentRead)
	        		contentRead.add(0)
	        }
        }
        else if(lms.equals("Moodle"))
	    {
	    	def sql1=new Sql(dataSource);
	    	sql1.eachRow("SELECT ML.userid AS USER_ID FROM mdl_log ML WHERE CONCAT(ML.module,'.',ML.action) IN('course.view','course.new') ORDER BY ML.userid")
	        {
	        	rowUser ->
	        	
	        	
	        	/* Get user login from user id */
	        	sql1.eachRow("SELECT MU.username AS EID FROM mdl_user MU WHERE id ='"+rowUser.USER_ID+"' ")
				{
	        		rowUserDet ->
	        		user.add( rowUserDet.EID)
	        		userLogin = rowUserDet.EID
				}
	        	//user.add(rowUser.USER_ID)
	        	isPresBegin = false
	        	isContentRead = false
	        	
	        	/* Check whether pres.begin event is there for user */
	        	sql.eachRow("SELECT ML.userid AS USER_ID,CONCAT(ML.module,'.',ML.action) AS EVENT_ID,"+
	        						"COUNT(CONCAT(ML.module,'.',ML.action))AS EVENT_COUNT "+
	        						"FROM mdl_log ML WHERE CONCAT(ML.module,'.',ML.action) "+
	        						"IN('course.view') AND ML.userid='"+rowUser.USER_ID+"' "+
	        						"GROUP BY ML.userid,CONCAT(ML.module,'.',ML.action) ORDER BY ML.userid" )
	        	{
	        		rowPresBegin ->
	        		
	        		presBegin.add(rowPresBegin.EVENT_COUNT)
	        		isPresBegin = true
	        		if(rowPresBegin.EVENT_COUNT > maxValue)
	            		maxValue = rowPresBegin.EVENT_COUNT
	            	
	            	studentResourceInstance = new CourseActivity()
	            	studentResourceInstance.userId = userLogin
	            	studentResourceInstance.userName = userLogin
	            	studentResourceInstance.eventId = rowPresBegin.EVENT_ID
	            	studentResourceInstance.eventCount = rowPresBegin.EVENT_COUNT
	            	studentResourceList.add(studentResourceInstance)
	            	countStudent++
	            	
	        	}
	        	
	        	if(!isPresBegin)
	        		presBegin.add(0)
	        	/* Check whether content.read event is there for user */
	        	sql.eachRow("SELECT ML.userid AS USER_ID,CONCAT(ML.module,'.',ML.action) AS EVENT_ID,"+
	        						"COUNT(CONCAT(ML.module,'.',ML.action))AS EVENT_COUNT "+
	        						"FROM mdl_log ML WHERE CONCAT(ML.module,'.',ML.action) IN('course.new') "+
	        						"AND ML.userid='"+rowUser.USER_ID+"'GROUP BY ML.userid,"+
	        						"CONCAT(ML.module,'.',ML.action) ORDER BY ML.userid")
	        	{
	        		rowContentRead ->
	        		
	        		contentRead.add(rowContentRead.EVENT_COUNT)
	        		isContentRead = true
	        		if(rowContentRead.EVENT_COUNT > maxValue)
	            		maxValue = rowContentRead.EVENT_COUNT
	            		
	        		studentResourceInstance = new CourseActivity()
	        		studentResourceInstance.userId = userLogin
	        		studentResourceInstance.userName = userLogin
	            		studentResourceInstance.eventId = rowContentRead.EVENT_ID
	            		studentResourceInstance.eventCount = rowContentRead.EVENT_COUNT
	            		studentResourceList.add(studentResourceInstance)
	            		countStudent++
	            	
	        	}
	        	if(!isContentRead)
	        		contentRead.add(0)
	        }
	    }
        chartValueRes.add(presBegin)
        chartValueRes.add(contentRead)
        
        for(int i=0;i<=maxValue;i++)
        	range.add(i)
        	
        axesLabelsRes.put(0,user)
        axesLabelsRes.put(1,range)
        
                
        [ studentResourceList: studentResourceList, studentResourceInstanceTotal: countStudent, axesLabelsRes:axesLabelsRes, chartValueRes:chartValueRes ]
        
    }
    
    
    
    def listAdmin = {
        
        def studentResourceList = []
        def studentResourceInstance=[]
        def user=[]
        def sql=new Sql(dataSource);
        sql.eachRow("SELECT course AS SiteId,coursename AS TITLE,module AS EVENT_ID,"+
		        			" count(action) AS EVENTNO FROM "+
		    			" master  GROUP BY coursename,module")
        	  {
	        	rowUser ->
	        	user.add(rowUser.TITLE)
	        	studentResourceInstance = new CourseActivity()
	        	studentResourceInstance.userId = rowUser.TITLE
	        	studentResourceInstance.eventId = rowUser.EVENT_ID
	            	studentResourceInstance.eventCount = rowUser.EVENTNO
	        	studentResourceList.add(studentResourceInstance)
        	}
         [studentResourceList: studentResourceList]
    }

    def PIE_CHART = {
         def lst = []
         def crs=[]
         Chart c = new Chart(" ");
    	PieChart p=new PieChart();
    	def sql=new Sql(dataSource);
    	sql.eachRow("SELECT course AS SiteId,coursename AS TITLE,module AS EVENT_ID, count(action) AS EVENTNO FROM master  GROUP BY module")
    	{ row ->
         	lst.add(row.EVENTNO)
    		crs.add(row.EVENT_ID);
    		p.addSlice(row.EVENTNO, row.EVENT_ID);
     	}
       
        p.setAnimate(true);
    	p.setStartAngle(35);
    	p.setBorder(2);
    	p.setAlpha(0.6f);
    	//p.addSlice(6.5f, "hello1 (6.5)");
    	p.setColours("#d01f3c", "#356aa0", "#C7777");
    	p.setTooltip("#val# of #total#<br>#percent# of 100%");
    	c.addElements(p);
       	render c;
        }

    def listSiteVisit = {
        def studentResourceList = []
        def studentResourceInstance=[]
        def user=[]
        GrailsHttpSession gh=getSession()
  	   // def lms = gh.getValue("LMS") 
        	 def sql=new Sql(dataSource);
               	 sql.eachRow(" SELECT coursename AS TITLE,count(action)AS TOTAL_VISITS FROM "+
        			"master GROUP BY coursename")
        	  {
	        	rowUser ->
	        	user.add(rowUser.TITLE)
	        	studentResourceInstance = new CourseActivity()
	        	studentResourceInstance.userId = rowUser.TITLE
	            	studentResourceInstance.eventCount = rowUser.TOTAL_VISITS
	        	studentResourceList.add(studentResourceInstance)
	        }
            
        [studentResourceList: studentResourceList,user:user]
      }

def sitevisit_PIE = {
         		def lst = []
	      		def crs=[]
	 	        Chart c = new Chart("");
	 	        //c.setXAxis(new XAxis().setLabels("Courses"));
	 		c.setYAxis(new YAxis().setRange(0, 100,10));
	 		def sql=new Sql(dataSource);
	 		sql.eachRow("select count(action) as cnt,coursename from master group by coursename") 
	 		{ row ->
	      			lst.add(row.cnt)
	 			crs.add(row.coursename);
	 		}
	 		BarChart b=new BarChart()
	 		b.setColour("#8B7D7");
	 		lst.sort();
	 		b.addValues(lst);
	 		c.setXAxis(new XAxis().setLabels(crs));
	 	        c.addElements(b);
			render c;
         }
    
    def studActivity= {
    		params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
	        def lst=[]
	        def crs=[]
	        def chartValueRes = []
	        def presBegin = []
	        def contentRead = []
	        def Event1 = []
	        def Event2 = []
	        def Event3 = []
	        def countData = []
	        def course = []
	        def count1 = []
	        def count2 = []
	        def count3 = []
	        def user = []
	        def range = []
	        def axesLabelsRes = [:]
	        def maxValue=0
	        def isPresBegin = false
	        def isContentRead = false
	        def studentResourceList = []
	        def studentResourceInstance=[]
	        def userLogin = ""
	        def countStudent=0
	        GrailsHttpSession gh=getSession()
	 	   
	 	    def lms = gh.getValue("LMS") 
	 	    def usr=gh.getValue("UserId");
	 	    def usr1
	 	    try
	 	    {
	 	    	usr1=usr.substring(0,usr.indexOf('@'))
	 	    }
	 	    catch(Exception e)
	 	    {
	 	         usr1=usr
	 	    }
	 	    //  def sql = Sql.newInstance("jdbc:mysql://192.168.18.90:3306/aums_sakai", "root",
	//           "devima", "com.mysql.jdbc.Driver")
			def sql=new Sql(dataSource);
	        /*sql.eachRow("select USER_ID,EVENT_ID,EVENT_COUNT from SST_EVENTS where EVENT_ID "+ 
	        		"in('pres.begin','content.read') group by USER_ID,EVENT_ID order by USER_ID")
	        //{ row ->
	        	
	        	user.add(row.USER_ID)
	        	if(row.EVENT_ID.equals("pres.begin"))
	        		presBegin.add(row.EVENT_COUNT)
	        	else if(row.EVENT_ID.equals("content.read"))
	        		contentRead.add(row.EVENT_COUNT)
	        	
	        	if(row.EVENT_COUNT > maxValue)
	        		maxValue = row.EVENT_COUNT
	        }
	        chartValueRes.add(presBegin)
	        chartValueRes.add(contentRead)
	        
	        //for(int i=0;i<=maxValue;i++)
	        	range.add(i)
	        	
	        axesLabelsRes.put(0,user)
	        axesLabelsRes.put(1,range)*/
	        
	        /* Get all users */
	       
	             	sql.eachRow("select count(action) as EventCount,coursename as TITLE from master where username='"+usr1+"' group by coursename")
	          	  {
	  	        	rowUser ->
	  	        	countData.add(rowUser.EventCount)
	  	        	user.add(rowUser.TITLE)
	  	        	studentResourceInstance = new CourseActivity()
	  	        	studentResourceInstance.userId = rowUser.TITLE
	  	        	studentResourceInstance.eventCount = rowUser.EventCount
	  	        	studentResourceList.add(studentResourceInstance)
	  	        	lst.add(rowUser.TITLE)
	  	        	crs.add(rowUser.EventCount)
	  	        }
	       chartValueRes.add(countData)
	       chartValueRes.add(user) 
	       chartValueRes.add(count3) 
	       for(int i=0;i<=150;i=i+10)
	       range.add(i)
	       axesLabelsRes.put(0,user)
	       axesLabelsRes.put(1,range)
	       
        [ studentResourceList: studentResourceList,lst:lst,crs:crs]
        
    }
    def STUD_ALL_Course={
    
    		GrailsHttpSession gh=getSession()
    		def usr=gh.getValue("UserId");
    		def user1
		try
		{
			user1=usr.substring(0,usr.indexOf('@'))
		}
		catch(Exception e)
		{
			user1=usr
	 	}
	 	
		def lst = []
		def crs=[]
		Chart c = new Chart("");
		c.setYAxis(new YAxis().setRange(0, 100,10));
		def sql=new Sql(dataSource);
		sql.eachRow("select count(action) as cnt,coursename from master where username='"+user1+"' group by coursename") 
		{ row ->
			lst.add(row.cnt)
			crs.add(row.coursename);
		}
		BarChart b=new BarChart()
		b.setColour("#8B7D7");
		lst.sort();
		b.addValues(lst);
		c.setXAxis(new XAxis().setLabels(crs));
		c.addElements(b);
		render c;
     }
  def STUD_SINGLE={
    
    		GrailsHttpSession gh=getSession()
    		def usr=gh.getValue("UserId");
    		def usr1
		try
		{
			usr1=usr.substring(0,usr.indexOf('@'))
		}
		catch(Exception e)
		{
			usr1=usr
	 	}
		def lst = []
		def crs=[]
		
		Chart c = new Chart("");
		c.setYAxis(new YAxis().setRange(0, 30,10));
		def sql=new Sql(dataSource);
		sql.eachRow("select count(action) as cnt,module from master where username='"+usr1+"' and coursename='"+params.mod+"' group by module") 
		{ row ->
			lst.add(row.cnt)
			crs.add(row.module);
		}
			BarChart b=new BarChart()
			b.setColour("#8B7D7");
			lst.sort();
			b.addValues(lst);
			c.setXAxis(new XAxis().setLabels(crs));
			c.addElements(b);
			render c;
    
     }
    
    def studActivityList = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
       
        def chartValueRes = []
        def presBegin = []
        def contentRead = []
        def Event1 = []
        def Event2 = []
        def Event3 = []
        def countData = []
        def course = []
        def count1 = []
        def count2 = []
        def count3 = []
        def user = []
        def range = []
        def axesLabelsRes = [:]
        def maxValue=0
        def isPresBegin = false
        def isContentRead = false
        def studentResourceList = []
        def studentResourceInstance=[]
        def userLogin = ""
        def countStudent=0
        GrailsHttpSession gh=getSession()
 	   
 	    def lms = gh.getValue("LMS") 
//        def sql = Sql.newInstance("jdbc:mysql://192.168.18.90:3306/aums_sakai", "root",
//           "devima", "com.mysql.jdbc.Driver")
		def sql = getSqlConnection()
        /*sql.eachRow("select USER_ID,EVENT_ID,EVENT_COUNT from SST_EVENTS where EVENT_ID "+ 
        		"in('pres.begin','content.read') group by USER_ID,EVENT_ID order by USER_ID")
        //{ row ->
        	
        	user.add(row.USER_ID)
        	if(row.EVENT_ID.equals("pres.begin"))
        		presBegin.add(row.EVENT_COUNT)
        	else if(row.EVENT_ID.equals("content.read"))
        		contentRead.add(row.EVENT_COUNT)
        	
        	if(row.EVENT_COUNT > maxValue)
        		maxValue = row.EVENT_COUNT
        }
        chartValueRes.add(presBegin)
        chartValueRes.add(contentRead)
       //for(int i=0;i<=maxValue;i++)
        	range.add(i)
        	
        axesLabelsRes.put(0,user)
        axesLabelsRes.put(1,range)*/
        
        /* Get all users */
       
       
        {
        	sql.eachRow("select TITLE,SE.USER_ID as UserId,SU.EID as Login,SE.EVENT_ID as EventId,sum(SE.EVENT_COUNT) as EventCount "+
                  "from SST_EVENTS SE,SAKAI_USER_ID_MAP SU ,SAKAI_SITE SS where  SE.USER_ID = SU.USER_ID and SS.SITE_ID  =SE.SITE_ID "+
                    " group by SS.SITE_ID order by SE.EVENT_ID")
        	  {
	        	rowUser ->
	        	countData.add(rowUser.EventCount)
	        	user.add(rowUser.TITLE)
	        	studentResourceInstance = new CourseActivity()
	        	studentResourceInstance.userId = rowUser.TITLE
	        	studentResourceInstance.eventId ='am001'
	            	studentResourceInstance.eventCount = rowUser.EventCount
	        	studentResourceList.add(studentResourceInstance)
	        }
        }
       
        chartValueRes.add(countData)
        for(int i=0;i<=32;i=i+10)
        	range.add(i)
         axesLabelsRes.put(0,user)
        axesLabelsRes.put(1,range)
        [ studentResourceList: studentResourceList, studentResourceInstanceTotal: 30, axesLabelsRes:axesLabelsRes, chartValueRes:chartValueRes,countData:countData ,user:user]
     }

    def listUser = {
    	
    	/* Bar chart for resource usage by a user */
        def chartValueEvent = []
        def axesLabelsEvent = [:]
        def rangeEvent = []
        def event = []
        def maxValue = 0
        def studentResourceList = []
        def studentResourceInstance
        def studentList = []
    	GrailsHttpSession gh=getSession()
    	
    	if(params.siteId==null)
	    	params.siteId=params.id
	     	
 	    	def lms = "Moodle"
  	      	if(params.siteName!=""&&params.siteName!=null)
		gh.putValue("siteName",params.siteName)
 	    if(lms.equals("Sakai"))
 	    {
    			def sql = getSqlConnection()
	        /* Get all users for selection list */
	        sql.eachRow("select distinct SE.USER_ID as UserId,SU.EID as Login from SST_EVENTS SE,SAKAI_USER_ID_MAP SU "+ 
	        		"where SE.USER_ID = SU.USER_ID order by SU.EID")
	        {
	        	rowStud ->
	        	CourseActivity student = new CourseActivity()
	    		student.userId = rowStud.UserId
	    		student.userName = rowStud.Login
	    		studentList.add(student)
	        }
	        
	    	if(params.userId && params.userId != null)
	    	{
	    		/* Get all resource count for the user */
		        sql.eachRow("select SE.USER_ID as UserId,SU.EID as Login,SE.EVENT_ID as EventId,sum(SE.EVENT_COUNT) as EventCount "+ 
		        		"from SST_EVENTS SE,SAKAI_USER_ID_MAP SU where SE.USER_ID = SU.USER_ID and "+ 
		        		"SE.USER_ID='"+params.userId+"' group by SE.EVENT_ID order by SE.EVENT_ID")
		        {
		        	rowEvent ->
		        	studentResourceInstance = new CourseActivity()
	        		studentResourceInstance.userId = rowEvent.Login
	        		studentResourceInstance.userName = rowEvent.Login
		        	event.add(rowEvent.EventId)
		        	chartValueEvent.add(rowEvent.EventCount)
		        	if(rowEvent.EventCount > maxValue)
		        		maxValue = rowEvent.EventCount
		    		studentResourceInstance.eventId = rowEvent.EventId
		        	studentResourceInstance.eventCount = rowEvent.EventCount
		        	studentResourceList.add(studentResourceInstance)
		        }
	    	}
 	    }
 	   else 
	    {
	    		def sql=new Sql(dataSource);
	    	/* Get all users for selection list */
	        sql.eachRow("SELECT distinct username AS UserId FROM master where coursename='"+session.siteName+"' ORDER BY username")
	        {
	        	row ->
	        	//CourseActivity student = new CourseActivity()
	    		//student.userId = rowStud.UserId
	    		//student.userName = rowStud.Login
	    		studentList.add(row.UserId)
	        }
	        
	   }
        [studentList:studentList]
    }
    
    def listSite =
    {
    	
    	/* Bar chart for resource usage in a site */
        def chartValueEvent = []
        def axesLabelsEvent = [:]
        def rangeEvent = []
        def event = []
        def maxValue = 0
        def siteResourceList = []
        def siteResourceInstance
        def siteList = []
//          def sql = Sql.newInstance("jdbc:mysql://192.168.18.90:3306/aums_sakai", "root",
//                "devima", "com.mysql.jdbc.Driver")
            GrailsHttpSession gh=getSession()
	    
	   
	    def usr1=gh.getValue("UserId");
		
	     if(params.siteName!="")
	         gh.putValue("siteName",params.siteName)
	    
	     session['LMS']="Moodle"
	    def lms = gh.getValue("LMS")
	    if(params.id!=null)
	    	params.siteId=params.id
	    	 if(params.siteId=="")
	 	    	params.siteId=gh.getValue("siteId")
	    		
	    if(lms.equals("Sakai"))
	    {
	    	
	    }
	    else if(lms.equals("Moodle"))
	    {
	    	def sql=new Sql(dataSource);

	    	 // Get all sites for selection list
	  
	    	 
	       
	    	//moodlesql.eachRow("SELECT DISTINCT MC.id AS SiteId,MC.fullname AS SiteName"+
	    	//					" FROM mdl_course MC,mdl_log ML WHERE ML.course = MC.id")
	    	sql.eachRow("SELECT module AS SiteId FROM master where coursename='"+params.siteName+"' group by module")
			{
	        	row ->
	        	//CourseActivity site = new CourseActivity()
	        	//site.siteId = rowSite.SiteId
	        	//site.siteName = rowSite.SiteName
	    		siteList.add(row.SiteId)
			}
	        
	    }
        [siteList:siteList]
    }
    def studAct=
    {
    		GrailsHttpSession gh=getSession()
    		def usr1=gh.getValue("UserId");
		
    		session.modname=params.modname
    		def siteResourceList = []
    		def sql=new Sql(dataSource);
    		sql.eachRow("SELECT username AS uname,count(action) AS ca,coursename AS csn FROM master where module='"+params.modname+"' and coursename='"+session.siteName+"' group by username")
    		{
			        	row->
			        	CourseActivity siteResourceInstance = new CourseActivity()
			        	siteResourceInstance.userName = row.uname
			        	siteResourceInstance.siteId = row.csn
			        	siteResourceInstance.eventCount = row.ca
		        		siteResourceList.add(siteResourceInstance)
		}
	
		[siteResourceList:siteResourceList]		        
    
    }
    def studSingleAct=
    {
        		
     		def lst = []
     		def crs=[]
     		GrailsHttpSession gh=getSession()
     		def usr=gh.getValue("UserId");
		def usr1
		try
		 {
			usr1=usr.substring(0,usr.indexOf('@'))
		 }
	 	 catch(Exception e)
	 	 {
	 	 	usr1=usr
	 	 }
	 	
     		def sql=new Sql(dataSource);
              	sql.eachRow("select count(action) as cnt,module from master where username='"+usr1+"' and coursename='"+params.modname+"'group by module")
              	{ row ->
                   	lst.add(row.cnt)
              		crs.add(row.module);
              	
               	}
              	
           [lst:lst,crs:crs]   		     
        
    }
    def stud_CHART = {
             def lst = []
             def crs=[]
             def sql=new Sql(dataSource);
             sql.eachRow("SELECT username AS uname,count(action) AS ca,coursename AS csn FROM master where module='"+params.id+"' and coursename='"+session.siteName+"' group by username")
        	{ row ->
             		lst.add(row.ca)
        		crs.add(row.uname);
        		
         	}
        	
        		def c=new Chart(params.id)
        		def l=new LineChart()
			c.setXAxis(new XAxis().addLabels(crs));
			l.addValues(lst)
			c.setYAxis(new YAxis().setRange(0, lst.max(), 1));
			c.addElements(l)
			render c;
      }
      
      def stud_actBAR_CHART = {
	   	def lst = []
     		def crs=[]
	        Chart c = new Chart("amma");
	       	c.setYAxis(new YAxis().setRange(0, 100,10));
		def sql=new Sql(dataSource);
		sql.eachRow("select count(action) as cnt,module from master where coursename='"+session.siteName+"' group by module") 
		{ row ->
     			lst.add(row.cnt)
			crs.add(row.module);
		}
		BarChart b=new BarChart()
		b.setColour("#8B7D7");
		lst.sort();
		b.addValues(lst);
		c.setXAxis(new XAxis().setLabels(crs));
		c.addElements(b);
		render c;
  }
def DATE_CHART = {
	   	def lst = []
     		def crs=[]
     		Chart c = new Chart("User Wise Activities on "+params.id);
     		c.setYAxis(new YAxis().setRange(0, 100,10));
		def sql=new Sql(dataSource);
		sql.eachRow("select count(action) as cnt,username from master where DATE_FORMAT(date, '%d-%m-%Y')='"+params.id+"' and coursename='"+session.siteName+"' group by username") 
		{ row ->
     			lst.add(row.cnt)
			crs.add(row.username);
		}
		BarChart b=new BarChart();
		def bar=new BarChart.Bar(1,"#72B400");
		lst.sort();
		b.addValues(lst);
		c.setXAxis(new XAxis().setLabels(crs));
		c.addElements(b);
            
	render c;
  }
  def USER_ALLCOMP_CHART = {
  	def lst = []
       	def crs=[]
        def sql=new Sql(dataSource);
  	sql.eachRow("select count(action) as cnt,module from master where coursename='"+session.siteName+"' and username='"+params.id+"' group by module") 
  	{ row ->
       		lst.add(row.cnt)
  		crs.add(row.module);
	}
       	def c=new Chart()
        def l=new LineChart()
  	c.setXAxis(new XAxis().addLabels(crs));
  	l.addValues(lst);
  	c.setYAxis(new YAxis().setRange(0, lst.max(), 1));
  	c.addElements(l);
        render c;
  
    }
    def USER_ALLCOMP_LINE = {
      	def lst = []
        def crs=[]
        def sql=new Sql(dataSource);
      	sql.eachRow("select count(action) as cnt,username from master where coursename='"+session.siteName+"' group by username") 
      	{ row ->
           		lst.add(row.cnt)
      			crs.add(row.username);
    	}
        def c=new Chart()
        def l=new LineChart()
      	c.setXAxis(new XAxis().addLabels(crs));
      	l.addValues(lst);
      	c.setYAxis(new YAxis().setRange(0, lst.max(), 10));
      	c.addElements(l);
            render c;
      
    }
	def COURSE_USAGE = {
      		def lst = []
        	def crs=[]
   		def sql=new Sql(dataSource);
   		
      	sql.eachRow("select username as uname,count(action) as cnt from master where coursename='"+params.siteName+"' group by username")
      	{ row ->
           		lst.add(row.cnt)
      			crs.add(row.uname);
    	}
           	def c=new Chart()
           	def l=new LineChart()
      		c.setXAxis(new XAxis().addLabels(crs));
      		l.addValues(lst);
      		c.setYAxis(new YAxis().setRange(0, 200, 10));
      		c.addElements(l);
            render c;
      
    	}
  def date_learn=
  {
  		def lst = []
       		def crs=[]
       		def sql=new Sql(dataSource);
  		sql.eachRow("select count(action) as cnt,username from master where DATE_FORMAT(date, '%d-%m-%Y')='"+params.eventId+"' and coursename='"+session.siteName+"' group by username") 
  		{ row ->
       			lst.add(row.cnt)
  			crs.add(row.username);
  		}
  		              
	[lst:lst,crs:crs]
	
  
  }
  def userusage=
  {
  		def lst = []
	       	def crs=[]
	        def sql=new Sql(dataSource);
	  	sql.eachRow("select count(action) as cnt,module from master where coursename='"+session.siteName+"' and username='"+params.uname+"' group by module") 
	  	{ row ->
	       		lst.add(row.cnt)
	  		crs.add(row.module);
		}
	    [lst:lst,crs:crs,uname:params.id]  	 
  
  }
  def courseusage=
    {
    		def lst = []
  	       	def urs=[]
  	       	def sql=new Sql(dataSource);
  	  	sql.eachRow("select username as uname,count(CONCAT(module,'.',action)) as cnt from master where coursename='"+params.siteName+"' group by username") 
  	  	{ row ->
  	       		lst.add(row.cnt)
  	  		urs.add(row.uname);
  		}
  	    [lst:lst,urs:urs]  	 
    
  }
    def listSiteEvent = 
    {
    		GrailsHttpSession gh=getSession()
    	 	if(params.id!=null)
 	    	params.siteId=params.id
 	    	 if(params.siteId=="")
 	 	    	params.siteId=gh.getValue("siteId")
	    	
    		
    	/* Bar chart for students usage of an event in a site */
        	def chartValueUser = []
        	def axesLabelsUser = [:]
        	def rangeUser = []
        	def user = []
        	def maxValue = 0
        	def userList = []
        	def eventList = []
    	    	
	    	def lms = "Moodle"
	    	
	    	if(params.siteName!=""&&params.siteName!=null)
	         	gh.putValue("siteName",params.siteName)
	    	if(lms.equals("Sakai"))
	    	{
	    	
//       	def sql = Sql.newInstance("jdbc:mysql://192.168.18.90:3306/aums_sakai", "root",
//                "devima", "com.mysql.jdbc.Driver")
			def sql = getSqlConnection()
         
			/* Get all events in the site for selection */
	        sql.eachRow("select distinct SE.EVENT_ID as EventId from SST_EVENTS SE where SE.SITE_ID = '"+params.siteId+"' ")
	        {
	        	rowEvent ->
	        	CourseActivity event = new CourseActivity()
	        	event.eventId = rowEvent.EventId
	        	eventList.add(event)
	        }
	    	
	    	if(params.eventId && params.eventId != null)
	    	{
		    	/* Get event count for users in the site */
		    	sql.eachRow("select SE.USER_ID as UserId,SE.SITE_ID as SiteId,SE.EVENT_ID as EventId, "+
		    			"sum(SE.EVENT_COUNT) as EventCount,SS.TITLE as SiteName,SU.EID as UserLogin "+ 
		    			"from SST_EVENTS SE,SAKAI_SITE SS,SAKAI_USER_ID_MAP SU where SE.USER_ID = SU.USER_ID "+
		    			"and SE.SITE_ID = SS.SITE_ID and SE.SITE_ID = '"+params.siteId+
		    			"' and SE.EVENT_ID='"+params.eventId+"' group by SE.USER_ID order by SU.EID")
				{
		    		rowUser ->
		    		CourseActivity userInstance = new CourseActivity()
		    		userInstance.siteId = rowUser.SiteId
		    		userInstance.siteName = rowUser.siteName
		    		userInstance.eventId = rowUser.EventId
		    		userInstance.userId = rowUser.UserLogin
		    		userInstance.userName = rowUser.UserLogin
		    		userInstance.eventCount = rowUser.EventCount
		    		userList.add(userInstance)
		    		
		    		user.add(rowUser.UserLogin)
		    		chartValueUser.add(rowUser.EventCount)
		        	if(rowUser.EventCount > maxValue)
		        		maxValue = rowUser.EventCount
			
				}
	    	}
	    }
    	else if(lms.equals("Moodle"))
	    {
    		
    		def sql=new Sql(dataSource);
	    	
	    	/* Get all events in the site for selection */
	       // moodlesql.eachRow("select distinct CONCAT(ML.module,'.',ML.action)AS EventId from mdl_log ML where ML.course='"+ params.siteId +"' ")
	       sql.eachRow("select distinct DATE_FORMAT(date,'%d-%m-%Y') as EventId from master where coursename='"+session.siteName+"' group by date")
	        {
	        	rowEvent ->
	        	CourseActivity event = new CourseActivity()
	        	event.eventId = rowEvent.EventId
	        	eventList.add(event)
	        }
	        
	    	/*if(params.siteId && params.siteId != null)
	    	{
	    		moodlesql.eachRow("SELECT ML.userid AS UserId,MC.id AS SiteId,CONCAT(ML.module,'.',ML.action)AS EventId,"+
    					"COUNT(CONCAT(ML.module,'.',ML.action))AS EventCount,MC.fullname AS SiteName,"+
    					"MU.username AS UserLogin FROM mdl_log ML,mdl_course MC,mdl_user MU WHERE "+
    					"ML.course="+ params.siteId +" AND ML.course = MC.id AND MU.id = ML.userid "+
    					"and CONCAT(ML.module,'.',ML.action)='"+params.eventId+"' "+
    					"GROUP BY EventId ORDER BY MU.username")
	    		{
		    		rowUser ->
		    		CourseActivity userInstance = new CourseActivity()
		    		userInstance.siteId = rowUser.SiteId
		    		userInstance.siteName = rowUser.siteName
		    		userInstance.eventId = rowUser.EventId
		    		userInstance.userId = rowUser.UserLogin
		    		userInstance.userName = rowUser.UserLogin
		    		userInstance.eventCount = rowUser.EventCount
		    		userList.add(userInstance)
		    		
		    		user.add(rowUser.UserLogin)
		    		chartValueUser.add(rowUser.EventCount)
		        	if(rowUser.EventCount > maxValue)
		        		maxValue = rowUser.EventCount
			
				}
	    	}
	    }
    	
    	for(int i=0;i<=maxValue;i++)
    		rangeUser.add(i)
        	
        axesLabelsUser.put(0,user)
        axesLabelsUser.put(1,rangeUser)*/
    	
        }
        [ eventList:eventList]
    }
    
    def listSiteForLoginUser = {
    			def siteList = []
    	  		GrailsHttpSession gh=getSession()
    			def usr=gh.getValue("UserId");
			def usr1
			try
			{
				usr1=usr.substring(0,usr.indexOf('@'))
			}
			catch(Exception e)
			{
				 usr1=usr
	 	    	}
    			gh.putValue("UI", params.UI);
    	      		if( params.siteName!=null)
	             	gh.putValue("LMS", params.siteName);
	   		if(gh.getValue("ROLE")=="ROLE_ADMIN")
	    		{
	    	 		
	    			redirect uri: '/courseActivity/listGraphAdmin'
	    		
	    		}
	    		if(gh.getValue("ROLE")=="ROLE_SUPERADMIN")
			{
				    	 
				   
				    redirect uri: '/courseActivity/listGraphAdmin'
				    		
	    		}
	    		else if(gh.getValue("ROLE")=="ROLE_STUDENT")
	    		{
   	   
	    			redirect uri: '/courseActivity/studActivity'
   	    		}
   	    		else
	    		{
	    			def sql=new Sql(dataSource);
				sql.eachRow("SELECT DISTINCT coursename AS SiteName FROM master where username='"+usr1+"'")
				{
					rowSite ->
					CourseActivity siteInstance = new CourseActivity()
					//siteInstance.siteId = rowSite.SiteId
					siteInstance.siteName = rowSite.SiteName
					siteList.add(siteInstance)
	    			}
	   	
	     }
	    
	    
    	return [siteList:siteList]
	    	
    }
    
    
    
    def listSiteForAdmin = {
    		def siteList = []
    		GrailsHttpSession gh=getSession()
    	    	if(gh.getValue("LMS") == 'Sakai')
	    	{
	    	   	def sql = getSqlConnection()//Sql.newInstance("jdbc:mysql://192.168.18.90:3306/aums_sakai", "root",
	                //"devima", "com.mysql.jdbc.Driver")
	                def query 
	                 query= "SELECT SS.SITE_ID AS SiteId,SS.TITLE AS SiteName FROM SAKAI_SITE SS,SAKAI_SITE_USER SSU,SAKAI_USER_ID_MAP  SMP "+
	    	        " WHERE SSU.SITE_ID = SS.SITE_ID AND SMP.USER_ID= SSU.USER_ID  AND SMP.EID = '"+gh.getValue("UserId")+"'  AND SS.TITLE <>'My Workspace'"
	        sql.eachRow(query)
			{
	    			rowSite ->
	    			CourseActivity siteInstance = new CourseActivity()
	    			siteInstance.siteId = rowSite.SiteId
	    			siteInstance.siteName = rowSite.siteName
	    			siteList.add(siteInstance)
		 	}
	    }
	    else
	    {
	    	def sql=new Sql(dataSource);
	    	sql.eachRow("SELECT DISTINCT MC.id AS SiteId,MC.fullname AS SiteName FROM mdl_course MC,mdl_log ML"+
	    	" WHERE ML.course = MC.id")
	    	{
	    	    		rowSite ->
	    	    		CourseActivity siteInstance = new CourseActivity()
	    	    		siteInstance.siteId = rowSite.SiteId
	    	    		siteInstance.siteName = rowSite.siteName
	    	    		siteList.add(siteInstance)
	    		
	    	}
	    }
	    
    	return [siteList:siteList]
	    	
    }
    
    
    def listLMS = {
    		def ins = []
    		def lns=[]
      		GrailsHttpSession gh=getSession()
      		gh.putValue("UVS", params.siteName);
		def sql=new Sql(dataSource);
		sql.eachRow("SELECT distinct lmsname as ln FROM master")
      		{
	  	    row->
	    	    lns.add(row.ln)
		}
		sql.eachRow("SELECT distinct institution AS inst FROM master")
		{
	    	    row->
	    	    ins.add(row.inst)
	    	}

	[lns:lns,ins:ins]
   }
    
    def listGraph = {
    		GrailsHttpSession gh=getSession()
    		gh.putValue("siteId", params.siteId);
    		
    		if(gh.getValue("LMS")=="Sakai")
    		{
    			def sql = getSqlConnection()//Sql.newInstance("jdbc:mysql://192.168.18.90:3306/aums_sakai", "root",
        		//"devima", "com.mysql.jdbc.Driver")
		sql.eachRow("SELECT SS.TITLE AS SiteName FROM SAKAI_SITE SS where SS.SITE_ID = '"+params.siteId+"'")
		{
			rowSite ->
			
			
			gh.putValue("siteName", rowSite.siteName);
			gh.putValue("siteId", params.siteId);
		
		}
    	}
    	else
    	{
    		def lst=[]
    		def sql=new Sql(dataSource);
    		
	        sql.eachRow("SELECT DISTINCT  coursename AS SiteName FROM master"+
	    	        " WHERE coursename= '"+params.siteId+"'")
	    			{
	    	    		rowSite ->
	    	    		gh.putValue("siteName", rowSite.siteName);
	    	    		   		
	    			}
    	}
    	
    	
    	//gh.putValue("siteName", params.siteName);
    	   	
    	 
    }








    //Newly Added   
    def  listInstitutes = {
                def sql=new Sql(dataSource);
        	def instList = []  ;      
                sql.rows("SELECT DISTINCT(insti_code) AS INSTITUTE FROM master order by insti_code asc").each
                    { row ->
                        instList.add(row.INSTITUTE)
                    }

                def cal = Calendar.instance;
                def year = cal.get(Calendar.YEAR);
                return [instList:instList,year:year]
      }



    def redirectpage ={       
         GrailsHttpSession gh=getSession()
	 gh.putValue("sel_institute",params.institute)
         gh.putValue("sel_year",params.year)
         redirect action: 'listAdminOptions', controller:'courseActivity'
    }

     def listAdminOptions = {
                GrailsHttpSession gh=getSession()
                def sel_inst=gh.getValue("sel_institute");
                def sel_year=gh.getValue("sel_year");
        	def courseList = []
        	def sql=new Sql(dataSource);         
                sql.rows("SELECT DISTINCT(coursename) AS COURSE FROM master where insti_code ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"'").each
                    { row ->
                        courseList.add(row.COURSE)
                    }
            	return [courseList:courseList,institute:sel_inst,year:sel_year]
      }

    def summaryStackChart={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sql=new Sql(dataSource);
        def list1=sql.rows("select distinct(coursename) as COURSE from master where insti_code ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' order by coursename asc")
        def list2=sql.rows("select distinct(module) as MODULE from master where insti_code ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' order by module asc")
        String xml_cont='<institute>';
        String x='';
        for(a in list1)
        {
            xml_cont+='<courses>';
            xml_cont+='<coursename>'+a.COURSE+'</coursename>'
            for(b in list2)
              {

                    xml_cont+='<'+b.MODULE+'>'
                    sql.rows("select count(action) as totcnt from master where  insti_code ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' and coursename ='"+a.COURSE+"' and module='"+b.MODULE+"'").each { row ->
                    xml_cont+=row.totcnt
                    xml_cont+='</'+b.MODULE+'>'
                }
              }
             xml_cont+='</courses>'
        }
        xml_cont+='</institute>'
        render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
    }

    def  summaryStackChartGrid={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sql=new Sql(dataSource);
        def query="SELECT coursename AS COURSE,module AS MODULE,count(action) AS USAGE_COUNT FROM master where insti_code ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' GROUP BY coursename,module"
        def list=sql.rows(query)
        render(contentType:"text/xml")
              {
                 components
                   {
                      for(a in list)
                      {
                        component()
                        {
                          course(a.COURSE)
                          module(a.MODULE)
                          count(a.USAGE_COUNT)
                       }
                     }
                   }
              }
    }
    def showSummary={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        return [institute:sel_inst,year:sel_year]
    }


   //For Visit Details
    def visitDetails={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sql=new Sql(dataSource);
        def query="SELECT coursename AS COURSE,count(action)AS TOTAL_VISITS FROM master where insti_code ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' GROUP BY coursename"
        def list=sql.rows(query)
        render(contentType:"text/xml")
              {
                 institutes
                   {
                      for(a in list)
                      {
                        courses()
                        {
                          coursename(a.COURSE)
                          count(a.TOTAL_VISITS)
                       }
                     }
                   }
              }
    }
   def showVisitDetails={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        return [institute:sel_inst,year:sel_year]
   }


    //For Viusl Details
      def showVisualDetails={
          def sel_course=params.hidCourse1;
          GrailsHttpSession gh=getSession();
          gh.putValue("sel_course", sel_course);
          def sel_inst=gh.getValue("sel_institute");
          def sel_year=gh.getValue("sel_year");
          return [institute:sel_inst,year:sel_year]
      }
      
   def moduleList={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sel_course=gh.getValue("sel_course");
        def sql=new Sql(dataSource);
        def query="SELECT distinct(module) as MODULE FROM master where coursename='"+sel_course+"'";
        def list=sql.rows(query);
        render(contentType:"text/xml")
              {
                 institute
                   {
                      for(a in list)
                      {
                        module()
                        {
                          label(a.MODULE)
                          data(a.MODULE)
                       }
                     }
                   }
              }
   }

    def visitDetailsChart={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sel_course=gh.getValue("sel_course");
        def sel_module=params.module;
        def sql=new Sql(dataSource);
        def query="SELECT username AS USER,count(action) AS TOT_COUNT FROM master where ( module='"+sel_module+"' and coursename='"+sel_course+"' and insti_code ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' ) group by username";
        def list=sql.rows(query);
        render(contentType:"text/xml")
              {
                 courses
                   {
                      for(a in list)
                      {
                        modules()
                        {
                          username(a.USER)
                          count(a.TOT_COUNT)
                       }
                     }
                   }
              }
    }

 


    //For Time Utilization
       def showTimeUtilization={
          def sel_course=params.hidCourse2;
          GrailsHttpSession gh=getSession();
          gh.putValue("sel_course", sel_course);
          def sel_inst=gh.getValue("sel_institute");
          def sel_year=gh.getValue("sel_year");
          return [institute:sel_inst,course:sel_course,year:sel_year]
      }

      def dateList={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sel_course=gh.getValue("sel_course");
        def sql=new Sql(dataSource);
        def query="select distinct DATE_FORMAT(date,'%d-%m-%Y') as EVENTDATE from master where ( coursename='"+sel_course+"' and insti_code ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' ) group by date";
        def list=sql.rows(query);
        render(contentType:"text/xml")
              {
                 institute
                   {
                      for(a in list)
                      {
                        module()
                        {
                          label(a.EVENTDATE)
                          data(a.EVENTDATE)
                       }
                     }
                   }
              }
   }



     def timeUtilChart={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sel_course=gh.getValue("sel_course");
        def sel_date=params.date;
        def sql=new Sql(dataSource);
        def query="select count(action) as TOT_COUNT,username as USER from master where ( DATE_FORMAT(date, '%d-%m-%Y')='"+sel_date+"' and coursename='"+sel_course+"' and insti_code ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' ) group by username";
        //println(query);
        def list=sql.rows(query);
        render(contentType:"text/xml")
              {
                 courses
                   {
                      for(a in list)
                      {
                        modules()
                        {
                          username(a.USER)
                          count(a.TOT_COUNT)
                       }
                     }
                   }
              }
    }




    //For Student Summary

     def showStudSummary={
          def sel_course=params.hidCourse3;
          GrailsHttpSession gh=getSession();
          gh.putValue("sel_course", sel_course);
          def sel_inst=gh.getValue("sel_institute");
          def sel_year=gh.getValue("sel_year");
          return [institute:sel_inst,course:sel_course,year:sel_year]
      }


    def studList={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sel_course=gh.getValue("sel_course");
        def sql=new Sql(dataSource);
        def query="SELECT distinct username AS USERNAME FROM master where ( coursename='"+sel_course+"' and insti_code ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' ) ORDER BY username asc";
        def list=sql.rows(query);
        render(contentType:"text/xml")
              {
                 institute
                   {
                      for(a in list)
                      {
                        user()
                        {
                          label(a.USERNAME)
                          data(a.USERNAME)
                       }
                     }
                   }
              }
   }

 def studSummaryChart={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sel_course=gh.getValue("sel_course");
        def sel_student=params.stud;
        def sql=new Sql(dataSource);
       // def query="select count(action) as TOT_COUNT,username as USER from master where ( DATE_FORMAT(date, '%d-%m-%Y')='"+sel_date+"' and coursename='"+sel_course+"' and insti_code ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' ) group by username";
        def query="select count(action) as TOT_COUNT,module as MODULE from master where ( coursename='"+sel_course+"' and username='"+sel_student+"' and insti_code ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' ) group by module"
       // println(query);
        def list=sql.rows(query);
        render(contentType:"text/xml")
              {
                 courses
                   {
                      for(a in list)
                      {
                        students()
                        {
                          module(a.MODULE)
                          count(a.TOT_COUNT)
                       }
                     }
                   }
              }
    }






    


    def show = {
        	def courseActivityInstance = CourseActivity.get( params.id )
        if(!courseActivityInstance) {
            flash.message = "CourseActivity not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ courseActivityInstance : courseActivityInstance ] }
    }

    def delete = {
        	def courseActivityInstance = CourseActivity.get( params.id )
        	if(courseActivityInstance) {
            		try {
                		courseActivityInstance.delete(flush:true)
                		flash.message = "CourseActivity ${params.id} deleted"
                		redirect(action:list)
            		     }
            		catch(org.springframework.dao.DataIntegrityViolationException e) {
                		flash.message = "CourseActivity ${params.id} could not be deleted"
                		redirect(action:show,id:params.id)
            }
        }
        else {
            	flash.message = "CourseActivity not found with id ${params.id}"
            	redirect(action:list)
        }
    }

    def edit = {
        	def courseActivityInstance = CourseActivity.get( params.id )
        	if(!courseActivityInstance) {
            	flash.message = "CourseActivity not found with id ${params.id}"
            	redirect(action:list)
        	}
        	else 
        	{
            		return [ courseActivityInstance : courseActivityInstance ]
        	}
    }

    def update = {
        	def courseActivityInstance = CourseActivity.get( params.id )
        	if(courseActivityInstance) {
            	if(params.version) {
                	def version = params.version.toLong()
                if(courseActivityInstance.version > version) {
                    
                    courseActivityInstance.errors.rejectValue("version", "courseActivity.optimistic.locking.failure", "Another user has updated this CourseActivity while you were editing.")
                    render(view:'edit',model:[courseActivityInstance:courseActivityInstance])
                    return
                }
            }
            courseActivityInstance.properties = params
            if(!courseActivityInstance.hasErrors() && courseActivityInstance.save()) {
                flash.message = "CourseActivity ${params.id} updated"
                redirect(action:show,id:courseActivityInstance.id)
            }
            else {
                render(view:'edit',model:[courseActivityInstance:courseActivityInstance])
            }
        }
        else {
            flash.message = "CourseActivity not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def create = {
        def courseActivityInstance = new CourseActivity()
        courseActivityInstance.properties = params
        return ['courseActivityInstance':courseActivityInstance]
    }

    def save = {
        def courseActivityInstance = new CourseActivity(params)
        if(!courseActivityInstance.hasErrors() && courseActivityInstance.save()) {
            flash.message = "CourseActivity ${courseActivityInstance.id} created"
            redirect(action:show,id:courseActivityInstance.id)
        }
        else {
            render(view:'create',model:[courseActivityInstance:courseActivityInstance])
        }
    }









    def  test={
        def sql=new Sql(dataSource);
        def query="SELECT course AS SiteId,coursename AS TITLE,module AS EVENT_ID,count(action) AS EVENTNO FROM master  GROUP BY coursename,module"
        def list=sql.rows(query)
        render(contentType:"text/xml")
              {
                 components
                   {
                      for(a in list)
                      {
                        component()
                        {
                          title(a.TITLE)
                          event(a.EVENT_ID)
                          count(a.EVENTNO)
                       }
                     }
                   }
              }
    }


  def upload = {
         def xml =request.getFile("file").inputStream.text
      //    xml+="""<results>
        //           <success>true</success>
        //            </results>""";
        render xml;
  }

    
    /* Get sql connection from properties file */
    private Sql getSqlConnection()
    {
    	Properties props = new Properties()
    	def webRootDir = servletContext.getRealPath("/")
         
    	
    	FileInputStream fis = new FileInputStream(webRootDir+ "/images/sakaiDb.properties" )
    	props.load( fis )
    	fis.close()
    	
    	String url = props.getProperty( "sakaiDbUrl" )
    	String user = props.getProperty("sakaiDbUSer")
    	String password = props.getProperty("sakaiDbPassword")
    	String driver = props.getProperty("sakaiDbDriver")
    	
    	
    	def sql = Sql.newInstance(url, user,password, driver)
    	return sql
    }
    private Sql getMoodleConnection()
    {
    	Properties props = new Properties()
    	def webRootDir = servletContext.getRealPath("/")
         
       	FileInputStream fileInputStream = new FileInputStream(webRootDir+ "/images/moodleDb.properties" )
    	props.load( fileInputStream )
    	fileInputStream.close()
    	
    	String url = props.getProperty( "moodleDbUrl" )
    	String user = props.getProperty("moodleDbUSer")
    	String password = props.getProperty("moodleDbPassword")
    	String driver = props.getProperty("moodleDbDriver")
    	
       	def sql = Sql.newInstance(url, user,password, driver)
    	return sql
    }
}