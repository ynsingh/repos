import groovy.sql.Sql
import java.util.*;
import java.io.*;
import grails.converters.*
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession
class GraphsController {

    def dataSource
    def grantAllocationService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


    //Graph Coding Starts here
    def projectList={
    	def sql=new Sql(dataSource);
    	GrailsHttpSession gh=getSession()
    	def grantAllocationWithprojectsInstanceList = grantAllocationService
													.getGrantAllocationGroupByProjects(gh.getValue("Party"))
		def list=grantAllocationWithprojectsInstanceList.projects
          render(contentType:"text/xml")
          {
            projects
               {
                  for(a in list)
                  {
                    project()
                    {
                      label(a.name)
                      data(a.id)
                   }
                 }
               }
          }
    }


    def allocationChart={      
        def sel_projectid=params.proj_id;
        def sql=new Sql(dataSource);
        DecimalFormat formatter = new DecimalFormat("##0.0######");
        def grantAllocationSplitInstance = GrantAllocationSplit.executeQuery("select new map(sum(GS.amount) as AMOUNT_ALLOCATED,GS.accountHead.name as ACCOUNT_HEAD) from GrantAllocationSplit GS where GS.grantAllocation.projects.id='"+sel_projectid+"' group by GS.accountHead")
        def query="select a.amount AS AMOUNT_ALLOCATED,b.name as ACCOUNT_HEAD from grant_allocation_split a INNER JOIN account_heads b where a.projects_id='"+sel_projectid+"' and a.account_head_id=b.id";  
        def list=grantAllocationSplitInstance
       
      //  DecimalFormat formatter = new DecimalFormat("##0.0######");
      // println(formatter.format(a.AMOUNT_ALLOCATED).toString());
                   
          render(contentType:"text/xml")
          {
            projects
               {
                  for(a in list)
                  {
                    allocation('accounthead':a.ACCOUNT_HEAD,'amount')
                    {
                      accounthead(a.ACCOUNT_HEAD)
                      amount(formatter.format(a.AMOUNT_ALLOCATED).toString())
                   }
                 }
               }
          }        
    }


    def setDates={
        def sel_projectid=params.proj_id;
       // def sel_projectid='162';
        def sql=new Sql(dataSource);
        def row1 = sql.firstRow("select MIN(DATE_FORMAT(date_of_expense,'%m/%d/%Y')) as MIN_DATE from grant_expense where projects_id='"+sel_projectid+"'")
        def row2 = sql.firstRow("select MAX(DATE_FORMAT(date_of_expense,'%m/%d/%Y')) as MAX_DATE from grant_expense where projects_id='"+sel_projectid+"'")
        String xml_cont='<expense>';
        xml_cont+='<daterange>';
        xml_cont+='<mindate>'+row1.MIN_DATE+'</mindate>';
        xml_cont+='<maxdate>'+row2.MAX_DATE+'</maxdate>';
        xml_cont+='</daterange>';
        xml_cont+='</expense>';
        render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
    }

     def expenseChart={
        def sel_projectid=params.proj_id;
        def from_date=params.from;
        def to_date=params.to;       
        def sql=new Sql(dataSource);
        DecimalFormat formatter = new DecimalFormat("##0.0######");
        def query="select SUM(a.expense_amount) as EXPENSE_AMOUNT,DATE_FORMAT(a.date_of_expense,'%Y-%m-%d') as EXPENSE_DATE,a.grant_allocation_split_id,b.account_head_id as ACCOUNT_HEAD_ID,c.name as ACCOUNT_HEAD from grant_expense a INNER JOIN grant_allocation_split b INNER JOIN account_heads c where a.projects_id='"+sel_projectid+"' and a.grant_allocation_split_id=b.id and b.account_head_id=c.id and ( DATE_FORMAT(date_of_expense,'%Y-%m-%d') >= '"+from_date+"' and DATE_FORMAT(date_of_expense,'%Y-%m-%d') <= '"+to_date+"')  group by b.account_head_id order by c.name asc";
        def list=sql.rows(query)
          render(contentType:"text/xml")
          {
            projects
               {
                  for(a in list)
                  {
                    expenses()
                    {          
                      accounthead(a.ACCOUNT_HEAD)
                      expense(formatter.format(a.EXPENSE_AMOUNT).toString())
                   }
                 }
               }
          }
    }


