import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import groovy.sql.Sql
import java.util.*;
import grails.converters.*



class CourseActivityController {
	def dataSource
     	def index = { redirect(action:list,params:params) }
    	// the delete, save and update actions only accept POST requests
    	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
    	def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)       
    }
    

    //Newly Added   
    def  listInstitutes = {
			def sql=new Sql(dataSource);
			def instList = []  ; 
			def instInstance;    
			     
			sql.eachRow("SELECT DISTINCT(a.inst_id) as INSTITUTE_ID,b.inst_name AS INSTITUTE FROM master a INNER JOIN institute b where a.inst_id=b.id order by b.inst_name asc")
				{ 
				    row ->
					instInstance  = new CourseActivity()
	    		                instInstance.instId = row.INSTITUTE_ID
	    		                instInstance.instName = row.INSTITUTE
	    		                instList.add(instInstance)
				}
								
			def cal = Calendar.instance;
			def year = cal.get(Calendar.YEAR);			
			return [instList:instList,year:year]
      }


    /*
    def redirectpage ={       
			 GrailsHttpSession gh=getSession()
			 gh.putValue("sel_institute",params.institute)
			 gh.putValue("sel_year",params.year)
			 redirect action: 'listAdminOptions', controller:'courseActivity'
    }
	*/
	
    /* Re-Written for Flex Select Year Popup */
	def redirectpage ={      
	           
				 def inst_id=params.inst_id;
				 def sel_year=params.sel_year;				
				 GrailsHttpSession gh=getSession()
				 gh.putValue("sel_institute",inst_id)
				 gh.putValue("sel_year",sel_year)				 
				 def xml_cont="";
				 xml_cont+='<redirect><institute><url>success</url></institute></redirect>';
                 render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8");			
		}


     def listAdminOptions = {
			GrailsHttpSession gh=getSession()
			def sel_inst=gh.getValue("sel_institute");
			def sel_year=gh.getValue("sel_year");
			def courseList = []
			def crsInstance;    
			def sql=new Sql(dataSource);  
			            
				sql.eachRow("SELECT DISTINCT(a.course_id) AS COURSE_ID,b.course_name as COURSE FROM master a INNER JOIN course_master b where a.inst_id ='"+sel_inst+"' and  DATE_FORMAT(a.date, '%Y')='"+sel_year+"' and a.course_id=b.id order by b.course_name asc")
				{ row ->
					crsInstance  = new CourseActivity()
	    		    crsInstance.crsId = row.COURSE_ID
	    		    crsInstance.crsName = row.COURSE
	    		    courseList.add(crsInstance)	
				}
				
				
				
			def lmsval = sql.firstRow("select distinct(a.lms_id) as LMS_ID,b.lms_name as LMS  from master a INNER JOIN lms b where a.inst_id='"+sel_inst+"' and a.lms_id=b.id");
			def lms_used=lmsval.LMS;  
			def insval = sql.firstRow("select inst_name as INSTITUTE from institute where id='"+sel_inst+"'");
			def inst_name=insval.INSTITUTE;                
			gh.putValue("lms_used",lms_used)
			
			
			return [courseList:courseList,institute:inst_name,year:sel_year,lms_used:lms_used]
      }



    def summaryStackChart={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sql=new Sql(dataSource);
			
        def list1=sql.rows("select distinct(a.course_id) as COURSE_ID,b.course_name as COURSE from master a INNER JOIN course_master b where inst_id ='"+sel_inst+"' and  DATE_FORMAT(a.date, '%Y')='"+sel_year+"' and a.course_id=b.id order by b.course_name asc");
		
        def list2=sql.rows("select distinct(module_name) as MODULE from master where inst_id ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' order by module_name asc");
		
        String xml_cont='<institute>';
        String x='';
        for(a in list1)
        {
            xml_cont+='<courses>';
            xml_cont+='<coursename>'+a.COURSE+'</coursename>'
            for(b in list2)
              {

                    xml_cont+='<'+b.MODULE+'>'
                    sql.rows("select count(action) as totcnt from master where inst_id ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' and course_id ='"+a.COURSE_ID+"' and module_name='"+b.MODULE+"'").each { row ->
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
        def query="SELECT coursename AS COURSE,module AS MODULE,count(action) AS USAGE_COUNT FROM master where inst_id ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' GROUP BY coursename,module"
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
        def lms_used=gh.getValue("lms_used");
		def sql=new Sql(dataSource);
	    def insval = sql.firstRow("select inst_name as INSTITUTE from institute where id='"+sel_inst+"'");
		def insname=insval.INSTITUTE;  
        return [institute:insname,year:sel_year,lms_used:lms_used]
    }


   //For Visit Details
    def visitDetails={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");		
        def sql=new Sql(dataSource);



        def query="SELECT a.course_id AS COURSE_ID,b.course_name as COURSE,count(a.action)AS TOTAL_VISITS FROM master a INNER JOIN course_master b where a.inst_id='"+sel_inst+"' and  DATE_FORMAT(a.date, '%Y')='"+sel_year+"' and a.course_id=b.id GROUP BY b.course_name"
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
        def lms_used=gh.getValue("lms_used");
		def sql=new Sql(dataSource);
	    def insval = sql.firstRow("select inst_name as INSTITUTE from institute where id='"+sel_inst+"'");
		def insname=insval.INSTITUTE;  
        return [institute:insname,year:sel_year,lms_used:lms_used]
   }


    //For Visual Details
      def showVisualDetails={
          def sel_course=params.hidCourse1;
          GrailsHttpSession gh=getSession();
          gh.putValue("sel_course", sel_course);
          def sel_inst=gh.getValue("sel_institute");
          def sel_year=gh.getValue("sel_year");
          def lms_used=gh.getValue("lms_used");
		  def sql=new Sql(dataSource);
		  def insval = sql.firstRow("select inst_name as INSTITUTE from institute where id='"+sel_inst+"'");
		  def insname=insval.INSTITUTE;  
          return [institute:insname,year:sel_year,lms_used:lms_used]
      }
      
	  
	  
   def moduleList={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sel_course=gh.getValue("sel_course");
        def sql=new Sql(dataSource);
        def query="SELECT distinct(module_name) as MODULE FROM master where course_id='"+sel_course+"'";
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
        def query="SELECT user_name AS USER,count(action) AS TOT_COUNT FROM master where ( module_name='"+sel_module+"' and course_id='"+sel_course+"' and inst_id='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' ) group by user_name";
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
		 // println(sel_course);
          GrailsHttpSession gh=getSession();
          gh.putValue("sel_course", sel_course);
          def sel_inst=gh.getValue("sel_institute");
          def sel_year=gh.getValue("sel_year");
          def lms_used=gh.getValue("lms_used");
		  def sql=new Sql(dataSource);
		  def insval = sql.firstRow("select inst_name as INSTITUTE from institute where id='"+sel_inst+"'");
		  def insname=insval.INSTITUTE;  
		  def crsval = sql.firstRow("select course_name as COURSE from course_master where id='"+sel_course+"'");
		  def crsname=crsval.COURSE;  		  
          return [institute:insname,year:sel_year,lms_used:lms_used,sel_course:crsname]
      }

      def dateList={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sel_course=gh.getValue("sel_course");
        def sql=new Sql(dataSource);
        def query="select distinct DATE_FORMAT(date,'%d-%m-%Y') as EVENTDATE from master where ( course_id='"+sel_course+"' and inst_id ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' ) group by date";
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
        def query="select count(action) as TOT_COUNT,user_name as USER from master where ( DATE_FORMAT(date, '%d-%m-%Y')='"+sel_date+"' and course_id='"+sel_course+"' and inst_id='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' ) group by user_name";      
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
          def lms_used=gh.getValue("lms_used");
		  def sql=new Sql(dataSource);
		  def insval = sql.firstRow("select inst_name as INSTITUTE from institute where id='"+sel_inst+"'");
		  def insname=insval.INSTITUTE;  
		  def crsval = sql.firstRow("select course_name as COURSE from course_master where id='"+sel_course+"'");
		  def crsname=crsval.COURSE;  		  
          return [institute:insname,year:sel_year,lms_used:lms_used,sel_course:crsname]
      }


    def studList={
        GrailsHttpSession gh=getSession()
        def sel_inst=gh.getValue("sel_institute");
        def sel_year=gh.getValue("sel_year");
        def sel_course=gh.getValue("sel_course");
        def sql=new Sql(dataSource);
        def query="SELECT distinct user_name AS USERNAME FROM master where ( course_id='"+sel_course+"' and inst_id ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' ) ORDER BY user_name asc";
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
        def query="select count(action) as TOT_COUNT,module_name as MODULE from master where ( course_id='"+sel_course+"' and user_name='"+sel_student+"' and inst_id ='"+sel_inst+"' and  DATE_FORMAT(date, '%Y')='"+sel_year+"' ) group by module_name"
      
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



/*######################################## STAFF VIEWS ########################*/

    def listStaffCourses = {
    			def courseList = []
				def sql=new Sql(dataSource);
					def crsInstance;   
                        GrailsHttpSession gh=getSession()
    	                def user_id=gh.getValue("currUsername");
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
	    			 
				sql.eachRow("SELECT DISTINCT(a.course_id) AS COURSE_ID,b.course_name as COURSE FROM master a INNER JOIN course_master b  where a.user_name='"+user_id+"' and a.course_id=b.id order by b.course_name asc")
				{
					  row ->
					   crsInstance  = new CourseActivity()
	    		       crsInstance.crsId = row.COURSE_ID
	    		       crsInstance.crsName = row.COURSE		
					   courseList.add(crsInstance)				   
	    			}
	     }

            def cal = Calendar.instance;
            def year = cal.get(Calendar.YEAR);
	    return [courseList:courseList,year:year]
    }
	
	

    def redirectstaffpage ={
         GrailsHttpSession gh=getSession()
	 gh.putValue("sel_course",params.course)
        // gh.putValue("sel_year",params.year)
         redirect action: 'listStaffOptions', controller:'courseActivity'
    }


    def listStaffOptions = {
                GrailsHttpSession gh=getSession()
                def sel_course=gh.getValue("sel_course");
               // def sel_year=gh.getValue("sel_year");
			    def sql=new Sql(dataSource);
	            def crsval = sql.firstRow("select course_name as COURSE from course_master where id='"+sel_course+"'");
        		def crsname=crsval.COURSE;  
				gh.putValue("crsval",crsname)
            	return [sel_course:crsname]
      }

//For Visual Details
      def showStaffVisualDetails={
           GrailsHttpSession gh=getSession()
           def sel_course=gh.getValue("sel_course");
		    def sql=new Sql(dataSource);
			def crsval = sql.firstRow("select course_name as COURSE from course_master where id='"+sel_course+"'");
			def crsname=crsval.COURSE;  
           return [sel_course:crsname]
      }

    def staffModuleList={
        GrailsHttpSession gh=getSession()
        def sel_course=gh.getValue("sel_course");   
        def staff_id=gh.getValue("currUsername");
        def sql=new Sql(dataSource);
        def query="SELECT distinct(module_name) as MODULE FROM master where course_id='"+sel_course+"'";       
        def list=sql.rows(query);
        render(contentType:"text/xml")
              {
                 staff
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


     def staffVisualChart={
        GrailsHttpSession gh=getSession()
        def sel_course=gh.getValue("sel_course");
        def staff_id=gh.getValue("currUsername");
        def sel_module=params.module;
        def sql=new Sql(dataSource);
        def query="SELECT user_name as USER,count(action) AS TOT_COUNT FROM master where ( module_name='"+sel_module+"' and course_id='"+sel_course+"' and LOWER(user_type) ='student') group by user_name";
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

      def showStaffTimeUtilization={
            GrailsHttpSession gh=getSession()
           def sel_course=gh.getValue("sel_course");
		   def sql=new Sql(dataSource);
	       def crsval = sql.firstRow("select course_name as COURSE from course_master where id='"+sel_course+"'");
           def crsname=crsval.COURSE;  
           return [sel_course:crsname]
      }

      def staffDateList={
        GrailsHttpSession gh=getSession()
        def sel_course=gh.getValue("sel_course");
        def staff_id=gh.getValue("currUsername");
        def sql=new Sql(dataSource);
        def query="select distinct DATE_FORMAT(date,'%d-%m-%Y') as EVENTDATE from master where ( course_id='"+sel_course+"') group by date";
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

    def staffUtilChart={
        GrailsHttpSession gh=getSession()
        def sel_course=gh.getValue("sel_course");
        def sel_date=params.date;
        def sql=new Sql(dataSource);
        def query="select count(action) as TOT_COUNT,user_name as USER from master where ( DATE_FORMAT(date, '%d-%m-%Y')='"+sel_date+"' and course_id='"+sel_course+"' and LOWER(user_type) ='student' ) group by user_name";
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



      def showStaffSummary={
            GrailsHttpSession gh=getSession()
           def sel_course=gh.getValue("sel_course");
		   def sql=new Sql(dataSource);
	       def crsval = sql.firstRow("select course_name as COURSE from course_master where id='"+sel_course+"'");
           def crsname=crsval.COURSE;  
           return [sel_course:crsname]
      }
	  
	  
	  def staffCourseSummary={
           GrailsHttpSession gh=getSession()
           def sel_course=gh.getValue("sel_course");
		   def sql=new Sql(dataSource);
	       def crsval = sql.firstRow("select course_name as COURSE from course_master where id='"+sel_course+"'");
           def crsname=crsval.COURSE;  
           return [sel_course:crsname]
      }
	  
	  //currently editing
	  
	   def staffCourseSummaryChart={
           GrailsHttpSession gh=getSession()
           //def sel_course=gh.getValue("sel_course");
		   def sel_course=17;
		   def sql=new Sql(dataSource);
	       def list=sql.rows("select distinct(module_name) as MODULE from master order by module_name asc");
		   def xml_cont='';  
  			xml_cont+='<institute>';  
			for(a in list)
						  {
								xml_cont+='<course>';                    
								xml_cont+='<module>'+a.MODULE+'</module>';
								xml_cont+='<usage>';
								sql.rows("select count(action) as totcnt from master where  course_id ='"+sel_course+"' and module_name='"+a.MODULE+"'").each { row ->
								xml_cont+=row.totcnt
								xml_cont+='</usage>';
							}
					xml_cont+='</course>';
						  }
			  xml_cont+='</institute>'; 
			  render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
      }
	  
	  
	  
    def staffStudList={
        GrailsHttpSession gh=getSession()
        def sel_course=gh.getValue("sel_course");
        def staff_id=gh.getValue("currUsername");
        def sql=new Sql(dataSource);
        def query="SELECT distinct user_name AS USERNAME FROM master where ( course_id='"+sel_course+"' and LOWER(user_type)='student' ) ORDER BY user_name asc";
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


 def studSummaryChart1={
       GrailsHttpSession gh=getSession()
        def sel_course=gh.getValue("sel_course");
        def sel_student=params.stud;
        def sql=new Sql(dataSource);
        def query="select count(action) as TOT_COUNT,module_name as MODULE from master where ( course_id='"+sel_course+"' and user_name='"+sel_student+"' ) group by module_name"
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


      def showStaffStackSummary={
         GrailsHttpSession gh=getSession()
           def sel_course=gh.getValue("sel_course");
		   def sql=new Sql(dataSource);
	       def crsval = sql.firstRow("select course_name as COURSE from course_master where id='"+sel_course+"'");
           def crsname=crsval.COURSE;  
           return [sel_course:crsname]
    }

def summaryStackChart1={
        GrailsHttpSession gh=getSession()
        def sel_course=gh.getValue("sel_course");
        def staff_id=gh.getValue("currUsername");
        def sql=new Sql(dataSource);
        def list1=sql.rows("select distinct(course_id) as COURSE_ID from master")
        def list2=sql.rows("select distinct(module_name) as MODULE from master order by module_name asc")
        String xml_cont='<institute>';
        String x='';
        for(a in list1)
        {
            def crsval = sql.firstRow("select course_name as COURSE from course_master where id='"+a.COURSE_ID+"'");
            def crsname=crsval.COURSE;  
		    xml_cont+='<courses>';
            xml_cont+='<coursename>'+crsname+'</coursename>'
            for(b in list2)
              {

                    xml_cont+='<'+b.MODULE+'>'
                    sql.rows("select count(action) as totcnt from master where  course_id ='"+a.COURSE_ID+"' and module_name='"+b.MODULE+"'").each { row ->
                    xml_cont+=row.totcnt
                    xml_cont+='</'+b.MODULE+'>'
                }
              }
             xml_cont+='</courses>'
        }
        xml_cont+='</institute>'
        render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
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
   
}