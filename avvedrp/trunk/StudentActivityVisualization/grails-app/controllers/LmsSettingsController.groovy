

import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.httpclient.*;
import groovy.sql.Sql



//For Kettle Transformations
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



class LmsSettingsController {
       def dataSource
   
    def edit = {
                    GrailsHttpSession gh=getSession()
                    def sql=new Sql(dataSource);
                    def lmsList = []  ;                   
                    def lmsInstance;                    
					
		         	def userid=gh.getValue("currUserId");		
					def inst = sql.firstRow("select id as institute_id from institute where user_id='"+userid+"'")
					def inst_id=inst.institute_id;
                    sql.eachRow("select id as LMS_ID,lms_name as LMS_NAME from lms where inst_id='"+inst_id+"' order by id asc")
                    {
                        row ->
                        lmsInstance  = new LmsSettings()
                        lmsInstance.lmsId = row.LMS_ID                    
                        lmsInstance.lmsName = row.LMS_NAME                      
                        lmsList.add(lmsInstance)
                    }
                  return [lmsList:lmsList]			
             }



/*---------------------------------------  AJAX FUNCTIONS START HERE -------------------------------------*/
def ajaxGetLmsInfo={
     def sel_id=params.id;  
     def response='';
    
def sql=new Sql(dataSource);	
	 def lmslist=sql.rows("select id,lms_hostname as HOSTNAME,lms_port as PORTNO,lms_username as USERNAME,lms_password as PASSWORD,lms_dbname as DBNAME  from  lms_settings where lms_id='"+sel_id+"'");
     for(lmsval in lmslist)
        {
            response+=lmsval.HOSTNAME+'|^@^|'+lmsval.PORTNO+'|^@^|'+lmsval.USERNAME+'|^@^|'+lmsval.PASSWORD+'|^@^|'+lmsval.DBNAME;
        }
      render response;
}
/*---------------------------------------  AJAX FUNCTIONS ENDS HERE -------------------------------------*/

    def update = {    
	              def lmsid=params.lmsid;             
                  def host=params.host;
				  def port=params.port;
                  def uname=params.uname;
                  def paswd=params.paswd;
                  def dbname=params.dbname;
                  def count_val=0;				  
                  def sql=new Sql(dataSource);
                  def lmsval = sql.firstRow("select count(*) as COUNT,id,lms_id  from lms_settings where lms_id='"+lmsid+"' group by id");
                  if(lmsval)
	       		count_val=lmsval.COUNT;
                 if(count_val>0)
                  {                     
                      def settings = LmsSettings.get(lmsval.id)
                      bindData(settings, [
                                    lms_id: lmsid,
                                    lms_hostname: host,
				    lms_port: port,
                                    lms_username: uname,
                                    lms_password: paswd,
                                    lms_dbname: dbname])
                      if (settings.save())
                      {
                           settings.save(flush: true)
                      }
                  }
                  else if(count_val<1)
                  {
                      def settings = new LmsSettings()
                      bindData(settings, [
                                    lms_id: lmsid,
                                    lms_hostname: host,
									lms_port: port,
                                    lms_username: uname,
                                    lms_password: paswd,
                                    lms_dbname: dbname])
                      if (settings.save())
                      {
                          settings.save(flush: true)
                      }
                  }
				  	flash.message = 'LMS details updated Successfully!'
					redirect action: edit			
           }//end of update
            
			
	
