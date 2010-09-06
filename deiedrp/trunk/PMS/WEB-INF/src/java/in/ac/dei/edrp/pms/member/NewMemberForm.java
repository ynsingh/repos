package in.ac.dei.edrp.pms.member;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 * MyEclipse Struts
 * Creation date: 02-20-2010
 * 
 * XDoclet definition:
 * @struts.form name="userdetailform"
 */
public class NewMemberForm extends ValidatorForm {
	/*
	 * Generated fields
	 */

	private static final long serialVersionUID = 1L;

	private String emailid2;
	/** emailid property */
	private String emailid;
	/** firstname property */
	private String firstname;
	/** lastname property */
	private String lastname;
	/** phoneno property */
	private String phoneno;
	/** experience property */
	private String experience="--Select--";
	/** skill property */
	private String skill;
	/** securequestion property */
	private String securequestion;
	/** secureanswer property */
	private String secureanswer;
	private String rolename;

	/*
	 * Generated Methods
	 */

	/** 
	 * Method validate
	 * @param mapping
	 * @param request
	 * @return ActionErrors
	 */
	/*public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.experience = "--Select--";
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getEmailid2() {
		return emailid2;
	}

	public void setEmailid2(String emailid2) {
		this.emailid2 = emailid2;
	}

	/** 
	 * Returns the lastname.
	 * @return String
	 */
	public String getLastname() {
		return lastname;
	}

	/** 
	 * Set the lastname.
	 * @param lastname The lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/** 
	 * Returns the emailid.
	 * @return String
	 */
	public String getEmailid() {
		return emailid;
	}

	/** 
	 * Set the emailid.
	 * @param emailid The emailid to set
	 */
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	/** 
	 * Returns the skill.
	 * @return String
	 */
	public String getSkill() {
		return skill;
	}

	/** 
	 * Set the skill.
	 * @param skill The skill to set
	 */
	public void setSkill(String skill) {
		this.skill = skill;
	}

	/** 
	 * Returns the phoneno.
	 * @return String
	 */
	public String getPhoneno() {
		return phoneno;
	}

	/** 
	 * Set the phoneno.
	 * @param phoneno The phoneno to set
	 */
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	/** 
	 * Returns the firstname.
	 * @return String
	 */
	public String getFirstname() {
		return firstname;
	}

	/** 
	 * Set the firstname.
	 * @param firstname The firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/** 
	 * Returns the experience.
	 * @return String
	 */
	public String getExperience() {
		return experience;
	}

	/** 
	 * Set the experience.
	 * @param experience The experience to set
	 */
	public void setExperience(String experience) {
		this.experience = experience;
	}

	/** 
	 * Returns the securequestion.
	 * @return String
	 */
	public String getSecurequestion() {
		return securequestion;
	}

	/** 
	 * Set the securequestion.
	 * @param securequestion The securequestion to set
	 */
	public void setSecurequestion(String securequestion) {
		this.securequestion = securequestion;
	}
	
	public String getSecureanswer() {
		return secureanswer;
	}

	/** 
	 * Set the secureanswer.
	 * @param secureanswer The secureanswer to set
	 */
	public void setSecureanswer(String secureanswer) {
		this.secureanswer = secureanswer;
	}
	
}
