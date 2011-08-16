/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

import com.myapp.struts.hbm.*;
import java.util.*;

/**
 *
 * @author System Administrator
 */
public class Approval {
    public static String search(List<AcqApproval> app,AcqBibliographyDetails acqbin){
        for(int i=0;i<app.size();i++){
        if(app.get(i).getControlNo()==acqbin.getId().getControlNo())
        return (String)String.valueOf(app.get(i).getNoOfCopies());
        }
        return null;


    }

}
