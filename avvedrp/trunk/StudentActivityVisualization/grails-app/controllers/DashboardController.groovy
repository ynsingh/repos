import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.httpclient.*;
import groovy.sql.Sql

import java.util.*;
import java.io.*;
import grails.converters.*


//import org.pentaho.di.core.*;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.trans.StepLoader;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransHopMeta;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.di.trans.steps.selectvalues.SelectValuesMeta;
import org.pentaho.di.trans.steps.tableinput.TableInputMeta;
import org.pentaho.di.trans.steps.tableoutput.TableOutputMeta;



class DashboardController {
        def dataSource
     	def index = { redirect(action:list,params:params) }    	
    	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
			def authenticateService
       


 /*----------------------  DASH BOARD FUNCTIONS FOR UNIVERSITY USER STARTS HERE  -------------------------------------------- */


    def admindashboard = {
	//authenticate();
	//if (!authenticateService.isLoggedIn()) { redirect action: 'index', controller:'login' }
	/*
         def instituteList = []
         def sql=new Sql(dataSource);
         sql.eachRow("SELECT distinct(inst_code) AS institute FROM master  where inst_code<>'' order by course asc")
          {
                row ->
                instituteList.add(row.institute)
          }
          [instituteList:instituteList]
		  */
		
    }


   def institutes={
        def sql=new Sql(dataSource);
        def query="select distinct(a.inst_id) as Insti_id,b.inst_name as Institute from master a INNER JOIN institute b  where a.inst_id=b.id  order by inst_name asc"
        def list=sql.rows(query)
          render(contentType:"text/xml")
          {
            university
               {
                  for(a in list)
                  {
                    institute()
                    {
                      label(a.Institute)
                      insti_id(a.Insti_id)
                    }
                  }
               }
            }
         }



    def years={
        def sql=new Sql(dataSource);
        def query="select distinct(year(date)) as year from master where year(date)<>'' order by year asc"
        def list=sql.rows(query)
          render(contentType:"text/xml")
          {
            institute
               {
                  for(a in list)
                  {
                    year()
                    {
                      label(a.year)
                      data(a.year)
                    }
                  }
               }
           }
       }



    def courses={
        def sql=new Sql(dataSource);
        def query="select distinct(a.course_id) as COURSE_ID,b.course_name as COURSE from master a INNER JOIN course_master b where a.course_id=b.id order by b.course_name asc"
        def list=sql.rows(query)
          render(contentType:"text/xml")
          {
            institute
               {
                  for(a in list)
                  {
                    course()
                    {
                      label(a.COURSE)
                      course_id(a.COURSE_ID)
                    }
                  }
               }
          }
      }


  
    def overallUsage={
		
	     def from_val=params.from;
	     def to_val=params.to;	
        def sql=new Sql(dataSource);		
		/*
		SELECT count(a.action) as LMS_USAGE,DATE_FORMAT(a.date, '%Y') as date,b.lms_name as LMS,c.inst_name as INSTITUTE from master a INNER JOIN lms b INNER JOIN institute c where  a.lms_id=b.id and a.inst_id=c.id and (DATE_FORMAT(a.date, '%Y')>='2001' and DATE_FORMAT(a.date, '%Y')<='2010' )group by c.inst_name
		*/

        def query="SELECT count(a.action) as LMS_USAGE,b.lms_name as LMS,c.id as INSTITUTE_ID,c.inst_name as INSTITUTE from master a INNER JOIN lms b   INNER JOIN institute c where  a.lms_id=b.id and a.inst_id=c.id and date_format(a.date,'%Y')>='"+from_val+"' and  date_format(a.date,'%Y')<='"+to_val+"' group by c.inst_name";	
		
        def list=sql.rows(query)
          render(contentType:"text/xml")
          {
             university
               {
                  for(a in list)
                  {
                    institute()
                    {
                      lmsname(a.LMS)
                      insid(a.INSTITUTE_ID)
                      insname(a.INSTITUTE)
                      usage(a.LMS_USAGE)
                    }
                 }
              }
          }
      }


