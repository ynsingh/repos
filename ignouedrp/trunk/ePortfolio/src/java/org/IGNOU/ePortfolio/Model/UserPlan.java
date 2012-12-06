package org.IGNOU.ePortfolio.Model;
// Generated Jul 11, 2012 5:35:40 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;

/**
 * UserPlan generated by hbm2java
 */
public class UserPlan implements java.io.Serializable {

    private long planId;
    private String userId;
    private String PTitle;
    private String PDescription;
    private Set<UserPlanTask> userPlanTasks = new HashSet<UserPlanTask>(0);

    public UserPlan() {
    }

    public UserPlan(long planId) {
        this.planId = planId;
    }

    public UserPlan(long planId, String userId, String PTitle, String PDescription, Set<UserPlanTask> userPlanTasks) {
        this.planId = planId;
        this.userId = userId;
        this.PTitle = PTitle;
        this.PDescription = PDescription;
        this.userPlanTasks = userPlanTasks;
    }

    public long getPlanId() {
        return this.planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPTitle() {
        return this.PTitle;
    }

    public void setPTitle(String PTitle) {
        this.PTitle = PTitle;
    }

    public String getPDescription() {
        return this.PDescription;
    }

    public void setPDescription(String PDescription) {
        this.PDescription = PDescription;
    }

    public Set<UserPlanTask> getUserPlanTasks() {
        return this.userPlanTasks;
    }

    public void setUserPlanTasks(Set<UserPlanTask> userPlanTasks) {
        this.userPlanTasks = userPlanTasks;
    }
}
