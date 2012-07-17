// ActionScript file
/**
 * @(#) globalscript.as
 * Copyright (c) 2011 EdRP, Dayalbagh Educational Institute.
 * All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or
 * without modification, are permitted provided that the following
 * conditions are met:
 *
 * Redistributions of source code must retain the above copyright
 * notice, this  list of conditions and the following disclaimer.
 *
 * Redistribution in binary form must reproducuce the above copyright
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
                  import mx.collections.ArrayCollection;
                  import mx.controls.Alert;
                  import mx.events.ItemClickEvent;

                  /* Viewing pages */
                  [Bindable] public var startAlert:uint;
                  [Bindable] public var endAlert:uint;
                  [Bindable] public var totalAlert:uint;
                  
                  [Bindable] public var myData:ArrayCollection = new ArrayCollection();
                  
                  public var orgData:ArrayCollection = new ArrayCollection();
				  public var ArrData:ArrayCollection = new ArrayCollection;
                  
                  [Bindable] public var nav:ArrayCollection = new ArrayCollection();
                  
                  /* Records per page */
                  private var pageSize:uint = 0; 
				  private var intPages:uint = 0;
    			  private var CurrPage:uint = 0;
                  
                  /* Number of pages per view */
                  public var navSize:uint = 2;
                  
                  private var index:uint = 0;
                  private var navPage:uint = 1;
                  
                  /* Test data */
                  private function InitApp():void
                  {     
        //                orgData = gridRecords;
        //                ArrData = gridRecords;
                        
                        
				        pageSize = (programCourseGrid.height/programCourseGrid.rowHeight)-1 ;        
				        //wow dynamic pazeSize !
				        
				        intPages = Math.ceil(orgData.length/pageSize);
						//let's calc the intPages
										
				        refreshDataProvider(index);
				        //refresh the data provider
				        
				        if(intPages<=1){ //less then one page ?
				            intPages = 1;
				            createNavBar(1);
				        }else{ //more pages !!!!
				            createNavBar(intPages);    
				        }

                  }
                  
                  /* Create pagination */
                  private function createNavBar(pages:uint,intSet:uint = 0):void{
                        nav.removeAll();
                        if( intSet > 1 ){
                              nav.addItem({label:"<< First",data:0});
                              
                              var intLFive:int = intSet-navSize; // calculate start of last 5;
                              
                              nav.addItem({label:"< Previous",data:intLFive});
                        }
                              
                      for( var x:uint = 0; x < navSize; x++){
                            var pg:uint = x + intSet;
                            nav.addItem({label: pg + 1,data: pg});
							
							var pgg:uint = pg+1;
							if(pgg>=intPages){ //if you reach the last page stop adding linkselectors on the navbar
								x=navSize; 
							}
                      }
					  
					  var lastpage:Number = 0;
					  for( var y:uint = navSize; y <= pages-1;y = y + navSize ){ //lets calculate the lastpage button
					  	if(y+5 > navSize){
					  		lastpage = y;
					  	}
					  	
					  }
					  
					  
					  
                      if( pg < pages - 1 ){
                            nav.addItem({label:"Next >",data:pg + 1});
                            nav.addItem({label:"Last >>",data:lastpage});
                      }                              
                    }
                  
                  /* Refresh data per page groups */
                  private function navigatePage(event:ItemClickEvent):void
                  {
                        for each(var obj:Object in myData)
						{
							obj.Select=false;
						}
                        
                        refreshDataProvider(event.item.data); 
                        
                        var lb:String = event.item.label.toString(); //event.item.data:- when a link button is clicked in the navigation bar, we get the id of clicked button
                        if( lb.indexOf("<") > -1 || lb.indexOf(">") > -1 )
                        {
                              //createNavBar(Math.ceil(orgData.length/pageSize),event.item.data);
                              createNavBar(Math.ceil(ArrData.length/pageSize),event.item.data);
                        }
                        
                  }
                  
                  private function refreshDataProvider(start:uint):void{
                  	
                  	myData = new ArrayCollection( ArrData.source.slice((start * pageSize),(start * pageSize) + pageSize) );
                  	
                  	
				//	programCourseGrid.dataProvider=myData;
					
               	    viewingAlertsLabel1.text = 'Page ' + (start+1).toString() + ' of ' + intPages.toString();
              	    viewingAlertsLabel2.text = 'Total items ' + orgData.length.toString();
                  }
                                    
                 
                  private function viewingAlerts ():void {
                        startAlert == ((navPage - 1) * pageSize + 1);
                        endAlert == ((navPage - 1) * pageSize + pageSize);
                        totalAlert == myData.length;
                  }
                  