    def staffstudUsage={	
	    def from_val=params.from;
	    def to_val=params.to;	
        def sql=new Sql(dataSource);
        def list1=sql.rows("select distinct(a.inst_id) as Insti_id,b.inst_name as Institute from master a INNER JOIN institute b  where a.inst_id=b.id  order by inst_name asc")
        def list2=sql.rows("select distinct(LOWER(user_type)) as usertype from master  where ( LOWER(user_type) = 'teacher' OR LOWER(user_type) = 'student' ) order by user_type asc")
        String xml_cont='<university>';
        String x='';
        for(a in list1)
        {
            xml_cont+='<institutes>';
            xml_cont+='<institute>'+a.Institute+'</institute>';
            for(b in list2)
              {
                    xml_cont+='<'+b.usertype+'>';
                    sql.rows("select count(action) as totcnt from master where  LOWER(user_type) ='"+b.usertype+"' and inst_id='"+a.Insti_id+"' and date_format(date,'%Y')>='"+from_val+"' and  date_format(date,'%Y')<='"+to_val+"'").each { row ->
                    xml_cont+=row.totcnt;
                    xml_cont+='</'+b.usertype+'>';
					
					
                }
              }
             xml_cont+='</institutes>';
        }
        xml_cont+='</university>';
        render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8");
    }



    def coursewiseUsage={	
	def from_val=params.from;
	def to_val=params.to;		
    def course_id=params.course_id
     def sql=new Sql(dataSource);
            def query="select count(a.action) as LMS_USAGE,b.inst_name as INSTITUTE,c.course_name from master a INNER JOIN institute b INNER JOIN course_master c where a.course_id='"+course_id+"' and a.inst_id=b.id and a.course_id=c.id and date_format(a.date,'%Y')>='"+from_val+"' and  date_format(a.date,'%Y')<='"+to_val+"' group by a.inst_id order by b.inst_name asc";
			
		
			
            def list=sql.rows(query)
              render(contentType:"text/xml")
              {
                 university
                   {
                      for(a in list)
                      {
                        institute()
                        {
                          insname(a.INSTITUTE)
                          usage(a.LMS_USAGE)
                       }
                     }
                   }
              }
       }


     def yearwiseUsage={
    String sel_year=params.year
     def sql=new Sql(dataSource);
            def query="select count(a.action) LMS_USAGE,b.inst_name as INSTITUTE from master a INNER JOIN institute b where year(a.date)='"+sel_year+"' and a.inst_id=b.id group by a.inst_id order by b.inst_name asc"
            def list=sql.rows(query)
              render(contentType:"text/xml")
              {
                 university
                   {
                      for(a in list)
                      {
                        institute()
                        {
                          insname(a.INSTITUTE)
                          usage(a.LMS_USAGE)
                       }
                     }
                   }
              }
        }


    def stackchart={
        def course_list=[]
       // String sel_courses=params.courses
       // course_list=sel_courses.split(",");
       course_list.add("Chemistry");
       course_list.add("English");
       course_list.add("Maths");
       println(course_list)


        println(course_list);
       
        def sql=new Sql(dataSource);
        def list1=sql.rows("select distinct(inst_code) from master order by inst_code asc")
        String xml_cont='<?xml version="1.0" encoding="utf-8"?><University>';
        String x='';
        for(a in list1)
        {
            xml_cont+='<Institutes>'
            xml_cont+='<Institute>'+a.inst_code+'</Institute>'
            for(course in course_list)
              {

                    xml_cont+='<'+course+'>'
                //    println("select count(action) as totcnt from master where coursename ='"+course+"' and inst_code='"+a.inst_code+"'")
                    sql.rows("select count(action) as totcnt from master where coursename ='"+course+"' and inst_code='"+a.inst_code+"'").each { row ->
                    xml_cont+=row.totcnt
                    xml_cont+='</'+course+'>'
                }
              }
             xml_cont+='</Institutes>'
        }
        xml_cont+='</University>'
        render xml_cont
    }

 /*----------------------  DASH BOARD FUNCTIONS FOR UNIVERSITY USER ENDS HERE  -------------------------------------------- */




 /*----------------------  DASH BOARD FUNCTIONS FOR INSTITUTE USER ENDS HERE  -------------------------------------------- */
  def institutedashboard= { }
  
