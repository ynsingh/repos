/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.setup;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 *  Copyright (c) 2010 - 2011.2014 SMVDU, Katra.
 *  Copyright (c) 2014 - 2016 ETRG, IITK.
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
 **
 *
 * @author : Om Prakash (omprakashkgp@gmail.com), IITK
 * Date of Creation : November, 2016.
 */

public class CollegePendingStatus implements Serializable {

    private int cpsid;
    private int orgcode;
    private boolean orgstatus;
    private String orgemail;
    private ArrayList<Org> og;

    public CollegePendingStatus(){}

    public CollegePendingStatus(int cpsid, int orgcode, boolean orgstatus, String orgemail) {
        this.cpsid = cpsid;
        this.orgcode = orgcode;
        this.orgstatus = orgstatus;
        this.orgemail = orgemail;
    }
        
    public int getCpsid() {
        return cpsid;
    }

    public void setCpsid(int cpsid) {
        this.cpsid = cpsid;
    }

    public int getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(int orgcode) {
        this.orgcode = orgcode;
    }

    public boolean isOrgstatus() {
        return orgstatus;
    }

    public void setOrgstatus(boolean orgstatus) {
        this.orgstatus = orgstatus;
    }

    public String getOrgemail() {
        return orgemail;
    }

    public void setOrgemail(String orgemail) {
        this.orgemail = orgemail;
    }

    public ArrayList<Org> getOg() {
        return og;
    }

    public void setOg(ArrayList<Org> og) {
        this.og = og;
    }

}
