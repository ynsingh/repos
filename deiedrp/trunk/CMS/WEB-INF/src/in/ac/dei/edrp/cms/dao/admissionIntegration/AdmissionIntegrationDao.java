package in.ac.dei.edrp.cms.dao.admissionIntegration;

import java.util.List;
import java.util.StringTokenizer;

import in.ac.dei.edrp.cms.domain.admissionIntegration.AdmissionIntegrationInfo;

/**
 * This interface contains method declarations for Integration Functionality with Admission System
 * @author Dheeraj Singh
 * @date 14-MAY-2012
 * @version 1.0
 */
public interface AdmissionIntegrationDao {

	/**
     * Method for getting entities
     * @param input
     * @return entityList
     */
	public List<AdmissionIntegrationInfo> getEntities(AdmissionIntegrationInfo input);
	
	/**
     * Method for getting programs of related entities
     * @param input
     * @return programList
     */
	public List<AdmissionIntegrationInfo> getPrograms(AdmissionIntegrationInfo input);
	
	/**
     * Method for getting selected students from Admission Tables
     * @param input
     * @return studentList
     */
	public List<AdmissionIntegrationInfo> getStudents(AdmissionIntegrationInfo input);
	
	/**
     * Method for getting branch and specialization details of related programs
     * @param input
     * @return branchList
     */
	public List<AdmissionIntegrationInfo> getBranches(AdmissionIntegrationInfo input);
	
	/**
     * Method for inserting data of selected students in CMS tables
     * @param input, data
     * @return status
     */
	public String submitDetails(AdmissionIntegrationInfo input, StringTokenizer data);
}
