/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.smvdu.payroll.beans.composite;

import com.graphbuilder.math.Expression;
import com.graphbuilder.math.ExpressionTree;
import com.graphbuilder.math.FuncMap;
import com.graphbuilder.math.VarMap;
import org.smvdu.payroll.beans.*;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.smvdu.payroll.api.FormattedDate;
import org.smvdu.payroll.api.SalaryDataHelper;
import org.smvdu.payroll.api.security.SalaryLockDB;
import org.smvdu.payroll.beans.db.EmployeeDB;
import org.smvdu.payroll.beans.db.SalaryDataDB;
import org.smvdu.payroll.beans.db.SalaryHeadDB;

/**
 *
 * @author Algox
 */
public class NewSalaryProcessing {


    private int numberOfdays=30;

    private int typeCode = -1;
    private String empCode;
    private UIData incomeGrid;
    private UIData deductGrid;
    private Employee employee;
    private String copyDate;

    private boolean copyAllowed;

    public boolean isCopyAllowed() {
        if(empCode==null)
        {
            copyAllowed=false;
        }
        if(locked)
        {
            copyAllowed = false;
        }
        
        return copyAllowed;
    }

    public void setCopyAllowed(boolean copyAllowed) {
        this.copyAllowed = copyAllowed;
    }



    public String getCopyDate() {
        return copyDate;
    }

    public void setCopyDate(String copyDate) {
        this.copyDate = copyDate;
    }

    public void copyData()
    {
        System.err.println("Copying Salary Data ...");
        new SalaryDataDB().copyLastMonthData(empCode,copyDate);
        loadProfile();
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary Data Copied.", ""));
    }

    private SelectItem[] copyDates;

    private void loadCopyDates()
    {
        ArrayList<FormattedDate> fds = new SalaryDataHelper().getCopyDate(empCode);
        copyDates = new SelectItem[fds.size()];
        for(int i=0;i<fds.size();i++)
        {
            SelectItem si = new SelectItem(fds.get(i).getValueString(), fds.get(i).getDisplayString());
            copyDates[i] = si;
        }
    }
    public SelectItem[] getCopyDates() {
        loadCopyDates();
        return copyDates;
    }

    public void setCopyDates(SelectItem[] copyDates) {
        this.copyDates = copyDates;
    }



    private ArrayList<SalaryHead> incomeHeads;
    private ArrayList<SalaryHead> deductHeads;
    private boolean locked;

