package in.ac.dei.edrp.cms.domain.associateMOU;

/**
 * this is client side bean class for Associate MOU
 * 
 * @version 1.0 7 MAY 2011
 * @author MOHD AMIR
 */
public class AssociateMOU {
	/** declaring private variables **/
	private String mouId;
	private String mouName;
	private String universityId;
	private String universityName;
	private String creatorId;

	/** defining getter and setter method for private variables **/
	public String getMouId() {
		return mouId;
	}

	public void setMouId(String mouId) {
		this.mouId = mouId;
	}

	public String getMouName() {
		return mouName;
	}

	public void setMouName(String mouName) {
		this.mouName = mouName;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}
}
