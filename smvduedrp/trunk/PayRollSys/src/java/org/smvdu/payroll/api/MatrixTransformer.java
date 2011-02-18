/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.api;

import java.util.ArrayList;
import org.smvdu.payroll.api.controller.GrossData;
import org.smvdu.payroll.beans.db.SalaryHeadDB;

/**
 *
 * @author Algox
 */
public class MatrixTransformer {

    

    public static ArrayList<GrossData> transform(Integer[][] original,String empCode)
    {
        ArrayList<String> heads = new SalaryHeadDB().getApplicableHeads(empCode);
        ArrayList<GrossData> copy = new ArrayList<GrossData>();
        for(int i=0;i<original.length;i++)
        {
           GrossData gd = new GrossData();
           Integer[] ids = new Integer[13];
           System.arraycopy(original[i], 0, ids, 0, 12);
           gd.setSalaryHead(heads.get(i));
           Integer total = 0;
           for(int k=0;k<12;k++)
           {
               total+=ids[k];
           }
           ids[12] = total;
           gd.setSalaryData(ids);
           copy.add(gd);           
        }
          
          return copy;
        
    }

}
