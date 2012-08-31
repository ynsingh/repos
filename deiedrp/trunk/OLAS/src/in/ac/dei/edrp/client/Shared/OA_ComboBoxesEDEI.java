/*
 * @(#) OA_ComboBoxesEDEI.java
 *Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in
 * the documentation and/or other materials provided with the
 * distribution.
 *
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL ETRG OR ITS CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL,SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
 * BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Contributors: Members of EdRP, Dayalbagh Educational Institute
 */

package in.ac.dei.edrp.client.Shared;

import in.ac.dei.edrp.client.EdeiAdmission.EDeiSummaryService;
import in.ac.dei.edrp.client.EdeiAdmission.EDeiSummaryServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.form.ComboBox;

/**
 * @version 1.0 9 AUGUST 2012
 * @author UPASANA KULSHRESTHA
 *
 */
public class OA_ComboBoxesEDEI  {
	EDeiSummaryServiceAsync summaryServiceAsync = GWT.create(EDeiSummaryService.class);	
    public final ComboBox statesCB = new ComboBox();
    public final ComboBox progNameUGCB = new ComboBox();
    public final ComboBox progNamePGCB = new ComboBox();
    public final ComboBox progNameOTCB = new ComboBox();
    
    public final ComboBox markGradeCB = new ComboBox();
    
    public final ComboBox genderCB = new ComboBox();
    final ComboBox territoryCB = new ComboBox();
    final ComboBox religionCB = new ComboBox();
    public ComboBox maritalCB = new ComboBox();
    final ComboBox nationalityCB = new ComboBox();
    final ComboBox naturePHCB = new ComboBox();
    public ComboBox phCB = new ComboBox();
    final ComboBox banksCB = new ComboBox();
    final ComboBox flagCB = new ComboBox();
    final ComboBox BoardComboBox = new ComboBox();
    public final ComboBox cityCB = new ComboBox();
    
    final Store markGradeStore=new SimpleStore(new String[]{"value","valueCode"}, getMarksGrade());
    
    final Store genderStore = new SimpleStore(new String[] { "gen", "Gender", "" },
            getGender());
    
    final Store territoryStore = new SimpleStore(new String[] {
                "terr", "Territory", ""
            }, getTerritory());
    
    final Store religionStore = new SimpleStore(new String[] {
                "rel", "Religion", ""
            }, getReligion());
    
    final Store maritalStore = new SimpleStore(new String[] {
                "maritalCode", "marital"
            }, getMarital());
    
    final Store nationalityStore = new SimpleStore(new String[] {
                "nation", "Nationality", ""
            }, getNationality());
    
    final Store naturePHStore = new SimpleStore(new String[] {
                "nature", "NaturePH", ""
            }, getNaturePH());
    
    final Store phStore = new SimpleStore(new String[] { "phCode", "PH"},
            getPH());
    
    final Store banksStore = new SimpleStore(new String[] { "bank", "Bank", "" },
            getBanks());
    
    final Store flagStore = new SimpleStore(new String[] { "flag", "Flag", "" },
            getFlag());
    
    final Store boardStore = new SimpleStore(new String[] { "abbr", "Board", "" },
            getBoard());       

    
    private Object[][] getMarksGrade(){
  	  return new String[][]{
  			  new String[] { "Marks", "MK" },
  	          new String[] { "Grade", "GR" },
  	  };
    }
    
    private Object[][] getGender() {
        return new String[][] {
            new String[] { "M", "Male", "" }, new String[] { "F", "Female", "" },
        };
    }

    private Object[][] getTerritory() {
        return new String[][] {
            new String[] { "u", "Urban", "" }, new String[] { "r", "Rural", "" },
            new String[] { "t", "Tribal", "" },
        };
    }

    private Object[][] getReligion() {
        return new String[][] {
            new String[] { "hin", "Hindu", "" },
            new String[] { "mus", "Muslim", "" },
            new String[] { "sikh", "Sikh", "" },
            new String[] { "ch", "Christian", "" },
            new String[] { "jain", "Jain", "" },
            new String[] { "buddh", "Buddhist", "" },
            new String[] { "par", "Parsi", "" },
            new String[] { "jew", "Jew", "" },
            new String[] { "oth", "Others", "" },
        };
    }

