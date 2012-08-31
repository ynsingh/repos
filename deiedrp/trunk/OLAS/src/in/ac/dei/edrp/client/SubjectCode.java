package in.ac.dei.edrp.client;

import java.io.Serializable;

public class SubjectCode implements Serializable{

	private String university_id;
	private String programID;
	private String subject_code;
	private String subject_code_description;
	public SubjectCode() {
		
	}
	public SubjectCode(String university_id, String programID,
			String subject_code) {
		super();
		this.university_id = university_id;
		this.programID = programID;
		this.subject_code = subject_code;
	}
	public void setSubject_code_description(String scd)
	{
	this.subject_code_description=scd;
	}
	public String getSubject_code_description()
	{
	return subject_code_description;
	}
	public String getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(String university_id) {
		this.university_id = university_id;
	}
	public String getProgramID() {
		return programID;
	}
	public void setProgramID(String programID) {
		this.programID = programID;
	}
	public String getSubject_code() {
		return subject_code;
	}
	public void setSubject_code(String subject_code) {
		this.subject_code = subject_code;
	}
	
	
}
