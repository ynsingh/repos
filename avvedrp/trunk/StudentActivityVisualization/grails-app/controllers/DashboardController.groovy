import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.apache.commons.httpclient.*;
import groovy.sql.Sql
import java.util.*;
import java.io.*;
import grails.converters.*

class DashboardController {
        def dataSource
     	def index = { redirect(action:list,params:params) }    	
    	static allowedMethods = [delete:'POST', save:'POST', update:'POST']
       


    //Newly Added
    /*-------------------------------------- DSAH BOARD FOR UNIVERSITY USER -------------------------------------------- */

    def admindashboard = {

         def instituteList = []
         session['insname']=''
         def sql=new Sql(dataSource);
         sql.eachRow("SELECT distinct(insti_code) AS institute FROM master  where insti_code<>'' order by course asc")
          {
                row ->
                instituteList.add(row.institute)
          }
          [instituteList:instituteList]
          
           
    }

   def institutes={
        def sql=new Sql(dataSource);
        def query="select distinct(insti_code) as Institute from master  where insti_code<>'' order by insti_code asc"
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
                      data(a.Institute)
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
        def query="select distinct(coursename) as Course from master where coursename<>'' order by coursename asc"
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

    def flex={
        def sql=new Sql(dataSource);
        def query="SELECT count(action) as LMS_USAGE,insti_code as INSTITUTE from master where insti_code<>'' group by insti_code"
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


    def flex2={
        def sql=new Sql(dataSource);
        def list1=sql.rows("select distinct(insti_code) from master where insti_code<>'' order by insti_code asc")
        def list2=sql.rows("select distinct(LOWER(usertype)) as usertype from master  where ( LOWER(usertype) = 'teacher' OR LOWER(usertype) = 'student' ) order by usertype asc")
        String xml_cont='<?xml version="1.0" encoding="utf-8"?><university>';
        String x='';
        for(a in list1)
        {
            xml_cont+='<institutes>'
            xml_cont+='<institute>'+a.insti_code+'</institute>'
            for(b in list2)
              {

                    xml_cont+='<'+b.usertype+'>'
                    sql.rows("select count(action) as totcnt from master where  LOWER(usertype) ='"+b.usertype+"' and insti_code='"+a.insti_code+"'").each { row ->
                    xml_cont+=row.totcnt
                    xml_cont+='</'+b.usertype+'>'
                }
              }
             xml_cont+='</institutes>'
        }
        xml_cont+='</university>'
        render xml_cont
    }


    def  single={
         def sql=new Sql(dataSource);
       String x=params.val
       //String x='Bangalore'
        def list2=sql.rows("select distinct(LOWER(usertype)) as usertype from master  where ( LOWER(usertype) = 'teacher' OR LOWER(usertype) = 'student' ) order by usertype asc")
        String xml_cont='<?xml version="1.0" encoding="utf-8"?><university>';

            xml_cont+='<institutes>'
            xml_cont+='<institute>'+x+'</institute>'
            for(b in list2)
              {
                    xml_cont+='<'+b.usertype+'>'
                    sql.rows("select count(action) as totcnt from master where LOWER(usertype) ='"+b.usertype+"' and insti_code='"+x+"'").each { row ->
                    xml_cont+=row.totcnt
                    xml_cont+='</'+b.usertype+'>'
                }
              }
             xml_cont+='</institutes>'
        xml_cont+='</university>'
        render xml_cont
    }



    def chart3={
    String sel_course=params.course
     def sql=new Sql(dataSource);
            def query="select count(action) as LMS_USAGE,insti_code as INSTITUTE,coursename from master where coursename='"+sel_course+"' group by insti_code order by insti_code asc"
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


     def chart4={
    String sel_year=params.year
     def sql=new Sql(dataSource);
            def query="select count(action) LMS_USAGE,insti_code as INSTITUTE from master where year(date)='"+sel_year+"' group by insti_code order by insti_code asc"
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

        println("called")
        def course_list=[]
       // String sel_courses=params.courses
       // course_list=sel_courses.split(",");
       course_list.add("Chemistry");
       course_list.add("English");
       course_list.add("Maths");
       println(course_list)


        println(course_list);
       
        def sql=new Sql(dataSource);
        def list1=sql.rows("select distinct(insti_code) from master order by insti_code asc")
        String xml_cont='<?xml version="1.0" encoding="utf-8"?><University>';
        String x='';
        for(a in list1)
        {
            xml_cont+='<Institutes>'
            xml_cont+='<Institute>'+a.insti_code+'</Institute>'
            for(course in course_list)
              {

                    xml_cont+='<'+course+'>'
                //    println("select count(action) as totcnt from master where coursename ='"+course+"' and insti_code='"+a.insti_code+"'")
                    sql.rows("select count(action) as totcnt from master where coursename ='"+course+"' and insti_code='"+a.insti_code+"'").each { row ->
                    xml_cont+=row.totcnt
                    xml_cont+='</'+course+'>'
                }
              }
             xml_cont+='</Institutes>'
        }
        xml_cont+='</University>'
        render xml_cont
    }

  /*-------------------------------------- DASH BOARD FOR UNIVERSITY USER ENDS HERE  -------------------------------------------- */





 /*-------------------------------------- DASH BOARD FOR STFAFF USER STARTS HERE  -------------------------------------------- */
     def staffdashboard= { }
    
     
     def course_usage={       
        GrailsHttpSession gh=getSession()
    	def staff_id=gh.getValue("currUsername");       
        def sql=new Sql(dataSource);       
        def list2=sql.rows("select distinct(coursename) as COURSE , username from master where LOWER(usertype)='teacher' and username='"+staff_id+"' order by coursename asc")
        String xml_cont='<?xml version="1.0" encoding="utf-8"?><institute>';
            for(b in list2)
              {  
                   xml_cont+='<courses>'
                   xml_cont+='<coursename>'+b.COURSE+'</coursename>'
                   xml_cont+='<usage>'                 
                    sql.rows("select count(action) as totcnt from master where LOWER(usertype)='teacher' and username='"+staff_id+"' and coursename='"+b.COURSE+"' group by coursename").each
                    { row -> xml_cont+=row.totcnt  }
                     xml_cont+='</usage>'
                   xml_cont+='</courses>'
              }
             xml_cont+='</institute>' 
        render xml_cont
     }


    def module_usage={

      //  println("inside staff dashboard")
        String sel_course=params.course
        def sql=new Sql(dataSource);
        def list=sql.rows("select distinct(module) as MODULE from master where coursename='"+sel_course+"' order by module asc")
        String xml_cont='<?xml version="1.0" encoding="utf-8"?><courses>';
            for(mdl in list)
              {
                   xml_cont+='<modules>'
                   xml_cont+='<modulename>'+mdl.MODULE+'</modulename>'
                    xml_cont+='<usage>'
                    sql.rows("select count(action) as totcnt   from master where coursename='"+sel_course+"' and module='"+mdl.MODULE+"' group by module order by module").each
                    { row -> xml_cont+=row.totcnt  }
                     xml_cont+='</usage>'
                   xml_cont+='</modules>'
              }
             xml_cont+='</courses>'
        render xml_cont
     }

  def staffstud_usage = {

        def sql=new Sql(dataSource);
        String x=params.course;
      // String x='Biology-101';
        def list2=sql.rows("select distinct(LOWER(usertype)) as usertype from master   where ( LOWER(usertype) = 'teacher' OR LOWER(usertype) = 'student' ) order by usertype asc")
        String xml_cont='<institute>';
            xml_cont+='<courses>'
            xml_cont+='<module>'+x+'</module>'
            for(b in list2)
              {
                    xml_cont+='<'+b.usertype+'>'
                    sql.rows("select count(action) as totcnt from master where coursename='"+x+"'and usertype='"+b.usertype+"' group by usertype").each { row ->
                    xml_cont+=row.totcnt
                    xml_cont+='</'+b.usertype+'>'
                }
              }
             xml_cont+='</courses></institute>'
        //render xml_cont
        render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
  }


    def staffstudmod_usage = {
       String sel_course=params.course;
       String sel_module=params.module;
       def sql=new Sql(dataSource);    

        def list2=sql.rows("select distinct(LOWER(usertype)) as usertype from master  where  ( LOWER(usertype) = 'teacher' OR LOWER(usertype) = 'student' ) order by usertype asc")
        String xml_cont='<institute>';
            xml_cont+='<courses>'
            xml_cont+='<module>'+sel_course+'</module>'
            for(b in list2)
              {
                    xml_cont+='<'+b.usertype+'>'                   
                    sql.rows("select count(action) as totcnt from master where coursename='"+sel_course+"' and module='"+sel_module+"' and LOWER(usertype)='"+b.usertype+"' group by usertype").each { row ->
                    xml_cont+=row.totcnt
                    xml_cont+='</'+b.usertype+'>'
                }
              }
             xml_cont+='</courses></institute>'
       // render xml_cont
       render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
  }


     /* ############################## For Second Tab in dashboard ############# */
         def staff_courses={
        GrailsHttpSession gh=getSession()
        def staff_id=gh.getValue("currUsername");
        def sql=new Sql(dataSource);
        def query="select distinct(coursename) as COURSE from master where LOWER(usertype) = 'teacher' and username='"+staff_id+"' order by coursename asc"
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
                          data(a.COURSE)
                       }
                     }
                   }
              }
       }

      def staff_components={
         String sel_course=params.course
        def sql=new Sql(dataSource);
        def query="select distinct(module) as COMPONENT from component where coursename='"+sel_course+"'"
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
        String sel_course=params.course
        String sel_module=params.module

       // String sel_course='Biology-101'
       //String sel_module='quiz'
        def sql=new Sql(dataSource);       
        def list2=sql.rows("select distinct(username)as USERNAME  from component  where coursename='"+sel_course+"' and module='"+sel_module+"' order by username asc")
        String  xml_cont='<components>'
            for(b in list2)
              {
                    xml_cont+='<component>'
                    xml_cont+='<user>'+b.USERNAME+'</user>'
                    xml_cont+='<avg_grade>'                 
                    sql.rows("select avg(grade) as AVERAGE from component where coursename='"+sel_course+"' and module='"+sel_module+"' and username='"+b.USERNAME+"' group by userid").each { row ->
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
        def query="select date as DATE,grade as GRADE from component where coursename='"+sel_course+"' and module='"+sel_module+"' and username='"+sel_user+"' order by date asc"
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
         def query="SELECT coursename AS COURSE,count(action)AS TOTAL_VISITS FROM master where insti_code='Amritapuri' GROUP BY coursename"
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



    def filedownload= {        
          response.setContentType("image/png");
          response.setHeader("Content-disposition", "attachment;fileName=chart.png")
          response.outputStream << request.inputStream
    }
 /*-------------------------------------- DASH BOARD FOR STAFF USER ENDS HERE  -------------------------------------------- */





  /*-------------------------------------- DASH BOARD FOR STUDENT USER STARTS HERE  -------------------------------------------- */


       def studentdashboard= { }


        def stud_courses={
        GrailsHttpSession gh=getSession()
        def stud_id=gh.getValue("currUsername");      
        def sql=new Sql(dataSource);
        def query="select distinct(coursename) as Course from master where coursename<>'' and  username='"+stud_id+"' order by coursename asc"
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
        GrailsHttpSession gh=getSession()
        def stud_id=gh.getValue("currUsername");
        def sql=new Sql(dataSource);
        def query="select count(action) as LMS_USAGE,coursename from master where username='"+stud_id+"' group by coursename order by coursename asc"
        def list=sql.rows(query);
        def  xml_cont='<student>';
              for(a in list)
               {
                    xml_cont+='<courses>';
                    xml_cont+='<coursename>'+a.coursename+'</coursename>';
                    xml_cont+='<usage>'+a.LMS_USAGE+'</usage>';
                    xml_cont+='</courses>';
                }
              xml_cont+='</student>';
       render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
     }

      def stud_moduleusage={
        GrailsHttpSession gh=getSession()
        def stud_id=gh.getValue("currUsername");
        def sel_course=params.course     
        def sql=new Sql(dataSource);
        def list=sql.rows("select distinct(module) as MODULE from master where coursename='"+sel_course+"' and username='"+stud_id+"' order by module asc")
        String xml_cont='<courses>';
            for(mdl in list)
              {
                   xml_cont+='<modules>';
                   xml_cont+='<modulename>'+mdl.MODULE+'</modulename>';
                    xml_cont+='<usage>';
                    sql.rows("select count(action) as totcnt  from master where coursename='"+sel_course+"' and module='"+mdl.MODULE+"' and username='"+stud_id+"' group by module order by module").each
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
        def sel_course=params.course
        def sql=new Sql(dataSource);
        def list=sql.rows("SELECT distinct(module) as MODULE from component where username='"+stud_id+"' and coursename='"+sel_course+"'")
        String xml_cont='<components>';
            for(mdl in list)
              {
                   xml_cont+='<component>';
                   xml_cont+='<cmpname>'+mdl.MODULE+'</cmpname>';
                    xml_cont+='<grade>';
                    sql.rows("SELECT avg(grade) as GRADE from component where username='"+stud_id+"' and coursename='"+sel_course+"' and module='"+mdl.MODULE+"'").each
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
        def query="SELECT coursename AS COURSE,module AS MODULE,count(action) AS USAGE_COUNT FROM master where  username='"+stud_id+"' GROUP BY coursename,module"
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

 /*-------------------------------------- DASH BOARD FOR STUDENT USER ENDS HERE  -------------------------------------------- */




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