  def staffstudInstUsage={	
    
  def from_val=params.from;
  def to_val=params.to;	
  GrailsHttpSession gh=getSession();
  def userid=gh.getValue("currUserId");
  def sql=new Sql(dataSource);
  def insval = sql.firstRow("select id as INSTITUTE_ID from institute where user_id='"+userid+"'");
  def instid=insval.INSTITUTE_ID;  
		
  def list=sql.rows("select distinct(LOWER(user_type)) as usertype from master  where ( LOWER(user_type) = 'teacher' OR LOWER(user_type) = 'student' ) order by user_type asc")
  
        String xml_cont='<university>';   
        
            for(a in list)
			 {
                    xml_cont+='<institute>';
				    xml_cont+='<user>'+a.usertype+'</user>';
                    sql.rows("select count(action) as totcnt from master where  LOWER(user_type) ='"+a.usertype+"' and inst_id='"+instid+"' and date_format(date,'%Y')>='"+from_val+"' and  date_format(date,'%Y')<='"+to_val+"'").each { row ->
                    xml_cont+='<usage>'+row.totcnt+'</usage>';                   
                   }
				    xml_cont+='</institute>'; 
              }                  
        xml_cont+='</university>';
        render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8");
		
    }
	
	
	
	def coursewiseInstUsage={	
    
  def from_val=params.from;
  def to_val=params.to;	
  GrailsHttpSession gh=getSession();
  def userid=gh.getValue("currUserId");
  def sql=new Sql(dataSource);
  def insval = sql.firstRow("select id as INSTITUTE_ID from institute where user_id='"+userid+"'");
  def instid=insval.INSTITUTE_ID;  
  def list=sql.rows("select distinct(a.course_id) as COURSE_ID,b.course_name as COURSE from master a INNER JOIN course_master b where a.inst_id='"+instid+"' and a.course_id=b.id");		
 String xml_cont='<university>';           
            for(a in list)
			 {
                    xml_cont+='<institute>';
					xml_cont+='<courseid>'+a.COURSE_ID+'</courseid>';
				    xml_cont+='<course>'+a.COURSE+'</course>';
                    sql.rows("select count(action) as totcnt from master where inst_id='"+instid+"' and course_id='"+a.COURSE_ID+"' and date_format(date,'%Y')>='"+from_val+"' and  date_format(date,'%Y')<='"+to_val+"'").each { row ->
                    xml_cont+='<usage>'+row.totcnt+'</usage>';                   
                   }
				    xml_cont+='</institute>'; 
              }                  
        xml_cont+='</university>';
        render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8");
		
    }
	
	
	 def modulewiseInstUsage={	  
	
	    def from_val=params.from;
        def to_val=params.to;	
        GrailsHttpSession gh=getSession();
        def userid=gh.getValue("currUserId");
        def course_id=params.course_id;	
		
		println("FROM "+from_val);
		println("TO "+to_val);
		println("USER ID "+userid);
		println("COURSE ID "+course_id);
		/*
		 def from_val=2008;
        def to_val=2012;	
       
        def userid=3;
        def course_id=4;	
		*/
		
		
		
		def sql=new Sql(dataSource);	
		  def insval = sql.firstRow("select id as INSTITUTE_ID from institute where user_id='"+userid+"'");
         def instid=insval.INSTITUTE_ID;  	
        def list=sql.rows("select distinct(module_name) as MODULE from master where course_id='"+course_id+"' and inst_id='"+instid+"' order by module_name asc")
        String xml_cont='<courses>';
            for(mdl in list)
              {
                   xml_cont+='<modules>';
                   xml_cont+='<modulename>'+mdl.MODULE+'</modulename>';
                    xml_cont+='<usage>';
                    sql.rows("select count(action) as totcnt  from master where course_id='"+course_id+"' and module_name='"+mdl.MODULE+"' and inst_id='"+instid+"' and date_format(date,'%Y')>='"+from_val+"' and  date_format(date,'%Y')<='"+to_val+"' group by module_name order by module_name").each
                    { row -> xml_cont+=row.totcnt  }
                     xml_cont+='</usage>';
                  xml_cont+='</modules>';
              }
             xml_cont+='</courses>';
       render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
       }

	
	
