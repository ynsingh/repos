package org.dei.edrp.pms.projmanage;

/**
 * This class is responsible for setting and getting the project fields.
 * It contains setter and getter methods.
 * @author anil
 *
 */
public class ProjectFields {
	//properties
	private int id;//for holding the project id.
	private String name;//For holding project name
	private String startDate;//for holding the start date of project.
	private String finishDate;//for holding finish date of project.
	private int tbudget;//for holding target budget of project.
	private String priority;//for holding the priority of project.
	private String status;//for holding the status of project.
	private String viewPermission;//for holding the view permission of project. 
	private String gcolor;//for holding the gantt chart color of project.
	private String darea;//for holding the description area of project.
	private String enable;
	
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
	public ProjectFields(int id, String name, String startDate, String finishDate,int targetbudget, String priority, String status, String viewpermission,String gchart,String description,String enable){
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.tbudget = targetbudget;
		this.priority = priority;
		this.status = status;
		this.viewPermission = viewpermission;
		this.gcolor = gchart;
		this.darea = description;
		this.enable = enable;
	}

	//getter and setter methods
	
	
	/**The method getId is used for getting the project id of a project */
	public int getId() {
		return id;
	}
	/**The method setId is used for setting the project id of a project */
	public void setId(int id) {
		this.id = id;
	}
	/**The method getName return the project name of a project */
	public String getName() {
		return name;
	}
	/**The method setName is used for setting the project name of a project */
	public void setName(String name) {
		this.name = name;
	}
	/**The method getStartdate return the starting date of a project */
	public String getStartDate() {
		return startDate;
	}
	/**The method setStartdate is used for setting the starting date of a project */
	public void setStartDate(String StartDate) {
		this.startDate = StartDate;
	}
	/**The method getFinishdate return the finished date of a project */
	public String getFinishDate() {
		return finishDate;
	}
	/**The method setFinishdate is used for setting the finished date of a project */
	public void setFinishDate(String FinishDate) {
		this.finishDate = FinishDate;
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

/**The method getViewPermission return the View Permission of a project */
	public String getViewPermission() {
		return viewPermission;
	}

/**The method setViewPermission is used for setting the view permission of a project */
	public void setViewPermission(String viewPermission) {
		this.viewPermission = viewPermission;
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
	
/**The method getDarea return the description about the project */
	public String getDarea() {
		return darea;
	}
	
/**The method setDarea is used for setting the description about the project */
	public void setDarea(String darea) {
		this.darea = darea;
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