	def transform={  		
	
							def source_host=""						
							def source_port=""
							def source_dbname=""
							def source_username=""
							def source_password=""
						
					
								
					
					//Running First ktr file starts here	
						//def filename1 = "mdl_course.ktr";	
						String filename1 = ApplicationHolder.application.parentContext.servletContext.getRealPath("/ktr/mdl_course.ktr")					 	
						try {		
						StepLoader.init();
						EnvUtil.environmentInit();						
						TransMeta transMeta1 = new TransMeta(filename1);
						Trans trans1 = new Trans(transMeta1);
						def lms_id1=params.lms_id;
						
						
						//Source db details
						def sql=new Sql(dataSource);
						def lmsset=sql.firstRow("select * from lms_settings where lms_id='"+lms_id1+"'");
						source_host=lmsset.lms_hostname;						
						source_port=lmsset.lms_port;
						source_dbname=lmsset.lms_dbname;
						source_username=lmsset.lms_username;
						source_password=lmsset.lms_password;
						
						
						//Destination db details 
						 def db_url=dataSource.url;
						 db_url=db_url.split('//');
						 def db_det=db_url[1].replace('/',':');		  
						 def split_det=db_det.split(":");
						 def dest_host=split_det[0];
						 def dest_port=split_det[1];
						 def dest_dbname=split_det[2];
						 def dest_username=dataSource.username;
						 def dest_password=dataSource.password;
						
						
						trans1.setVariable("SOURCEDB_HOST", source_host);				
						trans1.setVariable("SOURCEDB_DBNAME", source_dbname);
						trans1.setVariable("SOURCEDB_PORT", source_port);
						trans1.setVariable("SOURCEDB_USERNAME", source_username);
						trans1.setVariable("SOURCEDB_PASSWORD", source_password);	
									
						trans1.setVariable("TARGETDB_HOST", dest_host);	
						trans1.setVariable("TARGETDB_DBNAME",dest_dbname);
						trans1.setVariable("TARGETDB_PORT", dest_port);
						trans1.setVariable("TARGETDB_USERNAME", dest_username);
						trans1.setVariable("TARGETDB_PASSWORD", dest_password);	
										
						trans1.execute(null); // You can pass arguments instead of null.
						trans1.waitUntilFinished();
						trans1.endProcessing("end");
						if ( trans1.getErrors() > 0 )
						{
						throw new RuntimeException( "There were errors during transformation execution." );
						}
						}
						catch ( KettleException e ) {
						// TODO Put your exception-handling code here.
						println(e);
						}
				//Running first ktr file ends here
	  
	           //Running Second ktr file starts here
						//def filename2 = "log_transform.ktr"	
						String filename2 = ApplicationHolder.application.parentContext.servletContext.getRealPath("/ktr/log_transform.ktr")						
						try {	
						StepLoader.init();
						EnvUtil.environmentInit();						
						TransMeta transMeta2 = new TransMeta(filename2);
						Trans trans2 = new Trans(transMeta2);
						
						trans2.setVariable("SOURCEDB_HOST", source_host);				
						trans2.setVariable("SOURCEDB_DBNAME", source_dbname);
						trans2.setVariable("SOURCEDB_PORT", source_port);
						trans2.setVariable("SOURCEDB_USERNAME", source_username);
						trans2.setVariable("SOURCEDB_PASSWORD", source_password);		
									
						trans2.setVariable("TARGETDB_HOST", "localhost");	
						trans2.setVariable("TARGETDB_DBNAME", "studviz");
						trans2.setVariable("TARGETDB_PORT", "3306");
						trans2.setVariable("TARGETDB_USERNAME", "root");
						trans2.setVariable("TARGETDB_PASSWORD", "amma");	
						
						
					 //Getting LMS ID and INSTITUTE ID				
					    GrailsHttpSession gh=getSession()
						def sql=new Sql(dataSource);
						def lms_id=params.lms_id;	
						def userid=gh.getValue("currUserId");		
					    def inst = sql.firstRow("select id as institute_id from institute where user_id='"+userid+"'")
				    		def inst_id=inst.institute_id;		
						
						trans2.setVariable("LMS_ID", "\'"+lms_id+"\'");			
						trans2.setVariable("INST_ID", "\'"+inst_id+"\'");	
											
						
						//var encr = org.pentaho.di.core.encryption.Encr.encryptPassword(text);
										
						trans2.execute(null); // You can pass arguments instead of null.
						trans2.waitUntilFinished();
						trans2.endProcessing("end");
							if ( trans2.getErrors() > 0 )
							{
							  // throw new RuntimeException( "There were errors during transformation execution.</font>" );
								  flash.message = "<font color='#F60202'>Transformation Failed.There were errors during transformation execution.</font>"
								  //redirect action: edit
							}
							else
							{
							  Date d=trans2.getCurrentDate()
							  String s=d.toString();
							  println s
							  sql.execute("update last_update set date='"+s+"'");
							  flash.message = "<font color='#176F3D'>Data has been transferred Successfully!</font>";
							}
						}
						catch ( KettleException e ) {
						   // TODO Put your exception-handling code here.
						     println("sdfdsf"+e.getMessage());
						      flash.message = "<font color='#F60202'>Transformation Failed.There were errors during transformation execution.</font>";			           
							  
						}
				//Running Second ktr file ends here	
				
				
				  
					redirect action: edit
					
           }//End of Transformations
 		
			
                
}
