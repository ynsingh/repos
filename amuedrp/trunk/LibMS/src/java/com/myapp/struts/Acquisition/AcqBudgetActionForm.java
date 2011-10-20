/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts.Acquisition;

/**
 *
 * @author EdRP-05
 */
public class AcqBudgetActionForm extends org.apache.struts.action.ActionForm {

    String budgethead_id;
    String budget_name;
    String button;
    String budgethead_description;
    String opening_balance;
    String recieved_amount;
    String total_amount;
    String financial_yr;
    String remarks;
    String date;
    String estimited_exp;
    String net_bal;

    public String getEstimited_exp() {
        return estimited_exp;
    }

    public void setEstimited_exp(String estimited_exp) {
        this.estimited_exp = estimited_exp;
    }

    public String getNet_bal() {
        return net_bal;
    }

    public void setNet_bal(String net_bal) {
        this.net_bal = net_bal;
    }
    int transaction_id;

    public int getTransaction_id() {

        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

   

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



 public String getBudget_name() {
        return budget_name;
    }

    public void setBudget_name(String budget_name) {
        this.budget_name = budget_name;
    }

    public String getBudgethead_description() {
        return budgethead_description;
    }

    public void setBudgethead_description(String budgethead_description) {
        this.budgethead_description = budgethead_description;
    }

    public String getBudgethead_id() {
        return budgethead_id;
    }

    public void setBudgethead_id(String budgethead_id) {
        this.budgethead_id = budgethead_id;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
  public String getFinancial_yr() {
        return financial_yr;
    }

    public void setFinancial_yr(String financial_yr) {
        this.financial_yr = financial_yr;
    }

    public String getOpening_balance() {
        return opening_balance;
    }

    public void setOpening_balance(String opening_balance) {
        this.opening_balance = opening_balance;
    }

    public String getRecieved_amount() {
        return recieved_amount;
    }

    public void setRecieved_amount(String recieved_amount) {
        this.recieved_amount = recieved_amount;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

   
}
