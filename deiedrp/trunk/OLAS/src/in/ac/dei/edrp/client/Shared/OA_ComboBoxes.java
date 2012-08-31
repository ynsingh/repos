package in.ac.dei.edrp.client.Shared;

import in.ac.dei.edrp.client.RPCFiles.CM_connectTemp;
import in.ac.dei.edrp.client.RPCFiles.CM_connectTempAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.form.ComboBox;

/**
 *
 * @author AnshikaGupta
 *
 */
public class OA_ComboBoxes  {
	 private final CM_connectTempAsync connectTemp = (CM_connectTempAsync) GWT.create(CM_connectTemp.class);	
    public final ComboBox statesCB = new ComboBox();
    public final ComboBox genderCB = new ComboBox();
    final ComboBox territoryCB = new ComboBox();
    final ComboBox religionCB = new ComboBox();
    final ComboBox maritalCB = new ComboBox();
    final ComboBox nationalityCB = new ComboBox();
    final ComboBox naturePHCB = new ComboBox();
    final ComboBox phCB = new ComboBox();
    final ComboBox banksCB = new ComboBox();
    final ComboBox flagCB = new ComboBox();
    final ComboBox BoardComboBox = new ComboBox();
    public final ComboBox cityCB = new ComboBox();
    final Store genderStore = new SimpleStore(new String[] { "gen", "Gender", "" },
            getGender());
    final Store territoryStore = new SimpleStore(new String[] {
                "terr", "Territory", ""
            }, getTerritory());
    final Store religionStore = new SimpleStore(new String[] {
                "rel", "Religion", ""
            }, getReligion());
    final Store maritalStore = new SimpleStore(new String[] {
                "marital", "Marital", ""
            }, getMarital());
    final Store nationalityStore = new SimpleStore(new String[] {
                "nation", "Nationality", ""
            }, getNationality());
    final Store naturePHStore = new SimpleStore(new String[] {
                "nature", "NaturePH", ""
            }, getNaturePH());
    final Store phStore = new SimpleStore(new String[] { "ph", "PH", "" },
            getPH());
    final Store banksStore = new SimpleStore(new String[] { "bank", "Bank", "" },
            getBanks());
    final Store flagStore = new SimpleStore(new String[] { "flag", "Flag", "" },
            getFlag());
    final Store boardStore = new SimpleStore(new String[] { "abbr", "Board", "" },
            getBoard());       

    private Object[][] getGender() {
        return new String[][] {
            new String[] { "m", "Male", "" }, new String[] { "f", "Female", "" },
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
            new String[] { "unm", "Un-Married", "" },
            new String[] { "mar", "Married", "" },
        };
    }

    private Object[][] getNationality() {
        return new String[][] { new String[] { "in", "Indian", "" }, };
    }

    private Object[][] getPH() {
        return new String[][] {
            new String[] { "n", "No", "" }, new String[] { "Y", "Yes", "" },
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
    connectTemp.getCityData(state, new AsyncCallback<String[][]>() {
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
        
        
        connectTemp.getStateData(new AsyncCallback<String[][]>() {
 			@Override
 			public void onFailure(Throwable arg0) {			
 				MessageBox.alert("Exception in getting state "+arg0.getMessage());			
 			}

 			@Override
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
        maritalCB.setFieldLabel("Marital");
        maritalCB.setStore(maritalStore);
        maritalCB.setDisplayField("Marital");
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
        phCB.setFieldLabel("PH");
        phCB.setStore(phStore);
        phCB.setDisplayField("PH");
        phCB.setMode(ComboBox.LOCAL);
        phCB.setTriggerAction(ComboBox.ALL);
        phCB.setEmptyText("No");
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