     def receiptChart={
        def sel_projectid=params.proj_id;
        def sql=new Sql(dataSource);
        DecimalFormat formatter = new DecimalFormat("##0.0######");
        def query="select SUM(amount) as RECEIPT_AMOUNT,DATE_FORMAT(date_of_receipt,'%b %D %Y') as RECEIPT_DATE from grant_receipt where projects_id='"+sel_projectid+"' group by DATE_FORMAT(date_of_receipt,'%b %D %Y')";       
        def list=sql.rows(query)
          render(contentType:"text/xml")
          {
            projects
               {
                  for(a in list)
                  {
                    receipts()
                    {                 
                      date(a.RECEIPT_DATE)
                      amount(formatter.format(a.RECEIPT_AMOUNT).toString())
                   }
                 }
               }
          }
    }



    def test={
         def sel_projectid=params.proj_id;
         def sql=new Sql(dataSource);
         DecimalFormat formatter = new DecimalFormat("##0.0######");
         def years=[];
         def list1=sql.rows("select DISTINCT(DATE_FORMAT(date_of_receipt,'%Y')) as YEAR from grant_receipt where projects_id='"+sel_projectid+"' order by  DATE_FORMAT(date_of_receipt,'%Y') asc")
         def list2=sql.rows("select DISTINCT(DATE_FORMAT(date_of_expense,'%Y')) as YEAR from grant_expense where projects_id='"+sel_projectid+"' order by  DATE_FORMAT(date_of_expense,'%Y') asc")
        // def years = ["2020","2018","2010","2011","2011","2012","2009","2010","2015"];
         for(a in list1) {
             years.add(a.YEAR);
         }
         for(b in list2) {
             years.add(b.YEAR);
         }        
         years=years.unique();
         years=years.sort();


        /*
        println "final test";
        def expenseList = []
        def receiptList = []
        def sql=new Sql(dataSource);
        def years = ["2010","2011"];
        def months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];     
         for(year in years)
         {
                for(month in months)
                {
                 //   def mnth=month;
                    def find_date=month+'-'+year;
                    println(find_date);
                    def row = sql.firstRow("select sum(amount) as AMOUNT,DATE_FORMAT(date,'%b-%Y') from tbl_expense where DATE_FORMAT(date,'%b-%Y')='"+find_date+"'")
                    if(row.AMOUNT==null)
                    expenseList.add(find_date+'->0')
                    else
                    expenseList.add(find_date+'->'+row.AMOUNT)

                    def row1 = sql.firstRow("select sum(amount) as AMOUNT,DATE_FORMAT(date,'%b-%Y') from tbl_receipt where DATE_FORMAT(date,'%b-%Y')='"+find_date+"'")
                    if(row1.AMOUNT==null)
                    receiptList.add(find_date+'->0')
                    else
                    receiptList.add(find_date+'->'+row1.AMOUNT)
                }
         }

         def x=0;
         def y=0;

         String xml_cont='<project>';
         for ( i in 0..(receiptList.size-1))
         {
              def receipt_split=receiptList[i].split("->");
              def expense_split=expenseList[i].split("->");
              if(receipt_split[1].toInteger() !=0 && expense_split[1].toInteger() !=0)
              {                
                 x+=receipt_split[1].toFloat();
                 y+=expense_split[1].toFloat();
                 xml_cont+='<month>';
                 xml_cont+='<date>'+receipt_split[0]+'</date>';
                 xml_cont+='<amount>'+(x-y)+'</amount>';
                 xml_cont+='</month>';
              }
         }
         xml_cont+='</project>';
         render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
         */
    }


    def monthlyExpense={
         def sel_projectid=params.proj_id;
        def expenseList = []
        def receiptList = []
        def sql=new Sql(dataSource);
        def months = ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
        
        //Setting the Years
        def years=[];
        def list1=sql.rows("select DISTINCT(DATE_FORMAT(date_of_receipt,'%Y')) as YEAR from grant_receipt where projects_id='"+sel_projectid+"' order by  DATE_FORMAT(date_of_receipt,'%Y') asc")
        def list2=sql.rows("select DISTINCT(DATE_FORMAT(date_of_expense,'%Y')) as YEAR from grant_expense where projects_id='"+sel_projectid+"' order by  DATE_FORMAT(date_of_expense,'%Y') asc")
        for(a in list1) {
             years.add(a.YEAR);
         }
         for(b in list2) {
             years.add(b.YEAR);
         }
         years=years.unique();
         years=years.sort();
         //Setting the Years

    
         for(year in years)
         {
                for(month in months)
                {
                 //   def mnth=month;
                    def find_date=month+'-'+year;

                    def row1 = sql.firstRow("select sum(amount) as AMOUNT,DATE_FORMAT(date_of_receipt,'%b-%Y') from grant_receipt where DATE_FORMAT(date_of_receipt,'%b-%Y')='"+find_date+"' and projects_id='"+sel_projectid+"' group by DATE_FORMAT(date_of_receipt,'%b-%Y')")
                    if(row1?.AMOUNT==null)
                    receiptList.add(find_date+'->0')
                    else
                    receiptList.add(find_date+'->'+row1.AMOUNT)


                    def row = sql.firstRow("select sum(expense_amount) as AMOUNT,DATE_FORMAT(date_of_expense,'%b-%Y') from grant_expense where DATE_FORMAT(date_of_expense,'%b-%Y')='"+find_date+"' and projects_id='"+sel_projectid+"' group by DATE_FORMAT(date_of_expense,'%b-%Y')")
                    if(row?.AMOUNT==null)
                    expenseList.add(find_date+'->0')
                    else
                    expenseList.add(find_date+'->'+row.AMOUNT)

                   
                }
         }

         def x=0;
         def y=0;
        String xml_cont='<project>';
         for ( i in 0..(receiptList.size-1))
         {
              def receipt_split=receiptList[i].split("->");
              def expense_split=expenseList[i].split("->");
            
                 x+=receipt_split[1].toFloat();
                 y+=expense_split[1].toFloat();
                 xml_cont+='<month>';
                 xml_cont+='<date>'+receipt_split[0]+'</date>';
                 xml_cont+='<amount>'+(x-y)+'</amount>';
                 xml_cont+='</month>';                
          
              
         }
         xml_cont+='</project>';
         render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")
    }
	
	
	
	
	
