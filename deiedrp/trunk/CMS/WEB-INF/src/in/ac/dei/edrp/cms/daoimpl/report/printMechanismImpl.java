/**
 * 
 */
package in.ac.dei.edrp.cms.daoimpl.report;

import in.ac.dei.edrp.cms.dao.report.printMechanismDao;
import in.ac.dei.edrp.cms.domain.degreelist.DegreeListInfoGetter;
import in.ac.dei.edrp.cms.domain.report.printMechanismInfoGetter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * @author Mandeep
 *
 */
public class printMechanismImpl extends SqlMapClientDaoSupport implements printMechanismDao {
	
	Logger logger = Logger.getLogger(printMechanismImpl.class);
	/**
	 * The methods gets the details of the reports from the database for the
	 * selected session
	 * 
	 * @param input
	 *            object of the referenced bean
	 * @return details in the form of list
	 */
	@SuppressWarnings("unchecked")
	public List<printMechanismInfoGetter> getReportsDetails(printMechanismInfoGetter input) {

		List<printMechanismInfoGetter> resultList = new ArrayList<printMechanismInfoGetter>();

		try {

			resultList = getSqlMapClientTemplate().queryForList(
					"printMechanism.getReportsDetails", input);

		} catch (Exception e) {
			logger.error("Error in getReportDetails " + e);
		}

		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<printMechanismInfoGetter> getCombinations4Entity(printMechanismInfoGetter input) {

		List<printMechanismInfoGetter> result = new ArrayList<printMechanismInfoGetter>();

		try {

			/*
			 * program course key wise
			 */
			if (input.getReportType().equalsIgnoreCase("RCL")) {

				result = getSqlMapClientTemplate().queryForList(
						"printMechanism.getCombinations4Entity", input);
				
				Iterator<printMechanismInfoGetter> iterator = result.iterator();

				while (iterator.hasNext()) {

					printMechanismInfoGetter resultList = new printMechanismInfoGetter();

					printMechanismInfoGetter degreeListInfoGetter = (printMechanismInfoGetter) iterator
							.next();

					degreeListInfoGetter.setEntityId(input.getEntityId());
					degreeListInfoGetter.setPassedFromSession(input
							.getPassedFromSession());
					degreeListInfoGetter.setPassedToSession(input
							.getPassedToSession());
					degreeListInfoGetter.setReportCode(input.getReportCode());

					resultList = (printMechanismInfoGetter) getSqlMapClientTemplate()
							.queryForObject("printMechanism.getflagStatusRCL",
									degreeListInfoGetter);

					if (resultList != null) {

						degreeListInfoGetter.setReportGenerated(resultList
								.getReportGenerated());
						degreeListInfoGetter.setPrintStatus(resultList
								.getPrintStatus());

					}

				}
			}
			/*
			 * entity-program-semester type wise
			 */
			else if (input.getReportType().equalsIgnoreCase("EPS")) {

				result = getSqlMapClientTemplate().queryForList(
						"printMechanism.getCombinationsEPS", input);

				Iterator<printMechanismInfoGetter> iterator = result.iterator();

				while (iterator.hasNext()) {

					printMechanismInfoGetter resultList = new printMechanismInfoGetter();

					printMechanismInfoGetter degreeListInfoGetter = (printMechanismInfoGetter) iterator
							.next();

					degreeListInfoGetter.setEntityId(input.getEntityId());
					degreeListInfoGetter.setPassedFromSession(input
							.getPassedFromSession());
					degreeListInfoGetter.setPassedToSession(input
							.getPassedToSession());
					degreeListInfoGetter
							.setProgramPrintType(degreeListInfoGetter
									.getSemesterName());
					degreeListInfoGetter.setReportCode(input.getReportCode());

					resultList = (printMechanismInfoGetter) getSqlMapClientTemplate()
							.queryForObject("printMechanism.getflagStatusEPS",
									degreeListInfoGetter);

					if (resultList != null) {

						degreeListInfoGetter.setReportGenerated(resultList
								.getReportGenerated());
						degreeListInfoGetter.setPrintStatus(resultList
								.getPrintStatus());

					}

				}

			}
			/*
			 * entity-program wise
			 */
			else if(input.getReportType().equalsIgnoreCase("REP")){
				
				result = getSqlMapClientTemplate().queryForList(
						"printMechanism.getCombinationsREP", input);
				
				Iterator<printMechanismInfoGetter> iterator = result.iterator();

				while (iterator.hasNext()) {

					printMechanismInfoGetter resultList = new printMechanismInfoGetter();

					printMechanismInfoGetter degreeListInfoGetter = (printMechanismInfoGetter) iterator
							.next();

					degreeListInfoGetter.setEntityId(input.getEntityId());
					degreeListInfoGetter.setPassedFromSession(input
							.getPassedFromSession());
					degreeListInfoGetter.setPassedToSession(input
							.getPassedToSession());					
					degreeListInfoGetter.setReportCode(input.getReportCode());

					resultList = (printMechanismInfoGetter) getSqlMapClientTemplate()
							.queryForObject("printMechanism.getflagStatusREP",
									degreeListInfoGetter);

					if (resultList != null) {

						degreeListInfoGetter.setReportGenerated(resultList
								.getReportGenerated());
						degreeListInfoGetter.setPrintStatus(resultList
								.getPrintStatus());

					}

				}
				
			}
			
			/*
			 * program semester & semester type wise
			 */
			else if(input.getReportType().equalsIgnoreCase("PSS")){
				
				result = getSqlMapClientTemplate().queryForList(
						"printMechanism.getCombinationsPSS", input);
				
				Iterator<printMechanismInfoGetter> iterator = result.iterator();

				while (iterator.hasNext()) {

					printMechanismInfoGetter resultList = new printMechanismInfoGetter();

					printMechanismInfoGetter degreeListInfoGetter = (printMechanismInfoGetter) iterator
							.next();

					degreeListInfoGetter.setEntityId(input.getEntityId());
					degreeListInfoGetter.setPassedFromSession(input
							.getPassedFromSession());
					degreeListInfoGetter.setPassedToSession(input
							.getPassedToSession());					
					degreeListInfoGetter.setReportCode(input.getReportCode());
					
					resultList = (printMechanismInfoGetter) getSqlMapClientTemplate()
							.queryForObject("printMechanism.getflagStatusPSS",
									degreeListInfoGetter);

					if (resultList != null) {

						degreeListInfoGetter.setReportGenerated(resultList
								.getReportGenerated());
						degreeListInfoGetter.setPrintStatus(resultList
								.getPrintStatus());

					}

				}
				
			}
			
			/*
			 * program semester & semester type wise
			 */
			else if(input.getReportType().equalsIgnoreCase("PBS")){
				
				result = getSqlMapClientTemplate().queryForList(
						"printMechanism.getCombinationsPBS", input);
				
				Iterator<printMechanismInfoGetter> iterator = result.iterator();

				while (iterator.hasNext()) {

					printMechanismInfoGetter resultList = new printMechanismInfoGetter();

					printMechanismInfoGetter degreeListInfoGetter = (printMechanismInfoGetter) iterator
							.next();

					degreeListInfoGetter.setEntityId(input.getEntityId());
					degreeListInfoGetter.setPassedFromSession(input
							.getPassedFromSession());
					degreeListInfoGetter.setPassedToSession(input
							.getPassedToSession());					
					degreeListInfoGetter.setReportCode(input.getReportCode());

					resultList = (printMechanismInfoGetter) getSqlMapClientTemplate()
							.queryForObject("printMechanism.getflagStatusPBS",
									degreeListInfoGetter);

					if (resultList != null) {

						degreeListInfoGetter.setReportGenerated(resultList
								.getReportGenerated());
						degreeListInfoGetter.setPrintStatus(resultList
								.getPrintStatus());

					}

				}
				
			}
			
			else if(input.getReportType().equalsIgnoreCase("RVR")){
				
				result = getSqlMapClientTemplate().queryForList(
						"printMechanism.getCombinationsRVR", input);

				
			}
			
		} catch (Exception e) {
			logger.error("Error in getReportDetails " + e);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<printMechanismInfoGetter> getGeneralReportList(printMechanismInfoGetter input) {
		List<printMechanismInfoGetter>resultList=new ArrayList<printMechanismInfoGetter>();
		try{
			resultList=getSqlMapClientTemplate().queryForList("printMechanism.getGeneralReports",input);
		}
		catch (Exception e) {
			logger.error("Error in getGenralReports " + e);
			
		}
		return resultList;
	}
	/**
	 * The method retrieves the list of entities in the university
	 * @param input object of the referenced bean
	 * @return List of entities
	 */
	@SuppressWarnings("unchecked")
	public List<printMechanismInfoGetter> getEntities(printMechanismInfoGetter input) {
		List entityList = null;
		try {
			/*
			 * list of entities in the university
			 */
			entityList = getSqlMapClient().queryForList("printMechanism.getEntities",input);
		} catch (Exception e) {
			logger.error("Exception in getEntities"+e);
		}
		return entityList;
	}
	
	/**
	 * This method is used to get the List of Reports
	 * @param input details of the Reports 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<printMechanismInfoGetter> getReportDetailList(
			printMechanismInfoGetter input) {
		List<printMechanismInfoGetter>resultDetail=new ArrayList<printMechanismInfoGetter>();
		try{
			resultDetail=getSqlMapClientTemplate().queryForList("printMechanism.getReportDetail",input);
		}
		catch (Exception e) {
			logger.error("Error in getGenralReports " + e);
		}
		return resultDetail;
	}
	/**
	 * This method is used to insert,Update,Delete the Report Details
	 * @param input details of the Reports
	 * @return String
	 * @throws Exception
	 */	
	public String setReportAuthority(
			printMechanismInfoGetter input) {
		 String upresult=null;
		 String delResult=null;
		 int updateCount=0;
		 int deletedRecords=0;
		try{
 		
    	 if(input.getPrintStatus().equalsIgnoreCase("Insert")){
    		 int count=Integer.parseInt((String) getSqlMapClientTemplate().queryForObject("printMechanism.getCount",input));

    		 if(count==0){
    			 	getSqlMapClientTemplate().insert("printMechanism.setReportAuthority",input).toString();
    		 }
    		 else{
    			 
    			  updateCount+=getSqlMapClientTemplate().update("printMechanism.updateReportAuthority",input);
    			 upresult=Integer.toString(updateCount);
    		 }
	 
      		
    	 }
    	 else{
    		 deletedRecords+=getSqlMapClientTemplate().delete("printMechanism.deleteRecords",input);
      		delResult="delete"+deletedRecords;
    		    }
    	 }
     catch (Exception e) {
		logger.error("Error in setReportAuthority"+e);
		return "Error"+e;
	}
     return "success";
	}

}
