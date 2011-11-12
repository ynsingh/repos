/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.composite;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.beans.SessionMaster;
import org.smvdu.payroll.beans.db.SessionDB;

/**
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
public class SessionController  {

    private UIData dataGrid;
    private ArrayList<SessionMaster> sessions;
    private SelectItem[] asItems;
    private int currentSession;
    private String sessionName;




    public SessionController()
    {
        SessionMaster sm = new SessionDB().getCurrentSession();
        currentSession = sm.getCode();
        System.out.println("In Constructur : "+currentSession);
        sessionName = sm.getName();
    }
    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public void update() {
        ArrayList<SessionMaster> sess = (ArrayList<SessionMaster>) dataGrid.getValue();
        for (SessionMaster sm : sess) {
            System.err.println(sm.getName() + "," + sm.isCurrent());
        }
        new SessionDB().update(sess);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated", ""));

    }

    public int getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(int currentSession) {
        this.currentSession = currentSession;
    }

    public void refresh() {
        System.out.println("Session : " + currentSession);
        new SessionDB().setCurrentSession(currentSession);
    }

    public SelectItem[] getAsItems() {
        if (sessions == null) {
            sessions = new SessionDB().load();
        }
        asItems = new SelectItem[sessions.size()];
        SessionMaster sm = null;
        for (int i = 0; i < sessions.size(); i++) {
            sm = sessions.get(i);
            SelectItem si = new SelectItem(sm.getCode(), sm.toString());
            asItems[i] = si;
        }
        return asItems;
    }

    public void setAsItems(SelectItem[] asItems) {
        this.asItems = asItems;
    }

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }

    public ArrayList<SessionMaster> getSessions() {

        sessions = new SessionDB().load();
        dataGrid.setValue(sessions);
        return sessions;
    }

    public void setSessions(ArrayList<SessionMaster> sessions) {
        this.sessions = sessions;
    }
}
