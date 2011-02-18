/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.smvdu.payroll.beans.model;

import com.graphbuilder.math.Expression;
import com.graphbuilder.math.ExpressionTree;
import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIData;
import org.richfaces.model.SortOrder;
import org.richfaces.model.impl.SimpleGridDataModel;
import org.smvdu.payroll.beans.SalaryData;
import org.smvdu.payroll.beans.SalaryFormula;
import org.smvdu.payroll.beans.db.SalaryFormulaDB;

/**
 *
 * @author Algox
 */
public class MyTableModel extends SimpleGridDataModel
{
    private ArrayList<SalaryFormula> data;

    private UIData myGrid;

    public ArrayList<SalaryFormula> getData() {
        return data;
    }

    public void setData(ArrayList<SalaryFormula> data) {
        this.data = data;
    }

    public UIData getMyGrid() {
        //myGrid.
        return myGrid;
    }

    public void setMyGrid(UIData myGrid) {
        this.myGrid = myGrid;
    }

    public MyTableModel()
    {
        data = new SalaryFormulaDB().loadFormula();
        for(SalaryFormula sf : data)
        {
            if((!(sf.getFormula()==null))&&!sf.getFormula().isEmpty())
            {
                Expression ex = ExpressionTree.parse(sf.getFormula());
                String[] ss = ex.getVariableNames();
                String k = "";
                for(String s : ss)
                {
                    k+=s+" ";
                }
                sf.setFields(k);
            }
        }

    }

    @Override
    public List loadData(int i, int i1, SortOrder so) {

        return data;
    }


    public void update()
    {
       ArrayList<SalaryFormula> datas = (ArrayList<SalaryFormula>) myGrid.getValue();
       for(SalaryFormula sd : datas)
       {
           System.out.println(sd.getName()+" : "+sd.getFormula());
       }
       System.out.println("Saving Data ...");
       new SalaryFormulaDB().save(myGrid);
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getWrappedData() {
        return new SalaryFormula();
    }

    @Override
    public void setWrappedData(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
