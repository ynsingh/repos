package in.ac.dei.edrp.pms.projmanage;

/**
 * This class is responsible for setting and getting the project fields.
 * It contains setter and getter methods.
 * @author anil
 *
 */
public class ProjectFields {
	//properties
	private String project_code;//for holding the project code.
	private String project_name;//For holding project name
	private String scheduleStartDate;//for holding the schedule start date of project.
	private String scheduleEndDate;//for holding schedule end date of project.
	private String actualStartDate;//for holding the actual start date of project.
	private String actualEndDate;//for holding actual end date of project.
	private int tbudget;//for holding target budget of project.
	private String priority;//for holding the priority of project.
	private String status;//for holding the status of project.
	private String gcolor;//for holding the gantt chart color of project.
	private String description;//for holding the description area of project.
	private String enable;
	//private String org_portal;
	private String editPermission;
	
	
	//constructors
	public ProjectFields(){}
	/**
	 * This constructor is used for initialising the project fields with taking arguments. 
	 * @param id This holds the project id.
	 * @param name This holds the project name.
	 * @param startDate This holds the start date of project.
	 * @param finishDate This holds the finish date of project.
	 * @param targetbudget This holds the target budget of project.
	 * @param priority This holds the priority of project.
	 * @param status This holds the status of project.
	 * @param viewpermission This holds the view permission of project.
	 * @param gchart This holds the gantt chart colour of project.
	 * @param description This holds the description of a project.
	 */
	public ProjectFields(String project_code, String project_name, String scheduleStartDate,
			String scheduleEndDate,String actualStartDate,String actualEndDate,
			int tbudget, String priority, String status,String gcolor,
			String description,String enable,String editPermission){
		this.project_code = project_code;
		this.project_name = project_name;
		this.scheduleStartDate = scheduleStartDate;
		this.scheduleEndDate = scheduleEndDate;
		this.actualStartDate = actualStartDate;
		this.actualEndDate = actualEndDate;
		this.tbudget = tbudget;
		this.priority = priority;
		this.status = status;
		this.gcolor = gcolor;
		this.description = description;
		this.enable = enable;
		this.editPermission=editPermission;
	//	this.org_portal=org_portal;
		
	}

	//getter and setter methods
//	
//	/**The method getOrg_portal return the orgportal code  */
//	public String getOrg_portal() {
//		return org_portal;
//	}
///**The method setOrg_portal is used for setting the orgportal code*/
//	public void setOrg_portal(String org_portal) {
//		this.org_portal = org_portal;
//	}
//	
	
	/**The method getEditPermission return the project edit permission  */
	public String getEditPermission() {
		return editPermission;
	}
/**The method setEditPermission is used for setting the project edit permission */
	public void setEditPermission(String editPermission) {
		this.editPermission = editPermission;
	}
	
	/**The method getProject_code is used for getting the project code of a project */
	public String getProject_code() {
		return project_code;
	}
	/**The method setProject_code is used for setting the project code of a project */
	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}
	/**The method getProject_name return the project name of a project */
	public String getProject_name() {
		return project_name;
	}
	/**The method setProject_name is used for setting the project name of a project */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	/**The method getScheduleStartDate return the schedule start date of a project */
	public String getScheduleStartDate() {
		return scheduleStartDate;
	}
/**The method setScheduleStartDate is used for setting the schedule start date of a project */
	public void setScheduleStartDate(String scheduleStartDate) {
		this.scheduleStartDate = scheduleStartDate;
	}
	
/**The method getScheduleEndDate return the schedule end date of a project */
	public String getScheduleEndDate() {
		return scheduleEndDate;
	}
/**The method setScheduleEndDate is used for setting the schedule end date of a project */
	public void setScheduleEndDate(String scheduleEndDate) {
		this.scheduleEndDate = scheduleEndDate;
	}

	
	/**The method getActualStartDate return the actual start date of a project */
	public String getActualStartDate() {
		return actualStartDate;
	}
/**The method setActualStartDate is used for setting the actual start date of a project */
	public void setActualStartDate(String actualStartDate) {
		this.actualStartDate = actualStartDate;
	}
	
/**The method getActualEndDate return the actual end date of a project */
	public String getActualEndDate() {
		return actualEndDate;
	}
/**The method setActualEndDate is used for setting the actual end date of a project */
	public void setActualEndDate(String actualEndDate) {
		this.actualEndDate = actualEndDate;
	}
	
	
/**The method getTbudget return the target budget of a project */
	public int getTbudget() {
		return this.tbudget;
	}
/**The method setTbudget is used for setting the target budget of a project */
	public void setTbudget(int tbudget) {
		this.tbudget = tbudget;
	}

/**The method getPriority return the priority of a project */
	public String getPriority() {
		return priority;
	}
	
/**The method setPriority is used for setting the priority of a project */	
	public void setPriority(String priority) {
		this.priority = priority;
	}


/**The method getStatus return the status of a project */
	public String getStatus() {
		return status;
	}

/**The method setStatus is used for setting the status of a project */
	public void setStatus(String status) {
		this.status = status;
	}

	
/**The method getGcolor return the gantt chart color of a project */
	public String getGcolor() {
		return gcolor;
	}
	
/**The method setGcolor is used for setting the gantt chart color of a project */
	public void setGcolor(String gcolor) {
		this.gcolor = gcolor;
	}
	
/**The method getDescription return the description about the project */
	public String getDescription() {
		return description;
	}
	
/**The method setDescription is used for setting the description about the project */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**The method getEnable return the enable status of a project */
	public String getEnable() {
		return enable;
	}
	/**The method setName is used for setting the enable status of a project */
	public void setEnable(String enable) {
		this.enable = enable;
	}

}
