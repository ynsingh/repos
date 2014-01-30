/*
 * 
 *  Copyright (c) 2011 eGyankosh, IGNOU, New Delhi.
 *  All Rights Reserved.
 *
 *  Redistribution and use in source and binary forms, with or
 *  without modification, are permitted provided that the following
 *  conditions are met:
 *
 *  Redistributions of source code must retain the above copyright
 *  notice, this  list of conditions and the following disclaimer.
 *
 *  Redistribution in binary form must reproducuce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL eGyankosh, IGNOU OR ITS CONTRIBUTORS BE LIABLE
 *  FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 *  OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 *  BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *  WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 *  OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 *  EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *  Contributors: Members of eGyankosh, IGNOU, New Delhi.
 *
 */
package org.IGNOU.ePortfolio.MyWorkspace;

import com.opensymphony.xwork2.ActionSupport;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import org.IGNOU.ePortfolio.Action.UserSession;
import org.IGNOU.ePortfolio.DAO.MyNoteDao;
import org.IGNOU.ePortfolio.Model.MyNotes;
import org.apache.log4j.Logger;

/**
 *
 * @author IGNOU Team
 */
public class MyNotesInfoAction extends ActionSupport implements Serializable  {

    private static final long serialVersionUID = 1L;
    final Logger logger = Logger.getLogger(this.getClass());
    private String user_id = new UserSession().getUserInSession();
    private MyNoteDao notesDao = new MyNoteDao();
    private List<MyNotes> noteslistlist;
    private List<MyNotes> editNoteList;
    private long notesId;
    private String userId;
    private String note;
    Calendar c_Date = Calendar.getInstance();
    SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
    private String date = f.format(c_Date.getTime());
    private String topic;
    private String msg;
    private String infoDeleted = getText("msg.infoDeleted");
    private String infoUpdated = getText("msg.infoUpdated");

    public String ShowNotesInfo() throws Exception {
        noteslistlist = notesDao.MyNotesListByUserId(user_id);
        if (noteslistlist.isEmpty()) {
            return INPUT;
        } else {
            return SUCCESS;
        }
    }

    public String EditNoteInfo() {
        editNoteList = notesDao.MyNotesListByNotesId(notesId);
        return SUCCESS;
    }

    public String DeleteNotesInfo() throws Exception {
        notesDao.MyNotesDelete(notesId);
        msg = infoDeleted;
        return SUCCESS;
    }

    public String UpdateNotesInfo() throws Exception {
        notesDao.MyNotesUpdate(getNotesId(), getUserId(), getNote(), getDate(), getTopic());
        msg = infoUpdated;
        return SUCCESS;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the notesDao
     */
    public MyNoteDao getNotesDao() {
        return notesDao;
    }

    /**
     * @param notesDao the notesDao to set
     */
    public void setNotesDao(MyNoteDao notesDao) {
        this.notesDao = notesDao;
    }

    /**
     * @return the noteslistlist
     */
    public List<MyNotes> getNoteslistlist() {
        return noteslistlist;
    }

    /**
     * @param noteslistlist the noteslistlist to set
     */
    public void setNoteslistlist(List<MyNotes> noteslistlist) {
        this.noteslistlist = noteslistlist;
    }

    /**
     * @return the notesId
     */
    public long getNotesId() {
        return notesId;
    }

    /**
     * @param notesId the notesId to set
     */
    public void setNotesId(long notesId) {
        this.notesId = notesId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * @return the editNoteList
     */
    public List<MyNotes> getEditNoteList() {
        return editNoteList;
    }

    /**
     * @param editNoteList the editNoteList to set
     */
    public void setEditNoteList(List<MyNotes> editNoteList) {
        this.editNoteList = editNoteList;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the infoDeleted
     */
    public String getInfoDeleted() {
        return infoDeleted;
    }

    /**
     * @param infoDeleted the infoDeleted to set
     */
    public void setInfoDeleted(String infoDeleted) {
        this.infoDeleted = infoDeleted;
    }

    /**
     * @return the infoUpdated
     */
    public String getInfoUpdated() {
        return infoUpdated;
    }

    /**
     * @param infoUpdated the infoUpdated to set
     */
    public void setInfoUpdated(String infoUpdated) {
        this.infoUpdated = infoUpdated;
    }
}