  /*----------------------  DASH BOARD FUNCTIONS FOR INSTITUTE USER ENDS HERE  -------------------------------------------- */
  
  
  
  
  
  
 /*---------------------------  DASH BOARD FUNCTIONS FOR STAFF USER STARTS HERE  -------------------------------------------- */
     def staffdashboard= { }
    
     
     def course_usage={       
	    def from_val=params.from;
	    def to_val=params.to;	
        GrailsHttpSession gh=getSession()
    	def staff_id=gh.getValue("currUsername");       
        def sql=new Sql(dataSource);       
        def list2=sql.rows("select distinct(a.course_id) as COURSE_ID ,b.course_name as COURSE from master a INNER JOIN course_master b where LOWER(a.user_type)='teacher' and a.user_name='"+staff_id+"'  and a.course_id=b.id order by b.course_name asc")
        String xml_cont='<institute>';
            for(b in list2)
              {  
                   xml_cont+='<courses>'
                   xml_cont+='<coursename>'+b.COURSE+'</coursename>'
				   xml_cont+='<courseid>'+b.COURSE_ID+'</courseid>'
                   xml_cont+='<usage>'                 
                    sql.rows("select count(action) as totcnt from master where LOWER(user_type)='teacher' and user_name='"+staff_id+"' and course_id='"+b.COURSE_ID+"' and date_format(date,'%Y')>='"+from_val+"' and  date_format(date,'%Y')<='"+to_val+"' group by course_id").each
                    { row -> xml_cont+=row.totcnt  }
                     xml_cont+='</usage>'
                   xml_cont+='</courses>'
              }
             xml_cont+='</institute>' 
        render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8");
     }


    def module_usage={  
	    def from_val=params.from;
	    def to_val=params.to;	
        def course_id=params.course_id
        def sql=new Sql(dataSource);
        def list=sql.rows("select distinct(module_name) as MODULE from master where course_id='"+course_id+"' order by module asc")
        String xml_cont='<?xml version="1.0" encoding="utf-8"?><courses>';
            for(mdl in list)
              {
                   xml_cont+='<modules>'
                   xml_cont+='<modulename>'+mdl.MODULE+'</modulename>'
                    xml_cont+='<usage>'
                    sql.rows("select count(action) as totcnt   from master where course_id='"+course_id+"' and module_name='"+mdl.MODULE+"' and date_format(date,'%Y')>='"+from_val+"' and  date_format(date,'%Y')<='"+to_val+"' group by module_name order by module_name").each
                    { row -> xml_cont+=row.totcnt  }
                     xml_cont+='</usage>'
                   xml_cont+='</modules>'
              }
             xml_cont+='</courses>'
        render xml_cont
     }
	 

  def staffstud_usage = {
        def from_val=params.from;
	    def to_val=params.to;	
        def course_id=params.course_id;		
        def sql=new Sql(dataSource);        
		def crs = sql.firstRow("select course_name as course from course_master where id='"+course_id+"'")
        def list2=sql.rows("select distinct(LOWER(user_type)) as usertype from master   where ( LOWER(user_type) = 'teacher' OR LOWER(user_type) = 'student' ) order by user_type asc")
        String xml_cont='<institute>';
            xml_cont+='<courses>';
            xml_cont+='<course>'+crs.course+'</course>';
			xml_cont+='<dialect>Faculty</dialect>';
            for(b in list2)
              {
                    xml_cont+='<'+b.usertype+'>'
                    sql.rows("select count(action) as totcnt from master where course_id='"+course_id+"'and user_type='"+b.usertype+"' and date_format(date,'%Y')>='"+from_val+"' and  date_format(date,'%Y')<='"+to_val+"' group by user_type").each { row ->
                    xml_cont+=row.totcnt;                   
                }
				   xml_cont+='</'+b.usertype+'>'
              }
			  
             xml_cont+='</courses></institute>'
        //render xml_cont
        render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
  }