    public boolean isLocked() {
        return new SalaryLockDB().isLocked(empCode);
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    


     ArrayList<SalaryData> combinedData ;
    

    private int totalIncome=0;
    private int totalDeduct=0;
    private int gorssTotal;

    public int getGorssTotal() {
        return gorssTotal;
    }

    public void setGorssTotal(int gorssTotal) {
        this.gorssTotal = gorssTotal;
    }
    

    public int getTotalDeduct() {
        return totalDeduct;
    }

    public void setTotalDeduct(int totalDeduct) {
        this.totalDeduct = totalDeduct;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }

    
    public int getNumberOfdays() {
        return numberOfdays;
    }

    public void setNumberOfdays(int numberOfdays) {
        this.numberOfdays = numberOfdays;
    }
    public ArrayList<SalaryHead> getDeductHeads() {
        return deductHeads;
    }
    public void setDeductHeads(ArrayList<SalaryHead> deductHeads) {
        this.deductHeads = deductHeads;
    }
    public ArrayList<SalaryHead> getIncomeHeads() {        
        return incomeHeads;
    }
    public void setIncomeHeads(ArrayList<SalaryHead> incomeHeads) {

        this.incomeHeads = incomeHeads;
    }
    public String getEmpCode() {
        return empCode;
    }
   
    public void setEmpCode(String empCode) {
        this.empCode = empCode.toUpperCase();
    }


    public void scale()   {
        System.err.println("Scaling by "+numberOfdays);
        if(combinedData==null)
        {
            return;
        }
        combinedData.clear();
        combinedData = new SalaryDataDB().loadCurrentSalaryData(empCode);
        
        for(SalaryData sd : combinedData)
        {
            if(sd.isScalable())
            {
                System.err.println("Scalable field name : "+sd.getHeadName());
                sd.setHeadValue((sd.getHeadValue()*numberOfdays)/30);
                System.err.println("Scalable field Value : "+sd.getHeadValue());
            }
        }
        refreshModel();
    }
    public void processFormula(ArrayList<SalaryData> data)  {
        System.out.println("Processing Formula ...");
        VarMap map = new VarMap(false);
        //map.setValue("basic", currentBasic);
        FuncMap fm = null;
        for(SalaryData sd : data)
        {
            //System.err.println(sd.getAlias()+","+sd.getFormula()+","+sd.getHeadCode());
            if(sd.getFormula()!=null&&!sd.getFormula().isEmpty())
            {
                Expression x = ExpressionTree.parse(sd.getFormula());
                int val = (int) x.eval(map, fm);
                map.setValue(sd.getAlias(),val);
                sd.setHeadValue(val);
            }
         else
            {
                map.setValue(sd.getAlias(), sd.getHeadValue());
            }
        }
    }
    private void refreshModel()   {
            totalDeduct=0;
            totalIncome=0;
            incomeHeads = new ArrayList<SalaryHead>();
            deductHeads=new ArrayList<SalaryHead>();
            processFormula(combinedData);
            for (SalaryData sd : combinedData) {
                SalaryHead sh = new SalaryHead();
                sh.setName(sd.getHeadName());
                sh.setUnder(sd.isCatagory());
                sh.setNumber(sd.getHeadCode());
                sh.setDefaultValue(sd.getHeadValue());
                sh.setFormula(sd.getFormula());
                sh.setAlias(sd.getAlias());
                if (sd.isCatagory()) {
                    incomeHeads.add(sh);
                    totalIncome+=sh.getDefaultValue();
                } else {
                    deductHeads.add(sh);
                    totalDeduct+=sh.getDefaultValue();
                }

                gorssTotal = totalIncome-totalDeduct;
            }
    }
    public void loadProfile() {
        if(deductHeads!=null)
        deductHeads.clear();
        if(incomeHeads!=null)
        incomeHeads.clear();
        if(combinedData!=null)
        combinedData.clear();
        employee = new EmployeeDB().loadProfile(empCode);
        if (employee != null) {
         //   loadCopyDates();
            //currentBasic = employee.getCurrentBasic();
            combinedData = new SalaryDataDB().loadCurrentSalaryData(empCode);
            if (combinedData != null&&combinedData.size()>0) {
                System.out.println("Data exist. Loading ...");
                refreshModel();
                
            } else {
                typeCode = employee.getType();
                System.out.println("Data Not exist. Loading Defaults...");
                incomeHeads = new SalaryHeadDB().loadSelectedIncomeHeads(typeCode);                
                deductHeads = new SalaryHeadDB().loadSelectedDeductionHeads(typeCode);
            }
                deductGrid.setValue(deductHeads);
                incomeGrid.setValue(incomeHeads);

        }
    }
    public Employee getEmployee() {

        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public int getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(int typeCode) {
        this.typeCode = typeCode;
    }
    public void setDeductGrid(UIData deductGrid) {

        this.deductGrid = deductGrid;
    }
    public void setIncomeGrid(UIData incomeGrid) {
        this.incomeGrid = incomeGrid;
    }
    public UIData getDeductGrid() {        
        return deductGrid;
    }
    public UIData getIncomeGrid() {
        return incomeGrid;
    }
    public void save(ArrayList<SalaryHead> heads) {
        new SalaryHeadDB().updateDefault(heads, typeCode);
    }
    public void updateData() {       
        System.out.println("Updating Salary data ...." + empCode);
        //FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary data Updated", ""));        
        System.out.println("Code :" + empCode);
        ArrayList<SalaryHead> incomes = (ArrayList<SalaryHead>) incomeGrid.getValue();
        System.out.println("Size : " + incomes.size());
        ArrayList<SalaryData> datas = new ArrayList<SalaryData>();
        Employee emp = new Employee();
        emp.setCode(empCode.toUpperCase());
        totalDeduct=0;
        totalIncome=0;
        for (SalaryHead sh : incomes) {
            SalaryData sd = new SalaryData();
            System.err.println("Code : "+sh.getNumber()+", "+sh.getDefaultValue());
            sd.setEmployee(emp);
            sd.setHeadCode(sh.getNumber());
            sd.setHeadValue(sh.getDefaultValue());
            datas.add(sd);
            totalIncome+=sh.getDefaultValue();
        }
        //new SalaryDataDB().save(datas);
        incomes = (ArrayList<SalaryHead>) deductGrid.getValue();
        System.out.println("Deduct Size : " + incomes.size());
        for (SalaryHead sh : incomes) {
            SalaryData sd = new SalaryData();
            System.err.println("Code : "+sh.getNumber()+", "+sh.getDefaultValue());
            sd.setEmployee(emp);
            sd.setHeadCode(sh.getNumber());
            sd.setHeadValue(sh.getDefaultValue());
            datas.add(sd);
            totalDeduct+=sh.getDefaultValue();
        }
        gorssTotal =totalIncome-totalDeduct;
        SalaryDataDB sdb = new SalaryDataDB();
        sdb.save(datas);
        sdb.saveSummary(totalIncome, totalDeduct, gorssTotal, empCode);
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Salary data Updated", ""));
    }
}
