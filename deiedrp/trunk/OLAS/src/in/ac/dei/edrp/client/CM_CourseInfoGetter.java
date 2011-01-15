package in.ac.dei.edrp.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class CM_CourseInfoGetter implements IsSerializable {
	private String university_code;
	private String course_name;
	private String course_code;
	private String course_type_name;	
	private String course_type;
	private boolean status;

	

	public CM_CourseInfoGetter() {
	}

	public String getuniversity_code() {
		return university_code;
	}

	public void setuniversity_code(String university_code) {
		this.university_code = university_code;

	}

	public String getcourse_name() {
		return course_name;
	}

	public void setcourse_name(String course_name) {
		this.course_name = course_name;

	}

	public String getcourseCode() {
		return course_code;
	}

	public void setcourseCode(String course_code) {
		this.course_code = course_code;
	}

	public String getcourse_type_name() {
		return course_type_name;
	}

	public void setcourse_type_name(String course_type_name) {
		this.course_type_name = course_type_name;
	}

	
	public String getCourse_Type() {
		return course_type;
	}

	public void setcourse_Type(String course_type) {
		this.course_type = course_type;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}

