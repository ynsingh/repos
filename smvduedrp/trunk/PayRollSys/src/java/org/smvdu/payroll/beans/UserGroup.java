/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.util.ArrayList;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.UserGroupDB;

/**
 *
 * @author Algox
 */
public class UserGroup {

    public SelectItem[] getGroupAsArray() {
        ArrayList<UserGroup> groups = new UserGroupDB().load();
        groupAsArray = new SelectItem[groups.size()];
        UserGroup ug = null;
        for(int i=0;i<groups.size();i++)
        {
            ug = groups.get(i);
            SelectItem si = new SelectItem(ug.getId(), ug.getName());
            groupAsArray[i] = si;
        }
        return groupAsArray;
    }

    public void setGroupAsArray(SelectItem[] groupAsArray) {
        this.groupAsArray = groupAsArray;
    }
    private ArrayList<UserGroup> groups = null;

    public ArrayList<UserGroup> getGroups() {

        return new UserGroupDB().load();
    }

    public void setGroups(ArrayList<UserGroup> groups) {
        this.groups = groups;
    }




    private SelectItem[] groupAsArray;

    


    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