    def staffstudmod_usage = {
       def course_id=params.course_id;		  
	   def sel_module=params.module;
	   def from_val=params.from;
	   def to_val=params.to;		  
       def sql=new Sql(dataSource);    
        def crs = sql.firstRow("select course_name as course from course_master where id='"+course_id+"'")
        def list2=sql.rows("select distinct(LOWER(user_type)) as usertype from master  where  ( LOWER(user_type) = 'teacher' OR LOWER(user_type) = 'student' ) order by user_type asc")
        String xml_cont='<institute>';
            xml_cont+='<courses>'
            xml_cont+='<course>'+crs.course+'</course>'
            for(b in list2)
              {
                    xml_cont+='<'+b.usertype+'>';                   
                    sql.rows("select count(action) as totcnt from master where course_id='"+course_id+"' and module_name='"+sel_module+"' and LOWER(user_type)='"+b.usertype+"' and date_format(date,'%Y')>='"+from_val+"' and  date_format(date,'%Y')<='"+to_val+"' group by user_type").each { row ->
                    xml_cont+=row.totcnt                   
                }
				 xml_cont+='</'+b.usertype+'>';
              }
             xml_cont+='</courses></institute>';
       println(xml_cont);
       render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
  }



 def action_count={       
        def course_id=params.course_id;		  
	    def from_val=params.from;
	    def to_val=params.to;
        GrailsHttpSession gh=getSession()
    	def staff_id=gh.getValue("currUsername");       
        def sql=new Sql(dataSource);       
        def list2=sql.rows("select distinct(action) as ACTION from master where user_name='"+staff_id+"'")
        String xml_cont='<staff>';
            for(b in list2)
              {  
                   xml_cont+='<actions>'
                   xml_cont+='<action>'+b.ACTION+'</action>'
                   xml_cont+='<count>'   
                    sql.rows("select count(action) as totcnt from master where LOWER(user_name)='"+staff_id+"' and action='"+b.ACTION+"' and course_id='"+course_id+"' and date_format(date,'%Y')>='"+from_val+"' and  date_format(date,'%Y')<='"+to_val+"'").each
                    { row -> xml_cont+=row.totcnt  }
                     xml_cont+='</count>'
                   xml_cont+='</actions>'
              }
             xml_cont+='</staff>' 
        render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8");
     }
	 
	 
	 
     /* ############################## For Second Tab in dashboard ############# */
         def staff_courses={
        GrailsHttpSession gh=getSession()
        def staff_id=gh.getValue("currUsername");
        def sql=new Sql(dataSource);
        def query="select distinct(a.course_id) as COURSE_ID,b.course_name as COURSE from master a INNER JOIN course_master b where LOWER(a.user_type) = 'teacher' and a.user_name='"+staff_id+"' and a.course_id=b.id order by b.course_name asc"
        def list=sql.rows(query)
        render(contentType:"text/xml")
              {
                 staff
                   {
                      for(a in list)
                      {
                        course()
                        {
                          label(a.COURSE)
                          course_id(a.COURSE_ID)
                       }
                     }
                   }
              }
       }

      def staff_components={
	    def course_id=params.course_id;	
		println(course_id);
        def sql=new Sql(dataSource);
        def query="select distinct(module_name) as COMPONENT from component where course_id='"+course_id+"'";
        def list=sql.rows(query)
        render(contentType:"text/xml")
              {
                 staff
                   {
                      for(a in list)
                      {
                        component()
                        {
                          label(a.COMPONENT)
                          data(a.COMPONENT)
                       }
                     }
                   }
              }
       }


      def  component_usage={
        def course_id=params.course_id;	
        def sel_module=params.module
        def sql=new Sql(dataSource);       
        def list2=sql.rows("select distinct(user_name)as USERNAME  from component  where course_id='"+course_id+"' and module_name='"+sel_module+"' order by user_name asc")
        String  xml_cont='<components>'
            for(b in list2)
              {
                    xml_cont+='<component>'
                    xml_cont+='<user>'+b.USERNAME+'</user>'
                    xml_cont+='<avg_grade>'                 
                    sql.rows("select avg(grade) as AVERAGE from component where course_id='"+course_id+"' and module_name='"+sel_module+"' and user_name='"+b.USERNAME+"' group by userid").each { row ->
                    xml_cont+=row.AVERAGE
                    xml_cont+='</avg_grade></component>'
                }
              }
             xml_cont+='</components>'
       // render xml_cont
       render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
      }



    def drilldown={
        String sel_course=params.course
        String sel_module=params.module
        String sel_user=params.user
        def sql=new Sql(dataSource);      
        def query="select date as DATE,grade as GRADE from component where course_name='"+sel_course+"' and module_name='"+sel_module+"' and user_name='"+sel_user+"' order by date asc"
        def list=sql.rows(query)
        render(contentType:"text/xml")
              {
                 components
                   {
                      for(a in list)
                      {
                        component()
                        {
                          date(a.DATE)
                          grade(a.GRADE)
                       }
                     }
                   }
              }
       }


