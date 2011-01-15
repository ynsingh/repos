/*
 * Deepak Pandey
 * Write CopyRight Information here 
 * 
 */
package in.ac.dei.edrp.client.ReportGeneration;

import in.ac.dei.edrp.client.DataBean;
import in.ac.dei.edrp.client.Shared.constants;
import in.ac.dei.edrp.client.Shared.messages;

import java.util.Iterator;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.PagingToolbar;
import com.gwtext.client.widgets.ToolTip;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.event.FieldListenerAdapter;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtextux.client.data.PagingMemoryProxy;

/*
 * It creates grid after computing the marks for selected entity,program and branch.
 */
public class StudentGridList {

	public GridPanel grid = new GridPanel();// 
	private final constants constant=GWT.create(constants.class);/*for using property files*/
	private final messages message=GWT.create(messages.class);/*for using property files*/
	/*
	 * Default Constructors
	 */
	public StudentGridList(){
		
	}
	/*
	 * Parametric constructor
	 * It sets the data inside Grid and finally this grid will be displayed on screen.
	 */
	/**
	 * @author Deepak
	 * @param list: List contains students record
	 */
	public StudentGridList(List<DataBean> list){
		//System.out.println("Coming here inside list");
		final String[][] object2;
    	//Iterate list which returns from server
    	//Use of this iterator: To get Column Names
		
		Iterator<DataBean> itr1=list.iterator();
        itr1.hasNext();
        DataBean gd1=(DataBean)itr1.next();
        //sdf[]: to hold column names
        String[] s1=gd1.getDetail();
        String[][] heading=gd1.getCompData();
        int length=heading.length*(heading[0].length);
        String[] sdf=new String[3+s1.length+length];
         sdf[0]=constant.registrationNumber();
         int count=0;
         for(int k=0;k<(s1.length-1);k++){
        	 String detailheading="";
        	 switch (k) {
			case 0:{
				detailheading=constant.stduentName();
				break;
			}
			case 1:{
				detailheading=constant.dateOfBirth();
				break;
			}
			case 2:{
				detailheading=constant.category();
				break;
			}
			case 3:{
				detailheading=constant.gender();
				break;
			}
			default:
				break;
			}
         sdf[k+1]=detailheading;
         
         count++;
         }//For Loop ends
        
         for(int k=0,m=0;k<heading.length;k++){
        	 int l=m+count;
         	sdf[l+1]=constant.componentName()+" "+String.valueOf(k+1);
         	sdf[l+2]=constant.board()+" "+String.valueOf(k+1);
         	sdf[l+3]=""+constant.percentage()+" "+String.valueOf(k+1);
         	sdf[l+4]=constant.computeMarksHeading()+" "+String.valueOf(k+1);
         	count=count+4;
         	
         }//For Loop with K ends
         sdf[count+1]=constant.totalMarks();
         
         sdf[count+2]=constant.status();
         
         sdf[count+3]=constant.reasonCode();

        // System.out.println("-------------------------------------------");
    	//get Column name first,create your bean with column name
        //FieldDefinition
        
         FieldDef[] def=new FieldDef[sdf.length];
         for(int defl=0;defl<def.length;defl++){
        	 def[defl]=new StringFieldDef(sdf[defl]);
         }
        //RecordDefinition
    	final RecordDef recordDef = new RecordDef(def);//RecordDef
    	
        object2 = new String[list.size()][sdf.length];

        String[][] data=object2;

        Iterator<DataBean> itr=list.iterator();
        
        
        //itr.next();
        //while(itr.hasNext()){
        int i=0;
        while(itr.hasNext()){
        
        count=0;
        DataBean gd=(DataBean)itr.next();
        data[i][0]=gd.getRegistrationNumber();
        String[] detail=gd.getDetail();
        String[][] componentMarks=gd.getCompData();
        for(int k=0;k<(detail.length-1);k++){
         	data[i][k+1]=detail[k];
         	count++;
         }//k loop ends
       
        for(int k=0,m=0;k<componentMarks.length;k++){
        	int x=m+count;
        	for(int l=0;l<componentMarks[k].length;l++){
        	data[i][(x+1)+l]=componentMarks[k][l];
        	//System.out.println("values are : "+componentMarks[k][l]+" and "+((x+1)+l));
        	}
        	count=count+4;
        }//For Loop ends for components Marks 
        data[i][count+1]=String.valueOf(gd.getSum_cm());
        
        data[i][count+2]=gd.getStatus();
        
        data[i][count+3]=gd.getReason();
        
        i++;
        }//Iterator ends
        //Final data in data[] array
        PagingMemoryProxy proxy = new PagingMemoryProxy(data);
        ArrayReader reader = new ArrayReader(recordDef);
        final Store store = new Store(proxy, reader,true);
        store.load(0,5);
        //Here we have final data in store
        //creating column config
        
        ColumnConfig[] commonCol=new ColumnConfig[sdf.length];
        //commonCol[0].setEditor(null);
        //commonCol[0]= new ColumnConfig(sdf[0], sdf[0], 220, true, null, sdf[0]);
    	//commonCol[d].setEditor(new GridEditor(new TextField()));
    	//setting editor for columns so that user can edit
        for(int d=0;d<commonCol.length;d++){
        	commonCol[d]= new ColumnConfig(sdf[d], sdf[d], 220, true, null, sdf[d]);
        	commonCol[d].setSortable(true);
         } 
       // final BaseColumnConfig[] columns = commonCol;
      //creating ColumnModel
        final ColumnModel columnModel = new ColumnModel(commonCol);
        
        columnModel.setDefaultSortable(true); 
	
  //Creating object of editorgridpanel
 // GridPanel grid=new GridPanel(store,columnModel);	
//setting title, clicks to edit,frame, size, StripeRows, TrackMouseOver and visible
        		grid.setTitle(constant.titleStudentGrid());
		        grid.setStore(store);
		        grid.setColumnModel(columnModel);
		        grid.setFrame(true);  
		        grid.setStripeRows(true);  
		        for(int alen=0;alen<sdf.length;alen++){
		        	grid.setAutoExpandColumn(sdf[alen]);
		        }
		        grid.setWidth(800);  
	            grid.setHeight(350);  
	              
		        final PagingToolbar pagingToolbar = new PagingToolbar(store);  
	            pagingToolbar.setPageSize(4);  
	           pagingToolbar.setDisplayInfo(true);  
	            pagingToolbar.setDisplayMsg("Displaying data {0} - {1} of {2}");  
	            pagingToolbar.setEmptyMsg("No records to display");  
	      
	            TextField pageSizeField = new TextField();  
	            pageSizeField.setWidth(40);  
	            pageSizeField.setSelectOnFocus(true);  
	            pageSizeField.addListener(new FieldListenerAdapter() {  
	                public void onSpecialKey(Field field, EventObject e) {  
	                    if (e.getKey() == EventObject.ENTER) {  
	                      int pageSize = Integer.parseInt(field.getValueAsString());  
	                         pagingToolbar.setPageSize(pageSize);  
	                    }  
	                }  
	            });  
	      
	            ToolTip toolTip = new ToolTip("Enter page size");  
	            toolTip.applyTo(pageSizeField);  
	            pagingToolbar.addField(pageSizeField);  
	            grid.setBottomToolbar(pagingToolbar);  
	            store.load(0, 5);  

		 
		   
		         
	}

	public GridPanel createStudentGridPanel() {
		// TODO Auto-generated method stub
		return grid;
	}
}
