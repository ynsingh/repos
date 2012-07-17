package in.ac.dei.edrp.cms.domain.studentregistration;


/**
 * Bean class for course related data
 * @author Manpreet Kaur
 * @date 10-12-2010
 * @version 1.0
 */
public class CourseInfoGetter {
    private String courseCode;
    private String courseName;
    private String courseGroup;
    private String credits;
    private String courseType;
    private String minimumCourses;
    private String maximumCourses;
    private String programCourseKey;
    private String courseClassification;
    private String courseGroupCode;
    private String courseClassificationName;
    /*
     * added for compulsory group
     */
    private String groupCode;
    private String groupName;
    private String groupOrder;
    private String minimumSelection;
    private String maximumSelection;
    private String subGroupCode;
    private String subGroupName;
    private String conditionalGroup;
    private String linkedGroup;
    private String linkedMinimumSelection;
    private String linkedMaximumSelection;
    private String groupRule;


    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(String courseGroup) {
        this.courseGroup = courseGroup;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getMinimumCourses() {
        return minimumCourses;
    }

    public void setMinimumCourses(String minimumCourses) {
        this.minimumCourses = minimumCourses;
    }

    public String getMaximumCourses() {
        return maximumCourses;
    }

    public void setMaximumCourses(String maximumCourses) {
        this.maximumCourses = maximumCourses;
    }

    public String getProgramCourseKey() {
        return programCourseKey;
    }

    public void setProgramCourseKey(String programCourseKey) {
        this.programCourseKey = programCourseKey;
    }

    public String getCourseClassification() {
        return courseClassification;
    }

    public void setCourseClassification(String courseClassification) {
        this.courseClassification = courseClassification;
    }

	/**
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return groupCode;
	}

	/**
	 * @param groupCode the groupCode to set
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	/**
	 * @return the groupOrder
	 */
	public String getGroupOrder() {
		return groupOrder;
	}

	/**
	 * @param groupOrder the groupOrder to set
	 */
	public void setGroupOrder(String groupOrder) {
		this.groupOrder = groupOrder;
	}

	/**
	 * @return the minimumSelection
	 */
	public String getMinimumSelection() {
		return minimumSelection;
	}

	/**
	 * @param minimumSelection the minimumSelection to set
	 */
	public void setMinimumSelection(String minimumSelection) {
		this.minimumSelection = minimumSelection;
	}

	/**
	 * @return the maximumSelection
	 */
	public String getMaximumSelection() {
		return maximumSelection;
	}

	/**
	 * @param maximumSelection the maximumSelection to set
	 */
	public void setMaximumSelection(String maximumSelection) {
		this.maximumSelection = maximumSelection;
	}

	/**
	 * @return the subGroupCode
	 */
	public String getSubGroupCode() {
		return subGroupCode;
	}

	/**
	 * @param subGroupCode the subGroupCode to set
	 */
	public void setSubGroupCode(String subGroupCode) {
		this.subGroupCode = subGroupCode;
	}

	/**
	 * @return the conditionalGroup
	 */
	public String getConditionalGroup() {
		return conditionalGroup;
	}

	/**
	 * @param conditionalGroup the conditionalGroup to set
	 */
	public void setConditionalGroup(String conditionalGroup) {
		this.conditionalGroup = conditionalGroup;
	}

	/**
	 * @return the linkedGroup
	 */
	public String getLinkedGroup() {
		return linkedGroup;
	}

	/**
	 * @param linkedGroup the linkedGroup to set
	 */
	public void setLinkedGroup(String linkedGroup) {
		this.linkedGroup = linkedGroup;
	}

	/**
	 * @return the linkedMinimumSelection
	 */
	public String getLinkedMinimumSelection() {
		return linkedMinimumSelection;
	}

	/**
	 * @param linkedMinimumSelection the linkedMinimumSelection to set
	 */
	public void setLinkedMinimumSelection(String linkedMinimumSelection) {
		this.linkedMinimumSelection = linkedMinimumSelection;
	}

	/**
	 * @return the linkedMaximumSelection
	 */
	public String getLinkedMaximumSelection() {
		return linkedMaximumSelection;
	}

	/**
	 * @param linkedMaximumSelection the linkedMaximumSelection to set
	 */
	public void setLinkedMaximumSelection(String linkedMaximumSelection) {
		this.linkedMaximumSelection = linkedMaximumSelection;
	}

	/**
	 * @return the groupRule
	 */
	public String getGroupRule() {
		return groupRule;
	}

	/**
	 * @param groupRule the groupRule to set
	 */
	public void setGroupRule(String groupRule) {
		this.groupRule = groupRule;
	}

	/**
	 * @return the courseGroupCode
	 */
	public String getCourseGroupCode() {
		return courseGroupCode;
	}

	/**
	 * @param courseGroupCode the courseGroupCode to set
	 */
	public void setCourseGroupCode(String courseGroupCode) {
		this.courseGroupCode = courseGroupCode;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the subGroupName
	 */
	public String getSubGroupName() {
		return subGroupName;
	}

	/**
	 * @param subGroupName the subGroupName to set
	 */
	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
	}

	public void setCourseClassificationName(String courseClassificationName) {
		this.courseClassificationName = courseClassificationName;
	}

	public String getCourseClassificationName() {
		return courseClassificationName;
	}
}