      def visitdetails={
         def sql=new Sql(dataSource);
         def query="SELECT course_name AS COURSE,count(action)AS TOTAL_VISITS FROM master where inst_code='Amritapuri' GROUP BY coursename"
         def list=sql.rows(query)
         String xml_cont='<institutes>';
            for(a in list)
              {
                   xml_cont+='<courses>';
                   xml_cont+='<coursename>'+a.COURSE+'</coursename>';
                   xml_cont+='<visits>'+a.TOTAL_VISITS+'</visits>';
                   xml_cont+='</courses>';
              }
             xml_cont+='</institutes>';
        render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
       }
  /* ############################## Code For Second Tab in dashboard ends here ############# */


   def staff_synonym={
        String xml_cont='<institute><staff><synonym>faculty</synonym></staff></institute>';                   
        render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
       }

    def filedownload= {        
          response.setContentType("image/png");
          response.setHeader("Content-disposition", "attachment;fileName=chart.png")
          response.outputStream << request.inputStream
    }

 /*---------------------------  DASH BOARD FUNCTIONS FOR STAFF USER ENDS HERE  -------------------------------------------- */



 /*---------------------------  DASH BOARD FUNCTIONS FOR STUDENT USER STARTS HERE  -------------------------------------------- */


       def studentdashboard= { }


        def stud_courses={
        GrailsHttpSession gh=getSession()
        def stud_id=gh.getValue("currUsername");      
        def sql=new Sql(dataSource);
        def query="select distinct(course_name) as Course from master where course_name<>'' and  user_name='"+stud_id+"' order by course_name asc"
        def list=sql.rows(query)
          render(contentType:"text/xml")
          {
            institute
               {
                  for(a in list)
                  {
                    course()
                    {
                      label(a.Course)
                      data(a.Course)
                   }
                 }
               }
          }
       }


     def stud_courseusage={
	 
	    def from_val=params.from;
        def to_val=params.to;	
        GrailsHttpSession gh=getSession()
        def stud_id=gh.getValue("currUsername");
        def sql=new Sql(dataSource);
		
        def query="select count(a.action) as LMS_USAGE,a.course_id as COURSE_ID,b.course_name as COURSE from master a INNER JOIN course_master b where a.user_name='"+stud_id+"' and a.course_id=b.id and date_format(a.date,'%Y')>='"+from_val+"' and  date_format(a.date,'%Y')<='"+to_val+"' group by b.course_name order by b.course_name asc"
		
		println(query);
		
        def list=sql.rows(query);
        def  xml_cont='<student>';
              for(a in list)
               {
                    xml_cont+='<courses>';
                    xml_cont+='<coursename>'+a.COURSE+'</coursename>';
				    xml_cont+='<courseid>'+a.COURSE_ID+'</courseid>';
                    xml_cont+='<usage>'+a.LMS_USAGE+'</usage>';
                    xml_cont+='</courses>';
                }
              xml_cont+='</student>';
       render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
      }



      def stud_moduleusage={	  
	   def from_val=params.from;
       def to_val=params.to;	
        GrailsHttpSession gh=getSession()
        def stud_id=gh.getValue("currUsername");
        def course_id=params.course_id;	 
        def sql=new Sql(dataSource);
        def list=sql.rows("select distinct(module_name) as MODULE from master where course_id='"+course_id+"' and user_name='"+stud_id+"' order by module_name asc")
        String xml_cont='<courses>';
            for(mdl in list)
              {
                   xml_cont+='<modules>';
                   xml_cont+='<modulename>'+mdl.MODULE+'</modulename>';
                    xml_cont+='<usage>';
                    sql.rows("select count(action) as totcnt  from master where course_id='"+course_id+"' and module_name='"+mdl.MODULE+"' and user_name='"+stud_id+"' and date_format(date,'%Y')>='"+from_val+"' and  date_format(date,'%Y')<='"+to_val+"' group by module_name order by module_name").each
                    { row -> xml_cont+=row.totcnt  }
                     xml_cont+='</usage>';
                  xml_cont+='</modules>';
              }
             xml_cont+='</courses>';
       render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
       }


