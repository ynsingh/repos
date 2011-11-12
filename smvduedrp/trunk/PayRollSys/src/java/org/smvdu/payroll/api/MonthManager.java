/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api;

import java.util.ArrayList;
import javax.faces.component.UIData;
import org.smvdu.payroll.beans.BaseBean;

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
public class MonthManager {

    private ArrayList<BaseBean> months =new ArrayList<BaseBean>();
    public  MonthManager()
    {
        months.add(new BaseBean(1, "January"));
        months.add(new BaseBean(2, "February"));
        months.add(new BaseBean(3, "March"));
        months.add(new BaseBean(4, "Aril"));
        months.add(new BaseBean(5, "May"));
        months.add(new BaseBean(6, "Jane"));
        months.add(new BaseBean(7, "July"));
        months.add(new BaseBean(8, "August"));
        months.add(new BaseBean(9, "September"));
        months.add(new BaseBean(10, "October"));
        months.add(new BaseBean(11, "November"));
        months.add(new BaseBean(12, "December"));
    }


    public ArrayList<BaseBean> getMonths() {
        return months;
    }

    public void setMonths(ArrayList<BaseBean> months) {
        this.months = months;
    }

    private UIData dataGrid;

    public UIData getDataGrid() {
        return dataGrid;
    }

    public void setDataGrid(UIData dataGrid) {
        this.dataGrid = dataGrid;
    }
    
    

}
