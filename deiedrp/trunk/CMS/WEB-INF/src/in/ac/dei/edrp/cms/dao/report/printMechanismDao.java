/**
 * 
 */
package in.ac.dei.edrp.cms.dao.report;


import in.ac.dei.edrp.cms.domain.report.printMechanismInfoGetter;


import java.util.List;

/**
 * @author Mandeep
 *
 */
public interface printMechanismDao {

	/**
	 * The methods gets the details of the reports from the database
	 * for the selected session
	 * @param input object of the referenced bean
	 * @return details in the form of list
	 */
	public List<printMechanismInfoGetter> getReportsDetails(printMechanismInfoGetter input);

	/**
	 * The method picks the details of the combinations 
	 * for the passed inputs
	 * @param input object of the referenced bean
	 * @return list of combinations
	 */
	public List<printMechanismInfoGetter> getCombinations4Entity(
			printMechanismInfoGetter input);

	public List<printMechanismInfoGetter> getGeneralReportList(printMechanismInfoGetter input);	
	public List<printMechanismInfoGetter> getEntities(printMechanismInfoGetter input);
	public List<printMechanismInfoGetter> getReportDetailList(
			printMechanismInfoGetter input);

	public String setReportAuthority(
			printMechanismInfoGetter input);
}
