/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.db.UserGroupDB;

/**
 * As the basic security feture, payroll users are grouped as
 * User groups and access permissions will be granted to Group rather than a particular User
 *  menu system is being so designed that different user group will be
 * accessing different menus based on (User configuration later)
 *
 *  *  Copyright (c) 2010 - 2011 SMVDU, Katra.
*  All Rights Reserved.
**  Redistribution and use in source and binary forms, with or 
*  without modification, are permitted provided that the following 
*  conditions are met: 
**  Redistributions of source code must retain the above copyright 
*  notice, this  list of conditions and the following disclaimer. 
* 
*  Redistribution in binary form must reproduce the above copyright
*  notice, this list of conditions and the following disclaimer in 
*  the documentation and/or other materials provided with the 
*  distribution. 
* 
* 
*  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED 
*  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
*  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
*  DISCLAIMED.  IN NO EVENT SHALL SMVDU OR ITS CONTRIBUTORS BE LIABLE 
*  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR 
*  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
*  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
*  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
*  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE 
*  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
*  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. 
* 
* 
*  Contributors: Members of ERP Team @ SMVDU, Katra
*
 */
public class UserGroup implements Serializable{

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