    private Object[][] getMarital() {
        return new String[][] {
            new String[] { "UM", "Un-Married"},
            new String[] { "M", "Married" },
        };
    }

    private Object[][] getNationality() {
        return new String[][] { new String[] { "in", "Indian", "" }, };
    }

    private Object[][] getPH() {
        return new String[][] {
            new String[] { "N", "No", "" }, new String[] { "Y", "Yes", "" },
        };
    }

    private Object[][] getNaturePH() {
        return new String[][] {
            new String[] { "hear", "Hearing Impairment", "" },
            new String[] { "visual", "Visual Impairment", "" },
            new String[] { "loco", "Locomotor Impairment ", "" },
            new String[] { "read", "Reading Disability", "" },
        };
    }

    private Object[][] getBanks() {
        return new String[][] {
            new String[] { "sbi", "State Bank Of India", "" },
            new String[] { "pnb", "Punjab National Bank", "" },
            new String[] { "rucb", "Radhasoami Urban Co-Op. Bank", "" },
            new String[] { "axis", "Axis Bank", "" },
        };
    }
    private Object[][] getFlag() {
        return new String[][] {
            new String[] { "p", "P", "" },
            new String[] { "m", "M", "" },
            new String[] { "b", "B", "" },
           
        };
    }
    
    private Object[][] getBoard() {
        return new String[][] {
            new String[] { "k", "ICSE", "" },
            new String[] { "k", "CBSE", "" },
            new String[] { "n", "Uttar-Pradesh Board", "" },
            new String[] { "Am", "Assam Board", "" },
            
            new String[] { "b", "Bihar Board", "" },
            
            new String[] { "k", "Delhi Board", "" },
            new String[] { "r", "Gujrat Board", "" },
            
            new String[] { "k", "J&K Board", "" },
            new String[] { "k", "Karnataka Board", "" },
            new String[] { "r", "Kerla Board", "" },
            
            new String[] { "n", "Maharashtra Board", "" },
            new String[] { "r", "Mizoram Board", "" },
            new String[] { "k", "Punjab Board", "" },
            new String[] { "k", "Rajasthan Board", "" },
            
        };
    }
public void onStateChange(String state){    
	summaryServiceAsync.getCityData(state, new AsyncCallback<String[][]>() {
		@Override
		public void onFailure(Throwable arg0) {
			MessageBox.alert("Exception in getting city "+arg0.getMessage());	
			
		}
		@Override
		public void onSuccess(String[][] cityObj) {
			if(!cityObj[0][0].equals("FileNotFound")){
					Object[][] cityObject = new String[cityObj.length][2];										
						for(int i=0;i<cityObj.length;i++){
							cityObject[i][0]=cityObj[i][0];
							cityObject[i][1]=cityObj[i][1];							
						}											  
	                          
					Store cityStore = new SimpleStore(new String[] { "cityCode", "cityName"},cityObject);
					cityStore.load();					 					
					cityCB.setStore(cityStore); 			        				 		
				}
				else{					
					MessageBox.alert("File not found for getting state");
				}			
		}
	});
}
    public void onModuleLoad() {    	    	         	    
    	  boardStore.load();
    	  BoardComboBox.setForceSelection(true);
          BoardComboBox.setMinChars(1);
          BoardComboBox.setFieldLabel("Board");
          BoardComboBox.setStore(boardStore);
          BoardComboBox.setDisplayField("Board");
          BoardComboBox.setMode(ComboBox.LOCAL);
          BoardComboBox.setTriggerAction(ComboBox.ALL);
          BoardComboBox.setEmptyText("Choose Board");
          BoardComboBox.setLoadingText("Searching...");
          BoardComboBox.setTypeAhead(true);
          BoardComboBox.setSelectOnFocus(true);
          BoardComboBox.setWidth(130);
       
        statesCB.setForceSelection(true);
        statesCB.setMinChars(1);
        statesCB.setFieldLabel("State");
        statesCB.setDisplayField("State");
        statesCB.setMode(ComboBox.LOCAL);
        statesCB.setTriggerAction(ComboBox.ALL);
        statesCB.setEmptyText("Choose State");
        statesCB.setLoadingText("Searching...");
        statesCB.setTypeAhead(true);
        statesCB.setSelectOnFocus(true);
        statesCB.setWidth(190);
        statesCB.setHideTrigger(false);                  
        
        progNameUGCB.setForceSelection(true);
        progNameUGCB.setMinChars(1);
        progNameUGCB.setFieldLabel("Name");
        progNameUGCB.setDisplayField("Name");
        progNameUGCB.setMode(ComboBox.LOCAL);
        progNameUGCB.setTriggerAction(ComboBox.ALL);
        progNameUGCB.setEmptyText("Select");
        progNameUGCB.setLoadingText("Searching...");
        progNameUGCB.setTypeAhead(true);
        progNameUGCB.setSelectOnFocus(true);
        progNameUGCB.setWidth(190);
        progNameUGCB.setHideTrigger(false);
        
        progNamePGCB.setForceSelection(true);
        progNamePGCB.setMinChars(1);
        progNamePGCB.setFieldLabel("Name");
        progNamePGCB.setDisplayField("Name");
        progNamePGCB.setMode(ComboBox.LOCAL);
        progNamePGCB.setTriggerAction(ComboBox.ALL);
        progNamePGCB.setEmptyText("Select");
        progNamePGCB.setLoadingText("Searching...");
        progNamePGCB.setTypeAhead(true);
        progNamePGCB.setSelectOnFocus(true);
        progNamePGCB.setWidth(190);
        progNamePGCB.setHideTrigger(false);
        
        progNameOTCB.setForceSelection(true);
        progNameOTCB.setMinChars(1);
        progNameOTCB.setFieldLabel("Name");
        progNameOTCB.setDisplayField("Name");
        progNameOTCB.setMode(ComboBox.LOCAL);
        progNameOTCB.setTriggerAction(ComboBox.ALL);
        progNameOTCB.setEmptyText("Select");
        progNameOTCB.setLoadingText("Searching...");
        progNameOTCB.setTypeAhead(true);
        progNameOTCB.setSelectOnFocus(true);
        progNameOTCB.setWidth(190);
        progNameOTCB.setHideTrigger(false);
        
        summaryServiceAsync.getStateData(new AsyncCallback<String[][]>() {
 			public void onFailure(Throwable arg0) {			
 				MessageBox.alert("Exception in getting state "+arg0.getMessage());			
 			}
 			public void onSuccess(String[][] stateObject) {
 				if(!stateObject[0][0].equals("FileNotFound")){
 					String[][] object5 = new String[stateObject.length][2];
 						for(int i=0;i<stateObject.length;i++){
 							object5[i][0]=stateObject[i][0];
 							object5[i][1]=stateObject[i][1]; 							
 						}
 						Object[][] data=new Object[stateObject.length][2];
 						  data=object5;	
 					Store statesStore = new SimpleStore(new String[] { "abbr", "State", "" },data);
 					statesStore.load();
 					statesCB.setStore(statesStore); 			        				 		
 				}
 				else{
 					
 					MessageBox.alert("File not found for getting state");
 				}
 				
 			}
 		});
        
        summaryServiceAsync.getProgramNameUGData(new AsyncCallback<String[][]>() {
 			public void onFailure(Throwable arg0) {			
 				MessageBox.alert("Exception in getting program "+arg0.getMessage());			
 			}
 			public void onSuccess(String[][] progObject) {
 				if(!progObject[0][0].equals("FileNotFound")){
 					String[][] object5 = new String[progObject.length][2];
 						for(int i=0;i<progObject.length;i++){
 							object5[i][0]=progObject[i][0];
 							object5[i][1]=progObject[i][1]; 							
 						}
 						Object[][] data=new Object[progObject.length][2];
 						data=object5;	
 					Store progNameStore = new SimpleStore(new String[] { "Name", "Name", "" },data);
 					progNameStore.load();
 					progNameUGCB.setStore(progNameStore); 			        				 		
 				}
 				else{
 					
 					MessageBox.alert("File not found for getting state");
 				}
 				
 			}
 		});
        
        summaryServiceAsync.getProgramNamePGData(new AsyncCallback<String[][]>() {
 			public void onFailure(Throwable arg0) {			
 				MessageBox.alert("Exception in getting program "+arg0.getMessage());			
 			}
 			public void onSuccess(String[][] progObject) {
 				if(!progObject[0][0].equals("FileNotFound")){
 					String[][] object5 = new String[progObject.length][2];
 						for(int i=0;i<progObject.length;i++){
 							object5[i][0]=progObject[i][0];
 							object5[i][1]=progObject[i][1]; 							
 						}
 						Object[][] data=new Object[progObject.length][2];
 						data=object5;	
 					Store progNameStore = new SimpleStore(new String[] { "Name", "Name", "" },data);
 					progNameStore.load();
 					progNamePGCB.setStore(progNameStore); 			        				 		
 				}
 				else{
 					
 					MessageBox.alert("File not found for getting state");
 				}
 				
 			}
 		});
        
        summaryServiceAsync.getProgramNameOTData(new AsyncCallback<String[][]>() {
 			public void onFailure(Throwable arg0) {			
 				MessageBox.alert("Exception in getting program "+arg0.getMessage());			
 			}
 			public void onSuccess(String[][] progObject) {
 				if(!progObject[0][0].equals("FileNotFound")){
 					String[][] object5 = new String[progObject.length][2];
 						for(int i=0;i<progObject.length;i++){
 							object5[i][0]=progObject[i][0];
 							object5[i][1]=progObject[i][1]; 							
 						}
 						Object[][] data=new Object[progObject.length][2];
 						data=object5;	
 					Store progNameStore = new SimpleStore(new String[] { "Name", "Name", "" },data);
 					progNameStore.load();
 					progNameOTCB.setStore(progNameStore); 			        				 		
 				}
 				else{
 					
 					MessageBox.alert("File not found for getting state");
 				}
 			}
 		});
        
        markGradeStore.load();
        markGradeCB.setForceSelection(true);
        markGradeCB.setMinChars(1);
        markGradeCB.setValueField("valueCode");
        markGradeCB.setFieldLabel("value");
        markGradeCB.setStore(markGradeStore);
        markGradeCB.setDisplayField("value");
        markGradeCB.setMode(ComboBox.LOCAL);
        markGradeCB.setTriggerAction(ComboBox.ALL);
        markGradeCB.setEmptyText("Select");
        markGradeCB.setLoadingText("Searching...");
        markGradeCB.setTypeAhead(true);
        markGradeCB.setSelectOnFocus(true);
        markGradeCB.setWidth(190);
        markGradeCB.setHideTrigger(false);
           
        cityCB.setForceSelection(true);
        cityCB.setMinChars(1);
        cityCB.setFieldLabel("cityName");
        cityCB.setDisplayField("cityName");
        cityCB.setMode(ComboBox.LOCAL);
        cityCB.setTriggerAction(ComboBox.ALL);
        cityCB.setEmptyText("Choose City");
        cityCB.setLoadingText("Searching...");
        cityCB.setTypeAhead(true);
        cityCB.setSelectOnFocus(true);
        cityCB.setWidth(190);
        cityCB.setHideTrigger(false);
        
        genderStore.load();
        genderCB.setForceSelection(true);
        genderCB.setMinChars(1);
        genderCB.setFieldLabel("Gender");
        genderCB.setStore(genderStore);
        genderCB.setDisplayField("Gender");
        genderCB.setMode(ComboBox.LOCAL);
        genderCB.setTriggerAction(ComboBox.ALL);
        genderCB.setEmptyText("Select");
        genderCB.setLoadingText("Searching...");
        genderCB.setTypeAhead(true);
        genderCB.setSelectOnFocus(true);
        genderCB.setWidth(190);
        genderCB.setHideTrigger(false);

        territoryStore.load();
        territoryCB.setForceSelection(true);
        territoryCB.setMinChars(1);
        territoryCB.setFieldLabel("Territory");
        territoryCB.setStore(territoryStore);
        territoryCB.setDisplayField("Territory");
        territoryCB.setMode(ComboBox.LOCAL);
        territoryCB.setTriggerAction(ComboBox.ALL);
        territoryCB.setEmptyText("Select");
        territoryCB.setLoadingText("Searching...");
        territoryCB.setTypeAhead(true);
        territoryCB.setSelectOnFocus(true);
        territoryCB.setWidth(80);
        territoryCB.setHideTrigger(false);

     
        religionStore.load();
        religionCB.setForceSelection(true);
        religionCB.setMinChars(1);
        religionCB.setFieldLabel("Religion");
        religionCB.setStore(religionStore);
        religionCB.setDisplayField("Religion");
        religionCB.setMode(ComboBox.LOCAL);
        religionCB.setTriggerAction(ComboBox.ALL);
        religionCB.setEmptyText("Select");
        religionCB.setLoadingText("Searching...");
        religionCB.setTypeAhead(true);
        religionCB.setSelectOnFocus(true);
        religionCB.setWidth(130);
        religionCB.setHideTrigger(false);

        maritalStore.load();
        maritalCB.setForceSelection(true);
        maritalCB.setMinChars(1);
        /*maritalCB.setFieldLabel("Marital");*/
        maritalCB.setValueField("maritalCode");
        maritalCB.setStore(maritalStore);
        maritalCB.setDisplayField("marital");
        maritalCB.setMode(ComboBox.LOCAL);
        maritalCB.setTriggerAction(ComboBox.ALL);
        maritalCB.setEmptyText("Select");
        maritalCB.setLoadingText("Searching...");
        maritalCB.setTypeAhead(true);
        maritalCB.setSelectOnFocus(true);
        maritalCB.setWidth(135);
        maritalCB.setHideTrigger(false);

        nationalityStore.load();
        nationalityCB.setForceSelection(true);
        nationalityCB.setMinChars(1);
        nationalityCB.setFieldLabel("Nationality");
        nationalityCB.setStore(nationalityStore);
        nationalityCB.setDisplayField("Nationality");
        nationalityCB.setMode(ComboBox.LOCAL);
        nationalityCB.setTriggerAction(ComboBox.ALL);
        nationalityCB.setEmptyText("Select");
        nationalityCB.setLoadingText("Searching...");
        nationalityCB.setTypeAhead(true);
        nationalityCB.setSelectOnFocus(true);
        nationalityCB.setWidth(130);
        nationalityCB.setHideTrigger(false);

        phStore.load();
        phCB.setForceSelection(true);
        phCB.setMinChars(1);
        //phCB.setFieldLabel("PH");
        phCB.setValueField("phCode");
        phCB.setStore(phStore);
        phCB.setDisplayField("PH");
        phCB.setMode(ComboBox.LOCAL);
        phCB.setTriggerAction(ComboBox.ALL);
        phCB.setEmptyText("Select");
        phCB.setLoadingText("Searching...");
        phCB.setTypeAhead(true);
        phCB.setSelectOnFocus(true);
        phCB.setWidth(135);
        phCB.setHideTrigger(false);

        naturePHStore.load();
        naturePHCB.setForceSelection(true);
        naturePHCB.setMinChars(1);
        naturePHCB.setFieldLabel("NaturePH");
        naturePHCB.setStore(naturePHStore);
        naturePHCB.setDisplayField("NaturePH");
        naturePHCB.setMode(ComboBox.LOCAL);
        naturePHCB.setTriggerAction(ComboBox.ALL);
        naturePHCB.setEmptyText("Select");
        naturePHCB.setLoadingText("Searching...");
        naturePHCB.setTypeAhead(true);
        naturePHCB.setSelectOnFocus(true);
        naturePHCB.setWidth(135);
        naturePHCB.setHideTrigger(false);

        banksStore.load();
        banksCB.setForceSelection(true);
        banksCB.setMinChars(1);
        banksCB.setFieldLabel("BankPH");
        banksCB.setStore(banksStore);
        banksCB.setDisplayField("Bank");
        banksCB.setMode(ComboBox.LOCAL);
        banksCB.setTriggerAction(ComboBox.ALL);
        banksCB.setEmptyText("Select");
        banksCB.setLoadingText("Searching...");
        banksCB.setTypeAhead(true);
        banksCB.setSelectOnFocus(true);
        banksCB.setWidth(190);
        banksCB.setHideTrigger(false);
        
        flagStore.load();
        flagCB.setForceSelection(true);
        flagCB.setMinChars(1);
        flagCB.setFieldLabel("Flag");
        flagCB.setStore(flagStore);
        flagCB.setDisplayField("Flag");
        flagCB.setMode(ComboBox.LOCAL);
        flagCB.setTriggerAction(ComboBox.ALL);
        flagCB.setEmptyText("Select");
        flagCB.setLoadingText("Searching...");
        flagCB.setTypeAhead(true);
        flagCB.setSelectOnFocus(true);
        flagCB.setWidth(70);
        flagCB.setHideTrigger(false);
                
    }
}
