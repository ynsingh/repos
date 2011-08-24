import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Iterator;
import java.text.*;
import java.util.*;
import java.sql.*;
import java.io.*
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import javax.servlet.ServletOutputStream;
import grails.util.GrailsUtil

import org.codehaus.groovy.grails.commons.ApplicationHolder as AH

class UtilizationController extends GmsController {
    
    def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    def allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
		GrailsHttpSession gh = getSession()
		println "partyid"+gh.getValue("Party")
		def utilizationInstanceListbyparty
		utilizationInstanceListbyparty = Utilization.findAll("from Utilization U where U.projects.id in (select GA.projects.id from GrantAllocation GA where GA.granter='"+gh.getValue("Party")+"')")
		println"...params....."+params	
        [ utilizationInstanceList: utilizationInstanceListbyparty ]
    }

    def show = {
        def utilizationInstance = Utilization.get( params.id )

        if(!utilizationInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else { return [ utilizationInstance : utilizationInstance ] }
    }

    def delete = {
    		println";;;;;;params;;;;"+params
        def utilizationInstance = Utilization.get( params.id )
        if(utilizationInstance) {
            utilizationInstance.delete()
            flash.message = "${message(code: 'default.deleted.label')}"
            redirect(action:list)
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
    }

    def edit = {
        def utilizationInstance = Utilization.get( params.id )

        if(!utilizationInstance) {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:list)
        }
        else {
            return [ utilizationInstance : utilizationInstance ]
        }
    }

    def update = {
        def utilizationInstance = Utilization.get( params.id )
        if(utilizationInstance) {
            utilizationInstance.properties = params
            if(!utilizationInstance.hasErrors() && utilizationInstance.save()) {
                flash.message ="${message(code: 'default.updated.label')}"
                redirect(action:show,id:utilizationInstance.id)
            }
            else {
                render(view:'edit',model:[utilizationInstance:utilizationInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.notfond.label')}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def utilizationInstance = new Utilization()
        GrailsHttpSession gh = getSession()
        println "projectid=="+params.id
        def projectInstance = Projects.get(params.id)
        def utilizationInstanceCheck = Utilization.findAll("from Utilization U where U.projects.id="+projectInstance.id)
        utilizationInstance.properties = params
        def utilizationCertificateList
		utilizationCertificateList = Utilization.findAll("from Utilization U where U.projects.id ="+projectInstance.id+" and U.grantee='"+gh.getValue("Party")+"')")
        [projectInstance:projectInstance,utilizationInstance:utilizationInstance,utilizationCertificateList:utilizationCertificateList]
      }

    def save = {
		GrailsHttpSession gh=getSession()
		println "uti"+params
		def projectInstance = Projects.get(params.id)
        def utilizationInstance = new Utilization(params)
        utilizationInstance.projects= Projects.get(params.id)
        utilizationInstance.submittedDate=new Date()
		utilizationInstance.grantee=Party.get(gh.getValue("Party"))
		def utilizationInstanceList = Utilization.findAll("from Utilization U where U.projects="+projectInstance.id)
		
		   if(utilizationInstanceList)
		   {
			  
			   Date temp;
			   Date attr;
			   for(int i=0;i<utilizationInstanceList.size(); i++)
			   {
				   
				   temp = utilizationInstanceList[i].startDate
				   for (int j=1;j<utilizationInstanceList.size();j++ )
				   {
				   if(utilizationInstanceList[i].startDate > utilizationInstanceList[j].startDate)
				   {
					   temp = utilizationInstanceList[j].startDate
				   }
				   }
			   }
			   
			   for(int j=0;j<utilizationInstanceList.size(); j++)
			   {
				   
				   attr = utilizationInstanceList[j].endDate
				   for (int k=1;k<utilizationInstanceList.size();k++ )
				   {
				   if(utilizationInstanceList[j].endDate < utilizationInstanceList[k].endDate)
				   {
					   attr = utilizationInstanceList[k].endDate
				   }
				   }
			   }
				   
				   SimpleDateFormat sdfDestination = new SimpleDateFormat("dd/MM/yyyy");
				    
			          String strDate = sdfDestination.format(temp);
			         
			          String edDate = sdfDestination.format(attr);
			          
			          DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			          Date strtingDate = df.parse(strDate)
			         
			          Date endingDate = df.parse(edDate)
			         
			          Date startDate = df.parse(params.startDate_value)
			          
			          Date endDate = df.parse(params.endDate_value)
		        
			   if((( strtingDate <= startDate) && (endingDate >= endDate)) || (( strtingDate <= startDate) && (endingDate >= startDate))
					   || (( strtingDate <= endDate) && (endingDate >= endDate)))
			   {
				   
				
					  flash.message="${message(code:'default.UtilizationCertificateSubmission.label')}"
						  redirect(action:'create',id:params.id)
			   }
			   else
			   {
	
			if(!utilizationInstance.hasErrors() && utilizationInstance.save()) 
			{
				
				flash.message = "${message(code: 'default.Utilizationcertificatesubmitted.label')}"
				redirect(action:'create',id:params.id)
			}
			else {
				
            render(view:'create',model:[projectInstance:projectInstance])
			}
		}
		   }
		   else
		   {
			   if(!utilizationInstance.hasErrors() && utilizationInstance.save()) 
				{
					
					flash.message = "${message(code: 'default.Utilizationcertificatesubmitted.label')}"
					redirect(action:'create',id:params.id)
				}
				else {
					
	            render(view:'create',model:[projectInstance:projectInstance])
				}
		   }
		   
    }
    
    /* Get sql connection */
    private Connection getSqlConnection(){
        /*getting the datasource instance from the GmsController
         *as this class extends it*/
		 Connection con = dataSource.getConnection()
    	return con
    }
    
    def showReports=
	{
		File reportFile
		def webRootDir = servletContext.getRealPath("/")
		def projectInstance = Projects.get(params.id)
        def utilizationInstance = new Utilization(params)
        utilizationInstance.projects= Projects.get(params.id)
		reportFile = new File(webRootDir+ "/reports/StatementOFAccounts.jasper")
		Map parameters = new HashMap();
								
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		def ProjectID
		if(utilizationInstance)
		{
			parameters.put("reportDateTo",utilizationInstance.endDate)
			parameters.put("reportDate",utilizationInstance.startDate)
			
			
			ProjectID =utilizationInstance.projects.id
		}
		else
		{
			Date startDate  = df.parse(params.reportDate_value)
			Date endDate = df.parse(params.reportDateTo_value)
			
			parameters.put("reportDateTo",endDate)
			parameters.put("reportDate",startDate)
			ProjectID =params.projects.id
		}
			
		GrailsHttpSession gh=getSession()
		parameters.put("projectID",ProjectID.toString())
		parameters.put("Path",webRootDir+"/reports/")
		
		def sql = getSqlConnection()
		
		byte[] bytes = 
			JasperRunManager.runReportToPdf(
					reportFile.getPath(), 
				parameters, 
				sql
				);

		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		sql.close()
		ouputStream.flush();
		ouputStream.close();
				
	}
	def utilizationCertificate=
	{
		File reportFile
		def webRootDir = servletContext.getRealPath("/")
		
		def projectInstance = Projects.get(params.id)
        def utilizationInstance = new Utilization(params)
        utilizationInstance.projects= Projects.get(params.id)
		
		reportFile = new File(webRootDir+ "/reports/UtilizationCertificate.jasper")
		Map parameters = new HashMap();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		def ProjectID
		if(utilizationInstance)
		{
				parameters.put("reportDateTo",utilizationInstance.endDate)
			parameters.put("reportDate",utilizationInstance.startDate)
			ProjectID =utilizationInstance.projects.id
		}
		else
		{
			Date startDate  = df.parse(params.reportDate_value)
			Date endDate = df.parse(params.reportDateTo_value)
			
			parameters.put("reportDateTo",endDate)
			parameters.put("reportDate",startDate)
			
			ProjectID =params.projects.id
		}

		GrailsHttpSession gh=getSession()
		parameters.put("projectID",ProjectID.toString())
		parameters.put("Path",webRootDir+"/reports/")
		def sql = getSqlConnection()
		
		byte[] bytes = 
			JasperRunManager.runReportToPdf(
					reportFile.getPath(), 
				parameters, 
				sql
				);
		
		response.setContentType("application/pdf");
		response.setContentLength(bytes.length);
		ServletOutputStream ouputStream = response.getOutputStream();
		ouputStream.write(bytes, 0, bytes.length);
		sql.close()
		ouputStream.flush();
		ouputStream.close();
			
}

		
    def download ={
    		def utilizationInstance=Utilization.get(params.id)
    		println "utilizationid="+utilizationInstance.attachmentPath
    		String fileName = utilizationInstance.attachmentPath
    		def file = new File("grails-app/views/appForm/"+fileName)
    		response.setContentType("application/octet-stream") 
    		response.setHeader("Content-disposition", "attachment;fileName=${file.getName()}") 
    		response.outputStream << file.newInputStream()
    }
}