	 //Graph Coding Starts here
    def scroll={
	
	String xml_cont="""<institutes>
	<institute>
	<month>Jan</month>
	<rain>15</rain>
	</institute>
	<institute>
	<month>Feb</month>
	<rain>20</rain>
	</institute>
	<institute>
	<month>Mar</month>
	<rain>25</rain>
	</institute>
	<institute>
	<month>Apr</month>
	<rain>50</rain>
	</institute>
	<institute>
	<month>May</month>
	<rain>75</rain>
	</institute>
	<institute>
	<month>Jun</month>
	<rain>78</rain>
	</institute>
	<institute>
	<month>Jul</month>
	<rain>24</rain>
	</institute>
	<institute>
	<month>Aug</month>
	<rain>16</rain>
	</institute>
	<institute>
	<month>Sep</month>
	<rain>19</rain>
	</institute>
	<institute>
	<month>Oct</month>
	<rain>65</rain>
	</institute>
	<institute>
	<month>Nov</month>
	<rain>85</rain>
	</institute>
	<institute>
	<month>Dec</month>
	<rain>35</rain>
	</institute>	
	<institute>
	<month>abc</month>
	<rain>15</rain>
	</institute>
	<institute>
	<month>def</month>
	<rain>20</rain>
	</institute>
	<institute>
	<month>ghi</month>
	<rain>25</rain>
	</institute>
	<institute>
	<month>jkl</month>
	<rain>50</rain>
	</institute>
	<institute>
	<month>mno</month>
	<rain>75</rain>
	</institute>
	<institute>
	<month>pqr</month>
	<rain>78</rain>
	</institute>
	<institute>
	<month>stu</month>
	<rain>24</rain>
	</institute>
	<institute>
	<month>uvw</month>
	<rain>16</rain>
	</institute>
	<institute>
	<month>xyz</month>
	<rain>19</rain>
	</institute>
	<institute>
	<month>cde</month>
	<rain>65</rain>
	</institute>
	<institute>
	<month>efg</month>
	<rain>85</rain>
	</institute>
	<institute>
	<month>hij</month>
	<rain>35</rain>
	</institute>
	
	
	
	<institute>
	<month>klm</month>
	<rain>15</rain>
	</institute>
	<institute>
	<month>nop</month>
	<rain>20</rain>
	</institute>
	<institute>
	<month>qer</month>
	<rain>25</rain>
	</institute>
	<institute>
	<month>vbn</month>
	<rain>50</rain>
	</institute>
	<institute>
	<month>xft</month>
	<rain>75</rain>
	</institute>
	<institute>
	<month>jgf</month>
	<rain>78</rain>
	</institute>
	<institute>
	<month>rtt</month>
	<rain>24</rain>
	</institute>
	<institute>
	<month>sfg</month>
	<rain>16</rain>
	</institute>
	<institute>
	<month>ihg</month>
	<rain>19</rain>
	</institute>
	<institute>
	<month>jjh</month>
	<rain>65</rain>
	</institute>
	<institute>
	<month>rrr</month>
	<rain>85</rain>
	</institute>
	<institute>
	<month>mmm</month>
	<rain>35</rain>
	</institute>
</institutes>"""
			 render(text:xml_cont,contentType:"text/xml",encoding:"UTF-8")     
    }

}
