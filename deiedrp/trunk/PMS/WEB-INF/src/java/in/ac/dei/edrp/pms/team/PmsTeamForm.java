package in.ac.dei.edrp.pms.team;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 17-02-2010
 * 
 * XDoclet definition:
 * @struts.form name="editportalform"
 */
public class PmsTeamForm extends ValidatorForm{
	
	/*
	 * Generated fields
	 */
	private String[] select1=null;
	private String[] select2=null;
	private String projectName=null;
	private String orgportal=null;
	private String pcode=null;
	private String defValueOfSelect2=null;
	/*
	 * Generated Methods
	 */
		
/** 
* Method reset is used for resetting all the fields with default values.
* @param mapping
* @param request
*/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

public String getDefValueOfSelect2() {
	return defValueOfSelect2;
}

public void setDefValueOfSelect2(String defValueOfSelect2) {
	this.defValueOfSelect2 = defValueOfSelect2;
}

public String getPcode() {
	return pcode;
}

public void setPcode(String pcode) {
	this.pcode = pcode;
}

public String getProjectName() {
	return projectName;
}

public void setProjectName(String projectName) {
	this.projectName = projectName;
}

public String getOrgportal() {
	return orgportal;
}

public void setOrgportal(String orgportal) {
	this.orgportal = orgportal;
}

public String[] getSelect1() {
	return select1;
}

public void setSelect1(String []select1) {
	this.select1 = select1;
}

public String[] getSelect2() {
	return select2;
}

public void setSelect2(String[] select2) {
	this.select2 = select2;
}
	

}