     def stud_componentusage={	
        GrailsHttpSession gh=getSession()
        def stud_id=gh.getValue("currUsername");
        def course_id=params.course_id;	 
        def sql=new Sql(dataSource);
        def list=sql.rows("SELECT distinct(module_name) as MODULE from component where user_name='"+stud_id+"' and course_id='"+course_id+"'")
        String xml_cont='<components>';
            for(mdl in list)
              {
                   xml_cont+='<component>';
                   xml_cont+='<cmpname>'+mdl.MODULE+'</cmpname>';
                    xml_cont+='<grade>';
                    sql.rows("SELECT avg(grade) as GRADE from component where user_name='"+stud_id+"' and course_id='"+course_id+"' and module_name='"+mdl.MODULE+"'").each
                    { row -> xml_cont+=row.GRADE  }
                     xml_cont+='</grade>';
                  xml_cont+='</component>';
              }
             xml_cont+='</components>';
       render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
      }



    def  courseusage_grid={
        GrailsHttpSession gh=getSession()
        def stud_id=gh.getValue("currUsername");
        def sql=new Sql(dataSource);
        def query="SELECT a.course_id as COURSE_ID,b.course_name AS COURSE,a.module_name AS MODULE,count(a.action) AS USAGE_COUNT FROM master a INNER JOIN course_master b where  a.user_name='"+stud_id+"' and a.course_id=b.id GROUP BY b.course_name,a.module_name"
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

 /*---------------------------  DASH BOARD FUNCTIONS FOR STUDENT USER ENDS HERE  -------------------------------------------- */
 
 
 
  def  sessionInfo={
  /*
  def xml_cont="""<session>
  <data>  
    <user>User1</user>
    <usage>50, 60, 100, 150 , 160, 200, 300, 400</usage>
  </data>
  <data>  
    <user>User2</user>
    <usage>70, 80, 120, 170 , 350, 450</usage>
  </data>
  <data>   
    <user>User3</user>
    <usage>10, 100, 200, 250 , 450, 460, 480, 500</usage>
  </data>
  </session>""";
  render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8");
  
  */
    def sql=new Sql(dataSource);
     def arr=[]
   
	  def usrlist=sql.rows("select distinct(user_name) as USER from mdl_log order by user_name asc limit 0,1");
	  def xml_cont='';
	  xml_cont+='<session>';
	   for(a in usrlist)
              {                
				   xml_cont+='<data>';
				   xml_cont+='<user>'+a.USER+'</user>';
				 def query="select time,action from mdl_log where user_name='"+a.USER+"' and course=1 and ( action='login' or action='logout' ) order by time asc";
				  
				     def list=sql.rows(query) 
					 def str='';
					 def sep='';   	
						for(b in list)
						  {
							  str+= sep + (b.time);				                
							  sep = ',';
						  }
				  xml_cont+='<usage>'+str+'</usage>';		
				  xml_cont+='</data>';				 
              }
	      xml_cont+='</session>';
		render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8");		
  }
 
 
 
 
    def showSessionInfo={}
	
	
	 def studList={
        def sql=new Sql(dataSource);
        def query="SELECT distinct user_name AS USERNAME FROM master where  LOWER(user_type)='student'  ORDER BY user_name asc";
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
 
 def sessionVisualize={
        def sel_date=params.seldate;
		def sel_user=params.seluser;
		def sql=new Sql(dataSource);
		def login_arr=[];
		def logout_arr=[];		
		
		def login_list=sql.rows("select id,date ,action from master where user_name='"+sel_user+"' and ( action='login' )  and date_format(date,'%Y-%m-%d')='"+sel_date+"' order by date,id");		
		for(a in login_list)
		{  
		    login_arr.add(a.date);
		}  
		
		def logout_list=sql.rows("select id,date ,action from master where user_name='"+sel_user+"' and ( action='logout' )  and date_format(date,'%Y-%m-%d')='"+sel_date+"' order by date,id");		
		for(b in logout_list)
		{  
		   logout_arr.add(b.date);
		} 
	 

	  def len=login_arr.size()-1;
	  def fromtime='';
	  def totime='';
	  
	  def xml_cont='';
	  xml_cont+='<institute>';
	 for ( i in 0..len ) {  
	 	 
			 xml_cont+='<student>';  
			   
			 fromtime=login_arr[i];
			 totime=logout_arr[i];		 
				 
			 def loginval = sql.firstRow("SELECT DATE_FORMAT('"+fromtime+"','%h.%i %p') as LOGIN_TIME");      
			 xml_cont+='<login>'+loginval.LOGIN_TIME+'</login>';  		 
			 
			 def logout = sql.firstRow("SELECT DATE_FORMAT('"+totime+"','%h.%i %p') as LOGOUT_TIME");    
			 xml_cont+='<logout>'+logout.LOGOUT_TIME+'</logout>'; 
			 
			 def timespend = sql.firstRow("SELECT TIME_FORMAT(TIMEDIFF('"+totime+"','"+fromtime+"'),'%H hrs %i min') as TMEDIFF");
			 xml_cont+='<spend>'+timespend.TMEDIFF+'</spend>'; 
			 
			 def timediff = sql.firstRow("SELECT TIME_FORMAT(TIMEDIFF('"+totime+"','"+fromtime+"'),'%H.%i') as TMEDIFF");
			 xml_cont+='<timespan>'+timediff.TMEDIFF+'</timespan>'; 
					
			 fromtime=''; totime='';	
				 
			 xml_cont+='</student>';  
		 
       }//end of for loop
	   
	 xml_cont+='</institute>';	 
	 render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8");	 
 }
 
 
 
 
 /*--------------------------------- AUTHENTICATION FUNCTION STARTS HERE---------------------------------------------------*/
 
 private void authenticate()
 {
   if (!authenticateService.isLoggedIn()) { redirect action: 'index', controller:'login' }
 }
/*--------------------------------- AUTHENTICATION FUNCTION ENDS HERE---------------------------------------------------*/

      def  test={     
	             
	  
	           //Running Second ktr file starts here
						def filename2 = "log_transform.ktr"				
						try {	
						StepLoader.init();
						EnvUtil.environmentInit();								
						TransMeta transMeta2 = new TransMeta(filename2);
						Trans trans2 = new Trans(transMeta2);
						
						trans2.setVariable("SOURCEDB_HOST", "localhost");				
						trans2.setVariable("SOURCEDB_DBNAME", "moodle");
						trans2.setVariable("SOURCEDB_PORT", "3306");
						trans2.setVariable("SOURCEDB_USERNAME", "root");
						trans2.setVariable("SOURCEDB_PASSWORD", "root");		
									
						trans2.setVariable("TARGETDB_HOST", "localhost");	
						trans2.setVariable("TARGETDB_DBNAME", "test");
						trans2.setVariable("TARGETDB_PORT", "3306");
						trans2.setVariable("TARGETDB_USERNAME", "root");
						trans2.setVariable("TARGETDB_PASSWORD", "root");	
						
						//trans2.setVariable("INST_CODE", "1");			
						//trans2.setVariable("LMS_CODE", "2");			
						//var encr = org.pentaho.di.core.encryption.Encr.encryptPassword(text);
										
						trans2.execute(null); // You can pass arguments instead of null.
						trans2.waitUntilFinished();
						trans2.endProcessing("end");
						if ( trans2.getErrors() > 0 )
						{
						throw new RuntimeException( "There were errors during transformation execution." );
						}
						}
						catch ( KettleException e ) {
						// TODO Put your exception-handling code here.
						println(e);
						}
				//Running Second ktr file ends here				
      }
	  
	  
	   def loadsql={    
		         
				 /*
				    def sqlFilePath = 'test.sql'			
					def sql = Sql.newInstance(ConfigurationHolder.config.dataSource.url, ConfigurationHolder.config.dataSource.username, ConfigurationHolder.config.dataSource.password, ConfigurationHolder.config.dataSource.driverClassName)					
					String sqlString = new File(sqlFilePath).eachLine {
					sql.execute(it)
					}
					println("success");					
					
					*/
					String sqlFilePath = ApplicationHolder.application.parentContext.servletContext.getRealPath("/ktr/log_transform.ktr")
					println("File path -->"+sqlFilePath);
      }
 
} //end of main class
